/*
 * File: app/view/company/SafetyBook/SafetyBookForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.SafetyBook.SafetyBookForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companysafetybooksafetybookform',

    requires: [
        'MyApp.view.company.SafetyBook.SafetyBookFormViewModel',
        'MyApp.view.company.SafetyBook.SafetyBookFormViewController',
        'MyApp.view.shared.PrintFormTool',
        'MyApp.view.shared.CloseFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.FieldSet',
        'Ext.form.field.Number',
        'Ext.form.field.Date',
        'Ext.form.field.ComboBox',
        'Ext.form.field.TextArea',
        'Ext.button.Button',
        'Ext.toolbar.Toolbar',
        'Ext.panel.Tool'
    ],

    controller: 'companysafetybooksafetybookform',
    viewModel: {
        type: 'companysafetybooksafetybookform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    overflowY: 'scroll',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Φόρμα Ημερολογίου Μέτρων Ασφάλειας',
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
            id: 'companysafetybookform',
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
                    title: 'ΣΤΟΙΧΕΙΑ ΗΜΕΡΟΛΟΓΙΟΥ',
                    items: [
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            disabled: true,
                            fieldLabel: 'Αρ. Πρωτοκόλλου',
                            labelWidth: 180,
                            name: 'protNo',
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'numberfield',
                            anchor: '100%',
                            cls: 'x-item-disabled',
                            fieldLabel: 'Έτος Έκδοσης',
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
                            xtype: 'datefield',
                            anchor: '100%',
                            disabled: true,
                            width: 150,
                            fieldLabel: 'Ημερομηνία Δημιουργίας',
                            labelWidth: 180,
                            name: 'protDate',
                            submitValue: false,
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            format: 'd-m-Y'
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            cls: 'x-item-disabled',
                            disabled: true,
                            fieldLabel: 'Αρμόδια Διεύθυνση (ΤΚΕ)',
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
                        }
                    ]
                },
                {
                    xtype: 'fieldset',
                    flex: 1,
                    focusOnToFront: false,
                    padding: 10,
                    toFrontOnShow: false,
                    title: 'ΣΤΟΙΧΕΙΑ ΕΡΓΟΥ',
                    items: [
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Αριθμός Άδειας',
                            labelWidth: 180,
                            name: 'projLicenceNum',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            fieldLabel: 'Περιγραφή Τεχνικού Έργου',
                            labelAlign: 'top',
                            labelWidth: 180,
                            name: 'projDescr',
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
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 50,
                                    minLength: 5,
                                    vtype: 'Number'
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype: 'fieldset',
                    flex: 1,
                    focusOnToFront: false,
                    padding: 10,
                    toFrontOnShow: false,
                    title: 'ΣΤΟΙΧΕΙΑ ΙΔΙΟΚΤΗΤΗ',
                    items: [
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Όνομα',
                            labelWidth: 180,
                            name: 'ownerFirstname',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Επώνυμο',
                            labelWidth: 180,
                            name: 'ownerLastname',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Τόπος Γέννησης',
                            labelWidth: 180,
                            name: 'ownerBirthplace',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            fieldLabel: 'Ημ. Γέννησης',
                            labelWidth: 180,
                            name: 'ownerBirthdate',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50,
                            format: 'd-m-Y',
                            submitFormat: 'Y-m-d\\T00:00:00.000+0000'
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            fieldLabel: 'Διεύθυνση Κατοικίας',
                            labelWidth: 180,
                            name: 'ownerAddr',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Αριθμός Δελτίου Ταυτότητας',
                            labelWidth: 180,
                            name: 'ownerCardNumber',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'hiddenfield',
                            anchor: '100%',
                            fieldLabel: 'Αριθμός Δελτίου',
                            labelWidth: 180,
                            name: 'ownerCardType',
                            validateOnChange: false
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Εκδίδουσα Άρχη',
                            labelWidth: 180,
                            name: 'ownerCardIssuingAuth',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Α.Φ.Μ.',
                            labelWidth: 180,
                            name: 'ownerAfm',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 9,
                            minLength: 9,
                            maskRe:/[0-9.]/
                        }
                    ]
                },
                {
                    xtype: 'fieldset',
                    flex: 1,
                    id: 'bookengs',
                    title: 'ΕΠΙΒΛΕΠΩΝ ΜΗΧΑΝΙΚΟΣ(ΟΙ)',
                    items: [
                        {
                            xtype: 'button',
                            border: 0,
                            frame: false,
                            id: 'bookengsadd',
                            style: 'background-color:transparent;',
                            iconCls: 'addone',
                            tooltip: 'Προσθήκη Επιβλέπων',
                            listeners: {
                                click: 'onAddEngClick'
                            }
                        },
                        {
                            xtype: 'button',
                            border: 0,
                            frame: false,
                            id: 'bookengsdel',
                            style: 'background-color:transparent;',
                            iconCls: 'removeone',
                            tooltip: 'Διαγραφή τελευταίου Επιβλέπων',
                            listeners: {
                                click: 'onDelEngClick'
                            }
                        },
                        {
                            xtype: 'hiddenfield',
                            anchor: '100%',
                            labelAlign: 'top',
                            labelWidth: 180,
                            name: 'projsengsnum',
                            validateOnChange: false,
                            value: 1
                        },
                        {
                            xtype: 'hiddenfield',
                            anchor: '100%',
                            labelWidth: 180,
                            msgTarget: 'under',
                            name: 'projsengs',
                            validateOnChange: false
                        }
                    ]
                },
                {
                    xtype: 'fieldset',
                    flex: 1,
                    id: 'bookcontrs',
                    title: 'ΕΡΓΟΛΑΒΟΣ(ΟΙ) & ΥΠΕΡΓΟΛΑΒΟΣ(ΟΙ)',
                    items: [
                        {
                            xtype: 'button',
                            border: 0,
                            frame: false,
                            id: 'bookcontrsadd',
                            style: 'background-color:transparent;',
                            iconCls: 'addone',
                            tooltip: 'Προσθήκη',
                            listeners: {
                                click: 'onAddContrClick'
                            }
                        },
                        {
                            xtype: 'button',
                            border: 0,
                            frame: false,
                            id: 'bookcontrsdel',
                            style: 'background-color:transparent;',
                            iconCls: 'removeone',
                            tooltip: 'Διαγραφή τελευταίου',
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
                    xtype: 'hiddenfield',
                    width: 150,
                    fieldLabel: 'Όνομα',
                    labelWidth: 180,
                    name: 'docId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    flex: 1,
                    fieldLabel: 'Αριθμός Υπόθεσης',
                    labelWidth: 180,
                    name: 'caseId'
                },
                {
                    xtype: 'hiddenfield',
                    flex: 1,
                    fieldLabel: 'Κατάσταση',
                    labelWidth: 180,
                    name: 'subStatus',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    flex: 1,
                    fieldLabel: 'Κατάσταση',
                    labelWidth: 180,
                    name: 'companyId',
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
                else return null;
            },
            dock: 'bottom',
            html: '<span style="color:#696969;font-size:11px;"><em>*Τα ανενεργά πεδία θα συμπληρωθούν αυτόματα<br>από το σύστημα κατά την υποβολή</em></span>',
            id: 'compsafetybook_save_submit_toolbar',
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
                            fn: 'onDelete_COMPANY_SAFETYBOOK',
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
                            fn: 'onSave_COMPANY_SAFETYBOOK',
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
                            fn: 'onSubmit_COMPANY_SAFETYBOOK',
                            scope: 'controller'
                        }
                    }
                }
            ],
            listeners: {
                beforehide: 'onCompSafetyBook_save_submit_toolbarBeforeHide'
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

    onAddEngClick: function(button, e, eOpts) {
        var projseng = Ext.create('widget.companysafetybookeng', {});
        button.up('form').getForm().findField('projsengsnum').setValue(parseInt(button.up('form').getForm().findField('projsengsnum').getValue())+1);
        projseng.items.getAt(0).items.getAt(0).setValue(button.up('form').getForm().findField('projsengsnum').getValue()+". ");
        var scrollpos = button.up('window').getScrollY();
        button.up().add(projseng);
        button.up('window').scrollTo(0, scrollpos);
    },

    onDelEngClick: function(button, e, eOpts) {
        if(parseInt(button.up('form').getForm().findField('projsengsnum').getValue())!==1){
            button.up('form').getForm().findField('projsengsnum').setValue(parseInt(button.up('form').getForm().findField('projsengsnum').getValue())-1);
            var scrollpos = button.up('window').getScrollY();
            button.up().items.get(button.up().items.length-1).destroy();
            button.up('window').scrollTo(0, scrollpos);
        }
    },

    onAddContrClick: function(button, e, eOpts) {
        var projscontr = Ext.create('widget.companysafetybookcontr', {});
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

    onWindowBeforeDestroy: function(component, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.company.SafetyBooksPanel');
        center.add(viewsub);
    },

    onCompSafetyBook_save_submit_toolbarBeforeHide: function(component, eOpts) {
        component.getComponent('deletebutton').destroy();
        component.getComponent('savebutton').destroy();
        component.getComponent('submitbutton').destroy();
    }

});