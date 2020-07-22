/*
 * File: app/store/address/ADDR_D.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.address.ADDR_D', {
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
            storeId: 'address.ADDR_D',
            autoLoad: false,
            fields: [
                {
                    name: 'dimosDescr'
                },
                {
                    name: 'enotCode'
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
                url: '/TKalD/search/findByEnotCode',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.TKalD'
                }
            }
        }, cfg)]);
    }
});