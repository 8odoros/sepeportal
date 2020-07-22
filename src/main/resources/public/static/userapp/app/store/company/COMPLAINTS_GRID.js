/*
 * File: app/store/company/COMPLAINTS_GRID.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.COMPLAINTS_GRID', {
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
            storeId: 'company.COMPLAINTS_GRID',
            autoLoad: false,
            fields: [
                {
                    name: 'complDescr'
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
                    name: 'empSexDesc'
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
                url: '/compComplaint/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compComplaint',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});