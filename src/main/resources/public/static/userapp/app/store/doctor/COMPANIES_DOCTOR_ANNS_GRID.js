/*
 * File: app/store/doctor/COMPANIES_DOCTOR_ANNS_GRID.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.doctor.COMPANIES_DOCTOR_ANNS_GRID', {
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
            remoteSort: true,
            storeId: 'doctor.COMPANIES_DOCTOR_ANNS_GRID',
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
                    name: 'compIeAnnId'
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
                    sort: 'compIeAnnId,desc',
                    ieAnnStatus: 1,
                    reqStatus: 6
                },
                limitParam: '',
                startParam: '',
                url: '/vCompIeAnn/search/findAllNotExpired',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.vCompIeAnn',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});