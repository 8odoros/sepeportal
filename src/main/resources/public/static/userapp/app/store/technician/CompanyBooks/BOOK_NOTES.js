/*
 * File: app/store/technician/CompanyBooks/BOOK_NOTES.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.technician.CompanyBooks.BOOK_NOTES', {
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
            storeId: 'technician.CompanyBooks.BOOK_NOTES',
            autoLoad: false,
            fields: [
                {
                    name: 'compTaAnnBookId'
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
                    name: 'compTaAnnId'
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
                    size: 10,
                    sort: 'dateCreated,desc'
                },
                limitParam: '',
                startParam: '',
                url: '/compTaAnnBookNote/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compTaAnnBookNote',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});