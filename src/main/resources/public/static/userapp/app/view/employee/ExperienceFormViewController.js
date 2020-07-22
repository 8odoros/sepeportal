/*
 * File: app/view/employee/ExperienceFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.employee.ExperienceFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.employeeexperienceform',

    onDelete_EMPLOYEE_EXPERIENCE: function(button, e, eOpts) {

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
                        url: "/spPtlEmployeeExperiences/" + fid,
                        headers: { 'Content-Type': 'application/json' },
                        method: rest_method,
                        success: successCallback,
                        failure: failureCallback
                    });
                }
                else
                {
                    Ext.Msg.alert('Αποτυχία', 'Η φόρμα δεν έπρεπε να φθάσει σε αυτή την κατάσταση');
                }
            }
            if (buttonText == "no") {
            }
        };

        var msb = Ext.MessageBox.confirm('Διαγραφή φόρμας', 'Είστε σίγουροι ότι θέλετε να διαγράψετε τη φόρμα;', conffun);
    },

    onSave_EMPLOYEE_EXPERIENCE: function(button, e, eOpts) {

        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formall=button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window');
        var values = form.getValues();
        var validdates = true;
        form.getForm().clearInvalid();

        formWindow.mask("Παρακαλώ Περιμένετε...");
        var validafm=true;

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
            Ext.Msg.alert('Επιτυχής Αποθήκευση', 'Η αίτηση αποθηκεύτηκε με επιτυχία');

            // Close window
            formWindow.destroy();

        };

        // Failure
        var failureCallback = function(resp, ops) {
            formWindow.unmask();
            Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Η αίτηση δεν αποθηκεύτηκε.');

        };

        if (form.getForm().findField("compName").getValue() != "")
            form.getForm().findField("compName").validate();
        if (form.getForm().findField("intentedUse").getValue() != null)
            form.getForm().findField("intentedUse").validate();
        if (form.getForm().findField('empFromDate').getValue() != null)
            form.getForm().findField('empFromDate').validate();
        if (form.getForm().findField('empUntilDate').getValue() != null)
            form.getForm().findField('empUntilDate').validate();
        if((form.getForm().findField("compName").isValid() || form.getForm().findField("compName").getValue() == "") && validafm && (form.getForm().findField("intentedUse").isValid() || form.getForm().findField("intentedUse").getValue() == null) && (form.getForm().findField('empFromDate').isValid() || form.getForm().findField('empFromDate').getValue() == null) && (form.getForm().findField('empUntilDate').isValid() || form.getForm().findField('empUntilDate').getValue() == null)){

            if (!(form.getForm().findField('empFromDate').getValue() == null || form.getForm().findField('empUntilDate').getValue() == null))
            {
                if (!button.up('toolbar').validateDatesDifference(form.getForm().findField('empFromDate'),form.getForm().findField('empUntilDate')))
                validdates = false;
            }

            if (validdates){
                var fileIds = button.getFilesIds(form);

                if(values.attachedDocIdEmplVer!=="" && values.deletedfile1==="true" && Ext.getCmp("complfile1").getForm().findField("file").getValue()==="")
                values.attachedDocIdEmplVer="";
                if(values.attachedDocIdIka!=="" && values.deletedfile2==="true" && Ext.getCmp("complfile2").getForm().findField("file").getValue()==="")
                values.attachedDocIdIka="";
                if(values.attachedDocIdSepe!=="" && values.deletedfile3==="true" && Ext.getCmp("complfile3").getForm().findField("file").getValue()==="")
                values.attachedDocIdSepe="";
                if(values.attachedDocIdJudgmnt!=="" && values.deletedfile4==="true" && Ext.getCmp("complfile4").getForm().findField("file").getValue()==="")
                values.attachedDocIdJudgmnt="";

                var fileurl = '/setDocument';


                switch (fileIds.length) {
                    case 0:
                    values.subStatus=1;
                    values.protDate=button.up('toolbar').getCurrentTimestamp(1);
                    values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
                    values.protYear=button.up('toolbar').getCurrentTimestamp(3);
                    values.empFromDate=button.up('toolbar').dateToTimestamp(values.empFromDate);
                    values.empUntilDate=button.up('toolbar').dateToTimestamp(values.empUntilDate);
                        if (values.empFromDate == "undefined-undefined-T00:00:00.000+0000") values.empFromDate = null;
                        if (values.empUntilDate == "undefined-undefined-T00:00:00.000+0000") values.empUntilDate = null;
                    Ext.Ajax.request({
                        url: "/spPtlEmployeeExperiences/" + fid,
                        headers: { 'Content-Type': 'application/json' },
                        jsonData: Ext.util.JSON.encode(values),
                        method: rest_method,
                        success: successCallback,
                        failure: failureCallback
                    });
                    break;
                    case 1:
                    if(fileIds[0].next().query("#fid")[0].getValue()!=="")
                    fileurl = "/setDocument" + "?docId=" + fileIds[0].next().query("#fid")[0].getValue();
                    fileIds[0].submit({
                        url: fileurl,
                        waitMsg: 'Αποθήκευση αρχείου...',
                        success: function(form, action) {
                            fileIds[0].next().query("#fid")[0].setValue(parseInt(action.result.fileId));
                            values = formall.getValues();
                            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
                            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
                            values.protYear=button.up('toolbar').getCurrentTimestamp(3);
                            values.empFromDate=button.up('toolbar').dateToTimestamp(values.empFromDate);
                            values.empUntilDate=button.up('toolbar').dateToTimestamp(values.empUntilDate);
                            if (values.empFromDate == "undefined-undefined-T00:00:00.000+0000") values.empFromDate = null;
                            if (values.empUntilDate == "undefined-undefined-T00:00:00.000+0000") values.empUntilDate = null;
                            values.subStatus=1;
                            Ext.Ajax.request({
                                url: "/spPtlEmployeeExperiences/" + fid,
                                headers: { 'Content-Type': 'application/json' },
                                jsonData: Ext.util.JSON.encode(values),
                                method: rest_method,
                                success: successCallback,
                                failure: failureCallback
                            });
                        },
                        failure: function(form, action) {
                            form.findField("file").focus();
                            formWindow.unmask();
                            Ext.Msg.alert('Αποτυχία Αποθήκευσης Αρχείου', action.result.error);

                        }
                    });

                    break;
                    case 2:
                    if(fileIds[0].next().query("#fid")[0].getValue()!=="")
                    fileurl = "/setDocument" + "?docId=" + fileIds[0].next().query("#fid")[0].getValue();
                    fileIds[0].submit({
                        url: fileurl,
                        waitMsg: 'Αποθήκευση αρχείου...',
                        success: function(form, action) {
                            fileIds[0].next().query("#fid")[0].setValue(parseInt(action.result.fileId));
                            if(fileIds[1].next().query("#fid")[0].getValue()!=="")
                            fileurl = "/setDocument" + "?docId=" + fileIds[1].next().query("#fid")[0].getValue();
                            fileIds[1].submit({
                                url: fileurl,
                                waitMsg: 'Αποθήκευση αρχείου...',
                                success: function(form, action) {
                                    fileIds[1].next().query("#fid")[0].setValue(parseInt(action.result.fileId));
                                    values = formall.getValues();
                                    values.protDate=button.up('toolbar').getCurrentTimestamp(1);
                                    values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
                                    values.protYear=button.up('toolbar').getCurrentTimestamp(3);
                                    values.empFromDate=button.up('toolbar').dateToTimestamp(values.empFromDate);
                                    values.empUntilDate=button.up('toolbar').dateToTimestamp(values.empUntilDate);
                                    if (values.empFromDate == "undefined-undefined-T00:00:00.000+0000") values.empFromDate = null;
                                    if (values.empUntilDate == "undefined-undefined-T00:00:00.000+0000") values.empUntilDate = null;
                                    values.subStatus=1;
                                    Ext.Ajax.request({
                                        url: "/spPtlEmployeeExperiences/" + fid,
                                        headers: { 'Content-Type': 'application/json' },
                                        jsonData: Ext.util.JSON.encode(values),
                                        method: rest_method,
                                        success: successCallback,
                                        failure: failureCallback
                                    });
                                },
                                failure: function(form, action) {
                                    form.findField("file").focus();
                                    formWindow.unmask();
                                    Ext.Msg.alert('Αποτυχία Αποθήκευσης Αρχείου', action.result.error);

                                }
                            });
                        },
                        failure: function(form, action) {
                            form.findField("file").focus();
                            formWindow.unmask();
                            Ext.Msg.alert('Αποτυχία Αποθήκευσης Αρχείου', action.result.error);

                        }
                    });

                    break;
                    case 3:
                    if(fileIds[0].next().query("#fid")[0].getValue()!=="")
                    fileurl = "/setDocument" + "?docId=" + fileIds[0].next().query("#fid")[0].getValue();
                    fileIds[0].submit({
                        url: fileurl,
                        waitMsg: 'Αποθήκευση αρχείου...',
                        success: function(form, action) {
                            fileIds[0].next().query("#fid")[0].setValue(parseInt(action.result.fileId));
                            if(fileIds[1].next().query("#fid")[0].getValue()!=="")
                            fileurl = "/setDocument" + "?docId=" + fileIds[1].next().query("#fid")[0].getValue();
                            fileIds[1].submit({
                                url: fileurl,
                                waitMsg: 'Αποθήκευση αρχείου...',
                                success: function(form, action) {
                                    fileIds[1].next().query("#fid")[0].setValue(parseInt(action.result.fileId));
                                    if(fileIds[2].next().query("#fid")[0].getValue()!=="")
                                    fileurl = "/setDocument" + "?docId=" + fileIds[2].next().query("#fid")[0].getValue();
                                    fileIds[2].submit({
                                        url: fileurl,
                                        waitMsg: 'Αποθήκευση αρχείου...',
                                        success: function(form, action) {
                                            fileIds[2].next().query("#fid")[0].setValue(parseInt(action.result.fileId));
                                            values = formall.getValues();
                                            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
                                            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
                                            values.protYear=button.up('toolbar').getCurrentTimestamp(3);
                                            values.empFromDate=button.up('toolbar').dateToTimestamp(values.empFromDate);
                                            values.empUntilDate=button.up('toolbar').dateToTimestamp(values.empUntilDate);
                                            if (values.empFromDate == "undefined-undefined-T00:00:00.000+0000") values.empFromDate = null;
                                            if (values.empUntilDate == "undefined-undefined-T00:00:00.000+0000") values.empUntilDate = null;
                                            values.subStatus=1;
                                            Ext.Ajax.request({
                                                url: "/spPtlEmployeeExperiences/" + fid,
                                                headers: { 'Content-Type': 'application/json' },
                                                jsonData: Ext.util.JSON.encode(values),
                                                method: rest_method,
                                                success: successCallback,
                                                failure: failureCallback
                                            });
                                        },
                                        failure: function(form, action) {
                                            form.findField("file").focus();
                                            formWindow.unmask();
                                            Ext.Msg.alert('Αποτυχία Αποθήκευσης Αρχείου', action.result.error);

                                        }
                                    });
                                },
                                failure: function(form, action) {
                                    form.findField("file").focus();
                                    formWindow.unmask();
                                    Ext.Msg.alert('Αποτυχία Αποθήκευσης Αρχείου', action.result.error);

                                }
                            });
                        },
                        failure: function(form, action) {
                            form.findField("file").focus();
                            formWindow.unmask();
                            Ext.Msg.alert('Αποτυχία Αποθήκευσης Αρχείου', action.result.error);

                        }
                    });


                    break;
                    case 4:
                    if(fileIds[0].next().query("#fid")[0].getValue()!=="")
                    fileurl = "/setDocument" + "?docId=" + fileIds[0].next().query("#fid")[0].getValue();
                    fileIds[0].submit({
                        url: fileurl,
                        waitMsg: 'Αποθήκευση αρχείου...',
                        success: function(form, action) {
                            fileIds[0].next().query("#fid")[0].setValue(parseInt(action.result.fileId));
                            if(fileIds[1].next().query("#fid")[0].getValue()!=="")
                            fileurl = "/setDocument" + "?docId=" + fileIds[1].next().query("#fid")[0].getValue();
                            fileIds[1].submit({
                                url: fileurl,
                                waitMsg: 'Αποθήκευση αρχείου...',
                                success: function(form, action) {
                                    fileIds[1].next().query("#fid")[0].setValue(parseInt(action.result.fileId));
                                    if(fileIds[2].next().query("#fid")[0].getValue()!=="")
                                    fileurl = "/setDocument" + "?docId=" + fileIds[2].next().query("#fid")[0].getValue();
                                    fileIds[2].submit({
                                        url: fileurl,
                                        waitMsg: 'Αποθήκευση αρχείου...',
                                        success: function(form, action) {
                                            fileIds[2].next().query("#fid")[0].setValue(parseInt(action.result.fileId));
                                            if(fileIds[3].next().query("#fid")[0].getValue()!=="")
                                            fileurl = "/setDocument" + "?docId=" + fileIds[3].next().query("#fid")[0].getValue();
                                            fileIds[3].submit({
                                                url: fileurl,
                                                waitMsg: 'Αποθήκευση αρχείου...',
                                                success: function(form, action) {
                                                    fileIds[3].next().query("#fid")[0].setValue(parseInt(action.result.fileId));
                                                    values = formall.getValues();
                                                    values.protDate=button.up('toolbar').getCurrentTimestamp(1);
                                                    values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
                                                    values.protYear=button.up('toolbar').getCurrentTimestamp(3);
                                                    values.empFromDate=button.up('toolbar').dateToTimestamp(values.empFromDate);
                                                    values.empUntilDate=button.up('toolbar').dateToTimestamp(values.empUntilDate);
                                                    if (values.empFromDate == "undefined-undefined-T00:00:00.000+0000") values.empFromDate = null;
                                                    if (values.empUntilDate == "undefined-undefined-T00:00:00.000+0000") values.empUntilDate = null;
                                                    values.subStatus=1;
                                                    Ext.Ajax.request({
                                                        url: "/spPtlEmployeeExperiences/" + fid,
                                                        headers: { 'Content-Type': 'application/json' },
                                                        jsonData: Ext.util.JSON.encode(values),
                                                        method: rest_method,
                                                        success: successCallback,
                                                        failure: failureCallback
                                                    });
                                                },
                                                failure: function(form, action) {
                                                    form.findField("file").focus();
                                                    formWindow.unmask();
                                                    Ext.Msg.alert('Αποτυχία Αποθήκευσης Αρχείου', action.result.error);

                                                }
                                            });
                                        },
                                        failure: function(form, action) {
                                            form.findField("file").focus();
                                            formWindow.unmask();
                                            Ext.Msg.alert('Αποτυχία Αποθήκευσης Αρχείου', action.result.error);

                                        }
                                    });
                                },
                                failure: function(form, action) {
                                    form.findField("file").focus();
                                    formWindow.unmask();
                                    Ext.Msg.alert('Αποτυχία Αποθήκευσης Αρχείου', action.result.error);

                                }
                            });
                        },
                        failure: function(form, action) {
                            form.findField("file").focus();
                            formWindow.unmask();
                            Ext.Msg.alert('Αποτυχία Αποθήκευσης Αρχείου', action.result.error);

                        }
                    });

                    break;
                }
            }
            else{
                formWindow.unmask();
                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Παρακαλώ διορθώστε τα λάθος πεδία');
            }
        }
        else{
            formWindow.unmask();
            Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Παρακαλώ συμπληρώστε τα απαραίτητα πεδία για αποθήκευση');

        }
    },

    onSubmit_EMPLOYEE_EXPERIENCE: function(button, e, eOpts) {

        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formall=button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window');
        var values = form.getValues();
        var validdates = true;
        form.getForm().clearInvalid();

        formWindow.mask("Παρακαλώ Περιμένετε...");
        var validafm=true;
        if (form.getForm().findField('compAfm').getValue().length===0){
            validafm=false;
        }

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
            Ext.Msg.alert('Επιτυχής Υποβολής', 'Η αίτηση υποβλήθηκε με επιτυχία');

            // Close window
            formWindow.destroy();

        };

        // Failure
        var failureCallback = function(resp, ops) {
            formWindow.unmask();
            Ext.Msg.alert('Αποτυχία Υποβολής', 'Η αίτηση δεν υποβλήθηκε.');

        };

        if (values.file[0]=='') {
            Ext.getCmp("complfile1").items.items[0].setValidation(false);
        }
        if (values.file[1]=='') {
            Ext.getCmp("complfile2").items.items[0].setValidation(false);
        }

        if (form.isValid() && validafm && Ext.getCmp("complfile1").getForm().isValid() && Ext.getCmp("complfile2").getForm().isValid()) {

            if (!button.up('toolbar').validateDatesDifference(form.getForm().findField('empFromDate'),form.getForm().findField('empUntilDate')))
            validdates = false;

            if (validdates){
                var fileIds = button.getFilesIds(form);

                if(values.attachedDocIdEmplVer!=="" && values.deletedfile1==="true" && Ext.getCmp("complfile1").getForm().findField("file").getValue()==="")
                values.attachedDocIdEmplVer="";
                if(values.attachedDocIdIka!=="" && values.deletedfile2==="true" && Ext.getCmp("complfile2").getForm().findField("file").getValue()==="")
                values.attachedDocIdIka="";
                if(values.attachedDocIdSepe!=="" && values.deletedfile3==="true" && Ext.getCmp("complfile3").getForm().findField("file").getValue()==="")
                values.attachedDocIdSepe="";
                if(values.attachedDocIdJudgmnt!=="" && values.deletedfile4==="true" && Ext.getCmp("complfile4").getForm().findField("file").getValue()==="")
                values.attachedDocIdJudgmnt="";

                var fileurl = '/setDocument';


                switch (fileIds.length) {
                    case 0:
                    values.subStatus=2;
                    values.reqStatus=1;
                    values.protDate=button.up('toolbar').getCurrentTimestamp(1);
                    values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
                    values.protYear=button.up('toolbar').getCurrentTimestamp(3);
                    values.empFromDate=button.up('toolbar').dateToTimestamp(values.empFromDate);
                    values.empUntilDate=button.up('toolbar').dateToTimestamp(values.empUntilDate);
                    Ext.Ajax.request({
                        url: "/spPtlEmployeeExperiences/" + fid,
                        headers: { 'Content-Type': 'application/json' },
                        jsonData: Ext.util.JSON.encode(values),
                        method: rest_method,
                        success: successCallback,
                        failure: failureCallback
                    });
                    break;
                    case 1:
                    if(fileIds[0].next().query("#fid")[0].getValue()!=="")
                    fileurl = "/setDocument" + "?docId=" + fileIds[0].next().query("#fid")[0].getValue();
                    fileIds[0].submit({
                        url: fileurl,
                        waitMsg: 'Αποθήκευση αρχείου...',
                        success: function(form, action) {
                            fileIds[0].next().query("#fid")[0].setValue(parseInt(action.result.fileId));
                            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
                            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
                            values.protYear=button.up('toolbar').getCurrentTimestamp(3);
                            values.empFromDate=button.up('toolbar').dateToTimestamp(values.empFromDate);
                            values.empUntilDate=button.up('toolbar').dateToTimestamp(values.empUntilDate);
                            values = formall.getValues();
                            values.subStatus=2;
                            values.reqStatus=1;
                            Ext.Ajax.request({
                                url: "/spPtlEmployeeExperiences/" + fid,
                                headers: { 'Content-Type': 'application/json' },
                                jsonData: Ext.util.JSON.encode(values),
                                method: rest_method,
                                success: successCallback,
                                failure: failureCallback
                            });
                        },
                        failure: function(form, action) {
                            form.findField("file").focus();
                            formWindow.unmask();
                            Ext.Msg.alert('Αποτυχία Υποβολής Αρχείου', action.result.error);

                        }
                    });

                    break;
                    case 2:
                    if(fileIds[0].next().query("#fid")[0].getValue()!=="")
                    fileurl = "/setDocument" + "?docId=" + fileIds[0].next().query("#fid")[0].getValue();
                    fileIds[0].submit({
                        url: fileurl,
                        waitMsg: 'Αποθήκευση αρχείου...',
                        success: function(form, action) {
                            fileIds[0].next().query("#fid")[0].setValue(parseInt(action.result.fileId));
                            if(fileIds[1].next().query("#fid")[0].getValue()!=="")
                            fileurl = "/setDocument" + "?docId=" + fileIds[1].next().query("#fid")[0].getValue();
                            fileIds[1].submit({
                                url: fileurl,
                                waitMsg: 'Αποθήκευση αρχείου...',
                                success: function(form, action) {
                                    fileIds[1].next().query("#fid")[0].setValue(parseInt(action.result.fileId));
                                    values = formall.getValues();
                                    values.protDate=button.up('toolbar').getCurrentTimestamp(1);
                                    values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
                                    values.protYear=button.up('toolbar').getCurrentTimestamp(3);
                                    values.empFromDate=button.up('toolbar').dateToTimestamp(values.empFromDate);
                                    values.empUntilDate=button.up('toolbar').dateToTimestamp(values.empUntilDate);
                                    values.subStatus=2;
                                    values.reqStatus=1;
                                    Ext.Ajax.request({
                                        url: "/spPtlEmployeeExperiences/" + fid,
                                        headers: { 'Content-Type': 'application/json' },
                                        jsonData: Ext.util.JSON.encode(values),
                                        method: rest_method,
                                        success: successCallback,
                                        failure: failureCallback
                                    });
                                },
                                failure: function(form, action) {
                                    form.findField("file").focus();
                                    formWindow.unmask();
                                    Ext.Msg.alert('Αποτυχία Υποβολής Αρχείου', action.result.error);

                                }
                            });
                        },
                        failure: function(form, action) {
                            form.findField("file").focus();
                            formWindow.unmask();
                            Ext.Msg.alert('Αποτυχία Υποβολής Αρχείου', action.result.error);

                        }
                    });

                    break;
                    case 3:
                    if(fileIds[0].next().query("#fid")[0].getValue()!=="")
                    fileurl = "/setDocument" + "?docId=" + fileIds[0].next().query("#fid")[0].getValue();
                    fileIds[0].submit({
                        url: fileurl,
                        waitMsg: 'Αποθήκευση αρχείου...',
                        success: function(form, action) {
                            fileIds[0].next().query("#fid")[0].setValue(parseInt(action.result.fileId));
                            if(fileIds[1].next().query("#fid")[0].getValue()!=="")
                            fileurl = "/setDocument" + "?docId=" + fileIds[1].next().query("#fid")[0].getValue();
                            fileIds[1].submit({
                                url: fileurl,
                                waitMsg: 'Αποθήκευση αρχείου...',
                                success: function(form, action) {
                                    fileIds[1].next().query("#fid")[0].setValue(parseInt(action.result.fileId));
                                    if(fileIds[2].next().query("#fid")[0].getValue()!=="")
                                    fileurl = "/setDocument" + "?docId=" + fileIds[2].next().query("#fid")[0].getValue();
                                    fileIds[2].submit({
                                        url: fileurl,
                                        waitMsg: 'Αποθήκευση αρχείου...',
                                        success: function(form, action) {
                                            fileIds[2].next().query("#fid")[0].setValue(parseInt(action.result.fileId));
                                            values = formall.getValues();
                                            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
                                            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
                                            values.protYear=button.up('toolbar').getCurrentTimestamp(3);
                                            values.empFromDate=button.up('toolbar').dateToTimestamp(values.empFromDate);
                                            values.empUntilDate=button.up('toolbar').dateToTimestamp(values.empUntilDate);
                                            values.subStatus=2;
                                            values.reqStatus=1;
                                            Ext.Ajax.request({
                                                url: "/spPtlEmployeeExperiences/" + fid,
                                                headers: { 'Content-Type': 'application/json' },
                                                jsonData: Ext.util.JSON.encode(values),
                                                method: rest_method,
                                                success: successCallback,
                                                failure: failureCallback
                                            });
                                        },
                                        failure: function(form, action) {
                                            form.findField("file").focus();
                                            formWindow.unmask();
                                            Ext.Msg.alert('Αποτυχία Υποβολής Αρχείου', action.result.error);

                                        }
                                    });
                                },
                                failure: function(form, action) {
                                    form.findField("file").focus();
                                    formWindow.unmask();
                                    Ext.Msg.alert('Αποτυχία Υποβολής Αρχείου', action.result.error);

                                }
                            });
                        },
                        failure: function(form, action) {
                            form.findField("file").focus();
                            formWindow.unmask();
                            Ext.Msg.alert('Αποτυχία Υποβολής Αρχείου', action.result.error);

                        }
                    });


                    break;
                    case 4:
                    if(fileIds[0].next().query("#fid")[0].getValue()!=="")
                    fileurl = "/setDocument" + "?docId=" + fileIds[0].next().query("#fid")[0].getValue();
                    fileIds[0].submit({
                        url: fileurl,
                        waitMsg: 'Αποθήκευση αρχείου...',
                        success: function(form, action) {
                            fileIds[0].next().query("#fid")[0].setValue(parseInt(action.result.fileId));
                            if(fileIds[1].next().query("#fid")[0].getValue()!=="")
                            fileurl = "/setDocument" + "?docId=" + fileIds[1].next().query("#fid")[0].getValue();
                            fileIds[1].submit({
                                url: fileurl,
                                waitMsg: 'Αποθήκευση αρχείου...',
                                success: function(form, action) {
                                    fileIds[1].next().query("#fid")[0].setValue(parseInt(action.result.fileId));
                                    if(fileIds[2].next().query("#fid")[0].getValue()!=="")
                                    fileurl = "/setDocument" + "?docId=" + fileIds[2].next().query("#fid")[0].getValue();
                                    fileIds[2].submit({
                                        url: fileurl,
                                        waitMsg: 'Αποθήκευση αρχείου...',
                                        success: function(form, action) {
                                            fileIds[2].next().query("#fid")[0].setValue(parseInt(action.result.fileId));
                                            if(fileIds[3].next().query("#fid")[0].getValue()!=="")
                                            fileurl = "/setDocument" + "?docId=" + fileIds[3].next().query("#fid")[0].getValue();
                                            fileIds[3].submit({
                                                url: fileurl,
                                                waitMsg: 'Αποθήκευση αρχείου...',
                                                success: function(form, action) {
                                                    fileIds[3].next().query("#fid")[0].setValue(parseInt(action.result.fileId));
                                                    values = formall.getValues();
                                                    values.protDate=button.up('toolbar').getCurrentTimestamp(1);
                                                    values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
                                                    values.protYear=button.up('toolbar').getCurrentTimestamp(3);
                                                    values.empFromDate=button.up('toolbar').dateToTimestamp(values.empFromDate);
                                                    values.empUntilDate=button.up('toolbar').dateToTimestamp(values.empUntilDate);
                                                    values.subStatus=2;
                                                    values.reqStatus=1;
                                                    Ext.Ajax.request({
                                                        url: "/spPtlEmployeeExperiences/" + fid,
                                                        headers: { 'Content-Type': 'application/json' },
                                                        jsonData: Ext.util.JSON.encode(values),
                                                        method: rest_method,
                                                        success: successCallback,
                                                        failure: failureCallback
                                                    });
                                                },
                                                failure: function(form, action) {
                                                    form.findField("file").focus();
                                                    formWindow.unmask();
                                                    Ext.Msg.alert('Αποτυχία Υποβολής Αρχείου', action.result.error);

                                                }
                                            });
                                        },
                                        failure: function(form, action) {
                                            form.findField("file").focus();
                                            formWindow.unmask();
                                            Ext.Msg.alert('Αποτυχία Υποβολής Αρχείου', action.result.error);

                                        }
                                    });
                                },
                                failure: function(form, action) {
                                    form.findField("file").focus();
                                    formWindow.unmask();
                                    Ext.Msg.alert('Αποτυχία Υποβολής Αρχείου', action.result.error);

                                }
                            });
                        },
                        failure: function(form, action) {
                            form.findField("file").focus();
                            formWindow.unmask();
                            Ext.Msg.alert('Αποτυχία Υποβολής Αρχείου', action.result.error);

                        }
                    });

                    break;
                }
            }
            else{
                formWindow.unmask();
                Ext.Msg.alert('Αποτυχία Υποβολής', 'Παρακαλώ διορθώστε τα λάθος πεδία');
            }
        }
        else{
            formWindow.unmask();
            Ext.Msg.alert('Αποτυχία Υποβολής', 'Παρακαλώ συμπληρώστε τα απαραίτητα πεδία για υποβολή');

        }
    }

});
