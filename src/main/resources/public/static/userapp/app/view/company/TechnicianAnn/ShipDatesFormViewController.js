/*
 * File: app/view/company/TechnicianAnn/ShipDatesFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianAnn.ShipDatesFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companytechnicianannshipdatesform',

    onCheck_COMPANY_TECHNICIAN_ANN_DATES: function(button, e, eOpts) {

        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();


        if (Date.parse(values.dateStart.substr(0,10))<Date.parse(values.dateEnd.substr(0,10))){

            var successCall = function(options, success, response) {
                formWindow.unmask();
                if (response.responseText==="0") {
                    var emp_comp2 = Ext.create('widget.companytechnicianannshiptechform', {});
                    emp_comp2.down('form').getForm().findField('compShipId').setValue(values.shipId);
                    emp_comp2.down('form').getForm().findField('dateStart').setValue(form.getForm().findField('dateStart').rawValue);
                    emp_comp2.down('form').getForm().findField('dateEnd').setValue(form.getForm().findField('dateEnd').rawValue);

                    emp_comp2.down('form').getForm().findField('projStartDate').setValue(form.getForm().findField('dateStart').rawValue);

                    var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
                    var diffDays = Math.round(Math.abs((form.getForm().findField('dateStart').value.getTime() - form.getForm().findField('dateEnd').value.getTime())/(oneDay)));
                    emp_comp2.down('form').getForm().findField('projDuration').setValue(diffDays);

                    //emp_comp2.down('form').getForm().findField('projStartDate').minValue=form.getForm().findField('dateStart').value;
                    //emp_comp2.down('form').getForm().findField('projStartDate').maxValue=form.getForm().findField('dateEnd').value;
                    emp_comp2.show();

                    var projscontr = Ext.create('widget.companytechnicianannshipcontr', {});
                    projscontr.items.getAt(0).items.getAt(0).setValue("1. ");
                    Ext.getCmp('sanncontrs').add(projscontr);
                    formWindow.destroy();
                }
                else{

                    Ext.Msg.alert('Μη έγκυρες ημερομηνίες', 'Οι ημερομηνίες έχουν επικάλυψη με άλλη ενεργή αίτηση');

                }

            };
            formWindow.mask("Παρακαλώ Περιμένετε...");
            Ext.Ajax.request({
                url: "/compTaSann/search/countTaSann",
                params: {
                    compShipId: values.shipId,
                    startDate: form.getForm().findField('dateStart').rawValue
                },
                method: "GET",
                callback: successCall
            });
        }
        else{
            Ext.Msg.alert('Μη έγκυρες ημερομηνίες', 'Η ημερομηνία "Από" πρέπει να προηγείται της "Έως"');
        }
    }

});
