/*
 * File: app/view/company/ProjectAnnForm/ProjectAnnFromViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.ProjectAnnForm.ProjectAnnFromViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companyprojectannformprojectannform',

    onDelete_COMPANY_PROJECTANN: function(button, e, eOpts) {

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
                        Ext.Msg.alert('Επιτυχής Διαγραφή', 'Η γνωστοποίηση διαγράφηκε');

                        // Close window
                        formWindow.destroy();

                    };

                    // Failure
                    var failureCallback = function(resp, ops) {
                        formWindow.unmask();
                        Ext.Msg.alert('Αποτυχία Διαγραφής', 'Η γνωστοποίηση δεν διαγράφηκε');

                    };

                    formWindow.mask('Διαγραφή Αναγγελίας...', 'x-mask-loading');
                    Ext.Ajax.request({
                        url: "/compProjAnn/" + fid,
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

        var msb = Ext.MessageBox.confirm('Διαγραφή φόρμας', 'Είστε σίγουροι ότι θέλετε να διαγράψετε τη φόρμα;', conffun);
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

    onSave_COMPANY_PROJECTANN: function(button, e, eOpts) {
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

                formWindow.unmask();
                Ext.Msg.alert('Επιτυχής Αποθήκευση', 'Η γνωστοποίηση αποθηκεύθηκε με επιτυχία.');
                formWindow.destroy();

            };

            // Failure
            var failureCallback = function(resp, ops) {

                formWindow.unmask();
                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Η γνωστοποίηση δεν έγινε δεκτή από το σύστημα.');

            };

            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
            values.protYear=button.up('toolbar').getCurrentTimestamp(3);
            values.projStartDate=button.up('toolbar').dateToTimestamp(values.projStartDateView);

            values.subStatus=1;

            if(values.attachedDocId!=="-1" && values.deletedfile==="true" && values.file==="")
            values.attachedDocId=-1;

            var studiersArr = [];
            var selfEmplsArr = [];
            for (var j = 0; j < (parseInt(values.projsstudiersnum) + parseInt(values.projsmanagersnum) + parseInt(values.projsselfemplsnum)); j++) {
                if(values.ms_type[j]!=="3" ){
                    studiersArr.push({
                        firstname: values.ms_firstname[j],
                        lastname: values.ms_lastname[j],
                        afm: values.ms_afm[j],
                        addr: values.ms_addr[j],
                        type: parseInt(values.ms_type[j])
                    });
                }
                else{
                    selfEmplsArr.push({
                        firstname: values.ms_firstname[j],
                        lastname: values.ms_lastname[j],
                        afm:  values.ms_afm[j],
                        addr: values.ms_addr[j]
                    });
                }
            }

            delete values.ms_firstname;
            delete values.ms_lastname;
            delete values.ms_afm;
            delete values.ms_addr;
            delete values.ms_type;

            var engsArr = [];
            for (var j = 0; j < (parseInt(values.projsengs1num) + parseInt(values.projsengs2num) + parseInt(values.projsengs3num)); j++) {
                engsArr.push({
                    teeNum: values.eng_teeNum[j],
                    firstname: values.eng_firstname[j],
                    lastname: values.eng_lastname[j],
                    afm:  values.eng_afm[j],
                    addr: values.eng_addr[j],
                    engineerSpeciality: values.eng_engineerSpeciality[j],
                    invlolvementType: parseInt(values.eng_invlolvementType[j])
                });
            }

            delete values.eng_firstname;
            delete values.eng_lastname;
            delete values.eng_afm;
            delete values.eng_addr;
            delete values.eng_engineerSpeciality;
            delete values.eng_invlolvementType;
            delete values.eng_teeNum;

            var contrsArr = [];
            if (parseInt(values.projscontrsnum)>1){
                for (var j = 0; j < (parseInt(values.projscontrsnum)); j++) {
                    contrsArr.push({
                        firstname: values.contr_firstname[j],
                        lastname: values.contr_lastname[j],
                        afm:  values.contr_afm[j],
                        addr: values.contr_addr[j],
                        type: parseInt(values.contr_type[j]),
                        phone: values.contr_phone[j],
                    });
                }
            }
            else{
                contrsArr.push({
                    firstname: values.contr_firstname,
                    lastname: values.contr_lastname,
                    afm:  values.contr_afm,
                    addr: values.contr_addr,
                    type: parseInt(values.contr_type),
                    phone: values.contr_phone
                });
            }

            delete values.contr_firstname;
            delete values.contr_lastname;
            delete values.contr_afm;
            delete values.contr_addr;
            delete values.contr_type;
            delete values.contr_phone;

            values.projscontrs = contrsArr;
            values.projsengs = engsArr;
            values.projsstudiers = studiersArr;
            values.projsselfempls = selfEmplsArr;

            var fileurl = '/setDocument';
            if(values.attachedDocId!=="-1" &&values.file!==null)
            fileurl = fileurl + "?docId=" + values.attachedDocId;

            if(values.file!==""){
                form.down('form').submit({
                    url: fileurl,
                    waitMsg: 'Καταχώρηση αρχείου...',
                    success: function(form, action) {
                        values.attachedDocId=parseInt(action.result.fileId);
                        formWindow.mask('Αποθήκευση Αναγγελίας...', 'x-mask-loading');
                        Ext.Ajax.request({
                            url: "/compProjAnn/" + fid,
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
                    url: "/compProjAnn/" + fid,
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
            Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Παρακαλώ διορθώστε τα λάθος πεδία');

        }
    },

    onSubmit_COMPANY_PROJECTANN: function(button, e, eOpts) {
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

            // Success
            var successCallback = function(resp, ops) {

                formWindow.unmask();
                Ext.Msg.alert('Επιτυχής Υποβολή', 'Η γνωστοποίηση υποβλήθηκε με επιτυχία.');
                formWindow.destroy();

            };

            // Failure
            var failureCallback = function(resp, ops) {

                formWindow.unmask();
                Ext.Msg.alert('Αποτυχία Υποβολής', 'Η γνωστοποίηση δεν έγινε δεκτή από το σύστημα.');

            };

            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
            values.protYear=button.up('toolbar').getCurrentTimestamp(3);

            values.projStartDate=button.up('toolbar').dateToTimestamp(values.projStartDateView);

            values.subStatus=2;
            values.reqStatus=1;

            var studiersArr = [];
            var selfEmplsArr = [];
            for (var j = 0; j < (parseInt(values.projsstudiersnum) + parseInt(values.projsmanagersnum) + parseInt(values.projsselfemplsnum)); j++) {
                if(values.ms_type[j]!=="3" ){
                    studiersArr.push({
                        firstname: values.ms_firstname[j],
                        lastname: values.ms_lastname[j],
                        afm: values.ms_afm[j],
                        addr: values.ms_addr[j],
                        type: parseInt(values.ms_type[j])
                    });
                }
                else{
                    selfEmplsArr.push({
                        firstname: values.ms_firstname[j],
                        lastname: values.ms_lastname[j],
                        afm:  values.ms_afm[j],
                        addr: values.ms_addr[j]
                    });
                }
            }

            delete values.ms_firstname;
            delete values.ms_lastname;
            delete values.ms_afm;
            delete values.ms_addr;
            delete values.ms_type;

            var engsArr = [];
            for (var j = 0; j < (parseInt(values.projsengs1num) + parseInt(values.projsengs2num) + parseInt(values.projsengs3num)); j++) {
                engsArr.push({
                    teeNum: values.eng_teeNum[j],
                    firstname: values.eng_firstname[j],
                    lastname: values.eng_lastname[j],
                    afm:  values.eng_afm[j],
                    addr: values.eng_addr[j],
                    engineerSpeciality: values.eng_engineerSpeciality[j],
                    invlolvementType: parseInt(values.eng_invlolvementType[j])
                });
            }

            delete values.eng_firstname;
            delete values.eng_lastname;
            delete values.eng_afm;
            delete values.eng_addr;
            delete values.eng_engineerSpeciality;
            delete values.eng_invlolvementType;
            delete values.eng_teeNum;

            var contrsArr = [];
            if (parseInt(values.projscontrsnum)>1){
                for (var j = 0; j < (parseInt(values.projscontrsnum)); j++) {
                    contrsArr.push({
                        firstname: values.contr_firstname[j],
                        lastname: values.contr_lastname[j],
                        afm:  values.contr_afm[j],
                        addr: values.contr_addr[j],
                        type: parseInt(values.contr_type[j]),
                        phone: values.contr_phone[j]
                    });
                }
            }
            else{
                contrsArr.push({
                    firstname: values.contr_firstname,
                    lastname: values.contr_lastname,
                    afm:  values.contr_afm,
                    addr: values.contr_addr,
                    type: parseInt(values.contr_type),
                    phone: values.contr_phone
                });
            }

            delete values.contr_firstname;
            delete values.contr_lastname;
            delete values.contr_afm;
            delete values.contr_addr;
            delete values.contr_type;
            delete values.contr_phone;

            values.projscontrs = contrsArr;
            values.projsengs = engsArr;
            values.projsstudiers = studiersArr;
            values.projsselfempls = selfEmplsArr;

            if(values.attachedDocId!=="-1" && values.deletedfile==="true" && values.file==="")
            values.attachedDocId=-1;

            var fileurl = '/setDocument';
            if(values.attachedDocId!=="-1" &&values.file!==null)
            fileurl = fileurl + "?docId=" + values.attachedDocId;

            if(values.file!==""){
                form.down('form').submit({
                    url: fileurl,
                    waitMsg: 'Καταχώρηση αρχείου...',
                    success: function(form, action) {
                        values.attachedDocId=parseInt(action.result.fileId);
                        formWindow.mask('Αναμονή υποβολής...', 'x-mask-loading');
                        Ext.Ajax.request({
                            url: "/compProjAnn/" + fid,
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
                    url: "/compProjAnn/" + fid,
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
