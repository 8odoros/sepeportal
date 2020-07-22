/*
 * File: app/store/company/SafetyBooks/BOOK_NOTES.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.SafetyBooks.BOOK_NOTES', {
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
            storeId: 'company.SafetyBooks.BOOK_NOTES',
            autoLoad: false,
            fields: [
                {
                    name: 'compSecDiaryEngId'
                },
                {
                    name: 'creationDate'
                },
                {
                    name: 'updateDate'
                },
                {
                    mapping: '_links.self.href',
                    name: 'url'
                },
                {
                    convert: function(v, rec) {
                        var res = v.split("/");
                        return (res[res.length-1]);
                    },
                    mapping: '_links.self.href',
                    name: 'noteId'
                },
                {
                    name: 'aa'
                },
                {
                    name: 'compSecDiaryId'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    size: 10,
                    sort: 'creationDate,desc'
                },
                limitParam: '',
                startParam: '',
                url: '/compSecDiaryEntry/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compSecDiaryEntry',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});