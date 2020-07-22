/*
 * File: app/view/company/TechnicianAnn/Diary.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianAnn.RecurrentDiary', {
    extend: 'Ext.window.Window',
    alias: 'widget.companytechnicianannrecurrentdiary',

    requires: [
        'MyApp.view.company.TechnicianAnn.RecurrentDiaryViewModel',
        'MyApp.view.company.TechnicianAnn.RecurrentDiaryViewController',
        'Ext.form.FieldContainer',
        'Ext.form.field.Date',
        'Ext.form.field.Time',
        'Ext.form.field.Number',
        'MyApp.view.shared.CloseFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.FieldSet',
        'Ext.toolbar.Toolbar',
        'Ext.button.Button',
        'Ext.panel.Tool'
    ],

    controller: 'companytechnicianannrecurrentdiary',
    viewModel: {
        type: 'companytechnicianannrecurrentdiary'
    },
/*    padding: '0 5 5 5',
    layout: 'anchor',
    hideLabel: true,*/

    constrain: true,
    draggable: false,
    frame: true,
    overflowY: 'auto',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Επαναληπτικές επισκέψεις τεχνικού',
    modal: true,
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },

    items: [
        {
            xtype: 'form',
            id: 'companytechnicianannrecurrentdates',
            padding: 15,
            title: '',
            layout: {
                type: 'vbox',
                align: 'stretch',
            },
            items: [
                {
                    xtype: 'datefield',
                    anchor: '100%',
                    fieldLabel: 'Ημερομηνία Έναρξης',
                    labelWidth: 130,
                    name: 'visitDateStart',
                    validateOnChange: false,
                    validateOnBlur: false,
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    maxLength: 100,
                    format: 'd-m-Y',
                    disabledDates:['25-03', '15-08', '25-12', '26-12'],
                    disabledDays: [0,6],
                    submitFormat: 'Y-m-d\\T00:00:00.000+0000'
                },
                {
                    xtype: 'datefield',
                    anchor: '100%',
                    fieldLabel: 'Ημερομηνία Λήξης',
                    labelWidth: 130,
                    name: 'visitDateEnd',
                    validateOnChange: false,
                    validateOnBlur: false,
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    maxLength: 100,
                    format: 'd-m-Y',
                    disabledDates:['25-03', '15-08', '25-12', '26-12'],
                    disabledDays: [0,6],
                    submitFormat: 'Y-m-d\\T00:00:00.000+0000'
                },
                {
                    xtype: 'combobox',
                    anchor: '100%',
                    fieldLabel: 'Περιοδικότητα',
                    labelWidth: 130,
                    name: 'recurrency',
                    validateOnChange: false,
                    readOnly: false,
                    validateOnBlur: false,
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    editable: false,
                    autoLoadOnValue: true,
                    displayField: 'name',
                    forceSelection: true,
                    store: 'company.RECURRENCY',
                    valueField: 'abbr',
                    listeners: {
                        change: function(field, newValue, oldValue)
                        {
                            if (newValue == 4)
                            {
                                field.up('form').getForm().findField('specificRecurrencyOrder').show();
                                field.up('form').getForm().findField('specificRecurrencyDay').show();
                                field.up('form').getForm().findField('specificRecurrencyOrder').enable();
                                field.up('form').getForm().findField('specificRecurrencyDay').enable();
                            }
                            else
                            {
                                field.up('form').getForm().findField('specificRecurrencyOrder').hide();
                                field.up('form').getForm().findField('specificRecurrencyDay').hide();
                                field.up('form').getForm().findField('specificRecurrencyOrder').disable();
                                field.up('form').getForm().findField('specificRecurrencyDay').disable();
                            }
                        }
                    }
                },
                {
                    xtype:'container',
                    layout : {
                        type : 'hbox',
                        pack : 'center'
                    },
                    items: [
                    {
                        xtype: 'combobox',
                        margin: '7 7 7 7',
                        anchor: '50%',
                        fieldLabel: 'Φορά',
                        labelWidth: 50,
                        name: 'specificRecurrencyOrder',
                        validateOnChange: false,
                        readOnly: false,
                        validateOnBlur: false,
                        allowBlank: false,
                        allowOnlyWhitespace: false,
                        editable: false,
                        autoLoadOnValue: true,
                        displayField: 'name',
                        forceSelection: true,
                        bind: {store: '{SPECIFIC_RECURRENCY_ORDER}'},
                        valueField: 'abbr',
                    },
                    {
                        xtype: 'combobox',
                        margin: '7 7 7 7',
                        anchor: '50%',
                        fieldLabel: 'Ημέρα',
                        labelWidth: 50,
                        name: 'specificRecurrencyDay',
                        validateOnChange: false,
                        readOnly: false,
                        validateOnBlur: false,
                        allowBlank: false,
                        allowOnlyWhitespace: false,
                        editable: false,
                        autoLoadOnValue: true,
                        displayField: 'name',
                        forceSelection: true,
                        bind: {store: '{SPECIFIC_RECURRENCY_DAY}'},
                        valueField: 'abbr',
                    }
                    ]
                },
                {
                    xtype: 'timefield',
                    anchor: '100%',
                    fieldLabel: 'Ώρα',
                    labelWidth: 130,
                    name: 'visitTime',
                    validateOnChange: false,
                    value: '08:00',
                    validateOnBlur: false,
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    maxLength: 100,
                    format: 'H:i'
                },
                {
                    xtype: 'numberfield',
                    anchor: '100%',
                    fieldLabel: '[Διάρκεια] Ώρες',
                    labelWidth: 130,
                    name: 'visitDurHs',
                    validateOnChange: false,
                    value: 0,
                    validateOnBlur: false,
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    hideTrigger: true,
                    maxLength: 2,
                    minLength: 1,
                    allowDecimals: false,
                    allowExponential: false,
                    minValue: 0,
                    listeners: {
                        change:  function(form, newValue, oldValue, eOpts) {
                            try{
                            form.up().up().down().getComponent(4).setValue(((parseInt(newValue)*60)+parseInt(form.up().up().down().getComponent(6).getValue())));
                            }
                            catch (ex){}

                            var storeLocal = Ext.StoreMgr.lookup('company.TechnicianAnn.LocalTaEntries');
                            var taS = Ext.getCmp('taAnnTaEntries');
                            var tasEntriesArr = [];
                            var tot = Ext.getCmp('taAnnTaEntries').items.length-4;
                            if (parseInt(tot) > 0) {
                                for (var j = 0; j < parseInt(tot); j++) {
                                    try{
                                    if(form.up().up().down().getComponent(7).getValue() === taS.items.getAt(j+4).items.getAt(0).items.getAt(6).getValue() ){
                                        if (taS.items.getAt(j+4).items.getAt(0).items.getAt(0).getValue() === "2") {
                                            form.setMaxValue(Number.MAX_VALUE);
                                            form.up().up().down().getComponent(4).setMaxValue(Number.MAX_VALUE);
                                        }
                                    }
                                    }
                                    catch (ex){}
                                }
                            }
                        }
                    }
                },
                {
                    xtype: 'numberfield',
                    anchor: '100%',
                    fieldLabel: 'Λεπτά',
                    name: 'visitDurMs',
                    labelWidth: 130,
                    validateOnChange: false,
                    value: 0,
                    validateOnBlur: false,
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    hideTrigger: true,
                    maxLength: 2,
                    minLength: 1,
                    allowDecimals: false,
                    allowExponential: false,
                    maxText: 'Η ώρα έχει μέχρι 59 λεπτά',
                    maxValue: 59,
                    minValue: 0,
                    listeners: {
                        change:  function(form, newValue, oldValue, eOpts) {
                            form.up().up().down().getComponent(4).setValue(((parseInt(newValue))+parseInt(form.up().up().down().getComponent(5).getValue())*60));
                        }
                    }
                },
                {
                    xtype: 'combobox',
                    anchor: '100%',
                    fieldLabel: 'Τεχνικός Ασφάλειας',
                    labelWidth: 130,
                    name: 'taAnnTaIdLocal',
                    validateOnChange: false,
                    validateOnBlur: false,
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    editable: false,
                    maxLength: 200,
                    autoLoadOnValue: true,
                    displayField: 'name',
                    queryCaching: false,
                    queryMode: 'local',
                    store: 'company.TechnicianAnn.LocalTaEntries',
                    valueField: 'id',
                    listeners: {
                        change:  function(form, newValue, oldValue, eOpts) {
                            var storeLocal = Ext.StoreMgr.lookup('company.TechnicianAnn.LocalTaEntries');
                            var taS = Ext.getCmp('taAnnTaEntries');
                            var tasEntriesArr = [];
                            if (taS != null) {
                                var tot = Ext.getCmp('taAnnTaEntries').items.length-4;
                                if (parseInt(tot) > 0) {
                                    for (var j = 0; j < parseInt(tot); j++) {
                                        if(form.getValue() === taS.items.getAt(j+4).items.getAt(0).items.getAt(6).getValue() ){
                                            if (taS.items.getAt(j+4).items.getAt(0).items.getAt(0).getValue() === "2") {
                                                form.up().up().down().getComponent(4).setMaxValue(Number.MAX_VALUE);
                                                form.up().up().down().getComponent(3).setMaxValue(Number.MAX_VALUE);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                },
                {
                    xtype: 'displayfield',
                    name: 'offDaysMsg',
                    labelWidth: '500',
                    labelSeparator : "",
                    fieldLabel: '<i>* Τα Σαββατοκύριακα και οι αργίες δεν προστίθενται στο πρόγραμμα. Αντικαταστείστε τις επισκέψεις αυτές καταχωρώντας μία - μία όποιες ημερομηνίες επιθυμείτε.</i>',
                    allowBlank: false
                }
            ]
        },
    ],
    dockedItems:
        [
            {
                xtype: 'toolbar',
                dock: 'bottom',
                id: 'companytechnicianannrecurrentdatesbuttons',
                style: {
                    'background-color': '#f5f5f5'
                },
                layout: {
                    type: 'hbox',
                    pack: 'end'
                },
                items:
                    [
                        {
                            xtype: 'button',
                            itemId: 'addbutton',
                            maxWidth: 340,
                            padding: 10,
                            width: 200,
                            text: 'Προσθήκη',
                            tooltip: 'Έλεγχος διαθεσιμότητας των ημερομηνιών που ορίσατε',
                            listeners: {
                             click: {
                             fn: 'onAdd_COMPANY_TECHNICIAN_ANN_RECURRENT_DATES',
                             scope: 'controller'
                             }
                             }
                        },
                    ]
            },
        ],

    tools: [
        {
            xtype: 'sharedcloseformtool'
        }
    ],
});