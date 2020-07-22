/*
 * File: app/view/company/DoctorAnn/Diary.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.DoctorAnn.Diary', {
    extend: 'Ext.form.FieldContainer',
    alias: 'widget.companydoctoranndiary',

    requires: [
        'MyApp.view.company.DoctorAnn.DiaryViewModel',
        'Ext.form.FieldContainer',
        'Ext.form.field.Date',
        'Ext.form.field.Time',
        'Ext.form.field.Number'
    ],

    viewModel: {
        type: 'companydoctoranndiary'
    },
    padding: '0 5 5 5',
    layout: 'anchor',
    hideLabel: true,

    items: [
        {
            xtype: 'fieldcontainer',
            msgTarget: 'under',
            combineErrors: true,
            layout: {
                type: 'hbox',
                align: 'stretch'
            },
            items: [
                {
                    xtype: 'datefield',
                    flex: 2,
                    fieldLabel: 'Ημερομηνία',
                    labelAlign: 'top',
                    msgTarget: 'none',
                    name: 'visitDate',
                    validateOnChange: false,
                    validateOnBlur: false,
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    maxLength: 100,
                    format: 'd-m-Y',
                    submitFormat: 'Y-m-d\\T00:00:00.000+0000'
                },
                {
                    xtype: 'timefield',
                    flex: 1,
                    fieldLabel: 'Ώρα',
                    labelAlign: 'top',
                    msgTarget: 'none',
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
                    flex: 1,
                    hidden: true,
                    fieldLabel: 'Διάρκεια (σε λεπτά)',
                    labelAlign: 'top',
                    msgTarget: 'none',
                    name: 'visitDurationMinutes',
                    readOnly: true,
                    validateOnChange: false,
                    value: 0,
                    validateOnBlur: false,
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    hideTrigger: true,
                    maxLength: 5,
                    minLength: 1,
                    allowDecimals: false,
                    allowExponential: false,
                    minValue: 1,
                    minText: 'Η επίσκεψη πρέπει να έχει διάρκεια.',
                    //maxValue: 480,
                    //minText: 'Η επίσκεψη δεν πρέπει να ξεπερνά τις 8 ώρες.'
                },{
                    xtype: 'numberfield',
                    flex: 2,
                    fieldLabel: '[Διάρκεια] Ώρες',
                    labelAlign: 'top',
                    msgTarget: 'none',
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
                    //maxText: 'Το μέγιστο είναι 8 ώρες',
                    //maxValue: 8,
                    minValue: 0,
                    listeners: {
                        change:  function(field, newValue, oldValue, eOpts) {
                            field.up().up().down().getComponent(2).setValue(((parseInt(newValue)*60)+parseInt(field.up().up().down().getComponent(4).getValue())));
                        }
                    }
                },
                {
                    xtype: 'numberfield',
                    flex: 1,
                    fieldLabel: 'Λεπτά',
                    labelAlign: 'top',
                    msgTarget: 'none',
                    name: 'visitDurMs',
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
                        change:  function(field, newValue, oldValue, eOpts) {
                            field.up().up().down().getComponent(2).setValue(((parseInt(newValue))+parseInt(field.up().up().down().getComponent(3).getValue())*60));
                        }
                    }
                },
                {
                    xtype: 'combobox',
                    flex: 4,
                    fieldLabel: 'Iατρός Εργασίας',
                    labelAlign: 'top',
                    labelWidth: 160,
                    msgTarget: 'none',
                    name: 'ieAnnIeIdLocal',
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
                    store: 'company.DoctorAnn.LocalIeEntries',
                    valueField: 'id'
                },
                {
                    xtype: 'button',
                    border: 0,
                    hidden: true,
                    frame: false,
                    style: 'background-color:transparent;',
                    iconCls: 'removeone',
                    tooltip: 'Διαγραφή επίσκεψης',
                    listeners: {
                        click: function(button, e, eOpts) {
                            var scrollpos = Ext.getCmp('diaryEntries').up('window').getScrollY();
                            this.up().up().destroy();
                            Ext.getCmp('diaryEntries').up('window').scrollTo(0, scrollpos);

                        }
                    }
                }
            ]
        }
    ],
    onVisitDurHsChange: function(field, newValue, oldValue, eOpts) {
        field.up().up().down().getComponent(2).setValue(((parseInt(newValue)*60)+parseInt(field.up().up().down().getComponent(4).getValue())));
    },

    onVisitDurMsChange: function(field, newValue, oldValue, eOpts) {
        field.up().up().down().getComponent(2).setValue(((parseInt(newValue))+parseInt(field.up().up().down().getComponent(3).getValue())*60));
    }

});