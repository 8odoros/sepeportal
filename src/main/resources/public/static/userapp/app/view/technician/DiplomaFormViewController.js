/*
 * File: app/view/technician/DiplomaFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.technician.DiplomaFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.techniciandiplomaform',

    onSubmit_TECHNICIAN_DIPLOMA: function(button, e, eOpts) {
        var conffun = function(buttonText) {
            if (buttonText == "yes") {
                submitfun();
            }
            if (buttonText == "no") {
                return false;
            }
        };

        var msb = Ext.MessageBox.confirm('Υποβολή φόρμας', 'Είστε σίγουροι ότι θελετε να καταχωρηθεί η εγγραφή; ', conffun);

        var submitfun = function(){
            var form = button.up('toolbar').up('window').down('form'),
                formWindow = button.up('toolbar').up('window'),
                values = form.getValues();

            values.submitDate=button.up('toolbar').getCurrentTimestamp();

            if (form.isValid()) {
                var fid, rest_method;

                if (values.url===""){
                    fid = "";
                    rest_method = "POST";
                }

                // Success
                var successCallback = function(resp, ops) {

                    Ext.Msg.alert('Επιτυχής Αποθήκευση', 'Η εγγραφή σας αποθηκεύθηκε με επιτυχία.');
                    formWindow.destroy();

                };

                // Failure
                var failureCallback = function(resp, ops) {

                    Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Η εγγραφή σας δεν έγινε δεκτή από το σύστημα');

                };


                values.diplomaYear=button.up('toolbar').dateToTimestamp(values.diplomaYearView);

                var fileurl = 'setDocument';
                form.down('form').submit({
                    url: fileurl,
                    waitMsg: 'Καταχώρηση αρχείου...',
                    success: function(form, action) {
                        values.attachedDocId=parseInt(action.result.fileId);
                        Ext.Ajax.request({
                            url: "/technicianDiploma/" + fid,
                            headers: { 'Content-Type': 'application/json' },
                            jsonData: Ext.util.JSON.encode(values),
                            method: rest_method,
                            success: successCallback,
                            failure: failureCallback
                        });
                    },
                    failure: function(form, action) {
                        form.findField("file").focus();
                        Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                    }
                });
            }
            else{
                Ext.Msg.alert('Αποτυχία Καταχώρησης', 'Παρακαλώ διορθώστε τα λάθος πεδία');

            }
        };


    }

});