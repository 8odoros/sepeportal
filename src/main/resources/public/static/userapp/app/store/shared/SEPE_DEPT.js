/*
 * File: app/store/shared/SEPE_DEPT.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.shared.SEPE_DEPT', {
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
            storeId: 'shared.SEPE_DEPT',
            autoLoad: false,
            fields: [
                {
                    name: 'cdKind'
                },
                {
                    name: 'cdCode'
                },
                {
                    name: 'cdText',
                    convert: function(v, rec) {
                        return rec.data.cdCode + ' - ' + rec.data.cdText;
                    },
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
                    cdId: null,
                    sort: 'cdCode'
                },
                limitParam: '',
                startParam: '',
                url: '/TSepeDepartment/search/findById',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.TSepeDepartment',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});