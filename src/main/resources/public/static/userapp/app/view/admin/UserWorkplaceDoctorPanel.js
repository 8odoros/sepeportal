/*
 * File: app/view/employee/ComplaintsPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.admin.UserWorkplaceDoctorPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.adminuserworkplacedoctorpanel',

    requires: [
        'MyApp.view.admin.UserWorkplaceDoctorPanelViewModel',
        'MyApp.view.admin.UserWorkplaceDoctorGrid',
        'Ext.grid.Panel'
    ],

    viewModel: {
        type: 'adminuserworkplacedoctorpanel'
    },
    id: 'users',

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'adminuserworkplacedoctorgrid',
            flex: 1
        }
    ]

});