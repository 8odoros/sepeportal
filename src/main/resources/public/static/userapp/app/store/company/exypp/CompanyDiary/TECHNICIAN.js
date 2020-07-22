/*
 * File: app/store/technician/CompanyDiary/DIARY.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.exypp.CompanyDiary.TECHNICIAN', {
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
            storeId: 'company.exypp.CompanyDiary.TECHNICIAN',
            autoLoad: false,
            fields: [
                {
                    name: 'firstname'
                },
                {
                    name: 'lastname'
                },
                {
                    name: 'afm'
                },
                {
                    name: 'username'
                },
                {
                    mapping: '_links.self.href',
                    name: 'url'
                }
            ],
            proxy: {
                type: 'ajax',
                limitParam: '',
                startParam: '',
                url: '/SpPtlVUserSafetyTechnician/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.SpPtlVUserSafetyTechnician'
                }
            }
        }, cfg)]);
    }
});