/*
 * File: app/store/compScope/rtstakLevel3.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.compScope.rtstakLevel3', {
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
            storeId: 'compScope.rtstakLevel3',
            autoLoad: false,
            fields: [
                {
                    name: 'spRtstackLevel3Desc'
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
                url: '/RtStakLevel3/search/findLevel3',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.RtStakLevel3'
                }
            }
        }, cfg)]);
    }
});