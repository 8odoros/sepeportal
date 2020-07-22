/*
 * File: app/view/doctor/DiplomasPanel.js
 *
 * Created by Dimitris F.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.doctor.DiplomasPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.doctordiplomaspanel',

    requires: [
        'MyApp.view.doctor.DiplomasPanelViewModel',
        'MyApp.view.doctor.DiplomasPanelViewController',
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

    controller: 'doctordiplomaspanel',
    viewModel: {
        type: 'doctordiplomaspanel'
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
            title: 'Τίτλοι Ιατρικής Ειδικότητας',
            autoLoad: true,
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'vertical',
            store: 'doctor.DIPLOMAS_GRID',
            columns: [
                {
                    xtype: 'rownumberer'
                },
                {
                    xtype: 'gridcolumn',
                    sortable: false,
                    dataIndex: 'speciality',
                    text: 'Ειδικότητα',
                    flex: 16,
                    editor: {
                        allowBlank: false
                    }
                },
                /*{
                    xtype: 'gridcolumn',
                    sortable: false,
                    dataIndex: 'diplomaDescr',
                    text: 'Περιγραφή Τίτλου Ιατρικής Ειδικότητας',
                    flex: 8,
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
                    flex: 8
                },
                {
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
                                        url: "/doctorExtraInfo/deleteDoctorDiploma",
                                        method: 'POST',
                                        params: {
                                            id: record.get('abbr')
                                        },
                                        success: function (dataview, eOpts) {
                                            if (Ext.JSON.decode(dataview.responseText).success) {
                                                Ext.Msg.alert('Επιτυχής Αποθήκευση', 'Ο τίτλος ιατρικής ειδικότητας σας διαγράφηκε με επιτυχία.');
                                            } else {
                                                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Ο τίτλος ιατρικής ειδικότητας σας δεν διαγράφηκε από το σύστημα.');
                                            }
                                            Ext.getCmp('diplomas').getStore().load();
                                        },
                                        failure: function (dataview, eOpts) {
                                            Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Ο τίτλος ιατρικής ειδικότητας σας δεν διαγράφηκε από το σύστημα.');
                                            Ext.getCmp('diplomas').getStore().load();
                                        },
                                        exception: function (dataview, eOpts) {
                                            Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Ο τίτλος ιατρικής ειδικότητας σας δεν διαγράφηκε από το σύστημα.');
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
                    store: 'doctor.DIPLOMAS_GRID'
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
                            text: 'Προσθήκη Τίτλου Ιατρικής Ειδικότητας',
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
                /*{
                    ptype: 'rowediting',
                    clicksToMoveEditor: 1,
                    autoCancel: false
                }*/
            ],
            listeners: {
                /*edit: function (e) {
                    var me = this;
                    Ext.Ajax.request({
                        url: "/doctorExtraInfo/editDiploma",
                        method: 'POST',
                         params: {
                             speciality: e.context.record.data.speciality,
                             id: e.context.record.data.abbr,
                             attachedDocId: e.context.record.data.attachedDocId,
                             userId:e.context.record.data.userId
                         },
                        success: function (dataview, eOpts) {
                            if (Ext.JSON.decode(dataview.responseText).success) {
                                Ext.Msg.alert('Επιτυχής Αποθήκευση', 'Ο τίτλος ιατρικής ειδικότητας σας αποθηκεύθηκε με επιτυχία.');
                            } else {
                                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Ο τίτλος ιατρικής ειδικότητας σας δεν αποθηκεύθηκε από το σύστημα.');
                            }
                            me.getStore().load();
                        },
                        failure: function (dataview, eOpts) {
                            Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Ο τίτλος ιατρικής ειδικότητας σας δεν αποθηκεύθηκε από το σύστημα.');
                            me.getStore().load();
                        },
                        exception: function (dataview, eOpts) {
                            Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Ο τίτλος ιατρικής ειδικότητας σας δεν αποθηκεύθηκε από το σύστημα.');
                            me.getStore().load();
                        }
                    });
                }*/
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
        var emp_comp = Ext.create('widget.doctordiplomaform', {
        });
        emp_comp.show();
    }

});