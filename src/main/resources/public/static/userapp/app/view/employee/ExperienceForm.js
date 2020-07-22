/*
 * File: app/view/employee/ExperienceForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.employee.ExperienceForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.employeeexperienceform',

    requires: [
        'MyApp.view.employee.ExperienceFormViewModel',
        'MyApp.view.employee.ExperienceFormViewController',
        'MyApp.view.shared.PrintFormTool',
        'MyApp.view.shared.CloseFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.FieldSet',
        'Ext.form.field.Date',
        'Ext.form.field.Number',
        'Ext.form.field.ComboBox',
        'Ext.form.field.TextArea',
        'Ext.form.FieldContainer',
        'Ext.form.Label',
        'Ext.form.field.File',
        'Ext.button.Button',
        'Ext.toolbar.Toolbar',
        'Ext.panel.Tool'
    ],

    controller: 'employeeexperienceform',
    viewModel: {
        type: 'employeeexperienceform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    overflowY: 'scroll',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Φόρμα Χορήγησης Βεβαίωσης Προϋπηρεσίας',
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
            id: 'employeecomplaintform1',
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
                            fieldLabel: 'Χρήση Βεβαίωσης',
                            labelWidth: 180,
                            name: 'intentedUse',
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
                                store: '{EXPER_USAGE}'
                            }
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            cls: 'x-item-disabled',
                            disabled: true,
                            fieldLabel: 'Αρμόδιο Τμήμα',
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
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Ειδικότητα',
                                    labelWidth: 180,
                                    name: 'empSpecialityId',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 100,
                                    displayField: 'spRtspDescription',
                                    forceSelection: true,
                                    pageSize: 25,
                                    queryParam: 'descr',
                                    store: 'employee.SPECIALTY',
                                    valueField: 'abbr',
                                    listeners: {
                                        beforequery: 'onSpecialityExpand1'
                                    }
                                },
                                {
                                    xtype: 'fieldcontainer',
                                    fieldLabel: 'Διάρκεια εργασιακής σχέσης για την οποία ζητείται η βεβαίωση',
                                    labelWidth: 180,
                                    layout: {
                                        type: 'vbox',
                                        align: 'stretchmax',
                                        pack: 'center'
                                    },
                                    items: [
                                        {
                                            xtype: 'datefield',
                                            fieldLabel: 'Από',
                                            labelWidth: 40,
                                            msgTarget: 'under',
                                            name: 'empFromDate',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            allowBlank: false,
                                            allowOnlyWhitespace: false,
                                            editable: false
                                        },
                                        {
                                            xtype: 'datefield',
                                            fieldLabel: 'Εως',
                                            labelWidth: 40,
                                            msgTarget: 'under',
                                            name: 'empUntilDate',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            allowBlank: false,
                                            allowOnlyWhitespace: false,
                                            editable: false
                                        }
                                    ]
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
                                                select: 'onAddrPSelect3'
                                            }
                                        },
                                        {
                                            xtype: 'combobox',
                                            anchor: '100%',
                                            session: false,
                                            disabled: true,
                                            fieldLabel: 'Νομός',
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
                                                beforequery: 'onAddrPeExpand3',
                                                select: 'onAddrPeSelect3',
                                                dirtychange: {
                                                    fn: 'onAddrPeDirtyChange3',
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
                                                beforequery: 'onAddrDExpand3',
                                                select: 'onAddrDSelect3',
                                                dirtychange: {
                                                    fn: 'onAddrDDirtyChange3',
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
                                                beforequery: 'onAddrKExpand3',
                                                dirtychange: {
                                                    fn: 'onAddrKDirtyChange3',
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
                                    xtype: 'combobox',
                                    autofillCompFields: function(formPart) {
                                        formPart.findField('rtstakLevel2').suspendEvent('dirtyChange');
                                        formPart.findField('rtstakLevel3').suspendEvent('dirtyChange');
                                        formPart.findField('rtstakLevel4').suspendEvent('dirtyChange');
                                        formPart.findField('rtstakLevel5').suspendEvent('dirtyChange');

                                        //Autofill
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

                                        if (centralrec.get('rgEbrEmployerId')!==null){
                                            formPart.findField('compAmeIka').setValue(centralrec.get('rgEbrEmployerId'));
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

                                        formPart.findField('rtstakLevel2').resumeEvent('dirtyChange');
                                        formPart.findField('rtstakLevel3').resumeEvent('dirtyChange');
                                        formPart.findField('rtstakLevel4').resumeEvent('dirtyChange');
                                        formPart.findField('rtstakLevel5').resumeEvent('dirtyChange');


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
                                        select: 'onBranch1Select'
                                    }
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Επωνυμία Εργοδότη',
                                    labelWidth: 180,
                                    name: 'compName',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 300
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'AME IKA',
                                    labelWidth: 180,
                                    name: 'compAmeIka',
                                    validateOnChange: false,
                                    readOnly: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Τηλέφωνο Εργοδότη',
                                    labelWidth: 180,
                                    name: 'compPhone',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 10,
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
                                            text: 'Αντικείμενο Εργασιών Εργοδότη:'
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
                                            //allowOnlyWhitespace: false,
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
                                }
                            ]
                        },
                        {
                            xtype: 'form',
                            id: 'complfile1',
                            padding: '10 10 0 10',
                            standardSubmit: false,
                            items: [
                                {
                                    xtype: 'filefield',
                                    anchor: '100%',
                                    frame: false,
                                    fieldLabel: 'Επισυναπτόμενο Αρχείο  - Έγγραφο του εργοδότη με τίτλο "Βεβαίωση Προϋπηρεσίας", όπου αναγράφονται το ονοματεπώνυμο και η ειδικότητα του εργαζόμενου και το χρονικό διάστημα απασχόλησης του στην επιχείρηση του πιστοποιούντος εργοδότη: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
                                    labelAlign: 'top',
                                    labelSeparator: ' ',
                                    labelWidth: 180,
                                    msgTarget: 'under',
                                    name: 'file',
                                    allowBlank: false,
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
                            itemId: 'Exist_File1',
                            margin: '0 0 0 20',
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
                                    name: 'deletedfile1',
                                    validateOnChange: false,
                                    value: false
                                },
                                {
                                    xtype: 'hiddenfield',
                                    flex: 1,
                                    itemId: 'fid',
                                    fieldLabel: 'Επισυναπτόμενο Αρχείο',
                                    labelWidth: 180,
                                    msgTarget: 'under',
                                    name: 'attachedDocIdEmplVer',
                                    validateOnChange: false
                                }
                            ]
                        },
                        {
                            xtype: 'form',
                            id: 'complfile2',
                            padding: '10 10 0 10',
                            standardSubmit: false,
                            items: [
                                {
                                    xtype: 'filefield',
                                    anchor: '100%',
                                    frame: false,
                                    fieldLabel: 'Επισυναπτόμενο Αρχείο - Ένσημα του Ι.Κ.Α. της αναγραφόμενης χρονικής περιόδου εργασίας ',
                                    labelAlign: 'top',
                                    labelWidth: 180,
                                    msgTarget: 'under',
                                    name: 'file',
                                    allowBlank: false,
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
                            margin: '0 0 0 20',
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
                                        click: 'onDeleteClick11'
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
                                        click: 'onViewDocClick11'
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
                                },
                                {
                                    xtype: 'hiddenfield',
                                    flex: 1,
                                    itemId: 'fid',
                                    fieldLabel: 'Επισυναπτόμενο Αρχείο',
                                    labelWidth: 180,
                                    msgTarget: 'under',
                                    name: 'attachedDocIdIka',
                                    validateOnChange: false
                                }
                            ]
                        },
                        {
                            xtype: 'form',
                            id: 'complfile3',
                            padding: '10 10 0 10',
                            standardSubmit: false,
                            items: [
                                {
                                    xtype: 'filefield',
                                    anchor: '100%',
                                    frame: false,
                                    fieldLabel: 'Επισυναπτόμενο Αρχείο - Θεώρηση της βεβαιώσεως από τις κατά τόπους επαγγελματικές οργανώσεις για την απόκτηση άδειας επαγγέλματος για συγκεκριμένες και μόνο ειδικότητες εργαζομένων ',
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
                            itemId: 'Exist_File3',
                            margin: '0 0 0 20',
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
                                        click: 'onDeleteClick2'
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
                                        click: 'onViewDocClick2'
                                    }
                                },
                                {
                                    xtype: 'hiddenfield',
                                    flex: 1,
                                    labelWidth: 180,
                                    msgTarget: 'under',
                                    name: 'deletedfile3',
                                    validateOnChange: false,
                                    value: false
                                },
                                {
                                    xtype: 'hiddenfield',
                                    flex: 1,
                                    itemId: 'fid',
                                    fieldLabel: 'Επισυναπτόμενο Αρχείο',
                                    labelWidth: 180,
                                    msgTarget: 'under',
                                    name: 'attachedDocIdSepe',
                                    validateOnChange: false
                                }
                            ]
                        },
                        {
                            xtype: 'form',
                            id: 'complfile4',
                            padding: '10 10 0 10',
                            hidden: true,
                            standardSubmit: false,
                            items: [
                                {
                                    xtype: 'filefield',
                                    anchor: '100%',
                                    frame: false,
                                    fieldLabel: 'Επισυναπτόμενο Αρχείο - Σε περιπτώσεις διαγωνισμών Α.Σ.Ε.Π., βεβαίωση προϋπηρεσίας θεωρείται από την οικεία Επιθεώρηση Εργασίας μόνο εφόσον τούτο αναγράφεται ρητά στη συγκεκριμένη προκήρυξη και μόνο κατόπιν προσκομίσεως φωτοαντιγράφου της σχετικής προκήρυξης, που απαιτεί τέτοια θεώρηση ',
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
                            itemId: 'Exist_File4',
                            margin: '0 0 0 20',
                            layout: {
                                type: 'hbox',
                                align: 'stretch'
                            },
                            items: [
                                {
                                    xtype: 'label',
                                    hidden: true,
                                    baseCls: 'x-form-item-label',
                                    height: 24,
                                    padding: '5 0 0 0',
                                    text: 'Αποθηκευμένο αρχείο: '
                                },
                                {
                                    xtype: 'button',
                                    hidden: true,
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
                                    hidden: true,
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
                                    name: 'deletedfile4',
                                    validateOnChange: false,
                                    value: false
                                },
                                {
                                    xtype: 'hiddenfield',
                                    flex: 1,
                                    itemId: 'fid',
                                    fieldLabel: 'Επισυναπτόμενο Αρχείο',
                                    labelWidth: 180,
                                    msgTarget: 'under',
                                    name: 'attachedDocIdJudgmnt',
                                    validateOnChange: false
                                }
                            ]
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            margin: '5 0 0 0',
                            fieldLabel: 'Παρατηρήσεις (Σε περίπτωση χρήσης για διαγωνισμό ΑΣΕΠ, παρακαλώ εισάγετε τον αριθμό της προκήρυξης)',
                            labelWidth: 180,
                            name: 'notes',
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
            dateToTimestamp: function(date) {
                var pD = date.split("-");
                return (pD[2]+"-"+pD[1]+"-"+pD[0]+"T00:00:00.000+0000");
            },
            validateDatesDifference: function(dateFrom, dateUntil) {
                if (dateFrom.getValue()>=dateUntil.getValue()){
                    dateFrom.markInvalid("H ημ/νία Από πρέπει να είναι προγενέστερη από την Εως");
                    dateUntil.markInvalid("H ημ/νία Εως πρέπει να είναι προγενέστερη από την Από");
                    return false;
                }
                else
                return true;
            },
            dock: 'bottom',
            html: '<span style="color:#696969;font-size:11px;"><em>*Τα ανενεργά πεδία θα συμπληρωθούν αυτόματα<br>από το σύστημα κατά την υποβολή</em></span>',
            id: 'exper_save_submit_toolbar',
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
                    itemId: 'deleteexperiencebutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf00d@FontAwesome',
                    text: 'Διαγραφή',
                    tooltip: 'Διαγραφή  Αποθηκευμένης Αίτησης',
                    listeners: {
                        click: {
                            fn: 'onDelete_EMPLOYEE_EXPERIENCE',
                            scope: 'controller'
                        }
                    }
                },
                {
                    xtype: 'button',
                    getFilesIds: function(form) {
                        var fIds = [];
                        if (Ext.getCmp("complfile1").getForm().findField("file").getValue()!=="")
                        fIds.push(Ext.getCmp("complfile1"));
                        if (Ext.getCmp("complfile2").getForm().findField("file").getValue()!=="")
                        fIds.push(Ext.getCmp("complfile2"));
                        if (Ext.getCmp("complfile3").getForm().findField("file").getValue()!=="")
                        fIds.push(Ext.getCmp("complfile3"));
                        if (Ext.getCmp("complfile4").getForm().findField("file").getValue()!=="")
                        fIds.push(Ext.getCmp("complfile4"));

                        return fIds;
                    },
                    itemId: 'saveexperiencebutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf0c7@FontAwesome',
                    text: 'Αποθήκευση',
                    listeners: {
                        click: {
                            fn: 'onSave_EMPLOYEE_EXPERIENCE',
                            scope: 'controller'
                        }
                    }
                },
                {
                    xtype: 'button',
                    getFilesIds: function(form) {
                        var fIds = [];
                        if (Ext.getCmp("complfile1").getForm().findField("file").getValue()!=="")
                        fIds.push(Ext.getCmp("complfile1"));
                        if (Ext.getCmp("complfile2").getForm().findField("file").getValue()!=="")
                        fIds.push(Ext.getCmp("complfile2"));
                        if (Ext.getCmp("complfile3").getForm().findField("file").getValue()!=="")
                        fIds.push(Ext.getCmp("complfile3"));
                        if (Ext.getCmp("complfile4").getForm().findField("file").getValue()!=="")
                        fIds.push(Ext.getCmp("complfile4"));

                        return fIds;
                    },
                    itemId: 'submitexperiencebutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf1d8@FontAwesome',
                    text: 'Υποβολή',
                    listeners: {
                        click: {
                            fn: 'onSubmit_EMPLOYEE_EXPERIENCE',
                            scope: 'controller'
                        }
                    }
                }
            ],
            listeners: {
                beforehide: 'onExper_save_submit_toolbarBeforeHide'
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

    onSpecialityExpand1: function(queryPlan, eOpts) {
        var field=queryPlan.combo;
                    if (field.getValue()===null)
                                    {
                                        field.clearValue();
                                        field.getStore().getProxy().url = "/TEmployeeSpeciality";
                                        field.getStore().getProxy().getReader().setRootProperty("_embedded.TEmployeeSpeciality");
                                    }
                                    else{
                                       field.getStore().getProxy().url = "/TEmployeeSpeciality/search/findByDescr";
                                        field.getStore().getProxy().getReader().setRootProperty("_embedded.TEmployeeSpeciality");
                                    }

    },

    onAddrPSelect3: function(combo, records, eOpts) {
                var getnext = combo.up('form').getForm().findField('empAddrPe');
                getnext.clearValue();
                getnext.enable();
    },

    onAddrPeExpand3: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
                queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
                queryPlan.query=queryPlan.combo.up('form').getForm().findField('empAddrP').getValue()+" ";
    },

    onAddrPeSelect3: function(combo, records, eOpts) {
                var getnext = combo.up('form').getForm().findField('empAddrD');
                getnext.clearValue();
                getnext.enable();
    },

    onAddrPeDirtyChange3: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalPe/"+field.getValue()+" ";
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onAddrDExpand3: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalD");
        queryPlan.combo.getStore().getProxy().url = "/TKalD/search/findByEnotCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('empAddrPe').getValue()+" ";
    },

    onAddrDSelect3: function(combo, records, eOpts) {
                var getnext = combo.up('form').getForm().findField('empAddrK');
                getnext.clearValue();
                getnext.enable();
    },

    onAddrDDirtyChange3: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalD/"+field.getValue()+" ";
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        }
    },

    onAddrKExpand3: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalK");
        queryPlan.combo.getStore().getProxy().url = "/TKalK/search/findByDimosCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('empAddrD').getValue();
    },

    onAddrKDirtyChange3: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalK/"+field.getValue();
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        }
    },

    onAFMBlur1: function(component, event, eOpts) {
        var branchId = component.up('form').getForm().findField('branch1Id');
        if (component.up('form').getForm().findField('subStatus').getValue()!=="2"){
            if (component.getValue().length>0){
            branchId.getStore().getProxy().extraParams = {afm:component.getValue()};
            branchId.getStore().load({callback: function(records, operation, success) {
                    /*if (records.length>0) {
                        component.autofillCompFields(component.up('form').getForm());
                    } else {
                        Ext.Msg.alert('Πρόβλημα Α.Φ.Μ.', 'Το Α.Φ.Μ δεν αντιστοιχεί σε κάποιο οργανισμό');
                    }*/
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

    onBranch1Select: function(combo, records, eOpts) {
        //var store = Ext.StoreMgr.lookup( 'AFM_COMP' );
        //var centralId = store.findRecord('branchId',0).get('abbr');
        //combo.up('form').getForm().findField('branch0').setValue(centralId);
        var formToFill = combo.up('form').getForm();
        //combo.up('form').getForm().findField('branch0Id').setValue(0);


        //DISABLE BELOW PART TO STOP AUTOFILL
        value = combo.getValue();
        selectedrec = combo.findRecordByValue(value);

        combo.autofillCompFields(formToFill, selectedrec);
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

    onDeleteClick1: function(button, e, eOpts) {
        button.up('form').getForm().findField('deletedfile1').setValue(true);
        button.up('container').hide();
        button.up('form').getForm().findField('file').focus();
    },

    onViewDocClick1: function(button, e, eOpts) {
        var url = "/getDocument?docId="+button.up('form').getForm().findField('attachedDocIdEmplVer').getValue();
        var win = window.open(url, '_blank');
          win.focus();
    },

    onDeleteClick11: function(button, e, eOpts) {
        button.up('form').getForm().findField('deletedfile2').setValue(true);
        button.up('container').hide();
        button.up('form').getForm().findField('file').focus();
    },

    onViewDocClick11: function(button, e, eOpts) {
        var url = "/getDocument?docId="+button.up('form').getForm().findField('attachedDocIdIka').getValue();
        var win = window.open(url, '_blank');
          win.focus();
    },

    onDeleteClick2: function(button, e, eOpts) {
        button.up('form').getForm().findField('deletedfile3').setValue(true);
        button.up('container').hide();
        button.up('form').getForm().findField('file').focus();
    },

    onViewDocClick2: function(button, e, eOpts) {
        var url = "/getDocument?docId="+button.up('form').getForm().findField('attachedDocIdSepe').getValue();
        var win = window.open(url, '_blank');
        win.focus();
    },

    onDeleteClick: function(button, e, eOpts) {
        button.up('form').getForm().findField('deletedfile4').setValue(true);
        button.up('container').hide();
        button.up('form').getForm().findField('file').focus();
    },

    onViewDocClick: function(button, e, eOpts) {
        var url = "/getDocument?docId="+button.up('form').getForm().findField('attachedDocIdJudgmnt').getValue();
        var win = window.open(url, '_blank');
          win.focus();
    },

    onWindowBeforeDestroy: function(component, eOpts) {
        var view=Ext.getCmp('employeemainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.employee.ExperiencesPanel');
        center.add(viewsub);
    },

    onExper_save_submit_toolbarBeforeHide: function(component, eOpts) {
        component.getComponent('deleteexperiencebutton').destroy();
        component.getComponent('saveexperiencebutton').destroy();
        component.getComponent('submitexperiencebutton').destroy();
    }

});