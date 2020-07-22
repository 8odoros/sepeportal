/*
 * File: app/store/company/DANGERS_BRANCHES.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.DANGERS_BRANCHES', {
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
            storeId: 'company.DANGERS_BRANCHES',
            autoLoad: false,
            fields: [
                {
                    mapping: '_links.self.href',
                    name: 'url'
                },
                {
                    name: 'dateUpdated'
                }
            ],
            proxy: {
                type: 'ajax',
                limitParam: '',
                startParam: '',
                url: '/compDangerAssess/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compDangerAssess',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});