/*
 * File: app/view/company/GenRequestFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.CertificateFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companycertificateform',

    onDelete_COMPANY_CERTIFICATE: function(button, e, eOpts) {

        var form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        var conffun = function(buttonText) {
            if (buttonText == "yes") {
                if(values.subStatus==="1" && form.getForm().findField("a_new_form").getValue()==="false"){
                    var res = values.url.split("/");
                    var fid = res[res.length-1];
                    var rest_method = "DELETE";
                    fid = fid + "/";
                    var successCallback = function(resp, ops) {

                        Ext.Msg.alert('Επιτυχής Διαγραφή', 'Η αίτηση διαγράφηκε');

                        // Close window
                        formWindow.destroy();

                    };

                    // Failure
                    var failureCallback = function(resp, ops) {
                        Ext.Msg.alert('Αποτυχία Διαγραφής', 'Η αίτηση δεν διαγράφηκε');

                    };

                    Ext.Ajax.request({
                        url: "/compGenreq/" + fid,
                        headers: { 'Content-Type': 'application/json' },
                        method: rest_method,
                        success: successCallback,
                        failure: failureCallback
                    });
                }
                else{
                    Ext.Msg.alert('Αποτυχία', 'Η φόρμα δεν έπρεπε να φθάσει σε αυτή την κατάσταση');
                }
            }
            if (buttonText == "no") {
            }
        };

        var msb = Ext.MessageBox.confirm('Διαφραφή φόρμας', 'Είστε σίγουροι ότι θέλετε να διαγράψετε τη φόρμα;', conffun);
    },

    field_validation: function (form) {

         var form = form.getForm();
         var values = form.getValues();

         var invalidations = false;

         for (var i in values)
         {
             form.findField(i).clearInvalid();
             if (form.findField(i).getValue() != "" && form.findField(i).getValue() != null)
                 if (!form.findField(i).isValid())
                     invalidations =  true;
         }
         return !invalidations;
    },

    onSave_COMPANY_CERTIFICATE: function(button, e, eOpts) {

        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        if(this.field_validation(form)){
            var nurl = values.url;
            var res = nurl.split("/");
            var fid = res[res.length-1];
            var rest_method;
            if (values.url===""){
                fid="";
                rest_method = "POST";
            }
            else{
                fid = fid + "/";
                rest_method = "PUT";
            }

            var successCallback = function(resp, ops) {

                Ext.Msg.alert('Επιτυχής Αποθήκευση', 'Η αίτηση αποθηκεύτηκε με επιτυχία.');

                // Close window
                formWindow.destroy();

            };

            // Failure
            var failureCallback = function(resp, ops) {
                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Η αίτηση δεν αποθηκεύτηκε.');

            };

            values.subStatus=1;

            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
            values.protYear=button.up('toolbar').getCurrentTimestamp(3);
            values.contestExpiryDate=button.up('toolbar').dateToTimestamp(values.contestExpiryDateView);

            if(parseInt(values.attachedDocId)!==-1 && values.deletedfile==="true" && values.file==="")
                values.attachedDocId=-1;

            var fileurl = '/setDocument';
            if(parseInt(values.attachedDocId)!==-1 && values.file!==null)
                fileurl = fileurl + "?docId=" + values.attachedDocId;

            if(values.file!==""){
                form.down('form').submit({
                    url: fileurl,
                    waitMsg: 'Καταχώρηση αρχείου...',
                    success: function(form, action) {
                        values.attachedDocId=parseInt(action.result.fileId);
                        Ext.Ajax.request({
                            url: "/compGenreq/" + fid,
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
                    url: "/compGenreq/" + fid,
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
    },

    onSubmit_COMPANY_CERTIFICATE: function(button, e, eOpts) {
        Ext.Msg.alert('Μήνυμα Συστήματος', 'Ενημερώνονται οι χρήστες ότι σύμφωνα με την παρ. 46.α. του άρθρου 43 του Ν. 4605/2019: «Μέχρι να καταστεί εφικτή η έκδοση του πιστοποιητικού που προβλέπεται στην περίπτωση γ΄ της παραγράφου 2 του άρθρου 80 (του ν. 4412/2016), αυτό αντικαθίσταται από υπεύθυνη δήλωση του οικονομικού φορέα, χωρίς να απαιτείται επίσημη δήλωση του Σ.ΕΠ.Ε. σχετικά με την έκδοση του πιστοποιητικού.»');
        /*var view = this.getView(),
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
            }
            else
            {
                var nurl = values.url;
                var res = nurl.split("/");
                var fid = res[res.length-1];
                fid = fid + "/";
                rest_method = "PUT";
            }

            // Success
            var successCallback = function(resp, ops) {

                if (resp != null) {
                    var respObj = JSON.parse(resp.responseText);

                    var successCallback2 = function(resp, ops) {
                        formWindow.unmask();
                        Ext.Msg.alert('Επιτυχής Υποβολή', 'Η Αίτηση υποβλήθηκε με επιτυχία.');
                        formWindow.destroy();
                    };
                    var failureCallback2 = function(resp, ops) {
                        formWindow.unmask();
                        Ext.Msg.alert('Αποτυχία Υποβολής', 'Η Αίτηση δεν έγινε δεκτή από το σύστημα.');
                    };

                    Ext.Ajax.request({
                        url: "/getCertificateInPdf/" ,
                        //headers: { 'Content-Type': 'application/json' },
                        params: {
                            compGenReqProtNo: respObj.protNo
                        },
                        method: "POST",
                        success: successCallback2,
                        failure: failureCallback2
                    });

                    /!*var form = document.createElement("form");
                    form.setAttribute("method", "POST");
                    form.setAttribute("action", "/portal/getCertificateInPdf");
                    var hiddenField = document.createElement("input");
                    hiddenField.setAttribute("type", "hidden");
                    hiddenField.setAttribute("name", "compGenReqProtNo");
                    hiddenField.setAttribute("value", respObj.protNo);
                    form.appendChild(hiddenField);

                    document.body.appendChild(form);
                    form.submit();
                    form.remove();

                    Ext.Msg.alert('Επιτυχής Υποβολή', 'Η αίτηση υποβλήθηκε με επιτυχία.');*!/
                }
                //formWindow.destroy();

            };

            // Failure
            var failureCallback = function(resp, ops) {

                Ext.Msg.alert('Αποτυχία Υποβολής', 'Η αίτηση δεν έγινε δεκτή από το σύστημα.');

            };

            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
            values.protYear=button.up('toolbar').getCurrentTimestamp(3);
            values.contestExpiryDate=button.up('toolbar').dateToTimestamp(values.contestExpiryDateView);

            values.reqStatus=6;
            values.subStatus=2;

            if(parseInt(values.attachedDocId)!==-1 && values.deletedfile==="true" && values.file==="")
                values.attachedDocId=-1;

            var fileurl = '/setDocument';
            if(parseInt(values.attachedDocId)!==-1 &&values.file!==null)
                fileurl = fileurl + "?docId=" + values.attachedDocId;

            if(values.file!==""){
                form.down('form').submit({
                    url: fileurl,
                    waitMsg: 'Καταχώρηση αρχείου...',
                    success: function(form, action) {
                        values.attachedDocId=parseInt(action.result.fileId);
                        formWindow.mask('Αναμονή υποβολής...', 'x-mask-loading');
                        Ext.Ajax.request({
                            url: "/compGenreq/" + fid,
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
                formWindow.mask('Αναμονή υποβολής...', 'x-mask-loading');
                Ext.Ajax.request({
                    url: "/compGenreq/" + fid,
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

        }*/
    }

});
