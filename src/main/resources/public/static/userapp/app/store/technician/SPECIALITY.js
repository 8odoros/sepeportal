/*
 * File: app/store/technician/SPECIALITY.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.technician.SPECIALITY', {
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
            storeId: 'technician.SPECIALITY',
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
                    name: 'abbr'
                },
                {
                    name: 'spTaspEdulvl'
                },
            ],
            proxy: {
                type: 'ajax',
                limitParam: '',
                startParam: '',
                url: '/taSpeciality/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.taSpeciality'
                }
            }
        }, cfg)]);
    }
});