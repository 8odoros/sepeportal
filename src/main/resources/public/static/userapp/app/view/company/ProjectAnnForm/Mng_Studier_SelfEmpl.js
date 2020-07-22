/*
 * File: app/view/company/ProjectAnnForm/Mng_Studier_SelfEmpl.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.ProjectAnnForm.Mng_Studier_SelfEmpl', {
    extend: 'Ext.form.FieldContainer',
    alias: 'widget.companyprojectannformmng_studier_selfempl',

    requires: [
        'MyApp.view.company.ProjectAnnForm.Mng_Studier_SelfEmplViewModel',
        'Ext.container.Container',
        'Ext.form.field.Text',
        'Ext.form.field.Hidden'
    ],

    viewModel: {
        type: 'companyprojectannformmng_studier_selfempl'
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
            flex: 1,
            layout: {
                type: 'hbox',
                align: 'stretch'
            },
            items: [
                {
                    xtype: 'textfield',
                    flex: 1,
                    fieldLabel: 'Όνομα',
                    labelAlign: 'top',
                    name: 'ms_firstname',
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
                    labelAlign: 'top',
                    name: 'ms_lastname',
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
                    fieldLabel: 'Α.Φ.Μ.',
                    labelAlign: 'top',
                    name: 'ms_afm',
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
                    fieldLabel: 'Διεύθυνση Κατοικίας',
                    labelAlign: 'top',
                    name: 'ms_addr',
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
                    name: 'ms_type',
                    validateOnChange: false
                }
            ]
        }
    ]

});