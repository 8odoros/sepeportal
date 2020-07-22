/*
 * File: app/view/employee/ComplaintsGrid.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.employee.ComplaintsGrid', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.employeecomplaintsgrid',

    requires: [
        'MyApp.view.employee.ComplaintsGridViewModel',
        'MyApp.view.employee.ComplaintsGridViewController',
        'Ext.grid.column.RowNumberer',
        'Ext.grid.filters.filter.String',
        'Ext.grid.View',
        'Ext.toolbar.Paging',
        'Ext.form.field.Text',
        'Ext.form.trigger.Trigger',
        'Ext.grid.filters.Filters',
        'Ext.grid.column.Action',
        'Ext.saki.grid.MultiSearch'
    ],

    controller: 'employeecomplaintsgrid',
    viewModel: {
        type: 'employeecomplaintsgrid'
    },
    autoScroll: true,
    id: 'employeecomplgrid',
    itemId: 'employeecomplgrid1',
    title: 'Λίστα Καταγγελιών',
    autoLoad: true,
    columnLines: false,
    reserveScrollbar: true,
    scroll: 'vertical',
    store: 'employee.COMPLAINTS_GRID',
    defaultListenerScope: true,

    columns: [
        {
            xtype: 'rownumberer'
        },
        {
            xtype: 'gridcolumn',
            filterField: {
                xtype: 'textfield',
                emptyText: 'Αναζήτηση',
                emptyCls: 'empty-text-field'
            },
            sortable: false,
            dataIndex: 'complDescr',
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
            filterField: true,
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
            filterField: {
                xtype: 'combo',
                valueField: 'value',
                displayField: 'text',
                queryMode: 'local',
                store: {
                    fields: [
                        {
                            name: 'value'
                        },
                        {
                            name: 'text'
                        }
                    ],
                    data: [
                        {
                            value: '=1',
                            text: 'Αποθηκευμένη'
                        },
                        {
                            value: '=2',
                            text: 'Καταχωρημένη'
                        }
                    ]
                }
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
            itemdblclick: 'onComplaintItemDblClick',
            refresh: 'onViewRefresh'
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
            store: 'employee.COMPLAINTS_GRID'
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
        },
        {
            ptype: 'saki-gms',
            clearItemT: 'Καθαριμός Φίλτρων',
            iconColumn: false
        }
    ],

    onComplaintItemDblClick: function(dataview, record, item, index, e, eOpts) {
        dataview.up().icon_dbl_click_handler(record);

    },

    onViewRefresh: function(dataview, eOpts) {
        if (dataview.store.data.length<1 && dataview.store.filters.length>0){
            Ext.addon.MessagePop.msg("Προσοχή!", "Δεν υπάρχουν εγγραφές που να αντιστοιχούν στην αναζήτηση σας. Προβάλονται όλες οι εγγραφές της σελίδας.", 5000);
            dataview.up('grid').plugins[1].clearValues();
            dataview.store.reload();
        }
    },

    icon_dbl_click_handler: function(record) {
        var emp_comp = Ext.create('widget.employeecomplaintform', {});
        emp_comp.down('toolbar').getComponent('deletecomplaintbutton').show();

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
            Ext.getCmp('compl_save_submit_toolbar').hide();
            var infopart = Ext.ComponentQuery.query('#infopart')[0];
            infopart.hide();

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

        form.loadRecord(record);


            var complMatter = form.getForm().findField('complMatter');
            if(record.get('complInvolvesLabRelations')===1){
                form.getForm().findField('complInvolves').setValue("0");

                /*complMatter.getStore().getProxy().url = '/employeeComplaintMatter/search/findByComplInvolves';
                complMatter.getStore().getProxy().extraParams = {"spCmInvolves": "0"};
                complMatter.getStore().load({callback: function(records, operation, success) {

                    complMatter.setValue(Ext.util.JSON.decode("["+record.get('complMatter')+"]"));
                    complMatter.setReadOnly(false);
                    if (record.get('subStatus')===2)
                        complMatter.setReadOnly(true);
                }});*/
            }
            if(record.get('complInvlovesSafetyInsp')===1){
                form.getForm().findField('complInvolves').setValue("1");

                /*complMatter.getStore().getProxy().url = '/employeeComplaintMatter/search/findByComplInvolves';
                complMatter.getStore().getProxy().extraParams = {spCmInvolves: "1"};
                complMatter.getStore().load({callback: function(records, operation, success) {

                    complMatter.setValue(Ext.util.JSON.decode("["+record.get('complMatter')+"]"));
                    complMatter.setReadOnly(false);
                    if (record.get('subStatus')===2)
                        complMatter.setReadOnly(true);
                }});*/
            }


        if(record.get('subStatus')===1){

            if(record.get('protDate')!==null){
                form.getForm().findField('protDateview').setValue("");
                form.getForm().findField('protYear').setValue("");
                form.getForm().findField('submitTime').setValue("");
            }

            form.getForm().findField('protDateview').setValue("");
            form.getForm().findField('protDate').setValue("");
            try
            {
                form.getForm().findField('complMatter').setValue(Ext.util.JSON.decode("["+record.get('complMatter')+"]"));
            }
            catch (err)
            {
                form.getForm().findField('complMatter').setValue("");
            }
            //form.getForm().findField('complMatter').setValue(record.get('complMatter').split(","));

            form.getForm().findField('reqStatus').setValue("");

            if (record.get('docIdAttached')!==null){
                var vfc = Ext.ComponentQuery.query('#Exist_File')[0];
                vfc.show();
            }

        }
        else if(record.get('subStatus')===2){

            var pD = record.get('protDate').replace(/[^0-9]+/g,' ').split(" ");
            form.getForm().findField('protDateview').setValue(pD[2]+"-"+pD[1]+"-"+pD[0]);
            form.getForm().findField('complMatter').setValue(Ext.util.JSON.decode("["+record.get('complMatter')+"]"));
            //form.getForm().findField('complMatter').setValue(record.get('complMatter').split(","));
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

            if (record.get('docIdAttached')!==-1){
                var vfc = Ext.ComponentQuery.query('#Exist_File')[0];
                vfc.items.getAt(1).hide();
                vfc.show();
            }

        }


        emp_comp.show();

    }

});