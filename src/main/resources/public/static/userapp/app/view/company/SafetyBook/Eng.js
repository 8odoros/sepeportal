/*
 * File: app/view/company/SafetyBook/Eng.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.SafetyBook.Eng', {
    extend: 'Ext.form.FieldContainer',
    alias: 'widget.companysafetybookeng',

    requires: [
        'MyApp.view.company.SafetyBook.EngViewModel',
        'Ext.container.Container',
        'Ext.form.field.Display',
        'Ext.form.field.Date',
        'Ext.form.field.Hidden'
    ],

    viewModel: {
        type: 'companysafetybookeng'
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
                            name: 'eng_firstname',
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
                            name: 'eng_lastname',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 100
                        },
                        {
                            xtype: 'textfield',
                            flex: 1,
                            fieldLabel: 'Ειδικότητα Μηχανικού',
                            labelWidth: 160,
                            name: 'eng_engineerSpeciality',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 200
                        },
                        {
                            xtype: 'textfield',
                            flex: 1,
                            fieldLabel: 'Τόπος Γέννησης',
                            labelWidth: 160,
                            name: 'eng_birthplace',
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
                            name: 'eng_birthdate',
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
                            name: 'eng_addr',
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
                            name: 'eng_cardNumber',
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
                            name: 'eng_cardIssuingAuth',
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
                            name: 'eng_afm',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 9,
                            minLength: 9,
                            maskRe:/[0-9.]/
                        },
                        {
                            xtype: 'textfield',
                            flex: 1,
                            fieldLabel: 'Αρ. Μητρώου ΠΓ',
                            labelWidth: 160,
                            name: 'eng_teeNum',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 20
                        },
                        {
                            xtype: 'hiddenfield',
                            flex: 1,
                            fieldLabel: 'Αρ. Ταυτότητας',
                            labelWidth: 160,
                            name: 'eng_cardType',
                            validateOnChange: false
                        }
                    ]
                }
            ]
        }
    ]

});