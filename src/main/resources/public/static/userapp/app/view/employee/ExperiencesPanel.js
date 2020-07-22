/*
 * File: app/view/employee/ExperiencesPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.employee.ExperiencesPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.employeeexperiencespanel',

    requires: [
        'MyApp.view.employee.ExperiencesPanelViewModel',
        'MyApp.view.employee.ExperiencesGrid',
        'Ext.grid.Panel'
    ],

    viewModel: {
        type: 'employeeexperiencespanel'
    },
    id: 'experience',

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'employeeexperiencesgrid',
            flex: 1
        }
    ]

});