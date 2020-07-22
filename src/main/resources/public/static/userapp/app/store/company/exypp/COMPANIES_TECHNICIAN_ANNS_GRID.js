/*
 * File: app.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.exypp.COMPANIES_TECHNICIAN_ANNS_GRID', {
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
            pageSize: 1000,
            //remoteSort: true,
            storeId: 'company.exypp.COMPANIES_TECHNICIAN_ANNS_GRID',
            autoLoad: false,
            fields: [
                {
                    name: 'subStatus'
                },
                {
                    convert: function(v, rec) {
                        return rec.get('compFullName')+"   "+rec.get('brAddr')+", "+rec.get('brAddrTk');
                    },
                    name: 'compInfoGrid'
                },
                {
                    name: 'reqStatus'
                },
                {
                    name: 'ptlBranchId'
                },
                {
                    mapping: '_links.self.href',
                    name: 'url'
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
                    name: 'dateStart'
                },
                {
                    name: 'dateEnd'
                },
                {
                    name: 'categANum'
                },
                {
                    name: 'categBNum'
                },
                {
                    name: 'categCNum'
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
                    taAnnStatus: 1,
                    reqStatus: 6
                },
                limitParam: '',
                startParam: '',
                url: '/vCompTaAnn/search/findAllNotExpired',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.vCompTaAnn',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});