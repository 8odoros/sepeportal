/*
 * File: app/view/company/TechnicianAnn/DatesForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianAnn.DatesForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companytechniciananndatesform',

    requires: [
        'MyApp.view.company.TechnicianAnn.DatesFormViewModel',
        'MyApp.view.company.TechnicianAnn.DatesFormViewController',
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

    controller: 'companytechniciananndatesform',
    viewModel: {
        type: 'companytechniciananndatesform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    overflowY: 'auto',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Αναγγελία Τεχνικού Ασφάλειας',
    modal: true,
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'form',
            id: 'companytechniciananndates',
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
                    name: 'ebrBranchId',
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
                            submitFormat: 'Y-m-d\\T00:00:00.000+0000',
                            minValue: Ext.Date.add(new Date(
                                new Date().getFullYear(),
                                new Date().getMonth() - 1,
                                new Date().getDate()
                            ), Ext.Date.DAY,1),
                            minText: 'Η ημερομηνία έναρξης πρέπει να είναι το πολύ 1 μήνα πριν από την τρέχουσα ημερομηνία',
                            maxValue: Ext.Date.add(new Date(
                                new Date().getFullYear(),
                                new Date().getMonth() + 1,
                                new Date().getDate()
                            ), Ext.Date.DAY,-1),
                            maxText: 'Η ημερομηνία έναρξης πρέπει να είναι το πολύ 1 μήνα μετά από την τρέχουσα ημερομηνία',
                            listeners:
                            {
                                change: function () {
                                    this.up('form').getForm().findField('dateEnd').enable();
                                    /*var today = new Date(
                                        new Date().getFullYear(),
                                        new Date().getMonth(),
                                        new Date().getDate());
                                    if (today > this.up('form').getForm().findField('dateStart').getValue())
                                        this.up('form').getForm().findField('dateEnd').setMinValue(today);
                                    else
                                        this.up('form').getForm().findField('dateEnd').setMinValue(this.up('form').getForm().findField('dateStart').getValue());*/
                                    this.up('form').getForm().findField('dateEnd').setMinValue(Ext.Date.add(new Date(
                                        new Date().getFullYear(),
                                        new Date().getMonth() - 1,
                                        new Date().getDate()
                                    ), Ext.Date.DAY,1));
                                    this.up('form').getForm().findField('dateEnd').setMaxValue(Ext.Date.add(Ext.Date.add(this.up('form').getForm().findField('dateStart').getValue(), Ext.Date.YEAR,1), Ext.Date.DAY, -1));
                                }
                            }
                        },
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            fieldLabel: 'Έως',
                            msgTarget: 'under',
                            name: 'dateEnd',
                            disabled: true,
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 100,
                            format: 'd-m-Y',
                            submitFormat: 'Y-m-d\\T00:00:00.000+0000',
                            minText: 'Η ημερομηία λήξης πρέπει να είναι μεταγενέστερη της ημερομηνίας έναρξης',
                            maxText: 'Η ημερομηία λήξης πρέπει να είναι το πολύ 1 χρόνο μετά την ημερομηνία έναρξης',
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
            html: '<span style="font-size:12px;"><em><br>* Δώστε ημερομηνίες για έλεγχο επικάλυψης με ήδη ενεργές αναγγελίες</em></span>',
            id: 'comptechniciananndates_sumbit_toolbar',
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
                    text: 'Έλεγχος Ημερομηνιών',
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