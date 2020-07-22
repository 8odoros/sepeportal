/*
 * File: app/view/company/GenRequestsPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianMilitaryPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.companytechnicianmilitarypanel',

    requires: [
        'MyApp.view.company.TechnicianMilitaryPanelViewModel',
        'MyApp.view.company.TechnicianMilitaryPanelViewController',
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

    controller: 'companytechnicianmilitarypanel',
    viewModel: {
        type: 'companytechnicianmilitarypanel'
    },
    id: 'complaints6',
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'gridpanel',
            icon_dbl_click_handler: function(record) {
                var emp_comp = Ext.create('widget.companytechnicianmilitaryform', {});
                emp_comp.down('toolbar').getComponent('deletegenrequestbutton').show();

                var form = emp_comp.down('form');
                var fields = form.getForm().getFields();


                if(record.get('subStatus')===1){
                    form.getForm().findField('protNoview').setValue("-");
                    if (record.get('inspAddrPe')!==null && record.get('inspAddrD')===null){
                        form.getForm().findField('inspAddrPe').setDisabled(false);
                        form.getForm().findField('inspAddrD').setDisabled(false);
                    }
                    else if (record.get('inspAddrPe')!==null && record.get('inspAddrD')!==null){
                        form.getForm().findField('inspAddrPe').setDisabled(false);
                        form.getForm().findField('inspAddrD').setDisabled(false);
                        form.getForm().findField('inspAddrK').setDisabled(false);
                    }
                    else if (record.get('inspAddrP')!==null && record.get('inspAddrPe')===null){
                        form.getForm().findField('inspAddrPe').setDisabled(false);
                    }

                    /*if (record.get('reprAddrPe')!==null && record.get('reprAddrD')===null){
                        form.getForm().findField('reprAddrPe').setDisabled(false);
                        form.getForm().findField('reprAddrD').setDisabled(false);
                    }
                    else if (record.get('reprAddrPe')!==null && record.get('reprAddrD')!==null){
                        form.getForm().findField('reprAddrPe').setDisabled(false);
                        form.getForm().findField('reprAddrD').setDisabled(false);
                        form.getForm().findField('reprAddrK').setDisabled(false);
                    }
                    else if (record.get('reprAddrP')!==null && record.get('reprAddrPe')===null){
                        form.getForm().findField('reprAddrPe').setDisabled(false);
                    }*/
                }
                else{

                    form.getForm().findField('protNoview').setValue(record.get('protNo'));
                    Ext.getCmp('compgenreq_save_submit_toolbar').hide();

                    form.getForm().findField('file').hide();
                    fields.each(function (field) {
                        field.enable();
                        field.setReadOnly (true);});


                }

                form.getForm().findField('a_new_form').setValue(false);


                form.loadRecord(record);

                if(record.get('requestTypeId')!==null){
                    form.getForm().findField('requestTypeId').setValue(record.get('requestTypeId').toString());
                }
                if(record.get('reqSubject')!==null){
                    form.getForm().findField('reqSubject').setValue(record.get('reqSubject').toString());
                }

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

                if (record.get('ta_type')!= null)
                {
                    if (record.get('ta_type') == 0)
                    {
                        form.getForm().findField('ta_categ').show();
                        form.getForm().findField('ta_rank').show();
                    }
                    else
                    {
                        form.getForm().findField('ta_branch').show();
                        form.getForm().findField('ta_speciality').show();
                        form.getForm().findField('ta_speciality').enable();
                        try
                        {
                            form.getForm().findField('ta_speciality').setValue(Ext.util.JSON.decode("["+record.get('ta_speciality')+"]"));
                        }
                        catch (err)
                        {
                            form.getForm().findField('ta_speciality').setValue("");
                        }
                        if (record.get('ta_speciality').substring(1, record.get('ta_speciality').length-1) == 99){
                            form.getForm().findField('ta_specialityOther').show();
                        }
                    }
                }


                emp_comp.show();

            },
            flex: 1,
            autoScroll: true,
            id: 'companytechnicianmilitarygrid',
            itemId: 'companytechnicianmilitarygrid',
            title: 'Αναγγελίες Τεχνικών Ασφάλειας για ΈΝΟΠΛΕΣ ΔΥΝΑΜΕΙΣ',
            autoLoad: true,
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.GENREQUESTS_TECHNICIAN_MILITARY_GRID',
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
                    itemdblclick: 'onTechnicianMilitaryItemDblClick'
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
                    store: 'company.GENREQUESTS_TECHNICIAN_MILITARY_GRID'
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

    onTechnicianMilitaryItemDblClick: function(dataview, record, item, index, e, eOpts) {
        dataview.up().icon_dbl_click_handler(record);

    }

});