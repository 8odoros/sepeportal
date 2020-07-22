/*
 * File: app/store/company/ProjectsBooks/Projects.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.ProjectsBooks.Projects', {
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
            storeId: 'company.ProjectsBooks.Projects',
            autoLoad: false,
            fields: [
                {
                    name: 'id'
                },
                {
                    name: 'creationDate'
                },
                {
                    name: 'projectDescription'
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
                    name: 'projectid'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    size: 10,
                    sort: 'id,asc'
                },
                limitParam: '',
                startParam: '',
                url: '/companyProjects/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.companyProjects',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});