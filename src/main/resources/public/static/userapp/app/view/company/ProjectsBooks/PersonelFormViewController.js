/*
 * File: app/view/company/ProjectsBooks/PersonelFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.ProjectsBooks.PersonelFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companyprojectsbookspersonelform',

    onSubmit_COMPANY_PERSONEL: function(button, e, eOpts) {
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


            if (form.isValid()) {
                var fid, rest_method;

                if (values.url===""){
                    fid = "";
                    rest_method = "POST";
                    values.submitTime=button.up('toolbar').getCurrentTimestamp(1);
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

                    if (resp.status===304){
                        Ext.Msg.alert('Αποτυχία Καταχώρησης', decodeURIComponent(resp.getResponseHeader("SEPE_ERROR")).replace(": SEPE_ERROR:","").replace(/\+/g,' '));
                    }
                    else
                    Ext.Msg.alert('Επιτυχής Καταχώρηση', 'Η εγγραφή σας καταχωρήθηκε με επιτυχία.');
                    formWindow.destroy();
                    var dailyCardId = Ext.getCmp('companyProjectsBooks_Daily').getSelectionModel().getSelection()[0].get('dailycardid');
                    var personelgrid = Ext.getCmp('companyProjectsBooks_Personel');
                    Ext.getCmp('companyProjectsBooks_Personel').getView().store.proxy.setUrl('/companyPersonnelBook/search/findByDailyCardId?dailyCardId='+dailyCardId);
                    personelgrid.store.load( { callback : function(records, operation, success) {
                    personelgrid.getView().refresh();}
                });


            };

            // Failure
            var failureCallback = function(resp, ops) {

                Ext.Msg.alert('Αποτυχία Καταχώρησης', 'Η εγγραφή σας δεν έγινε δεκτή από το σύστημα');

            };

            values.editTime=button.up('toolbar').getCurrentTimestamp(1);
            values.dateOfRecruitment=button.up('toolbar').dateToTimestamp(values.dateOfRecruitmentView);
            values.restingDay=button.up('toolbar').dateToTimestamp(values.restingDayView);
            var dailycardid = Ext.getCmp('companyProjectsBooks_Daily').getSelectionModel().getSelection()[0].get('dailycardid');
            values.dailyCardId=parseInt(dailycardid);

            values.workingHourStart=form.getForm().findField('workingHourStart').rawValue;
            values.workingHourStop=form.getForm().findField('workingHourStop').rawValue;

            Ext.Ajax.request({
                url: "/companyPersonnelBook/" + fid,
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
