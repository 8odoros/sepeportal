/*
 * File: app/store/company/CompanyAdmin/USERPREV.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.CompanyAdmin.USERPREV', {
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
            storeId: 'company.CompanyAdmin.USERPREV',
            autoLoad: false,
            autoSync: true,
            fields: [
                {
                    name: 'userId'
                },
                {
                    name: 'branchIds'
                },
                {
                    name: 'privilagesIds'
                },
                {
                    name: 'active'
                }
            ],
            proxy: {
                type: 'rest',
                url: '/companyUsersPrevs/',
                reader: {
                    type: 'json'
                }
            }
        }, cfg)]);
    }
});