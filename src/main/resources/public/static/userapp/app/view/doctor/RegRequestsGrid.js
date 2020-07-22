/*
 * File: app/view/doctor/RegRequestsGrid.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.doctor.RegRequestsGrid', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.doctorregrequestsgrid',

    requires: [
        'MyApp.view.doctor.RegRequestsGridViewModel',
        'MyApp.view.doctor.RegRequestsGridViewController',
        'Ext.grid.column.RowNumberer',
        'Ext.grid.filters.filter.String',
        'Ext.grid.View',
        'Ext.toolbar.Paging',
        'Ext.form.field.Text',
        'Ext.form.trigger.Trigger',
        'Ext.grid.filters.Filters',
        'Ext.grid.column.Action'
    ],

    controller: 'doctorregrequestsgrid',
    viewModel: {
        type: 'doctorregrequestsgrid'
    },
    autoScroll: true,
    id: 'doctorregreqgrid',
    itemId: 'doctorregreqgrid',
    title: 'Προβολή Απαντήσεων και Αιτήσεων Εγγραφής στο Μητρώο ΙΕ',
    autoLoad: true,
    columnLines: false,
    reserveScrollbar: true,
    scroll: 'vertical',
    store: 'doctor.GENREQUESTS_GRID',
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
            store: 'doctor.GENREQUESTS_GRID'
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
        var emp_comp = Ext.create('widget.doctorregrequestform', {});
        emp_comp.down('toolbar').getComponent('deletebutton').show();

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
        }
        else{

        form.getForm().findField('protNoview').setValue(record.get('protNo'));
        Ext.getCmp('regreqie_save_submit_toolbar').hide();

        fields.each(function (field) {
            field.enable();
            field.setReadOnly (true);});

        }

        form.getForm().findField('a_new_form').setValue(false);


        form.loadRecord(record);

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

            /*if (record.get('attachedDocIdDiploma')!==-1){
                var vfc = Ext.ComponentQuery.query('#Exist_File1')[0];
                vfc.show();
            }

            if (record.get('attachedDocIdEmplTraining')!==-1){
                var vfc = Ext.ComponentQuery.query('#Exist_File2')[0];
                vfc.show();
            }

            if (record.get('attachedDocIdPedyNo')!==-1 || record.get('attachedDocIdPedyYes')!==-1){
                var vfc = Ext.ComponentQuery.query('#Exist_File3')[0];
                vfc.show();
            }


            if (record.get('attachedDocIdMilitary')!==-1){
                var vfc = Ext.ComponentQuery.query('#Exist_File5')[0];
                vfc.show();
            }

            if (record.get('attachedDocIdMedassoc')!==-1){
                var vfc = Ext.ComponentQuery.query('#Exist_File4')[0];
                vfc.show();
            }*/

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

            /*if (record.get('attachedDocIdDiploma')!==-1){
                var vfc = Ext.ComponentQuery.query('#Exist_File1')[0];
                vfc.items.getAt(1).hide();
                vfc.show();
            }

            if (record.get('attachedDocIdEmplTraining')!==-1){
                var vfc = Ext.ComponentQuery.query('#Exist_File2')[0];
                vfc.items.getAt(1).hide();
                vfc.show();
            }

            if (record.get('attachedDocIdPedyNo')!==-1 || record.get('attachedDocIdPedyYes')!==-1){
                var vfc = Ext.ComponentQuery.query('#Exist_File3')[0];
                vfc.items.getAt(1).hide();
                vfc.show();
            }


            if (record.get('attachedDocIdMilitary')!==-1 ){
                var vfc = Ext.ComponentQuery.query('#Exist_File5')[0];
                vfc.items.getAt(1).hide();
                vfc.show();
            }

            if (record.get('attachedDocIdMedassoc')!==-1 ){
                var vfc = Ext.ComponentQuery.query('#Exist_File4')[0];
                vfc.items.getAt(1).hide();
                vfc.show();
            }*/

            if (record.get('attachedDocId')!==-1 ){
                var vfc = Ext.ComponentQuery.query('#Exist_File')[0];
                vfc.items.getAt(1).hide();
                vfc.show();
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


        }

            //form.getForm().findField('cooperationType').setValue(record.get('cooperationType').toString());
            //form.getForm().findField('speciality').setValue(record.get('speciality').toString());


        if (record.get('educationLevel')===null)
            form.getForm().findField('educationLevel').setValue();
        else if (record.get('educationLevel')===-1)
            form.getForm().findField('educationLevel').setValue();
        else if (record.get('educationLevel')>-1)
            form.getForm().findField('educationLevel').setValue(record.get('educationLevel').toString());


            /*if (record.get('speciality')===99)
                form.getForm().findField('specialityOther').show();
            if (record.get('speciality')!==1){
                form.getForm().findField('ieDocDate').up().up().getComponent('ieDoc').show();
                form.getForm().findField('ieDocProtNo').show();
                form.getForm().findField('ieDocDate').show();
                form.getForm().findField('ieDocCompanyName').show();
                form.getForm().findField('ieDocDepartment').show();

                var ieDocDate = record.get('ieDocDate').replace(/[^0-9]+/g,' ').split(" ");
                form.getForm().findField('ieDocDate').setValue(ieDocDate[2]+"-"+ieDocDate[1]+"-"+ieDocDate[0]);

            }*/


        emp_comp.show();


    }

});