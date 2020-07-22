/*
 * File: app/view/company/SundayPmtForm/Pers.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.SundayPmtForm.Pers', {
    extend: 'Ext.form.FieldContainer',
    alias: 'widget.companysundaypmtformpers',

    requires: [
        'MyApp.view.company.SundayPmtForm.PersViewModel',
        'Ext.container.Container',
        'Ext.form.field.Display',
        'Ext.form.field.Time',
        'Ext.form.field.Date',
        'Ext.form.field.Hidden',
        'Ext.form.field.TextArea'
    ],

    viewModel: {
        type: 'companysundaypmtformpers'
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
                    suspendLayout: true,
                    layout: {
                        type: 'vbox',
                        align: 'stretch'
                    },
                    items: [
                        {
                            xtype: 'textfield',
                            flex: 1,
                            fieldLabel: 'A/A',
                            labelWidth: 160,
                            name: 'incNum',
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            maxLength: 19
                        },
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
                            xtype: 'textfield',
                            flex: 1,
                            fieldLabel: 'Αρ. Μητρώου ΙΚΑ',
                            labelWidth: 160,
                            name: 'empIka',
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
                            xtype: 'timefield',
                            flex: 1,
                            fieldLabel: 'Ωράριο Εργασίας Από',
                            labelWidth: 160,
                            name: 'empWorkHourStart',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            forceSelection: true,
                            format: 'H:i',
                            increment: 30
                        },
                        {
                            xtype: 'timefield',
                            flex: 1,
                            margin: '0 0 0 0',
                            fieldLabel: 'Ωράριο Εργασίας Εως',
                            labelWidth: 160,
                            name: 'empWorkHourStop',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            forceSelection: true,
                            format: 'H:i',
                            increment: 30
                        },
                        {
                            xtype: 'datefield',
                            flex: 1,
                            fieldLabel: 'Ημέρα αναπληρωματικής ανάπαυσης',
                            labelWidth: 160,
                            name: 'empAlternateRestingDayView',
                            validateOnChange: false,
                            validateOnBlur: false,
                            blankText: 'Δηλώσατε περισσότερες από 5 ώρες εργασίας. Δηλώστε ημέρα ανάπαυσης.',
                            maxLength: 50,
                            format: 'd-m-Y'
                        },
                        {
                            xtype: 'hiddenfield',
                            flex: 1,
                            fieldLabel: 'Ημερομηνία Πρόσληψης',
                            labelWidth: 180,
                            name: 'empAlternateRestingDay',
                            validateOnChange: false
                        },
                        {
                            xtype: 'textareafield',
                            flex: 1,
                            disabled: true,
                            fieldLabel: 'Παρατηρήσεις Επιθεωρητή',
                            labelWidth: 160,
                            name: 'inspectorComments',
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            maxLength: 3000
                        }
                    ]
                }
            ]
        }
    ]

});