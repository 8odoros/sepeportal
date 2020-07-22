/*
 * File: app/view/company/JobRecrOfficeForm/Pers.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.JobRecrOfficeForm.Pers', {
    extend: 'Ext.form.FieldContainer',
    alias: 'widget.companyjobrecrofficeformpers',

    requires: [
        'MyApp.view.company.JobRecrOfficeForm.PersViewModel',
        'Ext.form.field.Display',
        'Ext.form.field.ComboBox',
        'Ext.form.field.Number',
        'Ext.form.FieldSet',
        'Ext.form.field.TextArea'
    ],

    viewModel: {
        type: 'companyjobrecrofficeformpers'
    },
    border: 1,
    frame: true,
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
                            name: 'empFirstname',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 128
                        },
                        {
                            xtype: 'textfield',
                            flex: 1,
                            fieldLabel: 'Επώνυμο',
                            labelWidth: 160,
                            name: 'empLastname',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 128
                        },
                        {
                            xtype: 'textfield',
                            flex: 1,
                            fieldLabel: 'Αρ. Δελτίου Ταυτότητας',
                            labelWidth: 160,
                            name: 'empCardNumber',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'combobox',
                            flex: 1,
                            fieldLabel: 'Φύλο',
                            labelWidth: 160,
                            name: 'empSex',
                            validateOnChange: false,
                            readOnly: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            editable: false,
                            autoLoadOnValue: true,
                            displayField: 'name',
                            forceSelection: true,
                            store: 'shared.SEX',
                            valueField: 'abbr'
                        },
                        {
                            xtype: 'numberfield',
                            flex: 1,
                            fieldLabel: 'Ηλικία',
                            labelWidth: 160,
                            name: 'empAge',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            hideTrigger: true,
                            maxLength: 2,
                            minLength: 2,
                            allowDecimals: false,
                            allowExponential: false,
                            maxValue: 99,
                            minValue: 18
                        },
                        {
                            xtype: 'numberfield',
                            flex: 1,
                            fieldLabel: 'Αριθμός Τοποθετήσεων',
                            labelWidth: 160,
                            name: 'empPlacementNum',
                            validateOnChange: false,
                            validateOnBlur: false,
                            hideTrigger: true,
                            maxLength: 2,
                            allowDecimals: false,
                            allowExponential: false,
                            maxValue: 99,
                            minValue: 0
                        },
                        {
                            xtype: 'textfield',
                            flex: 1,
                            fieldLabel: 'Ειδικότητα',
                            labelWidth: 160,
                            name: 'empSpeciality',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 128
                        },
                        {
                            xtype: 'textfield',
                            flex: 1,
                            fieldLabel: 'Α.Φ.Μ.',
                            labelWidth: 160,
                            name: 'empAfm',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 9,
                            minLength: 9,
                            maskRe:/[0-9.]/,
                        },
                        {
                            xtype: 'textfield',
                            flex: 1,
                            fieldLabel: 'ΑΜΚΑ',
                            labelWidth: 160,
                            name: 'empAmka',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 100
                        },
                        {
                            xtype: 'combobox',
                            flex: 1,
                            fieldLabel: 'Επίπεδο Εκπαίδευσης',
                            labelWidth: 160,
                            name: 'empEduLevelId',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            editable: false,
                            autoLoadOnValue: true,
                            displayField: 'description',
                            store: 'shared.EDUCATION_LEVEL',
                            valueField: 'abbr'
                        },
                        {
                            xtype: 'combobox',
                            flex: 1,
                            fieldLabel: 'Ιδιότητα',
                            labelWidth: 160,
                            name: 'empCategoryId',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            editable: false,
                            maxLength: 200,
                            autoLoadOnValue: true,
                            displayField: 'name',
                            valueField: 'abbr',
                            bind: {
                                store: '{Pers_Category}'
                            }
                        },
                        {
                            xtype: 'fieldset',
                            flex: 1,
                            title: 'Εργοδότης',
                            layout: {
                                type: 'vbox',
                                align: 'stretch'
                            },
                            items: [
                                {
                                    xtype: 'textfield',
                                    flex: 1,
                                    fieldLabel: 'Ονομασία',
                                    name: 'compTitle',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 300
                                },
                                {
                                    xtype: 'textfield',
                                    flex: 1,
                                    fieldLabel: 'Α.Φ.Μ.',
                                    name: 'compAfm',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 9,
                                    minLength: 9,
                                    maskRe:/[0-9.]/,
                                },
                                {
                                    xtype: 'textareafield',
                                    flex: 1,
                                    fieldLabel: 'Διεύθυνση Εργασίας',
                                    name: 'compAddr',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 200
                                }
                            ]
                        }
                    ]
                }
            ]
        }
    ]

});