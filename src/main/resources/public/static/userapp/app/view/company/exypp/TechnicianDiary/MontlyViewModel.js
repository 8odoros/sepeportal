/*
 * File: app.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.exypp.TechnicianDiary.MontlyViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.companyexypptechniciandiarymonthly',

    requires: [
        'Ext.data.Store',
        'Ext.data.field.Field',
        'Ext.data.proxy.Ajax',
        'Ext.data.reader.Json',
        'Ext.util.Grouper'
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
        },
        MonthlyDiary: {
            remoteSort: true,
            autoLoad: false,
            fields: [
                {
                    convert: function(v, rec) {
                        var pD = v.replace(/[^0-9]+/g,' ').split(" ");
                        return (pD[2]+"-"+pD[1]+"-"+pD[0]);
                    },
                    name: 'visitDate'
                },
                {
                    name: 'visitTime'
                },
                {
                    name: 'visitDurationMinutes'
                },
                {
                    convert: function(v, rec) {
                        return rec.get("compFullName")+", "+rec.get("brAddr")+", "+rec.get("brAddrTk");
                    },
                    name: 'compGridName'
                },
                {
                    mapping: '_links.self.href',
                    name: 'url'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    size: 1000,
                    sort: [
                        'visitDate,asc',
                        'visitTime,asc'
                    ]
                },
                limitParam: '',
                startParam: '',
                url: '/vCompTaAnnDiary/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.vCompTaAnnDiary',
                    totalProperty: 'page.totalElements'
                }
            },
            grouper: {
                property: 'visitDate',
                sortProperty: 'visitDate'
            }
        }
    }

});