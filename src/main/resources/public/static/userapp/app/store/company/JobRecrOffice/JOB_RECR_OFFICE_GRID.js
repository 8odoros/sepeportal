/*
 * File: app/store/company/JobRecrOffice/JOB_RECR_OFFICE_GRID.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.JobRecrOffice.JOB_RECR_OFFICE_GRID', {
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
            storeId: 'company.JobRecrOffice.JOB_RECR_OFFICE_GRID',
            autoLoad: false,
            fields: [
                {
                    name: 'protNo'
                },
                {
                    name: 'protDate'
                },
                {
                    name: 'subStatus'
                },
                {
                    name: 'reqStatus'
                },
                {
                    name: 'outdated'
                },
                {
                    mapping: '_links.self.href',
                    name: 'url'
                },
                {
                    mapping: '_links.compJobRecrOffPersons.href',
                    name: 'jobRecrOfficePers'
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
                url: '/compJobRecrOff/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compJobRecrOff',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});