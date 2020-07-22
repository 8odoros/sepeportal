/*
 * File: app/view/doctor/RegRequestsPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.doctor.RegRequestsPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.doctorregrequestspanel',

    requires: [
        'MyApp.view.doctor.RegRequestsPanelViewModel',
        'MyApp.view.doctor.RegRequestsGrid',
        'Ext.grid.Panel'
    ],

    viewModel: {
        type: 'doctorregrequestspanel'
    },
    id: 'complaints3',

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'doctorregrequestsgrid',
            flex: 1
        }
    ]

});