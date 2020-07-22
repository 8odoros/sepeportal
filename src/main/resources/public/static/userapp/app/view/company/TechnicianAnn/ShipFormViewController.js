/*
 * File: app/view/company/TechnicianAnn/ShipFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianAnn.ShipFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companytechnicianannshipform',

    onSave_COMPANY_SHIP: function(button, e, eOpts) {

        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();


        if(form.isValid()){
            var rest_method = "POST";

            var successCallback = function(resp, ops) {
                if (resp.status===304){
                    Ext.Msg.alert('Αποτυχία Καταχώρησης', decodeURIComponent(resp.getResponseHeader("SEPE_ERROR")).replace(": SEPE_ERROR:","").replace(/\+/g,' '));
                }
                else{
                    Ext.Msg.alert('Επιτυχής Αποθήκευση', 'Το πλοίο αποθηκεύτηκε με επιτυχία');
                    var grid = Ext.getCmp('companyTechnicianShipAnn_Ships');
                    grid.getView().store.reload();
                    // Close window
                    formWindow.destroy();
                }

            };

            // Failure
            var failureCallback = function(resp, ops) {
                Ext.Msg.alert('Αποτυχία Καταχώρησης', 'Το πλοίο δεν αποθηκεύτηκε.');

            };


            Ext.Ajax.request({
                url: "/compShip/",
                headers: { 'Content-Type': 'application/json' },
                jsonData: Ext.util.JSON.encode(values),
                method: rest_method,
                success: successCallback,
                failure: failureCallback
            });



        }
        else{
            Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Παρακαλώ συμπληρώστε τα απαραίτητα πεδία για αποθήκευση');


        }
    }

});
