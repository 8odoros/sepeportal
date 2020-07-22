/*
 * File: app/store/compScope/rtstakLevel4.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.compScope.rtstakLevel4', {
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
            storeId: 'compScope.rtstakLevel4',
            autoLoad: false,
            fields: [
                {
                    name: 'spRtstackLevel4Desc'
                },
                {
                    convert: function(v, rec) {
                        var res = v.split("/");
                        return (res[res.length-1]);
                    },
                    mapping: '_links.self.href',
                    name: 'abbr'
                }
            ],
            proxy: {
                type: 'ajax',
                limitParam: '',
                startParam: '',
                url: '/RtStakLevel4/search/findLevel4',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.RtStakLevel4'
                }
            }
        }, cfg)]);
    }
});