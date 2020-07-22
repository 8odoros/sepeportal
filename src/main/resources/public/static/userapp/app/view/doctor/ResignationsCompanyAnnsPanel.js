/*
 * File: app/view/doctor/ResignationsCompanyAnnsPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.doctor.ResignationsCompanyAnnsPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.doctorresignationscompanyannspanel',

    requires: [
        'MyApp.view.doctor.ResignationsCompanyAnnsPanelViewModel',
        'Ext.grid.Panel',
        'Ext.grid.column.RowNumberer',
        'Ext.grid.View',
        'Ext.toolbar.Paging',
        'Ext.grid.filters.Filters',
        'Ext.grid.column.Action',
        'Ext.grid.filters.filter.String'
    ],

    viewModel: {
        type: 'doctorresignationscompanyannspanel'
    },
    id: 'projectanncompany6',
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
                form.getForm().findField('only_view').setValue("resignation");
                form.loadRecord(record);

                Ext.getCmp('companyInfoForm_accept_reject_toolbar').hide();
                Ext.getCmp('companyInfoForm_resignation_action_toolbar').show();

                var grid = this;
                var store11 = Ext.StoreMgr.lookup('company.TechnicianAnn.TaEntries');
                store11.getProxy().setUrl(record.get('technicianAnnTaEntries'));
                store11.load({
                    callback: function(records, operation, success) {
                        var tasEntriesArr = [];
                        for (var i = 0; i < records.length; i++) {

                            var newentrys = Ext.create('widget.companytechniciananntaentry', {});
                            newentrys.down().items.get(0).setValue();
                            // if (records[i].get('cooperationType') !== null)
                            //     newentrys.down().items.get(0).setValue(records[i].get('cooperationType').toString());
                            // if (parseInt(records[i].get('cooperationType')) === 3)
                            //     newentrys.down().items.get(1).setValue(records[i].get('technicianRegrequestUserId').toString());
                            newentrys.down().items.get(2).setValue(records[i].get('taAfm'));
                            newentrys.down().items.get(3).setValue(records[i].get('taFullname'));
                            if (records[i].get('taSpeciality') !== null)
                                newentrys.down().items.get(4).setValue(Ext.util.JSON.decode("["+records[i].get('taSpeciality')+"]"));
                            newentrys.down().items.get(5).setValue(records[i].get('taSpecialityOther'));
                            newentrys.down().items.get(6).setValue(records[i].get('technicianRegrequestUserId'));
                            newentrys.down().items.get(7).setValue(records[i].get('technicianRegrequestId'));

                            if ( parseInt(Ext.getCmp('taAnnTaEntries').up('form').getForm().findField('cooperationTypeBasic').getValue()) === 3) {
                                newentrys.down().items.get(0).readOnly = true;
                                newentrys.down().items.get(0).hidden = true;
                            }
                            if (records[i].get('cooperationType') === 3) {
                                newentrys.down().items.get(0).readOnly = true;
                                newentrys.down().items.get(1).readOnly = true;
                            }
                            Ext.getCmp('taAnnTaEntries').add(newentrys);
                            if (records[i].get('cooperationType') !== 3){
                                tasEntriesArr.push({
                                    id: records[i].get('technicianRegrequestUserId'),
                                    name: records[i].get('taFullname')
                                });
                            }
                        }
                        var storeLocal = Ext.StoreMgr.lookup('company.TechnicianAnn.LocalTaEntries');
                        storeLocal.loadData(tasEntriesArr, false);


                        fields = form.getForm().getFields();
                        fields.each(function (field) {
                            field.enable();
                            field.setReadOnly(true);
                        });

                        var store1 = Ext.StoreMgr.lookup('company.TechnicianAnn.Diary');
                        store1.getProxy().setUrl(record.get('technicianAnnDiaryEntries'));
                        store1.load({
                            callback: function (records, operation, success) {

                                var dates = [];
                                for (var i = 0; i < records.length; i++) {
                                    dates[i] = records[i].get('visitDate')+records[i].get('visitTime');
                                }

                                for (var j = 0; j < records.length; j++) {
                                    var i = dates.indexOf(Ext.Array.min(dates));
                                    dates[i] = Ext.Array.max(dates)+1;

                                    var newentrys = Ext.create('widget.companytechniciananndiary', {});
                                    newentrys.items.get(3).suspendEvents();
                                    newentrys.items.get(4).suspendEvents();

                                    newentrys.items.get(6).hide();

                                    newentrys.items.get(0).setValue(grid.timestampToDate(records[i].get('visitDate')));
                                    newentrys.items.get(1).setValue(records[i].get('visitTime'));
                                    newentrys.items.get(2).setValue(records[i].get('visitDurationMinutes'));
                                    var hs = Math.floor(records[i].get('visitDurationMinutes')/60);
                                    newentrys.items.get(3).setValue(hs);
                                    var ms = records[i].get('visitDurationMinutes')-(Math.floor(records[i].get('visitDurationMinutes')/60)*60);
                                    newentrys.items.get(4).setValue(ms);
                                    if (records[i].get('compTaAnnTaId') !== null)
                                        newentrys.items.get(5).setValue(records[i].get('compTaAnnTaId'));
                                    if (j >= 1) {
                                        newentrys.items.get(0).hideLabel = true;
                                        newentrys.items.get(1).hideLabel = true;
                                        newentrys.items.get(2).hideLabel = true;
                                        newentrys.items.get(3).hideLabel = true;
                                        newentrys.items.get(4).hideLabel = true;
                                        newentrys.items.get(5).hideLabel = true;
                                    }
                                    //else newentrys.down().items.get(6).setStyle({margin:'25px 0 0 0'});

                                    newentrys.items.get(3).resumeEvents(false);
                                    newentrys.items.get(4).resumeEvents(false);

                                    Ext.getCmp('tadiaryEntries').add(newentrys);

                                    fields = form.getForm().getFields();
                                    fields.each(function (field) {
                                        field.enable();
                                        field.setReadOnly(true);
                                    });
                                }
                                form.getForm().findField('diaryEntriesnum').setValue(records.length);
                            }

                        });
                    }
                });

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
            id: 'doctorActiveCompany_Anns',
            itemId: 'doctorActiveCompany_Anns',
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
            icon_dbl_click_handler: function(record) {
                var emp_comp = Ext.create('widget.doctorcompanyinfoviewform', {});

                var form = emp_comp.down('form');
                var fields = form.getForm().getFields();

                form.getForm().findField('a_new_form').setValue(false);
                form.getForm().findField('only_view').setValue("true");
                form.loadRecord(record);

                Ext.getCmp('companyInfoForm_accept_reject_toolbar').hide();

                fields.each(function (field) {
                    field.enable();
                field.setReadOnly (true);});

                emp_comp.show();

            },
            flex: 1,
            autoScroll: true,
            height: '50%',
            id: 'doctorCompany_Resignations',
            itemId: 'doctorCompany_Resignations',
            title: 'Αρχείο Αναγγελιών Παύσης Καθηκόντων με δική σας ενέργεια',
            autoLoad: true,
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'doctor.DOCTOR_RESIGNATIONS_COMP_ANNS_GRID',
            columns: [
                {
                    xtype: 'rownumberer'
                },
                {
                    xtype: 'gridcolumn',
                    sortable: false,
                    dataIndex: 'compInfoGrid',
                    text: 'Οργανισμός',
                    flex: 40,
                    filter: {
                        type: 'string',
                        emptyText: 'Εισαγωγή κειμένου...'
                    }
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
                    dataIndex: 'dateEnd',
                    text: 'Ημερομηνία Παύσης',
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
                    refresh: 'onResignViewRefresh'
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
                    store: 'doctor.DOCTOR_RESIGNATIONS_COMP_ANNS_GRID'
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
    },

    onResignViewRefresh: function(dataview, eOpts) {
        if (dataview.store.data.length<1 && dataview.store.filters.length>0){
            Ext.addon.MessagePop.msg("Προσοχή!", "Δεν υπάρχουν εγγραφές που να αντιστοιχούν στην αναζήτηση σας. Προβάλονται όλες οι εγγραφές της σελίδας.", 5000);
            dataview.up('grid').plugins[1].clearValues();
            dataview.store.reload();
        }
    }

});