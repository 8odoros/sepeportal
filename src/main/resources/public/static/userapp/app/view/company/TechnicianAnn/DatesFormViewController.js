/*
 * File: app/view/company/TechnicianAnn/DatesFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianAnn.DatesFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companytechniciananndatesform',

    onCheck_COMPANY_TECHNICIAN_ANN_DATES: function(button, e, eOpts) {

        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();


        if (values.dateStart != '' && values.dateStart == values.dateEnd)
        {
            Ext.Msg.alert('Μη έγκυρες ημερομηνίες', 'Η ημερομηνία έναρξης δεν πρέπει να είναι ίδια με την ημερομηνία λήξης');
            return;
        }
        if (values.dateStart > values.dateEnd)
        {
            Ext.Msg.alert('Μη έγκυρες ημερομηνίες', 'Η ημερομηνία έναρξης πρέπει να προηγείται της ημερομηνίας λήξης');
            return;
        }
        if (form.isValid()){


            var successCall = function(options, success, response) {
                formWindow.unmask();
                if (response.responseText==="0") {


                    var emp_comp2 = Ext.create('widget.companytechniciananntechnicianform', {});
                    var entrie = Ext.create('widget.companytechniciananndiary', {});
                    entrie.items.get(0).minValue= (form.getForm().findField('dateStart').value > new Date()) ? form.getForm().findField('dateStart').value : new Date();
                    entrie.items.get(0).maxValue=form.getForm().findField('dateEnd').value;
                    entrie.items.get(6).setStyle({margin:'26px 0 0 2px'});
                    Ext.getCmp('tadiaryEntries').add(entrie);

                    /*var taentrie = Ext.create('widget.companytechniciananntaentry', {});
                     taentrie.down().items.get(2).readOnly=true;
                     Ext.getCmp('taAnnTaEntries').add(taentrie);*/

                    emp_comp2.down('form').getForm().findField('dateStart').setValue(form.getForm().findField('dateStart').rawValue);
                    emp_comp2.down('form').getForm().findField('dateEnd').setValue(form.getForm().findField('dateEnd').rawValue);

                    var ptlBranch = Ext.getCmp('companyTechnicianAnn_Branches').getSelectionModel().getSelection()[0];
                    emp_comp2.down('form').getForm().findField('compPtlBranchId').setValue(ptlBranch.get('ptlBranchId'));
                    emp_comp2.down('form').getForm().findField('compEbrBranchId').setValue(ptlBranch.get('ebrBranchId'));


                    emp_comp2.down('form').getForm().findField('brAddr').setValue(ptlBranch.get('brAddr'));
                    emp_comp2.down('form').getForm().findField('brAddrTk').setValue(ptlBranch.get('brAddrTk'));
                    emp_comp2.down('form').getForm().findField('brAddrP').setValue(ptlBranch.get('brAddrP'));
                    emp_comp2.down('form').getForm().findField('brAddrPe').setValue(ptlBranch.get('brAddrPe'));
                    emp_comp2.down('form').getForm().findField('brAddrD').setValue(ptlBranch.get('brAddrD'));
                    emp_comp2.down('form').getForm().findField('brAddrK').setValue(ptlBranch.get('brAddrK'));

                    var successAns2 = function(options, success, response) {
                        if (Ext.JSON.decode(response.responseText).success) {

                            var resp =Ext.JSON.decode(response.responseText);

                            emp_comp2.down('form').getForm().findField('compCategANum').setValue(resp.categANum);
                            emp_comp2.down('form').getForm().findField('compCategBNum').setValue(resp.categBNum);
                            emp_comp2.down('form').getForm().findField('compCategCNum').setValue(resp.categCNum);
                        }
                        else{
                            Ext.Msg.alert('Σφάλμα!', 'Υπάρχει πρόβλημα στην επισύναψη στοιχείων');

                        }
                    };

                    Ext.Ajax.request({
                        url: '/companyExtraInfo',
                        method: "GET",
                        callback: successAns2
                    });

                    var successAns3 = function(options, success, response) {
                        if (Ext.JSON.decode(response.responseText).success) {

                            var resp =Ext.JSON.decode(response.responseText);
                            emp_comp2.down('form').getForm().findField('compFullName').setValue(resp.compFullName);
                            emp_comp2.down('form').getForm().findField('compTaxNumber').setValue(resp.compTaxNumber);
                            emp_comp2.down('form').getForm().findField('compAme').setValue(resp.compAme);
                            emp_comp2.down('form').getForm().findField('compAddr').setValue(resp.compAddr);
                            emp_comp2.down('form').getForm().findField('compAddrK').setValue(resp.compAddrK);
                            emp_comp2.down('form').getForm().findField('compAddrTk').setValue(resp.compAddrTk);
                            emp_comp2.down('form').getForm().findField('compPhone').setValue(resp.compPhone);
                        }
                        else{
                            Ext.Msg.alert('Σφάλμα!', 'Υπάρχει πρόβλημα στην επισύναψη στοιχείων');

                        }
                    };

                    Ext.Ajax.request({
                        url : "/getCompanyInfo",
                        method : 'GET',
                        callback : successAns3
                    });


                    emp_comp2.show();
                    formWindow.destroy();
                }
                else{

                    Ext.Msg.alert('Μη έγκυρες ημερομηνίες', 'Οι ημερομηνίες έχουν επικάλυψη με άλλη ενεργή αίτηση');

                }

            };
            formWindow.mask("Παρακαλώ Περιμένετε...");
            Ext.Ajax.request({
                url: "/compTaAnn/search/countTaAnn",
                params: {
                    ptlBranchId: Ext.getCmp('companyTechnicianAnn_Branches').getSelectionModel().getSelection()[0].get('ptlBranchId'),
                    startDate: form.getForm().findField('dateStart').rawValue,
                    endDate: form.getForm().findField('dateEnd').rawValue
                },
                method: "GET",
                callback: successCall
            });

        }
        else{
            Ext.Msg.alert('Μη έγκυρες ημερομηνίες', 'Παρακαλώ διορθώστε τις ημερομηνίες');
        }
    }

});
