/*
 * File: app/view/company/CompanyAdmin/CompanyUsersPanelViewModel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.CompanyAdmin.CompanyUsersPanelViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.companycompanyadmincompanyuserspanel',

    requires: [
        'Ext.data.Store',
        'Ext.data.field.Field',
        'Ext.data.proxy.Ajax',
        'Ext.data.reader.Json'
    ],

    stores: {
        COMPANY_USERS: {
            autoLoad: true,
            fields: [
                {
                    convert: function(v, rec) {
                        return rec.get('firstname')+" "+rec.get('lastname');
                    },
                    name: 'name'
                },
                {
                    name: 'username'
                },
                {
                    name: 'email'
                }
            ],
            proxy: {
                type: 'ajax',
                limitParam: '',
                startParam: '',
                url: '/companyUsers',
                reader: {
                    type: 'json',
                    rootProperty: ''
                }
            }
        }
    }

});