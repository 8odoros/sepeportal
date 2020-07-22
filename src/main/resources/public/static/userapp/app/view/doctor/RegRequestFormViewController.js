/*
 * File: app/view/doctor/RegRequestFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.doctor.RegRequestFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.doctorregrequestform',

    onDelete_DOCTOR_REGREQUEST: function(button, e, eOpts) {

        var form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

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
                url: "/doctorRegrequest/" + fid,
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

    onSave_DOCTOR_REGREQUEST: function(button, e, eOpts) {
        var conffun = function(buttonText) {
            if (buttonText == "yes") {
                submitfun();
            }
            if (buttonText == "no") {
                return false;
            }
        };

        var msb = Ext.MessageBox.confirm('Υποβολή φόρμας', 'Είστε σίγουροι ότι θελετε να αποθηκευθεί η αίτηση; ', conffun);

        var canPass = false;
        if (this.field_validation(button.up('toolbar').up('window').down('form'))) canPass = true;

        var submitfun = function(){
            var form = button.up('toolbar').up('window').down('form'),
                formWindow = button.up('toolbar').up('window'),
                values = form.getValues();

            /*if(values.isMilitaryDoctor==="1"){
                Ext.getCmp('attachedDocIdMilitary').getForm().findField('file').allowBlank=false;
                Ext.getCmp('attachedDocIdMilitary').getForm().findField('file').allowOnlyWhitespace=false;
            }
            else{
                Ext.getCmp('attachedDocIdMilitary').getForm().findField('file').allowBlank=true;
                Ext.getCmp('attachedDocIdMilitary').getForm().findField('file').allowOnlyWhitespace=true;
            }

            if(Ext.getCmp('attachedDocIdDiploma').getForm().findField('file').getValue()===""){
                if(values.deletedfile1!=="true"){
                    Ext.getCmp('attachedDocIdDiploma').getForm().findField('file').allowBlank=true;
                    Ext.getCmp('attachedDocIdDiploma').getForm().findField('file').allowOnlyWhitespace=true;
                }
                else{
                    Ext.getCmp('attachedDocIdDiploma').getForm().findField('file').allowBlank=false;
                    Ext.getCmp('attachedDocIdDiploma').getForm().findField('file').allowOnlyWhitespace=false;
                }
            }
            if(Ext.getCmp('attachedDocIdMedassoc').getForm().findField('file').getValue()===""){
                if(values.deletedfile4!=="true"){
                    Ext.getCmp('attachedDocIdMedassoc').getForm().findField('file').allowBlank=true;
                    Ext.getCmp('attachedDocIdMedassoc').getForm().findField('file').allowOnlyWhitespace=true;
                }
                else{
                    Ext.getCmp('attachedDocIdMedassoc').getForm().findField('file').allowBlank=false;
                    Ext.getCmp('attachedDocIdMedassoc').getForm().findField('file').allowOnlyWhitespace=false;
                }
            }
            if(Ext.getCmp('attachedDocIdPedy').getForm().findField('file').getValue()===""){
                if(values.deletedfile3!=="true"){
                    Ext.getCmp('attachedDocIdPedy').getForm().findField('file').allowBlank=true;
                    Ext.getCmp('attachedDocIdPedy').getForm().findField('file').allowOnlyWhitespace=true;
                }
                else{
                    Ext.getCmp('attachedDocIdPedy').getForm().findField('file').allowBlank=false;
                    Ext.getCmp('attachedDocIdPedy').getForm().findField('file').allowOnlyWhitespace=false;
                }
            }
            if (values.attachedDocIdPedyNo!=="-1" && values.belongsToPedy==="1")
            values.attachedDocIdPedyNo="-1";
            else if (values.attachedDocIdPedyYes!=="-1" && values.belongsToPedy==="0")
            values.attachedDocIdPedyYes="-1";

            if(values.attachedDocIdEmplTraining!=="-1" && values.deletedfile2==="true" && Ext.getCmp("attachedDocIdEmplTraining").getForm().findField("file").getValue()==="")
            values.attachedDocIdEmplTraining="-1";*/

            if(values.attachedDocId!=="-1" && values.deletedfile==="true" && Ext.getCmp("docfile1").getForm().findField("file").getValue()==="")
            values.attachedDocId="-1";

            /*if(values.attachedDocIdDiploma!=="-1" && values.deletedfile1==="true" && Ext.getCmp("attachedDocIdDiploma").getForm().findField("file").getValue()==="")
            values.attachedDocIdDiploma="-1";

            if(values.attachedDocIdMedassoc!=="-1" && values.deletedfile4==="true" && Ext.getCmp("attachedDocIdMedassoc").getForm().findField("file").getValue()==="")
            values.attachedDocIdMedassoc="";

            if(values.isMilitaryDoctor==="0" || (values.attachedDocIdMilitary!=="-1" && values.deletedfile5==="true" && Ext.getCmp("attachedDocIdMilitary").getForm().findField("file").getValue()===""))
            values.attachedDocIdMilitary="-1";

            if(form.getForm().findField("a_new_form").getValue()==="true"){
                Ext.getCmp('attachedDocIdMedassoc').getForm().findField('file').allowBlank=false;
                Ext.getCmp('attachedDocIdMedassoc').getForm().findField('file').allowOnlyWhitespace=false;
                Ext.getCmp('attachedDocIdPedy').getForm().findField('file').allowBlank=false;
                Ext.getCmp('attachedDocIdPedy').getForm().findField('file').allowOnlyWhitespace=false;
                Ext.getCmp('attachedDocIdDiploma').getForm().findField('file').allowBlank=false;
                Ext.getCmp('attachedDocIdDiploma').getForm().findField('file').allowOnlyWhitespace=false;
            }*/

            if (canPass) {
                formWindow.mask('Παρακαλώ Περιμένετε...', 'x-mask-loading');
                var fid, rest_method;

                if (values.url===""){
                    fid = "";
                    rest_method = "POST";
                }
                else
                {
                    var nurl = values.url;
                    var res = nurl.split("/");
                    fid = res[res.length-1];
                    fid = fid + "/";
                    rest_method = "PUT";
                }

                // Success
                var successCallback = function(resp, ops) {

                    formWindow.unmask();
                    Ext.Msg.alert('Επιτυχής Αποθήκευση', 'Η αίτηση σας αποθηκεύθηκε με επιτυχία.');
                    formWindow.destroy();

                };

                // Failure
                var failureCallback = function(resp, ops) {

                    formWindow.unmask();
                    Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Η αίτηση σας δεν έγινε δεκτή από το σύστημα');

                };

                /*if (values.ieDocDate!=="")
                values.ieDocDate=button.up('toolbar').dateToTimestamp(values.ieDocDate);
                else
                values.ieDocDate="0000-00-00T00:00:00.000+0000";*/
                values.protNo="";
                values.protDate=button.up('toolbar').getCurrentTimestamp(1);
                values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
                values.protYear=button.up('toolbar').getCurrentTimestamp(3);

                values.spDifferentCounty="";
                var checkedboxes = form.getForm().findField('doctorCountiess').getChecked().length;
                if(checkedboxes>0){
                    for (var j = 0; j < checkedboxes; j++) {
                        if (j==checkedboxes-1) {
                            values.spDifferentCounty += form.getForm().findField('doctorCountiess').getChecked()[j].inputValue;
                        } else {
                            values.spDifferentCounty += form.getForm().findField('doctorCountiess').getChecked()[j].inputValue + ",";
                        }
                    }
                } else {
                    values.spDifferentCounty="";
                }

                values.speciality="";
                var checkedboxes = form.getForm().findField('doctorSpecCountiess').getChecked().length;
                if(checkedboxes>0){
                    for (var j = 0; j < checkedboxes; j++) {
                        if (j==checkedboxes-1) {
                            values.speciality += form.getForm().findField('doctorSpecCountiess').getChecked()[j].inputValue;
                        } else {
                            values.speciality += form.getForm().findField('doctorSpecCountiess').getChecked()[j].inputValue + ",";
                        }
                    }
                } else {
                    values.speciality="";
                }

                /*if(values.spDifferentCounty!="[]"){
                    values.spDifferentCounty=Ext.util.JSON.encode(values.spDifferentCounty);
                    values.spDifferentCounty=values.spDifferentCounty.substring(1, values.spDifferentCounty.length-1);
                    values.spDifferentCounty=values.spDifferentCounty.replace(/["']/g, "");
                }

                if(values.spDifferentCounty==="ul"){
                    values.spDifferentCounty="";
                }*/

                values.subStatus=1;

                var fileurl = 'setDocument';
                if (values.attachedDocId !=="-1")
                fileurl = '/setDocument' + "?docId=" + values.attachedDocId;
                else
                fileurl = '/setDocument';
                if(values.file!=="") {
                    Ext.getCmp("docfile1").submit({
                        url: fileurl,
                        waitMsg: 'Καταχώρηση αρχείου...',
                        success: function (form, action) {
                            values.attachedDocId = parseInt(action.result.fileId);
                            //formWindow.mask('Αποθήκευση Αίτησης...', 'x-mask-loading');
                            Ext.Ajax.request({
                                url: "/doctorRegrequest/" + fid,
                                headers: {'Content-Type': 'application/json'},
                                jsonData: Ext.util.JSON.encode(values),
                                method: rest_method,
                                success: successCallback,
                                failure: failureCallback
                            });
                        },
                        failure: function (form, action) {
                            form.findField("file").focus();
                            Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                        }
                    });
                }
                else
                {
                    //formWindow.mask('Αποθήκευση Αίτησης...', 'x-mask-loading');
                    Ext.Ajax.request({
                        url: "/doctorRegrequest/" + fid,
                        headers: {'Content-Type': 'application/json'},
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
        };

    },

    onSubmit_DOCTOR_REGREQUEST: function(button, e, eOpts) {
        var conffun = function(buttonText) {
            if (buttonText == "yes") {
                submitfun();
            }
            if (buttonText == "no") {
                return false;
            }
        };

        var msb = Ext.MessageBox.confirm('Υποβολή φόρμας', 'Είστε σίγουροι ότι θελετε να υποβληθεί η αίτηση; ', conffun);

        var submitfun = function(){
            var form = button.up('toolbar').up('window').down('form'),
                formWindow = button.up('toolbar').up('window'),
                values = form.getValues();

            /*if(values.isMilitaryDoctor==="1"){
                Ext.getCmp('attachedDocIdMilitary').getForm().findField('file').allowBlank=false;
                Ext.getCmp('attachedDocIdMilitary').getForm().findField('file').allowOnlyWhitespace=false;
            }
            else{
                Ext.getCmp('attachedDocIdMilitary').getForm().findField('file').allowBlank=true;
                Ext.getCmp('attachedDocIdMilitary').getForm().findField('file').allowOnlyWhitespace=true;
            }

            if(Ext.getCmp('attachedDocIdDiploma').getForm().findField('file').getValue()===""){
                if(values.deletedfile1!=="true"){
                    Ext.getCmp('attachedDocIdDiploma').getForm().findField('file').allowBlank=true;
                    Ext.getCmp('attachedDocIdDiploma').getForm().findField('file').allowOnlyWhitespace=true;
                }
                else{
                    Ext.getCmp('attachedDocIdDiploma').getForm().findField('file').allowBlank=false;
                    Ext.getCmp('attachedDocIdDiploma').getForm().findField('file').allowOnlyWhitespace=false;
                }
            }
            if(Ext.getCmp('attachedDocIdMedassoc').getForm().findField('file').getValue()===""){
                if(values.deletedfile4!=="true"){
                    Ext.getCmp('attachedDocIdMedassoc').getForm().findField('file').allowBlank=true;
                    Ext.getCmp('attachedDocIdMedassoc').getForm().findField('file').allowOnlyWhitespace=true;
                }
                else{
                    Ext.getCmp('attachedDocIdMedassoc').getForm().findField('file').allowBlank=false;
                    Ext.getCmp('attachedDocIdMedassoc').getForm().findField('file').allowOnlyWhitespace=false;
                }
            }
            if(Ext.getCmp('attachedDocIdPedy').getForm().findField('file').getValue()===""){
                if(values.deletedfile3!=="true"){
                    Ext.getCmp('attachedDocIdPedy').getForm().findField('file').allowBlank=true;
                    Ext.getCmp('attachedDocIdPedy').getForm().findField('file').allowOnlyWhitespace=true;
                }
                else{
                    Ext.getCmp('attachedDocIdPedy').getForm().findField('file').allowBlank=false;
                    Ext.getCmp('attachedDocIdPedy').getForm().findField('file').allowOnlyWhitespace=false;
                }

            }

            if(form.getForm().findField("a_new_form").getValue()==="true"){
                Ext.getCmp('attachedDocIdMedassoc').getForm().findField('file').allowBlank=false;
                Ext.getCmp('attachedDocIdMedassoc').getForm().findField('file').allowOnlyWhitespace=false;
                Ext.getCmp('attachedDocIdPedy').getForm().findField('file').allowBlank=false;
                Ext.getCmp('attachedDocIdPedy').getForm().findField('file').allowOnlyWhitespace=false;
                Ext.getCmp('attachedDocIdDiploma').getForm().findField('file').allowBlank=false;
                Ext.getCmp('attachedDocIdDiploma').getForm().findField('file').allowOnlyWhitespace=false;
            }
            if (values.attachedDocIdPedyNo!=="-1" && values.belongsToPedy==="1")
            values.attachedDocIdPedyNo="-1";
            else if (values.attachedDocIdPedyYes!=="-1" && values.belongsToPedy==="0")
            values.attachedDocIdPedyYes="-1";

            if(values.attachedDocIdEmplTraining!=="-1" && values.deletedfile2==="true" && Ext.getCmp("attachedDocIdEmplTraining").getForm().findField("file").getValue()==="")
            values.attachedDocIdEmplTraining="-1";*/

            if(values.attachedDocId!=="-1" && values.deletedfile==="true" && Ext.getCmp("docfile1").getForm().findField("file").getValue()==="")
            values.attachedDocId="-1";

            /*if(values.attachedDocIdDiploma!=="-1" && values.deletedfile1==="true" && Ext.getCmp("attachedDocIdDiploma").getForm().findField("file").getValue()==="")
            values.attachedDocIdDiploma="-1";

            if(values.attachedDocIdMedassoc!=="-1" && values.deletedfile4==="true" && Ext.getCmp("attachedDocIdMedassoc").getForm().findField("file").getValue()==="")
            values.attachedDocIdMedassoc="";

            if(values.isMilitaryDoctor==="0" || (values.attachedDocIdMilitary!=="-1" && values.deletedfile5==="true" && Ext.getCmp("attachedDocIdMilitary").getForm().findField("file").getValue()===""))
            values.attachedDocIdMilitary="-1";*/

            if (values.phone == "" && values.mobile == "")
            {
                form.getForm().findField("phone").allowBlank=false;
                form.getForm().findField("phone").allowOnlyWhitespace=false;
                form.getForm().findField("mobile").allowBlank=false;
                form.getForm().findField("mobile").allowOnlyWhitespace=false;
                form.getForm().findField("mobile").invalidText = 'cannot be blank';
            }
            else
            {
                form.getForm().findField("phone").allowBlank=true;
                form.getForm().findField("phone").allowOnlyWhitespace=true;
                form.getForm().findField("mobile").allowBlank=true;
                form.getForm().findField("mobile").allowOnlyWhitespace=true;
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
                    fid = res[res.length-1];
                    fid = fid + "/";
                    rest_method = "PUT";
                }


                // Success
                var successCallback = function(resp, ops) {

                    Ext.Msg.alert('Επιτυχής Υποβολή', 'Η αίτηση σας υποβλήθηκε με επιτυχία.');
                    formWindow.destroy();

                };

                // Failure
                var failureCallback = function(resp, ops) {

                    Ext.Msg.alert('Αποτυχία Υποβολής', 'Η αίτηση σας δεν έγινε δεκτή από το σύστημα');

                };

                /*if (values.ieDocDate!=="")
                values.ieDocDate=button.up('toolbar').dateToTimestamp(values.ieDocDate);
                else
                values.ieDocDate="0000-00-00T00:00:00.000+0000";*/
                values.protNo="";
                values.protDate=button.up('toolbar').getCurrentTimestamp(1);
                values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
                values.protYear=button.up('toolbar').getCurrentTimestamp(3);

                /*if(values.spDifferentCounty!="[]"){
                    values.spDifferentCounty=Ext.util.JSON.encode(values.spDifferentCounty);
                    values.spDifferentCounty=values.spDifferentCounty.substring(1, values.spDifferentCounty.length-1);
                    values.spDifferentCounty=values.spDifferentCounty.replace(/["']/g, "");
                }

                if(values.spDifferentCounty==="ul"){
                    values.spDifferentCounty="";
                }*/
                values.reqStatus=5;
                values.subStatus=2;

                var fileurl = 'setDocument';

                if (Ext.getCmp("docfile1").getForm().findField("file").getValue() !== "") {
                    if (values.attachedDocId !=="-1")
                        fileurl = '/setDocument' + "?docId=" + values.attachedDocId;
                    else
                        fileurl = '/setDocument';
                    Ext.getCmp("docfile1").submit({
                        url: fileurl,
                        waitMsg: 'Καταχώρηση αρχείου...',
                        success: function(form, action) {
                            values.attachedDocId = parseInt(action.result.fileId);
                                delete values.file;
                                Ext.Ajax.request({
                                    url: "/doctorRegrequest/" + fid,
                                    headers: {
                                        'Content-Type': 'application/json'
                                    },
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

                /*var fileurl = 'setDocument';
                if (values.attachedDocIdDiploma !=="-1")
                fileurl = '/setDocument' + "?docId=" + values.attachedDocIdDiploma;
                else
                fileurl = '/setDocument';
                if (Ext.getCmp("attachedDocIdDiploma").getForm().findField("file").getValue() !== "") {
                    Ext.getCmp("attachedDocIdDiploma").submit({
                        url: fileurl,
                        waitMsg: 'Καταχώρηση αρχείου...',
                        success: function(form, action) {
                            values.attachedDocIdDiploma = parseInt(action.result.fileId);
                            if (values.attachedDocIdEmplTraining !=="-1")
                            fileurl = '/setDocument' + "?docId=" + values.attachedDocIdEmplTraining;
                            else
                            fileurl = '/setDocument';
                            if (Ext.getCmp("attachedDocIdEmplTraining").getForm().findField("file").getValue() !== "") {
                                Ext.getCmp("attachedDocIdEmplTraining").submit({
                                    url: fileurl,
                                    waitMsg: 'Καταχώρηση αρχείου...',
                                    success: function(form, action) {
                                        values.attachedDocIdEmplTraining = parseInt(action.result.fileId);
                                        if (values.attachedDocIdMedassoc !=="-1")
                                        fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMedassoc;
                                        else
                                        fileurl = '/setDocument';
                                        if (Ext.getCmp("attachedDocIdMedassoc").getForm().findField("file").getValue() !== "") {
                                            Ext.getCmp("attachedDocIdMedassoc").submit({
                                                url: fileurl,
                                                waitMsg: 'Καταχώρηση αρχείου...',
                                                success: function(form, action) {
                                                    values.attachedDocIdMedassoc = parseInt(action.result.fileId);
                                                    if (values.attachedDocIdPedyYes !=="-1")
                                                    fileurl = '/setDocument' + "?docId=" + values.attachedDocIdPedyYes;
                                                    else if (values.attachedDocIdPedyNo !=="-1")
                                                    fileurl = '/setDocument' + "?docId=" + values.attachedDocIdPedyNo;
                                                    else
                                                    fileurl = '/setDocument';
                                                    if (Ext.getCmp("attachedDocIdPedy").getForm().findField("file").getValue() !== "") {
                                                        Ext.getCmp("attachedDocIdPedy").submit({
                                                            url: fileurl,
                                                            waitMsg: 'Καταχώρηση αρχείου...',
                                                            success: function(form, action) {
                                                                if (values.belongsToPedy === "1")
                                                                values.attachedDocIdPedyYes = parseInt(action.result.fileId);
                                                                else
                                                                values.attachedDocIdPedyNo = parseInt(action.result.fileId);
                                                                if (Ext.getCmp("docfile1").getForm().findField("file").getValue() !== "") {
                                                                    if (values.attachedDocId !=="-1")
                                                                    fileurl = '/setDocument' + "?docId=" + values.attachedDocId;
                                                                    else
                                                                    fileurl = '/setDocument';
                                                                    Ext.getCmp("docfile1").submit({
                                                                        url: fileurl,
                                                                        waitMsg: 'Καταχώρηση αρχείου...',
                                                                        success: function(form, action) {
                                                                            values.attachedDocId = parseInt(action.result.fileId);
                                                                            if (values.isMilitaryDoctor === "1") {
                                                                                if (values.attachedDocIdMilitary !=="-1")
                                                                                fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                                                Ext.getCmp("attachedDocIdMilitary").submit({
                                                                                    url: fileurl,
                                                                                    waitMsg: 'Καταχώρηση αρχείου...',
                                                                                    success: function(form, action) {
                                                                                        values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                                                        delete values.file;
                                                                                        Ext.Ajax.request({
                                                                                            url: "/doctorRegrequest/" + fid,
                                                                                            headers: {
                                                                                                'Content-Type': 'application/json'
                                                                                            },
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

                                                                            } else {
                                                                                delete values.file;
                                                                                Ext.Ajax.request({
                                                                                    url: "/doctorRegrequest/" + fid,
                                                                                    headers: {
                                                                                        'Content-Type': 'application/json'
                                                                                    },
                                                                                    jsonData: Ext.util.JSON.encode(values),
                                                                                    method: rest_method,
                                                                                    success: successCallback,
                                                                                    failure: failureCallback
                                                                                });
                                                                            }
                                                                        },
                                                                        failure: function(form, action) {
                                                                            form.findField("file").focus();
                                                                            Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                                                        }
                                                                    });
                                                                } else {
                                                                    if (values.isMilitaryDoctor === "1") {
                                                                        if (values.attachedDocIdMilitary !=="-1")
                                                                        fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                                        Ext.getCmp("attachedDocIdMilitary").submit({
                                                                            url: fileurl,
                                                                            waitMsg: 'Καταχώρηση αρχείου...',
                                                                            success: function(form, action) {
                                                                                values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                                                delete values.file;
                                                                                Ext.Ajax.request({
                                                                                    url: "/doctorRegrequest/" + fid,
                                                                                    headers: {
                                                                                        'Content-Type': 'application/json'
                                                                                    },
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

                                                                    } else {
                                                                        delete values.file;
                                                                        Ext.Ajax.request({
                                                                            url: "/doctorRegrequest/" + fid,
                                                                            headers: {
                                                                                'Content-Type': 'application/json'
                                                                            },
                                                                            jsonData: Ext.util.JSON.encode(values),
                                                                            method: rest_method,
                                                                            success: successCallback,
                                                                            failure: failureCallback
                                                                        });
                                                                    }
                                                                }

                                                            },
                                                            failure: function(form, action) {
                                                                form.findField("file").focus();
                                                                Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                                            }
                                                        });
                                                    } else {
                                                        if (Ext.getCmp("docfile1").getForm().findField("file").getValue() !== "") {
                                                            if (values.attachedDocId !=="-1")
                                                            fileurl = '/setDocument' + "?docId=" + values.attachedDocId;
                                                            else
                                                            fileurl = '/setDocument';
                                                            Ext.getCmp("docfile1").submit({
                                                                url: fileurl,
                                                                waitMsg: 'Καταχώρηση αρχείου...',
                                                                success: function(form, action) {
                                                                    values.attachedDocId = parseInt(action.result.fileId);
                                                                    if (values.isMilitaryDoctor === "1") {
                                                                        if (values.attachedDocIdMilitary !=="-1")
                                                                        fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                                        Ext.getCmp("attachedDocIdMilitary").submit({
                                                                            url: fileurl,
                                                                            waitMsg: 'Καταχώρηση αρχείου...',
                                                                            success: function(form, action) {
                                                                                values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                                                delete values.file;
                                                                                Ext.Ajax.request({
                                                                                    url: "/doctorRegrequest/" + fid,
                                                                                    headers: {
                                                                                        'Content-Type': 'application/json'
                                                                                    },
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

                                                                    } else {
                                                                        delete values.file;
                                                                        Ext.Ajax.request({
                                                                            url: "/doctorRegrequest/" + fid,
                                                                            headers: {
                                                                                'Content-Type': 'application/json'
                                                                            },
                                                                            jsonData: Ext.util.JSON.encode(values),
                                                                            method: rest_method,
                                                                            success: successCallback,
                                                                            failure: failureCallback
                                                                        });
                                                                    }
                                                                },
                                                                failure: function(form, action) {
                                                                    form.findField("file").focus();
                                                                    Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                                                }
                                                            });
                                                        } else {
                                                            if (values.isMilitaryDoctor === "1") {
                                                                if (values.attachedDocIdMilitary !=="-1")
                                                                fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                                Ext.getCmp("attachedDocIdMilitary").submit({
                                                                    url: fileurl,
                                                                    waitMsg: 'Καταχώρηση αρχείου...',
                                                                    success: function(form, action) {
                                                                        values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                                        delete values.file;
                                                                        Ext.Ajax.request({
                                                                            url: "/doctorRegrequest/" + fid,
                                                                            headers: {
                                                                                'Content-Type': 'application/json'
                                                                            },
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

                                                            } else {
                                                                delete values.file;
                                                                Ext.Ajax.request({
                                                                    url: "/doctorRegrequest/" + fid,
                                                                    headers: {
                                                                        'Content-Type': 'application/json'
                                                                    },
                                                                    jsonData: Ext.util.JSON.encode(values),
                                                                    method: rest_method,
                                                                    success: successCallback,
                                                                    failure: failureCallback
                                                                });
                                                            }
                                                        }
                                                    }
                                                },
                                                failure: function(form, action) {
                                                    form.findField("file").focus();
                                                    Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                                }
                                            });
                                        } else {
                                            if (Ext.getCmp("attachedDocIdPedy").getForm().findField("file").getValue() !== "") {
                                                Ext.getCmp("attachedDocIdPedy").submit({
                                                    url: fileurl,
                                                    waitMsg: 'Καταχώρηση αρχείου...',
                                                    success: function(form, action) {
                                                        if (values.belongsToPedy === "1")
                                                        values.attachedDocIdPedyYes = parseInt(action.result.fileId);
                                                        else
                                                        values.attachedDocIdPedyNo = parseInt(action.result.fileId);
                                                        if (Ext.getCmp("docfile1").getForm().findField("file").getValue() !== "") {
                                                            if (values.attachedDocId !=="-1")
                                                            fileurl = '/setDocument' + "?docId=" + values.attachedDocId;
                                                            else
                                                            fileurl = '/setDocument';
                                                            Ext.getCmp("docfile1").submit({
                                                                url: fileurl,
                                                                waitMsg: 'Καταχώρηση αρχείου...',
                                                                success: function(form, action) {
                                                                    values.attachedDocId = parseInt(action.result.fileId);
                                                                    if (values.isMilitaryDoctor === "1") {
                                                                        if (values.attachedDocIdMilitary !=="-1")
                                                                        fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                                        Ext.getCmp("attachedDocIdMilitary").submit({
                                                                            url: fileurl,
                                                                            waitMsg: 'Καταχώρηση αρχείου...',
                                                                            success: function(form, action) {
                                                                                values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                                                delete values.file;
                                                                                Ext.Ajax.request({
                                                                                    url: "/doctorRegrequest/" + fid,
                                                                                    headers: {
                                                                                        'Content-Type': 'application/json'
                                                                                    },
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

                                                                    } else {
                                                                        delete values.file;
                                                                        Ext.Ajax.request({
                                                                            url: "/doctorRegrequest/" + fid,
                                                                            headers: {
                                                                                'Content-Type': 'application/json'
                                                                            },
                                                                            jsonData: Ext.util.JSON.encode(values),
                                                                            method: rest_method,
                                                                            success: successCallback,
                                                                            failure: failureCallback
                                                                        });
                                                                    }
                                                                },
                                                                failure: function(form, action) {
                                                                    form.findField("file").focus();
                                                                    Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                                                }
                                                            });
                                                        } else {
                                                            if (values.isMilitaryDoctor === "1") {
                                                                if (values.attachedDocIdMilitary !=="-1")
                                                                fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                                Ext.getCmp("attachedDocIdMilitary").submit({
                                                                    url: fileurl,
                                                                    waitMsg: 'Καταχώρηση αρχείου...',
                                                                    success: function(form, action) {
                                                                        values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                                        delete values.file;
                                                                        Ext.Ajax.request({
                                                                            url: "/doctorRegrequest/" + fid,
                                                                            headers: {
                                                                                'Content-Type': 'application/json'
                                                                            },
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

                                                            } else {
                                                                delete values.file;
                                                                Ext.Ajax.request({
                                                                    url: "/doctorRegrequest/" + fid,
                                                                    headers: {
                                                                        'Content-Type': 'application/json'
                                                                    },
                                                                    jsonData: Ext.util.JSON.encode(values),
                                                                    method: rest_method,
                                                                    success: successCallback,
                                                                    failure: failureCallback
                                                                });
                                                            }
                                                        }

                                                    },
                                                    failure: function(form, action) {
                                                        form.findField("file").focus();
                                                        Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                                    }
                                                });
                                            } else {
                                                if (Ext.getCmp("docfile1").getForm().findField("file").getValue() !== "") {
                                                    if (values.attachedDocId !=="-1")
                                                    fileurl = '/setDocument' + "?docId=" + values.attachedDocId;
                                                    else
                                                    fileurl = '/setDocument';
                                                    Ext.getCmp("docfile1").submit({
                                                        url: fileurl,
                                                        waitMsg: 'Καταχώρηση αρχείου...',
                                                        success: function(form, action) {
                                                            values.attachedDocId = parseInt(action.result.fileId);
                                                            if (values.isMilitaryDoctor === "1") {
                                                                if (values.attachedDocIdMilitary !=="-1")
                                                                fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                                Ext.getCmp("attachedDocIdMilitary").submit({
                                                                    url: fileurl,
                                                                    waitMsg: 'Καταχώρηση αρχείου...',
                                                                    success: function(form, action) {
                                                                        values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                                        delete values.file;
                                                                        Ext.Ajax.request({
                                                                            url: "/doctorRegrequest/" + fid,
                                                                            headers: {
                                                                                'Content-Type': 'application/json'
                                                                            },
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

                                                            } else {
                                                                delete values.file;
                                                                Ext.Ajax.request({
                                                                    url: "/doctorRegrequest/" + fid,
                                                                    headers: {
                                                                        'Content-Type': 'application/json'
                                                                    },
                                                                    jsonData: Ext.util.JSON.encode(values),
                                                                    method: rest_method,
                                                                    success: successCallback,
                                                                    failure: failureCallback
                                                                });
                                                            }
                                                        },
                                                        failure: function(form, action) {
                                                            form.findField("file").focus();
                                                            Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                                        }
                                                    });
                                                } else {
                                                    if (values.isMilitaryDoctor === "1") {
                                                        if (values.attachedDocIdMilitary !=="-1")
                                                        fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                        Ext.getCmp("attachedDocIdMilitary").submit({
                                                            url: fileurl,
                                                            waitMsg: 'Καταχώρηση αρχείου...',
                                                            success: function(form, action) {
                                                                values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                                delete values.file;
                                                                Ext.Ajax.request({
                                                                    url: "/doctorRegrequest/" + fid,
                                                                    headers: {
                                                                        'Content-Type': 'application/json'
                                                                    },
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

                                                    } else {
                                                        delete values.file;
                                                        Ext.Ajax.request({
                                                            url: "/doctorRegrequest/" + fid,
                                                            headers: {
                                                                'Content-Type': 'application/json'
                                                            },
                                                            jsonData: Ext.util.JSON.encode(values),
                                                            method: rest_method,
                                                            success: successCallback,
                                                            failure: failureCallback
                                                        });
                                                    }
                                                }
                                            }
                                        }
                                    },
                                    failure: function(form, action) {
                                        form.findField("file").focus();
                                        Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                    }
                                });
                            } else {
                                if (values.attachedDocIdMedassoc !=="-1")
                                fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMedassoc;
                                else
                                fileurl = '/setDocument';
                                if (Ext.getCmp("attachedDocIdMedassoc").getForm().findField("file").getValue() !== "") {
                                    Ext.getCmp("attachedDocIdMedassoc").submit({
                                        url: fileurl,
                                        waitMsg: 'Καταχώρηση αρχείου...',
                                        success: function(form, action) {
                                            values.attachedDocIdMedassoc = parseInt(action.result.fileId);
                                            if (values.attachedDocIdPedyYes !=="-1")
                                            fileurl = '/setDocument' + "?docId=" + values.attachedDocIdPedyYes;
                                            else if (values.attachedDocIdPedyNo !=="-1")
                                            fileurl = '/setDocument' + "?docId=" + values.attachedDocIdPedyNo;
                                            else
                                            fileurl = '/setDocument';
                                            if (Ext.getCmp("attachedDocIdPedy").getForm().findField("file").getValue() !== "") {
                                                Ext.getCmp("attachedDocIdPedy").submit({
                                                    url: fileurl,
                                                    waitMsg: 'Καταχώρηση αρχείου...',
                                                    success: function(form, action) {
                                                        if (values.belongsToPedy === "1")
                                                        values.attachedDocIdPedyYes = parseInt(action.result.fileId);
                                                        else
                                                        values.attachedDocIdPedyNo = parseInt(action.result.fileId);
                                                        if (Ext.getCmp("docfile1").getForm().findField("file").getValue() !== "") {
                                                            if (values.attachedDocId !=="-1")
                                                            fileurl = '/setDocument' + "?docId=" + values.attachedDocId;
                                                            else
                                                            fileurl = '/setDocument';
                                                            Ext.getCmp("docfile1").submit({
                                                                url: fileurl,
                                                                waitMsg: 'Καταχώρηση αρχείου...',
                                                                success: function(form, action) {
                                                                    values.attachedDocId = parseInt(action.result.fileId);
                                                                    if (values.isMilitaryDoctor === "1") {
                                                                        if (values.attachedDocIdMilitary !=="-1")
                                                                        fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                                        Ext.getCmp("attachedDocIdMilitary").submit({
                                                                            url: fileurl,
                                                                            waitMsg: 'Καταχώρηση αρχείου...',
                                                                            success: function(form, action) {
                                                                                values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                                                delete values.file;
                                                                                Ext.Ajax.request({
                                                                                    url: "/doctorRegrequest/" + fid,
                                                                                    headers: {
                                                                                        'Content-Type': 'application/json'
                                                                                    },
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

                                                                    } else {
                                                                        delete values.file;
                                                                        Ext.Ajax.request({
                                                                            url: "/doctorRegrequest/" + fid,
                                                                            headers: {
                                                                                'Content-Type': 'application/json'
                                                                            },
                                                                            jsonData: Ext.util.JSON.encode(values),
                                                                            method: rest_method,
                                                                            success: successCallback,
                                                                            failure: failureCallback
                                                                        });
                                                                    }
                                                                },
                                                                failure: function(form, action) {
                                                                    form.findField("file").focus();
                                                                    Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                                                }
                                                            });
                                                        } else {
                                                            if (values.isMilitaryDoctor === "1") {
                                                                if (values.attachedDocIdMilitary !=="-1")
                                                                fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                                Ext.getCmp("attachedDocIdMilitary").submit({
                                                                    url: fileurl,
                                                                    waitMsg: 'Καταχώρηση αρχείου...',
                                                                    success: function(form, action) {
                                                                        values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                                        delete values.file;
                                                                        Ext.Ajax.request({
                                                                            url: "/doctorRegrequest/" + fid,
                                                                            headers: {
                                                                                'Content-Type': 'application/json'
                                                                            },
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

                                                            } else {
                                                                delete values.file;
                                                                Ext.Ajax.request({
                                                                    url: "/doctorRegrequest/" + fid,
                                                                    headers: {
                                                                        'Content-Type': 'application/json'
                                                                    },
                                                                    jsonData: Ext.util.JSON.encode(values),
                                                                    method: rest_method,
                                                                    success: successCallback,
                                                                    failure: failureCallback
                                                                });
                                                            }
                                                        }

                                                    },
                                                    failure: function(form, action) {
                                                        form.findField("file").focus();
                                                        Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                                    }
                                                });
                                            } else {
                                                if (Ext.getCmp("docfile1").getForm().findField("file").getValue() !== "") {
                                                    if (values.attachedDocId !=="-1")
                                                    fileurl = '/setDocument' + "?docId=" + values.attachedDocId;
                                                    else
                                                    fileurl = '/setDocument';
                                                    Ext.getCmp("docfile1").submit({
                                                        url: fileurl,
                                                        waitMsg: 'Καταχώρηση αρχείου...',
                                                        success: function(form, action) {
                                                            values.attachedDocId = parseInt(action.result.fileId);
                                                            if (values.isMilitaryDoctor === "1") {
                                                                if (values.attachedDocIdMilitary !=="-1")
                                                                fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                                Ext.getCmp("attachedDocIdMilitary").submit({
                                                                    url: fileurl,
                                                                    waitMsg: 'Καταχώρηση αρχείου...',
                                                                    success: function(form, action) {
                                                                        values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                                        delete values.file;
                                                                        Ext.Ajax.request({
                                                                            url: "/doctorRegrequest/" + fid,
                                                                            headers: {
                                                                                'Content-Type': 'application/json'
                                                                            },
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

                                                            } else {
                                                                delete values.file;
                                                                Ext.Ajax.request({
                                                                    url: "/doctorRegrequest/" + fid,
                                                                    headers: {
                                                                        'Content-Type': 'application/json'
                                                                    },
                                                                    jsonData: Ext.util.JSON.encode(values),
                                                                    method: rest_method,
                                                                    success: successCallback,
                                                                    failure: failureCallback
                                                                });
                                                            }
                                                        },
                                                        failure: function(form, action) {
                                                            form.findField("file").focus();
                                                            Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                                        }
                                                    });
                                                } else {
                                                    if (values.isMilitaryDoctor === "1") {
                                                        if (values.attachedDocIdMilitary !=="-1")
                                                        fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                        Ext.getCmp("attachedDocIdMilitary").submit({
                                                            url: fileurl,
                                                            waitMsg: 'Καταχώρηση αρχείου...',
                                                            success: function(form, action) {
                                                                values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                                delete values.file;
                                                                Ext.Ajax.request({
                                                                    url: "/doctorRegrequest/" + fid,
                                                                    headers: {
                                                                        'Content-Type': 'application/json'
                                                                    },
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

                                                    } else {
                                                        delete values.file;
                                                        Ext.Ajax.request({
                                                            url: "/doctorRegrequest/" + fid,
                                                            headers: {
                                                                'Content-Type': 'application/json'
                                                            },
                                                            jsonData: Ext.util.JSON.encode(values),
                                                            method: rest_method,
                                                            success: successCallback,
                                                            failure: failureCallback
                                                        });
                                                    }
                                                }
                                            }
                                        },
                                        failure: function(form, action) {
                                            form.findField("file").focus();
                                            Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                        }
                                    });
                                } else {
                                    if (Ext.getCmp("attachedDocIdPedy").getForm().findField("file").getValue() !== "") {
                                        Ext.getCmp("attachedDocIdPedy").submit({
                                            url: fileurl,
                                            waitMsg: 'Καταχώρηση αρχείου...',
                                            success: function(form, action) {
                                                if (values.belongsToPedy === "1")
                                                values.attachedDocIdPedyYes = parseInt(action.result.fileId);
                                                else
                                                values.attachedDocIdPedyNo = parseInt(action.result.fileId);
                                                if (Ext.getCmp("docfile1").getForm().findField("file").getValue() !== "") {
                                                    if (values.attachedDocId !=="-1")
                                                    fileurl = '/setDocument' + "?docId=" + values.attachedDocId;
                                                    else
                                                    fileurl = '/setDocument';
                                                    Ext.getCmp("docfile1").submit({
                                                        url: fileurl,
                                                        waitMsg: 'Καταχώρηση αρχείου...',
                                                        success: function(form, action) {
                                                            values.attachedDocId = parseInt(action.result.fileId);
                                                            if (values.isMilitaryDoctor === "1") {
                                                                if (values.attachedDocIdMilitary !=="-1")
                                                                fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                                Ext.getCmp("attachedDocIdMilitary").submit({
                                                                    url: fileurl,
                                                                    waitMsg: 'Καταχώρηση αρχείου...',
                                                                    success: function(form, action) {
                                                                        values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                                        delete values.file;
                                                                        Ext.Ajax.request({
                                                                            url: "/doctorRegrequest/" + fid,
                                                                            headers: {
                                                                                'Content-Type': 'application/json'
                                                                            },
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

                                                            } else {
                                                                delete values.file;
                                                                Ext.Ajax.request({
                                                                    url: "/doctorRegrequest/" + fid,
                                                                    headers: {
                                                                        'Content-Type': 'application/json'
                                                                    },
                                                                    jsonData: Ext.util.JSON.encode(values),
                                                                    method: rest_method,
                                                                    success: successCallback,
                                                                    failure: failureCallback
                                                                });
                                                            }
                                                        },
                                                        failure: function(form, action) {
                                                            form.findField("file").focus();
                                                            Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                                        }
                                                    });
                                                } else {
                                                    if (values.isMilitaryDoctor === "1") {
                                                        if (values.attachedDocIdMilitary !=="-1")
                                                        fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                        Ext.getCmp("attachedDocIdMilitary").submit({
                                                            url: fileurl,
                                                            waitMsg: 'Καταχώρηση αρχείου...',
                                                            success: function(form, action) {
                                                                values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                                delete values.file;
                                                                Ext.Ajax.request({
                                                                    url: "/doctorRegrequest/" + fid,
                                                                    headers: {
                                                                        'Content-Type': 'application/json'
                                                                    },
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

                                                    } else {
                                                        delete values.file;
                                                        Ext.Ajax.request({
                                                            url: "/doctorRegrequest/" + fid,
                                                            headers: {
                                                                'Content-Type': 'application/json'
                                                            },
                                                            jsonData: Ext.util.JSON.encode(values),
                                                            method: rest_method,
                                                            success: successCallback,
                                                            failure: failureCallback
                                                        });
                                                    }
                                                }

                                            },
                                            failure: function(form, action) {
                                                form.findField("file").focus();
                                                Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                            }
                                        });
                                    } else {
                                        if (Ext.getCmp("docfile1").getForm().findField("file").getValue() !== "") {
                                            if (values.attachedDocId !=="-1")
                                            fileurl = '/setDocument' + "?docId=" + values.attachedDocId;
                                            else
                                            fileurl = '/setDocument';
                                            Ext.getCmp("docfile1").submit({
                                                url: fileurl,
                                                waitMsg: 'Καταχώρηση αρχείου...',
                                                success: function(form, action) {
                                                    values.attachedDocId = parseInt(action.result.fileId);
                                                    if (values.isMilitaryDoctor === "1") {
                                                        if (values.attachedDocIdMilitary !=="-1")
                                                        fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                        Ext.getCmp("attachedDocIdMilitary").submit({
                                                            url: fileurl,
                                                            waitMsg: 'Καταχώρηση αρχείου...',
                                                            success: function(form, action) {
                                                                values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                                delete values.file;
                                                                Ext.Ajax.request({
                                                                    url: "/doctorRegrequest/" + fid,
                                                                    headers: {
                                                                        'Content-Type': 'application/json'
                                                                    },
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

                                                    } else {
                                                        delete values.file;
                                                        Ext.Ajax.request({
                                                            url: "/doctorRegrequest/" + fid,
                                                            headers: {
                                                                'Content-Type': 'application/json'
                                                            },
                                                            jsonData: Ext.util.JSON.encode(values),
                                                            method: rest_method,
                                                            success: successCallback,
                                                            failure: failureCallback
                                                        });
                                                    }
                                                },
                                                failure: function(form, action) {
                                                    form.findField("file").focus();
                                                    Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                                }
                                            });
                                        } else {
                                            if (values.isMilitaryDoctor === "1") {
                                                if (values.attachedDocIdMilitary !=="-1")
                                                fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                Ext.getCmp("attachedDocIdMilitary").submit({
                                                    url: fileurl,
                                                    waitMsg: 'Καταχώρηση αρχείου...',
                                                    success: function(form, action) {
                                                        values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                        delete values.file;
                                                        Ext.Ajax.request({
                                                            url: "/doctorRegrequest/" + fid,
                                                            headers: {
                                                                'Content-Type': 'application/json'
                                                            },
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

                                            } else {
                                                delete values.file;
                                                Ext.Ajax.request({
                                                    url: "/doctorRegrequest/" + fid,
                                                    headers: {
                                                        'Content-Type': 'application/json'
                                                    },
                                                    jsonData: Ext.util.JSON.encode(values),
                                                    method: rest_method,
                                                    success: successCallback,
                                                    failure: failureCallback
                                                });
                                            }
                                        }
                                    }
                                }
                            }
                        },
                        failure: function(form, action) {
                            form.findField("file").focus();
                            Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                        }
                    });
                } else {
                    if (values.attachedDocIdEmplTraining !=="-1")
                    fileurl = '/setDocument' + "?docId=" + values.attachedDocIdEmplTraining;
                    else
                    fileurl = '/setDocument';
                    if (Ext.getCmp("attachedDocIdEmplTraining").getForm().findField("file").getValue() !== "") {
                        Ext.getCmp("attachedDocIdEmplTraining").submit({
                            url: fileurl,
                            waitMsg: 'Καταχώρηση αρχείου...',
                            success: function(form, action) {
                                values.attachedDocIdEmplTraining = parseInt(action.result.fileId);
                                if (values.attachedDocIdMedassoc !=="-1")
                                fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMedassoc;
                                else
                                fileurl = '/setDocument';
                                if (Ext.getCmp("attachedDocIdMedassoc").getForm().findField("file").getValue() !== "") {
                                    Ext.getCmp("attachedDocIdMedassoc").submit({
                                        url: fileurl,
                                        waitMsg: 'Καταχώρηση αρχείου...',
                                        success: function(form, action) {
                                            values.attachedDocIdMedassoc = parseInt(action.result.fileId);
                                            if (values.attachedDocIdPedyYes !=="-1")
                                            fileurl = '/setDocument' + "?docId=" + values.attachedDocIdPedyYes;
                                            else if (values.attachedDocIdPedyNo !=="-1")
                                            fileurl = '/setDocument' + "?docId=" + values.attachedDocIdPedyNo;
                                            else
                                            fileurl = '/setDocument';
                                            if (Ext.getCmp("attachedDocIdPedy").getForm().findField("file").getValue() !== "") {
                                                Ext.getCmp("attachedDocIdPedy").submit({
                                                    url: fileurl,
                                                    waitMsg: 'Καταχώρηση αρχείου...',
                                                    success: function(form, action) {
                                                        if (values.belongsToPedy === "1")
                                                        values.attachedDocIdPedyYes = parseInt(action.result.fileId);
                                                        else
                                                        values.attachedDocIdPedyNo = parseInt(action.result.fileId);
                                                        if (Ext.getCmp("docfile1").getForm().findField("file").getValue() !== "") {
                                                            if (values.attachedDocId !=="-1")
                                                            fileurl = '/setDocument' + "?docId=" + values.attachedDocId;
                                                            else
                                                            fileurl = '/setDocument';
                                                            Ext.getCmp("docfile1").submit({
                                                                url: fileurl,
                                                                waitMsg: 'Καταχώρηση αρχείου...',
                                                                success: function(form, action) {
                                                                    values.attachedDocId = parseInt(action.result.fileId);
                                                                    if (values.isMilitaryDoctor === "1") {
                                                                        if (values.attachedDocIdMilitary !=="-1")
                                                                        fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                                        Ext.getCmp("attachedDocIdMilitary").submit({
                                                                            url: fileurl,
                                                                            waitMsg: 'Καταχώρηση αρχείου...',
                                                                            success: function(form, action) {
                                                                                values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                                                delete values.file;
                                                                                Ext.Ajax.request({
                                                                                    url: "/doctorRegrequest/" + fid,
                                                                                    headers: {
                                                                                        'Content-Type': 'application/json'
                                                                                    },
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

                                                                    } else {
                                                                        delete values.file;
                                                                        Ext.Ajax.request({
                                                                            url: "/doctorRegrequest/" + fid,
                                                                            headers: {
                                                                                'Content-Type': 'application/json'
                                                                            },
                                                                            jsonData: Ext.util.JSON.encode(values),
                                                                            method: rest_method,
                                                                            success: successCallback,
                                                                            failure: failureCallback
                                                                        });
                                                                    }
                                                                },
                                                                failure: function(form, action) {
                                                                    form.findField("file").focus();
                                                                    Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                                                }
                                                            });
                                                        } else {
                                                            if (values.isMilitaryDoctor === "1") {
                                                                if (values.attachedDocIdMilitary !=="-1")
                                                                fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                                Ext.getCmp("attachedDocIdMilitary").submit({
                                                                    url: fileurl,
                                                                    waitMsg: 'Καταχώρηση αρχείου...',
                                                                    success: function(form, action) {
                                                                        values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                                        delete values.file;
                                                                        Ext.Ajax.request({
                                                                            url: "/doctorRegrequest/" + fid,
                                                                            headers: {
                                                                                'Content-Type': 'application/json'
                                                                            },
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

                                                            } else {
                                                                delete values.file;
                                                                Ext.Ajax.request({
                                                                    url: "/doctorRegrequest/" + fid,
                                                                    headers: {
                                                                        'Content-Type': 'application/json'
                                                                    },
                                                                    jsonData: Ext.util.JSON.encode(values),
                                                                    method: rest_method,
                                                                    success: successCallback,
                                                                    failure: failureCallback
                                                                });
                                                            }
                                                        }

                                                    },
                                                    failure: function(form, action) {
                                                        form.findField("file").focus();
                                                        Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                                    }
                                                });
                                            } else {
                                                if (Ext.getCmp("docfile1").getForm().findField("file").getValue() !== "") {
                                                    if (values.attachedDocId !=="-1")
                                                    fileurl = '/setDocument' + "?docId=" + values.attachedDocId;
                                                    else
                                                    fileurl = '/setDocument';
                                                    Ext.getCmp("docfile1").submit({
                                                        url: fileurl,
                                                        waitMsg: 'Καταχώρηση αρχείου...',
                                                        success: function(form, action) {
                                                            values.attachedDocId = parseInt(action.result.fileId);
                                                            if (values.isMilitaryDoctor === "1") {
                                                                if (values.attachedDocIdMilitary !=="-1")
                                                                fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                                Ext.getCmp("attachedDocIdMilitary").submit({
                                                                    url: fileurl,
                                                                    waitMsg: 'Καταχώρηση αρχείου...',
                                                                    success: function(form, action) {
                                                                        values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                                        delete values.file;
                                                                        Ext.Ajax.request({
                                                                            url: "/doctorRegrequest/" + fid,
                                                                            headers: {
                                                                                'Content-Type': 'application/json'
                                                                            },
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

                                                            } else {
                                                                delete values.file;
                                                                Ext.Ajax.request({
                                                                    url: "/doctorRegrequest/" + fid,
                                                                    headers: {
                                                                        'Content-Type': 'application/json'
                                                                    },
                                                                    jsonData: Ext.util.JSON.encode(values),
                                                                    method: rest_method,
                                                                    success: successCallback,
                                                                    failure: failureCallback
                                                                });
                                                            }
                                                        },
                                                        failure: function(form, action) {
                                                            form.findField("file").focus();
                                                            Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                                        }
                                                    });
                                                } else {
                                                    if (values.isMilitaryDoctor === "1") {
                                                        if (values.attachedDocIdMilitary !=="-1")
                                                        fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                        Ext.getCmp("attachedDocIdMilitary").submit({
                                                            url: fileurl,
                                                            waitMsg: 'Καταχώρηση αρχείου...',
                                                            success: function(form, action) {
                                                                values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                                delete values.file;
                                                                Ext.Ajax.request({
                                                                    url: "/doctorRegrequest/" + fid,
                                                                    headers: {
                                                                        'Content-Type': 'application/json'
                                                                    },
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

                                                    } else {
                                                        delete values.file;
                                                        Ext.Ajax.request({
                                                            url: "/doctorRegrequest/" + fid,
                                                            headers: {
                                                                'Content-Type': 'application/json'
                                                            },
                                                            jsonData: Ext.util.JSON.encode(values),
                                                            method: rest_method,
                                                            success: successCallback,
                                                            failure: failureCallback
                                                        });
                                                    }
                                                }
                                            }
                                        },
                                        failure: function(form, action) {
                                            form.findField("file").focus();
                                            Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                        }
                                    });
                                } else {
                                    if (Ext.getCmp("attachedDocIdPedy").getForm().findField("file").getValue() !== "") {
                                        Ext.getCmp("attachedDocIdPedy").submit({
                                            url: fileurl,
                                            waitMsg: 'Καταχώρηση αρχείου...',
                                            success: function(form, action) {
                                                if (values.belongsToPedy === "1")
                                                values.attachedDocIdPedyYes = parseInt(action.result.fileId);
                                                else
                                                values.attachedDocIdPedyNo = parseInt(action.result.fileId);
                                                if (Ext.getCmp("docfile1").getForm().findField("file").getValue() !== "") {
                                                    if (values.attachedDocId !=="-1")
                                                    fileurl = '/setDocument' + "?docId=" + values.attachedDocId;
                                                    else
                                                    fileurl = '/setDocument';
                                                    Ext.getCmp("docfile1").submit({
                                                        url: fileurl,
                                                        waitMsg: 'Καταχώρηση αρχείου...',
                                                        success: function(form, action) {
                                                            values.attachedDocId = parseInt(action.result.fileId);
                                                            if (values.isMilitaryDoctor === "1") {
                                                                if (values.attachedDocIdMilitary !=="-1")
                                                                fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                                Ext.getCmp("attachedDocIdMilitary").submit({
                                                                    url: fileurl,
                                                                    waitMsg: 'Καταχώρηση αρχείου...',
                                                                    success: function(form, action) {
                                                                        values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                                        delete values.file;
                                                                        Ext.Ajax.request({
                                                                            url: "/doctorRegrequest/" + fid,
                                                                            headers: {
                                                                                'Content-Type': 'application/json'
                                                                            },
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

                                                            } else {
                                                                delete values.file;
                                                                Ext.Ajax.request({
                                                                    url: "/doctorRegrequest/" + fid,
                                                                    headers: {
                                                                        'Content-Type': 'application/json'
                                                                    },
                                                                    jsonData: Ext.util.JSON.encode(values),
                                                                    method: rest_method,
                                                                    success: successCallback,
                                                                    failure: failureCallback
                                                                });
                                                            }
                                                        },
                                                        failure: function(form, action) {
                                                            form.findField("file").focus();
                                                            Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                                        }
                                                    });
                                                } else {
                                                    if (values.isMilitaryDoctor === "1") {
                                                        if (values.attachedDocIdMilitary !=="-1")
                                                        fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                        Ext.getCmp("attachedDocIdMilitary").submit({
                                                            url: fileurl,
                                                            waitMsg: 'Καταχώρηση αρχείου...',
                                                            success: function(form, action) {
                                                                values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                                delete values.file;
                                                                Ext.Ajax.request({
                                                                    url: "/doctorRegrequest/" + fid,
                                                                    headers: {
                                                                        'Content-Type': 'application/json'
                                                                    },
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

                                                    } else {
                                                        delete values.file;
                                                        Ext.Ajax.request({
                                                            url: "/doctorRegrequest/" + fid,
                                                            headers: {
                                                                'Content-Type': 'application/json'
                                                            },
                                                            jsonData: Ext.util.JSON.encode(values),
                                                            method: rest_method,
                                                            success: successCallback,
                                                            failure: failureCallback
                                                        });
                                                    }
                                                }

                                            },
                                            failure: function(form, action) {
                                                form.findField("file").focus();
                                                Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                            }
                                        });
                                    } else {
                                        if (Ext.getCmp("docfile1").getForm().findField("file").getValue() !== "") {
                                            if (values.attachedDocId !=="-1")
                                            fileurl = '/setDocument' + "?docId=" + values.attachedDocId;
                                            else
                                            fileurl = '/setDocument';
                                            Ext.getCmp("docfile1").submit({
                                                url: fileurl,
                                                waitMsg: 'Καταχώρηση αρχείου...',
                                                success: function(form, action) {
                                                    values.attachedDocId = parseInt(action.result.fileId);
                                                    if (values.isMilitaryDoctor === "1") {
                                                        if (values.attachedDocIdMilitary !=="-1")
                                                        fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                        Ext.getCmp("attachedDocIdMilitary").submit({
                                                            url: fileurl,
                                                            waitMsg: 'Καταχώρηση αρχείου...',
                                                            success: function(form, action) {
                                                                values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                                delete values.file;
                                                                Ext.Ajax.request({
                                                                    url: "/doctorRegrequest/" + fid,
                                                                    headers: {
                                                                        'Content-Type': 'application/json'
                                                                    },
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

                                                    } else {
                                                        delete values.file;
                                                        Ext.Ajax.request({
                                                            url: "/doctorRegrequest/" + fid,
                                                            headers: {
                                                                'Content-Type': 'application/json'
                                                            },
                                                            jsonData: Ext.util.JSON.encode(values),
                                                            method: rest_method,
                                                            success: successCallback,
                                                            failure: failureCallback
                                                        });
                                                    }
                                                },
                                                failure: function(form, action) {
                                                    form.findField("file").focus();
                                                    Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                                }
                                            });
                                        } else {
                                            if (values.isMilitaryDoctor === "1") {
                                                if (values.attachedDocIdMilitary !=="-1")
                                                fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                Ext.getCmp("attachedDocIdMilitary").submit({
                                                    url: fileurl,
                                                    waitMsg: 'Καταχώρηση αρχείου...',
                                                    success: function(form, action) {
                                                        values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                        delete values.file;
                                                        Ext.Ajax.request({
                                                            url: "/doctorRegrequest/" + fid,
                                                            headers: {
                                                                'Content-Type': 'application/json'
                                                            },
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

                                            } else {
                                                delete values.file;
                                                Ext.Ajax.request({
                                                    url: "/doctorRegrequest/" + fid,
                                                    headers: {
                                                        'Content-Type': 'application/json'
                                                    },
                                                    jsonData: Ext.util.JSON.encode(values),
                                                    method: rest_method,
                                                    success: successCallback,
                                                    failure: failureCallback
                                                });
                                            }
                                        }
                                    }
                                }
                            },
                            failure: function(form, action) {
                                form.findField("file").focus();
                                Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                            }
                        });
                    } else {
                        if (values.attachedDocIdMedassoc !=="-1")
                        fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMedassoc;
                        else
                        fileurl = '/setDocument';
                        if (Ext.getCmp("attachedDocIdMedassoc").getForm().findField("file").getValue() !== "") {
                            Ext.getCmp("attachedDocIdMedassoc").submit({
                                url: fileurl,
                                waitMsg: 'Καταχώρηση αρχείου...',
                                success: function(form, action) {
                                    values.attachedDocIdMedassoc = parseInt(action.result.fileId);
                                    if (values.attachedDocIdPedyYes !=="-1")
                                    fileurl = '/setDocument' + "?docId=" + values.attachedDocIdPedyYes;
                                    else if (values.attachedDocIdPedyNo !=="-1")
                                    fileurl = '/setDocument' + "?docId=" + values.attachedDocIdPedyNo;
                                    else
                                    fileurl = '/setDocument';
                                    if (Ext.getCmp("attachedDocIdPedy").getForm().findField("file").getValue() !== "") {
                                        Ext.getCmp("attachedDocIdPedy").submit({
                                            url: fileurl,
                                            waitMsg: 'Καταχώρηση αρχείου...',
                                            success: function(form, action) {
                                                if (values.belongsToPedy === "1")
                                                values.attachedDocIdPedyYes = parseInt(action.result.fileId);
                                                else
                                                values.attachedDocIdPedyNo = parseInt(action.result.fileId);
                                                if (Ext.getCmp("docfile1").getForm().findField("file").getValue() !== "") {
                                                    if (values.attachedDocId !=="-1")
                                                    fileurl = '/setDocument' + "?docId=" + values.attachedDocId;
                                                    else
                                                    fileurl = '/setDocument';
                                                    Ext.getCmp("docfile1").submit({
                                                        url: fileurl,
                                                        waitMsg: 'Καταχώρηση αρχείου...',
                                                        success: function(form, action) {
                                                            values.attachedDocId = parseInt(action.result.fileId);
                                                            if (values.isMilitaryDoctor === "1") {
                                                                if (values.attachedDocIdMilitary !=="-1")
                                                                fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                                Ext.getCmp("attachedDocIdMilitary").submit({
                                                                    url: fileurl,
                                                                    waitMsg: 'Καταχώρηση αρχείου...',
                                                                    success: function(form, action) {
                                                                        values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                                        delete values.file;
                                                                        Ext.Ajax.request({
                                                                            url: "/doctorRegrequest/" + fid,
                                                                            headers: {
                                                                                'Content-Type': 'application/json'
                                                                            },
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

                                                            } else {
                                                                delete values.file;
                                                                Ext.Ajax.request({
                                                                    url: "/doctorRegrequest/" + fid,
                                                                    headers: {
                                                                        'Content-Type': 'application/json'
                                                                    },
                                                                    jsonData: Ext.util.JSON.encode(values),
                                                                    method: rest_method,
                                                                    success: successCallback,
                                                                    failure: failureCallback
                                                                });
                                                            }
                                                        },
                                                        failure: function(form, action) {
                                                            form.findField("file").focus();
                                                            Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                                        }
                                                    });
                                                } else {
                                                    if (values.isMilitaryDoctor === "1") {
                                                        if (values.attachedDocIdMilitary !=="-1")
                                                        fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                        Ext.getCmp("attachedDocIdMilitary").submit({
                                                            url: fileurl,
                                                            waitMsg: 'Καταχώρηση αρχείου...',
                                                            success: function(form, action) {
                                                                values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                                delete values.file;
                                                                Ext.Ajax.request({
                                                                    url: "/doctorRegrequest/" + fid,
                                                                    headers: {
                                                                        'Content-Type': 'application/json'
                                                                    },
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

                                                    } else {
                                                        delete values.file;
                                                        Ext.Ajax.request({
                                                            url: "/doctorRegrequest/" + fid,
                                                            headers: {
                                                                'Content-Type': 'application/json'
                                                            },
                                                            jsonData: Ext.util.JSON.encode(values),
                                                            method: rest_method,
                                                            success: successCallback,
                                                            failure: failureCallback
                                                        });
                                                    }
                                                }

                                            },
                                            failure: function(form, action) {
                                                form.findField("file").focus();
                                                Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                            }
                                        });
                                    } else {
                                        if (Ext.getCmp("docfile1").getForm().findField("file").getValue() !== "") {
                                            if (values.attachedDocId !=="-1")
                                            fileurl = '/setDocument' + "?docId=" + values.attachedDocId;
                                            else
                                            fileurl = '/setDocument';
                                            Ext.getCmp("docfile1").submit({
                                                url: fileurl,
                                                waitMsg: 'Καταχώρηση αρχείου...',
                                                success: function(form, action) {
                                                    values.attachedDocId = parseInt(action.result.fileId);
                                                    if (values.isMilitaryDoctor === "1") {
                                                        if (values.attachedDocIdMilitary !=="-1")
                                                        fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                        Ext.getCmp("attachedDocIdMilitary").submit({
                                                            url: fileurl,
                                                            waitMsg: 'Καταχώρηση αρχείου...',
                                                            success: function(form, action) {
                                                                values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                                delete values.file;
                                                                Ext.Ajax.request({
                                                                    url: "/doctorRegrequest/" + fid,
                                                                    headers: {
                                                                        'Content-Type': 'application/json'
                                                                    },
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

                                                    } else {
                                                        delete values.file;
                                                        Ext.Ajax.request({
                                                            url: "/doctorRegrequest/" + fid,
                                                            headers: {
                                                                'Content-Type': 'application/json'
                                                            },
                                                            jsonData: Ext.util.JSON.encode(values),
                                                            method: rest_method,
                                                            success: successCallback,
                                                            failure: failureCallback
                                                        });
                                                    }
                                                },
                                                failure: function(form, action) {
                                                    form.findField("file").focus();
                                                    Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                                }
                                            });
                                        } else {
                                            if (values.isMilitaryDoctor === "1") {
                                                if (values.attachedDocIdMilitary !=="-1")
                                                fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                Ext.getCmp("attachedDocIdMilitary").submit({
                                                    url: fileurl,
                                                    waitMsg: 'Καταχώρηση αρχείου...',
                                                    success: function(form, action) {
                                                        values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                        delete values.file;
                                                        Ext.Ajax.request({
                                                            url: "/doctorRegrequest/" + fid,
                                                            headers: {
                                                                'Content-Type': 'application/json'
                                                            },
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

                                            } else {
                                                delete values.file;
                                                Ext.Ajax.request({
                                                    url: "/doctorRegrequest/" + fid,
                                                    headers: {
                                                        'Content-Type': 'application/json'
                                                    },
                                                    jsonData: Ext.util.JSON.encode(values),
                                                    method: rest_method,
                                                    success: successCallback,
                                                    failure: failureCallback
                                                });
                                            }
                                        }
                                    }
                                },
                                failure: function(form, action) {
                                    form.findField("file").focus();
                                    Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                }
                            });
                        } else {
                            if (Ext.getCmp("attachedDocIdPedy").getForm().findField("file").getValue() !== "") {
                                Ext.getCmp("attachedDocIdPedy").submit({
                                    url: fileurl,
                                    waitMsg: 'Καταχώρηση αρχείου...',
                                    success: function(form, action) {
                                        if (values.belongsToPedy === "1")
                                        values.attachedDocIdPedyYes = parseInt(action.result.fileId);
                                        else
                                        values.attachedDocIdPedyNo = parseInt(action.result.fileId);
                                        if (Ext.getCmp("docfile1").getForm().findField("file").getValue() !== "") {
                                            if (values.attachedDocId !=="-1")
                                            fileurl = '/setDocument' + "?docId=" + values.attachedDocId;
                                            else
                                            fileurl = '/setDocument';
                                            Ext.getCmp("docfile1").submit({
                                                url: fileurl,
                                                waitMsg: 'Καταχώρηση αρχείου...',
                                                success: function(form, action) {
                                                    values.attachedDocId = parseInt(action.result.fileId);
                                                    if (values.isMilitaryDoctor === "1") {
                                                        if (values.attachedDocIdMilitary !=="-1")
                                                        fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                        Ext.getCmp("attachedDocIdMilitary").submit({
                                                            url: fileurl,
                                                            waitMsg: 'Καταχώρηση αρχείου...',
                                                            success: function(form, action) {
                                                                values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                                delete values.file;
                                                                Ext.Ajax.request({
                                                                    url: "/doctorRegrequest/" + fid,
                                                                    headers: {
                                                                        'Content-Type': 'application/json'
                                                                    },
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

                                                    } else {
                                                        delete values.file;
                                                        Ext.Ajax.request({
                                                            url: "/doctorRegrequest/" + fid,
                                                            headers: {
                                                                'Content-Type': 'application/json'
                                                            },
                                                            jsonData: Ext.util.JSON.encode(values),
                                                            method: rest_method,
                                                            success: successCallback,
                                                            failure: failureCallback
                                                        });
                                                    }
                                                },
                                                failure: function(form, action) {
                                                    form.findField("file").focus();
                                                    Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                                }
                                            });
                                        } else {
                                            if (values.isMilitaryDoctor === "1") {
                                                if (values.attachedDocIdMilitary !=="-1")
                                                fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                Ext.getCmp("attachedDocIdMilitary").submit({
                                                    url: fileurl,
                                                    waitMsg: 'Καταχώρηση αρχείου...',
                                                    success: function(form, action) {
                                                        values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                        delete values.file;
                                                        Ext.Ajax.request({
                                                            url: "/doctorRegrequest/" + fid,
                                                            headers: {
                                                                'Content-Type': 'application/json'
                                                            },
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

                                            } else {
                                                delete values.file;
                                                Ext.Ajax.request({
                                                    url: "/doctorRegrequest/" + fid,
                                                    headers: {
                                                        'Content-Type': 'application/json'
                                                    },
                                                    jsonData: Ext.util.JSON.encode(values),
                                                    method: rest_method,
                                                    success: successCallback,
                                                    failure: failureCallback
                                                });
                                            }
                                        }

                                    },
                                    failure: function(form, action) {
                                        form.findField("file").focus();
                                        Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                    }
                                });
                            } else {
                                if (Ext.getCmp("docfile1").getForm().findField("file").getValue() !== "") {
                                    if (values.attachedDocId !=="-1")
                                    fileurl = '/setDocument' + "?docId=" + values.attachedDocId;
                                    else
                                    fileurl = '/setDocument';
                                    Ext.getCmp("docfile1").submit({
                                        url: fileurl,
                                        waitMsg: 'Καταχώρηση αρχείου...',
                                        success: function(form, action) {
                                            values.attachedDocId = parseInt(action.result.fileId);
                                            if (values.isMilitaryDoctor === "1") {
                                                if (values.attachedDocIdMilitary !=="-1")
                                                fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                                Ext.getCmp("attachedDocIdMilitary").submit({
                                                    url: fileurl,
                                                    waitMsg: 'Καταχώρηση αρχείου...',
                                                    success: function(form, action) {
                                                        values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                        delete values.file;
                                                        Ext.Ajax.request({
                                                            url: "/doctorRegrequest/" + fid,
                                                            headers: {
                                                                'Content-Type': 'application/json'
                                                            },
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

                                            } else {
                                                delete values.file;
                                                Ext.Ajax.request({
                                                    url: "/doctorRegrequest/" + fid,
                                                    headers: {
                                                        'Content-Type': 'application/json'
                                                    },
                                                    jsonData: Ext.util.JSON.encode(values),
                                                    method: rest_method,
                                                    success: successCallback,
                                                    failure: failureCallback
                                                });
                                            }
                                        },
                                        failure: function(form, action) {
                                            form.findField("file").focus();
                                            Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);

                                        }
                                    });
                                } else {
                                    if (values.isMilitaryDoctor === "1") {
                                        if (values.attachedDocIdMilitary !=="-1")
                                        fileurl = '/setDocument' + "?docId=" + values.attachedDocIdMilitary;
                                        Ext.getCmp("attachedDocIdMilitary").submit({
                                            url: fileurl,
                                            waitMsg: 'Καταχώρηση αρχείου...',
                                            success: function(form, action) {
                                                values.attachedDocIdMilitary = parseInt(action.result.fileId);
                                                delete values.file;
                                                Ext.Ajax.request({
                                                    url: "/doctorRegrequest/" + fid,
                                                    headers: {
                                                        'Content-Type': 'application/json'
                                                    },
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

                                    } else {
                                        delete values.file;
                                        Ext.Ajax.request({
                                            url: "/doctorRegrequest/" + fid,
                                            headers: {
                                                'Content-Type': 'application/json'
                                            },
                                            jsonData: Ext.util.JSON.encode(values),
                                            method: rest_method,
                                            success: successCallback,
                                            failure: failureCallback
                                        });
                                    }
                                }
                            }
                        }
                    }
                }*/
            }
            else{
                Ext.Msg.alert('Αποτυχία Υποβολής', 'Παρακαλώ διορθώστε τα λάθος πεδία');

            }
        };


    }

});
