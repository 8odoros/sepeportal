/*
 * File: app/view/technician/ship/CompanyInfoViewForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.technician.ship.CompanyInfoViewForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.technicianshipcompanyinfoviewform',

    requires: [
        'MyApp.view.technician.ship.CompanyInfoViewFormViewModel',
        'MyApp.view.technician.ship.CompanyInfoViewFormViewController',
        'MyApp.view.shared.PrintFormTool',
        'MyApp.view.shared.CloseFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.FieldSet',
        'Ext.form.field.Date',
        'Ext.form.field.TextArea',
        'Ext.form.field.ComboBox',
        'Ext.panel.Tool',
        'Ext.toolbar.Toolbar',
        'Ext.button.Button'
    ],

    controller: 'technicianshipcompanyinfoviewform',
    viewModel: {
        type: 'technicianshipcompanyinfoviewform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    overflowY: 'scroll',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Στοιχεία Εργοδότη/Επιχείρησης',
    modal: true,
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'form',
            id: 'techniciancompanyinfoform1',
            padding: 15,
            title: '',
            layout: {
                type: 'vbox',
                align: 'stretch'
            },
            items: [
                {
                    xtype: 'hiddenfield',
                    fieldLabel: '',
                    name: 'a_new_form',
                    submitValue: false,
                    validateOnChange: false,
                    value: true
                },
                {
                    xtype: 'hiddenfield',
                    fieldLabel: '',
                    name: 'only_view',
                    submitValue: false,
                    validateOnChange: false,
                    value: true
                },
                {
                    xtype: 'fieldset',
                    flex: 1,
                    focusOnToFront: false,
                    toFrontOnShow: false,
                    title: 'Διάρκεια Αναγγελίας',
                    items: [
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            fieldLabel: 'Από',
                            msgTarget: 'under',
                            name: 'dateStart',
                            validateOnChange: false,
                            readOnly: true,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 100,
                            format: 'd-m-Y',
                            submitFormat: 'Y-m-d\\T00:00:00.000+0000'
                        },
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            fieldLabel: 'Έως',
                            msgTarget: 'under',
                            name: 'dateEnd',
                            validateOnChange: false,
                            readOnly: true,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 100,
                            format: 'd-m-Y',
                            submitFormat: 'Y-m-d\\T00:00:00.000+0000'
                        }
                    ]
                },
                {
                    xtype: 'fieldset',
                    flex: 1,
                    focusOnToFront: false,
                    toFrontOnShow: false,
                    title: 'Στοιχεία Πλοίου',
                    items: [
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            disabled: true,
                            fieldLabel: 'Όνομα Πλοίου',
                            labelWidth: 180,
                            msgTarget: 'under',
                            name: 'shipName',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 200
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            disabled: true,
                            fieldLabel: 'Αριθμός ΙΜΟ',
                            labelWidth: 180,
                            msgTarget: 'under',
                            name: 'shipImo',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 200
                        }
                    ]
                },
                {
                    xtype: 'fieldset',
                    flex: 1,
                    focusOnToFront: false,
                    padding: 10,
                    toFrontOnShow: false,
                    title: '',
                    items: [
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            disabled: true,
                            fieldLabel: 'Επωνυμία ή Ονοματεπώνυμο',
                            labelWidth: 180,
                            name: 'compFullName',
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
                            name: 'compTaxNumber',
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
                        }
                    ]
                },
                {
                    xtype: 'hiddenfield',
                    width: 150,
                    fieldLabel: 'Όνομα',
                    labelWidth: 180,
                    name: 'technicianRegrequestId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    width: 150,
                    fieldLabel: 'Όνομα',
                    labelWidth: 180,
                    name: 'technicianRegrequestUserId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    width: 150,
                    fieldLabel: 'Όνομα',
                    labelWidth: 180,
                    name: 'compTaSannId',
                    validateOnChange: false
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
    tools: [
        {
            xtype: 'sharedprintformtool'
        },
        {
            xtype: 'sharedcloseformtool'
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
            flex: 1,
            dock: 'bottom',
            html: '<span style="color:#696969;font-size:12px;"><em><br>*Επιλέξτε Αποδοχή ή Απόρριψη της αναγγελίας</em></span>',
            id: 'companySTaInfoForm_accept_reject_toolbar',
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
                    itemId: 'submitbutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf00c@FontAwesome',
                    text: 'Αποδοχή',
                    listeners: {
                        click: {
                            fn: 'onAccept_COMPANY_ANN',
                            scope: 'controller'
                        }
                    }
                },
                {
                    xtype: 'button',
                    itemId: 'submitbutton1',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf00d@FontAwesome',
                    text: 'Απόρριψη',
                    listeners: {
                        click: {
                            fn: 'onReject_COMPANY_ANN',
                            scope: 'controller'
                        }
                    }
                }
            ]
        },
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
            flex: 1,
            dock: 'bottom',
            hidden: true,
            html: '<span style="color:#696969;font-size:12px;"><em><br>*Επιλέξτε Αποδοχή ή Απόρριψη της αναγγελίας</em></span>',
            id: 'companySTaInfoForm_replace_action_toolbar',
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
                    itemId: 'submitbutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 200,
                    glyph: 'xf087@FontAwesome',
                    text: 'Οικιοθελώς Παραίτηση',
                    listeners: {
                        click: {
                            fn: 'onAcceptReplace_COMPANY_ANN',
                            scope: 'controller'
                        }
                    }
                },
                {
                    xtype: 'button',
                    itemId: 'submitbutton1',
                    maxWidth: 340,
                    padding: 10,
                    width: 200,
                    glyph: 'xf164@FontAwesome',
                    text: 'Αποδοχή Παύσης',
                    listeners: {
                        click: {
                            fn: 'onResignationReplace_COMPANY_ANN',
                            scope: 'controller'
                        }
                    }
                }
            ]
        },
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
            flex: 1,
            dock: 'bottom',
            hidden: true,
            html: '<span style="color:#696969;font-size:12px;"><em><br>* Επιλέξτε Παραίτηση για διακοπή σύμβασης</em></span>',
            id: 'companySTaInfoForm_resignation_action_toolbar',
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
                    itemId: 'submitbutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 200,
                    glyph: 'xf087@FontAwesome',
                    text: 'Παραίτηση',
                    listeners: {
                        click: {
                            fn: 'onResignation_COMPANY_ANN',
                            scope: 'controller'
                        }
                    }
                }
            ]
        }
    ],
    listeners: {
        beforedestroy: 'onWindowBeforeDestroy'
    },

    onAddrPSelect: function(combo, records, eOpts) {
                var getnext = combo.up('form').getForm().findField('compAddrPe');
                getnext.clearValue();
         getnext.enable();
    },

    onAddrPeExpand: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
                queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
                queryPlan.query=queryPlan.combo.up('form').getForm().findField('compAddrP').getValue()+" ";
    },

    onAddrPeSelect: function(combo, records, eOpts) {
                var getnext = combo.up('form').getForm().findField('compAddrD');
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
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('compAddrPe').getValue()+" ";
    },

    onAddrDSelect: function(combo, records, eOpts) {
                var getnext = combo.up('form').getForm().findField('compAddrK');
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
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('compAddrD').getValue();
    },

    onAddrKDirtyChange: function(field, isDirty, eOpts) {
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

    onWindowBeforeDestroy: function(component, eOpts) {
        if(component.down('form').getForm().findField('only_view').getValue()==="false"){
            var view=Ext.getCmp('technicianmainView');
            var center = view.getComponent('contentPanel');
            center.removeAll();
            var viewsub = Ext.create('MyApp.view.technician.ship.PendingCompanyAnnsPanel');
            center.add(viewsub);
        }
        else if(component.down('form').getForm().findField('only_view').getValue()==="replaced"){
            var view=Ext.getCmp('technicianmainView');
            var center = view.getComponent('contentPanel');
            center.removeAll();
            var viewsub = Ext.create('MyApp.view.technician.ship.ReplacedCompanyAnnsPanel');
            center.add(viewsub);
        }
        else if(component.down('form').getForm().findField('only_view').getValue()==="resignation"){
            var view=Ext.getCmp('technicianmainView');
            var center = view.getComponent('contentPanel');
            center.removeAll();
            var viewsub = Ext.create('MyApp.view.technician.ship.ResignationsCompanyAnnsPanel');
            center.add(viewsub);
        }
    }

});