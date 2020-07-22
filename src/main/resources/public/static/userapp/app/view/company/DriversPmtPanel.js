/*
 * File: app/view/company/DriversPmtPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.DriversPmtPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.companydriverspmtpanel',

    requires: [
        'MyApp.view.company.DriversPmtPanelViewModel',
        'MyApp.view.company.DriversPmtPanelViewController',
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

    controller: 'companydriverspmtpanel',
    viewModel: {
        type: 'companydriverspmtpanel'
    },
    id: 'driverspmtcompany',
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'gridpanel',
            icon_dbl_click_handler: function(record) {
                var emp_comp = Ext.create('widget.companydriverspmtformdriverpmtform', {});
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
                    Ext.getCmp('compdriverspmt_save_submit_toolbar').hide();


                }

                form.getForm().findField('a_new_form').setValue(false);

                form.loadRecord(record);


                if(record.get('subStatus')===1){

                    if(record.get('protDate')!==null){
                        form.getForm().findField('protDateview').setValue("");
                        form.getForm().findField('protYear').setValue("");
                        form.getForm().findField('submitTime').setValue("");
                    }

                    form.getForm().findField('protDateview').setValue("");
                    form.getForm().findField('protDate').setValue("");

                    form.getForm().findField('reqStatus').setValue("");

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

            //Ext.getCmp('offdayadd').destroy();
            //Ext.getCmp('offdaydel').destroy();


        }

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

    /*var multidate = emp_comp.down('toolbar').up('window').down('#datePicker');
    var picker = multidate.createPicker();
    form.getForm().findField('driversPmtOffDays').setValue(record.get('driversPmtOffDays'));
    var store1 = Ext.StoreMgr.lookup('company.DriversPmt.OffDays');
    store1.getProxy().setUrl(record.get('driversPmtOffDays'));
    store1.load({
        callback: function(records, operation, success) {
            for (var i = 0; i < records.length; i++) {
                var newoff = Ext.create('widget.companydriverspmtformoffday', {});
                newoff.items.getAt(0).items.getAt(0).setValue(parseInt(i+1) + ". ");
                newoff.items.getAt(1).items.getAt(0).setValue(records[i].get('week'));
                newoff.items.getAt(1).items.getAt(1).setValue(form.timestampToDate(records[i].get('day')));
                Ext.getCmp('offdays').add(newoff);
                //picker.selectedDates[records[i].get('day')] = records[i].get('day');
                //picker.higlighDates();
            }
            form.getForm().findField('offdaysnum').setValue(parseInt(records.length));

            if(record.get('subStatus')===2){

                fields = form.getForm().getFields();
                fields.each(function (field) {
                    field.enable();
                field.setReadOnly (true);});
            }
            emp_comp.show();
        }
    });*/

                form.getForm().findField('driversPmtOffDays').setValue(record.get('driversPmtOffDays'));
                /*var month = form.getForm().findField('pmtMonth').getValue();
                var year = form.getForm().findField('pmtYear').getValue();*/
                var picker = Ext.getCmp('mutliDatePicker');
                var tip = Ext.getElementById('multiDatePickerTip');
                if (picker != null) {
                    /*picker.minDate = new Date(year+'-'+month+'-01');
                    picker.maxDate = new Date(year+'-'+(month+1)+'-01');
                    picker.setValue(picker.minDate);
                    picker.setDisabled(false);
                    tip.hidden = true;*/
                    var store1 = Ext.StoreMgr.lookup('company.DriversPmt.OffDays');
                    store1.getProxy().setUrl(record.get('driversPmtOffDays'));
                    store1.load({
                        callback: function(records, operation, success) {
                            for (var i = 0; i < records.length; i++) {
                                /*var newoff = Ext.create('widget.companydriverspmtformoffday', {});
                                 newoff.items.getAt(0).items.getAt(0).setValue(parseInt(i+1) + ". ");
                                 newoff.items.getAt(1).items.getAt(0).setValue(records[i].get('week'));
                                 newoff.items.getAt(1).items.getAt(1).setValue(form.timestampToDate(records[i].get('day')));
                                 Ext.getCmp('offdays').add(newoff);*/
                                picker.selectedDates[new Date(records[i].get('day')).toDateString()] = new Date(records[i].get('day'));
                            }
                            picker.higlighDates();
                            form.getForm().findField('offdaysnum').setValue(parseInt(records.length));
                        }
                    });
                    //picker.setDisabled(true);
                }
                
                if(record.get('subStatus')===2){

                    fields = form.getForm().getFields();
                    fields.each(function (field) {
                        field.enable();
                        field.setReadOnly (true);});
                }
                emp_comp.show();
            },
            flex: 1,
            autoScroll: true,
            id: 'companydriverspmtgrid',
            itemId: 'companydriverspmtgrid',
            title: 'Βιβλιάρια Εργασίας (ρεπό) οδηγών τουριστικών λεωφορείων ',
            autoLoad: true,
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.DriversPmt.DRIVERS_PMT_GRID',
            columns: [
                {
                    xtype: 'rownumberer'
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        return value+" "+record.get('pmtMonth')+"/"+record.get('pmtYear');
                    },
                    sortable: false,
                    dataIndex: 'driversNameSurname',
                    text: 'Οδηγός - Μήνας/Έτος',
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
                    store: 'company.DriversPmt.DRIVERS_PMT_GRID'
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