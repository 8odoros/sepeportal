/*
 * File: app/view/company/DoctorAnn/ReplaceInfoForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.DoctorAnn.ReplaceInfoForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companydoctorannreplaceinfoform',

    requires: [
        'MyApp.view.company.DoctorAnn.ReplaceInfoFormViewModel',
        'MyApp.view.company.DoctorAnn.ReplaceInfoFormViewController',
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

    controller: 'companydoctorannreplaceinfoform',
    viewModel: {
        type: 'companydoctorannreplaceinfoform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    overflowY: 'auto',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Αντικατάσταση Αναγγελίας Ιατρού Εργασίας',
    //modal: true,
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'form',
            id: 'companydoctorannbranch2',
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
                    labelWidth: 180,
                    name: 'compPtlBranchId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    flex: 1,
                    height: 150,
                    labelWidth: 180,
                    name: 'compEbrBranchId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    flex: 1,
                    height: 150,
                    labelWidth: 180,
                    name: 'compIeAnnPrevId',
                    validateOnChange: false
                },
                {
                    xtype: 'fieldset',
                    flex: 1,
                    focusOnToFront: false,
                    toFrontOnShow: false,
                    title: 'ΗΜΕΡΟΜΗΝΙΕΣ ΝΕΑΣ ΑΝΑΓΓΕΛΙΑΣ',
                    items: [
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            fieldLabel: 'Από',
                            msgTarget: 'under',
                            name: 'dateStart',
                            validateOnChange: false,
                            readOnly: true,
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
                            readOnly: true,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
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
            dock: 'bottom',
            html: '<span style="font-size:12px;"><em><br>* Οι ημερομηνίες θα είναι από σήμερα μέχρι την λήξη της προς αντικατάσταση αναγγελίας</em></span>',
            id: 'replaceformdoctorann_toolbar',
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
                    width: 150,
                    glyph: 'xf061@FontAwesome',
                    text: 'Συνέχεια',
                    listeners: {
                        click: {
                            fn: 'info_COMPANY_DOCTOR_ANN_REPLACE',
                            scope: 'controller'
                        }
                    }
                }
            ],
            listeners: {
                beforehide: 'onCompDoctorAnnInfoPrev_submit_toolbarBeforeHide'
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
    listeners: {
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

    onCompDoctorAnnInfoPrev_submit_toolbarBeforeHide: function(component, eOpts) {
        component.getComponent('savebutton').destroy();
    }

});