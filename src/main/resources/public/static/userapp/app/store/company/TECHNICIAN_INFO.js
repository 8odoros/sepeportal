/*
 * File: app/store/company/TECHNICIAN_INFO.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.TECHNICIAN_INFO', {
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
            storeId: 'company.TECHNICIAN_INFO',
            autoLoad: false,
            fields: [
                {
                    name: 'success'
                },
                {
                    name: 'fullname'
                },
                {
                    name: 'speciality'
                },
                {
                    name: 'error'
                },
                {
                    name: 'specialityOther'
                },
                {
                    mapping: 'id',
                    name: 'technicianReqId'
                },
                {
                    mapping: 'userId',
                    name: 'technicianReqUserId'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    afm: null
                },
                limitParam: '',
                startParam: '',
                url: '/findTaByAfm',
                reader: {
                    type: 'json'
                }
            }
        }, cfg)]);
    }
});