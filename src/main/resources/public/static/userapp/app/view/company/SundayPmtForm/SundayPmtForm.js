/*
 * File: app/view/company/SundayPmtForm/SundayPmtForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.SundayPmtForm.SundayPmtForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companysundaypmtformsundaypmtform',

    requires: [
        'MyApp.view.company.SundayPmtForm.SundayPmtFormViewModel',
        'MyApp.view.company.SundayPmtForm.SundayPmtFormViewController',
        'MyApp.view.shared.PrintFormTool',
        'MyApp.view.shared.CloseFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.FieldSet',
        'Ext.form.field.Date',
        'Ext.form.field.Number',
        'Ext.form.field.ComboBox',
        'Ext.form.field.TextArea',
        'Ext.form.CheckboxGroup',
        'Ext.form.field.Checkbox',
        'Ext.button.Button',
        'Ext.toolbar.Toolbar',
        'Ext.panel.Tool'
    ],

    controller: 'companysundaypmtformsundaypmtform',
    viewModel: {
        type: 'companysundaypmtformsundaypmtform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    overflowY: 'scroll',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Αίτηση χορήγησης άδειας εργασίας κατά την Κυριακή & ημέρα αργίας',
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
            id: 'companysundaypmt',
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
                            displayField: 'description',
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
                            xtype: 'datefield',
                            anchor: '100%',
                            fieldLabel: 'Κατά την Κυριακή',
                            labelWidth: 180,
                            name: 'sundayDateView',
                            validateOnChange: false,
                            validateOnBlur: false,
                            //editable: false,
                            minValue: new Date(),
                            disabledDays: [1,2,3,4,5,6],
                            disabledDaysText: 'Επιλέξτε μία Κυριακή',
                            maxLength: 50,
                            format: 'd-m-Y',
                            validator: function(value){
                                if ((!value && !this.up('form').getForm().findField('holidayDate').getValue()) || (value && this.up('form').getForm().findField('holidayDate').getValue())) {
                                    return 'ΜΟΝΟ ένα από τα πεδία "Κατά την Κυριακή" ή "Κατά Ημέρα Αργίας" πρέπει να συμπληρωθεί.';
                                }
                                return true;
                            }
                        },
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            fieldLabel: 'Κατά Ημέρα Αργίας',
                            labelWidth: 180,
                            name: 'holidayDate',
                            validateOnChange: false,
                            validateOnBlur: false,
                            //editable: false,
                            minValue: new Date(),
                            disabledDates: ["^(?!25-03-2017|17-04-2017|01-05-2017|15-08-2017|28-10-2017|25-12-2017|26-12-2017|" +
                            "25-03-2018|09-04-2018|01-05-2018|15-08-2018|28-10-2018|25-12-2018|26-12-2018|" +
                            "25-03-2019|29-04-2019|01-05-2019|15-08-2019|28-10-2019|25-12-2019|26-12-2019|" +
                            "25-03-2020|20-04-2020|01-05-2020|15-08-2020|28-10-2020|25-12-2020|26-12-2020|" +
                            "25-03-2021|01-05-2021|03-05-2021|15-08-2021|28-10-2021|25-12-2021|26-12-2021|" +
                            "25-03-2022|25-04-2022|01-05-2022|15-08-2022|28-10-2022|25-12-2022|26-12-2022|" +
                            "25-03-2023|17-04-2023|01-05-2023|15-08-2023|28-10-2023|25-12-2023|26-12-2023|" +
                            "25-03-2024|01-05-2024|06-05-2024|15-08-2024|28-10-2024|25-12-2024|26-12-2024|" +
                            "25-03-2025|21-04-2025|01-05-2025|15-08-2025|28-10-2025|25-12-2025|26-12-2025|" +
                            "25-03-2026|13-04-2026|01-05-2026|15-08-2026|28-10-2026|25-12-2026|26-12-2026|" +
                            "25-03-2027|01-05-2027|03-05-2027|15-08-2027|28-10-2027|25-12-2027|26-12-2027|" +
                            "25-03-2028|17-04-2028|01-05-2028|15-08-2028|28-10-2028|25-12-2028|26-12-2028|" +
                            "25-03-2029|09-04-2029|01-05-2029|15-08-2029|28-10-2029|25-12-2029|26-12-2029|" +
                            "25-03-2030|29-04-2030|01-05-2030|15-08-2030|28-10-2030|25-12-2030|26-12-2030).*$"],
                            //disabledDays: [1,2,3,4,5,6],
                            maxLength: 50,
                            format: 'd-m-Y',
                            validator: function(value){
                                if ((!value && !this.up('form').getForm().findField('sundayDateView').getValue()) || (value && this.up('form').getForm().findField('sundayDateView').getValue())) {
                                    return 'ΜΟΝΟ ένα από τα πεδία "Κατά την Κυριακή" ή "Κατά Ημέρα Αργίας" πρέπει να συμπληρωθεί.';
                                }
                                return true;
                            }
                        },
                        /*{
                         xtype: 'combobox',
                         anchor: '100%',
                         fieldLabel: 'Κατά Ημέρα Αργίας',
                         labelWidth: 180,
                         name: 'holidayDate',
                         validateOnChange: false,
                         validateOnBlur: false,
                         editable: false,
                         displayField: 'name',
                         forceSelection: true,
                         valueField: 'abbr',
                         bind: {
                         store: '{HOLIDAY_DATES}'
                         },
                         validator: function(value){
                         if (!value && !this.up('form').getForm().findField('sundayDateView').getValue()) {
                         return 'Ένα από τα πεδία "Κατά την Κυριακή" ή "Κατά Ημέρα Αργίας" πρέπει να συμπληρωθεί.';
                         }
                         return true;
                         }
                         },*/
                        {
                            xtype: 'hiddenfield',
                            anchor: '100%',
                            fieldLabel: 'Ημερομηνία Πρόσληψης',
                            labelWidth: 180,
                            name: 'sundayDate',
                            validateOnChange: false
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            cls: 'x-item-disabled',
                            fieldLabel: 'Αρμόδιος Επιθεωρητής',
                            labelWidth: 180,
                            name: 'inspectorName',
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            hideTrigger: true
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
                                    maxLength: 100
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
                                            disabled: true,
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
                                    disabled: true,
                                    fieldLabel: 'Τηλέφωνο Εργοδότη/Επιχείρησης',
                                    labelWidth: 180,
                                    name: 'compPhone',
                                    hidden: true,
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    maxLength: 10,
                                    vtype: 'telNumber'
                                },
                                {
                                    xtype: 'checkboxgroup',
                                    msgTarget: 'under',
                                    validateOnChange: false,
                                    hidden: true,
                                    //allowBlank: false,
                                    //blankText: 'Πρέπει να δεχθείτε τους όρους',
                                    items: [
                                        {
                                            xtype: 'checkboxfield',
                                            fieldLabel: '',
                                            name: 'compStatement',
                                            validateOnChange: false,
                                            invalidText: 'Πρέπει να δεχθείτε τους όρους',
                                            validateOnBlur: false,
                                            boxLabel: 'Δήλωση αποδοχής ακρίβειας υποβαλλόμενων στοιχείων (παρ. 6 του άρθρου 22 του Ν. 599/1986)',
                                            inputValue: '1',
                                            uncheckedValue: '0'
                                        }
                                    ]
                                }
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            focusOnToFront: false,
                            padding: 10,
                            toFrontOnShow: false,
                            title: 'ΣΤΟΙΧΕΙΑ ΠΑΡΑΡΤΗΜΑΤΟΣ',
                            items: [
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

                                    },
                                    anchor: '100%',
                                    fieldLabel: 'Παράρτημα που αφορά η αίτηση',
                                    labelWidth: 220,
                                    name: 'compEbrBranchId',
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
                                        select: 'onBranch1Select1',
                                        beforequery: 'onBranchExpand'
                                    }
                                },
                                {
                                    xtype: 'hiddenfield',
                                    anchor: '100%',
                                    labelWidth: 180,
                                    name: 'compEbrBranch0Id',
                                    validateOnChange: false,
                                    value: 0
                                }
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            focusOnToFront: false,
                            toFrontOnShow: false,
                            title: 'ΤΟΠΟΣ ΠΑΡΟΧΗΣ ΕΡΓΑΣΙΑΣ - ΕΠΙΒΕΒΑΙΩΣΤΕ Ή ΤΡΟΠΟΠΟΙΗΣΤΕ ΤΟΝ ΑΚΡΙΒΗ ΤΟΠΟ ΠΑΡΟΧΗΣ ΕΡΓΑΣΙΑΣ',
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
                                    maxLength: 50
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
                            fieldLabel: 'Ακριβής Χώρος',
                            labelWidth: 180,
                            name: 'compAddressDesc',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 300
                        },
                        {
                            xtype: 'numberfield',
                            anchor: '100%',
                            fieldLabel: 'Πλήθος απασχολούμενων ανδρών',
                            labelWidth: 180,
                            name: 'menNum',
                            validateOnChange: false,
                            //value: 0,
                            validateOnBlur: false,
                            allowDecimals: false,
                            allowExponential: false,
                            decimalPrecision: 0,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            //maxValue: 10000,
                            minValue: 0
                        },
                        {
                            xtype: 'numberfield',
                            anchor: '100%',
                            fieldLabel: 'Πλήθος απασχολούμενων γυναικών',
                            labelWidth: 180,
                            name: 'womenNum',
                            validateOnChange: false,
                            //value: 0,
                            validateOnBlur: false,
                            allowDecimals: false,
                            allowExponential: false,
                            decimalPrecision: 0,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            //maxValue: 10000,
                            minValue: 0
                        },
                        {
                            xtype: 'displayfield',
                            flex: 1,
                            padding: '',
                            fieldLabel: '<img height="32px" src="/portal/static/userapp/resources/info.png"/>',
                            labelSeparator: '',
                            labelWidth: 42,
                            labelStyle: 'margin-top:15px;',
                            height: 145,
                            name: 'infotext',
                            value: 'Για την υποβολή της αίτησής σας, παρακαλούμε χρησιμοποιείστε την προτεινόμενη φόρμα. Άλλως, επισυνάπτετε έγγραφο με το ΟΝΟΜΑ, ΕΠΩΝΥΜΟ, ΑΜΚΑ, ΕΙΔΙΚΟΤΗΤΑ, ΑΚΡΙΒΗ ΗΜΕΡΟΜΗΝΙΑ ΚΑΙ ΩΡΕΣ ΑΠΑΣΧΟΛΗΣΗΣ, ΗΜΕΡΑ ΑΝΑΠΛΗΡΩΜΑΤΙΚΗΣ ΑΝΑΠΑΥΣΗΣ ΑΝΑ ΕΡΓΑΖΟΜΕΝΟ, καθώς και ΑΙΤΙΟΛΟΓΙΑ ΓΙΑ ΤΗΝ ΑΝΑΓΚΗ ΕΡΓΑΣΙΑΣ ΚΑΤΑ ΤΗΝ ΚΥΡΙΑΚΗ / ΑΡΓΙΑ. Επίσης, η αίτηση να φέρει την σφραγίδα της επιχείρησης με τα στοιχεία και την υπογραφή του υπευθύνου. Ο Αιτών έχει την ευθύνη τα επισυναπτόμενα κείμενα να είναι ευανάγνωστα. Αίτηση που δεν πληροί τις ως άνω συνθήκες, απορρίπτεται. <br/>'
                            + 'Σημειώνεται ότι η προθεσμία υποβολής της εν λόγω αίτησης ορίζεται ως η αμέσως προηγούμενη εργάσιμη ημέρα της αργίας ή της Κυριακής που αφορά την αίτηση και ώρα 13:00 (αρ. 11 της ΥΑ οικ. 34331/Δ9.8920/26.07.2016).',
                            listeners: {
                                beforerender: 'onInfoTextBeforeRender'
                            }
                        },
                        {
                            xtype: 'label',
                            baseCls: 'x-form-item-label',
                            height: 24,
                            padding: '5 0 0 0',
                            text: 'Προτεινόμενη φόρμα: ',
                            listeners: {
                                beforerender: 'onSuggestedFormLabelBeforeRender'
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
                            tooltip: 'Προβολή προτεινόμενης φόρμας',
                            listeners: {
                                click: 'onViewSuggestedFormClick',
                                beforerender: 'onSuggestedFormBeforeRender'
                            }
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
            id: 'compsundaypmt_save_submit_toolbar',
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
                            fn: 'onDelete_COMPANY_SUNDAY_PMT',
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
                            fn: 'onSave_COMPANY_SUNDAY_PMT',
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
                            fn: 'onSubmit_COMPANY_SUNDAY_PMT',
                            scope: 'controller'
                        }
                    }
                }
            ],
            listeners: {
                beforehide: 'onCompSundayPmt_save_submit_toolbarBeforeHide'
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

    onAddrPSelect21: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('compAddrPe');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeExpand21: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
        queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('compAddrP').getValue()+" ";
    },

    onAddrPeSelect21: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('compAddrD');
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
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('compAddrPe').getValue()+" ";
    },

    onAddrDSelect21: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('compAddrK');
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
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('compAddrD').getValue();
    },

    onAddrKDirtyChange21: function(field, isDirty, eOpts) {
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

    onViewSuggestedFormClick: function(button, e, eOpts) {
        /*var url = "/getDocument?docId=34354";
         var win = window.open(url, '_blank');
         win.focus();*/
        var blob = null;
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "/portal/static/userapp/resources/documents/EMPLOYEE_TABLE_SUNDAY.doc");
        xhr.responseType = "blob";//force the HTTP response, response-type header to be blob
        xhr.onload = function()
        {
            blob = xhr.response;//xhr.response is now a blob object
            var csvData = new Blob([blob], {type: 'text/csv;charset=utf-8;'});
            var link = document.createElement('a');
            link.href = window.URL.createObjectURL(csvData);
            link.setAttribute('download', 'ΠΙΝΑΚΑΣ_ΕΡΓΑΖΟΜΕΝΩΝ_ΚΥΡΙΑΚΗΣ_ΑΡΓΙΑΣ.doc');
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
        }
        xhr.send();
    },

    onWindowBeforeDestroy: function(component, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.company.SundayPmtPanel');
        center.add(viewsub);
    },

    onCompSundayPmt_save_submit_toolbarBeforeHide: function(component, eOpts) {
        component.getComponent('deletebutton').destroy();
        component.getComponent('savebutton').destroy();
        component.getComponent('submitbutton').destroy();
    },

    onInfoTextBeforeRender: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue() !== 'true') {
            field.hide();
        }
    },

    onSuggestedFormBeforeRender: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue() !== 'true') {
            field.hide();
        }
    },

    onSuggestedFormLabelBeforeRender: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue() !== 'true') {
            field.hide();
        }
    },

    onBranch1Select1: function(combo, records, eOpts) {
        //var store = Ext.StoreMgr.lookup( 'AFM_COMP' );
        //var centralId = store.findRecord('branchId',0).get('abbr');
        //combo.up('form').getForm().findField('branch0').setValue(centralId);
        var formToFill = combo.up('form').getForm();
        combo.up('form').getForm().findField('compEbrBranch0Id').setValue(0);

        //DISABLE BELOW PART TO STOP AUTOFILL
        value = combo.getValue();
        selectedrec = combo.findRecordByValue(value);

        formToFill.findField('inspAddrK').suspendEvent('dirtyChange');
        formToFill.findField('inspAddrPe').suspendEvent('dirtyChange');
        formToFill.findField('inspAddrD').suspendEvent('dirtyChange');

        combo.autofillCompFields(formToFill, selectedrec);

        formToFill.findField('inspAddrK').resumeEvent('dirtyChange');
        formToFill.findField('inspAddrPe').resumeEvent('dirtyChange');
        formToFill.findField('inspAddrD').resumeEvent('dirtyChange');
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

});