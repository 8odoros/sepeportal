/*
 * File: app/store/company/DoctorAnn/Diary.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.DoctorAnn.Diary', {
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
            storeId: 'company.DoctorAnn.Diary',
            autoLoad: false,
            fields: [
                {
                    name: 'incNum'
                },
                {
                    name: 'companyid'
                },
                {
                    mapping: '_links.self.href',
                    name: 'url'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    size: 200,
                    compIeAnnId: null,
                    sort: [
                        'visitDate,asc',
                        'visitTime,asc'
                    ]
                },
                limitParam: '',
                startParam: '',
                url: '/compIeAnnDiaryEntry/search/findByCompIeAnnId',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compIeAnnDiaryEntry',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});