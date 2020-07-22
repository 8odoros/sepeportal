/*
 * File: app/view/company/MainViewViewModel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.MainViewViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.companymainview',

    requires: [
        'Ext.data.Store',
        'Ext.data.field.Field'
    ],

    stores: {
        TA_STATUS: {
            data: [
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