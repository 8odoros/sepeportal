/*
 * File: app/view/employee/ExperienceFormViewModel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.employee.ExperienceFormViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.employeeexperienceform',

    requires: [
        'Ext.data.Store',
        'Ext.data.field.Field',
        'Ext.data.proxy.Rest',
        'Ext.data.reader.Json'
    ],

    stores: {
        EXPER_USAGE: {
            autoLoad: false,
            autoSync: true,
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
                }
            ],
            proxy: {
                type: 'rest',
                url: '/emplExperienceCertUse/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.emplExperienceCertUse'
                }
            }
        }
    }

});