/*
 * File: app/view/company/AccidentPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.AccidentPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.companyaccidentpanel',

    requires: [
        'MyApp.view.company.AccidentPanelViewModel',
        'MyApp.view.company.AccidentPanelViewController',
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

    controller: 'companyaccidentpanel',
    viewModel: {
        type: 'companyaccidentpanel'
    },
    id: 'accidentcompany',
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'gridpanel',
            icon_dbl_click_handler: function(record) {
                var emp_comp = Ext.create('widget.companyaccidentformaccidentform', {});

                var successAns = function(options, success, response) {
                    if (Ext.JSON.decode(response.responseText).success) {

                        var resp =Ext.JSON.decode(response.responseText);
                        emp_comp.down('form').getForm().findField('compFullName').setValue(resp.compFullName);
                        emp_comp.down('form').getForm().findField('compTaxNumber').setValue(resp.compTaxNumber);
                        emp_comp.down('form').getForm().findField('compAme').setValue(resp.compAme);
                        emp_comp.down('form').getForm().findField('compAddr').setValue(resp.compAddr);
                        emp_comp.down('form').getForm().findField('compAddrK').setValue(resp.compAddrK);
                        emp_comp.down('form').getForm().findField('compAddrTk').setValue(resp.compAddrTk);
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

                emp_comp.down('toolbar').getComponent('deleteaccidentbutton').show();

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

                    if (record.get('accidentAddrPe')!==null && record.get('accidentAddrD')===null){
                        form.getForm().findField('accidentAddrPe').setDisabled(false);
                        form.getForm().findField('accidentAddrD').setDisabled(false);
                    }
                    else if (record.get('accidentAddrPe')!==null && record.get('accidentAddrD')!==null){
                        form.getForm().findField('accidentAddrPe').setDisabled(false);
                        form.getForm().findField('accidentAddrD').setDisabled(false);
                        form.getForm().findField('accidentAddrK').setDisabled(false);
                    }
                    else if (record.get('accidentAddrP')!==null && record.get('accidentAddrPe')===null){
                        form.getForm().findField('accidentAddrPe').setDisabled(false);
                    }
                }
                else{

                    form.getForm().findField('protNoview').setValue(record.get('protNo'));
                    Ext.getCmp('compaccident_save_submit_toolbar').hide();

                    form.getForm().findField('file').hide();

                }

                form.getForm().findField('a_new_form').setValue(false);

                form.loadRecord(record);

                if(record.get('empRecruitmentDate')!==null)
                form.getForm().findField('empRecruitmentDateView').setValue( form.timestampToDate(record.get('empRecruitmentDate')) );
                /*if(record.get('empBirthdate')!==null)
                form.getForm().findField('empBirthdateView').setValue( form.timestampToDate(record.get('empBirthdate')) );*/
                if(record.get('accidentDate')!==null)
                form.getForm().findField('accidentDateView').setValue( form.timestampToDate(record.get('accidentDate')) );

                if (record.get('empMaritalStatusCd') == null)
                    form.getForm().findField('empMaritalStatusCd').setValue("");
                else
                    form.getForm().findField('empMaritalStatusCd').setValue(record.get('empMaritalStatusCd').toString());
                if (record.get('empCitizenshipCd') == null)
                    form.getForm().findField('empCitizenshipCd').setValue("");
                else
                    form.getForm().findField('empCitizenshipCd').setValue(record.get('empCitizenshipCd').toString());
                if (record.get('empInsuranceBureauCode') == null)
                    form.getForm().findField('empInsuranceBureauCode').setValue("");
                else
                    form.getForm().findField('empInsuranceBureauCode').setValue(record.get('empInsuranceBureauCode').toString());
                if (record.get('empSexDesc') == null)
                    form.getForm().findField('empSexDesc').setValue("");
                else
                    form.getForm().findField('empSexDesc').setValue(record.get('empSexDesc'));
                if (record.get('trainingFlag') == null)
                    form.getForm().findField('trainingFlag').setValue("");
                else
                    form.getForm().findField('trainingFlag').setValue(record.get('trainingFlag').toString());

                /*form.getForm().findField('empMaritalStatusCd').setValue(record.get('empMaritalStatusCd').toString());
                form.getForm().findField('empCitizenshipCd').setValue(record.get('empCitizenshipCd').toString());
                form.getForm().findField('empInsuranceBureauCode').setValue(record.get('empInsuranceBureauCode').toString());
                form.getForm().findField('empSexDesc').setValue(parseInt(record.get('empSexDesc')));
                form.getForm().findField('trainingFlag').setValue(record.get('trainingFlag').toString());*/

                var empSpecialty = form.getForm().findField('empSpecialty');
                if (record.get('empSpecialty')>0 || record.get('empSpecialty')!==null){
                    empSpecialty.getStore().getProxy().setExtraParams({"descr":""});
                    empSpecialty.getStore().load( { callback : function(records, operation, success) {
                        form.getForm().findField('empSpecialty').setValue(record.get('empSpecialty').toString());
                    }
                    });
                }
                var empCitizenshipCd = form.getForm().findField('empCitizenshipCd');
                if (record.get('empCitizenshipCd')>0 || record.get('empCitizenshipCd')!==null){
                    empCitizenshipCd.getStore().getProxy().setExtraParams({"description":""});
                    empCitizenshipCd.getStore().load( { callback : function(records, operation, success) {
                        form.getForm().findField('empCitizenshipCd').setValue(record.get('empCitizenshipCd').toString());
                    }
                    });
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
                        store2.getProxy().setExtraParam("cdId", record.get('sepeDept'));
                        store2.load( { callback : function(records, operation, success) {
                            var sepeDept = Ext.decode(operation._response.responseText);
                            if (sepeDept !== null)
                                form.getForm().findField('sepeDept').setValue(sepeDept.cdText);
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

            Ext.getCmp('witnessadd').destroy();
            Ext.getCmp('witnessdel').destroy();

        }

        var store1 = Ext.StoreMgr.lookup('company.Accidents.Witnesses');
        store1.getProxy().setUrl(record.get('accidentWitnesses'));
        store1.load({
            callback: function(records, operation, success) {
                for (var i = 0; i < records.length; i++) {
                    var newwitness = Ext.create('widget.companyaccidentformwitness', {});
                    newwitness.items.getAt(0).items.getAt(0).setValue(i+1 + ". ");
                    newwitness.items.getAt(1).items.getAt(0).items.getAt(0).setValue(records[i].get('firstname'));
                    newwitness.items.getAt(1).items.getAt(0).items.getAt(1).setValue(records[i].get('lastname'));
                    newwitness.items.getAt(1).items.getAt(0).items.getAt(2).setValue(records[i].get('addr'));
                    newwitness.items.getAt(1).items.getAt(0).items.getAt(3).setValue(records[i].get('addrTk'));
                    newwitness.items.getAt(1).items.getAt(0).items.getAt(4).setValue(records[i].get('phone'));
                    if (records[i].get('typeId') != null)
                        newwitness.items.getAt(1).items.getAt(0).items.getAt(5).setValue(records[i].get('typeId').toString());
                    else
                        newwitness.items.getAt(1).items.getAt(0).items.getAt(5).setValue("");
                    if (records[i].get('citizenshipCd') != null)
                        newwitness.items.getAt(1).items.getAt(0).items.getAt(6).setValue(records[i].get('citizenshipCd').toString());
                    else
                        newwitness.items.getAt(1).items.getAt(0).items.getAt(6).setValue("");
                    Ext.getCmp('witnesses').add(newwitness);
                    form.getForm().findField('witnessesnum').setValue(parseInt(form.getForm().findField('witnessesnum').getValue()) + 1);
                }
                form.getForm().findField('witnessesnum').setValue(records.length);
                if(record.get('subStatus')===2){

                    fields = form.getForm().getFields();
                    fields.each(function (field) {
                        field.enable();
                    field.setReadOnly (true);});

                }


                emp_comp.show();
            }
        });





            },
            flex: 1,
            autoScroll: true,
            id: 'companyaccidentgrid',
            itemId: 'companyaccidentgrid',
            title: 'Αναγγελίες Εργατικών Ατυχημάτων',
            autoLoad: true,
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.Accidents.ACCIDENTS_GRID',
            columns: [
                {
                    xtype: 'rownumberer'
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        if (value!==null){
                            //var pD = value.replace(/[^0-9]+/g,' ').split(" ");
                            //return (pD[2]+"-"+pD[1]+"-"+pD[0])+"  "+record.get("submitTime");
                            return record.get("accidentDescription");
                        }
                        else
                        return "-";

                    },
                    sortable: false,
                    dataIndex: 'protDate',
                    text: 'Περιγραφή και Αίτια Ατυχήματος',
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
                    dataIndex: 'trainingFlag',
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
                    itemdblclick: 'onCompAccidentItemDblClick'
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
                    store: 'company.Accidents.ACCIDENTS_GRID'
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

    onCompAccidentItemDblClick: function(dataview, record, item, index, e, eOpts) {
        dataview.up().icon_dbl_click_handler(record);

    }

});