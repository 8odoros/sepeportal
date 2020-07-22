/*
 * File: app/store/shared/COOPERATION_TYPE.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.technician.PORT_AUTHORITY', {
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
            storeId: 'technician.PORT_AUTHORITY',
            autoLoad: false,
            fields: [
                {
                    name: 'item'
                },
                {
                    convert: function(v, rec) {
                        var res = v.split("/");
                        return (res[res.length-1]);
                    },
                    mapping: '_links.self.href',
                    name: 'id'
                }
            ],
            proxy: {
                type: 'ajax',
                limitParam: '',
                startParam: '',
                url: '/SpPtlVPortAuth/search/findAll',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.SpPtlVPortAuth'
                }
            }
        }, cfg)]);
    }
});
