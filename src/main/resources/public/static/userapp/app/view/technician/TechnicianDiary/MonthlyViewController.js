/*
 * File: app/view/technician/TechnicianDiary/MonthlyViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.technician.TechnicianDiary.MonthlyViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.techniciantechniciandiarymonthly',

    onView_TECH_SCHEDULE: function(button, e, eOpts) {
        var form = button.up('toolbar').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        if(form.isValid()){

            formWindow.mask("Παρακαλώ Περιμένετε...");
            var month = values.month;
            var year = values.year;
            formWindow.down('grid').store.proxy.setUrl("/vCompTaAnnDiary/search/findByMonthAndYear?"+"month="+month+"&year="+year);
            formWindow.down('grid').store.load({callback : function(records, operation, success){
                formWindow.unmask();
                if(records.length===0){
                    Ext.Msg.alert('Προσοχή', 'Το πρόγραμμα του μήνα είναι κενό.');
                }
            }
        });
    }
    }

});
