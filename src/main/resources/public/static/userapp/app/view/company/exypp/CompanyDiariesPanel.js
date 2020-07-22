/*
 * File: app.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.exypp.CompanyDiariesPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.companyexyppcompanydiariespanel',

    requires: [
        'MyApp.view.company.exypp.CompanyDiariesPanelViewModel',
        'Ext.grid.Panel',
        'Ext.grid.column.RowNumberer',
        'Ext.grid.View',
        'Ext.toolbar.Paging',
        'Ext.grid.filters.Filters',
        'Ext.grid.column.Action',
        'Ext.grid.filters.filter.String'
    ],

    viewModel: {
        type: 'companyexyppcompanydiariespanel'
    },
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
            timestampToTime: function(timestamp) {

                var pD = timestamp.replace(/[^0-9]+/g,' ').split(" ");
                return (pD[3]+":"+pD[4]);
            },
            icon_dbl_click_handler: function(record) {
                var emp_comp = Ext.create('widget.companyexyppcompanyinfoviewform', {});
                Ext.getCmp('companyTaInfoForm_accept_reject_toolbar1').hide();

                var form = emp_comp.down('form');
                var fields = form.getForm().getFields();

                form.getForm().findField('a_new_form').setValue(false);
                form.loadRecord(record);

                var successAns = function(options, success, response) {
                    var resp = Ext.JSON.decode(response.responseText);
                    var pause = resp.pauseExplanation;
                    var protNoPause = resp.protNoPause;
                    var pDPause = resp.protDatePause;
                    if (pDPause === null)
                    {
                        form.getForm().findField('protNoviewPause').hide();
                        form.getForm().findField('protDateviewPause').hide();
                    }
                    var protNoResign = resp.protNoResign;
                    var pDResign = resp.protDateResign;
                    var attachedDocIdPause = resp.attachedDocIdPause;
                    var attachedDocId = resp.attachedDocId;
                    if (attachedDocId != "" && attachedDocId != null)
                    {
                        form.getForm().findField('attachedDocId').setValue(attachedDocId);
                        var vfc = Ext.ComponentQuery.query('#Exist_File')[0];
                        vfc.show();
                    }
                    if (pause != "" && pause != null){
                        Ext.getCmp('taAnnPauseData').show();
                        //form.getForm().findField('pauseExplanation').show();
                        form.getForm().findField('pauseExplanation').setValue(pause);
                        form.getForm().findField('protNoviewPause').setValue(protNoPause);
                        form.getForm().findField('protDateviewPause').setValue(pDPause);
                    }
                    if (protNoResign != "" && protNoResign != null){
                        Ext.getCmp('taAnnResignData').show();
                        form.getForm().findField('protNoviewResign').setValue(protNoResign);
                        form.getForm().findField('protDateviewResign').setValue(pDResign);
                    }
                    if (attachedDocIdPause != -1 && attachedDocIdPause != null)
                    {
                        form.getForm().findField('attachedDocIdPause').setValue(attachedDocIdPause);
                        var vfc = Ext.ComponentQuery.query('#Exist_FilePause')[0];
                        vfc.show();
                    }
                };

                Ext.Ajax.request({
                    url: '/getPauseExplanation',
                    method: "GET",
                    params: {compTaAnnId: record.get('compTaAnnId')},
                    callback: successAns
                });

                form.getForm().findField('dateStart').setValue(this.timestampToDate(record.get('dateStart')));
                form.getForm().findField('dateEnd').setValue(this.timestampToDate(record.get('dateEnd')));

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
            flex: 1.2,
            autoScroll: true,
            id: 'companyexyppCompany_Anns',
            itemId: 'companyexyppCompany_Anns',
            title: 'Επιχειρήσεις με ενεργή σύμβαση',
            autoLoad: true,
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.exypp.COMPANIES_TECHNICIAN_ANNS_GRID',
            columns: [
                {
                    xtype: 'rownumberer',
                    width: 30
                },
                {
                    xtype: 'gridcolumn',
                    filterField: true,
                    sortable: true,
                    dataIndex: 'compInfoGrid',
                    text: 'Οργανισμός',
                    flex: 10
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
                    sortable: true,
                    dataIndex: 'dateStart',
                    text: 'Έναρξη',
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
                    sortable: true,
                    dataIndex: 'dateEnd',
                    text: 'Λήξη',
                },
                /*{
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
                                var emp_comp = Ext.create('widget.companyexyppcompanyinfoviewform', {});
                                Ext.getCmp('companyTaInfoForm_accept_reject_toolbar1').hide();

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
                                pstore = Ext.StoreManager.get('company.exypp.CompanyDiary.DIARY_ALL');
                                var compTaAnnId = record.get('compTaAnnId');
                                pstore.proxy.setUrl('vCompTaAnnDiary/search/findByCompTaAnnId2?compTaAnnId='+compTaAnnId);
                                pstore.load({
                                    callback: function(records, operation, success) {
                                        for (var i = 0; i < records.length; i++) {
                                            var newitem = Ext.create('widget.companyexypptechniciandiaryentry', {});
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
                },*/
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
                    xtype: 'toolbar',
                    dock: 'top',
                    items: [
                        {
                            xtype: 'button',
                            iconCls: 'x-tool-img x-tool-print',
                            text: 'Εκτύπωση Επιχειρήσεων',
                            listeners: {
                                click: 'onPrintClick'
                            }
                        }
                    ]
                }
            ],
            plugins: [
                {
                    ptype: 'gridfilters',
                    menuFilterText: 'Αναζήτηση'
                },
                {
                    ptype: 'saki-gms',
                    clearItemT: 'Καθαριμός Φίλτρων',
                    iconColumn: false
                },
            ]
        },
        {
            xtype: 'gridpanel',
            flex: 1,
            autoScroll: true,
            id: 'companyexyppCompany_Technician',
            itemId: 'companyexyppCompany_Technician',
            title: 'Τεχνικοί Ασφάλειας',
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.exypp.CompanyDiary.TECHNICIAN',
            columns: [
                {
                    xtype: 'rownumberer'
                },
                {
                    xtype: 'gridcolumn',
                    sortable: true,
                    dataIndex: 'firstname',
                    text: 'Όνομα',
                },
                {
                    xtype: 'gridcolumn',
                    sortable: true,
                    dataIndex: 'lastname',
                    text: 'Επώνυμο',
                },
                {
                    xtype: 'gridcolumn',
                    sortable: false,
                    dataIndex: 'afm',
                    text: 'ΑΦΜ',
                },
            ],
            viewConfig: {
                frame: true,
                preserveScrollOnRefresh: true,
                listeners: {
                    itemclick: 'onTechnicianViewItemClick',
                    containerclick: 'onTechnicianViewContainerClick',
                    refresh: 'onTechnicianViewRefresh',
                    beforerefresh: 'onTechnicianViewBeforeRefresh'
                }
            },
            plugins: [
                {
                    ptype: 'gridfilters',
                    menuFilterText: 'Αναζήτηση'
                }
            ]
        },
        {
            xtype: 'gridpanel',
            flex: 1.1,
            autoScroll: true,
            id: 'companyexyppCompany_Diary',
            itemId: 'companyexyppCompany_Diary',
            title: 'Πρόγραμμα Επισκέψεων',
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.exypp.CompanyDiary.DIARY',
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
                    sortable: true,
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
                    text: 'Διάρκεια',
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

        Ext.getCmp('companyexyppCompany_Diary').store.clearData();
        Ext.getCmp('companyexyppCompany_Diary').view.refresh();
        Ext.getCmp('companyexyppCompany_Technician').store.clearData();
        Ext.getCmp('companyexyppCompany_Technician').view.refresh();
    },

    onCompanyViewItemClick: function(dataview, record, item, index, e, eOpts) {
        var technician = Ext.getCmp('companyexyppCompany_Technician');
        var compTaAnnId = Ext.getCmp('companyexyppCompany_Anns').getSelectionModel().getSelection()[0].get('compTaAnnId');
        technician.store.proxy.setUrl('SpPtlVUserSafetyTechnician/search/findByCompTaAnnIdTech?compTaAnnId='+compTaAnnId);
        technician.getStore().reload({
            callback: function(){
                technician.getView().refresh();
            }
        });

        var dailygrid2 = Ext.getCmp('companyexyppCompany_Diary');
        dailygrid2.store.clearData();
        dailygrid2.getView().refresh();
    },

    onCompanyViewContainerClick: function(dataview, e, eOpts) {

        var dailygrid2 = Ext.getCmp('companyexyppCompany_Diary');
        dailygrid2.store.clearData();
        dailygrid2.getView().refresh();

        var dailygrid = Ext.getCmp('companyexyppCompany_Technician');
        dailygrid.store.clearData();
        dailygrid.getView().refresh();


    },

    onTechnicianViewItemClick: function(dataview, record, item, index, e, eOpts) {
        var diary = Ext.getCmp('companyexyppCompany_Diary');
        var compTaAnnId = Ext.getCmp('companyexyppCompany_Anns').getSelectionModel().getSelection()[0].get('compTaAnnId');
        var afm = Ext.getCmp('companyexyppCompany_Technician').getSelectionModel().getSelection()[0].get('afm');
        diary.store.proxy.setUrl('vCompTaAnnDiary/search/findByCompTaAnnIdDiary?compTaAnnId='+compTaAnnId+'&afm='+afm);
        diary.getStore().reload({
            callback: function(){
                diary.getView().refresh();
            }
        });
    },

    onTechnicianViewContainerClick: function(dataview, e, eOpts) {
        var dailygrid = Ext.getCmp('companyexyppCompany_Diary');
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
        if(Ext.getCmp('companyexyppCompany_Anns').getSelectionModel().getSelection().length>0){
            var compTaAnnId = Ext.getCmp('companyexyppCompany_Anns').getSelectionModel().getSelection()[0].get('compTaAnnId');
            var afm;
            try
            {
                afm = Ext.getCmp('companyexyppCompany_Technician').getSelectionModel().getSelection()[0].get('afm');
            }
            catch (ex)
            {
                afm = "";
            }
            dataview.store.proxy.setUrl('/vCompTaAnnDiary/search/findByCompTaAnnIdDiary?compTaAnnId='+compTaAnnId+'&afm='+afm);
        }
    },

    onTechnicianViewRefresh: function(dataview, eOpts) {
        if (dataview.store.data.length<1 && dataview.store.filters.length>0){
            Ext.addon.MessagePop.msg("Προσοχή!", "Δεν υπάρχουν εγγραφές που να αντιστοιχούν στην αναζήτηση σας. Προβάλονται όλες οι εγγραφές της σελίδας.", 5000);
            dataview.up('grid').plugins[1].clearValues();
            dataview.store.reload();
        }
    },

    onTechnicianViewBeforeRefresh: function(dataview, eOpts) {
        if(Ext.getCmp('companyexyppCompany_Anns').getSelectionModel().getSelection().length>0){
            var compTaAnnId = Ext.getCmp('companyexyppCompany_Anns').getSelectionModel().getSelection()[0].get('compTaAnnId');
            dataview.store.proxy.setUrl('/SpPtlVUserSafetyTechnician/search/findByCompTaAnnIdTech?compTaAnnId='+compTaAnnId);
        }
    },

    onPrintClick: function(button, e, eOpts) {

        button.up().up().up().up().up().mask('Παρακαλώ περιμένετε...');
        var pnl=button.up('gridpanel');
        if (!pnl) {
            pnl = this;
        }

        var iFrameId = "printerFrame";
        var printFrame = Ext.get(iFrameId);


        if (printFrame === null) {
            printFrame = Ext.getBody().appendChild({
                id: iFrameId,
                tag: 'iframe',
                cls: 'x-hidden',
                style: {
                    display: "none"
                }
            });
        }

        var cw = printFrame.dom.contentWindow;

        var stylesheets = "";
        var markup = "";
        // instantiate application stylesheets in the hidden iframe

        var printTask = new Ext.util.DelayedTask(function(){

            // print the iframe
            cw.print();

            // destroy the iframe
            Ext.fly(iFrameId).destroy();

        });

        var title="<h4>Επιχειρήσεις με ενεργή σύμβαση</h4>";
        var strTask = new Ext.util.DelayedTask(function(){
            var str = Ext.String.format('<!DOCTYPE html><html><meta charset="UTF-8"><head>{0}</head><body>{2}<br>{1}</body></html>',stylesheets,markup,title);

                button.up().up().up().up().up().unmask();
                cw.document.open();
                cw.document.write(str);
                cw.document.close();

                printTask.delay(1000);
        });

        var markUpTask = new Ext.util.DelayedTask(function(){

            var store4 = Ext.StoreMgr.lookup('company.exypp.COMPANIES_TECHNICIAN_ANNS_GRID');
            for (var i=0; i<store4.data.items.length; i++ )
                markup += '<div>'+(i+1)+'. '+store4.data.items[i].get('compInfoGrid')+'</div><br>';

            strTask.delay(2000);
        });


        var styleSheetConcatTask = new Ext.util.DelayedTask(function(){

            // various style overrides
            stylesheets += ''.concat(
                "<style>",
                ".x-panel-body {overflow: visible !important;}",
                ".x-form-item {page-break-inside: avoid !important;}",
                ".x-fieldset-header {page-break-inside: avoid !important;}",
                "div {overflow: visible !important;}",
                "</style>"
            );

            markUpTask.delay(1000);
        });


        var styleSheetCreateTask = new Ext.util.DelayedTask(function(){


            for (var i = 0; i < document.styleSheets.length; i++) {
                stylesheets += Ext.String.format('<link rel="stylesheet" href="{0}" />', document.styleSheets[i].href);
            }
            styleSheetConcatTask.delay(1000);
        });

        styleSheetCreateTask.delay(1000);

    }

});