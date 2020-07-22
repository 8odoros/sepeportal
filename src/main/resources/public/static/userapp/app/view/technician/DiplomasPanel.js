/*
 * File: app/view/technician/DiplomasPanel.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.technician.DiplomasPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.techniciandiplomaspanel',

    requires: [
        'MyApp.view.technician.DiplomasPanelViewModel',
        'MyApp.view.technician.DiplomasPanelViewController',
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

    controller: 'techniciandiplomaspanel',
    viewModel: {
        type: 'techniciandiplomaspanel'
    },
    id: 'diplomaspanel',
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
            itemId: 'diplomas',
            id: 'diplomas',
            title: 'Τίτλοι Σπουδών',
            autoLoad: true,
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'technician.DIPLOMAS_GRID',
            columns: [
                {
                    xtype: 'rownumberer'
                },
                {
                    xtype: 'gridcolumn',
                    sortable: false,
                    dataIndex: 'diplomaDescr',
                    text: 'Περιγραφή Τίτλου Σπουδών',
                    flex: 2,
                    editor: {
                        // defaults to textfield if no xtype is supplied
                        allowBlank: false
                    }
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
                    dataIndex: 'diplomaYear',
                    text: 'Ημερομηνία Λήψης',
                    flex: 14
                },
                /*{
                    xtype: 'gridcolumn',
                    filterField: true,
                    sortable: false,
                    dataIndex: 'diplomaYear',
                    text: 'Χρονολογία Λήψης',
                    flex: 1
                },*/
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
                            getClass: function (v, metadata, r, rowIndex, colIndex, store) {
                                if (v !== -1) { //will need fix
                                    return 'attachment';
                                }
                            },
                            handler: function (view, rowIndex, colIndex, item, e, record, row) {
                                if (record.get('attachedDocId') !== -1) {
                                    var url = "/getDocument?docId=" + record.get('attachedDocId');
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
                                        url: "/technicianExtraInfo/deleteDiploma",
                                        method: 'POST',
                                        params: {
                                            id: record.get('abbr')
                                        },
                                        success: function (dataview, eOpts) {
                                            if (Ext.JSON.decode(dataview.responseText).success) {
                                                Ext.Msg.alert('Επιτυχής Αποθήκευση', 'Ο τίτλος σπουδών σας διαγράφηκε με επιτυχία.');
                                            } else {
                                                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Ο τίτλος σπουδών σας δεν διαγράφηκε από το σύστημα.');
                                            }
                                            Ext.getCmp('diplomas').getStore().load();
                                        },
                                        failure: function (dataview, eOpts) {
                                            Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Ο τίτλος σπουδών σας δεν διαγράφηκε από το σύστημα.');
                                            Ext.getCmp('diplomas').getStore().load();
                                        },
                                        exception: function (dataview, eOpts) {
                                            Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Ο τίτλος σπουδών σας δεν διαγράφηκε από το σύστημα.');
                                            Ext.getCmp('diplomas').getStore().load();
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
                    store: 'technician.DIPLOMAS_GRID'
                },
                {
                    xtype: 'toolbar',
                    dock: 'top',
                    items: [
                        {
                            xtype: 'button',
                            height: 35,
                            margin: '10 10',
                            maxWidth: 350,
                            glyph: 'xf067@FontAwesome',
                            text: 'Προσθήκη Τίτλου Σπουδών',
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
                },
                {
                    ptype: 'rowediting',
                    clicksToMoveEditor: 1,
                    autoCancel: false
                }
            ],
            listeners: {
                edit: function (e) {
                    var me = this;
                    Ext.Ajax.request({
                        url: "/technicianExtraInfo/editDiploma",
                        method: 'POST',
                         params: {
                             diplomaDescr: e.context.record.data.diplomaDescr,
                             diplomaYear: e.context.record.data.diplomaYear,
                             id: e.context.record.data.abbr,
                             attachedDocId: e.context.record.data.attachedDocId,
                             userId:e.context.record.data.userId
                         },
                        success: function (dataview, eOpts) {
                            if (Ext.JSON.decode(dataview.responseText).success) {
                                Ext.Msg.alert('Επιτυχής Αποθήκευση', 'Ο τίτλος σπουδών σας αποθηκεύθηκε με επιτυχία.');
                            } else {
                                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Ο τίτλος σπουδών σας δεν αποθηκεύθηκε από το σύστημα.');
                            }
                            me.getStore().load();
                        },
                        failure: function (dataview, eOpts) {
                            Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Ο τίτλος σπουδών σας δεν αποθηκεύθηκε από το σύστημα.');
                            me.getStore().load();
                        },
                        exception: function (dataview, eOpts) {
                            Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Ο τίτλος σπουδών σας δεν αποθηκεύθηκε από το σύστημα.');
                            me.getStore().load();
                        }
                    });
                }
            },
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
        var emp_comp = Ext.create('widget.techniciandiplomaform', {
        });
        emp_comp.show();
    }

});