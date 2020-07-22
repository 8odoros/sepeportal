/*
 * File: app/view/technician/RegRequestForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.technician.RegRequestForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.technicianregrequestform',

    requires: [
        'MyApp.view.technician.RegRequestFormViewModel',
        'MyApp.view.technician.RegRequestFormViewController',
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
        'Ext.form.field.File',
        'Ext.form.Label',
        'Ext.button.Button',
        'Ext.toolbar.Toolbar',
        'Ext.panel.Tool'
    ],

    controller: 'technicianregrequestform',
    viewModel: {
        type: 'technicianregrequestform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    overflowY: 'scroll',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Αίτηση Εγγραφής στο Μητρώο Τεχνικών Ασφάλειας',
    //modal: true,
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'form',
            id: 'doctorregrequestform1',
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
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            cls: 'x-item-disabled',
                            fieldLabel: 'Ώρα Αίτησης',
                            labelWidth: 180,
                            name: 'submitTime',
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            hideTrigger: true
                        }
                    ]
                },
                {
                    xtype: 'fieldset',
                    flex: 1,
                    title: 'ΚΑΤΑΣΤΑΣΗ',
                    items: [
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            cls: 'x-item-disabled',
                            fieldLabel: 'Κατάσταση Αίτησης',
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
                            fieldLabel: 'Έλεγχος Προφίλ από ΣΕΠΕ',
                            labelWidth: 180,
                            validateOnChange: false,
                            items: [
                                {
                                    xtype: 'radiofield',
                                    name: 'sepeProfilStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Ναι',
                                    inputValue: '1'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'sepeProfilStatus',
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
                            fieldLabel: 'Μήνυμα Απάντησης',
                            labelWidth: 180,
                            name: 'StatusMsg',
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
                    focusOnToFront: false,
                    padding: 10,
                    toFrontOnShow: false,
                    title: 'ΣΤΟΙΧΕΙΑ ΑΙΤΗΣΗΣ',
                    items: [
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            disabled: true,
                            fieldLabel: 'Αρμόδια Τμήμα ΤΕ',
                            labelWidth: 180,
                            name: 'department',
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            displayField: 'description',
                            store: 'shared.SEPE_DEPT',
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
                            xtype: 'fieldset',
                            focusOnToFront: false,
                            padding: 10,
                            toFrontOnShow: false,
                            title: 'ΠΡΟΣΩΠΙΚΑ ΣΤΟΙΧΕΙΑ',
                            items: [
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    disabled: true,
                                    fieldLabel: 'Όνομα',
                                    labelWidth: 180,
                                    name: 'firstname',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    editable: false,
                                    maxLength: 50
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    disabled: true,
                                    fieldLabel: 'Επώνυμο',
                                    labelWidth: 180,
                                    name: 'lastname',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    editable: false
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    disabled: true,
                                    fieldLabel: 'Πατρώνυμο',
                                    labelWidth: 180,
                                    name: 'fatherrname',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    editable: false
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    cls: 'x-item-disabled',
                                    fieldLabel: 'ΑΦΜ',
                                    labelWidth: 180,
                                    name: 'afm',
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
                                    cls: 'x-item-disabled',
                                    fieldLabel: 'ΑΜΚΑ',
                                    labelWidth: 180,
                                    name: 'amka',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    editable: false,
                                    maxLength: 50
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    cls: 'x-item-disabled',
                                    fieldLabel: 'Αρ. Αποδεικτικού Ταυτότητας',
                                    labelWidth: 180,
                                    name: 'cardNumber',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    editable: false,
                                    maxLength: 50
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'e-mail',
                                    labelWidth: 180,
                                    name: 'email',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    vtype: 'email',
                                    maxLength: 100
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Σταθερό Τηλέφωνο',
                                    labelWidth: 180,
                                    name: 'phone',
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
                                    name: 'mobile',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 10,
                                    minLength: 10,
                                    vtype: 'telNumber'
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Fax',
                                    labelWidth: 180,
                                    name: 'fax',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 10,
                                    vtype: 'telNumber'
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Αριθμός Μητρώου ΤΕΕ/ΕΤΕΜ',
                                    labelWidth: 180,
                                    name: 'teeNumber',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 100,
                                    //allowBlank: false,
                                    //allowOnlyWhitespace: false
                                }
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            focusOnToFront: false,
                            toFrontOnShow: false,
                            title: 'Διεύθυνση Κατοικίας ',
                            items: [
                                {
                                    xtype: 'textareafield',
                                    anchor: '100%',
                                    fieldLabel: 'Οδός / Αριθμός',
                                    labelWidth: 180,
                                    name: 'reqAddr',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 100
                                },
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
                                        select: 'onAddrPSelect1111'
                                    }
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    disabled: true,
                                    fieldLabel: 'Νομός',
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
                                    store: 'address.ADDR_Pe',
                                    valueField: 'abbr',
                                    listeners: {
                                        beforequery: 'onAddrPeExpand1111',
                                        select: 'onAddrPeSelect1111',
                                        dirtychange: {
                                            fn: 'onAddrPeDirtyChange1111',
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
                                    store: 'address.ADDR_D',
                                    valueField: 'abbr',
                                    listeners: {
                                        beforequery: 'onAddrDExpand1111',
                                        select: 'onAddrDSelect1111',
                                        dirtychange: {
                                            fn: 'onAddrDDirtyChange1111',
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
                                    name: 'reqAddrK',
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
                                        beforequery: 'onAddrKExpand1111',
                                        dirtychange: {
                                            fn: 'onAddrKDirtyChange1111',
                                            single: true
                                        }
                                    }
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Τ.Κ',
                                    labelWidth: 180,
                                    name: 'reqAddrTk',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 5,
                                    minLength: 5,
                                    vtype: 'Number'
                                }
                            ]
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            fieldLabel: 'Επίπεδο Σπουδών',
                            labelWidth: 180,
                            name: 'educationLevel',
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            autoLoadOnValue: true,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            displayField: 'description',
                            store: 'shared.EDUCATION_LEVEL',
                            valueField: 'abbr',
                            listeners: {
                                select: 'onEducLevelSelect'
                            }
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            fieldLabel: 'Ειδικότητα/ες',
                            labelWidth: 180,
                            name: 'speciality',
                            disabled: true,
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
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
                            name: 'specialityOther',
                            validateOnChange: false,
                            value: '',
                            validateOnBlur: false,
                            emptyText: 'Λεκτικό Ειδικότητας'
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            fieldLabel: 'Ιδιότητα Συνεργασίας',
                            labelWidth: 180,
                            name: 'cooperationType',
                            hidden: true,
                            validateOnChange: false,
                            validateOnBlur: false,
                            //allowBlank: false,
                            //allowOnlyWhitespace: false,
                            editable: false,
                            autoLoadOnValue: true,
                            displayField: 'description',
                            store: 'shared.COOPERATION_TYPE',
                            valueField: 'abbr'
                        },
                        {
                            xtype: 'radiogroup',
                            anchor: '70%',
                            fieldLabel: 'Δυνατότητα ανάληψης καθηκόντων σε ναυπηγοεπισκευαστικές εργασίες',
                            labelAlign: 'top',
                            labelWidth: 180,
                            validateOnChange: false,
                            allowBlank: false,
                            listeners: {
                                change: 'onShipyardDutiesYesSelectListener'
                            },
                            items: [
                                {
                                    xtype: 'radiofield',
                                    name: 'shipyardDuties',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Ναι',
                                    inputValue: '1'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'shipyardDuties',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Οχι',
                                    inputValue: '0'
                                }
                            ]
                        },
                        {
                            xtype: 'displayfield',
                            flex: 1,
                            padding: '',
                            fieldLabel: '<img height="20px" src="static/userapp/resources/info.png"/>',
                            labelSeparator: '',
                            labelWidth: 30,
                            labelStyle: 'margin-top:15px;',
                            height: 35,
                            name: 'infotext',
                            value: ' Απαιτείται η επισύναψη βεβαιώσεων σχετικής προϋπηρεσίας βάσει του Π.Δ.70/90.',
                            listeners: {
                                beforerender: 'onInfoTextBeforeRender'
                            }
                        },
                        {
                            xtype: 'checkboxgroup',
                            layout: 'anchor',
                            fieldLabel: 'Τίτλοι Σπουδών που έχετε καταχωρήσει',
                            name: 'diplomas',
                            labelAlign: 'top',
                            msgTarget: 'side',
                            validateOnChange: false,
                            allowBlank: false,
                            blankText: 'Επιλέξτε τουλάχιστο ένα τίτλο σπουδών να συνοδεύει την αίτηση σας',
                            listeners: {
                                added: 'onLoadAfterRender1'
                            }
                        },
                        {
                            xtype: 'radiogroup',
                            anchor: '70%',
                            fieldLabel: 'Επιμόρφωση 100 Ωρών',
                            labelWidth: 180,
                            validateOnChange: false,
                            //allowBlank: false,
                            listeners: {
                                change: 'onEducation100YesSelectListener'
                            },
                            items: [
                                {
                                    xtype: 'radiofield',
                                    name: 'education100',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Ναι',
                                    inputValue: '1'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'education100',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Οχι',
                                    inputValue: '0'
                                }
                            ]
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            disabled: true,
                            fieldLabel: 'Αριθμός Σεμιναρίου',
                            labelWidth: 180,
                            name: 'seminarId100Text',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 300,
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            disabled: true,
                            fieldLabel: 'ΚΕΚ/Φορέας',
                            labelWidth: 180,
                            name: 'foreas100',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 100
                        },
                        {
                            xtype: 'radiogroup',
                            anchor: '70%',
                            fieldLabel: 'Επιμόρφωση 35 Ωρών',
                            labelWidth: 180,
                            validateOnChange: false,
                            //allowBlank: false,
                            listeners: {
                                change: 'onEducation3510YesSelectListener'
                            },
                            items: [
                                {
                                    xtype: 'radiofield',
                                    name: 'education3510',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Ναι',
                                    inputValue: '1'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'education3510',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Οχι',
                                    inputValue: '0'
                                }
                            ]
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            disabled: true,
                            fieldLabel: 'Αριθμός Σεμιναρίου',
                            labelWidth: 180,
                            name: 'seminarId3510Text',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 300,
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            disabled: true,
                            fieldLabel: 'ΚΕΚ/Φορέας',
                            labelWidth: 180,
                            name: 'foreas3510',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 100
                        },
                        {
                            xtype: 'radiogroup',
                            anchor: '70%',
                            fieldLabel: 'Επιμόρφωση 10 Ωρών',
                            labelWidth: 180,
                            validateOnChange: false,
                            //allowBlank: false,
                            listeners: {
                                change: 'onEducation10YesSelectListener'
                            },
                            items: [
                                {
                                    xtype: 'radiofield',
                                    name: 'education10',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Ναι',
                                    inputValue: '1'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'education10',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Οχι',
                                    inputValue: '0'
                                }
                            ]
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            disabled: true,
                            fieldLabel: 'Αριθμός Σεμιναρίου',
                            labelWidth: 180,
                            name: 'seminarId10Text',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 300,
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            disabled: true,
                            fieldLabel: 'ΚΕΚ/Φορέας',
                            labelWidth: 180,
                            name: 'foreas10',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 100
                        },
                        {
                            xtype: 'hiddenfield',
                            anchor: '100%',
                            fieldLabel: 'Label',
                            name: 'attachedDocIdEmplTraining',
                            value: '-1'
                        },
                        {
                            xtype: 'form',
                            id: 'attachedDocIdEmplTrainingTA',
                            standardSubmit: false,
                            items: [
                                {
                                    xtype: 'filefield',
                                    anchor: '100%',
                                    frame: false,
                                    fieldLabel: 'Επισυναπτόμενο Αρχείο Κατάρτισης/Προϋπηρεσίας',
                                    //disabled: true,
                                    labelAlign: 'top',
                                    labelWidth: 180,
                                    msgTarget: 'under',
                                    name: 'file',
                                    submitValue: true,
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    buttonText: 'Επιλογή...'
                                }
                            ]
                        },
                        {
                            xtype: 'container',
                            hidden: true,
                            itemId: 'Exist_File2',
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
                                        click: 'onDeleteClick111'
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
                                        click: 'onViewDocClick111'
                                    }
                                },
                                {
                                    xtype: 'hiddenfield',
                                    flex: 1,
                                    labelWidth: 180,
                                    msgTarget: 'under',
                                    name: 'deletedfile2',
                                    validateOnChange: false,
                                    value: false
                                }
                            ]
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            fieldLabel: 'Γενικές Σημειώσεις',
                            labelWidth: 180,
                            name: 'notes',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 500,
                            //validateBlank: true,
                            hidden: true,
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
                            id: 'docfile2',
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
                                    hidden: true,
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
                            xtype: 'textareafield',
                            anchor: '100%',
                            fieldLabel: 'Παρατηρήσεις',
                            labelWidth: 180,
                            name: 'descr',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 3000,
                            validateBlank: true
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
            dateToTimestamp: function(date) {
                var pD = date.split("-");
                return (pD[2]+"-"+pD[1]+"-"+pD[0]+"T00:00:00.000+0000");
            },
            parseDate: function(str) {
                var mdy = str.split('-');
                return new Date(mdy[2], mdy[1]-1, mdy[0]);
            },
            daysdiff: function(first, second) {
                return Math.round((second-first)/(1000*60*60*24));
            },
            dock: 'bottom',
            html: '<span style="color:#696969;font-size:11px;"><em>*Τα ανενεργά πεδία θα συμπληρωθούν αυτόματα<br>από το σύστημα κατά την υποβολή</em></span>',
            id: 'regreqta_save_submit_toolbar',
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
                            fn: 'onDelete_TECHNICIAN_REGREQUEST',
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
                            fn: 'onSave_TECHNICIAN_REGREQUEST',
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
                            fn: 'onSubmit_TECHNICIAN_REGREQUEST',
                            scope: 'controller'
                        }
                    }
                }
            ],
            listeners: {
                beforehide: 'onRegReqTa_save_submit_toolbarBeforeHide1'
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
    listeners: {
        beforedestroy: 'onWindowBeforeDestroy',
        show: function(component, eOpts) {
            var view = Ext.getCmp('technicianmainView');
            var menu = view.getComponent('headerPanel');
            var contentPanel = view.getComponent('contentPanel');
            menu.setDisabled(true);
            contentPanel.setDisabled(true);
        },
        hide: function(button, e, eOpts) {
            var view = Ext.getCmp('technicianmainView');
            var menu = view.getComponent('headerPanel');
            var contentPanel = view.getComponent('contentPanel');
            menu.setDisabled(false);
            contentPanel.setDisabled(false);
        }
    },

    onInfoTextBeforeRender: function(field, isDirty, eOpts) {
        var status = field.up('form').getForm().findField('subStatus');
        if (status.value == 2) {
            field.hide();
        }
    },

    onShipyardDutiesYesSelectListener: function(field, isDirty, eOpts) {
        var status = field.up('form').getForm().findField('subStatus');
        if (status.value != 2) {
            var getnext0 = field.up('form').getForm().findField('shipyardDuties');
            var getnext = field.up('form').getForm().findField('education3510');
            var getnext2 = field.up('form').getForm().findField('education100');
            var getnext3 = field.up('form').getForm().findField('education10');
            var file = Ext.getCmp("attachedDocIdEmplTrainingTA").getForm().findField("file");
            if (getnext.value == 1 || getnext2.value == 1  || getnext3.value == 1 || getnext0.value == 1) {
                file.enable();
                file.allowBlank = false;
                file.allowOnlyWhitespace = false;
            } else {
                file.disable();
                file.allowBlank = true;
                file.allowOnlyWhitespace = true;
            }
        }
    },

    onEducation100YesSelectListener: function(field, isDirty, eOpts) {
        var status = field.up('form').getForm().findField('subStatus');
        if (status.value != 2) {
            var getnext0 = field.up('form').getForm().findField('shipyardDuties');
            var getnext = field.up('form').getForm().findField('education3510');
            var getnext2 = field.up('form').getForm().findField('education100');
            var getnext3 = field.up('form').getForm().findField('education10');
            var file = Ext.getCmp("attachedDocIdEmplTrainingTA").getForm().findField("file");
            if (getnext.value == 1 || getnext2.value == 1  || getnext3.value == 1 || getnext0.value == 1) {
                file.enable();
                file.allowBlank = false;
                file.allowOnlyWhitespace = false;
            } else {
                file.disable();
                file.allowBlank = true;
                file.allowOnlyWhitespace = true;
            }
            var seminarId = field.up('form').getForm().findField('seminarId100Text');
            var foreas = field.up('form').getForm().findField('foreas100');
            if (getnext2.value == 1) {
                seminarId.enable();
                seminarId.allowBlank = false;
                seminarId.allowOnlyWhitespace = false;
                foreas.enable();
                foreas.allowBlank = false;
                foreas.allowOnlyWhitespace = false;
            } else {
                seminarId.disable();
                seminarId.allowBlank = true;
                seminarId.allowOnlyWhitespace = true;
                foreas.disable();
                foreas.allowBlank = true;
                foreas.allowOnlyWhitespace = true;
            }
            var educationLevel = field.up('form').getForm().findField('educationLevel');
            var education100 = field.up('form').getForm().findField('education100');
            var diplomasGroup = field.up('form').getForm().findField('diplomas');
            // Start diploma validation
            if (educationLevel.value == 2 ) { // ΠΕ
                if (diplomasGroup != null) {
                    var diplomas = diplomasGroup.getBoxes();
                    if (diplomas != null && diplomas.length > 0) {
                        for (var i=0; i<diplomas.length; i++) {
                            var diplomaDate = diplomas[i].diplomaDate;
                            if (diplomaDate != null && diplomaDate != '') {
                                var diplomaTime = field.up('form').up().down('toolbar').daysdiff(field.up('form').up().down('toolbar').parseDate(diplomaDate), new Date());
                                if (diplomaTime < 1*365 ) {
                                    diplomas[i].setValue(0);
                                    diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate + '\t <img src="/portal/static/userapp/resources/images/form/exclamation.png" title="Δεν μπορεί να γίνει αποδεκτός ο τίτλος σπουδών." style="width:16px;height:16px;margin:0 5px 0 5px;"/>');
                                } else if (diplomaTime >= 1*365 && diplomaTime < 2*365 && education100.getValue() == 0) {
                                    diplomas[i].setValue(0);
                                    diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate + '\t <img src="/portal/static/userapp/resources/images/form/exclamation.png" title="Δεν μπορεί να γίνει αποδεκτός ο τίτλος σπουδών. Είναι υποχρεωτική η καταχώρηση Επιμόρφωσης 100 ωρών για να γίνει αποδεκτό αυτό το πτυχίο." style="width:16px;height:16px;margin:0 5px 0 5px;"/>');
                                } else {
                                    diplomas[i].setValue(1);
                                    diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate);
                                }
                            }
                        }
                    }
                }
            } else if (educationLevel.value == 1 ) { // TΕ
                if (diplomasGroup != null) {
                    var diplomas = diplomasGroup.getBoxes();
                    if (diplomas != null && diplomas.length > 0) {
                        for (var i=0; i<diplomas.length; i++) {
                            var diplomaDate = diplomas[i].diplomaDate;
                            if (diplomaDate != null && diplomaDate != '') {
                                var diplomaTime = field.up('form').up().down('toolbar').daysdiff(field.up('form').up().down('toolbar').parseDate(diplomaDate), new Date());
                                if (diplomaTime < 2*365 ) {
                                    diplomas[i].setValue(0);
                                    diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate + '\t <img src="/portal/static/userapp/resources/images/form/exclamation.png" title="Δεν μπορεί να γίνει αποδεκτός ο τίτλος σπουδών." style="width:16px;height:16px;margin:0 5px 0 5px;"/>');
                                } else if (diplomaTime >= 2*365 && diplomaTime < 5*365 && education100.getValue() == 0) {
                                    diplomas[i].setValue(0);
                                    diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate + '\t <img src="/portal/static/userapp/resources/images/form/exclamation.png" title="Δεν μπορεί να γίνει αποδεκτός ο τίτλος σπουδών. Είναι υποχρεωτική η καταχώρηση Επιμόρφωσης 100 ωρών για να γίνει αποδεκτό αυτό το πτυχίο." style="width:16px;height:16px;margin:0 5px 0 5px;"/>');
                                } else {
                                    diplomas[i].setValue(1);
                                    diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate);
                                }
                            }
                        }
                    }
                }
            }
        }
    },
    
    onEducation3510YesSelectListener: function(field, isDirty, eOpts) {
        var status = field.up('form').getForm().findField('subStatus');
        if (status.value != 2) {
            var getnext0 = field.up('form').getForm().findField('shipyardDuties');
            var getnext = field.up('form').getForm().findField('education3510');
            var getnext2 = field.up('form').getForm().findField('education100');
            var getnext3 = field.up('form').getForm().findField('education10');
            var file = Ext.getCmp("attachedDocIdEmplTrainingTA").getForm().findField("file");
            if (getnext.value == 1 || getnext2.value == 1  || getnext3.value == 1 || getnext0.value == 1) {
                file.enable();
                file.allowBlank = false;
                file.allowOnlyWhitespace = false;
            } else {
                file.disable();
                file.allowBlank = true;
                file.allowOnlyWhitespace = true;
            }
            var seminarId = field.up('form').getForm().findField('seminarId3510Text');
            var foreas = field.up('form').getForm().findField('foreas3510');
            if (getnext.value == 1) {
                seminarId.enable();
                seminarId.allowBlank = false;
                seminarId.allowOnlyWhitespace = false;
                foreas.enable();
                foreas.allowBlank = false;
                foreas.allowOnlyWhitespace = false;
            } else {
                seminarId.disable();
                seminarId.allowBlank = true;
                seminarId.allowOnlyWhitespace = true;
                foreas.disable();
                foreas.allowBlank = true;
                foreas.allowOnlyWhitespace = true;
            }
            var educationLevel = field.up('form').getForm().findField('educationLevel');
            var education3510 = field.up('form').getForm().findField('education3510');
            var diplomasGroup = field.up('form').getForm().findField('diplomas');
            // Start diploma validation
            if (educationLevel.value == 3 ) { // ΔΕ
                if (diplomasGroup != null) {
                    var diplomas = diplomasGroup.getBoxes();
                    if (diplomas != null && diplomas.length > 0) {
                        for (var i=0; i<diplomas.length; i++) {
                            var diplomaDate = diplomas[i].diplomaDate;
                            if (diplomaDate != null && diplomaDate != '') {
                                var diplomaTime = field.up('form').up().down('toolbar').daysdiff(field.up('form').up().down('toolbar').parseDate(diplomaDate), new Date());
                                if (diplomaTime < 8*365 ) {
                                    diplomas[i].setValue(0);
                                    diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate + '\t <img src="/portal/static/userapp/resources/images/form/exclamation.png" title="Δεν μπορεί να γίνει αποδεκτός ο τίτλος σπουδών." style="width:16px;height:16px;margin:0 5px 0 5px;"/>');
                                } else if (diplomaTime >= 8*365 && education3510.getValue() == 0) {
                                    diplomas[i].setValue(0);
                                    diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate + '\t <img src="/portal/static/userapp/resources/images/form/exclamation.png" title="Δεν μπορεί να γίνει αποδεκτός ο τίτλος σπουδών. Είναι υποχρεωτική η καταχώρηση Επιμόρφωσης 35 ωρών για να γίνει αποδεκτό αυτό το πτυχίο." style="width:16px;height:16px;margin:0 5px 0 5px;"/>');
                                } else {
                                    diplomas[i].setValue(1);
                                    diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate);
                                }
                            }
                        }
                    }
                }
            }
        }
    },

    onEducation10YesSelectListener: function(field, isDirty, eOpts) {
        var status = field.up('form').getForm().findField('subStatus');
        if (status.value != 2) {
            var getnext0 = field.up('form').getForm().findField('shipyardDuties');
            var getnext = field.up('form').getForm().findField('education3510');
            var getnext2 = field.up('form').getForm().findField('education100');
            var getnext3 = field.up('form').getForm().findField('education10');
            var file = Ext.getCmp("attachedDocIdEmplTrainingTA").getForm().findField("file");
            if (getnext.value == 1 || getnext2.value == 1  || getnext3.value == 1 || getnext0.value == 1) {
                file.enable();
                file.allowBlank = false;
                file.allowOnlyWhitespace = false;
            } else {
                file.disable();
                file.allowBlank = true;
                file.allowOnlyWhitespace = true;
            }
            var seminarId = field.up('form').getForm().findField('seminarId10Text');
            var foreas = field.up('form').getForm().findField('foreas10');
            if (getnext3.value == 1) {
                seminarId.enable();
                seminarId.allowBlank = false;
                seminarId.allowOnlyWhitespace = false;
                foreas.enable();
                foreas.allowBlank = false;
                foreas.allowOnlyWhitespace = false;
            } else {
                seminarId.disable();
                seminarId.allowBlank = true;
                seminarId.allowOnlyWhitespace = true;
                foreas.disable();
                foreas.allowBlank = true;
                foreas.allowOnlyWhitespace = true;
            }
        }
    },

    onAddrPSelect1111: function(combo, records, eOpts) {
                var getnext = combo.up('form').getForm().findField('reqAddrPe');
                getnext.clearValue();
                getnext.enable();
    },

    onAddrPeExpand1111: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
        queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('reqAddrP').getValue()+" ";
    },

    onAddrPeSelect1111: function(combo, records, eOpts) {
                var getnext = combo.up('form').getForm().findField('reqAddrD');
                getnext.clearValue();
                getnext.enable();
    },

    onAddrPeDirtyChange1111: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
                    field.getStore().getProxy().url = "/TKalPe/"+field.getValue()+" ";
                field.getStore().getProxy().getReader().setRootProperty("");
                field.store.load();
                }
    },

    onAddrDExpand1111: function(queryPlan, eOpts) {
            queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalD");
        queryPlan.combo.getStore().getProxy().url = "/TKalD/search/findByEnotCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('reqAddrPe').getValue()+" ";
    },

    onAddrDSelect1111: function(combo, records, eOpts) {
                var getnext = combo.up('form').getForm().findField('reqAddrK');
                getnext.clearValue();
                getnext.enable();
    },

    onAddrDDirtyChange1111: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
                    field.getStore().getProxy().url = "/TKalD/"+field.getValue()+" ";
                field.getStore().getProxy().getReader().setRootProperty("");
                field.store.load();
                }
    },

    onAddrKExpand1111: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalK");
        queryPlan.combo.getStore().getProxy().url = "/TKalK/search/findByDimosCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('reqAddrD').getValue();
    },

    onAddrKDirtyChange1111: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalK/"+field.getValue();
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        }
    },

    onEducLevelSelect: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('speciality');
        getnext.clearValue();
        getnext.enable();
        var educationLevel = combo.up('form').getForm().findField('educationLevel');
        var education100 = combo.up('form').getForm().findField('education100');
        var education3510 = combo.up('form').getForm().findField('education3510');
        var diplomasGroup = combo.up('form').getForm().findField('diplomas');
        // Start diploma validation
        if (educationLevel.value == 4 || educationLevel.value == 5) {
            if (diplomasGroup != null) {
                var diplomas = diplomasGroup.getBoxes();
                if (diplomas != null && diplomas.length > 0) {
                    for (var i=0; i<diplomas.length; i++) {
                        if (diplomas[i].getValue() == 0) {
                            diplomas[i].setValue(1);
                            diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate);
                        }
                    }
                }
            }
        } else if (educationLevel.value == 2 ) { // ΠΕ
            if (diplomasGroup != null) {
                var diplomas = diplomasGroup.getBoxes();
                if (diplomas != null && diplomas.length > 0) {
                    for (var i=0; i<diplomas.length; i++) {
                        var diplomaDate = diplomas[i].diplomaDate;
                        if (diplomaDate != null && diplomaDate != '') {
                            var diplomaTime = combo.up('form').up().down('toolbar').daysdiff(combo.up('form').up().down('toolbar').parseDate(diplomaDate), new Date());
                            if (diplomaTime < 1*365 ) {
                                diplomas[i].setValue(0);
                                diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate + '\t <img src="/portal/static/userapp/resources/images/form/exclamation.png" title="Δεν μπορεί να γίνει αποδεκτός ο τίτλος σπουδών." style="width:16px;height:16px;margin:0 5px 0 5px;"/>');
                            } else if (diplomaTime >= 1*365 && diplomaTime < 2*365 && education100.getValue() == 0) {
                                diplomas[i].setValue(0);
                                diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate + '\t <img src="/portal/static/userapp/resources/images/form/exclamation.png" title="Δεν μπορεί να γίνει αποδεκτός ο τίτλος σπουδών. Είναι υποχρεωτική η καταχώρηση Επιμόρφωσης 100 ωρών για να γίνει αποδεκτό αυτό το πτυχίο." style="width:16px;height:16px;margin:0 5px 0 5px;"/>');
                            } else {
                                diplomas[i].setValue(1);
                                diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate);
                            }
                        }
                    }
                }
            }
        } else if (educationLevel.value == 1 ) { // TΕ
            if (diplomasGroup != null) {
                var diplomas = diplomasGroup.getBoxes();
                if (diplomas != null && diplomas.length > 0) {
                    for (var i=0; i<diplomas.length; i++) {
                        var diplomaDate = diplomas[i].diplomaDate;
                        if (diplomaDate != null && diplomaDate != '') {
                            var diplomaTime = combo.up('form').up().down('toolbar').daysdiff(combo.up('form').up().down('toolbar').parseDate(diplomaDate), new Date());
                            if (diplomaTime < 2*365 ) {
                                diplomas[i].setValue(0);
                                diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate + '\t <img src="/portal/static/userapp/resources/images/form/exclamation.png" title="Δεν μπορεί να γίνει αποδεκτός ο τίτλος σπουδών." style="width:16px;height:16px;margin:0 5px 0 5px;"/>');
                            } else if (diplomaTime >= 2*365 && diplomaTime < 5*365 && education100.getValue() == 0) {
                                diplomas[i].setValue(0);
                                diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate + '\t <img src="/portal/static/userapp/resources/images/form/exclamation.png" title="Δεν μπορεί να γίνει αποδεκτός ο τίτλος σπουδών. Είναι υποχρεωτική η καταχώρηση Επιμόρφωσης 100 ωρών για να γίνει αποδεκτό αυτό το πτυχίο." style="width:16px;height:16px;margin:0 5px 0 5px;"/>');
                            } else {
                                diplomas[i].setValue(1);
                                diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate);
                            }
                        }
                    }
                }
            }
        } else if (educationLevel.value == 3 ) { // ΔΕ
            if (diplomasGroup != null) {
                var diplomas = diplomasGroup.getBoxes();
                if (diplomas != null && diplomas.length > 0) {
                    for (var i=0; i<diplomas.length; i++) {
                        var diplomaDate = diplomas[i].diplomaDate;
                        if (diplomaDate != null && diplomaDate != '') {
                            var diplomaTime = combo.up('form').up().down('toolbar').daysdiff(combo.up('form').up().down('toolbar').parseDate(diplomaDate), new Date());
                            if (diplomaTime < 8*365 ) {
                                diplomas[i].setValue(0);
                                diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate + '\t <img src="/portal/static/userapp/resources/images/form/exclamation.png" title="Δεν μπορεί να γίνει αποδεκτός ο τίτλος σπουδών." style="width:16px;height:16px;margin:0 5px 0 5px;"/>');
                            } else if (diplomaTime >= 8*365 && education3510.getValue() == 0) {
                                diplomas[i].setValue(0);
                                diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate + '\t <img src="/portal/static/userapp/resources/images/form/exclamation.png" title="Δεν μπορεί να γίνει αποδεκτός ο τίτλος σπουδών. Είναι υποχρεωτική η καταχώρηση Επιμόρφωσης 35 ωρών για να γίνει αποδεκτό αυτό το πτυχίο." style="width:16px;height:16px;margin:0 5px 0 5px;"/>');
                            } else {
                                diplomas[i].setValue(1);
                                diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate);
                            }
                        }
                    }
                }
            }
        }
    },

    onSpecialitySelect: function(combo, records, eOpts) {
        var scrollpos=combo.up('window').getScrollY();
        if (parseInt(records[0].get('abbr'))===99){
            combo.up('form').getForm().findField('specialityOther').show();
            combo.up('form').getForm().findField('specialityOther').allowBlank=false;
            combo.up('form').getForm().findField('specialityOther').allowOnlyWhitespace=false;
        }
        else{
            combo.up('form').getForm().findField('specialityOther').setValue();
            combo.up('form').getForm().findField('specialityOther').hide();
            combo.up('form').getForm().findField('specialityOther').allowBlank=true;
            combo.up('form').getForm().findField('specialityOther').allowOnlyWhitespace=true;
        }
        combo.up('window').scrollTo(0, scrollpos);
    },

    onSpecialityExpand: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.taSpeciality");
        queryPlan.combo.getStore().getProxy().url = "/taSpeciality/search/findByEducLevel";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('educationLevel').getValue();
    },

    onSpecialityDirtyChange: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/taSpeciality/"+field.getValue();
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onLoadAfterRender1: function(component, container, pos, eOpts) {
        var store = Ext.StoreMgr.lookup('technician.DIPLOMAS_GRID' );

        store.load({
            callback: function(records, operation, success){
            if(success){
                var status = component.up('form').getForm().findField('subStatus');
                if(status.value == 2) {
                    for (i = 0; i < store.getCount(); i++) {
                        iv=store.getAt(i).get('abbr');
                        if (component.up('form').getRecord().get('diplomaIdComma') !== null ) {
                            var pD = store.getAt(i).get('diplomaYear').replace(/[^0-9]+/g,' ').split(" ");
                            var grp = new Ext.form.Checkbox({
                                boxLabel: store.getAt(i).get('diplomaDescr') + " - " + pD[2]+"-"+pD[1]+"-"+pD[0] ,
                                diplomaDate: pD[2]+"-"+pD[1]+"-"+pD[0],
                                diplomaDescr: store.getAt(i).get('diplomaDescr'),
                                inputValue: iv,
                                name: 'diplomaIdComma',
                                id: 'cba'+iv,
                                disabled: true,
                                checked: component.up('form').getRecord().get('diplomaIdComma').match(iv) ? true : false,
                                validateOnBlur: false,
                                validateOnChange: false
                            });
                            component.add(grp);
                        }
                    }
                } else {
                    for (i = 0; i < store.getCount(); i++) {
                        iv=store.getAt(i).get('abbr');
                        var pD = store.getAt(i).get('diplomaYear').replace(/[^0-9]+/g,' ').split(" ");
                        var grp = new Ext.form.Checkbox({
                            boxLabel: store.getAt(i).get('diplomaDescr') + " - " + pD[2]+"-"+pD[1]+"-"+pD[0] ,
                            diplomaDate: pD[2]+"-"+pD[1]+"-"+pD[0],
                            diplomaDescr: store.getAt(i).get('diplomaDescr'),
                            inputValue: iv,
                            name: 'diplomaIdComma',
                            id: 'cba'+iv,
                            disabled: true,
                            checked: true,
                            validateOnBlur: false,
                            validateOnChange: false
                        });
                        component.add(grp);

                    }

                    var educationLevel = component.up('form').getForm().findField('educationLevel');
                    var education100 = component.up('form').getForm().findField('education100');
                    var education3510 = component.up('form').getForm().findField('education3510');
                    var diplomasGroup = component.up('form').getForm().findField('diplomas');
                    // Start diploma validation
                    if (educationLevel.value == 4 || educationLevel.value == 5) {
                        if (diplomasGroup != null) {
                            var diplomas = diplomasGroup.getBoxes();
                            if (diplomas != null && diplomas.length > 0) {
                                for (var i=0; i<diplomas.length; i++) {
                                    if (diplomas[i].getValue() == 0) {
                                        diplomas[i].setValue(1);
                                        diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate);
                                    }
                                }
                            }
                        }
                    } else if (educationLevel.value == 2 ) { // ΠΕ
                        if (diplomasGroup != null) {
                            var diplomas = diplomasGroup.getBoxes();
                            if (diplomas != null && diplomas.length > 0) {
                                for (var i=0; i<diplomas.length; i++) {
                                    var diplomaDate = diplomas[i].diplomaDate;
                                    if (diplomaDate != null && diplomaDate != '') {
                                        var diplomaTime = component.up('form').up().down('toolbar').daysdiff(component.up('form').up().down('toolbar').parseDate(diplomaDate), new Date());
                                        if (diplomaTime < 1*365 ) {
                                            diplomas[i].setValue(0);
                                            diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate + '\t <img src="/portal/static/userapp/resources/images/form/exclamation.png" title="Δεν μπορεί να γίνει αποδεκτός ο τίτλος σπουδών." style="width:16px;height:16px;margin:0 5px 0 5px;"/>');
                                        } else if (diplomaTime >= 1*365 && diplomaTime < 2*365 && education100.getValue() == 0) {
                                            diplomas[i].setValue(0);
                                            diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate + '\t <img src="/portal/static/userapp/resources/images/form/exclamation.png" title="Δεν μπορεί να γίνει αποδεκτός ο τίτλος σπουδών. Είναι υποχρεωτική η καταχώρηση Επιμόρφωσης 100 ωρών για να γίνει αποδεκτό αυτό το πτυχίο." style="width:16px;height:16px;margin:0 5px 0 5px;"/>');
                                        } else {
                                            diplomas[i].setValue(1);
                                            diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate);
                                        }
                                    }
                                }
                            }
                        }
                    } else if (educationLevel.value == 1 ) { // TΕ
                        if (diplomasGroup != null) {
                            var diplomas = diplomasGroup.getBoxes();
                            if (diplomas != null && diplomas.length > 0) {
                                for (var i=0; i<diplomas.length; i++) {
                                    var diplomaDate = diplomas[i].diplomaDate;
                                    if (diplomaDate != null && diplomaDate != '') {
                                        var diplomaTime = component.up('form').up().down('toolbar').daysdiff(component.up('form').up().down('toolbar').parseDate(diplomaDate), new Date());
                                        if (diplomaTime < 2*365 ) {
                                            diplomas[i].setValue(0);
                                            diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate + '\t <img src="/portal/static/userapp/resources/images/form/exclamation.png" title="Δεν μπορεί να γίνει αποδεκτός ο τίτλος σπουδών." style="width:16px;height:16px;margin:0 5px 0 5px;"/>');
                                        } else if (diplomaTime >= 2*365 && diplomaTime < 5*365 && education100.getValue() == 0) {
                                            diplomas[i].setValue(0);
                                            diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate + '\t <img src="/portal/static/userapp/resources/images/form/exclamation.png" title="Δεν μπορεί να γίνει αποδεκτός ο τίτλος σπουδών. Είναι υποχρεωτική η καταχώρηση Επιμόρφωσης 100 ωρών για να γίνει αποδεκτό αυτό το πτυχίο." style="width:16px;height:16px;margin:0 5px 0 5px;"/>');
                                        } else {
                                            diplomas[i].setValue(1);
                                            diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate);
                                        }
                                    }
                                }
                            }
                        }
                    } else if (educationLevel.value == 3 ) { // ΔΕ
                        if (diplomasGroup != null) {
                            var diplomas = diplomasGroup.getBoxes();
                            if (diplomas != null && diplomas.length > 0) {
                                for (var i=0; i<diplomas.length; i++) {
                                    var diplomaDate = diplomas[i].diplomaDate;
                                    if (diplomaDate != null && diplomaDate != '') {
                                        var diplomaTime = component.up('form').up().down('toolbar').daysdiff(component.up('form').up().down('toolbar').parseDate(diplomaDate), new Date());
                                        if (diplomaTime < 8*365 ) {
                                            diplomas[i].setValue(0);
                                            diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate + '\t <img src="/portal/static/userapp/resources/images/form/exclamation.png" title="Δεν μπορεί να γίνει αποδεκτός ο τίτλος σπουδών." style="width:16px;height:16px;margin:0 5px 0 5px;"/>');
                                        } else if (diplomaTime >= 8*365 && education3510.getValue() == 0) {
                                            diplomas[i].setValue(0);
                                            diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate + '\t <img src="/portal/static/userapp/resources/images/form/exclamation.png" title="Δεν μπορεί να γίνει αποδεκτός ο τίτλος σπουδών. Είναι υποχρεωτική η καταχώρηση Επιμόρφωσης 35 ωρών για να γίνει αποδεκτό αυτό το πτυχίο." style="width:16px;height:16px;margin:0 5px 0 5px;"/>');
                                        } else {
                                            diplomas[i].setValue(1);
                                            diplomas[i].setBoxLabel(diplomas[i].diplomaDescr + ' - ' + diplomas[i].diplomaDate);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if (store.getCount()===0){
                    var grp1 = new Ext.form.DisplayField({
                        hideLabel: true,
                        value: "Δεν έχετε προσθέσει τίτλους. Πρέπει να το κάνετε από το μενού αριστερά."
                    });
                    component.add(grp1);
                }
                /*if(component.up('form').getForm().findField('a_new_form').getValue()==="false")
                    if(component.up('form').getRecord().get('diplomaIdComma')!==null)
                if(component.up('form').getRecord().get('diplomaIdComma').split(",").length>0){
                    var diplomaIdCommas = component.up('form').getRecord().get('diplomaIdComma').split(",");
                    for (i = 0; i < diplomaIdCommas.length; i++) {
                        Ext.getCmp('cba'+diplomaIdCommas[i]).setValue(true);
                    }
                }*/
            }
            }

        });

        

    },

    onDeleteClick111: function(button, e, eOpts) {
        button.up('form').getForm().findField('deletedfile2').setValue(true);
        button.up('container').hide();
        button.up('form').getForm().findField('file').focus();
    },

    onViewDocClick111: function(button, e, eOpts) {
        var url = "/getDocument?docId="+button.up('form').getForm().findField('attachedDocIdEmplTraining').getValue();
        var win = window.open(url, '_blank');
          win.focus();
    },

    onDeleteClick1: function(button, e, eOpts) {
        button.up('form').getForm().findField('deletedfile').setValue(true);
        button.up('container').hide();
        button.up('form').getForm().findField('file').focus();
    },

    onViewDocClick1: function(button, e, eOpts) {
        var url = "/getDocument?docId="+button.up('form').getForm().findField('attachedDocId').getValue();
        var win = window.open(url, '_blank');
          win.focus();
    },

    onRegReqTa_save_submit_toolbarBeforeHide1: function(component, eOpts) {
        component.getComponent('deletebutton').destroy();
        component.getComponent('savebutton').destroy();
        component.getComponent('submitbutton').destroy();
    },

    onWindowBeforeDestroy: function(component, eOpts) {
        var view=Ext.getCmp('technicianmainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.technician.RegRequestsPanel');
        center.add(viewsub);
    }

});