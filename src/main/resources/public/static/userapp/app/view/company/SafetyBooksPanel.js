/*
 * File: app/view/company/SafetyBooksPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.SafetyBooksPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.companysafetybookspanel',

    requires: [
        'MyApp.view.company.SafetyBooksPanelViewModel',
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
        type: 'companysafetybookspanel'
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
                var emp_comp = Ext.create('widget.companysafetybooksafetybookform', {});
                emp_comp.down('toolbar').getComponent('deletebutton').show();

                var form = emp_comp.down('form');
                var fields = form.getForm().getFields();

                form.getForm().findField('a_new_form').setValue(false);

                form.loadRecord(record);


                if(record.get('subStatus')===1){
                    form.getForm().findField('protNo').setValue();
                    form.getForm().findField('protDate').setValue();
                    form.getForm().findField('protYear').setValue();
                    if (record.get('projAddrPe')!==null && record.get('projAddrD')===null){
                        form.getForm().findField('projAddrPe').setDisabled(false);
                        form.getForm().findField('projAddrD').setDisabled(false);
                    }
                    else if (record.get('projAddrPe')!==null && record.get('projAddrD')!==null){
                        form.getForm().findField('projAddrPe').setDisabled(false);
                        form.getForm().findField('projAddrD').setDisabled(false);
                        form.getForm().findField('projAddrK').setDisabled(false);
                    }
                    else if (record.get('projAddrP')!==null && record.get('projAddrPe')===null){
                        form.getForm().findField('projAddrPe').setDisabled(false);
                    }



                }
                else{

                    form.getForm().findField('protDate').setValue( form.timestampToDate(record.get('protDate')) );
                    Ext.getCmp('compsafetybook_save_submit_toolbar').hide();

                    /*if (record.get('department')>0 || record.get('department')!==null){
                        var store2 = Ext.StoreMgr.lookup( 'shared.SEPE_DEPT' );
                        store2.getProxy().setExtraParam("id", record.get('department'));
                        store2.load( { callback : function(records, operation, success) {
                            form.getForm().findField('department').setValue(record.get('department').toString());
                        }
                    });
                }*/

                    if (record.get('department')>0 || record.get('department')!==null){
                        var store2 = Ext.StoreMgr.lookup( 'shared.SEPE_DEPT' );
                        store2.getProxy().url='/TSepeDepartment/search/findById'
                        store2.getProxy().setExtraParam("cdId", record.get('department'));
                        store2.load( { callback : function(records, operation, success) {
                            var sepeDept = Ext.decode(operation._response.responseText);
                            if (sepeDept !== null)
                                form.getForm().findField('department').setValue(sepeDept.cdCode + ' - ' + sepeDept.cdText);
                        }
                        });
                    }

                //get message
                if (record.get('caseId')!==null && record.get('docId')!==null){

                    form.getForm().findField('StatusMsg').show();
                    var store3 = Ext.StoreMgr.lookup( 'shared.CASE_MESSAGE' );
                    store3.getProxy().setExtraParams({"caseId": record.get('caseId'),"docId": record.get('docId')});
                    store3.load( { callback : function(records, operation, success) {
                        if(records!==null)
                    record.getField("StatusMsg").value=records[0].getData().cdText;}
                });
            }



            Ext.getCmp('bookcontrsadd').destroy();
            Ext.getCmp('bookcontrsdel').destroy();
            Ext.getCmp('bookengsadd').destroy();
            Ext.getCmp('bookengsdel').destroy();
        }




        var store1 = Ext.StoreMgr.lookup( 'company.SafetyBooks.Contr' );
        store1.getProxy().setUrl(record.get('safetyBookContrs'));
        store1.load( { callback : function(records, operation, success) {
            for (var i=0; i<records.length; i++){
                var projscontr = Ext.create('widget.companysafetybookcontr', {});
                projscontr.items.getAt(0).items.getAt(0).setValue(i+1+". ");
                projscontr.items.getAt(1).items.getAt(0).items.getAt(0).setValue(parseInt(records[i].get('type')));
                projscontr.items.getAt(1).items.getAt(0).items.getAt(1).setValue(records[i].get('contractorSpecialty').toString());
                projscontr.items.getAt(1).items.getAt(0).items.getAt(2).setValue(records[i].get('firstname'));
                projscontr.items.getAt(1).items.getAt(0).items.getAt(3).setValue(records[i].get('lastname'));
                projscontr.items.getAt(1).items.getAt(0).items.getAt(4).setValue(records[i].get('birthplace'));
                projscontr.items.getAt(1).items.getAt(0).items.getAt(5).setValue(form.timestampToDate(records[i].get('birthdate')));
                projscontr.items.getAt(1).items.getAt(0).items.getAt(6).setValue(records[i].get('addr'));
                projscontr.items.getAt(1).items.getAt(0).items.getAt(7).setValue(records[i].get('cardNumber'));
                projscontr.items.getAt(1).items.getAt(0).items.getAt(8).setValue(records[i].get('cardIssuingAuth'));
                projscontr.items.getAt(1).items.getAt(0).items.getAt(9).setValue(records[i].get('afm'));
                projscontr.items.getAt(1).items.getAt(0).items.getAt(10).setValue(form.timestampToDate(records[i].get('dateStart')));
                projscontr.items.getAt(1).items.getAt(0).items.getAt(11).setValue(form.timestampToDate(records[i].get('dateEnd')));
                Ext.getCmp('bookcontrs').add(projscontr);
                form.getForm().findField('projscontrsnum').setValue(parseInt(i+1));
            }
            form.getForm().findField('projscontrsnum').setValue(parseInt(records.length));
            store1 = Ext.StoreMgr.lookup( 'company.SafetyBooks.Eng' );
            store1.getProxy().setUrl(record.get('safetyBookEngs'));
            store1.load( { callback : function(records, operation, success) {
                for (var i=0; i<records.length; i++){
                    var projeng = Ext.create('widget.companysafetybookeng', {});
                    projeng.items.getAt(0).items.getAt(0).setValue(i+1+". ");
                    projeng.items.getAt(1).items.getAt(0).items.getAt(0).setValue(records[i].get('firstname'));
                    projeng.items.getAt(1).items.getAt(0).items.getAt(1).setValue(records[i].get('lastname'));
                    projeng.items.getAt(1).items.getAt(0).items.getAt(2).setValue(records[i].get('engineerSpeciality'));
                    projeng.items.getAt(1).items.getAt(0).items.getAt(3).setValue(records[i].get('birthplace'));
                    projeng.items.getAt(1).items.getAt(0).items.getAt(4).setValue(form.timestampToDate(records[i].get('birthdate')));
                    projeng.items.getAt(1).items.getAt(0).items.getAt(5).setValue(records[i].get('addr'));
                    projeng.items.getAt(1).items.getAt(0).items.getAt(6).setValue(records[i].get('cardNumber'));
                    projeng.items.getAt(1).items.getAt(0).items.getAt(7).setValue(records[i].get('cardIssuingAuth'));
                    projeng.items.getAt(1).items.getAt(0).items.getAt(8).setValue(records[i].get('afm'));
                    projeng.items.getAt(1).items.getAt(0).items.getAt(9).setValue(records[i].get('teeNum'));
                    Ext.getCmp('bookengs').add(projeng);
                }
                form.getForm().findField('projsengsnum').setValue(parseInt(i+1));

                if(record.get('subStatus')===2){

                    fields = form.getForm().getFields();
                    fields.each(function (field) {
                        field.enable();
                        field.setReadOnly (true);
                    });

                }
                form.getForm().findField('ownerBirthdate').setValue( form.timestampToDate(record.get('ownerBirthdate')) );
                emp_comp.show();


            } });
        } });



            },
            flex: 1,
            autoScroll: true,
            height: '50%',
            id: 'companySafetyBooks_Books',
            itemId: 'companySafetyBooks_Books',
            title: 'Ημερολόγια Μέτρων Ασφάλειας',
            autoLoad: true,
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.SafetyBooks.BOOKS_GRID',
            columns: [
                {
                    xtype: 'rownumberer'
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        return (value+", "+record.get('projAddrTk'));

                    },
                    sortable: false,
                    dataIndex: 'projAddr',
                    text: 'Διεύθυνση Έργου',
                    flex: 5
                },
                {
                    xtype: 'gridcolumn',
                    sortable: false,
                    dataIndex: 'projLicenceNum',
                    text: 'Αρ. Άδειας Έργου',
                    flex: 5
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {

                        if(record.get('subStatus')===2)
                        {
                            var pD = value.replace(/[^0-9]+/g,' ').split(" ");
                            return (pD[2]+"-"+pD[1]+"-"+pD[0]);
                        }
                        else{
                            return("Μη καταχωρημένο");
                        }

                    },
                    sortable: false,
                    dataIndex: 'protDate',
                    text: 'Ημερομηνία Δημιουργίας',
                    flex: 5
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
                                if (r.get('subStatus')===1){
                                    this.items[0].tooltip = 'Επεξεργασία';
                                    return 'editme'; // css for icon
                                }
                                else{
                                    this.items[0].tooltip = 'Προβολή';
                                    return 'viewme'; // css for icon

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
                    itemdblclick: 'onBookItemDblClick',
                    refresh: 'onBookViewRefresh',
                    itemclick: 'onBookViewItemClick',
                    containerclick: 'onBookViewContainerClick'
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
                    store: 'company.SafetyBooks.BOOKS_GRID'
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
            icon_dbl_click_handler: function(record) {
                var emp_comp = Ext.create('widget.companysafetybooknoteform', {});

                var form = emp_comp.down('form');
                var fields = form.getForm().getFields();

                form.getForm().findField('a_new_form').setValue(false);

                form.loadRecord(record);
                if(record.get('creationDate')!==null)
                form.getForm().findField('creationDate').setValue( form.timestampToDate(record.get('creationDate')) );
                if(record.get('updateDate')!==null)
                form.getForm().findField('updateDate').setValue( form.timestampToDate(record.get('updateDate')) );

                form.getForm().findField('compSecDiaryEngId').setValue( record.get('compSecDiaryEngId').toString() );

                emp_comp.show();

            },
            flex: 1,
            autoScroll: true,
            height: '50%',
            id: 'companySafetyBooks_Notes',
            itemId: 'companySafetyBooks_Notes',
            title: 'Καταχωρήσεις Ημερολογίου',
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.SafetyBooks.BOOK_NOTES',
            columns: [
                {
                    xtype: 'rownumberer',
                    hidden: true
                },
                {
                    xtype: 'gridcolumn',
                    sortable: false,
                    align: 'center',
                    dataIndex: 'aa',
                    text: 'A/A',
                    flex: 1
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        var store2 = Ext.StoreMgr.lookup('company.SafetyBooks.Eng');
                        return store2.findRecord('engId', record.get("compSecDiaryEngId")).get('fullname');
                    },
                    sortable: false,
                    dataIndex: 'compSecDiaryEngId',
                    text: 'Εργολάβος / Υπεργολάβος',
                    flex: 3,
                    filter: {
                        type: 'string',
                        emptyText: 'Εισαγωγή κειμένου...'
                    }
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        var store2 = Ext.StoreMgr.lookup('company.SafetyBooks.Eng');
                        return store2.findRecord('engId', record.get("compSecDiaryEngId")).get('engineerSpeciality');
                    },
                    filterField: true,
                    sortable: false,
                    dataIndex: 'compSecDiaryEngId',
                    text: 'Ειδικότητα',
                    flex: 3
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        var pD = value.replace(/[^0-9]+/g,' ').split(" ");
                        return (pD[2]+"-"+pD[1]+"-"+pD[0])+"/"+(pD[3]+":"+pD[4]);
                    },
                    sortable: false,
                    dataIndex: 'updateDate',
                    text: 'Ημερομηνία/Ωρα Επεξεργασίας',
                    flex: 3
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
                    itemdblclick: 'onNotesItemDblClick',
                    refresh: 'onNotesViewRefresh',
                    beforerefresh: 'onNotesViewBeforeRefresh'
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
                    store: 'company.SafetyBooks.BOOK_NOTES'
                },
                {
                    xtype: 'toolbar',
                    dock: 'top',
                    items: [
                        {
                            xtype: 'button',
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα Καταχώρηση',
                            listeners: {
                                click: 'onNewNote'
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

    onBookItemDblClick: function(dataview, record, item, index, e, eOpts) {
        dataview.up().icon_dbl_click_handler(record);

    },

    onBookViewRefresh: function(dataview, eOpts) {
        if (dataview.store.data.length<1 && dataview.store.filters.length>0){
            Ext.addon.MessagePop.msg("Προσοχή!", "Δεν υπάρχουν εγγραφές που να αντιστοιχούν στην αναζήτηση σας. Προβάλονται όλες οι εγγραφές της σελίδας.", 5000);
            dataview.up('grid').plugins[1].clearValues();
            dataview.store.reload();
        }
        Ext.getCmp('companySafetyBooks_Notes').store.clearData();
        Ext.getCmp('companySafetyBooks_Notes').view.refresh();
    },

    onBookViewItemClick: function(dataview, record, item, index, e, eOpts) {
        var notesgrid = Ext.getCmp('companySafetyBooks_Notes');
        var bookId = record.get('bookId');
        var store1 = Ext.StoreMgr.lookup( 'company.SafetyBooks.Eng' );
        store1.getProxy().setUrl(record.get('safetyBookEngs'));
        store1.load( { callback : function(records, operation, success) {
            notesgrid.store.proxy.setUrl('compSecDiaryEntry/search/findByCompSecDiaryId?compSecDiaryId='+bookId);
            notesgrid.getStore().reload({
            callback: function(){
                    notesgrid.getView().refresh();
                  }
                });
            }
                     });


    },

    onBookViewContainerClick: function(dataview, e, eOpts) {
        var notesgrid = Ext.getCmp('companySafetyBooks_Notes');
        notesgrid.store.clearData();
        notesgrid.getView().refresh();
    },

    onNotesItemDblClick: function(dataview, record, item, index, e, eOpts) {
        dataview.up().icon_dbl_click_handler(record);

    },

    onNotesViewRefresh: function(dataview, eOpts) {
        if (dataview.store.data.length<1 && dataview.store.filters.length>0){
            Ext.addon.MessagePop.msg("Προσοχή!", "Δεν υπάρχουν εγγραφές που να αντιστοιχούν στην αναζήτηση σας. Προβάλονται όλες οι εγγραφές της σελίδας.", 5000);
            dataview.up('grid').plugins[1].clearValues();
            dataview.store.reload();
        }
    },

    onNotesViewBeforeRefresh: function(dataview, eOpts) {
        if(Ext.getCmp('companySafetyBooks_Books').getSelectionModel().getSelection().length>0){
            var bookId = Ext.getCmp('companySafetyBooks_Books').getSelectionModel().getSelection()[0].get('bookId');
            dataview.store.proxy.setUrl('compSecDiaryEntry/search/findByCompSecDiaryId?compSecDiaryId='+bookId);
        }
    },

    onNewNote: function(button, e, eOpts) {
        if(Ext.getCmp('companySafetyBooks_Books').getSelectionModel().getSelection().length>0){

            if(Ext.getCmp('companySafetyBooks_Books').getSelectionModel().getSelection()[0].get('subStatus').toString()==="2"){
                var emp_comp = Ext.create('widget.companysafetybooknoteform', {
                });
                var incNum = parseInt(Ext.getCmp('companySafetyBooks_Notes').store.proxy.reader.rawData.page.totalElements)+1;
                emp_comp.down().getForm().findField('aa').setValue(incNum);
                emp_comp.down().getForm().findField('compSecDiaryId').setValue(Ext.getCmp('companySafetyBooks_Books').getSelectionModel().getSelection()[0].get('bookId'));
                emp_comp.show();
            }
            else{
                Ext.Msg.alert('Προσοχή!', 'Το ημερολόγιο είναι αποθηκευμένο. Πρέπει να καταχωρηθεί.');
            }
        }
        else {
                 Ext.Msg.alert('Προσοχή!', 'Δεν έχετε επιλέξει κάποιο ημερολόγιο από την παραπάνω λίστα.');
        }


    }

});