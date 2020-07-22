/*
 * File: app/view/company/DisputeNegFormViewModel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.DisputeNegFormViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.companydisputenegform',

    requires: [
        'Ext.data.Store',
        'Ext.data.field.Field'
    ],

    stores: {
        category: {
            data: [
                {
                    abbr: 2,
                    name: 'Συμφιλιωτική Διαδικασία'
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