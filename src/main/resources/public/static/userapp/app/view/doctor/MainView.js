/*
 * File: app/view/doctor/MainView.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.doctor.MainView', {
    extend: 'Ext.container.Viewport',
    alias: 'widget.doctormainview',

    requires: [
        'MyApp.view.doctor.MainViewViewModel',
        'Ext.panel.Panel',
        'Ext.button.Button'
    ],

    viewModel: {
        type: 'doctormainview'
    },
    id: 'doctormainView',
    itemId: 'doctormainView',
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
                            id: 'notifbut_doctor',
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
                    id: 'ieRegPanel',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    title: 'Αίτηση Εγγραφής στο Μητρώο ΙΕ',
                    dockedItems: [
                        /*{
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Προσθήκη Τίτλου Ιατρικής Ειδικότητας',
                            listeners: {
                                click: 'onTitleNewClick'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Προσθήκη Βεβαιώσεων Ιατρικών Συλλόγων',
                            listeners: {
                                click: 'onAssocNewClick'
                            }
                        },*/
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
                                click: 'onDoctorCompAnnStopClick'
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
                                click: 'onDoctorCompAnnStopClick'
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
                    title: 'Πρόγραμμα Επισκέψεων',
                    dockedItems: [
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
                    title: 'Καταχώρηση Υποδείξεων',
                    dockedItems: [
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
                    animCollapse: false,
                    collapsed: false,
                    hideCollapseTool: true,
                    overlapHeader: true,
                    listeners: {
                        afterrender: 'onEmptyPanelAfterRender'
                    }
                }
            ]
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
        var view=Ext.getCmp('doctormainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.doctor.NotificationsPanel');
        center.add(viewsub);
    },

    toStartPolling: function(component, eOpts) {

        var currentNewMessages=null;
        var oldNewMessages=0;
        var successCallback = function(resp, ops) {
            if (parseInt(resp.responseText)>0){
                currentNewMessages=parseInt(resp.responseText);
                Ext.getCmp('notifbut_doctor').setText(resp.responseText+" νέες");
                Ext.getCmp('notifbut_doctor').show();
                if (currentNewMessages>oldNewMessages){

                    //message to show
                    Ext.addon.MessagePop.msg("Προσοχή!", "Υπάρχουν μη αναγνωσμένες ενημερώσεις.", 5000);

                    //reloadgrid store if exist!
                    var view=Ext.getCmp('doctormainView');
                    var center = view.getComponent('contentPanel');
                    if (center.items.length==1 && center.items.get(0).items.length==2){ //will need fix at some point
                        grid=center.items.get(0).items.get(0);
                        if (grid.itemId==="doctornotificationgrid"){
                            grid.getView().store.reload();
                        }
                    }
                }
            }
            else{
                Ext.getCmp('notifbut_doctor').setText();
                Ext.getCmp('notifbut_doctor').hide();
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

    onAssocNewClick: function(button, e, eOpts) {
        var view=Ext.getCmp('doctormainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var storeasso = Ext.StoreManager.get('doctor.MEDICAL_ASSOC');

        storeasso.load({
            callback: function(records, operation, success){
                if(success){
                    var viewsub = Ext.create('MyApp.view.doctor.RegisteredAssociationsPanel');
                    center.add(viewsub);
                }
            }
        });

    },

    onTitleNewClick: function(button, e, eOpts) {
        var view=Ext.getCmp('doctormainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.doctor.DiplomasPanel');
        center.add(viewsub);
    },

    onRegRequestNewClick: function(button, e, eOpts) {
        var view=Ext.getCmp('doctormainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var storeasso = Ext.StoreManager.get('doctor.MEDICAL_ASSOC');

        storeasso.load({
          callback: function(records, operation, success){
            if(success){
                var emp_comp = Ext.create('widget.doctorregrequestform', {
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

                        var successAns = function(options, success, response) {
                            if (Ext.JSON.decode(response.responseText).success) {

                                var resp =Ext.JSON.decode(response.responseText);
                                if (resp.count > 0)
                                {
                                    emp_comp.down('form').getForm().findField('spec').setValue(resp.speciality);
                                    emp_comp.down('form').getForm().findField('file').hide();
                                    Ext.getCmp('docLabel').hide();
                                }
                            }
                        };

                        Ext.Ajax.request({
                            url : "/checkDoctorSpecialList",
                            params:{afm: resp.afm},
                            method : 'GET',
                            callback : successAns
                        });
                    }
                    else{
                        Ext.Msg.alert('Σφάλμα!', 'Υπάρχει πρόβλημα στην επισύναψη στοιχείων');
                    }
                };

                Ext.Ajax.request({
                    url : "/getDoctorInfo",
                    params:{mode: 0},
                    method : 'GET',
                    callback : successAns
                });
                
                emp_comp.down().getForm().findField('protNo').setValue("-");
                emp_comp.show();
            }
          }
        });
    },

    onRegRequestViewClick: function(button, e, eOpts) {
        var view=Ext.getCmp('doctormainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var storeasso = Ext.StoreManager.get('doctor.MEDICAL_ASSOC');

        storeasso.load({
          callback: function(records, operation, success){
            if(success){
                var viewsub = Ext.create('MyApp.view.doctor.RegRequestsPanel');
                center.add(viewsub);
            }
          }
        });
    },

    onCompAnnPendViewClick: function(button, e, eOpts) {
                var view=Ext.getCmp('doctormainView');
                var center = view.getComponent('contentPanel');
                center.removeAll();
                var viewsub = Ext.create('MyApp.view.doctor.PendingCompanyAnnsPanel');
                center.add(viewsub);
    },

    onCompAnnStopViewClick: function(button, e, eOpts) {
                var view=Ext.getCmp('doctormainView');
                var center = view.getComponent('contentPanel');
                center.removeAll();
                var viewsub = Ext.create('MyApp.view.doctor.ReplacedCompanyAnnsPanel');
                center.add(viewsub);
    },

    onDoctorCompAnnStopClick: function(button, e, eOpts) {
                var view=Ext.getCmp('doctormainView');
                var center = view.getComponent('contentPanel');
                center.removeAll();
                var viewsub = Ext.create('MyApp.view.doctor.ResignationsCompanyAnnsPanel');
                if (button.id == 'activeContractBtn')
                    Ext.getCmp('doctorCompany_Resignations').hide();
                else
                    Ext.getCmp('doctorActiveCompany_Anns').hide();
                center.add(viewsub);
    },

    onDiaryViewClick: function(button, e, eOpts) {
                var view=Ext.getCmp('doctormainView');
                var center = view.getComponent('contentPanel');
                center.removeAll();
                var viewsub = Ext.create('MyApp.view.doctor.CompanyDiariesPanel');
                Ext.getCmp('doctorCompany_Diary').store.clearData();
                center.add(viewsub);
    },

    onMonthViewClick: function(button, e, eOpts) {
                var view=Ext.getCmp('doctormainView');
                var center = view.getComponent('contentPanel');
                center.removeAll();
                var viewsub = Ext.create('MyApp.view.doctor.CompanyDiariesPanel');
                Ext.getCmp('doctorCompany_Diary').store.clearData();
                center.add(viewsub);
                var emp_comp = Ext.create('widget.doctordoctordiarymonthly', {
                        });
                emp_comp.show();
    },

    onCompanyBookClick: function(button, e, eOpts) {
                var view=Ext.getCmp('doctormainView');
                var center = view.getComponent('contentPanel');
                center.removeAll();
                var viewsub = Ext.create('MyApp.view.doctor.CompanyBooksPanel');
                center.add(viewsub);
    },

    onEmptyPanelAfterRender: function(component, eOpts) {
        /*var successAns = function(options, success, response) {
            if (Ext.JSON.decode(response.responseText).success) {

                var resp =Ext.JSON.decode(response.responseText);
                if (resp.count > 0)
                    Ext.getCmp('ieRegPanel').hide();
            }
        };

        Ext.Ajax.request({
            url : "/checkDoctorSpecialList",
            method : 'GET',
            callback : successAns
        });*/
        
        component.expand();
        component.header.hide();
    }

});