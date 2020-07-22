/*
 * File: app/view/employee/ComplaintsPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.employee.ComplaintsPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.employeecomplaintspanel',

    requires: [
        'MyApp.view.employee.ComplaintsPanelViewModel',
        'MyApp.view.employee.ComplaintsGrid',
        'Ext.grid.Panel'
    ],

    viewModel: {
        type: 'employeecomplaintspanel'
    },
    id: 'complaints',

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'employeecomplaintsgrid',
            flex: 1
        }
    ]

});