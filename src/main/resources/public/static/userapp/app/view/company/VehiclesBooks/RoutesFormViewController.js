/*
 * File: app/view/company/VehiclesBooks/RoutesFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.VehiclesBooks.RoutesFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companyvehiclesbooksroutesform',

    onSubmit_COMPANY_ROUTES: function(button, e, eOpts) {
        var conffun = function(buttonText) {
            if (buttonText == "yes") {
                submitfun();
            }
            if (buttonText == "no") {
                return false;
            }
        };

        var msb = Ext.MessageBox.confirm('Καταχώρηση φόρμας', 'Είστε σίγουροι ότι θελετε να αποθηκεύσετε τη φόρμα; ', conffun);

        var submitfun = function(){
            var form = button.up('toolbar').up('window').down('form'),
                formWindow = button.up('toolbar').up('window'),
                values = form.getValues();
            
            form.getForm().findField('activityStartTime').allowBlank = false;
            form.getForm().findField('activityStartTime').allowOnlyWhitespace = false;
            form.getForm().findField('activityEndTime').allowBlank = false;
            form.getForm().findField('activityEndTime').allowOnlyWhitespace = false;
            form.getForm().findField('jobHours').allowBlank = false;
            form.getForm().findField('jobHours').allowOnlyWhitespace = false;


            if (form.isValid()) {
                var fid, rest_method;

                if (values.url===""){
                    fid = "";
                    rest_method = "POST";
                    values.dateCreated=button.up('toolbar').getCurrentTimestamp(1);
                    values.timeCreated=button.up('toolbar').getCurrentTimestamp(2);
                    values.dateUpdated=button.up('toolbar').getCurrentTimestamp(1);
                    values.timeUpdated=button.up('toolbar').getCurrentTimestamp(2);
                }
                else
                {
                    var nurl = values.url;
                    var res = nurl.split("/");
                    fid = res[res.length-1];
                    fid = fid + "/";
                    rest_method = "PUT";
                    values.dateUpdated=button.up('toolbar').getCurrentTimestamp(1);
                    values.timeUpdated=button.up('toolbar').getCurrentTimestamp(2);
                }

                if (values.driverBirthdate == "") values.driverBirthdate = null;
                if (values.assistBirthdate == "") values.assistBirthdate = null;


                // Success
                var successCallback = function(resp, ops) {

                    if (resp.status===304){
                        Ext.Msg.alert('Αποτυχία Καταχώρησης', decodeURIComponent(resp.getResponseHeader("SEPE_ERROR")).replace(": SEPE_ERROR:","").replace(/\+/g,' '));
                    }
                    else
                    Ext.Msg.alert('Επιτυχής Καταχώρηση', 'Η εγγραφή σας καταχωρήθηκε με επιτυχία.');
                    formWindow.destroy();
                    var vehicleId = Ext.getCmp('companyVehiclesBooks_Vehicles').getSelectionModel().getSelection()[0].get('vehicleId');
                    var routesgrid = Ext.getCmp('companyVehiclesBooks_Routes');
                    routesgrid.getView().store.proxy.setUrl('/compVehicleBookEntry/search/findByCompVehicleBookId?compVehicleBookId='+vehicleId);
                    routesgrid.store.load( { callback : function(records, operation, success) {
                    routesgrid.getView().refresh();}
                });


            };

            // Failure
            var failureCallback = function(resp, ops) {

                Ext.Msg.alert('Αποτυχία Καταχώρησης', 'Η εγγραφή σας δεν έγινε δεκτή από το σύστημα');

            };


            Ext.Ajax.request({
                url: "/compVehicleBookEntry/" + fid,
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
