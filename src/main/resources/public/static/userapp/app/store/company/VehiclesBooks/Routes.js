/*
 * File: app/store/company/VehiclesBooks/Routes.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.company.VehiclesBooks.Routes', {
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
            storeId: 'company.VehiclesBooks.Routes',
            autoLoad: false,
            fields: [
                {
                    name: 'aa'
                },
                {
                    name: 'dateUpdated'
                },
                {
                    name: 'driverFirstname'
                },
                {
                    name: 'driverLastname'
                },
                {
                    name: 'routeDestination'
                },
                {
                    convert: function(v, rec) {
                        var res = v.split("/");
                        return (res[res.length-1]);
                    },
                    mapping: '_links.self.href',
                    name: 'routeId'
                },
                {
                    mapping: '_links.self.href',
                    name: 'url'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    size: 10,
                    sort: 'aa,asc'
                },
                limitParam: '',
                startParam: '',
                url: '/compVehicleBookEntry/search/findByCompVehicleBookId?compVehicleBookId=',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compVehicleBookEntry',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});