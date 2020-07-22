/*
 * File: app/store/company/TechnicianAnn/EXYPP.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.TechnicianAnn.LocalTaEntries', {
    extend: 'Ext.data.Store',

    requires: [
        'Ext.data.field.Field'
    ],

    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            storeId: 'company.TechnicianAnn.LocalTaEntries',
            autoLoad: false,
            fields: [
                {
                    name: 'id'
                },
                {
                    name: 'name'
                }
            ]
        }, cfg)]);
    }
});