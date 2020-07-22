/*
 * File: app/view/doctor/DoctorDiary/MonthlyViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.doctor.DoctorDiary.MonthlyViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.doctordoctordiarymonthly',

    onView_DOCTOR_SCHEDULE: function(button, e, eOpts) {
        var form = button.up('toolbar').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        if(form.isValid()){

            formWindow.down('grid').store.proxy.setUrl("/vCompIeAnnDiary/search/findByMonthAndYear?"+"month="+values.month+"&year="+values.year);
            formWindow.down('grid').store.load({callback : function(records, operation, success){
                if(records.length===0){
                    Ext.Msg.alert('Προσοχή', 'Το πρόγραμμα του μήνα είναι κενό.');
                }
            }
        });
    }
    }

});
