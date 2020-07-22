/*
 * File: app/view/company/ExplanationFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.ExplanationFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companyexplanationform',

    onDelete_COMPANY_EXPLANATION: function(button, e, eOpts) {

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

                        formWindow.unmask();
                        Ext.Msg.alert('Επιτυχής Διαγραφή', 'Η αίτηση διαγράφηκε');

                        // Close window
                        formWindow.destroy();

                    };

                    // Failure
                    var failureCallback = function(resp, ops) {
                        formWindow.unmask();
                        Ext.Msg.alert('Αποτυχία Διαγραφής', 'Η αίτηση δεν διαγράφηκε');

                    };

                    formWindow.mask('Διαγραφή Αναγγελίας...', 'x-mask-loading');
                    Ext.Ajax.request({
                        url: "/compExplanation/" + fid,
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

    onSave_COMPANY_EXPLANATION: function(button, e, eOpts) {

        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        if(values.file!==""){
            form.getForm().findField('attachedDocDescr').allowBlank=false;
            form.getForm().findField('attachedDocDescr').allowOnlyWhitespaces=false;
        }
        else{
            if(values.deletedfile==="true" && parseInt(values.attachedDocId)!==-1){
                form.getForm().findField('attachedDocDescr').allowBlank=true;
                form.getForm().findField('attachedDocDescr').allowOnlyWhitespaces=true;
            }
            else if(values.deletedfile==="false" && parseInt(values.attachedDocId)!==-1){
                form.getForm().findField('attachedDocDescr').allowBlank=false;
                form.getForm().findField('attachedDocDescr').allowOnlyWhitespaces=false;
            }
            else {
                form.getForm().findField('attachedDocDescr').allowBlank=true;
                form.getForm().findField('attachedDocDescr').allowOnlyWhitespaces=true;
            }
        }

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

                formWindow.unmask();
                Ext.Msg.alert('Επιτυχής Αποθήκευση', 'Η αίτηση αποθηκεύτηκε με επιτυχία.');

                // Close window
                formWindow.destroy();

            };

            // Failure
            var failureCallback = function(resp, ops) {
                formWindow.unmask();
                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Η αίτηση δεν αποθηκεύτηκε.');

            };



            values.subStatus=1;

            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
            values.protYear=button.up('toolbar').getCurrentTimestamp(3);

            values.inspectionDate=button.up('toolbar').dateToTimestamp(values.inspectionDateView);

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
                        formWindow.mask('Αποθήκευση Αναγγελίας...', 'x-mask-loading');
                        Ext.Ajax.request({
                            url: "/compExplanation/" + fid,
                            headers: { 'Content-Type': 'application/json' },
                            jsonData: Ext.util.JSON.encode(values),
                            async: false,
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
                formWindow.mask('Αποθήκευση Αναγγελίας...', 'x-mask-loading');
                Ext.Ajax.request({
                    url: "/compExplanation/" + fid,
                    headers: { 'Content-Type': 'application/json' },
                    jsonData: Ext.util.JSON.encode(values),
                    async: false,
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

    onSubmit_COMPANY_EXPLANATION: function(button, e, eOpts) {
        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        if(values.file!==""){
            form.getForm().findField('attachedDocDescr').allowBlank=false;
            form.getForm().findField('attachedDocDescr').allowOnlyWhitespaces=false;
        }
        else{
            if(values.deletedfile==="true" && parseInt(values.attachedDocId)!==-1){
                form.getForm().findField('attachedDocDescr').allowBlank=true;
                form.getForm().findField('attachedDocDescr').allowOnlyWhitespaces=true;
            }
            else if(values.deletedfile==="false" && parseInt(values.attachedDocId)!==-1){
                form.getForm().findField('attachedDocDescr').allowBlank=false;
                form.getForm().findField('attachedDocDescr').allowOnlyWhitespaces=false;
            }
            else {
                form.getForm().findField('attachedDocDescr').allowBlank=true;
                form.getForm().findField('attachedDocDescr').allowOnlyWhitespaces=true;
            }
        }

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

                formWindow.unmask();
                Ext.Msg.alert('Επιτυχής Υποβολή', 'Η αίτηση υποβλήθηκε με επιτυχία.');
                formWindow.destroy();

            };

            // Failure
            var failureCallback = function(resp, ops) {

                formWindow.unmask();
                Ext.Msg.alert('Αποτυχία Υποβολής', 'Η αίτηση δεν έγινε δεκτή από το σύστημα.');

            };

            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
            values.protYear=button.up('toolbar').getCurrentTimestamp(3);

            values.reqStatus=1;
            values.subStatus=2;

            values.inspectionDate=button.up('toolbar').dateToTimestamp(values.inspectionDateView);

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
                            url: "/compExplanation/" + fid,
                            headers: { 'Content-Type': 'application/json' },
                            jsonData: Ext.util.JSON.encode(values),
                            async: false,
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
                    url: "/compExplanation/" + fid,
                    headers: { 'Content-Type': 'application/json' },
                    jsonData: Ext.util.JSON.encode(values),
                    async: false,
                    method: rest_method,
                    success: successCallback,
                    failure: failureCallback
                });
            }


        }
        else{
            Ext.Msg.alert('Αποτυχία Υποβολής', 'Παρακαλώ διορθώστε τα λάθος πεδία');

        }
    }

});
