/*
 * File: app/view/company/DoctorAnn/BranchFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.DoctorAnn.BranchFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companydoctorannbranchform',

    onSave_COMPANY_BRANCH: function(button, e, eOpts) {

        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        if (values.NewebrBranchId != "" && isNaN(parseInt(values.NewebrBranchId, 10)))
        {
            Ext.Msg.alert('Αποτυχία Υποβολής', 'Παρακαλώ διορθώστε το παράρτημα');
            return;
        }


        if(form.isValid()){
            var rest_method = "POST";

            var successCallback = function(resp, ops) {
                if (resp.status===304){
                    Ext.Msg.alert('Αποτυχία Καταχώρησης', decodeURIComponent(resp.getResponseHeader("SEPE_ERROR")).replace(": SEPE_ERROR:","").replace(/\+/g,' '));
                }
                else{
                    Ext.Msg.alert('Επιτυχής Αποθήκευση', 'Το παράρτημα αποθηκεύτηκε με επιτυχία');

                    // Close window
                    if(Ext.getCmp('companyTechnicianAnn_Branches'))
                    var grid = Ext.getCmp('companyTechnicianAnn_Branches');
                    else if(Ext.getCmp('companyDoctorAnn_Branches'))
                    var grid = Ext.getCmp('companyDoctorAnn_Branches');
                    grid.getView().store.reload();
                    formWindow.destroy();
                }

            };

            // Failure
            var failureCallback = function(resp, ops) {
                Ext.Msg.alert('Αποτυχία Καταχώρησης', 'Το παράρτημα δεν αποθηκεύτηκε.');

            };


            Ext.Ajax.request({
                url: "/compPtlBranch/",
                headers: { 'Content-Type': 'application/json' },
                jsonData: Ext.util.JSON.encode(values),
                method: rest_method,
                success: successCallback,
                failure: failureCallback
            });



        }
        else{
            Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Παρακαλώ συμπληρώστε τα απαραίτητα πεδία για αποθήκευση');


        }
    },

    onUpdate_COMPANY_BRANCH: function(button, e, eOpts) {

        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();


        if(form.isValid()){
            var rest_method = "POST";

            var successCallback = function(resp, ops) {
                if (resp.status===304){
                    Ext.Msg.alert('Αποτυχία Καταχώρησης', decodeURIComponent(resp.getResponseHeader("SEPE_ERROR")).replace(": SEPE_ERROR:","").replace(/\+/g,' '));
                }
                else{
                    Ext.Msg.alert('Επιτυχής Ενημέρωση', 'Η ενημέρωση του παραρτήματος έγινε με επιτυχία');

                    // Close window
                    if(Ext.getCmp('companyTechnicianAnn_Branches'))
                        var grid = Ext.getCmp('companyTechnicianAnn_Branches');
                    else if(Ext.getCmp('companyDoctorAnn_Branches'))
                        var grid = Ext.getCmp('companyDoctorAnn_Branches');
                    grid.getView().store.reload();
                    formWindow.destroy();
                }

            };

            // Failure
            var failureCallback = function(resp, ops) {
                Ext.Msg.alert('Αποτυχία Καταχώρησης', 'Η αλλαγές του παραρτήματος δεν αποθηκεύτηκαν.');

            };


            Ext.Ajax.request({
                url: "/updateCompPtlBranch",
                params: {
                    companyId: values.companyId,
                    ptlBranchId: values.ptlBranchId,
                    brDescr: values.brDescr,
                    brActive: values.brActive
                },
                method: rest_method,
                success: successCallback,
                failure: failureCallback
            });



        }
        else{
            Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Παρακαλώ συμπληρώστε τα απαραίτητα πεδία για αποθήκευση');


        }
    }

});
