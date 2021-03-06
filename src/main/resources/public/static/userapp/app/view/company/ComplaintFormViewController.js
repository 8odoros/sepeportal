/*
 * File: app/view/company/ComplaintFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.ComplaintFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companycomplaintform',

    onDelete_COMPANY_COMPLAINT: function(button, e, eOpts) {

        var form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        if(values.subStatus==="1" && form.getForm().findField("a_new_form").getValue()==="false"){
            var res = values.url.split("/");
            var fid = res[res.length-1];
            var rest_method = "DELETE";
            fid = fid + "/";
            var successCallback = function(resp, ops) {

                Ext.Msg.alert('Επιτυχής Διαγραφή', 'Η καταγγελία διαγράφηκε');

                // Close window
                formWindow.destroy();

            };

            // Failure
            var failureCallback = function(resp, ops) {
                Ext.Msg.alert('Αποτυχία Διαγραφής', 'Η καταγγελία δεν διαγράφηκε');

            };

            Ext.Ajax.request({
                url: "/compComplaint/" + fid,
                headers: { 'Content-Type': 'application/json' },
                method: rest_method,
                success: successCallback,
                failure: failureCallback
            });
        }
        else{
            Ext.Msg.alert('Αποτυχία', 'Η φόρμα δεν έπρεπε να φθάσει σε αυτή την κατάσταση');

        }
    },

    onSave_COMPANY_COMPLAINT: function(button, e, eOpts) {

        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

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

            var successCallback = function(resp, ops) {

                Ext.Msg.alert('Επιτυχής Αποθήκευση', 'Η καταγγελία με τίτλο "'+values.complDescr.substring(0, 14)+'..." αποθηκεύτηκε με επιτυχία');

                // Close window
                formWindow.destroy();

            };

            // Failure
            var failureCallback = function(resp, ops) {
                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Η καταγγελία με τίτλο "'+values.complDescr.substring(0, 14)+'..." δεν αποθηκεύτηκε.');

            };



            values.subStatus=1;

            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
            values.protYear=button.up('toolbar').getCurrentTimestamp(3);

            if(values.complInvolves==='1'){
                values.complInvolvesLabRelations='0';
                values.complInvlovesSafetyInsp='1';
            }
            else if(values.complInvolves==='0'){
                values.complInvolvesLabRelations='1';
                values.complInvlovesSafetyInsp='0';
            }

            values.complMatter=values.complMatter.toString();

            if(parseInt(values.docIdAttached)!==-1 && values.deletedfile==="true" && values.file==="")
            values.docIdAttached=-1;

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
                            url: "/compComplaint/" + fid,
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
                    url: "/compComplaint/" + fid,
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

    onSubmit_COMPANY_COMPLAINT: function(button, e, eOpts) {
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

                Ext.Msg.alert('Επιτυχής Υποβολή', 'Η καταγγελία με τίτλο "'+values.complDescr.substring(0, 14)+'..." υποβλήθηκε με επιτυχία');
                formWindow.destroy();

            };

            // Failure
            var failureCallback = function(resp, ops) {

                Ext.Msg.alert('Αποτυχία Υποβολής', 'Η καταγγελία με τίτλο "'+values.complDescr.substring(0, 14)+'..." δεν έγινε δεκτή από το σύστημα');

            };


            values.complMatter=values.complMatter.toString();

            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
            values.protYear=button.up('toolbar').getCurrentTimestamp(3);

            if(values.complInvolves==='1'){
                values.complInvolvesLabRelations='0';
                values.complInvlovesSafetyInsp='1';
            }
            else if(values.complInvolves==='0'){
                values.complInvolvesLabRelations='1';
                values.complInvlovesSafetyInsp='0';
            }

            values.reqStatus=1;
            values.subStatus=2;

            if(parseInt(values.docIdAttached)!==-1 && values.deletedfile==="true" && values.file==="")
            values.docIdAttached=-1;

            var fileurl = '/setDocument';
            if(parseInt(values.docIdAttached)!==-1 &&values.file!==null)
            fileurl = fileurl + "?docId=" + values.docIdAttached;

            if(values.file!==""){
                form.down('form').submit({
                    url: fileurl,
                    waitMsg: 'Καταχώρηση αρχείου...',
                    success: function(form, action) {
                        values.docIdAttached=parseInt(action.result.fileId);
                        Ext.Ajax.request({
                            url: "/compComplaint/" + fid,
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
                    url: "/compComplaint/" + fid,
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
