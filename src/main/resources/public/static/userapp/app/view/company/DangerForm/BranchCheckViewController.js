/*
 * File: app/view/company/DangerForm/BranchCheckViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.DangerForm.BranchCheckViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companydangerformbranchcheck',

    onCheck_COMPANY_DANGER_BRANCH: function(button, e, eOpts) {

        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        if (values.branch1Id != "" && isNaN(parseInt(values.branch1Id, 10)))
        {
            Ext.Msg.alert('Αποτυχία Υποβολής', 'Παρακαλώ διορθώστε το παράρτημα');
            return;
        }

        if(form.isValid()){
            var successCall = function(options, success, response) {
                formWindow.unmask();
                if (response.responseText==="0") {

                    var emp_comp2 = Ext.create('widget.companydangerformdangerform', {});
                    emp_comp2.down('form').getForm().findField('branch1Id').setValue(values.branch1Id);
                    emp_comp2.show();
                    formWindow.destroy();
                }
                else{

                    Ext.Msg.alert('Προσοχή', 'Υπάρχει ήδη εγγραφή για το συγκεκριμένο Παράρτημα. <br>Μπορείτε να την επεξεργαστείτε.');

                }

            };
            formWindow.mask("Παρακαλώ Περιμένετε...");
            Ext.Ajax.request({
                url: "/compDangerAssess/search/findByBranchId",
                params: {
                    branchId: values.branch1Id
                },
                method: "GET",
                callback: successCall
            });
        }
    }

});
