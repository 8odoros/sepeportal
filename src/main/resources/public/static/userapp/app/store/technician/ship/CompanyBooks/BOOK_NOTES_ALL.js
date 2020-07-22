/*
 * File: app/store/technician/ship/CompanyBooks/BOOK_NOTES_ALL.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.technician.ship.CompanyBooks.BOOK_NOTES_ALL', {
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
            storeId: 'technician.ship.CompanyBooks.BOOK_NOTES_ALL',
            autoLoad: false,
            fields: [
                {
                    name: 'compTaSannBookId'
                },
                {
                    name: 'read'
                },
                {
                    name: 'readDate'
                },
                {
                    name: 'dateCreated'
                },
                {
                    name: 'compTaSannId'
                },
                {
                    name: 'notes'
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
                    name: 'noteId'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    size: 1000,
                    sort: 'dateCreated,asc'
                },
                limitParam: '',
                startParam: '',
                url: '/compTaSannBookNote/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compTaSannBookNote',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});