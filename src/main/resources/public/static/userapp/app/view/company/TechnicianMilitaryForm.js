/*
 * File: app/view/company/GenRequestForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianMilitaryForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companytechnicianmilitaryform',

    requires: [
        'MyApp.view.company.TechnicianMilitaryFormViewModel',
        'MyApp.view.company.TechnicianMilitaryFormViewController',
        'MyApp.view.shared.PrintFormTool',
        'MyApp.view.shared.CloseFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.FieldSet',
        'Ext.form.field.Date',
        'Ext.form.field.Number',
        'Ext.form.field.ComboBox',
        'Ext.form.field.TextArea',
        'Ext.form.Label',
        'Ext.button.Button',
        'Ext.form.field.File',
        'Ext.toolbar.Toolbar',
        'Ext.panel.Tool'
    ],

    controller: 'companytechnicianmilitaryform',
    viewModel: {
        type: 'companytechnicianmilitaryform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    overflowY: 'scroll',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Φόρμα αναγγελίας Τεχνικών Ασφάλειας για ΈΝΟΠΛΕΣ ΔΥΝΑΜΕΙΣ',
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
            id: 'employeecomplaintform4',
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
                    flex: 1,
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
                            title: 'ΔΙΕΥΘΥΝΣΗ ΠΑΡΑΡΤΗΜΑΤΟΣ',
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
                                    maxLength: 100
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
                                },
                                {
                                    xtype: 'textareafield',
                                    anchor: '100%',
                                    fieldLabel: 'Περιγραφή ακριβούς ή ειδικού τόπου',
                                    labelWidth: 180,
                                    name: 'complAddressDesc',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    //allowBlank: false,
                                    //allowOnlyWhitespace: false,
                                    maxLength: 300
                                },
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            focusOnToFront: false,
                            padding: 10,
                            toFrontOnShow: false,
                            title: 'ΣΤΟΙΧΕΙΑ ΕΠΙΚΟΙΝΩΝΙΑΣ ΑΙΤΟΥΝΤΑ',
                            toggleOnTitleClick: false,
                            items: [
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Όνομα',
                                    labelWidth: 180,
                                    name: 'reprFirstname',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    //editable: false,
                                    maxLength: 128
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Επώνυμο',
                                    labelWidth: 180,
                                    name: 'reprLastname',
                                    //hidden: true,
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    //editable: false,
                                    maxLength: 128
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Σταθερό Τηλέφωνο',
                                    labelWidth: 180,
                                    name: 'reprPhone',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 10,
                                    vtype: 'telNumber'
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Κινητό Τηλέφωνο',
                                    labelWidth: 180,
                                    name: 'reprMobile',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 10,
                                    vtype: 'telNumber'
                                }
                                /*{
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    disabled: true,
                                    fieldLabel: 'Όνομα',
                                    labelWidth: 180,
                                    name: 'reprFirstname',
                                    hidden: true,
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    editable: false,
                                    maxLength: 128
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    disabled: true,
                                    fieldLabel: 'Επώνυμο',
                                    labelWidth: 180,
                                    name: 'reprLastname',
                                    hidden: true,
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    editable: false
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    disabled: true,
                                    fieldLabel: 'Όνομα Πατέρα',
                                    labelWidth: 180,
                                    name: 'reprFathername',
                                    hidden: true,
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    editable: false
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    disabled: true,
                                    fieldLabel: 'Όνομα Μητέρας',
                                    labelWidth: 180,
                                    name: 'reprMothername',
                                    hidden: true,
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    editable: false
                                },
                                {
                                    xtype: 'fieldset',
                                    focusOnToFront: false,
                                    toFrontOnShow: false,
                                    title: 'Διεύθυνση Επικοινωνίας',
                                    items: [
                                        {
                                            xtype: 'textareafield',
                                            anchor: '100%',
                                            fieldLabel: 'Οδός / Αριθμός',
                                            labelWidth: 180,
                                            name: 'reprAddr',
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
                                            name: 'reprAddrP',
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
                                            name: 'reprAddrPe',
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
                                            name: 'reprAddrD',
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
                                            name: 'reprAddrK',
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
                                            name: 'reprAddrTk',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            //   allowBlank: false,
                                            //  allowOnlyWhitespace: false,
                                            maxLength: 5,
                                            minLength: 5,
                                            vtype: 'Number'
                                        }
                                    ]
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Αρ. Δελτίου Ταυτότητας',
                                    labelWidth: 180,
                                    name: 'reprCardNumber',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 50
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'ΑΜΚΑ',
                                    labelWidth: 180,
                                    name: 'reprAmka',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 50
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'ΑΦΜ',
                                    labelWidth: 180,
                                    name: 'reprAfm',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                 maxLength: 9,
                                 minLength: 9,
                                 maskRe:/[0-9.]/
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Σταθερό Τηλέφωνο',
                                    labelWidth: 180,
                                    name: 'reprPhone',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 10,
                                    vtype: 'telNumber'
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Κινητό Τηλέφωνο',
                                    labelWidth: 180,
                                    name: 'reprMobile',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 10,
                                    vtype: 'telNumber'
                                }*/
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            focusOnToFront: false,
                            padding: 10,
                            toFrontOnShow: false,
                            title: 'ΣΤΟΙΧΕΙΑ ΤΕΧΝΙΚΟΥ ΑΣΦΑΛΕΙΑΣ',
                            toggleOnTitleClick: false,
                            items: [
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Όνομα',
                                    labelWidth: 180,
                                    name: 'ta_firstname',
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
                                    name: 'ta_lastname',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 128
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Πατρώνυμο',
                                    labelWidth: 180,
                                    name: 'ta_fathername',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 128
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Ιδιότητα Τ.Α.',
                                    labelWidth: 180,
                                    name: 'ta_type',
                                    validateOnChange: false,
                                    readOnly: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    editable: false,
                                    autoLoadOnValue: true,
                                    displayField: 'name',
                                    forceSelection: true,
                                    bind: {store: '{MILITARY_TYPE}'},
                                    valueField: 'abbr',
                                    listeners:
                                    {
                                        select: 'onTypeSelect'
                                    }
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Κατηγορία',
                                    labelWidth: 180,
                                    name: 'ta_categ',
                                    validateOnChange: false,
                                    readOnly: false,
                                    validateOnBlur: false,
                                    //allowBlank: false,
                                    //allowOnlyWhitespace: false,
                                    editable: false,
                                    autoLoadOnValue: true,
                                    displayField: 'name',
                                    forceSelection: true,
                                    hidden: true,
                                    bind: {store: '{MILITARY_CATEG}'},
                                    valueField: 'abbr'
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Κλάδος',
                                    labelWidth: 180,
                                    name: 'ta_branch',
                                    validateOnChange: false,
                                    readOnly: false,
                                    validateOnBlur: false,
                                    //allowBlank: false,
                                    //allowOnlyWhitespace: false,
                                    editable: false,
                                    autoLoadOnValue: true,
                                    displayField: 'name',
                                    forceSelection: true,
                                    hidden: true,
                                    bind: {store: '{MILITARY_BRANCH}'},
                                    valueField: 'abbr',
                                    listeners:{
                                        select: 'onEducLevelSelect'
                                    },
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Ειδικότητα/ες',
                                    labelWidth: 180,
                                    name: 'ta_speciality',
                                    disabled: true,
                                    hidden: true,
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    //allowBlank: false,
                                    //allowOnlyWhitespace: false,
                                    editable: false,
                                    autoLoadOnValue: true,
                                    multiSelect: true,
                                    forceSelection: true,
                                    displayField: 'description',
                                    queryParam: 'spTaspEdulvl',
                                    store: 'technician.SPECIALITY',
                                    valueField: 'abbr',
                                    listeners: {
                                        select: 'onSpecialitySelect',
                                        beforequery: 'onSpecialityExpand',
                                        dirtychange: {
                                            fn: 'onSpecialityDirtyChange',
                                            single: true
                                        }
                                    }
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    hidden: true,
                                    fieldLabel: '',
                                    hideEmptyLabel: false,
                                    labelWidth: 180,
                                    name: 'ta_specialityOther',
                                    validateOnChange: false,
                                    value: '',
                                    validateOnBlur: false,
                                    emptyText: 'Λεκτικό Ειδικότητας'
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    hidden: true,
                                    fieldLabel: 'Βαθμός',
                                    labelWidth: 180,
                                    name: 'ta_rank',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 128
                                },
                            ]
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            fieldLabel: 'Τύπος Αίτησης',
                            labelWidth: 180,
                            name: 'reqSubject',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            editable: false,
                            autoLoadOnValue: true,
                            displayField: 'descr',
                            forceSelection: true,
                            valueField: 'abbr',
                            value: '2',
                            readOnly: true,
                            bind: {
                                store: '{GENREQUEST_SUBJECTS}'
                            }
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            fieldLabel: 'Αντικείμενο Αίτησης',
                            labelWidth: 180,
                            name: 'requestTypeId',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            editable: false,
                            autoLoadOnValue: true,
                            displayField: 'spGreqTitle',
                            forceSelection: true,
                            valueField: 'abbr',
                            value: '25',
                            readOnly: true,
                            bind: {
                                store: '{GENREQUEST_TYPES}'
                            },
                            /*listeners: {
                                beforeselect: 'onReqTypeBeforeSelect',
                                select: 'onReqTypeSelect',
                                expand: 'onReqTypeExpand'
                            }*/
                        },
                        {
                            xtype: 'container',
                            hidden: true,
                            itemId: 'infopart',
                            layout: {
                                type: 'hbox',
                                align: 'stretch'
                            },
                            items: [
                                {
                                    xtype: 'label',
                                    html: '<img height="32px"  src="static/userapp/resources/info.png"/>',
                                    width: 32
                                },
                                {
                                    xtype: 'container',
                                    layout: {
                                        type: 'vbox',
                                        align: 'stretch',
                                        padding: 5
                                    },
                                    items: [
                                        {
                                            xtype: 'container',
                                            layout: {
                                                type: 'hbox',
                                                align: 'stretch'
                                            },
                                            items: [
                                                {
                                                    xtype: 'label',
                                                    height: '60',
                                                    padding: '5 0 0 0',
                                                    text: 'Προβολή οδηγιών συμπλήρωσης αίτησης',
                                                    width: 668,
                                                },
                                                /*{
                                                 xtype: 'button',
                                                 focusCls: ' ',
                                                 border: 0,
                                                 frame: false,
                                                 height: 24,
                                                 style: 'background-color:transparent;',
                                                 iconCls: 'downloadme',
                                                 tooltip: 'Προβολή Αρχείου',
                                                 listeners: {
                                                 click: 'onViewDoc1Click'
                                                 }
                                                 }*/
                                            ]
                                        },
                                        {
                                            xtype: 'container',
                                            layout: {
                                                type: 'hbox',
                                                align: 'stretch'
                                            },
                                            items: [
                                                {
                                                    xtype: 'label',
                                                    height: 24,
                                                    padding: '5 0 0 0',
                                                    text: 'Aπαιτούμενο επισυναπτόμενο έντυπο'
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
                                                        click: 'onViewDoc2Click'
                                                    }
                                                }
                                            ]
                                        }
                                    ]
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
                            id: 'compgenreqfile',
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
                                    //allowBlank: false,
                                    //allowOnlyWhitespace: false,
                                    //blankText: 'Επισυνάψτε την συμπληρωμένη αίτηση',
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
                        },
                        /*{
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Τίτλος διαγωνισμού',
                            labelWidth: 180,
                            name: 'contestTitle',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 1000
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Αριθμός διακήρυξης',
                            labelWidth: 180,
                            name: 'contestNum',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 1000
                        },
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            width: 150,
                            fieldLabel: 'Ημ. λήξης υποβολής προσφορών',
                            labelWidth: 180,
                            name: 'contestExpiryDateView',
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            format: 'd-m-Y'
                        },
                        {
                            xtype: 'hiddenfield',
                            anchor: '100%',
                            fieldLabel: 'Ημ. λήξης υποβολής προσφορών',
                            labelWidth: 180,
                            name: 'contestExpiryDate',
                            validateOnChange: false
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Φορέας διαγωνισμού',
                            labelWidth: 180,
                            name: 'contestCarrier',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 1000
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            fieldLabel: 'Πειρεχόμενο Αίτησης',
                            labelWidth: 180,
                            name: 'descr',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 2000,
                            readOnly: true,
                            value: 'Παρακαλώ να μου χορηγήσετε πιστοποιητικό από το οποίο να προκύπτουν οι πράξεις επιβολής προστίμου που έχουν εκδοθεί σε βάρος μου σε χρονικό διάστημα δύο ετών πριν από την ημερομηνία λήξης της προθεσμίας υποβολής προσφοράς, σύμφωνα με το άρθρο 73 του Ν.4412/2016'
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
                            id: 'compgenreqfile',
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
                                    //allowBlank: false,
                                    //allowOnlyWhitespace: false,
                                    //blankText: 'Επισυνάψτε την συμπληρωμένη αίτηση',
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
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            margin: '5 0 0 0',
                            fieldLabel: 'Παρατηρήσεις',
                            labelWidth: 180,
                            name: 'notes',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 2000
                        }*/
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

    onTypeSelect: function(combo, records, eOpts) {
        var scrollpos=combo.up('window').getScrollY();
        if (parseInt(records[0].get('abbr')) == 0){ // ΕΝΣΤΟΛΟΣ
            combo.up('form').getForm().findField('ta_categ').clearValue();
            combo.up('form').getForm().findField('ta_rank').setValue();
            combo.up('form').getForm().findField('ta_categ').show();
            combo.up('form').getForm().findField('ta_categ').allowBlank = false;
            combo.up('form').getForm().findField('ta_categ').allowOnlyWhitespace = false;
            combo.up('form').getForm().findField('ta_rank').show();
            combo.up('form').getForm().findField('ta_rank').allowBlank = false;
            combo.up('form').getForm().findField('ta_rank').allowOnlyWhitespace = false;
            combo.up('form').getForm().findField('ta_speciality').hide();
            combo.up('form').getForm().findField('ta_speciality').allowBlank = true;
            combo.up('form').getForm().findField('ta_speciality').allowOnlyWhitespace = true;
            combo.up('form').getForm().findField('ta_branch').hide();
            combo.up('form').getForm().findField('ta_branch').allowBlank = true;
            combo.up('form').getForm().findField('ta_branch').allowOnlyWhitespace = true;
            combo.up('form').getForm().findField('ta_specialityOther').setValue();
            combo.up('form').getForm().findField('ta_specialityOther').hide();
            combo.up('form').getForm().findField('ta_specialityOther').allowBlank = true;
            combo.up('form').getForm().findField('ta_specialityOther').allowOnlyWhitespace = true;
        }
        else if (parseInt(records[0].get('abbr')) == 1){ // ΠΟΛΙΤΙΚΟ ΠΡΟΣΩΠΟ
            combo.up('form').getForm().findField('ta_categ').hide();
            combo.up('form').getForm().findField('ta_categ').allowBlank = true;
            combo.up('form').getForm().findField('ta_categ').allowOnlyWhitespace = true;
            combo.up('form').getForm().findField('ta_rank').hide();
            combo.up('form').getForm().findField('ta_rank').allowBlank = true;
            combo.up('form').getForm().findField('ta_rank').allowOnlyWhitespace = true;
            combo.up('form').getForm().findField('ta_speciality').clearValue();
            combo.up('form').getForm().findField('ta_branch').clearValue();
            combo.up('form').getForm().findField('ta_speciality').show();
            combo.up('form').getForm().findField('ta_speciality').allowBlank = false;
            combo.up('form').getForm().findField('ta_speciality').allowOnlyWhitespace = false;
            combo.up('form').getForm().findField('ta_branch').show();
            combo.up('form').getForm().findField('ta_branch').allowBlank = false;
            combo.up('form').getForm().findField('ta_branch').allowOnlyWhitespace = false;
            combo.up('form').getForm().findField('ta_specialityOther').setValue();
            combo.up('form').getForm().findField('ta_specialityOther').hide();
            combo.up('form').getForm().findField('ta_specialityOther').allowBlank = true;
            combo.up('form').getForm().findField('ta_specialityOther').allowOnlyWhitespace = true;
        }
        combo.up('window').scrollTo(0, scrollpos);
    },

    onEducLevelSelect: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('ta_speciality');
        getnext.clearValue();
        getnext.enable();
        combo.up('form').getForm().findField('ta_specialityOther').setValue();
        combo.up('form').getForm().findField('ta_specialityOther').hide();
        combo.up('form').getForm().findField('ta_specialityOther').allowBlank=true;
        combo.up('form').getForm().findField('ta_specialityOther').allowOnlyWhitespace=true;
    },

    onSpecialitySelect: function(combo, records, eOpts) {
        var scrollpos=combo.up('window').getScrollY();
        if (parseInt(records[0].get('abbr'))===99){
            combo.up('form').getForm().findField('ta_specialityOther').show();
            combo.up('form').getForm().findField('ta_specialityOther').allowBlank=false;
            combo.up('form').getForm().findField('ta_specialityOther').allowOnlyWhitespace=false;
        }
        else{
            combo.up('form').getForm().findField('ta_specialityOther').setValue();
            combo.up('form').getForm().findField('ta_specialityOther').hide();
            combo.up('form').getForm().findField('ta_specialityOther').allowBlank=true;
            combo.up('form').getForm().findField('ta_specialityOther').allowOnlyWhitespace=true;
        }
        combo.up('window').scrollTo(0, scrollpos);
    },

    onSpecialityExpand: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.taSpeciality");
        queryPlan.combo.getStore().getProxy().url = "/taSpeciality/search/findByEducLevel";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('ta_branch').getValue();
    },

    onSpecialityDirtyChange: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/taSpeciality/"+field.getValue();
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
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
            id: 'compgenreq_save_submit_toolbar',
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
                    itemId: 'deletegenrequestbutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf00d@FontAwesome',
                    text: 'Διαγραφή',
                    tooltip: 'Διαγραφή  Αποθηκευμένης Αίτησης',
                    listeners: {
                        click: {
                            fn: 'onDelete_COMPANY_TECHNICIAN_MILITARY',
                            scope: 'controller'
                        }
                    }
                },
                {
                    xtype: 'button',
                    itemId: 'savegenrequestbutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf0c7@FontAwesome',
                    text: 'Αποθήκευση',
                    listeners: {
                        click: {
                            fn: 'onSave_COMPANY_TECHNICIAN_MILITARY',
                            scope: 'controller'
                        }
                    }
                },
                {
                    xtype: 'button',
                    itemId: 'submitgenrequestbutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf1d8@FontAwesome',
                    text: 'Υποβολή',
                    listeners: {
                        click: {
                            fn: 'onSubmit_COMPANY_TECHNICIAN_MILITARY',
                            scope: 'controller'
                        }
                    }
                }
            ],
            listeners: {
                beforehide: 'onGenReq_save_submit_toolbarBeforeHide'
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

    onAddrPSelect31: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('reprAddrPe');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeExpand31: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
        queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('reprAddrP').getValue()+" ";
    },

    onAddrPeSelect31: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('reprAddrD');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeDirtyChange31: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalPe/"+field.getValue()+" ";
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onAddrDExpand31: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalD");
        queryPlan.combo.getStore().getProxy().url = "/TKalD/search/findByEnotCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('reprAddrPe').getValue()+" ";
    },

    onAddrDSelect31: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('reprAddrK');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrDDirtyChange31: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalD/"+field.getValue()+" ";
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onAddrKExpand31: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalK");
        queryPlan.combo.getStore().getProxy().url = "/TKalK/search/findByDimosCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('reprAddrD').getValue();
    },

    onAddrKDirtyChange31: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalK/"+field.getValue();
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    /*onReqTypeBeforeSelect: function(combo, record, index, eOpts) {
        var scrollpos = combo.up('window').getScrollY();
        var vfc = Ext.ComponentQuery.query('#infopart')[0];
        if (record.get('spGreqTemplDocId')==="null"){
            vfc.items.getAt(1).items.getAt(0).hide();
        }
        if (record.get('spGreqTemplDocId')==="null"){
            vfc.items.getAt(1).items.getAt(1).hide();
        }
        vfc.items.items[1].items.items[0].items.items[0].setText(record.get('spGreqHelpText'));
        vfc.show();
        combo.up('window').scrollTo(0, scrollpos);
    },*/

    /*onReqTypeSelect: function(combo, records, eOpts) {
         var scrollpos = combo.up('window').getScrollY();
         var vfc = combo.up('form').getForm().findField("descrBAP");
         if(combo.getValue()==="9"){
         vfc.show();
         vfc.allowBlank=false;
         vfc.allowOnlyWhitespace=false;
         }
         else{
         vfc.setValue();
         vfc.hide();
         vfc.allowBlank=true;
         vfc.allowOnlyWhitespace=true;
         }
         combo.up('window').scrollTo(0, scrollpos);
    },*/

    /*onReqTypeExpand: function(field, eOpts) {
        if(field.up('form').getForm().findField('reqSubject').getValue()===null){
            Ext.Msg.alert("Προσοχή!","Επιλέξτε Τύπο Αίτησης.");
        }
        else if(field.up('form').getForm().findField('reqSubject').getValue()==="1"){ //KE
            field.store.proxy.url="/vwGenreq/search/findAllKeBus";
            field.store.reload();
        }
        else if(field.up('form').getForm().findField('reqSubject').getValue()==="2"){ //TE
            field.store.proxy.url="/vwGenreq/search/findAllTeBus";
            field.store.reload();
        }
    },*/

    onViewDoc1Click: function(button, e, eOpts) {
        var url = "/getDocument?docId="+button.up('form').getForm().findField('requestTypeId').getSelectedRecord().get('spGreqHelplDocId');
        var win = window.open(url, '_blank');
        win.focus();
    },

    onViewDoc2Click: function(button, e, eOpts) {
        var url = "/getDocument?docId="+button.up('form').getForm().findField('requestTypeId').getSelectedRecord().get('spGreqTemplDocId');
        var win = window.open(url, '_blank');
        win.focus();
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
        var viewsub = Ext.create('MyApp.view.company.TechnicianMilitaryPanel');
        center.add(viewsub);
    },

    onGenReq_save_submit_toolbarBeforeHide: function(component, eOpts) {
        component.getComponent('deletegenrequestbutton').destroy();
        component.getComponent('savegenrequestbutton').destroy();
        component.getComponent('submitgenrequestbutton').destroy();
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

});