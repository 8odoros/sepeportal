/*
 * File: app/store/doctor/MEDICAL_ASSOC.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.doctor.MEDICAL_ASSOC', {
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
            storeId: 'doctor.MEDICAL_ASSOC',
            autoLoad: false,
            fields: [
                {
                    name: 'spMedasDescription'
                },
                {
                    name: 'spMedasAddress'
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
                url: '/medicalAsso/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.medicalAsso'
                }
            }
        }, cfg)]);
    }
});