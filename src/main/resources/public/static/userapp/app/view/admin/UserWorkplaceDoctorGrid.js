/*
 * File: app/view/employee/ComplaintsGrid.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.admin.UserWorkplaceDoctorGrid', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.adminuserworkplacedoctorgrid',

    requires: [
        'MyApp.view.admin.UserWorkplaceDoctorGridViewModel',
        'MyApp.view.admin.UserWorkplaceDoctorGridViewController',
        'Ext.grid.column.RowNumberer',
        'Ext.grid.filters.filter.String',
        'Ext.grid.View',
        'Ext.toolbar.Paging',
        'Ext.form.field.Text',
        'Ext.form.trigger.Trigger',
        'Ext.grid.filters.Filters',
        'Ext.grid.column.Action',
        'Ext.saki.grid.MultiSearch'
    ],

    controller: 'adminuserworkplacedoctorgrid',
    viewModel: {
        type: 'adminuserworkplacedoctorgrid'
    },
    autoScroll: true,
    id: 'adminuserworkplacedoctorgrid',
    itemId: 'adminuserworkplacedoctorgrid',
    title: 'Λίστα Χρηστών',
    autoLoad: false,
    columnLines: false,
    reserveScrollbar: true,
    scroll: 'vertical',
    store: 'admin.USER_WORKPLACE_DOCTOR_GRID',
    defaultListenerScope: true,

    columns: [
        {
            xtype: 'rownumberer'
        },
        {
            xtype: 'gridcolumn',
            filterField: true,
            sortable: false,
            dataIndex: 'id',
            text: 'id',
            flex: 17
        },
        /*{
         xtype: 'gridcolumn',
         filterField: true,
         sortable: false,
         dataIndex: 'isAdmin',
         text: 'isAdmin',
         flex: 17
         },
         {
         xtype: 'gridcolumn',
         filterField: true,
         sortable: false,
         dataIndex: 'role',
         text: 'role',
         flex: 17
         },
         {
         xtype: 'gridcolumn',
         filterField: true,
         sortable: false,
         dataIndex: 'status',
         text: 'status',
         flex: 17
         },*/
        {
            xtype: 'gridcolumn',
            filterField: true,
            sortable: false,
            dataIndex: 'username',
            text: 'username',
            flex: 17
        },
        {
            xtype: 'gridcolumn',
            filterField: true,
            sortable: false,
            dataIndex: 'email',
            text: 'Email',
            flex: 14,
            editor: {
                allowBlank: false
            }
        },
        /*{
         xtype: 'gridcolumn',
         filterField: true,
         sortable: false,
         dataIndex: 'emailNotifEn',
         text: 'emailNotifEn',
         flex: 17
         },*/
        {
            xtype: 'gridcolumn',
            filterField: true,
            sortable: false,
            dataIndex: 'firstname',
            text: 'firstname',
            flex: 14
        },
        {
            xtype: 'gridcolumn',
            filterField: true,
            sortable: false,
            dataIndex: 'lastname',
            text: 'lastname',
            flex: 17
        },
        {
            xtype: 'gridcolumn',
            filterField: true,
            sortable: false,
            dataIndex: 'phone',
            text: 'phone',
            flex: 17
        },
        {
            xtype: 'gridcolumn',
            filterField: true,
            sortable: false,
            dataIndex: 'mobile',
            text: 'mobile',
            flex: 17
        },
        {
            xtype: 'gridcolumn',
            filterField: true,
            sortable: false,
            dataIndex: 'addr',
            text: 'addr',
            flex: 17
        },
        {
            xtype: 'gridcolumn',
            filterField: true,
            sortable: false,
            dataIndex: 'addrTk',
            text: 'addrTk',
            flex: 17
        },
        {
            xtype: 'gridcolumn',
            filterField: true,
            sortable: false,
            dataIndex: 'fax',
            text: 'fax',
            flex: 17
        },
        /*{
         xtype: 'gridcolumn',
         filterField: true,
         sortable: false,
         dataIndex: 'isExypp',
         text: 'isExypp',
         flex: 17
         },*/
        {
            xtype: 'gridcolumn',
            filterField: true,
            sortable: false,
            dataIndex: 'afm',
            text: 'afm',
            flex: 17
        },
        {
            xtype: 'gridcolumn',
            filterField: true,
            sortable: false,
            dataIndex: 'cardType',
            text: 'cardType',
            flex: 17
        },
        {
            xtype: 'gridcolumn',
            filterField: true,
            sortable: false,
            dataIndex: 'cardNumber',
            text: 'cardNumber',
            flex: 17
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
            store: 'admin.USER_WORKPLACE_DOCTOR_GRID'
        },
        {
            xtype: 'toolbar',
            dock: 'top',
            items: [
                {
                    xtype: 'textfield',
                    name: 'usernameSearch',
                    emptyText: 'Username',
                    triggers: {
                        mytrigger: {
                            cls: 'x-form-search-trigger'
                        }
                    }
                },
                {
                    xtype: 'textfield',
                    name: 'afmSearch',
                    emptyText: 'ΑΦΜ',
                    triggers: {
                        mytrigger: {
                            cls: 'x-form-search-trigger'
                        }
                    }
                },
                {
                    xtype: 'textfield',
                    name: 'emailSearch',
                    emptyText: 'Email',
                    triggers: {
                        mytrigger: {
                            cls: 'x-form-search-trigger'
                        }
                    }
                },
                {
                    xtype: 'button',
                    name: 'search',
                    height: 26,
                    width: 125,
                    text: 'Αναζήτηση',
                    handler: function(dataview, eOpts) {
                        var me = this;
                        var username = dataview.up().items.items[0].value.toUpperCase();
                        var afm = dataview.up().items.items[1].value;
                        var email = dataview.up().items.items[2].value;

                        if (username=='' && afm=='' && email=='') {
                            Ext.Msg.alert('Αποτυχία Αναζήτησης', 'Συμπληρώστε τουλάχιστον ένα από τα φίλτρα αναζήτησης.');
                        } else {

                            var store = dataview.up().up().getStore();
                            store.getProxy().setUrl('/SpPtlVUserWorkplaceDoctor/search/findUsersByCriteria');
                            store.getProxy().setExtraParams({"username":username, "afm":afm, "email":email, "size": 100, "page": 0});
                            store.load({callback: function(records, operation, success) {
                                console.log('test2');
                            }});
                        }
                        console.log('test');
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
                url: "/userExtraInfo/editUserWorkplaceDoctor",
                method: 'POST',
                params: {
                    id: e.context.record.data.id,
                    email: e.context.record.data.email
                },
                success: function (dataview, eOpts) {
                    if (Ext.JSON.decode(dataview.responseText).success) {
                        Ext.Msg.alert('Επιτυχής Αποθήκευση', 'Οι αλλαγές σας αποθηκεύθηκαν με επιτυχία.');
                    } else {
                        Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Οι αλλαγές σας δεν αποθηκεύθηκαν από το σύστημα');
                    }
                    me.getStore().load();
                },
                failure: function (dataview, eOpts) {
                    Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Οι αλλαγές σας δεν αποθηκεύθηκαν από το σύστημα');
                    me.getStore().load();
                },
                exception: function (dataview, eOpts) {
                    Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Οι αλλαγές σας δεν αποθηκεύθηκαν από το σύστημα');
                    me.getStore().load();
                }
            });
        }
    },

    onViewRefresh: function(dataview, eOpts) {
        if (dataview.store.data.length<1 && dataview.store.filters.length>0){
            Ext.addon.MessagePop.msg("Προσοχή!", "Δεν υπάρχουν εγγραφές που να αντιστοιχούν στην αναζήτηση σας. Προβάλονται όλες οι εγγραφές της σελίδας.", 5000);
            dataview.up('grid').plugins[1].clearValues();
            dataview.store.reload();
        }
    },

});