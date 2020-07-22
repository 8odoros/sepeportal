/*
 * File: app/view/anonymous/ComplaintForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.anonymous.ComplaintForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.anonymouscomplaintform',

    requires: [
        'MyApp.view.anonymous.ComplaintFormViewModel',
        'MyApp.view.anonymous.ComplaintFormViewController',
        'MyApp.view.shared.PrintFormTool',
        'MyApp.view.shared.CloseFormTool',
        'Ext.form.Panel',
        'Ext.form.FieldSet',
        'Ext.form.field.Hidden',
        'Ext.form.field.Date',
        'Ext.form.field.Number',
        'Ext.form.field.TextArea',
        'Ext.form.field.ComboBox',
        'Ext.form.Label',
        'Ext.form.RadioGroup',
        'Ext.form.field.Radio',
        'Ext.form.field.File',
        'Ext.button.Button',
        'Ext.toolbar.Toolbar',
        'Ext.panel.Tool'
    ],

    controller: 'anonymouscomplaintform',
    viewModel: {
        type: 'anonymouscomplaintform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    overflowY: 'scroll',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Φόρμα Καταγγελίας Μη Εγγεγραμμένου Χρήστη',
    //modal: true,
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'form',
            id: 'publicusercomplaintform',
            padding: 15,
            title: '',
            layout: {
                type: 'vbox',
                align: 'stretch'
            },
            items: [
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
                            xtype: 'textfield',
                            anchor: '100%',
                            cls: 'x-item-disabled',
                            disabled: true,
                            fieldLabel: 'Αρμόδια Διεύθυνση/Τμήμα ΣΕΠΕ',
                            labelWidth: 180,
                            name: 'sepeDept',
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            hideTrigger: true
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
                            xtype: 'hiddenfield',
                            anchor: '100%',
                            fieldLabel: 'Τύπος Καταγγελίας',
                            labelWidth: 180,
                            name: 'complIsAnonymous',
                            validateOnChange: false,
                            value: 2
                        },
                        {
                            xtype: 'fieldset',
                            focusOnToFront: false,
                            padding: 10,
                            toFrontOnShow: false,
                            title: 'ΣΤΟΙΧΕΙΑ ΚΑΤΑΓΓΕΛΟΝΤΟΣ',
                            items: [
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Όνομα',
                                    labelWidth: 180,
                                    name: 'empFirstname',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    emptyText: 'Αφήστε κενό για ανωνυμία',
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
                                    emptyText: 'Αφήστε κενό για ανωνυμία',
                                    maxLength: 50
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Τηλέφωνο Επικοινωνίας',
                                    labelWidth: 180,
                                    name: 'empPhone',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    emptyText: 'Αφήστε κενό για ανωνυμία',
                                    maxLength: 10,
                                    vtype: 'telNumber'
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    hidden: true,
                                    fieldLabel: 'Διεύθυνση',
                                    labelWidth: 180,
                                    name: 'empAddr',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    emptyText: 'Αφήστε κενό για ανωνυμία',
                                    maxLength: 100
                                }
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            focusOnToFront: false,
                            padding: 10,
                            toFrontOnShow: false,
                            title: 'ΣΤΟΙΧΕΙΑ ΕΡΓΟΔΟΤΗ / ΕΠΙΧΕΙΡΗΣΗΣ',
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
                                    vtype: 'Number'
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Επωνυμία Εργοδότη/Επιχείρησης',
                                    labelWidth: 180,
                                    name: 'compName',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 300
                                },
                                {
                                    xtype: 'fieldset',
                                    focusOnToFront: false,
                                    toFrontOnShow: false,
                                    title: 'ΔΙΕΥΘΥΝΣΗ ΕΡΓΟΔΟΤΗ / ΕΠΙΧΕΙΡΗΣΗΣ',
                                    items: [
                                        {
                                            xtype: 'textareafield',
                                            anchor: '100%',
                                            fieldLabel: 'Οδός / Αριθμός',
                                            labelWidth: 180,
                                            name: 'compAddr',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            maxLength: 50
                                        },
                                        {
                                            xtype: 'combobox',
                                            anchor: '100%',
                                            fieldLabel: 'Περιφέρεια',
                                            labelWidth: 180,
                                            name: 'compAddrP',
                                            validateOnChange: false,
                                            validateOnBlur: false,
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
                                            fieldLabel: 'Νομός',
                                            labelWidth: 180,
                                            name: 'compAddrPe',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            editable: false,
                                            anyMatch: true,
                                            displayField: 'descr',
                                            queryParam: 'perifCode',
                                            store: 'address1.ADDR_Pe1',
                                            valueField: 'abbr',
                                            listeners: {
                                                beforequery: 'onAddrPeExpand1',
                                                select: 'onAddrPeSelect1'
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
                                            editable: false,
                                            displayField: 'dimosDescr',
                                            queryParam: 'enotCode',
                                            store: 'address1.ADDR_D1',
                                            valueField: 'abbr',
                                            listeners: {
                                                beforequery: 'onAddrDExpand1',
                                                select: 'onAddrDSelect1'
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
                                            editable: false,
                                            displayField: 'koinDescr',
                                            queryParam: 'dimosCode',
                                            store: 'address1.ADDR_K1',
                                            valueField: 'abbr',
                                            listeners: {
                                                beforequery: 'onAddrKExpand1'
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
                                            maxLength: 5,
                                            minLength: 5,
                                            vtype: 'Number'
                                        }
                                    ]
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Τηλέφωνο Εργοδότη/Επιχείρησης',
                                    labelWidth: 180,
                                    name: 'compPhone',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 10,
                                    vtype: 'telNumber'
                                },
                                {
                                    xtype: 'fieldset',
                                    focusOnToFront: false,
                                    toFrontOnShow: false,
                                    items: [
                                        {
                                            xtype: 'label',
                                            baseCls: 'x-form-item-label',
                                            text: 'Αντικείμενο Εργασιών Εργοδότη/Επιχείρησης:'
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
                                            allowBlank: false,
                                            allowOnlyWhitespace: false,
                                            editable: false,
                                            displayField: 'spRtstackLevel2Desc',
                                            queryParam: 'level1',
                                            store: 'compScope.rtstakLevel2',
                                            valueField: 'abbr',
                                            listeners: {
                                                beforequery: 'onCompScopeL2Expand',
                                                select: 'onCompScopeL2Select'
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
                                            allowBlank: false,
                                            allowOnlyWhitespace: false,
                                            editable: false,
                                            displayField: 'spRtstackLevel3Desc',
                                            queryParam: 'level2',
                                            store: 'compScope.rtstakLevel3',
                                            valueField: 'abbr',
                                            listeners: {
                                                beforequery: 'onCompScopeL3Expand',
                                                select: 'onCompScopeL3Select'
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
                                            allowBlank: false,
                                            allowOnlyWhitespace: false,
                                            editable: false,
                                            displayField: 'spRtstackLevel4Desc',
                                            queryParam: 'level3',
                                            store: 'compScope.rtstakLevel4',
                                            valueField: 'abbr',
                                            listeners: {
                                                beforequery: 'onCompScopeL4Expand',
                                                select: 'onCompScopeL4Select'
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
                                            allowBlank: false,
                                            allowOnlyWhitespace: false,
                                            editable: false,
                                            displayField: 'spRtstackLevel5Desc',
                                            queryParam: 'level4',
                                            store: 'compScope.rtstakLevel5',
                                            valueField: 'abbr',
                                            listeners: {
                                                beforequery: 'onCompScopeL5Expand'
                                            }
                                        }
                                    ]
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Αριθμός Απασχολούμενου Προσωπικού',
                                    labelWidth: 180,
                                    name: 'compEmpNum',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 50,
                                    vtype: 'Number'
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
                                    xtype: 'fieldset',
                                    focusOnToFront: false,
                                    toFrontOnShow: false,
                                    title: 'ΤΟΠΟΣ ΕΛΕΓΧΟΥ',
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
                                                select: 'onAddrPSelect'
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
                                            store: 'address.ADDR_Pe',
                                            valueField: 'abbr',
                                            listeners: {
                                                beforequery: 'onAddrPeExpand',
                                                select: 'onAddrPeSelect'
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
                                            store: 'address.ADDR_D',
                                            valueField: 'abbr',
                                            listeners: {
                                                beforequery: 'onAddrDExpand',
                                                select: 'onAddrDSelect'
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
                                            store: 'address.ADDR_K',
                                            valueField: 'abbr',
                                            listeners: {
                                                beforequery: 'onAddrKExpand'
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
                                            //allowBlank: false,
                                            //allowOnlyWhitespace: false,
                                            maxLength: 5,
                                            minLength: 5,
                                            vtype: 'Number'
                                        },
                                        {
                                            xtype: 'textareafield',
                                            anchor: '100%',
                                            hidden: true,
                                            fieldLabel: 'Ακριβής ή <br>Ειδικός Τόπος Ελέγχου<br>(Περιγραφή)',
                                            labelWidth: 180,
                                            name: 'inspAddrComment',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            maxLength: 100
                                        }
                                    ]
                                },
                                {
                                    xtype: 'textareafield',
                                    anchor: '100%',
                                    fieldLabel: 'Περιγραφή ακριβούς ή ειδικού τόπου ελέγχου',
                                    labelWidth: 180,
                                    name: 'complAddressDesc',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 300
                                }
                            ]
                        },
                        {
                            xtype: 'radiogroup',
                            anchor: '70%',
                            fieldLabel: 'Η Καταγγελία αφορά σε θέματα',
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
                                    inputValue: '1',
                                    listeners: {
                                        change: 'onComplInvolvesSelect'
                                    }
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'complInvolves',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Εργασιακών Σχέσεων',
                                    inputValue: '0',
                                    listeners: {
                                        change: 'onComplInvolvesSelect'
                                    }
                                }
                            ]
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            fieldLabel: 'Τύπος Καταγγελίας',
                            labelWidth: 180,
                            name: 'complMatter',
                            disabled: true,
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            blankText: 'Επιλέξτε ένα ή περισότερους τύπους',
                            editable: false,
                            autoLoadOnValue: true,
                            displayField: 'descr',
                            queryParam: 'spCmInvolves',
                            forceSelection: true,
                            multiSelect: true,
                            valueField: 'abbr',
                            bind: {
                                store: '{COMPL_MATTERS}'
                            },
                            listeners: {
                                beforequery: 'onComplMatterExpand',
                                /*dirtychange: {
                                 fn: 'onComplMatterDirtyChange',
                                 single: true
                                 }*/
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
                            xtype: 'textareafield',
                            anchor: '100%',
                            fieldLabel: 'Υπάρχει κάποιος ειδικός λόγος ο έλεγχος να πραγματοποιηθεί συγκεκριμένη ώρα',
                            labelAlign: 'top',
                            labelWidth: 180,
                            name: 'complInspTime',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 500
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
                            xtype: 'form',
                            id: 'complfile5',
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
            id: 'publiccompl_save_submit_toolbar',
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
                    itemId: 'submitcomplaintbutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf1d8@FontAwesome',
                    text: 'Υποβολή',
                    listeners: {
                        click: {
                            fn: 'onSubmit_PUBLICUSER_COMPLAINT',
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

    onAddrPSelect1: function(combo, records, eOpts) {
                var getnext = combo.up('form').getForm().findField('compAddrPe');
                getnext.clearValue();
                getnext.enable();
    },

    onAddrPeExpand1: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
        queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('compAddrP').getValue()+" ";
    },

    onAddrPeSelect1: function(combo, records, eOpts) {
                var getnext = combo.up('form').getForm().findField('compAddrD');
                getnext.clearValue();
                getnext.enable();
    },

    onAddrDExpand1: function(queryPlan, eOpts) {
            queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalD");
        queryPlan.combo.getStore().getProxy().url = "/TKalD/search/findByEnotCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('compAddrPe').getValue()+" ";
    },

    onAddrDSelect1: function(combo, records, eOpts) {
                var getnext = combo.up('form').getForm().findField('compAddrK');
                getnext.clearValue();
                getnext.enable();
    },

    onAddrKExpand1: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalK");
        queryPlan.combo.getStore().getProxy().url = "/TKalK/search/findByDimosCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('compAddrD').getValue();
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

    onCompScopeL5Expand: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.RtStakLevel5");
        queryPlan.combo.getStore().getProxy().url = "/RtStakLevel5/search/findLevel5";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('rtstakLevel4').getValue();
    },

    onAddrPSelect: function(combo, records, eOpts) {
                var getnext = combo.up('form').getForm().findField('inspAddrPe');
                getnext.clearValue();
                getnext.enable();
    },

    onAddrPeExpand: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
                queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
                queryPlan.query=queryPlan.combo.up('form').getForm().findField('inspAddrP').getValue()+" ";
    },

    onAddrPeSelect: function(combo, records, eOpts) {
                var getnext = combo.up('form').getForm().findField('inspAddrD');
                getnext.clearValue();
                getnext.enable();
    },

    onAddrDExpand: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalD");
        queryPlan.combo.getStore().getProxy().url = "/TKalD/search/findByEnotCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('inspAddrPe').getValue()+" ";
    },

    onAddrDSelect: function(combo, records, eOpts) {
                var getnext = combo.up('form').getForm().findField('inspAddrK');
                getnext.clearValue();
                getnext.enable();
    },

    onAddrKExpand: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalK");
        queryPlan.combo.getStore().getProxy().url = "/TKalK/search/findByDimosCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('inspAddrD').getValue();
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

    onComplInvolvesSelect: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('complMatter');
        getnext.clearValue();
        getnext.enable();
    },

    onComplMatterExpand: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.employeeComplaintMatter");
        queryPlan.combo.getStore().getProxy().url = "/employeeComplaintMatter/search/findByComplInvolves";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('complInvolves').getValue() ? 1 : 0;
    },

    /*onComplMatterDirtyChange: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/employeeComplaintMatter/"+field.getValue();
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },*/

});