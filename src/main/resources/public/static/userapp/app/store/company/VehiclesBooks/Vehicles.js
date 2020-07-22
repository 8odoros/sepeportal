/*
 * File: app/store/company/VehiclesBooks/Vehicles.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.VehiclesBooks.Vehicles', {
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
            //remoteSort: true,
            storeId: 'company.VehiclesBooks.Vehicles',
            autoLoad: false,
            fields: [
                {
                    name: 'id'
                },
                {
                    name: 'protDate'
                },
                {
                    name: 'vehicleLicenceNum'
                },
                {
                    name: 'companyid'
                },
                {
                    name: 'subStatus'
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
                    name: 'vehicleId'
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
                url: '/compVehicleBook/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compVehicleBook',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});