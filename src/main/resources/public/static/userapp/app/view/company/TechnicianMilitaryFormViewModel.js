/*
 * File: app/view/company/GenRequestFormViewModel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianMilitaryFormViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.companytechnicianmilitaryform',

    requires: [
        'Ext.data.Store',
        'Ext.data.field.Field',
        'Ext.data.proxy.Rest',
        'Ext.data.reader.Json'
    ],

    stores: {
        MILITARY_CATEG:
        {
            data: [
                {
                    abbr: 0,
                    name: 'ΑΣΕΙ'
                },
                {
                    abbr: 1,
                    name: 'ΑΣΣΥ'
                },
                {
                    abbr: 2,
                    name: 'Ν.3850/2010'
                }
            ],
            fields: [
                {
                    name: 'abbr'
                },
                {
                    name: 'name'
                }
            ]
        },
        MILITARY_BRANCH:
        {
            data: [
                {
                    abbr: 2,
                    name: 'ΠΕ'
                },
                {
                    abbr: 1,
                    name: 'ΤΕ'
                },
/*                {
                    abbr: 3,
                    name: 'ΔΕ'
                }*/
            ],
            fields: [
                {
                    name: 'abbr'
                },
                {
                    name: 'name'
                }
            ]
        },
        MILITARY_TYPE:
        {
            data: [
                {
                    abbr: 0,
                    name: 'ΕΝΣΤΟΛΟΣ'
                },
                {
                    abbr: 1,
                    name: 'ΠΟΛΙΤΙΚΟ ΠΡΟΣΩΠΟ'
                }
            ],
            fields: [
                {
                    name: 'abbr'
                },
                {
                    name: 'name'
                }
            ]
        },
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
                url: '/vwGenreq/search/findAllTeBus',
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