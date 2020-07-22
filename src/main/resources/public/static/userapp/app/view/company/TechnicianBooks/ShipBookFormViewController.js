/*
 * File: app/view/company/TechnicianBooks/ShipBookFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianBooks.ShipBookFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companytechnicianbooksshipbookform',

    onSubmit_COMPANY_TECHNICIAN_BOOK: function(button, e, eOpts) {
        var conffun = function(buttonText) {
            if (buttonText == "yes") {
                submitfun();
            }
            if (buttonText == "no") {
                return false;
            }
        };

        var msb = Ext.MessageBox.confirm('Καταχώρηση βιβλίου', 'Είστε σίγουροι ότι θελετε να δημιουργήσετε το βιβλίο; ', conffun);

        var submitfun = function(){
            var form = button.up('toolbar').up('window').down('form'),
                formWindow = button.up('toolbar').up('window'),
                values = form.getValues();


            var store = Ext.StoreMgr.lookup('company.TechnicianBooks.SHIP_BOOKS');
            var book = store.findRecord('compShipId', values.compShipId);

            if (book){
                Ext.Msg.alert('Αποτυχία Υποβολής', 'Υπάρχει ήδη βιβλίο για την συγκεκριμένη εγκατάσταση.');
            }
            else{
                if (form.isValid()) {
                    var rest_method;

                    rest_method = "POST";

                    // Success
                    var successCallback = function(resp, ops) {

                        if (resp.status===304){
                            Ext.Msg.alert('Αποτυχία Καταχώρησης', decodeURIComponent(resp.getResponseHeader("SEPE_ERROR")).replace(": SEPE_ERROR:","").replace(/\+/g,' '));
                        }
                        else
                        Ext.Msg.alert('Επιτυχής Καταχώρηση', 'Το βιβλίο δημιουργήθηκε με επιτυχία.');
                        formWindow.destroy();
                    };

                    // Failure
                    var failureCallback = function(resp, ops) {

                        Ext.Msg.alert('Αποτυχία Καταχώρησης', 'Το βιβλίο δεν έγινε δεκτό από το σύστημα');

                    };

                    values.dateCreated=button.up('toolbar').getCurrentTimestamp(1);
                    Ext.Ajax.request({
                        url: "/compTaSannBook/",
                        headers: { 'Content-Type': 'application/json' },
                        jsonData: Ext.util.JSON.encode(values),
                        method: rest_method,
                        success: successCallback,
                        failure: failureCallback
                    });




                }
                else{
                    Ext.Msg.alert('Αποτυχία Υποβολής', 'Παρακαλώ διορθώστε τα λάθος πεδία');

                }
            }
        };
    }

});
