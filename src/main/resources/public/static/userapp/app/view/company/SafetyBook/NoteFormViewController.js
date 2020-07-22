/*
 * File: app/view/company/SafetyBook/NoteFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.SafetyBook.NoteFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companysafetybooknoteform',

    onSubmit_COMPANY_SAFETYBOOK_NOTE: function(button, e, eOpts) {
        var conffun = function(buttonText) {
            if (buttonText == "yes") {
                submitfun();
            }
            if (buttonText == "no") {
                return false;
            }
        };

        var msb = Ext.MessageBox.confirm('Καταχώρηση Υπόδειξης', 'Είστε σίγουροι ότι θελετε να γίνει η καταχώρηση στο βιβλίο; ', conffun);

        var submitfun = function(){
            var form = button.up('toolbar').up('window').down('form'),
                formWindow = button.up('toolbar').up('window'),
                values = form.getValues();


            if (form.isValid()) {
                var fid, rest_method;

                if (values.url===""){
                    fid = "";
                    rest_method = "POST";
                    values.creationDate=button.up('toolbar').getCurrentTimestamp(1);
                    values.updateDate=button.up('toolbar').getCurrentTimestamp(1);
                }
                else
                {
                    var nurl = values.url;
                    var res = nurl.split("/");
                    fid = res[res.length-1];
                    fid = fid + "/";
                    rest_method = "PUT";
                    values.updateDate=button.up('toolbar').getCurrentTimestamp(1);
                }

                // Success
                var successCallback = function(resp, ops) {
                    formWindow.unmask();

                    if (resp.status===304){
                        Ext.Msg.alert('Αποτυχία Καταχώρησης', decodeURIComponent(resp.getResponseHeader("SEPE_ERROR")).replace(": SEPE_ERROR:","").replace(/\+/g,' '));
                    }
                    else
                    Ext.Msg.alert('Επιτυχία Αποθήκευσης', 'Η υπόδειξη έχει καταχωρηθεί στο βιβλίο.');
                    var notes = Ext.getCmp('companySafetyBooks_Notes');
                    var bookId = Ext.getCmp('companySafetyBooks_Books').getSelectionModel().getSelection()[0].get('bookId');
                    notes.store.proxy.setUrl('compSecDiaryEntry/search/findByCompSecDiaryId?compSecDiaryId='+bookId);
                    notes.store.load( { callback : function(records, operation, success) {
                        notes.getView().refresh();
                    }
                });
                formWindow.destroy();

            };

            var failureCallback = function(resp, ops) {
                formWindow.unmask();
                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Η υπόδειξη δεν έγινε δεκτή από το σύστημα.');

            };

            var successCall = function(options, success, response) {

            };
            formWindow.mask("Παρακαλώ Περιμένετε...");

            Ext.Ajax.request({
                url: "/compSecDiaryEntry/" + fid,
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
    };
    }

});
