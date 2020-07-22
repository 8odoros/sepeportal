/*
 * File: app/store/company/PTL_TA_ANN_COMP_SH_COMBO.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.PTL_TA_ANN_COMP_SH_COMBO', {
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
            storeId: 'company.PTL_TA_ANN_COMP_SH_COMBO',
            autoLoad: false,
            fields: [
                {
                    mapping: '_links.self.href',
                    name: 'url'
                },
                {
                    name: 'shipName'
                },
                {
                    name: 'shipImo'
                },
                {
                    name: 'shipId'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    size: 250,
                    sort: 'shipId,desc',
                    taSannStatus: 1,
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