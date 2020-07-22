/*
 * File: app/view/anonymous/MainView.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.admin.MainView', {
    extend: 'Ext.container.Viewport',
    alias: 'widget.adminmainview',

    requires: [
        'MyApp.view.admin.MainViewViewModel',
        'Ext.panel.Panel',
        'Ext.button.Button'
    ],

    viewModel: {
        type: 'adminmainview'
    },
    id: 'adminmainView',
    itemId: 'adminmainView',
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
            items: [
                {
                    xtype: 'panel',
                    width: 150,
                    animCollapse: true,
                    collapsed: false,
                    title: 'Υπηρεσίες',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Διαχείριση Επιχειρήσεων',
                            listeners: {
                                click: 'onUserCompanyView'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Διαχείριση Εργαζομένων',
                            listeners: {
                                click: 'onUserEmployeeView'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Διαχείριση Τεχνικών Ασφαλείας',
                            listeners: {
                                click: 'onUserSafetyTechnicianView'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Διαχείριση Ιατρών Εργασίας',
                            listeners: {
                                click: 'onUserWorkplaceDoctorView'
                            }
                        }
                    ]
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

    onUserCompanyView: function(button, e, eOpts) {
        var view=Ext.getCmp('adminmainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.admin.UserCompanyPanel');
        center.add(viewsub);
        var store2 = Ext.StoreMgr.lookup( 'admin.USER_COMPANY_GRID' );
        store2.getProxy().setExtraParams({"username": "qwertyuiopasdfghjkl", "afm":-1, "ame":-1, "email":-1, "size": 100});
        store2.load({
            callback: function (records, operation, success) {
                console.log('OK');
            }
        });
    },

    onUserEmployeeView: function(button, e, eOpts) {
        var view=Ext.getCmp('adminmainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.admin.UserEmployeePanel');
        center.add(viewsub);
        var store2 = Ext.StoreMgr.lookup( 'admin.USER_EMPLOYEE_GRID' );
        store2.getProxy().setExtraParams({"username": "qwertyuiopasdfghjkl", "afm":-1, "email":-1, "size": 100});
        store2.load({
            callback: function (records, operation, success) {
                console.log('OK');
            }
        });
    },

    onUserSafetyTechnicianView: function(button, e, eOpts) {
        var view=Ext.getCmp('adminmainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.admin.UserSafetyTechnicianPanel');
        center.add(viewsub);
        var store2 = Ext.StoreMgr.lookup( 'admin.USER_SAFETY_TECHNICIAN_GRID' );
        store2.getProxy().setExtraParams({"username": "qwertyuiopasdfghjkl", "afm":-1, "email":-1, "size": 100});
        store2.load({
            callback: function (records, operation, success) {
                console.log('OK');
            }
        });
    },

    onUserWorkplaceDoctorView: function(button, e, eOpts) {
        var view=Ext.getCmp('adminmainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.admin.UserWorkplaceDoctorPanel');
        center.add(viewsub);
        var store2 = Ext.StoreMgr.lookup( 'admin.USER_WORKPLACE_DOCTOR_GRID' );
        store2.getProxy().setExtraParams({"username": "qwertyuiopasdfghjkl", "afm":-1, "email":-1, "size": 100});
        store2.load({
            callback: function (records, operation, success) {
                console.log('OK');
            }
        });
    }

});