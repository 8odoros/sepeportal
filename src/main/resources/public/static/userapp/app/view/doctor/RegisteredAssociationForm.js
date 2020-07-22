/*
 * File: app/view/doctor/RegisteredAssociationForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.doctor.RegisteredAssociationForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.doctorregisteredassociationform',

    requires: [
        'MyApp.view.doctor.RegisteredAssociationFormViewModel',
        'MyApp.view.doctor.RegisteredAssociationFormViewController',
        'MyApp.view.shared.CloseFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.field.ComboBox',
        'Ext.form.field.File',
        'Ext.toolbar.Toolbar',
        'Ext.button.Button',
        'Ext.panel.Tool'
    ],

    controller: 'doctorregisteredassociationform',
    viewModel: {
        type: 'doctorregisteredassociationform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    resizable: false,
    width: 800,
    closable: false,
    title: 'Βεβαίωση για απασχόληση σε Ιατρικό Σύλλογο',
    modal: true,
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'form',
            id: 'doctorregassocform',
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
                    xtype: 'combobox',
                    flex: 1,
                    fieldLabel: 'Ιατρικός Σύλλογος',
                    labelWidth: 180,
                    name: 'medassocNotifiedId',
                    validateOnChange: false,
                    validateOnBlur: false,
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    editable: false,
                    autoLoadOnValue: true,
                    displayField: 'spMedasDescription',
                    store: 'doctor.MEDICAL_ASSOC',
                    valueField: 'abbr'
                },
                {
                    xtype: 'form',
                    flex: 1,
                    id: 'docassofile',
                    standardSubmit: false,
                    items: [
                        {
                            xtype: 'filefield',
                            anchor: '100%',
                            frame: false,
                            fieldLabel: 'Βεβαίωση',
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
                    xtype: 'hiddenfield',
                    width: 150,
                    fieldLabel: 'Όνομα',
                    labelWidth: 180,
                    name: 'doctorId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    width: 150,
                    fieldLabel: 'Όνομα',
                    labelWidth: 180,
                    name: 'submitDate',
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
            id: 'regassocie_save_submit_toolbar',
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
                            fn: 'onSubmit_DOCTOR_REGASSOC',
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
        beforedestroy: 'onWindowBeforeDestroy'
    },

    onWindowBeforeDestroy: function(component, eOpts) {
        var view=Ext.getCmp('doctormainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.doctor.RegisteredAssociationsPanel');
        center.add(viewsub);
    }

});