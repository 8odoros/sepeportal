/*
 * File: app/view/anonymous/MainView.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.anonymous.MainView', {
    extend: 'Ext.container.Viewport',
    alias: 'widget.anonymousmainview',

    requires: [
        'MyApp.view.anonymous.MainViewViewModel',
        'Ext.panel.Panel',
        'Ext.button.Button'
    ],

    viewModel: {
        type: 'anonymousmainview'
    },
    id: 'anonymousmainView',
    itemId: 'anonymousmainView',
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
                    title: 'Καταγγελίες',
                    dockedItems: [
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

    onComplaintNew: function(button, e, eOpts) {
        var view=Ext.getCmp('anonymousmainView');
                var center = view.getComponent('contentPanel');
                center.removeAll();
        var emp_comp = Ext.create('widget.anonymouscomplaintform', {
        });
        emp_comp.down().getForm().findField('protNo').setValue("-");
        emp_comp.show();
    }

});