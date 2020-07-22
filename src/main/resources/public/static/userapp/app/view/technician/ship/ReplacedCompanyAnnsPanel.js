/*
 * File: app/view/technician/ship/ReplacedCompanyAnnsPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.technician.ship.ReplacedCompanyAnnsPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.technicianshipreplacedcompanyannspanel',

    requires: [
        'MyApp.view.technician.ship.ReplacedCompanyAnnsPanelViewModel',
        'Ext.grid.Panel',
        'Ext.grid.column.RowNumberer',
        'Ext.grid.column.Action',
        'Ext.grid.View',
        'Ext.toolbar.Paging',
        'Ext.grid.filters.Filters'
    ],

    viewModel: {
        type: 'technicianshipreplacedcompanyannspanel'
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
                var emp_comp = Ext.create('widget.technicianshipcompanyinfoviewform', {});

                var form = emp_comp.down('form');
                var fields = form.getForm().getFields();

                form.getForm().findField('a_new_form').setValue(false);
                form.getForm().findField('only_view').setValue("replaced");
                form.loadRecord(record);

                Ext.getCmp('companySTaInfoForm_accept_reject_toolbar').hide();
                if(record.get('taResponseReplace')===null)
                Ext.getCmp('companySTaInfoForm_replace_action_toolbar').show();
                else
                Ext.getCmp('companySTaInfoForm_replace_action_toolbar').hide();

                fields.each(function (field) {
                    field.enable();
                field.setReadOnly (true);});

                emp_comp.show();
            },
            flex: 1,
            autoScroll: true,
            height: '100%',
            id: 'technicianShipReplacedCompany_Anns',
            itemId: 'technicianShipReplacedCompany_Anns',
            title: 'Αναγγελίες στις οποίες σας έχει γίνει Παύση - Αντικατάσταση',
            autoLoad: true,
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'technician.ship.COMPANIES_TECHNICIAN_REPL_ANNS_GRID',
            columns: [
                {
                    xtype: 'rownumberer'
                },
                {
                    xtype: 'gridcolumn',
                    filterField: true,
                    sortable: false,
                    dataIndex: 'compInfoGrid',
                    text: 'Οργανισμός - Πλοίο',
                    flex: 10
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        if(value===null)
                        return "Εκρεμεί";
                        if(value.toString()==="1")
                        return 'Απόλυση';
                        else if (value.toString()==="0")
                        return "Παραίτηση";

                    },
                    filterField: true,
                    sortable: false,
                    dataIndex: 'taResponseReplace',
                    text: 'Απάντηση',
                    flex: 2
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

                                this.items[0].tooltip = 'Προβολή';
                                if(r.get('taResponseReplace')===null)
                                return 'accept_or_reject';
                                else
                                return 'viewme';

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
                    itemdblclick: 'onCompanyItemDblClick',
                    refresh: 'onCompanyViewRefresh'
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
                    store: 'technician.ship.COMPANIES_TECHNICIAN_REPL_ANNS_GRID'
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

    onCompanyItemDblClick: function(dataview, record, item, index, e, eOpts) {
        dataview.up().icon_dbl_click_handler(record);

    },

    onCompanyViewRefresh: function(dataview, eOpts) {
        if (dataview.store.data.length<1 && dataview.store.filters.length>0){
            Ext.addon.MessagePop.msg("Προσοχή!", "Δεν υπάρχουν εγγραφές που να αντιστοιχούν στην αναζήτηση σας. Προβάλονται όλες οι εγγραφές της σελίδας.", 5000);
            dataview.up('grid').plugins[1].clearValues();
            dataview.store.reload();
        }
    }

});