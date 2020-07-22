/*
 * File: app/view/technician/MainView.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.technician.MainView', {
    extend: 'Ext.container.Viewport',
    alias: 'widget.technicianmainview',

    requires: [
        'MyApp.view.technician.MainViewViewModel',
        'Ext.panel.Panel',
        'Ext.button.Button',
        'Ext.form.field.ComboBox'
    ],

    viewModel: {
        type: 'technicianmainview'
    },
    id: 'technicianmainView',
    itemId: 'technicianmainView',
    layout: 'border',
    defaultListenerScope: true,

    items: [
        {
            xtype: 'panel',
            region: 'west',
            split: true,
            itemId: 'headerPanel',
            resizable: true,
            width: '20%',
            animCollapse: true,
            collapsed: false,
            collapsible: true,
            header: false,
            title: 'Υπηρεσίες',
            titleCollapse: false,
            layout: {
                type: 'accordion',
                animate: true
            },
            dockedItems: [
                {
                    xtype: 'container',
                    dock: 'top',
                    layout: {
                        type: 'hbox',
                        align: 'stretch'
                    },
                    items: [
                        {
                            xtype: 'button',
                            flex: 1,
                            height: 30,
                            itemId: 'mybutton',
                            glyph: 'xf01c@FontAwesome',
                            text: 'Ενημερώσεις',
                            listeners: {
                                click: 'onNotificationView',
                                afterrender: 'toStartPolling'
                            }
                        },
                        {
                            xtype: 'button',
                            border: 0,
                            disabled: true,
                            hidden: true,
                            id: 'notifbut_technician',
                            style: {
                                'background-color': 'red'
                            }
                        }
                    ]
                }
            ],
            items: [
                {
                    xtype: 'panel',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    title: 'Αίτηση Εγγραφής στο Μητρώο TA',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Προσθήκη Τίτλων Σπουδών',
                            listeners: {
                                click: 'onNewDiplomaClick'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα Αίτηση Εγγραφής',
                            listeners: {
                                click: 'onRegRequestNewClick'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf187@FontAwesome',
                            text: 'Προβολή Αιτήσεων - Απαντήσεων',
                            listeners: {
                                click: 'onRegRequestViewClick'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    height: 150,
                    minHeight: 150,
                    animCollapse: true,
                    collapsed: true,
                    title: 'Διαχείριση Αναγγελιών',
                    dockedItems: [
                        {
                            xtype: 'combobox',
                            dock: 'top',
                            margin: 10,
                            fieldLabel: 'Επιλέξτε τον τύπο αναγγελιών που θέλετε να διαχειριστείτε',
                            labelAlign: 'top',
                            labelWidth: 160,
                            name: 'taStatus',
                            validateOnChange: false,
                            value: 2,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            editable: false,
                            maxLength: 200,
                            autoLoadOnValue: true,
                            displayField: 'name',
                            valueField: 'abbr',
                            bind: {
                                store: '{TA_STATUS}'
                            },
                            listeners: {
                                change: 'onComboboxChange'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf145@FontAwesome',
                            text: 'Αναγγελίες σε Εκκρεμότητα',
                            listeners: {
                                click: 'onCompAnnPendViewClick'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf145@FontAwesome',
                            text: 'Αντικατάσταση Προβολή - Απάντηση',
                            listeners: {
                                click: 'onCompAnnStopViewClick'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Γνωστοποίηση Παύσης Καθηκόντων',
                            listeners: {
                                click: 'onTechCompAnnStopClick'
                            }
                        },
                        {
                            xtype: 'button',
                            id: 'activeContractBtn',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf187@FontAwesome',
                            text: 'Επιχειρήσεις με Ενεργή Σύμβαση',
                            listeners: {
                                click: 'onTechCompAnnStopClick'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    title: 'Πρόγραμμα Επισκέψεων',
                    dockedItems: [
                        {
                            xtype: 'combobox',
                            dock: 'top',
                            margin: 10,
                            fieldLabel: 'Επιλέξτε τον τύπο του προγράμματος που θέλετε να προβάλετε',
                            labelAlign: 'top',
                            labelWidth: 160,
                            name: 'taStatus',
                            validateOnChange: false,
                            value: 2,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            editable: false,
                            maxLength: 200,
                            autoLoadOnValue: true,
                            displayField: 'name',
                            valueField: 'abbr',
                            bind: {
                                store: '{TA_STATUS}'
                            },
                            listeners: {
                                change: 'onComboboxChange1'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf073@FontAwesome',
                            text: 'Προβολή Προγράμματος',
                            listeners: {
                                click: 'onDiaryViewClick'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf073@FontAwesome',
                            text: 'Συνολικό Πρόγραμμα',
                            listeners: {
                                click: 'onMonthViewClick'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    height: 150,
                    minHeight: 150,
                    animCollapse: true,
                    collapsed: true,
                    itemId: 'techDisabled1',
                    //disabled: true,
                    title: 'Καταχώρηση Υποδείξεων',
                    dockedItems: [
                        {
                            xtype: 'combobox',
                            dock: 'top',
                            margin: 10,
                            fieldLabel: 'Επιλέξτε τον τύπο εργασίας που αφορούν οι υποδείξεις',
                            labelAlign: 'top',
                            labelWidth: 160,
                            name: 'taStatus',
                            validateOnChange: false,
                            value: 2,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            editable: false,
                            maxLength: 200,
                            autoLoadOnValue: true,
                            displayField: 'name',
                            valueField: 'abbr',
                            bind: {
                                store: '{TA_STATUS}'
                            },
                            listeners: {
                                change: 'onComboboxChange2'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Προσθήκη και Προβολή Υποδείξεων',
                            listeners: {
                                click: 'onCompanyBookClick'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    itemId: 'techDisabled2',
                    //disabled: true,
                    title: 'Καταγγελίες',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf187@FontAwesome',
                            text: 'Προβολή Καταγγελιών',
                            listeners: {
                                click: 'onComplaintView'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα Καταγγελία',
                            listeners: {
                                click: 'onComplaintNew'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    animCollapse: false,
                    collapsed: false,
                    hideCollapseTool: true,
                    overlapHeader: true,
                    listeners: {
                        afterrender: 'onEmptyPanelAfterRender'
                    }
                }
            ],
        },
        {
            xtype: 'panel',
            region: 'north',
            height: 62,
            bodyStyle: {
                'background-color': '#cecece'
            }
        },
        {
            xtype: 'panel',
            region: 'south',
            height: 10,
            itemId: 'footerPanel',
            bodyStyle: {
                'background-color': '#cecece'
            }
        },
        {
            xtype: 'panel',
            region: 'center',
            split: true,
            itemId: 'contentPanel',
            layout: 'fit',
            collapsible: false
        }
    ],

    onNotificationView: function(button, e, eOpts) {
        var view=Ext.getCmp('technicianmainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.technician.NotificationsPanel');
        center.add(viewsub);
    },

    toStartPolling: function(component, eOpts) {
        var currentNewMessages=null;
        var oldNewMessages=0;
        var successCallback = function(resp, ops) {
            if (parseInt(resp.responseText)>0){
                currentNewMessages=parseInt(resp.responseText);
                Ext.getCmp('notifbut_technician').setText(resp.responseText+" νέες");
                Ext.getCmp('notifbut_technician').show();
                if (currentNewMessages>oldNewMessages){

                    //message to show
                    Ext.addon.MessagePop.msg("Προσοχή!", "Υπάρχουν μη αναγνωσμένες ενημερώσεις.", 5000);

                    //reloadgrid store if exist!
                    var view=Ext.getCmp('technicianmainView');
                    var center = view.getComponent('contentPanel');
                    if (center.items.length==1 && center.items.get(0).items.length==2){ //will need fix at some point
                        grid=center.items.get(0).items.get(0);
                        if (grid.itemId==="techniciannotificationgrid"){
                            grid.getView().store.reload();
                        }
                    }
                }
            }
            else{
                Ext.getCmp('notifbut_technician').setText();
                Ext.getCmp('notifbut_technician').hide();
            }
            oldNewMessages=currentNewMessages;
        };

        var poll = new Ext.direct.PollingProvider({
           type: 'polling',
           url: function () {
              Ext.Ajax.request({
                 url: 'tNotificationsAccountEntities/search/countByViewed?viewed=0',
                 qualifier: 'Keep Alive',
                 success: successCallback,
                 failure: function () {
                 }
              });
           },
           interval: 15000
        });


        Ext.Direct.addProvider(poll);

        // poll.disconnect();

    },

    onNewDiplomaClick: function(button, e, eOpts) {
        var view=Ext.getCmp('technicianmainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.technician.DiplomasPanel');
        center.add(viewsub);
    },

    onRegRequestNewClick: function(button, e, eOpts) {
        var view=Ext.getCmp('technicianmainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var emp_comp = Ext.create('widget.technicianregrequestform', {
        });

        var successAns = function(options, success, response) {
            if (Ext.JSON.decode(response.responseText).success) {

                var resp =Ext.JSON.decode(response.responseText);
                emp_comp.down('form').getForm().findField('firstname').setValue(resp.firstname);
                emp_comp.down('form').getForm().findField('lastname').setValue(resp.lastname);
                emp_comp.down('form').getForm().findField('fatherrname').setValue(resp.fatherrname);
                emp_comp.down('form').getForm().findField('afm').setValue(resp.afm);
                emp_comp.down('form').getForm().findField('amka').setValue(resp.amka);
                emp_comp.down('form').getForm().findField('cardNumber').setValue(resp.cardNumber);
            }
            else{
                Ext.Msg.alert('Σφάλμα!', 'Υπάρχει πρόβλημα στην επισύναψη στοιχείων');
            }
        };

        Ext.Ajax.request({
            url : "/getTechnicianInfo",
            params:{mode: 0},
            method : 'GET',
            callback : successAns
        });

        emp_comp.down().getForm().findField('protNo').setValue("-");
        emp_comp.show();
    },

    onRegRequestViewClick: function(button, e, eOpts) {
        var view=Ext.getCmp('technicianmainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.technician.RegRequestsPanel');
        center.add(viewsub);
    },

    onComboboxChange: function(field, newValue, oldValue, eOpts) {
        if(newValue!==oldValue){
            field.up('panel').next().down('combobox').setValue(newValue);
            field.up('panel').next().next().down('combobox').setValue(newValue);
        }
    },

    onCompAnnPendViewClick: function(button, e, eOpts) {
                var view=Ext.getCmp('technicianmainView');
                var center = view.getComponent('contentPanel');
                center.removeAll();
                if(button.up('panel').down('combobox').getValue()===2)
                    var viewsub = Ext.create('MyApp.view.technician.PendingCompanyAnnsPanel');
                else if(button.up('panel').down('combobox').getValue()===1)
                    var viewsub = Ext.create('MyApp.view.technician.ship.PendingCompanyAnnsPanel');
                center.add(viewsub);
    },

    onCompAnnStopViewClick: function(button, e, eOpts) {
                var view=Ext.getCmp('technicianmainView');
                var center = view.getComponent('contentPanel');
                center.removeAll();
                if(button.up('panel').down('combobox').getValue()===2)
                    var viewsub = Ext.create('MyApp.view.technician.ReplacedCompanyAnnsPanel');
                else if(button.up('panel').down('combobox').getValue()===1)
                    var viewsub = Ext.create('MyApp.view.technician.ship.ReplacedCompanyAnnsPanel');
                center.add(viewsub);
    },

    onTechCompAnnStopClick: function(button, e, eOpts) {
                var view=Ext.getCmp('technicianmainView');
                var center = view.getComponent('contentPanel');
                center.removeAll();
                if(button.up('panel').down('combobox').getValue()===2) {
                    if (button.id == 'activeContractBtn'){
                        var viewsub = Ext.create('MyApp.view.technician.ActiveCompanyAnnsPanel');
                    }else {
                        var viewsub = Ext.create('MyApp.view.technician.ResignationsCompanyAnnsPanel');
                    }
                }
                else if(button.up('panel').down('combobox').getValue()===1)
                            var viewsub = Ext.create('MyApp.view.technician.ship.ResignationsCompanyAnnsPanel');

                if (button.id != 'activeContractBtn')
                    Ext.getCmp('technicianActiveCompany_Anns').hide();
                center.add(viewsub);
    },

    onComboboxChange1: function(field, newValue, oldValue, eOpts) {
        if(newValue!==oldValue){
            field.up('panel').prev().down('combobox').setValue(newValue);
            field.up('panel').next().down('combobox').setValue(newValue);
        }
    },

    onDiaryViewClick: function(button, e, eOpts) {
                var view=Ext.getCmp('technicianmainView');
                var center = view.getComponent('contentPanel');
                center.removeAll();
                if(button.up('panel').down('combobox').getValue()===2)
                    var viewsub = Ext.create('MyApp.view.technician.CompanyDiariesPanel');
                else if(button.up('panel').down('combobox').getValue()===1)
                    var viewsub = Ext.create('MyApp.view.technician.ship.CompanyDiariesPanel');
                center.add(viewsub);
    },

    onMonthViewClick: function(button, e, eOpts) {
                var view=Ext.getCmp('technicianmainView');
                var center = view.getComponent('contentPanel');
                center.removeAll();
                if(button.up('panel').down('combobox').getValue()===2){
                       var viewsub = Ext.create('MyApp.view.technician.CompanyDiariesPanel');
                        var emp_comp = Ext.create('widget.techniciantechniciandiarymonthly', {});
                }
                else if(button.up('panel').down('combobox').getValue()===1){
                       var viewsub = Ext.create('MyApp.view.technician.ship.CompanyDiariesPanel');
                        var emp_comp = Ext.create('widget.technicianshiptechniciandiarymonthly', {});
                }
                center.add(viewsub);

                emp_comp.show();
    },

    onComboboxChange2: function(field, newValue, oldValue, eOpts) {
        if(newValue!==oldValue){
            field.up('panel').prev().down('combobox').setValue(newValue);
            field.up('panel').prev().prev().down('combobox').setValue(newValue);
        }
    },

    onCompanyBookClick: function(button, e, eOpts) {
                var view=Ext.getCmp('technicianmainView');
                var center = view.getComponent('contentPanel');
                center.removeAll();
                if(button.up('panel').down('combobox').getValue()===2)
                            var viewsub = Ext.create('MyApp.view.technician.CompanyBooksPanel');
                else if(button.up('panel').down('combobox').getValue()===1)
                            var viewsub = Ext.create('MyApp.view.technician.ship.CompanyBooksPanel');
                center.add(viewsub);
    },

    onEmptyPanelAfterRender: function(component, eOpts) {
        component.expand();
        component.header.hide();
    },

});