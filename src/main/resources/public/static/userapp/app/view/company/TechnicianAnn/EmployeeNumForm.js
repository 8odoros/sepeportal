/*
 * File: app/view/company/TechnicianAnn/EmployeeNumForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianAnn.EmployeeNumForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companytechnicianannemployeenumform',

    requires: [
        'MyApp.view.company.TechnicianAnn.EmployeeNumFormViewModel',
        'MyApp.view.company.TechnicianAnn.EmployeeNumFormViewController',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.FieldSet',
        'Ext.form.field.Number',
        'Ext.form.field.Display',
        'Ext.form.field.ComboBox',
        'Ext.form.CheckboxGroup',
        'Ext.form.field.Checkbox',
        'Ext.toolbar.Toolbar',
        'Ext.button.Button'
    ],

    controller: 'companytechnicianannemployeenumform',
    viewModel: {
        type: 'companytechnicianannemployeenumform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    overflowY: 'scroll',
    resizable: false,
    width: 800,
    title: 'Δηλώσεις Αριθμού Απασχολούμενων και Τεχνικού Ασφαλείας',
    modal: true,
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'form',
            rules: function() {

                var formt = this.getForm();

                var store = formt.findField('isTaNoneEmployerEmployee').store;
                if (formt.findField('categBNum').getValue()>0) {
                    if (store.getRange().length > 1) {
                        store.removeAt(1);
                    }
                } else {
                    if (store.getRange().length == 1) {
                        store.add({
                            name: 'Εργαζόμενος',
                            value: 1
                        });
                    }
                }

                if (formt.findField('categANum').getValue()>0){
                    formt.findField('isTaNoneEmployerEmployee').allowBlank=true;
                    formt.findField('isTaNoneEmployerEmployee').setDisabled(true);
                    formt.findField('isTaNoneEmployerEmployee').setValue();
                    formt.findField('taAfm').allowBlank=true;
                    formt.findField('taFullname').allowBlank=true;
                    formt.findField('taVisitProgram').allowBlank=true;
                    formt.findField('taDegree').allowBlank=true;
                    formt.findField('taAfm').setValue();
                    formt.findField('taFullname').setValue();
                    formt.findField('taVisitProgram').setValue();
                    formt.findField('taDegree').setValue();
                    formt.findField('taAfm').setDisabled(true);
                    formt.findField('taFullname').setDisabled(true);
                    formt.findField('taVisitProgram').setDisabled(true);
                    formt.findField('taDegree').setDisabled(true);
                    Ext.getCmp('rulesValid').allowBlank=true;
                    formt.findField('isValidData1').value=false;
                    formt.findField('isValidData2').value=false;
                    formt.findField('isValidData1').setDisabled(true);
                    formt.findField('isValidData2').setDisabled(true);

                }
                else{
                    if (formt.findField('categBNum').getValue()<=50 && formt.findField('categCNum').getValue()<=50){
                        formt.findField('isTaNoneEmployerEmployee').allowBlank=true;
                        formt.findField('isTaNoneEmployerEmployee').setDisabled(false);
                        formt.findField('isTaNoneEmployerEmployee').setValue();
                        formt.findField('taAfm').allowBlank=true;
                        formt.findField('taFullname').allowBlank=true;
                        formt.findField('taVisitProgram').allowBlank=true;
                        formt.findField('taDegree').allowBlank=true;
                        formt.findField('taAfm').setDisabled(false);
                        formt.findField('taFullname').setDisabled(false);
                        formt.findField('taVisitProgram').setDisabled(false);
                        formt.findField('taDegree').setDisabled(false);

                        formt.findField('isValidData1').value=false;
                        formt.findField('isValidData2').value=false;

                        if (formt.findField('categBNum').getValue()>20){
                            Ext.getCmp('rulesValid').allowBlank=true;
                            formt.findField('isValidData1').value=false;
                            formt.findField('isValidData2').value=false;
                            formt.findField('isValidData2').setDisabled(true);
                            formt.findField('isValidData1').setDisabled(false);
                            //Ext.getCmp('rulesValid').allowBlank=false;
                        }
                        else if (formt.findField('categBNum').getValue()<=20){
                            formt.findField('isTaNoneEmployerEmployee').allowBlank=true;
                            formt.findField('taAfm').allowBlank=true;
                            formt.findField('taFullname').allowBlank=true;
                            formt.findField('taVisitProgram').allowBlank=true;
                            formt.findField('taDegree').allowBlank=true;
                            Ext.getCmp('rulesValid').allowBlank=true;
                            formt.findField('isValidData1').value=false;
                            formt.findField('isValidData2').value=false;
                            formt.findField('isValidData1').setDisabled(true);
                            formt.findField('isValidData2').setDisabled(false);
                            //Ext.getCmp('rulesValid').allowBlank=true;
                        }

                    }
                    else{

                        formt.findField('isTaNoneEmployerEmployee').allowBlank=true;
                        formt.findField('isTaNoneEmployerEmployee').setDisabled(true);
                        formt.findField('isTaNoneEmployerEmployee').setValue();
                        formt.findField('taAfm').allowBlank=true;
                        formt.findField('taFullname').allowBlank=true;
                        formt.findField('taVisitProgram').allowBlank=true;
                        formt.findField('taDegree').allowBlank=true;
                        formt.findField('taAfm').setValue();
                        formt.findField('taFullname').setValue();
                        formt.findField('taVisitProgram').setValue();
                        formt.findField('taDegree').setValue();
                        formt.findField('taAfm').setDisabled(true);
                        formt.findField('taFullname').setDisabled(true);
                        formt.findField('taVisitProgram').setDisabled(true);
                        formt.findField('taDegree').setDisabled(true);
                        Ext.getCmp('rulesValid').allowBlank=true;
                        formt.findField('isValidData1').value=false;
                        formt.findField('isValidData2').value=false;
                        formt.findField('isValidData1').setDisabled(true);
                        formt.findField('isValidData2').setDisabled(true);
                    }


                }
            },
            //flex: 1,
            id: 'companytechniciananncompanyextra',
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
                    flex: 1,
                    height: 150,
                    fieldLabel: 'Ημ. Πρωτοκόλλου',
                    labelWidth: 180,
                    name: 'companyId',
                    validateOnChange: false
                },
                {
                    xtype: 'fieldset',
                    calculateSum: function(categANum, categBNum, categCNum, categId) {
                        var sum = categANum+categBNum+categCNum;
                        this.up('form').getForm().findField('totalEmpls').setValue(sum);

                        var form = this;
                        var successCall = function (options, success, response) {
                            if (Ext.JSON.decode(response.responseText).success) {


                                //form.up('form').getForm().findField('totalHours').setValue(Ext.JSON.decode(response.responseText).hours);
                                var tms = parseInt(Ext.JSON.decode(response.responseText).hours);
                                var hours1 = Math.floor(tms/60);
                                var minutes1 = tms - (hours1*60);
                                var textR = hours1.toString()+ " ώρες και " +minutes1.toString()+ " λεπτά.";
                                form.up('form').getForm().findField('taMinWorkTime').setValue(textR);


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
                    flex: 1,
                    focusOnToFront: false,
                    padding: 10,
                    toFrontOnShow: false,
                    title: 'ΑΡΙΘΜΟΣ ΑΠΑΣΧΟΛΟΥΜΕΝΩΝ ΑΝΑ ΔΡΑΣΤΗΡΙΟΤΗΤΑ ΕΠΙΚΙΝΔΥΝΟΤΗΤΑΣ',
                    items: [
                        {
                            xtype: 'numberfield',
                            anchor: '100%',
                            fieldLabel: 'Επικινδυνότητας Α\'',
                            labelWidth: 140,
                            name: 'categANum',
                            validateOnChange: false,
                            defaultValue: 0,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            hideTrigger: true,
                            maxLength: 19,
                            allowDecimals: true,
                            allowExponential: false,
                            minValue: 0,
                            listeners: {
                                change: 'onANumChange1'
                            }
                        },
                        {
                            xtype: 'numberfield',
                            anchor: '100%',
                            fieldLabel: 'Επικινδυνότητας Β\'',
                            labelWidth: 140,
                            name: 'categBNum',
                            validateOnChange: false,
                            defaultValue: 0,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            hideTrigger: true,
                            maxLength: 19,
                            allowDecimals: true,
                            allowExponential: false,
                            minValue: 0,
                            listeners: {
                                change: 'onBNumChange1'
                            }
                        },
                        {
                            xtype: 'numberfield',
                            anchor: '100%',
                            fieldLabel: 'Επικινδυνότητας Γ\'',
                            labelWidth: 140,
                            name: 'categCNum',
                            validateOnChange: false,
                            defaultValue: 0,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            hideTrigger: true,
                            maxLength: 19,
                            allowDecimals: true,
                            allowExponential: false,
                            minValue: 0,
                            listeners: {
                                change: 'onCNumChange1'
                            }
                        },
                        {
                            xtype: 'displayfield',
                            fieldLabel: 'Συνολικός Αριθμός Απασχολούμενων',
                            labelStyle: 'font-weight: bold',
                            labelWidth: 280,
                            name: 'totalEmpls',
                            submitValue: true,
                            value: 0
                        },
                        {
                            xtype: 'displayfield',
                            fieldLabel: 'Ελάχιστος χρόνος απασχόλησης Τεχνικού Ασφάλειας',
                            labelStyle: 'font-weight: bold',
                            labelWidth: 280,
                            name: 'taMinWorkTime',
                            submitValue: true,
                            value: 0
                        }
                    ]
                },
                {
                    xtype: 'fieldset',
                    flex: 1,
                    focusOnToFront: false,
                    padding: 10,
                    toFrontOnShow: false,
                    title: 'ΣΤΟΙΧΕΙΑ ΕΡΓΟΔΟΤΗ Ή ΕΡΓΑΖΟΜΕΝΟΥ ΠΟΥ ΑΝΑΛΑΜΒΑΝΕΙ ΚΑΘΗΚΟΝΤΑ ΤΕΧΝΙΚΟΥ ΑΣΦΑΛΕΙΑΣ',
                    items: [
                        {
                            xtype: 'displayfield',
                            anchor: '100%',
                            fieldLabel: '',
                            value: 'Συμπληρώνεται στην περίπτωση που ο εργοδότης ή κάποιος υπάλληλος της επιχείρησης εκτελεί χρέη Τεχνικού Ασφαλείας.'
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            fieldLabel: 'Ρόλος',
                            labelWidth: 180,
                            msgTarget: 'under',
                            name: 'isTaNoneEmployerEmployee',
                            validateOnChange: false,
                            readOnly: false,
                            validateOnBlur: false,
                            allowOnlyWhitespace: false,
                            editable: false,
                            maxLength: 100,
                            autoLoadOnValue: true,
                            displayField: 'name',
                            valueField: 'value',
                            bind: {
                                store: '{NoneEmployerEmployee}'
                            },
                            listeners: {
                                select: 'onIsTaNoneEmployerEmployeeSelect'
                            }
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Α.Φ.Μ',
                            labelWidth: 180,
                            name: 'taAfm',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowOnlyWhitespace: false,
                            blankText: 'Πρέπει να καταχωρήσετε το ΑΦΜ',
                            hideTrigger: true,
                            maxLength: 9,
                            minLength: 9,
                            maskRe:/[0-9.]/,
                            vtype: 'Number'
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Ονοματεπώνυμο',
                            labelWidth: 180,
                            name: 'taFullname',
                            validateOnChange: false,
                            readOnly: false,
                            validateOnBlur: false,
                            allowOnlyWhitespace: false,
                            blankText: 'Πρέπει να καταχωρήσετε το Ονοματεπώνυμο',
                            maxLength: 200
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            margin: '0 0 20 0',
                            fieldLabel: 'Πρόγραμμα επισκέψεων Τεχνικού Ασφάλειας',
                            labelWidth: 180,
                            name: 'taVisitProgram',
                            validateOnChange: false,
                            readOnly: false,
                            validateOnBlur: false,
                            allowOnlyWhitespace: false,
                            maxLength: 1000
                        },
                        {
                            xtype: 'checkboxgroup',
                            anchor: '100%',
                            id: 'rulesValid',
                            width: 400,
                            msgTarget: 'under',
                            validateOnChange: false,
                            blankText: 'Πρέπει να αποδεχθείτε τους όρους.',
                            columns: 1,
                            items: [
                                {
                                    xtype: 'checkboxfield',
                                    margin: '0 0 10 0',
                                    name: 'isValidData1',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Δηλώνω υπεύθυνα ότι είμαι ΠΕ και έχω μια από τις ειδικότητες που προβλέπονται για τον κλάδο της οικονομικής δραστηριότητας του οργανισμού. (Για Εργοδότες και αριθμό εργαζομένων Β κατηγορίας άνω των 20)',
                                    inputValue: '1',
                                    uncheckedValue: '0'
                                },
                                {
                                    xtype: 'checkboxfield',
                                    name: 'isValidData2',
                                    validateOnChange: false,
                                    validateOnBlur: false,
                                    boxLabel: 'Δηλώνω υπεύθυνα ότι είμαι ΠΕ και έχω μια από τις ειδικότητες που προβλέπονται για τον κλάδο της οικονομικής δραστηριότητας του οργανισμού ή έχω παρακολουθήσει εκπαίδευση 35 ωρών. (Για Εργοδότες και αριθμό εργαζομένων Β κατηγορίας έως 20)',
                                    inputValue: '1',
                                    uncheckedValue: '0'
                                }
                            ]
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            margin: '0 0 20 0',
                            fieldLabel: 'Αριθμός Επιμόρφωσης ή τίτλος πτυχίου',
                            labelWidth: 180,
                            name: 'taDegree',
                            validateOnChange: false,
                            readOnly: false,
                            validateOnBlur: false,
                            allowOnlyWhitespace: false,
                            blankText: 'Πρέπει να καταχωρήσετε τον αριθμό επιμόρφωσης ή τον τίτλο πτυχίου',
                            maxLength: 500
                        },
                    ]
                },
                {
                    xtype: 'checkboxgroup',
                    flex: 1,
                    width: 400,
                    msgTarget: 'under',
                    allowBlank: false,
                    blankText: 'Πρέπει να αποδεχθείτε τους όρους για να προχωρήσετε στην ενημέρωση.',
                    items: [
                        {
                            xtype: 'checkboxfield',
                            name: 'isValidAllData',
                            validateOnChange: false,
                            validateOnBlur: false,
                            boxLabel: 'Υπεύθυνη Δήλωση ότι τα παραπάνω στοιχεία είναι ακριβή και αληθή.',
                            inputValue: '1',
                            uncheckedValue: '0'
                        }
                    ]
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
            flex: 1,
            dock: 'bottom',
            id: 'comptechnicianannemplsnum_sumbit_toolbar',
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
                    itemId: 'savebutton',
                    name: 'savebutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 250,
                    glyph: 'xf0c7@FontAwesome',
                    text: 'Ενημέρωση Στοιχείων',
                    listeners: {
                        click: {
                            fn: 'onSave_COMPANY_TECHNICIAN_ANN_EMPLS_NUM',
                            scope: 'controller'
                        }
                    }
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

    onANumChange1: function(field, newValue, oldValue, eOpts) {
        field.up('component').calculateSum(
            Number(newValue !== null ? newValue : 0),
            Number(field.up('form').getForm().findField('categBNum').getValue() !== null ? field.up('form').getForm().findField('categBNum').getValue() : 0),
            Number(field.up('form').getForm().findField('categCNum').getValue() !== null ? field.up('form').getForm().findField('categCNum').getValue() : 0),1);
        field.up('form').rules();
    },

    onBNumChange1: function(field, newValue, oldValue, eOpts) {
        field.up('component').calculateSum(
            Number(field.up('form').getForm().findField('categANum').getValue() !== null ? field.up('form').getForm().findField('categANum').getValue() : 0),
            Number(newValue !== null ? newValue : 0),
            Number(field.up('form').getForm().findField('categCNum').getValue() !== null ? field.up('form').getForm().findField('categCNum').getValue() : 0),2);
        field.up('form').rules();
    },

    onCNumChange1: function(field, newValue, oldValue, eOpts) {
        field.up('component').calculateSum(
            Number(field.up('form').getForm().findField('categANum').getValue() !== null ? field.up('form').getForm().findField('categANum').getValue() : 0),
            Number(field.up('form').getForm().findField('categBNum').getValue() !== null ? field.up('form').getForm().findField('categBNum').getValue() : 0),
            Number(newValue !== null ? newValue : 0),3);
        field.up('form').rules();
    },

    onWindowBeforeDestroy: function(component, eOpts) {
        var view = Ext.getCmp('companymainView');
        var menu = view.getComponent('menuPanel');
        var contentPanel = view.getComponent('contentPanel');
        menu.setDisabled(false);
        contentPanel.setDisabled(false);
    },

    onIsTaNoneEmployerEmployeeSelect: function (combo, records, eOpts) {
        if (combo.getValue() === 0 || combo.getValue() === 1) {
            Ext.getCmp('rulesValid').allowBlank=false;
        }
    },

});