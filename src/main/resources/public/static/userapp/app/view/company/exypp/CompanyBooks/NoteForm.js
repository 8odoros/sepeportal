/*
 * File: app/view/company/exypp/CompanyBooks/NoteForm.js
 *
 * This file was generated by Sencha Architect version 3.2.0.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Ext JS 5.0.x library, under independent license.
 * License of Sencha Architect does not include license for Ext JS 5.0.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

Ext.define('MyApp.view.company.exypp.CompanyBooks.NoteForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companyexyppcompanybooksnoteform',

    requires: [
        'MyApp.view.company.exypp.CompanyBooks.NoteFormViewModel',
        'MyApp.view.company.exypp.CompanyBooks.NoteFormViewController',
        'MyApp.view.shared.PrintFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.FieldSet',
        'Ext.form.field.Date',
        'Ext.form.field.TextArea',
        'Ext.toolbar.Toolbar',
        'Ext.button.Button',
        'Ext.panel.Tool'
    ],

    controller: 'companyexyppcompanybooksnoteform',
    viewModel: {
        type: 'companyexyppcompanybooksnoteform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    resizable: false,
    width: 800,
    title: 'Υπόδειξη',
    modal: true,
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'form',
            id: 'companyexyppbooknote',
            padding: 15,
            title: '',
            layout: {
                type: 'vbox',
                align: 'stretch'
            },
            items: [
                {
                    xtype: 'hiddenfield',
                    fieldLabel: '',
                    name: 'a_new_form',
                    submitValue: false,
                    validateOnChange: false,
                    value: true
                },
                {
                    xtype: 'hiddenfield',
                    fieldLabel: '',
                    name: 'branchId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    fieldLabel: '',
                    name: 'compTaAnnId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    fieldLabel: '',
                    name: 'read',
                    validateOnChange: false,
                    value: 0
                },
                {
                    xtype: 'fieldset',
                    flex: 1,
                    focusOnToFront: false,
                    padding: 10,
                    toFrontOnShow: false,
                    title: '',
                    items: [
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            fieldLabel: 'Ημερομηνία Καταχώρησης',
                            labelWidth: 180,
                            msgTarget: 'none',
                            name: 'dateCreated',
                            validateOnChange: false,
                            readOnly: true,
                            validateOnBlur: false,
                            maxLength: 100,
                            format: 'd-m-Y',
                            submitFormat: 'y-m-d\\T00:00:00.000+0000'
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            height: 200,
                            minHeight: 200,
                            fieldLabel: 'Κείμενο Υπόδειξης',
                            labelWidth: 180,
                            msgTarget: 'none',
                            name: 'notes',
                            validateOnChange: false,
                            readOnly: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 2000
                        },
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            hidden: true,
                            fieldLabel: 'H υπόδειξη διαβάστηκε από τον οργανισμό!',
                            labelWidth: 320,
                            msgTarget: 'none',
                            name: 'readDate',
                            validateOnChange: false,
                            readOnly: true,
                            validateOnBlur: false,
                            maxLength: 100,
                            format: 'd-m-Y',
                            submitFormat: 'y-m-d\\T00:00:00.000+0000'
                        }
                    ]
                }
            ]
        }
    ],
    dockedItems: [
        {
            xtype: 'toolbar',
            getCurrentTimestamp: function(part) {
                var currentDate = new Date();
                if (part === 1){
                    var month = currentDate.getMonth()+1;
                    if (month.toString().length < 2){
                        month = "0"+month;
                    }
                    var date = currentDate.getDate();
                    if (date.toString().length < 2){
                        date = "0"+date;
                    }
                    var hours = currentDate.getHours();
                    if (hours.toString().length < 2){
                        hours = "0"+hours;
                    }
                    var minutes = currentDate.getMinutes();
                    if (minutes.toString().length < 2){
                        minutes = "0"+minutes;
                    }
                    var seconds = currentDate.getSeconds();
                    if (seconds.toString().length < 2){
                        seconds = "0"+seconds;
                    }
                    return  currentDate.getFullYear()+"-"+month+"-"+date+
                    "T"+hours+":"+minutes+":"+seconds+"."+"000"+"+0000";

                }
                else if (part === 2){
                    var hours = currentDate.getHours();
                    if (hours.toString().length < 2){
                        hours = "0"+hours;
                    }
                    var minutes = currentDate.getMinutes();
                    if (minutes.toString().length < 2){
                        minutes = "0"+minutes;
                    }
                    return hours+":"+minutes;
                }
                else if (part === 3){
                    return currentDate.getFullYear();
                }


            },
            dateToTimestamp: function(date) {
                var pD = date.split("-");
                return (pD[2]+"-"+pD[1]+"-"+pD[0]+"T00:00:00.000+0000");
            },
            dock: 'bottom',
            html: '',
            id: 'companyexyppbooknote_save_submit_toolbar',
            style: {
                'background-color': '#f5f5f5'
            },
            layout: {
                type: 'hbox',
                pack: 'end'
            },
            items: [
                {
                    xtype: 'button',
                    itemId: 'submitbutton',
                    padding: 10,
                    glyph: 'xf1d8@FontAwesome',
                    text: 'Καταχώρηση',
                    listeners: {
                        click: {
                            fn: 'onSubmit_COMPANY_TECH_BOOK',
                            scope: 'controller'
                        }
                    }
                }
            ],
            listeners: {
                beforehide: 'onTechBookNote_save_submit_toolbarBeforeHide'
            }
        }
    ],
    tools: [
        {
            xtype: 'sharedprintformtool'
        }
    ],

    onTechBookNote_save_submit_toolbarBeforeHide: function(component, eOpts) {
        component.getComponent('submitbutton').destroy();
    }

});