/*
 * File: app/view/technician/RegRequestsGrid.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.technician.RegRequestsGrid', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.technicianregrequestsgrid',

    requires: [
        'MyApp.view.technician.RegRequestsGridViewModel',
        'MyApp.view.technician.RegRequestsGridViewController',
        'Ext.grid.column.RowNumberer',
        'Ext.grid.filters.filter.String',
        'Ext.grid.View',
        'Ext.toolbar.Paging',
        'Ext.form.field.Text',
        'Ext.form.trigger.Trigger',
        'Ext.grid.filters.Filters',
        'Ext.grid.column.Action'
    ],

    controller: 'technicianregrequestsgrid',
    viewModel: {
        type: 'technicianregrequestsgrid'
    },
    autoScroll: true,
    id: 'technicianregreqgrid',
    itemId: 'technicianregreqgrid',
    title: 'Προβολή Απαντήσεων και Αιτήσεων Εγγραφής στο Μητρώο ΤΑ',
    autoLoad: true,
    columnLines: false,
    reserveScrollbar: true,
    scroll: 'vertical',
    store: 'technician.GENREQUESTS_GRID',
    defaultListenerScope: true,

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
            text: 'Καταχώρηση',
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
            flex: 20
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
            flex: 20
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
                            text: 'Ενεργή Εγγραφή Μητρώου'
                        },
                        {
                            value: '=2',
                            text: 'Παλιά Εγγραφή'
                        }
                    ]
                }
            },
            sortable: false,
            dataIndex: 'subStatus',
            text: 'Κατάσταση',
            flex: 20
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
                            //this.items[0].tooltip = 'Επεξεργασία';
                            return 'editme'; // css for icon
                        }
                        else{
                            //                  this.items[0].tooltip = 'Προβολή';
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
            store: 'technician.GENREQUESTS_GRID'
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
        var emp_comp = Ext.create('widget.technicianregrequestform', {});
        emp_comp.down('toolbar').getComponent('deletebutton').show();

        var successAns = function(options, success, response) {
            if (Ext.JSON.decode(response.responseText).success) {

                var resp =Ext.JSON.decode(response.responseText);
                emp_comp.down('form').getForm().findField('firstname').setValue(resp.firstname);
                emp_comp.down('form').getForm().findField('lastname').setValue(resp.lastname);
                emp_comp.down('form').getForm().findField('fatherrname').setValue(resp.fatherrname);
                emp_comp.down('form').getForm().findField('afm').setValue(resp.afm);
                emp_comp.down('form').getForm().findField('amka').setValue(resp.amka);
                emp_comp.down('form').getForm().findField('cardNumber').setValue(resp.cardNumber);
            }
            else{
                Ext.Msg.alert('Σφάλμα!', 'Υπάρχει πρόβλημα στην επισύναψη στοιχείων');
            }
        };

        Ext.Ajax.request({
            url : "/getTechnicianInfo",
            params:{mode:1},
            method : 'GET',
            callback : successAns
        });

        var form = emp_comp.down('form');
        var fields = form.getForm().getFields();

        if(record.get('subStatus')===1){
            form.getForm().findField('protNoview').setValue("-");
            if (record.get('reqAddrPe')!==null && record.get('reqAddrD')===null){
                form.getForm().findField('reqAddrPe').setDisabled(false);
                form.getForm().findField('reqAddrD').setDisabled(false);
            }
            else if (record.get('reqAddrPe')!==null && record.get('reqAddrD')!==null){
                form.getForm().findField('reqAddrPe').setDisabled(false);
                form.getForm().findField('reqAddrD').setDisabled(false);
                form.getForm().findField('reqAddrK').setDisabled(false);
            }
            else if (record.get('reqAddrP')!==null && record.get('reqAddrPe')===null){
                form.getForm().findField('reqAddrPe').setDisabled(false);
            }
            if (record.get('educationLevel')!==null){
                form.getForm().findField('speciality').setDisabled(false);
            }
        }
        else{

            form.getForm().findField('protNoview').setValue(record.get('protNo'));
            Ext.getCmp('regreqta_save_submit_toolbar').hide();

            form.getForm().findField('file').hide();
            fields.each(function (field) {
                    field.enable();
                    field.setReadOnly (true);});

        }
        form.getForm().findField('a_new_form').setValue(false);


        form.loadRecord(record);


        //form.getForm().findField('cooperationType').setValue(record.get('cooperationType').toString());

        if(record.get('subStatus')===1){

            if(record.get('protDate')!==null){
                form.getForm().findField('protDateview').setValue("");
                form.getForm().findField('protYear').setValue("");
                form.getForm().findField('submitTime').setValue("");
            }
            form.getForm().findField('department').setValue("");

            form.getForm().findField('protDateview').setValue("");
            form.getForm().findField('protDate').setValue("");

            form.getForm().findField('reqStatus').setValue("");

            try
            {
                form.getForm().findField('speciality').setValue(Ext.util.JSON.decode("["+record.get('speciality')+"]"));
            }
            catch (err)
            {
                form.getForm().findField('speciality').setValue("");
            }

            if (record.get('attachedDocIdEmplTraining')!==-1){
                var vfc = Ext.ComponentQuery.query('#Exist_File2')[0];
                vfc.show();
            }


            if (record.get('attachedDocId')!==-1 ){
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

            try
            {
                form.getForm().findField('speciality').setValue(Ext.util.JSON.decode("["+record.get('speciality')+"]"));
            }
            catch (err)
            {
                form.getForm().findField('speciality').setValue("");
            }


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


                if (record.get('caseId')!==null && record.get('docId')!==null){

            form.getForm().findField('StatusMsg').show();
                    var store3 = Ext.StoreMgr.lookup( 'shared.CASE_MESSAGE' );
                    store3.getProxy().setExtraParams({"caseId": record.get('caseId'),"docId": record.get('docId')});
                    store3.load( { callback : function(records, operation, success) {
                        if(records!==null)
                            record.getField("StatusMsg").value=records[0].getData().cdText;}
                                                });
                }



            if (record.get('attachedDocIdEmplTraining')!==-1){
                    var vfc = Ext.ComponentQuery.query('#Exist_File2')[0];
                    vfc.items.getAt(1).hide();
                    vfc.show();
                }


            if (record.get('attachedDocId')!==-1 ){
                    var vfc = Ext.ComponentQuery.query('#Exist_File')[0];
                    vfc.items.getAt(1).hide();
                    vfc.show();
                }

        }

        //form.getForm().findField('speciality').setValue(record.get('speciality').toString());
        if (record.get('educationLevel')===null)
            form.getForm().findField('educationLevel').setValue();
        else if (record.get('educationLevel')===-1)
            form.getForm().findField('educationLevel').setValue();
        else if (record.get('educationLevel')>-1)
            form.getForm().findField('educationLevel').setValue(record.get('educationLevel').toString());


        if (record.get('speciality')!=null && record.get('speciality').indexOf('99') !== -1)
            form.getForm().findField('specialityOther').show();

        emp_comp.show();

    }

});