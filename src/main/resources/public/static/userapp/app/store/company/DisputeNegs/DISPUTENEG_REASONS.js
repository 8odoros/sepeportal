/*
 * File: app/store/company/DisputeNegs/DISPUTENEG_REASONS.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.DisputeNegs.DISPUTENEG_REASONS', {
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
            storeId: 'company.DisputeNegs.DISPUTENEG_REASONS',
            autoLoad: false,
            fields: [
                {
                    name: 'spRlsDetailDesc'
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
                url: '/tEmployeeDisputeSubjects',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.tEmployeeDisputeSubjects'
                }
            }
        }, cfg)]);
    }
});