/*
 * File: app/view/company/SafetyBook/NoteForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.SafetyBook.NoteForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companysafetybooknoteform',

    requires: [
        'MyApp.view.company.SafetyBook.NoteFormViewModel',
        'MyApp.view.company.SafetyBook.NoteFormViewController',
        'MyApp.view.shared.PrintFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.FieldSet',
        'Ext.form.field.Date',
        'Ext.form.field.ComboBox',
        'Ext.form.field.TextArea',
        'Ext.toolbar.Toolbar',
        'Ext.button.Button',
        'Ext.panel.Tool'
    ],

    controller: 'companysafetybooknoteform',
    viewModel: {
        type: 'companysafetybooknoteform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    resizable: false,
    width: 800,
    title: 'Εγγραφή Ημερολογίου',
    //modal: true,
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'form',
            timestampToDate: function(timestamp) {

                var pD = timestamp.replace(/[^0-9]+/g,' ').split(" ");
                return (pD[2]+"-"+pD[1]+"-"+pD[0]);
            },
            id: 'doctorbooknote2',
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
                    name: 'compSecDiaryId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    fieldLabel: '',
                    name: 'url',
                    validateOnChange: false
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
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'A/A',
                            labelWidth: 180,
                            msgTarget: 'none',
                            name: 'aa',
                            validateOnChange: false,
                            readOnly: true,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 100
                        },
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            fieldLabel: 'Ημερομηνία Εγγραφής',
                            labelWidth: 180,
                            msgTarget: 'none',
                            name: 'creationDate',
                            validateOnChange: false,
                            readOnly: true,
                            validateOnBlur: false,
                            maxLength: 100,
                            format: 'd-m-Y',
                            submitFormat: 'Y-m-d\\T00:00:00.000+0000'
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            fieldLabel: 'Υπεύθυνος Εγγραφής',
                            labelWidth: 180,
                            name: 'compSecDiaryEngId',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            editable: false,
                            maxLength: 200,
                            displayField: 'fullname',
                            store: 'company.SafetyBooks.Eng',
                            valueField: 'engId'
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            height: 200,
                            minHeight: 200,
                            fieldLabel: 'Διαπίστωση',
                            labelWidth: 180,
                            msgTarget: 'none',
                            name: 'assessment',
                            validateOnChange: false,
                            readOnly: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 2000
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            height: 200,
                            minHeight: 200,
                            fieldLabel: 'Κείμενο Υπόδειξης',
                            labelWidth: 180,
                            msgTarget: 'none',
                            name: 'suggestion',
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
                            fieldLabel: 'Ημερομηνία Επεξεργασίας',
                            labelWidth: 180,
                            msgTarget: 'none',
                            name: 'updateDate',
                            validateOnChange: false,
                            readOnly: true,
                            validateOnBlur: false,
                            maxLength: 100,
                            format: 'd-m-Y',
                            submitFormat: 'Y-m-d\\T00:00:00.000+0000'
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
            id: 'safetybooknote_save_submit_toolbar',
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
                            fn: 'onSubmit_COMPANY_SAFETYBOOK_NOTE',
                            scope: 'controller'
                        }
                    }
                }
            ],
            listeners: {
                beforehide: 'onSafetyBookNote_save_submit_toolbarBeforeHide'
            }
        }
    ],
    listeners: {
        beforedestroy: 'onWindowBeforeDestroy',
        show: function(component, eOpts) {
            var view = Ext.getCmp('companymainView');
            var menu = view.getComponent('menuPanel');
            var contentPanel = view.getComponent('contentPanel');
            menu.setDisabled(true);
            contentPanel.setDisabled(true);
        },
        hide: function(button, e, eOpts) {
            var view = Ext.getCmp('companymainView');
            var menu = view.getComponent('menuPanel');
            var contentPanel = view.getComponent('contentPanel');
            menu.setDisabled(false);
            contentPanel.setDisabled(false);
        }
    },
    tools: [
        {
            xtype: 'sharedprintformtool'
        }
    ],

    onSafetyBookNote_save_submit_toolbarBeforeHide: function(component, eOpts) {
        component.getComponent('submitbutton').destroy();
    },

    onWindowBeforeDestroy: function(component, eOpts) {
        var view = Ext.getCmp('companymainView');
        var menu = view.getComponent('menuPanel');
        var contentPanel = view.getComponent('contentPanel');
        menu.setDisabled(false);
        contentPanel.setDisabled(false);
    },

});