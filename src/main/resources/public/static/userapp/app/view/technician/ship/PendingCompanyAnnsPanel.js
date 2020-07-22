/*
 * File: app/view/technician/ship/PendingCompanyAnnsPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.technician.ship.PendingCompanyAnnsPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.technicianshippendingcompanyannspanel',

    requires: [
        'MyApp.view.technician.ship.PendingCompanyAnnsPanelViewModel',
        'Ext.grid.Panel',
        'Ext.grid.column.RowNumberer',
        'Ext.grid.View',
        'Ext.toolbar.Paging',
        'Ext.grid.filters.Filters',
        'Ext.grid.column.Action'
    ],

    viewModel: {
        type: 'technicianshippendingcompanyannspanel'
    },
    id: 'projectanncompany9',
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
                /*var emp_comp = Ext.create('widget.technicianshipcompanyinfoviewform', {});
                 Ext.getCmp('companySTaInfoForm_accept_reject_toolbar').show();

                 var form = emp_comp.down('form');
                 var fields = form.getForm().getFields();

                 form.getForm().findField('a_new_form').setValue(false);
                 form.getForm().findField('only_view').setValue(false);
                 form.loadRecord(record);

                 fields.each(function (field) {
                 field.enable();
                 field.setReadOnly (true);});

                 emp_comp.show();*/


                //form.getForm().findField('a_new_form').setValue(false);

                var emp_comp = Ext.create('widget.companytechnicianannshiptechform', {});

                var form = emp_comp.down('form');
                form.getForm().findField('only_view').setValue(false);
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

                    emp_comp.down('toolbar').getComponent('submitbutton').show();
                    emp_comp.down('toolbar').getComponent('submitbutton1').show();

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
                                form.getForm().findField('sepeDept').setValue(sepeDept.cdCode + ' - ' + sepeDept.cdText);
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

                }

                Ext.getCmp('sanncontrsadd').destroy();
                Ext.getCmp('sanncontrsdel').destroy();

                Ext.getCmp('tasentryadd').destroy();
                Ext.getCmp('tasentrydel').destroy();

                if(record.get('taSpeciality')!==null)
                    form.getForm().findField('taSpeciality').setValue(Ext.util.JSON.decode("["+record.get('taSpeciality')+"]"));

                if(record.get('portAuthority')!==null)
                    form.getForm().findField('portAuthority').setValue(Ext.util.JSON.decode("[\""+record.get('portAuthority')+"\"]"));
                if(record.get('shipyard')!==null)
                    form.getForm().findField('shipyard').setValue(Ext.util.JSON.decode("[\""+record.get('shipyard')+"\"]"));

                //var ship = Ext.getCmp('companyTechnicianShipAnn_Ships').getSelectionModel().getSelection()[0];
                //emp_comp.down('form').getForm().findField('compShipId').setValue(ship.get('shipId'));

                var grid = this;
                if(record.get('subStatus')===2){

                    /*if (record.get('taFullname') !== null) {
                        var storeLocal = Ext.StoreMgr.lookup('company.TechnicianAnn.LocalTaEntries');
                        var tasEntriesArr = [
                            ['1', record.get('taFullname')]
                        ];
                        storeLocal.loadData(tasEntriesArr, false);
                    }*/

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
            height: '100%',
            id: 'technicianShipPendingCompany_Anns',
            itemId: 'technicianShipPendingCompany_Anns',
            title: 'Αναγγελίες σε Εκρεμμότητα',
            autoLoad: true,
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'technician.ship.COMPANIES_TECHNICIAN_PEND_ANNS_GRID',
            columns: [
                {
                    xtype: 'rownumberer'
                },
                {
                    xtype: 'gridcolumn',
                    filterField: true,
                    sortable: false,
                    dataIndex: 'compInfoGrid',
                    text: 'Οργανισμός - Πλοίο',
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
                                return 'accept_or_reject'; // css for icon

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
                    store: 'technician.ship.COMPANIES_TECHNICIAN_PEND_ANNS_GRID'
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