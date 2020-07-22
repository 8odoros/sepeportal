/*
 * File: app/view/employee/DisputeReason.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.employee.DisputeReason', {
    extend: 'Ext.form.FieldContainer',
    alias: 'widget.employeedisputereason',

    requires: [
        'MyApp.view.employee.DisputeReasonViewModel',
        'Ext.container.Container',
        'Ext.form.field.Checkbox',
        'Ext.form.field.Date',
        'Ext.form.field.TextArea',
        'Ext.form.field.Hidden'
    ],

    viewModel: {
        type: 'employeedisputereason'
    },
    padding: '0 5 5 5',
    hideLabel: true,

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
                    xtype: 'checkboxfield',
                    handler: function(checkbox, checked) {
                        checkbox.up('form').onCheckBoxClick(checkbox, checked);
                    },
                    name: 'reasonId',
                    validateOnChange: false,
                    validateOnBlur: false
                },
                {
                    xtype: 'container',
                    flex: 1,
                    padding: '0 0 0 40',
                    layout: {
                        type: 'vbox',
                        align: 'stretchmax'
                    },
                    items: [
                        {
                            xtype: 'datefield',
                            disabled: true,
                            hidden: true,
                            itemId: 'id_dateStart',
                            margin: '0 0 0 10',
                            padding: '',
                            fieldLabel: 'από',
                            labelPad: 15,
                            labelWidth: 20,
                            name: 'dateStart',
                            validateOnChange: false,
                            validateOnBlur: false
                        },
                        {
                            xtype: 'datefield',
                            disabled: true,
                            hidden: true,
                            itemId: 'id_dateEnd',
                            margin: '0 0 0 10',
                            fieldLabel: 'έως',
                            labelPad: 15,
                            labelWidth: 20,
                            name: 'dateEnd',
                            validateOnChange: false,
                            validateOnBlur: false
                        }
                    ]
                }
            ]
        },
        {
            xtype: 'textareafield',
            disabled: true,
            hidden: true,
            hideEmptyLabel: false,
            labelWidth: 10,
            name: 'comments',
            validateOnChange: false,
            validateOnBlur: false,
            emptyText: 'Παρατηρήσεις',
            maxLength: 1000
        },
        {
            xtype: 'hiddenfield',
            flex: 1,
            fieldLabel: 'Label',
            name: 'id',
            submitValue: false
        }
    ]

});