/*
 * File: app/view/company/DangersPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.DangersPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.companydangerspanel',

    requires: [
        'MyApp.view.company.DangersPanelViewModel',
        'MyApp.view.company.DangersPanelViewController',
        'Ext.grid.Panel',
        'Ext.grid.column.RowNumberer',
        'Ext.grid.filters.filter.String',
        'Ext.grid.View',
        'Ext.toolbar.Paging',
        'Ext.form.field.Text',
        'Ext.form.trigger.Trigger',
        'Ext.grid.filters.Filters',
        'Ext.grid.column.Action'
    ],

    controller: 'companydangerspanel',
    viewModel: {
        type: 'companydangerspanel'
    },
    id: 'complaints7',
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'gridpanel',
            icon_dbl_click_handler: function(record) {
                var emp_comp = Ext.create('widget.companydangerformdangerform', {});

                var form = emp_comp.down('form');
                var fields = form.getForm().getFields();


                form.getForm().findField('a_new_form').setValue(false);

                form.loadRecord(record);

                if(record.get('dateUpdated')!==null)
                form.getForm().findField('dateUpdated').setValue(form.timestampToDate(record.get('dateUpdated')));


                if (parseInt(record.get('attachedDocId'))!==-1){
                    var vfc = Ext.ComponentQuery.query('#Exist_File')[0];
                    vfc.show();
                }


                emp_comp.show();


            },
            flex: 1,
            autoScroll: true,
            id: 'companydangersgrid',
            itemId: 'companydangersgrid',
            title: 'Λίστα Εκτιμήσεων Επαγγελματικού Κινδύνου Υποκαταστημάτων',
            autoLoad: true,
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.DANGERS_BRANCHES',
            columns: [
                {
                    xtype: 'rownumberer'
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        var store = Ext.StoreMgr.lookup('company.COMP_BRANCES');
                        return store.findRecord('rgEbrBranchId', record.get("branch1Id")).get('rgEbrAddressStreetCombo');
                    },
                    sortable: false,
                    dataIndex: 'url',
                    text: 'Υποκατάστημα',
                    flex: 40,
                    filter: {
                        type: 'string',
                        emptyText: 'Εισαγωγή κειμένου...'
                    }
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {

                        var pD = value.replace(/[^0-9]+/g,' ').split(" ");
                        return (pD[2]+"-"+pD[1]+"-"+pD[0]);

                    },
                    sortable: false,
                    dataIndex: 'dateUpdated',
                    text: 'Ημερομηνία Επεξεργασίας',
                    flex: 14
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
                                return 'editme'; // css for icon

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
                    itemdblclick: 'onDangerItemDblClick'
                }
            },
            dockedItems: [
                {
                    xtype: 'pagingtoolbar',
                    dock: 'bottom',
                    displayInfo: true,
                    firstText: 'Πρώτη Σελίδα',
                    lastText: 'Τελευταία Σελίδα',
                    nextText: 'Επόμενη Σελίδα',
                    prevText: 'Προηγούμενη Σελίδα',
                    refreshText: 'Ανανέωση',
                    store: 'company.DANGERS_BRANCHES'
                },
                {
                    xtype: 'toolbar',
                    dock: 'top',
                    hidden: true,
                    items: [
                        {
                            xtype: 'textfield',
                            name: 'searchbox',
                            emptyText: 'Αναζήτηση',
                            triggers: {
                                mytrigger: {
                                    cls: 'x-form-search-trigger'
                                }
                            },
                            listeners: {
                                change: {
                                    fn: 'onTextfieldChange',
                                    scope: 'controller'
                                }
                            }
                        }
                    ]
                }
            ],
            plugins: [
                {
                    ptype: 'gridfilters',
                    menuFilterText: 'Αναζήτηση'
                }
            ]
        }
    ],

    onDangerItemDblClick: function(dataview, record, item, index, e, eOpts) {
        dataview.up().icon_dbl_click_handler(record);
    }

});