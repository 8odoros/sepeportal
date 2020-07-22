/*
 * File: app/view/company/TechnicianAnn/EmployeeNumFormViewModel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianAnn.EmployeeNumFormViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.companytechnicianannemployeenumform',

    requires: [
        'Ext.data.Store',
        'Ext.data.field.Field'
    ],

    stores: {
        NoneEmployerEmployee: {
            data: [
                {
                    name: 'Εργοδότης',
                    value: 0
                },
                {
                    name: 'Εργαζόμενος',
                    value: 1
                }
            ],
            fields: [
                {
                    name: 'value'
                },
                {
                    name: 'name'
                }
            ]
        }
    }

});