/*
 * File: app/view/company/SafetyBook/Contr.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.SafetyBook.Contr', {
    extend: 'Ext.form.FieldContainer',
    alias: 'widget.companysafetybookcontr',

    requires: [
        'MyApp.view.company.SafetyBook.ContrViewModel',
        'Ext.container.Container',
        'Ext.form.field.Display',
        'Ext.form.field.ComboBox',
        'Ext.form.field.Date',
        'Ext.form.field.Hidden'
    ],

    viewModel: {
        type: 'companysafetybookcontr'
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
                            xtype: 'combobox',
                            flex: 1,
                            fieldLabel: 'Ιδιότητα',
                            labelWidth: 160,
                            name: 'contr_type',
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
                                store: '{Contr_Type}'
                            }
                        },
                        {
                            xtype: 'combobox',
                            flex: 1,
                            fieldLabel: 'Ειδικότητα',
                            labelWidth: 160,
                            name: 'contr_specialty',
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
                                store: '{Contr_Speciality}'
                            }
                        },
                        {
                            xtype: 'textfield',
                            flex: 1,
                            fieldLabel: 'Όνομα',
                            labelWidth: 160,
                            name: 'contr_firstname',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 100
                        },
                        {
                            xtype: 'textfield',
                            flex: 1,
                            fieldLabel: 'Επώνυμο',
                            labelWidth: 160,
                            name: 'contr_lastname',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 100
                        },
                        {
                            xtype: 'textfield',
                            flex: 1,
                            fieldLabel: 'Τόπος Γέννησης',
                            labelWidth: 160,
                            name: 'contr_birthplace',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 200
                        },
                        {
                            xtype: 'datefield',
                            flex: 1,
                            fieldLabel: 'Ημερομηνία Γέννησης',
                            labelWidth: 160,
                            name: 'contr_birthdate',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 200,
                            format: 'd-m-Y',
                            submitFormat: 'Y-m-d\\T00:00:00.000+0000'
                        },
                        {
                            xtype: 'textfield',
                            flex: 1,
                            fieldLabel: 'Διεύθυνση Κατοικίας',
                            labelWidth: 160,
                            name: 'contr_addr',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 200
                        },
                        {
                            xtype: 'textfield',
                            flex: 1,
                            fieldLabel: 'Αρ. Ταυτότητας',
                            labelWidth: 160,
                            name: 'contr_cardNumber',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 200
                        },
                        {
                            xtype: 'textfield',
                            flex: 1,
                            fieldLabel: 'Εκδίδουσα Άρχη',
                            labelWidth: 160,
                            name: 'contr_cardIssuingAuth',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            flex: 1,
                            fieldLabel: 'Α.Φ.Μ.',
                            labelWidth: 160,
                            name: 'contr_afm',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 9,
                            minLength: 9,
                            maskRe:/[0-9.]/
                        },
                        {
                            xtype: 'datefield',
                            flex: 1,
                            fieldLabel: 'Ημερομηνία Έναρξης',
                            labelWidth: 160,
                            name: 'contr_dateStart',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50,
                            format: 'd-m-Y',
                            submitFormat: 'Y-m-d\\T00:00:00.000+0000'
                        },
                        {
                            xtype: 'datefield',
                            flex: 1,
                            fieldLabel: 'Ημερομηνία Λήξης',
                            labelWidth: 160,
                            name: 'contr_dateEnd',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50,
                            format: 'd-m-Y',
                            submitFormat: 'Y-m-d\\T00:00:00.000+0000'
                        },
                        {
                            xtype: 'hiddenfield',
                            flex: 1,
                            fieldLabel: 'Αρ. Ταυτότητας',
                            labelWidth: 160,
                            name: 'contr_cardType',
                            validateOnChange: false
                        }
                    ]
                }
            ]
        }
    ]

});