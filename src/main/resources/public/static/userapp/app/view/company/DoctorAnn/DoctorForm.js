/*
 * File: app/view/company/DoctorAnn/DoctorForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.DoctorAnn.DoctorForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companydoctoranndoctorform',

    requires: [
        'MyApp.view.company.DoctorAnn.DoctorFormViewModel',
        'MyApp.view.company.DoctorAnn.DoctorFormViewController',
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
        'Ext.form.field.Display',
        'Ext.button.Button',
        'Ext.form.field.File',
        'Ext.form.Label',
        'Ext.toolbar.Toolbar',
        'Ext.panel.Tool'
    ],

    controller: 'companydoctoranndoctorform',
    viewModel: {
        type: 'companydoctoranndoctorform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    id: 'doctorAnnWinId',
    overflowY: 'scroll',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Αναγγελία Ιατρού Εργασίας',
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
                                },
                                {
                                    xtype: 'fieldset',
                                    focusOnToFront: false,
                                    toFrontOnShow: false,
                                    title: 'Διεύθυνση Υποκαταστήματος',
                                    items: [
                                        {
                                            xtype: 'textareafield',
                                            anchor: '100%',
                                            disabled: true,
                                            fieldLabel: 'Οδός / Αριθμός',
                                            labelWidth: 180,
                                            name: 'brAddr',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            maxLength: 50
                                        },
                                        {
                                            xtype: 'combobox',
                                            anchor: '100%',
                                            disabled: true,
                                            fieldLabel: 'Περιφέρεια',
                                            labelWidth: 180,
                                            name: 'brAddrP',
                                            validateOnChange: false,
                                            validateOnBlur: false,
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
                                            name: 'brAddrPe',
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
                                            name: 'brAddrD',
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
                                            name: 'brAddrK',
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
                                            disabled: true,
                                            fieldLabel: 'Τ.Κ',
                                            labelWidth: 180,
                                            name: 'brAddrTk',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            maxLength: 5,
                                            minLength: 5,
                                            vtype: 'Number'
                                        }
                                    ]
                                }
                            ]
                        },{
                            xtype: 'fieldset',
                            title: 'Επιλογή Είδους Αναγγελίας προς ΕΞΥΠΠ ή Ανεξάρτητους Ιατρούς',
                            items: [
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Ιδιότητα Συνεργασίας',
                                    labelWidth: 180,
                                    name: 'cooperationTypeBasic',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    editable: false,
                                    emptyText: 'Επιλέξτε για ενεργοποίηση της περιοχής Στοιχεία Ιατρών Εργασίας.',
                                    autoLoadOnValue: true,
                                    displayField: 'name',
                                    valueField: 'value',
                                    bind: {
                                        store: '{isExyppSelected}'
                                    },
                                    listeners: {
                                        change: 'onCooperationBasicChange'
                                    }
                                },
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    hidden: true,
                                    fieldLabel: 'Επιλογή ΕΞΥΠΠ',
                                    labelWidth: 180,
                                    name: 'exyppBasic',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowOnlyWhitespace: false,
                                    editable: false,
                                    autoLoadOnValue: true,
                                    displayField: 'description',
                                    store: 'company.TechnicianAnn.EXYPP',
                                    valueField: 'abbr',
                                    listeners: {
                                        change: 'onExyppBasicChange'
                                    }
                                }
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            id: 'ieAnnIeEntries',
                            focusOnToFront: false,
                            padding: 10,
                            toFrontOnShow: false,
                            title: 'ΣΤΟΙΧΕΙΑ ΙΑΤΡΟΥ ΕΡΓΑΣΙΑΣ',
                            items: [
                                {
                                    xtype: 'button',
                                    border: 0,
                                    frame: false,
                                    id: 'newIeEntry',
                                    style: 'background-color:transparent;',
                                    iconCls: 'addone',
                                    tooltip: 'Προσθήκη',
                                    listeners: {
                                        click: 'onAddIeEntryClick'
                                    }
                                },
                                {
                                    xtype: 'button',
                                    border: 0,
                                    frame: false,
                                    id: 'delIeEntry',
                                    style: 'background-color:transparent;',
                                    iconCls: 'removeone',
                                    tooltip: 'Διαγραφή τελευταίου Τεχνικού',
                                    listeners: {
                                        click: 'onDelIeEntryClick'
                                    }
                                },
                                {
                                    xtype: 'hiddenfield',
                                    anchor: '100%',
                                    labelAlign: 'top',
                                    labelWidth: 180,
                                    name: 'ieEntriesnum',
                                    validateOnChange: false,
                                    value: 0
                                },
                                {
                                    xtype: 'hiddenfield',
                                    anchor: '100%',
                                    labelWidth: 180,
                                    msgTarget: 'under',
                                    name: 'docEntries',
                                    validateOnChange: false
                                }
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            calculateHours: function(categANum, categBNum, categCNum) {
                                var scrollpos = this.up('window').getScrollY();
                                //var yearTotalHours = parseFloat(categANum*0.8+categBNum*0.6+categCNum*0.4);
                                var oneDay = 24 * 60 * 60 * 1000;
                                var daysDiff = Math.round((this.up('form').getForm().findField('dateEnd').value - this.up('form').getForm().findField('dateStart').value) / oneDay);
                                var dateFrom = this.up('form').getForm().findField('dateStart').value;
                                var dateTo = this.up('form').getForm().findField('dateEnd').value;
                                //var annHours = parseFloat(yearTotalHours / 365) * (daysDiff+1);

                                var form = this;
                                var successCall = function (options, success, response) {
                                    if (Ext.JSON.decode(response.responseText).success) {


                                        if (form.up('form').getForm().findField('protNo').getValue() == null || form.up('form').getForm().findField('protNo').getValue() == "")
                                            form.up('form').getForm().findField('totalHours').setValue(Ext.JSON.decode(response.responseText).hours);
                                        var tms = parseInt(Ext.JSON.decode(response.responseText).hours);
                                        var hours1 = Math.floor(tms/60);
                                        var minutes1 = tms - (hours1*60);
                                        var textR = hours1.toString()+ " ώρες και " +minutes1.toString()+ " λεπτά.";
                                        if (form.up('form').getForm().findField('protNo').getValue() == null || form.up('form').getForm().findField('protNo').getValue() == "")
                                            form.up('form').getForm().findField('totalHoursText').setValue(textR);
                                        form.up('window').scrollTo(0, scrollpos);


                                    }
                                    else {
                                        Ext.Msg.alert('Αποτυχία Υπολογισμού ωρών', Ext.JSON.decode(response.responseText).error);
                                        if (parseInt(Ext.JSON.decode(response.responseText).code) === 0)
                                            form.up('window').destroy();
                                        else if (parseInt(Ext.JSON.decode(response.responseText).code) === 1) {
                                            if (categId === 1)
                                                form.up('form').getForm().findField('categANum').setValue(0);
                                            else if (categId === 2)
                                                form.up('form').getForm().findField('categBNum').setValue(0);
                                            else if (categId === 3)
                                                form.up('form').getForm().findField('categCNum').setValue(0);

                                        }


                                    }

                                };
                                Ext.Ajax.request({
                                    url: "/companyExtraInfo/calcIeHours",
                                    params: {
                                        sumDepA: categANum,
                                        sumDepB: categBNum,
                                        sumDepC: categCNum,
                                        numOfDays: daysDiff,
                                        dateFrom: dateFrom,
                                        dateTo: dateTo
                                    },
                                    method: "POST",
                                    callback: successCall
                                });
                            },
                            focusOnToFront: false,
                            padding: 10,
                            toFrontOnShow: false,
                            title: 'Δήλωση Εργαζομένων ανά δραστηριότητα',
                            items: [
                                {
                                    xtype: 'numberfield',
                                    anchor: '100%',
                                    fieldLabel: 'Αριθμός Εργαζομένων Δραστηριότητας Επικινδυνότητας Α\'',
                                    labelWidth: 350,
                                    name: 'categANum',
                                    validateOnChange: false,
                                    value: 0,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    hideTrigger: true,
                                    maxLength: 19,
                                    allowDecimals: false,
                                    allowExponential: false,
                                    minValue: 0,
                                    listeners: {
                                        change: 'onANumChange'
                                    }
                                },
                                {
                                    xtype: 'numberfield',
                                    anchor: '100%',
                                    fieldLabel: 'Αριθμός Εργαζομένων Δραστηριότητας Επικινδυνότητας Β\'',
                                    labelWidth: 350,
                                    name: 'categBNum',
                                    validateOnChange: false,
                                    value: 0,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    hideTrigger: true,
                                    maxLength: 19,
                                    allowDecimals: false,
                                    allowExponential: false,
                                    minValue: 0,
                                    listeners: {
                                        change: 'onBNumChange'
                                    }
                                },
                                {
                                    xtype: 'numberfield',
                                    anchor: '100%',
                                    fieldLabel: 'Αριθμός Εργαζομένων Δραστηριότητας Επικινδυνότητας Γ\'',
                                    labelWidth: 350,
                                    name: 'categCNum',
                                    validateOnChange: false,
                                    value: 0,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    hideTrigger: true,
                                    maxLength: 19,
                                    allowDecimals: false,
                                    allowExponential: false,
                                    minValue: 0,
                                    listeners: {
                                        change: 'onCNumChange'
                                    }
                                },
                                {
                                    xtype: 'displayfield',
                                    hidden: true,
                                    fieldLabel: 'Συνολικός χρόνος απαιτούμενων επισκέψεων',
                                    labelStyle: 'font-weight: bold',
                                    labelWidth: 350,
                                    name: 'totalHours',
                                    submitValue: true,
                                    value: 0
                                },
                                {
                                    xtype: 'displayfield',
                                    fieldLabel: 'Συνολικός χρόνος απαιτούμενων επισκέψεων',
                                    labelStyle: 'font-weight: bold',
                                    labelWidth: 350,
                                    name: 'totalHoursText',
                                    submitValue: false,
                                    value: 0
                                }
                            ]
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
                            xtype: 'button',
                            glyph: 'xf021@FontAwesome',
                            text: 'Ανανέωση Λίστας Ιατρών για Επισκέψεις',
                            listeners: {
                                click: 'onButtonClick'
                            }
                        },
                        {
                            xtype: 'fieldset',
                            id: 'diaryEntries',
                            title: 'Επισκέψεις Ιατρών',
                            items: [
                                {
                                    xtype: 'button',
                                    border: 0,
                                    frame: false,
                                    id: 'entryadd',
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
                                    id: 'entrydel',
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
                                    fieldLabel: 'Επισυναπτόμενο Αρχείο - Αντίγραφο Σύμβασης με Ιατρό',
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
                                    blankText: 'Πρέπει να επισυνάψετε την σύμβαση με τον ΙΕ',
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
                    name: 'compPtlBranchId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    width: 150,
                    labelWidth: 180,
                    name: 'doctorRegrequestId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    width: 150,
                    labelWidth: 180,
                    name: 'doctorRegrequestUserId',
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
                    validateOnChange: false
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
                    name: 'compIeAnnPrevId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    width: 150,
                    labelWidth: 180,
                    name: 'ieAnnStatus',
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
                var pD = date.split("-");
                return (pD[2]+"-"+pD[1]+"-"+pD[0]+"T00:00:00.000+0000");
            },
            dock: 'bottom',
            html: '<span style="color:#696969;font-size:11px;"><em>*Τα ανενεργά πεδία θα συμπληρωθούν αυτόματα<br>από το σύστημα κατά την υποβολή</em></span>',
            id: 'compdoctorann_save_submit_toolbar',
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
                            fn: 'onDelete_DOCTOR_ANN',
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
                            fn: 'onSave_DOCTOR_ANN',
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
                            fn: 'onSubmit_DOCTOR_ANN',
                            scope: 'controller'
                        }
                    }
                },
                {
                    xtype: 'button',
                    hidden: true,
                    itemId: 'changeProgrambutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 180,
                    glyph: 'xf1d8@FontAwesome',
                    text: 'Αλλαγή στο πρόγραμμα',
                    listeners: {
                        click: {
                            fn: 'onChangeProgram_DOCTOR_ANN',
                            scope: 'controller'
                        }
                    }
                },
                {
                    xtype: 'button',
                    hidden: true,
                    itemId: 'changeProgramSavebutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 250,
                    glyph: 'xf1d8@FontAwesome',
                    text: 'Αποθήκευση Αλλαγών Προγράμματος',
                    listeners: {
                        click: {
                            fn: 'onChangeProgramSave_DOCTOR_ANN',
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
                            fn: 'onChange_DOCTOR_ANN',
                            scope: 'controller'
                        }
                    }
                }
            ],
            listeners: {
                beforehide: 'onIeAnn_save_submit_toolbarBeforeHide'
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
        var getnext = combo.up('form').getForm().findField('brAddrPe');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeExpand1: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
        queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('brAddrP').getValue()+" ";
    },

    onAddrPeSelect1: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('brAddrD');
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
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('brAddrPe').getValue()+" ";
    },

    onAddrDSelect1: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('brAddrK');
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
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('brAddrD').getValue();
    },

    onAddrKDirtyChange1: function(field, isDirty, eOpts) {
        if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
            field.getStore().getProxy().url = "/TKalK/"+field.getValue();
            field.getStore().getProxy().getReader().setRootProperty("");
            field.store.load();
        }
    },

    onAddIeEntryClick: function (button, e, eOpts) {
        Ext.getCmp('ieAnnIeEntries').up('form').getForm().findField('ieEntriesnum').setValue(parseInt(Ext.getCmp('ieAnnIeEntries').items.length - 4));
        if(button.up('form').getForm().findField('cooperationTypeBasic').getValue()!==null) {
            if(button.up('form').getForm().findField('cooperationTypeBasic').getValue()===3){
                if(button.up('form').getForm().findField('exyppBasic').getValue()===null){
                    Ext.Msg.alert('Προσοχή!', 'Πρέπει πρώτα να επιλέξετε την ΕΞΥΠΠ της αναγγελίας.');
                }
                else{
                    var entrie = Ext.create('widget.companydoctorannieentry', {});
                    button.up('form').getForm().findField('ieEntriesnum').setValue(parseInt(button.up('form').getForm().findField('ieEntriesnum').getValue()) + 1);


                    entrie.down().items.get(2).readOnly = true;

                    entrie.down().items.get(0).setValue('1');
                    entrie.down().items.get(0).readOnly = true;
                    entrie.down().items.get(0).hidden = true;
                    var scrollpos = button.up('window').getScrollY();
                    button.up().add(entrie);
                    button.up('window').scrollTo(0, scrollpos);
                }

            }
            else {
                var entrie = Ext.create('widget.companydoctorannieentry', {});
                button.up('form').getForm().findField('ieEntriesnum').setValue(parseInt(button.up('form').getForm().findField('ieEntriesnum').getValue()) + 1);


                entrie.down().items.get(2).readOnly = true;

                var scrollpos = button.up('window').getScrollY();
                button.up().add(entrie);
                button.up('window').scrollTo(0, scrollpos);
            }
        }
        else{
            Ext.Msg.alert('Προσοχή!', 'Πρέπει πρώτα να επιλέξετε της γενική Ιδιότητα Συνεργασίας της αναγγελίας.');
        }

    },

    onDelIeEntryClick: function (button, e, eOpts) {
        Ext.getCmp('ieAnnIeEntries').up('form').getForm().findField('ieEntriesnum').setValue(parseInt(Ext.getCmp('ieAnnIeEntries').items.length - 4));

        if(button.up('form').getForm().findField('cooperationTypeBasic').getValue()===1) {
            if (parseInt(button.up('form').getForm().findField('ieEntriesnum').getValue()) !== 1) {
                button.up('form').getForm().findField('ieEntriesnum').setValue(parseInt(button.up('form').getForm().findField('ieEntriesnum').getValue()) - 1);
                var scrollpos = button.up('window').getScrollY();
                button.up().items.get(button.up().items.length - 1).destroy();
                button.up('window').scrollTo(0, scrollpos);
            }
        }
        else if(button.up('form').getForm().findField('cooperationTypeBasic').getValue()===3){
            if (parseInt(button.up('form').getForm().findField('ieEntriesnum').getValue()) > 2) {
                button.up('form').getForm().findField('ieEntriesnum').setValue(parseInt(button.up('form').getForm().findField('ieEntriesnum').getValue()) - 1);
                var scrollpos = button.up('window').getScrollY();
                button.up().items.get(button.up().items.length - 1).destroy();
                button.up('window').scrollTo(0, scrollpos);
            }
        }

    },

    onANumChange: function (field, newValue, oldValue, eOpts) {
        if (newValue !== null)
            field.up('component').calculateHours(
                parseInt(newValue),
                parseInt(field.up('form').getForm().findField('categBNum').getValue()),
                parseInt(field.up('form').getForm().findField('categCNum').getValue()), 1);
    },

    onBNumChange: function (field, newValue, oldValue, eOpts) {
        if (newValue !== null)
            field.up('component').calculateHours(
                parseInt(field.up('form').getForm().findField('categANum').getValue()),
                parseInt(newValue),
                parseInt(field.up('form').getForm().findField('categCNum').getValue()), 2);
    },

    onCNumChange: function (field, newValue, oldValue, eOpts) {
        if (newValue !== null)
            field.up('component').calculateHours(
                parseInt(field.up('form').getForm().findField('categANum').getValue()),
                parseInt(field.up('form').getForm().findField('categBNum').getValue()),
                parseInt(newValue), 3);
    },

    onButtonClick: function (button, e, eOpts) {
        var storeLocal = Ext.StoreMgr.lookup('company.DoctorAnn.LocalIeEntries');
        var ieS = Ext.getCmp('ieAnnIeEntries');
        var iesEntriesArr = [];
        var tot = Ext.getCmp('ieAnnIeEntries').items.length-4;
        if (parseInt(tot) > 0) {
            for (var j = 0; j < parseInt(tot); j++) {
                if(ieS.items.getAt(j+4).items.getAt(0).items.getAt(6)!==null){
                    if(ieS.items.getAt(j+4).items.getAt(0).items.getAt(0).getValue()!=='3') {
                        iesEntriesArr.push({
                            id: ieS.items.getAt(j + 4).items.getAt(0).items.getAt(6).getValue(),
                            name: ieS.items.getAt(j + 4).items.getAt(0).items.getAt(3).getValue()
                        });
                    }
                }
            }
            storeLocal.loadData(iesEntriesArr,false);
        }
    },

    onAddEntryClick: function(button, e, eOpts) {
        var entrie = Ext.create('widget.companydoctoranndiary', {});
        button.up('form').getForm().findField('diaryEntriesnum').setValue(parseInt(button.up('form').getForm().findField('diaryEntriesnum').getValue())+1);
        entrie.down().items.get(0).minValue= (button.up('form').getForm().findField('dateStart').value > new Date()) ? button.up('form').getForm().findField('dateStart').value : new Date();
        entrie.down().items.get(0).maxValue=button.up('form').getForm().findField('dateEnd').value;
        if(parseInt(button.up('form').getForm().findField('diaryEntriesnum').getValue())>1){
            entrie.down().items.get(0).hideLabel=true;
            entrie.down().items.get(1).hideLabel=true;
            entrie.down().items.get(2).hideLabel=true;
            entrie.down().items.get(3).hideLabel=true;
            entrie.down().items.get(4).hideLabel=true;
            entrie.down().items.get(5).hideLabel=true;
        }

        if(Ext.getCmp('entrydel').hidden===true) {
            //var tomorrow = new Date(new Date().getTime() + 24 * 60 * 60 * 1000);
            entrie.down().items.get(0).minValue= (button.up('form').getForm().findField('dateStart').getValue() > new Date()) ? button.up('form').getForm().findField('dateStart').getValue() : new Date();
            entrie.down().items.get(0).maxValue=button.up('form').getForm().findField('dateEnd').getValue();
            entrie.down().items.get(6).show();
        }
        var scrollpos = button.up('window').getScrollY();
        button.up().add(entrie);
        button.up('window').scrollTo(0, scrollpos);
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
        button.up('form').getForm().findField('deletedfile').setValue(true);
        button.up('container').hide();
        button.up('form').getForm().findField('file').focus();
    },

    onViewDocClick1: function(button, e, eOpts) {
        var url = "/getDocument?docId="+button.up('form').getForm().findField('attachedDocId').getValue();
        var win = window.open(url, '_blank');
        win.focus();
    },

    onIeAnn_save_submit_toolbarBeforeHide: function(component, eOpts) {
        component.getComponent('savebutton').destroy();
        component.getComponent('submitbutton').destroy();
    },

    onWindowBeforeDestroy: function(component, eOpts) {
        var ptlBranchId = component.down('form').getForm().findField('compPtlBranchId').getValue();
        var doctor = Ext.getCmp('companyDoctorAnn_Doctors');
        doctor.store.proxy.setUrl('/compIeAnn/search/findByPtlBranchId?branchId='+ptlBranchId);
        doctor.store.load( { callback : function(records, operation, success) {
            doctor.getView().refresh();
        }
        });

    },
    onCooperationBasicChange: function(field, newValue, oldValue, eOpts) {
        var scrollpos=Ext.getCmp('doctorAnnWinId').getScrollY();
        var itemsTotal = Ext.getCmp('ieAnnIeEntries').items.length;
        for(i=itemsTotal; i>4; i--){
            Ext.getCmp('ieAnnIeEntries').remove(i-1);
        }

        field.up('form').getForm().findField('ieEntriesnum').setValue(parseInt(0));
        if (parseInt(newValue)===3 && parseInt(oldValue)!==3){
            field.up('form').getForm().findField('exyppBasic').show();
            field.up('form').getForm().findField('exyppBasic').allowBlank=false;

            Ext.getCmp('doctorAnnWinId').scrollTo(0, scrollpos);
        }
        else{
            field.up('form').getForm().findField('exyppBasic').setValue();
            field.up('form').getForm().findField('exyppBasic').hide();
            field.up('form').getForm().findField('exyppBasic').allowBlank=true;
            var ieentrie = Ext.create('widget.companydoctorannieentry', {});
            field.up('form').getForm().findField('ieEntriesnum').setValue(parseInt(field.up('form').getForm().findField('ieEntriesnum').getValue()) + 1);
            Ext.getCmp('ieAnnIeEntries').add(ieentrie);

            Ext.getCmp('doctorAnnWinId').scrollTo(0, scrollpos);
        }

    },

    onExyppBasicChange: function(field, newValue, oldValue, eOpts) {
        if(field.getValue()!==null)
            if(field.getSelectedRecord().getData().rgEmpEmployerStatus===1){

                var scrollpos=Ext.getCmp('doctorAnnWinId').getScrollY();
                var itemsTotal = Ext.getCmp('ieAnnIeEntries').items.length;
                for(i=itemsTotal; i>4; i--){
                    Ext.getCmp('ieAnnIeEntries').remove(i-1);
                }
                field.up('form').getForm().findField('ieEntriesnum').setValue(parseInt(0));
                var ieentrie = Ext.create('widget.companydoctorannieentry', {});

                ieentrie.down().items.get(0).setValue();
                ieentrie.down().items.get(0).setValue('3');
                ieentrie.down().items.get(0).readOnly = true;
                ieentrie.down().items.get(1).setValue(newValue.toString());
                ieentrie.down().items.get(1).readOnly = true;

                Ext.getCmp('ieAnnIeEntries').add(ieentrie);
                var ieentrie2 = Ext.create('widget.companydoctorannieentry', {});

                ieentrie2.down().items.get(0).setValue('1');
                ieentrie2.down().items.get(0).readOnly = true;
                ieentrie2.down().items.get(0).hidden = true;
                Ext.getCmp('ieAnnIeEntries').add(ieentrie2);

                Ext.getCmp('doctorAnnWinId').scrollTo(0, scrollpos);

            }
            else{
                var scrollpos=field.up('window').getScrollY();
                newValue=oldValue;
                Ext.Msg.alert('Πρόβλημα ΕΞΥΠΠ', 'Η ΕΞΥΠΠ είναι ανενεργή. Επιλέξτε κάποια ενεργή');
                field.up('window').scrollTo(0, scrollpos);
            }
    }

});