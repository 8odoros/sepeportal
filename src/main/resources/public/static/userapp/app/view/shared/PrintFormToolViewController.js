/*
 * File: app/view/shared/PrintFormToolViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.shared.PrintFormToolViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.mytool3',

    onPrintClick: function (tool, e, owner, eOpts) {
        if (navigator.userAgent.indexOf("Firefox") != -1)
            Ext.addon.MessagePop.msg("", "Γίνεται λήψη της φόρμας. Παρακαλώ περιμένετε...", 6000);
        var pnl = owner.down('form');
        if (!pnl) {
            pnl = this;
        }

        var opt = {
            margin: [2.7, 5, 2.7, 5],
            filename: owner.title + '.pdf',
            image: {type: 'jpeg', quality: 0.88},
            html2canvas: {scale: 1.5}
        }

        var iFrameId = "printerFrame";
        var printFrame = Ext.get(iFrameId);


        if (printFrame === null) {
            printFrame = Ext.getBody().appendChild({
                id: iFrameId,
                tag: 'iframe',
                cls: 'x-hidden',
                style: {
                    display: "none"
                }
            });
        }

        var cw = printFrame.dom.contentWindow;

        var stylesheets = "";
        var markup;
        // instantiate application stylesheets in the hidden iframe


        var printTask = new Ext.util.DelayedTask(function () {

            // print the iframe
            cw.print();

            // destroy the iframe
            Ext.fly(iFrameId).destroy();

        });

        var title = "<h4>" + owner.title + "</h4>";
        var strTask = new Ext.util.DelayedTask(function () {

            if (navigator.userAgent.indexOf("Firefox") != -1) {
                var str = Ext.String.format('<!DOCTYPE html><html><meta charset="UTF-8"><head>{0}</head><body>{2}<br>{1}</body></html>', stylesheets, markup, title);
                html2pdf(str, opt);
                //alert('Firefox');
            }
            else {
                var str = Ext.String.format('<!DOCTYPE html><html><meta charset="UTF-8"><head>{0}</head><body>{2}<br><br>{1}</body></html>', stylesheets, markup, title);
                cw.document.open();
                cw.document.write(str);
                cw.document.close();

                printTask.delay(1000);

                cw.document.getElementsByTagName('DIV')[0].removeAttribute('style');

            }
        });

        var markUpTask = new Ext.util.DelayedTask(function () {
            pnl.getForm().getFields().each(function (field) {
                if (field.xtype === "combobox" || field.xtype === "textfield" || field.xtype === "numberfield" || field.xtype === "datefield") {
                    if (field.getEl())
                        field.getEl().dom.childNodes.item(1).childNodes.item(0).childNodes.item(0).childNodes.item(0).setAttribute('value', field.getRawValue());
                }
                else if ((field.xtype === "radiofield" || field.xtype === "checkboxfield") && field.getValue()) {
                    if (!field.getEl().dom.innerText.indexOf("✔") == 0)
                        field.getEl().dom.innerText = "✔" + field.getEl().dom.innerText;
                }
                else if (field.xtype === "textareafield") {
                    field.getEl().dom.childNodes.item(1).childNodes.item(0).childNodes.item(0).childNodes.item(0).innerHTML = field.getRawValue();
                }

            });
            markup = pnl.getEl().dom.innerHTML;

            while (markup.indexOf('overflow: auto;') >= 0) {
                markup = markup.replace('overflow: auto;', '');
            }
            strTask.delay(2000);
        });


        var styleSheetConcatTask = new Ext.util.DelayedTask(function () {

            // various style overrides
            stylesheets += ''.concat(
                "<style>",
                ".x-panel-body {overflow: visible !important;}",
                ".x-form-item {page-break-inside: avoid !important;}",
                ".x-fieldset-header {page-break-inside: avoid !important;}",
                "div {overflow: visible !important;}",
                "</style>"
            );

            markUpTask.delay(1000);
        });


        var styleSheetCreateTask = new Ext.util.DelayedTask(function () {


            for (var i = 0; i < document.styleSheets.length; i++) {
                stylesheets += Ext.String.format('<link rel="stylesheet" href="{0}" />', document.styleSheets[i].href);
            }
            styleSheetConcatTask.delay(1000);
        });

        styleSheetCreateTask.delay(1000);

    }

});
