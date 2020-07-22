/*
 * File: app/view/technician/TechnicianDiary/entry.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.technician.TechnicianDiary.entry', {
    extend: 'Ext.form.FieldContainer',
    alias: 'widget.techniciantechniciandiaryentry',

    requires: [
        'MyApp.view.technician.TechnicianDiary.entryViewModel',
        'Ext.form.FieldContainer',
        'Ext.form.field.Date',
        'Ext.form.field.Time',
        'Ext.form.field.Number'
    ],

    viewModel: {
        type: 'techniciantechniciandiaryentry'
    },
    padding: '0 5 5 5',
    layout: 'anchor',
    hideLabel: true,

    items: [
        {
            xtype: 'fieldcontainer',
            msgTarget: 'under',
            combineErrors: true,
            layout: {
                type: 'hbox',
                align: 'stretch'
            },
            items: [
                {
                    xtype: 'datefield',
                    flex: 1,
                    fieldLabel: 'Ημερομηνία',
                    labelAlign: 'top',
                    msgTarget: 'none',
                    name: 'visitDate',
                    validateOnChange: false,
                    validateOnBlur: false,
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    maxLength: 100,
                    disabledDays: [
                        0,
                        6
                    ],
                    format: 'd-m-Y',
                    submitFormat: 'Y-m-d\\T00:00:00.000+0000'
                },
                {
                    xtype: 'timefield',
                    flex: 1,
                    fieldLabel: 'Ώρα',
                    labelAlign: 'top',
                    msgTarget: 'none',
                    name: 'visitTime',
                    validateOnChange: false,
                    value: '08:00',
                    validateOnBlur: false,
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    maxLength: 100,
                    format: 'H:i'
                },
                {
                    xtype: 'textfield',
                    flex: 1,
                    fieldLabel: 'Διάρκεια',
                    labelAlign: 'top',
                    msgTarget: 'none',
                    name: 'visitDurationMinutes',
                    validateOnChange: false,
                    validateOnBlur: false
                }
            ]
        }
    ]

});