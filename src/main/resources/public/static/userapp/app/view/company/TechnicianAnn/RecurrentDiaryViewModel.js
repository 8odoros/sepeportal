Ext.define('MyApp.view.company.TechnicianAnn.RecurrentDiaryViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.companytechnicianannrecurrentdiary',

    stores: {
        SPECIFIC_RECURRENCY_DAY:
        {
            data: [
                { abbr: 1, name: 'Δευτέρα' },
                { abbr: 2, name: 'Τρίτη' },
                { abbr: 3, name: 'Τετάρτη' },
                { abbr: 4, name: 'Πέμπτη' },
                { abbr: 5, name: 'Παρασκευή' }
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
        SPECIFIC_RECURRENCY_ORDER:
        {
            data: [
                { abbr: 1, name: '1η' },
                { abbr: 2, name: '2η' },
                { abbr: 3, name: '3η' },
                { abbr: 4, name: '4η' },
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
    }

});