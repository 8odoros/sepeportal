/*
 * File: app/view/company/NotificationsPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.NotificationsPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.companynotificationspanel',

    requires: [
        'MyApp.view.company.NotificationsPanelViewModel',
        'MyApp.view.company.NotificationsGrid',
        'Ext.grid.Panel',
        'Ext.XTemplate',
        'Ext.panel.Tool'
    ],

    viewModel: {
        type: 'companynotificationspanel'
    },
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'companynotificationsgrid',
            flex: 1
        },
        {
            xtype: 'panel',
            flex: 1,
            autoScroll: true,
            height: '50%',
            hidden: true,
            tpl: [
                '<head><link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet"></head>',
                '<h3>{title}</h3>',
                '',
                '<table align="left" border="0" cellpadding="6" cellspacing="2">',
                '	<tbody>',
                '		<tr>',
                '			<td><strong>Αποστολέας:</strong></td>',
                '			<td>{sender}</td>',
                '		</tr>',
                '		<tr>',
                '			<td><strong>Ημερομηνία - Ώρα:&nbsp&nbsp</strong></td>',
                '			<td>{dateTimeview}</td>',
                '		</tr>',
                '		<tr>',
                '			<td><strong>Προτεραιότητα:</strong></td>',
                '			<td>{priorityview}</td>',
                '		</tr>',
                '	</tbody>',
                '</table>',
                '<p>&nbsp;</p>',
                '',
                '<p>&nbsp;</p>',
                '',
                '<p>&nbsp;</p>',
                '',
                '<tpl if="docId &gt; 0">',
                '<a href="/portal/getDocument?docId={docId}" target="_blank"><button class="btn btn-primary"><i class="fa fa-file fa-fw"></i>&nbsp;Βεβαιωση Κοινοποιησης</button></a>&nbsp;&nbsp;',
                '</tpl>',
                '<tpl if="attDocId &gt; 0">',
                '<a href="/portal/getDocument?docId={attDocId}" target="_blank"><button class="btn btn-primary"><i class="fa fa-paperclip fa-fw"></i>&nbsp;Κοινοποιουμενο Εγγραφο</button></a>',
                '</tpl>',
                '<p>&nbsp;</p>',
                '',
                '<div style="background:#eee;border:1px solid #ccc;padding:5px 10px;"><p><em><strong>Μήνυμα:</strong></em></p>{message}</div>'
            ],
            bodyBorder: true,
            bodyPadding: 10,
            collapseFirst: false,
            collapsible: false,
            frameHeader: false,
            tools: [
                {
                    xtype: 'tool',
                    callback: function(owner, tool, event) {
                        var pnl = owner;


                        // instantiate hidden iframe

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



                        var printTask = new Ext.util.DelayedTask(function(){
                            // print the iframe
                            cw.print();

                            // destroy the iframe
                            Ext.fly(iFrameId).destroy();

                        });


                        var strTask = new Ext.util.DelayedTask(function(){
                            var str = Ext.String.format('<html><head>{0}</head><body>{1}</body></html>',stylesheets,markup);


                            // output to the iframe
                            cw.document.open();
                            cw.document.write(str);
                            cw.document.close();

                            // remove style attrib that has hardcoded height property
                            //             cw.document.getElementsByTagName('DIV')[0].removeAttribute('style');
                            printTask.delay(500);

                        });

                        var markUpTask = new Ext.util.DelayedTask(function(){
                            // get the contents of the panel and remove hardcoded overflow properties
                            markup = pnl.getEl().dom.innerHTML;
                            while (markup.indexOf('overflow: auto;') >= 0) {
                                markup = markup.replace('overflow: auto;', '');
                            }
                            while (markup.indexOf('background: rgb(255, 192, 203) !important;') >= 0) {
                                markup = markup.replace('background: rgb(255, 192, 203) !important;', 'background: pink !important;');
                            }

                            strTask.delay(500);
                        });


                        var styleSheetConcatTask = new Ext.util.DelayedTask(function(){

                            // various style overrides
                            stylesheets += ''.concat(
                            "<style>",
                            ".x-panel-body {overflow: visible !important;}",
                            // experimental - page break after embedded panels
                            // .x-panel {page-break-after: always; margin-top: 10px}",
                            "</style>"
                            );

                            markUpTask.delay(500);
                        });


                        var styleSheetCreateTask = new Ext.util.DelayedTask(function(){


                            for (var i = 0; i < document.styleSheets.length; i++) {
                                stylesheets += Ext.String.format('<link rel="stylesheet" href="{0}" />', document.styleSheets[i].href);
                            }
                            styleSheetConcatTask.delay(500);
                        });

                        styleSheetCreateTask.delay(500);
                    },
                    tooltip: 'Εκτύπωση Μηνύματος',
                    type: 'print'
                }
            ],
            listeners: {
                afterrender: 'onPanelAfterRender'
            }
        }
    ],

    onPanelAfterRender: function(component, eOpts) {
        component.header.hide();
    }

});