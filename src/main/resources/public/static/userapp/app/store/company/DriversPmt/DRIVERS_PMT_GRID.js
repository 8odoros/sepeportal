/*
 * File: app/store/company/DriversPmt/DRIVERS_PMT_GRID.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.DriversPmt.DRIVERS_PMT_GRID', {
    extend: 'Ext.data.Store',

    requires: [
        'Ext.data.field.Field',
        'Ext.data.proxy.Ajax',
        'Ext.data.reader.Json'
    ],

    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            pageSize: 10,
            remoteSort: true,
            storeId: 'company.DriversPmt.DRIVERS_PMT_GRID',
            autoLoad: false,
            fields: [
                {
                    name: 'protNo'
                },
                {
                    name: 'protDate'
                },
                {
                    name: 'subStatus'
                },
                {
                    name: 'reqStatus'
                },
                {
                    mapping: '_links.self.href',
                    name: 'url'
                },
                {
                    mapping: '_links.compDriverPmtEntries.href',
                    name: 'driversPmtOffDays'
                },
                {
                    convert: function(v, rec) {
                        return rec.get('ownerFirstname')+" "+rec.get('ownerLastname');
                    },
                    name: 'driversNameSurname'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    size: 10,
                    sort: 'protDate,desc'
                },
                limitParam: '',
                startParam: '',
                url: '/compDriverPmt/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compDriverPmt',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});