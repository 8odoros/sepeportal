/*
 * File: app/view/company/ProjectsBooks/ProjectsForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.ProjectsBooks.ProjectsForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companyprojectsbooksprojectsform',

    requires: [
        'MyApp.view.company.ProjectsBooks.ProjectsFormViewModel',
        'MyApp.view.company.ProjectsBooks.ProjectsFormViewController',
        'MyApp.view.shared.PrintFormTool',
        'MyApp.view.shared.CloseFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.field.Date',
        'Ext.form.field.ComboBox',
        'Ext.form.FieldSet',
        'Ext.form.field.TextArea',
        'Ext.toolbar.Toolbar',
        'Ext.button.Button',
        'Ext.panel.Tool'
    ],

    controller: 'companyprojectsbooksprojectsform',
    viewModel: {
        type: 'companyprojectsbooksprojectsform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    overflowY: 'scroll',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Φόρμα Οικοδομικού - Τεχνικού Έργου',
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
            id: 'projectsbookprojects',
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
                    xtype: 'datefield',
                    disabled: true,
                    disabledCls: 'x-form-readonly',
                    width: 150,
                    fieldLabel: 'Ημερομηνία Δημιουργίας',
                    labelWidth: 180,
                    name: 'creationDateView',
                    submitValue: false,
                    validateOnChange: false,
                    validateOnBlur: false,
                    editable: false,
                    format: 'd-m-Y'
                },
                {
                    xtype: 'hiddenfield',
                    flex: 1,
                    height: 150,
                    fieldLabel: 'Ημ. Πρωτοκόλλου',
                    labelWidth: 180,
                    name: 'creationDate',
                    validateOnChange: false
                },
                {
                    xtype: 'combobox',
                    flex: 1,
                    disabled: true,
                    disabledCls: 'x-form-readonly',
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
                    xtype: 'fieldset',
                    flex: 1,
                    focusOnToFront: false,
                    padding: 10,
                    toFrontOnShow: false,
                    title: 'ΣΤΟΙΧΕΙΑ ΕΡΓΟΥ',
                    items: [
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Αριθμός Άδειας',
                            labelWidth: 180,
                            name: 'licenceNumber',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'fieldset',
                            focusOnToFront: false,
                            toFrontOnShow: false,
                            title: 'Ακριβής Διεύθυνση του Οικοδομικού - Τεχνικού Έργου',
                            items: [
                                {
                                    xtype: 'textareafield',
                                    anchor: '100%',
                                    fieldLabel: 'Οδός / Αριθμός',
                                    labelWidth: 180,
                                    name: 'projectAddr',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 50
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Περιφέρεια',
                                    labelWidth: 180,
                                    name: 'projectAddrP',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    editable: false,
                                    autoLoadOnValue: true,
                                    displayField: 'descr',
                                    store: 'address.ADDR_P',
                                    transformInPlace: false,
                                    valueField: 'abbr',
                                    listeners: {
                                        select: 'onAddrPSelect2'
                                    }
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    session: false,
                                    disabled: true,
                                    fieldLabel: 'Νομός',
                                    labelWidth: 180,
                                    name: 'projectAddrPe',
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
                                        beforequery: 'onAddrPeExpand2',
                                        select: 'onAddrPeSelect2',
                                        dirtychange: {
                                            fn: 'onAddrPeDirtyChange2',
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
                                    name: 'projectAddrD',
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
                                        beforequery: 'onAddrDExpand2',
                                        select: 'onAddrDSelect2',
                                        dirtychange: {
                                            fn: 'onAddrDDirtyChange2',
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
                                    name: 'projectAddrK',
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
                                        beforequery: 'onAddrKExpand2',
                                        dirtychange: {
                                            fn: 'onAddrKDirtyChange2',
                                            single: true
                                        }
                                    }
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Τ.Κ',
                                    labelWidth: 180,
                                    name: 'projectAddrTk',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                              //      allowBlank: false,
                                //    allowOnlyWhitespace: false,
                                    maxLength: 5,
                                    minLength: 5,
                                    vtype: 'Number'
                                }
                            ]
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            fieldLabel: 'Περιγραφή Ειδικότητας Οικοδομικών Εργασιών',
                            labelAlign: 'top',
                            labelWidth: 180,
                            name: 'projectDescription',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 512
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            fieldLabel: 'Υποκατάστημα ΙΚΑ Απογραφής',
                            labelWidth: 180,
                            name: 'ikaDept',
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            autoLoadOnValue: true,
                            displayField: 'description',
                            forceSelection: true,
                            store: 'company.IKA_DEPT',
                            valueField: 'abbr'
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Αριθμός Γεν. Μητρώου',
                            labelWidth: 180,
                            name: 'genRecordNum',
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
                    flex: 1,
                    focusOnToFront: false,
                    padding: 10,
                    toFrontOnShow: false,
                    title: 'ΣΤΟΙΧΕΙΑ ΙΔΙΟΚΤΗΤΗ',
                    items: [
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Όνομα',
                            labelWidth: 180,
                            name: 'empName',
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
                            name: 'empSurname',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Τόπος Γέννησης',
                            labelWidth: 180,
                            name: 'empBirthPlace',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            fieldLabel: 'Ημ. Γέννησης',
                            labelWidth: 180,
                            name: 'empDateOfBirthView',
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
                            fieldLabel: 'Ημ. Γέννησης',
                            labelWidth: 180,
                            name: 'empDateOfBirth',
                            validateOnChange: false
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            fieldLabel: 'Διεύθυνση Κατοικίας',
                            labelWidth: 180,
                            name: 'empHomeAddr',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Αριθμός Δελτίου Ταυτότητας',
                            labelWidth: 180,
                            name: 'empCardNum',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Εκδίδουσα Άρχη',
                            labelWidth: 180,
                            name: 'empCardIssuingAuth',
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
                            name: 'empAfm',
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
                    flex: 1,
                    focusOnToFront: false,
                    padding: 10,
                    toFrontOnShow: false,
                    title: 'ΣΤΟΙΧΕΙΑ ΕΡΓΟΛΑΒΟΥ',
                    items: [
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Όνομα',
                            labelWidth: 180,
                            name: 'contrName',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Επώνυμο',
                            labelWidth: 180,
                            name: 'contrSurname',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Τόπος Γέννησης',
                            labelWidth: 180,
                            name: 'contrBirthPlace',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            fieldLabel: 'Ημ. Γέννησης',
                            labelWidth: 180,
                            name: 'contrDateOfBirthView',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 50,
                            format: 'd-m-Y'
                        },
                        {
                            xtype: 'hiddenfield',
                            anchor: '100%',
                            fieldLabel: 'Ημ. Γέννησης',
                            labelWidth: 180,
                            name: 'contrDateOfBirth',
                            validateOnChange: false
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            fieldLabel: 'Διεύθυνση Κατοικίας',
                            labelWidth: 180,
                            name: 'contrHomeAddr',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Αριθμός Δελτίου Ταυτότητας',
                            labelWidth: 180,
                            name: 'contrCardNum',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Εκδίδουσα Άρχη',
                            labelWidth: 180,
                            name: 'contrCardIssuingAuth',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Α.Φ.Μ.',
                            labelWidth: 180,
                            name: 'contrAfm',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 9,
                            minLength: 9,
                            maskRe:/[0-9.]/,
                        }
                    ]
                },
                {
                    xtype: 'fieldset',
                    flex: 1,
                    focusOnToFront: false,
                    padding: 10,
                    toFrontOnShow: false,
                    title: 'ΣΤΟΙΧΕΙΑ ΥΠΕΡΓΟΛΑΒΟΥ',
                    items: [
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Όνομα',
                            labelWidth: 180,
                            name: 'subContrName',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Επώνυμο',
                            labelWidth: 180,
                            name: 'subContrSurname',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Τόπος Γέννησης',
                            labelWidth: 180,
                            name: 'subContrBirthPlace',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            fieldLabel: 'Ημ. Γέννησης',
                            labelWidth: 180,
                            name: 'subContrDateOfBirthView',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 50,
                            format: 'd-m-Y'
                        },
                        {
                            xtype: 'hiddenfield',
                            anchor: '100%',
                            fieldLabel: 'Ημ. Γέννησης',
                            labelWidth: 180,
                            name: 'subContrDateOfBirth',
                            validateOnChange: false
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            fieldLabel: 'Διεύθυνση Κατοικίας',
                            labelWidth: 180,
                            name: 'subContrHomeAddr',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Αριθμός Δελτίου Ταυτότητας',
                            labelWidth: 180,
                            name: 'subContrCardNum',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Εκδίδουσα Άρχη',
                            labelWidth: 180,
                            name: 'subContrCardIssuingAuth',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Α.Φ.Μ.',
                            labelWidth: 180,
                            name: 'subContrAfm',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 9,
                            minLength: 9,
                            maskRe:/[0-9.]/,
                        }
                    ]
                },
                {
                    xtype: 'fieldset',
                    flex: 1,
                    focusOnToFront: false,
                    padding: 10,
                    toFrontOnShow: false,
                    title: 'ΣΤΟΙΧΕΙΑ ΣΤΕΓΑΣΜΕΝΩΝ ΕΠΙΧΕΙΡΗΣΕΩΝ',
                    items: [
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Επωνυμία',
                            labelWidth: 180,
                            name: 'housedCompanyTile',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 512
                        },
                        {
                            xtype: 'fieldset',
                            focusOnToFront: false,
                            toFrontOnShow: false,
                            title: 'Διεύθυνση',
                            items: [
                                {
                                    xtype: 'textareafield',
                                    anchor: '100%',
                                    fieldLabel: 'Οδός / Αριθμός',
                                    labelWidth: 180,
                                    name: 'housedCompanyAddr',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 50
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Περιφέρεια',
                                    labelWidth: 180,
                                    name: 'housedCompanyAddrP',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    editable: false,
                                    autoLoadOnValue: true,
                                    displayField: 'descr',
                                    store: 'address.ADDR_P',
                                    valueField: 'abbr',
                                    listeners: {
                                        select: 'onAddrPSelect11'
                                    }
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    disabled: true,
                                    fieldLabel: 'Νομός',
                                    labelWidth: 180,
                                    name: 'housedCompanyAddrPe',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    editable: false,
                                    anyMatch: true,
                                    displayField: 'descr',
                                    queryParam: 'perifCode',
                                    store: 'address1.ADDR_Pe1',
                                    valueField: 'abbr',
                                    listeners: {
                                        beforequery: 'onAddrPeExpand11',
                                        select: 'onAddrPeSelect11',
                                        dirtychange: {
                                            fn: 'onAddrPeDirtyChange11',
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
                                    name: 'housedCompanyAddrD',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    editable: false,
                                    displayField: 'dimosDescr',
                                    queryParam: 'enotCode',
                                    store: 'address1.ADDR_D1',
                                    valueField: 'abbr',
                                    listeners: {
                                        beforequery: 'onAddrDExpand11',
                                        select: 'onAddrDSelect11',
                                        dirtychange: {
                                            fn: 'onAddrDDirtyChange11',
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
                                    name: 'housedCompanyAddrK',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    editable: false,
                                    displayField: 'koinDescr',
                                    queryParam: 'dimosCode',
                                    store: 'address1.ADDR_K1',
                                    valueField: 'abbr',
                                    listeners: {
                                        beforequery: 'onAddrKExpand11',
                                        dirtychange: {
                                            fn: 'onAddrKDirtyChange11',
                                            single: true
                                        }
                                    }
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Τ.Κ',
                                    labelWidth: 180,
                                    name: 'housedCompanyAddrTk',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 5,
                                    minLength: 5,
                                    vtype: 'Number'
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype: 'hiddenfield',
                    height: 150,
                    labelWidth: 180,
                    name: 'url',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    height: 150,
                    labelWidth: 180,
                    name: 'companyid',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    height: 150,
                    labelWidth: 180,
                    name: 'empCardType',
                    validateOnChange: false,
                    value: 5
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
            html: '<span style="color:#696969;font-size:11px;"><em>*Τα ανενεργά πεδία θα συμπληρωθούν αυτόματα<br>από το σύστημα κατά την καταχώρηση</em></span>',
            id: 'compproject_save_submit_toolbar',
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
                            fn: 'onSubmit_COMPANY_PROJECT',
                            scope: 'controller'
                        }
                    }
                }
            ]
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

    onAddrPSelect2: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('projectAddrPe');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeExpand2: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
        queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('projectAddrP').getValue()+" ";
    },

    onAddrPeSelect2: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('projectAddrD');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeDirtyChange2: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalPe/"+field.getValue()+" ";
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onAddrDExpand2: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalD");
        queryPlan.combo.getStore().getProxy().url = "/TKalD/search/findByEnotCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('projectAddrPe').getValue()+" ";
    },

    onAddrDSelect2: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('projectAddrK');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrDDirtyChange2: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalD/"+field.getValue()+" ";
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onAddrKExpand2: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalK");
        queryPlan.combo.getStore().getProxy().url = "/TKalK/search/findByDimosCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('projectAddrD').getValue();
    },

    onAddrKDirtyChange2: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalK/"+field.getValue();
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onAddrPSelect11: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('housedCompanyAddrPe');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeExpand11: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
        queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('housedCompanyAddrP').getValue()+" ";
    },

    onAddrPeSelect11: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('housedCompanyAddrD');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeDirtyChange11: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalPe/"+field.getValue()+" ";
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onAddrDExpand11: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalD");
        queryPlan.combo.getStore().getProxy().url = "/TKalD/search/findByEnotCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('housedCompanyAddrPe').getValue()+" ";
    },

    onAddrDSelect11: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('housedCompanyAddrK');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrDDirtyChange11: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalD/"+field.getValue()+" ";
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onAddrKExpand11: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalK");
        queryPlan.combo.getStore().getProxy().url = "/TKalK/search/findByDimosCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('housedCompanyAddrD').getValue();
    },

    onAddrKDirtyChange11: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalK/"+field.getValue();
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onWindowBeforeDestroy: function(component, eOpts) {
        if(component.down('form').getForm().findField("a_new_form").getValue()!=="groupprint" || component.down('form').getForm().findField("a_new_form").getValue()!=="true"){
            var dailygrid = Ext.getCmp('companyProjectsBooks_Daily');
            dailygrid.store.clearData();
            dailygrid.getView().refresh();
            var personelgrid = Ext.getCmp('companyProjectsBooks_Personel');
            personelgrid.store.clearData();
            personelgrid.getView().refresh();
            var view=Ext.getCmp('companymainView');
            var center = view.getComponent('contentPanel');
            center.removeAll();
            var viewsub = Ext.create('MyApp.view.company.ProjectsBooksPanel');
            center.add(viewsub);
        }
    }

});