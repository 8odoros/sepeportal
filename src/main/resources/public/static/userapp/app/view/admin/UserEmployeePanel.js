/*
 * File: app/view/employee/ComplaintsPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.admin.UserEmployeePanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.adminuseremployeepanel',

    requires: [
        'MyApp.view.admin.UserEmployeePanelViewModel',
        'MyApp.view.admin.UserEmployeeGrid',
        'Ext.grid.Panel'
    ],

    viewModel: {
        type: 'adminuseremployeepanel'
    },
    id: 'users',

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'adminuseremployeegrid',
            flex: 1
        }
    ]

});