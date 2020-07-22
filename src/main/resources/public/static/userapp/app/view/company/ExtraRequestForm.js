/*
 * File: app/view/company/ExtraRequestForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.ExtraRequestForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companyextrarequestform',

    requires: [
        'MyApp.view.company.ExtraRequestFormViewModel',
        'MyApp.view.company.ExtraRequestFormViewController',
        'MyApp.view.shared.PrintFormTool',
        'MyApp.view.shared.CloseFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.FieldSet',
        'Ext.form.field.Date',
        'Ext.form.field.Number',
        'Ext.form.field.ComboBox',
        'Ext.form.field.TextArea',
        'Ext.form.field.File',
        'Ext.form.Label',
        'Ext.button.Button',
        'Ext.toolbar.Toolbar',
        'Ext.panel.Tool'
    ],

    controller: 'companyextrarequestform',
    viewModel: {
        type: 'companyextrarequestform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    overflowY: 'scroll',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Φόρμα Υποβολής Πρόσθετων Στοιχείων',
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
            id: 'companyextrarequest',
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
                            displayField: 'cdText',
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
                                    hidden: true,
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
                                            //allowBlank: false,
                                            //allowOnlyWhitespace: false,
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
                                            //allowBlank: false,
                                            //allowOnlyWhitespace: false,
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
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Ιατρός Εργασίας',
                                    labelWidth: 180,
                                    name: 'ieFullname',
                                    hidden: true,
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    //allowBlank: false,
                                    //allowOnlyWhitespace: false,
                                    maxLength: 200
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Τεχνικός Ασφάλειας',
                                    labelWidth: 180,
                                    name: 'taFullname',
                                    hidden: true,
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    //allowBlank: false,
                                    //allowOnlyWhitespace: false,
                                    emptyText: '',
                                    maxLength: 200
                                }
                            ]
                        },
                        {
                            xtype: 'container',
                            focusOnToFront: false,
                            toFrontOnShow: false,
                            layout: {
                                type: 'vbox',
                                align: 'stretch'
                            },
                            items: [
                                {
                                    xtype: 'combobox',
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
                                    listeners: {
                                        beforequery: 'onBranchExpand'
                                    }
                                },
                                {
                                    xtype: 'hiddenfield',
                                    labelWidth: 180,
                                    name: 'branch0Id',
                                    validateOnChange: false,
                                    value: 0
                                }
                            ]
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            fieldLabel: 'Τομέας Επιθεώρησης',
                            labelWidth: 180,
                            name: 'teKe',
                            validateOnChange: false,
                            //readOnly: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            editable: false,
                            autoLoadOnValue: true,
                            displayField: 'name',
                            forceSelection: true,
                            queryMode: 'local',
                            valueField: 'abbr',
                            bind: {
                                store: '{teKeCat}'
                            },
                            listeners: {
                                select: 'onTeKeSelect'
                            }
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            disabled: true,
                            fieldLabel: 'Αρμόδια Υπηρεσία ΣΕΠΕ',
                            labelWidth: 180,
                            name: 'sepeDeptCombobox',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            editable: false,
                            displayField: 'cdText',
                            store: 'shared.SEPE_DEPT',
                            valueField: 'abbr',
                            queryParam: 'cdKind',
                            listeners: {
                                beforequery: 'onSepeDeptByKindExpand211',
                                dirtychange: {
                                    fn: 'onSepeDeptByKindDirtyChange1',
                                    single: true
                                }
                            }
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Αρμόδιος Επιθεωρητής',
                            labelWidth: 180,
                            name: 'inspectorName',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 128
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Αρ. Δελτίου Ελέγχου / Αρ. Πρωτ. Εγγράφου',
                            labelWidth: 180,
                            name: 'controlStatementNum',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            fieldLabel: 'Ημερομηνία Εγγράφου',
                            labelWidth: 180,
                            name: 'inspectionDate',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50,
                            format: 'd-m-Y',
                            submitFormat: 'Y-m-d\\T00:00:00.000+0000'
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
                            id: 'compextrareqfile',
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
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
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
                            fieldLabel: 'Περιγραφή Αρχείου',
                            labelWidth: 180,
                            name: 'attachedDocDescr',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 300
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            fieldLabel: 'Παρατηρήσεις',
                            labelWidth: 180,
                            name: 'descr',
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
            id: 'compextrareq_save_submit_toolbar',
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
                            fn: 'onDelete_COMPANY_EXTRA_REQ',
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
                            fn: 'onSave_COMPANY_EXTRA_REQ',
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
                            fn: 'onSubmit_COMPANY_EXTRA_REQ',
                            scope: 'controller'
                        }
                    }
                }
            ],
            listeners: {
                beforehide: 'onCompExtraReq_save_submit_toolbarBeforeHide'
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

    onTeKeSelect: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('sepeDeptCombobox');
        getnext.clearValue();
        getnext.enable();
    },

    onSepeDeptByKindExpand211: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TSepeDepartment");
        queryPlan.combo.getStore().getProxy().url = "/TSepeDepartment/search/findByKindActive";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('teKe').getValue();
    },

    onSepeDeptByKindDirtyChange1: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue() !== "true") {
            field.getStore().getProxy().url = "/TSepeDepartment/search/findById" + "?cdId=" + field.getValue() + " ";
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
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
        var viewsub = Ext.create('MyApp.view.company.ExtraRequestPanel');
        center.add(viewsub);
    },

    onCompExtraReq_save_submit_toolbarBeforeHide: function(component, eOpts) {
        component.getComponent('deletebutton').destroy();
        component.getComponent('savebutton').destroy();
        component.getComponent('submitbutton').destroy();
    }

});