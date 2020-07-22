/*
 * File: app/view/company/ProjectAnnForm/Contr.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.ProjectAnnForm.Contr', {
    extend: 'Ext.form.FieldContainer',
    alias: 'widget.companyprojectannformcontr',

    requires: [
        'MyApp.view.company.ProjectAnnForm.ContrViewModel',
        'Ext.container.Container',
        'Ext.form.field.Display',
        'Ext.form.field.ComboBox'
    ],

    viewModel: {
        type: 'companyprojectannformcontr'
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
                            name: 'contr_firstname',
                            validateOnChange: false,
                            validateOnBlur: false,
                            //allowBlank: false,
                            //allowOnlyWhitespace: false,
                            hidden: true,
                            maxLength: 100
                        },
                        {
                            xtype: 'textfield',
                            flex: 1,
                            fieldLabel: 'Επωνυμία Εργοδότη',
                            labelWidth: 160,
                            name: 'contr_lastname',
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
                            fieldLabel: 'Διεύθυνση Έδρας Αναδόχου/Εργολάβου',
                            labelWidth: 160,
                            name: 'contr_addr',
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
                            labelWidth: 160,
                            fieldLabel: 'Τηλέφωνο',
                            name: 'contr_phone',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            enforceMaxLength: true,
                            maxLength: 10,
                            vtype: 'telNumber',
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
                            enforceMaxLength: true,
                            maxLength: 9,
                            minLength: 9,
                            maskRe:/[0-9.]/
                        },
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
                            enforceMaxLength: true,
                            maxLength: 200,
                            displayField: 'name',
                            valueField: 'abbr',
                            bind: {
                                store: '{Contr_Type}'
                            }
                        }
                    ]
                }
            ]
        }
    ]

});