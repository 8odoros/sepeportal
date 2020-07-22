/*
 * File: app/store/shared/CASE_MESSAGE.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.shared.CASE_MESSAGE', {
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
            storeId: 'shared.CASE_MESSAGE',
            autoLoad: false,
            fields: [
                {
                    name: 'spProtDocId'
                },
                {
                    name: 'spProtStatusCode'
                },
                {
                    name: 'cdText'
                },
                {
                    name: 'spPtotStatus'
                },
                {
                    convert: function(v, rec) {
                        var res = v.split("/");
                        return (res[res.length-1]);
                    },
                    mapping: '_links.self.href',
                    name: 'caseId'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    caseId: null,
                    docId: null
                },
                limitParam: '',
                startParam: '',
                url: '/TProtocol/search/findByDocIdCaseId',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.TProtocol'
                }
            }
        }, cfg)]);
    }
});