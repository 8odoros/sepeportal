/*
 * File: app/store/company/TechnicianBooks/SHIPS_BOOKS.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.TechnicianBooks.SHIPS_BOOKS', {
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
            storeId: 'company.TechnicianBooks.SHIPS_BOOKS',
            autoLoad: false,
            fields: [
                {
                    mapping: '_links.self.href',
                    name: 'url'
                },
                {
                    name: 'descr'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    size: 10,
                    sort: 'compShipId,desc'
                },
                limitParam: '',
                startParam: '',
                url: '/compTaSannBook/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compTaSannBook',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});