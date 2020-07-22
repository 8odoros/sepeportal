/*
 * File: app/view/company/ProjectAnnForm/Eng.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.ProjectAnnForm.Eng', {
    extend: 'Ext.form.FieldContainer',
    alias: 'widget.companyprojectannformeng',

    requires: [
        'MyApp.view.company.ProjectAnnForm.EngViewModel',
        'Ext.container.Container',
        'Ext.form.field.Display',
        'Ext.form.field.Text',
        'Ext.form.field.Hidden'
    ],

    viewModel: {
        type: 'companyprojectannformeng'
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
                            fieldLabel: 'Αρ. Μητρώου ΤΕΕ',
                            labelWidth: 160,
                            name: 'eng_teeNum',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            enforceMaxLength: true,
                            maxLength: 20
                        },
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
                            enforceMaxLength: true,
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
                            enforceMaxLength: true,
                            maxLength: 100
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
                            enforceMaxLength: true,
                            maxLength: 200
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
                            enforceMaxLength: true,
                            maxLength: 9,
                            minLength: 9,
                            maskRe:/[0-9.]/
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
                            enforceMaxLength: true,
                            maxLength: 200
                        },
                        {
                            xtype: 'hiddenfield',
                            flex: 1,
                            labelAlign: 'top',
                            labelWidth: 180,
                            name: 'eng_invlolvementType',
                            validateOnChange: false
                        }
                    ]
                }
            ]
        }
    ]

});