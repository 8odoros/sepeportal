/*
 * File: app/view/company/DriversPmtForm/OffDay.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.DriversPmtForm.OffDay', {
    extend: 'Ext.form.FieldContainer',
    alias: 'widget.companydriverspmtformoffday',

    requires: [
        'MyApp.view.company.DriversPmtForm.OffDayViewModel',
        'Ext.container.Container',
        'Ext.form.field.Display',
        'Ext.form.field.Date',
        'Ext.form.field.Hidden'
    ],

    viewModel: {
        type: 'companydriverspmtformoffday'
    },
    border: 1,
    frame: true,
    padding: '0 0 0 5',
    hideLabel: true,

    layout: {
        type: 'hbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'container',
            items: [
                {
                    xtype: 'displayfield',
                    name: 'incNum'
                }
            ]
        },
        {
            xtype: 'container',
            flex: 1,
            margin: '0 0 0 15',
            layout: {
                type: 'hbox',
                align: 'stretchmax'
            },
            items: [
                {
                    xtype: 'textfield',
                    flex: 1,
                    fieldLabel: 'Εβδομάδα',
                    name: 'week',
                    validateOnChange: false,
                    validateOnBlur: false,
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    maxLength: 20
                },
                {
                    xtype: 'datefield',
                    flex: 1,
                    margin: '0 0 0 5',
                    fieldLabel: 'Ημέρα ανάπαυσης',
                    labelWidth: 140,
                    name: 'dayView',
                    validateOnChange: false,
                    validateOnBlur: false,
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    maxLength: 50,
                    format: 'd-m-Y'
                },
                {
                    xtype: 'hiddenfield',
                    flex: 1,
                    labelWidth: 180,
                    name: 'day',
                    validateOnChange: false
                }
            ]
        }
    ]

});