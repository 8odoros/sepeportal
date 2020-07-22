/*
 * File: app/store/address/ADDR_K.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.address.ADDR_K', {
    extend: 'Ext.data.Store',

    requires: [
        'Ext.data.field.Field',
        'Ext.data.proxy.Rest',
        'Ext.data.reader.Json'
    ],

    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            storeId: 'address.ADDR_K',
            autoLoad: false,
            fields: [
                {
                    name: 'koinDescr'
                },
                {
                    convert: function(v, rec) {
                        var res = v.split("/");
                        return (res[res.length-1]);
                    },
                    mapping: '_links.self.href',
                    name: 'abbr'
                },
                {
                    name: 'peCode'
                },
                {
                    name: 'pCode'
                },
                {
                    name: 'dCode'
                }
            ],
            proxy: {
                type: 'rest',
                limitParam: '',
                startParam: '',
                url: '/TKalK/search/findByDimosCode',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.TKalK'
                }
            }
        }, cfg)]);
    }
});