/*
 * File: app/view/doctor/RegisteredAssociationsPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.doctor.RegisteredAssociationsPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.doctorregisteredassociationspanel',

    requires: [
        'MyApp.view.doctor.RegisteredAssociationsPanelViewModel',
        'MyApp.view.doctor.RegisteredAssociationsPanelViewController',
        'Ext.grid.Panel',
        'Ext.grid.column.RowNumberer',
        'Ext.grid.column.Action',
        'Ext.grid.View',
        'Ext.toolbar.Paging',
        'Ext.button.Button',
        'Ext.form.field.Text',
        'Ext.form.trigger.Trigger',
        'Ext.grid.filters.Filters'
    ],

    controller: 'doctorregisteredassociationspanel',
    viewModel: {
        type: 'doctorregisteredassociationspanel'
    },
    id: 'registeredassociations',
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'gridpanel',
            flex: 1,
            autoScroll: true,
            itemId: 'registeredassociations',
            title: 'Βεβαιώσεις Ιατρικών Συλλόγων',
            autoLoad: true,
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'doctor.REGASSOC_GRID',
            columns: [
                {
                    xtype: 'rownumberer'
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        if (value!==null){
                            var store2 = Ext.StoreMgr.lookup( 'doctor.MEDICAL_ASSOC' );
                            return store2.findRecord('abbr', record.get('medassocNotifiedId').toString()).get('spMedasDescription');
                        }
                        else
                        return "";
                    },
                    sortable: false,
                    dataIndex: 'medassocNotifiedId',
                    text: 'Ιατρικός Σύλλογος',
                    flex: 1
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        if (value!==null){
                            var store2 = Ext.StoreMgr.lookup( 'doctor.MEDICAL_ASSOC' );
                            return store2.findRecord('abbr', record.get('medassocNotifiedId').toString()).get('spMedasAddress');
                        }
                        else
                        return "";
                    },
                    filterField: true,
                    sortable: false,
                    dataIndex: 'medassocNotifiedId',
                    text: 'Διεύθυνση',
                    flex: 1
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        if (value!==null){
                            var pD = value.replace(/[^0-9]+/g,' ').split(" ");
                            return (pD[2]+"-"+pD[1]+"-"+pD[0]);
                        }
                        else
                        return "";

                    },
                    sortable: false,
                    dataIndex: 'submitDate',
                    text: 'Ημερομηνία Καταχώρησης',
                    flex: 1
                },
                {
                    xtype: 'actioncolumn',
                    disabled: true,
                    resizable: false,
                    enableColumnHide: false,
                    align: 'center',
                    dataIndex: 'docId',
                    hideable: false,
                    menuDisabled: true,
                    text: 'Αρχείο',
                    tooltip: 'Συνημμένο Αρχείο',
                    stopSelection: false,
                    items: [
                        {
                            getClass: function(v, metadata, r, rowIndex, colIndex, store) {
                                if (v!==-1){ //will need fix
                                    return 'attachment';
                                }
                            },
                            handler: function(view, rowIndex, colIndex, item, e, record, row) {
                                if (record.get('attachedDocId')!==-1){
                                    var url = "/getDocument?docId="+record.get('attachedDocId');
                                    var win = window.open(url, '_blank');
                                    win.focus();
                                }
                            }
                        }
                    ]
                },
                {
                    xtype: 'actioncolumn',
                    disabled: true,
                    resizable: false,
                    enableColumnHide: false,
                    align: 'center',
                    //dataIndex: 'docId',
                    hideable: false,
                    menuDisabled: true,
                    text: 'Διαγραφή',
                    tooltip: 'Διαγραφή',
                    stopSelection: false,
                    items: [
                        {
                            getClass: function (v, metadata, r, rowIndex, colIndex, store) {
                                if (v !== -1) { //will need fix
                                    return 'deleteme';
                                }
                            },
                            handler: function (view, rowIndex, colIndex, item, e, record, row) {
                                if (record.get('abbr') !== null) {
                                    Ext.Ajax.request({
                                        url: "/doctorExtraInfo/deleteMedicalAssoc",
                                        method: 'POST',
                                        params: {
                                            id: record.get('abbr')
                                        },
                                        success: function (dataview, eOpts) {
                                            if (Ext.JSON.decode(dataview.responseText).success) {
                                                Ext.Msg.alert('Επιτυχής Αποθήκευση', 'Η βεβαίωση του ιατρικού συλλόγου διαγράφηκε με επιτυχία.');
                                            } else {
                                                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Η βεβαίωση του ιατρικού συλλόγου δεν διαγράφηκε από το σύστημα.');
                                            }
                                            Ext.getCmp('registeredassociations').getComponent('registeredassociations').getStore().load();
                                        },
                                        failure: function (dataview, eOpts) {
                                            Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Η βεβαίωση του ιατρικού συλλόγου δεν διαγράφηκε από το σύστημα.');
                                            Ext.getCmp('registeredassociations').getComponent('registeredassociations').getStore().load();
                                        },
                                        exception: function (dataview, eOpts) {
                                            Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Η βεβαίωση του ιατρικού συλλόγου δεν διαγράφηκε από το σύστημα.');
                                            Ext.getCmp('registeredassociations').getComponent('registeredassociations').getStore().load();
                                        }
                                    });
                                }
                            }
                        }
                    ]
                }
            ],
            viewConfig: {
                frame: true,
                preserveScrollOnRefresh: true,
                listeners: {
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
                    store: 'doctor.REGASSOC_GRID'
                },
                {
                    xtype: 'toolbar',
                    dock: 'top',
                    items: [
                        {
                            xtype: 'button',
                            height: 35,
                            margin: '10 10',
                            maxWidth: 430,
                            glyph: 'xf067@FontAwesome',
                            text: 'Προσθήκη Βεβαίωσης Ιατρικού Συλλόγου στον οποίο είστε εγγεγραμμένος',
                            listeners: {
                                click: 'onButtonClick'
                            }
                        },
                        {
                            xtype: 'textfield',
                            hidden: true,
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

    onViewRefresh: function(dataview, eOpts) {
        if (dataview.store.data.length<1 && dataview.store.filters.length>0){
            Ext.addon.MessagePop.msg("Προσοχή!", "Δεν υπάρχουν εγγραφές που να αντιστοιχούν στην αναζήτηση σας. Προβάλονται όλες οι εγγραφές της σελίδας.", 5000);
            dataview.up('grid').plugins[1].clearValues();
            dataview.store.reload();
        }
    },

    onButtonClick: function(button, e, eOpts) {
        //var view=Ext.getCmp('doctormainView');
        //var center = view.getComponent('contentPanel');
        //center.removeAll();
        var regCount = Ext.getCmp('registeredassociations').getComponent('registeredassociations').getStore().getTotalCount();
        if (regCount > 0)
        {
            Ext.Msg.alert('Μήνυμα Συστήματος', 'Μπορείτε να καταχωρήσετε βεβαίωση από έναν μόνο σύλλογο.');
        }
        else
        {
            var emp_comp = Ext.create('widget.doctorregisteredassociationform', {
            });
            emp_comp.show();
        }
    }

});