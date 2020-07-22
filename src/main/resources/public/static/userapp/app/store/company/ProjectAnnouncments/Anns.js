/*
 * File: app/store/company/ProjectAnnouncments/Anns.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.ProjectAnnouncments.Anns', {
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
            storeId: 'company.ProjectAnnouncments.Anns',
            autoLoad: false,
            fields: [
                {
                    name: 'subStatus'
                },
                {
                    name: 'reqStatus'
                },
                {
                    name: 'protNo'
                },
                {
                    mapping: '_links.self.href',
                    name: 'url'
                },
                {
                    mapping: '_links.projAnnContrs.href',
                    name: 'projAnnContrs'
                },
                {
                    mapping: '_links.projAnnEngs.href',
                    name: 'projAnnEngs'
                },
                {
                    mapping: '_links.projAnnSelfempls.href',
                    name: 'projAnnSelfempls'
                },
                {
                    mapping: '_links.projAnnStudiers.href',
                    name: 'projAnnStudiers'
                },
                {
                    name: 'protDate'
                },
                {
                    convert: function(v, rec) {
                        var res = v.split("/");
                        return (res[res.length-1]);
                    },
                    mapping: '_links.self.href',
                    name: 'projectid'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    size: 10,
                    sort: 'protDate,desc'
                },
                limitParam: '',
                startParam: '',
                url: '/compProjAnn/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compProjAnn',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});