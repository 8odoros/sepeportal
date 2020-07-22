/*
 * File: app/view/employee/GenRequestForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.employee.GenRequestForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.employeegenrequestform',

    requires: [
        'MyApp.view.employee.GenRequestFormViewModel',
        'MyApp.view.employee.GenRequestFormViewController',
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
        'Ext.form.field.Display',
        'Ext.button.Button',
        'Ext.form.field.File',
        'Ext.toolbar.Toolbar',
        'Ext.panel.Tool'
    ],

    controller: 'employeegenrequestform',
    viewModel: {
        type: 'employeegenrequestform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    overflowY: 'scroll',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Φόρμα Αίτησης Βεβαιώσεων/Χορήγησης αντιγράφων από το αρχείο υπηρεσιών του ΣΕΠΕ',
    //modal: true,
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'form',
            id: 'employeecomplaintform2',
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
                    title: 'ΣΤΟΙΧΕΙΑ ΑΙΤΗΣΗΣ',
                    items: [
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            cls: 'x-item-disabled',
                            disabled: true,
                            fieldLabel: 'Αρμόδιo Τμήμα',
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
                            xtype: 'fieldset',
                            focusOnToFront: false,
                            padding: 10,
                            toFrontOnShow: false,
                            title: 'ΣΤΟΙΧΕΙΑ ΑΙΤΟΥΝΤΑ',
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
                                    editable: false,
                                    emptyText: '',
                                    maxLength: 50
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
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    cls: 'x-item-disabled',
                                    fieldLabel: 'Όνομα Πατέρα',
                                    labelWidth: 180,
                                    name: 'empFathername',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    editable: false
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    cls: 'x-item-disabled',
                                    fieldLabel: 'Όνομα Μητέρας',
                                    labelWidth: 180,
                                    name: 'empMothername',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    editable: false
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
                                                select: 'onAddrPSelect31'
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
                            toFrontOnShow: false,
                            title: 'Διεύθυνση-Περιοχή που αφορά το αίτημα',
                            items: [
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Περιφέρεια',
                                    labelWidth: 180,
                                    name: 'reqAddrP',
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
                                    fieldLabel: 'Περιφερειακή Ενότητα',
                                    labelWidth: 180,
                                    name: 'reqAddrPe',
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
                                    name: 'reqAddrD',
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
                                    fieldLabel: 'Δημοτική Ενότητα',
                                    labelWidth: 180,
                                    name: 'reqAddrK',
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
                                }
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
                            bind: {
                                store: '{GENREQUEST_SUBJECTS}'
                            },
                            listeners: {
                                change: 'onreqSubjectChange'
                            }
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            fieldLabel: 'Αντικείμενο Αίτησης',
                            labelWidth: 180,
                            name: 'reqType',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            editable: false,
                            autoLoadOnValue: true,
                            displayField: 'spGreqTitle',
                            forceSelection: true,
                            valueField: 'abbr',
                            bind: {
                                store: '{GENREQUEST_TYPES}'
                            },
                            listeners: {
                                beforeselect: 'onReqTypeBeforeSelect',
                                expand: 'onReqTypeExpand'
                            }
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
                                    html: '<img height="32px"  src="/portal/static/userapp/resources/info.png"/>',
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
                                            xtype: 'displayfield',
                                            flex: 1,
                                            padding: '',
                                            fieldLabel: '',
                                            name: 'infotext',
                                            value: 'Display Field'
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
                                                    text: 'Προβολή εκτυπώσιμης αίτησης',
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
                                                        click: 'onViewDocClick1'
                                                    }
                                                }*/
                                            ]
                                        }
                                    ]
                                }
                            ]
                        },
                        {
                            xtype: 'container',
                            itemId: 'infopart2',
                            layout: {
                                type: 'hbox',
                                align: 'stretch'
                            },
                            items: [
                                {
                                    xtype: 'label',
                                    html: '<img height="32px"  src="/portal/static/userapp/resources/info.png"/>',
                                    width: 32
                                },
                                {
                                    xtype: 'label',
                                    height: 24,
                                    padding: '5 0 0 0',
                                    text: 'Παρακαλούμε αναφέρατε στο πεδίο "Περιεχόμενο Αίτησης" τα ακριβή έγγραφα στα οποία επιθυμείτε να έχετε πρόσβαση'
                                }
                            ]
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            fieldLabel: 'Περιεχόμενο Αίτησης',
                            labelWidth: 180,
                            name: 'reqDescription',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 3000,
                            validateBlank: true
                        },
                        {
                            xtype: 'hiddenfield',
                            anchor: '100%',
                            fieldLabel: 'Επισυναπτόμενο Αρχείο',
                            labelWidth: 180,
                            msgTarget: 'under',
                            name: 'attachedDocId',
                            validateOnChange: false
                        },
                        {
                            xtype: 'form',
                            id: 'genreqfile',
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
                                    allowBlank: true,
                                    allowOnlyWhitespace: false,
                                    blankText: 'Επισυνάψτε την συμπληρωμένη αίτηση',
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
                            name: 'reqRemarks',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 1000
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
            id: 'empgenreq_save_submit_toolbar',
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
                            fn: 'onDelete_EMPLOYEE_GENREQUEST',
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
                            fn: 'onSave_EMPLOYEE_GENREQUEST',
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
                            fn: 'onSubmit_EMPLOYEE_GENREQUEST',
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

    onreqSubjectChange: function(field, newValue, oldValue, eOpts) {
        if(newValue!==oldValue && oldValue!==null){
            field.up('form').getForm().findField('reqType').setValue(null);
            Ext.ComponentQuery.query('#infopart')[0].hide();
        }
    },

    onAddrPSelect11: function(combo, records, eOpts) {
                var getnext = combo.up('form').getForm().findField('reqAddrPe');
                getnext.clearValue();
                getnext.enable();
    },

    onAddrPeExpand11: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
        queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('reqAddrP').getValue()+" ";
    },

    onAddrPeSelect11: function(combo, records, eOpts) {
                var getnext = combo.up('form').getForm().findField('reqAddrD');
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
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('reqAddrPe').getValue()+" ";
    },

    onAddrDSelect11: function(combo, records, eOpts) {
                var getnext = combo.up('form').getForm().findField('reqAddrK');
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
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('reqAddrD').getValue();
    },

    onAddrKDirtyChange11: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalK/"+field.getValue();
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        }
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
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalPe/"+field.getValue()+" ";
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
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
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalD/"+field.getValue()+" ";
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        }
    },

    onAddrKExpand31: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalK");
        queryPlan.combo.getStore().getProxy().url = "/TKalK/search/findByDimosCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('empAddrD').getValue();
    },

    onAddrKDirtyChange31: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalK/"+field.getValue();
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        }
    },

    onReqTypeBeforeSelect: function(combo, record, index, eOpts) {
        var scrollpos = combo.up('window').getScrollY();
        var vfc = Ext.ComponentQuery.query('#infopart')[0];
        vfc.items.getAt(1).items.getAt(0).setValue(record.get('spGreqHelpText'));
        if (record.get('spGreqTemplDocId')===null){
            vfc.items.getAt(1).items.getAt(1).hide();
        }
        vfc.show();
        combo.up('window').scrollTo(0, scrollpos);
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

    onReqTypeExpand: function(field, eOpts) {
        if(field.up('form').getForm().findField('reqSubject').getValue()===null){
            Ext.Msg.alert("Προσοχή!","Επιλέξτε Τύπο Αίτησης.");
        }
        else if(field.up('form').getForm().findField('reqSubject').getValue()==="1"){ //KE
            field.store.proxy.url="/vwGenreq/search/findAllKeEmp";
            field.store.reload();
        }
        else if(field.up('form').getForm().findField('reqSubject').getValue()==="2"){ //TE
            field.store.proxy.url="/vwGenreq/search/findAllTeEmp";
            field.store.reload();
        }
    },

    onViewDocClick1: function(button, e, eOpts) {
        var url = "/getDocument?docId="+button.up('form').getForm().findField('reqType').getSelectedRecord().get('spGreqTemplDocId');
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
        var view=Ext.getCmp('employeemainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.employee.GenRequestsPanel');
        center.add(viewsub);
    },

    onGenReq_save_submit_toolbarBeforeHide: function(component, eOpts) {
        component.getComponent('deletegenrequestbutton').destroy();
        component.getComponent('savegenrequestbutton').destroy();
        component.getComponent('submitgenrequestbutton').destroy();
    }

});