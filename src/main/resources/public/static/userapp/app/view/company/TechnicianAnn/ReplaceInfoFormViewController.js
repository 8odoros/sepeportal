/*
 * File: app/view/company/TechnicianAnn/ReplaceInfoFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianAnn.ReplaceInfoFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companytechnicianannreplaceinfoform',

    info_COMPANY_TECHNICIAN_ANN_REPLACE: function(button, e, eOpts) {

        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        var emp_comp3 = Ext.create('widget.companytechniciananntechnicianform', {});
        var entrie = Ext.create('widget.companytechniciananndiary', {});
        entrie.items.get(0).minValue= (form.getForm().findField('dateStart').value > new Date()) ? form.getForm().findField('dateStart').value : new Date();
        entrie.items.get(0).maxValue=form.getForm().findField('dateEnd').value;
        Ext.getCmp('tadiaryEntries').add(entrie);
/*
        var taentrie = Ext.create('widget.companytechniciananntaentry', {});
        taentrie.down().items.get(2).readOnly=true;
        Ext.getCmp('taAnnTaEntries').add(taentrie);*/

        emp_comp3.down('form').getForm().findField('dateStart').setValue(form.getForm().findField('dateStart').rawValue);
        emp_comp3.down('form').getForm().findField('dateEnd').setValue(form.getForm().findField('dateEnd').rawValue);
        emp_comp3.down('form').getForm().findField('compPtlBranchId').setValue(values.compPtlBranchId);
        emp_comp3.down('form').getForm().findField('compEbrBranchId').setValue(values.compEbrBranchId);
        emp_comp3.down('form').getForm().findField('compTaAnnPrevId').setValue(values.compTaAnnPrevId);
        Ext.StoreMgr.lookup('company.TechnicianAnn.LocalTaEntries').clearData();
        emp_comp3.down('toolbar').getComponent('savebutton').hide();
        emp_comp3.show();

        formWindow.destroy();

    }

});
