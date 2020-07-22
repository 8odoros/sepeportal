/*
 * File: app/view/technician/DiplomaForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.technician.DiplomaForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.techniciandiplomaform',

    requires: [
        'MyApp.view.technician.DiplomaFormViewModel',
        'MyApp.view.technician.DiplomaFormViewController',
        'MyApp.view.shared.CloseFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.field.TextArea',
        'Ext.form.field.Number',
        'Ext.form.field.File',
        'Ext.toolbar.Toolbar',
        'Ext.button.Button',
        'Ext.panel.Tool'
    ],

    controller: 'techniciandiplomaform',
    viewModel: {
        type: 'techniciandiplomaform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    resizable: false,
    width: 800,
    closable: false,
    title: 'Απαιτούμενα Επισυναπτόμενα Έγγραφα',
    //modal: true,
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'form',
            id: 'techniciandiplomaform',
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
                    flex: 1,
                    fieldLabel: 'Επισυναπτόμενο Αρχείο',
                    labelWidth: 180,
                    msgTarget: 'under',
                    name: 'attachedDocId',
                    validateOnChange: false,
                    value: '-1'
                },
                {
                    xtype: 'textareafield',
                    flex: 1,
                    fieldLabel: 'Τίτλος Σπουδών',
                    labelWidth: 180,
                    name: 'diplomaDescr',
                    validateOnChange: false,
                    validateOnBlur: false,
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    maxLength: 500
                },
                {
                    xtype: 'datefield',
                    anchor: '100%',
                    fieldLabel: 'Ημ. Λήψης Πτυχίου',
                    labelWidth: 180,
                    name: 'diplomaYearView',
                    validateOnChange: false,
                    validateOnBlur: false,
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    maxValue: new Date(),
                    maxLength: 50,
                    format: 'd-m-Y'
                },
                {
                    xtype: 'hiddenfield',
                    anchor: '100%',
                    fieldLabel: 'Ημ. Λήψης Πτυχίου',
                    labelWidth: 180,
                    name: 'diplomaYear',
                    validateOnChange: false
                },
                
                /*{
                    xtype: 'numberfield',
                    flex: 1,
                    fieldLabel: 'Χρονολογία Λήψης',
                    labelWidth: 180,
                    name: 'diplomaYear',
                    validateOnChange: false,
                    validateOnBlur: false,
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    hideTrigger: true,
                    maxLength: 4,
                    minLength: 4,
                    repeatTriggerClick: false,
                    keyNavEnabled: false,
                    mouseWheelEnabled: false,
                    spinDownEnabled: false,
                    spinUpEnabled: false,
                    allowDecimals: false,
                    allowExponential: false,
                    minValue: 1000,
                    submitLocaleSeparator: false
                },*/
                {
                    xtype: 'form',
                    flex: 1,
                    id: 'docdiplomafile',
                    standardSubmit: false,
                    items: [
                        {
                            xtype: 'filefield',
                            anchor: '100%',
                            frame: false,
                            fieldLabel: 'Αντίγραφο Πτυχίου',
                            labelWidth: 180,
                            msgTarget: 'under',
                            name: 'file',
                            submitValue: true,
                            validateOnChange: false,
                            inputId: 'file',
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            buttonText: 'Επιλογή...'
                        }
                    ]
                },
                {
                    xtype: 'displayfield',
                    fieldLabel: 'Παρακαλώ επισυνάψτε σε ένα αρχείο το πτυχίο, την άδεια ασκήσεως επαγγέλματος και την Βεβαίωση Ισοτιμίας εφόσον πρόκειται για τίτλο σπουδών εξωτερικού.',
                    labelSeparator: '',
                    labelWidth: 768,
                    name: 'home_score',
                    value: ''
                },
                {
                    xtype: 'hiddenfield',
                    width: 150,
                    fieldLabel: 'Όνομα',
                    labelWidth: 180,
                    name: 'technicianId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    height: 150,
                    labelWidth: 180,
                    name: 'url',
                    validateOnChange: false
                }
            ]
        }
    ],
    dockedItems: [
        {
            xtype: 'toolbar',
            getCurrentTimestamp: function() {
                var currentDate = new Date();

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


            },
            dateToTimestamp: function(date) {
                var pD = date.split("-");
                return (pD[2]+"-"+pD[1]+"-"+pD[0]+"T00:00:00.000+0000");
            },
            dock: 'bottom',
            id: 'diplomata_save_submit_toolbar',
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
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf1d8@FontAwesome',
                    text: 'Καταχώρηση',
                    listeners: {
                        click: {
                            fn: 'onSubmit_TECHNICIAN_DIPLOMA',
                            scope: 'controller'
                        }
                    }
                }
            ]
        }
    ],
    tools: [
        {
            xtype: 'sharedcloseformtool'
        }
    ],
    listeners: {
        beforedestroy: 'onWindowBeforeDestroy',
        show: function(component, eOpts) {
            var view = Ext.getCmp('technicianmainView');
            var menu = view.getComponent('headerPanel');
            var contentPanel = view.getComponent('contentPanel');
            menu.setDisabled(true);
            contentPanel.setDisabled(true);
        },
        hide: function(button, e, eOpts) {
            var view = Ext.getCmp('technicianmainView');
            var menu = view.getComponent('headerPanel');
            var contentPanel = view.getComponent('contentPanel');
            menu.setDisabled(false);
            contentPanel.setDisabled(false);
        }
    },

    onWindowBeforeDestroy: function(component, eOpts) {
        var view=Ext.getCmp('technicianmainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.technician.DiplomasPanel');
        center.add(viewsub);
    }

});