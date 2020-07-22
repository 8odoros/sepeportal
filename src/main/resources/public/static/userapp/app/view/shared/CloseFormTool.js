/*
 * File: app/view/shared/CloseFormTool.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.shared.CloseFormTool', {
    extend: 'Ext.panel.Tool',
    alias: 'widget.sharedcloseformtool',

    requires: [
        'MyApp.view.shared.CloseFormToolViewModel',
        'MyApp.view.shared.CloseFormToolViewController'
    ],

    controller: 'mytool5',
    viewModel: {
        type: 'mytool5'
    },
    type: 'close',

    listeners: {
        click: 'onCloseClick'
    }

});