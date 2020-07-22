/*
 * File: app/store/company/PROJECT_TYPE.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.PROJECT_TYPE', {
    extend: 'Ext.data.Store',

    requires: [
        'Ext.data.field.Field'
    ],

    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            storeId: 'company.PROJECT_TYPE',
            data: [
                {
                    abbr: 1,
                    name: 'ΔΗΜΟΣΙΟ ΕΡΓΟ'
                },
                {
                    abbr: 2,
                    name: 'ΙΔΙΩΤΙΚΟ ΕΡΓΟ'
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