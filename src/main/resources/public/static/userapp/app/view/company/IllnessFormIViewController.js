/*
 * File: app/view/company/IllnessFormIViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.IllnessFormIViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companyillnessform',

    onDelete_COMPANY_ILLNESS: function(button, e, eOpts) {

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

                        Ext.Msg.alert('Επιτυχής Διαγραφή', 'Η αναγγελία διαγράφηκε');

                        // Close window
                        formWindow.destroy();

                    };

                    // Failure
                    var failureCallback = function(resp, ops) {
                        Ext.Msg.alert('Αποτυχία Διαγραφής', 'Η αναγγελία δεν διαγράφηκε');

                    };

                    Ext.Ajax.request({
                        url: "/companyIllness/" + fid,
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

        var msb = Ext.MessageBox.confirm('Διαγραφή φόρμας', 'Είστε σίγουροι ότι θέλετε να διαγράψετε τη φόρμα;', conffun);
    },

    field_validation: function (form) {

        var form = form.getForm();
        var values = form.getValues();

        var invalidations = false;

        for (var i in values)
        {
            form.findField(i).clearInvalid();
            if ((form.findField(i).getValue() != "" && form.findField(i).getValue() != null))
                if (!form.findField(i).isValid())
                    invalidations =  true;
        }
        return !invalidations;
    },

    onSave_COMPANY_ILLNESS: function(button, e, eOpts) {
        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        if (this.field_validation(form)) {
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

                Ext.Msg.alert('Επιτυχής Αποθήκευση', 'Η αναγγελία αποθηκεύθηκε με επιτυχία.');
                formWindow.destroy();

            };

            // Failure
            var failureCallback = function(resp, ops) {

                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Η αναγγελία δεν έγινε δεκτή από το σύστημα.');

            };

            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
            values.protYear=button.up('toolbar').getCurrentTimestamp(3);


            values.diagnosisDate=button.up('toolbar').dateToTimestamp(values.diagnosisDateView);
            if(values.prDiagnosisDateView!=="")
            values.prDiagnosisDate=button.up('toolbar').dateToTimestamp(values.prDiagnosisDateView);
            else
            values.prDiagnosisDate=null;
            values.empBirthdate=button.up('toolbar').dateToTimestamp(values.empBirthdateView);
            values.empRecruitmentDate=button.up('toolbar').dateToTimestamp(values.empRecruitmentDateView);

            values.empTimeCode=form.getForm().findField('workingHourStart').rawValue+"-"+form.getForm().findField('workingHourStop').rawValue;
            values.subStatus=1;

            if(values.attachedDocId!=="-1" && values.deletedfile==="true" && values.file==="")
            values.attachedDocId="-1";

            var fileurl = '/setDocument';
            if(values.attachedDocId!=="-1" &&values.file!==null)
            fileurl = fileurl + "?docId=" + values.attachedDocId;

            if(values.file!==""){
                form.down('form').submit({
                    url: fileurl,
                    waitMsg: 'Καταχώρηση αρχείου...',
                    success: function(form, action) {
                        values.attachedDocId=parseInt(action.result.fileId);
                        Ext.Ajax.request({
                            url: "/companyIllness/" + fid,
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
                    url: "/companyIllness/" + fid,
                    headers: { 'Content-Type': 'application/json' },
                    jsonData: Ext.util.JSON.encode(values),
                    method: rest_method,
                    success: successCallback,
                    failure: failureCallback
                });
            }

        }
        else{
            Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Παρακαλώ διορθώστε τα λάθος πεδία');

        }
    },

    onSubmit_COMPANY_ILLNESS: function(button, e, eOpts) {
        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        if (values.compEbrBranchId != "" && isNaN(parseInt(values.compEbrBranchId, 10)))
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

                Ext.Msg.alert('Επιτυχής Υποβολή', 'Η αναγγελία υποβλήθηκε με επιτυχία.');
                formWindow.destroy();

            };

            // Failure
            var failureCallback = function(resp, ops) {

                Ext.Msg.alert('Αποτυχία Υποβολής', 'Η αναγγελία δεν έγινε δεκτή από το σύστημα.');

            };

            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
            values.protYear=button.up('toolbar').getCurrentTimestamp(3);


            values.diagnosisDate=button.up('toolbar').dateToTimestamp(values.diagnosisDateView);
            if(values.prDiagnosisDateView!=="")
            values.prDiagnosisDate=button.up('toolbar').dateToTimestamp(values.prDiagnosisDateView);
            else
            values.prDiagnosisDate=null;
            values.empBirthdate=button.up('toolbar').dateToTimestamp(values.empBirthdateView);
            values.empRecruitmentDate=button.up('toolbar').dateToTimestamp(values.empRecruitmentDateView);


            values.subStatus=2;
            values.reqStatus=1;

            values.empTimeCode=form.getForm().findField('workingHourStart').rawValue+"-"+form.getForm().findField('workingHourStop').rawValue;

            if(values.attachedDocId!=="-1" && values.deletedfile==="true" && values.file==="")
            values.attachedDocId="-1";

            var fileurl = '/setDocument';
            if(values.attachedDocId!=="-1" &&values.file!==null)
            fileurl = fileurl + "?docId=" + values.attachedDocId;

            if(values.file!==""){
                form.down('form').submit({
                    url: fileurl,
                    waitMsg: 'Καταχώρηση αρχείου...',
                    success: function(form, action) {
                        values.attachedDocId=parseInt(action.result.fileId);
                        Ext.Ajax.request({
                            url: "/companyIllness/" + fid,
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
                    url: "/companyIllness/" + fid,
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
    }

});
