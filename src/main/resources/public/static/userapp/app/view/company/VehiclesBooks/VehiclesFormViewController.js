/*
 * File: app/view/company/VehiclesBooks/VehiclesFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.VehiclesBooks.VehiclesFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companyvehiclesbooksvehiclesform',

    onDelete_COMPANY_VEHICLES: function(button, e, eOpts) {

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
                url: "/compVehicleBook/" + fid,
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

    onSave_COMPANY_VEHICLES: function(button, e, eOpts) {
        var conffun = function(buttonText) {
            if (buttonText == "yes") {
                submitfun();
            }
            if (buttonText == "no") {
                return false;
            }
        };

        var msb = Ext.MessageBox.confirm('Καταχώρηση φόρμας', 'Είστε σίγουροι ότι θελετε να αποθηκεύσετε το όχημα; ', conffun);

        var submitfun = function(){
            var form = button.up('toolbar').up('window').down('form'),
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
                    fid = res[res.length-1];
                    fid = fid + "/";
                    rest_method = "PUT";
                }

                // Success
                var successCallback = function(resp, ops) {

                    Ext.Msg.alert('Επιτυχής Αποθήκευση', 'Η εγγραφή σας αποθηκεύθηκε με επιτυχία.');
                    formWindow.destroy();

                };

                // Failure
                var failureCallback = function(resp, ops) {

                    Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Η εγγραφή σας δεν έγινε δεκτή από το σύστημα');

                };
                values.subStatus=1;

                values.protDate=button.up('toolbar').getCurrentTimestamp(1);
                values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
                values.protYear=button.up('toolbar').getCurrentTimestamp(3);

                Ext.Ajax.request({
                    url: "/compVehicleBook/" + fid,
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
        };

    },

    onSubmit_COMPANY_VEHICLES: function(button, e, eOpts) {
        var conffun = function(buttonText) {
            if (buttonText == "yes") {
                submitfun();
            }
            if (buttonText == "no") {
                return false;
            }
        };

        var msb = Ext.MessageBox.confirm('Καταχώρηση φόρμας', 'Είστε σίγουροι ότι θελετε να υποβάλετε το βιβλίο οχήματος;', conffun);

        var submitfun = function(){
            var form = button.up('toolbar').up('window').down('form'),
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
                    fid = res[res.length-1];
                    fid = fid + "/";
                    rest_method = "PUT";
                }

                // Success
                var successCallback = function(resp, ops) {

                    Ext.Msg.alert('Επιτυχής Καταχώρηση', 'Η εγγραφή σας καταχωρήθηκε με επιτυχία.');
                    formWindow.destroy();

                };

                // Failure
                var failureCallback = function(resp, ops) {

                    Ext.Msg.alert('Αποτυχία Καταχώρησης', 'Η εγγραφή σας δεν έγινε δεκτή από το σύστημα');

                };


                values.protDate=button.up('toolbar').getCurrentTimestamp(1);
                values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
                values.protYear=button.up('toolbar').getCurrentTimestamp(3);

                values.subStatus=2;

                Ext.Ajax.request({
                    url: "/compVehicleBook/" + fid,
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
        };


    }

});
