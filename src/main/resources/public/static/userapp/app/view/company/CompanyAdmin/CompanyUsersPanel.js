/*
 * File: app/view/company/CompanyAdmin/CompanyUsersPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.CompanyAdmin.CompanyUsersPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.companycompanyadmincompanyuserspanel',

    requires: [
        'MyApp.view.company.CompanyAdmin.CompanyUsersPanelViewModel',
        'Ext.grid.Panel',
        'Ext.grid.column.RowNumberer',
        'Ext.grid.column.Action',
        'Ext.grid.View',
        'Ext.grid.filters.Filters',
        'Ext.toolbar.Toolbar',
        'Ext.button.Button'
    ],

    viewModel: {
        type: 'companycompanyadmincompanyuserspanel'
    },
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'gridpanel',
            icon_dbl_click_handler: function(record) {
                var emp_comp = Ext.create('widget.companycompanyadminuserneweditprevform', {});
                //Ext.getCmp('compusercreate_submit_toolbar').hide();
                emp_comp.down('toolbar').getComponent('savebutton').show();
                emp_comp.down('toolbar').getComponent('submitbutton').destroy();

                var form = emp_comp.down('form');
                var fields = form.getForm().getFields();

                form.getForm().findField('a_new_form').setValue(false);
                form.loadRecord(record);
                form.getForm().findField("password").hide();
                form.getForm().findField("password2").hide();

                fields.each(function (field) {
                    field.enable();
                    field.setReadOnly (true);
                });

                Ext.getCmp('companyuserprev').getForm().findField('a_new_form').setValue(false);
                Ext.getCmp('companyuserprev').getForm().findField('userId').setValue(record.get('id'));


                emp_comp.show();
            },
            flex: 1,
            autoScroll: true,
            id: 'companyUsers',
            itemId: 'companyUsers',
            title: 'Χρήστες Οργανισμού και Δικαιώματα Υπηρεσιών',
            autoLoad: true,
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            bind: {
                store: '{COMPANY_USERS}'
            },
            columns: [
                {
                    xtype: 'rownumberer'
                },
                {
                    xtype: 'actioncolumn',
                    width: 30,
                    enableColumnHide: false,
                    sealed: true,
                    hideable: false,
                    menuDisabled: true,
                    items: [
                        {
                            getClass: function(v, metadata, r, rowIndex, colIndex, store) {
                                var store = Ext.StoreMgr.lookup('company.CompanyAdmin.USERPREV');
                                var record = store.findRecord('userId', r.get('id'));
                                if (record.get('active')==="1"){
                                    this.items[0].tooltip = 'Ενεργός';
                                    return 'activeU'; // css for icon
                                }
                                else{
                                    this.items[0].tooltip = 'Ανενεργός';
                                    return 'inactiveU'; // css for icon

                                }


                            },
                            disabled: false
                        }
                    ]
                },
                {
                    xtype: 'gridcolumn',
                    filterField: true,
                    sortable: false,
                    dataIndex: 'name',
                    text: 'Ονοματεπώνυμο Χρήστη',
                    flex: 10
                },
                {
                    xtype: 'gridcolumn',
                    sortable: false,
                    dataIndex: 'username',
                    text: 'Όνομα Χρήστη (username)',
                    flex: 5
                },
                {
                    xtype: 'gridcolumn',
                    filterField: true,
                    sortable: false,
                    dataIndex: 'email',
                    text: 'Email',
                    flex: 10
                },
                {
                    xtype: 'actioncolumn',
                    width: 30,
                    enableColumnHide: false,
                    hideable: false,
                    menuDisabled: true,
                    items: [
                        {
                            getClass: function(v, metadata, r, rowIndex, colIndex, store) {

                                this.items[0].tooltip = 'Επεξεργασία';
                                return 'settingsU'; // css for icon

                            },
                            handler: function(view, rowIndex, colIndex, item, e, record, row) {
                                view.up().icon_dbl_click_handler(record);
                            }
                        }
                    ]
                }
            ],
            viewConfig: {
                frame: true,
                preserveScrollOnRefresh: true,
                listeners: {
                    itemdblclick: 'onUserItemDblClick'
                }
            },
            plugins: [
                {
                    ptype: 'gridfilters',
                    menuFilterText: 'Αναζήτηση'
                }
            ],
            dockedItems: [
                {
                    xtype: 'toolbar',
                    dock: 'top',
                    items: [
                        {
                            xtype: 'button',
                            glyph: 'xf067@FontAwesome',
                            text: 'Δημιουργία Νέου Χρήστη',
                            listeners: {
                                click: 'onNewUser'
                            }
                        }
                    ]
                }
            ]
        }
    ],

    onUserItemDblClick: function(dataview, record, item, index, e, eOpts) {
        dataview.up().icon_dbl_click_handler(record);

    },

    onNewUser: function(button, e, eOpts) {
        var emp_comp = Ext.create('widget.companycompanyadminuserneweditprevform', {
        });
        emp_comp.show();
    }

});