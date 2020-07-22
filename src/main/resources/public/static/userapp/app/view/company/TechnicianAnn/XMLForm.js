/**
 * Created by dimitrisf on 15/4/2019.
 */

Ext.define('MyApp.view.company.TechnicianAnn.XMLForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companytechnicianannxmlform',

    requires: [
        'MyApp.view.company.TechnicianAnn.XMLFormViewModel',
        'MyApp.view.company.TechnicianAnn.XMLFormViewController',
        'MyApp.view.shared.PrintFormTool',
        'MyApp.view.shared.CloseFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.FieldSet',
        'Ext.form.field.Date',
        'Ext.toolbar.Toolbar',
        'Ext.button.Button',
        'Ext.panel.Tool'
    ],

    controller: 'companytechnicianannxmlform',
    viewModel: {
        type: 'companytechnicianannxmlform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    overflowY: 'auto',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Αναγγελία Τεχνικού Ασφάλειας',
    modal: true,
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'form',
            id: 'companytechnicianannxml',
            padding: 15,
            title: '',
            layout: {
                type: 'vbox',
                align: 'stretch'
            },
            items: [
                {
                    xtype: 'hiddenfield',
                    name: 'a_new_form',
                    submitValue: false,
                    validateOnChange: false,
                    value: true
                },
                {
                    xtype: 'hiddenfield',
                    flex: 1,
                    height: 150,
                    fieldLabel: 'Ημ. Πρωτοκόλλου',
                    labelWidth: 180,
                    name: 'companyId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    flex: 1,
                    height: 150,
                    fieldLabel: 'Ημ. Πρωτοκόλλου',
                    labelWidth: 180,
                    name: 'ebrBranchId',
                    validateOnChange: false,
                    value: 0
                },
                {
                    xtype: 'fieldset',
                    flex: 1,
                    focusOnToFront: false,
                    toFrontOnShow: false,
                    title: 'ΕΙΣΑΓΩΓΗ ΑΝΑΓΓΕΛΙΑΣ ΑΠΟ ΑΡΧΕΙΟ XML',
                    items: [
                        {
                            xtype: 'filefield',
                            anchor: '100%',
                            frame: false,
                            msgTarget: 'under',
                            name: 'file',
                            submitValue: true,
                            validateOnChange: false,
                            validateOnBlur: false,
                            buttonText: 'Επιλογή...',
                            listeners:
                            {
                                change:
                                {
                                    fn: 'onCheck_COMPANY_TECHNICIAN_ANN_XML',
                                    scope: 'controller'
                                }
                            }
                        }
                    ]
                },
                {
                    xtype: 'hiddenfield',
                    height: 150,
                    labelWidth: 180,
                    name: 'url',
                    validateOnChange: false
                }
            ]
        }
    ],
    dockedItems: [],
    tools: [
        {
            xtype: 'sharedprintformtool'
        },
        {
            xtype: 'sharedcloseformtool'
        }
    ],

});