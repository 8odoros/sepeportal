/*
 * File: app/view/company/CompanyAdmin/UserNewEditPrevForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.CompanyAdmin.UserNewEditPrevForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companycompanyadminuserneweditprevform',

    requires: [
        'MyApp.view.company.CompanyAdmin.UserNewEditPrevFormViewModel',
        'MyApp.view.company.CompanyAdmin.UserNewEditPrevFormViewController',
        'MyApp.view.shared.PrintFormTool',
        'MyApp.view.shared.CloseFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.field.TextArea',
        'Ext.form.field.Checkbox',
        'Ext.form.CheckboxGroup',
        'Ext.toolbar.Toolbar',
        'Ext.button.Button',
        'Ext.panel.Tool'
    ],

    controller: 'companycompanyadminuserneweditprevform',
    viewModel: {
        type: 'companycompanyadminuserneweditprevform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    overflowY: 'scroll',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Δημιουργία Χρήστη για παραχώρηση δικαιωμάτων σε υπηρεσίες',
    modal: true,
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
            id: 'companyuserform',
            padding: 15,
            title: 'Στοιχεία Χρήστη',
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
                    width: 150,
                    labelWidth: 180,
                    name: 'isAdmin',
                    validateOnChange: false,
                    value: 0
                },
                {
                    xtype: 'hiddenfield',
                    width: 150,
                    labelWidth: 180,
                    name: 'status',
                    validateOnChange: false,
                    value: 0
                },
                {
                    xtype: 'hiddenfield',
                    width: 150,
                    labelWidth: 180,
                    name: 'role',
                    validateOnChange: false,
                    value: 7
                }
            ],
            dockedItems: [
                {
                    xtype: 'container',
                    flex: 1,
                    dock: 'top',
                    focusOnToFront: false,
                    padding: 10,
                    style: '{border-color:#f0f}',
                    toFrontOnShow: false,
                    layout: {
                        type: 'vbox',
                        align: 'stretch'
                    },
                    items: [
                        {
                            xtype: 'textfield',
                            fieldLabel: 'Όνομα',
                            labelWidth: 180,
                            name: 'firstname',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false
                        },
                        {
                            xtype: 'textfield',
                            fieldLabel: 'Επώνυμο',
                            labelWidth: 180,
                            name: 'lastname',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false
                        },
                        {
                            xtype: 'textfield',
                            fieldLabel: 'Email',
                            labelWidth: 180,
                            name: 'email',
                            validateOnChange: false,
                            inputType: 'email',
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            vtype: 'email'
                        },
                        {
                            xtype: 'textfield',
                            fieldLabel: 'Ονομα Χρήστη',
                            labelWidth: 180,
                            name: 'username',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            vtype: 'username'
                        },
                        {
                            xtype: 'textfield',
                            fieldLabel: 'Κωδικός Χρήστη',
                            labelWidth: 180,
                            name: 'password',
                            validateOnChange: false,
                            inputType: 'password',
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            vtype: 'password'
                        },
                        {
                            xtype: 'textfield',
                            fieldLabel: 'Επιβεβαίωση Κωδικού',
                            labelWidth: 180,
                            name: 'password2',
                            validateOnChange: false,
                            inputType: 'password',
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            vtype: 'password'
                        },
                        {
                            xtype: 'textareafield',
                            fieldLabel: 'Οδός / Αριθμός',
                            labelWidth: 180,
                            name: 'address',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 100
                        },
                        {
                            xtype: 'textfield',
                            fieldLabel: 'Τ.Κ',
                            labelWidth: 180,
                            name: 'addrTk',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 5,
                            minLength: 5,
                            vtype: 'Number'
                        },
                        {
                            xtype: 'textfield',
                            fieldLabel: 'Σταθερό Τηλέφωνο',
                            labelWidth: 180,
                            name: 'phone',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 10,
                            vtype: 'telNumber'
                        },
                        {
                            xtype: 'textfield',
                            fieldLabel: 'Κινητό Τηλέφωνο',
                            labelWidth: 180,
                            name: 'mobile',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 10,
                            vtype: 'telNumber'
                        },
                        {
                            xtype: 'textfield',
                            fieldLabel: 'FAX',
                            labelWidth: 180,
                            name: 'fax',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 10,
                            vtype: 'telNumber'
                        },
                        {
                            xtype: 'checkboxfield',
                            flex: 1,
                            fieldLabel: 'Ειδοποιήσεις',
                            labelWidth: 180,
                            name: 'emailNotifEn',
                            validateOnChange: false,
                            validateOnBlur: false,
                            boxLabel: 'Να λαμβάνει ειδοποιήσεις με email',
                            inputValue: '1',
                            uncheckedValue: '2'
                        }
                    ]
                }
            ]
        },
        {
            xtype: 'form',
            id: 'companyuserprev',
            padding: 15,
            title: 'Δικαιώματα',
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
                    name: 'userId',
                    validateOnChange: false
                },
                {
                    xtype: 'container',
                    focusOnToFront: false,
                    padding: 10,
                    style: 'border-color:#fff',
                    toFrontOnShow: false,
                    items: [
                        {
                            xtype: 'checkboxgroup',
                            id: 'userprevbranches',
                            layout: 'anchor',
                            fieldLabel: 'Επιλογή Υποκαταστημάτων Παραχώρησης Δικαιωμάτων',
                            labelAlign: 'top',
                            msgTarget: 'under',
                            validateOnChange: false,
                            allowBlank: false,
                            blankText: 'Επιλέξτε τουλάχιστον ένα παράρτημα'
                        }
                    ]
                },
                {
                    xtype: 'container',
                    flex: 1,
                    focusOnToFront: false,
                    padding: 10,
                    style: 'border-color:#fff',
                    toFrontOnShow: false,
                    items: [
                        {
                            xtype: 'checkboxgroup',
                            id: 'userprevs',
                            layout: 'anchor',
                            fieldLabel: 'Επιλογή Υπηρεσιών',
                            labelAlign: 'top',
                            msgTarget: 'under',
                            validateOnChange: false,
                            allowBlank: false,
                            blankText: 'Επιλέξτε τουλάχιστον μια υπηρεσία'
                        }
                    ]
                }
            ]
        }
    ],
    listeners: {
        beforedestroy: 'onWindowBeforeDestroy',
        show: 'onWindowShow'
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
                var pD = date.split("-");
                return (pD[2]+"-"+pD[1]+"-"+pD[0]+"T00:00:00.000+0000");
            },
            dock: 'bottom',
            id: 'compusercreate_submit_toolbar',
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
                    maxWidth: 380,
                    padding: 10,
                    width: 190,
                    glyph: 'xf1d8@FontAwesome',
                    text: 'Δημιουργία Χρήστη',
                    listeners: {
                        click: {
                            fn: 'onSubmit_COMPANY_USER_CREATE',
                            scope: 'controller'
                        }
                    }
                },
                {
                    xtype: 'button',
                    hidden: true,
                    itemId: 'deactivatebutton',
                    maxWidth: 250,
                    padding: 10,
                    width: 220,
                    glyph: 'xf071@FontAwesome',
                    text: 'Απενεργοποίηση Λογαριασμού',
                    listeners: {
                        click: {
                            fn: 'onDeactivate_COMPANY_USER',
                            scope: 'controller'
                        }
                    }
                },
                {
                    xtype: 'button',
                    hidden: true,
                    itemId: 'savebutton',
                    maxWidth: 380,
                    padding: 10,
                    width: 190,
                    glyph: 'xf0c7@FontAwesome',
                    text: 'Ενημέρωση Δικαιωμάτων',
                    listeners: {
                        click: {
                            fn: 'onSave_COMPANY_USER_PREVS',
                            scope: 'controller'
                        }
                    }
                },
                {
                    xtype: 'button',
                    hidden: true,
                    itemId: 'activatebutton',
                    maxWidth: 250,
                    padding: 10,
                    width: 220,
                    glyph: 'xf00c@FontAwesome',
                    text: 'Ενεργοποίηση Λογαριασμού',
                    listeners: {
                        click: {
                            fn: 'onActivate_COMPANY_USER',
                            scope: 'controller'
                        }
                    }
                }
            ],
            listeners: {
                beforehide: 'onCompUser_save_submit_toolbarBeforeHide'
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

    onWindowBeforeDestroy: function(component, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var store = Ext.StoreMgr.lookup('company.CompanyAdmin.USERPREV');
        store.load({
            callback: function(records, operation, success) {
                if (success) {
                    var viewsub = Ext.create('MyApp.view.company.CompanyAdmin.CompanyUsersPanel');
                    center.add(viewsub);
                }
            }
        });

    },

    onWindowShow: function(component, eOpts) {
        component.mask("Παρακαλώ Περιμένετε...");
        var store = Ext.StoreMgr.lookup('company.CompanyAdmin.SERVICES');

        store.load({
            callback: function(records, operation, success) {
                if (success) {
                    for (i = 0; i < store.getCount(); i++) {
                        //if (i == 0 || i == 2 || i == 7 || i == 8 || i == 9) {
                            iv = store.getAt(i).get('abbr');
                            var grp = new Ext.form.Checkbox({
                                boxLabel: store.getAt(i).get('description'),
                                inputValue: iv,
                                name: 'userprevs',
                                id: 'ups' + iv,
                                validateOnBlur: false,
                                validateOnChange: false
                            });
                            Ext.getCmp('userprevs').add(grp);
                        //}

                    }
                    store = Ext.StoreMgr.lookup('company.COMP_BRANCES');

                    store.load({
                        callback: function(records, operation, success) {
                            if (success) {
                                var grp = new Ext.form.Checkbox({
                                    boxLabel: 'Επιλογή όλων',
                                    inputValue: -100,
                                    name: 'branches',
                                    id: 'upb-100',
                                    validateOnBlur: false,
                                    validateOnChange: false,
                                    checked :  false,
                                    listeners: {
                                        change : function(cmp, newVal, oldVal){
                                            if (newVal) {
                                                var group = Ext.getCmp('userprevbranches').items.items;
                                                for (var i=0; i< group.length; i++) {
                                                    group[i].setValue(true);
                                                }
                                            } else {
                                                var group = Ext.getCmp('userprevbranches').items.items;
                                                for (var i=0; i< group.length; i++) {
                                                    group[i].setValue(false);
                                                }
                                            }
                                        }
                                    }
                                });
                                Ext.getCmp('userprevbranches').add(grp);
                                for (i = 0; i < store.getCount(); i++) {
                                    iv = store.getAt(i).get('rgEbrBranchId');
                                    var grp = new Ext.form.Checkbox({
                                        boxLabel: store.getAt(i).get('rgEbrAddressStreetCombo'),
                                        inputValue: iv,
                                        name: 'branches',
                                        id: 'upb' + iv,
                                        validateOnBlur: false,
                                        validateOnChange: false,
                                        handler: function(checkbox, checked) {
                                            if (!checked)
                                            {
                                                Ext.getCmp('userprevbranches').items.items[0].suspendEvents();
                                                Ext.getCmp('userprevbranches').items.items[0].setValue(false);
                                                Ext.getCmp('userprevbranches').items.items[0].resumeEvents();
                                            }
                                        }
                                    });
                                    Ext.getCmp('userprevbranches').add(grp);

                                }
                                store = Ext.StoreMgr.lookup('company.CompanyAdmin.USERPREV');
                                if (component.down('form').getForm().findField('a_new_form').getValue() === "false") {

                                    var record = store.findRecord('userId', Ext.getCmp('companyuserprev').getForm().findField('userId').getValue());
                                    if (record.get('branchIds')!==null){
                                        var branches = record.get('branchIds').split(",");
                                        for (i = 1; i < branches.length-1; i++) {
                                            Ext.getCmp('upb'+branches[i]).setValue(true);
                                        }
                                    }
                                    if (record.get('privilagesIds')!==null){
                                        var services = record.get('privilagesIds').split(",");
                                        for (i = 0; i < services.length; i++) {
                                            Ext.getCmp('ups'+services[i]).setValue(true);
                                        }
                                    }
                                    if (record.get('active')==="1"){
                                        component.down('toolbar').getComponent('deactivatebutton').show();
                                    }
                                    else{
                                        component.down('toolbar').getComponent('activatebutton').show();
                                        component.down('toolbar').getComponent('savebutton').hide();
                                    }
                                    component.unmask();


                                } else {
                                    component.unmask();
                                }
                            }
                        }

                    });
                }
            }

        });
    },

    onCompUser_save_submit_toolbarBeforeHide: function(component, eOpts) {
        component.getComponent('submitbutton').destroy();
    }

});