/*
 * File: app/store/company/SafetyBooks/Eng.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.SafetyBooks.Eng', {
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
            pageSize: 500,
            remoteSort: true,
            storeId: 'company.SafetyBooks.Eng',
            autoLoad: false,
            fields: [
                {
                    convert: function(v, rec) {
                        return rec.get('firstname')+" "+rec.get('lastname')+" ("+rec.get('afm')+")";
                    },
                    name: 'fullname'
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
                    name: 'engId'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    size: 200
                },
                limitParam: '',
                startParam: '',
                url: '/compSecDiaryEng/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compSecDiaryEng',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});