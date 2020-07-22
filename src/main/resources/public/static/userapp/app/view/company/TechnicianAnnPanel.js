/*
 * File: app/view/company/TechnicianAnnPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */
Ext.define('MyApp.view.company.TechnicianAnnPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.companytechnicianannpanel',

    requires: [
        'MyApp.view.company.TechnicianAnnPanelViewModel',
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
        type: 'companytechnicianannpanel'
    },
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'gridpanel',
            icon_dbl_click_handler: function (record) {
                var emp_comp = Ext.create('widget.companydoctorannbranchform', {});
                //Ext.getCmp('compdoctorannbranch_save_submit_toolbar').hide();

                var form = emp_comp.down('form');
                var fields = form.getForm().getFields();

                form.getForm().findField('a_new_form').setValue(false);

                form.loadRecord(record);

                fields.each(function (field) {
                    field.enable();
                    field.setReadOnly(true);
                });

                form.getForm().findField('brDescr').setReadOnly(false);
                form.getForm().findField('brActive').setReadOnly(false);
                if (record.get('brActive') == 1)
                    form.getForm().findField('brActive').setValue(true);
                else
                    form.getForm().findField('brActive').setValue(false);
                emp_comp.down('toolbar').getComponent('savebutton').hide();
                emp_comp.down('toolbar').getComponent('updatebutton').show();

                emp_comp.show();

            },
            flex: 1,
            autoScroll: true,
            //height: '40%',
            id: 'companyTechnicianAnn_Branches',
            itemId: 'companyTechnicianAnn_Branches',
            title: 'Εγκαταστάσεις ή Τοποθεσίες Έργων Οργανισμού',
            autoLoad: true,
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.PTL_COMPANY_BRANCHES',
            columns: [
                {
                    xtype: 'rownumberer',
                    width: 30
                },
                {
                    xtype: 'gridcolumn',
                    filterField: true,
                    sortable: true,
                    dataIndex: 'brDescr',
                    text: 'Περιγραφή Παραρτήματος',
                    flex: 10,
                    filter: {
                        type: 'string',
                        emptyText: 'Εισαγωγή κειμένου...'
                    }
                },
                {
                    xtype: 'actioncolumn',
                    width: 30,
                    enableColumnHide: false,
                    hideable: false,
                    menuDisabled: true,
                    items: [
                        {
                            getClass: function (v, metadata, r, rowIndex, colIndex, store) {

                                this.items[0].tooltip = 'Προβολή';
                                return 'viewme'; // css for icon

                            },
                            handler: function (view, rowIndex, colIndex, item, e, record, row) {
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
                            text: 'Προσθήκη Υποκαταστήματος από ΕΡΓΑΝΗ ή άλλης Τοποθεσίας',
                            listeners: {
                                click: 'onNewBranch'
                            }
                        },
                        {
                            xtype: 'button',
                            iconCls: 'x-tool-img x-tool-print',
                            text: 'Εκτύπωση Παραρτημάτων',
                            listeners: {
                                click: 'onPrintClick'
                            }
                        },
                        {
                            xtype: 'radiogroup',
                            //fieldLabel: 'Two Columns',
                            // Arrange radio buttons into two columns, distributed vertically
                            columns: 3,
                            vertical: true,
                            items: [
                                { boxLabel: 'Ενεργά', name: 'rb', inputValue: '1', margin: '3 3 3 3', checked: true},
                                { boxLabel: 'Ανενεργά', name: 'rb', inputValue: '0', margin: '3 3 3 3'},
                                { boxLabel: 'Όλα', name: 'rb', inputValue: '2', margin: '3 3 3 3'},
                            ],
                            listeners: {
                                change: function(field, newValue, oldValue)
                                {
                                    var branches = Ext.getCmp('companyTechnicianAnn_Branches');
                                    if (newValue.rb == 2)
                                        branches.store.proxy.setUrl('/compPtlBranch/search/findAllList');
                                    else
                                        branches.store.proxy.setUrl('/compPtlBranch/search/findByActivationCode?brActive=' + newValue.rb);
                                    branches.getStore().reload({
                                        callback: function () {
                                            branches.getView().refresh();
                                        }
                                    });
                                }
                            }
                        },
                        {
                            xtype: 'label',
                            html: '<img height="19px"  src="static/userapp/resources/info.png" title="Για να ενεργοποιήσετε ή να απενεργοποιήσετε ένα παράρτημα,\nκάντε διπλό κλικ σε αυτό που επιθυμείτε, ρυθμίστε κατάλληλα την επιλογή ΄Ενεργό΄\nκαι πατήστε ΄Ενημέρωση΄."/>',
                            width: 19
                        },
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
            xtype: 'splitter',
            height: '6px',
            style: 'background-color: #cecece;'
        },
        {
            xtype: 'tabpanel',
            renderTo: Ext.getBody(),
            cls: 'navigationTabPanel',
            flex: 1,
            items: [
                {
                    xtype: 'gridpanel',
                    loadCategNums: function (wid) {
                        var successAns = function (options, success, response) {
                            if (Ext.JSON.decode(response.responseText).success) {

                                var resp = Ext.JSON.decode(response.responseText);

                                wid.down('form').getForm().findField('compCategANum').setValue(resp.categANum);
                                wid.down('form').getForm().findField('compCategBNum').setValue(resp.categBNum);
                                wid.down('form').getForm().findField('compCategCNum').setValue(resp.categCNum);
                            }
                            else {
                                Ext.Msg.alert('Σφάλμα!', 'Υπάρχει πρόβλημα στην επισύναψη στοιχείων');

                            }
                        };

                        Ext.Ajax.request({
                            url: '/companyExtraInfo',
                            method: "GET",
                            callback: successAns
                        });
                    },
                    timestampToDate: function (timestamp) {

                        var pD = timestamp.replace(/[^0-9]+/g, ' ').split(" ");
                        return (pD[2] + "-" + pD[1] + "-" + pD[0]);
                    },
                    timestampToTime: function (timestamp) {

                        var pD = timestamp.replace(/[^0-9]+/g, ' ').split(" ");
                        return (pD[3] + ":" + pD[4]);
                    },
                    icon_dbl_click_handler: function (record) {

                        var emp_comp = Ext.create('widget.companytechniciananntechnicianform', {});
                        var form = emp_comp.down('form');
                        var fields = form.getForm().getFields();
                        if (record.get('protNo') == null)
                            this.loadCategNums(emp_comp);
                        else
                        {
                            form.getForm().findField('compCategANum').setValue(record.get('compCategANum'));
                            form.getForm().findField('compCategBNum').setValue(record.get('compCategBNum'));
                            form.getForm().findField('compCategCNum').setValue(record.get('compCategCNum'));
                            form.getForm().findField('totalHours').setValue(record.get('totalHours'));
                            var tms = parseInt(form.getForm().findField('totalHours').getValue());
                            var hours1 = Math.floor(tms / 60);
                            var minutes1 = tms - (hours1 * 60);
                            var textR = hours1.toString() + " ώρες και " + minutes1.toString() + " λεπτά.";
                            form.getForm().findField('totalHoursText').setValue(textR);
                        }
                        emp_comp.down('toolbar').getComponent('deletebutton').show();

                        if (record.get('pauseExplanation') != "" && record.get('pauseExplanation') != null) {
                            Ext.getCmp('taAnnPauseData').show();
                            //form.getForm().findField('pauseExplanation').show();
                            form.getForm().findField('pauseExplanation').setValue(record.get('pauseExplanation'));
                            form.getForm().findField('protNoviewPause').setValue(record.get('protNoPause'));
                            try
                            {
                                var pDPause = record.get('protDatePause').replace(/[^0-9]+/g, ' ').split(" ");
                                form.getForm().findField('protDateviewPause').setValue(pDPause[2] + "-" + pDPause[1] + "-" + pDPause[0]);
                            }
                            catch (err)
                            {
                                form.getForm().findField('protDateviewPause').hide();
                                form.getForm().findField('protNoviewPause').hide();
                            }
                        }

                        if (record.get('protNoResign') != "" && record.get('protNoResign') != null) {
                            Ext.getCmp('taAnnResignData').show();
                            form.getForm().findField('protNoviewResign').setValue(record.get('protNoResign'));
                            try
                            {
                                var pDResign = record.get('protDateResign').replace(/[^0-9]+/g, ' ').split(" ");
                                form.getForm().findField('protDateviewResign').setValue(pDResign[2] + "-" + pDResign[1] + "-" + pDResign[0]);
                            }
                            catch (err)
                            {
                                form.getForm().findField('protDateviewResign').hide();
                                form.getForm().findField('protNoviewResign').hide();
                            }
                        }


                        if (record.get('subStatus') === 1) {
                            form.getForm().findField('protNoview').setValue("-");

                            if (record.get('compAddrPe') !== null && record.get('compAddrD') === null) {
                                form.getForm().findField('compAddrPe').setDisabled(false);
                                form.getForm().findField('compAddrD').setDisabled(false);
                            }
                            else if (record.get('compAddrPe') !== null && record.get('compAddrD') !== null) {
                                form.getForm().findField('compAddrPe').setDisabled(false);
                                form.getForm().findField('compAddrD').setDisabled(false);
                                form.getForm().findField('compAddrK').setDisabled(false);
                            }
                            else if (record.get('compAddrP') !== null && record.get('compAddrPe') === null) {
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
                        else {

                            form.getForm().findField('protNoview').setValue(record.get('protNo'));
                            emp_comp.down('toolbar').getComponent('submitbutton').hide();
                            emp_comp.down('toolbar').getComponent('savebutton').hide();

                            if (record.get('taAnnStatus') === 0 && record.get('reqStatus') === 5) {
                                emp_comp.down('toolbar').getComponent('deletebutton').show();
                                emp_comp.down('toolbar').getComponent('pausebutton').hide();
                                emp_comp.down('toolbar').getComponent('changebutton').hide();
                                emp_comp.down('toolbar').getComponent('changeProgrambutton').hide();
                            }
                            else if (record.get('taAnnStatus') === 1 && record.get('reqStatus') === 6) {
                                emp_comp.down('toolbar').getComponent('pausebutton').show();
                                emp_comp.down('toolbar').getComponent('changebutton').show();
                                emp_comp.down('toolbar').getComponent('changeProgrambutton').show();
                                emp_comp.down('toolbar').getComponent('deletebutton').hide();
                            }
                            else if ((record.get('taAnnStatus') === -1 && record.get('reqStatus') === 7) || (record.get('taAnnStatus') === 3 && record.get('reqStatus') === 7)) {
                                emp_comp.down('toolbar').hide();
                            }
                            else if (record.get('taAnnStatus') === 2 || record.get('taAnnStatus') === 3) {
                                emp_comp.down('toolbar').getComponent('pausebutton').hide();
                                emp_comp.down('toolbar').getComponent('changebutton').hide();
                                emp_comp.down('toolbar').getComponent('changeProgrambutton').hide();
                                emp_comp.down('toolbar').getComponent('changeProgramSavebutton').hide();
                                emp_comp.down('toolbar').getComponent('deletebutton').hide();
                            }


                            form.getForm().findField('file').hide();

                        }

                        form.getForm().findField('a_new_form').setValue(false);

                        //form.getForm().findField('exyppBasic').suspendEvents();
                        //form.getForm().findField('cooperationTypeBasic').suspendEvents();
                        emp_comp.show();
                        emp_comp.mask("Παρακαλώ Περιμένετε...");

                        form.getForm().findField('dateStart').setValue(this.timestampToDate(record.get('dateStart')));
                        form.getForm().findField('dateEnd').setValue(this.timestampToDate(record.get('dateEnd')));


                        if (record.get('cooperationTypeBasic') == 3) {
                            if (record.data.exyppBasic == null)
                                var exyppB = "";
                            else
                                var exyppB = record.data.exyppBasic.toString();
                            record.data.exyppBasic = null;
                        }

                        if (record.get('categCNum') != 0)
                        {
                            form.getForm().findField('categANum').suspendEvents();
                            form.getForm().findField('categBNum').suspendEvents();
                        }
                        else if (record.get('categBNum') != 0)
                        {
                            form.getForm().findField('categANum').suspendEvents();
                        }
                        form.loadRecord(record);
                        form.getForm().findField('categANum').resumeEvents();
                        form.getForm().findField('categBNum').resumeEvents();


                        if (record.get('branchSector') != null) {
                            form.getForm().findField('branchSector').setValue(record.get('branchSector').toString());
                            form.getForm().findField('cooperationTypeBasic').enable();
                        }

                        form.getForm().findField('branchSectorId').setValue(record.get('branchSector'));

                        if (form.getForm().findField('cooperationTypeBasic').getValue() == "0")
                            form.getForm().findField('cooperationTypeBasic').setValue(null);

                        if (form.getForm().findField('cooperationTypeBasic').getValue() != null) {
                            form.getForm().findField('cooperationTypeBasic').enable();
                            // TOTAL NUMBERS LOAD
                            form.getForm().findField('firsttimeloadCooperation').setValue(1);
                            form.getForm().findField('cooperationTypeBasic').setValue(record.get('cooperationTypeBasic'));
                        }


                        if (record.get('cooperationTypeBasic') == 3) {
                            form.getForm().findField('firsttimeload').setValue(1);
                            console.log('value before setValue: ' + form.getForm().findField('exyppBasic').getValue());
                            form.getForm().findField('exyppBasic').setValue(exyppB);
                            console.log('value after setValue: ' + form.getForm().findField('exyppBasic').getValue());
                            form.getForm().findField('exyppBasic').show();
                        } else {
                            form.getForm().findField('exyppBasic').allowBlank = true;
                        }

                        // form.getForm().findField('taFullname').setValue(record.get('taFullname'));
                        // form.getForm().findField('technicianRegrequestId').setValue(record.get('technicianRegrequestId'));
                        // form.getForm().findField('technicianRegrequestUserId').setValue(record.get('technicianRegrequestUserId'));
// if(parseInt(record.get('cooperationType'))===3)
                        //     form.getForm().findField('exypp').setValue(record.get('technicianRegrequestUserId').toString());
                        form.getForm().findField('dateStart').setValue(this.timestampToDate(record.get('dateStart')));
                        form.getForm().findField('dateEnd').setValue(this.timestampToDate(record.get('dateEnd')));

                        /*if (record.get('branchSector')>0 || record.get('branchSector')!==null){
                         var store2 = Ext.StoreMgr.lookup( 'BRANCH_SECTOR' );
                         store2.getProxy().url='/taBranchSector/search/findById'
                         store2.getProxy().setExtraParam("id", record.get('branchSector'));
                         store2.load( { callback : function(records, operation, success) {
                         var branchSector = Ext.decode(operation._response.responseText);
                         if (branchSector !== null)
                         form.getForm().findField('branchSector').setValue(branchSector.cdTect);
                         }
                         });
                         }*/


                        if (record.get('subStatus') === 1) {

                            if (record.get('protDate') !== null) {
                                form.getForm().findField('protDateview').setValue("");
                                form.getForm().findField('protYear').setValue("");
                                form.getForm().findField('submitTime').setValue("");
                            }

                            form.getForm().findField('protDateview').setValue("");
                            form.getForm().findField('protDate').setValue("");

                            form.getForm().findField('reqStatus').setValue("");

                            if (record.get('attachedDocId') !== -1) {
                                var vfc = Ext.ComponentQuery.query('#Exist_File')[0];
                                vfc.show();
                            }

                        }
                        else if (record.get('subStatus') === 2) {

                            var pD = record.get('protDate').replace(/[^0-9]+/g, ' ').split(" ");
                            form.getForm().findField('protDateview').setValue(pD[2] + "-" + pD[1] + "-" + pD[0]);
                            form.getForm().findField('reqStatus').setValue(record.get('reqStatus').toString());
                            if (record.get('reqStatus') > 0)
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

                            if (record.get('sepeDept') > 0 || record.get('sepeDept') !== null) {
                                var store2 = Ext.StoreMgr.lookup('shared.SEPE_DEPT');
                                store2.getProxy().url = '/TSepeDepartment/search/findById'
                                store2.getProxy().setExtraParam("cdId", record.get('sepeDept'));
                                store2.load({
                                    callback: function (records, operation, success) {
                                        var sepeDept = Ext.decode(operation._response.responseText);
                                        if (sepeDept !== null)
                                            form.getForm().findField('sepeDept').setValue(sepeDept.cdCode + ' - ' + sepeDept.cdText);
                                    }
                                });
                            }

                            //get message
                            if (record.get('caseId') !== null && record.get('docId') !== null) {

                                form.getForm().findField('StatusMsg').show();
                                var store3 = Ext.StoreMgr.lookup('shared.CASE_MESSAGE');
                                store3.getProxy().setExtraParams({
                                    "caseId": record.get('caseId'),
                                    "docId": record.get('docId')
                                });
                                store3.load({
                                    callback: function (records, operation, success) {
                                        if (records !== null)
                                            record.getField("StatusMsg").value = records[0].getData().cdText;
                                    }
                                });
                            }


                            if (record.get('attachedDocId') !== -1) {
                                var vfc = Ext.ComponentQuery.query('#Exist_File')[0];
                                vfc.items.getAt(1).hide();
                                vfc.show();
                            }
                            if (record.get('attachedDocIdPause') !== -1 && record.get('attachedDocIdPause') != null) {
                                var vfc = Ext.ComponentQuery.query('#Exist_FilePause')[0];
                                vfc.show();
                            }


                            Ext.getCmp('taentryadd').hide();
                            Ext.getCmp('taentrydel').hide();
                            Ext.getCmp('taRecurrentEntryadd').hide();
                            Ext.getCmp('newTaEntry').destroy();
                            Ext.getCmp('delTaEntry').destroy();

                        }
//       form.getForm().findField('cooperationType').setValue(record.get('taSpeciality').toString());

                        var grid = this;
                        var store11 = Ext.StoreMgr.lookup('company.TechnicianAnn.TaEntries');
                        store11.getProxy().setUrl(record.get('technicianAnnTaEntries'));
                        store11.load({
                            callback: function (records, operation, success) {
                                //form.getForm().findField('exyppBasic').suspendEvents();
                                //form.getForm().findField('cooperationTypeBasic').suspendEvents();
                                console.log("mpika");
                                if (records != null) {
                                    records.sort(function (a, b) {
                                        // Compare the 2 values
                                        if (a.data.cooperationType < b.data.cooperationType) return 1;
                                        if (a.data.cooperationType > b.data.cooperationType) return -1;
                                        return 0;
                                    });
                                }
                                var tasEntriesArr = [];

                                for (var i = 0; i < records.length; i++) {

                                    var newentrys = Ext.create('widget.companytechniciananntaentry', {});
                                    newentrys.down().items.get(0).setValue()
                                    if (records[i].get('cooperationType') !== null && records[i].get('cooperationType') != "0")
                                        newentrys.down().items.get(0).setValue(records[i].get('cooperationType').toString());
                                    if (parseInt(records[i].get('cooperationType')) === 3)
                                        newentrys.down().items.get(1).setValue(records[i].get('technicianRegrequestUserId').toString());
                                    newentrys.down().items.get(2).setValue(records[i].get('taAfm'));
                                    newentrys.down().items.get(3).setValue(records[i].get('taFullname').trim());
                                    if (records[i].get('taSpeciality') !== null)
                                        newentrys.down().items.get(4).setValue(Ext.util.JSON.decode("[" + records[i].get('taSpeciality') + "]").toString());
                                    newentrys.down().items.get(5).setValue(records[i].get('taSpecialityOther'));
                                    newentrys.down().items.get(6).setValue(records[i].get('technicianRegrequestUserId'));
                                    newentrys.down().items.get(7).setValue(records[i].get('technicianRegrequestId'));

                                    if (parseInt(Ext.getCmp('taAnnTaEntries').up('form').getForm().findField('cooperationTypeBasic').getValue()) === 3) {
                                        newentrys.down().items.get(0).readOnly = true;
                                        newentrys.down().items.get(0).hidden = true;
                                    }
                                    if (records[i].get('cooperationType') === 3) {
                                        newentrys.down().items.get(0).readOnly = true;
                                        newentrys.down().items.get(1).readOnly = true;
                                    }
                                    Ext.getCmp('taAnnTaEntries').add(newentrys);
                                    if (records[i].get('cooperationType') !== 3 && records[i].get('cooperationType') != "0") {
                                        tasEntriesArr.push({
                                            id: records[i].get('technicianRegrequestUserId'),
                                            name: records[i].get('taFullname').trim()
                                        });
                                    }
                                }

                                var storeLocal = Ext.StoreMgr.lookup('company.TechnicianAnn.LocalTaEntries');

                                storeLocal.loadData(tasEntriesArr, false);

                                //form.getForm().findField('taEntriesnum').setValue(records.length);
                                if (record.get('subStatus') === 2) {

                                    fields = form.getForm().getFields();
                                    fields.each(function (field) {
                                        field.enable();
                                        field.setReadOnly(true);
                                    });

                                }

                                var store1 = Ext.StoreMgr.lookup('company.TechnicianAnn.Diary');
                                store1.getProxy().setUrl(record.get('technicianAnnDiaryEntries'));
                                store1.load({
                                    callback: function (records, operation, success) {

                                        var dates = [];
                                        for (var i = 0; i < records.length; i++) {
                                            dates[i] = records[i].get('visitDate') + records[i].get('visitTime');
                                        }

                                        Ext.suspendLayouts();

                                        for (var j = 0; j < records.length; j++) {

                                            var i = dates.indexOf(Ext.Array.min(dates));
                                            dates[i] = Ext.Array.max(dates) + 1;

                                            var newentrys = Ext.create('widget.companytechniciananndiary', {});
                                            newentrys.items.get(3).suspendEvents();
                                            newentrys.items.get(4).suspendEvents();

                                            newentrys.items.get(0).minValue = (form.getForm().findField('dateStart').value > new Date()) ? form.getForm().findField('dateStart').value : new Date();
                                            newentrys.items.get(0).maxValue = form.getForm().findField('dateEnd').value;

                                            if (records[i].get('visitDate') == "1970-02-01T00:00:00.000+0000")
                                                newentrys.items.get(0).setValue(grid.timestampToDate(""));
                                            else
                                                newentrys.items.get(0).setValue(grid.timestampToDate(records[i].get('visitDate')));

                                            newentrys.items.get(1).setValue(records[i].get('visitTime'));
                                            newentrys.items.get(2).setValue(records[i].get('visitDurationMinutes'));
                                            var hs = Math.floor(records[i].get('visitDurationMinutes') / 60);
                                            newentrys.items.get(3).setValue(hs);
                                            var ms = records[i].get('visitDurationMinutes') - (Math.floor(records[i].get('visitDurationMinutes') / 60) * 60);
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
                                            else newentrys.items.get(6).setStyle({margin: '26px 0 0 2px'});

                                            newentrys.items.get(3).resumeEvents(false);
                                            newentrys.items.get(4).resumeEvents(false);

                                            if (record.get('subStatus') === 2)
                                                newentrys.items.get(6).hidden = true;

                                            Ext.getCmp('tadiaryEntries').add(newentrys);
                                        }

                                        Ext.resumeLayouts(true);

                                        form.getForm().findField('diaryEntriesnum').setValue(records.length);
                                        if (record.get('subStatus') === 2) {

                                            fields = form.getForm().getFields();
                                            fields.each(function (field) {
                                                field.enable();
                                                field.setReadOnly(true);
                                            });

                                        }

                                        var ptlBranch = Ext.getCmp('companyTechnicianAnn_Branches').getSelectionModel().getSelection()[0];
                                        emp_comp.down('form').getForm().findField('compPtlBranchId').setValue(ptlBranch.get('ptlBranchId'));
                                        emp_comp.down('form').getForm().findField('compEbrBranchId').setValue(ptlBranch.get('ebrBranchId'));
                                        emp_comp.down('form').getForm().findField('brAddr').setValue(ptlBranch.get('brAddr'));
                                        emp_comp.down('form').getForm().findField('brAddrTk').setValue(ptlBranch.get('brAddrTk'));
                                        emp_comp.down('form').getForm().findField('brAddrP').setValue(ptlBranch.get('brAddrP'));
                                        emp_comp.down('form').getForm().findField('brAddrPe').setValue(ptlBranch.get('brAddrPe'));
                                        emp_comp.down('form').getForm().findField('brAddrD').setValue(ptlBranch.get('brAddrD'));
                                        emp_comp.down('form').getForm().findField('brAddrK').setValue(ptlBranch.get('brAddrK'));
                                        //form.getForm().findField('exyppBasic').resumeEvents(false);
                                        //form.getForm().findField('cooperationTypeBasic').resumeEvents(false);

                                        emp_comp.unmask();

                                    }

                                });
                            }
                        });
                    },
                    //flex: 0.8,
                    //flex: 1,
                    autoScroll: true,
                    //height: '40%',
                    id: 'companyTechnicianAnn_Technicians',
                    itemId: 'companyTechnicianAnn_Technicians',
                    title: 'Αναγγελίες Τεχνικών Ασφάλειας',
                    columnLines: false,
                    reserveScrollbar: true,
                    scroll: 'vertical',
                    store: 'company.TechnicianAnn.TECHNICIAN_ANNS_GRID',
                    columns: [
                        {
                            xtype: 'rownumberer'
                        },
                        {
                            xtype: 'actioncolumn',
                            width: 30,
                            //enableColumnHide: false,
                            //sealed: true,
                            dataIndex: 'taAnnStatus',
                            //hideable: false,
                            //menuDisabled: true,
                            renderer: function (v, metaData, r, rowIndex, colIndex, store, view) {
                                if (v === -2) {
                                    this.items[0].tooltip = 'Μη καταχωρημένη';
                                }
                                else if (v === 2 && r.get('reqStatus') === 7) {
                                    this.items[0].tooltip = 'Αντικαταστάθηκε';
                                }
                                else if (v === 2 && r.get('reqStatus') === 6) {
                                    this.items[0].tooltip = 'Παραιτήθηκε';
                                }
                                else if (v === 3 && r.get('reqStatus') === 6) {
                                    this.items[0].tooltip = 'Παύση Εταιρείας';
                                }
                                else if (Date.parse(new Date()) > Date.parse(r.get('dateEnd'))) {
                                    this.items[0].tooltip = 'Έληξε';
                                }
                                else if (v === 0) {
                                    this.items[0].tooltip = 'Αναμονή Τεχνικού';
                                }
                                else if (v === 1 && r.get('reqStatus') === 6) {
                                    this.items[0].tooltip = 'Αποδοχή Τεχνικού';
                                }
                                else if (v === -1 && r.get('reqStatus') === 7) {
                                    this.items[0].tooltip = 'Απόρριψη Τεχνικού';
                                }

                            },
                            items: [
                                {
                                    getClass: function (v, metadata, r, rowIndex, colIndex, store) {
                                        if (v === -2) {
                                            //this.items[0].tooltip = 'Μη καταχωρημένη';
                                            return ''; // css for icon
                                        }
                                        else if (v === 2 && r.get('reqStatus') === 7) {
                                            //this.items[0].tooltip = 'Αντικαταστάθηκε';
                                            return 'inactivependingU'; // css for icon
                                        }
                                        else if (v === 2 && r.get('reqStatus') === 6) {
                                            //this.items[0].tooltip = 'Παραιτήθηκε';
                                            return 'activependingU'; // css for icon
                                        }
                                        else if (v === 3 && r.get('reqStatus') === 6) {
                                            //this.items[0].tooltip = 'Παύση';
                                            return 'activepausedU'; // css for icon
                                        }
                                        else if (Date.parse(new Date()) > Date.parse(r.get('dateEnd'))) {
                                            //this.items[0].tooltip = 'Έληξε';
                                            return 'previousactiveU'; // css for icon
                                        }
                                        else if (v === 0) {
                                            //this.items[0].tooltip = 'Αναμονή Τεχνικού';
                                            return 'pendingU'; // css for icon
                                        }
                                        else if (v === 1 && r.get('reqStatus') === 6) {
                                            //this.items[0].tooltip = 'Αποδοχή Τεχνικού';
                                            return 'activeU'; // css for icon
                                        }
                                        else if (v === -1 && r.get('reqStatus') === 7) {
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
                            renderer: function (value, metaData, record, rowIndex, colIndex, store, view) {
                                if (value !== null) {
                                    var pD = value.replace(/[^0-9]+/g, ' ').split(" ");
                                    return (pD[2] + "-" + pD[1] + "-" + pD[0]);
                                }
                                else
                                    return "";

                            },
                            sortable: false,
                            dataIndex: 'dateStart',
                            text: 'Έναρξη',
                            //flex: 10,
                        },
                        {
                            xtype: 'gridcolumn',
                            renderer: function (value, metaData, record, rowIndex, colIndex, store, view) {
                                if (value !== null) {
                                    var pD = value.replace(/[^0-9]+/g, ' ').split(" ");
                                    return (pD[2] + "-" + pD[1] + "-" + pD[0]);
                                }
                                else
                                    return "";

                            },
                            sortable: false,
                            dataIndex: 'dateEnd',
                            text: 'Λήξη',
                            //flex: 10,
                        },
                        {
                            xtype: 'gridcolumn',
                            renderer: function (value, metaData, record, rowIndex, colIndex, store, view) {
                                if (value !== null) {
                                    var pD = value.replace(/[^0-9]+/g, ' ').split(" ");
                                    return (pD[2] + "-" + pD[1] + "-" + pD[0]) + "  " + record.get("submitTime");
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
                            renderer: function (value, metaData, record, rowIndex, colIndex, store, view) {
                                if (value !== null) {
                                    if (record.get('subStatus') === 2) {
                                        var pD = value.replace(/[^0-9]+/g, ' ').split(" ");
                                        return (pD[2] + "-" + pD[1] + "-" + pD[0]) + "/" + record.get("submitTime");
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
                            renderer: function (value, metaData, record, rowIndex, colIndex, store, view) {
                                if (value !== null) {
                                    if (record.get('subStatus') === 1) {
                                        return "Δεν δόθηκε";
                                    }
                                    else
                                        return value + "/" + record.get("protYear");
                                } else
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
                            renderer: function (value, metaData, record, rowIndex, colIndex, store, view) {
                                if (value !== null) {
                                    if (record.get('subStatus') === 1) {
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
                            renderer: function (v, metaData, r, rowIndex, colIndex, store, view) {
                                if (v === -2) {
                                    return 'Μη καταχωρημένη';
                                }
                                else if (v === 2 && r.get('reqStatus') === 7) {
                                    return 'Αντικαταστάθηκε';
                                }
                                else if (v === 2 && r.get('reqStatus') === 6) {
                                    return 'Παραιτήθηκε';
                                }
                                else if (v === 3 && r.get('reqStatus') === 6) {
                                    return 'Παύση Εταιρείας';
                                }
                                else if (Date.parse(new Date()) > Date.parse(r.get('dateEnd'))) {
                                    return 'Έληξε';
                                }
                                else if (v === 0) {
                                    return 'Αναμονή Τεχνικού';
                                }
                                else if (v === 1 && r.get('reqStatus') === 6) {
                                    return 'Αποδοχή Τεχνικού';
                                }
                                else if (v === -1 && r.get('reqStatus') === 7) {
                                    return 'Απόρριψη Τεχνικού';
                                } else {
                                    return '';
                                }

                            },
                            sortable: false,
                            dataIndex: 'taAnnStatus',
                            text: 'Κατάσταση',
                            flex: 17,
                        },
                        {
                            xtype: 'gridcolumn',
                            hidden: true,
                            renderer: function (value, metaData, record, rowIndex, colIndex, store, view) {
                                if (value !== null) {
                                    if (record.get('subStatus') === 1) {
                                        return "";
                                    }
                                    else {
                                        /*if(Date.parse(new Date())>Date.parse(record.get('dateEnd'))){
                                         return 'Έληξε'; // Check Date
                                         } else {*/
                                        var store2 = Ext.StoreMgr.lookup('shared.COMPL_STATUS');
                                        if (record.get('reqStatus') > 0) {

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
                            dataIndex: 'taAnnStatus',
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
                                    getClass: function (v, metadata, r, rowIndex, colIndex, store) {
                                        if (r.get('subStatus') === 1) {
                                            this.items[0].tooltip = 'Επεξεργασία';
                                            return 'editme'; // css for icon
                                        }
                                        else {
                                            this.items[0].tooltip = 'Προβολή';
                                            return 'viewme'; // css for icon

                                        }
                                    },
                                    handler: function (view, rowIndex, colIndex, item, e, record, row) {
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
                            store: 'company.TechnicianAnn.TECHNICIAN_ANNS_GRID'
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
                                },
                                {
                                    xtype: 'button',
                                    hidden: true,
                                    glyph: 'xf067@FontAwesome',
                                    text: 'Εισαγωγή Αναγγελίας από αρχείο xml',
                                    listeners: {
                                        click: 'onNewTechnicianXML'
                                    }
                                },
                            ]
                        },
                        {
                            xtype: 'toolbar',
                            dock: 'bottom',
                            items: [
                                {
                                    xtype: 'container',
                                    html: 'Κατάσταση Αναγγελίας: <img height="16px" src="static/userapp/resources/pending.png"/> Αναμονή &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <img height="16px" src="static/userapp/resources/active.png"/> Αποδοχή &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<img height="16px" src="static/userapp/resources/inactive.png"/> Απόρριψη &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<img height="16px" src="static/userapp/resources/inactivepending.png"/> Αντικαταστάθηκε&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<img height="16px" src="static/userapp/resources/activepending.png"/> Παραιτήθηκε&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<img height="16px" src="static/userapp/resources/previousactive.png"/> Έληξε  &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<img height="16px" src="static/userapp/resources/activepaused.png"/> Παύση Εταιρείας',
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
                },
                {
                    xtype: 'gridpanel',
                    loadCategNums: function (wid) {
                        var successAns = function (options, success, response) {
                            if (Ext.JSON.decode(response.responseText).success) {

                                var resp = Ext.JSON.decode(response.responseText);

                                //wid.down('form').getForm().findField('categANumMax').setValue(resp.categANum);
                                wid.down('form').getForm().findField('compCategBNum').setValue(resp.categBNum);
                                wid.down('form').getForm().findField('compCategCNum').setValue(resp.categCNum);
                            }
                            else {
                                Ext.Msg.alert('Σφάλμα!', 'Υπάρχει πρόβλημα στην επισύναψη στοιχείων');

                            }
                        };

                        Ext.Ajax.request({
                            url: '/companyExtraInfo',
                            method: "GET",
                            callback: successAns
                        });
                    },
                    timestampToDate: function (timestamp) {

                        if (timestamp != null) {
                            var pD = timestamp.replace(/[^0-9]+/g, ' ').split(" ");
                            return (pD[2] + "-" + pD[1] + "-" + pD[0]);
                        }
                        else return null;
                    },
                    timestampToTime: function (timestamp) {

                        if (timestamp != null) {
                            var pD = timestamp.replace(/[^0-9]+/g, ' ').split(" ");
                            return (pD[3] + ":" + pD[4]);
                        }
                        else return null;
                    },
                    icon_dbl_click_handler: function (record) {
                        var emp_comp = Ext.create('widget.companytechniciananntechnicianformse', {});
                        var form = emp_comp.down('form');
                        var fields = form.getForm().getFields();
                        if (record.get('protNo') == null)
                            this.loadCategNums(emp_comp);
                        else
                        {
                            form.getForm().findField('compCategBNum').setValue(record.get('compCategBNum'));
                            form.getForm().findField('compCategCNum').setValue(record.get('compCategCNum'));
                            form.getForm().findField('totalHours').setValue(record.get('totalHours'));
                            var tms = parseInt(form.getForm().findField('totalHours').getValue());
                            var hours1 = Math.floor(tms / 60);
                            var minutes1 = tms - (hours1 * 60);
                            var textR = hours1.toString() + " ώρες και " + minutes1.toString() + " λεπτά.";
                            form.getForm().findField('totalHoursText').setValue(textR);
                        }
                        emp_comp.down('toolbar').getComponent('deletebutton').show();


                        if (record.get('subStatus') === 1) {
                            form.getForm().findField('protNoview').setValue("-");

                            if (record.get('compAddrPe') !== null && record.get('compAddrD') === null) {
                                form.getForm().findField('compAddrPe').setDisabled(false);
                                form.getForm().findField('compAddrD').setDisabled(false);
                            }
                            else if (record.get('compAddrPe') !== null && record.get('compAddrD') !== null) {
                                form.getForm().findField('compAddrPe').setDisabled(false);
                                form.getForm().findField('compAddrD').setDisabled(false);
                                form.getForm().findField('compAddrK').setDisabled(false);
                            }
                            else if (record.get('compAddrP') !== null && record.get('compAddrPe') === null) {
                                form.getForm().findField('compAddrPe').setDisabled(false);
                            }

                        }
                        else {

                            form.getForm().findField('protNoview').setValue(record.get('protNo'));
                            emp_comp.down('toolbar').getComponent('submitbutton').hide();
                            emp_comp.down('toolbar').getComponent('savebutton').hide();

                            emp_comp.down('toolbar').hide();

                            form.getForm().findField('file').hide();

                        }

                        if (record.get('dateEnd') != null)
                            form.getForm().findField('dateEnd').setDisabled(false);

                        if (record.get('dateStart') == null && record.get('dateEnd') == null)
                            form.getForm().findField('intervalCheck').setValue(true);

                        form.getForm().findField('a_new_form').setValue(false);

                        emp_comp.show();
                        emp_comp.mask("Παρακαλώ Περιμένετε...");

                        form.getForm().findField('dateStart').setValue(this.timestampToDate(record.get('dateStart')));
                        form.getForm().findField('dateEnd').setValue(this.timestampToDate(record.get('dateEnd')));


                        form.loadRecord(record);


                        if (record.get('branchSector') != null) {
                            form.getForm().findField('branchSector').setValue(record.get('branchSector').toString());
                        }

                        form.getForm().findField('branchSectorId').setValue(record.get('branchSector'));

                        form.getForm().findField('dateStart').setValue(this.timestampToDate(record.get('dateStart')));
                        form.getForm().findField('dateEnd').setValue(this.timestampToDate(record.get('dateEnd')));


                        if (record.get('subStatus') === 1) {

                            if (record.get('protDate') !== null) {
                                form.getForm().findField('protDateview').setValue("");
                                form.getForm().findField('protYear').setValue("");
                                form.getForm().findField('submitTime').setValue("");
                            }

                            form.getForm().findField('protDateview').setValue("");
                            form.getForm().findField('protDate').setValue("");

                            form.getForm().findField('reqStatus').setValue("");

                            if (record.get('attachedDocId') !== -1) {
                                var vfc = Ext.ComponentQuery.query('#Exist_File')[0];
                                vfc.show();
                            }

                        }
                        else if (record.get('subStatus') === 2) {

                            var pD = record.get('protDate').replace(/[^0-9]+/g, ' ').split(" ");
                            form.getForm().findField('protDateview').setValue(pD[2] + "-" + pD[1] + "-" + pD[0]);
                            //form.getForm().findField('reqStatus').setValue(record.get('reqStatus').toString());
                            if (record.get('reqStatus') > 0)
                                form.getForm().findField('reqStatus').setValue(record.get('reqStatus').toString());
                            else
                                form.getForm().findField('reqStatus').setValue("");

                            if (record.get('sepeDept') > 0 || record.get('sepeDept') !== null) {
                                var store2 = Ext.StoreMgr.lookup('shared.SEPE_DEPT');
                                store2.getProxy().url = '/TSepeDepartment/search/findById'
                                store2.getProxy().setExtraParam("cdId", record.get('sepeDept'));
                                store2.load({
                                    callback: function (records, operation, success) {
                                        var sepeDept = Ext.decode(operation._response.responseText);
                                        if (sepeDept !== null)
                                            form.getForm().findField('sepeDept').setValue(sepeDept.cdCode + ' - ' + sepeDept.cdText);
                                    }
                                });
                            }

                            //get message
                            if (record.get('caseId') !== null && record.get('docId') !== null) {

                                form.getForm().findField('StatusMsg').show();
                                var store3 = Ext.StoreMgr.lookup('shared.CASE_MESSAGE');
                                store3.getProxy().setExtraParams({
                                    "caseId": record.get('caseId'),
                                    "docId": record.get('docId')
                                });
                                store3.load({
                                    callback: function (records, operation, success) {
                                        if (records !== null)
                                            record.getField("StatusMsg").value = records[0].getData().cdText;
                                    }
                                });
                            }


                            if (record.get('attachedDocId') !== -1) {
                                var vfc = Ext.ComponentQuery.query('#Exist_File')[0];
                                vfc.items.getAt(1).hide();
                                vfc.show();
                            }


                            Ext.getCmp('taentryadd').hide();
                            Ext.getCmp('taentrydel').hide();
                            Ext.getCmp('taRecurrentEntryadd').hide();
                            //Ext.getCmp('newTaEntry').destroy();
                            //Ext.getCmp('delTaEntry').destroy();

                        }

                        var grid = this;
                        var store1 = Ext.StoreMgr.lookup('company.TechnicianAnn.DiarySe');
                        store1.getProxy().setUrl(record.get('technicianSeAnnDiaryEntries'));
                        store1.load({
                            callback: function (records, operation, success) {

                                var dates = [];
                                for (var i = 0; i < records.length; i++) {
                                    dates[i] = records[i].get('visitDate') + records[i].get('visitTime');
                                }

                                Ext.suspendLayouts();

                                for (var j = 0; j < records.length; j++) {

                                    var i = dates.indexOf(Ext.Array.min(dates));
                                    dates[i] = Ext.Array.max(dates) + 1;

                                    var newentrys = Ext.create('widget.companytechniciananndiary', {});
                                    newentrys.items.get(3).suspendEvents();
                                    newentrys.items.get(4).suspendEvents();

                                    newentrys.items.get(0).minValue = (form.getForm().findField('dateStart').value > new Date()) ? form.getForm().findField('dateStart').value : new Date();
                                    newentrys.items.get(0).maxValue = form.getForm().findField('dateEnd').value;

                                    if (records[i].get('visitDate') == "1970-02-01T00:00:00.000+0000")
                                        newentrys.items.get(0).setValue(grid.timestampToDate(""));
                                    else
                                        newentrys.items.get(0).setValue(grid.timestampToDate(records[i].get('visitDate')));

                                    newentrys.items.get(1).setValue(records[i].get('visitTime'));
                                    newentrys.items.get(2).setValue(records[i].get('visitDurationMinutes'));
                                    var hs = Math.floor(records[i].get('visitDurationMinutes') / 60);
                                    newentrys.items.get(3).setValue(hs);
                                    var ms = records[i].get('visitDurationMinutes') - (Math.floor(records[i].get('visitDurationMinutes') / 60) * 60);
                                    newentrys.items.get(4).setValue(ms);
                                    newentrys.items.get(5).disable();
                                    newentrys.items.get(5).hide();
                                    if (j >= 1) {
                                        newentrys.items.get(0).hideLabel = true;
                                        newentrys.items.get(1).hideLabel = true;
                                        newentrys.items.get(2).hideLabel = true;
                                        newentrys.items.get(3).hideLabel = true;
                                        newentrys.items.get(4).hideLabel = true;
                                    }
                                    else newentrys.items.get(6).setStyle({margin: '26px 0 0 2px'});

                                    newentrys.items.get(3).resumeEvents(false);
                                    newentrys.items.get(4).resumeEvents(false);

                                    if (record.get('subStatus') === 2)
                                        newentrys.items.get(6).hidden = true;

                                    Ext.getCmp('tadiaryEntries').add(newentrys);
                                }

                                Ext.resumeLayouts(true);

                                form.getForm().findField('diaryEntriesnum').setValue(records.length);
                                if (record.get('subStatus') === 2) {

                                    fields = form.getForm().getFields();
                                    fields.each(function (field) {
                                        field.enable();
                                        field.setReadOnly(true);
                                    });

                                }

                                var ptlBranch = Ext.getCmp('companyTechnicianAnn_Branches').getSelectionModel().getSelection()[0];
                                emp_comp.down('form').getForm().findField('compPtlBranchId').setValue(ptlBranch.get('ptlBranchId'));
                                emp_comp.down('form').getForm().findField('compEbrBranchId').setValue(ptlBranch.get('ebrBranchId'));
                                emp_comp.down('form').getForm().findField('brAddr').setValue(ptlBranch.get('brAddr'));
                                emp_comp.down('form').getForm().findField('brAddrTk').setValue(ptlBranch.get('brAddrTk'));
                                emp_comp.down('form').getForm().findField('brAddrP').setValue(ptlBranch.get('brAddrP'));
                                emp_comp.down('form').getForm().findField('brAddrPe').setValue(ptlBranch.get('brAddrPe'));
                                emp_comp.down('form').getForm().findField('brAddrD').setValue(ptlBranch.get('brAddrD'));
                                emp_comp.down('form').getForm().findField('brAddrK').setValue(ptlBranch.get('brAddrK'));

                                if (record.get('taAddrPe') !== null && record.get('taAddrD') === null) {
                                    form.getForm().findField('taAddrPe').setDisabled(false);
                                    form.getForm().findField('taAddrD').setDisabled(false);
                                }
                                else if (record.get('taAddrPe') !== null && record.get('taAddrD') !== null) {
                                    form.getForm().findField('taAddrPe').setDisabled(false);
                                    form.getForm().findField('taAddrD').setDisabled(false);
                                    form.getForm().findField('taAddrK').setDisabled(false);
                                }
                                else if (record.get('taAddrP') !== null && record.get('taAddrPe') === null) {
                                    form.getForm().findField('taAddrPe').setDisabled(false);
                                }

                                emp_comp.unmask();

                            }

                        });

                        //emp_comp.unmask();
                    },
                    //flex: 0.8,
                    autoScroll: true,
                    //height: '20%',
                    //hidden: true,
                    id: 'companyTechnicianSeAnn_Technicians',
                    itemId: 'companyTechnicianSeAnn_Technicians',
                    title: 'Αναγγελίες Τεχνικών Ασφάλειας Δευτεροβάθμιας Εκπαίδευσης',
                    columnLines: false,
                    reserveScrollbar: true,
                    autoLoad: false,
                    scroll: 'vertical',
                    store: 'company.TechnicianAnn.TECHNICIAN_SE_ANNS_GRID',
                    columns: [
                        {
                            xtype: 'rownumberer'
                        },
                        /*{
                         xtype: 'actioncolumn',
                         width: 30,
                         //enableColumnHide: false,
                         //sealed: true,
                         dataIndex: 'taAnnStatus',
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
                         },*/
                        {
                            xtype: 'gridcolumn',
                            renderer: function (value, metaData, record, rowIndex, colIndex, store, view) {
                                if (value !== null) {
                                    var pD = value.replace(/[^0-9]+/g, ' ').split(" ");
                                    return (pD[2] + "-" + pD[1] + "-" + pD[0]);
                                }
                                else
                                    return "";

                            },
                            sortable: false,
                            dataIndex: 'dateStart',
                            text: 'Έναρξη',
                            flex: 10,
                        },
                        {
                            xtype: 'gridcolumn',
                            renderer: function (value, metaData, record, rowIndex, colIndex, store, view) {
                                if (value !== null) {
                                    var pD = value.replace(/[^0-9]+/g, ' ').split(" ");
                                    return (pD[2] + "-" + pD[1] + "-" + pD[0]);
                                }
                                else
                                    return "";

                            },
                            sortable: false,
                            dataIndex: 'dateEnd',
                            text: 'Λήξη',
                            flex: 10,
                        },
                        {
                            xtype: 'gridcolumn',
                            sortable: false,
                            dataIndex: 'taAfm',
                            text: 'ΑΦΜ Τεχνικού',
                            flex: 10,
                            filterField: true,
                            filter: {
                                type: 'string',
                                emptyText: 'Εισαγωγή κειμένου...'
                            }
                        },
                        {
                            xtype: 'gridcolumn',
                            renderer: function (value, metaData, record, rowIndex, colIndex, store, view) {
                                if (value !== null) {
                                    var pD = value.replace(/[^0-9]+/g, ' ').split(" ");
                                    return (pD[2] + "-" + pD[1] + "-" + pD[0]) + "  " + record.get("submitTime");
                                }
                                else
                                    return "";

                            },
                            sortable: false,
                            dataIndex: 'protDate',
                            text: 'Τελευταία Επεξεργασία',
                            flex: 18,
                            /*filter: {
                             type: 'string',
                             emptyText: 'Εισαγωγή κειμένου...'
                             }*/
                        },
                        {
                            xtype: 'gridcolumn',
                            renderer: function (value, metaData, record, rowIndex, colIndex, store, view) {
                                if (value !== null) {
                                    if (record.get('subStatus') === 2) {
                                        var pD = value.replace(/[^0-9]+/g, ' ').split(" ");
                                        return (pD[2] + "-" + pD[1] + "-" + pD[0]) + "/" + record.get("submitTime");
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
                            renderer: function (value, metaData, record, rowIndex, colIndex, store, view) {
                                if (value !== null) {
                                    if (record.get('subStatus') === 1) {
                                        return "Δεν δόθηκε";
                                    }
                                    else
                                        return value + "/" + record.get("protYear");
                                } else
                                    return "";
                            },
                            sortable: false,
                            dataIndex: 'protNo',
                            text: 'Πρωτόκολλο',
                            flex: 12
                        },
                        {
                            xtype: 'gridcolumn',
                            renderer: function (value, metaData, record, rowIndex, colIndex, store, view) {
                                if (value !== null) {
                                    if (record.get('subStatus') === 1) {
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
                            xtype: 'actioncolumn',
                            width: 30,
                            //enableColumnHide: false,
                            //hideable: false,
                            //menuDisabled: true,
                            items: [
                                {
                                    getClass: function (v, metadata, r, rowIndex, colIndex, store) {
                                        if (r.get('subStatus') === 1) {
                                            this.items[0].tooltip = 'Επεξεργασία';
                                            return 'editme'; // css for icon
                                        }
                                        else {
                                            this.items[0].tooltip = 'Προβολή';
                                            return 'viewme'; // css for icon

                                        }
                                    },
                                    handler: function (view, rowIndex, colIndex, item, e, record, row) {
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
                            beforerefresh: 'onTechnicianSeViewBeforeRefresh'
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
                            store: 'company.TechnicianAnn.TECHNICIAN_SE_ANNS_GRID'
                        },
                        {
                            xtype: 'toolbar',
                            dock: 'top',
                            items: [
                                {
                                    xtype: 'button',
                                    glyph: 'xf067@FontAwesome',
                                    text: 'Νέα Αναγγελία Τεχνικού Ασφάλειας Δευτεροβάθμιας Εκπαίδευσης',
                                    listeners: {
                                        click: 'onNewTechnicianSE'
                                    }
                                }
                            ]
                        },
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
                }
            ]
        },
    ],

    onBranchItemDblClick: function (dataview, record, item, index, e, eOpts) {
        dataview.up().icon_dbl_click_handler(record);

    },

    onBranchViewRefresh: function (dataview, eOpts) {
        if (dataview.store.data.length < 1 && dataview.store.filters.length > 0) {
            Ext.addon.MessagePop.msg("Προσοχή!", "Δεν υπάρχουν εγγραφές που να αντιστοιχούν στην αναζήτηση σας. Προβάλονται όλες οι εγγραφές της σελίδας.", 5000);
            dataview.up('grid').plugins[1].clearValues();
            dataview.store.reload();
        }
        Ext.getCmp('companyTechnicianAnn_Technicians').store.clearData();
        Ext.getCmp('companyTechnicianAnn_Technicians').view.refresh();
    },

    onBranchViewItemClick: function (dataview, record, item, index, e, eOpts) {
        var technicians = Ext.getCmp('companyTechnicianAnn_Technicians');
        var techniciansSE = Ext.getCmp('companyTechnicianSeAnn_Technicians');
        var ptlBranchId = Ext.getCmp('companyTechnicianAnn_Branches').getSelectionModel().getSelection()[0].get('ptlBranchId');
        technicians.store.proxy.setUrl('/compTaAnn/search/findByPtlBranchId?branchId=' + ptlBranchId);
        technicians.getStore().reload({
            callback: function () {
                technicians.getView().refresh();
            }
        });
        techniciansSE.store.proxy.setUrl('/compTaAnnSe/search/findByPtlBranchId?branchId=' + ptlBranchId);
        techniciansSE.getStore().reload({
            callback: function () {
                techniciansSE.getView().refresh();
            }
        });
    },

    onBranchViewContainerClick: function (dataview, e, eOpts) {
        var dailygrid = Ext.getCmp('companyTechnicianAnn_Technicians');
        dailygrid.store.clearData();
        dailygrid.getView().refresh();

        var dailygrid2 = Ext.getCmp('companyTechnicianSeAnn_Technicians');
        dailygrid2.store.clearData();
        dailygrid2.getView().refresh();
    },

    onNewBranch: function (button, e, eOpts) {
        var emp_comp = Ext.create('widget.companydoctorannbranchform', {});
        emp_comp.down('toolbar').getComponent('updatebutton').hide();
        emp_comp.down('form').items.getAt(7).show();

        var successCallback = function (resp, ops) {
            if (Ext.JSON.decode(resp.responseText).success) {
                emp_comp.down('form').getForm().findField('isNewPlaceAllowed').setValue(true);
                emp_comp.down('form').getForm().findField('brAddr').setReadOnly(false);
                emp_comp.down('form').getForm().findField('brAddrP').setReadOnly(false);
                emp_comp.down('form').getForm().findField('brAddrTk').setReadOnly(false);
                emp_comp.down('form').getForm().findField('brInfoText').setHidden(false);

            }
            else {
                emp_comp.down('form').getForm().findField('isNewPlaceAllowed').setValue(false);
                emp_comp.down('form').getForm().findField('brAddr').setReadOnly(true);
                emp_comp.down('form').getForm().findField('brAddrP').setReadOnly(true);
                emp_comp.down('form').getForm().findField('brAddrTk').setReadOnly(true);
                emp_comp.down('form').getForm().findField('brInfoText').setHidden(true);
            }
            emp_comp.show();
        };

        // Failure
        var failureCallback = function (resp, ops) {
            emp_comp.down('form').getForm().findField('isNewPlaceAllowed').setValue(false);
            emp_comp.down('form').getForm().findField('brAddr').setReadOnly(true);
            emp_comp.down('form').getForm().findField('brAddrP').setReadOnly(true);
            emp_comp.down('form').getForm().findField('brAddrTk').setReadOnly(true);
            emp_comp.down('form').getForm().findField('brInfoText').setHidden(true);
            emp_comp.show();
        };


        Ext.Ajax.request({
            url: "/checkCompPtlBranchPrivlages",
            method: "POST",
            success: successCallback,
            failure: failureCallback
        });
    },

    onTechnicianViewRefresh: function (dataview, eOpts) {
        if (dataview.store.data.length < 1 && dataview.store.filters.length > 0) {
            Ext.addon.MessagePop.msg("Προσοχή!", "Δεν υπάρχουν εγγραφές που να αντιστοιχούν στην αναζήτηση σας. Προβάλονται όλες οι εγγραφές της σελίδας.", 5000);
            dataview.up('grid').plugins[1].clearValues();
            dataview.store.reload();
        }
    },

    onTechnicianViewItemDblClick: function (dataview, record, item, index, e, eOpts) {
        dataview.up().icon_dbl_click_handler(record);

    },

    onTechnicianViewBeforeRefresh: function (dataview, eOpts) {
        if (Ext.getCmp('companyTechnicianAnn_Branches').getSelectionModel().getSelection().length > 0) {
            var ptlBranchId = Ext.getCmp('companyTechnicianAnn_Branches').getSelectionModel().getSelection()[0].get('ptlBranchId');
            dataview.store.proxy.setUrl('/compTaAnn/search/findByPtlBranchId?branchId=' + ptlBranchId);
        }
    },

    onTechnicianSeViewBeforeRefresh: function (dataview, eOpts) {
        if (Ext.getCmp('companyTechnicianAnn_Branches').getSelectionModel().getSelection().length > 0) {
            var ptlBranchId = Ext.getCmp('companyTechnicianAnn_Branches').getSelectionModel().getSelection()[0].get('ptlBranchId');
            dataview.store.proxy.setUrl('/compTaAnnSe/search/findByPtlBranchId?branchId=' + ptlBranchId);
        }
    },

    onNewTechnician: function (button, e, eOpts) {
        if (Ext.getCmp('companyTechnicianAnn_Branches').getSelectionModel().getSelection().length > 0) {
            /*                     var successCall = function(options, success, response) {
             if(response.responseText!=="0" ){
             Ext.Msg.alert('Προσοχή!', 'Υπάρχει αναγγελία σε αναμονή ή <br>προηγούμενη ενεργή ή που έχει τερματιστεί.'+
             ' <br>Επιλέξτε την από την λίστα και επιλέξτε διαγραφή στην περίπτωση αναμονής <br>αλλιώς επιλέξτε αντικατάσταση.');
             }
             else{*/
            var emp_comp = Ext.create('widget.companytechniciananndatesform', {});
            emp_comp.show();
            /*                            }

             };*/
            /*                    Ext.Ajax.request({
             url: "/compTaAnn/search/findCategTaAnn",
             params: {
             ptlBranchId: Ext.getCmp('companyTechnicianAnn_Branches').getSelectionModel().getSelection()[0].get('ptlBranchId')
             },
             method: "GET",
             callback: successCall
             });*/

        }
        else
            Ext.Msg.alert('Προσοχή!', 'Δεν έχετε επιλέξει κάποιο υποκατάστημα από την παραπάνω λίστα.');


    },
    onNewTechnicianXML: function (button, e, eOpts) {
        if (Ext.getCmp('companyTechnicianAnn_Branches').getSelectionModel().getSelection().length > 0) {
            var emp_comp = Ext.create('widget.companytechnicianannxmlform', {});
            emp_comp.show();
        }
        else
            Ext.Msg.alert('Προσοχή!', 'Δεν έχετε επιλέξει κάποιο υποκατάστημα από την παραπάνω λίστα.');


    },
    onNewTechnicianSE: function (button, e, eOpts) {
        if (Ext.getCmp('companyTechnicianAnn_Branches').getSelectionModel().getSelection().length > 0) {
            var emp_comp = Ext.create('widget.companytechniciananntechnicianformse', {});

            var ptlBranch = Ext.getCmp('companyTechnicianAnn_Branches').getSelectionModel().getSelection()[0];
            emp_comp.down('form').getForm().findField('compPtlBranchId').setValue(ptlBranch.get('ptlBranchId'));
            emp_comp.down('form').getForm().findField('compEbrBranchId').setValue(ptlBranch.get('ebrBranchId'));


            emp_comp.down('form').getForm().findField('brAddr').setValue(ptlBranch.get('brAddr'));
            emp_comp.down('form').getForm().findField('brAddrTk').setValue(ptlBranch.get('brAddrTk'));
            emp_comp.down('form').getForm().findField('brAddrP').setValue(ptlBranch.get('brAddrP'));
            emp_comp.down('form').getForm().findField('brAddrPe').setValue(ptlBranch.get('brAddrPe'));
            emp_comp.down('form').getForm().findField('brAddrD').setValue(ptlBranch.get('brAddrD'));
            emp_comp.down('form').getForm().findField('brAddrK').setValue(ptlBranch.get('brAddrK'));

            emp_comp.down('form').getForm().findField('diaryEntriesnum').setValue(1);

            var successAns2 = function (options, success, response) {
                if (Ext.JSON.decode(response.responseText).success) {

                    var resp = Ext.JSON.decode(response.responseText);

                    emp_comp.down('form').getForm().findField('compCategANum').setValue(resp.categANum);
                    emp_comp.down('form').getForm().findField('compCategBNum').setValue(resp.categBNum);
                    emp_comp.down('form').getForm().findField('compCategCNum').setValue(resp.categCNum);
                }
                else {
                    Ext.Msg.alert('Σφάλμα!', 'Υπάρχει πρόβλημα στην επισύναψη στοιχείων');

                }
            };

            Ext.Ajax.request({
                url: '/companyExtraInfo',
                method: "GET",
                callback: successAns2
            });

            var successAns3 = function (options, success, response) {
                if (Ext.JSON.decode(response.responseText).success) {

                    var resp = Ext.JSON.decode(response.responseText);
                    emp_comp.down('form').getForm().findField('compFullName').setValue(resp.compFullName);
                    emp_comp.down('form').getForm().findField('compTaxNumber').setValue(resp.compTaxNumber);
                    emp_comp.down('form').getForm().findField('compAme').setValue(resp.compAme);
                    emp_comp.down('form').getForm().findField('compAddr').setValue(resp.compAddr);
                    emp_comp.down('form').getForm().findField('compAddrK').setValue(resp.compAddrK);
                    emp_comp.down('form').getForm().findField('compAddrTk').setValue(resp.compAddrTk);
                    emp_comp.down('form').getForm().findField('compPhone').setValue(resp.compPhone);
                }
                else {
                    Ext.Msg.alert('Σφάλμα!', 'Υπάρχει πρόβλημα στην επισύναψη στοιχείων');

                }
            };

            Ext.Ajax.request({
                url: "/getCompanyInfo",
                method: 'GET',
                callback: successAns3
            });

            var entrie = Ext.create('widget.companytechniciananndiary', {});
            entrie.items.get(0).minValue = new Date();
            entrie.items.get(5).disable();
            entrie.items.get(5).hide();
            entrie.items.get(6).setStyle({margin: '26px 0 0 2px'});
            Ext.getCmp('tadiaryEntries').add(entrie);

            emp_comp.show();
        }
        else
            Ext.Msg.alert('Προσοχή!', 'Δεν έχετε επιλέξει κάποιο υποκατάστημα από την παραπάνω λίστα.');


    },
    onPrintClick: function (button, e, eOpts) {

        button.up().up().up().up().up().mask('Παρακαλώ περιμένετε...');
        var pnl = button.up('gridpanel');
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

        var printTask = new Ext.util.DelayedTask(function () {

            // print the iframe
            cw.print();

            // destroy the iframe
            Ext.fly(iFrameId).destroy();

        });

        var title = "<h4>Εγκαταστάσεις ή Τοποθεσίες Έργων Οργανισμού</h4>";
        var strTask = new Ext.util.DelayedTask(function () {
            var str = Ext.String.format('<!DOCTYPE html><html><meta charset="UTF-8"><head>{0}</head><body>{2}<br>{1}</body></html>', stylesheets, markup, title);

            button.up().up().up().up().up().unmask();
            cw.document.open();
            cw.document.write(str);
            cw.document.close();

            printTask.delay(1000);
        });

        var markUpTask = new Ext.util.DelayedTask(function () {

            var store = Ext.StoreMgr.lookup('company.PTL_COMPANY_BRANCHES');
            for (var i = 0; i < store.data.items.length; i++)
                markup += '<div>' + (i + 1) + '. ' + store.data.items[i].get('brDescr') + '</div><br>';

            strTask.delay(2000);
        });


        var styleSheetConcatTask = new Ext.util.DelayedTask(function () {

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


        var styleSheetCreateTask = new Ext.util.DelayedTask(function () {


            for (var i = 0; i < document.styleSheets.length; i++) {
                stylesheets += Ext.String.format('<link rel="stylesheet" href="{0}" />', document.styleSheets[i].href);
            }
            styleSheetConcatTask.delay(1000);
        });

        styleSheetCreateTask.delay(1000);

    }

});