/*
 * File: app/view/company/DangerForm/DangerFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.DangerForm.DangerFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companydangerformdangerform',

    onSave_COMPANY_DANGER: function(button, e, eOpts) {

        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();


        if (values.branch1Id != "" && isNaN(parseInt(values.branch1Id, 10)))
        {
            Ext.Msg.alert('Αποτυχία Υποβολής', 'Παρακαλώ διορθώστε το παράρτημα');
            return;
        }

        if (form.isValid()) {
            var fid, rest_method;

            if (values.url===""){
                fid = "";
                rest_method = "POST";
                values.dateUpdated=button.up('toolbar').getCurrentTimestamp(1);
            }
            else
            {
                var nurl = values.url;
                var res = nurl.split("/");
                var fid = res[res.length-1];
                fid = fid + "/";
                rest_method = "PUT";
                values.dateUpdated=button.up('toolbar').getCurrentTimestamp(1);
            }

            var successCallback = function(resp, ops) {

                Ext.Msg.alert('Επιτυχής Αποθήκευση', 'Η εκτίμηση αποθηκεύτηκε με επιτυχία');

                // Close window
                formWindow.destroy();

            };

            // Failure
            var failureCallback = function(resp, ops) {
                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Η εκτίμηση δεν αποθηκεύτηκε.');

            };


            if(parseInt(values.attachedDocId)!==-1 && values.deletedfile==="true" && values.file==="")
            values.attachedDocId=-1;

            values.subStatus=1;

            var fileurl = '/setDocument';
            if(parseInt(values.attachedDocId)!==-1 &&values.file!==null)
            fileurl = fileurl + "?docId=" + values.attachedDocId;

            if(values.file!==""){
                form.down('form').submit({
                    url: fileurl,
                    waitMsg: 'Αποθήκευση αρχείου...',
                    success: function(form, action) {
                        values.attachedDocId=parseInt(action.result.fileId);
                        Ext.Ajax.request({
                            url: "/compDangerAssess/" + fid,
                            headers: { 'Content-Type': 'application/json' },
                            jsonData: Ext.util.JSON.encode(values),
                            method: rest_method,
                            success: successCallback,
                            failure: failureCallback
                        });
                    },
                    failure: function(form, action) {
                        form.findField("file").focus();
                        Ext.Msg.alert('Αποτυχία Αποθήκευσης Αρχείου', action.result.error);

                    }
                });
            }
            else{
                Ext.Ajax.request({
                    url: "/compDangerAssess/" + fid,
                    headers: { 'Content-Type': 'application/json' },
                    jsonData: Ext.util.JSON.encode(values),
                    method: rest_method,
                    success: successCallback,
                    failure: failureCallback
                });

            }

        }
        else{
            Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Παρακαλώ συμπληρώστε τα απαραίτητα πεδία για αποθήκευση');


        }
    }

});
