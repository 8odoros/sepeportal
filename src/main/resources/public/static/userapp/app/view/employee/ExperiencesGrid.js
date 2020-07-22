/*
 * File: app/view/employee/ExperiencesGrid.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.employee.ExperiencesGrid', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.employeeexperiencesgrid',

    requires: [
        'MyApp.view.employee.ExperiencesGridViewModel',
        'MyApp.view.employee.ExperiencesGridViewController',
        'Ext.grid.column.RowNumberer',
        'Ext.grid.filters.filter.String',
        'Ext.grid.View',
        'Ext.toolbar.Paging',
        'Ext.form.field.Text',
        'Ext.form.trigger.Trigger',
        'Ext.grid.filters.Filters',
        'Ext.grid.column.Action'
    ],

    controller: 'employeeexperiencesgrid',
    viewModel: {
        type: 'employeeexperiencesgrid'
    },
    autoScroll: true,
    id: 'employeeexpergrid',
    itemId: 'employeeexpergrid',
    title: 'Λίστα Αιτήσεων Χορήγησης Βεβαίωσης Προϋπηρεσίας',
    autoLoad: true,
    columnLines: false,
    reserveScrollbar: true,
    scroll: 'vertical',
    store: 'employee.EXPERIENCES_GRID',
    defaultListenerScope: true,

    columns: [
        {
            xtype: 'rownumberer'
        },
        {
            xtype: 'gridcolumn',
            sortable: false,
            dataIndex: 'compName',
            text: 'Οργανισμός',
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
            itemdblclick: 'onExperienceItemDblClick'
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
            store: 'employee.EXPERIENCES_GRID'
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

    onExperienceItemDblClick: function(dataview, record, item, index, e, eOpts) {
        dataview.up().icon_dbl_click_handler(record);

    },

    icon_dbl_click_handler: function(record) {
        var emp_comp = Ext.create('widget.employeeexperienceform', {});
        emp_comp.down('toolbar').getComponent('deleteexperiencebutton').show();

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
            Ext.getCmp('exper_save_submit_toolbar').hide();

            fields.each(function (field) {
                field.enable();
                field.setReadOnly (true);});


        }

        form.getForm().findField('a_new_form').setValue(false);

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


        if (record.get('empSpecialityId')!==null){
            form.getForm().findField('empSpecialityId').setValue(record.get('empSpecialityId').toString());
        }
        try
        {
            form.getForm().findField('intentedUse').setValue(record.get('intentedUse').toString());
        }
        catch (err)
        {
            form.getForm().findField('intentedUse').setValue("");
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

            if (record.get('attachedDocIdEmplVer')!==null){
                var vfc = Ext.ComponentQuery.query('#Exist_File1')[0];
                vfc.show();
            }
            if (record.get('attachedDocIdIka')!==null){
                var vfc = Ext.ComponentQuery.query('#Exist_File2')[0];
                vfc.show();
            }
            if (record.get('attachedDocIdSepe')!==null){
                var vfc = Ext.ComponentQuery.query('#Exist_File3')[0];
                vfc.show();
            }
            if (record.get('attachedDocIdJudgmnt')!==null){
                var vfc = Ext.ComponentQuery.query('#Exist_File4')[0];
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


            if (record.get('attachedDocIdEmplVer')!==-1){
                var vfc = Ext.ComponentQuery.query('#Exist_File1')[0];
                vfc.items.getAt(1).hide();
                vfc.show();
            }
            else{
                Ext.getCmp("complfile1").hide();
            }
            if (record.get('attachedDocIdIka')!==-1){
                var vfc = Ext.ComponentQuery.query('#Exist_File2')[0];
                vfc.items.getAt(1).hide();
                vfc.show();
            }
            else{
                Ext.getCmp("complfile2").hide();
            }
            if (record.get('attachedDocIdSepe')!==-1){
                var vfc = Ext.ComponentQuery.query('#Exist_File3')[0];
                vfc.items.getAt(1).hide();
                vfc.show();
            }
            else{
                Ext.getCmp("complfile3").hide();
            }
            if (record.get('attachedDocIdJudgmnt')!==-1){
                var vfc = Ext.ComponentQuery.query('#Exist_File4')[0];
                vfc.items.getAt(1).hide();
                vfc.show();
            }
            else{
                Ext.getCmp("complfile4").hide();
            }


        }


        emp_comp.show();

    }

});