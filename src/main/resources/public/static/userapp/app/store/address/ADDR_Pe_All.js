/*
 * File: app/store/address/ADDR_Pe_All.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.address.ADDR_Pe_All', {
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
            storeId: 'address.ADDR_Pe_All',
            autoLoad: false,
            fields: [
                {
                    name: 'descr'
                },
                {
                    name: 'perifCode'
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
                limitParam: '',
                startParam: '',
                url: '/TKalPe/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.TKalPe'
                }
            }
        }, cfg)]);
    }
});