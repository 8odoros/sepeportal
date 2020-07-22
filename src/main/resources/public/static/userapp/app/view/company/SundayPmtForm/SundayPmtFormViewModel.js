/*
 * File: app/view/company/SundayPmtForm/SundayPmtFormViewModel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.SundayPmtForm.SundayPmtFormViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.companysundaypmtformsundaypmtform',

    requires: [
        'Ext.data.Store',
        'Ext.data.field.Field',
        'Ext.data.proxy.Rest',
        'Ext.data.reader.Json'
    ],

    stores: {
        HOLIDAY_DATES: {
            data: [
                {
                    abbr: 1,
                    name: '01-05-2017'
                },
                {
                    abbr: 2,
                    name: '25-12-2017'
                },
                {
                    abbr: 3,
                    name: '06-01-2018'
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