/*
 * File: app/store/company/SPECIALTY.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.SPECIALTY', {
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
            storeId: 'company.SPECIALTY',
            autoLoad: false,
            fields: [
                {
                    name: 'spRtspDescription'
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
                extraParams: {
                    size: 25,
                    sort: 'spRtspDescription'
                },
                limitParam: '',
                startParam: '',
                url: '/TEmployeeSpeciality/search/findByDescr',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.TEmployeeSpeciality',
                    totalProperty: 'page.totalElements'
                }
            },
        }, cfg)]);
    }
});