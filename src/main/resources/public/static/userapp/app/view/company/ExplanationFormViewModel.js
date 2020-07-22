/*
 * File: app/view/company/ExplanationFormViewModel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.ExplanationFormViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.companyexplanationform',

    requires: [
        'Ext.data.Store',
        'Ext.data.field.Field'
    ],

    stores: {
        teKeCat: {
            data: [
                {
                    abbr: 'ΤΧ',
                    name: 'Ασφάλειας και Υγείας στην Εργασία'
                },
                {
                    abbr: 'ΚΝ',
                    name: 'Εργασιακών Σχέσεων'
                },
                {
                    abbr: 'ΕΕ',
                    name: 'Ειδικών Επιθεωρητών Εργασίας'
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
        }
    }

});