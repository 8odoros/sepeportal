/*
 * File: app/view/company/SundayPmtForm/SundayPmtFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.SundayPmtForm.SundayPmtFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companysundaypmtformsundaypmtform',

    onDelete_COMPANY_SUNDAY_PMT: function(button, e, eOpts) {

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
                        url: "/compSundayPmt/" + fid,
                        headers: { 'Content-Type': 'application/json' },
                        async: false,
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

    onSave_COMPANY_SUNDAY_PMT: function(button, e, eOpts) {
        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        if(parseInt(values.attachedDocId)!==-1 && values.deletedfile==="true" && values.file===""){
            form.getForm().findField('file').allowBlank=false;
            form.getForm().findField('file').allowOnlyWhitespace=false;
        }
        else{
            if(form.getForm().findField('a_new_form').getValue()==="true"){
                form.getForm().findField('file').allowBlank=false;
                form.getForm().findField('file').allowOnlyWhitespace=false;
            }
            else{
                form.getForm().findField('file').allowBlank=true;
                form.getForm().findField('file').allowOnlyWhitespace=true;
            }

        }

        for (var j = 0; j < (parseInt(values.persnum)); j++) {
            if (values.empAlternateRestingDayView[j]!==""){
                if (parseInt(values.persnum)>1){
                    var hdiff = parseInt(values.empWorkHourStop[j].split(":")[0])-parseInt(values.empWorkHourStart[j].split(":")[0]);
                    var mdiff = parseInt(values.empWorkHourStop[j].split(":")[1])-parseInt(values.empWorkHourStart[j].split(":")[1]);
                }
                else{
                    var hdiff = parseInt(values.empWorkHourStop.split(":")[0])-parseInt(values.empWorkHourStart.split(":")[0]);
                    var mdiff = parseInt(values.empWorkHourStop.split(":")[1])-parseInt(values.empWorkHourStart.split(":")[1]);
                }
                if (hdiff<5){
                    Ext.ComponentQuery.query('datefield')[j+2].allowBlank=true;
                    Ext.ComponentQuery.query('datefield')[j+2].allowOnlyWhitespace=true;
                }
                else if(hdiff>5){
                    Ext.ComponentQuery.query('datefield')[j+2].allowBlank=false;
                    Ext.ComponentQuery.query('datefield')[j+2].allowOnlyWhitespace=false;

                }
                else if(hdiff===5){
                    if (mdiff>0){
                        Ext.ComponentQuery.query('datefield')[j+2].allowBlank=false;
                        Ext.ComponentQuery.query('datefield')[j+2].allowOnlyWhitespace=false;
                    }
                    else{
                        Ext.ComponentQuery.query('datefield')[j+2].allowBlank=true;
                        Ext.ComponentQuery.query('datefield')[j+2].allowOnlyWhitespace=true;
                    }
                }
            }
        }

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

                formWindow.unmask();
                Ext.Msg.alert('Επιτυχής Αποθήκευση', 'Η αναγγελία αποθηκεύθηκε με επιτυχία.');
                formWindow.destroy();

            };

            // Failure
            var failureCallback = function(resp, ops) {

                formWindow.unmask();
                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Η αναγγελία δεν έγινε δεκτή από το σύστημα.');

            };

            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
            values.protYear=button.up('toolbar').getCurrentTimestamp(3);


            values.sundayDate= values.sundayDateView == '' ? null : button.up('toolbar').dateToTimestamp(values.sundayDateView);
            values.holidayDate= values.holidayDate == '' ? null : button.up('toolbar').dateToTimestamp(values.holidayDate);

            values.subStatus=1;

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
                            url: "/compSundayPmt/" + fid,
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
            else {
                formWindow.mask('Αποθήκευση Αναγγελίας...', 'x-mask-loading');
                Ext.Ajax.request({
                    url: "/compSundayPmt/" + fid,
                    headers: {'Content-Type': 'application/json'},
                    jsonData: Ext.util.JSON.encode(values),
                    async: false,
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

    onSubmit_COMPANY_SUNDAY_PMT: function(button, e, eOpts) {
        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        if(parseInt(values.attachedDocId)!==-1 && values.deletedfile==="true" && values.file===""){
            form.getForm().findField('file').allowBlank=false;
            form.getForm().findField('file').allowOnlyWhitespace=false;
        }
        else{
            if(parseInt(values.attachedDocId)!==-1){
                form.getForm().findField('file').allowBlank=true;
                form.getForm().findField('file').allowOnlyWhitespace=true;
            }
            else {
                form.getForm().findField('file').allowBlank=false;
                form.getForm().findField('file').allowOnlyWhitespace=false;
            }
        }

        for (var j = 0; j < (parseInt(values.persnum)); j++) {
            if (values.empAlternateRestingDayView[j]!==""){
                if (parseInt(values.persnum)>1){
                    var hdiff = parseInt(values.empWorkHourStop[j].split(":")[0])-parseInt(values.empWorkHourStart[j].split(":")[0]);
                    var mdiff = parseInt(values.empWorkHourStop[j].split(":")[1])-parseInt(values.empWorkHourStart[j].split(":")[1]);
                }
                else{
                    var hdiff = parseInt(values.empWorkHourStop.split(":")[0])-parseInt(values.empWorkHourStart.split(":")[0]);
                    var mdiff = parseInt(values.empWorkHourStop.split(":")[1])-parseInt(values.empWorkHourStart.split(":")[1]);
                }
                if (hdiff<5){
                    Ext.ComponentQuery.query('datefield')[j+2].allowBlank=true;
                    Ext.ComponentQuery.query('datefield')[j+2].allowOnlyWhitespace=true;
                }
                else if(hdiff>5){
                    Ext.ComponentQuery.query('datefield')[j+2].allowBlank=false;
                    Ext.ComponentQuery.query('datefield')[j+2].allowOnlyWhitespace=false;

                }
                else if(hdiff===5){
                    if (mdiff>0){
                        Ext.ComponentQuery.query('datefield')[j+2].allowBlank=false;
                        Ext.ComponentQuery.query('datefield')[j+2].allowOnlyWhitespace=false;
                    }
                    else{
                        Ext.ComponentQuery.query('datefield')[j+2].allowBlank=true;
                        Ext.ComponentQuery.query('datefield')[j+2].allowOnlyWhitespace=true;
                    }
                }
            }
        }

        var currentDate = new Date();
        var sundayDate = form.getForm().findField('sundayDateView').value;

        var diff = sundayDate - currentDate;
        var oneDay = 1000 * 60 * 60 * 24;
        var day = Math.floor(diff / oneDay);

        if((day < 1 && day > -2) || (day == 1 && currentDate.getHours() > 12)) {
            Ext.Msg.alert('Αποτυχία Υποβολής', 'Δεν μπορείτε πλέον να αιτηθείτε άδεια εργασίας για την Κυριακή της τρέχουσας εβδομάδας.');
        } else {

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


                values.sundayDate= values.sundayDateView == '' ? null : button.up('toolbar').dateToTimestamp(values.sundayDateView);
                values.holidayDate= values.holidayDate == '' ? null : button.up('toolbar').dateToTimestamp(values.holidayDate);

                values.subStatus=2;
                values.reqStatus=5;

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
                                url: "/compSundayPmt/" + fid,
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
                else {
                    formWindow.mask('Αναμονή υποβολής...', 'x-mask-loading');
                    Ext.Ajax.request({
                        url: "/compSundayPmt/" + fid,
                        headers: {'Content-Type': 'application/json'},
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

    }

});
