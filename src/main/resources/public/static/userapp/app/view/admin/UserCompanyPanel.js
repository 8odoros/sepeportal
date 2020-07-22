/*
 * File: app/view/employee/ComplaintsPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.admin.UserCompanyPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.adminusercompanypanel',

    requires: [
        'MyApp.view.admin.UserCompanyPanelViewModel',
        'MyApp.view.admin.UserCompanyGrid',
        'Ext.grid.Panel'
    ],

    viewModel: {
        type: 'adminusercompanypanel'
    },
    id: 'users',

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'adminusercompanygrid',
            flex: 1
        }
    ]

});