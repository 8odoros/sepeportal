/*
 * File: app/view/company/ComplaintForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.ComplaintForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companycomplaintform',

    requires: [
        'MyApp.view.company.ComplaintFormViewModel',
        'MyApp.view.company.ComplaintFormViewController',
        'MyApp.view.shared.PrintFormTool',
        'MyApp.view.shared.CloseFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.FieldSet',
        'Ext.form.field.Date',
        'Ext.form.field.Number',
        'Ext.form.field.ComboBox',
        'Ext.form.field.TextArea',
        'Ext.form.trigger.Trigger',
        'Ext.form.RadioGroup',
        'Ext.form.field.Radio',
        'Ext.form.field.File',
        'Ext.form.Label',
        'Ext.button.Button',
        'Ext.toolbar.Toolbar',
        'Ext.panel.Tool'
    ],

    controller: 'companycomplaintform',
    viewModel: {
        type: 'companycomplaintform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    overflowY: 'scroll',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Φόρμα Καταγγελίας',
    //modal: true,
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'form',
            id: 'employeecomplaintform3',
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
                    title: 'ΣΤΟΙΧΕΙΑ ΚΑΤΑΓΓΕΛΙΑΣ',
                    items: [
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            cls: 'x-item-disabled',
                            disabled: true,
                            fieldLabel: 'Αρμόδια Διεύθυνση/Τμήμα ΣΕΠΕ',
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
                            fieldLabel: 'Ώρα Καταγγελίας',
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
                            xtype: 'combobox',
                            anchor: '100%',
                            cls: 'x-item-disabled',
                            fieldLabel: 'Τύπος Αίτησης',
                            labelWidth: 180,
                            name: 'applicantType',
                            validateOnChange: false,
                            value: 2,
                            readOnly: true,
                            validateOnBlur: false,
                            editable: false,
                            hideTrigger: true,
                            autoLoadOnValue: true,
                            displayField: 'name',
                            forceSelection: true,
                            queryMode: 'local',
                            store: 'shared.FORM_TYPES',
                            valueField: 'abbr'
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            hidden: true,
                            fieldLabel: 'Τύπος Καταγγελίας',
                            labelWidth: 180,
                            name: 'complIsAnonymous',
                            validateOnChange: false,
                            value: 1,
                            readOnly: true,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            editable: false,
                            displayField: 'name',
                            forceSelection: true,
                            valueField: 'abbr',
                            bind: {
                                store: '{COMPL_TYPES}'
                            }
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
                                                select: 'onAddrPSelect211'
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
                                                beforequery: 'onAddrPeExpand211',
                                                select: 'onAddrPeSelect211',
                                                dirtychange: {
                                                    fn: 'onAddrPeDirtyChange211',
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
                                                beforequery: 'onAddrDExpand211',
                                                select: 'onAddrDSelect211',
                                                dirtychange: {
                                                    fn: 'onAddrDDirtyChange211',
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
                                                beforequery: 'onAddrKExpand211',
                                                dirtychange: {
                                                    fn: 'onAddrKDirtyChange211',
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
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 10,
                                    vtype: 'telNumber'
                                },
                                {
                                    xtype: 'numberfield',
                                    anchor: '100%',
                                    fieldLabel: 'Αριθμός Απασχολούμενου Προσωπικού',
                                    labelWidth: 180,
                                    name: 'employeeNum',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    hideTrigger: true,
                                    maxLength: 19,
                                    allowDecimals: false,
                                    allowExponential: false
                                }
                            ]
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
                                    fieldLabel: 'Όνομα',
                                    labelWidth: 180,
                                    name: 'empFirstname',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: true,
                                    allowOnlyWhitespace: true,
                                    emptyText: '',
                                    maxLength: 50
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Επώνυμο',
                                    labelWidth: 180,
                                    name: 'empLastname',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: true,
                                    allowOnlyWhitespace: true
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
                                    allowBlank: true,
                                    allowOnlyWhitespace: true,
                                    editable: false,
                                    autoLoadOnValue: true,
                                    displayField: 'name',
                                    forceSelection: true,
                                    store: 'shared.SEX',
                                    valueField: 'abbr'
                                },
                                {
                                    xtype: 'numberfield',
                                    anchor: '100%',
                                    fieldLabel: 'Ηλικία',
                                    labelWidth: 180,
                                    name: 'empAge',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: true,
                                    allowOnlyWhitespace: true,
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
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Α.Φ.Μ.',
                                    labelWidth: 180,
                                    name: 'empAfm',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: true,
                                    allowOnlyWhitespace: true,
                                    maxLength: 9,
                                    minLength: 9,
                                    maskRe:/[0-9.]/
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'ΑΜΚΑ',
                                    labelWidth: 180,
                                    name: 'empAmka',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: true,
                                    allowOnlyWhitespace: true,
                                    maxLength: 50
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Αρ. Δελτίου Ταυτότητας',
                                    labelWidth: 180,
                                    name: 'empCardNumber',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: true,
                                    allowOnlyWhitespace: true,
                                    maxLength: 50
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Υπηκοότητα',
                                    labelWidth: 180,
                                    name: 'empCitizenshipCd',
                                    validateOnChange: false,
                                    //readOnly: false,
                                    validateOnBlur: false,
                                    allowBlank: true,
                                    allowOnlyWhitespace: true,
                                    emptyText:'Πληκτρολογήστε μία χώρα',
                                    minChars: 1,
                                    triggerAction: 'query',
                                    typeAhead: true,
                                    hideTrigger:true,
                                    queryParam: 'description',
                                    //editable: false,
                                    //autoLoadOnValue: true,
                                    displayField: 'description',
                                    forceSelection: true,
                                    store: 'company.NATIONALITY',
                                    valueField: 'abbr'
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Σταθερό Τηλέφωνο',
                                    labelWidth: 180,
                                    name: 'empPhone',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: true,
                                    allowOnlyWhitespace: true,
                                    maxLength: 10,
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
                                    vtype: 'telNumber'
                                }
                            ]
                        },
                        {
                            xtype: 'container',
                            focusOnToFront: false,
                            padding: 10,
                            toFrontOnShow: false,
                            layout: 'anchor',
                            items: [
                                {
                                    xtype: 'combobox',
                                    autofillCompFields: function(formPart, selectedrec) {
                                        if (selectedrec.get('rgEbrAddressStreet')!==null){
                                            formPart.findField('inspAddr').setValue(selectedrec.get('rgEbrAddressStreet'));
                                        }

                                        if (selectedrec.get('rgEbrZipCode')!==null){
                                            formPart.findField('inspAddrTk').setValue(selectedrec.get('rgEbrZipCode'));
                                        }

                                        if (selectedrec.get('rgEbrKallikratis')!==null){
                                            formPart.findField('inspAddrK').setValue(selectedrec.get('rgEbrKallikratis'));

                                            formPart.findField('inspAddrK').getStore().getProxy().url = "/TKalK/"+selectedrec.get('rgEbrKallikratis');
                                            formPart.findField('inspAddrK').getStore().getProxy().getReader().setRootProperty("");
                                            formPart.findField('inspAddrK').store.load({callback : function(records, operation, success){
                                                var storeAddr1 = Ext.StoreMgr.lookup( 'address1.ADDR_K1' );

                                                formPart.findField('inspAddrP').setValue(storeAddr1.getAt(0).get('pCode'));
                                                formPart.findField('inspAddrPe').setValue(storeAddr1.getAt(0).get('peCode'));
                                                formPart.findField('inspAddrD').setValue(storeAddr1.getAt(0).get('dCode'));

                                                formPart.findField('inspAddrPe').getStore().getProxy().url = "/TKalPe/"+storeAddr1.getAt(0).get('peCode')+" ";
                                                formPart.findField('inspAddrPe').getStore().getProxy().getReader().setRootProperty("");
                                                formPart.findField('inspAddrPe').store.load();

                                                formPart.findField('inspAddrD').getStore().getProxy().url = "/TKalD/"+storeAddr1.getAt(0).get('dCode')+" ";
                                                formPart.findField('inspAddrD').getStore().getProxy().getReader().setRootProperty("");
                                                formPart.findField('inspAddrD').store.load();
                                                formPart.findField('inspAddrPe').enable();
                                                formPart.findField('inspAddrD').enable();
                                                formPart.findField('inspAddrK').enable();

                                            }
                                        });
                                    }

                                    },
                                    anchor: '100%',
                                    fieldLabel: 'Παράρτημα που αφορά',
                                    labelWidth: 180,
                                    name: 'branch1Id',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    //editable: false,
                                    minChars: 0,
                                    forceSelection: true,
                                    queryParam: 'descr',
                                    autoLoadOnValue: true,
                                    displayField: 'rgEbrAddressStreetCombo',
                                    store: 'company.COMP_BRANCES',
                                    valueField: 'rgEbrBranchId',
                                    triggers: {
                                        deselectBr: {
                                            handler: function(field, trigger, e) {
                                                field.clearValue();
                                                formPart=field.up('form').getForm();
                                                formPart.findField('inspAddr').reset();
                                                formPart.findField('inspAddr').setReadOnly(false);
                                                formPart.findField('inspAddrTk').reset();
                                                formPart.findField('inspAddrTk').setReadOnly(false);

                                                formPart.findField('inspAddrP').reset();
                                                formPart.findField('inspAddrPe').reset();
                                                formPart.findField('inspAddrD').reset();
                                                formPart.findField('inspAddrK').reset();
                                                formPart.findField('inspAddrPe').disable();
                                                formPart.findField('inspAddrD').disable();
                                                formPart.findField('inspAddrK').disable();

                                                formPart.findField('inspAddrP').setReadOnly(false);
                                                formPart.findField('inspAddrPe').setReadOnly(false);
                                                formPart.findField('inspAddrD').setReadOnly(false);
                                                formPart.findField('inspAddrK').setReadOnly(false);



                                            },
                                            cls: 'x-form-clear-trigger',
                                            hidden: true
                                        }
                                    },
                                    listeners: {
                                        select: 'onBranch1Select',
                                        beforequery: 'onBranchExpand'
                                    }
                                },
                                {
                                    xtype: 'hiddenfield',
                                    anchor: '100%',
                                    labelWidth: 180,
                                    name: 'branch0Id',
                                    validateOnChange: false,
                                    value: 0
                                }
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            focusOnToFront: false,
                            toFrontOnShow: false,
                            title: 'ΔΙΕΥΘΥΝΣΗ ΤΟΠΟΥ ΕΛΕΓΧΟΥ',
                            items: [
                                {
                                    xtype: 'textareafield',
                                    anchor: '100%',
                                    fieldLabel: 'Οδός / Αριθμός',
                                    labelWidth: 180,
                                    name: 'inspAddr',
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
                                    name: 'inspAddrP',
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
                                        select: 'onAddrPSelect212'
                                    }
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    session: false,
                                    disabled: true,
                                    fieldLabel: 'Νομός',
                                    labelWidth: 180,
                                    name: 'inspAddrPe',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    editable: false,
                                    displayField: 'descr',
                                    queryParam: 'perifCode',
                                    store: 'address1.ADDR_Pe1',
                                    valueField: 'abbr',
                                    listeners: {
                                        beforequery: 'onAddrPeExpand212',
                                        select: 'onAddrPeSelect212',
                                        dirtychange: {
                                            fn: 'onAddrPeDirtyChange212',
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
                                    name: 'inspAddrD',
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
                                        beforequery: 'onAddrDExpand212',
                                        select: 'onAddrDSelect212',
                                        dirtychange: {
                                            fn: 'onAddrDDirtyChange212',
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
                                    name: 'inspAddrK',
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
                                        beforequery: 'onAddrKExpand212',
                                        dirtychange: {
                                            fn: 'onAddrKDirtyChange212',
                                            single: true
                                        }
                                    }
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Τ.Κ',
                                    labelWidth: 180,
                                    name: 'inspAddrTk',
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
                            xtype: 'radiogroup',
                            anchor: '70%',
                            fieldLabel: 'Υπάρχει στον Οργανισμό Σωματείο Εργαζομένων',
                            labelAlign: 'top',
                            labelWidth: 250,
                            validateOnChange: false,
                            allowBlank: false,
                            items: [
                                {
                                    xtype: 'radiofield',
                                    name: 'compHasLabourUnion',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Ναι',
                                    inputValue: '1'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'compHasLabourUnion',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Οχι',
                                    inputValue: '0'
                                }
                            ]
                        },
                        {
                            xtype: 'radiogroup',
                            anchor: '70%',
                            fieldLabel: 'Η Καταγγελία αφορά επιθεώρηση Ασφάλειας και Υγείας στην Εργασία',
                            labelAlign: 'top',
                            labelWidth: 250,
                            validateOnChange: false,
                            allowBlank: false,
                            items: [
                                {
                                    xtype: 'radiofield',
                                    name: 'complInvolves',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Ασφάλειας και Υγείας στην Εργασία',
                                    inputValue: '1'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'complInvolves',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Εργασιακών Σχέσεων',
                                    inputValue: '0'
                                }
                            ]
                        },
                        /*{
                            xtype: 'radiogroup',
                            anchor: '70%',
                            fieldLabel: 'Η Καταγγελία αφορά επιθεώρηση Ασφάλειας και Υγείας στην Εργασία',
                            labelAlign: 'top',
                            labelWidth: 250,
                            validateOnChange: false,
                            allowBlank: false,
                            items: [
                                {
                                    xtype: 'radiofield',
                                    name: 'complInvlovesSafetyInsp',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Ναι',
                                    inputValue: '1'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'complInvlovesSafetyInsp',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Οχι',
                                    inputValue: '0'
                                }
                            ]
                        },
                        {
                            xtype: 'radiogroup',
                            anchor: '70%',
                            fieldLabel: 'Η Καταγγελία αφορά επιθεώρηση Εργασιακών Σχέσεων',
                            labelAlign: 'top',
                            labelWidth: 250,
                            combineErrors: true,
                            validateOnChange: false,
                            allowBlank: false,
                            items: [
                                {
                                    xtype: 'radiofield',
                                    name: 'complInvolvesLabRelations',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Ναι',
                                    inputValue: '1'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'complInvolvesLabRelations',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Οχι',
                                    inputValue: '0'
                                }
                            ]
                        },*/
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            fieldLabel: 'Τύπος Καταγγελίας',
                            labelWidth: 180,
                            name: 'complMatter',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            blankText: 'Επιλέξτε ένα ή περισότερους τύπους',
                            editable: false,
                            autoLoadOnValue: true,
                            displayField: 'descr',
                            forceSelection: true,
                            multiSelect: true,
                            valueField: 'abbr',
                            bind: {
                                store: '{COMPL_MATTERS}'
                            }
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            fieldLabel: 'Θέμα Καταγγελίας',
                            labelWidth: 180,
                            name: 'complDescr',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 500,
                            validateBlank: true
                        },
                        {
                            xtype: 'hiddenfield',
                            anchor: '100%',
                            fieldLabel: 'Επισυναπτόμενο Αρχείο',
                            labelWidth: 180,
                            msgTarget: 'under',
                            name: 'docIdAttached',
                            validateOnChange: false,
                            value: '-1'
                        },
                        {
                            xtype: 'form',
                            id: 'complfile6',
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
                    name: 'companyId',
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
            dock: 'bottom',
            html: '<span style="color:#696969;font-size:11px;"><em>*Τα ανενεργά πεδία θα συμπληρωθούν αυτόματα<br>από το σύστημα κατά την υποβολή</em></span>',
            id: 'compcompl_save_submit_toolbar',
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
                    itemId: 'deletecomplaintbutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf00d@FontAwesome',
                    text: 'Διαγραφή',
                    tooltip: 'Διαγραφή  Αποθηκευμένης Καταγγελίας',
                    listeners: {
                        click: {
                            fn: 'onDelete_COMPANY_COMPLAINT',
                            scope: 'controller'
                        }
                    }
                },
                {
                    xtype: 'button',
                    itemId: 'savecomplaintbutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf0c7@FontAwesome',
                    text: 'Αποθήκευση',
                    listeners: {
                        click: {
                            fn: 'onSave_COMPANY_COMPLAINT',
                            scope: 'controller'
                        }
                    }
                },
                {
                    xtype: 'button',
                    itemId: 'submitcomplaintbutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf1d8@FontAwesome',
                    text: 'Υποβολή',
                    listeners: {
                        click: {
                            fn: 'onSubmit_COMPANY_COMPLAINT',
                            scope: 'controller'
                        }
                    }
                }
            ],
            listeners: {
                beforehide: 'onCompl_save_submit_toolbarBeforeHide'
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

    onAddrPSelect211: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('compAddrPe');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeExpand211: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
        queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('compAddrP').getValue()+" ";
    },

    onAddrPeSelect211: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('compAddrD');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeDirtyChange211: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalPe/"+field.getValue()+" ";
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onAddrDExpand211: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalD");
        queryPlan.combo.getStore().getProxy().url = "/TKalD/search/findByEnotCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('compAddrPe').getValue()+" ";
    },

    onAddrDSelect211: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('compAddrK');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrDDirtyChange211: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalD/"+field.getValue()+" ";
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onAddrKExpand211: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalK");
        queryPlan.combo.getStore().getProxy().url = "/TKalK/search/findByDimosCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('compAddrD').getValue();
    },

    onAddrKDirtyChange211: function(field, isDirty, eOpts) {
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

    onBranch1Select: function(combo, records, eOpts) {
        var formToFill = combo.up('form').getForm();

        //DISABLE BELOW PART TO STOP AUTOFILL
        value = combo.getValue();
        selectedrec = combo.findRecordByValue(value);

        formToFill.findField('inspAddrK').suspendEvent('dirtyChange');
        formToFill.findField('inspAddrPe').suspendEvent('dirtyChange');
        formToFill.findField('inspAddrD').suspendEvent('dirtyChange');

        combo.autofillCompFields(formToFill, selectedrec);


        formToFill.findField('inspAddrK').resumeEvent('dirtyChange');
        formToFill.findField('inspAddrPe').resumeEvent('dirtyChange');
        formToFill.findField('inspAddrD').resumeEvent('dirtyChange');

    },

    onAddrPSelect212: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('inspAddrPe');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeExpand212: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
        queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('inspAddrP').getValue()+" ";
    },

    onAddrPeSelect212: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('inspAddrD');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeDirtyChange212: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalPe/"+field.getValue()+" ";
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onAddrDExpand212: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalD");
        queryPlan.combo.getStore().getProxy().url = "/TKalD/search/findByEnotCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('inspAddrPe').getValue()+" ";
    },

    onAddrDSelect212: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('inspAddrK');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrDDirtyChange212: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalD/"+field.getValue()+" ";
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onAddrKExpand212: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalK");
        queryPlan.combo.getStore().getProxy().url = "/TKalK/search/findByDimosCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('inspAddrD').getValue();
    },

    onAddrKDirtyChange212: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalK/"+field.getValue();
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onDeleteClick: function(button, e, eOpts) {
        button.up('form').getForm().findField('deletedfile').setValue(true);
        button.up('container').hide();
        button.up('form').getForm().findField('file').focus();
    },

    onViewDocClick: function(button, e, eOpts) {
        var url = "/getDocument?docId="+button.up('form').getForm().findField('docIdAttached').getValue();
        var win = window.open(url, '_blank');
        win.focus();
    },

    onWindowBeforeDestroy: function(component, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.company.ComplaintsPanel');
        center.add(viewsub);
    },

    onCompl_save_submit_toolbarBeforeHide: function(component, eOpts) {
        component.getComponent('deletecomplaintbutton').destroy();
        component.getComponent('savecomplaintbutton').destroy();
        component.getComponent('submitcomplaintbutton').destroy();
    }

});