/*
 * File: app/view/technician/RegRequestFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.technician.RegRequestFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.technicianregrequestform',

    onDelete_TECHNICIAN_REGREQUEST: function(button, e, eOpts) {

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
                        url: "/technicianRegrequest/" + fid,
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

    onSave_TECHNICIAN_REGREQUEST: function(button, e, eOpts) {

        var field_validation = function (form) {

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
        };

        var conffun = function(buttonText) {
            if (buttonText == "yes") {
                submitfun();
            }
            if (buttonText == "no") {
                return false;
            }
        };

        var msb = Ext.MessageBox.confirm('Υποβολή φόρμας', 'Είστε σίγουροι ότι θελετε να αποθηκευθεί η αίτηση; ', conffun);

        var submitfun = function(){
            var form = button.up('toolbar').up('window').down('form'),
                formWindow = button.up('toolbar').up('window'),
                values = form.getValues();

                if(parseInt(values.attachedDocIdEmplTraining)!==-1 && values.deletedfile2==="true" && values.file[0]===""){
                    form.getForm().findField('file').allowBlank=false;
                    form.getForm().findField('file').allowOnlyWhitespace=false;
                }
                else {
                    if (form.getForm().findField('a_new_form').getValue() === "true") {
                        if ((parseInt(values.shipyardDuties) == 1 || parseInt(values.education100) == 1 || parseInt(values.education3510) == 1 || parseInt(values.education10) == 1) && values.file[0] === "") {
                            form.getForm().findField('file').allowBlank = false;
                            form.getForm().findField('file').allowOnlyWhitespace = false;
                        } else {
                            form.getForm().findField('file').allowBlank = true;
                            form.getForm().findField('file').allowOnlyWhitespace = true;
                        }
                    }
                    else {
                        if ((parseInt(values.shipyardDuties) == 1 || parseInt(values.education100) == 1 || parseInt(values.education3510) == 1 || parseInt(values.education10) == 1) && values.file[0] === "") {
                            form.getForm().findField('file').allowBlank = false;
                            form.getForm().findField('file').allowOnlyWhitespace = false;
                        } else {
                            form.getForm().findField('file').allowBlank = true;
                            form.getForm().findField('file').allowOnlyWhitespace = true;
                        }
                    }
                }

            if (field_validation(form)) {
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

                    Ext.Msg.alert('Επιτυχής Αποθήκευση', 'Η αίτηση σας αποθηκεύθηκε με επιτυχία.');
                    formWindow.destroy();

                };

                // Failure
                var failureCallback = function(resp, ops) {

                    Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Η αίτηση σας δεν έγινε δεκτή από το σύστημα');

                };

                values.protNo="";
                values.protDate=button.up('toolbar').getCurrentTimestamp(1);
                values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
                values.protYear=button.up('toolbar').getCurrentTimestamp(3);

                values.diplomaIdComma="";
                var checkedboxes = form.getForm().findField('diplomas').getChecked().length;
                if(checkedboxes>0){
                    for (var j = 0; j < checkedboxes; j++) {
                        if (j==checkedboxes-1) {
                            values.diplomaIdComma += form.getForm().findField('diplomas').getChecked()[j].inputValue;
                        } else {
                            values.diplomaIdComma += form.getForm().findField('diplomas').getChecked()[j].inputValue + ",";
                        }
                    }
                } else {
                    values.diplomaIdComma="";
                }

                /*if(values.diplomaIdComma!="[]"){
                    values.diplomaIdComma=Ext.util.JSON.encode(values.diplomaIdComma);
                    values.diplomaIdComma=values.diplomaIdComma.substring(1, values.diplomaIdComma.length-1);
                    values.diplomaIdComma=values.diplomaIdComma.replace(/["']/g, "");
                }

                if(values.diplomaIdComma==="ul"){
                    values.diplomaIdComma="";
                }*/

                if(values.speciality==="ul"){
                    values.speciality="";
                }

                values.speciality=Ext.util.JSON.encode(values.speciality);
                values.speciality=values.speciality.substring(1, values.speciality.length-1);

                values.subStatus=1;

                var fileurl = 'setDocument';


                if(values.attachedDocIdEmplTraining!=="-1" && values.deletedfile2==="true" && Ext.getCmp("attachedDocIdEmplTraining").getForm().findField("file").getValue()==="")
                values.attachedDocIdEmplTraining="-1";

                if(values.attachedDocId!=="-1" && values.deletedfile==="true" && Ext.getCmp("docfile2").getForm().findField("file").getValue()==="")
                values.attachedDocId="-1";

                if(Ext.getCmp("attachedDocIdEmplTrainingTA").getForm().findField("file").getValue()!==""){
                    if(values.attachedDocIdEmplTraining!=="-1" && Ext.getCmp("attachedDocIdEmplTrainingTA").getForm().findField("file").getValue()!=="")
                    fileurl = '/setDocument' + "?docId=" + values.attachedDocIdEmplTraining;
                    else
                    fileurl = '/setDocument' ;
                    Ext.getCmp("attachedDocIdEmplTrainingTA").submit({
                        url: fileurl,
                        waitMsg: 'Καταχώρηση αρχείου...',
                        success: function(form, action) {
                            values.attachedDocIdEmplTraining=parseInt(action.result.fileId);
                            if (Ext.getCmp("docfile2").getForm().findField("file").getValue()!==""){
                                if(values.attachedDocId!=="-1" && Ext.getCmp("docfile2").getForm().findField("file").getValue()!=="")
                                fileurl = '/setDocument' + "?docId=" + values.attachedDocId;
                                else
                                fileurl = '/setDocument' ;
                                Ext.getCmp("docfile2").submit({
                                    url: fileurl,
                                    waitMsg: 'Καταχώρηση αρχείου...',
                                    success: function(form, action) {
                                        values.attachedDocId=parseInt(action.result.fileId);
                                        delete values.file;
                                        Ext.Ajax.request({
                                            url: "/technicianRegrequest/" + fid,
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

                                    } });
                                }
                                else{
                                    delete values.file;
                                    Ext.Ajax.request({
                                        url: "/technicianRegrequest/" + fid,
                                        headers: { 'Content-Type': 'application/json' },
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

                            } });

                        }
                        else{
                            if (Ext.getCmp("docfile2").getForm().findField("file").getValue()!==""){
                                if(values.attachedDocId!=="-1" && Ext.getCmp("docfile2").getForm().findField("file").getValue()!=="")
                                fileurl = '/setDocument' + "?docId=" + values.attachedDocId;
                                else
                                fileurl = '/setDocument' ;
                                Ext.getCmp("docfile2").submit({
                                    url: fileurl,
                                    waitMsg: 'Καταχώρηση αρχείου...',
                                    success: function(form, action) {
                                        values.attachedDocId=parseInt(action.result.fileId);

                                        delete values.file;
                                        Ext.Ajax.request({
                                            url: "/technicianRegrequest/" + fid,
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

                                    } });
                                }
                                else{

                                    delete values.file;
                                    Ext.Ajax.request({
                                        url: "/technicianRegrequest/" + fid,
                                        headers: { 'Content-Type': 'application/json' },
                                        jsonData: Ext.util.JSON.encode(values),
                                        method: rest_method,
                                        success: successCallback,
                                        failure: failureCallback
                                    });
                                }


                            }



                        }
                        else{
                            Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Παρακαλώ διορθώστε τα λάθος πεδία');

                        }
                    };

    },

    onSubmit_TECHNICIAN_REGREQUEST: function(button, e, eOpts) {
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

            var file = Ext.getCmp("attachedDocIdEmplTrainingTA").getForm().findField("file");
            if (file.getValue() =='') {
                file.setValidation(false);
            }

            form.getForm().findField("speciality").validate();
            if (form.isValid() && form.getForm().findField("speciality").isValid() && (Ext.getCmp("attachedDocIdEmplTrainingTA").getForm().isValid() || file.disabled == false)) {
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

                values.protNo="";
                values.protDate=button.up('toolbar').getCurrentTimestamp(1);
                values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
                values.protYear=button.up('toolbar').getCurrentTimestamp(3);

                values.diplomaIdComma="";
                var checkedboxes = form.getForm().findField('diplomas').getChecked().length;
                if(checkedboxes>0){
                    for (var j = 0; j < checkedboxes; j++) {
                        if (j==checkedboxes-1) {
                            values.diplomaIdComma += form.getForm().findField('diplomas').getChecked()[j].inputValue;
                        } else {
                            values.diplomaIdComma += form.getForm().findField('diplomas').getChecked()[j].inputValue + ",";
                        }
                    }
                } else {
                    values.diplomaIdComma="";
                }

                /*if(values.diplomaIdComma!="[]"){
                    values.diplomaIdComma=Ext.util.JSON.encode(values.diplomaIdComma);
                    values.diplomaIdComma=values.diplomaIdComma.substring(1, values.diplomaIdComma.length-1);
                    values.diplomaIdComma=values.diplomaIdComma.replace(/["']/g, "");
                }

                if(values.diplomaIdComma==="ul"){
                    values.diplomaIdComma="";
                }*/

                if(values.speciality==="ul"){
                    values.speciality="";
                }

                values.speciality=Ext.util.JSON.encode(values.speciality);
                values.speciality=values.speciality.substring(1, values.speciality.length-1);

                values.reqStatus=5;
                values.subStatus=2;

                var fileurl = 'setDocument';


                if(values.attachedDocIdEmplTraining!=="-1" && values.deletedfile2==="true" && Ext.getCmp("attachedDocIdEmplTraining").getForm().findField("file").getValue()==="")
                values.attachedDocIdEmplTraining="-1";

                if(values.attachedDocId!=="-1" && values.deletedfile==="true" && Ext.getCmp("docfile2").getForm().findField("file").getValue()==="")
                values.attachedDocId="-1";

                if(Ext.getCmp("attachedDocIdEmplTrainingTA").getForm().findField("file").getValue()!==""){
                    if(values.attachedDocIdEmplTraining!=="-1" && Ext.getCmp("attachedDocIdEmplTrainingTA").getForm().findField("file").getValue()!=="")
                    fileurl = '/setDocument' + "?docId=" + values.attachedDocIdEmplTraining;
                    else
                    fileurl = '/setDocument' ;
                    Ext.getCmp("attachedDocIdEmplTrainingTA").submit({
                        url: fileurl,
                        waitMsg: 'Καταχώρηση αρχείου...',
                        success: function(form, action) {
                            values.attachedDocIdEmplTraining=parseInt(action.result.fileId);
                            if (Ext.getCmp("docfile2").getForm().findField("file").getValue()!==""){
                                if(values.attachedDocId!=="-1" && Ext.getCmp("docfile2").getForm().findField("file").getValue()!=="")
                                fileurl = '/setDocument' + "?docId=" + values.attachedDocId;
                                else
                                fileurl = '/setDocument' ;
                                Ext.getCmp("docfile2").submit({
                                    url: fileurl,
                                    waitMsg: 'Καταχώρηση αρχείου...',
                                    success: function(form, action) {
                                        values.attachedDocId=parseInt(action.result.fileId);
                                        delete values.file;
                                        Ext.Ajax.request({
                                            url: "/technicianRegrequest/" + fid,
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

                                    } });
                                }
                                else{
                                    delete values.file;
                                    Ext.Ajax.request({
                                        url: "/technicianRegrequest/" + fid,
                                        headers: { 'Content-Type': 'application/json' },
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

                            } });

                        }
                        else{
                            if (Ext.getCmp("docfile2").getForm().findField("file").getValue()!==""){
                                if(values.attachedDocId!=="-1" && Ext.getCmp("docfile2").getForm().findField("file").getValue()!=="")
                                fileurl = '/setDocument' + "?docId=" + values.attachedDocId;
                                else
                                fileurl = '/setDocument' ;
                                Ext.getCmp("docfile2").submit({
                                    url: fileurl,
                                    waitMsg: 'Καταχώρηση αρχείου...',
                                    success: function(form, action) {
                                        values.attachedDocId=parseInt(action.result.fileId);

                                        delete values.file;
                                        Ext.Ajax.request({
                                            url: "/technicianRegrequest/" + fid,
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

                                    } });
                                }
                                else{

                                    delete values.file;
                                    Ext.Ajax.request({
                                        url: "/technicianRegrequest/" + fid,
                                        headers: { 'Content-Type': 'application/json' },
                                        jsonData: Ext.util.JSON.encode(values),
                                        method: rest_method,
                                        success: successCallback,
                                        failure: failureCallback
                                    });
                                }


                            }



                        }
                        else{
                            Ext.Msg.alert('Αποτυχία Υποβολής', 'Παρακαλώ διορθώστε τα λάθος πεδία');

                        }
                    };


    }

});
