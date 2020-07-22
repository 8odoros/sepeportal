/*
 * File: app/view/company/SundayPmtPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.SundayPmtPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.companysundaypmtpanel',

    requires: [
        'MyApp.view.company.SundayPmtPanelViewModel',
        'MyApp.view.company.SundayPmtPanelViewController',
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

    controller: 'companysundaypmtpanel',
    viewModel: {
        type: 'companysundaypmtpanel'
    },
    id: 'sundaypmtcompany',
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'gridpanel',
            icon_dbl_click_handler: function(record) {
                var emp_comp = Ext.create('widget.companysundaypmtformsundaypmtform', {});

                var successAns = function(options, success, response) {
                    if (Ext.JSON.decode(response.responseText).success) {

                        var resp =Ext.JSON.decode(response.responseText);
                        emp_comp.down('form').getForm().findField('compName').setValue(resp.compFullName);
                        emp_comp.down('form').getForm().findField('compAfm').setValue(resp.compTaxNumber);
                        emp_comp.down('form').getForm().findField('compAme').setValue(resp.compAme);
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
                }
                else{

                    form.getForm().findField('protNoview').setValue(record.get('protNo'));
                    Ext.getCmp('compsundaypmt_save_submit_toolbar').hide();

                    form.getForm().findField('file').hide();
                    fields.each(function (field) {
                        field.enable();
                        field.setReadOnly (true);});
                }

                form.getForm().findField('a_new_form').setValue(false);

                emp_comp.show();

                form.loadRecord(record);

                if(record.get('sundayDate')!==null)
                    form.getForm().findField('sundayDateView').setValue( form.timestampToDate(record.get('sundayDate')) );

                if(record.get('holidayDate')!==null)
                    form.getForm().findField('holidayDate').setValue( form.timestampToDate(record.get('holidayDate')) );

                if (record.get('inspectorName')!==null && record.get('inspectorName')>0){
                    var storeI = Ext.StoreMgr.lookup( 'company.INSPECTORS' );
                    storeI.getProxy().setExtraParams({"id": record.get('inspectorName')});
                    storeI.load( { callback : function(records, operation, success) {
                        if(records!==null)
                            form.getForm().findField('inspectorName').setValue(records[0].get('description'));
                    }
                    });
                }
                else
                    form.getForm().findField('inspectorName').setValue();

                /*var store1 = Ext.StoreMgr.lookup('company.SundayPmt.Pers');
                 store1.getProxy().setUrl(record.get('sundayPmtPers'));
                 store1.load({
                 callback: function(records, operation, success) {
                 for (var i = 0; i < records.length; i++) {
                 var newpers = Ext.create('widget.companysundaypmtformpers', {});
                 newpers.items.getAt(0).items.getAt(0).setValue(form.getForm().findField('persnum').getValue() + ". ");
                 newpers.items.getAt(1).items.getAt(0).items.getAt(0).setValue(records[i].get('incNum'));
                 newpers.items.getAt(1).items.getAt(0).items.getAt(1).setValue(records[i].get('empFirstname'));
                 newpers.items.getAt(1).items.getAt(0).items.getAt(2).setValue(records[i].get('empLastname'));
                 newpers.items.getAt(1).items.getAt(0).items.getAt(3).setValue(records[i].get('empAfm'));
                 newpers.items.getAt(1).items.getAt(0).items.getAt(4).setValue(records[i].get('empAmka'));
                 newpers.items.getAt(1).items.getAt(0).items.getAt(5).setValue(records[i].get('empIka'));
                 newpers.items.getAt(1).items.getAt(0).items.getAt(6).setValue(parseInt(records[i].get('empSex')));
                 newpers.items.getAt(1).items.getAt(0).items.getAt(7).setValue(records[i].get('empSpeciality'));
                 newpers.items.getAt(1).items.getAt(0).items.getAt(8).setValue(records[i].get('empWorkHourStart'));
                 newpers.items.getAt(1).items.getAt(0).items.getAt(9).setValue(records[i].get('empWorkHourStop'));
                 if(records[i].get('empAlternateRestingDay')!==null)
                 newpers.items.getAt(1).items.getAt(0).items.getAt(10).setValue(form.timestampToDate(records[i].get('empAlternateRestingDay')));
                 newpers.items.getAt(1).items.getAt(0).items.getAt(12).setValue(records[i].get('inspectorComments'));
                 Ext.getCmp('pers').add(newpers);
                 form.getForm().findField('persnum').setValue(parseInt(form.getForm().findField('persnum').getValue()) + 1);
                 }
                 form.getForm().findField('persnum').setValue(parseInt(form.getForm().findField('persnum').getValue()) - 1);

                 if(record.get('subStatus')===2){

                 fields = form.getForm().getFields();
                 fields.each(function (field) {
                 field.enable();
                 field.setReadOnly (true);});
                 }
                 emp_comp.show();
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

                    /*Ext.getCmp('persadd').destroy();
                     Ext.getCmp('persdel').destroy();*/

                }

            },
            flex: 1,
            autoScroll: true,
            id: 'companysundaypmtgrid',
            itemId: 'companysundaypmtgrid',
            title: 'Αιτήσεις χορήγησης άδειας εργασίας κατά την Κυριακή & ημέρα αργίας',
            autoLoad: true,
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.SundayPmt.SUNDAY_PMT_GRID',
            columns: [
                {
                    xtype: 'rownumberer',
                    flex: 2
                },
                /*{
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
                 },*/
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
                    flex: 7
                },
                {
                    xtype: 'gridcolumn',
                    sortable: false,
                    dataIndex: 'cdText',
                    text: 'Αρμόδια Υπηρεσία',
                    flex: 18
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        if (value != null)
                        {
                            var pD = record.data['sundayDate'].replace(/[^0-9]+/g,' ').split(" ");
                            return (pD[2]+"-"+pD[1]+"-"+pD[0]);
                        }
                        else
                        {
                            try
                            {
                                var pD = record.data['holidayDate'].replace(/[^0-9]+/g,' ').split(" ");
                                return (pD[2]+"-"+pD[1]+"-"+pD[0]);
                            }
                            catch (err)
                            {
                                return "";
                            }
                        }
                    },
                    sortable: false,
                    dataIndex: 'sundayDate',
                    text: 'Ημερομηνία Εργασίας',
                    flex: 10
                },
                {
                    xtype: 'gridcolumn',
                    sortable: false,
                    dataIndex: 'compAddr',
                    text: 'Οδός/Αριθμός',
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
                    flex: 10
                },
                /*{
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
                    store: 'company.SundayPmt.SUNDAY_PMT_GRID'
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

    },
});