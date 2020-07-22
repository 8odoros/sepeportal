/*
 * File: app/view/doctor/CompanyBooksPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.doctor.CompanyBooksPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.doctorcompanybookspanel',

    requires: [
        'MyApp.view.doctor.CompanyBooksPanelViewModel',
        'Ext.grid.Panel',
        'Ext.grid.column.RowNumberer',
        'Ext.grid.View',
        'Ext.toolbar.Paging',
        'Ext.grid.filters.Filters',
        'Ext.grid.column.Action',
        'Ext.grid.filters.filter.String',
        'Ext.button.Button'
    ],

    viewModel: {
        type: 'doctorcompanybookspanel'
    },
    id: 'projectanncompany3',
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'gridpanel',
            timestampToDate: function(timestamp) {

                var pD = timestamp.replace(/[^0-9]+/g,' ').split(" ");
                return (pD[2]+"-"+pD[1]+"-"+pD[0]);
            },
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
            flex: 1,
            autoScroll: true,
            height: '50%',
            id: 'doctorCompanyNotes_Ann',
            itemId: 'doctorCompanyNotes_Ann',
            title: 'Βιβλία Επιχειρήσεων με ενεργή σύμβαση',
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

                                this.items[0].tooltip = 'Εκτύπωση Υποδείξεων';
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
                                    value:'Ακολουθούν οι υποδείξεις:'
                                });


                                form.add(grp);
                                var grid = view.up('grid');
                                pstore = Ext.StoreManager.get('doctor.CompanyBooks.BOOK_NOTES_ALL');
                                var compIeAnnId = record.get('compIeAnnId');
                                pstore.proxy.setUrl('/compIeAnnBookNote/search/findByCompIeAnnId?compIeAnnId='+compIeAnnId);
                                pstore.load({
                                    callback: function(records, operation, success) {
                                        for (var i = 0; i < records.length; i++) {
                                            var formnote = Ext.create('widget.doctorcompanybooksnoteform', {});
                                            var newitem = formnote.down('form').items.getAt(4);
                                            newitem.items.get(0).setValue(grid.timestampToDate(records[i].get('dateCreated')));
                                            newitem.items.get(1).setValue(records[i].get('notes'));
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
            timestampToDate: function(timestamp) {

                var pD = timestamp.replace(/[^0-9]+/g,' ').split(" ");
                return (pD[2]+"-"+pD[1]+"-"+pD[0]);
            },
            timestampToTime: function(timestamp) {

                var pD = timestamp.replace(/[^0-9]+/g,' ').split(" ");
                return (pD[3]+":"+pD[4]);
            },
            icon_dbl_click_handler: function(record) {

                var emp_comp = Ext.create('widget.doctorcompanybooksnoteform', {});
                var form = emp_comp.down('form');
                var fields = form.getForm().getFields();

                Ext.getCmp('doctorbooknote_save_submit_toolbar').hide();
                var grid = this;
                form.getForm().findField('a_new_form').setValue(false);
                form.loadRecord(record);

                form.getForm().findField('dateCreated').setValue(grid.timestampToDate(record.get('dateCreated')));
                if(parseInt(record.get('read'))===1){
                    form.getForm().findField('readDate').setValue(grid.timestampToDate(record.get('readDate')));
                }

                fields.each(function (field) {
                    field.enable();
                field.setReadOnly (true);});

                emp_comp.show();
            },
            flex: 1,
            autoScroll: true,
            height: '50%',
            id: 'doctorCompanyNotes_Book',
            itemId: 'doctorCompanyNotes_Book',
            title: 'Καταχωρήσεις Βιβλίου',
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'doctor.CompanyBooks.BOOK_NOTES',
            columns: [
                {
                    xtype: 'rownumberer'
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        if (value!==null){
                            var pD = value.replace(/[^0-9]+/g,' ').split(" ");
                            return ((pD[2]+"-"+pD[1]+"-"+pD[0])+"  "+pD[3]+":"+pD[4]);
                        }
                        else
                        return "";

                    },
                    sortable: false,
                    dataIndex: 'dateCreated',
                    text: 'Ημερομηνία Καταχώρησης',
                    flex: 40,
                    filter: {
                        type: 'string',
                        emptyText: 'Εισαγωγή κειμένου...'
                    }
                },
                {
                    xtype: 'actioncolumn',
                    width: 30,
                    enableColumnHide: false,
                    dataIndex: 'read',
                    hideable: false,
                    menuDisabled: true,
                    items: [
                        {
                            getClass: function(v, metadata, r, rowIndex, colIndex, store) {
                                if (v===1){
                                    return 'oldwarning'; // css for icon
                                }
                                else{
                                    return 'noread'; // css for icon

                                }
                            }
                        }
                    ]
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        if (value!==null){
                            var pD = value.replace(/[^0-9]+/g,' ').split(" ");
                            return ((pD[2]+"-"+pD[1]+"-"+pD[0])+"  "+pD[3]+":"+pD[4]);
                        }
                        else
                        return "";
                    },
                    sortable: false,
                    dataIndex: 'readDate',
                    text: 'Ημερομηνία Διαβάσματος',
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
                    refresh: 'onBookViewRefresh',
                    itemdblclick: 'onBookViewItemDblClick',
                    beforerefresh: 'onBookBeforeRefresh'
                }
            },
            dockedItems: [
                {
                    xtype: 'toolbar',
                    dock: 'top',
                    items: [
                        {
                            xtype: 'button',
                            glyph: 'xf067@FontAwesome',
                            text: 'Προσθήκη Υπόδειξης',
                            listeners: {
                                click: 'onNewBookNote'
                            }
                        }
                    ]
                },
                {
                    xtype: 'pagingtoolbar',
                    dock: 'bottom',
                    displayInfo: true,
                    firstText: 'Πρώτη Σελίδα',
                    lastText: 'Τελευταία Σελίδα',
                    nextText: 'Επόμενη Σελίδα',
                    prevText: 'Προηγούμενη Σελίδα',
                    refreshText: 'Ανανέωση',
                    store: 'doctor.CompanyBooks.BOOK_NOTES'
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
        Ext.getCmp('doctorCompanyNotes_Book').store.clearData();
        Ext.getCmp('doctorCompanyNotes_Book').view.refresh();
    },

    onCompanyViewItemClick: function(dataview, record, item, index, e, eOpts) {
        var book = Ext.getCmp('doctorCompanyNotes_Book');
        var compIeAnnId = record.get('compIeAnnId');
        book.store.proxy.setUrl('/compIeAnnBookNote/search/findByCompIeAnnId?compIeAnnId='+compIeAnnId);
        book.getStore().reload({
          callback: function(){
            book.getView().refresh();
          }
        });


    },

    onCompanyViewContainerClick: function(dataview, e, eOpts) {
        var dailygrid = Ext.getCmp('doctorCompanyNotes_Book');
        dailygrid.store.clearData();
        dailygrid.getView().refresh();
    },

    onBookViewRefresh: function(dataview, eOpts) {
        if (dataview.store.data.length<1 && dataview.store.filters.length>0){
            Ext.addon.MessagePop.msg("Προσοχή!", "Δεν υπάρχουν εγγραφές που να αντιστοιχούν στην αναζήτηση σας. Προβάλονται όλες οι εγγραφές της σελίδας.", 5000);
            dataview.up('grid').plugins[1].clearValues();
            dataview.store.reload();
        }
    },

    onBookViewItemDblClick: function(dataview, record, item, index, e, eOpts) {
        dataview.up().icon_dbl_click_handler(record);

    },

    onBookBeforeRefresh: function(dataview, eOpts) {

        if(Ext.getCmp('doctorCompanyNotes_Ann').getSelectionModel().getSelection().length>0){
            var compIeAnnId = Ext.getCmp('doctorCompanyNotes_Ann').getSelectionModel().getSelection()[0].get('compIeAnnId');
            dataview.store.proxy.setUrl('/compIeAnnBookNote/search/findByCompIeAnnId?compIeAnnId='+compIeAnnId);
        }
    },

    onNewBookNote: function(button, e, eOpts) {
        if(Ext.getCmp('doctorCompanyNotes_Ann').getSelectionModel().getSelection().length>0){
            var compIeAnnId = Ext.getCmp('doctorCompanyNotes_Ann').getSelectionModel().getSelection()[0].get('compIeAnnId');
            var branchId = Ext.getCmp('doctorCompanyNotes_Ann').getSelectionModel().getSelection()[0].get('ptlBranchId');
            var emp_comp = Ext.create('widget.doctorcompanybooksnoteform', {
            });

            emp_comp.down().getForm().findField('branchId').setValue(branchId);
            emp_comp.down().getForm().findField('compIeAnnId').setValue(compIeAnnId);
            emp_comp.show();
        }
        else {
                 Ext.Msg.alert('Προσοχή!', 'Δεν έχετε επιλέξει κάποιο βιβλίο από την παραπάνω λίστα.');

        }


    }

});