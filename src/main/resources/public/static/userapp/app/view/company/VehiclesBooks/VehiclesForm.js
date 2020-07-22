/*
 * File: app/view/company/VehiclesBooks/VehiclesForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.VehiclesBooks.VehiclesForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companyvehiclesbooksvehiclesform',

    requires: [
        'MyApp.view.company.VehiclesBooks.VehiclesFormViewModel',
        'MyApp.view.company.VehiclesBooks.VehiclesFormViewController',
        'MyApp.view.shared.PrintFormTool',
        'MyApp.view.shared.CloseFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.field.Date',
        'Ext.form.FieldSet',
        'Ext.form.field.Number',
        'Ext.form.field.ComboBox',
        'Ext.form.field.TextArea',
        'Ext.toolbar.Toolbar',
        'Ext.button.Button',
        'Ext.panel.Tool'
    ],

    controller: 'companyvehiclesbooksvehiclesform',
    viewModel: {
        type: 'companyvehiclesbooksvehiclesform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    overflowY: 'scroll',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Καταχώρηση Νέου Αυτοκινήτου',
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
            id: 'vehiclesbooksvehicles',
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
                    xtype: 'datefield',
                    disabled: true,
                    disabledCls: 'x-form-readonly',
                    width: 150,
                    fieldLabel: 'Ημερομηνία Δημιουργίας',
                    labelWidth: 180,
                    name: 'creationDate',
                    submitValue: false,
                    validateOnChange: false,
                    validateOnBlur: false,
                    editable: false,
                    format: 'd-m-Y'
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
                            name: 'protNo',
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            disabled: true,
                            width: 150,
                            fieldLabel: 'Ημ. Πρωτοκόλλου',
                            labelWidth: 180,
                            name: 'protDate',
                            submitValue: false,
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            format: 'd-m-Y'
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
                                        select: 'onAddrPSelect2111'
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
                                        beforequery: 'onAddrPeExpand2111',
                                        select: 'onAddrPeSelect2111',
                                        dirtychange: {
                                            fn: 'onAddrPeDirtyChange2111',
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
                                        beforequery: 'onAddrDExpand2111',
                                        select: 'onAddrDSelect2111',
                                        dirtychange: {
                                            fn: 'onAddrDDirtyChange2111',
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
                                        beforequery: 'onAddrKExpand2111',
                                        dirtychange: {
                                            fn: 'onAddrKDirtyChange2111',
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
                    xtype: 'fieldset',
                    flex: 1,
                    focusOnToFront: false,
                    padding: 10,
                    toFrontOnShow: false,
                    title: 'ΣΤΟΙΧΕΙΑ ΟΧΗΜΑΤΟΣ',
                    items: [
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Αρ. Άδειας Κυκλοφορίας',
                            labelWidth: 180,
                            name: 'vehicleLicenceNum',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            fieldLabel: 'Τύπος Οχήματος',
                            labelWidth: 180,
                            name: 'vehicleType',
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            autoLoadOnValue: true,
                            displayField: 'description',
                            forceSelection: true,
                            valueField: 'abbr',
                            bind: {
                                store: '{VEHICLE_TYPES}'
                            }
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
                        },
                        {
                            xtype: 'hiddenfield',
                            height: 150,
                            labelWidth: 180,
                            name: 'ownerCardType',
                            validateOnChange: false
                        }
                    ]
                },
                {
                    xtype: 'hiddenfield',
                    height: 150,
                    labelWidth: 180,
                    name: 'url',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    height: 150,
                    labelWidth: 180,
                    name: 'companyId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    height: 150,
                    labelWidth: 180,
                    name: 'branch1Id',
                    validateOnChange: false,
                    value: 0
                },
                {
                    xtype: 'hiddenfield',
                    height: 150,
                    labelWidth: 180,
                    name: 'branch0Id',
                    validateOnChange: false,
                    value: 0
                },
                {
                    xtype: 'hiddenfield',
                    height: 150,
                    labelWidth: 180,
                    name: 'caseId',
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
            dock: 'bottom',
            html: '<span style="color:#696969;font-size:11px;"><em>*Τα ανενεργά πεδία θα συμπληρωθούν αυτόματα<br>από το σύστημα κατά την καταχώρηση</em></span>',
            id: 'compvehicle_save_submit_toolbar',
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
                            fn: 'onDelete_COMPANY_VEHICLES',
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
                            fn: 'onSave_COMPANY_VEHICLES',
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
                    text: 'Καταχώρηση',
                    listeners: {
                        click: {
                            fn: 'onSubmit_COMPANY_VEHICLES',
                            scope: 'controller'
                        }
                    }
                }
            ],
            listeners: {
                beforehide: 'onCompVehicles_save_submit_toolbarBeforeHide'
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

    onAddrPSelect2111: function(combo, records, eOpts) {
                var getnext = combo.up('form').getForm().findField('compAddrPe');
                getnext.clearValue();
         getnext.enable();
    },

    onAddrPeExpand2111: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
                queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
                queryPlan.query=queryPlan.combo.up('form').getForm().findField('compAddrP').getValue()+" ";
    },

    onAddrPeSelect2111: function(combo, records, eOpts) {
                var getnext = combo.up('form').getForm().findField('compAddrD');
                getnext.clearValue();
                getnext.enable();
    },

    onAddrPeDirtyChange2111: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalPe/"+field.getValue()+" ";
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onAddrDExpand2111: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalD");
        queryPlan.combo.getStore().getProxy().url = "/TKalD/search/findByEnotCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('compAddrPe').getValue()+" ";
    },

    onAddrDSelect2111: function(combo, records, eOpts) {
                var getnext = combo.up('form').getForm().findField('compAddrK');
                getnext.clearValue();
                getnext.enable();
    },

    onAddrDDirtyChange2111: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalD/"+field.getValue()+" ";
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        }
    },

    onAddrKExpand2111: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalK");
        queryPlan.combo.getStore().getProxy().url = "/TKalK/search/findByDimosCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('compAddrD').getValue();
    },

    onAddrKDirtyChange2111: function(field, isDirty, eOpts) {
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

    onCompVehicles_save_submit_toolbarBeforeHide: function(component, eOpts) {
        component.getComponent('submitbutton').destroy();
        component.getComponent('savebutton').destroy();
        component.getComponent('deletebutton').destroy();
    },

    onWindowBeforeDestroy: function(component, eOpts) {
        if(component.down('form').getForm().findField("a_new_form").getValue()==="true"){
            var routesgrid = Ext.getCmp('companyVehiclesBooks_Routes');
            routesgrid.store.clearData();
            routesgrid.getView().refresh();
            var view=Ext.getCmp('companymainView');
            var center = view.getComponent('contentPanel');
            center.removeAll();
            var viewsub = Ext.create('MyApp.view.company.VehiclesBooksPanel');
            center.add(viewsub);
        }
    }

});