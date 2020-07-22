/*
 * File: app/view/company/TechnicianAnn/ShipTechFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianAnn.ShipTechFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companytechnicianannshiptechform',

    onDelete_TECHNICIAN_SANN: function(button, e, eOpts) {

        var form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        var conffun = function(buttonText) {
            if (buttonText == "yes") {
                if(form.getForm().findField("a_new_form").getValue()==="false"){
                    var res = values.url.split("/");
                    var fid = res[res.length-1];
                    var rest_method = "DELETE";
                    fid = fid + "/";
                    var successCallback = function(resp, ops) {
                        if(values.ieAnnStatus==="0"){
                            Ext.Msg.alert('Επιτυχής Διαγραφή', 'Η αίτηση διαγράφηκε');

                            var emp_comp = Ext.create('widget.companytechnciananndatesform', {});
                            emp_comp.show();
                            formWindow.destroy();
                        }

                        else{

                            Ext.Msg.alert('Επιτυχής Διαγραφή', 'Η αίτηση διαγράφηκε');
                            formWindow.destroy();
                        }


                    };

                    // Failure
                    var failureCallback = function(resp, ops) {
                        Ext.Msg.alert('Αποτυχία Διαγραφής', 'Η αίτηση δεν διαγράφηκε');

                    };

                    Ext.Ajax.request({
                        url: "/compTaSann/" + fid,
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
            if (form.findField(i).getValue() != "" && form.findField(i).getValue() != null)
                if (!form.findField(i).isValid())
                    invalidations =  true;
        }
        return !invalidations;
    },

    onSave_TECHNICIAN_SANN: function(button, e, eOpts) {
        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        if(parseInt(values.attachedDocId)!==-1 && values.deletedfile==="true" && values.file===""){
            form.getForm().findField('file').allowBlank=false;
            form.getForm().findField('file').allowOnlyWhitespace=false;
        }
        else{
            if(form.getForm().findField('a_new_form').getValue()==="true" && parseInt(values.attachedDocId)===-1){
                form.getForm().findField('file').allowBlank=false;
                form.getForm().findField('file').allowOnlyWhitespace=false;
            }
            else{
                form.getForm().findField('file').allowBlank=true;
                form.getForm().findField('file').allowOnlyWhitespace=true;
            }

        }



        if(form.isValid()){
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
                if (resp.status===304){
                    if (values.attachedDocId!==-1){
                        form.getForm().findField('attachedDocId').setValue(values.attachedDocId);
                        form.getForm().findField('file').hide();
                        var vfc = Ext.ComponentQuery.query('#Exist_File')[0];
                        vfc.show();
                    }
                    msg=decodeURIComponent(resp.getResponseHeader("SEPE_ERROR")).replace(": SEPE_ERROR:","").replace(/\+/g,' ');

                    msgDates=msg.split(":");
                    if (msgDates.length>1){
                        Ext.Msg.alert('Αποτυχία Υποβολής',
                        "Διωρθώστε το ωράριο λόγω επικάλυψης.");
                    }
                    else{
                        Ext.Msg.alert('Αποτυχία Υποβολής', msg);
                    }
                }
                else{
                    Ext.Msg.alert('Επιτυχής Υποβολή', 'Η αίτηση υποβλήθηκε με επιτυχία.');
                    formWindow.destroy();
                }

            };

            // Failure
            var failureCallback = function(resp, ops) {
                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Η αίτηση δεν αποθηκεύθηκε.');

            };

            var ship = Ext.getCmp('companyTechnicianShipAnn_Ships').getSelectionModel().getSelection()[0];
            values.compShipName = ship.get('shipName');
            values.compShipImo = ship.get('shipImo');

            values.taSpeciality=Ext.util.JSON.encode(values.taSpeciality);
            values.taSpeciality=values.taSpeciality.substring(1, values.taSpeciality.length-1);

            values.subStatus=1;
            values.taSannStatus=-2;

            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
            values.protYear=button.up('toolbar').getCurrentTimestamp(3);

            if(parseInt(values.attachedDocId)!==-1 && values.deletedfile==="true" && values.file==="")
            values.attachedDocId=-1;

            var fileurl = '/setDocument';
            if(parseInt(values.attachedDocId)!==-1 &&values.file!==null)
            fileurl = fileurl + "?docId=" + values.attachedDocId;


            Date.prototype.addDays = function(days)
            {
                var dat = new Date(this.valueOf());
                dat.setDate(dat.getDate() + days);
                return dat;
            };

            values.diaryEntries="";
            var entriesArr = [];
            values.diaryEntriesnum=parseInt(values.projDuration);
            if (parseInt(values.diaryEntriesnum)>0){
                if (parseInt(values.diaryEntriesnum)>1){
                    for (var j = 0; j < (parseInt(values.diaryEntriesnum)); j++) {
                        dat = new Date(values.projStartDate);
                        var date = dat.addDays(j);
                        date = button.up('toolbar').dateToTimestamp(date.getUTCDate()+"-"+parseInt(date.getUTCMonth()+1)+"-"+date.getUTCFullYear());
                        entriesArr.push({
                            visitDate:  date,
                            visitTime:  values.startTime,
                            visitDurationMinutes: values.durationDay
                        });
                    }
                }
                else{
                    entriesArr.push({
                        visitDate:  values.projStartDate,
                        visitTime:  values.startTime,
                        visitDurationMinutes: values.durationDay
                    });
                }

                values.diaryEntries=entriesArr;
            }
            else{
                values.diaryEntries=entriesArr;
            }

            var contrsArr = [];
            if (parseInt(values.projscontrsnum)>1){
                for (var j = 0; j < (parseInt(values.projscontrsnum)); j++) {
                    contrsArr.push({
                        firstname: values.contr_firstname[j],
                        lastname: values.contr_lastname[j],
                        afm:  values.contr_afm[j],
                        addr: values.contr_addr[j],
                        contractorSpecialty: values.contr_specialty,
                        birthplace: values.contr_birthplace[j],
                        birthdate: values.contr_birthdate[j],
                        cardNumber: values.contr_cardNumber[j],
                        cardIssuingAuth: values.contr_cardIssuingAuth[j],
                        type: values.contr_type[j],
                        cardType: values.contr_cardType[j]
                    });
                }
            }
            else{
                contrsArr.push({
                    firstname: values.contr_firstname,
                    lastname: values.contr_lastname,
                    afm:  values.contr_afm,
                    addr: values.contr_addr,
                    contractorSpecialty: values.contr_specialty,
                    birthplace: values.contr_birthplace,
                    birthdate: values.contr_birthdate,
                    cardNumber: values.contr_cardNumber,
                    cardIssuingAuth: values.contr_cardIssuingAuth,
                    type: values.contr_type,
                    cardType: values.contr_cardType
                });
            }

            delete values.contr_firstname;
            delete values.contr_lastname;
            delete values.contr_afm;
            delete values.contr_addr;
            delete values.contr_type;
            delete values.contr_specialty;
            delete values.contr_birthplace;
            delete values.contr_birthdate;
            delete values.contr_cardNumber;
            delete values.contr_cardIssuingAuth;
            delete values.contr_cardType;

            values.projscontrs = contrsArr;


            if(values.file!==""){
                form.down('form').submit({
                    url: fileurl,
                    waitMsg: 'Καταχώρηση αρχείου...',
                    success: function(form, action) {
                        values.attachedDocId=parseInt(action.result.fileId);
                        Ext.Ajax.request({
                            url: "/compTaSann/" + fid,
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
                    url: "/compTaSann/" + fid,
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

    onSubmit_TECHNICIAN_SANN: function(button, e, eOpts) {
        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        if(parseInt(values.attachedDocId)!==-1 && values.deletedfile==="true" && values.file===""){
            form.getForm().findField('file').allowBlank=false;
            form.getForm().findField('file').allowOnlyWhitespace=false;
        }
        else{
            if(form.getForm().findField('a_new_form').getValue()==="true" && parseInt(values.attachedDocId)===-1){
                form.getForm().findField('file').allowBlank=false;
                form.getForm().findField('file').allowOnlyWhitespace=false;
            }
            else{
                form.getForm().findField('file').allowBlank=true;
                form.getForm().findField('file').allowOnlyWhitespace=true;
            }

        }


        if(form.isValid()){
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
                if (resp.status===304){
                    if (values.attachedDocId!==-1){
                        form.getForm().findField('attachedDocId').setValue(values.attachedDocId);
                        form.getForm().findField('file').hide();
                        var vfc = Ext.ComponentQuery.query('#Exist_File')[0];
                        vfc.show();
                    }
                    msg=decodeURIComponent(resp.getResponseHeader("SEPE_ERROR")).replace(": SEPE_ERROR:","").replace(/\+/g,' ');

                    msgDates=msg.split(":");
                    if (msgDates.length>1){
                        Ext.Msg.alert('Αποτυχία Υποβολής',
                        "Διωρθώστε το ωράριο λόγω επικάλυψης.");
                    }
                    else{
                        Ext.Msg.alert('Αποτυχία Υποβολής', msg);
                    }
                }
                else{
                    Ext.Msg.alert('Επιτυχής Υποβολή', 'Η αίτηση υποβλήθηκε με επιτυχία.');
                    formWindow.destroy();
                }

            };

            // Failure
            var failureCallback = function(resp, ops) {
                Ext.Msg.alert('Αποτυχία Υποβολής', 'Η αίτηση δεν υποβλήθηκε.');

            };

            var ship = Ext.getCmp('companyTechnicianShipAnn_Ships').getSelectionModel().getSelection()[0];
            values.compShipName = ship.get('shipName');
            values.compShipImo = ship.get('shipImo');

            values.taSpeciality=Ext.util.JSON.encode(values.taSpeciality);
            values.taSpeciality=values.taSpeciality.substring(1, values.taSpeciality.length-1);

            values.reqStatus=5;
            values.subStatus=2;
            values.taSannStatus=0;

            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
            values.protYear=button.up('toolbar').getCurrentTimestamp(3);

            if(parseInt(values.attachedDocId)!==-1 && values.deletedfile==="true" && values.file==="")
            values.attachedDocId=-1;

            var fileurl = '/setDocument';
            if(parseInt(values.attachedDocId)!==-1 &&values.file!==null)
            fileurl = fileurl + "?docId=" + values.attachedDocId;


            Date.prototype.addDays = function(days)
            {
                var dat = new Date(this.valueOf());
                dat.setDate(dat.getDate() + days);
                return dat;
            };

            values.diaryEntries="";
            var entriesArr = [];
            values.diaryEntriesnum=parseInt(values.projDuration);
            if (parseInt(values.diaryEntriesnum)>0){
                if (parseInt(values.diaryEntriesnum)>1){
                    for (var j = 0; j < (parseInt(values.diaryEntriesnum)); j++) {
                        dat = new Date(values.projStartDate);
                        var date = dat.addDays(j);
                        date = button.up('toolbar').dateToTimestamp(date.getUTCDate()+"-"+parseInt(date.getUTCMonth()+1)+"-"+date.getUTCFullYear());
                        entriesArr.push({
                            visitDate:  date,
                            visitTime:  values.startTime,
                            visitDurationMinutes: values.durationDay
                        });
                    }
                }
                else{
                    entriesArr.push({
                        visitDate:  values.projStartDate,
                        visitTime:  values.startTime,
                        visitDurationMinutes: values.durationDay
                    });
                }

                values.diaryEntries=entriesArr;
            }
            else{
                values.diaryEntries=entriesArr;
            }


            var contrsArr = [];
            if (parseInt(values.projscontrsnum)>1){
                for (var j = 0; j < (parseInt(values.projscontrsnum)); j++) {
                    contrsArr.push({
                        firstname: values.contr_firstname[j],
                        lastname: values.contr_lastname[j],
                        afm:  values.contr_afm[j],
                        addr: values.contr_addr[j],
                        contractorSpecialty: values.contr_specialty,
                        birthplace: values.contr_birthplace[j],
                        birthdate: values.contr_birthdate[j],
                        cardNumber: values.contr_cardNumber[j],
                        cardIssuingAuth: values.contr_cardIssuingAuth[j],
                        type: values.contr_type[j],
                        cardType: values.contr_cardType[j]
                    });
                }
            }
            else{
                contrsArr.push({
                    firstname: values.contr_firstname,
                    lastname: values.contr_lastname,
                    afm:  values.contr_afm,
                    addr: values.contr_addr,
                    contractorSpecialty: values.contr_specialty,
                    birthplace: values.contr_birthplace,
                    birthdate: values.contr_birthdate,
                    cardNumber: values.contr_cardNumber,
                    cardIssuingAuth: values.contr_cardIssuingAuth,
                    type: values.contr_type,
                    cardType: values.contr_cardType
                });
            }

            delete values.contr_firstname;
            delete values.contr_lastname;
            delete values.contr_afm;
            delete values.contr_addr;
            delete values.contr_type;
            delete values.contr_specialty;
            delete values.contr_birthplace;
            delete values.contr_birthdate;
            delete values.contr_cardNumber;
            delete values.contr_cardIssuingAuth;
            delete values.contr_cardType;

            values.projscontrs = contrsArr;


            if(values.file!==""){
                form.down('form').submit({
                    url: fileurl,
                    waitMsg: 'Καταχώρηση αρχείου...',
                    success: function(form, action) {
                        values.attachedDocId=parseInt(action.result.fileId);
                        Ext.Ajax.request({
                            url: "/compTaSann/" + fid,
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
                    url: "/compTaSann/" + fid,
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

    onChange_TECHNICIAN_SANN: function(button, e, eOpts) {
        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        if(Date.parse(new Date())<Date.parse(form.getForm().findField('dateEnd').value)){

            var emp_comp = Ext.create('widget.companytechnicianannshipreplaceinfoform', {});

            var nurl = values.url;
            var res = nurl.split("/");
            var fid = res[res.length-1];

            emp_comp.down('form').getForm().findField('dateStart').setValue(new Date());
            emp_comp.down('form').getForm().findField('projStartDate').setValue(new Date());
            emp_comp.down('form').getForm().findField('dateEnd').setValue(form.getForm().findField('dateEnd').rawValue);
            emp_comp.down('form').getForm().findField('compShipId').setValue(values.compShipId);
            emp_comp.down('form').getForm().findField('compTaSannPrevId').setValue(fid);
            emp_comp.show();


            emp_comp.show();
            formWindow.destroy();
        }
        else{
            var emp_comp = Ext.create('widget.companytechnicianannshipdatesform', {});
            emp_comp.show();
            formWindow.destroy();
        }
    }

});
