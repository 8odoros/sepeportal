/*
 * File: app/store/compScope/rtstakLevel5.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.compScope.rtstakLevel5', {
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
            storeId: 'compScope.rtstakLevel5',
            autoLoad: false,
            fields: [
                {
                    name: 'spRtstackLevel5Desc'
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
                url: '/RtStakLevel5/search/findLevel5',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.RtStakLevel5'
                }
            }
        }, cfg)]);
    }
});