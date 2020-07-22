/*
 * File: app/store/technician/COMPANIES_TECHNICIAN_PEND_ANNS_GRID.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.technician.COMPANIES_TECHNICIAN_PEND_ANNS_GRID', {
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
            storeId: 'technician.COMPANIES_TECHNICIAN_PEND_ANNS_GRID',
            autoLoad: false,
            fields: [
                {
                    name: 'subStatus'
                },
                {
                    name: 'reqStatus'
                },
                {
                    name: 'taAnnStatus'
                },
                {
                    convert: function(v, rec) {
                        var res = v.split("/");
                        return res[res.length-1];
                    },
                    mapping: '_links.self.href',
                    name: 'compTaAnnId'
                },
                {
                    convert: function(v, rec) {
                        return rec.get('compFullName')+"   "+rec.get('brAddr')+", "+rec.get('brAddrTk');
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
                    convert: function(v, rec) {
                        var res = v.replace('vCompTaAnn', 'compTaAnn');
                        res += '/compTaAnnDiaryEntries';
                        return res;
                    },
                    mapping: '_links.self.href',
                    name: 'technicianAnnDiaryEntries'
                },
                {
                    convert: function(v, rec) {
                        var res = v.replace('vCompTaAnn', 'compTaAnn');
                        res += '/compTaAnnTaEntries';
                        return res;
                    },
                    mapping: '_links.self.href',
                    name: 'technicianAnnTaEntries'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    size: 10,
                    sort: 'compTaAnnId,desc',
                    taAnnStatus: 0,
                    reqStatus: 5
                },
                limitParam: '',
                startParam: '',
                url: '/vCompTaAnn/search/taFindAll',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.vCompTaAnn',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});