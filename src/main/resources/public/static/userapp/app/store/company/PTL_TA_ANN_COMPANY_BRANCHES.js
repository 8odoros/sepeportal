/*
 * File: app/store/company/PTL_TA_ANN_COMPANY_BRANCHES.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.PTL_TA_ANN_COMPANY_BRANCHES', {
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
            storeId: 'company.PTL_TA_ANN_COMPANY_BRANCHES',
            autoLoad: false,
            fields: [
                {
                    mapping: '_links.self.href',
                    name: 'url'
                },
                {
                    name: 'brDescr'
                },
                {
                    name: 'ptlBranchId'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    size: 1000,
                    sort: [
                        'taAnnStatus,desc',
                        'reqStatus,asc'
                    ]
                },
                limitParam: '',
                startParam: '',
                url: '/vCompTaAnn',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.vCompTaAnn',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});