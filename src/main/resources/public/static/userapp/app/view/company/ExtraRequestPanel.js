/*
 * File: app/view/company/ExtraRequestPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.ExtraRequestPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.companyextrarequestpanel',

    requires: [
        'MyApp.view.company.ExtraRequestPanelViewModel',
        'MyApp.view.company.ExtraRequestPanelViewController',
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

    controller: 'companyextrarequestpanel',
    viewModel: {
        type: 'companyextrarequestpanel'
    },
    id: 'extrareqcompany',
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'gridpanel',
            icon_dbl_click_handler: function(record) {
                var emp_comp = Ext.create('widget.companyextrarequestform', {});

                var successAns = function(options, success, response) {
                    if (Ext.JSON.decode(response.responseText).success) {

                        var resp =Ext.JSON.decode(response.responseText);
                        emp_comp.down('form').getForm().findField('compName').setValue(resp.compFullName);
                        emp_comp.down('form').getForm().findField('compAfm').setValue(resp.compTaxNumber);
                        emp_comp.down('form').getForm().findField('compAme').setValue(resp.compAme);
                        emp_comp.down('form').getForm().findField('compPhone').setValue(resp.compPhone);
                    }
                    else{
                        Ext.Msg.alert('Σφάλμα!', 'Υπάρχει πρόβλημα στην επισύναψη στοιχείων');

                    }
                };

                Ext.Ajax.request({
                    url : "/getCompanyInfo",
                    method : 'GET',
                    callback : successAns
                });

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
                }
                else{

                    form.getForm().findField('protNoview').setValue(record.get('protNo'));
                    Ext.getCmp('compextrareq_save_submit_toolbar').hide();

                    form.getForm().findField('file').hide();
                    fields.each(function (field) {
                        field.enable();
                    field.setReadOnly (true);});


                }

                form.getForm().findField('a_new_form').setValue(false);


                form.loadRecord(record);
                form.getForm().findField('inspectionDate').setValue(form.timestampToDate(record.get('inspectionDate')));

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

                    var sepeDeptCombobox = form.getForm().findField('sepeDeptCombobox');
                    sepeDeptCombobox.enable();
                    if (record.get('sepeDeptCombobox')>0 || record.get('sepeDeptCombobox')!==null){
                        sepeDeptCombobox.getStore().getProxy().setExtraParams({"cdKind":record.get('teKe')});
                        sepeDeptCombobox.getStore().load( { callback : function(records, operation, success) {
                            form.getForm().findField('sepeDeptCombobox').setValue(record.get('sepeDeptCombobox').toString());
                        }
                        });
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


                    var sepeDeptCombobox = form.getForm().findField('sepeDeptCombobox');
                    if (record.get('sepeDeptCombobox')>0 || record.get('sepeDeptCombobox')!==null){
                        sepeDeptCombobox.getStore().getProxy().setExtraParams({"id":record.get('sepeDeptCombobox')});
                        sepeDeptCombobox.getStore().load( { callback : function(records, operation, success) {
                            form.getForm().findField('sepeDeptCombobox').setValue(record.get('sepeDeptCombobox').toString());
                        }
                        });
                    }
                    
                    var sepeDept = form.getForm().findField('sepeDept');
                    if (record.get('sepeDeptCombobox')>0 || record.get('sepeDeptCombobox')!==null){
                        //var store2 = Ext.StoreMgr.lookup( 'shared.SEPE_DEPT' );
                        sepeDept.getStore().getProxy().setExtraParam("cdId", record.get('sepeDeptCombobox'));
                        sepeDept.getStore().load( { callback : function(records, operation, success) {
                            var sepeDept = Ext.decode(operation._response.responseText);
                            if (sepeDept !== null)
                                form.getForm().findField('sepeDept').setValue(sepeDept.cdText);
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


        emp_comp.show();

            },
            flex: 1,
            autoScroll: true,
            id: 'companyextrarequestgrid',
            itemId: 'companyextrarequestgrid',
            title: 'Λίστα Υποβολών Πρόσθετων Στοιχείων',
            autoLoad: true,
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.EXTRA_REQS_GRID',
            columns: [
                {
                    xtype: 'rownumberer'
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
                    dataIndex: 'reqStatus',
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
                    store: 'company.EXTRA_REQS_GRID'
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