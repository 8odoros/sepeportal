/*
 * File: app/view/technician/RegRequestsPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.technician.RegRequestsPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.technicianregrequestspanel',

    requires: [
        'MyApp.view.technician.RegRequestsPanelViewModel',
        'MyApp.view.technician.RegRequestsGrid',
        'Ext.grid.Panel'
    ],

    viewModel: {
        type: 'technicianregrequestspanel'
    },

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'technicianregrequestsgrid',
            flex: 1
        }
    ]

});