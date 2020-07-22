/*
 * File: app/view/company/TechnicianAnn/ShipTechViewModel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianAnn.ShipTechViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.companytechnicianannshiptechform',

    requires: [
        'Ext.data.Store',
        'Ext.data.field.Field',
        'Ext.data.proxy.Ajax',
        'Ext.data.reader.Json'
    ],

    stores: {
        TECHNICIAN_SPECIALITY: {
            autoLoad: false,
            fields: [
                {
                    name: 'description'
                },
                {
                    convert: function(v, rec) {
                        var res = v.split("/");
                        return (res[res.length-1]);
                    },
                    mapping: '_links.self.href',
                    name: 'abbr'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    id: null
                },
                limitParam: '',
                startParam: '',
                url: '/taSpeciality/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.taSpeciality'
                }
            }
        },
        Ship_Category: {
            data: [
                {
                    name: 'ΥΨΗΛΗ',
                    value: 1
                },
                {
                    name: 'ΜΕΣΑΙΑ',
                    value: 2
                },
                {
                    name: 'ΧΑΜΗΛΗ',
                    value: 3
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