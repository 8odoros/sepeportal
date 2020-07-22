/*
 * File: app/view/company/ProjectsBooks/DailyFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.ProjectsBooks.DailyFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companyprojectsbooksdailyform',

    onSubmit_COMPANY_DAILYCARD: function(button, e, eOpts) {
        var conffun = function(buttonText) {
            if (buttonText == "yes") {
                submitfun();
            }
            if (buttonText == "no") {
                return false;
            }
        };

        var msb = Ext.MessageBox.confirm('Καταχώρηση δελτίου', 'Είστε σίγουροι ότι θελετε να δημιουργήσετε το δελτίο; ', conffun);

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

                // Success
                var successCallback = function(resp, ops) {

                    if (resp.status===304){
                        Ext.Msg.alert('Αποτυχία Καταχώρησης', decodeURIComponent(resp.getResponseHeader("SEPE_ERROR")).replace(": SEPE_ERROR:","").replace(/\+/g,' '));
                    }
                    else
                    Ext.Msg.alert('Επιτυχής Καταχώρηση', 'Το ημερήσιο δελτίο καταχωρήθηκε με επιτυχία.');
                    formWindow.destroy();
                    var projectid = Ext.getCmp('companyProjectsBooks_Projects').getSelectionModel().getSelection()[0].get('projectid');
                    var dailygrid = Ext.getCmp('companyProjectsBooks_Daily');
                    Ext.getCmp('companyProjectsBooks_Daily').getView().store.proxy.setUrl('/companyDailyCards/search/findByProjectId?projectId='+projectid);
                    dailygrid.store.load( { callback : function(records, operation, success) {
                    dailygrid.getView().refresh();}
                });



            };

            // Failure
            var failureCallback = function(resp, ops) {

                Ext.Msg.alert('Αποτυχία Καταχώρησης', 'Το ημερήσιο δελτίο δεν έγινε δεκτό από το σύστημα');

            };

            values.cardDate=button.up('toolbar').getCurrentTimestamp(1);
            var projectid = Ext.getCmp('companyProjectsBooks_Projects').getSelectionModel().getSelection()[0].get('projectid');
            values.projectId=parseInt(projectid);
            Ext.Ajax.request({
                url: "/companyDailyCards/",
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
