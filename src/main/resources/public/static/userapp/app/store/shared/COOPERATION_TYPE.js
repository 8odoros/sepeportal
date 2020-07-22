/*
 * File: app/store/shared/COOPERATION_TYPE.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.shared.COOPERATION_TYPE', {
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
            storeId: 'shared.COOPERATION_TYPE',
            autoLoad: false,
            fields: [
                {
                    name: 'description'
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
                extraParams: {
                    id: null
                },
                limitParam: '',
                startParam: '',
                url: '/cooperationType/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.cooperationType'
                }
            }
        }, cfg)]);
    }
});