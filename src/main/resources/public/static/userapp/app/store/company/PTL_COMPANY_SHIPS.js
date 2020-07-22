/*
 * File: app/store/company/PTL_COMPANY_SHIPS.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.PTL_COMPANY_SHIPS', {
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
            storeId: 'company.PTL_COMPANY_SHIPS',
            autoLoad: false,
            fields: [
                {
                    mapping: '_links.self.href',
                    name: 'url'
                },
                {
                    convert: function(v, rec) {
                        var res = v.split("/");
                        return (res[res.length-1]);
                    },
                    mapping: '_links.self.href',
                    name: 'shipId'
                },
                {
                    name: 'shipName'
                },
                {
                    name: 'shipImo'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    size: 10
                },
                limitParam: '',
                startParam: '',
                url: '/compShip/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compShip',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});