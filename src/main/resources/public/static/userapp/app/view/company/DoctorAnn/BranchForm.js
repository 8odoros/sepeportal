/*
 * File: app/view/company/DoctorAnn/BranchForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.DoctorAnn.BranchForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companydoctorannbranchform',

    requires: [
        'MyApp.view.company.DoctorAnn.BranchFormViewModel',
        'MyApp.view.company.DoctorAnn.BranchFormViewController',
        'MyApp.view.shared.PrintFormTool',
        'MyApp.view.shared.CloseFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.FieldSet',
        'Ext.form.field.ComboBox',
        'Ext.form.trigger.Trigger',
        'Ext.form.field.TextArea',
        'Ext.toolbar.Toolbar',
        'Ext.button.Button',
        'Ext.panel.Tool'
    ],

    controller: 'companydoctorannbranchform',
    viewModel: {
        type: 'companydoctorannbranchform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    overflowY: 'auto',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Φόρμα Παραρτήματος ή Τοποθεσίας Έργου',
    //modal: true,
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'form',
            id: 'companydoctorannbranch',
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
                    flex: 1,
                    height: 150,
                    fieldLabel: 'Ημ. Πρωτοκόλλου',
                    labelWidth: 180,
                    name: 'companyId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    flex: 1,
                    height: 150,
                    fieldLabel: 'Id',
                    labelWidth: 180,
                    name: 'ptlBranchId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    flex: 1,
                    height: 150,
                    fieldLabel: 'Branch Id',
                    labelWidth: 180,
                    name: 'ebrBranchId',
                    validateOnChange: false,
                    value: 0
                },
                {
                    xtype: 'hiddenfield',
                    flex: 1,
                    height: 150,
                    fieldLabel: 'isNewPlaceAllowed',
                    labelWidth: 180,
                    name: 'isNewPlaceAllowed',
                    validateOnChange: false,
                    value: false
                },
                {
                    xtype: 'displayfield',
                    flex: 1,
                    padding: '',
                    fieldLabel: '<img height="32px" src="/portal/static/userapp/resources/info.png"/>',
                    labelSeparator: '',
                    labelWidth: 42,
                    labelStyle: 'margin-top:15px;',
                    height: 80,
                    name: 'brInfoText',
                    value: 'Έχετε τη δυνατότητα δημιουργίας Nέου Τόπου παροχής υπηρεσιών Τεχνικού Ασφάλειας βάσει των προβλεπομένων στη σύμβαση με τον εργοδότη, αφήνοντας την επιλογή του εγγ. παραρτήματος στο ΙΚΑ κενή και συμπληρώνοντας τα στοιχεία της διεύθυνσης του τόπου που εσείς επιθυμείτε. Σε περίπτωση που επιλέξετε κάποιο από τα εγγ. παραρτήματα στο ΙΚΑ τα στοιχέια της διεύθυνσης συμπληρώνονται αυτόματα και δεν μπορείτε να τα αλλάξετε.',
                    hidden: true
                },
                {
                    xtype: 'textfield',
                    flex: 1,
                    fieldLabel: 'Περιγραφή Παρατήματος',
                    labelWidth: 180,
                    name: 'brDescr',
                    validateOnChange: false,
                    validateOnBlur: false,
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    maxLength: 100
                },
                {
                    xtype: 'fieldset',
                    flex: 1,
                    focusOnToFront: false,
                    hidden: true,
                    padding: 10,
                    toFrontOnShow: false,
                    title: '',
                    items: [
                        {
                            xtype: 'combobox',
                            autofillCompFields: function(formPart, selectedrec) {
                                if (selectedrec.get('rgEbrAddressStreet')!==null){
                                    formPart.findField('brDescr').setValue(selectedrec.get('rgEbrAddressStreet'));
                                    formPart.findField('brAddr').setValue(selectedrec.get('rgEbrAddressStreet'));
                                    formPart.findField('brAddr').setReadOnly(true);
                                }

                                if (selectedrec.get('rgEbrZipCode')!==null){
                                    formPart.findField('brAddrTk').setValue(selectedrec.get('rgEbrZipCode'));
                                    formPart.findField('brAddrTk').setReadOnly(true);
                                }

                                if (selectedrec.get('rgEbrBranchId')!==null){
                                    formPart.findField('ebrBranchId').setValue(selectedrec.get('rgEbrBranchId'));
                                }

                                formPart.findField('brAddrPe').enable();
                                formPart.findField('brAddrD').enable();
                                formPart.findField('brAddrK').enable();

                                formPart.findField('brAddrP').setReadOnly(true);
                                formPart.findField('brAddrPe').setReadOnly(true);
                                formPart.findField('brAddrD').setReadOnly(true);
                                formPart.findField('brAddrK').setReadOnly(true);

                                if (selectedrec.get('rgEbrKallikratis')!==null){


                                    formPart.findField('brAddrK').setValue(selectedrec.get('rgEbrKallikratis'));

                                    formPart.findField('brAddrK').getStore().getProxy().url = "/TKalK/"+selectedrec.get('rgEbrKallikratis');
                                    formPart.findField('brAddrK').getStore().getProxy().getReader().setRootProperty("");
                                    formPart.findField('brAddrK').store.load({callback : function(records, operation, success){
                                        var storeAddr1 = Ext.StoreMgr.lookup( 'address.ADDR_K' );

                                        formPart.findField('brAddrP').setValue(storeAddr1.getAt(0).get('pCode'));
                                        formPart.findField('brAddrPe').setValue(storeAddr1.getAt(0).get('peCode'));
                                        formPart.findField('brAddrD').setValue(storeAddr1.getAt(0).get('dCode'));

                                        formPart.findField('brAddrPe').getStore().getProxy().url = "/TKalPe/"+storeAddr1.getAt(0).get('peCode')+" ";
                                        formPart.findField('brAddrPe').getStore().getProxy().getReader().setRootProperty("");
                                        formPart.findField('brAddrPe').store.load();

                                        formPart.findField('brAddrD').getStore().getProxy().url = "/TKalD/"+storeAddr1.getAt(0).get('dCode')+" ";
                                        formPart.findField('brAddrD').getStore().getProxy().getReader().setRootProperty("");
                                        formPart.findField('brAddrD').store.load();

                                    }
                                });
                            }

                            },
                            anchor: '100%',
                            fieldLabel: 'Παράρτημα εγγ. στο ΙΚΑ',
                            labelWidth: 180,
                            name: 'NewebrBranchId',
                            validateOnChange: false,
                            validateOnBlur: false,
                            //editable: false,
                            autoLoadOnValue: true,
                            displayField: 'rgEbrAddressStreetCombo',
                            store: 'company.COMP_BRANCES',
                            valueField: 'rgEbrBranchId',
                            minChars: 0,
                            queryParam: 'descr',
                            forceSelection: true,
                            triggers: {
                                deselectBr: {
                                    handler: function(field, trigger, e) {
                                        field.clearValue();
                                        formPart=field.up('form').getForm();
                                        formPart.findField('brDescr').reset();
                                        formPart.findField('brAddr').reset();
                                        formPart.findField('brAddrTk').reset();
                                        formPart.findField('brAddrP').reset();
                                        if (formPart.findField('isNewPlaceAllowed').value == "true") {
                                            formPart.findField('brAddrTk').setReadOnly(false);
                                            formPart.findField('brAddr').setReadOnly(false);
                                            formPart.findField('brAddrP').setReadOnly(false);
                                            formPart.findField('brAddrPe').setReadOnly(false);
                                            formPart.findField('brAddrD').setReadOnly(false);
                                            formPart.findField('brAddrK').setReadOnly(false);
                                        } else {
                                            formPart.findField('brAddr').setReadOnly(true);
                                            formPart.findField('brAddrTk').setReadOnly(true);
                                            formPart.findField('brAddrP').setReadOnly(true);
                                            formPart.findField('brAddrPe').setReadOnly(true);
                                            formPart.findField('brAddrD').setReadOnly(true);
                                            formPart.findField('brAddrK').setReadOnly(true);
                                        }

                                        formPart.findField('brAddrPe').reset();
                                        formPart.findField('brAddrD').reset();
                                        formPart.findField('brAddrK').reset();
                                        formPart.findField('brAddrPe').disable();
                                        formPart.findField('brAddrD').disable();
                                        formPart.findField('brAddrK').disable();
                                    },
                                    cls: 'x-form-clear-trigger'
                                }
                            },
                            listeners: {
                                select: 'onBranch1Select1',
                                beforequery: 'onBranchExpand'
                            }
                        }
                    ]
                },
                {
                    xtype: 'fieldset',
                    flex: 1,
                    focusOnToFront: false,
                    toFrontOnShow: false,
                    title: 'ΔΙΕΥΘΥΝΣΗ ΥΠΟΚΑΤΑΣΤΗΜΑΤΟΣ ή ΕΡΓΟΥ',
                    items: [
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            fieldLabel: 'Οδός / Αριθμός',
                            labelWidth: 180,
                            name: 'brAddr',
                            readOnly: true,
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 3000
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            fieldLabel: 'Περιφέρεια',
                            labelWidth: 180,
                            name: 'brAddrP',
                            readOnly: true,
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
                            name: 'brAddrPe',
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
                            name: 'brAddrD',
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
                            name: 'brAddrK',
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
                            fieldLabel: 'Τ.Κ',
                            labelWidth: 180,
                            name: 'brAddrTk',
                            readOnly: true,
                            validateOnChange: false,
                            validateOnBlur: false,
                            //allowBlank: false,
                            //allowOnlyWhitespace: false,
                            //maxLength: 5, TODO Προσοχή μπορεί να περάσει οτιδήποτε!
                            //minLength: 5,
                            vtype: 'Number'
                        }
                    ]
                },
                {
                    xtype: 'checkboxgroup',
                    flex: 1,
                    width: 400,
                    //msgTarget: 'under',
                    //allowBlank: false,
                    //blankText: 'Πρέπει να αποδεχθείτε τους όρους για να προχωρήσετε στην ενημέρωση.',
                    items: [
                        {
                            xtype: 'checkboxfield',
                            name: 'brActive',
                            validateOnChange: false,
                            validateOnBlur: false,
                            boxLabel: 'Ενεργό',
                            inputValue: '1',
                            uncheckedValue: '0',
                            checked: true
                        }
                    ]
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
            html: '<span style="font-size:12px;"><em>*Επιλέξτε παράρτημα που είναι εγγεγραμμένο στο ΙΚΑ<br>ή καταχωρήστε διεύθυνση για νέο εικονικό παράρτημα</em></span>',
            id: 'compdoctorannbranch_save_submit_toolbar',
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
                            fn: 'onSave_COMPANY_BRANCH',
                            scope: 'controller'
                        }
                    }
                },
                {
                    xtype: 'button',
                    itemId: 'updatebutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf0c7@FontAwesome',
                    text: 'Ενημέρωση',
                    listeners: {
                        click: {
                            fn: 'onUpdate_COMPANY_BRANCH',
                            scope: 'controller'
                        }
                    }
                }
            ],
            listeners: {
                beforehide: 'onCompDoctorAnnBranch_save_submit_toolbarBeforeHide'
            }
        }
    ],
    listeners: {
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
    tools: [
        {
            xtype: 'sharedprintformtool'
        },
        {
            xtype: 'sharedcloseformtool'
        }
    ],

    onBranch1Select1: function(combo, records, eOpts) {
        var formToFill = combo.up('form').getForm();

        //DISABLE BELOW PART TO STOP AUTOFILL
        value = combo.getValue();
        selectedrec = combo.findRecordByValue(value);

        formToFill.findField('brAddr').reset();
        formToFill.findField('brAddrTk').reset();
        formToFill.findField('brAddrP').reset();
        formToFill.findField('brAddrPe').reset();
        formToFill.findField('brAddrD').reset();
        formToFill.findField('brAddrK').reset();

        formToFill.findField('brAddrK').suspendEvent('dirtyChange');
        formToFill.findField('brAddrPe').suspendEvent('dirtyChange');
        formToFill.findField('brAddrD').suspendEvent('dirtyChange');

        combo.autofillCompFields(formToFill, selectedrec);


        formToFill.findField('brAddrK').resumeEvent('dirtyChange');
        formToFill.findField('brAddrPe').resumeEvent('dirtyChange');
        formToFill.findField('brAddrD').resumeEvent('dirtyChange');
    },

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

    onAddrPSelect: function(combo, records, eOpts) {
                var getnext = combo.up('form').getForm().findField('brAddrPe');
                getnext.clearValue();
                getnext.enable();
    },

    onAddrPeExpand: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
                queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
                queryPlan.query=queryPlan.combo.up('form').getForm().findField('brAddrP').getValue()+" ";
    },

    onAddrPeSelect: function(combo, records, eOpts) {
                var getnext = combo.up('form').getForm().findField('brAddrD');
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
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('brAddrPe').getValue()+" ";
    },

    onAddrDSelect: function(combo, records, eOpts) {
                var getnext = combo.up('form').getForm().findField('brAddrK');
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
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('brAddrD').getValue();
    },

    onAddrKDirtyChange: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalK/"+field.getValue();
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        }
    },

    onCompDoctorAnnBranch_save_submit_toolbarBeforeHide: function(component, eOpts) {
        component.getComponent('savebutton').destroy();
    }

});