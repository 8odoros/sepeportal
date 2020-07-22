/*
 * File: app/view/company/TechnicianAnn/ShipContrViewModel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianAnn.ShipContrViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.companytechnicianannshipcontr',

    requires: [
        'Ext.data.Store',
        'Ext.data.field.Field',
        'Ext.data.proxy.Ajax',
        'Ext.data.reader.Json'
    ],

    stores: {
        Contr_Type: {
            data: [
                {
                    abbr: 1,
                    name: 'ΥΠΕΡΓΟΛΑΒΟΣ'
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
        },
        Contr_Speciality: {
            fields: [
                {
                    convert: function(v, rec) {
                        var res = v.split("/");
                        return (res[res.length-1]);
                    },
                    mapping: '_links.self.href',
                    name: 'abbr'
                },
                {
                    name: 'description'
                }
            ],
            proxy: {
                type: 'ajax',
                limitParam: '',
                startParam: '',
                url: '/contrSpeciality/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.contrSpeciality',
                    totalProperty: 'page.totalElements'
                }
            }
        }
    }

});