/*
 * File: app/view/employee/ComplaintsPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.admin.UserSafetyTechnicianPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.adminusersafetytechnicianpanel',

    requires: [
        'MyApp.view.admin.UserSafetyTechnicianPanelViewModel',
        'MyApp.view.admin.UserSafetyTechnicianGrid',
        'Ext.grid.Panel'
    ],

    viewModel: {
        type: 'adminusersafetytechnicianpanel'
    },
    id: 'users',

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'adminusersafetytechniciangrid',
            flex: 1
        }
    ]

});