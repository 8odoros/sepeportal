/*
 * File: app/view/company/ProjectsBooks/ProjectsFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.ProjectsBooks.ProjectsFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companyprojectsbooksprojectsform',

    onSubmit_COMPANY_PROJECT: function(button, e, eOpts) {
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
                    values.creationDate=button.up('toolbar').getCurrentTimestamp(1);
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

                };

                // Failure
                var failureCallback = function(resp, ops) {

                    Ext.Msg.alert('Αποτυχία Καταχώρησης', 'Η εγγραφή σας δεν έγινε δεκτή από το σύστημα');

                };

                values.empDateOfBirth=button.up('toolbar').dateToTimestamp(values.empDateOfBirthView);
                values.contrDateOfBirth=button.up('toolbar').dateToTimestamp(values.contrDateOfBirthView);
                values.subContrDateOfBirth=button.up('toolbar').dateToTimestamp(values.subContrDateOfBirthView);

                Ext.Ajax.request({
                    url: "/companyProjects/" + fid,
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
