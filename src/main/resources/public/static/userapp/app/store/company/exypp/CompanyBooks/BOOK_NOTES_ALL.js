/*
 * File: app/store/company/exypp/CompanyBooks/BOOK_NOTES_ALL.js
 *
 * This file was generated by Sencha Architect version 3.2.0.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Ext JS 5.0.x library, under independent license.
 * License of Sencha Architect does not include license for Ext JS 5.0.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

Ext.define('MyApp.store.company.exypp.CompanyBooks.BOOK_NOTES_ALL', {
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
            storeId: 'company.exypp.CompanyBooks.BOOK_NOTES_ALL',
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
                    size: 1000,
                    sort: 'dateCreated,asc'
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