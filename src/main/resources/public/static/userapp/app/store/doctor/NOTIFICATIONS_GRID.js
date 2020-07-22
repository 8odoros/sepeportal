/*
 * File: app/store/doctor/NOTIFICATIONS_GRID.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.doctor.NOTIFICATIONS_GRID', {
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
            storeId: 'doctor.NOTIFICATIONS_GRID',
            autoLoad: false,
            autoSync: true,
            fields: [
                {
                    name: 'title'
                },
                {
                    name: 'message'
                },
                {
                    name: 'sender'
                },
                {
                    name: 'priority'
                },
                {
                    convert: function(v, rec) {
                        if (v===1)
                        return "Χαμηλή";
                        else if (v===2)
                        return "Κανονική";
                        else if (v===3)
                        return "Υψηλή";
                    },
                    mapping: 'priority',
                    name: 'priorityview'
                },
                {
                    name: 'viewed'
                },
                {
                    name: 'accountId'
                },
                {
                    name: 'docId'
                },
                {
                    name: 'dateTime'
                },
                {
                    convert: function(v, rec) {

                        return Ext.Date.format(new Date(v), "d-m-Y h:i:s");
                    },
                    mapping: 'dateTime',
                    name: 'dateTimeview'
                },
                {
                    mapping: '_links.self.href',
                    name: 'url'
                },
                {
                    name: 'id'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    size: 10,
                    sort: 'dateTime,desc'
                },
                limitParam: '',
                startParam: '',
                url: '/tNotificationsAccountEntities',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.tNotificationsAccountEntities',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});