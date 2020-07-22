/*
 * File: app/view/employee/DisputeForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.employee.DisputeForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.employeedisputeform',

    requires: [
        'MyApp.view.employee.DisputeFormViewModel',
        'MyApp.view.employee.DisputeFormViewController',
        'MyApp.view.shared.PrintFormTool',
        'MyApp.view.shared.CloseFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.FieldSet',
        'Ext.form.field.Date',
        'Ext.form.field.Number',
        'Ext.form.field.TextArea',
        'Ext.form.field.Time',
        'Ext.form.Label',
        'Ext.form.RadioGroup',
        'Ext.form.field.Radio',
        'Ext.form.field.File',
        'Ext.button.Button',
        'Ext.toolbar.Toolbar',
        'Ext.panel.Tool'
    ],

    controller: 'employeedisputeform',
    viewModel: {
        type: 'employeedisputeform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    overflowY: 'scroll',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Αίτηση Συζήτησης Εργατικής Διαφοράς',
    //modal: true,
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'form',
            onCheckBoxClick: function(checkbox, checked) {
                var textarea = checkbox.up('fieldcontainer').down('textareafield');
                var dateareafrom = checkbox.up('fieldcontainer').down('container').down('container').items.get(0);
                var dateareato = checkbox.up('fieldcontainer').down('container').down('container').items.get(1);
                var scrollpos = checkbox.up('window').getScrollY();
                if (checked) {
                    textarea.show();
                    textarea.enable();
                    dateareafrom.reset();
                    dateareato.reset();
                    dateareafrom.show();
                    dateareafrom.enable();
                    dateareafrom.focus();
                    dateareato.enable();
                    dateareato.show();
                    dateareafrom.allowBlank=false;
                    dateareato.allowBlank=false;
                    textarea.allowBlank=true;
                } else {
                    textarea.setValue("");
                    textarea.disable();
                    textarea.hide();
                    dateareafrom.reset();
                    dateareafrom.disable();
                    dateareafrom.hide();
                    dateareato.reset();
                    dateareato.disable();
                    dateareato.hide();
                    dateareafrom.allowBlank=true;
                    dateareato.allowBlank=true;
                    textarea.allowBlank=true;
                }
                checkbox.up('window').scrollTo(0, scrollpos);
            },
            timestampToDate: function(timestamp) {

                var pD = timestamp.replace(/[^0-9]+/g,' ').split(" ");
                return (pD[2]+"-"+pD[1]+"-"+pD[0]);
            },
            id: 'employeedisputeform',
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
                            editable: false
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
                            fieldLabel: 'Αρμόδιο Τμήμα',
                            labelWidth: 180,
                            name: 'department',
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
                            fieldLabel: 'Ώρα Υποβολής',
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
                            fieldLabel: 'Τύπος Αίτησης',
                            labelWidth: 180,
                            name: 'applicantType',
                            validateOnChange: false,
                            value: 1,
                            readOnly: true,
                            validateOnBlur: false,
                            editable: false,
                            hideTrigger: true,
                            displayField: 'name',
                            forceSelection: true,
                            queryMode: 'local',
                            store: 'shared.FORM_TYPES',
                            valueField: 'abbr'
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
                            xtype: 'combobox',
                            hidden: true,
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
                            xtype: 'fieldset',
                            focusOnToFront: false,
                            padding: 10,
                            toFrontOnShow: false,
                            title: 'ΣΤΟΙΧΕΙΑ ΕΡΓΑΖΟΜΕΝΟΥ',
                            toggleOnTitleClick: false,
                            items: [
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    cls: 'x-item-disabled',
                                    fieldLabel: 'Όνομα',
                                    labelWidth: 180,
                                    name: 'empFirstname',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    editable: false
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    cls: 'x-item-disabled',
                                    fieldLabel: 'Επώνυμο',
                                    labelWidth: 180,
                                    name: 'empLastname',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    editable: false
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Ειδικότητα',
                                    labelWidth: 180,
                                    name: 'empSpecialityId',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 100,
                                    displayField: 'spRtspDescription',
                                    forceSelection: true,
                                    pageSize: 25,
                                    queryParam: 'descr',
                                    store: 'employee.SPECIALTY',
                                    valueField: 'abbr',
                                    listeners: {
                                        beforequery: 'onSpecialityExpand'
                                    }
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Οικογενειακή Κατάσταση',
                                    labelWidth: 180,
                                    name: 'empMaritalStatus',
                                    validateOnChange: false,
                                    readOnly: false,
                                    validateOnBlur: false,
                                    editable: false,
                                    autoLoadOnValue: true,
                                    displayField: 'description',
                                    forceSelection: true,
                                    store: 'employee.MARITAL_STATUS',
                                    valueField: 'mid'
                                },
                                {
                                    xtype: 'numberfield',
                                    anchor: '100%',
                                    fieldLabel: 'Ανήλικα Τέκνα',
                                    labelWidth: 180,
                                    name: 'empChildrenNum',
                                    validateOnChange: false,
                                    value: 0,
                                    validateOnBlur: false,
                                    allowDecimals: false,
                                    allowExponential: false,
                                    decimalPrecision: 0,
                                    maxValue: 20,
                                    minValue: 0
                                },
                                {
                                    xtype: 'numberfield',
                                    anchor: '100%',
                                    fieldLabel: 'Αποδοχές Καθαρές (Ευρώ)',
                                    labelWidth: 180,
                                    name: 'empNetSalary',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    hideTrigger: true,
                                    maxLength: 50,
                                    repeatTriggerClick: false,
                                    keyNavEnabled: false,
                                    mouseWheelEnabled: false,
                                    spinDownEnabled: false,
                                    spinUpEnabled: false,
                                    allowExponential: false,
                                    decimalSeparator: ',',
                                    //currencySymbol: 'EUR',
                                    decimalPrecision:2,
                                    allowDecimals:true,
                                    alwaysDisplayDecimals: true,
                                    minValue: 0
                                },
                                {
                                    xtype: 'numberfield',
                                    anchor: '100%',
                                    fieldLabel: 'Αποδοχές Μικτές (Ευρώ)',
                                    labelWidth: 180,
                                    name: 'empGrossSalary',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: true,
                                    allowOnlyWhitespace: true,
                                    hideTrigger: true,
                                    maxLength: 50,
                                    repeatTriggerClick: false,
                                    keyNavEnabled: false,
                                    mouseWheelEnabled: false,
                                    spinDownEnabled: false,
                                    spinUpEnabled: false,
                                    allowExponential: false,
                                    decimalSeparator: ',',
                                    //currencySymbol: 'EUR',
                                    decimalPrecision:2,
                                    allowDecimals:true,
                                    alwaysDisplayDecimals: true,
                                    minValue: 0
                                },
                                {
                                    xtype: 'fieldcontainer',
                                    fieldLabel: 'Διάρκεια Εργασιακής Σχέσης',
                                    labelWidth: 180,
                                    layout: {
                                        type: 'vbox',
                                        align: 'stretchmax',
                                        pack: 'center'
                                    },
                                    items: [
                                        {
                                            xtype: 'datefield',
                                            fieldLabel: 'Από',
                                            labelWidth: 40,
                                            msgTarget: 'under',
                                            name: 'empFromDate',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            allowBlank: false,
                                            allowOnlyWhitespace: false,
                                            editable: false
                                        },
                                        {
                                            xtype: 'datefield',
                                            fieldLabel: 'Εως',
                                            labelWidth: 40,
                                            msgTarget: 'under',
                                            name: 'empUntilDate',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            allowBlank: false,
                                            allowOnlyWhitespace: false,
                                            editable: false
                                        }
                                    ]
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
                                            fieldLabel: 'Από',
                                            labelWidth: 40,
                                            name: 'empWorkingHoursFrom',
                                            submitValue: false,
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            forceSelection: true,
                                            format: 'H:i',
                                            increment: 30
                                        },
                                        {
                                            xtype: 'timefield',
                                            margin: '0 0 0 20',
                                            fieldLabel: 'Εως',
                                            labelWidth: 40,
                                            name: 'empWorkingHoursUntil',
                                            submitValue: false,
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            forceSelection: true,
                                            format: 'H:i',
                                            increment: 30
                                        },
                                        {
                                            xtype: 'hiddenfield',
                                            name: 'empWorkingHours',
                                            validateOnChange: false
                                        }
                                    ]
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
                                            maxLength: 100
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
                                                select: 'onAddrPSelect'
                                            }
                                        },
                                        {
                                            xtype: 'combobox',
                                            anchor: '100%',
                                            session: false,
                                            disabled: true,
                                            fieldLabel: 'Περιφερειακή Ενότητα',
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
                                                beforequery: 'onAddrPeExpand',
                                                select: 'onAddrPeSelect',
                                                dirtychange: {
                                                    fn: 'onAddrPeDirtyChange',
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
                                                beforequery: 'onAddrDExpand',
                                                select: 'onAddrDSelect',
                                                dirtychange: {
                                                    fn: 'onAddrDDirtyChange',
                                                    single: true
                                                }
                                            }
                                        },
                                        {
                                            xtype: 'combobox',
                                            anchor: '100%',
                                            disabled: true,
                                            fieldLabel: 'Δημοτική Ενότητα',
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
                                                beforequery: 'onAddrKExpand',
                                                dirtychange: {
                                                    fn: 'onAddrKDirtyChange',
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
                                            allowBlank: false,
                                            allowOnlyWhitespace: false,
                                            maxLength: 5,
                                            minLength: 5,
                                            vtype: 'Number'
                                        }
                                    ]
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    cls: 'x-item-disabled',
                                    fieldLabel: 'Αρ. Αποδεικτικού Ταυτότητας',
                                    labelWidth: 180,
                                    name: 'empCardNumber',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    editable: false,
                                    maxLength: 50
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    cls: 'x-item-disabled',
                                    fieldLabel: 'ΑΜΚΑ',
                                    labelWidth: 180,
                                    name: 'empAmka',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    editable: false,
                                    maxLength: 50
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    cls: 'x-item-disabled',
                                    fieldLabel: 'ΑΦΜ',
                                    labelWidth: 180,
                                    name: 'empAfm',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    editable: false,
                                    maxLength: 9,
                                    minLength: 9,
                                    maskRe:/[0-9.]/
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
                                    minLength: 10,
                                    vtype: 'telNumber'
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
                                    minLength: 10,
                                    vtype: 'telNumber'
                                }
                            ]
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
                                    fieldLabel: 'Α.Φ.Μ.',
                                    labelWidth: 180,
                                    name: 'compAfm',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 9,
                                    minLength: 9,
                                    maskRe:/[0-9.]/,
                                    vtype: 'Number',
                                    listeners: {
                                        blur: 'onAFMBlur',
                                        specialkey: 'onAFMEnterKey'
                                    }
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Επωνυμία ή Ονοματεπώνυμο',
                                    labelWidth: 180,
                                    name: 'compName',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 300
                                },
                                {
                                    xtype: 'combobox',
                                    autofillCompFields: function(formPart, selectedrec) {
                                        //Autofill for Working Branch
                                        if (selectedrec.get('rgEbrAddressStreet')!==null){
                                            formPart.findField('compBrAddr').setValue(selectedrec.get('rgEbrAddressStreet'));
                                        }

                                        if (selectedrec.get('rgEbrZipCode')!==null){
                                            formPart.findField('compBrAddrTk').setValue(selectedrec.get('rgEbrZipCode'));
                                        }

                                        if (selectedrec.get('rgEbrEmployerId')!==null){
                                            formPart.findField('compAmeIka').setValue(selectedrec.get('rgEbrEmployerId'));
                                        }

                                        if (selectedrec.get('rgEbrKallikratis')!==null){
                                            formPart.findField('compBrAddrPe').enable();
                                            formPart.findField('compBrAddrD').enable();
                                            formPart.findField('compBrAddrK').enable();

                                            formPart.findField('compBrAddrK').setValue(selectedrec.get('rgEbrKallikratis'));

                                            formPart.findField('compBrAddrK').getStore().getProxy().url = "/TKalK/"+selectedrec.get('rgEbrKallikratis');
                                            formPart.findField('compBrAddrK').getStore().getProxy().getReader().setRootProperty("");
                                            formPart.findField('compBrAddrK').store.load({callback : function(records, operation, success){
                                                var storeAddr1 = Ext.StoreMgr.lookup( 'address1.ADDR_K1' );

                                                formPart.findField('compBrAddrP').setValue(storeAddr1.getAt(0).get('pCode'));
                                                formPart.findField('compBrAddrPe').setValue(storeAddr1.getAt(0).get('peCode'));
                                                formPart.findField('compBrAddrD').setValue(storeAddr1.getAt(0).get('dCode'));

                                                formPart.findField('compBrAddrPe').getStore().getProxy().url = "/TKalPe/"+storeAddr1.getAt(0).get('peCode')+" ";
                                                formPart.findField('compBrAddrPe').getStore().getProxy().getReader().setRootProperty("");
                                                formPart.findField('compBrAddrPe').store.load();

                                                formPart.findField('compBrAddrD').getStore().getProxy().url = "/TKalD/"+storeAddr1.getAt(0).get('dCode')+" ";
                                                formPart.findField('compBrAddrD').getStore().getProxy().getReader().setRootProperty("");
                                                formPart.findField('compBrAddrD').store.load();

                                            }
                                            });
                                        }

                                        //Autofill for Central Branch
                                        var store = Ext.StoreMgr.lookup( 'shared.AFM_COMP' );
                                        var centralrec = store.findRecord('rgEbrBranchId',0);

                                        if (centralrec.get('rgEmpFullname')!==null || centralrec.get('rgEmpName')!==null){
                                            if (centralrec.get('rgEmpName')===null)
                                                formPart.findField('compName').setValue(centralrec.get('rgEmpFullname'));
                                            else
                                                formPart.findField('compName').setValue(centralrec.get('rgEmpName'));
                                        }

                                        if (centralrec.get('rgEbrPhoneNumber')!==null){
                                            formPart.findField('compPhone').setValue(centralrec.get('rgEbrPhoneNumber'));
                                        }

                                        if (centralrec.get('rgEbrAddressStreet')!==null){
                                            formPart.findField('compAddr').setValue(centralrec.get('rgEbrAddressStreet'));
                                        }

                                        if (centralrec.get('rgEbrZipCode')!==null){
                                            formPart.findField('compAddrTk').setValue(centralrec.get('rgEbrZipCode'));
                                        }

                                        if (centralrec.get("rgEbrSecStakod1")!==null && centralrec.get("rgEbrSecStakod2")!==null &&
                                            centralrec.get("rgEbrSecStakod3")!==null && centralrec.get("rgEbrSecStakod4")!==null &&
                                            centralrec.get("rgEbrSecStakod5")!==null)
                                        {
                                            formPart.findField('rtstakLevel2').enable();
                                            formPart.findField('rtstakLevel3').enable();
                                            formPart.findField('rtstakLevel4').enable();
                                            formPart.findField('rtstakLevel5').enable();

                                            formPart.findField('rtstakLevel1').setValue(centralrec.get('rgEbrSecStakod1'));
                                            formPart.findField('rtstakLevel2').setValue(centralrec.get('rgEbrSecStakod2'));
                                            formPart.findField('rtstakLevel3').setValue(centralrec.get('rgEbrSecStakod3'));
                                            formPart.findField('rtstakLevel4').setValue(centralrec.get('rgEbrSecStakod4'));
                                            formPart.findField('rtstakLevel5').setValue(centralrec.get('rgEbrSecStakod5'));

                                            formPart.findField('rtstakLevel2').getStore().getProxy().url = "/RtStakLevel2/search/findLevel2byId"+"?id="+centralrec.get("rgEbrSecStakod2");
                                            formPart.findField('rtstakLevel2').store.load();
                                            formPart.findField('rtstakLevel3').getStore().getProxy().url = "/RtStakLevel3/search/findLevel3byId"+"?id="+centralrec.get("rgEbrSecStakod3");
                                            formPart.findField('rtstakLevel3').store.load();
                                            formPart.findField('rtstakLevel4').getStore().getProxy().url = "/RtStakLevel4/search/findLevel4byId"+"?id="+centralrec.get("rgEbrSecStakod4");
                                            formPart.findField('rtstakLevel4').store.load();
                                            formPart.findField('rtstakLevel5').getStore().getProxy().url = "/RtStakLevel5/search/findLevel5byId"+"?id="+centralrec.get("rgEbrSecStakod5");
                                            formPart.findField('rtstakLevel5').store.load();

                                        }

                                        if (centralrec.get('rgEbrKallikratis')!==null){
                                            formPart.findField('compAddrPe').enable();
                                            formPart.findField('compAddrD').enable();
                                            formPart.findField('compAddrK').enable();

                                            formPart.findField('compAddrK').setValue(centralrec.get('rgEbrKallikratis'));

                                            formPart.findField('compAddrK').getStore().getProxy().url = "/TKalK/"+centralrec.get('rgEbrKallikratis');
                                            formPart.findField('compAddrK').getStore().getProxy().getReader().setRootProperty("");
                                            formPart.findField('compAddrK').store.load({callback : function(records, operation, success){
                                                var storeAddr2 = Ext.StoreMgr.lookup( 'address.ADDR_K' );

                                                formPart.findField('compAddrP').setValue(storeAddr2.getAt(0).get('pCode'));
                                                formPart.findField('compAddrPe').setValue(storeAddr2.getAt(0).get('peCode'));
                                                formPart.findField('compAddrD').setValue(storeAddr2.getAt(0).get('dCode'));

                                                formPart.findField('compAddrPe').getStore().getProxy().url = "/TKalPe/"+storeAddr2.getAt(0).get('peCode')+" ";
                                                formPart.findField('compAddrPe').getStore().getProxy().getReader().setRootProperty("");
                                                formPart.findField('compAddrPe').store.load();

                                                formPart.findField('compAddrD').getStore().getProxy().url = "/TKalD/"+storeAddr2.getAt(0).get('dCode')+" ";
                                                formPart.findField('compAddrD').getStore().getProxy().getReader().setRootProperty("");
                                                formPart.findField('compAddrD').store.load();

                                            }
                                            });
                                        }

                                    },
                                    anchor: '100%',
                                    fieldLabel: 'Επιλογή Παραρτήματος',
                                    labelWidth: 180,
                                    name: 'branch1Id',
                                    validateOnChange: false,
                                    readOnly: true,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    editable: false,
                                    emptyText: 'Καταχωρήστε πρώτα το Α.Φ.Μ. του Εργοδότη/Επιχείρησης',
                                    displayField: 'rgEbrAddressStreetCombo',
                                    store: 'shared.AFM_COMP',
                                    valueField: 'rgEbrBranchId',
                                    listeners: {
                                        select: 'onBranch1Select'
                                    }
                                },
                                {
                                    xtype: 'hiddenfield',
                                    anchor: '100%',
                                    fieldLabel: 'Label',
                                    name: 'branch0Id'
                                },
                                {
                                    xtype: 'fieldset',
                                    hidden: true,
                                    focusOnToFront: false,
                                    toFrontOnShow: false,
                                    items: [
                                        {
                                            xtype: 'label',
                                            baseCls: 'x-form-item-label',
                                            text: 'Αντικείμενο Εργασιών Εργοδότη:'
                                        },
                                        {
                                            xtype: 'combobox',
                                            anchor: '100%',
                                            labelWidth: 180,
                                            name: 'rtstakLevel1',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            editable: false,
                                            autoLoadOnValue: true,
                                            displayField: 'spRtstackLevel1Desc',
                                            store: 'compScope.rtstakLevel1',
                                            transformInPlace: false,
                                            valueField: 'abbr',
                                            listeners: {
                                                select: 'onCompScopeL1Select'
                                            }
                                        },
                                        {
                                            xtype: 'combobox',
                                            anchor: '100%',
                                            session: false,
                                            disabled: true,
                                            labelWidth: 180,
                                            name: 'rtstakLevel2',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            //allowBlank: false,
                                            //allowOnlyWhitespace: false,
                                            editable: false,
                                            displayField: 'spRtstackLevel2Desc',
                                            queryParam: 'level1',
                                            store: 'compScope.rtstakLevel2',
                                            valueField: 'abbr',
                                            listeners: {
                                                beforequery: 'onCompScopeL2Expand',
                                                select: 'onCompScopeL2Select',
                                                dirtychange: {
                                                    fn: 'onCompScopeL2DirtyChange',
                                                    single: true
                                                }
                                            }
                                        },
                                        {
                                            xtype: 'combobox',
                                            anchor: '100%',
                                            disabled: true,
                                            labelWidth: 180,
                                            name: 'rtstakLevel3',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            //allowBlank: false,
                                            //allowOnlyWhitespace: false,
                                            editable: false,
                                            displayField: 'spRtstackLevel3Desc',
                                            queryParam: 'level2',
                                            store: 'compScope.rtstakLevel3',
                                            valueField: 'abbr',
                                            listeners: {
                                                beforequery: 'onCompScopeL3Expand',
                                                select: 'onCompScopeL3Select',
                                                dirtychange: {
                                                    fn: 'onCompScopeL3DirtyChange',
                                                    single: true
                                                }
                                            }
                                        },
                                        {
                                            xtype: 'combobox',
                                            anchor: '100%',
                                            disabled: true,
                                            labelWidth: 180,
                                            name: 'rtstakLevel4',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            //allowBlank: false,
                                            //allowOnlyWhitespace: false,
                                            editable: false,
                                            displayField: 'spRtstackLevel4Desc',
                                            queryParam: 'level3',
                                            store: 'compScope.rtstakLevel4',
                                            valueField: 'abbr',
                                            listeners: {
                                                beforequery: 'onCompScopeL4Expand',
                                                select: 'onCompScopeL4Select',
                                                dirtychange: {
                                                    fn: 'onCompScopeL4DirtyChange',
                                                    single: true
                                                }
                                            }
                                        },
                                        {
                                            xtype: 'combobox',
                                            anchor: '100%',
                                            disabled: true,
                                            labelWidth: 180,
                                            name: 'rtstakLevel5',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            //allowBlank: false,
                                            //allowOnlyWhitespace: false,
                                            editable: false,
                                            displayField: 'spRtstackLevel5Desc',
                                            queryParam: 'level4',
                                            store: 'compScope.rtstakLevel5',
                                            valueField: 'abbr',
                                            listeners: {
                                                beforequery: 'onCompScopeL5Expand',
                                                dirtychange: {
                                                    fn: 'onCompScopeL5DirtyChange',
                                                    single: true
                                                }
                                            }
                                        }
                                    ]
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Νόμιμος Εκπρόσωπος',
                                    labelWidth: 180,
                                    name: 'compRepresentative',
                                    hidden: true,
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 100
                                },
                                {
                                    xtype: 'fieldset',
                                    focusOnToFront: false,
                                    toFrontOnShow: false,
                                    title: 'Έδρα-Διεύθυνση Εργοδότη',
                                    hidden: true,
                                    items: [
                                        {
                                            xtype: 'textareafield',
                                            anchor: '100%',
                                            fieldLabel: 'Οδός / Αριθμός',
                                            labelWidth: 180,
                                            name: 'compAddr',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            allowBlank: false,
                                            allowOnlyWhitespace: false,
                                            maxLength: 100
                                        },
                                        {
                                            xtype: 'combobox',
                                            anchor: '100%',
                                            fieldLabel: 'Περιφέρεια',
                                            labelWidth: 180,
                                            name: 'compAddrP',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            //allowBlank: false,
                                            //allowOnlyWhitespace: false,
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
                                            fieldLabel: 'Περιφερειακή Ενότητα',
                                            labelWidth: 180,
                                            name: 'compAddrPe',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            //allowBlank: false,
                                            //allowOnlyWhitespace: false,
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
                                            name: 'compAddrD',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            //allowBlank: false,
                                            //allowOnlyWhitespace: false,
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
                                            fieldLabel: 'Δημοτική Ενότητα',
                                            labelWidth: 180,
                                            name: 'compAddrK',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            //allowBlank: false,
                                            //allowOnlyWhitespace: false,
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
                                            name: 'compAddrTk',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            //allowBlank: false,
                                            //allowOnlyWhitespace: false,
                                            maxLength: 50,
                                            minLength: 5,
                                            vtype: 'Number'
                                        }
                                    ]
                                },
                                {
                                    xtype: 'fieldset',
                                    focusOnToFront: false,
                                    toFrontOnShow: false,
                                    title: 'Διεύθυνση Παροχής Εργασίας',
                                    items: [
                                        {
                                            xtype: 'textareafield',
                                            anchor: '100%',
                                            fieldLabel: 'Οδός / Αριθμός',
                                            labelWidth: 180,
                                            name: 'compBrAddr',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            allowBlank: false,
                                            allowOnlyWhitespace: false,
                                            maxLength: 100
                                        },
                                        {
                                            xtype: 'combobox',
                                            anchor: '100%',
                                            fieldLabel: 'Περιφέρεια',
                                            labelWidth: 180,
                                            name: 'compBrAddrP',
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
                                                select: 'onAddrPSelect1'
                                            }
                                        },
                                        {
                                            xtype: 'combobox',
                                            anchor: '100%',
                                            disabled: true,
                                            fieldLabel: 'Περιφερειακή Ενότητα',
                                            labelWidth: 180,
                                            name: 'compBrAddrPe',
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
                                                beforequery: 'onAddrPeExpand1',
                                                select: 'onAddrPeSelect1',
                                                dirtychange: {
                                                    fn: 'onAddrPeDirtyChange1',
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
                                            name: 'compBrAddrD',
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
                                                beforequery: 'onAddrDExpand1',
                                                select: 'onAddrDSelect1',
                                                dirtychange: {
                                                    fn: 'onAddrDDirtyChange1',
                                                    single: true
                                                }
                                            }
                                        },
                                        {
                                            xtype: 'combobox',
                                            anchor: '100%',
                                            disabled: true,
                                            fieldLabel: 'Δημοτική Ενότητα',
                                            labelWidth: 180,
                                            name: 'compBrAddrK',
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
                                                beforequery: 'onAddrKExpand1',
                                                dirtychange: {
                                                    fn: 'onAddrKDirtyChange1',
                                                    single: true
                                                }
                                            }
                                        },
                                        {
                                            xtype: 'textfield',
                                            anchor: '100%',
                                            fieldLabel: 'Τ.Κ',
                                            labelWidth: 180,
                                            name: 'compBrAddrTk',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            allowBlank: false,
                                            allowOnlyWhitespace: false,
                                            maxLength: 5,
                                            minLength: 5,
                                            vtype: 'Number'
                                        }
                                    ]
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Δ.Ο.Υ.',
                                    labelWidth: 180,
                                    name: 'compDoy',
                                    hidden: true,
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    editable: false,
                                    autoLoadOnValue: true,
                                    displayField: 'descr',
                                    forceSelection: true,
                                    store: 'shared.DOY',
                                    valueField: 'abbr'
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'ΑΜΕ ΙΚΑ',
                                    labelWidth: 180,
                                    name: 'compAmeIka',
                                    hidden: true,
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 100
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Τηλέφωνο Εργοδότη',
                                    labelWidth: 180,
                                    name: 'compPhone',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 10,
                                    minLength: 10,
                                    vtype: 'telNumber'
                                }
                            ]
                        },
                        {
                            xtype: 'checkboxgroup',
                            layout: 'anchor',
                            fieldLabel: 'Επιλέξτε όσα πεδία αφορούν την αίτησή σας, και συμπληρώστε το χρονικό διάστημα στο οποίο αναφέρεστε για κάθε θέμα.',
                            labelAlign: 'top',
                            msgTarget: 'side',
                            validateOnChange: false,
                            allowBlank: false,
                            blankText: 'Επιλέξτε τουλάχιστον ένα λόγο',
                            listeners: {
                                added: 'onLoadAfterRender'
                            }
                        },
                        {
                            xtype: 'radiogroup',
                            anchor: '90%',
                            fieldLabel: 'Αποδέχεστε την συζήτηση της αίτησης σας μαζί με άλλες που αφορούν τον ίδιο εργοδότη και έχουν γίνει σε κοντινό διάστημα; (Αν υπάρχουν)',
                            labelAlign: 'top',
                            combineErrors: true,
                            validateOnChange: false,
                            allowBlank: false,
                            items: [
                                {
                                    xtype: 'radiofield',
                                    name: 'groupDiscussion',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Ναι',
                                    inputValue: '1'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'groupDiscussion',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Οχι',
                                    inputValue: '0'
                                }
                            ]
                        },
                        {
                            xtype: 'form',
                            id: 'dispfile',
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
                                        click: 'onDeleteClick1'
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
                                        click: 'onViewDocClick1'
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
                        },
                        {
                            xtype: 'hiddenfield',
                            anchor: '100%',
                            fieldLabel: 'Επισυναπτόμενο Αρχείο',
                            labelWidth: 180,
                            msgTarget: 'under',
                            name: 'docIdAttached',
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
                            maxLength: 3000
                        }
                    ]
                },
                {
                    xtype: 'hiddenfield',
                    width: 150,
                    fieldLabel: 'Όνομα',
                    labelWidth: 180,
                    name: 'userId',
                    validateOnChange: false
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
                    name: 'employeeDisputeReason',
                    validateOnChange: false
                }
            ]
        }
    ],
    listeners: {
        beforedestroy: 'onWindowBeforeDestroy',
        show: function(component, eOpts) {
            var view = Ext.getCmp('employeemainView');
            var menu = view.getComponent('headerPanel');
            var contentPanel = view.getComponent('contentPanel');
            menu.setDisabled(true);
            contentPanel.setDisabled(true);
        },
        hide: function(button, e, eOpts) {
            var view = Ext.getCmp('employeemainView');
            var menu = view.getComponent('headerPanel');
            var contentPanel = view.getComponent('contentPanel');
            menu.setDisabled(false);
            contentPanel.setDisabled(false);
        }
    },
    dockedItems: [
        {
            xtype: 'toolbar',
            dateToTimestamp: function(date) {
                if (date != null)
                {
                    var pD = date.split("-");
                    return (pD[2]+"-"+pD[1]+"-"+pD[0]+"T00:00:00.000+0000");
                }
                else return "";
            },
            validateDatesDifference: function(dateFrom, dateUntil) {
                if (dateFrom.getValue()>=dateUntil.getValue()){
                    dateFrom.markInvalid("H ημ/νία Από πρέπει να είναι προγενέστερη από την Εως");
                    dateUntil.markInvalid("H ημ/νία Εως πρέπει να είναι προγενέστερη από την Από");
                    return false;
                }
                else
                    return true;
            },
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
            html: '<span style="color:#696969;font-size:11px;"><em>*Τα ανενεργά πεδία θα συμπληρωθούν αυτόματα<br>από το σύστημα κατά την υποβολή</em></span>',
            id: 'disp_save_submit_toolbar',
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
                    itemId: 'deletedisputebutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf00d@FontAwesome',
                    text: 'Διαγραφή',
                    tooltip: 'Διαγραφή  Αποθηκευμένης Καταγγελίας',
                    listeners: {
                        click: {
                            fn: 'onDelete_EMPLOYEE_DISPUTE',
                            scope: 'controller'
                        }
                    }
                },
                {
                    xtype: 'button',
                    itemId: 'savedisputebutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf0c7@FontAwesome',
                    text: 'Αποθήκευση',
                    listeners: {
                        click: {
                            fn: 'onSave_EMPLOYER_DISPUTE',
                            scope: 'controller'
                        }
                    }
                },
                {
                    xtype: 'button',
                    itemId: 'submitdisputebutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf1d8@FontAwesome',
                    text: 'Υποβολή',
                    listeners: {
                        click: {
                            fn: 'onSubmit_EMPLOYER_DISPUTE',
                            scope: 'controller'
                        }
                    }
                }
            ],
            listeners: {
                beforehide: 'onDisp_save_submit_toolbarBeforeHide'
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

    onSpecialityExpand: function(queryPlan, eOpts) {
        var field=queryPlan.combo;
        if (field.getValue()===null)
        {
            field.clearValue();
            field.getStore().getProxy().url = "/TEmployeeSpeciality";
            field.getStore().getProxy().getReader().setRootProperty("_embedded.TEmployeeSpeciality");
        }
        else{
            field.getStore().getProxy().url = "/TEmployeeSpeciality/search/findByDescr";
            field.getStore().getProxy().getReader().setRootProperty("_embedded.TEmployeeSpeciality");
        }


    },

    onAddrPSelect: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('empAddrPe');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeExpand: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
        queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('empAddrP').getValue()+" ";
    },

    onAddrPeSelect: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('empAddrD');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeDirtyChange: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalPe/"+field.getValue()+" ";
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onAddrDExpand: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalD");
        queryPlan.combo.getStore().getProxy().url = "/TKalD/search/findByEnotCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('empAddrPe').getValue()+" ";
    },

    onAddrDSelect: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('empAddrK');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrDDirtyChange: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalD/"+field.getValue()+" ";
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onAddrKExpand: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalK");
        queryPlan.combo.getStore().getProxy().url = "/TKalK/search/findByDimosCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('empAddrD').getValue();
    },

    onAddrKDirtyChange: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalK/"+field.getValue();
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onAFMBlur: function(component, event, eOpts) {
        var branchId = component.up('form').getForm().findField('branch1Id');
        if (component.up('form').getForm().findField('subStatus').getValue()!=="2"){
            if (component.getValue().length>0){
                branchId.getStore().getProxy().extraParams = {afm:component.getValue()};
                branchId.getStore().load({callback: function(records, operation, success) {
                    if (records.length>0) {
                        branchId.setReadOnly(false);
                        branchId.focus();
                        branchId.expand();
                    } else {
                        Ext.Msg.alert('Πρόβλημα Α.Φ.Μ.', 'Το Α.Φ.Μ δεν αντιστοιχεί σε κάποιο οργανισμό');
                        branchId.clearValue();
                        branchId.setReadOnly(true);
                    }
                }});
            }
            else
            {
                branchId.clearValue();
                branchId.setReadOnly(true);
            }
        }
    },

    onAFMEnterKey: function(field, e, eOpts) {
        if (e.getKey() == e.ENTER) {


            var branchId = field.up('form').getForm().findField('branch1Id');
            branchId.focus();

        }
    },

    onBranch1Select: function(combo, records, eOpts) {
        //var store = Ext.StoreMgr.lookup( 'AFM_COMP' );
        //var centralId = store.findRecord('branchId',0).get('abbr');
        //combo.up('form').getForm().findField('branch0').setValue(centralId);
        var formToFill = combo.up('form').getForm();
        combo.up('form').getForm().findField('branch0Id').setValue(0);


        //DISABLE BELOW PART TO STOP AUTOFILL
        value = combo.getValue();
        selectedrec = combo.findRecordByValue(value);

        formToFill.findField('compBrAddrK').suspendEvent('dirtyChange');
        formToFill.findField('compBrAddrPe').suspendEvent('dirtyChange');
        formToFill.findField('compBrAddrD').suspendEvent('dirtyChange');
        formToFill.findField('compAddrK').suspendEvent('dirtyChange');
        formToFill.findField('compAddrPe').suspendEvent('dirtyChange');
        formToFill.findField('compAddrD').suspendEvent('dirtyChange');
        formToFill.findField('rtstakLevel2').suspendEvent('dirtyChange');
        formToFill.findField('rtstakLevel3').suspendEvent('dirtyChange');
        formToFill.findField('rtstakLevel4').suspendEvent('dirtyChange');
        formToFill.findField('rtstakLevel5').suspendEvent('dirtyChange');

        combo.autofillCompFields(formToFill, selectedrec);

        formToFill.findField('compBrAddrK').resumeEvent('dirtyChange');
        formToFill.findField('compBrAddrPe').resumeEvent('dirtyChange');
        formToFill.findField('compBrAddrD').resumeEvent('dirtyChange');
        formToFill.findField('compAddrK').resumeEvent('dirtyChange');
        formToFill.findField('compAddrPe').resumeEvent('dirtyChange');
        formToFill.findField('compAddrD').resumeEvent('dirtyChange');
        formToFill.findField('rtstakLevel2').resumeEvent('dirtyChange');
        formToFill.findField('rtstakLevel3').resumeEvent('dirtyChange');
        formToFill.findField('rtstakLevel4').resumeEvent('dirtyChange');
        formToFill.findField('rtstakLevel5').resumeEvent('dirtyChange');
    },

    onCompScopeL1Select: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('rtstakLevel2');
        getnext.clearValue();
        getnext.enable();
    },

    onCompScopeL2Expand: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.RtStakLevel2");
        queryPlan.combo.getStore().getProxy().url = "/RtStakLevel2/search/findLevel2";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('rtstakLevel1').getValue();
    },

    onCompScopeL2Select: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('rtstakLevel3');
        getnext.clearValue();
        getnext.enable();
    },

    onCompScopeL2DirtyChange: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/RtStakLevel2/search/findLevel2byId"+"?id="+field.getValue();
            field.store.load();
        }
    },

    onCompScopeL3Expand: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.RtStakLevel3");
        queryPlan.combo.getStore().getProxy().url = "/RtStakLevel3/search/findLevel3";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('rtstakLevel2').getValue();
    },

    onCompScopeL3Select: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('rtstakLevel4');
        getnext.clearValue();
        getnext.enable();
    },

    onCompScopeL3DirtyChange: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/RtStakLevel3/search/findLevel3byId"+"?id="+field.getValue();
            field.store.load();
        }
    },

    onCompScopeL4Expand: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.RtStakLevel4");
        queryPlan.combo.getStore().getProxy().url = "/RtStakLevel4/search/findLevel4";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('rtstakLevel3').getValue();
    },

    onCompScopeL4Select: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('rtstakLevel5');
        getnext.clearValue();
        getnext.enable();
    },

    onCompScopeL4DirtyChange: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/RtStakLevel4/search/findLevel4byId"+"?id="+field.getValue();
            field.store.load();
        }
    },

    onCompScopeL5Expand: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.RtStakLevel5");
        queryPlan.combo.getStore().getProxy().url = "/RtStakLevel5/search/findLevel5";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('rtstakLevel4').getValue();
    },

    onCompScopeL5DirtyChange: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/RtStakLevel5/search/findLevel5byId"+"?id="+field.getValue();
            field.store.load();
        }
    },

    onAddrPSelect2: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('compAddrPe');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeExpand2: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
        queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('compAddrP').getValue()+" ";
    },

    onAddrPeSelect2: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('compAddrD');
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
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('compAddrPe').getValue()+" ";
    },

    onAddrDSelect2: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('compAddrK');
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
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('compAddrD').getValue();
    },

    onAddrKDirtyChange2: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalK/"+field.getValue();
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onAddrPSelect1: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('compBrAddrPe');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeExpand1: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
        queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('compBrAddrP').getValue()+" ";
    },

    onAddrPeSelect1: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('compBrAddrD');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeDirtyChange1: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalPe/"+field.getValue()+" ";
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onAddrDExpand1: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalD");
        queryPlan.combo.getStore().getProxy().url = "/TKalD/search/findByEnotCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('compBrAddrPe').getValue()+" ";
    },

    onAddrDSelect1: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('compBrAddrK');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrDDirtyChange1: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalD/"+field.getValue()+" ";
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onAddrKExpand1: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalK");
        queryPlan.combo.getStore().getProxy().url = "/TKalK/search/findByDimosCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('compBrAddrD').getValue();
    },

    onAddrKDirtyChange1: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalK/"+field.getValue();
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onLoadAfterRender: function(component, container, pos, eOpts) {
        var store = Ext.StoreMgr.lookup( 'employee.DISPUTE_REASONS' );

        for (i = 0; i < store.getCount(); i++) {

            var newreason = Ext.create('widget.employeedisputereason', {



            });
            newreason.down('checkbox').boxLabel=store.getAt(i).get('spRlsDetailDesc');
            newreason.down('checkbox').inputValue=store.getAt(i).get('abbr');
            newreason.down('hiddenfield').name=store.getAt(i).get('abbr');
            component.add(newreason);
        }
    },

    onDeleteClick1: function(button, e, eOpts) {
        button.up('form').getForm().findField('deletedfile').setValue(true);
        button.up('container').hide();
        button.up('form').getForm().findField('file').focus();
    },

    onViewDocClick1: function(button, e, eOpts) {
        var url = "/getDocument?docId="+button.up('form').getForm().findField('docIdAttached').getValue();
        var win = window.open(url, '_blank');
        win.focus();
    },

    onWindowBeforeDestroy: function(component, eOpts) {
        var view=Ext.getCmp('employeemainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.employee.DisputesPanel');
        center.add(viewsub);

    },

    onDisp_save_submit_toolbarBeforeHide: function(component, eOpts) {
        component.getComponent('deletedisputebutton').destroy();
        component.getComponent('savedisputebutton').destroy();
        component.getComponent('submitdisputebutton').destroy();
    }

});