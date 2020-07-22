/*
 * File: app/store/company/PTL_COMPANY_BRANCHES.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.PTL_COMPANY_BRANCHES', {
    extend: 'Ext.data.Store',

    requires: [
        'Ext.data.field.Field',
        'Ext.data.proxy.Ajax',
        'Ext.data.reader.Json'
    ],

    constructor: function (cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            //pageSize: 10,
            //remoteSort: true,
            storeId: 'company.PTL_COMPANY_BRANCHES',
            autoLoad: false,
            fields: [
                {
                    mapping: '_links.self.href',
                    name: 'url'
                },
                {
                    convert: function (v, rec) {
                        var res = v.split("/");
                        return (res[res.length - 1]);
                    },
                    mapping: '_links.self.href',
                    name: 'ptlBranchId'
                },
                {
                    name: 'brDescr'
                }
            ],
            proxy: {
                type: 'ajax',
                /*extraParams: {
                 size: 10
                 },*/
                limitParam: '',
                startParam: '',
                //url: '/compPtlBranch/search/findAllList',
                url: '/compPtlBranch/search/findByActivationCode?brActive=1',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compPtlBranch',
                    //totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});