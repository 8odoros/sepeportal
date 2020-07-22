/*
 * File: app/view/company/TechnicianAnn/TechnicianFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianAnn.TechnicianFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companytechniciananntechnicianform',

    onDelete_TECHNICIAN_ANN: function(button, e, eOpts) {

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
                            formWindow.destroy();
                            Ext.Msg.alert('Επιτυχής Διαγραφή', 'Η αίτηση διαγράφηκε');

                            var emp_comp = Ext.create('widget.companytechniciananndatesform', {});
                            emp_comp.show();

                        }

                        else{
                            formWindow.destroy();
                            Ext.Msg.alert('Επιτυχής Διαγραφή', 'Η αίτηση διαγράφηκε');

                        }


                    };

                    // Failure
                    var failureCallback = function(resp, ops) {
                        Ext.Msg.alert('Αποτυχία Διαγραφής', 'Η αίτηση δεν διαγράφηκε');

                    };

                    Ext.Ajax.request({
                        url: "/compTaAnn/" + fid,
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

        var exclude = ["visitDate", "visitDurHs", "visitDurMs", "visitDurationMinutes"];

        for (var i in values)
        {
            form.findField(i).clearInvalid();
            if (form.findField(i).getValue() != "" && form.findField(i).getValue() != null && exclude.indexOf(i) == -1)
                if (!form.findField(i).isValid())
                    invalidations =  true;
        }
        return !invalidations;
    },

    onSave_TECHNICIAN_ANN: function(button, e, eOpts) {
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

        form.getForm().applyToFields({disabled:true});

        if(form.isValid()){
            
            form.getForm().applyToFields({disabled:false});

            formWindow.mask('Παρακαλώ Περιμένετε...');

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
                if (resp.status===304){
                    if (values.attachedDocId!==-1){
                        form.getForm().findField('attachedDocId').setValue(values.attachedDocId);
                        form.getForm().findField('file').hide();
                        var vfc = Ext.ComponentQuery.query('#Exist_File')[0];
                        vfc.show();
                    }
                    msg=decodeURIComponent(resp.getResponseHeader("SEPE_ERROR")).replace(": SEPE_ERROR:","").replace(/\+/g,' ');

                    if(msg.indexOf(":ERR:")<0) {
                        msgDates = msg.split(":");
                        if (msgDates.length > 1) {
                            errorDates = msgDates[msgDates.length - 1].trim().split(" ");
                            Ext.suspendLayouts();
                            for (var i = 0; i < errorDates.length; i++)
                                Ext.ComponentQuery.query('timefield')[parseInt(errorDates[i])].markInvalid("Ο TA έχει άλλη επίσκεψη.");
                            Ext.resumeLayouts(true);

                            Ext.Msg.alert('Αποτυχία Αποθήκευσης',
                                "Διορθώστε τις επισκέψεις που υπάρχει επικάλυψη με το ωράριο του TA.");
                        }else {
                            Ext.Msg.alert('Αποτυχία Αποθήκευσης', "Η αίτηση δεν αποθηκευτηκε.");
                        }
                    }
                    else{
                        Ext.Msg.alert('Αποτυχία Αποθήκευσης', msg.substring(16));
                    }
                }
                else{
                    formWindow.destroy();
                    Ext.Msg.alert('Επιτυχής Αποθήκευσης', 'Η αίτηση αποθηκεύτηκε με επιτυχία.');
                }
            };

            // Failure
            var failureCallback = function(resp, ops) {
                formWindow.unmask();
                if (resp.responseText.includes("SP_PTL.SP_PTL_COMP_TAANNDIARY_UNQ"))
                    Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Υπάρχουν διπλές ή κενές επισκέψεις.');
                else if (resp.responseText.includes("SP_PTL.SP_PTL_COMPTAANN_UNQ"))
                    Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Λείπουν ή υπάρχουν δύο φορές στοιχεία τεχνικών.');
                else
                    Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Η αίτηση δεν αποθηκεύθηκε.');
            };
            values.subStatus=1;
            values.taAnnStatus=-2;

            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
            values.protDatePause=button.up('toolbar').getCurrentTimestamp(1);
            values.protDateResign=button.up('toolbar').getCurrentTimestamp(1);
            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
            values.protYear=button.up('toolbar').getCurrentTimestamp(3);

            if (values.file.constructor == Array) values.file = values.file[0];
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
                            compTaAnnTaId: values.taAnnTaIdLocal[j]
                        });
                    }
                }
                else{
                    entriesArr.push({
                        visitDate:  values.visitDate,
                        visitTime:  values.visitTime,
                        visitDurationMinutes: values.visitDurationMinutes,
                        compTaAnnTaId: values.taAnnTaIdLocal
                    });
                }

                values.diaryEntries=entriesArr;
            }
            else{
                values.diaryEntries=entriesArr;
            }

            values.taEntries="";
            var taEntriesArr = [];
            values.taEntriesnum=Ext.getCmp('taAnnTaEntries').items.length-4;
            if (parseInt(values.taEntriesnum)>0){
                if (parseInt(values.taEntriesnum)>1){
                    for (var j = 0; j < (parseInt(values.taEntriesnum)); j++) {
                        var taSpeciality=Ext.util.JSON.encode(values.taSpeciality[j]);
                        taSpeciality=taSpeciality.substring(1, taSpeciality.length-1);

                        if (values.cooperationType[j] == "")
                            values.cooperationType[j] = "0";

                        if (values.taFullname[j] == "")
                            values.taFullname[j] = " ";

                        taEntriesArr.push({
                            cooperationType:  values.cooperationType[j],
                            technicianRegrequestId:  values.technicianRegrequestId[j],
                            technicianRegrequestUserId: values.technicianRegrequestUserId[j],
                            taFullname:  values.taFullname[j],
                            taAfm:  values.taAfm[j],
                            taSpeciality: taSpeciality,
                            taSpecialityOther: values.taSpecialityOther[j],
                            taAnnTaStatus: -2
                        });
                    }
                }
                else{
                    var taSpeciality=Ext.util.JSON.encode(values.taSpeciality);
                    taSpeciality=taSpeciality.substring(1, taSpeciality.length-1);

                    if (values.cooperationType == "")
                        values.cooperationType = "0";

                    if (values.taFullname == "")
                        values.taFullname = " ";

                    taEntriesArr.push({
                        cooperationType:  values.cooperationType,
                        technicianRegrequestId:  values.technicianRegrequestId,
                        technicianRegrequestUserId: values.technicianRegrequestUserId,
                        taFullname:  values.taFullname,
                        taAfm:  values.taAfm,
                        taSpeciality: taSpeciality,
                        taSpecialityOther: values.taSpecialityOther,
                        taAnnTaStatus: -2
                    });
                }

                values.taEntries=taEntriesArr;
            }
            else{
                values.taEntries=taEntriesArr;
            }


            delete values.visitDate;
            delete values.visitTime;
            delete values.visitDurationMinutes;
            delete values.taAnnTaIdLocal;

            delete values.cooperationType;
            delete values.technicianRegrequestId;
            delete values.technicianRegrequestUserId;
            delete values.taFullname;
            delete values.taAfm;
            delete values.taSpeciality;
            delete values.taSpecialityOther;
            delete values.exypp;

            for (var k = 0; k < values.diaryEntriesnum; k++)
            {
                if (values.diaryEntries[k].visitDate == "")
                    values.diaryEntries[k].visitDate = "1970-02-01T00:00:00.000+0000";
            }


            if(values.file!==""){
                form.down('form').submit({
                    url: fileurl,
                    waitMsg: 'Καταχώρηση αρχείου...',
                    success: function(form, action) {
                        values.attachedDocId=parseInt(action.result.fileId);
                        Ext.Ajax.request({
                            url: "/compTaAnn/" + fid,
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
                if (values.cooperationTypeBasic == "" || values.cooperationTypeBasic == null)
                    values.cooperationTypeBasic = "0";

                    Ext.Ajax.request({
                        url: "/compTaAnn/" + fid,
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

    onSubmit_TECHNICIAN_ANN: function(button, e, eOpts) {

        if(Ext.getCmp('companyTechnicianAnn_Branches').getSelectionModel().getSelection().length>0){

            var form = button.up('toolbar').up('window').down('form'),
                formWindow = button.up('toolbar').up('window');

            form.getForm().findField('compCategANum').enable();
            form.getForm().findField('compCategBNum').enable();
            form.getForm().findField('compCategCNum').enable();
            
            var values = form.getValues();

            form.getForm().findField('compCategANum').disable();
            form.getForm().findField('compCategBNum').disable();
            form.getForm().findField('compCategCNum').disable();

            var duplicates = function (arr) {
                var duplicates=false;
                for (var j=0;j<arr.length;j++)
                    for (var k=j+1;k<arr.length;k++)
                        if (k!=j && arr[k] == arr[j])
                            duplicates=true;
                return duplicates;
            };

            form.getForm().clearInvalid();

            var successCall = function(options, success, response) {
                if(response.responseText!=="0" ){
                    Ext.Msg.alert('Προσοχή!', 'Υπάρχει αναγγελία σε αναμονή ή <br>προηγούμενη ενεργή ή που έχει τερματιστεί.'+
                        ' <br>Επιλέξτε την από την λίστα και επιλέξτε διαγραφή στην περίπτωση αναμονής <br>αλλιώς επιλέξτε αντικατάσταση.');
                }
                else{
                    //var view = this.getView(),
                    /*var form = button.up('toolbar').up('window').down('form'),
                     formWindow = button.up('toolbar').up('window'),
                     values = form.getValues();*/

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

                    if (Ext.Date.format(new Date(values.dateStart.split("T")[0]), 'Y-m-d') < Ext.Date.format(new Date(Ext.Date.add(new Date(new Date().getFullYear(), new Date().getMonth() - 1, new Date().getDate()), Ext.Date.DAY,1)), 'Y-m-d'))
                    {
                        Ext.Msg.alert('Αποτυχία Υποβολής', 'Η ημερομηνία έναρξης πρέπει να είναι το πολύ 1 μήνα πριν από την τρέχουσα ημερομηνία');
                        return;
                    }
                    
                    if (parseInt(values.diaryEntriesnum) == 1)
                    {
                        if ((Ext.Date.format(new Date(values.visitDate), 'Y-m-d')) == Ext.Date.format(new Date(), 'Y-m-d') && (values.visitTime < Ext.Date.format(new Date(), 'H:i')))
                        {
                            Ext.getCmp('tadiaryEntries').items.get(5).items.get(1).markInvalid("Η ώρα της επίσκεψης πρέπει να είναι μεταγενέστερη της τρέχουσας.");
                            Ext.Msg.alert('Αποτυχία Υποβολής', 'Παρακαλώ διορθώστε τα λάθος πεδία');
                            return;
                        }
                    }
                    else
                    {
                        var err = false;
                        for (var j = 0; j < (parseInt(values.diaryEntriesnum)); j++)
                        {
                            if ((Ext.Date.format(new Date(values.visitDate[j]), 'Y-m-d')) == Ext.Date.format(new Date(), 'Y-m-d') && (values.visitTime[j] < Ext.Date.format(new Date(), 'H:i')))
                            {
                                Ext.getCmp('tadiaryEntries').items.get(5+j).items.get(1).markInvalid("Η ώρα της επίσκεψης πρέπει να είναι μεταγενέστερη της τρέχουσας.");
                                Ext.Msg.alert('Αποτυχία Υποβολής', 'Παρακαλώ διορθώστε τα λάθος πεδία');
                                err = true;
                            }
                        }
                        if (err) return;
                    }


                    if(form.isValid() && validTotalHours && validTotalMinutes){

                        formWindow.mask('Παρακαλώ Περιμένετε...');

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
                            if (resp.status===304){
                                if (values.attachedDocId!==-1){
                                    form.getForm().findField('attachedDocId').setValue(values.attachedDocId);
                                    form.getForm().findField('file').hide();
                                    var vfc = Ext.ComponentQuery.query('#Exist_File')[0];
                                    vfc.show();
                                }
                                msg=decodeURIComponent(resp.getResponseHeader("SEPE_ERROR")).replace(": SEPE_ERROR:","").replace(/\+/g,' ');

                                if(msg.indexOf(":ERR:")<0) {
                                    msgDates = msg.split(":");
                                    if (msgDates.length > 1) {
                                        errorDates = msgDates[msgDates.length - 1].trim().split(" ");
                                        Ext.suspendLayouts();
                                        for (var i = 0; i < errorDates.length; i++)
                                            Ext.ComponentQuery.query('timefield')[parseInt(errorDates[i])].markInvalid("Ο TA έχει άλλη επίσκεψη.");
                                        Ext.resumeLayouts(true);

                                        Ext.Msg.alert('Αποτυχία Αποθήκευσης',
                                            "Διορθώστε τις επισκέψεις που υπάρχει επικάλυψη με το ωράριο του TA.");
                                    }else {
                                        Ext.Msg.alert('Αποτυχία Υποβολής', "Η αίτηση δεν υποβλήθηκε.");
                                    }
                                }
                                else{
                                    Ext.Msg.alert('Αποτυχία Υποβολής', msg.substring(16));
                                }
                            }
                            else{
                                formWindow.destroy();
                                Ext.Msg.alert('Επιτυχής Υποβολή', 'Η αίτηση υποβλήθηκε με επιτυχία.');

                            }

                        };

                        // Failure
                        var failureCallback = function(resp, ops) {
                            formWindow.unmask();
                            if (resp.responseText.includes("SP_PTL.SP_PTL_COMP_TAANNDIARY_UNQ"))
                                Ext.Msg.alert('Αποτυχία Υποβολής', 'Υπάρχουν διπλές ή κενές επισκέψεις.');
                            else if (resp.responseText.includes("SP_PTL.SP_PTL_COMPTAANN_UNQ"))
                                Ext.Msg.alert('Αποτυχία Υποβολής', 'Λείπουν ή υπάρχουν δύο φορές στοιχεία τεχνικών.');
                            else
                                Ext.Msg.alert('Αποτυχία Υποβολής', 'Η αίτηση δεν υποβλήθηκε.');

                        };


                        values.reqStatus=5;
                        values.subStatus=2;
                        values.taAnnStatus=0;

                        values.protDate=button.up('toolbar').getCurrentTimestamp(1);
                        values.protDatePause=button.up('toolbar').getCurrentTimestamp(1);
                        values.protDateResign=button.up('toolbar').getCurrentTimestamp(1);
                        values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
                        values.protYear=button.up('toolbar').getCurrentTimestamp(3);

                        if (values.file.constructor == Array) values.file = values.file[0];

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
                                        compTaAnnTaId: values.taAnnTaIdLocal[j]
                                    });
                                }
                            }
                            else{
                                entriesArr.push({
                                    visitDate:  values.visitDate,
                                    visitTime:  values.visitTime,
                                    visitDurationMinutes: values.visitDurationMinutes,
                                    compTaAnnTaId: values.taAnnTaIdLocal
                                });
                            }

                            values.diaryEntries=entriesArr;
                        }
                        else{
                            values.diaryEntries=entriesArr;
                        }

                        values.taEntries="";
                        var taEntriesArr = [];
                        values.taEntriesnum=Ext.getCmp('taAnnTaEntries').items.length-4;
                        if (parseInt(values.taEntriesnum)>0){
                            if (parseInt(values.taEntriesnum)>1){
                                if (duplicates(values.technicianRegrequestUserId))
                                {
                                    Ext.Msg.alert('Αποτυχία Υποβολής', 'Επαναλαμβάνεται κάποιος τεχνικός.');
                                    formWindow.unmask();
                                    return;
                                }
                                for (var j = 0; j < (parseInt(values.taEntriesnum)); j++) {
                                    var taSpeciality=Ext.util.JSON.encode(values.taSpeciality[j]);
                                    taSpeciality=taSpeciality.substring(1, taSpeciality.length-1);
                                    taEntriesArr.push({
                                        cooperationType:  values.cooperationType[j],
                                        technicianRegrequestId:  values.technicianRegrequestId[j],
                                        technicianRegrequestUserId: values.technicianRegrequestUserId[j],
                                        taFullname:  values.taFullname[j],
                                        taAfm:  values.taAfm[j],
                                        taSpeciality: taSpeciality,
                                        taSpecialityOther: values.taSpecialityOther[j],
                                        taAnnTaStatus: 0
                                    });
                                }
                            }
                            else{
                                var taSpeciality=Ext.util.JSON.encode(values.taSpeciality);
                                taSpeciality=taSpeciality.substring(1, taSpeciality.length-1);
                                taEntriesArr.push({
                                    cooperationType:  values.cooperationType,
                                    technicianRegrequestId:  values.technicianRegrequestId,
                                    technicianRegrequestUserId: values.technicianRegrequestUserId,
                                    taFullname:  values.taFullname,
                                    taAfm:  values.taAfm,
                                    taSpeciality: taSpeciality,
                                    taSpecialityOther: values.taSpecialityOther,
                                    taAnnTaStatus: 0
                                });
                            }

                            values.taEntries=taEntriesArr;
                        }
                        else{
                            values.taEntries=taEntriesArr;
                        }


                        delete values.visitDate;
                        delete values.visitTime;
                        delete values.visitDurationMinutes;
                        delete values.taAnnTaIdLocal;

                        delete values.cooperationType;
                        delete values.technicianRegrequestId;
                        delete values.technicianRegrequestUserId;
                        delete values.taFullname;
                        delete values.taAfm;
                        delete values.taSpeciality;
                        delete values.taSpecialityOther;
                        delete values.exypp;


                        if(values.file !== ""){
                            form.down('form').submit({
                                url: fileurl,
                                waitMsg: 'Καταχώρηση αρχείου...',
                                success: function(form, action) {
                                    values.attachedDocId=parseInt(action.result.fileId);
                                    Ext.Ajax.request({
                                        url: "/compTaAnn/" + fid,
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
                                url: "/compTaAnn/" + fid,
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
                            Ext.Msg.alert('Αποτυχία Υποβολής', 'Δηλώστε αριθμό υπαλλήλων σε τουλάχιστον μία κατηγορία');
                        else if(validTotalMinutes===false)
                            Ext.Msg.alert('Αποτυχία Υποβολής', 'Τα συνολικά λεπτά επισκέψεων που δηλώσατε δεν είναι ανάλογα με τις ώρες που απαιτούνται');
                        else
                            Ext.Msg.alert('Αποτυχία Υποβολής', 'Παρακαλώ διορθώστε τα λάθος πεδία');
                    }
                }

            };
            Ext.Ajax.request({
                url: "/compTaAnn/search/countTaAnn2",
                params: {
                    ptlBranchId: Ext.getCmp('companyTechnicianAnn_Branches').getSelectionModel().getSelection()[0].get('ptlBranchId'),
                    startDate: form.getForm().findField('dateStart').rawValue,
                    compTaAnnPrevId: form.getForm().findField('compTaAnnPrevId').getValue()
                },
                method: "GET",
                callback: successCall
            });

        }
    },

    onPauseProgram_TECHNICIAN_ANN: function(button, e, eOpts) {
        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        var fields=Ext.getCmp('tadiaryEntries').items;
        //fields.getAt(0).show();
/*        for(var i=5; i<fields.length; i++){
            //var tomorrow = new Date(new Date().getTime() + 24 * 60 * 60 * 1000);
            var today = new Date();
            if(Ext.Date.format(fields.getAt(i).items.get(0).getValue(), 'Y-m-d')>=Ext.Date.format(today, 'Y-m-d')){
                if (Ext.Date.format(fields.getAt(i).items.get(0).getValue(), 'Y-m-d') == Ext.Date.format(today, 'Y-m-d') && fields.getAt(i).items.get(1).rawValue < Ext.Date.format(new Date(), 'H:i'))
                {
                    continue;
                }
                fields.getAt(i).items.get(0).setReadOnly(false);
                fields.getAt(i).items.get(0).minValue= (form.getForm().findField('dateStart').getValue() > today) ? form.getForm().findField('dateStart').getValue() : today;
                fields.getAt(i).items.get(0).maxValue=form.getForm().findField('dateEnd').getValue();
                fields.getAt(i).items.get(1).setReadOnly(false);
                fields.getAt(i).items.get(3).setReadOnly(false);
                fields.getAt(i).items.get(4).setReadOnly(false);
                fields.getAt(i).items.get(5).setReadOnly(false);
            }
        }*/


        Ext.Msg.alert('Παύση Προγράμματος', 'Συμπληρώστε το πεδίο \'Αιτιολογία παύσης\' και προχωρείστε στην ολοκλήρωση της διαδικασίας πατώντας στο κουμπί \'Ολοκλήρωση Πάυσης\'');

        //form.getForm().findField('pauseExplanation').show();
        form.getForm().findField('pauseExplanation').setReadOnly(false);
        form.getForm().findField('pauseExplanation').allowBlank = false;
        form.getForm().findField('pauseExplanation').allowOnlyWhitespace = false;
        Ext.getCmp('taAnnPauseData').show();
        Ext.getCmp('pauseForm').items.get(0).show();
        form.getForm().findField('protNoviewPause').setDisabled(true);
        form.getForm().findField('protDateviewPause').setDisabled(true);
        button.up('toolbar').getComponent('pausebutton').hide();
        button.up('toolbar').getComponent('changeProgrambutton').hide();
        button.up('toolbar').getComponent('changebutton').hide();
        button.up('toolbar').getComponent('pauseSaveButton').show();
    },
    onPauseProgramSave_TECHNICIAN_ANN: function(button, e, eOpts) {
        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window');

        form.getForm().findField('compCategANum').enable();
        form.getForm().findField('compCategBNum').enable();
        form.getForm().findField('compCategCNum').enable();

        var values = form.getValues();

        form.getForm().findField('compCategANum').disable();
        form.getForm().findField('compCategBNum').disable();
        form.getForm().findField('compCategCNum').disable();

        if (!form.getForm().findField('pauseExplanation').isValid())
        {
            Ext.Msg.alert('Αποτυχία Παύσης', 'Παρακαλώ συμπληρώστε την αιτιολογία της πάυσης');
            return;
        }

        if(Date.parse(new Date())<Date.parse(form.getForm().findField('dateEnd').value) && form.getForm().findField("a_new_form").getValue()==="false"){
            var nurl = values.url;
            var res = nurl.split("/");
            var fid = res[res.length-1];
            var successCallback = function(resp, ops) {

                formWindow.unmask();
                // Close window
                formWindow.destroy();
                Ext.Msg.alert('Επιτυχής Παύση', 'Η παύση της αναγγελίας ολοκληρώθηκε επιτυχώς');



            };

            // Failure
            var failureCallback = function(resp, ops) {
                formWindow.unmask();
                if (resp.responseText.includes("SP_PTL.SP_PTL_COMP_TAANNDIARY_UNQ"))
                    Ext.Msg.alert('Αποτυχία Παύσης', 'Υπάρχουν διπλές ή κενές επισκέψεις.');
                else if (resp.responseText.includes("SP_PTL.SP_PTL_COMPTAANN_UNQ"))
                    Ext.Msg.alert('Αποτυχία Παύσης', 'Λείπουν ή υπάρχουν δύο φορές στοιχεία τεχνικών.');
                else
                    Ext.Msg.alert('Αποτυχία Παύσης', 'Η παύση της αναγγελίας δεν ολοκληρώθηκε επιτυχώς');

            };

            formWindow.mask('Παύση Αναγγελίας...', 'x-mask-loading');
            var entriesArr = [];
            if (parseInt(values.diaryEntriesnum)>0){
                if (parseInt(values.diaryEntriesnum)>1){
                    for (var j = 0; j < (parseInt(values.diaryEntriesnum)); j++) {
                        entriesArr.push({
                            visitDate:  values.visitDate[j],
                            visitTime:  values.visitTime[j],
                            visitDurationMinutes: values.visitDurationMinutes[j],
                            compTaAnnTaId: values.taAnnTaIdLocal[j]
                        });
                    }
                }
                else{
                    entriesArr.push({
                        visitDate:  values.visitDate,
                        visitTime:  values.visitTime,
                        visitDurationMinutes: values.visitDurationMinutes,
                        compTaAnnTaId: values.taAnnTaIdLocal
                    });
                }

            }

            var fileurl = '/setDocument';
            if(parseInt(values.attachedDocIdPause)!==-1 && Ext.getCmp('pauseForm').items.get(0)!==null)
                fileurl = fileurl + "?docId=" + values.attachedDocIdPause;


            var valuesSend = {};
            valuesSend.id=fid;
            valuesSend.companyId=values.companyId;
            valuesSend.diaryEntries=entriesArr;
            valuesSend.pauseExplanation=values.pauseExplanation;

            if(Ext.getCmp('pauseForm').items.get(0).getValue()!==""){
                Ext.getCmp('pauseForm').submit({
                    url: fileurl,
                    waitMsg: 'Καταχώρηση αρχείου...',
                    success: function(form, action) {
                        valuesSend.attachedDocIdPause=parseInt(action.result.fileId);
                        Ext.Ajax.request({
                            url: "/pauseTaAnnProgram/" ,
                            headers: { 'Content-Type': 'application/json' },
                            jsonData: Ext.util.JSON.encode(valuesSend),
                            method: "POST",
                            success: successCallback,
                            failure: failureCallback
                        });
                    },
                    failure: function(form, action) {
                        Ext.getCmp('pauseForm').items.get(0).focus();
                        Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                    }
                });
            }
            else
            {
                Ext.Ajax.request({
                    url: "/pauseTaAnnProgram/" ,
                    headers: { 'Content-Type': 'application/json' },
                    jsonData: Ext.util.JSON.encode(valuesSend),
                    method: "POST",
                    success: successCallback,
                    failure: failureCallback
                });
            }
        }
        else{
            Ext.Msg.alert('Αποτυχία', 'Η φόρμα δεν έπρεπε να φθάσει σε αυτή την κατάσταση');

        }
    },
    onChange_TECHNICIAN_ANN: function(button, e, eOpts) {
        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window');

        form.getForm().findField('compCategANum').enable();
        form.getForm().findField('compCategBNum').enable();
        form.getForm().findField('compCategCNum').enable();

        var values = form.getValues();

        form.getForm().findField('compCategANum').disable();
        form.getForm().findField('compCategBNum').disable();
        form.getForm().findField('compCategCNum').disable();

        if(Date.parse(new Date())<Date.parse(form.getForm().findField('dateEnd').value)){

            var emp_comp = Ext.create('widget.companytechnicianannreplaceinfoform', {});

            var nurl = values.url;
            var res = nurl.split("/");
            var fid = res[res.length-1];

            emp_comp.down('form').getForm().findField('dateStart').setValue(new Date());
            emp_comp.down('form').getForm().findField('dateEnd').setValue(form.getForm().findField('dateEnd').rawValue);
            emp_comp.down('form').getForm().findField('compPtlBranchId').setValue(values.compPtlBranchId);
            emp_comp.down('form').getForm().findField('compEbrBranchId').setValue(values.compEbrBranchId);
            emp_comp.down('form').getForm().findField('compTaAnnPrevId').setValue(fid);
            emp_comp.show();


            emp_comp.show();
            formWindow.destroy();
        }
        else{
            formWindow.destroy();
            var emp_comp = Ext.create('widget.companydoctoranndatesform', {});
            emp_comp.show();

        }
    },
    onChangeProgram_TECHNICIAN_ANN: function(button, e, eOpts) {
        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        formWindow.mask("Παρακαλώ Περιμένετε...");
        Ext.suspendLayouts();

        var fields=Ext.getCmp('tadiaryEntries').items;
        fields.getAt(0).show();
        fields.getAt(2).show();
        for(var i=5; i<fields.length; i++){
            //var tomorrow = new Date(new Date().getTime() + 24 * 60 * 60 * 1000);
            var today = new Date();
            if(Ext.Date.format(fields.getAt(i).items.get(0).getValue(), 'Y-m-d') >= Ext.Date.format(today, 'Y-m-d')){
                if (Ext.Date.format(fields.getAt(i).items.get(0).getValue(), 'Y-m-d') == Ext.Date.format(today, 'Y-m-d') && fields.getAt(i).items.get(1).rawValue < Ext.Date.format(new Date(), 'H:i'))
                {
                    continue;
                }
                fields.getAt(i).items.get(0).setReadOnly(false);
                fields.getAt(i).items.get(0).minValue= (form.getForm().findField('dateStart').getValue() > today) ? form.getForm().findField('dateStart').getValue() : today;
                fields.getAt(i).items.get(0).maxValue=form.getForm().findField('dateEnd').getValue();
                fields.getAt(i).items.get(1).setReadOnly(false);
                fields.getAt(i).items.get(3).setReadOnly(false);
                fields.getAt(i).items.get(4).setReadOnly(false);
                fields.getAt(i).items.get(5).setReadOnly(false);
                fields.getAt(i).items.get(6).show();
            }
        }

        Ext.resumeLayouts(true);
        formWindow.unmask();


        Ext.Msg.alert('Ενεργοποίηση Αλλαγών Προγράμματος', 'Πλοηγηθείτε και κάντε αλλαγές στο πρόγραμμα. Στη συνέχεια πατήστε Αποθήκευση Αλλαγών. \n Προσοχή μπορείτε να αλλάξτε τις επισκέψεις που δεν έχει περάσει η ημ/νια τους.');

        button.up('toolbar').getComponent('changeProgrambutton').hide();
        button.up('toolbar').getComponent('changeProgramSavebutton').show();
    },

    onChangeProgramSave_TECHNICIAN_ANN: function(button, e, eOpts) {
        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window');

        form.getForm().findField('compCategANum').enable();
        form.getForm().findField('compCategBNum').enable();
        form.getForm().findField('compCategCNum').enable();

        var values = form.getValues();

        form.getForm().findField('compCategANum').disable();
        form.getForm().findField('compCategBNum').disable();
        form.getForm().findField('compCategCNum').disable();

        form.getForm().findField('exyppBasic').allowNull=true; //toValidateForm correct
        form.getForm().findField('file').allowBlank=true;
        values.diaryEntriesnum=Ext.getCmp('tadiaryEntries').items.length-5;
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

        var dfields=Ext.getCmp('tadiaryEntries').items;
        
        if (parseInt(values.diaryEntriesnum) == 1)
        {
            if ((Ext.Date.format(new Date(values.visitDate), 'Y-m-d')) == Ext.Date.format(new Date(), 'Y-m-d') && (values.visitTime < Ext.Date.format(new Date(), 'H:i')) && !dfields.getAt(5).items.get(0).readOnly)
            {
                Ext.getCmp('tadiaryEntries').items.get(5).items.get(1).markInvalid("Η ώρα της επίσκεψης πρέπει να είναι μεταγενέστερη της τρέχουσας.");
                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Παρακαλώ διορθώστε τα λάθος πεδία');
                return;
            }
        }
        else
        {
            var err = false;
            for (var j = 0; j < (parseInt(values.diaryEntriesnum)); j++)
            {
                if ((Ext.Date.format(new Date(values.visitDate[j]), 'Y-m-d')) == Ext.Date.format(new Date(), 'Y-m-d') && (values.visitTime[j] < Ext.Date.format(new Date(), 'H:i')) && !dfields.getAt(5+j).items.get(0).readOnly)
                {
                    Ext.getCmp('tadiaryEntries').items.get(5+j).items.get(1).markInvalid("Η ώρα της επίσκεψης πρέπει να είναι μεταγενέστερη της τρέχουσας.");
                    Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Παρακαλώ διορθώστε τα λάθος πεδία');
                    err = true;
                }
            }
            if (err) return;
        }

        for(var j=5; j<dfields.length; j++){
            var tomorrow = new Date(new Date().getTime() + 24 * 60 * 60 * 1000);
            var today = new Date();
            if(Ext.Date.format(dfields.getAt(j).items.get(0).getValue(), 'Y-m-d') < Ext.Date.format(today, 'Y-m-d') && !dfields.getAt(j).items.get(6).isVisible()){
                dfields.getAt(j).disable();
            }
        }


        if(form.isValid() &&  validTotalMinutes){
            var nurl = values.url;
            var res = nurl.split("/");
            var fid = res[res.length-1];

            formWindow.mask('Παρακαλώ Περιμένετε...');

            for(var j=5; j<dfields.length; j++){
                dfields.getAt(j).enable();
            }

            var successCallback = function(resp, ops) {
                formWindow.unmask();
                if (resp.status===304){
                    msg=decodeURIComponent(resp.getResponseHeader("SEPE_ERROR")).replace(": SEPE_ERROR:","").replace(/\+/g,' ');

                    msgDates=msg.split(":");
                    if (msgDates.length>1){
                        errorDates=msgDates[msgDates.length-1].trim().split(" ");
                        Ext.suspendLayouts();
                        for (var i = 0; i < errorDates.length; i++)
                            Ext.ComponentQuery.query('timefield')[parseInt(errorDates[i])].markInvalid("Ο TA έχει άλλη επίσκεψη.");
                        Ext.resumeLayouts(true);

                        Ext.Msg.alert('Αποτυχία Καταχώρησης',
                            "Διορθώστε τις επισκέψεις που υπάρχει επικάλυψη με το ωράριο του ΤΑ.");
                    }
                    else{
                        Ext.Msg.alert('Αποτυχία Καταχώρησης', msg);
                    }
                }
                else{
                    if (Ext.JSON.decode(resp.responseText).success) {
                        formWindow.destroy();
                        Ext.Msg.alert('Επιτυχής Καταχώρηση', 'Οι αλλαγές καταχωρήθηκαν με επιτυχία.');

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
                formWindow.unmask();
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
                            compTaAnnTaId: values.taAnnTaIdLocal[j]
                        });
                    }
                }
                else{
                    entriesArr.push({
                        visitDate:  values.visitDate,
                        visitTime:  values.visitTime,
                        visitDurationMinutes: values.visitDurationMinutes,
                        compTaAnnTaId: values.taAnnTaIdLocal
                    });
                }

            }


            var valuesSend = {};
            valuesSend.id=fid;
            valuesSend.companyId=values.companyId;
            valuesSend.diaryEntries=entriesArr;
            valuesSend.dateStart=values.dateStart;
            valuesSend.dateEnd=values.dateEnd;
            Ext.Ajax.request({
                url: "/updateTaAnnProgram/" ,
                headers: { 'Content-Type': 'application/json' },
                jsonData: Ext.util.JSON.encode(valuesSend),
                method: "POST",
                success: successCallback,
                failure: failureCallback
            });
        }

        else{

            for(var j=5; j<dfields.length; j++){
                dfields.getAt(j).enable();
            }

            if(validTotalMinutes===false)
                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Τα συνολικά λεπτά επισκέψεων που δηλώσατε δεν είναι ανάλογα με τις ώρες που απαιτούνται');
            else
                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Παρακαλώ διορθώστε τα λάθος πεδία');
        }

    }

});
