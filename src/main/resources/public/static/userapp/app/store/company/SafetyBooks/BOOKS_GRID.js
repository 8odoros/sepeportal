/*
 * File: app/store/company/SafetyBooks/BOOKS_GRID.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.SafetyBooks.BOOKS_GRID', {
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
            storeId: 'company.SafetyBooks.BOOKS_GRID',
            autoLoad: false,
            fields: [
                {
                    name: 'subStatus'
                },
                {
                    name: 'reqStatus'
                },
                {
                    name: 'protNo'
                },
                {
                    mapping: '_links.self.href',
                    name: 'url'
                },
                {
                    mapping: '_links.compSecDiaryContrs.href',
                    name: 'safetyBookContrs'
                },
                {
                    mapping: '_links.compSecDiaryEngs.href',
                    name: 'safetyBookEngs'
                },
                {
                    mapping: '_links.compSecDiaryEntries.href',
                    name: 'safetyBookNotes'
                },
                {
                    name: 'protDate'
                },
                {
                    convert: function(v, rec) {
                        var res = v.split("/");
                        return (res[res.length-1]);
                    },
                    mapping: '_links.self.href',
                    name: 'bookId'
                },
                {
                    name: 'projLicenceNum'
                },
                {
                    name: 'projAddr'
                },
                {
                    name: 'projAddrTk'
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
                url: '/compSecDiary/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compSecDiary',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});