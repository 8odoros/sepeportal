/*
 * File: app/view/company/AccidentForm/Witness.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.AccidentForm.Witness', {
    extend: 'Ext.form.FieldContainer',
    alias: 'widget.companyaccidentformwitness',

    requires: [
        'MyApp.view.company.AccidentForm.WitnessViewModel',
        'Ext.container.Container',
        'Ext.form.field.Display',
        'Ext.form.field.ComboBox'
    ],

    viewModel: {
        type: 'companyaccidentformwitness'
    },
    padding: '0 5 5 5',
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
                    name: 'id'
                }
            ]
        },
        {
            xtype: 'container',
            flex: 1,
            margin: '0 0 0 10',
            layout: {
                type: 'vbox',
                align: 'stretch'
            },
            items: [
                {
                    xtype: 'container',
                    flex: 1,
                    layout: {
                        type: 'vbox',
                        align: 'stretch'
                    },
                    items: [
                        {
                            xtype: 'textfield',
                            flex: 1,
                            fieldLabel: 'Όνομα',
                            labelWidth: 160,
                            name: 'wname',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            enforceMaxLength: true,
                            maxLength: 128
                        },
                        {
                            xtype: 'textfield',
                            flex: 1,
                            fieldLabel: 'Επώνυμο',
                            labelWidth: 160,
                            name: 'wsurname',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            enforceMaxLength: true,
                            maxLength: 128
                        },
                        {
                            xtype: 'textfield',
                            flex: 1,
                            fieldLabel: 'Διεύθυνση',
                            labelWidth: 160,
                            name: 'waddr',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            enforceMaxLength: true,
                            maxLength: 200
                        },
                        {
                            xtype: 'textfield',
                            flex: 1,
                            fieldLabel: 'Τ.Κ',
                            labelWidth: 160,
                            name: 'wtk',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            enforceMaxLength: true,
                            maxLength: 5,
                            minLength: 5,
                            vtype: 'Number'
                        },
                        {
                            xtype: 'textfield',
                            flex: 1,
                            fieldLabel: 'Τηλέφωνο',
                            labelWidth: 160,
                            name: 'wphone',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            enforceMaxLength: true,
                            maxLength: 10,
                            vtype: 'telNumber'
                        },
                        {
                            xtype: 'combobox',
                            flex: 1,
                            fieldLabel: 'Ιδιότητα',
                            labelWidth: 160,
                            name: 'wtype',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            editable: false,
                            maxLength: 200,
                            autoLoadOnValue: true,
                            displayField: 'description',
                            valueField: 'abbr',
                            bind: {
                                store: '{witness_type}'
                            }
                        },
                        {
                            xtype: 'combobox',
                            flex: 1,
                            fieldLabel: 'Υπηκοότητα',
                            labelWidth: 160,
                            name: 'wnationality',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            emptyText:'Πληκτρολογήστε μία χώρα',
                            minChars: 1,
                            triggerAction: 'query',
                            typeAhead: true,
                            hideTrigger:true,
                            queryParam: 'description',
                            //editable: false,
                            maxLength: 200,
                            displayField: 'description',
                            store: 'company.NATIONALITY',
                            valueField: 'abbr',
                            forceSelection: true
                        }
                    ]
                }
            ]
        }
    ]

});