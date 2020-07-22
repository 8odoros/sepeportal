/*
 * File: app/view/employee/MainView.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.employee.MainView', {
    extend: 'Ext.container.Viewport',
    alias: 'widget.employeemainview',

    requires: [
        'MyApp.view.employee.MainViewViewModel',
        'Ext.panel.Panel',
        'Ext.button.Button'
    ],

    viewModel: {
        type: 'employeemainview'
    },
    id: 'employeemainView',
    itemId: 'employeemainView',
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
                            itemId: 'mybutton11',
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
                            id: 'notifbut_employee',
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
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
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
                    animCollapse: true,
                    collapsed: true,
                    itemId: 'empDisabled1',
                    //disabled: true,
                    title: 'Εργατικές Διαφορές',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf187@FontAwesome',
                            text: 'Προβολή Αιτήσεων',
                            listeners: {
                                click: 'onDisputeView'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα Αίτηση Συζήτησης Εργ. Διαφοράς',
                            listeners: {
                                click: 'onDisputeNew'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    animCollapse: true,
                    collapsed: true,
                    itemId: 'empDisabled2',
                    //disabled: true,
                    title: 'Βεβαιώσεις Προϋπηρεσίας',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf187@FontAwesome',
                            text: 'Προβολή Αιτήσεων',
                            listeners: {
                                click: 'onExperienceView'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα Αίτηση Χορήγησης Προϋπηρεσίας',
                            listeners: {
                                click: 'onExperienceNew'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    animCollapse: true,
                    collapsed: true,
                    itemId: 'empDisabled3',
                    //disabled: true,
                    title: 'Αίτηση για πρόσβαση σε έγγραφα',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf187@FontAwesome',
                            text: 'Προβολή Αιτήσεων',
                            listeners: {
                                click: 'onGenRequestView'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα Αίτηση Χορήγησης Αντιγράφων ΣΕΠΕ',
                            listeners: {
                                click: 'onGenRequestNew'
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
            id: 'contentPanel',
            itemId: 'contentPanel',
            layout: 'fit',
            collapsible: false
        }
    ],

    onNotificationView: function(button, e, eOpts) {
        var view=Ext.getCmp('employeemainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.employee.NotificationsPanel');
        center.add(viewsub);
    },

    toStartPolling: function(component, eOpts) {
        var currentNewMessages=null;
        var oldNewMessages=0;
        var successCallback = function(resp, ops) {
            if (parseInt(resp.responseText)>0){
                currentNewMessages=parseInt(resp.responseText);
                Ext.getCmp('notifbut_employee').setText(resp.responseText+" νέες");
                Ext.getCmp('notifbut_employee').show();
                if (currentNewMessages>oldNewMessages){

                    //message to show
                    Ext.addon.MessagePop.msg("Προσοχή!", "Υπάρχουν μη αναγνωσμένες ενημερώσεις.", 5000);

                    //reloadgrid store if exist!
                    var view=Ext.getCmp('employeemainView');
                    var center = view.getComponent('contentPanel');
                    if (center.items.length==1 && center.items.get(0).items.length==2){ //will need fix at some point
                        grid=center.items.get(0).items.get(0);
                        if (grid.itemId==="employeenotificationgrid"){
                            grid.getView().store.reload();
                        }
                    }
                }
            }
            else{
                Ext.getCmp('notifbut_employee').setText();
                Ext.getCmp('notifbut_employee').hide();
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
           interval: 60000
        });


        Ext.Direct.addProvider(poll);

        // poll.disconnect();

    },

    onComplaintView: function(button, e, eOpts) {
        var view=Ext.getCmp('employeemainView');
        //console.log(view);
        var center = view.getComponent('contentPanel');
        //var center2 = Ext.ComponentQuery.query('centerPanel');
        //console.log(center2);
        //var center = this.getView();
        //console.log(center);
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.employee.ComplaintsPanel');
        //console.log(viewsub);
        center.add(viewsub);
        //console.log(Ext.getCmp('submenu').show());
        //console.log(Ext.getCmp('complains').show());
    },

    onComplaintNew: function(button, e, eOpts) {
        var view=Ext.getCmp('employeemainView');
                //console.log(view);
                var center = view.getComponent('contentPanel');
                //var center2 = Ext.ComponentQuery.query('centerPanel');
                //console.log(center2);
                //var center = this.getView();
                //console.log(center);
                center.removeAll();
        var emp_comp = Ext.create('widget.employeecomplaintform', {

           // emp_comp.down()
        });

        var successAns = function(options, success, response) {
            if (Ext.JSON.decode(response.responseText).success) {

                var resp =Ext.JSON.decode(response.responseText);
                emp_comp.down('form').getForm().findField('empFirstname').setValue(resp.empFirstname);
                emp_comp.down('form').getForm().findField('empLastname').setValue(resp.empLastname);
            }
            else{
                Ext.Msg.alert('Σφάλμα!', 'Υπάρχει πρόβλημα στην επισύναψη στοιχείων');
            }
        };

        Ext.Ajax.request({
            url : "/getEmployeeInfo",
            method : 'GET',
            callback : successAns
        });

        //debugger;
        emp_comp.down().getForm().findField('protNo').setValue("-");
        // Show window
        emp_comp.show();


           //values.complNo="-";
            //values.protNo="-";
    },

    onDisputeView: function(button, e, eOpts) {
        var view=Ext.getCmp('employeemainView');
        //console.log(view);
        var center = view.getComponent('contentPanel');
        //var center2 = Ext.ComponentQuery.query('centerPanel');
        //console.log(center2);
        //var center = this.getView();
        //console.log(center);
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.employee.DisputesPanel');
        //console.log(viewsub);
        center.add(viewsub);
        //console.log(Ext.getCmp('submenu').show());
        //console.log(Ext.getCmp('complains').show());
    },

    onDisputeNew: function(button, e, eOpts) {
        Ext.getBody().mask("Παρακαλώ Περιμένετε...");

        var store = Ext.StoreMgr.lookup( 'employee.DISPUTE_REASONS' );
        store.load( { callback : function(records, operation, success) {
               var view=Ext.getCmp('employeemainView');

        //console.log(view);
        var center = view.getComponent('contentPanel');
        //var center2 = Ext.ComponentQuery.query('centerPanel');
        //console.log(center2);
        //var center = this.getView();
        //console.log(center);
        center.removeAll();
        var emp_disp = Ext.create('widget.employeedisputeform', {

            // emp_comp.down()
        });

            var successAns = function(options, success, response) {
                if (Ext.JSON.decode(response.responseText).success) {

                    var resp =Ext.JSON.decode(response.responseText);
                    emp_disp.down('form').getForm().findField('empFirstname').setValue(resp.empFirstname);
                    emp_disp.down('form').getForm().findField('empLastname').setValue(resp.empLastname);
                    emp_disp.down('form').getForm().findField('empAmka').setValue(resp.empAmka);
                    emp_disp.down('form').getForm().findField('empCardNumber').setValue(resp.empCardNumber);
                    emp_disp.down('form').getForm().findField('empAfm').setValue(resp.empAfm);
                    emp_disp.down('form').getForm().findField('userId').setValue(resp.userId);
                }
                else{
                    Ext.Msg.alert('Σφάλμα!', 'Υπάρχει πρόβλημα στην επισύναψη στοιχείων');
                }
            };

            Ext.Ajax.request({
                url : "/getEmployeeInfo",
                method : 'GET',
                callback : successAns
            });
        //debugger;
        emp_disp.down().getForm().findField('protNo').setValue(0);
        // Show window



        emp_disp.show();
            }
        });

        Ext.getBody().unmask();

    },

    onExperienceView: function(button, e, eOpts) {
        var view=Ext.getCmp('employeemainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.employee.ExperiencesPanel');
        center.add(viewsub);
    },

    onExperienceNew: function(button, e, eOpts) {
        Ext.getBody().mask("Παρακαλώ Περιμένετε...");


               var view=Ext.getCmp('employeemainView');


        var center = view.getComponent('contentPanel');
        center.removeAll();
        var emp_disp = Ext.create('widget.employeeexperienceform', {
        });

        var successAns = function(options, success, response) {
            if (Ext.JSON.decode(response.responseText).success) {

                var resp =Ext.JSON.decode(response.responseText);
                emp_disp.down('form').getForm().findField('empFirstname').setValue(resp.empFirstname);
                emp_disp.down('form').getForm().findField('empLastname').setValue(resp.empLastname);
                emp_disp.down('form').getForm().findField('empAmka').setValue(resp.empAmka);
                emp_disp.down('form').getForm().findField('empCardNumber').setValue(resp.empCardNumber);
                emp_disp.down('form').getForm().findField('empAfm').setValue(resp.empAfm);
            }
            else{
                Ext.Msg.alert('Σφάλμα!', 'Υπάρχει πρόβλημα στην επισύναψη στοιχείων');
            }
        };

        Ext.Ajax.request({
            url : "/getEmployeeInfo",
            method : 'GET',
            callback : successAns
        });

        emp_disp.down().getForm().findField('protNo').setValue(0);

        emp_disp.show();

        Ext.getBody().unmask();

    },

    onGenRequestView: function(button, e, eOpts) {
        var view=Ext.getCmp('employeemainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.employee.GenRequestsPanel');
        center.add(viewsub);
    },

    onGenRequestNew: function(button, e, eOpts) {
        Ext.getBody().mask("Παρακαλώ Περιμένετε...");


               var view=Ext.getCmp('employeemainView');


        var center = view.getComponent('contentPanel');
        center.removeAll();
        var emp_disp = Ext.create('widget.employeegenrequestform', {
        });

        var successAns = function(options, success, response) {
            if (Ext.JSON.decode(response.responseText).success) {

                var resp =Ext.JSON.decode(response.responseText);
                emp_disp.down('form').getForm().findField('empFirstname').setValue(resp.empFirstname);
                emp_disp.down('form').getForm().findField('empLastname').setValue(resp.empLastname);
                emp_disp.down('form').getForm().findField('empAmka').setValue(resp.empAmka);
                emp_disp.down('form').getForm().findField('empCardNumber').setValue(resp.empCardNumber);
                emp_disp.down('form').getForm().findField('empAfm').setValue(resp.empAfm);
                emp_disp.down('form').getForm().findField('empFathername').setValue(resp.empFathername);
                emp_disp.down('form').getForm().findField('empMothername').setValue(resp.empMothername);
            }
            else{
                Ext.Msg.alert('Σφάλμα!', 'Υπάρχει πρόβλημα στην επισύναψη στοιχείων');
            }
        };

        Ext.Ajax.request({
            url : "/getEmployeeInfo",
            method : 'GET',
            callback : successAns
        });

        emp_disp.down().getForm().findField('protNo').setValue(0);

        emp_disp.show();

        Ext.getBody().unmask();

    },

    onEmptyPanelAfterRender: function(component, eOpts) {
        component.expand();
        component.header.hide();
    }

});