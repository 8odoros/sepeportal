/*
 * File: app/view/company/ProjectsBooksPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.ProjectsBooksPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.companyprojectsbookspanel',

    requires: [
        'MyApp.view.company.ProjectsBooksPanelViewModel',
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
        type: 'companyprojectsbookspanel'
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
                var emp_comp = Ext.create('widget.companyprojectsbooksprojectsform', {});

                var form = emp_comp.down('form');
                var fields = form.getForm().getFields();

                form.getForm().findField('a_new_form').setValue(false);

                form.loadRecord(record);

                if(record.get('creationDate')!==null)
                form.getForm().findField('creationDateView').setValue( form.timestampToDate(record.get('creationDate')) );
                if(record.get('empDateOfBirth')!==null)
                form.getForm().findField('empDateOfBirthView').setValue( form.timestampToDate(record.get('empDateOfBirth')) );
                if(record.get('contrDateOfBirth')!==null)
                form.getForm().findField('contrDateOfBirthView').setValue( form.timestampToDate(record.get('contrDateOfBirth')) );
                if(record.get('subContrDateOfBirth')!==null)
                form.getForm().findField('subContrDateOfBirthView').setValue( form.timestampToDate(record.get('subContrDateOfBirth')) );

                /*if (record.get('sepeDept')>0 || record.get('sepeDept')!==null){
                    var store2 = Ext.StoreMgr.lookup( 'shared.SEPE_DEPT' );
                    store2.getProxy().setExtraParam("id", record.get('sepeDept'));
                    store2.load( { callback : function(records, operation, success) {
                        form.getForm().findField('sepeDept').setValue(record.get('sepeDept').toString());
                    }
                });
            }*/

                if (record.get('sepeDept')>0 || record.get('sepeDept')!==null){
                    var store2 = Ext.StoreMgr.lookup( 'shared.SEPE_DEPT' );
                    store2.getProxy().url='/TSepeDepartment/search/findById'
                    store2.getProxy().setExtraParam("cdId", record.get('sepeDept'));
                    store2.load( { callback : function(records, operation, success) {
                        var sepeDept = Ext.decode(operation._response.responseText);
                        if (sepeDept !== null)
                            form.getForm().findField('sepeDept').setValue(sepeDept.cdCode + ' - ' + sepeDept.cdText);
                    }
                    });
                }    


            form.getForm().findField('projectAddrPe').setDisabled(false);
            form.getForm().findField('projectAddrD').setDisabled(false);
            form.getForm().findField('projectAddrK').setDisabled(false);
            form.getForm().findField('housedCompanyAddrPe').setDisabled(false);
            form.getForm().findField('housedCompanyAddrD').setDisabled(false);
            form.getForm().findField('housedCompanyAddrK').setDisabled(false);

            emp_comp.show();

            },
            flex: 1,
            autoScroll: true,
            height: '33%',
            id: 'companyProjectsBooks_Projects',
            itemId: 'companyProjectsBooks_Projects',
            title: 'Βιβλια Έργων',
            autoLoad: true,
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.ProjectsBooks.Projects',
            columns: [
                {
                    xtype: 'rownumberer'
                },
                {
                    xtype: 'gridcolumn',
                    filterField: true,
                    sortable: false,
                    dataIndex: 'projectDescription',
                    text: 'Περιγραφή',
                    flex: 10
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {

                        var pD = value.replace(/[^0-9]+/g,' ').split(" ");
                        return (pD[2]+"-"+pD[1]+"-"+pD[0]);

                    },
                    sortable: false,
                    dataIndex: 'creationDate',
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
                    itemdblclick: 'onProjectItemDblClick',
                    refresh: 'onProjectViewRefresh',
                    itemclick: 'onProjectViewItemClick',
                    containerclick: 'onProjectViewContainerClick'
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
                    store: 'company.ProjectsBooks.Projects'
                },
                {
                    xtype: 'toolbar',
                    dock: 'top',
                    items: [
                        {
                            xtype: 'button',
                            glyph: 'xf067@FontAwesome',
                            text: 'Δημιουργία Νέου Βιβλίου Έργου',
                            listeners: {
                                click: 'onNewProjectBook'
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
            flex: 1,
            autoScroll: true,
            height: '33%',
            id: 'companyProjectsBooks_Daily',
            itemId: 'companyProjectsBooks_Daily',
            title: 'Ημερήσιες Καρτέλες',
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.ProjectsBooks.Daily',
            columns: [
                {
                    xtype: 'rownumberer'
                },
                {
                    xtype: 'gridcolumn',
                    sortable: false,
                    align: 'center',
                    dataIndex: 'incNum',
                    text: 'Αριθμός Δελτίου',
                    flex: 1
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {

                        var pD = value.replace(/[^0-9]+/g,' ').split(" ");
                        return (pD[2]+"-"+pD[1]+"-"+pD[0]);

                    },
                    sortable: false,
                    dataIndex: 'cardDate',
                    text: 'Ημερομηνία Δελτίου',
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

                                this.items[0].tooltip = 'Εκτύπωση Ημερήσιων Δελτίων Προσωπικού Μέρας';
                                return 'printme'; // css for icon

                            },
                            handler: function(view, rowIndex, colIndex, item, e, record, row) {
                                var emp_comp = Ext.create('widget.companyprojectsbooksprojectsform', {});

                                var formbig = emp_comp.down('form');
                                var form = emp_comp.down('form');
                                var fields = formbig.getForm().getFields();

                                formbig.getForm().findField('a_new_form').setValue("groupprint");

                                var precord = Ext.getCmp('companyProjectsBooks_Projects').getSelectionModel().getSelection()[0];

                                formbig.loadRecord(precord);

                                if(precord.get('creationDate')!==null)
                                formbig.getForm().findField('creationDateView').setValue( formbig.timestampToDate(precord.get('creationDate')) );
                                if(precord.get('empDateOfBirth')!==null)
                                formbig.getForm().findField('empDateOfBirthView').setValue( formbig.timestampToDate(precord.get('empDateOfBirth')) );
                                if(precord.get('contrDateOfBirth')!==null)
                                formbig.getForm().findField('contrDateOfBirthView').setValue( formbig.timestampToDate(precord.get('contrDateOfBirth')) );
                                if(precord.get('subContrDateOfBirth')!==null)
                                formbig.getForm().findField('subContrDateOfBirthView').setValue( formbig.timestampToDate(precord.get('subContrDateOfBirth')) );

                                /*if (precord.get('sepeDept')>0 || precord.get('sepeDept')!==null){
                                    var store2 = Ext.StoreMgr.lookup( 'shared.SEPE_DEPT' );
                                    store2.getProxy().setExtraParam("id", precord.get('sepeDept'));
                                    store2.load( { callback : function(records, operation, success) {
                                        formbig.getForm().findField('sepeDept').setValue(precord.get('sepeDept').toString());
                                    }
                                });
                            }*/

                                if (record.get('sepeDept')>0 || record.get('sepeDept')!==null){
                                    var store2 = Ext.StoreMgr.lookup( 'shared.SEPE_DEPT' );
                                    store2.getProxy().url='/TSepeDepartment/search/findById'
                                    store2.getProxy().setExtraParam("cdId", record.get('sepeDept'));
                                    store2.load( { callback : function(records, operation, success) {
                                        var sepeDept = Ext.decode(operation._response.responseText);
                                        if (sepeDept !== null)
                                            form.getForm().findField('sepeDept').setValue(sepeDept.cdCode + ' - ' + sepeDept.cdText);
                                    }
                                    });
                                }    

                            formbig.getForm().findField('projectAddrPe').setDisabled(false);
                            formbig.getForm().findField('projectAddrD').setDisabled(false);
                            formbig.getForm().findField('projectAddrK').setDisabled(false);
                            formbig.getForm().findField('housedCompanyAddrPe').setDisabled(false);
                            formbig.getForm().findField('housedCompanyAddrD').setDisabled(false);
                            formbig.getForm().findField('housedCompanyAddrK').setDisabled(false);



                            fields.each(function (field) {
                                field.enable();
                            field.setReadOnly (true);});

                            Ext.getCmp('compproject_save_submit_toolbar').hide();
                            //emp_comp.show();

                            var personelgrid = Ext.getCmp('companyProjectsBooks_Personel');
                            var dailycardid = record.get('dailycardid');
                            pstore = Ext.StoreManager.get('company.ProjectsBooks.PersonelAll');
                            pstore.proxy.setUrl('/companyPersonnelBook/search/findByDailyCardId?dailyCardId='+dailycardid);
                            pstore.load({
                                callback: function(){

                                    for (var i=0; i<pstore.data.length; i++){
                                        record = pstore.data.items[i];

                                        var emp_comp1 = Ext.create('widget.companyprojectsbookspersonelform', {});

                                        form[i] = emp_comp1.down('form');
                                        var fields = form[i].getForm().getFields();

                                        form[i].getForm().findField('a_new_form').setValue(false);

                                        form[i].loadRecord(record);

                                        if(record.get('editTime')!==null)
                                        form[i].getForm().findField('editDateView').setValue( form[i].timestampToDate(record.get('editTime')) );
                                        var pD = record.get('editTime').replace(/[^0-9]+/g,' ').split(" ");
                                        form[i].getForm().findField('editTimeView').setValue( pD[3]+":"+pD[4] );


                                        if(record.get('submitTime')!==null)
                                        form[i].getForm().findField('submitDateView').setValue( form[i].timestampToDate(record.get('submitTime')) );
                                        pD = record.get('submitTime').replace(/[^0-9]+/g,' ').split(" ");
                                        form[i].getForm().findField('submitTimeView').setValue( pD[3]+":"+pD[4] );

                                        if(record.get('dateOfRecruitment')!==null)
                                        form[i].getForm().findField('dateOfRecruitmentView').setValue( form[i].timestampToDate(record.get('dateOfRecruitment')) );
                                        if(record.get('restingDay')!==null)
                                        form[i].getForm().findField('restingDayView').setValue( form[i].timestampToDate(record.get('restingDay')) );

                                        var dailyCardDate = Ext.getCmp('companyProjectsBooks_Daily').getSelectionModel().getSelection()[0].get('cardDate');
                                        form[i].getForm().findField('dailyCardDate').setValue( form[i].timestampToDate(dailyCardDate));
                                        var dailyCardId = Ext.getCmp('companyProjectsBooks_Daily').getSelectionModel().getSelection()[0].get('incNum');
                                        form[i].getForm().findField('dailyCardId').setValue(dailyCardId);

                                        fields.each(function (field) {
                                            field.enable();
                                        field.setReadOnly (true);});

                                    }
                                    grp = new Ext.form.DisplayField({
                                        fieldLabel: '',
                                        value:'Ακολουθούν οι καρτέλες προσωπικού για το συγκεκριμένο Ημερήσιο Δελτίο:'
                                    });
                                    formbig.add(grp);
                                    for (i=0; i<pstore.data.length; i++){
                                        form[i].id="projectsbookpersonel"+i;
                                        grp = new Ext.form.FieldSet({
                                            title: ''
                                        });
                                        grp.add(form[i]);
                                        formbig.add(grp);
                                    }
                                    emp_comp.show();

                                }
                            });

                            }
                        }
                    ]
                }
            ],
            viewConfig: {
                frame: true,
                preserveScrollOnRefresh: true,
                listeners: {
                    refresh: 'onDailyViewRefresh',
                    beforerefresh: 'onDailyViewBeforeRefresh',
                    containerclick: 'onDailyViewContainerClick',
                    itemclick: 'onDailyViewItemClick'
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
                    store: 'company.ProjectsBooks.Daily'
                },
                {
                    xtype: 'toolbar',
                    dock: 'top',
                    items: [
                        {
                            xtype: 'button',
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα Καρτέλα Ημέρας',
                            listeners: {
                                click: 'onNewDaily'
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
            icon_dbl_click_handler: function(record) {
                var emp_comp = Ext.create('widget.companyprojectsbookspersonelform', {});

                var form = emp_comp.down('form');
                var fields = form.getForm().getFields();

                form.getForm().findField('a_new_form').setValue(false);

                form.loadRecord(record);

                if(record.get('editTime')!==null)
                form.getForm().findField('editDateView').setValue( form.timestampToDate(record.get('editTime')) );
                var pD = record.get('editTime').replace(/[^0-9]+/g,' ').split(" ");
                form.getForm().findField('editTimeView').setValue( pD[3]+":"+pD[4] );


                if(record.get('submitTime')!==null)
                form.getForm().findField('submitDateView').setValue( form.timestampToDate(record.get('submitTime')) );
                var pD = record.get('submitTime').replace(/[^0-9]+/g,' ').split(" ");
                form.getForm().findField('submitTimeView').setValue( pD[3]+":"+pD[4] );

                if(record.get('dateOfRecruitment')!==null)
                form.getForm().findField('dateOfRecruitmentView').setValue( form.timestampToDate(record.get('dateOfRecruitment')) );
                if(record.get('restingDay')!==null)
                form.getForm().findField('restingDayView').setValue( form.timestampToDate(record.get('restingDay')) );

                var dailyCardDate = Ext.getCmp('companyProjectsBooks_Daily').getSelectionModel().getSelection()[0].get('cardDate');
                form.getForm().findField('dailyCardDate').setValue( form.timestampToDate(dailyCardDate));
                var dailyCardId = Ext.getCmp('companyProjectsBooks_Daily').getSelectionModel().getSelection()[0].get('incNum');
                form.getForm().findField('dailyCardId').setValue(dailyCardId);

                emp_comp.show();

            },
            flex: 1,
            autoScroll: true,
            height: '33%',
            id: 'companyProjectsBooks_Personel',
            itemId: 'companyProjectsBooks_Personel',
            title: 'Καρτέλες Προσωπικού',
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.ProjectsBooks.Personel',
            columns: [
                {
                    xtype: 'rownumberer'
                },
                {
                    xtype: 'gridcolumn',
                    sortable: false,
                    align: 'center',
                    dataIndex: 'incNum',
                    text: 'A/A',
                    flex: 1
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        return record.get("name")+"  "+record.get("surname");

                    },
                    sortable: false,
                    dataIndex: 'name',
                    text: 'Ονοματεπώνυμο Εργαζομένου',
                    flex: 3,
                    filter: {
                        type: 'string',
                        emptyText: 'Εισαγωγή κειμένου...'
                    }
                },
                {
                    xtype: 'gridcolumn',
                    filterField: true,
                    sortable: false,
                    dataIndex: 'speciality',
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
                    dataIndex: 'editTime',
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
                    itemdblclick: 'onPersonelItemDblClick',
                    refresh: 'onPersonelViewRefresh',
                    beforerefresh: 'onPersonelViewBeforeRefresh'
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
                    store: 'company.ProjectsBooks.Personel'
                },
                {
                    xtype: 'toolbar',
                    dock: 'top',
                    items: [
                        {
                            xtype: 'button',
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα Καρτέλα Απασχολούμενου',
                            listeners: {
                                click: 'onNewPersonel'
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

    onProjectItemDblClick: function(dataview, record, item, index, e, eOpts) {
        dataview.up().icon_dbl_click_handler(record);

    },

    onProjectViewRefresh: function(dataview, eOpts) {
        if (dataview.store.data.length<1 && dataview.store.filters.length>0){
            Ext.addon.MessagePop.msg("Προσοχή!", "Δεν υπάρχουν εγγραφές που να αντιστοιχούν στην αναζήτηση σας. Προβάλονται όλες οι εγγραφές της σελίδας.", 5000);
            dataview.up('grid').plugins[1].clearValues();
            dataview.store.reload();
        }
        Ext.getCmp('companyProjectsBooks_Daily').store.clearData();
        Ext.getCmp('companyProjectsBooks_Personel').store.clearData();
        Ext.getCmp('companyProjectsBooks_Daily').view.refresh();
        Ext.getCmp('companyProjectsBooks_Personel').view.refresh();
    },

    onProjectViewItemClick: function(dataview, record, item, index, e, eOpts) {
        var dailygrid = Ext.getCmp('companyProjectsBooks_Daily');
        var projectid = Ext.getCmp('companyProjectsBooks_Projects').getSelectionModel().getSelection()[0].get('projectid');
        dailygrid.store.proxy.setUrl('/companyDailyCards/search/findByProjectId?projectId='+projectid);
        dailygrid.getStore().reload({
          callback: function(){
            dailygrid.getView().refresh();
            var personelgrid = Ext.getCmp('companyProjectsBooks_Personel');
            personelgrid.store.clearData();
            personelgrid.getView().refresh();
          }
        });
    },

    onProjectViewContainerClick: function(dataview, e, eOpts) {
        var dailygrid = Ext.getCmp('companyProjectsBooks_Daily');
        dailygrid.store.clearData();
        dailygrid.getView().refresh();
        var personelgrid = Ext.getCmp('companyProjectsBooks_Personel');
        personelgrid.store.clearData();
        personelgrid.getView().refresh();
    },

    onNewProjectBook: function(button, e, eOpts) {
        var emp_comp = Ext.create('widget.companyprojectsbooksprojectsform', {
        });
        emp_comp.show();
    },

    onDailyViewRefresh: function(dataview, eOpts) {
        if (dataview.store.data.length<1 && dataview.store.filters.length>0){
            Ext.addon.MessagePop.msg("Προσοχή!", "Δεν υπάρχουν εγγραφές που να αντιστοιχούν στην αναζήτηση σας. Προβάλονται όλες οι εγγραφές της σελίδας.", 5000);
            dataview.up('grid').plugins[1].clearValues();
            dataview.store.reload();
        }
        Ext.getCmp('companyProjectsBooks_Personel').store.clearData();
        Ext.getCmp('companyProjectsBooks_Personel').view.refresh();
    },

    onDailyViewBeforeRefresh: function(dataview, eOpts) {
        if(Ext.getCmp('companyProjectsBooks_Projects').getSelectionModel().getSelection().length>0){
            var projectid = Ext.getCmp('companyProjectsBooks_Projects').getSelectionModel().getSelection()[0].get('projectid');
            dataview.store.proxy.setUrl('/companyDailyCards/search/findByProjectId?projectId='+projectid);
        }
    },

    onDailyViewContainerClick: function(dataview, e, eOpts) {
        var personelgrid = Ext.getCmp('companyProjectsBooks_Personel');
        personelgrid.store.clearData();
        personelgrid.getView().refresh();
    },

    onDailyViewItemClick: function(dataview, record, item, index, e, eOpts) {
        var personelgrid = Ext.getCmp('companyProjectsBooks_Personel');
        var dailycardid = Ext.getCmp('companyProjectsBooks_Daily').getSelectionModel().getSelection()[0].get('dailycardid');
        personelgrid.store.proxy.setUrl('/companyPersonnelBook/search/findByDailyCardId?dailyCardId='+dailycardid);
        personelgrid.getStore().reload({
          callback: function(){
            personelgrid.getView().refresh();
          }
        });
    },

    onNewDaily: function(button, e, eOpts) {
        if(Ext.getCmp('companyProjectsBooks_Projects').getSelectionModel().getSelection().length>0){
            var emp_comp = Ext.create('widget.companyprojectsbooksdailyform', {
        });
            var incNum = parseInt(Ext.getCmp('companyProjectsBooks_Daily').store.proxy.reader.rawData.page.totalElements)+1;
            emp_comp.down().getForm().findField('incNum').setValue(incNum);
            emp_comp.show();
        }
        else
            Ext.Msg.alert('Προσοχή!', 'Δεν έχετε επιλέξει κάποιο έργο από την παραπάνω λίστα.');


    },

    onPersonelItemDblClick: function(dataview, record, item, index, e, eOpts) {
        dataview.up().icon_dbl_click_handler(record);

    },

    onPersonelViewRefresh: function(dataview, eOpts) {
        if (dataview.store.data.length<1 && dataview.store.filters.length>0){
            Ext.addon.MessagePop.msg("Προσοχή!", "Δεν υπάρχουν εγγραφές που να αντιστοιχούν στην αναζήτηση σας. Προβάλονται όλες οι εγγραφές της σελίδας.", 5000);
            dataview.up('grid').plugins[1].clearValues();
            dataview.store.reload();
        }
    },

    onPersonelViewBeforeRefresh: function(dataview, eOpts) {
        if(Ext.getCmp('companyProjectsBooks_Daily').getSelectionModel().getSelection().length>0){
            var dailyCardId = Ext.getCmp('companyProjectsBooks_Daily').getSelectionModel().getSelection()[0].get('dailycardid');
            dataview.store.proxy.setUrl('/companyPersonnelBook/search/findByDailyCardId?dailyCardId='+dailyCardId);
        }
    },

    onNewPersonel: function(button, e, eOpts) {
        if(Ext.getCmp('companyProjectsBooks_Daily').getSelectionModel().getSelection().length>0){
            var emp_comp = Ext.create('widget.companyprojectsbookspersonelform', {
            });
            var incNum = parseInt(Ext.getCmp('companyProjectsBooks_Personel').store.proxy.reader.rawData.page.totalElements)+1;
            emp_comp.down().getForm().findField('incNum').setValue(incNum);
            emp_comp.show();
        }
        else {
            if(Ext.getCmp('companyProjectsBooks_Projects').getSelectionModel().getSelection().length===0)
                 Ext.Msg.alert('Προσοχή!', 'Δεν έχετε επιλέξει κάποιο έργο από την παραπάνω λίστα.');
            else if(Ext.getCmp('companyProjectsBooks_Daily').getSelectionModel().getSelection().length===0)
                Ext.Msg.alert('Προσοχή!', 'Δεν έχετε επιλέξει κάποιο ημερήσιο δελτίο από την παραπάνω λίστα.');

        }


    }

});