/*
 * File: app/store/shared/AFM_COMP.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.shared.AFM_COMP', {
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
            storeId: 'shared.AFM_COMP',
            autoLoad: false,
            fields: [
                {
                    name: 'rgEbrAddressStreet'
                },
                {
                    convert: function(v, rec) {

                        /*debugger;
                        storeAddr1.getAt(0).get('pCode')
                        var storeAddr1 = Ext.StoreMgr.lookup( 'address1.ADDR_K1' );
                        var koinCode = storeAddr1.getAt(0).get('koinCode');
                        var store = Ext.StoreMgr.lookup( 'address2.ADDR_D2' );
                        var dimosDescr = store.findRecord('dimosDescr',rec.get('rgEbrKallikratis')).get('abbr');*/
                        //return rec.get('rgEbrAddressStreet') + ' - ' + dimosDescr + '(' + rec.get('rgEbrEmployerId') + ')';
                        return rec.get('rgEbrAddressStreet') + ' (' + rec.get('rgEbrEmployerId') + ')';
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
                extraParams: {
                    afm: null
                },
                limitParam: '',
                startParam: '',
                url: '/TEmployerBranchIKA/search/findByCompanyAfm',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.TEmployerBranchIKA'
                }
            }
        }, cfg)]);
    }
});