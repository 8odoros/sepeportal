/*
 * File: app/store/company/TechnicianAnn/ShipDiary.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.TechnicianAnn.ShipDiary', {
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
            storeId: 'company.TechnicianAnn.ShipDiary',
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
                    compTaSannId: null,
                    sort: 'visitDate,asc'
                },
                limitParam: '',
                startParam: '',
                url: '/compTaSannDiaryEntry/search/findByCompTaSannId',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compTaSannDiaryEntr',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});