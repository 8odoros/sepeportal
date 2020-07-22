/*
 * File: app/store/company/TechnicianAnn/Diary.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.TechnicianAnn.Diary', {
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
            pageSize: 100,
            remoteSort: true,
            storeId: 'company.TechnicianAnn.Diary',
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
                    sort: 'visitDate,asc'
                },
                limitParam: '',
                startParam: '',
                url: '/compTaAnnDiaryEntry/search/findByCompTaAnnId',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compTaAnnDiaryEntry',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});