/*
 * File: app/view/company/TechnicianAnn/ShipTechForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianAnn.ShipTechForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companytechnicianannshiptechform',

    requires: [
        'MyApp.view.company.TechnicianAnn.ShipTechViewModel',
        'MyApp.view.company.TechnicianAnn.ShipTechFormViewController',
        'MyApp.view.shared.PrintFormTool',
        'MyApp.view.shared.CloseFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.FieldSet',
        'Ext.form.field.Date',
        'Ext.form.field.Number',
        'Ext.form.field.TextArea',
        'Ext.button.Button',
        'Ext.form.field.Display',
        'Ext.form.field.Time',
        'Ext.form.field.File',
        'Ext.form.Label',
        'Ext.toolbar.Toolbar',
        'Ext.panel.Tool'
    ],

    controller: 'companytechnicianannshiptechform',
    viewModel: {
        type: 'companytechnicianannshiptechform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    overflowY: 'scroll',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Αναγγελία Τεχνικού Ασφαλείας σε Πλοία και Ναυπηγικές Εργασίες',
    //modal: true,
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'form',
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
                    flex: 1,
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
                        },
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
                            xtype: 'fieldset',
                            focusOnToFront: false,
                            toFrontOnShow: false,
                            title: 'Διεύθυνση Έργου',
                            items: [
                                {
                                    xtype: 'textareafield',
                                    anchor: '100%',
                                    fieldLabel: 'Οδός / Αριθμός',
                                    labelWidth: 180,
                                    name: 'projAddr',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 50
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
                                        select: 'onAddrPSelect1'
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
                                    name: 'projAddrD',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
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
                                    name: 'projAddrTk',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 5,
                                    minLength: 5,
                                    vtype: 'Number'
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Αρμόδιο Λιμεναρχείο',
                                    labelWidth: 180,
                                    name: 'portAuthority',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    editable: false,
                                    autoLoadOnValue: true,
                                    displayField: 'item',
                                    store: 'technician.PORT_AUTHORITY',
                                    valueField: 'id'
                                },
                                /*{
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Αρμόδιο Λιμεναρχείο',
                                    labelWidth: 180,
                                    name: 'portAuthority',
                                    validateOnChange: false,
                                    readOnly: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 200
                                },*/
                                
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Ναυπηγείο',
                                    labelWidth: 180,
                                    name: 'shipyard',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    editable: false,
                                    autoLoadOnValue: true,
                                    displayField: 'item',
                                    store: 'technician.SHIPYARDS',
                                    valueField: 'id'
                                },
                                /*{
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Ναυπηγείο',
                                    labelWidth: 180,
                                    name: 'shipyard',
                                    validateOnChange: false,
                                    readOnly: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 200
                                }*/
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            id: 'sanncontrs',
                            title: 'ΕΡΓΟΛΑΒΟΣ(ΟΙ) & ΥΠΕΡΓΟΛΑΒΟΣ(ΟΙ)',
                            items: [
                                {
                                    xtype: 'button',
                                    border: 0,
                                    frame: false,
                                    id: 'sanncontrsadd',
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
                                    id: 'sanncontrsdel',
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
                            xtype: 'fieldset',
                            focusOnToFront: false,
                            padding: 10,
                            toFrontOnShow: false,
                            title: 'ΣΤΟΙΧΕΙΑ ΤΕΧΝΙΚΟΥ ΑΣΦΑΛΕΙΑΣ',
                            items: [
                                {
                                    xtype: 'textfield',
                                    autofillTechFields: function(formPart, selectedrec) {
                                        //Autofill for Doctor
                                        var scrollpos = formPart.owner.up('window').getScrollY();
                                        if (selectedrec.get('fullname')!==null){
                                            formPart.findField('taFullname').setValue(selectedrec.get('fullname'));
                                        }

                                        if (selectedrec.get('speciality')!==null){
                                            formPart.findField('taSpeciality').setValue(Ext.util.JSON.decode("[\""+selectedrec.get('speciality')+"\"]"));
                                        }

                                        //if (parseInt(selectedrec.get('speciality'))===99){
                                        if (selectedrec.get('speciality')!=null && selectedrec.get('speciality').toString().indexOf('99') !== -1){
                                            formPart.findField('taSpecialityOther').setValue(selectedrec.get('specialityOther'));
                                            formPart.findField('taSpecialityOther').show();
                                            formPart.findField('taSpecialityOther').allowBlank=false;
                                            formPart.findField('taSpecialityOther').allowOnlyWhitespace=false;
                                        }
                                        else{
                                            formPart.findField('taSpecialityOther').setValue();
                                            formPart.findField('taSpecialityOther').hide();
                                            formPart.findField('taSpecialityOther').allowBlank=true;
                                            formPart.findField('taSpecialityOther').allowOnlyWhitespace=true;
                                        }

                                        if (selectedrec.get('technicianReqId')!==null){
                                            formPart.findField('technicianRegrequestId').setValue(selectedrec.get('technicianReqId'));
                                        }
                                        if (selectedrec.get('technicianReqUserId')!==null){
                                            formPart.findField('technicianRegrequestUserId').setValue(selectedrec.get('technicianReqUserId'));
                                        }

                                        formPart.owner.up('window').scrollTo(0, scrollpos);
                                    },
                                    anchor: '100%',
                                    fieldLabel: 'Α.Φ.Μ',
                                    labelWidth: 180,
                                    name: 'taAfm',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    emptyText: 'Καταχωρήστε του ΑΦΜ του Τεχνικού εγγεγραμμένου στο μητρώο',
                                    maxLength: 200,
                                    listeners: {
                                        blur: 'onAFMBlur',
                                        specialkey: 'onAFMEnterKey'
                                    }
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Ονοματεπώνυμο',
                                    labelWidth: 180,
                                    name: 'taFullname',
                                    validateOnChange: false,
                                    readOnly: true,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 200
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Ειδικότητα',
                                    labelWidth: 180,
                                    name: 'taSpeciality',
                                    validateOnChange: false,
                                    readOnly: true,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    editable: false,
                                    autoLoadOnValue: true,
                                    multiSelect: true,
                                    displayField: 'description',
                                    valueField: 'abbr',
                                    bind: {
                                        store: '{TECHNICIAN_SPECIALITY}'
                                    },
                                    listeners: {
                                        change: 'onSpecialitySelect'
                                    }
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    hidden: true,
                                    fieldLabel: '',
                                    hideEmptyLabel: false,
                                    labelWidth: 180,
                                    name: 'taSpecialityOther',
                                    validateOnChange: false,
                                    value: '',
                                    readOnly: true,
                                    validateOnBlur: false,
                                    emptyText: 'Λεκτικό Ειδικότητας',
                                    maxLength: 50
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Ιδιότητα Συνεργασίας',
                                    labelWidth: 180,
                                    name: 'cooperationType',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    editable: false,
                                    autoLoadOnValue: true,
                                    displayField: 'description',
                                    store: 'shared.COOPERATION_TYPE',
                                    valueField: 'abbr'
                                }
                            ]
                        },
                        {
                            xtype: 'numberfield',
                            anchor: '100%',
                            fieldLabel: 'Μέγιστος Αριθμός Εργαζομένων',
                            labelWidth: 180,
                            msgTarget: 'under',
                            name: 'projEmplNum',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 100,
                            allowDecimals: false,
                            allowExponential: false,
                            minValue: 1,
                            listeners: {
                                change: 'onEmplNumSelect'
                            }
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            fieldLabel: 'Κατηγορία Επικινδυνότητας Εργασιών',
                            labelWidth: 180,
                            msgTarget: 'under',
                            name: 'shipDangerZone',
                            validateOnChange: false,
                            validateOnBlur: false,
                            disabled: true,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            editable: false,
                            autoLoadOnValue: true,
                            displayField: 'name',
                            transformInPlace: false,
                            valueField: 'value',
                            bind: {
                                store: '{Ship_Category}'
                            },
                            listeners: {
                                change: 'onShipDangerZoneSelect'
                            }
                        },
                        {
                            xtype: 'displayfield',
                            fieldLabel: 'Ημερήσιες Ελάχιστες απαιτούμενες ώρες απασχόλησης',
                            labelStyle: 'font-weight: bold',
                            labelWidth: 380,
                            name: 'dayTotalHours',
                            submitValue: true,
                            value: 0
                        },
                        {
                            xtype: 'fieldset',
                            focusOnToFront: false,
                            toFrontOnShow: false,
                            title: 'Διάστημα Αναγγελίας',
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
                            focusOnToFront: false,
                            toFrontOnShow: false,
                            title: 'Διάστημα Εργασιών',
                            items: [
                                {
                                    xtype: 'datefield',
                                    anchor: '100%',
                                    fieldLabel: 'Ημ. Έναρξης Εργασιών',
                                    labelWidth: 180,
                                    msgTarget: 'under',
                                    name: 'projStartDate',
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
                                    xtype: 'numberfield',
                                    anchor: '100%',
                                    fieldLabel: 'Διάρκεια σε ημέρες',
                                    labelWidth: 180,
                                    msgTarget: 'under',
                                    name: 'projDuration',
                                    validateOnChange: false,
                                    readOnly: true,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 100,
                                    allowDecimals: false,
                                    allowExponential: false,
                                    minValue: 1
                                },
                                {
                                    xtype: 'timefield',
                                    anchor: '100%',
                                    fieldLabel: 'Έναρξη Ωραρίου',
                                    labelWidth: 180,
                                    msgTarget: 'under',
                                    name: 'startTime',
                                    validateOnChange: false,
                                    readOnly: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 100,
                                    format: 'H:i',
                                    listeners: {
                                        expand: 'onTimefieldExpand'
                                    }
                                },
                                {
                                    xtype: 'hiddenfield',
                                    anchor: '100%',
                                    labelWidth: 180,
                                    msgTarget: 'under',
                                    name: 'durationDay',
                                    validateOnChange: false
                                }
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            hidden: true,
                            id: 'tasdiaryEntries',
                            title: 'Επισκέψεις Τεχνικού Ασφάλειας',
                            items: [
                                {
                                    xtype: 'button',
                                    border: 0,
                                    frame: false,
                                    id: 'tasentryadd',
                                    style: 'background-color:transparent;',
                                    iconCls: 'addone',
                                    tooltip: 'Προσθήκη επίσκεψης',
                                    listeners: {
                                        click: 'onAddEntryClick'
                                    }
                                },
                                {
                                    xtype: 'button',
                                    border: 0,
                                    frame: false,
                                    id: 'tasentrydel',
                                    style: 'background-color:transparent;',
                                    iconCls: 'removeone',
                                    tooltip: 'Διαγραφή τελευταίας επίσκεψης',
                                    listeners: {
                                        click: 'onDelEntryClick'
                                    }
                                },
                                {
                                    xtype: 'hiddenfield',
                                    anchor: '100%',
                                    labelAlign: 'top',
                                    labelWidth: 180,
                                    name: 'diaryEntriesnum',
                                    validateOnChange: false,
                                    value: 1
                                },
                                {
                                    xtype: 'hiddenfield',
                                    anchor: '100%',
                                    labelWidth: 180,
                                    msgTarget: 'under',
                                    name: 'diaryEntries',
                                    validateOnChange: false
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
                            standardSubmit: false,
                            items: [
                                {
                                    xtype: 'filefield',
                                    anchor: '100%',
                                    frame: false,
                                    fieldLabel: 'Επισυναπτόμενο Αρχείο - Αντίγραφο Σύμβασης με Τεχνικό',
                                    labelAlign: 'top',
                                    labelWidth: 180,
                                    msgTarget: 'under',
                                    name: 'file',
                                    submitValue: true,
                                    validateOnChange: false,
                                    inputId: 'file',
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    blankText: 'Πρέπει να επισυνάψετε την σύμβαση με τον ΤΑ',
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
                        }
                    ]
                },
                {
                    xtype: 'hiddenfield',
                    width: 150,
                    labelWidth: 180,
                    name: 'compShipId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    width: 150,
                    labelWidth: 180,
                    name: 'technicianRegrequestId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    width: 150,
                    labelWidth: 180,
                    name: 'technicianRegrequestUserId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    width: 150,
                    labelWidth: 180,
                    name: 'compEbrBranch0Id',
                    validateOnChange: false,
                    value: 0
                },
                {
                    xtype: 'hiddenfield',
                    width: 150,
                    labelWidth: 180,
                    name: 'compEbrBranchId',
                    validateOnChange: false,
                    value: 0
                },
                {
                    xtype: 'hiddenfield',
                    width: 150,
                    labelWidth: 180,
                    name: 'companyId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    width: 150,
                    labelWidth: 180,
                    name: 'compTaSannPrevId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    width: 150,
                    labelWidth: 180,
                    name: 'taSannStatus',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    width: 150,
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
                if (date != "")
                {
                    var pD = date.split("-");
                    if(pD[0].length===1)
                    pD[0]="0"+pD[0];
                    if(pD[1].length===1)
                    pD[1]="0"+pD[1];
                    return (pD[2]+"-"+pD[1]+"-"+pD[0]+"T00:00:00.000+0000");
                }
                else return null;
            },
            dock: 'bottom',
            html: '<span style="color:#696969;font-size:11px;"><em>*Τα ανενεργά πεδία θα συμπληρωθούν αυτόματα<br>από το σύστημα κατά την υποβολή</em></span>',
            id: 'comptechsann_save_submit_toolbar',
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
                            fn: 'onDelete_TECHNICIAN_SANN',
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
                            fn: 'onSave_TECHNICIAN_SANN',
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
                            fn: 'onSubmit_TECHNICIAN_SANN',
                            scope: 'controller'
                        }
                    }
                },
                {
                    xtype: 'button',
                    hidden: true,
                    itemId: 'changebutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 180,
                    glyph: 'xf1d8@FontAwesome',
                    text: 'Παύση - Αντικατάσταση',
                    listeners: {
                        click: {
                            fn: 'onChange_TECHNICIAN_SANN',
                            scope: 'controller'
                        }
                    }
                }
            ],
            listeners: {
                beforehide: 'onSAnnTa_save_submit_toolbarBeforeHide'
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

    onEmplNumSelect: function(combo, records, eOpts) {
        var subStatus = combo.up('form').getForm().findField('subStatus');
        if (subStatus.value != 2) {
            var getnext = combo.up('form').getForm().findField('shipDangerZone');
            var dayTotalHours = combo.up('form').getForm().findField('dayTotalHours');
            //dayTotalHours.setValue("0");
            if (records > 149) {
                getnext.getStore().clearFilter();
                getnext.getStore().setFilters(function(record, id){
                    return (record.data.value < 2);
                });
            } else if (records > 49 && records < 150) {
                getnext.getStore().clearFilter();
                getnext.getStore().setFilters(function(record, id){
                    return (record.data.value < 3);
                });
            } else {
                getnext.getStore().clearFilter();
            }
            getnext.clearValue();
            getnext.enable();
        }
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

    onAddrPSelect1: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('projAddrPe');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeExpand1: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
        queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('projAddrP').getValue()+" ";
    },

    onAddrPeSelect1: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('projAddrD');
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
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('projAddrPe').getValue()+" ";
    },

    onAddrDSelect1: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('projAddrK');
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
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('projAddrD').getValue();
    },

    onAddrKDirtyChange1: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalK/"+field.getValue();
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onAddContrClick: function(button, e, eOpts) {
        var projscontr = Ext.create('widget.companytechnicianannshipcontr', {});
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

    onAFMBlur: function(component, event, eOpts) {
        var scrollpos=component.up('window').getScrollY();
        if (component.getValue().length>0){
            var store = Ext.StoreMgr.lookup( 'company.TECHNICIAN_INFO' );
            var formPart = component.up('form').getForm();
                store.getProxy().extraParams = {afm:component.getValue(), ta:'tas', branchSectorId: -1, taSum: -1};
                store.load({callback: function(records, operation, success) {
                    if (records[0].get('success')) {
                        component.autofillTechFields(formPart,records[0]);
                    } else {
                        Ext.Msg.alert('Πρόβλημα Α.Φ.Μ.', 'Το Α.Φ.Μ δεν αντιστοιχεί σε κάποιo Τεχνικό Ασφάλειας');
                        formPart.findField('taFullname').setValue();
                        formPart.findField('taSpeciality').setValue();
                        formPart.findField('taSpecialityOther').hide();
                        formPart.findField('taSpecialityOther').allowBlank=true;
                        formPart.findField('taSpecialityOther').allowOnlyWhitespace=true;
                        formPart.findField('taSpecialityOther').setValue();
                        formPart.findField('technicianRegrequestId').setValue();
                        component.setValue("");
                        component.up('window').scrollTo(0, scrollpos);
                    }
                }});
            }
            else
               {
                    var formPart = component.up('form').getForm();
                    component.setValue("");
                    formPart.findField('taFullname').setValue();
                    formPart.findField('taSpeciality').setValue();
                    formPart.findField('taSpecialityOther').hide();
                    formPart.findField('taSpecialityOther').allowBlank=true;
                    formPart.findField('taSpecialityOther').allowOnlyWhitespace=true;
                    formPart.findField('taSpecialityOther').setValue();
                    formPart.findField('technicianRegrequestId').setValue();
               }
        component.up('window').scrollTo(0, scrollpos);
    },

    onAFMEnterKey: function(field, e, eOpts) {
        if (e.getKey() == e.ENTER) {
            field.blur();
        }
    },

    onSpecialitySelect: function(field, newValue, oldValue, eOpts) {
        var combo = field;
        var scrollpos=combo.up('window').getScrollY();
        if (parseInt(newValue)===99){
            combo.up('form').getForm().findField('taSpecialityOther').show();
            combo.up('form').getForm().findField('taSpecialityOther').allowBlank=false;
            combo.up('form').getForm().findField('taSpecialityOther').allowOnlyWhitespace=false;
        }
        else{
            combo.up('form').getForm().findField('taSpecialityOther').setValue();
            combo.up('form').getForm().findField('taSpecialityOther').hide();
            combo.up('form').getForm().findField('taSpecialityOther').allowBlank=true;
            combo.up('form').getForm().findField('taSpecialityOther').allowOnlyWhitespace=true;
        }
        combo.up('window').scrollTo(0, scrollpos);
    },

    onShipDangerZoneSelect: function(field, newValue, oldValue, eOpts) {
        var scrollpos = field.up('window').getScrollY();
        if(parseInt(newValue)===1)
            field.up('form').getForm().findField('dayTotalHours').setValue("5");
        else if(parseInt(newValue)===2)
            field.up('form').getForm().findField('dayTotalHours').setValue("2");
        else if(parseInt(newValue)===3)
            field.up('form').getForm().findField('dayTotalHours').setValue("1");
        field.up('window').scrollTo(0, scrollpos);
    },

    onTimefieldExpand: function(field, eOpts) {
        var scrollpos = field.up('window').getScrollY();
        if(parseInt(field.up('form').getForm().findField('dayTotalHours').getValue())===0){
            Ext.Msg.alert('Προσοχή', 'Ωρίστε την επικινδυνότητα των εργασιών <br>ώστε να μπορεί να υπολογιστεί η λήξη ωραρίου');
        }
        else{
            field.up('form').getForm().findField('durationDay').setValue(60*parseInt(field.up('form').getForm().findField('dayTotalHours').getValue()));
        }
        field.clearInvalid();
        field.up('window').scrollTo(0, scrollpos);
    },

    onAddEntryClick: function(button, e, eOpts) {
        if(parseInt(button.up('form').getForm().findField('dayTotalHours').getValue())===0){
            var entrie = Ext.create('widget.companytechniciananndiary', {});
            button.up('form').getForm().findField('diaryEntriesnum').setValue(parseInt(button.up('form').getForm().findField('diaryEntriesnum').getValue())+1);
            entrie.items.get(0).minValue= (button.up('form').getForm().findField('dateStart').value > new Date()) ? button.up('form').getForm().findField('dateStart').value : new Date();
            entrie.items.get(0).maxValue=button.up('form').getForm().findField('dateEnd').value;
            if(parseInt(button.up('form').getForm().findField('diaryEntriesnum').getValue())>1){
                entrie.items.get(0).hideLabel=true;
                entrie.items.get(1).hideLabel=true;
                entrie.items.get(2).hideLabel=true;
                entrie.items.get(2).minValue=60*parseInt(field.up('form').getForm().findField('dayTotalHours').getValue());
            }

            entrie.items.get(2).hidden=false;entrie.down().items.get(2).readOnly=false;
            entrie.items.get(3).hidden=false;entrie.down().items.get(4).hidden=false;entrie.down().items.get(5).hidden=false;
            entrie.items.get(3).allowBlank=true;entrie.down().items.get(4).allowBlank=true;entrie.down().items.get(5).allowBlank=true;

            var scrollpos = button.up('window').getScrollY();
            button.up().add(entrie);
            button.up('window').scrollTo(0, scrollpos);
        }
        else
            Ext.Msg.alert('Προσοχή', 'Ωρίστε την επικινδυνότητα των εργασιών <br>ώστε να μπορούν να οριστούν τα ελάχιστα λεπτά απασχόλησης του ΤΑ');

    },

    onDelEntryClick: function(button, e, eOpts) {
        if(parseInt(button.up('form').getForm().findField('diaryEntriesnum').getValue())!==1){
            button.up('form').getForm().findField('diaryEntriesnum').setValue(parseInt(button.up('form').getForm().findField('diaryEntriesnum').getValue())-1);
            var scrollpos = button.up('window').getScrollY();
            button.up().items.get(button.up().items.length-1).destroy();
            button.up('window').scrollTo(0, scrollpos);
        }
    },

    onDeleteClick1: function(button, e, eOpts) {
        var subStatus = button.up('form').getForm().findField('subStatus');
        if (subStatus.value == '') {
            button.up('form').getForm().findField('deletedfile').setValue(true);
            button.up('form').getForm().findField('attachedDocId').setValue(-1);
            button.up('container').hide();
            button.up('form').getForm().findField('file').show();
            button.up('form').getForm().findField('file').focus();
        } else {
            button.up('form').getForm().findField('deletedfile').setValue(true);
            button.up('container').hide();
            button.up('form').getForm().findField('file').focus();
        }
    },

    onViewDocClick1: function(button, e, eOpts) {
        var url = "/getDocument?docId="+button.up('form').getForm().findField('attachedDocId').getValue();
        var win = window.open(url, '_blank');
        win.focus();
    },

    onSAnnTa_save_submit_toolbarBeforeHide: function(component, eOpts) {
        component.getComponent('savebutton').destroy();
        component.getComponent('submitbutton').destroy();
    },

    onWindowBeforeDestroy: function(component, eOpts) {
        var shipId = component.down('form').getForm().findField('compShipId').getValue();
        var technician = Ext.getCmp('companyTechnicianShipAnn_Technicians');
        technician.store.proxy.setUrl('/compTaSann/search/findByCompShipId?compShipId='+shipId);
        technician.store.load( { callback : function(records, operation, success) {
            technician.getView().refresh();
        }
        });
    }

});