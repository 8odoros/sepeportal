/*
 * File: app/view/employee/DisputeFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.employee.DisputeFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.employeedisputeform',

    onDelete_EMPLOYEE_DISPUTE: function(button, e, eOpts) {

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
                        url: "/tEmployeeDisputes/" + fid,
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

    onSave_EMPLOYER_DISPUTE: function(button, e, eOpts) {
        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        form.getForm().clearInvalid();
        formWindow.mask("Παρακαλώ Περιμένετε...");
        var validafm=true;
        if (form.getForm().findField('empFromDate').getValue() != null)
            form.getForm().findField('empFromDate').validate();
        if (form.getForm().findField('empUntilDate').getValue() != null)
            form.getForm().findField('empUntilDate').validate();

        if (form.getForm().findField('compAfm').getValue().length>0){
            if (!form.getForm().findField('branch1Id').isValid())
                validafm=false;
            values.branch0Id=parseInt(values.branch0Id);

        }
        if((form.getForm().findField("compName").getValue() == "" || form.getForm().findField("compName").isValid()) && validafm && (form.getForm().findField('empFromDate').getValue() == null || form.getForm().findField('empFromDate').isValid()) && (form.getForm().findField('empUntilDate').getValue() == null || form.getForm().findField('empUntilDate').isValid())){

            var valid = true;
            var validdates = true;
            var checkedboxes = form.down("checkboxgroup").getChecked().length;
            if(checkedboxes>1){
                for (var j = 0; j < checkedboxes; j++) {

                    var cbc = form.getForm().findField(values.reasonId[j].toString()).up('fieldcontainer');
                    if (cbc.down().down('container').getComponent('id_dateStart').getValue() != null)
                    {
                        if(!cbc.down().down('container').getComponent('id_dateStart').isValid()){
                            valid=false;
                        }
                    }
                    if (cbc.down().down('container').getComponent('id_dateEnd').getValue() != null)
                    {
                        if(!cbc.down().down('container').getComponent('id_dateEnd').isValid()){
                            valid=false;
                        }
                    }
                }
            }
            else if (checkedboxes == 1){
                var cbc = form.getForm().findField(values.reasonId.toString()).up('fieldcontainer');
                if (cbc.down().down('container').getComponent('id_dateStart').getValue() != null)
                {
                    if(!cbc.down().down('container').getComponent('id_dateStart').isValid()){
                        valid=false;
                    }
                }
                if (cbc.down().down('container').getComponent('id_dateEnd').getValue() != null)
                {
                    if(!cbc.down().down('container').getComponent('id_dateEnd').isValid()){
                        valid=false;
                    }
                }
            }


            if (!(form.getForm().findField('empFromDate').getValue() == null || form.getForm().findField('empUntilDate').getValue() == null))
            {
                if (!button.up('toolbar').validateDatesDifference(form.getForm().findField('empFromDate'),form.getForm().findField('empUntilDate')))
                    validdates = false;
            }

            /*if (form.getForm().findField('empFromDate').getValue() == null) form.getForm().findField('empFromDate').setValue("");
             if (form.getForm().findField('empUntilDate').getValue() == null) form.getForm().findField('empUntilDate').setValue("");
             for (var i in values)
             {
             //form.findField(i).clearInvalid();
             if (form.getForm().findField(i).getValue() == null) form.getForm().findField(i).setValue("");
             }*/

            if(checkedboxes>1){
                for (var j = 0; j < checkedboxes; j++) {

                    var sDate = form.getForm().findField(values.reasonId[j].toString()).up('fieldcontainer').down().down('container').items.getAt(0);
                    var uDate = form.getForm().findField(values.reasonId[j].toString()).up('fieldcontainer').down().down('container').items.getAt(1);
                    if (!(sDate.getValue() == null || uDate.getValue() == null))
                    {
                        if (!button.up('toolbar').validateDatesDifference(sDate,uDate))
                            validdates = false;
                    }
                }
            }
            else if (checkedboxes == 1){
                var sDate = form.getForm().findField(values.reasonId.toString()).up('fieldcontainer').down().down('container').items.getAt(0);
                var uDate = form.getForm().findField(values.reasonId.toString()).up('fieldcontainer').down().down('container').items.getAt(1);
                if (!(sDate.getValue() == null || uDate.getValue() == null))
                {
                    if (!button.up('toolbar').validateDatesDifference(sDate,uDate))
                        validdates = false;
                }
            }

            if(valid && validdates){
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

                // Success
                var arr = [];
                var len = form.down("checkboxgroup").getChecked().length;
                if(len>1){
                    for (var j = 0; j < len; j++) {
                        arr.push({
                            employeeDispute: values.url,
                            reasonId: values.reasonId[j],
                            dateStart: button.up('toolbar').dateToTimestamp(values.dateStart[j]),
                            dateEnd: button.up('toolbar').dateToTimestamp(values.dateEnd[j]),
                            comments: values.comments[j]
                        });
                    }
                }
                else if (len == 1){
                    arr.push({
                        employeeDispute: values.url,
                        reasonId: values.reasonId,
                        dateStart: button.up('toolbar').dateToTimestamp(values.dateStart),
                        dateEnd: button.up('toolbar').dateToTimestamp(values.dateEnd),
                        comments: values.comments
                    });
                }

                //clear submited but not need values
                values.reasonId="";
                values.dateStart="";
                values.dateEnd="";
                values.comments="";

                var arr1 = [];
                if (values.url==="")
                    values.employeeDisputeReason=arr1;
                else
                    values.employeeDisputeReason=arr;

                var successCallback2 = function(resp, ops) {
                    formWindow.unmask();

                    Ext.Msg.alert('Επιτυχής Αποθήκευση', 'Η αίτηση σας αποθηκεύτηκε με επιτυχία');

                    // Close window
                    formWindow.destroy();

                };

                var successCallback = function(resp, ops) {

                    if (values.url===""){

                        var urlresp = resp.getResponseHeader("Location");

                        var update_url = urlresp.split("/");
                        fid = update_url[update_url.length-1];

                        for (var i = 0; i < len; i++) {
                            arr[i].employeeDispute=urlresp;
                            if (arr[i].dateStart == "undefined-undefined-T00:00:00.000+0000") arr[i].dateStart = null;
                            if (arr[i].dateEnd == "undefined-undefined-T00:00:00.000+0000") arr[i].dateEnd = null;
                        }


                        values.employeeDisputeReason=arr;

                        var call2 = Ext.Ajax.request({
                            url: "/tEmployeeDisputes/" + fid,
                            headers: { 'Content-Type': 'application/json' },
                            jsonData: Ext.util.JSON.encode(values),
                            method: "PUT",
                            success: successCallback2,
                            failure: failureCallback
                        });

                    }
                    else{

                        formWindow.unmask();

                        Ext.Msg.alert('Επιτυχής Αποθήκευση', 'Η αίτηση σας αποθηκεύτηκε με επιτυχία');

                        // Close window
                        formWindow.destroy();
                    }


                };

                // Failure
                var failureCallback = function(resp, ops) {
                    formWindow.unmask();
                    Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Η αίτηση σας δεν αποθηκεύτηκε.');

                };

                values.subStatus=1;

                var tf=form.getForm().findField('empWorkingHoursFrom').rawValue;
                var tu=form.getForm().findField('empWorkingHoursUntil').rawValue;
                if(tf==="")
                    tf="null";
                if(tu==="")
                    tu="null";
                values.empWorkingHours= tf+"-"+ tu;

                values.empFromDate=button.up('toolbar').dateToTimestamp(values.empFromDate);
                values.empUntilDate=button.up('toolbar').dateToTimestamp(values.empUntilDate);

                if (values.empFromDate == "undefined-undefined-T00:00:00.000+0000") values.empFromDate = null;
                if (values.empUntilDate == "undefined-undefined-T00:00:00.000+0000") values.empUntilDate = null;

                values.protDate=button.up('toolbar').getCurrentTimestamp(1);
                values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
                values.protYear=button.up('toolbar').getCurrentTimestamp(3);
                values.empSpecialityId=parseInt(values.empSpecialityId);

                if(values.docIdAttached!=="" && values.deletedfile==="true" && values.file==="")
                    values.docIdAttached="";

                var fileurl = '/setDocument';
                if(values.docIdAttached!=="" &&values.file!==null)
                    fileurl = fileurl + "?docId=" + values.docIdAttached;

                if(values.file!==""){
                    form.down('form').submit({
                        url: fileurl,
                        waitMsg: 'Αποθήκευση αρχείου...',
                        success: function(form, action) {
                            values.docIdAttached=parseInt(action.result.fileId);
                            Ext.Ajax.request({
                                url: "/tEmployeeDisputes/" + fid,
                                headers: { 'Content-Type': 'application/json' },
                                jsonData: Ext.util.JSON.encode(values),
                                method: rest_method,
                                success: successCallback,
                                failure: failureCallback
                            });
                        },
                        failure: function(form, action) {
                            form.findField("file").focus();
                            Ext.Msg.alert('Αποτυχία Αποθήκευσης Αρχείου', action.result.error);

                        }
                    });
                }
                else{
                    try
                    {
                        for (var k = 0; k < len; k++)
                        {
                            if (values.employeeDisputeReason[k].dateStart == "undefined-undefined-T00:00:00.000+0000")  values.employeeDisputeReason[k].dateStart = null;
                            if (values.employeeDisputeReason[k].dateEnd == "undefined-undefined-T00:00:00.000+0000")  values.employeeDisputeReason[k].dateEnd = null;
                        }
                    }
                    catch (err){}
                    var call1 = Ext.Ajax.request({
                        url: "/tEmployeeDisputes/" + fid,
                        headers: { 'Content-Type': 'application/json' },
                        jsonData: Ext.util.JSON.encode(values),
                        method: rest_method,
                        success: successCallback,
                        failure: failureCallback
                    });
                }
            }
            else
            {
                formWindow.unmask();
                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Παρακαλώ συμπληρώστε τα απαραίτητα πεδία');

            }
        }
        else{
            if ( !form.down('checkboxgroup').isValid())
                form.down('checkboxgroup').markInvalid('Επιλέξτε τουλάχιστον ένα λόγο');
            formWindow.unmask();
            Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Παρακαλώ συμπληρώστε τo όνομα του οργανισμού που αφορά η αίτηση σας, τη διάρκεια της Εργασιακής Σχέσης και τουλάχιστον ένα λόγο αίτησης');


        }
    },

    onSubmit_EMPLOYER_DISPUTE: function(button, e, eOpts) {
        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        formWindow.mask("Παρακαλώ Περιμένετε...");
        form.getForm().clearInvalid();
        var validafm = true;
        if (form.getForm().findField('compAfm').getValue().length>0){
            if (!form.getForm().findField('branch1Id').isValid())
                validafm=false;
            values.branch0Id=parseInt(values.branch0Id);

        }
        if (form.isValid() && validafm) {

            var validdates=true;

            if (!button.up('toolbar').validateDatesDifference(form.getForm().findField('empFromDate'),form.getForm().findField('empUntilDate')))
                validdates = false;
            var checkedboxes = form.down("checkboxgroup").getChecked().length;
            if(checkedboxes>1){
                for (var j = 0; j < checkedboxes; j++) {

                    var sDate = form.getForm().findField(values.reasonId[j].toString()).up('fieldcontainer').down().down('container').items.getAt(0);
                    var uDate = form.getForm().findField(values.reasonId[j].toString()).up('fieldcontainer').down().down('container').items.getAt(1);
                    if (!button.up('toolbar').validateDatesDifference(sDate,uDate))
                        validdates = false;
                }
            }
            else{

                var sDate = form.getForm().findField(values.reasonId.toString()).up('fieldcontainer').down().down('container').items.getAt(0);
                var uDate = form.getForm().findField(values.reasonId.toString()).up('fieldcontainer').down().down('container').items.getAt(1);
                if (!button.up('toolbar').validateDatesDifference(sDate,uDate))
                    validdates = false;
            }

            if(validdates) {

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

                var arr = [];
                var len = form.down("checkboxgroup").getChecked().length;
                if(len>1){
                    for (var j = 0; j < len; j++) {
                        arr.push({
                            employeeDispute: values.url,
                            reasonId: values.reasonId[j],
                            dateStart: button.up('toolbar').dateToTimestamp(values.dateStart[j]),
                            dateEnd: button.up('toolbar').dateToTimestamp(values.dateEnd[j]),
                            comments: values.comments[j]
                        });
                    }
                }
                else{
                    arr.push({
                        employeeDispute: values.url,
                        reasonId: values.reasonId,
                        dateStart: button.up('toolbar').dateToTimestamp(values.dateStart),
                        dateEnd: button.up('toolbar').dateToTimestamp(values.dateEnd),
                        comments: values.comments
                    });
                }


                values.branch0Id=parseInt(values.branch0Id);
                values.empSpecialityId=parseInt(values.empSpecialityId);
                //clear submited but not need values
                values.reasonId="";
                values.dateStart="";
                values.dateEnd="";
                values.comments="";

                var arr1 = [];
                if (values.url==="")
                    values.employeeDisputeReason=arr1;
                else
                    values.employeeDisputeReason=arr;

                var successCallback2 = function(resp, ops) {
                    formWindow.unmask();
                    Ext.Msg.alert('Επιτυχής Υποβολή', 'Η αίτηση σας υποβλήθηκε με επιτυχία');

                    // Close window
                    formWindow.destroy();

                };


                var successCallback = function(resp, ops) {

                    if (values.url===""){

                        var urlresp = resp.getResponseHeader("Location");

                        var update_url = urlresp.split("/");
                        fid = update_url[update_url.length-1];

                        for (var i = 0; i < len; i++) {
                            arr[i].employeeDispute=urlresp;
                        }

                        values.employeeDisputeReason=arr;
                        values.reqStatus=1;
                        values.subStatus=2;

                        var call2 = Ext.Ajax.request({
                            url: "/tEmployeeDisputes/" + fid,
                            headers: { 'Content-Type': 'application/json' },
                            jsonData: Ext.util.JSON.encode(values),
                            method: "PUT",
                            success: successCallback2,
                            failure: failureCallback
                        });
                    }
                    else{

                        formWindow.unmask();

                        Ext.Msg.alert('Επιτυχής Υποβολή', 'Η αίτηση σας υποβλήθηκε με επιτυχία');

                        formWindow.destroy();
                    }


                };

                // Failure
                var failureCallback = function(resp, ops) {
                    formWindow.unmask();
                    Ext.Msg.alert('Αποτυχία Υποβολής', 'Η αίτηση σας δεν υποβλήθηκε αλλά αποθηκεύτηκε.');
                    formWindow.destroy();
                };


                var tf=form.getForm().findField('empWorkingHoursFrom').rawValue;
                var tu=form.getForm().findField('empWorkingHoursUntil').rawValue;
                if(tf==="")
                    tf="null";
                if(tu==="")
                    tu="null";
                values.empWorkingHours= tf+"-"+ tu;


                values.empFromDate=button.up('toolbar').dateToTimestamp(values.empFromDate);
                values.empUntilDate=button.up('toolbar').dateToTimestamp(values.empUntilDate);


                values.protDate=button.up('toolbar').getCurrentTimestamp(1);
                values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
                values.protYear=button.up('toolbar').getCurrentTimestamp(3);

                if (values.url==="")
                    values.subStatus=1;
                else{
                    values.reqStatus=1;
                    values.subStatus=2;
                }


                if(values.docIdAttached!=="" && values.deletedfile==="true" && values.file==="")
                    values.docIdAttached="";

                var fileurl = '/setDocument';
                if(values.docIdAttached!=="" &&values.file!==null)
                    fileurl = fileurl + "?docId=" + values.docIdAttached;

                if(values.file!==""){
                    form.down('form').submit({
                        url: fileurl,
                        waitMsg: 'Αποθήκευση αρχείου...',
                        success: function(form, action) {
                            values.docIdAttached=parseInt(action.result.fileId);
                            Ext.Ajax.request({
                                url: "/tEmployeeDisputes/" + fid,
                                headers: { 'Content-Type': 'application/json' },
                                jsonData: Ext.util.JSON.encode(values),
                                method: rest_method,
                                success: successCallback,
                                failure: failureCallback
                            });
                        },
                        failure: function(form, action) {
                            form.findField("file").focus();
                            Ext.Msg.alert('Αποτυχία Αποθήκευσης Αρχείου', action.result.error);

                        }
                    });
                }
                else{
                    var call1 = Ext.Ajax.request({
                        url: "/tEmployeeDisputes/" + fid,
                        headers: { 'Content-Type': 'application/json' },
                        jsonData: Ext.util.JSON.encode(values),
                        method: rest_method,
                        success: successCallback,
                        failure: failureCallback
                    });
                }
            }
            else{
                formWindow.unmask();
                Ext.Msg.alert('Αποτυχία Υποβολής', 'Παρακαλώ διορθώστε τις ημερομηνίες');


            }
        }
        else{
            formWindow.unmask();
            Ext.Msg.alert('Αποτυχία Υποβολής', 'Παρακαλώ διορθώστε τα λάθος πεδία');

        }
    }

});
