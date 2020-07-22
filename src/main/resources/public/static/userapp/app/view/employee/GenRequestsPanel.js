/*
 * File: app/view/employee/GenRequestsPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.employee.GenRequestsPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.employeegenrequestspanel',

    requires: [
        'MyApp.view.employee.GenRequestsPanelViewModel',
        'MyApp.view.employee.GenRequestsGrid',
        'Ext.grid.Panel'
    ],

    viewModel: {
        type: 'employeegenrequestspanel'
    },
    id: 'complaints2',

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'employeegenrequestsgrid',
            flex: 1
        }
    ]

});