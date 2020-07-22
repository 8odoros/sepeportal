/*
 * File: app/view/company/DangerForm/DangerForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.DangerForm.DangerForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companydangerformdangerform',

    requires: [
        'MyApp.view.company.DangerForm.DangerFormViewModel',
        'MyApp.view.company.DangerForm.DangerFormViewController',
        'MyApp.view.shared.PrintFormTool',
        'MyApp.view.shared.CloseFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.FieldSet',
        'Ext.form.field.ComboBox',
        'Ext.form.field.Date',
        'Ext.form.RadioGroup',
        'Ext.form.field.Radio',
        'Ext.form.field.TextArea',
        'Ext.form.field.File',
        'Ext.form.Label',
        'Ext.button.Button',
        'Ext.toolbar.Toolbar',
        'Ext.panel.Tool'
    ],

    controller: 'companydangerformdangerform',
    viewModel: {
        type: 'companydangerformdangerform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    overflowY: 'scroll',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Φόρμα Γραπτής Εκτίμησης Επαγγελματικού Κινδύνου (ΓΕΕΚ)',
    modal: true,
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
            id: 'employeecomplaintform5',
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
                    title: 'ΣΤΟΙΧΕΙΑ ΕΚΤΙΜΗΣΗΣ',
                    items: [
                        {
                            xtype: 'container',
                            focusOnToFront: false,
                            toFrontOnShow: false,
                            layout: 'anchor',
                            items: [
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Παράρτημα που αφορά',
                                    labelWidth: 180,
                                    name: 'branch1Id',
                                    validateOnChange: false,
                                    readOnly: true,
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
                                    listeners: {
                                        beforequery: 'onBranchExpand'
                                    }
                                }
                            ]
                        },
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            margin: '0 0 10 0',
                            fieldLabel: 'Ημερομηνία Επεξεργασίας',
                            labelWidth: 180,
                            name: 'dateUpdated',
                            submitValue: false,
                            validateOnChange: false,
                            readOnly: true,
                            validateOnBlur: false,
                            editable: false,
                            format: 'd-m-Y'
                        },
                        {
                            xtype: 'radiogroup',
                            anchor: '70%',
                            fieldLabel: 'Μελέτη επικινδυνότητας χώρου εργασίας',
                            labelAlign: 'top',
                            labelWidth: 250,
                            validateOnChange: false,
                            allowBlank: false,
                            items: [
                                {
                                    xtype: 'radiofield',
                                    name: 'dngAreaStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Ναι',
                                    inputValue: '1'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'dngAreaStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Οχι',
                                    inputValue: '0'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'dngAreaStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Δεν εφαρμόζεται',
                                    inputValue: '2'
                                }
                            ]
                        },
                        {
                            xtype: 'radiogroup',
                            anchor: '70%',
                            fieldLabel: 'Μελέτη επικινδυνότητας εξοπλισμού εργασίας',
                            labelAlign: 'top',
                            labelWidth: 250,
                            validateOnChange: false,
                            allowBlank: false,
                            items: [
                                {
                                    xtype: 'radiofield',
                                    name: 'dngEquipmentStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Ναι',
                                    inputValue: '1'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'dngEquipmentStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Οχι',
                                    inputValue: '0'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'dngEquipmentStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Δεν εφαρμόζεται',
                                    inputValue: '2'
                                }
                            ]
                        },
                        {
                            xtype: 'radiogroup',
                            anchor: '70%',
                            fieldLabel: 'Μελέτη επικινδυνότητας αυτοκινούμενου εξοπλισμού εργασίας',
                            labelAlign: 'top',
                            labelWidth: 250,
                            validateOnChange: false,
                            allowBlank: false,
                            items: [
                                {
                                    xtype: 'radiofield',
                                    name: 'dngVehicleStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Ναι',
                                    inputValue: '1'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'dngVehicleStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Οχι',
                                    inputValue: '0'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'dngVehicleStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Δεν εφαρμόζεται',
                                    inputValue: '2'
                                }
                            ]
                        },
                        {
                            xtype: 'radiogroup',
                            anchor: '70%',
                            fieldLabel: 'Μελέτη επικινδυνότητας ηλεκτρολογικής εγκατάστασης',
                            labelAlign: 'top',
                            labelWidth: 250,
                            validateOnChange: false,
                            allowBlank: false,
                            items: [
                                {
                                    xtype: 'radiofield',
                                    name: 'dngElectricalStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Ναι',
                                    inputValue: '1'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'dngElectricalStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Οχι',
                                    inputValue: '0'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'dngElectricalStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Δεν εφαρμόζεται',
                                    inputValue: '2'
                                }
                            ]
                        },
                        {
                            xtype: 'radiogroup',
                            anchor: '70%',
                            fieldLabel: 'Ενεργητική/Παθητική Πυροπροστασία - Πιστοποιητικό Πυροσβεστικής',
                            labelAlign: 'top',
                            labelWidth: 250,
                            validateOnChange: false,
                            allowBlank: false,
                            items: [
                                {
                                    xtype: 'radiofield',
                                    name: 'dngFireStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Ναι',
                                    inputValue: '1'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'dngFireStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Οχι',
                                    inputValue: '0'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'dngFireStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Δεν εφαρμόζεται',
                                    inputValue: '2'
                                }
                            ]
                        },
                        {
                            xtype: 'radiogroup',
                            anchor: '70%',
                            margin: '0 0 10 0',
                            fieldLabel: 'Μελέτη επικινδυνότητας θέσεων εργασίας',
                            labelAlign: 'top',
                            labelWidth: 250,
                            validateOnChange: false,
                            allowBlank: false,
                            items: [
                                {
                                    xtype: 'radiofield',
                                    name: 'dngJobpositionStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Ναι',
                                    inputValue: '1'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'dngJobpositionStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Οχι',
                                    inputValue: '0'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'dngJobpositionStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Δεν εφαρμόζεται',
                                    inputValue: '2'
                                }
                            ]
                        },
                        {
                            xtype: 'radiogroup',
                            anchor: '70%',
                            fieldLabel: 'Μετρήσεις θορύβου',
                            labelAlign: 'top',
                            labelWidth: 250,
                            validateOnChange: false,
                            allowBlank: false,
                            items: [
                                {
                                    xtype: 'radiofield',
                                    name: 'dngNoiseStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Ναι',
                                    inputValue: '1'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'dngNoiseStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Οχι',
                                    inputValue: '0'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'dngNoiseStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Δεν εφαρμόζεται',
                                    inputValue: '2'
                                }
                            ]
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            margin: '0 0 10 5',
                            labelAlign: 'top',
                            labelWidth: 180,
                            name: 'dngNoiseDescr',
                            validateOnChange: false,
                            validateOnBlur: false,
                            emptyText: 'Αναφέρετε υπερβάσεις',
                            maxLength: 1000
                        },
                        {
                            xtype: 'radiogroup',
                            anchor: '70%',
                            fieldLabel: 'Μετρήσεις χημικών παραγόντων',
                            labelAlign: 'top',
                            labelWidth: 250,
                            validateOnChange: false,
                            allowBlank: false,
                            items: [
                                {
                                    xtype: 'radiofield',
                                    name: 'dngChemicalStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Ναι',
                                    inputValue: '1'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'dngChemicalStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Οχι',
                                    inputValue: '0'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'dngChemicalStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Δεν εφαρμόζεται',
                                    inputValue: '2'
                                }
                            ]
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            margin: '0 0 10 5',
                            labelAlign: 'top',
                            labelWidth: 180,
                            name: 'dngChemicalDescr',
                            validateOnChange: false,
                            validateOnBlur: false,
                            emptyText: 'Αναφέρετε υπερβάσεις',
                            maxLength: 1000
                        },
                        {
                            xtype: 'radiogroup',
                            anchor: '70%',
                            fieldLabel: 'Ύπαρξη άλλων παραγόντων (μόλυβδος, αμίαντος κ.λ.π.)',
                            labelAlign: 'top',
                            labelWidth: 250,
                            validateOnChange: false,
                            allowBlank: false,
                            items: [
                                {
                                    xtype: 'radiofield',
                                    name: 'dngOtherStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Ναι',
                                    inputValue: '1'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'dngOtherStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Οχι',
                                    inputValue: '0'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'dngOtherStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Δεν εφαρμόζεται',
                                    inputValue: '2'
                                }
                            ]
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            margin: '0 0 10 5',
                            labelAlign: 'top',
                            labelWidth: 180,
                            name: 'dngOtherDescr',
                            validateOnChange: false,
                            validateOnBlur: false,
                            emptyText: 'Αναφέρετε υπερβάσεις',
                            maxLength: 1000
                        },
                        {
                            xtype: 'radiogroup',
                            anchor: '70%',
                            fieldLabel: 'Χειρωνακτική διακίνηση φορτίων/μυοσκελετική κατάσταση εργαζομένων',
                            labelAlign: 'top',
                            labelWidth: 250,
                            validateOnChange: false,
                            allowBlank: false,
                            items: [
                                {
                                    xtype: 'radiofield',
                                    name: 'dngEmplhealthStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Ναι',
                                    inputValue: '1'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'dngEmplhealthStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Οχι',
                                    inputValue: '0'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'dngEmplhealthStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Δεν εφαρμόζεται',
                                    inputValue: '2'
                                }
                            ]
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            margin: '0 0 10 5',
                            labelAlign: 'top',
                            labelWidth: 180,
                            name: 'dngEmplhealthDescr',
                            validateOnChange: false,
                            validateOnBlur: false,
                            emptyText: 'Αναφέρετε υπερβάσεις',
                            maxLength: 1000
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            fieldLabel: 'Παρατηρήσεις',
                            labelAlign: 'top',
                            labelWidth: 180,
                            name: 'notes',
                            validateOnChange: false,
                            validateOnBlur: false,
                            emptyText: 'Καταχώρηση παρατηρήσεων και ληπτέων μέτρων για τους μεγαλύτερους κινδύνους βάσει της εκτίμησης που έχει γίνει.',
                            maxLength: 3000
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
                            id: 'complfile7',
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
                    submitValue: false,
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    flex: 1,
                    labelWidth: 180,
                    msgTarget: 'under',
                    name: 'branch0Id',
                    validateOnChange: false,
                    value: 0
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
        beforedestroy: 'onWindowBeforeDestroy'
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
            id: 'compdanger_save_submit_toolbar',
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
                    itemId: 'savebutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf0c7@FontAwesome',
                    text: 'Αποθήκευση',
                    listeners: {
                        click: {
                            fn: 'onSave_COMPANY_DANGER',
                            scope: 'controller'
                        }
                    }
                }
            ],
            listeners: {
                beforehide: 'onDanger_save_submit_toolbarBeforeHide'
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
        var viewsub = Ext.create('MyApp.view.company.DangersPanel');
        center.add(viewsub);
    },

    onDanger_save_submit_toolbarBeforeHide: function(component, eOpts) {
        component.getComponent('savebutton').destroy();
    }

});