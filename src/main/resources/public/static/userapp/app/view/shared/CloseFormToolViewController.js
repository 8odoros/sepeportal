/*
 * File: app/view/shared/CloseFormToolViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.shared.CloseFormToolViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.mytool5',

    onCloseClick: function(tool, e, owner, eOpts) {
        var conffun = function(buttonText) {
            if (buttonText == "yes") {
                if (tool.toolOwner.controller.type == "anonymouscomplaintform") {
                    owner.destroy();
                    window.location = "/portal/login";
                }else {
                    owner.destroy();
                }
            }
            if (buttonText == "no") {
            }
        };

        var msb = Ext.MessageBox.confirm('Κλείσιμο φόρμας', 'Είστε σίγουροι ότι θέλετε να κλείσετε τη φόρμα;', conffun);

    }

});
