/*
 * File: app/view/doctor/ReplacedCompanyAnnsPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.doctor.ReplacedCompanyAnnsPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.doctorreplacedcompanyannspanel',

    requires: [
        'MyApp.view.doctor.ReplacedCompanyAnnsPanelViewModel',
        'Ext.grid.Panel',
        'Ext.grid.column.RowNumberer',
        'Ext.grid.column.Action',
        'Ext.grid.View',
        'Ext.toolbar.Paging',
        'Ext.grid.filters.Filters'
    ],

    viewModel: {
        type: 'doctorreplacedcompanyannspanel'
    },
    id: 'projectanncompany5',
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'gridpanel',
            icon_dbl_click_handler: function(record) {
                var emp_comp = Ext.create('widget.doctorcompanyinfoviewform', {});

                var form = emp_comp.down('form');
                var fields = form.getForm().getFields();

                form.getForm().findField('a_new_form').setValue(false);
                form.getForm().findField('only_view').setValue("replaced");
                form.loadRecord(record);

                Ext.getCmp('companyInfoForm_accept_reject_toolbar').hide();
                if(record.get('ieReplaceResponse')===null)
                Ext.getCmp('companyInfoForm_replace_action_toolbar').show();
                else
                Ext.getCmp('companyInfoForm_replace_action_toolbar').hide();

                fields.each(function (field) {
                    field.enable();
                field.setReadOnly (true);});

                emp_comp.show();
            },
            flex: 1,
            autoScroll: true,
            height: '100%',
            id: 'doctorReplacedCompany_Anns',
            itemId: 'doctorReplacedCompany_Anns',
            title: 'Αναγγελίες στις οποίες σας έχει γίνει Παύση - Αντικατάσταση',
            autoLoad: true,
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'doctor.COMPANIES_DOCTOR_REPL_ANNS_GRID',
            columns: [
                {
                    xtype: 'rownumberer'
                },
                {
                    xtype: 'gridcolumn',
                    filterField: true,
                    sortable: false,
                    dataIndex: 'compInfoGrid',
                    text: 'Οργανισμός',
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
                    dataIndex: 'ieReplaceResponse',
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

                                if(r.get('ieReplaceResponse')===null)
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
                    store: 'doctor.COMPANIES_DOCTOR_REPL_ANNS_GRID'
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