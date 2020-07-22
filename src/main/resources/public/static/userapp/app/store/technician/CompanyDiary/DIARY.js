/*
 * File: app/store/technician/CompanyDiary/DIARY.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.technician.CompanyDiary.DIARY', {
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
            storeId: 'technician.CompanyDiary.DIARY',
            autoLoad: false,
            fields: [
                {
                    name: 'visitDate'
                },
                {
                    name: 'visitTime'
                },
                {
                    convert: function(v, rec) {
                        var tms = parseInt(v);
                        var hours1 = Math.floor(tms/60);
                        var minutes1 = tms - (hours1*60);
                        var textR = hours1.toString()+ " ώρες και " +minutes1.toString()+ " λεπτά.";
                        return (textR);
                    },
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
                    size: 20,
                    sort: [
                        'visitDate,asc',
                        'visitTime,asc'
                    ]
                },
                limitParam: '',
                startParam: '',
                url: '/vCompTaAnnDiary/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.vCompTaAnnDiary',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});