/*
 * File: app/store/company/DisputeNegs/DISPUTENEGS_GRID.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.DisputeNegs.DISPUTENEGS_GRID', {
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
            storeId: 'company.DisputeNegs.DISPUTENEGS_GRID',
            autoLoad: false,
            fields: [
                {
                    name: 'descr'
                },
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
                    mapping: '_links.self.href',
                    name: 'url'
                },
                {
                    allowNull: true,
                    name: 'StatusMsg'
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
                url: '/compDisputeNeg/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compDisputeNeg',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});