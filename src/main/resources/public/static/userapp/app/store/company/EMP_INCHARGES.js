/*
 * File: app/store/company/EMP_INCHARGES.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.EMP_INCHARGES', {
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
            storeId: 'company.EMP_INCHARGES',
            autoLoad: false,
            fields: [
                {
                    name: 'rgEinTaxationNumber'
                },
                {
                    convert: function(v, rec) {
                        return rec.get('rgEinName')+" "+rec.get('rgEinSurname')+" ("+rec.get('rgEinTaxationNumber')+")";
                    },
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
                    size: 25
                },
                limitParam: '',
                startParam: '',
                url: '/empIncharges',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.empIncharges',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});