/*
 * File: app/store/company/RECURRENCY.js
 *
 * Created by Dimitris F.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.RECURRENCY', {
    extend: 'Ext.data.Store',

    requires: [
        'Ext.data.field.Field'
    ],

    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            storeId: 'company.RECURRENCY',
            data: [
                {
                    abbr: 0,
                    name: 'Κάθε μέρα'
                },
                {
                    abbr: 1,
                    name: 'Κάθε εβδομάδα'
                },
                {
                    abbr: 2,
                    name: 'Κάθε 2 εβδομάδες'
                },
                {
                    abbr: 3,
                    name: 'Κάθε μήνα'
                },
                {
                    abbr: 4,
                    name: 'Συγκεκριμένη μέρα κάθε μήνα'
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