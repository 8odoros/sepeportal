/*
 * File: app/view/company/DriversPmtForm/DriverPmtForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.DriversPmtForm.DriverPmtForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companydriverspmtformdriverpmtform',

    requires: [
        'MyApp.view.company.DriversPmtForm.DriverPmtFormViewModel',
        'MyApp.view.company.DriversPmtForm.DriverPmtFormViewController',
        'MyApp.view.shared.PrintFormTool',
        'MyApp.view.shared.CloseFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.FieldSet',
        'Ext.form.field.Date',
        'Ext.form.field.Number',
        'Ext.form.field.ComboBox',
        'Ext.form.field.TextArea',
        'Ext.button.Button',
        'Ext.toolbar.Toolbar',
        'Ext.panel.Tool'
    ],

    controller: 'companydriverspmtformdriverpmtform',
    viewModel: {
        type: 'companydriverspmtformdriverpmtform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    overflowY: 'scroll',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Φόρμα Αίτησης Θεώρησης Βιβλιαρίου Εργασίας (ρεπό) Οδηγού Τουριστικού Λεωφορείου',
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
            id: 'companydriverspmt',
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
                    name: 'companyId',
                    validateOnChange: false
                },
                {
                    xtype: 'fieldset',
                    focusOnToFront: false,
                    padding: 10,
                    toFrontOnShow: false,
                    title: 'ΠΡΩΤΟΚΟΛΛΟ',
                    items: [
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            disabled: true,
                            fieldLabel: 'Αρ. Πρωτοκόλλου',
                            labelWidth: 180,
                            name: 'protNoview',
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'hiddenfield',
                            anchor: '100%',
                            width: 150,
                            fieldLabel: 'Αρ. Πρωτοκόλλου',
                            labelWidth: 180,
                            name: 'protNo',
                            validateOnChange: false
                        },
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            disabled: true,
                            width: 150,
                            fieldLabel: 'Ημ. Πρωτοκόλλου',
                            labelWidth: 180,
                            name: 'protDateview',
                            submitValue: false,
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            format: 'd-m-Y'
                        },
                        {
                            xtype: 'hiddenfield',
                            anchor: '100%',
                            height: 150,
                            fieldLabel: 'Ημ. Πρωτοκόλλου',
                            labelWidth: 180,
                            name: 'protDate',
                            validateOnChange: false
                        },
                        {
                            xtype: 'numberfield',
                            anchor: '100%',
                            cls: 'x-item-disabled',
                            fieldLabel: 'Έτος Πρωτοκόλλου',
                            labelWidth: 180,
                            name: 'protYear',
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            hideTrigger: true,
                            repeatTriggerClick: false,
                            keyNavEnabled: false,
                            mouseWheelEnabled: false,
                            spinDownEnabled: false,
                            spinUpEnabled: false,
                            allowDecimals: false,
                            allowExponential: false,
                            submitLocaleSeparator: false
                        }
                    ]
                },
                {
                    xtype: 'fieldset',
                    focusOnToFront: false,
                    padding: 10,
                    toFrontOnShow: false,
                    title: 'ΣΤΟΙΧΕΙΑ ΑΙΤΗΣΗΣ',
                    items: [
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            cls: 'x-item-disabled',
                            disabled: true,
                            fieldLabel: 'Αρμόδια Διεύθυνση',
                            labelWidth: 180,
                            name: 'sepeDept',
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            hideTrigger: true,
                            displayField: 'description',
                            store: 'shared.SEPE_DEPT',
                            valueField: 'abbr'
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            cls: 'x-item-disabled',
                            fieldLabel: 'Ώρα Καταχώρησης',
                            labelWidth: 180,
                            name: 'submitTime',
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            hideTrigger: true
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            fieldLabel: 'Μήνας',
                            labelWidth: 180,
                            msgTarget: 'under',
                            name: 'pmtMonth',
                            validateOnChange: false,
                            readOnly: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            editable: false,
                            maxLength: 100,
                            autoLoadOnValue: true,
                            displayField: 'name',
                            valueField: 'value',
                            bind: {
                                store: '{Months}'
                            },
                            listeners: {
                                change: 'onMonthYearChange'
                            }
                        },
                        {
                            xtype: 'numberfield',
                            anchor: '100%',
                            fieldLabel: 'Έτος',
                            labelWidth: 180,
                            msgTarget: 'under',
                            name: 'pmtYear',
                            validateOnChange: false,
                            readOnly: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            hideTrigger: true,
                            maxLength: 4,
                            minLength: 4,
                            allowDecimals: false,
                            allowExponential: false,
                            minValue: 2015,
                            listeners: {
                                blur: 'onMonthYearChange'
                            }
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            cls: 'x-item-disabled',
                            fieldLabel: 'Κατάσταση',
                            labelWidth: 180,
                            name: 'reqStatus',
                            validateOnChange: false,
                            readOnly: true,
                            validateOnBlur: false,
                            editable: false,
                            hideTrigger: true,
                            autoLoadOnValue: true,
                            displayField: 'description',
                            store: 'shared.COMPL_STATUS',
                            valueField: 'reqStatus'
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            cls: 'x-item-disabled',
                            fieldLabel: 'Αρμόδιος Επιθεωρητής',
                            labelWidth: 180,
                            name: 'inspectorName',
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            hideTrigger: true
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            disabled: true,
                            fieldLabel: 'Αιτιολογία',
                            labelWidth: 180,
                            name: 'etiology',
                            submitValue: false,
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            maxLength: 2000
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            hidden: true,
                            fieldLabel: 'Μήνυμα Υπόθεσης',
                            labelWidth: 180,
                            name: 'StatusMsg',
                            submitValue: false,
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'hiddenfield',
                            anchor: '100%',
                            fieldLabel: 'Κατάσταση',
                            labelWidth: 180,
                            name: 'subStatus',
                            validateOnChange: false
                        },
                        {
                            xtype: 'hiddenfield',
                            anchor: '100%',
                            fieldLabel: 'Αριθμός Υπόθεσης',
                            labelWidth: 180,
                            name: 'caseId'
                        },
                        {
                            xtype: 'fieldset',
                            focusOnToFront: false,
                            padding: 10,
                            toFrontOnShow: false,
                            title: 'ΣΤΟΙΧΕΙΑ ΕΡΓΟΔΟΤΗ',
                            items: [
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    disabled: true,
                                    fieldLabel: 'Επωνυμία ή Ονοματεπώνυμο',
                                    labelWidth: 180,
                                    name: 'compName',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 300
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    disabled: true,
                                    fieldLabel: 'Α.Φ.Μ.',
                                    labelWidth: 180,
                                    name: 'compAfm',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 9,
                                    minLength: 9,
                                    maskRe:/[0-9.]/,
                                    vtype: 'Number'
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    disabled: true,
                                    fieldLabel: 'ΑΜΕ ΙΚΑ',
                                    labelWidth: 180,
                                    name: 'compAmeIka',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 100
                                },
                                {
                                    xtype: 'fieldset',
                                    focusOnToFront: false,
                                    toFrontOnShow: false,
                                    hidden: true,
                                    title: 'Έδρα-Διεύθυνση Εργοδότη/Επιχείρησης',
                                    items: [
                                        {
                                            xtype: 'textareafield',
                                            anchor: '100%',
                                            disabled: true,
                                            fieldLabel: 'Οδός / Αριθμός',
                                            labelWidth: 180,
                                            name: 'compAddr',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            maxLength: 100
                                        },
                                        {
                                            xtype: 'combobox',
                                            anchor: '100%',
                                            disabled: true,
                                            fieldLabel: 'Περιφέρεια',
                                            labelWidth: 180,
                                            name: 'compAddrP',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            editable: false,
                                            autoLoadOnValue: true,
                                            displayField: 'descr',
                                            store: 'address.ADDR_P',
                                            transformInPlace: false,
                                            valueField: 'abbr',
                                            listeners: {
                                                select: 'onAddrPSelect21'
                                            }
                                        },
                                        {
                                            xtype: 'combobox',
                                            anchor: '100%',
                                            session: false,
                                            disabled: true,
                                            fieldLabel: 'Νομός',
                                            labelWidth: 180,
                                            name: 'compAddrPe',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            allowBlank: false,
                                            allowOnlyWhitespace: false,
                                            editable: false,
                                            displayField: 'descr',
                                            queryParam: 'perifCode',
                                            store: 'address.ADDR_Pe',
                                            valueField: 'abbr',
                                            listeners: {
                                                beforequery: 'onAddrPeExpand21',
                                                select: 'onAddrPeSelect21',
                                                dirtychange: {
                                                    fn: 'onAddrPeDirtyChange21',
                                                    single: true
                                                }
                                            }
                                        },
                                        {
                                            xtype: 'combobox',
                                            anchor: '100%',
                                            disabled: true,
                                            fieldLabel: 'Δήμος',
                                            labelWidth: 180,
                                            name: 'compAddrD',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            allowBlank: false,
                                            allowOnlyWhitespace: false,
                                            editable: false,
                                            displayField: 'dimosDescr',
                                            queryParam: 'enotCode',
                                            store: 'address.ADDR_D',
                                            valueField: 'abbr',
                                            listeners: {
                                                beforequery: 'onAddrDExpand21',
                                                select: 'onAddrDSelect21',
                                                dirtychange: {
                                                    fn: 'onAddrDDirtyChange21',
                                                    single: true
                                                }
                                            }
                                        },
                                        {
                                            xtype: 'combobox',
                                            anchor: '100%',
                                            disabled: true,
                                            fieldLabel: 'Δημοτικό Διαμέρισμα',
                                            labelWidth: 180,
                                            name: 'compAddrK',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            allowBlank: false,
                                            allowOnlyWhitespace: false,
                                            editable: false,
                                            displayField: 'koinDescr',
                                            queryParam: 'dimosCode',
                                            store: 'address.ADDR_K',
                                            valueField: 'abbr',
                                            listeners: {
                                                beforequery: 'onAddrKExpand21',
                                                dirtychange: {
                                                    fn: 'onAddrKDirtyChange21',
                                                    single: true
                                                }
                                            }
                                        },
                                        {
                                            xtype: 'textfield',
                                            anchor: '100%',
                                            disabled: true,
                                            fieldLabel: 'Τ.Κ',
                                            labelWidth: 180,
                                            name: 'compAddrTk',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            maxLength: 50,
                                            minLength: 5,
                                            vtype: 'Number'
                                        }
                                    ]
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    disabled: true,
                                    fieldLabel: 'Τηλέφωνο Εργοδότη/Επιχείρησης',
                                    labelWidth: 180,
                                    name: 'compPhone',
                                    hidden: true,
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 10,
                                    vtype: 'telNumber'
                                }
                            ]
                        },
                        {
                            xtype: 'hiddenfield',
                            anchor: '100%',
                            labelWidth: 180,
                            name: 'branch0Id',
                            validateOnChange: false,
                            value: 0
                        },
                        {
                            xtype: 'hiddenfield',
                            anchor: '100%',
                            labelWidth: 180,
                            name: 'branch1Id',
                            validateOnChange: false,
                            value: 0
                        },
                        {
                            xtype: 'fieldset',
                            focusOnToFront: false,
                            padding: 10,
                            toFrontOnShow: false,
                            title: 'ΣΤΟΙΧΕΙΑ ΟΧΗΜΑΤΟΣ',
                            items: [
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Αρ. Άδειας Κυκλοφορίας',
                                    labelWidth: 180,
                                    name: 'vehicleLicenceNum',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 50
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Αρ. Πινακίδας',
                                    labelWidth: 180,
                                    name: 'vehiclePlateNum',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 50
                                }
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            focusOnToFront: false,
                            padding: 10,
                            toFrontOnShow: false,
                            title: 'ΣΤΟΙΧΕΙΑ ΟΔΗΓΟΥ',
                            items: [
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Όνομα',
                                    labelWidth: 180,
                                    name: 'ownerFirstname',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 50
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Επώνυμο',
                                    labelWidth: 180,
                                    name: 'ownerLastname',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 50
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Αρ. Διπλώματος',
                                    labelWidth: 180,
                                    name: 'ownerLicenceNum',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 50
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Αριθμός Δελτίου Ταυτότητας',
                                    labelWidth: 180,
                                    name: 'ownerCardNumber',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 50
                                },
                                {
                                    xtype: 'hiddenfield',
                                    anchor: '100%',
                                    fieldLabel: 'Δελτίο Ταυτότητας',
                                    labelWidth: 180,
                                    name: 'ownerCardType',
                                    validateOnChange: false,
                                    value: null
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Εκδίδουσα Άρχη',
                                    labelWidth: 180,
                                    name: 'ownerCardIssuer',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 50
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Α.Φ.Μ.',
                                    labelWidth: 180,
                                    name: 'ownerAfm',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 9,
                                    minLength: 9,
                                    maskRe:/[0-9.]/,
                                }
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            id: 'offdays',
                            title: 'ΣΤΟΙΧΕΙΑ ΡΕΠΟ',
                            items: [
                                {
                                    xtype: 'displayfield',
                                    //fieldLabel: 'Επιλέξτε πρώτα Μήνα και Έτος για να ενεργοποιηθεί το ημερολόγιο',
                                    width: 700,
                                    name: 'multiDatePickerLabel',
                                    id: 'multiDatePickerLabel',
                                    value: 'Επιλέξτε πρώτα Μήνα και Έτος για να ενεργοποιηθεί το ημερολόγιο'
                                },
                                { // https://fiddle.sencha.com/#fiddle/84j
                                    xtype: 'highlightdate',
                                    name: 'mutliDatePicker',
                                    id: 'mutliDatePicker',
                                    cls: 'mutliDatePicker',
                                    anchor:'100%',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    format: 'd-m-Y',
                                    disabled: true,
                                    listeners: {
                                        afterrender: function(label){
                                            var status = label.up('form').getForm().findField('subStatus').getValue();
                                            var picker = Ext.getCmp('mutliDatePicker');
                                            if (status == 2 && picker != null) {
                                                picker.eventEl.setStyle('pointer-events', 'none');
                                                picker.eventEl.setStyle('cursor', 'default');
                                            }
                                        }
                                    }
                                },
                                /*{
                                    xtype: 'button',
                                    border: 0,
                                    frame: false,
                                    id: 'offdayadd',
                                    style: 'background-color:transparent;',
                                    iconCls: 'addone',
                                    tooltip: 'Προσθήκη ρεπό',
                                    listeners: {
                                        click: 'onAddOffDayClick'
                                    }
                                },
                                {
                                    xtype: 'button',
                                    border: 0,
                                    frame: false,
                                    id: 'offdaydel',
                                    style: 'background-color:transparent;',
                                    iconCls: 'removeone',
                                    tooltip: 'Διαγραφή τελευταίου ρεπό',
                                    listeners: {
                                        click: 'onDelOffDayClick'
                                    }
                                },*/
                                {
                                    xtype: 'hiddenfield',
                                    anchor: '100%',
                                    labelAlign: 'top',
                                    labelWidth: 180,
                                    name: 'driversPmtOffDays',
                                    validateOnChange: false,
                                    value: 1
                                },
                                {
                                    xtype: 'hiddenfield',
                                    anchor: '100%',
                                    labelAlign: 'top',
                                    labelWidth: 180,
                                    name: 'offdaysnum',
                                    validateOnChange: false,
                                    value: 1
                                },
                                {
                                    xtype: 'hiddenfield',
                                    anchor: '100%',
                                    labelWidth: 180,
                                    msgTarget: 'under',
                                    name: 'offdays',
                                    validateOnChange: false
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype: 'hiddenfield',
                    width: 150,
                    fieldLabel: 'Όνομα',
                    labelWidth: 180,
                    name: 'docId',
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
                var day = date.getDate().toString();
                if (day.length<2) day = "0"+day;
                var month = (date.getMonth() + 1).toString();
                if (month.length<2) month = "0"+month;
                var year = date.getFullYear().toString();
                return (year+"-"+month+"-"+day+"T00:00:00.000+0000");
            },
            dock: 'bottom',
            html: '<span style="color:#696969;font-size:11px;"><em>*Τα ανενεργά πεδία θα συμπληρωθούν αυτόματα<br>από το σύστημα κατά την υποβολή</em></span>',
            id: 'compdriverspmt_save_submit_toolbar',
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
                    hidden: true,
                    itemId: 'deletebutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf00d@FontAwesome',
                    text: 'Διαγραφή',
                    tooltip: 'Διαγραφή  Αποθηκευμένης Αίτησης',
                    listeners: {
                        click: {
                            fn: 'onDelete_COMPANY_DRIVERS_PMT',
                            scope: 'controller'
                        }
                    }
                },
                {
                    xtype: 'button',
                    itemId: 'savebutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf0c7@FontAwesome',
                    text: 'Αποθήκευση',
                    listeners: {
                        click: {
                            fn: 'onSave_COMPANY_DRIVERS_PMT',
                            scope: 'controller'
                        }
                    }
                },
                {
                    xtype: 'button',
                    itemId: 'submitbutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf1d8@FontAwesome',
                    text: 'Υποβολή',
                    listeners: {
                        click: {
                            fn: 'onSubmit_COMPANY_DRIVERS_PMT',
                            scope: 'controller'
                        }
                    }
                }
            ],
            listeners: {
                beforehide: 'onCompDriversPmt_save_submit_toolbarBeforeHide'
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

    onAddrPSelect21: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('compAddrPe');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeExpand21: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
        queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('compAddrP').getValue()+" ";
    },

    onAddrPeSelect21: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('compAddrD');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeDirtyChange21: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalPe/"+field.getValue()+" ";
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onAddrDExpand21: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalD");
        queryPlan.combo.getStore().getProxy().url = "/TKalD/search/findByEnotCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('compAddrPe').getValue()+" ";
    },

    onAddrDSelect21: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('compAddrK');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrDDirtyChange21: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalD/"+field.getValue()+" ";
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onAddrKExpand21: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalK");
        queryPlan.combo.getStore().getProxy().url = "/TKalK/search/findByDimosCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('compAddrD').getValue();
    },

    onAddrKDirtyChange21: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalK/"+field.getValue();
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load({callback: function(records, operation, success) {
                field.up('form').getForm().findField('compAddrP').setValue(records[0].get('pCode'));
                field.up('form').getForm().findField('compAddrPe').setValue(records[0].get('peCode'));
                field.up('form').getForm().findField('compAddrD').setValue(records[0].get('dCode'));
            }
            });
        }
    },

    /*onAddOffDayClick: function(button, e, eOpts) {
        var offday = Ext.create('widget.companydriverspmtformoffday', {});
        button.up('form').getForm().findField('offdaysnum').setValue(parseInt(button.up('form').getForm().findField('offdaysnum').getValue())+1);
        offday.items.getAt(0).items.getAt(0).setValue(button.up('form').getForm().findField('offdaysnum').getValue()+". ");
        var scrollpos = button.up('window').getScrollY();
        button.up().add(offday);
        button.up('window').scrollTo(0, scrollpos);
    },

    onDelOffDayClick: function(button, e, eOpts) {
        if(parseInt(button.up('form').getForm().findField('offdaysnum').getValue())!==1){
            button.up('form').getForm().findField('offdaysnum').setValue(parseInt(button.up('form').getForm().findField('offdaysnum').getValue())-1);
            var scrollpos = button.up('window').getScrollY();
            button.up().items.get(button.up().items.length-1).destroy();
            button.up('window').scrollTo(0, scrollpos);
        }

    },*/

    onMonthYearChange: function(combo, records, eOpts) {
        var status = combo.up('form').getForm().findField('subStatus').getValue();

        var month = combo.up('form').getForm().findField('pmtMonth').getValue();
        var year = combo.up('form').getForm().findField('pmtYear').getValue();
        var picker = Ext.getCmp('mutliDatePicker');
        var label = Ext.getCmp('multiDatePickerLabel');
        if (month!=null && year!=null) {
            picker.minDate = new Date(year+'-'+month+'-01');
            picker.maxDate = new Date(year+'-'+(month+1)+'-01');
            picker.setValue(picker.minDate);
            if (status != 2) {
                picker.selectedDates = {};
            }
            picker.higlighDates();
            picker.setDisabled(false);
            label.setHidden(true);
        } else {
            picker.setDisabled(true);
            label.hidden.setHidden(false);
        }
    },

    /*onDatePickerClick: function(button, e, eOpts) {
        var status = button.up('form').getForm().findField('subStatus').getValue();
        var url = button.up('form').getForm().findField('driversPmtOffDays').getValue();
        if (status == 1) {
            button.status = status;
            button.url = url;
        }
    },*/

    onWindowBeforeDestroy: function(component, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.company.DriversPmtPanel');
        center.add(viewsub);
    },

    onCompDriversPmt_save_submit_toolbarBeforeHide: function(component, eOpts) {
        component.getComponent('deletebutton').destroy();
        component.getComponent('savebutton').destroy();
        component.getComponent('submitbutton').destroy();
    }

});