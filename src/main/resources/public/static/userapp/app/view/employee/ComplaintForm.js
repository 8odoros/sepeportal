/*
 * File: app/view/employee/ComplaintForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.employee.ComplaintForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.employeecomplaintform',

    requires: [
        'MyApp.view.employee.ComplaintFormViewModel',
        'MyApp.view.employee.ComplaintFormViewController',
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
        'Ext.form.RadioGroup',
        'Ext.form.field.Radio',
        'Ext.form.field.File',
        'Ext.button.Button',
        'Ext.toolbar.Toolbar',
        'Ext.panel.Tool'
    ],

    controller: 'employeecomplaintform',
    viewModel: {
        type: 'employeecomplaintform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    overflowY: 'scroll',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Φόρμα Καταγγελίας',
    //modal: true,
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'form',
            id: 'employeecomplaintform',
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
                    title: 'ΣΤΟΙΧΕΙΑ ΚΑΤΑΓΓΕΛΙΑΣ',
                    items: [
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            cls: 'x-item-disabled',
                            disabled: true,
                            fieldLabel: 'Αρμόδια Διεύθυνση/Τμήμα ΣΕΠΕ',
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
                            xtype: 'combobox',
                            anchor: '100%',
                            hidden: true,
                            fieldLabel: 'Τύπος Καταγγελίας',
                            labelWidth: 180,
                            name: 'complIsAnonymous',
                            validateOnChange: false,
                            value: 1,
                            readOnly: true,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            editable: false,
                            displayField: 'name',
                            forceSelection: true,
                            store: 'employee.COMPL_TYPES',
                            valueField: 'abbr'
                        },
                        {
                            xtype: 'fieldset',
                            focusOnToFront: false,
                            padding: 10,
                            toFrontOnShow: false,
                            title: 'ΣΤΟΙΧΕΙΑ ΕΡΓΑΖΟΜΕΝΟΥ',
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
                                    fieldLabel: 'Τηλέφωνο Επικοινωνίας Καταγγέλλοντος',
                                    labelWidth: 180,
                                    name: 'empPhone',
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
                            padding: 10,
                            toFrontOnShow: false,
                            title: 'ΣΤΟΙΧΕΙΑ ΕΡΓΟΔΟΤΗ',
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
                                    vtype: 'Number',
                                    listeners: {
                                        blur: 'onAFMBlur1',
                                        specialkey: 'onAFMEnterKey1'
                                    }
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
                                    xtype: 'combobox',
                                    autofillCompFields: function(formPart, selectedrec) {
                                        //Autofill for Working Branch
                                        if (selectedrec.get('rgEbrAddressStreet')!==null){
                                            formPart.findField('inspAddr').setValue(selectedrec.get('rgEbrAddressStreet'));
                                        }

                                        if (selectedrec.get('rgEbrZipCode')!==null){
                                            formPart.findField('inspAddrTk').setValue(selectedrec.get('rgEbrZipCode'));
                                        }

                                        if (selectedrec.get('rgEbrKallikratis')!==null){
                                            formPart.findField('inspAddrPe').enable();
                                            formPart.findField('inspAddrD').enable();
                                            formPart.findField('inspAddrK').enable();

                                            formPart.findField('inspAddrK').setValue(selectedrec.get('rgEbrKallikratis'));

                                            formPart.findField('inspAddrK').getStore().getProxy().url = "/TKalK/"+selectedrec.get('rgEbrKallikratis');
                                            formPart.findField('inspAddrK').getStore().getProxy().getReader().setRootProperty("");
                                            formPart.findField('inspAddrK').store.load({callback : function(records, operation, success){
                                                var storeAddr1 = Ext.StoreMgr.lookup( 'address.ADDR_K' );

                                                formPart.findField('inspAddrP').setValue(storeAddr1.getAt(0).get('pCode'));
                                                formPart.findField('inspAddrPe').setValue(storeAddr1.getAt(0).get('peCode'));
                                                formPart.findField('inspAddrD').setValue(storeAddr1.getAt(0).get('dCode'));

                                                formPart.findField('inspAddrPe').getStore().getProxy().url = "/TKalPe/"+storeAddr1.getAt(0).get('peCode')+" ";
                                                formPart.findField('inspAddrPe').getStore().getProxy().getReader().setRootProperty("");
                                                formPart.findField('inspAddrPe').store.load();

                                                formPart.findField('inspAddrD').getStore().getProxy().url = "/TKalD/"+storeAddr1.getAt(0).get('dCode')+" ";
                                                formPart.findField('inspAddrD').getStore().getProxy().getReader().setRootProperty("");
                                                formPart.findField('inspAddrD').store.load();

                                            }
                                        });
                                    }


                                    //Autofill for Central Branch
                                    var store = Ext.StoreMgr.lookup( 'shared.AFM_COMP' );
                                    var centralrec = store.findRecord('rgEbrBranchId',0);

                                    if (centralrec.get('rgEmpFullname')!==null || centralrec.get('rgEmpName')!==null){
                                        if (centralrec.get('rgEmpName')===null)
                                        formPart.findField('compName').setValue(centralrec.get('rgEmpFullname'));
                                        else
                                        formPart.findField('compName').setValue(centralrec.get('rgEmpName'));
                                    }

                                    if (centralrec.get('rgEbrPhoneNumber')!==null){
                                        formPart.findField('compPhone').setValue(centralrec.get('rgEbrPhoneNumber'));
                                    }

                                    if (centralrec.get('rgEbrAddressStreet')!==null){
                                        formPart.findField('compAddr').setValue(centralrec.get('rgEbrAddressStreet'));
                                    }

                                    if (centralrec.get('rgEbrZipCode')!==null){
                                        formPart.findField('compAddrTk').setValue(centralrec.get('rgEbrZipCode'));
                                    }

                                    if (centralrec.get("rgEbrSecStakod1")!==null && centralrec.get("rgEbrSecStakod2")!==null &&
                                    centralrec.get("rgEbrSecStakod3")!==null && centralrec.get("rgEbrSecStakod4")!==null &&
                                    centralrec.get("rgEbrSecStakod5")!==null)
                                    {
                                        formPart.findField('rtstakLevel2').enable();
                                        formPart.findField('rtstakLevel3').enable();
                                        formPart.findField('rtstakLevel4').enable();
                                        formPart.findField('rtstakLevel5').enable();

                                        formPart.findField('rtstakLevel1').setValue(centralrec.get('rgEbrSecStakod1'));
                                        formPart.findField('rtstakLevel2').setValue(centralrec.get('rgEbrSecStakod2'));
                                        formPart.findField('rtstakLevel3').setValue(centralrec.get('rgEbrSecStakod3'));
                                        formPart.findField('rtstakLevel4').setValue(centralrec.get('rgEbrSecStakod4'));
                                        formPart.findField('rtstakLevel5').setValue(centralrec.get('rgEbrSecStakod5'));


                                        formPart.findField('rtstakLevel2').getStore().getProxy().url = "/RtStakLevel2/search/findLevel2byId"+"?id="+centralrec.get("rgEbrSecStakod2");
                                        formPart.findField('rtstakLevel2').store.load();
                                        formPart.findField('rtstakLevel3').getStore().getProxy().url = "/RtStakLevel3/search/findLevel3byId"+"?id="+centralrec.get("rgEbrSecStakod3");
                                        formPart.findField('rtstakLevel3').store.load();
                                        formPart.findField('rtstakLevel4').getStore().getProxy().url = "/RtStakLevel4/search/findLevel4byId"+"?id="+centralrec.get("rgEbrSecStakod4");
                                        formPart.findField('rtstakLevel4').store.load();
                                        formPart.findField('rtstakLevel5').getStore().getProxy().url = "/RtStakLevel5/search/findLevel5byId"+"?id="+centralrec.get("rgEbrSecStakod5");
                                        formPart.findField('rtstakLevel5').store.load();

                                    }

                                    if (centralrec.get('rgEbrKallikratis')!==null){
                                        formPart.findField('compAddrPe').enable();
                                        formPart.findField('compAddrD').enable();
                                        formPart.findField('compAddrK').enable();

                                        formPart.findField('compAddrK').setValue(centralrec.get('rgEbrKallikratis'));

                                        formPart.findField('compAddrK').getStore().getProxy().url = "/TKalK/"+centralrec.get('rgEbrKallikratis');
                                        formPart.findField('compAddrK').getStore().getProxy().getReader().setRootProperty("");
                                        formPart.findField('compAddrK').store.load({callback : function(records, operation, success){
                                            var storeAddr2 = Ext.StoreMgr.lookup( 'address1.ADDR_K1' );

                                            formPart.findField('compAddrP').setValue(storeAddr2.getAt(0).get('pCode'));
                                            formPart.findField('compAddrPe').setValue(storeAddr2.getAt(0).get('peCode'));
                                            formPart.findField('compAddrD').setValue(storeAddr2.getAt(0).get('dCode'));

                                            formPart.findField('compAddrPe').getStore().getProxy().url = "/TKalPe/"+storeAddr2.getAt(0).get('peCode')+" ";
                                            formPart.findField('compAddrPe').getStore().getProxy().getReader().setRootProperty("");
                                            formPart.findField('compAddrPe').store.load();

                                            formPart.findField('compAddrD').getStore().getProxy().url = "/TKalD/"+storeAddr2.getAt(0).get('dCode')+" ";
                                            formPart.findField('compAddrD').getStore().getProxy().getReader().setRootProperty("");
                                            formPart.findField('compAddrD').store.load();

                                        }
                                    });
                                }

                                    },
                                    anchor: '100%',
                                    fieldLabel: 'Επιλογή Παραρτήματος',
                                    labelWidth: 180,
                                    name: 'branch1Id',
                                    validateOnChange: false,
                                    readOnly: true,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    editable: false,
                                    emptyText: 'Καταχωρήστε πρώτα το Α.Φ.Μ. του Εργοδότη/Επιχείρησης',
                                    displayField: 'rgEbrAddressStreetCombo',
                                    store: 'shared.AFM_COMP',
                                    valueField: 'rgEbrBranchId',
                                    listeners: {
                                        select: 'onBranch1Select1'
                                    }
                                },
                                {
                                    xtype: 'hiddenfield',
                                    anchor: '100%',
                                    fieldLabel: 'Label',
                                    name: 'branch0Id'
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
                                            maxLength: 100
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
                                            fieldLabel: 'Περιφερειακή Ενότητα',
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
                                            fieldLabel: 'Δημοτική Ενότητα',
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
                                            name: 'inspAddrTk',
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
                                    xtype: 'textareafield',
                                    anchor: '100%',
                                    fieldLabel: 'Ειδικός τόπος ελέγχου εντός της επιχείρησης',
                                    labelWidth: 180,
                                    name: 'complAddressDesc',
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
                                    title: 'ΔΙΕΥΘΥΝΣΗ ΕΔΡΑΣ',
                                    items: [
                                        {
                                            xtype: 'textareafield',
                                            anchor: '100%',
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
                                            fieldLabel: 'Περιφερειακή Ενότητα',
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
                                                select: 'onAddrPeSelect1',
                                                dirtychange: {
                                                    fn: 'onAddrPeDirtyChange1',
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
                                            editable: false,
                                            displayField: 'dimosDescr',
                                            queryParam: 'enotCode',
                                            store: 'address1.ADDR_D1',
                                            valueField: 'abbr',
                                            listeners: {
                                                beforequery: 'onAddrDExpand1',
                                                select: 'onAddrDSelect1',
                                                dirtychange: {
                                                    fn: 'onAddrDDirtyChange1',
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
                                            name: 'compAddrK',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            editable: false,
                                            displayField: 'koinDescr',
                                            queryParam: 'dimosCode',
                                            store: 'address1.ADDR_K1',
                                            valueField: 'abbr',
                                            listeners: {
                                                beforequery: 'onAddrKExpand1',
                                                dirtychange: {
                                                    fn: 'onAddrKDirtyChange1',
                                                    single: true
                                                }
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
                                    minLength: 10,
                                    vtype: 'telNumber'
                                },
                                {
                                    xtype: 'fieldset',
                                    hidden: true,
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
                                            //allowBlank: false,
                                            //allowOnlyWhitespace: false,
                                            editable: false,
                                            displayField: 'spRtstackLevel2Desc',
                                            queryParam: 'level1',
                                            store: 'compScope.rtstakLevel2',
                                            valueField: 'abbr',
                                            listeners: {
                                                beforequery: 'onCompScopeL2Expand',
                                                select: 'onCompScopeL2Select',
                                                dirtychange: {
                                                    fn: 'onCompScopeL2DirtyChange',
                                                    single: true
                                                }
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
                                            //allowBlank: false,
                                            //allowOnlyWhitespace: false,
                                            editable: false,
                                            displayField: 'spRtstackLevel3Desc',
                                            queryParam: 'level2',
                                            store: 'compScope.rtstakLevel3',
                                            valueField: 'abbr',
                                            listeners: {
                                                beforequery: 'onCompScopeL3Expand',
                                                select: 'onCompScopeL3Select',
                                                dirtychange: {
                                                    fn: 'onCompScopeL3DirtyChange',
                                                    single: true
                                                }
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
                                            //allowBlank: false,
                                            //allowOnlyWhitespace: false,
                                            editable: false,
                                            displayField: 'spRtstackLevel4Desc',
                                            queryParam: 'level3',
                                            store: 'compScope.rtstakLevel4',
                                            valueField: 'abbr',
                                            listeners: {
                                                beforequery: 'onCompScopeL4Expand',
                                                select: 'onCompScopeL4Select',
                                                dirtychange: {
                                                    fn: 'onCompScopeL4DirtyChange',
                                                    single: true
                                                }
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
                                            //allowBlank: false,
                                           // allowOnlyWhitespace: false,
                                            editable: false,
                                            displayField: 'spRtstackLevel5Desc',
                                            queryParam: 'level4',
                                            store: 'compScope.rtstakLevel5',
                                            valueField: 'abbr',
                                            listeners: {
                                                beforequery: 'onCompScopeL5Expand',
                                                dirtychange: {
                                                    fn: 'onCompScopeL5DirtyChange',
                                                    single: true
                                                }
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
                                    fieldLabel: 'Υπάρχει στoν Οργανισμό Σωματείο Εργαζομένων',
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
                                }
                            ]
                        },
                        {
                            xtype: 'container',
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
                                    xtype: 'label',
                                    height: 24,
                                    padding: '5 0 0 0',
                                    text: 'Εφόσον θέλετε να υποβάλλετε καταγγελία που αφορά και θέματα Ασφάλειας και Υγείας στην Εργασία αλλά και θέματα Εργασιακών Σχέσεων, θα πρέπει να υποβάλλετε 2 ξεχωριστές καταγγελίες.'
                                },
                            ]
                        },
                        {
                            xtype: 'radiogroup',
                            anchor: '70%',
                            fieldLabel: 'Η Καταγγελία αφορά',
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
                            maxLength: 3000,
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
                            maxLength: 1000
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
                            id: 'complfile',
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
            id: 'compl_save_submit_toolbar',
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
                    itemId: 'deletecomplaintbutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf00d@FontAwesome',
                    text: 'Διαγραφή',
                    tooltip: 'Διαγραφή  Αποθηκευμένης Καταγγελίας',
                    listeners: {
                        click: {
                            fn: 'onDelete_EMPLOYEE_COMPLAINT',
                            scope: 'controller'
                        }
                    }
                },
                {
                    xtype: 'button',
                    itemId: 'savecomplaintbutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf0c7@FontAwesome',
                    text: 'Αποθήκευση',
                    listeners: {
                        click: {
                            fn: 'onSave_EMPLOYEE_COMPLAINT',
                            scope: 'controller'
                        }
                    }
                },
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
                            fn: 'onSubmit_EMPLOYEE_COMPLAINT',
                            scope: 'controller'
                        }
                    }
                }
            ],
            listeners: {
                beforehide: 'onCompl_save_submit_toolbarBeforeHide'
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

    onAFMBlur1: function(component, event, eOpts) {
        var branchId = component.up('form').getForm().findField('branch1Id');
        if (component.up('form').getForm().findField('subStatus').getValue()!=="2"){
            if (component.getValue().length>0){
                branchId.getStore().getProxy().extraParams = {afm:component.getValue()};
                branchId.getStore().load({callback: function(records, operation, success) {
                        if (records.length>0) {
                            branchId.setReadOnly(false);
                            branchId.focus();
                            branchId.expand();
                        } else {
                            Ext.Msg.alert('Πρόβλημα Α.Φ.Μ.', 'Το Α.Φ.Μ δεν αντιστοιχεί σε κάποιο οργανισμό');
                            branchId.clearValue();
                            branchId.setReadOnly(true);
                        }
                    }});
            }
            else
               {
                            branchId.clearValue();
                            branchId.setReadOnly(true);
               }
        }
    },

    onAFMEnterKey1: function(field, e, eOpts) {
        if (e.getKey() == e.ENTER) {


        var branchId = field.up('form').getForm().findField('branch1Id');
             branchId.focus();

        }
    },

    onBranch1Select1: function(combo, records, eOpts) {
        //var store = Ext.StoreMgr.lookup( 'AFM_COMP' );
        //var centralId = store.findRecord('branchId',0).get('abbr');
        //combo.up('form').getForm().findField('branch0').setValue(centralId);
        var formToFill = combo.up('form').getForm();
        combo.up('form').getForm().findField('branch0Id').setValue(0);


        //DISABLE BELOW PART TO STOP AUTOFILL
        value = combo.getValue();
        selectedrec = combo.findRecordByValue(value);

        formToFill.findField('rtstakLevel2').suspendEvent('dirtyChange');
        formToFill.findField('rtstakLevel3').suspendEvent('dirtyChange');
        formToFill.findField('rtstakLevel4').suspendEvent('dirtyChange');
        formToFill.findField('rtstakLevel5').suspendEvent('dirtyChange');
        formToFill.findField('inspAddrK').suspendEvent('dirtyChange');
        formToFill.findField('inspAddrPe').suspendEvent('dirtyChange');
        formToFill.findField('inspAddrD').suspendEvent('dirtyChange');
        formToFill.findField('compAddrK').suspendEvent('dirtyChange');
        formToFill.findField('compAddrPe').suspendEvent('dirtyChange');
        formToFill.findField('compAddrD').suspendEvent('dirtyChange');

        combo.autofillCompFields(formToFill, selectedrec);

        formToFill.findField('inspAddrK').resumeEvent('dirtyChange');
        formToFill.findField('inspAddrPe').resumeEvent('dirtyChange');
        formToFill.findField('inspAddrD').resumeEvent('dirtyChange');
        formToFill.findField('compAddrK').resumeEvent('dirtyChange');
        formToFill.findField('compAddrPe').resumeEvent('dirtyChange');
        formToFill.findField('compAddrD').resumeEvent('dirtyChange');
        formToFill.findField('rtstakLevel2').resumeEvent('dirtyChange');
        formToFill.findField('rtstakLevel3').resumeEvent('dirtyChange');
        formToFill.findField('rtstakLevel4').resumeEvent('dirtyChange');
        formToFill.findField('rtstakLevel5').resumeEvent('dirtyChange');
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
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('inspAddrPe').getValue()+" ";
    },

    onAddrDSelect: function(combo, records, eOpts) {
                var getnext = combo.up('form').getForm().findField('inspAddrK');
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
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('inspAddrD').getValue();
    },

    onAddrKDirtyChange: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalK/"+field.getValue();
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        }
    },

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

    onAddrPeDirtyChange1: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
                    field.getStore().getProxy().url = "/TKalPe/"+field.getValue()+" ";
                field.getStore().getProxy().getReader().setRootProperty("");
                field.store.load();
                }
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

    onAddrDDirtyChange1: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
                    field.getStore().getProxy().url = "/TKalD/"+field.getValue()+" ";
                field.getStore().getProxy().getReader().setRootProperty("");
                field.store.load();
                }
    },

    onAddrKExpand1: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalK");
        queryPlan.combo.getStore().getProxy().url = "/TKalK/search/findByDimosCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('compAddrD').getValue();
    },

    onAddrKDirtyChange1: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalK/"+field.getValue();
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        }
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

    onCompScopeL2DirtyChange: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/RtStakLevel2/search/findLevel2byId"+"?id="+field.getValue();
            field.store.load();
        }
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

    onCompScopeL3DirtyChange: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/RtStakLevel3/search/findLevel3byId"+"?id="+field.getValue();
        field.store.load();
        }
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

    onCompScopeL4DirtyChange: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
           field.getStore().getProxy().url = "/RtStakLevel4/search/findLevel4byId"+"?id="+field.getValue();
        field.store.load();
        }
    },

    onCompScopeL5Expand: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.RtStakLevel5");
        queryPlan.combo.getStore().getProxy().url = "/RtStakLevel5/search/findLevel5";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('rtstakLevel4').getValue();
    },

    onCompScopeL5DirtyChange: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/RtStakLevel5/search/findLevel5byId"+"?id="+field.getValue();
        field.store.load();
        }
    },

    onDeleteClick: function(button, e, eOpts) {
        button.up('form').getForm().findField('deletedfile').setValue(true);
        button.up('container').hide();
        button.up('form').getForm().findField('file').focus();
    },

    onViewDocClick: function(button, e, eOpts) {
        var url = "/getDocument?docId="+button.up('form').getForm().findField('docIdAttached').getValue();
        var win = window.open(url, '_blank');
          win.focus();
    },

    onWindowBeforeDestroy: function(component, eOpts) {
        var view=Ext.getCmp('employeemainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.employee.ComplaintsPanel');
        center.add(viewsub);
    },

    onCompl_save_submit_toolbarBeforeHide: function(component, eOpts) {
        component.getComponent('deletecomplaintbutton').destroy();
        component.getComponent('savecomplaintbutton').destroy();
        component.getComponent('submitcomplaintbutton').destroy();
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
            field.getStore().getProxy().url = "/employeeComplaintMatter/search/findByComplInvolves"+"?id="+field.up('form').getForm().findField('complInvolves').getValue() ? 1 : 0;
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    }*/

});