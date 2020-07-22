/*
 * File: app/view/company/TechnicianAnn/ShipReplaceInfoFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianAnn.ShipReplaceInfoFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companytechnicianannshipreplaceinfoform',

    info_COMPANY_TECHNICIAN_ANN_REPLACE: function(button, e, eOpts) {

        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        var emp_comp3 = Ext.create('widget.companytechnicianannshiptechform', {});

        emp_comp3.down('form').getForm().findField('dateStart').setValue(form.getForm().findField('dateStart').rawValue);
        emp_comp3.down('form').getForm().findField('dateEnd').setValue(form.getForm().findField('dateEnd').rawValue);
        emp_comp3.down('form').getForm().findField('compShipId').setValue(values.compShipId);
        emp_comp3.down('form').getForm().findField('compTaSannPrevId').setValue(values.compTaSannId);
        emp_comp3.down('form').getForm().findField('projStartDate').setValue(form.getForm().findField('dateStart').rawValue);

        var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
        var diffDays = Math.round(Math.abs((form.getForm().findField('dateStart').value.getTime() - form.getForm().findField('dateEnd').value.getTime())/(oneDay)));
        emp_comp3.down('form').getForm().findField('projDuration').setValue(diffDays);
        
        var projscontr = Ext.create('widget.companytechnicianannshipcontr', {});
        projscontr.items.getAt(0).items.getAt(0).setValue("1. ");
        Ext.getCmp('sanncontrs').add(projscontr);

        emp_comp3.down('toolbar').getComponent('savebutton').hide();
        emp_comp3.show();

        formWindow.destroy();
    }

});
