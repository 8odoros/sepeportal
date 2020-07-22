/*
 * File: app/store/company/SundayPmt/SUNDAY_PMT_GRID.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.SundayPmt.SUNDAY_PMT_GRID', {
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
            storeId: 'company.SundayPmt.SUNDAY_PMT_GRID',
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
                    name: 'sepeDept'
                },
                {
                    name: 'cdText'
                },
                {
                    name: 'sundayDate'
                },
                {
                    name: 'holidayDate'
                },
                {
                    name: 'compAddr'
                },
                {
                    mapping: '_links.self.href',
                    name: 'url'
                },
                {
                    mapping: '_links.compSundayPmtPers.href',
                    name: 'sundayPmtPers'
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
                url: '/compSundayPmtExt',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compSundayPmtExt',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});