/*
 * File: app/view/company/VehiclesBooks/VehiclesFormViewModel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.VehiclesBooks.VehiclesFormViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.companyvehiclesbooksvehiclesform',

    requires: [
        'Ext.data.Store',
        'Ext.data.field.Field'
    ],

    stores: {
        VEHICLE_TYPES: {
            autoLoad: false,
            data: [
                {
                    description: 'Τουριστικό Λεωφορείο',
                    abbr: 1
                },
                {
                    description: 'Φορτηγό Όχημα',
                    abbr: 2
                }
            ],
            fields: [
                {
                    name: 'description'
                },
                {
                    name: 'abbr'
                }
            ]
        }
    }

});