/*
 * File: app/store/company/ProjectsBooks/Daily.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.ProjectsBooks.Daily', {
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
            storeId: 'company.ProjectsBooks.Daily',
            autoLoad: false,
            fields: [
                {
                    name: 'incNum'
                },
                {
                    name: 'cardDate'
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
                    name: 'dailycardid'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    size: 10,
                    sort: 'incNum,desc'
                },
                limitParam: '',
                startParam: '',
                url: '/companyDailyCards/search/findByProjectId?projectId=',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.companyDailyCards',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});