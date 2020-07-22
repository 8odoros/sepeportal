/*
 * File: app/view/technician/MainViewViewModel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.technician.MainViewViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.technicianmainview',

    requires: [
        'Ext.data.Store',
        'Ext.data.field.Field'
    ],

    stores: {
        TA_STATUS: {
            data: [
                {
                    abbr: 1,
                    name: 'ΠΛΟΙΑ'
                },
                {
                    abbr: 2,
                    name: 'ΕΓΚΑΤΑΣΤΑΣΕΙΣ'
                }
            ],
            fields: [
                {
                    name: 'abbr'
                },
                {
                    name: 'name'
                }
            ]
        }
    }

});