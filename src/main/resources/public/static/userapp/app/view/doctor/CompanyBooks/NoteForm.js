/*
 * File: app/view/doctor/CompanyBooks/NoteForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.doctor.CompanyBooks.NoteForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.doctorcompanybooksnoteform',

    requires: [
        'MyApp.view.doctor.CompanyBooks.NoteFormViewModel',
        'MyApp.view.doctor.CompanyBooks.NoteFormViewController',
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

    controller: 'doctorcompanybooksnoteform',
    viewModel: {
        type: 'doctorcompanybooksnoteform'
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
            id: 'doctorbooknote1',
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
                    name: 'compIeAnnId',
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
            id: 'doctorbooknote_save_submit_toolbar',
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
                            fn: 'onSubmit_COMPANY_DOCTOR_BOOK',
                            scope: 'controller'
                        }
                    }
                }
            ],
            listeners: {
                beforehide: 'onDoctorBookNote_save_submit_toolbarBeforeHide'
            }
        }
    ],
    tools: [
        {
            xtype: 'sharedprintformtool'
        }
    ],

    onDoctorBookNote_save_submit_toolbarBeforeHide: function(component, eOpts) {
        component.getComponent('submitbutton').destroy();
    }

});