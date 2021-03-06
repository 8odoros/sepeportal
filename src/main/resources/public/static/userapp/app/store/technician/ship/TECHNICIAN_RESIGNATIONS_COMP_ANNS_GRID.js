/*
 * File: app/store/technician/ship/TECHNICIAN_RESIGNATIONS_COMP_ANNS_GRID.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.technician.ship.TECHNICIAN_RESIGNATIONS_COMP_ANNS_GRID', {
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
            storeId: 'technician.ship.TECHNICIAN_RESIGNATIONS_COMP_ANNS_GRID',
            autoLoad: false,
            fields: [
                {
                    name: 'subStatus'
                },
                {
                    name: 'reqStatus'
                },
                {
                    name: 'taReplaceResponse'
                },
                {
                    convert: function(v, rec) {
                        var res = v.split("/");
                        return res[res.length-1];
                    },
                    mapping: '_links.self.href',
                    name: 'compTaSannId'
                },
                {
                    convert: function(v, rec) {
                        return rec.get('compFullName')+"   "+rec.get('compAddr')+", "+rec.get('compAddrTk')+" - "+rec.get('shipName');
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
                    sort: 'compTaSannId,desc',
                    taSannStatus: 2,
                    reqStatus: 6
                },
                limitParam: '',
                startParam: '',
                url: '/vCompTaSann/search/taFindAll',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.vCompTaSann',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});