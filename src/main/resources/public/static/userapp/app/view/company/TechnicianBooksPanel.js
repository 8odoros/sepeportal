/*
 * File: app/view/company/TechnicianBooksPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianBooksPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.companytechnicianbookspanel',

    requires: [
        'MyApp.view.company.TechnicianBooksPanelViewModel',
        'Ext.grid.Panel',
        'Ext.grid.column.RowNumberer',
        'Ext.grid.View',
        'Ext.toolbar.Paging',
        'Ext.grid.filters.Filters',
        'Ext.grid.column.Action',
        'Ext.button.Button',
        'Ext.grid.filters.filter.String'
    ],

    viewModel: {
        type: 'companytechnicianbookspanel'
    },
    id: 'technicianbookscompany',
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
                var emp_comp = Ext.create('widget.companydoctorannbranchform', {});
                Ext.getCmp('compdoctorannbranch_save_submit_toolbar').hide();

                var form = emp_comp.down('form');
                var fields = form.getForm().getFields();

                form.getForm().findField('a_new_form').setValue(false);
                form.getForm().findField('NewebrBranchId').up('component').hide();

                form.loadRecord(record);

                fields.each(function (field) {
                    field.enable();
                field.setReadOnly (true);});

                emp_comp.show();

            },
            flex: 1,
            autoScroll: true,
            height: '50%',
            id: 'companyTechnicianBooks_Branches',
            itemId: 'companyTechnicianBooks_Branches',
            title: 'Βιβλία Τεχνικών Ασφαλείας για Εγκαταστάσεις ή Τοποθεσίες Έργων Οργανισμού',
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.PTL_TA_ANN_COMPANY_BRANCHES',
            columns: [
                {
                    xtype: 'rownumberer'
                },
                {
                    xtype: 'gridcolumn',
                    filterField: true,
                    sortable: false,
                    dataIndex: 'brDescr',
                    text: 'Περιγραφή Παραρτήματος',
                    flex: 10
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        var store2 = Ext.StoreMgr.lookup('company.TechnicianBooks.BOOKS');
                        var record = store2.findRecord('compPtlBranchId', value);
                        if(record)
                        return "Ναι";
                        else
                        return "Όχι";
                    },
                    sortable: false,
                    dataIndex: 'ptlBranchId',
                    text: 'Ενεργό Βιβλίο',
                    flex: 3
                },
                {
                    xtype: 'actioncolumn',
                    width: 30,
                    enableColumnHide: false,
                    sealed: true,
                    dataIndex: 'ptlBranchId',
                    hideable: false,
                    menuDisabled: true,
                    items: [
                        {
                            getClass: function(v, metadata, r, rowIndex, colIndex, store) {
                                var store2 = Ext.StoreMgr.lookup('company.TechnicianBooks.BOOKS');
                                var record = store2.findRecord('compPtlBranchId', v);
                                if (record){
                                    return 'bookopened'; // css for icon
                                }
                                else{
                                    if(r.get('taAnnStatus')===1)
                                    return 'bookclosed'; // css for icon
                                    else
                                    return 'newwarning';

                                }


                            },
                            handler: function(view, rowIndex, colIndex, item, e, record, row) {
                                if(record.get('taAnnStatus')===1){
                                    var emp_comp = Ext.create('widget.companytechnicianbooksbookform', {});


                                    var store = Ext.StoreMgr.lookup('company.TechnicianBooks.BOOKS');
                                    var book = store.findRecord('compPtlBranchId', record.get('ptlBranchId'));
                                    if (book){
                                        Ext.getCmp('comptechnicianbook_save_submit_toolbar').hide();
                                        var form = emp_comp.down('form');
                                        var fields = form.getForm().getFields();

                                        form.getForm().findField('a_new_form').setValue(false);

                                        form.getForm().findField('compPtlBranchId').setValue(book.get('compPtlBranchId'));
                                        form.getForm().findField('dateCreated').setValue(view.up('grid').timestampToDate(book.get('dateCreated')));
                                        fields.each(function (field) {
                                            field.enable();
                                        field.setReadOnly (true);});
                                        emp_comp.show();
                                    }
                                    else{
                                        var form = emp_comp.down('form');
                                        form.getForm().findField('compPtlBranchId').setValue(record.get('ptlBranchId'));
                                        form.getForm().findField('compPtlBranchId').setReadOnly(true);
                                        emp_comp.show();
                                    }
                                }
                                else {
                                    Ext.Msg.alert('Προσοχή', 'Δεν υπάρχει ενεργή αναγγελία για την συγκεκρημένη εγκατάσταση');
                                }
                            },
                            disabled: false
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

                                this.items[0].tooltip = 'Εκτύπωση Υποδείξεων';
                                return 'printme'; // css for icon

                            },
                            handler: function(view, rowIndex, colIndex, item, e, record, row) {
                                var emp_comp = Ext.create('widget.companydoctorannbranchform', {});
                                Ext.getCmp('compdoctorannbranch_save_submit_toolbar').hide();

                                var form = emp_comp.down('form');
                                var fields = form.getForm().getFields();

                                form.getForm().findField('a_new_form').setValue(false);
                                form.getForm().findField('NewebrBranchId').up('component').hide();

                                form.loadRecord(record);

                                grp = new Ext.form.DisplayField({
                                    fieldLabel: '',
                                    value:'Ακολουθούν οι υποδείξεις:'
                                });

                                form.add(grp);
                                var grid = view.up('grid');
                                pstore = Ext.StoreManager.get('company.TechnicianBooks.BOOK_NOTES_ALL');
                                pstore.getProxy().getReader().setRootProperty("_embedded.compTaAnnBookNote");
                                var ptlBranchId = record.get('ptlBranchId');
                                pstore.proxy.setUrl('/compTaAnnBookNote/search/findByPtlBranchId?ptlBranchId='+ptlBranchId);
                                pstore.load({
                                    callback: function(records, operation, success) {
                                        for (var i = 0; i < records.length; i++) {
                                            var formnote = Ext.create('widget.companytechnicianbooksnoteform', {});
                                            var newitem = formnote.down('form').items.getAt(1);
                                            newitem.items.get(0).setValue(grid.timestampToDate(records[i].get('dateCreated')));
                                            newitem.items.get(1).hide();
                                            newitem.items.get(2).setValue(records[i].get('authorName')); //add name
                                            newitem.items.get(3).setValue(records[i].get('notes'));
                                            newitem.items.get(4).hide();
                                            form.add(newitem);
                                        }

                                        fields = form.getForm().getFields();
                                        fields.each(function (field) {
                                            field.enable();
                                        field.setReadOnly (true);});

                                        emp_comp.overflowY="scroll";
                                        emp_comp.height="90%";
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
                    itemdblclick: 'onBranchItemDblClick',
                    refresh: 'onBranchViewRefresh',
                    itemclick: 'onBranchViewItemClick',
                    containerclick: 'onBranchViewContainerClick'
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
                            text: 'Ενεργοποίηση Βιβλίου',
                            listeners: {
                                click: 'onNewBook'
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
        },
        {
            xtype: 'gridpanel',
            timestampToDate: function(timestamp) {

                var pD = timestamp.replace(/[^0-9]+/g,' ').split(" ");
                return (pD[2]+"-"+pD[1]+"-"+pD[0]);
            },
            icon_dbl_click_handler: function(record) {
                var emp_comp = Ext.create('widget.companytechnicianannshipform', {});
                Ext.getCmp('comptechnicianannship_save_submit_toolbar').hide();

                var form = emp_comp.down('form');
                var fields = form.getForm().getFields();

                form.getForm().findField('a_new_form').setValue(false);
                form.getForm().findField('shipCapacity').hide();
                form.getForm().findField('shipType').hide();
                form.loadRecord(record);

                fields.each(function (field) {
                    field.enable();
                field.setReadOnly (true);});

                emp_comp.show();

            },
            flex: 1,
            autoScroll: true,
            height: '50%',
            hidden: true,
            id: 'companyTechnicianBooks_Ships',
            itemId: 'companyTechnicianBooks_Ships',
            title: 'Βιβλία Τεχνικών Ασφαλείας για Πλοία ή Ναυπηγικές Εργασίες',
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.PTL_TA_ANN_COMPANY_SHIPS',
            columns: [
                {
                    xtype: 'rownumberer'
                },
                {
                    xtype: 'gridcolumn',
                    filterField: true,
                    sortable: false,
                    dataIndex: 'shipName',
                    text: 'Όνομα Πλοίου',
                    flex: 10
                },
                {
                    xtype: 'gridcolumn',
                    filterField: true,
                    sortable: false,
                    dataIndex: 'shipImo',
                    text: 'Αριθμός ΙΜΟ',
                    flex: 10
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        var store2 = Ext.StoreMgr.lookup('company.TechnicianBooks.SHIPS_BOOKS');
                        var record = store2.findRecord('compShipId', value);
                        if(record)
                        return "Ναι";
                        else
                        return "Όχι";
                    },
                    sortable: false,
                    dataIndex: 'shipId',
                    text: 'Ενεργό Βιβλίο',
                    flex: 3
                },
                {
                    xtype: 'actioncolumn',
                    width: 30,
                    enableColumnHide: false,
                    sealed: true,
                    dataIndex: 'shipId',
                    hideable: false,
                    menuDisabled: true,
                    items: [
                        {
                            getClass: function(v, metadata, r, rowIndex, colIndex, store) {
                                var store2 = Ext.StoreMgr.lookup('company.TechnicianBooks.SHIPS_BOOKS');
                                var record = store2.findRecord('compShipId', v);
                                if (record){
                                    return 'bookopened'; // css for icon
                                }
                                else{
                                    if(r.get('taSannStatus')===1)
                                    return 'bookclosed'; // css for icon
                                    else
                                    return 'newwarning';

                                }


                            },
                            handler: function(view, rowIndex, colIndex, item, e, record, row) {
                                if(record.get('taSannStatus')===1){
                                    var emp_comp = Ext.create('widget.companytechnicianbooksshipbookform', {});


                                    var store = Ext.StoreMgr.lookup('company.TechnicianBooks.SHIPS_BOOKS');
                                    var book = store.findRecord('compShipId', record.get('shipId'));
                                    if (book){
                                        Ext.getCmp('comptechnicianshipbook_save_submit_toolbar').hide();
                                        var form = emp_comp.down('form');
                                        var fields = form.getForm().getFields();

                                        form.getForm().findField('a_new_form').setValue(false);

                                        form.getForm().findField('compShipId').setValue(book.get('compShipId'));
                                        form.getForm().findField('dateCreated').setValue(view.up('grid').timestampToDate(book.get('dateCreated')));
                                        fields.each(function (field) {
                                            field.enable();
                                        field.setReadOnly (true);});
                                        emp_comp.show();
                                    }
                                    else{
                                        var form = emp_comp.down('form');
                                        form.getForm().findField('compShipId').setValue(record.get('shipId'));
                                        form.getForm().findField('compShipId').setReadOnly(true);
                                        emp_comp.show();
                                    }
                                }
                                else {
                                    Ext.Msg.alert('Προσοχή', 'Δεν υπάρχει ενεργή αναγγελία για την συγκεκρημένη εγκατάσταση');
                                }
                            },
                            disabled: false
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

                                this.items[0].tooltip = 'Εκτύπωση Υποδείξεων';
                                return 'printme'; // css for icon

                            },
                            handler: function(view, rowIndex, colIndex, item, e, record, row) {
                                var emp_comp = Ext.create('widget.companytechnicianannshipform', {});
                                Ext.getCmp('comptechnicianannship_save_submit_toolbar').hide();


                                var form = emp_comp.down('form');
                                var fields = form.getForm().getFields();

                                form.getForm().findField('shipCapacity').hide();
                                form.getForm().findField('shipType').hide();

                                form.getForm().findField('a_new_form').setValue(false);

                                form.loadRecord(record);

                                grp = new Ext.form.DisplayField({
                                    fieldLabel: '',
                                    value:'Ακολουθούν οι υποδείξεις:'
                                });

                                form.add(grp);
                                var grid = view.up('grid');
                                pstore = Ext.StoreManager.get('company.TechnicianBooks.BOOK_NOTES_ALL');
                                pstore.getProxy().getReader().setRootProperty("_embedded.compTaSannBookNote");
                                var shipId = record.get('shipId');
                                pstore.proxy.setUrl('/compTaSannBookNote/search/findByCompShipId?compShipId='+shipId);
                                pstore.load({
                                    callback: function(records, operation, success) {
                                        for (var i = 0; i < records.length; i++) {
                                            var formnote = Ext.create('widget.companytechnicianbooksnoteform', {});
                                            var newitem = formnote.down('form').items.getAt(1);
                                            newitem.items.get(0).setValue(grid.timestampToDate(records[i].get('dateCreated')));
                                            newitem.items.get(1).hide();
                                            newitem.items.get(2).setValue(records[i].get('authorName')); //add name
                                            newitem.items.get(3).setValue(records[i].get('notes'));
                                            newitem.items.get(4).hide();
                                            form.add(newitem);
                                        }

                                        fields = form.getForm().getFields();
                                        fields.each(function (field) {
                                            field.enable();
                                        field.setReadOnly (true);});

                                        emp_comp.overflowY="scroll";
                                        emp_comp.height="90%";
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
                    itemdblclick: 'onShipItemDblClick',
                    refresh: 'onShipViewRefresh',
                    itemclick: 'onShipViewItemClick',
                    containerclick: 'onShipViewContainerClick'
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
                    store: 'company.PTL_TA_ANN_COMPANY_SHIPS'
                },
                {
                    xtype: 'toolbar',
                    dock: 'top',
                    items: [
                        {
                            xtype: 'button',
                            glyph: 'xf067@FontAwesome',
                            text: 'Ενεργοποίηση Βιβλίου',
                            listeners: {
                                click: 'onNewShipBook'
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
                if(Ext.getCmp('companyTechnicianBooks_Ships').getSelectionModel().getSelection().length>0){
                    var shipName = Ext.getCmp('companyTechnicianBooks_Ships').getSelectionModel().getSelection()[0].get('shipName');
                    if(record.get('read')===1){
                        var emp_comp = Ext.create('widget.companytechnicianbooksshipnoteform', {});
                        var form = emp_comp.down('form');
                        var fields = form.getForm().getFields();

                        var grid = this;
                        form.getForm().findField('a_new_form').setValue(false);

                        form.loadRecord(record);

                        form.getForm().findField('shipName').setValue(shipName);
                        form.getForm().findField('dateCreated').setValue(grid.timestampToDate(record.get('dateCreated')));
                        if(parseInt(record.get('read'))===1){
                            form.getForm().findField('readDate').setValue(grid.timestampToDate(record.get('readDate')));
                        }

                        fields.each(function (field) {
                            field.enable();
                        field.setReadOnly (true);});

                        emp_comp.show();
                    }
                    else{
                        noteId = record.get('noteId');
                        Ext.Ajax.request({
                            url: '/compTaSannBookNoteRead',
                            params: {'id': noteId},
                            method: "POST",
                            success: function() {
                                Ext.getCmp('companyTechnicianBooks_Book').store.reload();
                                var emp_comp = Ext.create('widget.companytechnicianbooksshipnoteform', {});
                                var form = emp_comp.down('form');
                                var fields = form.getForm().getFields();

                                var grid = Ext.getCmp('companyTechnicianBooks_Book');
                                form.getForm().findField('a_new_form').setValue(false);

                                form.loadRecord(record);

                                form.getForm().findField('shipName').setValue(shipName);
                                form.getForm().findField('dateCreated').setValue(grid.timestampToDate(record.get('dateCreated')));
                                if(parseInt(record.get('read'))===1){
                                    form.getForm().findField('readDate').setValue(grid.timestampToDate(record.get('readDate')));
                                }

                                fields.each(function (field) {
                                    field.enable();
                                field.setReadOnly (true);});

                                emp_comp.show();

                            }
                        });
                    }

                }

                if(Ext.getCmp('companyTechnicianBooks_Branches').getSelectionModel().getSelection().length>0){
                    var ptlBranchDescr = Ext.getCmp('companyTechnicianBooks_Branches').getSelectionModel().getSelection()[0].get('brDescr');
                    if(record.get('read')===1){
                        var emp_comp = Ext.create('widget.companytechnicianbooksnoteform', {});
                        var form = emp_comp.down('form');
                        var fields = form.getForm().getFields();

                        var grid = this;
                        form.getForm().findField('a_new_form').setValue(false);

                        form.loadRecord(record);

                        form.getForm().findField('compPtlBranchId').setValue(ptlBranchDescr);
                        form.getForm().findField('dateCreated').setValue(grid.timestampToDate(record.get('dateCreated')));
                        if(parseInt(record.get('read'))===1){
                            form.getForm().findField('readDate').setValue(grid.timestampToDate(record.get('readDate')));
                        }

                        fields.each(function (field) {
                            field.enable();
                        field.setReadOnly (true);});

                        emp_comp.show();
                    }
                    else{
                        noteId = record.get('noteId');
                        Ext.Ajax.request({
                            url: '/compTaAnnBookNoteRead',
                            params: {'id': noteId},
                            method: "POST",
                            success: function() {
                                Ext.getCmp('companyTechnicianBooks_Book').store.reload();
                                var emp_comp = Ext.create('widget.companytechnicianbooksnoteform', {});
                                var form = emp_comp.down('form');
                                var fields = form.getForm().getFields();

                                var grid = Ext.getCmp('companyTechnicianBooks_Book');
                                form.getForm().findField('a_new_form').setValue(false);

                                form.loadRecord(record);

                                form.getForm().findField('compPtlBranchId').setValue(ptlBranchDescr);
                                form.getForm().findField('dateCreated').setValue(grid.timestampToDate(record.get('dateCreated')));
                                if(parseInt(record.get('read'))===1){
                                    form.getForm().findField('readDate').setValue(grid.timestampToDate(record.get('readDate')));
                                }

                                fields.each(function (field) {
                                    field.enable();
                                field.setReadOnly (true);});

                                emp_comp.show();

                            }
                        });
                    }

                }
            },
            flex: 1,
            autoScroll: true,
            height: '50%',
            id: 'companyTechnicianBooks_Book',
            itemId: 'companyTechnicianBooks_Book',
            title: 'Καταχωρημένες Υποδείξεις',
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.TechnicianBooks.BOOK_NOTES',
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
                                    return 'newwarning'; // css for icon

                                }
                            },
                            handler: function(view, rowIndex, colIndex, item, e, record, row) {
                                view.up().icon_dbl_click_handler(record);
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
                    xtype: 'pagingtoolbar',
                    dock: 'bottom',
                    displayInfo: true,
                    firstText: 'Πρώτη Σελίδα',
                    lastText: 'Τελευταία Σελίδα',
                    nextText: 'Επόμενη Σελίδα',
                    prevText: 'Προηγούμενη Σελίδα',
                    refreshText: 'Ανανέωση',
                    store: 'company.TechnicianBooks.BOOK_NOTES'
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

    onBranchItemDblClick: function(dataview, record, item, index, e, eOpts) {
        dataview.up().icon_dbl_click_handler(record);

    },

    onBranchViewRefresh: function(dataview, eOpts) {
        var count = dataview.store.data.length;
        for (var i=0; i<dataview.store.data.length; i++){
        	var ptlBranchId = dataview.store.data.getAt(i).get('ptlBranchId');
        	var curId = dataview.store.data.getAt(i).get('id');
        	var flag=0;
        	var d=dataview.store.query('ptlBranchId',ptlBranchId);
        	var crecords = d.getCount();
        	for (var j=0; j<crecords; j++){
        		if (d.getAt(j).id!==curId){
        			if(d.getAt(j).get('taAnnStatus')===1){
        				if(flag===0){
        					dataview.store.removeAt(i);
        					flag=1;
        				}
        			}
        			else{
        				dataview.store.removeAt(dataview.store.findExact('id',d.getAt(j).id));
        			}
        		}
        	}
        }
        if (dataview.store.data.length<1 && dataview.store.filters.length>0){
            Ext.addon.MessagePop.msg("Προσοχή!", "Δεν υπάρχουν εγγραφές που να αντιστοιχούν στην αναζήτηση σας. Προβάλονται όλες οι εγγραφές της σελίδας.", 5000);
            dataview.up('grid').plugins[1].clearValues();
            dataview.store.reload();
        }
        Ext.getCmp('companyTechnicianBooks_Book').store.clearData();
        Ext.getCmp('companyTechnicianBooks_Book').view.refresh();
    },

    onBranchViewItemClick: function(dataview, record, item, index, e, eOpts) {
        var notes = Ext.getCmp('companyTechnicianBooks_Book');
        var ptlBranchId = Ext.getCmp('companyTechnicianBooks_Branches').getSelectionModel().getSelection()[0].get('ptlBranchId');
        notes.getStore().getProxy().getReader().setRootProperty("_embedded.compTaAnnBookNote");
        notes.store.proxy.setUrl('/compTaAnnBookNote/search/findByPtlBranchId?ptlBranchId='+ptlBranchId);
        notes.getStore().reload({
          callback: function(){
            notes.getView().refresh();
          }
        });
    },

    onBranchViewContainerClick: function(dataview, e, eOpts) {
        var bookgrid = Ext.getCmp('companyTechnicianBooks_Book');
        bookgrid.store.clearData();
        bookgrid.getView().refresh();
    },

    onNewBook: function(button, e, eOpts) {
            var emp_comp = Ext.create('widget.companytechnicianbooksbookform', {});
            emp_comp.show();
    },

    onShipItemDblClick: function(dataview, record, item, index, e, eOpts) {
        dataview.up().icon_dbl_click_handler(record);

    },

    onShipViewRefresh: function(dataview, eOpts) {
        var count = dataview.store.data.length;
        for (var i=0; i<dataview.store.data.length; i++){
        	var shipId = dataview.store.data.getAt(i).get('shipId');
        	var curId = dataview.store.data.getAt(i).get('id');
        	var flag=0;
        	var d=dataview.store.query('shipId',shipId);
        	var crecords = d.getCount();
        	for (var j=0; j<crecords; j++){
        		if (d.getAt(j).id!==curId){
        			if(d.getAt(j).get('taSannStatus')===1){
        				if(flag===0){
        					dataview.store.removeAt(i);
        					flag=1;
        				}
        			}
        			else{
        				dataview.store.removeAt(dataview.store.findExact('id',d.getAt(j).id));
        			}
        		}
        	}
        }
        if (dataview.store.data.length<1 && dataview.store.filters.length>0){
            Ext.addon.MessagePop.msg("Προσοχή!", "Δεν υπάρχουν εγγραφές που να αντιστοιχούν στην αναζήτηση σας. Προβάλονται όλες οι εγγραφές της σελίδας.", 5000);
            dataview.up('grid').plugins[1].clearValues();
            dataview.store.reload();
        }
        Ext.getCmp('companyTechnicianBooks_Book').store.clearData();
        Ext.getCmp('companyTechnicianBooks_Book').view.refresh();
    },

    onShipViewItemClick: function(dataview, record, item, index, e, eOpts) {
        var notes = Ext.getCmp('companyTechnicianBooks_Book');
        var shipId = Ext.getCmp('companyTechnicianBooks_Ships').getSelectionModel().getSelection()[0].get('shipId');
        notes.getStore().getProxy().getReader().setRootProperty("_embedded.compTaSannBookNote");
        notes.store.proxy.setUrl('/compTaSannBookNote/search/findByCompShipId?compShipId='+shipId);
        notes.getStore().reload({
          callback: function(){
            notes.getView().refresh();
          }
        });
    },

    onShipViewContainerClick: function(dataview, e, eOpts) {
        var bookgrid = Ext.getCmp('companyTechnicianBooks_Book');
        bookgrid.store.clearData();
        bookgrid.getView().refresh();
    },

    onNewShipBook: function(button, e, eOpts) {
            var emp_comp = Ext.create('widget.companytechnicianbooksshipbookform', {});
            emp_comp.show();
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
        if(Ext.getCmp('companyTechnicianBooks_Branches').getSelectionModel().getSelection().length>0){
            var ptlBranchId = Ext.getCmp('companyTechnicianBooks_Branches').getSelectionModel().getSelection()[0].get('ptlBranchId');
            dataview.getStore().getProxy().getReader().setRootProperty("_embedded.compTaAnnBookNote");
            dataview.store.proxy.setUrl('/compTaAnnBookNote/search/findByPtlBranchId?ptlBranchId='+ptlBranchId);
            }
        if(Ext.getCmp('companyTechnicianBooks_Ships').getSelectionModel().getSelection().length>0){
            var shipId = Ext.getCmp('companyTechnicianBooks_Ships').getSelectionModel().getSelection()[0].get('shipId');
            dataview.getStore().getProxy().getReader().setRootProperty("_embedded.compTaSannBookNote");
            dataview.store.proxy.setUrl('/compTaSannBookNote/search/findByCompShipId?compShipId='+shipId);

        }
    }

});