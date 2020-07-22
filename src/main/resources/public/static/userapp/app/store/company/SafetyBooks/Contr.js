/*
 * File: app/store/company/SafetyBooks/Contr.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.SafetyBooks.Contr', {
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
            storeId: 'company.SafetyBooks.Contr',
            autoLoad: false,
            fields: [
                {
                    name: 'afm'
                },
                {
                    name: 'companyid'
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
                    name: 'contrId'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    size: 200
                },
                limitParam: '',
                startParam: '',
                url: '/compSecDiaryContr/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compSecDiaryContr',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});