/*
 * File: app/store/compScope/rtstakLevel1.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.compScope.rtstakLevel1', {
    extend: 'Ext.data.Store',

    requires: [
        'Ext.data.field.String',
        'Ext.data.proxy.Ajax',
        'Ext.data.reader.Json'
    ],

    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            storeId: 'compScope.rtstakLevel1',
            autoLoad: false,
            fields: [
                {
                    name: 'spRtstackLevel1Desc'
                },
                {
                    type: 'string',
                    convert: function(v, rec) {
                        var res = v.split("/");
                        return decodeURIComponent((res[res.length-1]));
                    },
                    mapping: '_links.self.href',
                    name: 'abbr'
                }
            ],
            proxy: {
                type: 'ajax',
                limitParam: '',
                startParam: '',
                url: '/RtStakLevel1',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.RtStakLevel1'
                }
            }
        }, cfg)]);
    }
});