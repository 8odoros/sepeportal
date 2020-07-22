/*
 * File: app/view/company/TechnicianShipAnnPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianShipAnnPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.companytechnicianshipannpanel',

    requires: [
        'MyApp.view.company.TechnicianShipAnnPanelViewModel',
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
        type: 'companytechnicianshipannpanel'
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
                var emp_comp = Ext.create('widget.companytechnicianannshipform', {});
                Ext.getCmp('comptechnicianannship_save_submit_toolbar').hide();

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
            id: 'companyTechnicianShipAnn_Ships',
            itemId: 'companyTechnicianShipAnn_Ships',
            title: 'Πλοία Εργοδότη',
            autoLoad: true,
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.PTL_COMPANY_SHIPS',
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
                    store: 'company.PTL_COMPANY_SHIPS'
                },
                {
                    xtype: 'container',
                    dock: 'top',
                    html: 'Επιλέξτε το πλοίο για το οποίο θέλετε να γίνει η αναγγελία ή προσθέστε νέο πλοίο.',
                    layout: 'anchor'
                },
                {
                    xtype: 'toolbar',
                    dock: 'top',
                    items: [
                        {
                            xtype: 'button',
                            glyph: 'xf067@FontAwesome',
                            text: 'Προσθήκη νέου πλοίου',
                            listeners: {
                                click: 'onNewShip'
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
                var emp_comp = Ext.create('widget.companytechnicianannshiptechform', {});
                emp_comp.down('toolbar').getComponent('deletebutton').show();

                var form = emp_comp.down('form');
                var fields = form.getForm().getFields();


                if(record.get('subStatus')===1){
                    form.getForm().findField('protNoview').setValue("-");

                    if (record.get('compAddrPe')!==null && record.get('compAddrD')===null){
                        form.getForm().findField('compAddrPe').setDisabled(false);
                        form.getForm().findField('compAddrD').setDisabled(false);
                    }
                    else if (record.get('compAddrPe')!==null && record.get('compAddrD')!==null){
                        form.getForm().findField('compAddrPe').setDisabled(false);
                        form.getForm().findField('compAddrD').setDisabled(false);
                        form.getForm().findField('compAddrK').setDisabled(false);
                    }
                    else if (record.get('compAddrP')!==null && record.get('compAddrPe')===null){
                        form.getForm().findField('compAddrPe').setDisabled(false);
                    }

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

                    form.getForm().findField('protNoview').setValue(record.get('protNo'));
                    emp_comp.down('toolbar').getComponent('submitbutton').hide();
                    emp_comp.down('toolbar').getComponent('savebutton').hide();

                    if(record.get('taSannStatus')===0 && record.get('reqStatus')===5){
                        emp_comp.down('toolbar').getComponent('deletebutton').show();
                        emp_comp.down('toolbar').getComponent('changebutton').hide();
                    }
                    else if(record.get('taSannStatus')===1 && record.get('reqStatus')===6){
                        emp_comp.down('toolbar').getComponent('changebutton').show();
                        emp_comp.down('toolbar').getComponent('deletebutton').hide();
                    }
                    else if(record.get('taSannStatus')===-1 && record.get('reqStatus')===7){
                        emp_comp.down('toolbar').hide();
                    }
                    else if(record.get('taSannStatus')===2){
                        emp_comp.down('toolbar').getComponent('changebutton').hide();
                        emp_comp.down('toolbar').getComponent('deletebutton').hide();
                    }


                    form.getForm().findField('file').hide();

                }

                form.getForm().findField('a_new_form').setValue(false);

                emp_comp.show();
                emp_comp.mask("Παρακαλώ Περιμένετε...");
                form.loadRecord(record);

                form.getForm().findField('cooperationType').setValue(record.get('cooperationType').toString());
                form.getForm().findField('dateStart').setValue(this.timestampToDate(record.get('dateStart')));
                form.getForm().findField('dateEnd').setValue(this.timestampToDate(record.get('dateEnd')));
                form.getForm().findField('projStartDate').setValue(this.timestampToDate(record.get('projStartDate')));


                if(record.get('subStatus')===1){

                    if(record.get('protDate')!==null){
                        form.getForm().findField('protDateview').setValue("");
                        form.getForm().findField('protYear').setValue("");
                        form.getForm().findField('submitTime').setValue("");
                    }

                    form.getForm().findField('protDateview').setValue("");
                    form.getForm().findField('protDate').setValue("");

                    form.getForm().findField('reqStatus').setValue("");

                    if (record.get('attachedDocId')!==-1){
                        var vfc = Ext.ComponentQuery.query('#Exist_File')[0];
                        vfc.show();
                    }

                }
                else if(record.get('subStatus')===2){

                    var pD = record.get('protDate').replace(/[^0-9]+/g,' ').split(" ");
                    form.getForm().findField('protDateview').setValue(pD[2]+"-"+pD[1]+"-"+pD[0]);
                    form.getForm().findField('reqStatus').setValue(record.get('reqStatus').toString());
                    if (record.get('reqStatus')>0)
                    form.getForm().findField('reqStatus').setValue(record.get('reqStatus').toString());
                    else
                    form.getForm().findField('reqStatus').setValue("");

                    /*if (record.get('sepeDept')>0 || record.get('sepeDept')!==null){
                        var store2 = Ext.StoreMgr.lookup( 'shared.SEPE_DEPT' );
                        store2.getProxy().setExtraParam("id", record.get('sepeDept'));
                        store2.load( { callback : function(records, operation, success) {
                            form.getForm().findField('department').setValue(record.get('sepeDept').toString());
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


            if (record.get('attachedDocId')!==-1){
                var vfc = Ext.ComponentQuery.query('#Exist_File')[0];
                vfc.items.getAt(1).hide();
                vfc.show();
            }

            Ext.getCmp('sanncontrsadd').destroy();
            Ext.getCmp('sanncontrsdel').destroy();


        }


        Ext.getCmp('tasentryadd').destroy();
        Ext.getCmp('tasentrydel').destroy();

        if(record.get('taSpeciality')!==null)
        form.getForm().findField('taSpeciality').setValue(Ext.util.JSON.decode("["+record.get('taSpeciality')+"]"));

        if(record.get('portAuthority')!==null)
            form.getForm().findField('portAuthority').setValue(Ext.util.JSON.decode("[\""+record.get('portAuthority')+"\"]"));
        if(record.get('shipyard')!==null)
            form.getForm().findField('shipyard').setValue(Ext.util.JSON.decode("[\""+record.get('shipyard')+"\"]"));
        if(record.get('shipDangerZone')!==null)
            form.getForm().findField('shipDangerZone').setValue(Ext.util.JSON.decode("[\""+record.get('shipDangerZone')+"\"]"));

        var ship = Ext.getCmp('companyTechnicianShipAnn_Ships').getSelectionModel().getSelection()[0];
        emp_comp.down('form').getForm().findField('compShipId').setValue(ship.get('shipId'));

        var grid = this;
        if(record.get('subStatus')===2){

            if (record.get('taFullname') !== null) {
                var storeLocal = Ext.StoreMgr.lookup('company.TechnicianAnn.LocalTaEntries');
                var tasEntriesArr = [
                    ['1', record.get('taFullname')]
                ];
                storeLocal.loadData(tasEntriesArr, false);
            }

            var store1 = Ext.StoreMgr.lookup('company.TechnicianAnn.ShipDiary');
            store1.getProxy().setUrl(record.get('compTaSannDiaryEntries'));
            store1.load({
                callback: function(records, operation, success) {
                    /*for (var i = 0; i < records.length; i++) {
                        var newentrys = Ext.create('widget.companytechniciananndiary', {});
                        newentrys.down().items.get(0).setValue(grid.timestampToDate(records[i].get('visitDate')));
                        newentrys.down().items.get(1).setValue(records[i].get('visitTime'));
                        newentrys.down().items.get(2).setValue(records[i].get('visitDurationMinutes'));
                        if(i>=1){
                            newentrys.down().items.get(0).hideLabel=true;
                            newentrys.down().items.get(1).hideLabel=true;
                            newentrys.down().items.get(2).hideLabel=true;

                            newentrys.down().items.get(2).hidden=false;newentrys.down().items.get(2).readOnly=false;
                            newentrys.down().items.get(3).hidden=false;newentrys.down().items.get(4).hidden=false;newentrys.down().items.get(5).hidden=false;
                            newentrys.down().items.get(3).allowBlank=true;newentrys.down().items.get(4).allowBlank=true;newentrys.down().items.get(5).allowBlank=true;
                        }
                        if(i===1){
                            form.getForm().findField('startTime').setValue(records[i].get('visitTime'));
                        }
                        Ext.getCmp('tasdiaryEntries').add(newentrys);
                    }
                    form.getForm().findField('diaryEntriesnum').setValue(records.length);
                    Ext.getCmp('tasdiaryEntries').show();*/

                    for (var i = 0; i < records.length; i++) {
                        var newentrys = Ext.create('widget.companytechniciananndiary', {});
                        newentrys.items.get(3).suspendEvents();
                        newentrys.items.get(4).suspendEvents();
                        newentrys.items.get(0).setValue(grid.timestampToDate(records[i].get('visitDate')));
                        newentrys.items.get(1).setValue(records[i].get('visitTime'));
                        newentrys.items.get(2).setValue(records[i].get('visitDurationMinutes'));
                        var hs = Math.floor(records[i].get('visitDurationMinutes')/60);
                        newentrys.items.get(3).setValue(hs);
                        var ms = records[i].get('visitDurationMinutes')-(Math.floor(records[i].get('visitDurationMinutes')/60)*60);
                        newentrys.items.get(4).setValue(ms);
                        if (records[i].get('compTaAfm') !== null)
                            newentrys.items.get(5).setValue('1'); // setValue(records[i].get('compTaAfm'));
                        if(i>=1){
                            newentrys.items.get(0).hideLabel=true;
                            newentrys.items.get(1).hideLabel=true;
                            newentrys.items.get(2).hideLabel=true;
                            newentrys.items.get(3).hideLabel = true;
                            newentrys.items.get(4).hideLabel = true;
                            newentrys.items.get(5).hideLabel = true;

                            //newentrys.down().items.get(2).hidden=false;newentrys.down().items.get(2).readOnly=false;
                            //newentrys.down().items.get(3).hidden=false;newentrys.down().items.get(4).hidden=false;newentrys.down().items.get(5).hidden=false;
                            //newentrys.down().items.get(3).allowBlank=true;newentrys.down().items.get(4).allowBlank=true;newentrys.down().items.get(5).allowBlank=true;
                        }
                        newentrys.items.get(3).resumeEvents(false);
                        newentrys.items.get(4).resumeEvents(false);

                        if(i===0){
                            form.getForm().findField('startTime').setValue(records[i].get('visitTime'));
                        }

                        Ext.getCmp('tasdiaryEntries').add(newentrys);
                    }
                    form.getForm().findField('diaryEntriesnum').setValue(records.length);
                    Ext.getCmp('tasdiaryEntries').show();

                    var store1 = Ext.StoreMgr.lookup( 'company.TechnicianAnn.ShipContr' );
                    store1.getProxy().setUrl(record.get('compTaSannContrs'));
                    store1.load( { callback : function(records, operation, success) {
                        for (var i=0; i<records.length; i++){
                            var projscontr = Ext.create('widget.companytechnicianannshipcontr', {});
                            projscontr.items.getAt(0).items.getAt(0).setValue(i+1+". ");
                            //projscontr.items.getAt(1).items.getAt(0).items.getAt(0).setValue(parseInt(records[i].get('type')));
                            //projscontr.items.getAt(1).items.getAt(0).items.getAt(2).setValue(records[i].get('firstname'));
                            //projscontr.items.getAt(1).items.getAt(0).items.getAt(4).setValue(records[i].get('birthplace'));
                            //projscontr.items.getAt(1).items.getAt(0).items.getAt(5).setValue(grid.timestampToDate(records[i].get('birthdate')));
                            //projscontr.items.getAt(1).items.getAt(0).items.getAt(7).setValue(records[i].get('cardNumber'));
                            //projscontr.items.getAt(1).items.getAt(0).items.getAt(8).setValue(records[i].get('cardIssuingAuth'));
                            //form.getForm().findField('speciality').setValue(Ext.util.JSON.decode("["+record.get('speciality')+"]"));
                            projscontr.items.getAt(1).items.getAt(0).items.getAt(0).setValue(records[i].get('contractorSpecialty').toString());
                            projscontr.items.getAt(1).items.getAt(0).items.getAt(0).setReadOnly (true);
                            projscontr.items.getAt(1).items.getAt(0).items.getAt(1).setValue(records[i].get('lastname'));
                            projscontr.items.getAt(1).items.getAt(0).items.getAt(1).setReadOnly (true);
                            projscontr.items.getAt(1).items.getAt(0).items.getAt(2).setValue(records[i].get('addr'));
                            projscontr.items.getAt(1).items.getAt(0).items.getAt(2).setReadOnly (true);
                            projscontr.items.getAt(1).items.getAt(0).items.getAt(3).setValue(records[i].get('afm'));
                            projscontr.items.getAt(1).items.getAt(0).items.getAt(3).setReadOnly (true);
                            Ext.getCmp('sanncontrs').add(projscontr);
                            form.getForm().findField('projscontrsnum').setValue(parseInt(i+1));
                        }
                        form.getForm().findField('projscontrsnum').setValue(parseInt(records.length));

                    } });


                    fields = form.getForm().getFields();
                    fields.each(function (field) {
                        field.enable();
                    field.setReadOnly (true);});

                    emp_comp.unmask();
                }

            });

        }
        else{

            var store1 = Ext.StoreMgr.lookup( 'company.TechnicianAnn.ShipContr' );
            store1.getProxy().setUrl(record.get('compTaSannContrs'));
            store1.load( { callback : function(records, operation, success) {
                for (var i=0; i<records.length; i++){
                    var projscontr = Ext.create('widget.companytechnicianannshipcontr', {});
                    projscontr.items.getAt(0).items.getAt(0).setValue(i+1+". ");
                    //projscontr.items.getAt(1).items.getAt(0).items.getAt(0).setValue(parseInt(records[i].get('type')));
                    //projscontr.items.getAt(1).items.getAt(0).items.getAt(2).setValue(records[i].get('firstname'));
                    //projscontr.items.getAt(1).items.getAt(0).items.getAt(4).setValue(records[i].get('birthplace'));
                    //projscontr.items.getAt(1).items.getAt(0).items.getAt(5).setValue(grid.timestampToDate(records[i].get('birthdate')));
                    //projscontr.items.getAt(1).items.getAt(0).items.getAt(7).setValue(records[i].get('cardNumber'));
                    //projscontr.items.getAt(1).items.getAt(0).items.getAt(8).setValue(records[i].get('cardIssuingAuth'));
                    projscontr.items.getAt(1).items.getAt(0).items.getAt(0).setValue(records[i].get('contractorSpecialty').toString());
                    projscontr.items.getAt(1).items.getAt(0).items.getAt(1).setValue(records[i].get('lastname'));
                    projscontr.items.getAt(1).items.getAt(0).items.getAt(2).setValue(records[i].get('addr'));
                    projscontr.items.getAt(1).items.getAt(0).items.getAt(3).setValue(records[i].get('afm'));
                    Ext.getCmp('sanncontrs').add(projscontr);
                    form.getForm().findField('projscontrsnum').setValue(parseInt(i+1));
                }
                form.getForm().findField('projscontrsnum').setValue(parseInt(records.length));
                emp_comp.unmask();
            } });



        }


            },
            flex: 1,
            autoScroll: true,
            height: '50%',
            id: 'companyTechnicianShipAnn_Technicians',
            itemId: 'companyTechnicianShipAnn_Technicians',
            title: 'Καρτέλες Αναγγελίες Τεχνικών Ασφάλειας σε Πλοία και Ναυπηγικές Εργασίες',
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.TechnicianAnn.TECHNICIAN_SHIPS_ANNS_GRID',
            columns: [
                {
                    xtype: 'rownumberer'
                },
                {
                    xtype: 'actioncolumn',
                    width: 30,
                    //enableColumnHide: false,
                    //sealed: true,
                    dataIndex: 'taSannStatus',
                    //hideable: false,
                    //menuDisabled: true,
                    renderer: function(v, metaData, r, rowIndex, colIndex, store, view) {
                        if (v===-2){
                            this.items[0].tooltip = 'Μη καταχωρημένη';
                        }
                        else if(v===2 && r.get('reqStatus')===7){
                            this.items[0].tooltip = 'Αντικαταστάθηκε';
                        }
                        else if(v===2 && r.get('reqStatus')===6){
                            this.items[0].tooltip = 'Παραιτήθηκε';
                        }
                        else if(v===3 && r.get('reqStatus')===6){
                            this.items[0].tooltip = 'Παύση Εταιρείας';
                        }
                        else if(Date.parse(new Date())>Date.parse(r.get('dateEnd'))){
                            this.items[0].tooltip = 'Έληξε';
                        }
                        else if(v===0){
                            this.items[0].tooltip = 'Αναμονή Τεχνικού';
                        }
                        else if(v===1 && r.get('reqStatus')===6){
                            this.items[0].tooltip = 'Αποδοχή Τεχνικού';
                        }
                        else if(v===-1 && r.get('reqStatus')===7){
                            this.items[0].tooltip = 'Απόρριψη Τεχνικού';
                        }

                    },
                    items: [
                        {
                            getClass: function(v, metadata, r, rowIndex, colIndex, store) {
                                if (v===-2){
                                    //this.items[0].tooltip = 'Μη καταχωρημένη';
                                    return ''; // css for icon
                                }
                                else if(v===2 && r.get('reqStatus')===7){
                                    //this.items[0].tooltip = 'Αντικαταστάθηκε';
                                    return 'inactivependingU'; // css for icon
                                }
                                else if(v===2 && r.get('reqStatus')===6){
                                    //this.items[0].tooltip = 'Παραιτήθηκε';
                                    return 'activependingU'; // css for icon
                                }
                                else if(v===3 && r.get('reqStatus')===6){
                                    //this.items[0].tooltip = 'Παύση';
                                    return 'activepausedU'; // css for icon
                                }
                                else if(Date.parse(new Date())>Date.parse(r.get('dateEnd'))){
                                    //this.items[0].tooltip = 'Έληξε';
                                    return 'previousactiveU'; // css for icon
                                }
                                else if(v===0){
                                    //this.items[0].tooltip = 'Αναμονή Τεχνικού';
                                    return 'pendingU'; // css for icon
                                }
                                else if(v===1 && r.get('reqStatus')===6){
                                    //this.items[0].tooltip = 'Αποδοχή Τεχνικού';
                                    return 'activeU'; // css for icon
                                }
                                else if(v===-1 && r.get('reqStatus')===7){
                                    //this.items[0].tooltip = 'Απόρριψη Τεχνικού';
                                    return 'inactiveU'; // css for icon
                                }

                            },
                            disabled: false
                        }
                    ]
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
                    sortable: false,
                    dataIndex: 'dateEnd',
                    text: 'Λήξη',
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        if (value!==null){
                            var pD = value.replace(/[^0-9]+/g,' ').split(" ");
                            return (pD[2]+"-"+pD[1]+"-"+pD[0])+"  "+record.get("submitTime");
                        }
                        else
                        return "";

                    },
                    sortable: false,
                    dataIndex: 'protDate',
                    text: 'Τελευταία Επεξεργασία',
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
                            if (record.get('subStatus')===2){
                                var pD = value.replace(/[^0-9]+/g,' ').split(" ");
                                return (pD[2]+"-"+pD[1]+"-"+pD[0])+"/"+record.get("submitTime");
                            }
                        }
                        else
                        return "";

                    },
                    sortable: false,
                    dataIndex: 'protDate',
                    text: 'Ημερομηνία/Ωρα',
                    flex: 14
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        if (value!==null){
                            if (record.get('subStatus')===1){
                                return "Δεν δόθηκε";
                            }
                            else
                            return value+"/"+record.get("protYear");
                        }else
                        return "";
                    },
                    sortable: false,
                    dataIndex: 'protNo',
                    text: 'Πρωτόκολλο',
                    flex: 14
                },
                {
                    xtype: 'gridcolumn',
                    hidden: true,
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        if (value!==null){
                            if (record.get('subStatus')===1){
                                return "Αποθηκευμένη";
                            }
                            else
                            return "Καταχωρημένη";
                        }
                        else
                        return "";
                    },
                    sortable: false,
                    dataIndex: 'subStatus',
                    text: 'Κατάσταση',
                    flex: 14
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(v, metaData, r, rowIndex, colIndex, store, view) {
                        if (v===-2){
                            return 'Μη καταχωρημένη';
                        }
                        else if(v===2 && r.get('reqStatus')===7){
                            return  'Αντικαταστάθηκε';
                        }
                        else if(v===2 && r.get('reqStatus')===6){
                            return  'Παραιτήθηκε';
                        }
                        else if(v===3 && r.get('reqStatus')===6){
                            return  'Παύση Εταιρείας';
                        }
                        else if(Date.parse(new Date())>Date.parse(r.get('dateEnd'))){
                            return  'Έληξε';
                        }
                        else if(v===0){
                            return  'Αναμονή Τεχνικού';
                        }
                        else if(v===1 && r.get('reqStatus')===6){
                            return  'Αποδοχή Τεχνικού';
                        }
                        else if(v===-1 && r.get('reqStatus')===7){
                            return  'Απόρριψη Τεχνικού';
                        } else {
                            return '';
                        }

                    },
                    sortable: false,
                    dataIndex: 'taSannStatus',
                    text: 'Κατάσταση',
                    flex: 17,
                },
                {
                    xtype: 'gridcolumn',
                    hidden: true,
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        if (value!==null){
                            if (record.get('subStatus')===1){
                                return "";
                            }
                            else{
                                /*if(Date.parse(new Date())>Date.parse(record.get('dateEnd'))){
                                    return 'Έληξε'; // Check Date
                                } else {*/
                                    var store2 = Ext.StoreMgr.lookup( 'shared.COMPL_STATUS' );
                                    if (record.get('reqStatus')>0){

                                        return store2.findRecord('reqStatus', record.get('reqStatus').toString()).get('description');
                                    }
                                    else
                                        return "";
                                //}

                            }
                        }
                        else
                        return "";
                    },
                    sortable: false,
                    dataIndex: 'taSannStatus',
                    text: 'Πορεία',
                    flex: 17
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
                    refresh: 'onTechnicianViewRefresh',
                    itemdblclick: 'onTechnicianViewItemDblClick',
                    beforerefresh: 'onTechnicianViewBeforeRefresh'
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
                    store: 'company.TechnicianAnn.TECHNICIAN_SHIPS_ANNS_GRID'
                },
                {
                    xtype: 'toolbar',
                    dock: 'top',
                    items: [
                        {
                            xtype: 'button',
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα Αναγγελία Τεχνικού Ασφάλειας',
                            listeners: {
                                click: 'onNewTechnician'
                            }
                        }
                    ]
                },
                {
                    xtype: 'toolbar',
                    dock: 'bottom',
                    items: [
                        {
                            xtype: 'container',
                            html: 'Κατάσταση Αναγγελίας: <img height="16px" src="static/userapp/resources/pending.png"/> Αναμονή &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <img height="16px" src="static/userapp/resources/active.png"/> Αποδοχή &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<img height="16px" src="static/userapp/resources/inactive.png"/> Απόρριψη &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<img height="16px" src="static/userapp/resources/inactivepending.png"/> Αντικαταστάθηκε&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<img height="16px" src="static/userapp/resources/activepending.png"/> Παραιτήθηκε&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<img height="16px" src="static/userapp/resources/previousactive.png"/> Έληξε&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<img height="16px" src="static/userapp/resources/activepaused.png"/> Παύση Εταιρείας',
                            layout: 'anchor'
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

    onShipItemDblClick: function(dataview, record, item, index, e, eOpts) {
        dataview.up().icon_dbl_click_handler(record);

    },

    onShipViewRefresh: function(dataview, eOpts) {
        if (dataview.store.data.length<1 && dataview.store.filters.length>0){
            Ext.addon.MessagePop.msg("Προσοχή!", "Δεν υπάρχουν εγγραφές που να αντιστοιχούν στην αναζήτηση σας. Προβάλονται όλες οι εγγραφές της σελίδας.", 5000);
            dataview.up('grid').plugins[1].clearValues();
            dataview.store.reload();
        }
        Ext.getCmp('companyTechnicianShipAnn_Technicians').store.clearData();
        Ext.getCmp('companyTechnicianShipAnn_Technicians').view.refresh();
    },

    onShipViewItemClick: function(dataview, record, item, index, e, eOpts) {
        var technicians = Ext.getCmp('companyTechnicianShipAnn_Technicians');
        var shipId = Ext.getCmp('companyTechnicianShipAnn_Ships').getSelectionModel().getSelection()[0].get('shipId');
        technicians.store.proxy.setUrl('/compTaSann/search/findByCompShipId?compShipId='+shipId);
        technicians.getStore().reload({
          callback: function(){
            technicians.getView().refresh();
          }
        });
    },

    onShipViewContainerClick: function(dataview, e, eOpts) {
        var shipsgrid = Ext.getCmp('companyTechnicianShipAnn_Technicians');
        shipsgrid.store.clearData();
        shipsgrid.getView().refresh();
    },

    onNewShip: function(button, e, eOpts) {
        var emp_comp = Ext.create('widget.companytechnicianannshipform', {
        });
        emp_comp.show();
    },

    onTechnicianViewRefresh: function(dataview, eOpts) {
        if (dataview.store.data.length<1 && dataview.store.filters.length>0){
            Ext.addon.MessagePop.msg("Προσοχή!", "Δεν υπάρχουν εγγραφές που να αντιστοιχούν στην αναζήτηση σας. Προβάλονται όλες οι εγγραφές της σελίδας.", 5000);
            dataview.up('grid').plugins[1].clearValues();
            dataview.store.reload();
        }
    },

    onTechnicianViewItemDblClick: function(dataview, record, item, index, e, eOpts) {
        dataview.up().icon_dbl_click_handler(record);

    },

    onTechnicianViewBeforeRefresh: function(dataview, eOpts) {
        if(Ext.getCmp('companyTechnicianShipAnn_Ships').getSelectionModel().getSelection().length>0){
            var shipId = Ext.getCmp('companyTechnicianShipAnn_Ships').getSelectionModel().getSelection()[0].get('shipId');
            dataview.store.proxy.setUrl('/compTaSann/search/findByCompShipId?compShipId'+shipId);
        }



    },

    onNewTechnician: function(button, e, eOpts) {
        if(Ext.getCmp('companyTechnicianShipAnn_Ships').getSelectionModel().getSelection().length>0){
                     var successCall = function(options, success, response) {
                            if(response.responseText!=="0" ){
                    Ext.Msg.alert('Προσοχή!', 'Υπάρχει αναγγελία σε αναμονή ή <br>προηγούμενη ενεργή ή που έχει τερματιστεί.'+
                                ' <br>Επιλέξτε την από την λίστα και επιλέξτε διαγραφή στην περίπτωση αναμονής <br>αλλιώς επιλέξτε αντικατάσταση.');
                            }
                         else{
                             var emp_comp = Ext.create('widget.companytechnicianannshipdatesform', {});
                              emp_comp.down('form').getForm().findField('shipId').setValue(
                                  Ext.getCmp('companyTechnicianShipAnn_Ships').getSelectionModel().getSelection()[0].get('shipId'));
                                emp_comp.show();
                            }



                    };
                    Ext.Ajax.request({
                        url: "/compTaSann/search/findCategTaSann",
                        params: {
                            compShipId: Ext.getCmp('companyTechnicianShipAnn_Ships').getSelectionModel().getSelection()[0].get('shipId')
                        },
                        method: "GET",
                        callback: successCall
                    });

                }
                else
                    Ext.Msg.alert('Προσοχή!', 'Δεν έχετε επιλέξει κάποιο πλοίο από την παραπάνω λίστα.');


    }

});