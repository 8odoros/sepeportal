/*
 * File: app/view/employee/ComplaintFormViewModel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.employee.ComplaintFormViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.employeecomplaintform',

    requires: [
        'Ext.data.Store',
        'Ext.data.field.Field',
        'Ext.data.proxy.Rest',
        'Ext.data.reader.Json'
    ],

    stores: {
        COMPL_MATTERS: {
            autoLoad: false,
            //autoSync: true,
            fields: [
                {
                    name: 'descr'
                },
                {
                    convert: function(v, rec) {
                        var res = v.split("/");
                        return (res[res.length-1]);
                    },
                    mapping: '_links.self.href',
                    name: 'abbr'
                },
                {
                    name: 'spCmInvolves'
                },
            ],
            proxy: {
                type: 'ajax',
                /*extraParams: {
                    spCmInvolves: null
                },*/
                limitParam: '',
                startParam: '',
                url: '/employeeComplaintMatter/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.employeeComplaintMatter'
                }
            }
        }
    }

});