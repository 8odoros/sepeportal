/*
 * File: app/store/company/TechnicianAnn/TECHNICIAN_SHIPS_ANNS_GRID.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.TechnicianAnn.TECHNICIAN_SHIPS_ANNS_GRID', {
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
            storeId: 'company.TechnicianAnn.TECHNICIAN_SHIPS_ANNS_GRID',
            autoLoad: false,
            fields: [
                {
                    name: 'protNo'
                },
                {
                    mapping: '_links.compTaSannDiaryEntries.href',
                    name: 'compTaSannDiaryEntries'
                },
                {
                    name: 'protDate'
                },
                {
                    name: 'subStatus'
                },
                {
                    name: 'reqStatus'
                },
                {
                    name: 'taSannStatus'
                },
                {
                    name: 'dateStart'
                },
                {
                    name: 'dateEnd'
                },
                {
                    mapping: '_links.self.href',
                    name: 'url'
                },
                {
                    mapping: '_links.compTaSannContrs.href',
                    name: 'compTaSannContrs'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    size: 10,
                    sort: 'protDate,desc'
                },
                limitParam: '',
                startParam: '',
                url: '/compTaSann/search/findByCompShipId/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compTaSann',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});