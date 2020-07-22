/*
 * File: app/store/address2/ADDR_K2.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.address2.ADDR_K2', {
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
            storeId: 'address2.ADDR_K2',
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