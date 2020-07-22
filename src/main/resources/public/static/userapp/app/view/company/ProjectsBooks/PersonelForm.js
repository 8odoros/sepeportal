/*
 * File: app/view/company/ProjectsBooks/PersonelForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.ProjectsBooks.PersonelForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companyprojectsbookspersonelform',

    requires: [
        'MyApp.view.company.ProjectsBooks.PersonelFormViewModel',
        'MyApp.view.company.ProjectsBooks.PersonelFormViewController',
        'MyApp.view.shared.PrintFormTool',
        'MyApp.view.shared.CloseFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.FieldSet',
        'Ext.form.field.Date',
        'Ext.form.field.Number',
        'Ext.form.FieldContainer',
        'Ext.form.field.Time',
        'Ext.form.field.TextArea',
        'Ext.toolbar.Toolbar',
        'Ext.button.Button',
        'Ext.panel.Tool'
    ],

    controller: 'companyprojectsbookspersonelform',
    viewModel: {
        type: 'companyprojectsbookspersonelform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    overflowY: 'auto',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Φόρμα Απασχολούμενου Προσωπικού',
    //modal: true,

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
            id: 'projectsbookpersonel',
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
                    focusOnToFront: false,
                    padding: 10,
                    toFrontOnShow: false,
                    title: 'ΣΤΟΙΧΕΙΑ ΗΜΕΡΗΣΙΟΥ ΔΕΛΤΙΟΥ',
                    items: [
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            disabled: true,
                            disabledCls: 'x-form-readonly',
                            fieldLabel: 'Α/Α Ημερήσιου Δελτίου',
                            labelWidth: 180,
                            name: 'dailyCardId',
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            disabled: true,
                            disabledCls: 'x-form-readonly',
                            fieldLabel: 'Ημερομηνία Δελτίου',
                            labelWidth: 180,
                            name: 'dailyCardDate',
                            submitValue: false,
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            maxLength: 50
                        }
                    ]
                },
                {
                    xtype: 'fieldset',
                    flex: 1,
                    focusOnToFront: false,
                    padding: 10,
                    toFrontOnShow: false,
                    title: 'ΣΤΟΙΧΕΙΑ ΑΠΑΣΧΟΛΟΥΜΕΝΟΥ ΠΡΟΣΩΠΙΚΟΥ',
                    items: [
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            disabledCls: 'x-form-field',
                            fieldLabel: 'Α/Α',
                            labelWidth: 180,
                            name: 'incNum',
                            validateOnChange: false,
                            readOnly: true,
                            validateOnBlur: false,
                            editable: false
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Όνομα',
                            labelWidth: 180,
                            name: 'name',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 128
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Επώνυμο',
                            labelWidth: 180,
                            name: 'surname',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 180
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Α.Μ. ΙΚΑ',
                            labelWidth: 180,
                            name: 'ikaId',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 20
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Ειδικότητα',
                            labelWidth: 180,
                            name: 'speciality',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 128
                        },
                        {
                            xtype: 'numberfield',
                            anchor: '100%',
                            fieldLabel: 'Μικτό Ημερομίσθιο',
                            labelWidth: 180,
                            name: 'dailySalary',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            hideTrigger: true,
                            maxLength: 10,
                            repeatTriggerClick: false,
                            keyNavEnabled: false,
                            mouseWheelEnabled: false,
                            spinDownEnabled: false,
                            spinUpEnabled: false,
                            allowDecimals: false,
                            allowExponential: false,
                            submitLocaleSeparator: false
                        },
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            fieldLabel: 'Ημερομηνία Πρόσληψης',
                            labelWidth: 180,
                            name: 'dateOfRecruitmentView',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50,
                            format: 'd-m-Y'
                        },
                        {
                            xtype: 'hiddenfield',
                            anchor: '100%',
                            fieldLabel: 'Ημερομηνία Πρόσληψης',
                            labelWidth: 180,
                            name: 'dateOfRecruitment',
                            validateOnChange: false
                        },
                        {
                            xtype: 'fieldcontainer',
                            suspendLayout: true,
                            fieldLabel: 'Ωράριο Εργασίας',
                            labelWidth: 180,
                            layout: {
                                type: 'hbox',
                                align: 'middle'
                            },
                            items: [
                                {
                                    xtype: 'timefield',
                                    autoFitErrors: false,
                                    fieldLabel: 'Από',
                                    labelWidth: 40,
                                    name: 'workingHourStart',
                                    submitValue: false,
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    blankText: 'Επιλέξτε ώρα',
                                    editable: false,
                                    forceSelection: true,
                                    format: 'H:i',
                                    increment: 30
                                },
                                {
                                    xtype: 'timefield',
                                    margin: '0 0 0 20',
                                    autoFitErrors: false,
                                    fieldLabel: 'Εως',
                                    labelWidth: 40,
                                    name: 'workingHourStop',
                                    submitValue: false,
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    blankText: 'Επιλέξτε ώρα',
                                    editable: false,
                                    forceSelection: true,
                                    format: 'H:i',
                                    increment: 30
                                }
                            ]
                        },
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            fieldLabel: 'Ημέρα Αναπαύσεως',
                            labelWidth: 180,
                            name: 'restingDayView',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50,
                            format: 'd-m-Y'
                        },
                        {
                            xtype: 'hiddenfield',
                            anchor: '100%',
                            fieldLabel: 'Ημέρα Αναπαύσεως',
                            labelWidth: 180,
                            name: 'restingDay',
                            validateOnChange: false
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            fieldLabel: 'Παρατηρήσεις',
                            labelWidth: 180,
                            name: 'notes',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 3000,
                            validateBlank: true
                        }
                    ]
                },
                {
                    xtype: 'fieldset',
                    flex: 1,
                    title: 'Ιστορικό Ημερήσιου Δελτίου',
                    items: [
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            disabledCls: 'x-form-item',
                            fieldLabel: 'Ημερομηνία Καταχώρησης',
                            labelWidth: 180,
                            name: 'submitDateView',
                            validateOnChange: false,
                            readOnly: true,
                            validateOnBlur: false,
                            editable: false,
                            hideTrigger: true,
                            maxLength: 50,
                            format: 'd-m-Y'
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Ώρα Καταχώρησης',
                            labelWidth: 180,
                            name: 'submitTimeView',
                            validateOnChange: false,
                            readOnly: true,
                            validateOnBlur: false,
                            editable: false,
                            hideTrigger: true
                        },
                        {
                            xtype: 'hiddenfield',
                            anchor: '100%',
                            cls: 'x-item-disabled',
                            fieldLabel: 'Ώρα Αίτησης',
                            labelWidth: 180,
                            name: 'submitTime',
                            validateOnChange: false
                        },
                        {
                            xtype: 'hiddenfield',
                            anchor: '100%',
                            cls: 'x-item-disabled',
                            fieldLabel: 'Ώρα Αίτησης',
                            labelWidth: 180,
                            name: 'editTime',
                            validateOnChange: false
                        },
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            fieldLabel: 'Ημερομηνία Επεξεργασίας',
                            labelWidth: 180,
                            name: 'editDateView',
                            validateOnChange: false,
                            readOnly: true,
                            validateOnBlur: false,
                            editable: false,
                            maxLength: 50,
                            format: 'd-m-Y'
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Ώρα Επεξεργασίας',
                            labelWidth: 180,
                            name: 'editTimeView',
                            validateOnChange: false,
                            readOnly: true,
                            validateOnBlur: false,
                            editable: false,
                            hideTrigger: true
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
            dateToTimestamp: function(date) {
                var pD = date.split("-");
                return (pD[2]+"-"+pD[1]+"-"+pD[0]+"T00:00:00.000+0000");
            },
            dock: 'bottom',
            html: '<span style="color:#696969;font-size:11px;"><em>*Τα ανενεργά πεδία θα συμπληρωθούν αυτόματα<br>από το σύστημα κατά την υποβολή</em></span>',
            id: 'compperso_save_submit_toolbar',
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
                        click: 'onSubmit_COMPANY_PERSONEL'
                    }
                }
            ]
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
    tools: [
        {
            xtype: 'sharedprintformtool'
        },
        {
            xtype: 'sharedcloseformtool'
        }
    ]

});