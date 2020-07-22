/*
 * File: app/view/company/AccidentForm/AccidentForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.AccidentForm.AccidentForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companyaccidentformaccidentform',

    requires: [
        'MyApp.view.company.AccidentForm.AccidentFormViewModel',
        'MyApp.view.company.AccidentForm.AccidentFormViewController',
        'MyApp.view.shared.PrintFormTool',
        'MyApp.view.shared.CloseFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.FieldSet',
        'Ext.form.field.Date',
        'Ext.form.field.Number',
        'Ext.form.field.ComboBox',
        'Ext.form.RadioGroup',
        'Ext.form.field.Radio',
        'Ext.form.field.TextArea',
        'Ext.button.Button',
        'Ext.form.field.File',
        'Ext.form.Label',
        'Ext.toolbar.Toolbar',
        'Ext.panel.Tool'
    ],

    controller: 'companyaccidentformaccidentform',
    viewModel: {
        type: 'companyaccidentformaccidentform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    overflowY: 'scroll',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Φόρμα Αναγγελίας Εργατικού Ατυχήματος',
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
            id: 'companyaccident',
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
                    title: 'ΣΤΟΙΧΕΙΑ ΑΤΥΧΗΜΑΤΟΣ',
                    items: [
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            cls: 'x-item-disabled',
                            disabled: true,
                            fieldLabel: 'Αρμόδια Διεύθυνση (ΚΕΠΕΚ)',
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
                            xtype: 'radiogroup',
                            anchor: '70%',
                            disabled: true,
                            fieldLabel: 'Εκπρόθεση Αναγγελία',
                            labelWidth: 180,
                            validateOnChange: false,
                            items: [
                                {
                                    xtype: 'radiofield',
                                    name: 'outdated',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Ναι',
                                    inputValue: '1'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'outdated',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Οχι',
                                    inputValue: '0'
                                }
                            ]
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
                                    name: 'compFullName',
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
                                    name: 'compTaxNumber',
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
                                    name: 'compAme',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 100,
                                    vtype: 'Number'
                                },
                                {
                                    xtype: 'fieldset',
                                    focusOnToFront: false,
                                    toFrontOnShow: false,
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
                                            maxLength: 5,
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
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 10,
                                    vtype: 'telNumber'
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Ιατρός Εργασίας',
                                    labelWidth: 180,
                                    name: 'compNameIe',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 128
                                    //allowBlank: false,
                                    //allowOnlyWhitespace: false
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Τεχνικός Ασφάλειας',
                                    labelWidth: 180,
                                    name: 'compNameTe',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    emptyText: '',
                                    maxLength: 128
                                }
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            focusOnToFront: false,
                            padding: 10,
                            toFrontOnShow: false,
                            title: 'ΣΤΟΙΧΕΙΑ ΠΑΘΟΝΤΟΣ',
                            toggleOnTitleClick: false,
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
                                    maxLength: 128
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
                                    maxLength: 128
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Όνομα Πατρός',
                                    labelWidth: 180,
                                    name: 'empFathersName',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 500
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Α.Δ.Τ. / άλλο νομιμοποιητικό έγγραφο',
                                    labelWidth: 180,
                                    name: 'empCardNumber',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    //allowBlank: false,
                                    //allowOnlyWhitespace: false,
                                    maxLength: 128
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'ΑΜΚΑ',
                                    labelWidth: 180,
                                    name: 'empAmkaNumber',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 15
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Α.Φ.Μ.',
                                    labelWidth: 180,
                                    name: 'empTaxNumber',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 9,
                                    minLength: 9,
                                    maskRe:/[0-9.]/
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Φορέας Ασφάλισης',
                                    labelWidth: 180,
                                    name: 'empInsuranceBureauCode',
                                    validateOnChange: false,
                                    readOnly: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    editable: false,
                                    autoLoadOnValue: true,
                                    displayField: 'description',
                                    forceSelection: true,
                                    store: 'company.INSURANCE_BUREAU_CODE',
                                    valueField: 'abbr'
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Ειδικότητα',
                                    labelWidth: 180,
                                    name: 'empSpecialty',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 500,
                                    displayField: 'spRtspDescription',
                                    forceSelection: true,
                                    pageSize: 25,
                                    minChars: 0,
                                    queryParam: 'descr',
                                    store: 'company.SPECIALTY',
                                    valueField: 'abbr',
                                    listeners: {
                                        beforequery: 'onSpecialityExpand'
                                    }
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Φύλο',
                                    labelWidth: 180,
                                    name: 'empSexDesc',
                                    validateOnChange: false,
                                    readOnly: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    editable: false,
                                    autoLoadOnValue: true,
                                    displayField: 'name',
                                    forceSelection: true,
                                    store: 'shared.SEX',
                                    valueField: 'abbr'
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Οικογενειακή Κατάσταση',
                                    labelWidth: 180,
                                    name: 'empMaritalStatusCd',
                                    validateOnChange: false,
                                    readOnly: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    editable: false,
                                    autoLoadOnValue: true,
                                    displayField: 'description',
                                    forceSelection: true,
                                    store: 'company.MARITAL_STATUS',
                                    valueField: 'mid'
                                },
                                {
                                    xtype: 'datefield',
                                    anchor: '100%',
                                    fieldLabel: 'Ημερομηνία Γεννήσεως',
                                    labelWidth: 180,
                                    name: 'empBirthdateView',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    hidden: true,
                                    //allowBlank: false,
                                    //allowOnlyWhitespace: false,
                                    maxLength: 50,
                                    format: 'd-m-Y',
                                    /*listeners: {
                                     select: 'setEmpAge'
                                     }*/
                                },
                                {
                                    xtype: 'hiddenfield',
                                    anchor: '100%',
                                    labelWidth: 180,
                                    name: 'empBirthdate',
                                    validateOnChange: false
                                },
                                {
                                    xtype: 'numberfield',
                                    anchor: '100%',
                                    fieldLabel: 'Ηλικία',
                                    labelWidth: 180,
                                    name: 'empAge',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    hideTrigger: true,
                                    maxLength: 2,
                                    minLength: 2,
                                    repeatTriggerClick: false,
                                    keyNavEnabled: false,
                                    mouseWheelEnabled: false,
                                    spinDownEnabled: false,
                                    spinUpEnabled: false,
                                    allowDecimals: false,
                                    allowExponential: false,
                                    minValue: 18,
                                    submitLocaleSeparator: false
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Υπηκοότητα',
                                    labelWidth: 180,
                                    name: 'empCitizenshipCd',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    emptyText:'Πληκτρολογήστε μία χώρα',
                                    minChars: 1,
                                    triggerAction: 'query',
                                    typeAhead: true,
                                    queryParam: 'description',
                                    displayField: 'description',
                                    store: 'company.NATIONALITY',
                                    valueField: 'abbr',
                                    forceSelection: true
                                },
                                {
                                    xtype: 'datefield',
                                    anchor: '100%',
                                    fieldLabel: 'Ημερομηνία Πρόσληψης',
                                    labelWidth: 180,
                                    name: 'empRecruitmentDateView',
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
                                    name: 'empRecruitmentDate',
                                    validateOnChange: false
                                },
                                {
                                    xtype: 'fieldset',
                                    focusOnToFront: false,
                                    toFrontOnShow: false,
                                    title: 'Διεύθυνση Κατοικίας',
                                    items: [
                                        {
                                            xtype: 'textareafield',
                                            anchor: '100%',
                                            fieldLabel: 'Οδός / Αριθμός',
                                            labelWidth: 180,
                                            name: 'empAddr',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            allowBlank: false,
                                            allowOnlyWhitespace: false,
                                            maxLength: 250
                                        },
                                        {
                                            xtype: 'combobox',
                                            anchor: '100%',
                                            fieldLabel: 'Περιφέρεια',
                                            labelWidth: 180,
                                            name: 'empAddrP',
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
                                                select: 'onAddrPSelect31'
                                            }
                                        },
                                        {
                                            xtype: 'combobox',
                                            anchor: '100%',
                                            session: false,
                                            disabled: true,
                                            fieldLabel: 'Νομός',
                                            labelWidth: 180,
                                            name: 'empAddrPe',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            allowBlank: false,
                                            allowOnlyWhitespace: false,
                                            editable: false,
                                            displayField: 'descr',
                                            queryParam: 'perifCode',
                                            store: 'address2.ADDR_Pe2',
                                            valueField: 'abbr',
                                            listeners: {
                                                beforequery: 'onAddrPeExpand31',
                                                select: 'onAddrPeSelect31',
                                                dirtychange: {
                                                    fn: 'onAddrPeDirtyChange31',
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
                                            name: 'empAddrD',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            allowBlank: false,
                                            allowOnlyWhitespace: false,
                                            editable: false,
                                            displayField: 'dimosDescr',
                                            queryParam: 'enotCode',
                                            store: 'address2.ADDR_D2',
                                            valueField: 'abbr',
                                            listeners: {
                                                beforequery: 'onAddrDExpand31',
                                                select: 'onAddrDSelect31',
                                                dirtychange: {
                                                    fn: 'onAddrDDirtyChange31',
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
                                            name: 'empAddrK',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            allowBlank: false,
                                            allowOnlyWhitespace: false,
                                            editable: false,
                                            displayField: 'koinDescr',
                                            queryParam: 'dimosCode',
                                            store: 'address2.ADDR_K2',
                                            valueField: 'abbr',
                                            listeners: {
                                                beforequery: 'onAddrKExpand31',
                                                dirtychange: {
                                                    fn: 'onAddrKDirtyChange31',
                                                    single: true
                                                }
                                            }
                                        },
                                        {
                                            xtype: 'textfield',
                                            anchor: '100%',
                                            fieldLabel: 'Τ.Κ',
                                            labelWidth: 180,
                                            name: 'empAddrTk',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            //allowBlank: false,
                                            //allowOnlyWhitespace: false,
                                            maxLength: 5,
                                            minLength: 5,
                                            vtype: 'Number'
                                        }
                                    ]
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Σταθερό Τηλέφωνο',
                                    labelWidth: 180,
                                    name: 'empPhone',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 10,
                                    vtype: 'telNumber',
                                    validator: function(value){
                                        if (!value && !this.up('form').getForm().findField('empMobile').getValue()) {
                                            return 'Ένα από τα πεδία "Σταθερό Τηλέφωνο" ή "Κινητό Τηλέφωνο" πρέπει να συμπληρωθεί.';
                                        }
                                        return true;
                                    }
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Κινητό Τηλέφωνο',
                                    labelWidth: 180,
                                    name: 'empMobile',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 10,
                                    vtype: 'telNumber',
                                    validator: function(value){
                                        if (!value && !this.up('form').getForm().findField('empPhone').getValue()) {
                                            return 'Ένα από τα πεδία "Σταθερό Τηλέφωνο" ή "Κινητό Τηλέφωνο" πρέπει να συμπληρωθεί.';
                                        }
                                        return true;
                                    }
                                }
                            ]
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            fieldLabel: 'Θέση Εργασίας',
                            labelWidth: 180,
                            name: 'empJobPositionCode',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            editable: false,
                            autoLoadOnValue: true,
                            displayField: 'description',
                            forceSelection: true,
                            store: 'company.Accidents.AFFECTED_POSITION',
                            valueField: 'abbr'
                        },
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            fieldLabel: 'Ημερομηνία Ατυχήματος',
                            labelWidth: 180,
                            name: 'accidentDateView',
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
                            fieldLabel: 'Ημερομηνία Ατυχήματος',
                            labelWidth: 180,
                            name: 'accidentDate',
                            validateOnChange: false
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            fieldLabel: 'Περιγραφή Ώρας Ατυχήματος',
                            labelWidth: 180,
                            name: 'empTimeCode',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            editable: false,
                            autoLoadOnValue: true,
                            displayField: 'description',
                            forceSelection: true,
                            store: 'company.Accidents.AFFECTED_TIME',
                            valueField: 'abbr'
                        },
                        {
                            xtype: 'timefield',
                            anchor: '100%',
                            fieldLabel: 'Ώρα Ατυχήματος',
                            labelWidth: 180,
                            name: 'accidentTime',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            forceSelection: true,
                            format: 'H:i',
                            increment: 30
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            fieldLabel: 'Σοβαρότητα Ατυχήματος',
                            labelWidth: 180,
                            name: 'accidentAffectedSeverity',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            editable: false,
                            autoLoadOnValue: true,
                            displayField: 'description',
                            forceSelection: true,
                            store: 'company.Accidents.AFFECTED_SEVERITY',
                            valueField: 'abbr'
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            fieldLabel: 'Είδος Ατυχήματος',
                            labelWidth: 180,
                            name: 'accidentAffectedType',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            editable: false,
                            autoLoadOnValue: true,
                            displayField: 'description',
                            forceSelection: true,
                            store: 'company.Accidents.AFFECTED_TYPE',
                            valueField: 'abbr'
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Είδος Τραύματος',
                            labelWidth: 180,
                            name: 'accidentWoundType',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 1000
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Μέρος Σώματος που Τραυματίστηκε',
                            labelWidth: 180,
                            name: 'accidentBodyPart',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 1000
                        },
                        {
                            xtype: 'fieldset',
                            focusOnToFront: false,
                            padding: 10,
                            toFrontOnShow: false,
                            title: 'Στοιχεία Παραρτήματος',
                            items: [
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Παράρτημα που αφορά η αίτηση',
                                    labelWidth: 220,
                                    name: 'compEbrBranchId',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    displayField: 'rgEbrAddressStreetCombo',
                                    store: 'company.COMP_BRANCES',
                                    valueField: 'rgEbrBranchId',
                                    minChars: 0,
                                    forceSelection: true,
                                    queryParam: 'descr',
                                    listeners: {
                                        beforequery: 'onBranchExpand'
                                    }
                                },
                                {
                                    xtype: 'hiddenfield',
                                    anchor: '100%',
                                    labelWidth: 180,
                                    name: 'compEbrBranch0Id',
                                    validateOnChange: false,
                                    value: 0
                                }
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            focusOnToFront: false,
                            toFrontOnShow: false,
                            title: 'Ακριβής Τόπος - Διεύθυνση Ατυχήματος',
                            items: [
                                {
                                    xtype: 'textareafield',
                                    anchor: '100%',
                                    fieldLabel: 'Περιγραφή Τοποθεσίας Ατυχήματος',
                                    labelAlign: 'top',
                                    labelWidth: 180,
                                    name: 'accidentPlace',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 500,
                                    validateBlank: true
                                },
                                {
                                    xtype: 'textareafield',
                                    anchor: '100%',
                                    fieldLabel: 'Οδός / Αριθμός',
                                    labelWidth: 180,
                                    name: 'accidentAddr',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 250
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Περιφέρεια',
                                    labelWidth: 180,
                                    name: 'accidentAddrP',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
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
                                    name: 'accidentAddrPe',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
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
                                    name: 'accidentAddrD',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
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
                                    name: 'accidentAddrK',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
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
                                    name: 'accidentAddrTk',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    //allowBlank: false,
                                    //allowOnlyWhitespace: false,
                                    maxLength: 5,
                                    minLength: 5,
                                    vtype: 'Number'
                                }
                            ]
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            fieldLabel: 'Εργασία την Στιγμή του Ατυχήματος',
                            labelAlign: 'top',
                            labelWidth: 180,
                            name: 'accidentJobsComment',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 2000,
                            validateBlank: true
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            fieldLabel: 'Περιγραφή και Αίτια Ατυχήματος',
                            labelAlign: 'top',
                            labelWidth: 180,
                            //height: 295,
                            name: 'accidentDescription',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 2000,
                            validateBlank: true
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            fieldLabel: 'Στοιχεία Επικοινωνίας με αρμόδιο υπάλληλο της επιχείρησης για το εργατικό ατύχημα',
                            labelAlign: 'top',
                            labelWidth: 180,
                            name: 'compUserContactInfo',
                            validateOnChange: false,
                            validateOnBlur: false,
                            //allowBlank: false,
                            //allowOnlyWhitespace: false,
                            maxLength: 2000,
                            //validateBlank: true
                        },
                        {
                            xtype: 'radiogroup',
                            anchor: '70%',
                            fieldLabel: 'Εκπαίδευση για Θέματα Υγείας',
                            labelAlign: 'top',
                            labelWidth: 180,
                            validateOnChange: false,
                            allowBlank: false,
                            items: [
                                {
                                    xtype: 'radiofield',
                                    name: 'trainingFlag',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Ναι',
                                    inputValue: '1'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'trainingFlag',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Οχι',
                                    inputValue: '2'
                                }
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            id: 'witnesses',
                            title: 'Μάρτυρες',
                            items: [
                                {
                                    xtype: 'button',
                                    border: 0,
                                    frame: false,
                                    id: 'witnessadd',
                                    style: 'background-color:transparent;',
                                    iconCls: 'addone',
                                    tooltip: 'Προσθήκη μάρτυρα',
                                    listeners: {
                                        click: 'onAddWitnessClick'
                                    }
                                },
                                {
                                    xtype: 'button',
                                    border: 0,
                                    frame: false,
                                    id: 'witnessdel',
                                    style: 'background-color:transparent;',
                                    iconCls: 'removeone',
                                    tooltip: 'Διαγραφή τελευταίου μάρτυρα',
                                    listeners: {
                                        click: 'onDelWitnessClick'
                                    }
                                },
                                {
                                    xtype: 'hiddenfield',
                                    anchor: '100%',
                                    fieldLabel: 'Μάρτυρες',
                                    labelAlign: 'top',
                                    labelWidth: 180,
                                    name: 'witnessesnum',
                                    validateOnChange: false,
                                    value: 0
                                },
                                {
                                    xtype: 'hiddenfield',
                                    anchor: '100%',
                                    labelWidth: 180,
                                    msgTarget: 'under',
                                    name: 'witnessesObj',
                                    validateOnChange: false
                                }
                            ]
                        },
                        {
                            xtype: 'hiddenfield',
                            anchor: '100%',
                            fieldLabel: 'Επισυναπτόμενο Αρχείο',
                            labelWidth: 180,
                            msgTarget: 'under',
                            name: 'attachedDocId',
                            validateOnChange: false,
                            value: '-1'
                        },
                        {
                            xtype: 'form',
                            id: 'accidentfile',
                            standardSubmit: false,
                            items: [
                                {
                                    xtype: 'filefield',
                                    anchor: '100%',
                                    frame: false,
                                    fieldLabel: 'Επισυναπτόμενο Αρχείο',
                                    labelWidth: 180,
                                    msgTarget: 'under',
                                    name: 'file',
                                    submitValue: true,
                                    validateOnChange: false,
                                    inputId: 'file',
                                    validateOnBlur: false,
                                    buttonText: 'Επιλογή...'
                                }
                            ]
                        },
                        {
                            xtype: 'container',
                            hidden: true,
                            itemId: 'Exist_File',
                            margin: '0 0 0 185',
                            layout: {
                                type: 'hbox',
                                align: 'stretch'
                            },
                            items: [
                                {
                                    xtype: 'label',
                                    baseCls: 'x-form-item-label',
                                    height: 24,
                                    padding: '5 0 0 0',
                                    text: 'Αποθηκευμένο αρχείο: '
                                },
                                {
                                    xtype: 'button',
                                    focusCls: ' ',
                                    border: 0,
                                    frame: false,
                                    height: 24,
                                    style: 'background-color:transparent;',
                                    iconCls: 'deleteme',
                                    tooltip: 'Διαγραφή Αρχείου',
                                    listeners: {
                                        click: 'onDeleteClick'
                                    }
                                },
                                {
                                    xtype: 'button',
                                    focusCls: ' ',
                                    border: 0,
                                    frame: false,
                                    height: 24,
                                    style: 'background-color:transparent;',
                                    iconCls: 'downloadme',
                                    tooltip: 'Προβολή Αρχείου',
                                    listeners: {
                                        click: 'onViewDocClick'
                                    }
                                },
                                {
                                    xtype: 'hiddenfield',
                                    flex: 1,
                                    labelWidth: 180,
                                    msgTarget: 'under',
                                    name: 'deletedfile',
                                    validateOnChange: false,
                                    value: false
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
                if (date != "")
                {
                    var pD = date.split("-");
                    return (pD[2]+"-"+pD[1]+"-"+pD[0]+"T00:00:00.000+0000");
                }
                else
                {
                    return null;
                }
            },
            dock: 'bottom',
            html: '<span style="color:#696969;font-size:11px;"><em>*Τα ανενεργά πεδία θα συμπληρωθούν αυτόματα<br>από το σύστημα κατά την υποβολή</em></span>',
            id: 'compaccident_save_submit_toolbar',
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
                    itemId: 'deleteaccidentbutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf00d@FontAwesome',
                    text: 'Διαγραφή',
                    tooltip: 'Διαγραφή  Αποθηκευμένης Αίτησης',
                    listeners: {
                        click: {
                            fn: 'onDelete_COMPANY_ACCIDENT',
                            scope: 'controller'
                        }
                    }
                },
                {
                    xtype: 'button',
                    itemId: 'saveaccidentbutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf0c7@FontAwesome',
                    text: 'Αποθήκευση',
                    listeners: {
                        click: {
                            fn: 'onSave_COMPANY_ACCIDENT',
                            scope: 'controller'
                        }
                    }
                },
                {
                    xtype: 'button',
                    itemId: 'submitaccidentbutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf1d8@FontAwesome',
                    text: 'Υποβολή',
                    listeners: {
                        click: {
                            fn: 'onSubmit_COMPANY_ACCIDENT',
                            scope: 'controller'
                        }
                    }
                }
            ],
            listeners: {
                beforehide: 'onCompAccident_save_submit_toolbarBeforeHide'
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
        //if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
        field.getStore().getProxy().url = "/TKalPe/"+field.getValue()+" ";
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        //}
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
        //if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
        field.getStore().getProxy().url = "/TKalD/"+field.getValue()+" ";
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        //}
    },

    onAddrKExpand21: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalK");
        queryPlan.combo.getStore().getProxy().url = "/TKalK/search/findByDimosCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('compAddrD').getValue();
    },

    onAddrKDirtyChange21: function(field, isDirty, eOpts) {
        //if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
        field.getStore().getProxy().url = "/TKalK/"+field.getValue();
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load({callback: function(records, operation, success) {
            field.up('form').getForm().findField('compAddrP').setValue(records[0].get('pCode'));
            field.up('form').getForm().findField('compAddrPe').setValue(records[0].get('peCode'));
            field.up('form').getForm().findField('compAddrD').setValue(records[0].get('dCode'));
        }
        });
        //}
    },

    onAddrPSelect31: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('empAddrPe');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeExpand31: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
        queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('empAddrP').getValue()+" ";
    },

    onAddrPeSelect31: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('empAddrD');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeDirtyChange31: function(field, isDirty, eOpts) {
        //if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
        field.getStore().getProxy().url = "/TKalPe/"+field.getValue()+" ";
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        //}
    },

    onAddrDExpand31: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalD");
        queryPlan.combo.getStore().getProxy().url = "/TKalD/search/findByEnotCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('empAddrPe').getValue()+" ";
    },

    onAddrDSelect31: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('empAddrK');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrDDirtyChange31: function(field, isDirty, eOpts) {
        //if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
        field.getStore().getProxy().url = "/TKalD/"+field.getValue()+" ";
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        //}
    },

    onAddrKExpand31: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalK");
        queryPlan.combo.getStore().getProxy().url = "/TKalK/search/findByDimosCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('empAddrD').getValue();
    },

    onAddrKDirtyChange31: function(field, isDirty, eOpts) {
        //if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
        field.getStore().getProxy().url = "/TKalK/"+field.getValue();
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        //}
    },

    onAddrPSelect11: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('accidentAddrPe');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeExpand11: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
        queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('accidentAddrP').getValue()+" ";
    },

    onAddrPeSelect11: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('accidentAddrD');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeDirtyChange11: function(field, isDirty, eOpts) {
        //if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
        field.getStore().getProxy().url = "/TKalPe/"+field.getValue()+" ";
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        //}
    },

    onAddrDExpand11: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalD");
        queryPlan.combo.getStore().getProxy().url = "/TKalD/search/findByEnotCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('accidentAddrPe').getValue()+" ";
    },

    onAddrDSelect11: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('accidentAddrK');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrDDirtyChange11: function(field, isDirty, eOpts) {
        //if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
        field.getStore().getProxy().url = "/TKalD/"+field.getValue()+" ";
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        //}
    },

    onAddrKExpand11: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalK");
        queryPlan.combo.getStore().getProxy().url = "/TKalK/search/findByDimosCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('accidentAddrD').getValue();
    },

    onAddrKDirtyChange11: function(field, isDirty, eOpts) {
        //if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
        field.getStore().getProxy().url = "/TKalK/"+field.getValue();
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        //}
    },

    onAddWitnessClick: function(button, e, eOpts) {
        var newwitness = Ext.create('widget.companyaccidentformwitness', {});
        button.up('form').getForm().findField('witnessesnum').setValue(parseInt(button.up('form').getForm().findField('witnessesnum').getValue())+1);
        newwitness.items.getAt(0).items.getAt(0).setValue(button.up('form').getForm().findField('witnessesnum').getValue()+". ");
        var scrollpos = button.up('window').getScrollY();
        button.up().add(newwitness);
        button.up('window').scrollTo(0, scrollpos);
    },

    onDelWitnessClick: function(button, e, eOpts) {
        if(parseInt(button.up('form').getForm().findField('witnessesnum').getValue())!==0){
            button.up('form').getForm().findField('witnessesnum').setValue(parseInt(button.up('form').getForm().findField('witnessesnum').getValue())-1);
            var scrollpos = button.up('window').getScrollY();
            button.up().items.get(button.up().items.length-1).destroy();
            button.up('window').scrollTo(0, scrollpos);
        }
    },

    onDeleteClick: function(button, e, eOpts) {
        button.up('form').getForm().findField('deletedfile').setValue(true);
        button.up('container').hide();
        button.up('form').getForm().findField('file').focus();
    },

    onViewDocClick: function(button, e, eOpts) {
        var url = "/getDocument?docId="+button.up('form').getForm().findField('attachedDocId').getValue();
        var win = window.open(url, '_blank');
        win.focus();
    },

    onWindowBeforeDestroy: function(component, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.company.AccidentPanel');
        center.add(viewsub);
    },

    onCompAccident_save_submit_toolbarBeforeHide: function(component, eOpts) {
        component.getComponent('deleteaccidentbutton').destroy();
        component.getComponent('saveaccidentbutton').destroy();
        component.getComponent('submitaccidentbutton').destroy();
    },

    onSpecialityExpand: function(queryPlan, eOpts) {
        var field=queryPlan.combo;
        if (field.getValue()===null) {
            field.clearValue();
            field.getStore().getProxy().url = "/TEmployeeSpeciality";
            field.getStore().getProxy().getReader().setRootProperty("_embedded.TEmployeeSpeciality");
        }
        else {
            field.getStore().getProxy().url = "/TEmployeeSpeciality/search/findByDescr";
            field.getStore().getProxy().getReader().setRootProperty("_embedded.TEmployeeSpeciality");
        }
    },

    onBranchExpand: function(queryPlan, eOpts) {
        var field=queryPlan.combo;
        if (field.getValue()===null) {
            field.clearValue();
            field.getStore().getProxy().url = "/TEmployerBranchIKA/search/findBySession";
            field.getStore().getProxy().getReader().setRootProperty("_embedded.TEmployerBranchIKA");
        }
        else {
            field.getStore().getProxy().url = "/TEmployerBranchIKA/search/findByDescr";
            field.getStore().getProxy().getReader().setRootProperty("_embedded.TEmployerBranchIKA");
        }
    },

    /*setEmpAge: function(field, isDirty, eOpts) {
     var currentDate = new Date();
     var birthdate = field.up('form').getForm().findField('empBirthdateView').getValue();
     field.up('form').getForm().findField('empAge').setValue(currentDate.getFullYear() - birthdate.getFullYear());
     },*/

});