/*
 * File: app/view/company/TechnicianBooks/ShipNoteForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianBooks.ShipNoteForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companytechnicianbooksshipnoteform',

    requires: [
        'MyApp.view.company.TechnicianBooks.ShipNoteFormViewModel',
        'MyApp.view.company.TechnicianBooks.ShipNoteFormViewController',
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

    controller: 'companytechnicianbooksshipnoteform',
    viewModel: {
        type: 'companytechnicianbooksshipnoteform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    resizable: false,
    width: 800,
    title: 'Υπόδειξη',
    modal: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'form',
            id: 'techshipbooknote',
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
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Όνομα Πλοίου',
                            labelWidth: 180,
                            name: 'shipName',
                            validateOnChange: false,
                            readOnly: true,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            editable: false
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Τεχνικός Ασφάλειας',
                            labelWidth: 180,
                            msgTarget: 'none',
                            name: 'authorName',
                            validateOnChange: false,
                            readOnly: true,
                            validateOnBlur: false,
                            maxLength: 100
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
                            readOnly: true,
                            validateOnBlur: false,
                            maxLength: 2000
                        },
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            fieldLabel: 'Το σύστημα ενημερώθηκε ότι η υπόδειξη διαβάστηκε!',
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
            hidden: true,
            html: '',
            id: 'comptechniciannote_save_submit_toolbar1',
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
                    text: ' Δημιουργία Βιβλίου',
                    listeners: {
                        click: 'onSubmit_COMPANY_TECHNICIAN_BOOK'
                    }
                }
            ]
        }
    ],
    tools: [
        {
            xtype: 'sharedprintformtool'
        }
    ]

});