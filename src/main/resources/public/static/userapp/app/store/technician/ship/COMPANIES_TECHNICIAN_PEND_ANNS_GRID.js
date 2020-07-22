/*
 * File: app/store/technician/ship/COMPANIES_TECHNICIAN_PEND_ANNS_GRID.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.technician.ship.COMPANIES_TECHNICIAN_PEND_ANNS_GRID', {
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
            storeId: 'technician.ship.COMPANIES_TECHNICIAN_PEND_ANNS_GRID',
            autoLoad: false,
            fields: [
                {
                    name: 'subStatus'
                },
                {
                    name: 'reqStatus'
                },
                {
                    convert: function(v, rec) {
                        var res = v.split("/");
                        return res[res.length-1];
                    },
                    mapping: '_links.self.href',
                    name: 'id'
                },
                {
                    convert: function(v, rec) {
                        return rec.get('compFullName')+"   "+rec.get('compAddr')+", "+rec.get('compAddrTk')+" - "+rec.get('compShipName'); // +" - "+rec.get('shipName')
                    },
                    name: 'compInfoGrid'
                },
                {
                    convert: function(v, rec) {
                        var pD = v.replace(/[^0-9]+/g,' ').split(" ");
                        return (pD[2]+"-"+pD[1]+"-"+pD[0]);
                    },
                    name: 'dateStart'
                },
                {
                    convert: function(v, rec) {
                        var pD = v.replace(/[^0-9]+/g,' ').split(" ");
                        return (pD[2]+"-"+pD[1]+"-"+pD[0]);
                    },
                    name: 'dateEnd'
                },
                {
                    mapping: '_links.compTaSannDiaryEntries.href',
                    name: 'compTaSannDiaryEntries'
                },
                {
                    mapping: '_links.compTaSannContrs.href',
                    name: 'compTaSannContrs'
                },
                {
                    name: 'taSannStatus'
                },
                {
                    mapping: '_links.self.href',
                    name: 'url'
                },
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    size: 10,
                    sort: 'id,desc',
                    taSannStatus: 0,
                    reqStatus: 5
                },
                limitParam: '',
                startParam: '',
                url: '/compTaSann/search/findTaByCompShipId/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compTaSann',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});