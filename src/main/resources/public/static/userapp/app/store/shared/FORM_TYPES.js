/*
 * File: app/store/shared/FORM_TYPES.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.shared.FORM_TYPES', {
    extend: 'Ext.data.Store',

    requires: [
        'Ext.data.field.Field'
    ],

    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            storeId: 'shared.FORM_TYPES',
            data: [
                {
                    abbr: 1,
                    name: 'Αίτηση Εργαζομένου'
                },
                {
                    abbr: 2,
                    name: 'Αίτηση Εργοδότη'
                },
                {
                    abbr: 3,
                    name: 'Αίτηση Σωματείου'
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