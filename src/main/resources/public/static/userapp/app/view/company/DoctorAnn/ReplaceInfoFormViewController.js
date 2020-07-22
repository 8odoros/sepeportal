/*
 * File: app/view/company/DoctorAnn/ReplaceInfoFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.DoctorAnn.ReplaceInfoFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companydoctorannreplaceinfoform',

    info_COMPANY_DOCTOR_ANN_REPLACE: function(button, e, eOpts) {

        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        var emp_comp3 = Ext.create('widget.companydoctoranndoctorform', {});
        var entrie = Ext.create('widget.companydoctoranndiary', {});
        entrie.down().items.get(0).minValue= (form.getForm().findField('dateStart').value > new Date()) ? form.getForm().findField('dateStart').value : new Date();
        entrie.down().items.get(0).maxValue=form.getForm().findField('dateEnd').value;
        Ext.getCmp('diaryEntries').add(entrie);

        emp_comp3.down('form').getForm().findField('dateStart').setValue(form.getForm().findField('dateStart').rawValue);
        emp_comp3.down('form').getForm().findField('dateEnd').setValue(form.getForm().findField('dateEnd').rawValue);
        emp_comp3.down('form').getForm().findField('compPtlBranchId').setValue(values.compPtlBranchId);
        emp_comp3.down('form').getForm().findField('compEbrBranchId').setValue(values.compEbrBranchId);
        emp_comp3.down('form').getForm().findField('compIeAnnPrevId').setValue(values.compIeAnnPrevId);

        emp_comp3.down('toolbar').getComponent('savebutton').hide();
        emp_comp3.show();

        formWindow.destroy();
    }

});
