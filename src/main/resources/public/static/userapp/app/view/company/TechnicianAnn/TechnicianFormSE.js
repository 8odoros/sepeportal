Ext.define('MyApp.view.company.TechnicianAnn.TechnicianFormSE', {
    extend: 'Ext.window.Window',
    alias: 'widget.companytechniciananntechnicianformse',

    requires: [
        'MyApp.view.company.TechnicianAnn.TechnicianFormSEViewModel',
        'MyApp.view.company.TechnicianAnn.TechnicianFormSEViewController',
        'MyApp.view.shared.PrintFormTool',
        'MyApp.view.shared.CloseFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.FieldSet',
        'Ext.form.field.Date',
        'Ext.form.field.Number',
        'Ext.form.field.ComboBox',
        'Ext.form.field.TextArea',
        'Ext.button.Button',
        'Ext.form.field.Display',
        'Ext.form.field.File',
        'Ext.form.Label',
        'Ext.toolbar.Toolbar',
        'Ext.panel.Tool'
    ],

    controller: 'companytechniciananntechnicianformse',
    viewModel: {
        type: 'companytechniciananntechnicianformse'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    id: 'techAnnWinSEId',
    overflowY: 'scroll',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Αναγγελία Τεχνικού Ασφάλειας Δευτεροβάθμιας Εκπαίδευσης',
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
                            name: 'sepeDept',
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
                            title: 'ΣΤΟΙΧΕΙΑ ΕΡΓΟΔΟΤΗ / ΕΠΙΧΕΙΡΗΣΗΣ',
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
                                            //allowBlank: false,
                                            //allowOnlyWhitespace: false,
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
                                            //allowBlank: false,
                                            //allowOnlyWhitespace: false,
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
                                            //allowBlank: false,
                                            //allowOnlyWhitespace: false,
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
                                    validateOnBlur: false
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
                                            maxLength: 1000
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
                                    //readOnly: true,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 100,
                                    format: 'd-m-Y',
                                    submitFormat: 'Y-m-d\\T00:00:00.000+0000',
                                    minValue: Ext.Date.add(new Date(
                                        new Date().getFullYear(),
                                        new Date().getMonth() - 1,
                                        new Date().getDate()
                                    ), Ext.Date.DAY,1),
                                    minText: 'Η ημερομηνία έναρξης πρέπει να είναι το πολύ 1 μήνα πριν από την τρέχουσα ημερομηνία',
                                    maxValue: Ext.Date.add(new Date(
                                        new Date().getFullYear(),
                                        new Date().getMonth() + 1,
                                        new Date().getDate()
                                    ), Ext.Date.DAY,-1),
                                    maxText: 'Η ημερομηνία έναρξης πρέπει να είναι το πολύ 1 μήνα μετά από την τρέχουσα ημερομηνία',
                                    listeners:
                                    {
                                        change: function () {
                                            Ext.getCmp('categs').checkDates();
                                            try
                                            {
                                                this.up('form').getForm().findField('dateEnd').setMinValue(Ext.Date.add(this.up('form').getForm().findField('dateStart').getValue(), Ext.Date.DAY,1));
                                                this.up('form').getForm().findField('dateEnd').setMaxValue(Ext.Date.add(Ext.Date.add(this.up('form').getForm().findField('dateStart').getValue(), Ext.Date.YEAR,1), Ext.Date.DAY, -1));
                                                this.up('form').getForm().findField('dateEnd').enable();
                                            }
                                            catch (err){}
                                            for (var i = 0; i < this.up('form').getForm().findField('diaryEntriesnum').getValue(); i++)
                                            {
                                                try
                                                {
                                                    (this.up('form').getForm().findField('dateStart').value > new Date()) ? Ext.getCmp('tadiaryEntries').items.get(5+i).items.get(0).setMinValue(this.up('form').getForm().findField('dateStart').value) : Ext.getCmp('tadiaryEntries').items.get(5+i).items.get(0).setMinValue(new Date());
                                                }
                                                catch (err){}
                                            }
                                        }
                                    }
                                },
                                {
                                    xtype: 'datefield',
                                    anchor: '100%',
                                    fieldLabel: 'Έως',
                                    disabled: true,
                                    msgTarget: 'under',
                                    name: 'dateEnd',
                                    validateOnChange: false,
                                    //readOnly: true,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 100,
                                    format: 'd-m-Y',
                                    submitFormat: 'Y-m-d\\T00:00:00.000+0000',
                                    minText: 'Η ημερομηία λήξης πρέπει να είναι μεταγενέστερη της ημερομηνίας έναρξης',
                                    maxText: 'Η ημερομηία λήξης πρέπει να είναι το πολύ 1 χρόνο μετά την ημερομηνία έναρξης',
                                    listeners:
                                    {
                                        change: function () {
                                            Ext.getCmp('categs').checkDates();
                                            for (var i = 0; i < this.up('form').getForm().findField('diaryEntriesnum').getValue(); i++)
                                            {
                                                try
                                                {
                                                    Ext.getCmp('tadiaryEntries').items.get(5 + i).items.get(0).setMaxValue(this.up('form').getForm().findField('dateEnd').value);
                                                }
                                                catch (err){}
                                            }
                                        }
                                    }
                                },
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            title: 'Κλάδος Οικονομικής Δραστηριότητας',
                            items: [
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    fieldLabel: 'Κλάδος Οικονομικής Δραστηριότητας',
                                    labelWidth: 180,
                                    //labelWidth: 180,
                                    name: 'branchSector',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    editable: false,
                                    emptyText: 'Επιλέξτε τον κλάδο οικονομικής δραστηριότητας.',
                                    autoLoadOnValue: true,
                                    displayField: 'description',
                                    valueField: 'abbr',
                                    bind: {
                                        store: '{BRANCH_SECTOR}'
                                    },
                                    listeners: {
                                        select: 'onBranchSectorSelect'
                                    }
                                },
                                {
                                    xtype: 'hiddenfield',
                                    anchor: '100%',
                                    fieldLabel: 'branchSectorId',
                                    labelWidth: 180,
                                    name: 'branchSectorId',
                                    validateOnChange: false
                                },
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            id: 'taAnnTaEntries',
                            title: 'ΣΤΟΙΧΕΙΑ ΤΕΧΝΙΚΟΥ ΑΣΦΑΛΕΙΑΣ',
                            items: [
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Όνομα',
                                    labelWidth: 180,
                                    name: 'taFirstName',
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
                                    name: 'taLastName',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 50
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Πατρώνυμο',
                                    labelWidth: 180,
                                    name: 'taFatherName',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 50
                                },
                                {
                                    xtype: 'textfield',
                                    fieldLabel: 'Α.Φ.Μ',
                                    anchor: '100%',
                                    labelWidth: 180,
                                    name: 'taAfm',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 9,
                                    minLength: 9,
                                    enforceMaxLength: true,
                                    hideTrigger: true,
                                    keyNavEnabled: false,
                                    mouseWheelEnabled: false,
                                    maskRe:/[0-9.]/,
                                    vtype: 'Number'
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Τηλέφωνο',
                                    labelWidth: 180,
                                    name: 'taPhone',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 10,
                                    vtype: 'telNumber'
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'E-mail',
                                    labelWidth: 180,
                                    name: 'taEmail',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    vtype: 'email',
                                    maxLength: 50
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
                                            name: 'taAddr',
                                            validateOnChange: false,
                                            validateOnBlur: false,
                                            allowBlank: false,
                                            allowOnlyWhitespace: false,
                                            maxLength: 250
                                        },
                                        {
                                            xtype: 'combobox',
                                            anchor: '100%',
                                            fieldLabel: 'Περιφέρεια',
                                            labelWidth: 180,
                                            name: 'taAddrP',
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
                                                select: 'onAddrPSelect31'
                                            }
                                        },
                                        {
                                            xtype: 'combobox',
                                            anchor: '100%',
                                            session: false,
                                            disabled: true,
                                            fieldLabel: 'Νομός',
                                            labelWidth: 180,
                                            name: 'taAddrPe',
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
                                                beforequery: 'onAddrPeExpand31',
                                                select: 'onAddrPeSelect31',
                                                dirtychange: {
                                                    fn: 'onAddrPeDirtyChange31',
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
                                            name: 'taAddrD',
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
                                                beforequery: 'onAddrDExpand31',
                                                select: 'onAddrDSelect31',
                                                dirtychange: {
                                                    fn: 'onAddrDDirtyChange31',
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
                                            name: 'taAddrK',
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
                                                beforequery: 'onAddrKExpand31',
                                                dirtychange: {
                                                    fn: 'onAddrKDirtyChange31',
                                                    single: true
                                                }
                                            }
                                        },
                                        {
                                            xtype: 'textfield',
                                            anchor: '100%',
                                            fieldLabel: 'Τ.Κ',
                                            labelWidth: 180,
                                            name: 'taAddrTk',
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
                                    fieldLabel: 'Τίτλος Εκπαίδευσης',
                                    labelWidth: 180,
                                    name: 'eduTitle',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 100
                                },
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: 'Αριθμός Βεβαίωσης Εκπαίδευσης',
                                    labelWidth: 180,
                                    name: 'eduNum',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    maxLength: 100
                                },
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            id: 'categs',
                            calculateHours: function (categANum, categBNum, categCNum, categId) {

                                if (this.up('form').getForm().findField('dateStart').getValue() != null && this.up('form').getForm().findField('dateEnd').getValue() != null)
                                {
                                    Ext.getCmp('categs').calculateHoursWithDates(
                                        0,
                                        parseInt(this.up('form').getForm().findField('categBNum').getValue()),
                                        parseInt(this.up('form').getForm().findField('categCNum').getValue()), 1);
                                    return;
                                }
                                
                                try
                                {
                                    var scrollpos = this.up('window').getScrollY();
                                }
                                catch (ex){}

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
                                        try
                                        {
                                            form.up('window').scrollTo(0, scrollpos);
                                        }
                                        catch (ex){}


                                    }
                                    else {
                                        Ext.Msg.alert('Αποτυχία Υπολογισμού ωρών', Ext.JSON.decode(response.responseText).error);
                                        if (parseInt(Ext.JSON.decode(response.responseText).code) === 0)
                                            form.up('window').destroy();
                                        else if (parseInt(Ext.JSON.decode(response.responseText).code) === 1) {
                                            if (categId === 2)
                                                form.up('form').getForm().findField('categBNum').setValue(0);
                                            else if (categId === 3)
                                                form.up('form').getForm().findField('categCNum').setValue(0);

                                        }


                                    }

                                };
                                Ext.Ajax.request({
                                    url: "/companyExtraInfo/calcMinTaHours",
                                    params: {
                                        sumDepA: categANum,
                                        sumDepB: categBNum,
                                        sumDepC: categCNum
                                    },
                                    method: "POST",
                                    callback: successCall
                                });


                            },
                            calculateHoursWithDates: function (categANum, categBNum, categCNum, categId) {
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
                                        var hours1 = Math.floor(tms / 60);
                                        var minutes1 = tms - (hours1 * 60);
                                        var textR = hours1.toString() + " ώρες και " + minutes1.toString() + " λεπτά.";
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
                                    url: "/companyExtraInfo/calcTaHours",
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
                            checkDates: function () {
                                if (this.up('form').getForm().findField('dateStart').getValue() != null && this.up('form').getForm().findField('dateEnd').getValue() != null)
                                {
                                    Ext.getCmp('categs').calculateHoursWithDates(
                                        0,
                                        parseInt(this.up('form').getForm().findField('categBNum').getValue()),
                                        parseInt(this.up('form').getForm().findField('categCNum').getValue()), 1);
                                }
                            },
                            focusOnToFront: false,
                            padding: 10,
                            toFrontOnShow: false,
                            title: 'Δήλωση αριθμού εργαζομένων Υποκαταστήματος ανά κατηγορία Επικινδυνότητας',
                            items: [
                                {
                                    xtype: 'numberfield',
                                    anchor: '100%',
                                    fieldLabel: 'Μέγιστος Αριθμός Εργαζομένων Σε Δραστηριότητα Επικινδυνότητας Α\'',
                                    labelWidth: 350,
                                    name: 'compCategANum',
                                    validateOnChange: false,
                                    disabled: true,
                                    hidden: true,
                                    validateOnBlur: false,
                                    readOnly: true,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    hideTrigger: true,
                                    maxLength: 19,
                                    allowDecimals: false,
                                    allowExponential: false,
                                    minValue: 0
                                },
                                {
                                    xtype: 'numberfield',
                                    anchor: '100%',
                                    fieldLabel: 'Αριθμός Εργαζομένων Δραστηριότητας Επικινδυνότητας Α\'',
                                    labelWidth: 350,
                                    name: 'categANum',
                                    validateOnChange: false,
                                    value: 0,
                                    hidden: true,
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
                                    fieldLabel: 'Μέγιστος Αριθμός Εργαζομένων Σε Δραστηριότητα Επικινδυνότητας Β\'',
                                    labelWidth: 350,
                                    name: 'compCategBNum',
                                    validateOnChange: false,
                                    disabled: true,
                                    validateOnBlur: false,
                                    readOnly: true,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    hideTrigger: true,
                                    maxLength: 19,
                                    allowDecimals: false,
                                    allowExponential: false,
                                    minValue: 0
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
                                    fieldLabel: 'Μέγιστος Αριθμός Εργαζομένων Σε Δραστηριότητα Επικινδυνότητας Γ\'',
                                    labelWidth: 350,
                                    name: 'compCategCNum',
                                    validateOnChange: false,
                                    disabled: true,
                                    validateOnBlur: false,
                                    readOnly: true,
                                    allowBlank: false,
                                    allowOnlyWhitespace: false,
                                    hideTrigger: true,
                                    maxLength: 19,
                                    allowDecimals: false,
                                    allowExponential: false,
                                    minValue: 0
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
                                    fieldLabel: 'Ελάχιστες προβλεπόμενες ετήσιες ώρες απασχόλησης του ΤΑ',
                                    labelStyle: 'font-weight: bold',
                                    labelWidth: 350,
                                    name: 'totalHours',
                                    submitValue: true,
                                    value: 0
                                },
                                {
                                    xtype: 'displayfield',
                                    fieldLabel: 'Ελάχιστες προβλεπόμενες ετήσιες ώρες απασχόλησης του ΤΑ',
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
                            id: 'tadiaryEntries',
                            title: 'Επισκέψεις Τεχνικού',
                            items: [
                                {
                                    xtype: 'label',
                                    id: 'taentryadd',
                                    margin: '0 4 3 4',
                                    style: 'cursor: pointer;',
                                    html: '<img height="17px" src="/portal/static/userapp/resources/addone.png" title="Προσθήκη επίσκεψης"/>',
                                    listeners: {
                                        render: function(c){
                                            c.getEl().on('click', function(){
                                                this.up('window').onAddEntryClick(c);
                                            }, c);
                                        }
                                    }
                                },
                                {
                                    xtype: 'label',
                                    id: 'taentrydel',
                                    margin: '0 4 3 4',
                                    style: 'cursor: pointer;',
                                    html: '<img height="17px" src="/portal/static/userapp/resources/removeone.png" title="Διαγραφή τελευταίας επίσκεψης"/>',
                                    listeners: {
                                        render: function(c){
                                            c.getEl().on('click', function(){
                                                this.up('window').onDelEntryClick(c);
                                            }, c);
                                        }
                                    }
                                },
                                {
                                    xtype: 'label',
                                    id: 'taRecurrentEntryadd',
                                    margin: '0 4 3 4',
                                    style: 'cursor: pointer;',
                                    html: '<img height="17px" src="/portal/static/userapp/resources/recurring.png" title="Επαναληπτικές επισκέψεις τεχνικών"/>',
                                    listeners: {
                                        render: function(c){
                                            c.getEl().on('click', function(){
                                                this.up('window').onAddRecurrentEntryClick(c);
                                            }, c);
                                        }
                                    }
                                },
                                /*{
                                    xtype: 'button',
                                    border: 0,
                                    frame: false,
                                    id: 'taentryadd',
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
                                    id: 'taentrydel',
                                    style: 'background-color:transparent;',
                                    iconCls: 'removeone',
                                    tooltip: 'Διαγραφή τελευταίας επίσκεψης',
                                    listeners: {
                                        click: 'onDelEntryClick'
                                    }
                                },
                                {
                                    xtype: 'button',
                                    border: 0,
                                    frame: false,
                                    id: 'taRecurrentEntryadd',
                                    style: 'background-color:transparent;',
                                    iconCls: 'recurring',
                                    tooltip: 'Επαναληπτικές επισκέψεις τεχνικών',
                                    listeners: {
                                        click: 'onAddRecurrentEntryClick'
                                    }
                                },*/
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
                                },
                            ]
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            fieldLabel: 'Υπεύθυνη Δήλωση',
                            labelWidth: 120,
                            labelAlign: 'top',
                            name: 'affirmation',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 2000,
                            height: 130,
                            readOnly: true,
                            value: 'Ανατίθενται τα καθήκοντα Τεχνικού Ασφάλειας στον αναφερόμενο εργαζόμενο, πλήρους ωραρίου απασχόλησης στην επιχείρησης, επιπέδου εκπαίδευσης ΔΕ, βάσει των προβλεπόμενων στο άρθρο 12, παράγραφοι (β) και (γ) του Ν.3850/2010, και με βάση τα επισυναπτόμενα έγγραφα (Ενυπόγραφη αποδοχή καθηκόντων ΤΑ, Απολυτήριο Τεχνικού Λυκείου, Μέσης Τεχνικής Σχολής ή Άδεια Άσκησης Εμπειροτεχνίτη, Βεβαίωση Επιμόρφωσης ΤΑ, Βεβαίωση / αποδεικτικό οκταετούς (τουλάχιστον) προϋπηρεσίας, Σύμβαση πρόσληψης εργαζόμενου, Αντίγραφο Πίνακα Προσωπικού της επιχείρησης).',
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
                                    fieldLabel: 'Επισυναπτόμενο Αρχείο',
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
                                    blankText: 'Πρέπει να επισυνάψετε το αρχείο με τα απαραίτητα έγγαφα',
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
                        },
                        {
                            xtype: 'displayfield',
                            name: 'docDetails',
                            labelWidth: '100%',
                            labelSeparator : "",
                            fieldLabel: '<i>Το αρχείο πρέπει να περιέχει:</i><ul><li>Απολυτήριο Τεχνικού Λυκείου, Μέσης Τεχνικής Σχολής ή Άδεια Άσκησης Εμπειροτεχνίτη<li>Βεβαίωση Επιμόρφωσης ΤΑ<li>Βεβαίωση / αποδεικτικό οκταετούς (τουλάχιστον) προϋπηρεσίας<li>Σύμβαση πρόσληψης εργαζόμενου<li>Αντίγραφο Πίνακα Προσωπικού της επιχείρησης<li>Ενυπόγραφη Ανάθεση Καθηκόντων ΤΑ εκ μέρους του εργοδότη και ενυπόγραφη Αποδοχή Καθηκόντων ΤΑ εκ μέρους του εργαζόμενου ΤΑ</ul>',
                            allowBlank: false
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
                    name: 'userId',
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
            getCurrentTimestamp: function (part) {
                var currentDate = new Date();
                if (part === 1) {
                    var month = currentDate.getMonth() + 1;
                    if (month.toString().length < 2) {
                        month = "0" + month;
                    }
                    var date = currentDate.getDate();
                    if (date.toString().length < 2) {
                        date = "0" + date;
                    }
                    var hours = currentDate.getHours();
                    if (hours.toString().length < 2) {
                        hours = "0" + hours;
                    }
                    var minutes = currentDate.getMinutes();
                    if (minutes.toString().length < 2) {
                        minutes = "0" + minutes;
                    }
                    var seconds = currentDate.getSeconds();
                    if (seconds.toString().length < 2) {
                        seconds = "0" + seconds;
                    }
                    return currentDate.getFullYear() + "-" + month + "-" + date +
                        "T" + hours + ":" + minutes + ":" + seconds + "." + "000" + "+0000";

                }
                else if (part === 2) {
                    var hours = currentDate.getHours();
                    if (hours.toString().length < 2) {
                        hours = "0" + hours;
                    }
                    var minutes = currentDate.getMinutes();
                    if (minutes.toString().length < 2) {
                        minutes = "0" + minutes;
                    }
                    return hours + ":" + minutes;
                }
                else if (part === 3) {
                    return currentDate.getFullYear();
                }


            },
            dateToTimestamp: function (date) {
                var pD = date.split("-");
                return (pD[2] + "-" + pD[1] + "-" + pD[0] + "T00:00:00.000+0000");
            },
            dock: 'bottom',
            html: '<span style="color:#696969;font-size:11px;"><em>*Τα ανενεργά πεδία θα συμπληρωθούν αυτόματα<br>από το σύστημα κατά την υποβολή</em></span>',
            id: 'comptechnicianann_save_submit_toolbar1',
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
                            fn: 'onDelete_TECHNICIAN_ANN_SE',
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
                            fn: 'onSave_TECHNICIAN_ANN_SE',
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
                            fn: 'onSubmit_TECHNICIAN_ANN_SE',
                            scope: 'controller'
                        }
                    }
                },
            ],
            listeners: {
                beforehide: 'onTaAnn_save_submit_toolbarBeforeHide'
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

    onWindowBeforeDestroy: function(component, eOpts) {
        var ptlBranchId = component.down('form').getForm().findField('compPtlBranchId').getValue();
        var tech = Ext.getCmp('companyTechnicianSeAnn_Technicians');
        tech.store.proxy.setUrl('/compTaAnnSe/search/findByPtlBranchId?branchId='+ptlBranchId);
        tech.store.load( { callback : function(records, operation, success) {
            tech.getView().refresh();
        }
        });

    },

    onAddrPSelect: function (combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('compAddrPe');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeExpand: function (queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
        queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
        queryPlan.query = queryPlan.combo.up('form').getForm().findField('compAddrP').getValue() + " ";
    },

    onAddrPeSelect: function (combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('compAddrD');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeDirtyChange: function (field, isDirty, eOpts) {
        //if (field.up('form').getForm().findField('a_new_form').getValue() !== "true") {
        field.getStore().getProxy().url = "/TKalPe/" + field.getValue() + " ";
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        //}
    },

    onAddrDExpand: function (queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalD");
        queryPlan.combo.getStore().getProxy().url = "/TKalD/search/findByEnotCode";
        queryPlan.query = queryPlan.combo.up('form').getForm().findField('compAddrPe').getValue() + " ";
    },

    onAddrDSelect: function (combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('compAddrK');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrDDirtyChange: function (field, isDirty, eOpts) {
        //if (field.up('form').getForm().findField('a_new_form').getValue() !== "true") {
        field.getStore().getProxy().url = "/TKalD/" + field.getValue() + " ";
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        //}
    },

    onAddrKExpand: function (queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalK");
        queryPlan.combo.getStore().getProxy().url = "/TKalK/search/findByDimosCode";
        queryPlan.query = queryPlan.combo.up('form').getForm().findField('compAddrD').getValue();
    },

    onAddrKDirtyChange: function (field, isDirty, eOpts) {
        //if (field.up('form').getForm().findField('a_new_form').getValue() !== "true") {
        field.getStore().getProxy().url = "/TKalK/" + field.getValue();
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load({
            callback: function (records, operation, success) {
                field.up('form').getForm().findField('compAddrP').setValue(records[0].get('pCode'));
                field.up('form').getForm().findField('compAddrPe').setValue(records[0].get('peCode'));
                field.up('form').getForm().findField('compAddrD').setValue(records[0].get('dCode'));
            }
        });
        //}
    },

    onAddrPSelect1: function (combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('brAddrPe');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeExpand1: function (queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
        queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
        queryPlan.query = queryPlan.combo.up('form').getForm().findField('brAddrP').getValue() + " ";
    },

    onAddrPeSelect1: function (combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('brAddrD');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeDirtyChange1: function (field, isDirty, eOpts) {
        //if (field.up('form').getForm().findField('a_new_form').getValue() !== "true") {
        field.getStore().getProxy().url = "/TKalPe/" + field.getValue() + " ";
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        //}
    },

    onAddrDExpand1: function (queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalD");
        queryPlan.combo.getStore().getProxy().url = "/TKalD/search/findByEnotCode";
        queryPlan.query = queryPlan.combo.up('form').getForm().findField('brAddrPe').getValue() + " ";
    },

    onAddrDSelect1: function (combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('brAddrK');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrDDirtyChange1: function (field, isDirty, eOpts) {
        //if (field.up('form').getForm().findField('a_new_form').getValue() !== "true") {
        field.getStore().getProxy().url = "/TKalD/" + field.getValue() + " ";
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        //}
    },

    onAddrKExpand1: function (queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalK");
        queryPlan.combo.getStore().getProxy().url = "/TKalK/search/findByDimosCode";
        queryPlan.query = queryPlan.combo.up('form').getForm().findField('brAddrD').getValue();
    },

    onAddrKDirtyChange1: function (field, isDirty, eOpts) {
        //if (field.up('form').getForm().findField('a_new_form').getValue() !== "true") {
        field.getStore().getProxy().url = "/TKalK/" + field.getValue();
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        //}
    },

    onBranchSectorSelect: function (combo, records, eOpts) {
        //if (combo.up('form').getForm().findField('a_new_form').getValue() === "true") {
        var branchSectorId = combo.up('form').getForm().findField('branchSectorId');
        branchSectorId.setValue(combo.getValue());
        //}
    },

    onAddrPSelect31: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('taAddrPe');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeExpand31: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalPe");
        queryPlan.combo.getStore().getProxy().url = "/TKalPe/search/findByPerifCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('taAddrP').getValue()+" ";
    },

    onAddrPeSelect31: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('taAddrD');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrPeDirtyChange31: function(field, isDirty, eOpts) {
        //if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
        field.getStore().getProxy().url = "/TKalPe/"+field.getValue()+" ";
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        //}
    },

    onAddrDExpand31: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalD");
        queryPlan.combo.getStore().getProxy().url = "/TKalD/search/findByEnotCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('taAddrPe').getValue()+" ";
    },

    onAddrDSelect31: function(combo, records, eOpts) {
        var getnext = combo.up('form').getForm().findField('taAddrK');
        getnext.clearValue();
        getnext.enable();
    },

    onAddrDDirtyChange31: function(field, isDirty, eOpts) {
        //if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
        field.getStore().getProxy().url = "/TKalD/"+field.getValue()+" ";
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        //}
    },

    onAddrKExpand31: function(queryPlan, eOpts) {
        queryPlan.combo.getStore().getProxy().getReader().setRootProperty("_embedded.TKalK");
        queryPlan.combo.getStore().getProxy().url = "/TKalK/search/findByDimosCode";
        queryPlan.query=queryPlan.combo.up('form').getForm().findField('taAddrD').getValue();
    },

    onAddrKDirtyChange31: function(field, isDirty, eOpts) {
        //if (field.up('form').getForm().findField('a_new_form').getValue()!=="true"){
        field.getStore().getProxy().url = "/TKalK/"+field.getValue();
        field.getStore().getProxy().getReader().setRootProperty("");
        field.store.load();
        //}
    },

    onAddEntryClick: function(button, e, eOpts) {

        var entrie = Ext.create('widget.companytechniciananndiary', {});
        entrie.items.get(6).setStyle({margin:'0 0 0 2px'});
        entrie.items.get(5).disable();
        entrie.items.get(5).hide();
        button.up('form').getForm().findField('diaryEntriesnum').setValue(parseInt(button.up('form').getForm().findField('diaryEntriesnum').getValue())+1);
        if (button.up('form').getForm().findField('dateStart').value != "")
            entrie.items.get(0).minValue= (button.up('form').getForm().findField('dateStart').value > new Date()) ? button.up('form').getForm().findField('dateStart').value : new Date();
        if (button.up('form').getForm().findField('dateEnd').value != "")
            entrie.items.get(0).maxValue=button.up('form').getForm().findField('dateEnd').value;
        if(parseInt(button.up('form').getForm().findField('diaryEntriesnum').getValue())>1){
            entrie.items.get(0).hideLabel=true;
            entrie.items.get(1).hideLabel=true;
            entrie.items.get(2).hideLabel=true;
            entrie.items.get(3).hideLabel=true;
            entrie.items.get(4).hideLabel=true;
        }

        if(Ext.getCmp('taentrydel').hidden===true) {
            //var tomorrow = new Date(new Date().getTime() + 24 * 60 * 60 * 1000);
            var today = new Date();
            if (button.up('form').getForm().findField('dateStart').getValue() != "")
                entrie.items.get(0).minValue= (button.up('form').getForm().findField('dateStart').getValue() > today) ? button.up('form').getForm().findField('dateStart').getValue() : today;
            if (button.up('form').getForm().findField('dateEnd').getValue() != "")
                entrie.items.get(0).maxValue=button.up('form').getForm().findField('dateEnd').getValue();
            entrie.items.get(6).show();
        }
        var scrollpos = button.up('window').getScrollY();
        button.up().add(entrie);
        if(parseInt(button.up('form').getForm().findField('diaryEntriesnum').getValue())>1){
            var lastEntrie = button.up().items.get(button.up().items.length-2);
            if (lastEntrie.items.get(0).value !== null) {
                entrie.items.get(0).setValue(Ext.Date.add(lastEntrie.items.get(0).value, Ext.Date.MONTH, 1));
            } else {
                entrie.items.get(0).setValue(lastEntrie.items.get(0).value);
            }
            try {entrie.items.get(0).setValue(Ext.Date.add(lastEntrie.items.get(0).value, Ext.Date.MONTH, 1));}
            catch (ex){}
            entrie.items.get(1).setValue(lastEntrie.items.get(1).value);
            entrie.items.get(2).setValue(lastEntrie.items.get(2).value);
            entrie.items.get(3).setValue(lastEntrie.items.get(3).value);
            entrie.items.get(4).setValue(lastEntrie.items.get(4).value);
        }
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

    onAddRecurrentEntryClick: function(button, e, eOpts) {

        var entrie = Ext.create('widget.companytechnicianannrecurrentdiary', {});
        entrie.show();
        try
        {
            if (button.up('form').getForm().findField('dateStart').value != null)
            {
                entrie.down('form').getForm().findField('visitDateStart').minValue = button.up('form').getForm().findField('dateStart').value;
                entrie.down('form').getForm().findField('visitDateEnd').minValue = button.up('form').getForm().findField('dateStart').value;
            }
            else
            {
                entrie.down('form').getForm().findField('visitDateStart').minValue = new Date();
                entrie.down('form').getForm().findField('visitDateEnd').minValue = new Date();
            }
        }
        catch (err){}
        try
        {
            if (button.up('form').getForm().findField('dateEnd').value != null)
            {
                entrie.down('form').getForm().findField('visitDateStart').maxValue = button.up('form').getForm().findField('dateEnd').value;
                entrie.down('form').getForm().findField('visitDateEnd').maxValue = button.up('form').getForm().findField('dateEnd').value;
            }
            else
            {
                entrie.down('form').getForm().findField('visitDateStart').maxValue = Ext.Date.add(new Date(
                    new Date().getFullYear() + 1,
                    new Date().getMonth(),
                    new Date().getDate()
                ), Ext.Date.DAY,-1);
                entrie.down('form').getForm().findField('visitDateEnd').maxValue = Ext.Date.add(new Date(
                    new Date().getFullYear() + 1,
                    new Date().getMonth(),
                    new Date().getDate()
                ), Ext.Date.DAY,-1);
            }
        }
        catch (err){}
        entrie.down('form').getForm().findField('taAnnTaIdLocal').hide();
        entrie.down('form').getForm().findField('recurrency').setValue(0);

        button.up().add(entrie);
        entrie.down('form').getForm().findField('visitDateEnd').setValue(button.up('form').getForm().findField('dateEnd').getValue(), Ext.Date.MONTH, 1);
    },

    getRecurrentEntries: function(dateStart, time, totalDuration, durationH, durationM, technicianId) {
        var entrie = Ext.create('widget.companytechniciananndiary', {});
        entrie.items.get(6).setStyle({margin:'0 0 0 2px'});
        entrie.items.get(5).disable();
        entrie.items.get(5).hide();

        // Διαγραφή των πρώτων κενών επισκέψεων
        var emptyEntries;
        emptyEntries = this.down('form').getForm().findField('diaryEntriesnum').up().items.get(5);
        if (emptyEntries.items.get(0).getValue() == null && emptyEntries.items.get(1).rawValue == "08:00" && emptyEntries.items.get(2).getValue() == "" && emptyEntries.items.get(3).getValue() == "0" && emptyEntries.items.get(4).getValue() == "0" && emptyEntries.items.get(0).getValue() == null)
        {
            emptyEntries.destroy();
            this.down('form').getForm().findField('diaryEntriesnum').setValue(parseInt(this.down('form').getForm().findField('diaryEntriesnum').getValue())-1);
        }

        this.down('form').getForm().findField('diaryEntriesnum').setValue(parseInt(this.down('form').getForm().findField('diaryEntriesnum').getValue())+1);
        entrie.items.get(0).minValue= (this.down('form').getForm().findField('dateStart').value > new Date()) ? this.down('form').getForm().findField('dateStart').value : new Date();
        entrie.items.get(0).maxValue=this.down('form').getForm().findField('dateEnd').value;
        if(parseInt(this.down('form').getForm().findField('diaryEntriesnum').getValue())>1){
            entrie.items.get(0).hideLabel=true;
            entrie.items.get(1).hideLabel=true;
            entrie.items.get(2).hideLabel=true;
            entrie.items.get(3).hideLabel=true;
            entrie.items.get(4).hideLabel=true;
        }

        if(Ext.getCmp('taentrydel').hidden===true) {
            //var tomorrow = new Date(new Date().getTime() + 24 * 60 * 60 * 1000);
            var today = new Date();
            entrie.items.get(0).minValue= (this.down('form').getForm().findField('dateStart').getValue() > today) ? this.down('form').getForm().findField('dateStart').getValue() : today;
            entrie.items.get(0).maxValue=this.down('form').getForm().findField('dateEnd').getValue();
            entrie.items.get(6).show();
        }
        var scrollpos = this.down('window').getScrollY();
        this.down('form').getForm().findField('diaryEntriesnum').up().add(entrie); // Επισκέψεις Τεχνικών widget
        console.log(new Date()+" "+parseInt(this.down('form').getForm().findField('diaryEntriesnum').getValue()));
        if (this.down('form').getForm().findField('diaryEntriesnum').getValue() == 1)
            entrie.items.get(6).setStyle({margin:'26px 0 0 2px'});
        entrie.items.get(0).setValue(dateStart);
        entrie.items.get(1).setValue(time);
        entrie.items.get(2).setValue(totalDuration);
        entrie.items.get(3).setValue(durationH);
        entrie.items.get(4).setValue(durationM);
    },

    onBNumChange: function (field, newValue, oldValue, eOpts) {
        if (newValue !== null)
            field.up('component').calculateHours(
                0,
                parseInt(newValue),
                parseInt(field.up('form').getForm().findField('categCNum').getValue()), 2);
    },

    onCNumChange: function (field, newValue, oldValue, eOpts) {
        if (newValue !== null)
            field.up('component').calculateHours(
                0,
                parseInt(field.up('form').getForm().findField('categBNum').getValue()),
                parseInt(newValue), 3);
    },

    onDeleteClick: function(button, e, eOpts) {
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

    onViewDocClick: function(button, e, eOpts) {
        var url = "/getDocument?docId="+button.up('form').getForm().findField('attachedDocId').getValue();
        var win = window.open(url, '_blank');
        win.focus();
    },

    onTaAnn_save_submit_toolbarBeforeHide: function(component, eOpts) {
        component.getComponent('savebutton').destroy();
        component.getComponent('submitbutton').destroy();
    },
});