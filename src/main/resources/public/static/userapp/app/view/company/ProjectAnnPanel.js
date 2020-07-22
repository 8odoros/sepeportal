/*
 * File: app/view/company/ProjectAnnPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.ProjectAnnPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.companyprojectannpanel',

    requires: [
        'MyApp.view.company.ProjectAnnPanelViewModel',
        'MyApp.view.company.ProjectAnnPanelViewController',
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

    controller: 'companyprojectannpanel',
    viewModel: {
        type: 'companyprojectannpanel'
    },
    id: 'projectanncompany',
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'gridpanel',
            icon_dbl_click_handler: function(record) {
                var emp_comp = Ext.create('widget.companyprojectannformprojectannform', {});
                emp_comp.down('toolbar').getComponent('deletebutton').show();

                var form = emp_comp.down('form');
                var fields = form.getForm().getFields();

                form.getForm().findField('a_new_form').setValue(false);

                form.loadRecord(record);


                if(record.get('subStatus')===1){
                    form.getForm().findField('protNoview').setValue("-");
                    if (record.get('projAddrPe')!==null && record.get('projAddrD')===null){
                        form.getForm().findField('projAddrPe').setDisabled(false);
                        form.getForm().findField('projAddrD').setDisabled(false);
                    }
                    else if (record.get('projAddrPe')!==null && record.get('projAddrD')!==null){
                        form.getForm().findField('projAddrPe').setDisabled(false);
                        form.getForm().findField('projAddrD').setDisabled(false);
                        form.getForm().findField('projAddrK').setDisabled(false);
                    }
                    else if (record.get('projAddrP')!==null && record.get('projAddrPe')===null){
                        form.getForm().findField('projAddrPe').setDisabled(false);
                    }

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
                else{

                    form.getForm().findField('protNoview').setValue(record.get('protNo'));
                    Ext.getCmp('compprojectann_save_submit_toolbar').hide();

                    form.getForm().findField('file').hide();

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


            if (record.get('attachedDocId')!==-1){
                var vfc = Ext.ComponentQuery.query('#Exist_File')[0];
                vfc.items.getAt(1).hide();
                vfc.show();
            }

            Ext.getCmp('projsmanagersadd').destroy();
            Ext.getCmp('projsmanagersdel').destroy();
            Ext.getCmp('projscontrsadd').destroy();
            Ext.getCmp('projscontrsdel').destroy();
            Ext.getCmp('projsstudiersadd').destroy();
            Ext.getCmp('projsstudiersdel').destroy();
            Ext.getCmp('projsselfemplsadd').destroy();
            Ext.getCmp('projsselfemplsdel').destroy();
            Ext.getCmp('projsengs1add').destroy();
            Ext.getCmp('projsengs1del').destroy();
            Ext.getCmp('projsengs2add').destroy();
            Ext.getCmp('projsengs2del').destroy();
            Ext.getCmp('projsengs3add').destroy();
            Ext.getCmp('projsengs3del').destroy();
        }


        if(record.get('projStartDate')!==null)
        form.getForm().findField('projStartDateView').setValue( form.timestampToDate(record.get('projStartDate')) );


        var store1 = Ext.StoreMgr.lookup( 'company.ProjectAnnouncments.Studier' );
        store1.getProxy().setUrl(record.get('projAnnStudiers'));
        store1.load( { callback : function(records, operation, success) {
            for (var i=0; i<records.length; i++){
                if(records[i].get('type')===2){
                    var projstudier = Ext.create('widget.companyprojectannformmng_studier_selfempl', {});
                    projstudier.down().items.get(4).setValue("2");
                    projstudier.down().items.get(0).setValue(records[i].get('firstname'));
                    projstudier.down().items.get(1).setValue(records[i].get('lastname'));
                    projstudier.down().items.get(2).setValue(records[i].get('afm'));
                    projstudier.down().items.get(3).setValue(records[i].get('addr'));
                    if(parseInt(form.getForm().findField('projsstudiersnum').getValue())>1){
                        projstudier.down().items.get(0).hideLabel=true;
                        projstudier.down().items.get(1).hideLabel=true;
                        projstudier.down().items.get(2).hideLabel=true;
                        projstudier.down().items.get(3).hideLabel=true;
                    }
                    Ext.getCmp('projsstudiers').add(projstudier);
                    form.getForm().findField('projsstudiersnum').setValue(parseInt(form.getForm().findField('projsstudiersnum').getValue())+1);
                }
                else if(records[i].get('type')===1){
                    var projmanager = Ext.create('widget.companyprojectannformmng_studier_selfempl', {});
                    projmanager.down().items.get(4).setValue("1");
                    projmanager.down().items.get(0).setValue(records[i].get('firstname'));
                    projmanager.down().items.get(1).setValue(records[i].get('lastname'));
                    projmanager.down().items.get(2).setValue(records[i].get('afm'));
                    projmanager.down().items.get(3).setValue(records[i].get('addr'));
                    if(parseInt(form.getForm().findField('projsmanagersnum').getValue())>1){
                        projmanager.down().items.get(0).hideLabel=true;
                        projmanager.down().items.get(1).hideLabel=true;
                        projmanager.down().items.get(2).hideLabel=true;
                        projmanager.down().items.get(3).hideLabel=true;
                    }
                    Ext.getCmp('projsmanagers').add(projmanager);
                    form.getForm().findField('projsmanagersnum').setValue(parseInt(form.getForm().findField('projsmanagersnum').getValue())+1);
                }
            }
            form.getForm().findField('projsstudiersnum').setValue(parseInt(form.getForm().findField('projsstudiersnum').getValue())-1);
            form.getForm().findField('projsmanagersnum').setValue(parseInt(form.getForm().findField('projsmanagersnum').getValue())-1);
            store1 = Ext.StoreMgr.lookup( 'company.ProjectAnnouncments.Contr' );
            store1.getProxy().setUrl(record.get('projAnnContrs'));
            store1.load( { callback : function(records, operation, success) {
                for (var i=0; i<records.length; i++){
                    var projscontr = Ext.create('widget.companyprojectannformcontr', {});
                    projscontr.items.getAt(0).items.getAt(0).setValue(i+1+". ");
                    projscontr.items.getAt(1).items.getAt(0).items.getAt(0).setValue(records[i].get('firstname'));
                    projscontr.items.getAt(1).items.getAt(0).items.getAt(1).setValue(records[i].get('lastname'));
                    projscontr.items.getAt(1).items.getAt(0).items.getAt(2).setValue(records[i].get('addr'));
                    projscontr.items.getAt(1).items.getAt(0).items.getAt(3).setValue(records[i].get('phone'));
                    projscontr.items.getAt(1).items.getAt(0).items.getAt(4).setValue(records[i].get('afm'));
                    projscontr.items.getAt(1).items.getAt(0).items.getAt(5).setValue(records[i].get('type'));
                    Ext.getCmp('projscontrs').add(projscontr);
                    form.getForm().findField('projscontrsnum').setValue(parseInt(i+1));
                }
                form.getForm().findField('projscontrsnum').setValue(parseInt(records.length));
                store1 = Ext.StoreMgr.lookup( 'company.ProjectAnnouncments.SelfEmpl' );
                store1.getProxy().setUrl(record.get('projAnnSelfempls'));
                store1.load( { callback : function(records, operation, success) {
                    for (var i=0; i<records.length; i++){
                        var projself = Ext.create('widget.companyprojectannformmng_studier_selfempl', {});
                        projself.down().items.get(4).setValue("3"); //does not matter
                        projself.down().items.get(0).setValue(records[i].get('firstname'));
                        projself.down().items.get(1).setValue(records[i].get('lastname'));
                        projself.down().items.get(2).setValue(records[i].get('afm'));
                        projself.down().items.get(3).setValue(records[i].get('addr'));
                        if(parseInt(form.getForm().findField('projsselfemplsnum').getValue())>1){
                            projself.down().items.get(0).hideLabel=true;
                            projself.down().items.get(1).hideLabel=true;
                            projself.down().items.get(2).hideLabel=true;
                            projself.down().items.get(3).hideLabel=true;
                        }
                        projself.down().items.get(0).allowOnlyWhitespace=true;
                        projself.down().items.get(1).allowOnlyWhitespace=true;
                        projself.down().items.get(2).allowOnlyWhitespace=true;
                        projself.down().items.get(3).allowOnlyWhitespace=true;
                        projself.down().items.get(0).allowBlank=true;
                        projself.down().items.get(1).allowBlank=true;
                        projself.down().items.get(2).allowBlank=true;
                        projself.down().items.get(3).allowBlank=true;
                        Ext.getCmp('projsselfempls').add(projself);
                        form.getForm().findField('projsselfemplsnum').setValue(parseInt(form.getForm().findField('projsselfemplsnum').getValue())+1);
                    }
                    form.getForm().findField('projsselfemplsnum').setValue(parseInt(form.getForm().findField('projsselfemplsnum').getValue())-1);
                    store1 = Ext.StoreMgr.lookup( 'company.ProjectAnnouncments.Eng' );
                    store1.getProxy().setUrl(record.get('projAnnEngs'));
                    store1.load( { callback : function(records, operation, success) {
                        for (var i=0; i<records.length; i++){
                            if(records[i].get('invlolvementType')===1){
                                var projeng = Ext.create('widget.companyprojectannformeng', {});
                                projeng.items.getAt(0).items.getAt(0).setValue(form.getForm().findField('projsengs1num').getValue()+". ");
                                projeng.items.getAt(1).items.getAt(0).items.getAt(6).setValue("1");
                                projeng.items.getAt(1).items.getAt(0).items.getAt(0).setValue(records[i].get('teeNum'));
                                projeng.items.getAt(1).items.getAt(0).items.getAt(1).setValue(records[i].get('firstname'));
                                projeng.items.getAt(1).items.getAt(0).items.getAt(2).setValue(records[i].get('lastname'));
                                projeng.items.getAt(1).items.getAt(0).items.getAt(3).setValue(records[i].get('addr'));
                                projeng.items.getAt(1).items.getAt(0).items.getAt(4).setValue(records[i].get('afm'));
                                projeng.items.getAt(1).items.getAt(0).items.getAt(5).setValue(records[i].get('engineerSpeciality'));
                                Ext.getCmp('projsengs1').add(projeng);
                                form.getForm().findField('projsengs1num').setValue(parseInt(form.getForm().findField('projsengs1num').getValue())+1);
                            }
                            else if(records[i].get('invlolvementType')===2){
                                var projeng = Ext.create('widget.companyprojectannformeng', {});
                                projeng.items.getAt(0).items.getAt(0).setValue(form.getForm().findField('projsengs2num').getValue()+". ");
                                projeng.items.getAt(1).items.getAt(0).items.getAt(6).setValue("2");
                                projeng.items.getAt(1).items.getAt(0).items.getAt(0).setValue(records[i].get('teeNum'));
                                projeng.items.getAt(1).items.getAt(0).items.getAt(1).setValue(records[i].get('firstname'));
                                projeng.items.getAt(1).items.getAt(0).items.getAt(2).setValue(records[i].get('lastname'));
                                projeng.items.getAt(1).items.getAt(0).items.getAt(3).setValue(records[i].get('addr'));
                                projeng.items.getAt(1).items.getAt(0).items.getAt(4).setValue(records[i].get('afm'));
                                projeng.items.getAt(1).items.getAt(0).items.getAt(5).setValue(records[i].get('engineerSpeciality'));
                                Ext.getCmp('projsengs2').add(projeng);
                                form.getForm().findField('projsengs2num').setValue(parseInt(form.getForm().findField('projsengs2num').getValue())+1);
                            }
                            else if(records[i].get('invlolvementType')===3){
                                var projeng = Ext.create('widget.companyprojectannformeng', {});
                                projeng.items.getAt(0).items.getAt(0).setValue(form.getForm().findField('projsengs3num').getValue()+". ");
                                projeng.items.getAt(1).items.getAt(0).items.getAt(6).setValue("3");
                                projeng.items.getAt(1).items.getAt(0).items.getAt(0).setValue(records[i].get('teeNum'));
                                projeng.items.getAt(1).items.getAt(0).items.getAt(1).setValue(records[i].get('firstname'));
                                projeng.items.getAt(1).items.getAt(0).items.getAt(2).setValue(records[i].get('lastname'));
                                projeng.items.getAt(1).items.getAt(0).items.getAt(3).setValue(records[i].get('addr'));
                                projeng.items.getAt(1).items.getAt(0).items.getAt(4).setValue(records[i].get('afm'));
                                projeng.items.getAt(1).items.getAt(0).items.getAt(5).setValue(records[i].get('engineerSpeciality'));
                                Ext.getCmp('projsengs3').add(projeng);
                                form.getForm().findField('projsengs3num').setValue(parseInt(form.getForm().findField('projsengs3num').getValue())+1);
                            }
                        }
                        form.getForm().findField('projsengs1num').setValue(parseInt(form.getForm().findField('projsengs1num').getValue())-1);
                        form.getForm().findField('projsengs2num').setValue(parseInt(form.getForm().findField('projsengs2num').getValue())-1);
                        form.getForm().findField('projsengs3num').setValue(parseInt(form.getForm().findField('projsengs3num').getValue())-1);

                        if(record.get('subStatus')===2){

                            fields = form.getForm().getFields();
                            fields.each(function (field) {
                                field.enable();
                                field.setReadOnly (true);
                            });

                        }
                        emp_comp.show();


                    } });
                } });
            } });
        }  });




            },
            flex: 1,
            autoScroll: true,
            id: 'companyprojectann',
            itemId: 'companyprojectann',
            title: '"Εκ των προτέρων Γνωστοποιήσεις" Έναρξης Οικοδομικών Εργασιών',
            autoLoad: true,
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.ProjectAnnouncments.Anns',
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
                    itemdblclick: 'onCompProjectAnnItemDblClick'
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
                    store: 'company.ProjectAnnouncments.Anns'
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

    onCompProjectAnnItemDblClick: function(dataview, record, item, index, e, eOpts) {
        dataview.up().icon_dbl_click_handler(record);

    }

});