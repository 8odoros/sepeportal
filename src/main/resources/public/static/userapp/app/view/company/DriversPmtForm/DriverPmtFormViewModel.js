/*
 * File: app/view/company/DriversPmtForm/DriverPmtFormViewModel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.DriversPmtForm.DriverPmtFormViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.companydriverspmtformdriverpmtform',

    requires: [
        'Ext.data.Store',
        'Ext.data.field.Field'
    ],

    stores: {
        Months: {
            data: [
                {
                    name: 'Ιανουάριος',
                    value: 1
                },
                {
                    name: 'Φεβρουάριος',
                    value: 2
                },
                {
                    name: 'Μάρτιος',
                    value: 3
                },
                {
                    name: 'Απρίλιος',
                    value: 4
                },
                {
                    name: 'Μάιος',
                    value: 5
                },
                {
                    name: 'Ιούνιος',
                    value: 6
                },
                {
                    name: 'Ιούλιος',
                    value: 7
                },
                {
                    name: 'Αύγουστος',
                    value: 8
                },
                {
                    name: 'Σεπτέμβριος',
                    value: 9
                },
                {
                    name: 'Οκτώβριος',
                    value: 10
                },
                {
                    name: 'Νοέμβριος',
                    value: 11
                },
                {
                    name: 'Δεκέμβριος',
                    value: 12
                }
            ],
            fields: [
                {
                    name: 'name'
                },
                {
                    name: 'value'
                }
            ]
        }
    }

});