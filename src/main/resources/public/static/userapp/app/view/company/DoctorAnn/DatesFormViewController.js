/*
 * File: app/view/company/DoctorAnn/DatesFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.DoctorAnn.DatesFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companydoctoranndatesform',

    onCheck_COMPANY_DOCTOR_ANN_DATES: function(button, e, eOpts) {

        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();


        if (Date.parse(values.dateStart.substr(0,10))<Date.parse(values.dateEnd.substr(0,10))){

            var successCall = function(options, success, response) {
                formWindow.unmask();
                if (response.responseText==="0") {


                    var emp_comp2 = Ext.create('widget.companydoctoranndoctorform', {});
                    var entrie = Ext.create('widget.companydoctoranndiary', {});
                    entrie.down().items.get(0).minValue= (form.getForm().findField('dateStart').value > new Date()) ? form.getForm().findField('dateStart').value : new Date();
                    entrie.down().items.get(0).maxValue=form.getForm().findField('dateEnd').value;
                    Ext.getCmp('diaryEntries').add(entrie);

                    emp_comp2.down('form').getForm().findField('dateStart').setValue(form.getForm().findField('dateStart').rawValue);
                    emp_comp2.down('form').getForm().findField('dateEnd').setValue(form.getForm().findField('dateEnd').rawValue);
                    var ptlBranch = Ext.getCmp('companyDoctorAnn_Branches').getSelectionModel().getSelection()[0];
                    emp_comp2.down('form').getForm().findField('compPtlBranchId').setValue(ptlBranch.get('ptlBranchId'));
                    emp_comp2.down('form').getForm().findField('compEbrBranchId').setValue(ptlBranch.get('ebrBranchId'));

                    emp_comp2.show();
                    formWindow.destroy();
                }
                else{

                    Ext.Msg.alert('Μη έγκυρες ημερομηνίες', 'Οι ημερομηνίες έχουν επικάλυψη με άλλη ενεργή αίτηση');

                }

            };
            formWindow.mask("Παρακαλώ Περιμένετε...");
            Ext.Ajax.request({
                url: "/compIeAnn/search/countIeAnn",
                params: {
                    ptlBranchId: Ext.getCmp('companyDoctorAnn_Branches').getSelectionModel().getSelection()[0].get('ptlBranchId'),
                    startDate: form.getForm().findField('dateStart').rawValue
                },
                method: "GET",
                callback: successCall
            });

            /* WITHOUT CALL
            var validDates = false;
            var record=Ext.getCmp('companyDoctorAnn_Doctors').store.findRecord('ieAnnStatus', 0);
            if(record){
            if (Date.parse(values.dateStart)>Date.parse(record.get('dateEnd')) && Date.parse(values.dateEnd)>Date.parse(record.get('dateEnd')) )
            validDates=true;
            if (Date.parse(values.dateStart)<Date.parse(record.get('dateStart')) && Date.parse(values.dateEnd)<Date.parse(record.get('dateStart')) )
            validDates=true;
            }
            else{
            record=Ext.getCmp('companyDoctorAnn_Doctors').store.findRecord('reqStatus', 6);
            if(record && record.get('ieAnnStatus')===1){
            if (Date.parse(values.dateStart)>Date.parse(record.get('dateEnd')) && Date.parse(values.dateEnd)>Date.parse(record.get('dateEnd')) )
            validDates=true;
            if (Date.parse(values.dateStart)<Date.parse(record.get('dateStart')) && Date.parse(values.dateEnd)<Date.parse(record.get('dateStart')) )
            validDates=true;
            }
            else{
            validDates=true;
            }

            }

            if(validDates){
            create form
            }
            else{
            Ext.Msg.alert('Μη έγκυρες ημερομηνίες', 'Οι ημερομηνίες έχουν επικάλυψη με άλλη ενεργή αίτηση');
            }*/
        }
        else{
            Ext.Msg.alert('Μη έγκυρες ημερομηνίες', 'Η ημερομηνία "Από" πρέπει να προηγείται της "Έως"');
        }
    }

});
