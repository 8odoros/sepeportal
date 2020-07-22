/*
 * File: app/view/company/DoctorAnn/DoctorFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.DoctorAnn.DoctorFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companydoctoranndoctorform',

    onDelete_DOCTOR_ANN: function(button, e, eOpts) {

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

                            var emp_comp = Ext.create('widget.companydoctoranndatesform', {});
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
                        url: "/compIeAnn/" + fid,
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

    onSave_DOCTOR_ANN: function(button, e, eOpts) {
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

        if(parseInt(values.totalHours)<=0){
            var validTotalHours = false;
        }
        else{
            var validTotalHours = true;
        }

        if (parseInt(values.diaryEntriesnum)>0){
            var countMinutes = 0;
            if (parseInt(values.diaryEntriesnum)>1){
                for (var j = 0; j < (parseInt(values.diaryEntriesnum)); j++) {
                    countMinutes = countMinutes + parseInt(values.visitDurationMinutes[j]);
                }
            }
            else{
                countMinutes = parseInt(values.visitDurationMinutes);
            }
            if ((values.totalHours)>countMinutes)
                var validTotalMinutes = false;
            else
                var validTotalMinutes = true;
        }


        delete values.visitDurHs;
        delete values.visitDurMs;
        if(form.isValid() && validTotalHours && validTotalMinutes){
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
                    msg=decodeURIComponent(resp.getResponseHeader("SEPE_ERROR")).replace(": SEPE_ERROR:","").replace(/\+/g,' ');

                    if(msg.indexOf(":ERR:")<0) {
                        msgDates=msg.split(":");
                        if (msgDates.length>1){
                            errorDates=msgDates[msgDates.length-1].trim().split(" ");
                            for(var i=0; i<errorDates.length; i++)
                                Ext.ComponentQuery.query('timefield')[parseInt(errorDates[i])].markInvalid("Ο Ιατρός έχει άλλη επίσκεψη.");

                            Ext.Msg.alert('Αποτυχία Υποβολής',
                                "Διορθώστε τις επισκέψεις που υπάρχει επικάλυψη με το ωράριο του ΙΕ.");
                        }else {
                            Ext.Msg.alert('Αποτυχία Αποθήκευσης', "Η αίτηση δεν αποθηκευτηκε.");
                        }
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
            values.subStatus=1;
            values.ieAnnStatus=-2;

            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
            values.protYear=button.up('toolbar').getCurrentTimestamp(3);

            if(parseInt(values.attachedDocId)!==-1 && values.deletedfile==="true" && values.file==="")
                values.attachedDocId=-1;

            var fileurl = '/setDocument';
            if(parseInt(values.attachedDocId)!==-1 &&values.file!==null)
                fileurl = fileurl + "?docId=" + values.attachedDocId;


            values.diaryEntries="";
            var entriesArr = [];
            if (parseInt(values.diaryEntriesnum)>0){
                if (parseInt(values.diaryEntriesnum)>1){
                    for (var j = 0; j < (parseInt(values.diaryEntriesnum)); j++) {
                        entriesArr.push({
                            visitDate:  values.visitDate[j],
                            visitTime:  values.visitTime[j],
                            visitDurationMinutes: values.visitDurationMinutes[j],
                            compIeAnnIeId: values.ieAnnIeIdLocal[j]
                        });
                    }
                }
                else{
                    entriesArr.push({
                        visitDate:  values.visitDate,
                        visitTime:  values.visitTime,
                        visitDurationMinutes: values.visitDurationMinutes,
                        compIeAnnIeId: values.ieAnnIeIdLocal
                    });
                }

                values.diaryEntries=entriesArr;
            }
            else{
                values.diaryEntries=entriesArr;
            }

            values.ieEntries="";
            var ieEntriesArr = [];
            values.ieEntriesnum=Ext.getCmp('ieAnnIeEntries').items.length-4;
            if (parseInt(values.ieEntriesnum)>0){
                if (parseInt(values.ieEntriesnum)>1){
                    for (var j = 0; j < (parseInt(values.ieEntriesnum)); j++) {
                        ieEntriesArr.push({
                            cooperationType:  values.cooperationType[j],
                            doctorRegrequestId:  values.doctorRegrequestId[j],
                            doctorRegrequestUserId: values.doctorRegrequestUserId[j],
                            ieFullname:  values.ieFullname[j],
                            ieAfm:  values.ieAfm[j],
                            ieSpeciality: values.ieSpeciality[j],
                            ieSpecialityOther: values.ieSpecialityOther[j],
                            ieAnnIeStatus: -2
                        });
                    }
                }
                else{
                    ieEntriesArr.push({
                        cooperationType:  values.cooperationType,
                        doctorRegrequestId:  values.doctorRegrequestId[0],
                        doctorRegrequestUserId: values.doctorRegrequestUserId[0],
                        ieFullname:  values.ieFullname,
                        ieAfm:  values.ieAfm,
                        ieSpeciality: values.ieSpeciality,
                        ieSpecialityOther: values.ieSpecialityOther,
                        ieAnnIeStatus: -2
                    });
                }

                values.ieEntries=ieEntriesArr;
            }
            else{
                values.ieEntries=ieEntriesArr;
            }
            delete values.visitDate;
            delete values.visitTime;
            delete values.visitDurationMinutes;
            delete values.taAnnTaIdLocal;

            delete values.cooperationType;
            delete values.doctorRegrequestId;
            delete values.doctorRegrequestUserId;
            delete values.ieFullname;
            delete values.ieAfm;
            delete values.ieSpeciality;
            delete values.ieSpecialityOther;
            delete values.exypp;


            if(values.file!==""){
                form.down('form').submit({
                    url: fileurl,
                    waitMsg: 'Καταχώρηση αρχείου...',
                    success: function(form, action) {
                        values.attachedDocId=parseInt(action.result.fileId);
                        Ext.Ajax.request({
                            url: "/compIeAnn/" + fid,
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
                    url: "/compIeAnn/" + fid,
                    headers: { 'Content-Type': 'application/json' },
                    jsonData: Ext.util.JSON.encode(values),
                    method: rest_method,
                    success: successCallback,
                    failure: failureCallback
                });
            }

        }
        else{
            if(validTotalHours===false)
                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Δηλώστε αριθμό υπαλλήλων σε τουλάχιστον μία κατηγορία');
            else if(validTotalMinutes===false)
                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Τα συνολικά λεπτά επισκέψεων που δηλώσατε δεν είναι ανάλογα με τις ώρες που απαιτούνται');
            else
                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Παρακαλώ διορθώστε τα λάθος πεδία');
        }
    },

    onSubmit_DOCTOR_ANN: function(button, e, eOpts) {
        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();
        values.doctorRegrequestId.splice(0,1);
        values.doctorRegrequestUserId.splice(0,1);

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

        if(parseInt(values.totalHours)<=0){
            var validTotalHours = false;
        }
        else{
            var validTotalHours = true;
        }

        if (parseInt(values.diaryEntriesnum)>0){
            var countMinutes = 0;
            if (parseInt(values.diaryEntriesnum)>1){
                for (var j = 0; j < (parseInt(values.diaryEntriesnum)); j++) {
                    countMinutes = countMinutes + parseInt(values.visitDurationMinutes[j]);
                }
            }
            else{
                countMinutes = parseInt(values.visitDurationMinutes);
            }
            if ((values.totalHours)>countMinutes)
                var validTotalMinutes = false;
            else
                var validTotalMinutes = true;
        }


        if(form.isValid() && validTotalHours && validTotalMinutes){
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
                    msg=decodeURIComponent(resp.getResponseHeader("SEPE_ERROR")).replace(": SEPE_ERROR:","").replace(/\+/g,' ');

                    if(msg.indexOf(":ERR:")<0) {
                        msgDates=msg.split(":");
                        if (msgDates.length>1){
                            errorDates=msgDates[msgDates.length-1].trim().split(" ");
                            for(var i=0; i<errorDates.length; i++)
                                Ext.ComponentQuery.query('timefield')[parseInt(errorDates[i])].markInvalid("Ο Ιατρός έχει άλλη επίσκεψη.");

                            Ext.Msg.alert('Αποτυχία Υποβολής',
                                "Διορθώστε τις επισκέψεις που υπάρχει επικάλυψη με το ωράριο του ΙΕ.");
                        }else {
                            Ext.Msg.alert('Αποτυχία Αποθήκευσης', "Η αίτηση δεν αποθηκευτηκε.");
                        }
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


            values.reqStatus=5;
            values.subStatus=2;
            values.ieAnnStatus=0;

            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
            values.protYear=button.up('toolbar').getCurrentTimestamp(3);

            if(parseInt(values.attachedDocId)!==-1 && values.deletedfile==="true" && values.file==="")
                values.attachedDocId=-1;

            var fileurl = '/setDocument';
            if(parseInt(values.attachedDocId)!==-1 &&values.file!==null)
                fileurl = fileurl + "?docId=" + values.attachedDocId;


            values.diaryEntries="";
            var entriesArr = [];
            if (parseInt(values.diaryEntriesnum)>0){
                if (parseInt(values.diaryEntriesnum)>1){
                    for (var j = 0; j < (parseInt(values.diaryEntriesnum)); j++) {
                        entriesArr.push({
                            visitDate:  values.visitDate[j],
                            visitTime:  values.visitTime[j],
                            visitDurationMinutes: values.visitDurationMinutes[j],
                            compIeAnnIeId: values.ieAnnIeIdLocal[j]
                        });
                    }
                }
                else{
                    entriesArr.push({
                        visitDate:  values.visitDate,
                        visitTime:  values.visitTime,
                        visitDurationMinutes: values.visitDurationMinutes,
                        compIeAnnIeId: values.ieAnnIeIdLocal
                    });
                }

                values.diaryEntries=entriesArr;
            }
            else{
                values.diaryEntries=entriesArr;
            }

            values.ieEntries="";
            var ieEntriesArr = [];
            values.ieEntriesnum=Ext.getCmp('ieAnnIeEntries').items.length-4;
            if (parseInt(values.ieEntriesnum)>0){
                if (parseInt(values.ieEntriesnum)>1){
                    for (var j = 0; j < (parseInt(values.ieEntriesnum)); j++) {
                        ieEntriesArr.push({
                            cooperationType:  values.cooperationType[j],
                            doctorRegrequestId:  values.doctorRegrequestId[j],
                            doctorRegrequestUserId: values.doctorRegrequestUserId[j],
                            ieFullname:  values.ieFullname[j],
                            ieAfm:  values.ieAfm[j],
                            ieSpeciality: values.ieSpeciality[j],
                            ieSpecialityOther: values.ieSpecialityOther[j],
                            ieAnnIeStatus: 0
                        });
                    }
                }
                else{
                    ieEntriesArr.push({
                        cooperationType:  values.cooperationType,
                        doctorRegrequestId:  values.doctorRegrequestId[0],
                        doctorRegrequestUserId: values.doctorRegrequestUserId[0],
                        ieFullname:  values.ieFullname,
                        ieAfm:  values.ieAfm,
                        ieSpeciality: values.ieSpeciality,
                        ieSpecialityOther: values.ieSpecialityOther,
                        ieAnnIeStatus: 0
                    });
                }

                values.ieEntries=ieEntriesArr;
            }
            else{
                values.ieEntries=ieEntriesArr;
            }
            delete values.visitDate;
            delete values.visitTime;
            delete values.visitDurationMinutes;
            delete values.ieAnnIeIdLocal;

            delete values.cooperationType;
            delete values.doctorRegrequestId;
            delete values.doctorRegrequestUserId;
            delete values.ieFullname;
            delete values.ieAfm;
            delete values.ieSpeciality;
            delete values.ieSpecialityOther;
            delete values.exypp;


            if(values.file!==""){
                form.down('form').submit({
                    url: fileurl,
                    waitMsg: 'Καταχώρηση αρχείου...',
                    success: function(form, action) {
                        values.attachedDocId=parseInt(action.result.fileId);
                        Ext.Ajax.request({
                            url: "/compIeAnn/" + fid,
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
                    url: "/compIeAnn/" + fid,
                    headers: { 'Content-Type': 'application/json' },
                    jsonData: Ext.util.JSON.encode(values),
                    method: rest_method,
                    success: successCallback,
                    failure: failureCallback
                });
            }

        }
        else{
            if(validTotalHours===false)
                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Δηλώστε αριθμό υπαλλήλων σε τουλάχιστον μία κατηγορία');
            else if(validTotalMinutes===false)
                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Τα συνολικά λεπτά επισκέψεων που δηλώσατε δεν είναι ανάλογα με τις ώρες που απαιτούνται');
            else
                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Παρακαλώ διορθώστε τα λάθος πεδία');
        }
    },

    onChange_DOCTOR_ANN: function(button, e, eOpts) {
        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        if(Date.parse(new Date())<Date.parse(form.getForm().findField('dateEnd').value)){

            var emp_comp = Ext.create('widget.companydoctorannreplaceinfoform', {});

            var nurl = values.url;
            var res = nurl.split("/");
            var fid = res[res.length-1];

            emp_comp.down('form').getForm().findField('dateStart').setValue(new Date());
            emp_comp.down('form').getForm().findField('dateEnd').setValue(form.getForm().findField('dateEnd').rawValue);
            emp_comp.down('form').getForm().findField('compPtlBranchId').setValue(values.compPtlBranchId);
            emp_comp.down('form').getForm().findField('compEbrBranchId').setValue(values.compEbrBranchId);
            emp_comp.down('form').getForm().findField('compIeAnnPrevId').setValue(fid);
            emp_comp.show();


            emp_comp.show();
            formWindow.destroy();
        }
        else{
            var emp_comp = Ext.create('widget.companydoctoranndatesform', {});
            emp_comp.show();
            formWindow.destroy();
        }
    },
    onChangeProgram_DOCTOR_ANN: function(button, e, eOpts) {
        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        var fields=Ext.getCmp('diaryEntries').items;
        fields.getAt(0).show();
        var today = new Date();
        for(var i=4; i<fields.length; i++){
            //tomorrow = new Date(new Date().getTime() + 24 * 60 * 60 * 1000);
            if(fields.getAt(i).down().items.get(0).getValue()>=today){
                fields.getAt(i).down().items.get(0).setReadOnly(false);
                fields.getAt(i).down().items.get(0).minValue= (form.getForm().findField('dateStart').getValue() > today) ? form.getForm().findField('dateStart').getValue() : today;
                fields.getAt(i).down().items.get(0).maxValue=form.getForm().findField('dateEnd').getValue();
                fields.getAt(i).down().items.get(1).setReadOnly(false);
                fields.getAt(i).down().items.get(3).setReadOnly(false);
                fields.getAt(i).down().items.get(4).setReadOnly(false);
                fields.getAt(i).down().items.get(5).setReadOnly(false);
                if(i>4)
                    fields.getAt(i).down().items.get(6).show();
            }
        }


        Ext.Msg.alert('Ενεργοποίηση Αλλαγών Προγράμματος', 'Πλοηγηθείτε και κάντε αλλαγές στο πρόγραμμα. Στη συνέχεια πατήστε Αποθήκευση Αλλαγών. \n Προσοχή μπορείτε να αλλάξτε τις επισκέψεις που δεν έχει περάσει η ημ/νια τους.');

        button.up('toolbar').getComponent('changeProgrambutton').hide();
        button.up('toolbar').getComponent('changeProgramSavebutton').show();
    },

    onChangeProgramSave_DOCTOR_ANN: function(button, e, eOpts) {
        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        form.getForm().findField('exyppBasic').allowNull=true; //toValidateForm correct
        form.getForm().findField('file').allowBlank=true;
        values.diaryEntriesnum=Ext.getCmp('diaryEntries').items.length-5;
        if (parseInt(values.diaryEntriesnum)>0){
            var countMinutes = 0;
            if (parseInt(values.diaryEntriesnum)>1){
                for (var j = 0; j < (parseInt(values.diaryEntriesnum)); j++) {
                    countMinutes = countMinutes + parseInt(values.visitDurationMinutes[j]);
                }
            }
            else{
                countMinutes = parseInt(values.visitDurationMinutes);
            }
            if ((values.totalHours)>countMinutes)
                var validTotalMinutes = false;
            else
                var validTotalMinutes = true;
        }


        if(form.isValid() &&  validTotalMinutes){
            var nurl = values.url;
            var res = nurl.split("/");
            var fid = res[res.length-1];


            var successCallback = function(resp, ops) {
                if (resp.status===304){
                    msg=decodeURIComponent(resp.getResponseHeader("SEPE_ERROR")).replace(": SEPE_ERROR:","").replace(/\+/g,' ');

                    msgDates=msg.split(":");
                    if (msgDates.length>1){
                        errorDates=msgDates[msgDates.length-1].trim().split(" ");
                        for(var i=0; i<errorDates.length; i++)
                            Ext.ComponentQuery.query('timefield')[parseInt(errorDates[i])].markInvalid("Ο Ιατρός έχει άλλη επίσκεψη.");

                        Ext.Msg.alert('Αποτυχία Καταχώρησης',
                            "Διορθώστε τις επισκέψεις που υπάρχει επικάλυψη με το ωράριο του ΙΕ.");
                    }
                    else{
                        Ext.Msg.alert('Αποτυχία Καταχώρησης', msg);
                    }
                }
                else{
                    if (Ext.JSON.decode(resp.responseText).success) {

                        Ext.Msg.alert('Επιτυχής Καταχώρηση', 'Οι αλλαγές καταχωρήθηκαν με επιτυχία.');
                        formWindow.destroy();
                    }
                    else{
                        if (Ext.JSON.decode(resp.responseText).error) {

                            Ext.Msg.alert('Επιτυχής Καταχώρηση', Ext.JSON.decode(resp.responseText).errorMsg);
                        }
                        else
                            Ext.Msg.alert('Αποτυχία Υποβολής', 'Οι αλλαγές δεν καταχωρήθηκαν.');
                    }
                }

            };

            var failureCallback = function(resp, ops) {
                Ext.Msg.alert('Αποτυχία Υποβολής', 'Οι αλλαγές δεν καταχωρήθηκαν.');

            };

            var entriesArr = [];
            if (parseInt(values.diaryEntriesnum)>0){
                if (parseInt(values.diaryEntriesnum)>1){
                    for (var j = 0; j < (parseInt(values.diaryEntriesnum)); j++) {
                        entriesArr.push({
                            visitDate:  values.visitDate[j],
                            visitTime:  values.visitTime[j],
                            visitDurationMinutes: values.visitDurationMinutes[j],
                            compIeAnnIeId: values.ieAnnIeIdLocal[j]
                        });
                    }
                }
                else{
                    entriesArr.push({
                        visitDate:  values.visitDate,
                        visitTime:  values.visitTime,
                        visitDurationMinutes: values.visitDurationMinutes,
                        compIeAnnIeId: values.ieAnnIeIdLocal
                    });
                }

            }


            var valuesSend = {};
            valuesSend.id=fid;
            valuesSend.companyId=values.companyId;
            valuesSend.diaryEntries=entriesArr;
            Ext.Ajax.request({
                url: "/updateIeAnnProgram/" ,
                headers: { 'Content-Type': 'application/json' },
                jsonData: Ext.util.JSON.encode(valuesSend),
                method: "POST",
                success: successCallback,
                failure: failureCallback
            });
        }

        else{
            if(validTotalMinutes===false)
                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Τα συνολικά λεπτά επισκέψεων που δηλώσατε δεν είναι ανάλογα με τις ώρες που απαιτούνται');
            else
                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Παρακαλώ διορθώστε τα λάθος πεδία');
        }
    }

});
