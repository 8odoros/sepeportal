/*
 * File: app/store/company/DriversPmt/OffDays.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.DriversPmt.OffDays', {
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
            storeId: 'company.DriversPmt.OffDays',
            autoLoad: false,
            fields: [
                {
                    name: 'incNum'
                },
                {
                    name: 'companyid'
                },
                {
                    mapping: '_links.self.href',
                    name: 'url'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    size: 200
                },
                limitParam: '',
                startParam: '',
                url: '/compDriverPmtEntries/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compDriverPmtEntry',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});