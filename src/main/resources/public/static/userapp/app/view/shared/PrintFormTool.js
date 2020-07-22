/*
 * File: app/view/shared/PrintFormTool.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.shared.PrintFormTool', {
    extend: 'Ext.panel.Tool',
    alias: 'widget.sharedprintformtool',

    requires: [
        'MyApp.view.shared.PrintFormToolViewModel',
        'MyApp.view.shared.PrintFormToolViewController'
    ],

    controller: 'mytool3',
    viewModel: {
        type: 'mytool3'
    },
    type: 'print',

    listeners: {
        click: 'onPrintClick'
    }

});