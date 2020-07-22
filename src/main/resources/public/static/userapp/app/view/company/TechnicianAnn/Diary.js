/*
 * File: app/view/company/TechnicianAnn/Diary.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianAnn.Diary', {
    extend: 'Ext.form.FieldContainer',
    alias: 'widget.companytechniciananndiary',

    requires: [
        'MyApp.view.company.TechnicianAnn.DiaryViewModel',
        'Ext.form.FieldContainer',
        'Ext.form.field.Date',
        'Ext.form.field.Time',
        'Ext.form.field.Number'
    ],

    viewModel: {
        type: 'companytechniciananndiary'
    },
    padding: '0 5 5 5',
 //   layout: 'anchor',
    msgTarget: 'under',
    combineErrors: true,
    layout: {
        type: 'hbox',
        align: 'stretch'
    },
    hideLabel: true,

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
                    submitFormat: 'Y-m-d\\T00:00:00.000+0000',
                    minText: 'Η ημερομηνία επίσκεψης πρέπει να είναι μετά την '+Ext.Date.format(new Date(new Date().getTime() - 24 * 60 * 60 * 1000), 'd-m-Y')+ ' και μετά την έναρξη της αναγγελίας',
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
                    format: 'H:i',
                    minText: 'Η ώρα της επίσκεψης πρέπει να είναι μεταγενέστερη της τρέχουσας.'
                },
                {
                    xtype: 'numberfield',
                    flex: 2,
                    hidden: true,
                    fieldLabel: 'Διάρκεια (λεπτά)',
                    labelAlign: 'top',
                    msgTarget: 'none',
                    name: 'visitDurationMinutes',
                    validateOnChange: false,
                    value: 0,
                    readOnly: true,
                    validateOnBlur: false,
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    hideTrigger: true,
                    maxLength: 5,
                    minLength: 1,
                    allowDecimals: false,
                    allowExponential: false,
                    minValue: 24,
                    minText: 'Η επίσκεψη πρέπει να έχει διάρκεια τουλάχιστον 24 λεπτά.',
                    maxValue: 1440,
                    maxText: 'Η επίσκεψη δεν πρέπει να ξεπερνά τις 24 ώρες.'
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
                            field.up().getComponent(2).setValue(((parseInt(newValue) * 60) + parseInt(field.up().getComponent(4).getValue())));

                            if (field.up().getComponent(5).isVisible()){
                            var storeLocal = Ext.StoreMgr.lookup('company.TechnicianAnn.LocalTaEntries');
                            var taS = Ext.getCmp('taAnnTaEntries');
                            var tasEntriesArr = [];
                            var tot = Ext.getCmp('taAnnTaEntries').items.length - 4;
                            if (parseInt(tot) > 0) {
                                for (var j = 0; j < parseInt(tot); j++) {
                                    if (field.up().getComponent(5).getValue() === taS.items.getAt(j + 4).items.getAt(0).items.getAt(6).getValue()) {
                                        if (taS.items.getAt(j + 4).items.getAt(0).items.getAt(0).getValue() === "2") {
                                            field.setMaxValue(Number.MAX_VALUE);
                                            field.up().getComponent(2).setMaxValue(Number.MAX_VALUE);
                                        }
                                    }
                                }
                            }

                        }

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
                            field.up().getComponent(2).setValue(((parseInt(newValue))+parseInt(field.up().getComponent(3).getValue())*60));
                        }
                    }
                },
                {
                    xtype: 'combobox',
                    flex: 4,
                    fieldLabel: 'Τεχνικός Ασφάλειας',
                    labelAlign: 'top',
                    labelWidth: 160,
                    msgTarget: 'none',
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
                        change:  function(field, newValue, oldValue, eOpts) {
                            var storeLocal = Ext.StoreMgr.lookup('company.TechnicianAnn.LocalTaEntries');
                            var taS = Ext.getCmp('taAnnTaEntries');
                            var tasEntriesArr = [];
                            if (taS != null) {
                                var tot = Ext.getCmp('taAnnTaEntries').items.length-4;
                                if (parseInt(tot) > 0) {
                                    for (var j = 0; j < parseInt(tot); j++) {
                                        if(field.getValue() === taS.items.getAt(j+4).items.getAt(0).items.getAt(6).getValue() ){
                                            if (taS.items.getAt(j+4).items.getAt(0).items.getAt(0).getValue() === "2") {
                                                field.up().getComponent(3).setMaxValue(Number.MAX_VALUE);
                                                field.up().getComponent(2).setMaxValue(Number.MAX_VALUE);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                },
                /*{
                    xtype: 'label',
                    style: 'cursor: pointer;',
                    html: '<img height="17px" src="static/userapp/resources/delete.png" title="Διαγραφή Επίσκεψης"/>',
                    listeners: {
                        render: function(c){
                            c.getEl().on('click', function(){
                                if (Ext.getCmp('tadiaryEntries').up('form').getForm().findField('diaryEntriesnum').getValue()>1)
                                {
                                    var firstCmp = false;
                                    if (this.up() == Ext.getCmp('tadiaryEntries').items.get(5))
                                    {
                                        firstCmp = true;
                                    }
                                    
                                    var scrollpos = Ext.getCmp('tadiaryEntries').up('window').getScrollY();
                                    this.up().destroy();
                                    Ext.getCmp('tadiaryEntries').up('form').getForm().findField('diaryEntriesnum').setValue(parseInt(Ext.getCmp('tadiaryEntries').up('form').getForm().findField('diaryEntriesnum').getValue())-1);

                                    Ext.getCmp('tadiaryEntries').up('form').getForm().findField('diaryEntriesnum').up().items.get(5).items.get(0).setFieldLabel('Ημερομηνία:');
                                    Ext.getCmp('tadiaryEntries').up('form').getForm().findField('diaryEntriesnum').up().items.get(5).items.get(1).setFieldLabel('Ώρα:');
                                    Ext.getCmp('tadiaryEntries').up('form').getForm().findField('diaryEntriesnum').up().items.get(5).items.get(2).setFieldLabel('Διάρκεια (λεπτά)');
                                    Ext.getCmp('tadiaryEntries').up('form').getForm().findField('diaryEntriesnum').up().items.get(5).items.get(3).setFieldLabel('[Διάρκεια] Ώρες:');
                                    Ext.getCmp('tadiaryEntries').up('form').getForm().findField('diaryEntriesnum').up().items.get(5).items.get(4).setFieldLabel('Λεπτά:');
                                    if (Ext.getCmp('tadiaryEntries').up('form').getForm().findField('diaryEntriesnum').up().items.get(5).items.get(5).isVisible()) Ext.getCmp('tadiaryEntries').up('form').getForm().findField('diaryEntriesnum').up().items.get(5).items.get(5).setFieldLabel('Τεχνικός Ασφάλειας:');

                                    if (firstCmp)
                                        Ext.getCmp('tadiaryEntries').up('form').getForm().findField('diaryEntriesnum').up().items.get(5).items.get(6).setStyle({margin:'26px 0 0 0'});

                                    Ext.getCmp('tadiaryEntries').up('window').scrollTo(0, scrollpos);
                                }
                            }, c);
                        }
                    }
                },*/
                {
                    xtype: 'button',
                    border: 0,
                    frame: false,
                    style: 'background-color:transparent;',
                    //iconCls : 'x-fa fa-ban',
                    iconCls: 'deleteme',
                    listeners: {
                        click: function(button, e, eOpts) {
                            if (Ext.getCmp('tadiaryEntries').up('form').getForm().findField('diaryEntriesnum').getValue()>1)
                            {
                                var firstCmp = false;
                                if (this.up() == Ext.getCmp('tadiaryEntries').items.get(5))
                                {
                                    firstCmp = true;
                                }

                                var scrollpos = Ext.getCmp('tadiaryEntries').up('window').getScrollY();
                                this.up().destroy();
                                Ext.getCmp('tadiaryEntries').up('form').getForm().findField('diaryEntriesnum').setValue(parseInt(Ext.getCmp('tadiaryEntries').up('form').getForm().findField('diaryEntriesnum').getValue())-1);

                                Ext.getCmp('tadiaryEntries').up('form').getForm().findField('diaryEntriesnum').up().items.get(5).items.get(0).setFieldLabel('Ημερομηνία:');
                                Ext.getCmp('tadiaryEntries').up('form').getForm().findField('diaryEntriesnum').up().items.get(5).items.get(1).setFieldLabel('Ώρα:');
                                Ext.getCmp('tadiaryEntries').up('form').getForm().findField('diaryEntriesnum').up().items.get(5).items.get(2).setFieldLabel('Διάρκεια (λεπτά)');
                                Ext.getCmp('tadiaryEntries').up('form').getForm().findField('diaryEntriesnum').up().items.get(5).items.get(3).setFieldLabel('[Διάρκεια] Ώρες:');
                                Ext.getCmp('tadiaryEntries').up('form').getForm().findField('diaryEntriesnum').up().items.get(5).items.get(4).setFieldLabel('Λεπτά:');
                                if (Ext.getCmp('tadiaryEntries').up('form').getForm().findField('diaryEntriesnum').up().items.get(5).items.get(5).isVisible()) Ext.getCmp('tadiaryEntries').up('form').getForm().findField('diaryEntriesnum').up().items.get(5).items.get(5).setFieldLabel('Τεχνικός Ασφάλειας:');

                                if (firstCmp)
                                    Ext.getCmp('tadiaryEntries').up('form').getForm().findField('diaryEntriesnum').up().items.get(5).items.get(6).setStyle({margin:'13px 0 0 0'});

                                Ext.getCmp('tadiaryEntries').up('window').scrollTo(0, scrollpos);
                            }

                        }
                    }
                }
    ],

    onVisitDurHsChange: function(field, newValue, oldValue, eOpts) {
        field.up().getComponent(2).setValue(((parseInt(newValue)*60)+parseInt(field.up().getComponent(4).getValue())));
    },

    onVisitDurMsChange: function(field, newValue, oldValue, eOpts) {
        field.up().getComponent(2).setValue(((parseInt(newValue))+parseInt(field.up().getComponent(3).getValue())*60));
    }

});