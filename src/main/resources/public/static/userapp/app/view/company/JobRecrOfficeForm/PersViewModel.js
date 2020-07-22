/*
 * File: app/view/company/JobRecrOfficeForm/PersViewModel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.JobRecrOfficeForm.PersViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.companyjobrecrofficeformpers',

    requires: [
        'Ext.data.Store',
        'Ext.data.field.Field'
    ],

    stores: {
        Pers_Category: {
            data: [
                {
                    abbr: 1,
                    name: 'ΑΝΕΡΓΟΣ'
                },
                {
                    abbr: 2,
                    name: 'ΕΡΓΑΖΟΜΕΝΟΣ'
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