/*
 * File: app/view/employee/DisputesPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.employee.DisputesPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.employeedisputespanel',

    requires: [
        'MyApp.view.employee.DisputesPanelViewModel',
        'MyApp.view.employee.DisputesGrid',
        'Ext.grid.Panel'
    ],

    viewModel: {
        type: 'employeedisputespanel'
    },
    id: 'complaints1',

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'employeedisputesgrid',
            flex: 1
        }
    ]

});