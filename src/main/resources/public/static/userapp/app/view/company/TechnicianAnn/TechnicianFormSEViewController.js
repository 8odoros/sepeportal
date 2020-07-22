Ext.define('MyApp.view.company.TechnicianAnn.TechnicianFormSEViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companytechniciananntechnicianformse',

    onDelete_TECHNICIAN_ANN_SE: function(button, e, eOpts) {

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
                        formWindow.destroy();
                        Ext.Msg.alert('Επιτυχής Διαγραφή', 'Η αίτηση διαγράφηκε');
                    };

                    // Failure
                    var failureCallback = function(resp, ops) {
                        Ext.Msg.alert('Αποτυχία Διαγραφής', 'Η αίτηση δεν διαγράφηκε');

                    };

                    Ext.Ajax.request({
                        url: "/compTaAnnSe/" + fid,
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

    onSave_TECHNICIAN_ANN_SE: function(button, e, eOpts) {
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
                    Ext.Msg.alert('Αποτυχία Αποθήκευσης', msg.substring(16));
                }
                else{
                    formWindow.destroy();
                    Ext.Msg.alert('Επιτυχής Αποθήκευσης', 'Η αίτηση αποθηκεύτηκε με επιτυχία.');
                }
            };

            // Failure
            var failureCallback = function(resp, ops) {
                formWindow.unmask();
                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Η αίτηση δεν αποθηκεύθηκε.');
            };
            values.subStatus=1;

            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
            values.protYear=button.up('toolbar').getCurrentTimestamp(3);
            
            if (values.dateStart == "")
                values.dateStart = null;
            if (values.dateEnd == "")
                values.dateEnd = null;

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
                            visitDurationMinutes: values.visitDurationMinutes[j]
                        });
                    }
                }
                else{
                    entriesArr.push({
                        visitDate:  values.visitDate,
                        visitTime:  values.visitTime,
                        visitDurationMinutes: values.visitDurationMinutes
                    });
                }

                values.diaryEntries=entriesArr;
            }
            else{
                values.diaryEntries=entriesArr;
            }
            
            /*delete values.visitDate;
            delete values.visitTime;
            delete values.visitDurationMinutes;

            delete values.cooperationType;
            delete values.taFirstname;
            delete values.taLastname;
            delete values.taFathername;
            delete values.taPhone;
            delete values.taEmail;
            delete values.taAfm;*/

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
                            url: "/compTaAnnSe/" + fid,
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
                    url: "/compTaAnnSe/" + fid,
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

    onSubmit_TECHNICIAN_ANN_SE: function(button, e, eOpts) {
        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window');

        form.getForm().findField('compCategBNum').enable();
        form.getForm().findField('compCategCNum').enable();

        var values = form.getValues();

        form.getForm().findField('compCategBNum').disable();
        form.getForm().findField('compCategCNum').disable();

        if(parseInt(values.attachedDocId)!==-1 && values.deletedfile==="true" && values.file===""){
            form.getForm().findField('file').allowBlank=false;
            form.getForm().findField('file').allowOnlyWhitespace=false;
        }
        else{
            if(parseInt(values.attachedDocId)===-1){
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

            try
            {
                if (Ext.Date.format(new Date(values.dateStart.split("T")[0]), 'Y-m-d') < Ext.Date.format(new Date(Ext.Date.add(new Date(new Date().getFullYear(), new Date().getMonth() - 1, new Date().getDate()), Ext.Date.DAY,1)), 'Y-m-d'))
                {
                    Ext.Msg.alert('Αποτυχία Υποβολής', 'Η ημερομηνία έναρξης πρέπει να είναι το πολύ 1 μήνα πριν από την τρέχουσα ημερομηνία');
                    return;
                }
            }
            catch (err){}

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
                    Ext.Msg.alert('Αποτυχία Υποβολής', msg.substring(16));
                }
                else{
                    formWindow.destroy();
                    Ext.Msg.alert('Επιτυχής Υποβολή', 'Η αίτηση υποβλήθηκε με επιτυχία.');
                }
            };

            // Failure
            var failureCallback = function(resp, ops) {
                formWindow.unmask();
                Ext.Msg.alert('Αποτυχία Υποβολής', 'Η αίτηση δεν υποβλήθηκε.');
            };
            values.subStatus=2;

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
                            visitDurationMinutes: values.visitDurationMinutes[j]
                        });
                    }
                }
                else{
                    entriesArr.push({
                        visitDate:  values.visitDate,
                        visitTime:  values.visitTime,
                        visitDurationMinutes: values.visitDurationMinutes
                    });
                }

                values.diaryEntries=entriesArr;
            }
            else{
                values.diaryEntries=entriesArr;
            }

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
                            url: "/compTaAnnSe/" + fid,
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
                    url: "/compTaAnnSe/" + fid,
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
    },
});