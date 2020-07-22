/*
 * File: app/view/company/DoctorAnnPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.DoctorAnnPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.companydoctorannpanel',

    requires: [
        'MyApp.view.company.DoctorAnnPanelViewModel',
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
        type: 'companydoctorannpanel'
    },
    id: 'projectanncompany1',
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'gridpanel',
            icon_dbl_click_handler: function(record) {
                var emp_comp = Ext.create('widget.companydoctorannbranchform', {});
                //Ext.getCmp('compdoctorannbranch_save_submit_toolbar').hide();

                var form = emp_comp.down('form');
                var fields = form.getForm().getFields();

                form.getForm().findField('a_new_form').setValue(false);

                form.loadRecord(record);

                fields.each(function (field) {
                    field.enable();
                    field.setReadOnly (true);});

                form.getForm().findField('brDescr').setReadOnly(false);
                emp_comp.down('toolbar').getComponent('savebutton').hide();
                emp_comp.down('toolbar').getComponent('updatebutton').show();

                emp_comp.show();

            },
            flex: 1,
            autoScroll: true,
            height: '50%',
            id: 'companyDoctorAnn_Branches',
            itemId: 'companyDoctorAnn_Branches',
            title: 'Εγκαταστάσεις ή Τοποθεσίες Έργων Οργανισμού',
            autoLoad: true,
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.PTL_COMPANY_BRANCHES',
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
                    xtype: 'pagingtoolbar',
                    dock: 'bottom',
                    displayInfo: true,
                    firstText: 'Πρώτη Σελίδα',
                    lastText: 'Τελευταία Σελίδα',
                    nextText: 'Επόμενη Σελίδα',
                    prevText: 'Προηγούμενη Σελίδα',
                    refreshText: 'Ανανέωση',
                    store: 'company.PTL_COMPANY_BRANCHES'
                },
                {
                    xtype: 'toolbar',
                    dock: 'top',
                    items: [
                        {
                            xtype: 'button',
                            glyph: 'xf067@FontAwesome',
                            text: 'Προσθήκη Υποκαταστήματος από ΕΡΓΑΝΗ ή άλλης Τοποθεσίας',
                            listeners: {
                                click: 'onNewBranch'
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
                var emp_comp = Ext.create('widget.companydoctoranndoctorform', {});
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
                    /*
                     if (record.get('brAddrPe')!==null && record.get('brAddrD')===null){
                     form.getForm().findField('brAddrPe').setDisabled(false);
                     form.getForm().findField('brAddrD').setDisabled(false);
                     }
                     else if (record.get('brAddrPe')!==null && record.get('brAddrD')!==null){
                     form.getForm().findField('brAddrPe').setDisabled(false);
                     form.getForm().findField('brAddrD').setDisabled(false);
                     form.getForm().findField('brAddrK').setDisabled(false);
                     }
                     else if (record.get('brAddrP')!==null && record.get('brAddrPe')===null){
                     form.getForm().findField('brAddrPe').setDisabled(false);
                     }
                     */
                }
                else{

                    form.getForm().findField('protNoview').setValue(record.get('protNo'));
                    emp_comp.down('toolbar').getComponent('submitbutton').hide();
                    emp_comp.down('toolbar').getComponent('savebutton').hide();

                    if(record.get('ieAnnStatus')===0 && record.get('reqStatus')===5){
                        emp_comp.down('toolbar').getComponent('deletebutton').show();
                        emp_comp.down('toolbar').getComponent('changebutton').hide();
                        emp_comp.down('toolbar').getComponent('changeProgrambutton').hide();
                    }
                    else if(record.get('ieAnnStatus')===1 && record.get('reqStatus')===6){
                        emp_comp.down('toolbar').getComponent('changebutton').show();
                        emp_comp.down('toolbar').getComponent('changeProgrambutton').show();
                        emp_comp.down('toolbar').getComponent('deletebutton').hide();
                    }
                    else if(record.get('ieAnnStatus')===-1 && record.get('reqStatus')===7){
                        emp_comp.down('toolbar').hide();
                    }
                    else if(record.get('ieAnnStatus')===2){
                        emp_comp.down('toolbar').getComponent('changebutton').hide();
                        emp_comp.down('toolbar').getComponent('changeProgrambutton').hide();
                        emp_comp.down('toolbar').getComponent('changeProgramSavebutton').hide();
                        emp_comp.down('toolbar').getComponent('deletebutton').hide();
                    }


                    form.getForm().findField('file').hide();

                }

                form.getForm().findField('a_new_form').setValue(false);

                form.getForm().findField('exyppBasic').suspendEvents();
                form.getForm().findField('cooperationTypeBasic').suspendEvents();
                emp_comp.show();
                emp_comp.mask("Παρακαλώ Περιμένετε...");

                form.getForm().findField('dateStart').setValue(this.timestampToDate(record.get('dateStart')));
                form.getForm().findField('dateEnd').setValue(this.timestampToDate(record.get('dateEnd')));
                if(record.get('cooperationTypeBasic')==3) {
                    var exyppB = record.data.exyppBasic.toString();
                    record.data.exyppBasic = null;
                }
                form.loadRecord(record);

                // TOTAL NUMBERS LOAD
                form.getForm().findField('cooperationTypeBasic').setValue(record.get('cooperationTypeBasic'));
                if(record.get('cooperationTypeBasic')==3) {
                    form.getForm().findField('exyppBasic').setValue(exyppB);
                    form.getForm().findField('exyppBasic').show();
                }else{
                    form.getForm().findField('exyppBasic').allowBlank=true;
                }
                form.getForm().findField('dateStart').setValue(this.timestampToDate(record.get('dateStart')));
                form.getForm().findField('dateEnd').setValue(this.timestampToDate(record.get('dateEnd')));

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

                    Ext.getCmp('entryadd').hide();
                    Ext.getCmp('entrydel').hide();
                    Ext.getCmp('newIeEntry').destroy();
                    Ext.getCmp('delIeEntry').destroy();

                }

                var grid = this;
                var store11 = Ext.StoreMgr.lookup('company.DoctorAnn.IeEntries');
                store11.getProxy().setUrl(record.get('doctorAnnIeEntries'));
                store11.load({
                    callback: function(records, operation, success) {
                        var iesEntriesArr = [];
                        for (var i = 0; i < records.length; i++) {

                            var newentrys = Ext.create('widget.companydoctorannieentry', {});
                            newentrys.down().items.get(0).setValue();
                            if (records[i].get('cooperationType') !== null)
                                newentrys.down().items.get(0).setValue(records[i].get('cooperationType').toString());
                            if (parseInt(records[i].get('cooperationType')) === 3)
                                newentrys.down().items.get(1).setValue(records[i].get('doctorRegrequestUserId').toString());
                            newentrys.down().items.get(2).setValue(records[i].get('ieAfm'));
                            newentrys.down().items.get(3).setValue(records[i].get('ieFullname'));
                            if (records[i].get('ieSpeciality') !== null)
                                newentrys.down().items.get(4).setValue(records[i].get('ieSpeciality'));
                            newentrys.down().items.get(5).setValue(records[i].get('ieSpecialityOther'));
                            newentrys.down().items.get(6).setValue(records[i].get('doctorRegrequestUserId'));
                            newentrys.down().items.get(7).setValue(records[i].get('doctorRegrequestId'));

                            if ( parseInt(Ext.getCmp('ieAnnIeEntries').up('form').getForm().findField('cooperationTypeBasic').getValue()) === 3) {
                                newentrys.down().items.get(0).readOnly = true;
                                newentrys.down().items.get(0).hidden = true;
                            }
                            if (records[i].get('cooperationType') === 3) {
                                newentrys.down().items.get(0).readOnly = true;
                                newentrys.down().items.get(1).readOnly = true;
                            }
                            Ext.getCmp('ieAnnIeEntries').add(newentrys);
                            if (records[i].get('cooperationType') !== 3){
                                iesEntriesArr.push({
                                    id: records[i].get('doctorRegrequestUserId'),
                                    name: records[i].get('ieFullname')
                                });
                            }
                        }
                        var storeLocal = Ext.StoreMgr.lookup('company.DoctorAnn.LocalIeEntries');
                        storeLocal.loadData(iesEntriesArr, false);

                        //form.getForm().findField('taEntriesnum').setValue(records.length);
                        if (record.get('subStatus') === 2) {

                            fields = form.getForm().getFields();
                            fields.each(function (field) {
                                field.enable();
                                field.setReadOnly(true);
                            });

                        }
                        var store1 = Ext.StoreMgr.lookup('company.DoctorAnn.Diary');
                        store1.getProxy().setUrl(record.get('doctorAnnDiaryEntries'));
                        store1.load({
                            callback: function(records, operation, success) {
                                for (var i = 0; i < records.length; i++) {
                                    var newentrys = Ext.create('widget.companydoctoranndiary', {});
                                    newentrys.down().items.get(3).suspendEvents();
                                    newentrys.down().items.get(4).suspendEvents();

                                    newentrys.down().items.get(0).setValue(grid.timestampToDate(records[i].get('visitDate')));
                                    newentrys.down().items.get(1).setValue(records[i].get('visitTime'));
                                    newentrys.down().items.get(2).setValue(records[i].get('visitDurationMinutes'));
                                    var hs = Math.floor(records[i].get('visitDurationMinutes')/60);
                                    newentrys.down().items.get(3).setValue(hs);
                                    var ms = records[i].get('visitDurationMinutes')-(Math.floor(records[i].get('visitDurationMinutes')/60)*60);
                                    newentrys.down().items.get(4).setValue(ms);
                                    if (records[i].get('compIeAnnIeId') !== null)
                                        newentrys.down().items.get(5).setValue(records[i].get('compIeAnnIeId'));
                                    if(i>=1){
                                        newentrys.down().items.get(0).hideLabel=true;
                                        newentrys.down().items.get(1).hideLabel=true;
                                        newentrys.down().items.get(2).hideLabel=true;
                                        newentrys.down().items.get(3).hideLabel = true;
                                        newentrys.down().items.get(4).hideLabel = true;
                                        newentrys.down().items.get(5).hideLabel = true;

                                    }
                                    newentrys.down().items.get(3).resumeEvents(false);
                                    newentrys.down().items.get(4).resumeEvents(false);

                                    Ext.getCmp('diaryEntries').add(newentrys);
                                }
                                form.getForm().findField('diaryEntriesnum').setValue(records.length);
                                if(record.get('subStatus')===2){

                                    fields = form.getForm().getFields();
                                    fields.each(function (field) {
                                        field.enable();
                                        field.setReadOnly (true);});

                                }

                                var ptlBranch = Ext.getCmp('companyDoctorAnn_Branches').getSelectionModel().getSelection()[0];
                                emp_comp.down('form').getForm().findField('compPtlBranchId').setValue(ptlBranch.get('ptlBranchId'));
                                emp_comp.down('form').getForm().findField('compEbrBranchId').setValue(ptlBranch.get('ebrBranchId'));
                                emp_comp.down('form').getForm().findField('brAddr').setValue(ptlBranch.get('brAddr'));
                                emp_comp.down('form').getForm().findField('brAddrTk').setValue(ptlBranch.get('brAddrTk'));
                                emp_comp.down('form').getForm().findField('brAddrP').setValue(ptlBranch.get('brAddrP'));
                                emp_comp.down('form').getForm().findField('brAddrPe').setValue(ptlBranch.get('brAddrPe'));
                                emp_comp.down('form').getForm().findField('brAddrD').setValue(ptlBranch.get('brAddrD'));
                                emp_comp.down('form').getForm().findField('brAddrK').setValue(ptlBranch.get('brAddrK'));
                                form.getForm().findField('exyppBasic').resumeEvents(false);
                                form.getForm().findField('cooperationTypeBasic').resumeEvents(false);

                                emp_comp.unmask();
                            }

                        });

                    }
                });



            },
            flex: 1,
            autoScroll: true,
            height: '50%',
            id: 'companyDoctorAnn_Doctors',
            itemId: 'companyDoctorAnn_Doctors',
            title: 'Καρτέλες Αναγγελίες Ιατρών Εργασίας',
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.DoctorAnn.DOCTOR_ANNS_GRID',
            columns: [
                {
                    xtype: 'rownumberer'
                },
                {
                    xtype: 'actioncolumn',
                    width: 30,
                    enableColumnHide: false,
                    sealed: true,
                    dataIndex: 'ieAnnStatus',
                    hideable: false,
                    menuDisabled: true,
                    items: [
                        {
                            getClass: function(v, metadata, r, rowIndex, colIndex, store) {
                                if (v===-2){
                                    this.items[0].tooltip = 'Μη καταχωρημένη';
                                    return ''; // css for icon
                                }
                                else if(v===2 && r.get('reqStatus')===7){
                                    this.items[0].tooltip = 'Αντικαταστάθηκε';
                                    return 'inactivependingU'; // css for icon
                                }
                                else if(v===2 && r.get('reqStatus')===6){
                                    this.items[0].tooltip = 'Παραιτήθηκε';
                                    return 'activependingU'; // css for icon
                                }
                                else if(Date.parse(new Date())>Date.parse(r.get('dateEnd'))){
                                    this.items[0].tooltip = 'Έληξε';
                                    return 'previousactiveU'; // css for icon
                                }
                                else if(v===0){
                                    this.items[0].tooltip = 'Αναμονή Γιατρού';
                                    return 'pendingU'; // css for icon
                                }
                                else if(v===1 && r.get('reqStatus')===6){
                                    this.items[0].tooltip = 'Αποδοχή Γιατρού';
                                    return 'activeU'; // css for icon
                                }
                                else if(v===-1 && r.get('reqStatus')===7){
                                    this.items[0].tooltip = 'Απόρριψη Γιατρού';
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
                    flex: 20,
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
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        if (value!==null){
                            if (record.get('subStatus')===1){
                                return "";
                            }
                            else{
                                var store2 = Ext.StoreMgr.lookup( 'shared.COMPL_STATUS' );
                                if (record.get('reqStatus')>0){

                                    return store2.findRecord('reqStatus', record.get('reqStatus').toString()).get('description');
                                }
                                else
                                    return "";



                            }
                        }
                        else
                            return "";
                    },
                    sortable: false,
                    dataIndex: 'ieAnnStatus',
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
                    refresh: 'onDoctorViewRefresh',
                    itemdblclick: 'onDoctorViewItemDblClick',
                    beforerefresh: 'onDoctorViewBeforeRefresh'
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
                    store: 'company.DoctorAnn.DOCTOR_ANNS_GRID'
                },
                {
                    xtype: 'toolbar',
                    dock: 'top',
                    items: [
                        {
                            xtype: 'button',
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα Αναγγελία Ιατρού Εργασίας',
                            listeners: {
                                click: 'onNewDoctor'
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
                            html: 'Κατάσταση Αναγγελίας: <img height="16px" src="static/userapp/resources/pending.png"/> Αναμονή Ιατρού&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <img height="16px" src="static/userapp/resources/active.png"/> Αποδοχή Ιατρού&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<img height="16px" src="static/userapp/resources/inactive.png"/> Απόρριψη Ιατρού&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<img height="16px" src="static/userapp/resources/inactivepending.png"/> Αντικαταστάθηκε&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<img height="16px" src="static/userapp/resources/activepending.png"/> Παραιτήθηκε&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<img height="16px" src="static/userapp/resources/previousactive.png"/> Έληξε ',
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

    onBranchItemDblClick: function(dataview, record, item, index, e, eOpts) {
        dataview.up().icon_dbl_click_handler(record);

    },

    onBranchViewRefresh: function(dataview, eOpts) {
        if (dataview.store.data.length<1 && dataview.store.filters.length>0){
            Ext.addon.MessagePop.msg("Προσοχή!", "Δεν υπάρχουν εγγραφές που να αντιστοιχούν στην αναζήτηση σας. Προβάλονται όλες οι εγγραφές της σελίδας.", 5000);
            dataview.up('grid').plugins[1].clearValues();
            dataview.store.reload();
        }
        Ext.getCmp('companyDoctorAnn_Doctors').store.clearData();
        Ext.getCmp('companyDoctorAnn_Doctors').view.refresh();
    },

    onBranchViewItemClick: function(dataview, record, item, index, e, eOpts) {
        var doctors = Ext.getCmp('companyDoctorAnn_Doctors');
        var ptlBranchId = Ext.getCmp('companyDoctorAnn_Branches').getSelectionModel().getSelection()[0].get('ptlBranchId');
        doctors.store.proxy.setUrl('/compIeAnn/search/findByPtlBranchId?branchId='+ptlBranchId);
        doctors.getStore().reload({
            callback: function(){
                doctors.getView().refresh();
            }
        });
    },

    onBranchViewContainerClick: function(dataview, e, eOpts) {
        var dailygrid = Ext.getCmp('companyDoctorAnn_Doctors');
        dailygrid.store.clearData();
        dailygrid.getView().refresh();
    },

    onNewBranch: function(button, e, eOpts) {
        var emp_comp = Ext.create('widget.companydoctorannbranchform', {
        });
        emp_comp.down('form').items.getAt(4).show();
        emp_comp.show();
    },

    onDoctorViewRefresh: function(dataview, eOpts) {
        if (dataview.store.data.length<1 && dataview.store.filters.length>0){
            Ext.addon.MessagePop.msg("Προσοχή!", "Δεν υπάρχουν εγγραφές που να αντιστοιχούν στην αναζήτηση σας. Προβάλονται όλες οι εγγραφές της σελίδας.", 5000);
            dataview.up('grid').plugins[1].clearValues();
            dataview.store.reload();
        }
    },

    onDoctorViewItemDblClick: function(dataview, record, item, index, e, eOpts) {
        dataview.up().icon_dbl_click_handler(record);

    },

    onDoctorViewBeforeRefresh: function(dataview, eOpts) {
        if(Ext.getCmp('companyDoctorAnn_Branches').getSelectionModel().getSelection().length>0){
            var ptlBranchId = Ext.getCmp('companyDoctorAnn_Branches').getSelectionModel().getSelection()[0].get('ptlBranchId');
            dataview.store.proxy.setUrl('/compIeAnn/search/findByPtlBranchId?branchId='+ptlBranchId);
        }



    },

    onNewDoctor: function(button, e, eOpts) {
        if(Ext.getCmp('companyDoctorAnn_Branches').getSelectionModel().getSelection().length>0){
            var successCall = function(options, success, response) {
                if(response.responseText!=="0" ){
                    Ext.Msg.alert('Προσοχή!', 'Υπάρχει αναγγελία σε αναμονή ή <br>προηγούμενη ενεργή ή που έχει τερματιστεί.'+
                        ' <br>Επιλέξτε την από την λίστα και επιλέξτε διαγραφή στην περίπτωση αναμονής <br>αλλιώς επιλέξτε αντικατάσταση.');
                }
                else{
                    var emp_comp = Ext.create('widget.companydoctoranndatesform', {});
                    emp_comp.show();
                }

            };
            Ext.Ajax.request({
                url: "/compIeAnn/search/findCategIeAnn",
                params: {
                    ptlBranchId: Ext.getCmp('companyDoctorAnn_Branches').getSelectionModel().getSelection()[0].get('ptlBranchId')
                },
                method: "GET",
                callback: successCall
            });

            /* WITHOUT CALL
             if(Ext.getCmp('companyDoctorAnn_Doctors').store.findRecord('ieAnnStatus', 0) ){
             Ext.Msg.alert('Προσοχή!', 'Υπάρχει προηγούμενη σε αναμονή αναγγελία.'+
             ' Επιλέξτε την από την λίστα και πατήστε διαγραφή.');
             }
             else if( Ext.getCmp('companyDoctorAnn_Doctors').store.findRecord('ieAnnStatus', 1) ){
             Ext.Msg.alert('Προσοχή!', 'Υπάρχει προηγούμενη ενεργή αναγγελία.'+
             ' Επιλέξτε την από την λίστα και πατήστε αντικατάστάση.');
             }
             else{
             var emp_comp = Ext.create('widget.companydoctoranndatesform', {});
             emp_comp.show();

             }
             */
        }
        else
            Ext.Msg.alert('Προσοχή!', 'Δεν έχετε επιλέξει κάποιο υποκατάστημα από την παραπάνω λίστα.');


    }

});