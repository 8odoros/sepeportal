/*
 * File: app/store/doctor/CompanyDiary/DIARY_ALL.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.doctor.CompanyDiary.DIARY_ALL', {
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
            storeId: 'doctor.CompanyDiary.DIARY_ALL',
            autoLoad: false,
            fields: [
                {
                    name: 'visitDate'
                },
                {
                    name: 'visitTime'
                },
                {
                    name: 'visitDurationMinutes'
                },
                {
                    name: 'companyId'
                },
                {
                    mapping: '_links.self.href',
                    name: 'url'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    size: 1000,
                    sort: [
                        'visitDate,asc',
                        'visitTime,asc'
                    ]
                },
                limitParam: '',
                startParam: '',
                url: '/vCompIeAnnDiary/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.vCompIeAnnDiary',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});