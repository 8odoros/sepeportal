/*
 * File: app/store/company/ProjectAnnouncments/Studier.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.ProjectAnnouncments.Studier', {
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
            pageSize: 10,
            remoteSort: true,
            storeId: 'company.ProjectAnnouncments.Studier',
            autoLoad: false,
            fields: [
                {
                    name: 'afm'
                },
                {
                    name: 'companyid'
                },
                {
                    mapping: '_links.self.href',
                    name: 'url'
                },
                {
                    convert: function(v, rec) {
                        var res = v.split("/");
                        return (res[res.length-1]);
                    },
                    mapping: '_links.self.href',
                    name: 'compProjAnn_studierId'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    size: 200
                },
                limitParam: '',
                startParam: '',
                url: '/compProjAnnStudier/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compProjAnnStudier',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});