/*
 * File: app/store/company/COMP_BRANCES.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.COMP_BRANCES', {
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
            storeId: 'company.COMP_BRANCES',
            autoLoad: false,
            fields: [
                {
                    name: 'rgEbrAddressStreet'
                },
                {
                    convert: function(v, rec) {
                        return rec.get('rgEbrBranchId') + ' - ' + rec.get('rgEbrAddressStreet') + ' (' + rec.get('rgEbrEmployerId') + ')';
                    },
                    name: 'rgEbrAddressStreetCombo'
                },
                {
                    name: 'rgEbrEmployerId'
                },
                {
                    name: 'rgEbrBranchId'
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
                    name: 'rgEbrZipCode'
                },
                {
                    name: 'rgEbrKallikratis'
                },
                {
                    convert: function(v, rec) {
                        if (v!==null) {
                            return v.replace(/[^0-9]+/,'');
                        }
                        else
                        return "";
                    },
                    name: 'rgEbrPhoneNumber'
                },
                {
                    name: 'rgEbrSecStakod1'
                },
                {
                    name: 'rgEbrSecStakod2'
                },
                {
                    name: 'rgEbrSecStakod3'
                },
                {
                    name: 'rgEbrSecStakod4'
                },
                {
                    name: 'rgEbrSecStakod5'
                },
                {
                    name: 'rgEmpFullname'
                },
                {
                    name: 'rgEmpName'
                }
            ],
            proxy: {
                type: 'ajax',
                limitParam: '',
                startParam: '',
                url: '/TEmployerBranchIKA/search/findBySession',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.TEmployerBranchIKA'
                }
            }
        }, cfg)]);
    }
});