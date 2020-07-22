/*
 * File: app/view/company/TechnicianBooks/BookForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianBooks.BookForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companytechnicianbooksbookform',

    requires: [
        'MyApp.view.company.TechnicianBooks.BookFormViewModel',
        'MyApp.view.company.TechnicianBooks.BookFormViewController',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.FieldSet',
        'Ext.form.field.Date',
        'Ext.form.field.ComboBox',
        'Ext.form.field.Display',
        'Ext.toolbar.Toolbar',
        'Ext.button.Button'
    ],

    controller: 'companytechnicianbooksbookform',
    viewModel: {
        type: 'companytechnicianbooksbookform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    resizable: false,
    width: 800,
    title: 'Ενεργοποίηση Βιβλίου',
    modal: true,
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'form',
            id: 'doctorbookbook1',
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
                    flex: 1,
                    focusOnToFront: false,
                    padding: 10,
                    toFrontOnShow: false,
                    title: '',
                    items: [
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            fieldLabel: 'Ημερομηνία Δημιουργίας',
                            labelWidth: 200,
                            msgTarget: 'none',
                            name: 'dateCreated',
                            validateOnChange: false,
                            readOnly: true,
                            validateOnBlur: false,
                            maxLength: 100,
                            format: 'd-m-Y',
                            submitFormat: 'y-m-d\\T00:00:00.000+0000'
                        },
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            fieldLabel: 'Παράρτημα Ενεργής Αναγγελίας',
                            labelWidth: 200,
                            name: 'compPtlBranchId',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            editable: false,
                            autoLoadOnValue: true,
                            displayField: 'brDescr',
                            store: 'company.PTL_TA_ANN_COMP_BR_COMBO',
                            valueField: 'ptlBranchId'
                        }
                    ]
                },
                {
                    xtype: 'displayfield',
                    flex: 1,
                    value: 'Το βιβλίο τηρείται ανά υποκατάστημα / εγκατάσταση'
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
            html: '',
            id: 'comptechnicianbook_save_submit_toolbar',
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
                    itemId: 'submitbutton',
                    padding: 10,
                    glyph: 'xf1d8@FontAwesome',
                    text: ' Δημιουργία Βιβλίου',
                    listeners: {
                        click: {
                            fn: 'onSubmit_COMPANY_TECHNICIAN_BOOK',
                            scope: 'controller'
                        }
                    }
                }
            ]
        }
    ],
    listeners: {
        beforedestroy: 'onWindowBeforeDestroy'
    },

    onWindowBeforeDestroy: function(component, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var store = Ext.StoreMgr.lookup('company.TechnicianBooks.BOOKS');
        store.load({
            callback: function(records, operation, success) {
                if (success) {
                    var viewsub = Ext.create('MyApp.view.company.TechnicianBooksPanel');
                    center.add(viewsub);
                }
            }
        });

    }

});