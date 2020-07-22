/*
 * File: app/view/company/exypp/CompanyInfoViewFormViewModel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.exypp.ReplacedCompanyDoctorAnnsPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.companyexyppreplacedcompanydoctorannspanel',

    requires: [
        'MyApp.view.company.exypp.ReplacedCompanyAnnsPanelViewModel',
        'Ext.grid.Panel',
        'Ext.grid.column.RowNumberer',
        'Ext.grid.column.Action',
        'Ext.grid.View',
        'Ext.toolbar.Paging',
        'Ext.grid.filters.Filters'
    ],

    viewModel: {
        type: 'companyexyppreplacedcompanydoctorannspanel'
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
                var emp_comp = Ext.create('widget.companyexyppcompanydoctorinfoviewform', {});

                var form = emp_comp.down('form');
                var fields = form.getForm().getFields();

                form.getForm().findField('a_new_form').setValue(false);
                form.getForm().findField('only_view').setValue("replaced");
                form.loadRecord(record);

                Ext.getCmp('companyIeInfoForm_accept_reject_toolbar1').hide();
                if(record.get('ieAnnIeReplaceResponse')===null)
                    Ext.getCmp('companyIeInfoForm_replace_action_toolbar1').show();
                else
                    Ext.getCmp('companyIeInfoForm_replace_action_toolbar1').hide();

                fields.each(function (field) {
                    field.enable();
                    field.setReadOnly (true);});

                emp_comp.show();
            },
            flex: 1,
            autoScroll: true,
            height: '100%',
            id: 'companyexyppReplacedCompanyDoctor_Anns',
            itemId: 'companyexyppReplacedCompanyDoctor_Anns',
            title: 'Αναγγελίες στις οποίες σας έχει γίνει Παύση - Αντικατάσταση',
            autoLoad: true,
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.exypp.COMPANIES_DOCTOR_REPL_ANNS_GRID',
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
                    dataIndex: 'ieAnnIeReplaceResponse',
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
                                if(r.get('ieAnnIeReplaceResponse')===null)
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
                    store: 'company.exypp.COMPANIES_DOCTOR_REPL_ANNS_GRID'
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