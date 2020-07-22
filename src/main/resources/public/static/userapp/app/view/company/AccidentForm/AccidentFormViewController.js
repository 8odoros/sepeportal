/*
 * File: app/view/company/AccidentForm/AccidentFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.AccidentForm.AccidentFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companyaccidentformaccidentform',
    
    onDelete_COMPANY_ACCIDENT: function(button, e, eOpts) {

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
                            Ext.Msg.alert('Επιτυχής Διαγραφή', 'Η αναγγελία διαγράφηκε');

                            // Close window
                            formWindow.destroy();

                        };

                        // Failure
                        var failureCallback = function(resp, ops) {
                            formWindow.unmask();
                            Ext.Msg.alert('Αποτυχία Διαγραφής', 'Η αναγγελία δεν διαγράφηκε');

                        };



                        formWindow.mask('Διαγραφή Αναγγελίας...', 'x-mask-loading');
                        Ext.Ajax.request({
                            url: "/companyAccident/" + fid,
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
    
    onSave_COMPANY_ACCIDENT: function(button, e, eOpts) {
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


            values.accidentDate=button.up('toolbar').dateToTimestamp(values.accidentDateView);
            values.empRecruitmentDate=button.up('toolbar').dateToTimestamp(values.empRecruitmentDateView);
            values.empBirthdate= null; //button.up('toolbar').dateToTimestamp(values.empBirthdateView);


            values.witnessesObj="";
            var witnessesArr = [];
            if (parseInt(values.witnessesnum)>0){
                if (parseInt(values.witnessesnum)>1){
                    for (var j = 0; j < (parseInt(values.witnessesnum)); j++) {
                        witnessesArr.push({
                            incNum: j+1,
                            firstname: values.wname[j],
                            lastname: values.wsurname[j],
                            addr:  values.waddr[j],
                            addrTk:  values.wtk[j],
                            phone:  values.wphone[j],
                            typeId:  values.wtype[j],
                            citizenshipCd: values.wnationality[j]
                        });
                    }
                }
                else{
                    witnessesArr.push({
                        incNum: 1,
                        firstname: values.wname,
                        lastname: values.wsurname,
                        addr:  values.waddr,
                        addrTk:  values.wtk,
                        phone:  values.wphone,
                        typeId:  values.wtype,
                        citizenshipCd: values.wnationality
                    });
                }

                values.witnessesObj=witnessesArr;
            }
            else{
                values.witnessesObj=witnessesArr;
            }

            delete values.wname;
            delete values.wsurname;
            delete values.waddr;
            delete values.wtk;
            delete values.wphone;
            delete values.wtype;
            delete values.wnationality;

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
                        formWindow.mask('Αποθήκευση Αναγγελίας...', 'x-mask-loading');
                        Ext.Ajax.request({
                            url: "/companyAccident/" + fid,
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
                    url: "/companyAccident/" + fid,
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

    onSubmit_COMPANY_ACCIDENT: function(button, e, eOpts) {
        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        if (values.empCitizenshipCd != "" && isNaN(parseInt(values.empCitizenshipCd, 10)))
        {
            Ext.Msg.alert('Αποτυχία Υποβολής', 'Παρακαλώ διορθώστε την υπηκοότητα');
            return;
        }

        if (values.witnessesnum == 1 && values.wnationality != "" && isNaN(parseInt(values.wnationality, 10)))
        {
            Ext.Msg.alert('Αποτυχία Υποβολής', 'Παρακαλώ διορθώστε την υπηκοότητα του μάρτυρα');
            return;
        }
        else if (values.witnessesnum > 1)
        {
            for (var i = 0; i < values.witnessesnum; i++)
            {
                if (values.wnationality[i] != "" && isNaN(parseInt(values.wnationality[i], 10)))
                {
                    Ext.Msg.alert('Αποτυχία Υποβολής', 'Παρακαλώ διορθώστε την υπηκοότητα των μαρτύρων');
                    return;
                }
            }
        }

        if (values.empSpecialty != "" && isNaN(parseInt(values.empSpecialty, 10)))
        {
            Ext.Msg.alert('Αποτυχία Υποβολής', 'Παρακαλώ διορθώστε την ειδικότητα');
            return;
        }

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
                Ext.Msg.alert('Επιτυχής Υποβολή', 'Η αναγγελία υποβλήθηκε με επιτυχία.');
                formWindow.destroy();

            };

            // Failure
            var failureCallback = function(resp, ops) {

                formWindow.unmask();
                Ext.Msg.alert('Αποτυχία Υποβολής', 'Η αναγγελία δεν έγινε δεκτή από το σύστημα.');

            };

            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
            values.protYear=button.up('toolbar').getCurrentTimestamp(3);


            values.accidentDate=button.up('toolbar').dateToTimestamp(values.accidentDateView);
            values.empRecruitmentDate=button.up('toolbar').dateToTimestamp(values.empRecruitmentDateView);
            values.empBirthdate= null; //button.up('toolbar').dateToTimestamp(values.empBirthdateView);

            values.witnessesObj="";
            var witnessesArr = [];
            if (parseInt(values.witnessesnum)>0){
                if (parseInt(values.witnessesnum)>1){
                    for (var j = 0; j < (parseInt(values.witnessesnum)); j++) {
                        witnessesArr.push({
                            incNum: j+1,
                            firstname: values.wname[j],
                            lastname: values.wsurname[j],
                            addr:  values.waddr[j],
                            addrTk:  values.wtk[j],
                            phone:  values.wphone[j],
                            typeId:  values.wtype[j],
                            citizenshipCd: values.wnationality[j]
                        });
                    }
                }
                else{
                    witnessesArr.push({
                        incNum: 1,
                        firstname: values.wname,
                        lastname: values.wsurname,
                        addr:  values.waddr,
                        addrTk:  values.wtk,
                        phone:  values.wphone,
                        typeId:  values.wtype,
                        citizenshipCd: values.wnationality
                    });
                }

                values.witnessesObj=witnessesArr;
            }
            else{
                values.witnessesObj=witnessesArr;
            }

            delete values.wname;
            delete values.wsurname;
            delete values.waddr;
            delete values.wtk;
            delete values.wphone;
            delete values.wtype;
            delete values.wnationality;

            values.subStatus=2;
            values.reqStatus=1;


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
                        formWindow.mask('Αναμονή υποβολής...', 'x-mask-loading');
                        Ext.Ajax.request({
                            url: "/companyAccident/" + fid,
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
                    url: "/companyAccident/" + fid,
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
