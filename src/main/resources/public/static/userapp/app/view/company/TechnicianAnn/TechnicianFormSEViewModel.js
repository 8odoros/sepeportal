Ext.define('MyApp.view.company.TechnicianAnn.TechnicianFormSEViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.companytechniciananntechnicianformse',

    requires: [
        'Ext.data.Store',
        'Ext.data.field.Field',
        'Ext.data.proxy.Ajax',
        'Ext.data.reader.Json'
    ],

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
        BRANCH_SECTOR: {
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
                    id: null,
                    sort: 'description'
                },
                limitParam: '',
                startParam: '',
                url: '/taBranchSector/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.taBranchSector'
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