/*
 * File: app/view/employee/DisputesGrid.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.employee.DisputesGrid', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.employeedisputesgrid',

    requires: [
        'MyApp.view.employee.DisputesGridViewModel',
        'MyApp.view.employee.DisputesGridViewController',
        'Ext.grid.column.RowNumberer',
        'Ext.grid.filters.filter.String',
        'Ext.grid.View',
        'Ext.toolbar.Paging',
        'Ext.form.field.Text',
        'Ext.form.trigger.Trigger',
        'Ext.grid.filters.Filters',
        'Ext.grid.column.Action'
    ],

    controller: 'employeedisputesgrid',
    viewModel: {
        type: 'employeedisputesgrid'
    },
    autoScroll: true,
    id: 'employeedispudegrid',
    itemId: 'employeedispudegrid',
    title: 'Λίστα Αιτήσεων Συζήτησης Εργατικής Διαφοράς',
    autoLoad: true,
    columnLines: false,
    reserveScrollbar: true,
    scroll: 'vertical',
    store: 'employee.DISPUTES_GRID',
    defaultListenerScope: true,

    columns: [
        {
            xtype: 'rownumberer'
        },
        {
            xtype: 'gridcolumn',
            renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                if (record.get('compName') == null)
                    return "Οργανισμός: (κενό) -- ("+ value.length + " καταγγελίες)";
                else
                    return "Οργανισμός: " +record.get('compName')+" -- ("+ value.length + " καταγγελίες)";
            },
            sortable: false,
            dataIndex: 'employeeDisputeReason',
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
                if (value!==null){
                    if (record.get('subStatus')===1){
                        return "Αποθηκευμένη";
                    }
                    else
                        return "Καταχωρημένη";
                }
                else
                    return "Αποθηκευμένη";
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

                        var store = Ext.StoreMgr.lookup( 'shared.COMPL_STATUS' );
                        if (record.get('reqStatus')>0){
                            return store.findRecord('reqStatus', record.get('reqStatus').toString()).get('description');
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
                            //this.items[0].tooltip = 'Επεξεργασία';
                            return 'editme'; // css for icon
                        }
                        else{
                            //this.items[0].tooltip = 'Προβολή';
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
            itemdblclick: 'onDisputeItemDblClick'
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
            store: 'employee.DISPUTES_GRID'
        },
        {
            xtype: 'toolbar',
            dock: 'top',
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
    ],

    onDisputeItemDblClick: function(dataview, record, item, index, e, eOpts) {
        dataview.up().icon_dbl_click_handler(record);
    },

    icon_dbl_click_handler: function(record) {
        var store = Ext.StoreMgr.lookup( 'employee.DISPUTE_REASONS' );
        Ext.getBody().mask("Παρακαλώ Περιμένετε...");
        store.load( { callback : function(records, operation, success) {

            ///////////////////////////////////////////////////////////////////////////
            var emp_comp = Ext.create('widget.employeedisputeform', {});

            emp_comp.down('toolbar').getComponent('deletedisputebutton').show();

            var successAns = function(options, success, response) {
                if (Ext.JSON.decode(response.responseText).success) {

                    var resp =Ext.JSON.decode(response.responseText);
                    emp_comp.down('form').getForm().findField('empFirstname').setValue(resp.empFirstname);
                    emp_comp.down('form').getForm().findField('empLastname').setValue(resp.empLastname);
                    emp_comp.down('form').getForm().findField('empAmka').setValue(resp.empAmka);
                    emp_comp.down('form').getForm().findField('empCardNumber').setValue(resp.empCardNumber);
                    emp_comp.down('form').getForm().findField('empAfm').setValue(resp.empAfm);
                }
                else{
                    Ext.Msg.alert('Σφάλμα!', 'Υπάρχει πρόβλημα στην επισύναψη στοιχείων');
                }
            };

            Ext.Ajax.request({
                url : "/getEmployeeInfo",
                method : 'GET',
                callback : successAns
            });

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

                if (record.get('compBrAddrPe')!==null && record.get('compBrAddrD')===null){
                    form.getForm().findField('compBrAddrPe').setDisabled(false);
                    form.getForm().findField('compBrAddrD').setDisabled(false);
                }
                else if (record.get('compBrAddrPe')!==null && record.get('compBrAddrD')!==null){
                    form.getForm().findField('compBrAddrPe').setDisabled(false);
                    form.getForm().findField('compBrAddrD').setDisabled(false);
                    form.getForm().findField('compBrAddrK').setDisabled(false);
                }
                else if (record.get('compBrAddrP')!==null && record.get('compBrAddrPe')===null){
                    form.getForm().findField('compBrAddrPe').setDisabled(false);
                }

                if (record.get('empAddrPe')!==null && record.get('empAddrD')===null){
                    form.getForm().findField('empAddrPe').setDisabled(false);
                    form.getForm().findField('empAddrD').setDisabled(false);
                }
                else if (record.get('empAddrPe')!==null && record.get('empAddrD')!==null){
                    form.getForm().findField('empAddrPe').setDisabled(false);
                    form.getForm().findField('empAddrD').setDisabled(false);
                    form.getForm().findField('empAddrK').setDisabled(false);
                }
                else if (record.get('empAddrP')!==null && record.get('empAddrPe')===null){
                    form.getForm().findField('empAddrPe').setDisabled(false);
                }

                if (record.get('rtstakLevel2')!==null && record.get('rtstakLevel3')===null){
                    form.getForm().findField('rtstakLevel2').setDisabled(false);
                    form.getForm().findField('rtstakLevel3').setDisabled(false);
                }
                else if (record.get('rtstakLevel2')!==null && record.get('rtstakLevel3')!==null && record.get('rtstakLevel4')===null){
                    form.getForm().findField('rtstakLevel2').setDisabled(false);
                    form.getForm().findField('rtstakLevel3').setDisabled(false);
                    form.getForm().findField('rtstakLevel4').setDisabled(false);
                }
                else if (record.get('rtstakLevel2')!==null && record.get('rtstakLevel3')!==null && record.get('rtstakLevel4')!==null){
                    form.getForm().findField('rtstakLevel2').setDisabled(false);
                    form.getForm().findField('rtstakLevel3').setDisabled(false);
                    form.getForm().findField('rtstakLevel4').setDisabled(false);
                    form.getForm().findField('rtstakLevel5').setDisabled(false);
                }
                else if (record.get('rtstakLevel1')!==null && record.get('rtstakLevel2')===null){
                    form.getForm().findField('rtstakLevel2').setDisabled(false);
                }


            }
            else{

                form.getForm().findField('protNoview').setValue(record.get('protNo'));
                Ext.getCmp('disp_save_submit_toolbar').hide();
                form.getForm().findField('file').hide();
                fields.each(function (field) {
                    field.enable();
                    field.setReadOnly (true);});


            }

            form.getForm().findField('a_new_form').setValue(false);

            var branchId = form.getForm().findField('branch1Id');

            if (record.get('compAfm')!==null){
                branchId.getStore().getProxy().extraParams = {afm:record.get('compAfm')};
                branchId.getStore().load({callback: function(records, operation, success) {

                    branchId.setReadOnly(false);
                    if (record.get('subStatus')===2)
                        branchId.setReadOnly(true);
                }});
            }

            //get
            if (record.get('empSpecialityId')!==null && record.get('empSpecialityId')!==-1){
                var store4 = Ext.StoreMgr.lookup( 'employee.SPECIALTY' );
                store4.proxy.url = '/TEmployeeSpeciality/'+record.get('empSpecialityId');
                store4.proxy.reader.setRootProperty("");
                store4.load();

            }


            form.loadRecord(record);
            if(record.get('empFromDate')!==null)
                form.getForm().findField('empFromDate').setValue( form.timestampToDate(record.get('empFromDate')) );
            if(record.get('empUntilDate')!==null)
                form.getForm().findField('empUntilDate').setValue( form.timestampToDate(record.get('empUntilDate')) );


            if (record.get('empSpecialityId')!==null)
                form.getForm().findField('empSpecialityId').setValue(record.get('empSpecialityId').toString());

            emp_comp.show();
            Ext.getBody().unmask();

            if (record.get('empWorkingHours')!==null){
                var st = record.get('empWorkingHours').split("-");
                if (st[0]!=="null")
                    form.getForm().findField('empWorkingHoursFrom').setValue(st[0]);
                if (st[1]!=="null")
                    form.getForm().findField('empWorkingHoursUntil').setValue(st[1]);
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

                if (record.get('docIdAttached')!==null){
                    var vfc = Ext.ComponentQuery.query('#Exist_File')[0];
                    vfc.show();
                }

                form.getForm().findField('department').setValue("");
            }
            else if(record.get('subStatus')===2){

                var pD = record.get('protDate').replace(/[^0-9]+/g,' ').split(" ");
                form.getForm().findField('protDateview').setValue(pD[2]+"-"+pD[1]+"-"+pD[0]);

                form.getForm().findField('reqStatus').setValue(record.get('reqStatus').toString());
                form.getForm().findField('StatusMsg').show();
                form.getForm().findField('StatusMsg').setValue(record.getField("StatusMsg").value);
                /*if (record.get('department')>0 || record.get('department')!==null){
                 var store2 = Ext.StoreMgr.lookup( 'shared.SEPE_DEPT' );
                 store2.getProxy().setExtraParam("id", record.get('department'));
                 store2.load( { callback : function(records, operation, success) {
                 form.getForm().findField('department').setValue(record.get('department').toString());
                 }
                 });
                 }*/

                if (record.get('department')>0 || record.get('department')!==null){
                    var store2 = Ext.StoreMgr.lookup( 'shared.SEPE_DEPT' );
                    store2.getProxy().url='/TSepeDepartment/search/findById'
                    store2.getProxy().setExtraParam("cdId", record.get('department'));
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

                if (record.get('docIdAttached')!==-1){
                    var vfc1 = Ext.ComponentQuery.query('#Exist_File')[0];
                    vfc1.items.getAt(1).hide();
                    vfc1.show();
                }

            }



            if (record.get('empMaritalStatus')!==null)
                form.getForm().findField('empMaritalStatus').setValue(record.get('empMaritalStatus').toString());
            var tEmployeeDisputeReasons = record.get('employeeDisputeReason');
            if(tEmployeeDisputeReasons!==null){
                for (var i = 0; i < tEmployeeDisputeReasons.length; i++) {
                    var cbc = form.getForm().findField(tEmployeeDisputeReasons[i].reasonId.toString()).up('fieldcontainer');
                    cbc.down('checkbox').setValue(tEmployeeDisputeReasons[i].reasonId);
                    cbc.down('textarea').setValue(tEmployeeDisputeReasons[i].comments);
                    if (tEmployeeDisputeReasons[i].dateStart!==null){
                        var sD = tEmployeeDisputeReasons[i].dateStart.replace(/[^0-9]+/g,' ').split(" ");
                        cbc.down().down('container').items.getAt(0).setValue(sD[2]+"-"+sD[1]+"-"+sD[0]);}
                    if (tEmployeeDisputeReasons[i].dateEnd!==null){
                        var eD = tEmployeeDisputeReasons[i].dateEnd.replace(/[^0-9]+/g,' ').split(" ");
                        cbc.down().down('container').items.getAt(1).setValue(eD[2]+"-"+eD[1]+"-"+eD[0]);
                    }

                }

            }


            ///////////////////////////////////////////////////////////////////////////
        }
        });


    }

});