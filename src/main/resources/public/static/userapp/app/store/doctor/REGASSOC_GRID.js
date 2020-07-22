/*
 * File: app/store/doctor/REGASSOC_GRID.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.doctor.REGASSOC_GRID', {
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
            pageSize: 10,
            remoteSort: true,
            storeId: 'doctor.REGASSOC_GRID',
            autoLoad: false,
            fields: [
                {
                    name: 'submitDate'
                },
                {
                    name: 'attachedDocId'
                },
                {
                    name: 'medassocNotifiedId'
                },
                {
                    mapping: '_links.self.href',
                    name: 'url'
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
                    size: 10,
                    sort: 'submitDate,desc'
                },
                limitParam: '',
                startParam: '',
                url: '/doctorCounty/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.doctorCounty',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});