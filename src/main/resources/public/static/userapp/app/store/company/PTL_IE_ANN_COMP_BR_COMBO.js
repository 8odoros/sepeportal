/*
 * File: app/store/company/PTL_IE_ANN_COMP_BR_COMBO.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.PTL_IE_ANN_COMP_BR_COMBO', {
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
            remoteSort: true,
            storeId: 'company.PTL_IE_ANN_COMP_BR_COMBO',
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
                    size: 250,
                    sort: 'ptlBranchId,desc',
                    ieAnnStatus: 1,
                    reqStatus: 6
                },
                limitParam: '',
                startParam: '',
                url: '/vCompIeAnn/search/ieFindAll',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.vCompIeAnn',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});