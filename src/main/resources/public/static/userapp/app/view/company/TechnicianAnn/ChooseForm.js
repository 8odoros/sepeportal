/*
 * File: app/view/company/TechnicianAnn/ChooseForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianAnn.ChooseForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companytechnicianannchooseform',

    requires: [
        'MyApp.view.company.TechnicianAnn.ChooseFormViewModel',
        'MyApp.view.company.TechnicianAnn.ChooseFormViewController',
        'Ext.button.Button',
        'Ext.form.field.Hidden'
    ],

    controller: 'companytechnicianannchooseform',
    viewModel: {
        type: 'companytechnicianannchooseform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    overflowY: 'auto',
    resizable: false,
    width: 400,
    title: 'Επιλογή Είδους Αναγγελίας Τεχνικού Ασφάλειας',
    modal: true,

    layout: {
        type: 'hbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'button',
            height: 120,
            itemId: 'normal',
            maxWidth: 200,
            padding: 10,
            width: 200,
            text: 'Αναγγελίες Τεχνικών <br>σε Επιχειρήσεις <br>και Υποκαταστήματα',
            listeners: {
                click: 'onChoose_COMPANY_TECHNICIAN_ANN_NORMAL'
            }
        },
        {
            xtype: 'button',
            height: 120,
            itemId: 'ships',
            maxWidth: 200,
            padding: 10,
            width: 200,
            text: 'Αναγγελίες Τεχνικών <br>σε Πλοία και <br>Ναυπηγικές Εργασίες',
            //disabled: true,
            listeners: {
                click: 'onChoose_COMPANY_TECHNICIAN_ANN_SHIPS',
            }
        },
        {
            xtype: 'hiddenfield',
            flex: 1,
            fieldLabel: 'Label',
            name: 'isBooks',
            value: false
        }
    ]

});