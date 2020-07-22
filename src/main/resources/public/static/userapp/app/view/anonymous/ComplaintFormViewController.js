/*
 * File: app/view/anonymous/ComplaintFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.anonymous.ComplaintFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.anonymouscomplaintform',

    onSubmit_PUBLICUSER_COMPLAINT: function(button, e, eOpts) {
        var conffun = function(buttonText) {
            if (buttonText == "yes") {
                submitfun();
            }
            if (buttonText == "no") {
                return false;
            }
        };

        var msb = Ext.MessageBox.confirm('Υποβολή φόρμας', 'Είστε σίγουροι ότι θελετε να υποβληθεί η καταγγελία; ', conffun);

        var submitfun = function(){
            var form = button.up('toolbar').up('window').down('form'),
                formWindow = button.up('toolbar').up('window'),
                values = form.getValues();

            if (form.isValid()) {
                var fid, rest_method;

                if (values.url===""){
                    fid = "";
                    rest_method = "POST";
                }

                // Success
                var successCallback = function(resp, ops) {

                    Ext.Msg.alert('Επιτυχής Υποβολή', 'Η καταγγελία σας υποβλήθηκε με επιτυχία. Πατήστε εντάξει να προβάλετε και να εκτυπώσετε τα στοιχεία της Πρωτοκολλημένης καταγγελίας.');

                    var response=Ext.JSON.decode(resp.responseText);
                    form.getForm().findField('protNoview').setValue(response.protNo);
                    var pD = response.protDate.replace(/[^0-9]+/g,' ').split(" ");
                    form.getForm().findField('protDateview').setValue(pD[2]+"-"+pD[1]+"-"+pD[0]);
                    form.getForm().findField('protYear').setValue(response.protYear);
                    form.getForm().findField('submitTime').setValue(response.submitTime);

                    form.getForm().findField('file').hide();
                    if (form.getForm().findField('docIdAttached').getValue()!==""){
                        var vfc = Ext.ComponentQuery.query('#Exist_File')[0];
                        vfc.show();
                    }
                    Ext.getCmp('publiccompl_save_submit_toolbar').hide();
                    var fields = form.getForm().getFields();
                    fields.each(function (field) {
                        field.enable();
                    field.setReadOnly (true);});


                };

                // Failure
                var failureCallback = function(resp, ops) {

                    Ext.Msg.alert('Αποτυχία Υποβολής', 'Η καταγγελία σας δεν έγινε δεκτή από το σύστημα');

                };

                if(values.complInvolves==='1'){
                    values.complInvolvesLabRelations='0';
                    values.complInvlovesSafetyInsp='1';
                }
                else if(values.complInvolves==='0'){
                    values.complInvolvesLabRelations='1';
                    values.complInvlovesSafetyInsp='0';
                }

                values.protNo="";
                values.complMatter=Ext.util.JSON.encode(values.complMatter);
                values.complMatter=values.complMatter.substring(1, values.complMatter.length-1);
                values.complMatter=values.complMatter.replace(/["']/g, "");
                values.protDate=button.up('toolbar').getCurrentTimestamp(1);
                values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
                values.protYear=button.up('toolbar').getCurrentTimestamp(3);

                values.reqStatus=1;
                values.subStatus=2;

                if(values.docIdAttached!=="" && values.deletedfile==="true" && values.file==="")
                values.docIdAttached="";

                var fileurl = '/setDocument';
                if(values.docIdAttached!=="" &&values.file!==null)
                fileurl = fileurl + "?docId=" + values.docIdAttached;

                if(values.file!==""){
                    form.down('form').submit({
                        url: fileurl,
                        waitMsg: 'Καταχώρηση αρχείου...',
                        success: function(form, action) {
                            values.docIdAttached=parseInt(action.result.fileId);
                            Ext.Ajax.request({
                                url: "/anonymousComplaint/" + fid,
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
                    Ext.Ajax.request({
                        url: "/anonymousComplaint/" + fid,
                        headers: { 'Content-Type': 'application/json' },
                        jsonData: Ext.util.JSON.encode(values),
                        method: rest_method,
                        success: successCallback,
                        failure: failureCallback
                    });
                }

            }
            else{
                Ext.Msg.alert('Αποτυχία Υποβολής', 'Παρακαλώ διορθώστε τα λάθος πεδία');

            }
        };

    }

});
