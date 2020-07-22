/*
 * File: app/store/employee/COMPL_TYPES.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.employee.COMPL_TYPES', {
    extend: 'Ext.data.Store',

    requires: [
        'Ext.data.field.Field'
    ],

    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            storeId: 'employee.COMPL_TYPES',
            data: [
                {
                    abbr: 2,
                    name: 'ΑΝΩΝΥΜΗ'
                },
                {
                    abbr: 1,
                    name: 'ΕΠΩΝΥΜΗ'
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