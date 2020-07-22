/*
 * File: app/view/doctor/RegRequestForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.doctor.RegRequestForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.doctorregrequestform',

    requires: [
        'MyApp.view.doctor.RegRequestFormViewModel',
        'MyApp.view.doctor.RegRequestFormViewController',
        'MyApp.view.shared.PrintFormTool',
        'MyApp.view.shared.CloseFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.FieldSet',
        'Ext.form.field.Date',
        'Ext.form.field.Number',
        'Ext.form.field.ComboBox',
        'Ext.form.RadioGroup',
        'Ext.form.field.Radio',
        'Ext.form.field.TextArea',
        'Ext.form.field.File',
        'Ext.form.Label',
        'Ext.button.Button',
        'Ext.toolbar.Toolbar',
        'Ext.panel.Tool'
    ],

    controller: 'doctorregrequestform',
    viewModel: {
        type: 'doctorregrequestform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    overflowY: 'scroll',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Αίτηση Εγγραφής στο Μητρώο Ιατρών Εργασίας',
    //modal: true,
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'form',
            id: 'doctorregrequestform',
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
                        }
                    ]
                },
                {
                    xtype: 'fieldset',
                    flex: 1,
                    title: 'ΚΑΤΑΣΤΑΣΗ',
                    items: [
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
                            xtype: 'radiogroup',
                            anchor: '70%',
                            disabled: true,
                            fieldLabel: 'Έλεγχος Προφίλ από ΣΕΠΕ',
                            labelWidth: 180,
                            validateOnChange: false,
                            items: [
                                {
                                    xtype: 'radiofield',
                                    name: 'sepeProfilStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Ναι',
                                    inputValue: '1'
                                },
                                {
                                    xtype: 'radiofield',
                                    name: 'sepeProfilStatus',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Οχι',
                                    inputValue: '0'
                                }
                            ]
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
                            disabled: true,
                            fieldLabel: 'Αρμόδιο Τμήμα ΣΕΠΕ',
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
                            title: 'ΠΡΟΣΩΠΙΚΑ ΣΤΟΙΧΕΙΑ',
                            items: [
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    cls: 'x-item-disabled',
                                    fieldLabel: 'Όνομα',
                                    labelWidth: 180,
                                    name: 'firstname',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    editable: false,
                                    maxLength: 50
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    cls: 'x-item-disabled',
                                    fieldLabel: 'Επώνυμο',
                                    labelWidth: 180,
                                    name: 'lastname',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    editable: false
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    cls: 'x-item-disabled',
                                    fieldLabel: 'Πατρώνυμο',
                                    labelWidth: 180,
                                    name: 'fatherrname',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    editable: false
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    cls: 'x-item-disabled',
                                    fieldLabel: 'ΑΦΜ',
                                    labelWidth: 180,
                                    name: 'afm',
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
                                    cls: 'x-item-disabled',
                                    fieldLabel: 'ΑΜΚΑ',
                                    labelWidth: 180,
                                    name: 'amka',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    editable: false,
                                    maxLength: 50
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    cls: 'x-item-disabled',
                                    fieldLabel: 'Αρ. Αποδεικτικού Ταυτότητας',
                                    labelWidth: 180,
                                    name: 'cardNumber',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    editable: false,
                                    maxLength: 50
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'e-mail',
                                    labelWidth: 180,
                                    name: 'email',
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    vtype: 'email'
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Σταθερό Τηλέφωνο',
                                    labelWidth: 180,
                                    name: 'phone',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 10,
                                    vtype: 'telNumber',
                                    blankText: 'Το σταθερό ή το κινητό τηλέφωνο είναι υποχρεωτικό'
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Κινητό Τηλέφωνο',
                                    labelWidth: 180,
                                    name: 'mobile',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 10,
                                    vtype: 'telNumber',
                                    blankText: 'Το σταθερό ή το κινητό τηλέφωνο είναι υποχρεωτικό'
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Fax',
                                    labelWidth: 180,
                                    name: 'fax',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 10,
                                    vtype: 'telNumber'
                                },
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            focusOnToFront: false,
                            toFrontOnShow: false,
                            title: 'Διεύθυνση Κατοικίας ',
                            items: [
                                {
                                    xtype: 'textareafield',
                                    anchor: '100%',
                                    fieldLabel: 'Οδός / Αριθμός',
                                    labelWidth: 180,
                                    name: 'reqAddr',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 100
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Περιφέρεια',
                                    labelWidth: 180,
                                    name: 'reqAddrP',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    editable: false,
                                    autoLoadOnValue: true,
                                    displayField: 'descr',
                                    store: 'address.ADDR_P',
                                    valueField: 'abbr',
                                    listeners: {
                                        select: 'onAddrPSelect111'
                                    }
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    disabled: true,
                                    fieldLabel: 'Νομός',
                                    labelWidth: 180,
                                    name: 'reqAddrPe',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    editable: false,
                                    anyMatch: true,
                                    displayField: 'descr',
                                    queryParam: 'perifCode',
                                    store: 'address.ADDR_Pe',
                                    valueField: 'abbr',
                                    listeners: {
                                        beforequery: 'onAddrPeExpand111',
                                        select: 'onAddrPeSelect111',
                                        dirtychange: {
                                            fn: 'onAddrPeDirtyChange111',
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
                                    name: 'reqAddrD',
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
                                        beforequery: 'onAddrDExpand111',
                                        select: 'onAddrDSelect111',
                                        dirtychange: {
                                            fn: 'onAddrDDirtyChange111',
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
                                    name: 'reqAddrK',
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
                                        beforequery: 'onAddrKExpand111',
                                        dirtychange: {
                                            fn: 'onAddrKDirtyChange111',
                                            single: true
                                        }
                                    }
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Τ.Κ',
                                    labelWidth: 180,
                                    name: 'reqAddrTk',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 5,
                                    minLength: 5,
                                    vtype: 'Number'
                                }
                            ]
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            fieldLabel: 'Επίπεδο Σπουδών',
                            labelWidth: 180,
                            name: 'educationLevel',
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            autoLoadOnValue: true,
                            hidden: true,
                            displayField: 'description',
                            store: 'shared.EDUCATION_LEVEL',
                            valueField: 'abbr'
                        },
                        /*{
                            xtype: 'combobox',
                            anchor: '100%',
                            fieldLabel: 'Ειδικότητα/ες',
                            labelWidth: 180,
                            name: 'speciality',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            editable: false,
                            autoLoadOnValue: true,
                            displayField: 'description',
                            store: 'doctor.SPECIALITY',
                            valueField: 'abbr',
                            listeners: {
                                select: 'onSpecialitySelect'
                            }
                        },*/
                        /*{
                            xtype: 'checkboxgroup',
                            id: 'doctorSpecCountiess',
                            layout: 'anchor',
                            fieldLabel: 'Ειδικότητα/ες',
                            labelAlign: 'top',
                            msgTarget: 'side',
                            validateOnChange: false,
                            listeners: {
                                added: {
                                    fn: 'onLoadAfterRenderSpec',
                                    single: true
                                }
                            }
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            hidden: true,
                            fieldLabel: '',
                            hideEmptyLabel: false,
                            labelWidth: 180,
                            name: 'specialityOther',
                            validateOnChange: false,
                            value: '',
                            validateOnBlur: false,
                            emptyText: 'Λεκτικό Ειδικότητας'
                        },
                        {
                            xtype: 'checkboxgroup',
                            id: 'doctorCountiess',
                            layout: 'anchor',
                            fieldLabel: 'Ιατρικός Σύλλογος',
                            labelAlign: 'top',
                            msgTarget: 'side',
                            validateOnChange: false,
                            listeners: {
                                added: {
                                    fn: 'onLoadAfterRender',
                                    single: true
                                }
                            }
                        },*/
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            readOnly: true,
                            fieldLabel: 'Τίτλος Ειδικότητας',
                            value: 'Ιατρική της Εργασίας',
                            labelWidth: 180,
                            name: 'spec',
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            maxLength: 50
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
                            id: 'docfile1',
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
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    validateOnChange: false,
                                    inputId: 'file',
                                    validateOnBlur: false,
                                    buttonText: 'Επιλογή...'
                                }
                            ]
                        },
                        {
                            xtype: 'label',
                            forId: 'docLabel',
                            id: 'docLabel',
                            html: '<span style="color:#666666;font-size:11px;"><i>* Παρακαλώ επισυνάψτε επίσημο έγγραφο επιβεβαίωσης της Ιατρικής Ειδικότητας π.χ. Τίτλος Ιατρικής Ειδικότητας - Βεβαίωση Ιατρικού Συλλόγου κ.α.</i></span>'
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
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            fieldLabel: 'Παρατηρήσεις',
                            labelWidth: 180,
                            name: 'descr',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 500,
                            validateBlank: true
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            disabled: true,
                            fieldLabel: 'Αιτιολογία',
                            labelWidth: 180,
                            name: 'etiology',
                            submitValue: false,
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            maxLength: 2000
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
            html: '<span style="color:#696969;font-size:11px;"><em>*Τα ανενεργά πεδία θα συμπληρωθούν αυτόματα<br>από το σύστημα κατά την υποβολή</em></span>',
            id: 'regreqie_save_submit_toolbar',
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
                            fn: 'onDelete_DOCTOR_REGREQUEST',
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
                            fn: 'onSave_DOCTOR_REGREQUEST',
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
                            fn: 'onSubmit_DOCTOR_REGREQUEST',
                            scope: 'controller'
                        }
                    }
                }
            ],
            listeners: {
                beforehide: 'onRegReqIe_save_submit_toolbarBeforeHide'
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
            var view = Ext.getCmp('doctormainView');
            var menu = view.getComponent('headerPanel');
            var contentPanel = view.getComponent('contentPanel');
            menu.setDisabled(true);
            contentPanel.setDisabled(true);
        },
        hide: function(button, e, eOpts) {
            var view = Ext.getCmp('doctormainView');
            var menu = view.getComponent('headerPanel');
            var contentPanel = view.getComponent('contentPanel');
            menu.setDisabled(false);
            contentPanel.setDisabled(false);
        }
    },

    onAddrPSelect111: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('reqAddrPe');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeExpand111: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
        queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('reqAddrP').getValue()+" ";
    },

    onAddrPeSelect111: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('reqAddrD');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeDirtyChange111: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalPe/"+field.getValue()+" ";
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onAddrDExpand111: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalD");
        queryPlan.combo.getStore().getProxy().url = "/TKalD/search/findByEnotCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('reqAddrPe').getValue()+" ";
    },

    onAddrDSelect111: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('reqAddrK');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrDDirtyChange111: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalD/"+field.getValue()+" ";
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onAddrKExpand111: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalK");
        queryPlan.combo.getStore().getProxy().url = "/TKalK/search/findByDimosCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('reqAddrD').getValue();
    },

    onAddrKDirtyChange111: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalK/"+field.getValue();
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onSpecialitySelect: function(combo, records, eOpts) {
        var scrollpos=combo.up('window').getScrollY();
        if (parseInt(records[0].get('abbr'))===99){
            combo.up('form').getForm().findField('specialityOther').show();
            combo.up('form').getForm().findField('specialityOther').allowBlank=false;
            combo.up('form').getForm().findField('specialityOther').allowOnlyWhitespace=false;
        }
        else{
            combo.up('form').getForm().findField('specialityOther').setValue();
            combo.up('form').getForm().findField('specialityOther').hide();
            combo.up('form').getForm().findField('specialityOther').allowBlank=true;
            combo.up('form').getForm().findField('specialityOther').allowOnlyWhitespace=true;
        }
        if (parseInt(records[0].get('abbr'))!==1){
            combo.up().getComponent('ieDoc').show();
            combo.up('form').getForm().findField('ieDocProtNo').show();
            combo.up('form').getForm().findField('ieDocDate').show();
            combo.up('form').getForm().findField('ieDocCompanyName').show();
            combo.up('form').getForm().findField('ieDocDepartment').show();
            combo.up('form').getForm().findField('ieDocProtNo').allowBlank=false;
            combo.up('form').getForm().findField('ieDocProtNo').allowOnlyWhitespace=false;
            combo.up('form').getForm().findField('ieDocDate').allowBlank=false;
            combo.up('form').getForm().findField('ieDocDate').allowOnlyWhitespace=false;
            combo.up('form').getForm().findField('ieDocCompanyName').allowBlank=false;
            combo.up('form').getForm().findField('ieDocCompanyName').allowOnlyWhitespace=false;
            combo.up('form').getForm().findField('ieDocDepartment').allowBlank=false;
            combo.up('form').getForm().findField('ieDocDepartment').allowOnlyWhitespace=false;
        }
        else{
            combo.up().getComponent('ieDoc').hide();
            combo.up('form').getForm().findField('ieDocProtNo').setValue();
            combo.up('form').getForm().findField('ieDocDate').setValue();
            combo.up('form').getForm().findField('ieDocCompanyName').setValue();
            combo.up('form').getForm().findField('ieDocDepartment').setValue();
            combo.up('form').getForm().findField('ieDocProtNo').hide();
            combo.up('form').getForm().findField('ieDocDate').hide();
            combo.up('form').getForm().findField('ieDocCompanyName').hide();
            combo.up('form').getForm().findField('ieDocDepartment').hide();
            combo.up('form').getForm().findField('ieDocProtNo').allowBlank=true;
            combo.up('form').getForm().findField('ieDocProtNo').allowOnlyWhitespace=true;
            combo.up('form').getForm().findField('ieDocDate').allowBlank=true;
            combo.up('form').getForm().findField('ieDocDate').allowOnlyWhitespace=true;
            combo.up('form').getForm().findField('ieDocCompanyName').allowBlank=true;
            combo.up('form').getForm().findField('ieDocCompanyName').allowOnlyWhitespace=true;
            combo.up('form').getForm().findField('ieDocDepartment').allowBlank=true;
            combo.up('form').getForm().findField('ieDocDepartment').allowOnlyWhitespace=true;
        }
        combo.up('window').scrollTo(0, scrollpos);
    },

    onDeleteClick: function(button, e, eOpts) {
        button.up('form').getForm().findField('deletedfile1').setValue(true);
        button.up('container').hide();
        button.up('form').getForm().findField('file').focus();
    },

    onViewDocClick: function(button, e, eOpts) {
        var url = "/getDocument?docId="+button.up('form').getForm().findField('attachedDocIdDiploma').getValue();
        var win = window.open(url, '_blank');
        win.focus();
    },

    onDeleteClick111: function(button, e, eOpts) {
        button.up('form').getForm().findField('deletedfile2').setValue(true);
        button.up('container').hide();
        button.up('form').getForm().findField('file').focus();
    },

    onViewDocClick111: function(button, e, eOpts) {
        var url = "/getDocument?docId="+button.up('form').getForm().findField('attachedDocIdEmplTraining').getValue();
        var win = window.open(url, '_blank');
        win.focus();
    },

    onDeleteClick2: function(button, e, eOpts) {
        button.up('form').getForm().findField('deletedfile3').setValue(true);
        button.up('container').hide();
        button.up('form').getForm().findField('file').focus();
    },

    onViewDocClick2: function(button, e, eOpts) {
        if(button.up('form').getForm().findField('attachedDocIdPedyYes').getValue()!==-1)
            var url = "/getDocument?docId="+button.up('form').getForm().findField('attachedDocIdPedyNo').getValue();
        else if(button.up('form').getForm().findField('attachedDocIdPedyYes').getValue()!==-1)
            var url = "/getDocument?docId="+button.up('form').getForm().findField('attachedDocIdPedyYes').getValue();
        var win = window.open(url, '_blank');
        win.focus();
    },

    onDeleteClick11111: function(button, e, eOpts) {
        button.up('form').getForm().findField('deletedfile5').setValue(true);
        button.up('container').hide();
        button.up('form').getForm().findField('file').focus();
    },

    onViewDocClick11111: function(button, e, eOpts) {
        var url = "/getDocument?docId="+button.up('form').getForm().findField('attachedDocIdMilitary').getValue();
        var win = window.open(url, '_blank');
        win.focus();
    },

    onDeleteClick1111: function(button, e, eOpts) {
        button.up('form').getForm().findField('deletedfile4').setValue(true);
        button.up('container').hide();
        button.up('form').getForm().findField('file').focus();
    },

    onViewDocClick1111: function(button, e, eOpts) {
        var url = "/getDocument?docId="+button.up('form').getForm().findField('attachedDocIdMedassoc').getValue();
        var win = window.open(url, '_blank');
        win.focus();
    },

    onLoadAfterRender: function(component, container, pos, eOpts) {
        var store = Ext.StoreMgr.lookup( 'doctor.REGASSOC_GRID' );
        var store2 = Ext.StoreMgr.lookup( 'doctor.MEDICAL_ASSOC' );

        store.load({
            callback: function(records, operation, success){
                if(success){
                    for (i = 0; i < store.getCount(); i++) {
                        iv=store.getAt(i).get('abbr');
                        var pD = store.getAt(i).get('submitDate').replace(/[^0-9]+/g,' ').split(" ");
                        var dateto = pD[2]+"-"+pD[1]+"-"+pD[0];
                        var grp = new Ext.form.Checkbox({
                            boxLabel: store2.findRecord('abbr', store.getAt(i).get('medassocNotifiedId').toString()).get('spMedasDescription') + '  - Βεβαίωση με ημ/νια: ' + dateto,
                            inputValue: iv,
                            name: 'spDifferentCounty',
                            id: 'cb'+iv,
                            disabled: true,
                            checked: true,
                            validateOnBlur: false,
                            validateOnChange: false
                        });
                        component.add(grp);

                    }
                    if (store.getCount()===0){
                        var grp1 = new Ext.form.DisplayField({
                            hideLabel: true,
                            value: "Δεν έχετε προσθέσει βεβαιώσεις. Μπορείτε να το κάνετε από το μενού αριστερά."
                        });
                        component.add(grp1);
                    }
                    else{
                        if(component.up('form').getForm().findField('a_new_form').getValue()==="false"){
                            var spDifferentCounties = component.up('form').getRecord().get('spDifferentCounty').split(",");
                            for (i = 0; i < spDifferentCounties.length; i++) {
                                Ext.getCmp('cb'+spDifferentCounties[i]).setValue(true);
                            }
                        }
                    }
                }
            }

        });


    },

    onLoadAfterRenderSpec: function(component, container, pos, eOpts) {
        var store = Ext.StoreMgr.lookup( 'doctor.DIPLOMAS_GRID' );

        store.load({
            callback: function(records, operation, success){
                if(success){
                    for (i = 0; i < store.getCount(); i++) {
                        iv=store.getAt(i).get('abbr');
                        var grp = new Ext.form.Checkbox({
                            boxLabel: store.getAt(i).get('speciality'),
                            inputValue: iv,
                            name: 'speciality',
                            id: 'cb'+iv,
                            disabled: true,
                            checked: true,
                            validateOnBlur: false,
                            validateOnChange: false
                        });
                        component.add(grp);

                    }
                    if (store.getCount()===0){
                        var grp1 = new Ext.form.DisplayField({
                            hideLabel: true,
                            value: "Δεν έχετε προσθέσει τίτλους ιατρικής ειδικότητας. Μπορείτε να το κάνετε από το μενού αριστερά."
                        });
                        component.add(grp1);
                    }
                    else{
                        if(component.up('form').getForm().findField('a_new_form').getValue()==="false"){
                            var speciality = component.up('form').getRecord().get('speciality').split(",");
                            for (i = 0; i < speciality.length; i++) {
                                Ext.getCmp('cb'+speciality[i]).setValue(true);
                            }
                        }
                    }
                }
            }

        });


    },

    onDeleteClick1: function(button, e, eOpts) {
        button.up('form').getForm().findField('deletedfile').setValue(true);
        button.up('container').hide();
        button.up('form').getForm().findField('file').focus();
    },

    onViewDocClick1: function(button, e, eOpts) {
        var url = "/getDocument?docId="+button.up('form').getForm().findField('attachedDocId').getValue();
        var win = window.open(url, '_blank');
        win.focus();
    },

    onRegReqIe_save_submit_toolbarBeforeHide: function(component, eOpts) {
        component.getComponent('deletebutton').destroy();
        component.getComponent('savebutton').destroy();
        component.getComponent('submitbutton').destroy();
    },

    onWindowBeforeDestroy: function(component, eOpts) {
        var view=Ext.getCmp('doctormainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.doctor.RegRequestsPanel');
        center.add(viewsub);
    }

});