/*
 * File: app/view/company/DoctorAnn/DoctorFormViewModel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.DoctorAnn.DoctorFormViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.companydoctoranndoctorform',

    requires: [
        'Ext.data.Store',
        'Ext.data.field.Field',
        'Ext.data.proxy.Ajax',
        'Ext.data.reader.Json'
    ],

    stores: {
        DOCTOR_SPECIALITY: {
            autoLoad: false,
            fields: [
                {
                    name: 'description'
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
                type: 'ajax',
                extraParams: {
                    id: null
                },
                limitParam: '',
                startParam: '',
                url: '/ieSpeciality/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.ieSpeciality'
                }
            }
        },
        isExyppSelected: {
            data: [
                {
                    name: 'Ανεξάρτητοι Ιατροί',
                    value: 1
                },
                {
                    name: 'ΕΞΥΠΠ',
                    value: 3
                }
            ],
            fields: [
                {
                    name: 'value'
                },
                {
                    name: 'name'
                }
            ]
        }
    }

});