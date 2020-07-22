/*
 * File: app/store/address1/ADDR_D1.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.address1.ADDR_D1', {
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
            storeId: 'address1.ADDR_D1',
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