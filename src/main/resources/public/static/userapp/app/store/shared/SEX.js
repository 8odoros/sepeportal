/*
 * File: app/store/shared/SEX.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.shared.SEX', {
    extend: 'Ext.data.Store',

    requires: [
        'Ext.data.field.Field'
    ],

    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            storeId: 'shared.SEX',
            data: [
                {
                    abbr: 0,
                    name: 'ΑΡΡΕΝ'
                },
                {
                    abbr: 1,
                    name: 'ΘΗΛΥ'
                }
            ],
            fields: [
                {
                    name: 'abbr'
                },
                {
                    name: 'name'
                }
            ]
        }, cfg)]);
    }
});