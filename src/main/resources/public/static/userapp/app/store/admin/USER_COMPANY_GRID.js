/*
 * File: app/store/employee/COMPLAINTS_GRID.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.store.admin.USER_COMPANY_GRID', {
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
            pageSize: 100,
            remoteSort: true,
            storeId: 'admin.USER_COMPANY_GRID',
            autoLoad: false,
            fields: [
                {
                    convert: function(v, rec) {
                        var res = v.split("/");
                        return (res[res.length-1]);
                    },
                    mapping: '_links.self.href',
                    name: 'id'
                },
                {
                    name: 'isAdmin'
                },
                {
                    name: 'role'
                },
                {
                    name: 'status'
                },
                {
                    name: 'useranme'
                },
                {
                    name: 'email'
                },
                {
                    name: 'emailNotifEn'
                },
                {
                    name: 'firstname'
                },
                {
                    name: 'lastname'
                },
                {
                    name: 'phone'
                },
                {
                    name: 'mobile'
                },
                {
                    name: 'addr'
                },
                {
                    name: 'addrTk'
                },
                {
                    name: 'fax'
                },
                {
                    name: 'isExypp'
                },
                {
                    name: 'name'
                },
                {
                    name: 'title'
                },
                {
                    name: 'afm'
                },
                {
                    name: 'ame'
                },
                {
                    name: 'isAssociation'
                },
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    size: 100,
                    sort: 'id',
                    username: -1,
                    afm: null,
                    ame: null,
                    email: null
                },
                limitParam: '',
                startParam: '',
                url: '/SpPtlVUserCompany/search/findUsersByCriteria',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.SpPtlVUserCompany',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});