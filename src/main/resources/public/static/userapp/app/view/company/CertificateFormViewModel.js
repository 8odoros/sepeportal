/*
 * File: app/view/company/GenRequestFormViewModel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.CertificateFormViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.companycertificateform',

    requires: [
        'Ext.data.Store',
        'Ext.data.field.Field',
        'Ext.data.proxy.Rest',
        'Ext.data.reader.Json'
    ],

    stores: {
        GENREQUEST_TYPES: {
            autoLoad: false,
            autoSync: true,
            fields: [
                {
                    name: 'spGreqTitle'
                },
                {
                    name: 'spGreqHelpText'
                },
                {
                    name: 'spGreqHelplDocId'
                },
                {
                    name: 'spGreqTemplDocId'
                },
                {
                    name: 'spGreqActive'
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
                url: '/vwGenreq/search/findAllKeBus',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.vwGenreq'
                }
            }
        },
        GENREQUEST_SUBJECTS: {
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
                url: '/emplGenrequestSubject/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.emplGenrequestSubject'
                }
            }
        }
    }

});