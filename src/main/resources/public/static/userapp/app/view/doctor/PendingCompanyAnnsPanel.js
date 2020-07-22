/*
 * File: app/view/doctor/PendingCompanyAnnsPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.doctor.PendingCompanyAnnsPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.doctorpendingcompanyannspanel',

    requires: [
        'MyApp.view.doctor.PendingCompanyAnnsPanelViewModel',
        'Ext.grid.Panel',
        'Ext.grid.column.RowNumberer',
        'Ext.grid.View',
        'Ext.toolbar.Paging',
        'Ext.grid.filters.Filters',
        'Ext.grid.column.Action'
    ],

    viewModel: {
        type: 'doctorpendingcompanyannspanel'
    },
    id: 'projectanncompany4',
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
                if (record.get('ieAnnIeStatus')===1 && record.get('ieAnnStatus')===0 ){
                    Ext.getCmp('companyInfoForm_accept_reject_toolbar').hide();
                }
                else {
                    Ext.getCmp('companyInfoForm_accept_reject_toolbar').show();
                }

                var form = emp_comp.down('form');
                var fields = form.getForm().getFields();

                form.getForm().findField('a_new_form').setValue(false);
                form.getForm().findField('only_view').setValue(false);
                form.loadRecord(record);

                fields.each(function (field) {
                    field.enable();
                    field.setReadOnly (true);});

                emp_comp.show();

            },
            flex: 1,
            autoScroll: true,
            height: '100%',
            id: 'doctorPendingCompany_Anns',
            itemId: 'doctorPendingCompany_Anns',
            title: 'Αναγγελίες σε Εκρεμμότητα',
            autoLoad: true,
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'doctor.COMPANIES_DOCTOR_PEND_ANNS_GRID',
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
                    xtype: 'actioncolumn',
                    width: 30,
                    enableColumnHide: false,
                    hideable: false,
                    menuDisabled: true,
                    items: [
                        {
                            getClass: function(v, metadata, r, rowIndex, colIndex, store) {
                                if (r.get('taAnnTaStatus')===1 && r.get('taAnnStatus')===0 ){
                                    //this.items[0].tooltip = 'Αναμονή υπολοίπων';
                                    metadata.tdAttr='data-qtip="Αναμονή υπολοίπων"';
                                    return 'pendingU'; // css for icon
                                }
                                else {
                                    //this.items[0].tooltip = 'Έγκριση / Απόρριψη';
                                    metadata.tdAttr='data-qtip="Έγκριση / Απόρριψη"';
                                    return 'accept_or_reject'; // css for icon
                                }

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
                    store: 'doctor.COMPANIES_DOCTOR_PEND_ANNS_GRID'
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