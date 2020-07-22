/*
 * File: app/view/company/ProjectAnnForm/ContrViewModel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.ProjectAnnForm.ContrViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.companyprojectannformcontr',

    requires: [
        'Ext.data.Store',
        'Ext.data.field.Field'
    ],

    stores: {
        Contr_Type: {
            data: [
                {
                    abbr: 1,
                    name: 'ΑΝΑΔΟΧΟΣ'
                },
                {
                    abbr: 2,
                    name: 'ΕΡΓΟΛΑΒΟΣ'
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