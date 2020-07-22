/*
 * File: app/store/company/TechnicianAnn/EXYPP.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.TechnicianAnn.EXYPP', {
    extend: 'Ext.data.Store',

    requires: [
        'Ext.data.field.Integer',
        'Ext.data.proxy.Ajax',
        'Ext.data.reader.Json'
    ],

    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            storeId: 'company.TechnicianAnn.EXYPP',
            autoLoad: false,
            fields: [
                {
                    name: 'rgEmpEmployerId'
                },
                {
                    name: 'rgEmpTaxationNumber'
                },
                {
                    name: 'rgEmpFullname'
                },
                {
                    type: 'int',
                    convert: function(v, rec) {
                        return parseInt(v);
                    },
                    name: 'rgEmpEmployerStatus'
                },
                {
                    convert: function(v, rec) {
                        var res = v.split("/");
                        return (res[res.length-1]);
                    },
                    mapping: '_links.self.href',
                    name: 'abbr'
                },
                {
                    convert: function(v, rec) {
                        if(parseInt(rec.get('rgEmpEmployerStatus'))===1)
                        return "(Ενεργή) "+rec.get("rgEmpFullname");
                        else
                        return "(Ανενεργή) "+rec.get("rgEmpFullname");
                    },
                    name: 'description'
                }
            ],
            proxy: {
                type: 'ajax',
                limitParam: '',
                startParam: '',
                url: '/exypp',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.exypp'
                }
            }
        }, cfg)]);
    }
});