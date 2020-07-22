/*
 * File: app/store/company/Accidents/ACCIDENTS_GRID.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.Accidents.ACCIDENTS_GRID', {
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
            storeId: 'company.Accidents.ACCIDENTS_GRID',
            autoLoad: false,
            fields: [
                {
                    name: 'protNo'
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
                    convert: function(v, rec) {
                        if (v != null)
                            return v.toString();
                        else return "";
                    },
                    name: 'trainingFlag'
                },
                {
                    mapping: '_links.self.href',
                    name: 'url'
                },
                {
                    mapping: '_links.companyAccidentWitnesses.href',
                    name: 'accidentWitnesses'
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
                url: '/companyAccident/search/findAll',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.companyAccident',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});