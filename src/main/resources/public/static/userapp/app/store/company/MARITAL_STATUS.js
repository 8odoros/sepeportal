/*
 * File: app/store/company/MARITAL_STATUS.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.MARITAL_STATUS', {
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
            storeId: 'company.MARITAL_STATUS',
            autoLoad: false,
            fields: [
                {
                    name: 'description'
                },
                {
                    convert: function(v, rec) {
                        var res = v.split("/");
                        return (res[res.length-1]);
                    },
                    mapping: '_links.self.href',
                    name: 'mid'
                }
            ],
            proxy: {
                type: 'ajax',
                limitParam: '',
                startParam: '',
                url: '/TMaritalStatus',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.TMaritalStatus'
                }
            }
        }, cfg)]);
    }
});