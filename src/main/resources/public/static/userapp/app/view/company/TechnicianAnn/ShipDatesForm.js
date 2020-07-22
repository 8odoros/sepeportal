/*
 * File: app/view/company/TechnicianAnn/ShipDatesForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianAnn.ShipDatesForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companytechnicianannshipdatesform',

    requires: [
        'MyApp.view.company.TechnicianAnn.ShipDatesFormViewModel',
        'MyApp.view.company.TechnicianAnn.ShipDatesFormViewController',
        'MyApp.view.shared.PrintFormTool',
        'MyApp.view.shared.CloseFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.FieldSet',
        'Ext.form.field.Date',
        'Ext.toolbar.Toolbar',
        'Ext.button.Button',
        'Ext.panel.Tool'
    ],

    controller: 'companytechnicianannshipdatesform',
    viewModel: {
        type: 'companytechnicianannshipdatesform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    overflowY: 'auto',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Αναγγελία Τεχνικού Ασφάλειας σε Πλοία και Ναυπηγικές Εργασίες',
    modal: true,
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'form',
            id: 'companytechniciananndates1',
            padding: 15,
            title: '',
            layout: {
                type: 'vbox',
                align: 'stretch'
            },
            items: [
                {
                    xtype: 'hiddenfield',
                    name: 'a_new_form',
                    submitValue: false,
                    validateOnChange: false,
                    value: true
                },
                {
                    xtype: 'hiddenfield',
                    flex: 1,
                    height: 150,
                    fieldLabel: 'Ημ. Πρωτοκόλλου',
                    labelWidth: 180,
                    name: 'companyId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    flex: 1,
                    height: 150,
                    fieldLabel: 'Ημ. Πρωτοκόλλου',
                    labelWidth: 180,
                    name: 'shipId',
                    validateOnChange: false,
                    value: 0
                },
                {
                    xtype: 'fieldset',
                    flex: 1,
                    focusOnToFront: false,
                    toFrontOnShow: false,
                    title: 'ΕΛΕΓΧΟΣ ΗΜΕΡΟΜΗΝΙΩΝ ΑΝΑΓΓΕΛΙΑΣ',
                    items: [
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            fieldLabel: 'Από',
                            msgTarget: 'under',
                            name: 'dateStart',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 100,
                            format: 'd-m-Y',
                            submitFormat: 'Y-m-d\\T00:00:00.000+0000'
                        },
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            fieldLabel: 'Έως',
                            msgTarget: 'under',
                            name: 'dateEnd',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 100,
                            format: 'd-m-Y',
                            submitFormat: 'Y-m-d\\T00:00:00.000+0000'
                        }
                    ]
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
            dock: 'bottom',
            html: '<span style="font-size:12px;"><em><br>* Δώστε ημερομηνίες για έλγχο επικάλυψης με ήδη ενεργές αναγγελίες</em></span>',
            id: 'comptechniciananndates_sumbit_toolbar1',
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
                    itemId: 'savebutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 250,
                    glyph: 'xf0c7@FontAwesome',
                    text: 'Kαταχώρηση Περιόδου Εργασιών',
                    listeners: {
                        click: {
                            fn: 'onCheck_COMPANY_TECHNICIAN_ANN_DATES',
                            scope: 'controller'
                        }
                    }
                }
            ],
            listeners: {
                beforehide: 'onCompTechnicianAnnBranch_save_submit_toolbarBeforeHide'
            }
        }
    ],
    tools: [
        {
            xtype: 'sharedprintformtool'
        },
        {
            xtype: 'sharedcloseformtool'
        }
    ],

    onCompTechnicianAnnBranch_save_submit_toolbarBeforeHide: function(component, eOpts) {
        component.getComponent('savebutton').destroy();
    }

});