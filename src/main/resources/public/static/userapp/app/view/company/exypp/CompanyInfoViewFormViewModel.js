/*
 * File: app/view/company/exypp/CompanyInfoViewFormViewModel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.exypp.CompanyInfoViewFormViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.companyexyppcompanyinfoviewform',

    stores: {
        TECHNICIAN_SPECIALITY: {
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
                url: '/taSpeciality/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.taSpeciality'
                }
            }
        },
        isExyppSelected: {
            data: [
                {
                    name: 'Φυσικό Πρόσωπο',
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