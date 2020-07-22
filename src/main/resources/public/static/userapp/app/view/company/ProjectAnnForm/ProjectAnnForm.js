/*
 * File: app/view/company/ProjectAnnForm/ProjectAnnForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.ProjectAnnForm.ProjectAnnForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companyprojectannformprojectannform',

    requires: [
        'MyApp.view.company.ProjectAnnForm.ProjectAnnFromViewModel',
        'MyApp.view.company.ProjectAnnForm.ProjectAnnFromViewController',
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
        'Ext.form.field.File',
        'Ext.form.Label',
        'Ext.toolbar.Toolbar',
        'Ext.panel.Tool'
    ],

    controller: 'companyprojectannformprojectannform',
    viewModel: {
        type: 'companyprojectannformprojectannform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    overflowY: 'scroll',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Φόρμα "Εκ των προτέρων γνωστοποίησης" έναρξης οικοδομικών Εργασιών',
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
                if (timestamp != null)
                {
                    var pD = timestamp.replace(/[^0-9]+/g,' ').split(" ");
                    return (pD[2]+"-"+pD[1]+"-"+pD[0]);
                }
                else return "";
            },
            id: 'companyprojannform',
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
                    title: 'ΣΤΟΙΧΕΙΑ ΓΝΩΣΤΟΠΟΙΗΣΗΣ',
                    items: [
                        {
                            xtype: 'hiddenfield',
                            anchor: '100%',
                            cls: 'x-item-disabled',
                            disabled: true,
                            fieldLabel: 'Αρμόδια Διεύθυνση (ΚΕΠΕΚ)',
                            labelWidth: 180,
                            name: 'kepek',
                            validateOnChange: false
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            cls: 'x-item-disabled',
                            disabled: true,
                            fieldLabel: 'Αρμόδια Διεύθυνση',
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
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Αριθμός Μητρώου Οικοδομικών Εργασιών (ΑΜΟΕ)',
                            labelWidth: 180,
                            name: 'amoe',
                            validateOnChange: false,
                            validateOnBlur: false,
                            //allowBlank: false,
                            //allowOnlyWhitespace: false,
                            maxLength: 100
                        },
                        {
                            xtype: 'fieldset',
                            focusOnToFront: false,
                            padding: 10,
                            toFrontOnShow: false,
                            title: 'ΣΤΟΙΧΕΙΑ ΕΡΓΟΥ',
                            items: [
                                {
                                    xtype: 'textareafield',
                                    anchor: '100%',
                                    fieldLabel: 'Περιγραφή Τεχνικού Έργου',
                                    labelAlign: 'top',
                                    labelWidth: 180,
                                    name: 'descr',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 3000
                                },
                                {
                                    xtype: 'fieldset',
                                    focusOnToFront: false,
                                    toFrontOnShow: false,
                                    title: 'Ακριβής Διεύθυνση Εργοταξίου',
                                    items: [
                                        {
                                            xtype: 'textareafield',
                                            anchor: '100%',
                                            fieldLabel: 'Οδός / Αριθμός',
                                            labelWidth: 180,
                                            name: 'projAddr',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            allowBlank: false,
                                            allowOnlyWhitespace: false,
                                            maxLength: 200
                                        },
                                        {
                                            xtype: 'combobox',
                                            anchor: '100%',
                                            fieldLabel: 'Περιφέρεια',
                                            labelWidth: 180,
                                            name: 'projAddrP',
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
                                            name: 'projAddrPe',
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
                                            name: 'projAddrD',
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
                                            name: 'projAddrK',
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
                                            fieldLabel: 'Τ.Κ',
                                            labelWidth: 180,
                                            name: 'projAddrTk',
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
                                    fieldLabel: 'Αριθμός Άδειας (ή Έγκρισης για δημόσια έργα)',
                                    labelWidth: 180,
                                    name: 'licenceNum',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    //allowBlank: false,
                                    //allowOnlyWhitespace: false,
                                    maxLength: 100
                                },
                                {
                                    xtype: 'fieldset',
                                    id: 'projsmanagers',
                                    title: 'Κύριος(οι) του έργου',
                                    items: [
                                        {
                                            xtype: 'button',
                                            border: 0,
                                            frame: false,
                                            id: 'projsmanagersadd',
                                            style: 'background-color:transparent;',
                                            iconCls: 'addone',
                                            tooltip: 'Προσθήκη Κύριου του έργου',
                                            listeners: {
                                                click: 'onAddMngClick'
                                            }
                                        },
                                        {
                                            xtype: 'button',
                                            border: 0,
                                            frame: false,
                                            id: 'projsmanagersdel',
                                            style: 'background-color:transparent;',
                                            iconCls: 'removeone',
                                            tooltip: 'Διαγραφή τελευταίου Κύριου του έργου',
                                            listeners: {
                                                click: 'onDelMngClick'
                                            }
                                        },
                                        {
                                            xtype: 'hiddenfield',
                                            anchor: '100%',
                                            labelAlign: 'top',
                                            labelWidth: 180,
                                            name: 'projsmanagersnum',
                                            validateOnChange: false,
                                            value: 1
                                        },
                                        {
                                            xtype: 'hiddenfield',
                                            anchor: '100%',
                                            labelWidth: 180,
                                            msgTarget: 'under',
                                            name: 'projsmanagers',
                                            validateOnChange: false
                                        }
                                    ]
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Είδος του Έργου',
                                    labelWidth: 180,
                                    name: 'projType',
                                    validateOnChange: false,
                                    readOnly: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    editable: false,
                                    autoLoadOnValue: true,
                                    displayField: 'name',
                                    forceSelection: true,
                                    store: 'company.PROJECT_TYPE',
                                    valueField: 'abbr'
                                },
                                {
                                    xtype: 'fieldset',
                                    id: 'projscontrs',
                                    title: 'Ανάδοχος(οι) του έργου & Εργολάβος(οι)',
                                    items: [
                                        {
                                            xtype: 'button',
                                            border: 0,
                                            frame: false,
                                            id: 'projscontrsadd',
                                            style: 'background-color:transparent;',
                                            iconCls: 'addone',
                                            tooltip: 'Προσθήκη Ανάδοχου/Εργολάβου',
                                            listeners: {
                                                click: 'onAddContrClick'
                                            }
                                        },
                                        {
                                            xtype: 'button',
                                            border: 0,
                                            frame: false,
                                            id: 'projscontrsdel',
                                            style: 'background-color:transparent;',
                                            iconCls: 'removeone',
                                            tooltip: 'Διαγραφή τελευταίου Ανάδοχου/Εργολάβου',
                                            listeners: {
                                                click: 'onDelContrClick'
                                            }
                                        },
                                        {
                                            xtype: 'hiddenfield',
                                            anchor: '100%',
                                            labelAlign: 'top',
                                            labelWidth: 180,
                                            name: 'projscontrsnum',
                                            validateOnChange: false,
                                            value: 1
                                        },
                                        {
                                            xtype: 'hiddenfield',
                                            anchor: '100%',
                                            labelWidth: 180,
                                            msgTarget: 'under',
                                            name: 'projscontrs',
                                            validateOnChange: false
                                        }
                                    ]
                                },
                                {
                                    xtype: 'numberfield',
                                    anchor: '100%',
                                    fieldLabel: 'Αριθμός Υπεργολάβων',
                                    labelWidth: 180,
                                    name: 'subcontractorNum',
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
                                    allowDecimals: false,
                                    allowExponential: false,
                                    minValue: 0
                                },
                                {
                                    xtype: 'fieldset',
                                    id: 'projsstudiers',
                                    title: 'Μελετητής(ές) του έργου',
                                    items: [
                                        {
                                            xtype: 'button',
                                            border: 0,
                                            frame: false,
                                            id: 'projsstudiersadd',
                                            style: 'background-color:transparent;',
                                            iconCls: 'addone',
                                            tooltip: 'Προσθήκη Μελετητή',
                                            listeners: {
                                                click: 'onAddStudierClick'
                                            }
                                        },
                                        {
                                            xtype: 'button',
                                            border: 0,
                                            frame: false,
                                            id: 'projsstudiersdel',
                                            style: 'background-color:transparent;',
                                            iconCls: 'removeone',
                                            tooltip: 'Διαγραφή τελευταίου Μελετητή',
                                            listeners: {
                                                click: 'onDelStudierClick'
                                            }
                                        },
                                        {
                                            xtype: 'hiddenfield',
                                            anchor: '100%',
                                            labelAlign: 'top',
                                            labelWidth: 180,
                                            name: 'projsstudiersnum',
                                            validateOnChange: false,
                                            value: 1
                                        },
                                        {
                                            xtype: 'hiddenfield',
                                            anchor: '100%',
                                            labelWidth: 180,
                                            msgTarget: 'under',
                                            name: 'projsstudiers',
                                            validateOnChange: false
                                        }
                                    ]
                                },
                                {
                                    xtype: 'fieldset',
                                    id: 'projsengs1',
                                    title: 'Επιβλέπων Μηχανικός(οι) / Επιβλέπουσα(ες) Αρχή(ές)',
                                    items: [
                                        {
                                            xtype: 'button',
                                            border: 0,
                                            frame: false,
                                            id: 'projsengs1add',
                                            style: 'background-color:transparent;',
                                            iconCls: 'addone',
                                            tooltip: 'Προσθήκη Επιβλέπων',
                                            listeners: {
                                                click: 'onAddEng1Click'
                                            }
                                        },
                                        {
                                            xtype: 'button',
                                            border: 0,
                                            frame: false,
                                            id: 'projsengs1del',
                                            style: 'background-color:transparent;',
                                            iconCls: 'removeone',
                                            tooltip: 'Διαγραφή τελευταίου Επιβλέπων',
                                            listeners: {
                                                click: 'onDelEng1Click'
                                            }
                                        },
                                        {
                                            xtype: 'hiddenfield',
                                            anchor: '100%',
                                            labelAlign: 'top',
                                            labelWidth: 180,
                                            name: 'projsengs1num',
                                            validateOnChange: false,
                                            value: 1
                                        },
                                        {
                                            xtype: 'hiddenfield',
                                            anchor: '100%',
                                            labelWidth: 180,
                                            msgTarget: 'under',
                                            name: 'projsengs1',
                                            validateOnChange: false
                                        }
                                    ]
                                },
                                {
                                    xtype: 'fieldset',
                                    id: 'projsselfempls',
                                    title: 'Αυτοαπασχολούμενοι που έχουν ήδη επιλεγεί',
                                    items: [
                                        {
                                            xtype: 'button',
                                            border: 0,
                                            frame: false,
                                            id: 'projsselfemplsadd',
                                            style: 'background-color:transparent;',
                                            iconCls: 'addone',
                                            tooltip: 'Προσθήκη Αυτοαπασχολούμενου',
                                            listeners: {
                                                click: 'onAddSelfEmplClick'
                                            }
                                        },
                                        {
                                            xtype: 'button',
                                            border: 0,
                                            frame: false,
                                            id: 'projsselfemplsdel',
                                            style: 'background-color:transparent;',
                                            iconCls: 'removeone',
                                            tooltip: 'Διαγραφή τελευταίου Μελετητή',
                                            listeners: {
                                                click: 'onDelSelfEmplClick'
                                            }
                                        },
                                        {
                                            xtype: 'hiddenfield',
                                            anchor: '100%',
                                            labelAlign: 'top',
                                            labelWidth: 180,
                                            name: 'projsselfemplsnum',
                                            validateOnChange: false,
                                            value: 1
                                        },
                                        {
                                            xtype: 'hiddenfield',
                                            anchor: '100%',
                                            labelWidth: 180,
                                            msgTarget: 'under',
                                            name: 'projsselfempls',
                                            validateOnChange: false
                                        }
                                    ]
                                },
                                {
                                    xtype: 'fieldset',
                                    id: 'projsengs2',
                                    title: 'Συντονιστής(ες) σε θέματα ασφάλειας και υγείας κατά την εκπόνηση της μελέτης του έργου',
                                    items: [
                                        {
                                            xtype: 'button',
                                            border: 0,
                                            frame: false,
                                            id: 'projsengs2add',
                                            style: 'background-color:transparent;',
                                            iconCls: 'addone',
                                            tooltip: 'Προσθήκη Μηχανικού',
                                            listeners: {
                                                click: 'onAddEng2Click'
                                            }
                                        },
                                        {
                                            xtype: 'button',
                                            border: 0,
                                            frame: false,
                                            id: 'projsengs2del',
                                            style: 'background-color:transparent;',
                                            iconCls: 'removeone',
                                            tooltip: 'Διαγραφή τελευταίου Μηχανικού',
                                            listeners: {
                                                click: 'onDelEng2Click'
                                            }
                                        },
                                        {
                                            xtype: 'hiddenfield',
                                            anchor: '100%',
                                            labelAlign: 'top',
                                            labelWidth: 180,
                                            name: 'projsengs2num',
                                            validateOnChange: false,
                                            value: 1
                                        },
                                        {
                                            xtype: 'hiddenfield',
                                            anchor: '100%',
                                            labelWidth: 180,
                                            msgTarget: 'under',
                                            name: 'projsengs2',
                                            validateOnChange: false
                                        }
                                    ]
                                },
                                {
                                    xtype: 'fieldset',
                                    id: 'projsengs3',
                                    title: 'Συντονιστής(ες) σε θέματα ασφάλειας και υγείας κατά την εκτέλεση του έργου',
                                    items: [
                                        {
                                            xtype: 'button',
                                            border: 0,
                                            frame: false,
                                            id: 'projsengs3add',
                                            style: 'background-color:transparent;',
                                            iconCls: 'addone',
                                            tooltip: 'Προσθήκη Μηχανικού',
                                            listeners: {
                                                click: 'onAddEng3Click'
                                            }
                                        },
                                        {
                                            xtype: 'button',
                                            border: 0,
                                            frame: false,
                                            id: 'projsengs3del',
                                            style: 'background-color:transparent;',
                                            iconCls: 'removeone',
                                            tooltip: 'Διαγραφή τελευταίου Μηχανικού',
                                            listeners: {
                                                click: 'onDelEng3Click'
                                            }
                                        },
                                        {
                                            xtype: 'hiddenfield',
                                            anchor: '100%',
                                            labelAlign: 'top',
                                            labelWidth: 180,
                                            name: 'projsengs3num',
                                            validateOnChange: false,
                                            value: 1
                                        },
                                        {
                                            xtype: 'hiddenfield',
                                            anchor: '100%',
                                            labelWidth: 180,
                                            msgTarget: 'under',
                                            name: 'projsengs3',
                                            validateOnChange: false
                                        }
                                    ]
                                },
                                {
                                    xtype: 'datefield',
                                    anchor: '100%',
                                    fieldLabel: 'Προβλεπόμενη Ημερομηνία Έναρξης Εργασιών',
                                    labelWidth: 180,
                                    name: 'projStartDateView',
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
                                    fieldLabel: '',
                                    labelWidth: 180,
                                    name: 'projStartDate',
                                    validateOnChange: false
                                },
                                {
                                    xtype: 'textareafield',
                                    anchor: '100%',
                                    fieldLabel: 'Προβλεπόμενη Διάρκεια Εργασιών (ανά α. Φάση Εργασίας, β. Τμήμα του Έργου)',
                                    labelAlign: 'top',
                                    labelWidth: 180,
                                    name: 'projDuration',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 1000,
                                    validateBlank: true
                                },
                                {
                                    xtype: 'numberfield',
                                    anchor: '100%',
                                    fieldLabel: 'Προβλεπόμενος μέγιστος αριθμός εργαζομένων στο εργοτάξιο',
                                    labelAlign: 'top',
                                    labelWidth: 180,
                                    name: 'maxEmployeeNum',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    hideTrigger: true,
                                    maxLength: 100,
                                    repeatTriggerClick: false,
                                    keyNavEnabled: false,
                                    mouseWheelEnabled: false,
                                    spinDownEnabled: false,
                                    spinUpEnabled: false,
                                    allowDecimals: false,
                                    allowExponential: false,
                                    minValue: 1
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
                            id: 'projectannfile',
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
            id: 'compprojectann_save_submit_toolbar',
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
                            fn: 'onDelete_COMPANY_PROJECTANN',
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
                            fn: 'onSave_COMPANY_PROJECTANN',
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
                            fn: 'onSubmit_COMPANY_PROJECTANN',
                            scope: 'controller'
                        }
                    }
                }
            ],
            listeners: {
                beforehide: 'onCompProjAnn_save_submit_toolbarBeforeHide'
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
        var getnext = combo.up('form').getForm().findField('projAddrPe');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeExpand21: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
        queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('projAddrP').getValue()+" ";
    },

    onAddrPeSelect21: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('projAddrD');
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
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('projAddrPe').getValue()+" ";
    },

    onAddrDSelect21: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('projAddrK');
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
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('projAddrD').getValue();
    },

    onAddrKDirtyChange21: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalK/"+field.getValue();
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        }
    },

    onAddMngClick: function(button, e, eOpts) {
        var projsmanager = Ext.create('widget.companyprojectannformmng_studier_selfempl', {});
        button.up('form').getForm().findField('projsmanagersnum').setValue(parseInt(button.up('form').getForm().findField('projsmanagersnum').getValue())+1);
        projsmanager.down().items.get(4).setValue("1");
        if(parseInt(button.up('form').getForm().findField('projsmanagersnum').getValue())>1){
            projsmanager.down().items.get(0).hideLabel=true;
            projsmanager.down().items.get(1).hideLabel=true;
            projsmanager.down().items.get(2).hideLabel=true;
            projsmanager.down().items.get(3).hideLabel=true;
        }
        var scrollpos = button.up('window').getScrollY();
        button.up().add(projsmanager);
        button.up('window').scrollTo(0, scrollpos);
    },

    onDelMngClick: function(button, e, eOpts) {
        if(parseInt(button.up('form').getForm().findField('projsmanagersnum').getValue())!==1){
            button.up('form').getForm().findField('projsmanagersnum').setValue(parseInt(button.up('form').getForm().findField('projsmanagersnum').getValue())-1);
            var scrollpos = button.up('window').getScrollY();
            button.up().items.get(button.up().items.length-1).destroy();
            button.up('window').scrollTo(0, scrollpos);
        }

    },

    onAddContrClick: function(button, e, eOpts) {
        var projscontr = Ext.create('widget.companyprojectannformcontr', {});
        button.up('form').getForm().findField('projscontrsnum').setValue(parseInt(button.up('form').getForm().findField('projscontrsnum').getValue())+1);
        projscontr.items.getAt(0).items.getAt(0).setValue(button.up('form').getForm().findField('projscontrsnum').getValue()+". ");
        var scrollpos = button.up('window').getScrollY();
        button.up().add(projscontr);
        button.up('window').scrollTo(0, scrollpos);
    },

    onDelContrClick: function(button, e, eOpts) {
        if(parseInt(button.up('form').getForm().findField('projscontrsnum').getValue())!==1){
            button.up('form').getForm().findField('projscontrsnum').setValue(parseInt(button.up('form').getForm().findField('projscontrsnum').getValue())-1);
            var scrollpos = button.up('window').getScrollY();
            button.up().items.get(button.up().items.length-1).destroy();
            button.up('window').scrollTo(0, scrollpos);
        }

    },

    onAddStudierClick: function(button, e, eOpts) {
        var projstudier = Ext.create('widget.companyprojectannformmng_studier_selfempl', {});
        button.up('form').getForm().findField('projsstudiersnum').setValue(parseInt(button.up('form').getForm().findField('projsstudiersnum').getValue())+1);
        projstudier.down().items.get(4).setValue("2");
        if(parseInt(button.up('form').getForm().findField('projsstudiersnum').getValue())>1){
            projstudier.down().items.get(0).hideLabel=true;
            projstudier.down().items.get(1).hideLabel=true;
            projstudier.down().items.get(2).hideLabel=true;
            projstudier.down().items.get(3).hideLabel=true;
        }
        var scrollpos = button.up('window').getScrollY();
        button.up().add(projstudier);
        button.up('window').scrollTo(0, scrollpos);
    },

    onDelStudierClick: function(button, e, eOpts) {
        if(parseInt(button.up('form').getForm().findField('projsstudiersnum').getValue())!==1){
            button.up('form').getForm().findField('projsstudiersnum').setValue(parseInt(button.up('form').getForm().findField('projsstudiersnum').getValue())-1);
            var scrollpos = button.up('window').getScrollY();
            button.up().items.get(button.up().items.length-1).destroy();
            button.up('window').scrollTo(0, scrollpos);
        }

    },

    onAddEng1Click: function(button, e, eOpts) {
        var projseng = Ext.create('widget.companyprojectannformeng', {});
        button.up('form').getForm().findField('projsengs1num').setValue(parseInt(button.up('form').getForm().findField('projsengs1num').getValue())+1);
        projseng.items.getAt(1).items.getAt(0).items.getAt(6).setValue("1");
        projseng.items.getAt(0).items.getAt(0).setValue(button.up('form').getForm().findField('projsengs1num').getValue()+". ");
        var scrollpos = button.up('window').getScrollY();
        button.up().add(projseng);
        button.up('window').scrollTo(0, scrollpos);
    },

    onDelEng1Click: function(button, e, eOpts) {
        if(parseInt(button.up('form').getForm().findField('projsengs1num').getValue())!==1){
            button.up('form').getForm().findField('projsengs1num').setValue(parseInt(button.up('form').getForm().findField('projsengs1num').getValue())-1);
            var scrollpos = button.up('window').getScrollY();
            button.up().items.get(button.up().items.length-1).destroy();
            button.up('window').scrollTo(0, scrollpos);
        }

    },

    onAddSelfEmplClick: function(button, e, eOpts) {
        var projself = Ext.create('widget.companyprojectannformmng_studier_selfempl', {});
        button.up('form').getForm().findField('projsselfemplsnum').setValue(parseInt(button.up('form').getForm().findField('projsselfemplsnum').getValue())+1);
        projself.down().items.get(4).setValue("3");
        if(parseInt(button.up('form').getForm().findField('projsselfemplsnum').getValue())>1){
            projself.down().items.get(0).hideLabel=true;
            projself.down().items.get(1).hideLabel=true;
            projself.down().items.get(2).hideLabel=true;
            projself.down().items.get(3).hideLabel=true;
        }
        projself.down().items.get(0).allowOnlyWhitespace=true;
        projself.down().items.get(1).allowOnlyWhitespace=true;
        projself.down().items.get(2).allowOnlyWhitespace=true;
        projself.down().items.get(3).allowOnlyWhitespace=true;
        projself.down().items.get(0).allowBlank=true;
        projself.down().items.get(1).allowBlank=true;
        projself.down().items.get(2).allowBlank=true;
        projself.down().items.get(3).allowBlank=true;
        var scrollpos = button.up('window').getScrollY();
        button.up().add(projself);
        button.up('window').scrollTo(0, scrollpos);
    },

    onDelSelfEmplClick: function(button, e, eOpts) {
        if(parseInt(button.up('form').getForm().findField('projsselfemplsnum').getValue())!==1){
            button.up('form').getForm().findField('projsselfemplsnum').setValue(parseInt(button.up('form').getForm().findField('projsselfemplsnum').getValue())-1);
            var scrollpos = button.up('window').getScrollY();
            button.up().items.get(button.up().items.length-1).destroy();
            button.up('window').scrollTo(0, scrollpos);
        }

    },

    onAddEng2Click: function(button, e, eOpts) {
        var projseng = Ext.create('widget.companyprojectannformeng', {});
        button.up('form').getForm().findField('projsengs2num').setValue(parseInt(button.up('form').getForm().findField('projsengs2num').getValue())+1);
        projseng.items.getAt(1).items.getAt(0).items.getAt(6).setValue("2");
        projseng.items.getAt(0).items.getAt(0).setValue(button.up('form').getForm().findField('projsengs2num').getValue()+". ");

        projseng.items.getAt(1).items.getAt(0).items.getAt(0).allowOnlyWhitespace=true;
        projseng.items.getAt(1).items.getAt(0).items.getAt(1).allowOnlyWhitespace=true;
        projseng.items.getAt(1).items.getAt(0).items.getAt(2).allowOnlyWhitespace=true;
        projseng.items.getAt(1).items.getAt(0).items.getAt(3).allowOnlyWhitespace=true;
        projseng.items.getAt(1).items.getAt(0).items.getAt(4).allowOnlyWhitespace=true;
        projseng.items.getAt(1).items.getAt(0).items.getAt(5).allowOnlyWhitespace=true;
        projseng.items.getAt(1).items.getAt(0).items.getAt(0).allowBlank=true;
        projseng.items.getAt(1).items.getAt(0).items.getAt(1).allowBlank=true;
        projseng.items.getAt(1).items.getAt(0).items.getAt(2).allowBlank=true;
        projseng.items.getAt(1).items.getAt(0).items.getAt(3).allowBlank=true;
        projseng.items.getAt(1).items.getAt(0).items.getAt(4).allowBlank=true;
        projseng.items.getAt(1).items.getAt(0).items.getAt(5).allowBlank=true;
        var scrollpos = button.up('window').getScrollY();
        button.up().add(projseng);
        button.up('window').scrollTo(0, scrollpos);
    },

    onDelEng2Click: function(button, e, eOpts) {
        if(parseInt(button.up('form').getForm().findField('projsengs2num').getValue())!==1){
            button.up('form').getForm().findField('projsengs2num').setValue(parseInt(button.up('form').getForm().findField('projsengs2num').getValue())-1);
            var scrollpos = button.up('window').getScrollY();
            button.up().items.get(button.up().items.length-1).destroy();
            button.up('window').scrollTo(0, scrollpos);
        }

    },

    onAddEng3Click: function(button, e, eOpts) {
        var projseng = Ext.create('widget.companyprojectannformeng', {});
        button.up('form').getForm().findField('projsengs3num').setValue(parseInt(button.up('form').getForm().findField('projsengs3num').getValue())+1);
        projseng.items.getAt(1).items.getAt(0).items.getAt(6).setValue("3");
        projseng.items.getAt(0).items.getAt(0).setValue(button.up('form').getForm().findField('projsengs3num').getValue()+". ");
        var scrollpos = button.up('window').getScrollY();
        button.up().add(projseng);
        button.up('window').scrollTo(0, scrollpos);
    },

    onDelEng3Click: function(button, e, eOpts) {
        if(parseInt(button.up('form').getForm().findField('projsengs3num').getValue())!==1){
            button.up('form').getForm().findField('projsengs3num').setValue(parseInt(button.up('form').getForm().findField('projsengs3num').getValue())-1);
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
        var viewsub = Ext.create('MyApp.view.company.ProjectAnnPanel');
        center.add(viewsub);
    },

    onCompProjAnn_save_submit_toolbarBeforeHide: function(component, eOpts) {
        component.getComponent('deletebutton').destroy();
        component.getComponent('savebutton').destroy();
        component.getComponent('submitbutton').destroy();
    }

});