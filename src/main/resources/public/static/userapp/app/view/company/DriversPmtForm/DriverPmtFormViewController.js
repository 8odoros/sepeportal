/*
 * File: app/view/company/DriversPmtForm/DriverPmtFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.DriversPmtForm.DriverPmtFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companydriverspmtformdriverpmtform',

    onDelete_COMPANY_DRIVERS_PMT: function(button, e, eOpts) {

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
                url: "/compDriverPmt/" + fid,
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

    onSave_COMPANY_DRIVERS_PMT: function(button, e, eOpts) {
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

                if (resp.status===304){
                    msg=decodeURIComponent(resp.getResponseHeader("SEPE_ERROR")).replace(": SEPE_ERROR:","").replace(/\+/g,' ');
                    Ext.Msg.alert('Αποτυχία Αποθήκευσης', msg);

                }
                else{
                    Ext.Msg.alert('Επιτυχής Αποθήκευση', 'Η αίτηση αποθηκεύθηκε με επιτυχία.');
                    formWindow.destroy();
                }


            };

            // Failure
            var failureCallback = function(resp, ops) {

                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Η αίτηση δεν έγινε δεκτή από το σύστημα.');

            };

            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
            values.protYear=button.up('toolbar').getCurrentTimestamp(3);

            values.offdays="";
            var picker = Ext.getCmp('mutliDatePicker');
            var seldaysArr = picker.getSelectedDates();
            var offdaysArr = [];
            for (var j = 0; j < seldaysArr.length; j++) {
                seldaysArr[j] = button.up('toolbar').dateToTimestamp(seldaysArr[j]);
                offdaysArr.push({
                    week: -1,
                    day: seldaysArr[j]
                });
            }

            /*values.offdays="";
            var offdaysArr = [];
            if (parseInt(values.offdaysnum)>1){
                for (var j = 0; j < (parseInt(values.offdaysnum)); j++) {
                    if(values.dayView[j]!=="")
                    values.day[j]=button.up('toolbar').dateToTimestamp(values.dayView[j]);
                    offdaysArr.push({
                        week: values.week[j],
                        day: values.day[j]
                    });
                }
            }
            else{
                if(values.dayView!=="")
                values.day=button.up('toolbar').dateToTimestamp(values.dayView);
                offdaysArr.push({
                    week: values.week,
                    day: values.day,
                });
            }

            delete values.week;
            delete values.day;*/

            values.offdays=offdaysArr;
            values.subStatus=1;


            Ext.Ajax.request({
                url: "/compDriverPmt/" + fid,
                headers: { 'Content-Type': 'application/json' },
                jsonData: Ext.util.JSON.encode(values),
                method: rest_method,
                success: successCallback,
                failure: failureCallback
            });


        }
        else{
            Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Παρακαλώ διορθώστε τα λάθος πεδία');

        }
    },

    onSubmit_COMPANY_DRIVERS_PMT: function(button, e, eOpts) {
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

                if (resp.status===304){
                    msg=decodeURIComponent(resp.getResponseHeader("SEPE_ERROR")).replace(": SEPE_ERROR:","").replace(/\+/g,' ');
                    Ext.Msg.alert('Αποτυχία Υποβολής', msg);

                }
                else{
                    Ext.Msg.alert('Επιτυχής Υποβολή', 'Η αίτηση υποβλήθηκε με επιτυχία.');
                    formWindow.destroy();
                }



            };

            // Failure
            var failureCallback = function(resp, ops) {

                Ext.Msg.alert('Αποτυχία Υποβολής', 'Η αίτηση δεν έγινε δεκτή από το σύστημα.');

            };

            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
            values.protYear=button.up('toolbar').getCurrentTimestamp(3);


            values.offdays="";
            var picker = Ext.getCmp('mutliDatePicker');
            var seldaysArr = picker.getSelectedDates();
            var offdaysArr = [];
            for (var j = 0; j < seldaysArr.length; j++) {
                seldaysArr[j] = button.up('toolbar').dateToTimestamp(seldaysArr[j]);
                offdaysArr.push({
                    week: -1,
                    day: seldaysArr[j]
                });
            }
            /*if (parseInt(values.offdaysnum)>1){
                for (var j = 0; j < (parseInt(values.offdaysnum)); j++) {
                    if(values.dayView[j]!=="")
                    values.day[j]=button.up('toolbar').dateToTimestamp(values.dayView[j]);
                    offdaysArr.push({
                        week: values.week[j],
                        day: values.day[j]
                    });
                }
            }
            else{
                if(values.dayView!=="")
                values.day=button.up('toolbar').dateToTimestamp(values.dayView);
                offdaysArr.push({
                    week: values.week,
                    day: values.day,
                });
            }

            delete values.week;
            delete values.day;*/

            values.offdays=offdaysArr;

            values.subStatus=2;
            values.reqStatus=5;


            Ext.Ajax.request({
                url: "/compDriverPmt/" + fid,
                headers: { 'Content-Type': 'application/json' },
                jsonData: Ext.util.JSON.encode(values),
                method: rest_method,
                success: successCallback,
                failure: failureCallback
            });


        }
        else{
            Ext.Msg.alert('Αποτυχία Υποβολής', 'Παρακαλώ διορθώστε τα λάθος πεδία');

        }

    }

});
