/*
 * File: app/view/company/VehiclesBooksPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.VehiclesBooksPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.companyvehiclesbookspanel',

    requires: [
        'MyApp.view.company.VehiclesBooksPanelViewModel',
        'Ext.grid.Panel',
        'Ext.grid.column.RowNumberer',
        'Ext.grid.View',
        'Ext.toolbar.Paging',
        'Ext.grid.filters.Filters',
        'Ext.grid.column.Action',
        'Ext.button.Button',
        'Ext.grid.filters.filter.String'
    ],

    viewModel: {
        type: 'companyvehiclesbookspanel'
    },
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'gridpanel',
            icon_dbl_click_handler: function(record) {
                var emp_comp = Ext.create('widget.companyvehiclesbooksvehiclesform', {});
                emp_comp.down('toolbar').getComponent('deletebutton').show();

                var form = emp_comp.down('form');
                var fields = form.getForm().getFields();

                form.getForm().findField('a_new_form').setValue(false);

                form.loadRecord(record);

                form.getForm().findField('ownerBirthdate').setValue( form.timestampToDate(record.get('ownerBirthdate')) );


                if(record.get('subStatus')===1){
                    form.getForm().findField('protNo').setValue();
                    form.getForm().findField('creationDate').setValue();
                    form.getForm().findField('protDate').setValue();
                    form.getForm().findField('protYear').setValue();
                    form.getForm().findField('submitTime').setValue();


                    form.getForm().findField('compAddrPe').setDisabled(true);
                    form.getForm().findField('compAddrD').setDisabled(true);
                    form.getForm().findField('compAddrK').setDisabled(true);

                }
                else{

                    form.getForm().findField('protDate').setValue( form.timestampToDate(record.get('protDate')) );
                    form.getForm().findField('creationDate').setValue( form.timestampToDate(record.get('protDate')) );
                    Ext.getCmp('compvehicle_save_submit_toolbar').hide();

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


            fields = form.getForm().getFields();
            fields.each(function (field) {
                field.enable();
                field.setReadOnly (true);
            });


        }

        emp_comp.show();




            },
            flex: 1,
            autoScroll: true,
            height: '33%',
            id: 'companyVehiclesBooks_Vehicles',
            itemId: 'companyVehiclesBooks_Vehicles',
            title: 'Ημερολόγια Δρομολογίων Αυτοκινήτων',
            autoLoad: true,
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.VehiclesBooks.Vehicles',
            columns: [
                {
                    xtype: 'rownumberer'
                },
                {
                    xtype: 'gridcolumn',
                    sortable: true,
                    filterField: true,
                    dataIndex: 'vehicleLicenceNum',
                    text: 'Αρ. Κυκλοφορίας Οχήματος',
                    flex: 5,
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {

                        if(record.get('subStatus')===2)   {
                            var pD = value.replace(/[^0-9]+/g,' ').split(" ");
                            return (pD[2]+"-"+pD[1]+"-"+pD[0]);
                        }
                        else
                        return "";

                    },
                    sortable: true,
                    dataIndex: 'protDate',
                    text: 'Ημερομηνία Δημιουργίας',
                    flex: 5
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
                    flex: 5
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        if (record.get('subStatus')===1){
                            return "Αποθηκευμένο";
                        }
                        else
                        return "Καταχωρημένο";
                    },
                    sortable: false,
                    dataIndex: 'subStatus',
                    text: 'Κατάσταση',
                    flex: 5
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
                    itemdblclick: 'onVehiclesItemDblClick',
                    refresh: 'onVehiclesViewRefresh',
                    itemclick: 'onVehiclesViewItemClick',
                    containerclick: 'onVehiclesViewContainerClick'
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
                    store: 'company.VehiclesBooks.Vehicles'
                },
                {
                    xtype: 'toolbar',
                    dock: 'top',
                    items: [
                        {
                            xtype: 'button',
                            glyph: 'xf067@FontAwesome',
                            text: 'Καταχώρηση Νέου Αυτοκινήτου',
                            listeners: {
                                click: 'onNewVehicleBook'
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
                },
            ]
        },
        {
            xtype: 'gridpanel',
            icon_dbl_click_handler: function(record) {
                var emp_comp = Ext.create('widget.companyvehiclesbooksroutesform', {});

                var form = emp_comp.down('form');
                var fields = form.getForm().getFields();

                form.getForm().findField('a_new_form').setValue(false);

                form.loadRecord(record);

                form.getForm().findField('driverFirstname').setReadOnly(true);
                form.getForm().findField('driverLastname').setReadOnly(true);
                form.getForm().findField('driverBirthplace').setReadOnly(true);
                form.getForm().findField('driverBirthdate').setReadOnly(true);
                form.getForm().findField('driverAddr').setReadOnly(true);
                form.getForm().findField('driverCardNumber').setReadOnly(true);
                form.getForm().findField('driverCardIssuingAuth').setReadOnly(true);
                form.getForm().findField('driverAfm').setReadOnly(true);
                form.getForm().findField('driverLicenceNum').setReadOnly(true);
                form.getForm().findField('routeSource').setReadOnly(true);
                form.getForm().findField('activityStartTime').setReadOnly(true);
                form.getForm().findField('activityEndTime').setReadOnly(true);
                form.getForm().findField('jobHours').setReadOnly(true);

                if(record.get('dateCreated')!==null)
                form.getForm().findField('dateCreated').setValue( form.timestampToDate(record.get('dateCreated')) );
                if(record.get('dateUpdated')!==null)
                form.getForm().findField('dateUpdated').setValue( form.timestampToDate(record.get('dateUpdated')) );

                if(Ext.getCmp('companyVehiclesBooks_Vehicles').getSelectionModel().getSelection().length>0){
                    var licnum = Ext.getCmp('companyVehiclesBooks_Vehicles').getSelectionModel().getSelection()[0].get('vehicleLicenceNum');
                    form.getForm().findField('licenceNumber').setValue(licnum);
                    var dateC = Ext.getCmp('companyVehiclesBooks_Vehicles').getSelectionModel().getSelection()[0].get('protDate');
                    form.getForm().findField('createdDate').setValue(form.timestampToDate(dateC));
                }

                if(record.get('driverBirthdate')!==null)
                form.getForm().findField('driverBirthdate').setValue( form.timestampToDate(record.get('driverBirthdate')) );
                if(record.get('assistBirthdate')!==null)
                form.getForm().findField('assistBirthdate').setValue( form.timestampToDate(record.get('assistBirthdate')) );
                emp_comp.show();

            },
            flex: 1,
            autoScroll: true,
            height: '33%',
            id: 'companyVehiclesBooks_Routes',
            itemId: 'companyVehiclesBooks_Routes',
            title: 'Δρομολόγια Αυτοκινήτου',
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'company.VehiclesBooks.Routes',
            columns: [
                {
                    xtype: 'rownumberer'
                },
                {
                    xtype: 'gridcolumn',
                    sortable: false,
                    align: 'center',
                    dataIndex: 'aa',
                    text: 'A/A',
                    flex: 1
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        return value+"  "+record.get("driverLastname");

                    },
                    sortable: true,
                    filterField: true,
                    dataIndex: 'driverFirstname',
                    text: 'Ονοματεπώνυμο Εργαζομένου',
                    flex: 3,
                    filter: {
                        type: 'string',
                        emptyText: 'Εισαγωγή κειμένου...'
                    }
                },
                {
                    xtype: 'gridcolumn',
                    filterField: true,
                    sortable: true,
                    dataIndex: 'routeDestination',
                    text: 'Τόπος Προορισμού',
                    flex: 3
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        var pD = value.replace(/[^0-9]+/g,' ').split(" ");
                        return (pD[2]+"-"+pD[1]+"-"+pD[0])+"/"+record.get('timeUpdated');
                    },
                    sortable: true,
                    dataIndex: 'dateUpdated',
                    text: 'Ημερομηνία/Ωρα Επεξεργασίας',
                    flex: 3
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

                                this.items[0].tooltip = 'Προβολή';
                                return 'editme'; // css for icon

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
                    itemdblclick: 'onRoutesItemDblClick',
                    refresh: 'onRoutesViewRefresh',
                    beforerefresh: 'onRoutesViewBeforeRefresh'
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
                    store: 'company.VehiclesBooks.Routes'
                },
                {
                    xtype: 'toolbar',
                    dock: 'top',
                    items: [
                        {
                            xtype: 'button',
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέο Δρομολόγιο Αυτοκινήτου',
                            listeners: {
                                click: 'onNewRoute'
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
                },
            ]
        }
    ],

    onVehiclesItemDblClick: function(dataview, record, item, index, e, eOpts) {
        dataview.up().icon_dbl_click_handler(record);

    },

    onVehiclesViewRefresh: function(dataview, eOpts) {
        if (dataview.store.data.length<1 && dataview.store.filters.length>0){
            Ext.addon.MessagePop.msg("Προσοχή!", "Δεν υπάρχουν εγγραφές που να αντιστοιχούν στην αναζήτηση σας. Προβάλονται όλες οι εγγραφές της σελίδας.", 5000);
            dataview.up('grid').plugins[1].clearValues();
            dataview.store.reload();
        }
        Ext.getCmp('companyVehiclesBooks_Routes').store.clearData();
        Ext.getCmp('companyVehiclesBooks_Routes').view.refresh();
    },

    onVehiclesViewItemClick: function(dataview, record, item, index, e, eOpts) {
        var routesgrid = Ext.getCmp('companyVehiclesBooks_Routes');
        var vehicleId = Ext.getCmp('companyVehiclesBooks_Vehicles').getSelectionModel().getSelection()[0].get('vehicleId');
        routesgrid.store.proxy.setUrl('/compVehicleBookEntry/search/findByCompVehicleBookId?compVehicleBookId='+vehicleId);
        routesgrid.getStore().reload({
          callback: function(){
            routesgrid.getView().refresh();
          }
        });
    },

    onVehiclesViewContainerClick: function(dataview, e, eOpts) {
        var routesgrid = Ext.getCmp('companyVehiclesBooks_Routes');
        routesgrid.store.clearData();
        routesgrid.getView().refresh();
    },

    onNewVehicleBook: function(button, e, eOpts) {
        var emp_comp = Ext.create('widget.companyvehiclesbooksvehiclesform', {
        });
        emp_comp.show();
    },

    onRoutesItemDblClick: function(dataview, record, item, index, e, eOpts) {
        dataview.up().icon_dbl_click_handler(record);

    },

    onRoutesViewRefresh: function(dataview, eOpts) {
        if (dataview.store.data.length<1 && dataview.store.filters.length>0){
            Ext.addon.MessagePop.msg("Προσοχή!", "Δεν υπάρχουν εγγραφές που να αντιστοιχούν στην αναζήτηση σας. Προβάλονται όλες οι εγγραφές της σελίδας.", 5000);
            dataview.up('grid').plugins[1].clearValues();
            dataview.store.reload();
        }
    },

    onRoutesViewBeforeRefresh: function(dataview, eOpts) {
        if(Ext.getCmp('companyVehiclesBooks_Vehicles').getSelectionModel().getSelection().length>0){
            var vehicleId = Ext.getCmp('companyVehiclesBooks_Vehicles').getSelectionModel().getSelection()[0].get('vehicleId');
            dataview.store.proxy.setUrl('/compVehicleBookEntry/search/findByCompVehicleBookId?compVehicleBookId='+vehicleId);
        }
    },

    onNewRoute: function(button, e, eOpts) {
        if(Ext.getCmp('companyVehiclesBooks_Vehicles').getSelectionModel().getSelection().length>0){

            if(Ext.getCmp('companyVehiclesBooks_Vehicles').getSelectionModel().getSelection()[0].get('subStatus').toString()==="2"){
                var emp_comp = Ext.create('widget.companyvehiclesbooksroutesform', {
                });
                var incNum = parseInt(Ext.getCmp('companyVehiclesBooks_Routes').store.proxy.reader.rawData.page.totalElements)+1;
                emp_comp.down().getForm().findField('aa').setValue(incNum);
                var licnum = Ext.getCmp('companyVehiclesBooks_Vehicles').getSelectionModel().getSelection()[0].get('vehicleLicenceNum');
                emp_comp.down().getForm().findField('licenceNumber').setValue(licnum);
                var dateC = Ext.getCmp('companyVehiclesBooks_Vehicles').getSelectionModel().getSelection()[0].get('protDate');
                emp_comp.down().getForm().findField('createdDate').setValue(emp_comp.down().timestampToDate(dateC));
                var vehicleId = Ext.getCmp('companyVehiclesBooks_Vehicles').getSelectionModel().getSelection()[0].get('vehicleId');
                emp_comp.down().getForm().findField('spPtlCompVehicleBookId').setValue(vehicleId);
                var companyId = Ext.getCmp('companyVehiclesBooks_Vehicles').getSelectionModel().getSelection()[0].get('companyId');
                emp_comp.down().getForm().findField('companyId').setValue(companyId);

                emp_comp.show();
            }
            else{
                Ext.Msg.alert('Προσοχή!', 'Το όχημα είναι αποθηκευμένο. Πρέπει να καταχωρηθεί.');
            }
        }
        else {
                 Ext.Msg.alert('Προσοχή!', 'Δεν έχετε επιλέξει κάποιο όχημα από την παραπάνω λίστα.');
        }


    }

});