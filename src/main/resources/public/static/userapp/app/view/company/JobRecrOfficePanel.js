/*
 * File: app/view/company/JobRecrOfficePanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.JobRecrOfficePanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.companyjobrecrofficepanel',

    requires: [
        'MyApp.view.company.JobRecrOfficePanelViewModel',
        'MyApp.view.company.JobRecrOfficePanelViewController',
        'Ext.grid.Panel',
        'Ext.grid.column.RowNumberer',
        'Ext.grid.filters.filter.String',
        'Ext.grid.View',
        'Ext.toolbar.Paging',
        'Ext.form.field.Text',
        'Ext.form.trigger.Trigger',
        'Ext.grid.filters.Filters',
        'Ext.grid.column.Action'
    ],

    controller: 'companyjobrecrofficepanel',
    viewModel: {
        type: 'companyjobrecrofficepanel'
    },
    id: 'jobrecrofficecompany',
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'gridpanel',
            icon_dbl_click_handler: function(record) {
                var emp_comp = Ext.create('widget.companyjobrecrofficeformjobrecrofficeform', {});
                emp_comp.down('toolbar').getComponent('deletebutton').show();

                var form = emp_comp.down('form');
                var fields = form.getForm().getFields();


                if(record.get('subStatus')===1){
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
                }
                else{

                    form.getForm().findField('protNoview').setValue(record.get('protNo'));
                    Ext.getCmp('compjobrecroffice_save_submit_toolbar').hide();

                    form.getForm().findField('file').hide();
                    fields.each(function (field) {
                    field.enable();
                    field.setReadOnly (true);});

                }

                form.getForm().findField('a_new_form').setValue(false);


                emp_comp.show();


                form.loadRecord(record);


                /*var store1 = Ext.StoreMgr.lookup('company.JobRecrOffice.Pers');
                store1.getProxy().setUrl(record.get('jobRecrOfficePers'));
                store1.load({
                    callback: function(records, operation, success) {
                        if (record.get('requestEmpty')===0)
                        Ext.getCmp('jobrecrofficepers').items.getAt(4).destroy();
                        for (var i = 0; i < records.length; i++) {
                            var newpers = Ext.create('widget.companyjobrecrofficeformpers', {});
                            newpers.items.getAt(0).items.getAt(0).setValue(i+1 + ". ");
                            newpers.items.getAt(1).items.getAt(0).items.getAt(0).setValue(records[i].get('empFirstname'));
                            newpers.items.getAt(1).items.getAt(0).items.getAt(1).setValue(records[i].get('empLastname'));
                            newpers.items.getAt(1).items.getAt(0).items.getAt(2).setValue(records[i].get('empCardNumber'));
                            newpers.items.getAt(1).items.getAt(0).items.getAt(3).setValue(parseInt(records[i].get('empSex')));
                            newpers.items.getAt(1).items.getAt(0).items.getAt(4).setValue(records[i].get('empAge'));
                            newpers.items.getAt(1).items.getAt(0).items.getAt(5).setValue(records[i].get('empPlacementNum'));
                            newpers.items.getAt(1).items.getAt(0).items.getAt(6).setValue(records[i].get('empSpeciality'));
                            newpers.items.getAt(1).items.getAt(0).items.getAt(7).setValue(records[i].get('empAfm'));
                            newpers.items.getAt(1).items.getAt(0).items.getAt(8).setValue(records[i].get('empAmka'));
                            newpers.items.getAt(1).items.getAt(0).items.getAt(9).setValue(records[i].get('empEduLevelId').toString());
                            newpers.items.getAt(1).items.getAt(0).items.getAt(10).setValue(parseInt(records[i].get('empCategoryId')));

                            newpers.items.getAt(1).items.getAt(0).items.getAt(11).items.getAt(0).setValue(records[i].get('compTitle'));
                            newpers.items.getAt(1).items.getAt(0).items.getAt(11).items.getAt(1).setValue(records[i].get('compAfm'));
                            newpers.items.getAt(1).items.getAt(0).items.getAt(11).items.getAt(2).setValue(records[i].get('compAddr'));
                            Ext.getCmp('jobrecrofficepers').add(newpers);
                            form.getForm().findField('persnum').setValue(parseInt(form.getForm().findField('persnum').getValue()) + 1);
                        }
                        form.getForm().findField('persnum').setValue(parseInt(form.getForm().findField('persnum').getValue()) - 1);


                        if(record.get('subStatus')===2){
                            Ext.getCmp('recrpersadd').destroy();
                            Ext.getCmp('recrpersdel').destroy();
                            fields = form.getForm().getFields();
                            fields.each(function (field) {
                                field.enable();
                            field.setReadOnly (true);});
                        }

                    }
                });*/
                
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

        }


            },
            flex: 1,
            autoScroll: true,
            id: 'companyjobrecrofficegrid',
            itemId: 'companyjobrecrofficegrid',
            title: 'Καταστάσεις Γραφείων Ευρέσεως Εργασίας',
            autoLoad: true,
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.JobRecrOffice.JOB_RECR_OFFICE_GRID',
            columns: [
                {
                    xtype: 'rownumberer'
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        if(record.get("requestHalfYear")==="1"){
                            var sem="A' εξ./"+record.get("requestYear");
                        }
                        else if (record.get("requestHalfYear") == null)
                        {
                            var sem=record.get("requestYear");
                        }
                        else{
                            var sem="B' εξ./"+record.get("requestYear");
                        }
                        if (record.get("requestYear") == null) sem = "";
                        if (value!==null){
                            var pD = value.replace(/[^0-9]+/g,' ').split(" ");
                            return sem+"   Τροπ.: "+(pD[2]+"-"+pD[1]+"-"+pD[0])+"  "+record.get("submitTime");
                        }
                        else
                        return "";

                    },
                    sortable: false,
                    dataIndex: 'protDate',
                    text: 'Περιγραφή',
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
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        if (value == 1) return "Εκπρόθεσμη";
                        else if (value == 2) return "Εμπρόθεσμη";
                    },
                    sortable: false,
                    dataIndex: 'outdated',
                    text: 'Κατάσταση',
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
                    itemdblclick: 'onCompItemDblClick'
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
                    store: 'company.JobRecrOffice.JOB_RECR_OFFICE_GRID'
                },
                {
                    xtype: 'toolbar',
                    dock: 'top',
                    hidden: true,
                    items: [
                        {
                            xtype: 'textfield',
                            name: 'searchbox',
                            emptyText: 'Αναζήτηση',
                            triggers: {
                                mytrigger: {
                                    cls: 'x-form-search-trigger'
                                }
                            },
                            listeners: {
                                change: {
                                    fn: 'onTextfieldChange',
                                    scope: 'controller'
                                }
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

    onCompItemDblClick: function(dataview, record, item, index, e, eOpts) {
        dataview.up().icon_dbl_click_handler(record);

    }

});