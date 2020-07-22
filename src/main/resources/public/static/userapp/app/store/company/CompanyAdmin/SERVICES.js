/*
 * File: app/store/company/CompanyAdmin/SERVICES.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.CompanyAdmin.SERVICES', {
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
            storeId: 'company.CompanyAdmin.SERVICES',
            autoLoad: false,
            autoSync: true,
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
                    name: 'abbr'
                }
            ],
            proxy: {
                type: 'rest',
                url: '/SpPtlCompanyServices/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.SpPtlCompanyServices'
                }
            }
        }, cfg)]);
    }
});