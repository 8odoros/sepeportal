/*
 * File: app/view/doctor/CompanyDiariesPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.doctor.CompanyDiariesPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.doctorcompanydiariespanel',

    requires: [
        'MyApp.view.doctor.CompanyDiariesPanelViewModel',
        'Ext.grid.Panel',
        'Ext.grid.column.RowNumberer',
        'Ext.grid.View',
        'Ext.toolbar.Paging',
        'Ext.grid.filters.Filters',
        'Ext.grid.column.Action',
        'Ext.grid.filters.filter.String'
    ],

    viewModel: {
        type: 'doctorcompanydiariespanel'
    },
    id: 'projectanncompany2',
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
                Ext.getCmp('companyInfoForm_accept_reject_toolbar').hide();

                var form = emp_comp.down('form');
                var fields = form.getForm().getFields();

                form.getForm().findField('a_new_form').setValue(false);
                form.loadRecord(record);

                fields.each(function (field) {
                    field.enable();
                field.setReadOnly (true);});

                emp_comp.show();

            },
            timestampToDate: function(timestamp) {

                var pD = timestamp.replace(/[^0-9]+/g,' ').split(" ");
                return (pD[2]+"-"+pD[1]+"-"+pD[0]);
            },
            timestampToTime: function(timestamp) {

                var pD = timestamp.replace(/[^0-9]+/g,' ').split(" ");
                return (pD[3]+":"+pD[4]);
            },
            flex: 1,
            autoScroll: true,
            height: '50%',
            id: 'doctorCompany_Anns',
            itemId: 'doctorCompany_Anns',
            title: 'Επιχειρήσεις με ενεργή σύμβαση',
            autoLoad: true,
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'doctor.COMPANIES_DOCTOR_ANNS_GRID',
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

                                this.items[0].tooltip = 'Εκτύπωση Προγράμματος Επίσκέψεων';
                                return 'printme'; // css for icon

                            },
                            handler: function(view, rowIndex, colIndex, item, e, record, row) {
                                var emp_comp = Ext.create('widget.doctorcompanyinfoviewform', {});
                                Ext.getCmp('companyInfoForm_accept_reject_toolbar').hide();

                                var form = emp_comp.down('form');
                                var fields = form.getForm().getFields();

                                form.getForm().findField('a_new_form').setValue(false);
                                form.loadRecord(record);

                                grp = new Ext.form.DisplayField({
                                    fieldLabel: '',
                                    value:'Ακολουθεί το πρόγραμμα επισκέψεων:'
                                });


                                form.add(grp);
                                var grid = view.up('grid');
                                pstore = Ext.StoreManager.get('doctor.CompanyDiary.DIARY_ALL');
                                var compIeAnnId = record.get('compIeAnnId');
                                pstore.proxy.setUrl('vCompIeAnnDiary/search/findByCompIeAnnId?compIeAnnId='+compIeAnnId);
                                pstore.load({
                                    callback: function(records, operation, success) {
                                        for (var i = 0; i < records.length; i++) {
                                            var newitem = Ext.create('widget.doctordoctordiaryentry', {});
                                            newitem.down().items.get(0).setValue(grid.timestampToDate(records[i].get('visitDate')));
                                            newitem.down().items.get(1).setValue(records[i].get('visitTime'));
                                            newitem.down().items.get(2).setValue(records[i].get('visitDurationMinutes'));
                                            form.add(newitem);
                                        }

                                        fields = form.getForm().getFields();
                                        fields.each(function (field) {
                                            field.enable();
                                        field.setReadOnly (true);});

                                        emp_comp.show();
                                    }
                                });
                            }
                        }
                    ]
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
                                return 'viewme'; // css for icon

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
                    refresh: 'onCompanyViewRefresh',
                    itemclick: 'onCompanyViewItemClick',
                    containerclick: 'onCompanyViewContainerClick'
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
                    store: 'doctor.COMPANIES_DOCTOR_ANNS_GRID'
                }
            ],
            plugins: [
                {
                    ptype: 'gridfilters',
                    menuFilterText: 'Αναζήτηση'
                }
            ]
        },
        {
            xtype: 'gridpanel',
            flex: 1,
            autoScroll: true,
            height: '50%',
            id: 'doctorCompany_Diary',
            itemId: 'doctorCompany_Diary',
            title: 'Πρόγραμμα Επισκέψεων',
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'doctor.CompanyDiary.DIARY',
            columns: [
                {
                    xtype: 'rownumberer'
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        if (value!==null){
                            var pD = value.replace(/[^0-9]+/g,' ').split(" ");
                            return (pD[2]+"-"+pD[1]+"-"+pD[0]);
                        }
                        else
                        return "";

                    },
                    sortable: false,
                    dataIndex: 'visitDate',
                    text: 'Ημερομηνία',
                    flex: 40,
                    filter: {
                        type: 'string',
                        emptyText: 'Εισαγωγή κειμένου...'
                    }
                },
                {
                    xtype: 'gridcolumn',
                    sortable: false,
                    dataIndex: 'visitTime',
                    text: 'Ώρα',
                    flex: 14
                },
                {
                    xtype: 'gridcolumn',
                    sortable: false,
                    dataIndex: 'visitDurationMinutes',
                    text: 'Διάρκεια (σε λεπτά)',
                    flex: 14
                }
            ],
            viewConfig: {
                frame: true,
                preserveScrollOnRefresh: true,
                listeners: {
                    refresh: 'onDiaryViewRefresh',
                    beforerefresh: 'onDiaryViewBeforeRefresh'
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
                    store: 'doctor.CompanyDiary.DIARY'
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

        Ext.getCmp('doctorCompany_Diary').store.clearData();
        Ext.getCmp('doctorCompany_Diary').view.refresh();
    },

    onCompanyViewItemClick: function(dataview, record, item, index, e, eOpts) {
        var diary = Ext.getCmp('doctorCompany_Diary');
        var compIeAnnId = Ext.getCmp('doctorCompany_Anns').getSelectionModel().getSelection()[0].get('compIeAnnId');
        diary.store.proxy.setUrl('vCompIeAnnDiary/search/findByCompIeAnnId?compIeAnnId='+compIeAnnId);
        diary.getStore().reload({
          callback: function(){
            diary.getView().refresh();
          }
        });
    },

    onCompanyViewContainerClick: function(dataview, e, eOpts) {
        var dailygrid = Ext.getCmp('doctorCompany_Diary');
        dailygrid.store.clearData();
        dailygrid.getView().refresh();
    },

    onDiaryViewRefresh: function(dataview, eOpts) {
        if (dataview.store.data.length<1 && dataview.store.filters.length>0){
            Ext.addon.MessagePop.msg("Προσοχή!", "Δεν υπάρχουν εγγραφές που να αντιστοιχούν στην αναζήτηση σας. Προβάλονται όλες οι εγγραφές της σελίδας.", 5000);
            dataview.up('grid').plugins[1].clearValues();
            dataview.store.reload();
        }
    },

    onDiaryViewBeforeRefresh: function(dataview, eOpts) {
        if(Ext.getCmp('doctorCompany_Anns').getSelectionModel().getSelection().length>0){
            var compIeAnnId = Ext.getCmp('doctorCompany_Anns').getSelectionModel().getSelection()[0].get('compIeAnnId');
            dataview.store.proxy.setUrl('/vCompIeAnnDiary/search/findByCompIeAnnId?compIeAnnId='+compIeAnnId);
        }



    }

});