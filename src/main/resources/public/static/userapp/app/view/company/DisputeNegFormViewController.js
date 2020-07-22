/*
 * File: app/view/company/DisputeNegFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.DisputeNegFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companydisputenegform',

    onDelete_COMPANY_DISPUTE: function(button, e, eOpts) {

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
                        url: "/compDisputeNeg/" + fid,
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

    onSave_COMPANY_DISPUTE: function(button, e, eOpts) {
        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        formWindow.mask("Παρακαλώ Περιμένετε...");

        var validates = true;
        if ((form.getForm().findField('empFromDate').rawValue != "" && form.getForm().findField('empFromDate').rawValue != null) && (form.getForm().findField('empUntilDate').rawValue != "" && form.getForm().findField('empUntilDate').rawValue != null))
            validates = Ext.getCmp('compdisp_save_submit_toolbar').validateDatesDifference(form.getForm().findField('empFromDate'),form.getForm().findField('empUntilDate'));
        
        if (this.field_validation(form) && validates) {

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


            var successCallback = function(resp, ops) {

                formWindow.unmask();

                Ext.Msg.alert('Επιτυχής Αποθήκευση', 'Η αίτηση σας αποθηκεύθηκε με επιτυχία');

                formWindow.destroy();


            };

            // Failure
            var failureCallback = function(resp, ops) {
                formWindow.unmask();
                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Η αίτηση σας δεν αποθηκεύτηκε.');
                formWindow.destroy();
            };


            var tf=form.getForm().findField('empWorkingHoursFrom').rawValue;
            var tu=form.getForm().findField('empWorkingHoursUntil').rawValue;
            if(tf==="")
            tf="null";
            if(tu==="")
            tu="null";
            values.empWorkingHours= tf+"-"+ tu;


            values.empFromDate=button.up('toolbar').dateToTimestamp(values.empFromDate);
            values.empUntilDate=button.up('toolbar').dateToTimestamp(values.empUntilDate);


            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
            values.protYear=button.up('toolbar').getCurrentTimestamp(3);

            values.subStatus=1;

            if(Ext.getCmp("disputereasons1").isDisabled())
            values.disputeConciliationCategId="";
            else
            values.disputeConciliationCategId=Ext.getCmp("disputereasons1").getValue().disputeType1.toString();


            if(parseInt(values.docIdAttached)!==-1 && values.deletedfile==="true" && values.file==="")
            values.docIdAttached=-1;

            values.descr=Ext.util.JSON.encode(values.descr);
            values.descr=values.descr.substring(1, values.descr.length-1);

            var fileurl = '/setDocument';
            if(parseInt(values.docIdAttached)!==-1 &&values.file!==null)
            fileurl = fileurl + "?docId=" + values.docIdAttached;

            if(values.file!==""){
                form.down('form').submit({
                    url: fileurl,
                    waitMsg: 'Αποθήκευση αρχείου...',
                    success: function(form, action) {
                        values.docIdAttached=parseInt(action.result.fileId);
                        Ext.Ajax.request({
                            url: "/compDisputeNeg/" + fid,
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
                var call1 = Ext.Ajax.request({
                    url: "/compDisputeNeg/" + fid,
                    headers: { 'Content-Type': 'application/json' },
                    jsonData: Ext.util.JSON.encode(values),
                    method: rest_method,
                    success: successCallback,
                    failure: failureCallback
                });
            }
        }
        else{
            formWindow.unmask();
            Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Παρακαλώ διορθώστε τα λάθος πεδία');


        }

    },

    onSubmit_COMPANY_DISPUTE: function(button, e, eOpts) {
        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        formWindow.mask("Παρακαλώ Περιμένετε...");

        var validates = Ext.getCmp('compdisp_save_submit_toolbar').validateDatesDifference(form.getForm().findField('empFromDate'),form.getForm().findField('empUntilDate'));

        if (values.branch1Id != "" && isNaN(parseInt(values.branch1Id, 10)))
        {
            Ext.Msg.alert('Αποτυχία Υποβολής', 'Παρακαλώ διορθώστε το παράρτημα');
            return;
        }

        if (form.isValid() && validates) {

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


            var successCallback = function(resp, ops) {

                formWindow.unmask();

                Ext.Msg.alert('Επιτυχής Υποβολή', 'Η αίτηση σας υποβλήθηκε με επιτυχία');

                formWindow.destroy();


            };

            // Failure
            var failureCallback = function(resp, ops) {
                formWindow.unmask();
                Ext.Msg.alert('Αποτυχία Υποβολής', 'Η αίτηση σας δεν υποβλήθηκε αλλά αποθηκεύτηκε.');
                formWindow.destroy();
            };


            var tf=form.getForm().findField('empWorkingHoursFrom').rawValue;
            var tu=form.getForm().findField('empWorkingHoursUntil').rawValue;
            if(tf==="")
            tf="null";
            if(tu==="")
            tu="null";
            values.empWorkingHours= tf+"-"+ tu;


            values.empFromDate=button.up('toolbar').dateToTimestamp(values.empFromDate);
            values.empUntilDate=button.up('toolbar').dateToTimestamp(values.empUntilDate);


            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
            values.protYear=button.up('toolbar').getCurrentTimestamp(3);

            values.subStatus=2;
            values.reqStatus=1;


            if(Ext.getCmp("disputereasons1").isDisabled())
            values.disputeConciliationCategId="";
            else
            values.disputeConciliationCategId=Ext.getCmp("disputereasons1").getValue().disputeType1.toString();

            if(parseInt(values.docIdAttached)!==-1 && values.deletedfile==="true" && values.file==="")
            values.docIdAttached=-1;

            values.descr=Ext.util.JSON.encode(values.descr);
            values.descr=values.descr.substring(1, values.descr.length-1);

            var fileurl = '/setDocument';
            if(parseInt(values.docIdAttached)!==-1 &&values.file!==null)
            fileurl = fileurl + "?docId=" + values.docIdAttached;

            if(values.file!==""){
                form.down('form').submit({
                    url: fileurl,
                    waitMsg: 'Αποθήκευση αρχείου...',
                    success: function(form, action) {
                        values.docIdAttached=parseInt(action.result.fileId);
                        Ext.Ajax.request({
                            url: "/compDisputeNeg/" + fid,
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
                var call1 = Ext.Ajax.request({
                    url: "/compDisputeNeg/" + fid,
                    headers: { 'Content-Type': 'application/json' },
                    jsonData: Ext.util.JSON.encode(values),
                    method: rest_method,
                    success: successCallback,
                    failure: failureCallback
                });
            }
        }
        else{
            formWindow.unmask();
            Ext.Msg.alert('Αποτυχία Υποβολής', 'Παρακαλώ διορθώστε τα λάθος πεδία');


        }

    }

});
