/*
 * File: app/store/company/ProjectsBooks/PersonelAll.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.ProjectsBooks.PersonelAll', {
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
            remoteSort: true,
            storeId: 'company.ProjectsBooks.PersonelAll',
            autoLoad: false,
            fields: [
                {
                    name: 'incNum'
                },
                {
                    name: 'editTime'
                },
                {
                    name: 'name'
                },
                {
                    name: 'surname'
                },
                {
                    name: 'speciality'
                },
                {
                    convert: function(v, rec) {
                        var res = v.split("/");
                        return (res[res.length-1]);
                    },
                    mapping: '_links.self.href',
                    name: 'dailycardid'
                },
                {
                    mapping: '_links.self.href',
                    name: 'url'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    size: 200,
                    sort: 'incNum,asc'
                },
                limitParam: '',
                startParam: '',
                url: '/companyPersonnelBook/search/findByDailyCardId?dailyCardId=',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.companyPersonnelBook',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});