/*
 * File: app/view/company/AccidentForm/WitnessViewModel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.AccidentForm.WitnessViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.companyaccidentformwitness',

    requires: [
        'Ext.data.Store',
        'Ext.data.field.Field',
        'Ext.data.proxy.Ajax',
        'Ext.data.reader.Json'
    ],

    stores: {
        witness_type: {
            autoLoad: false,
            fields: [
                {
                    name: 'description'
                },
                {
                    convert: function(v, rec) {
                        var res = v.split("/");
                        return decodeURIComponent((res[res.length-1]));
                    },
                    mapping: '_links.self.href',
                    name: 'abbr'
                }
            ],
            proxy: {
                type: 'ajax',
                limitParam: '',
                startParam: '',
                url: '/witnessType',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.witnessType'
                }
            }
        }
    }

});