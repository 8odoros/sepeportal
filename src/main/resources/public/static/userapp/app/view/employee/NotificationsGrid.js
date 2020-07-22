/*
 * File: app/view/employee/NotificationsGrid.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.employee.NotificationsGrid', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.employeenotificationsgrid',

    requires: [
        'MyApp.view.employee.NotificationsGridViewModel',
        'MyApp.view.employee.NotificationsGridViewController',
        'Ext.grid.column.RowNumberer',
        'Ext.grid.column.Action',
        'Ext.grid.filters.filter.String',
        'Ext.grid.View',
        'Ext.toolbar.Paging',
        'Ext.form.field.Text',
        'Ext.form.trigger.Trigger',
        'Ext.grid.filters.Filters'
    ],

    controller: 'employeenotificationsgrid',
    viewModel: {
        type: 'employeenotificationsgrid'
    },
    autoScroll: true,
    id: 'employeenotificationgrid',
    itemId: 'employeenotificationgrid',
    title: 'Ενημερώσεις',
    autoLoad: true,
    columnLines: false,
    reserveScrollbar: true,
    scroll: 'vertical',
    store: 'employee.NOTIFICATIONS_GRID',
    defaultListenerScope: true,

    columns: [
        {
            xtype: 'rownumberer'
        },
        {
            xtype: 'actioncolumn',
            disabled: true,
            margin: '0 5 0 0',
            resizable: false,
            width: 60,
            enableColumnHide: false,
            align: 'center',
            hideable: false,
            menuDisabled: true,
            tooltip: 'Διαβασμένο/Προτεραιότητα',
            stopSelection: false,
            items: [
                {
                    getClass: function(v, metadata, r, rowIndex, colIndex, store) {
                        if (r.get('viewed')===0){
                            //this.items[0].tooltip = 'Μη αναγνωσμένο';
                            return 'readmenow'; // css for icon
                        }
                        else{
                            //this.items[0].tooltip = 'Αναγνώστηκε';
                            return 'readmebefore'; // css for icon

                        }
                    }
                },
                {
                    disabled: true
                },
                {
                    getClass: function(v, metadata, r, rowIndex, colIndex, store) {
                        if (r.get('priority')===1){
                            // this.items[2].tooltip = 'Χαμηλή Προτεραιότητα';
                            return 'lowpriority'; // css for icon
                        }
                        else if (r.get('priority')===2){
                            //      this.items[2].tooltip = 'Κανονική Προτεραιότητα';
                            return 'normalpriority'; // css for icon
                        }
                        else if (r.get('priority')===3){
                            //    this.items[2].tooltip = 'Υψηλή Προτεραιότητα';
                            return 'highpriority'; // css for icon

                        }
                    }
                }
            ]
        },
        {
            xtype: 'gridcolumn',
            sortable: false,
            dataIndex: 'title',
            text: 'Τίτλος',
            flex: 45,
            filter: {
                type: 'string',
                emptyText: 'Εισαγωγή κειμένου...'
            }
        },
        {
            xtype: 'gridcolumn',
            sortable: false,
            dataIndex: 'sender',
            text: 'Αποστολέας',
            flex: 34
        },
        {
            xtype: 'gridcolumn',
            sortable: false,
            dataIndex: 'dateTimeview',
            text: 'Ημερομηνία - Ώρα',
            flex: 18
        },
        {
            xtype: 'actioncolumn',
            disabled: true,
            resizable: false,
            width: 30,
            enableColumnHide: false,
            align: 'center',
            dataIndex: 'docId',
            hideable: false,
            menuDisabled: true,
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
                        if (record.get('docId')!==-1){
                            var url = "/getDocument?docId="+record.get('docId');
                            var win = window.open(url, '_blank');
                            win.focus();
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
            itemclick: 'onMessageItemClick'
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
            store: 'employee.NOTIFICATIONS_GRID'
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

    onMessageItemClick: function(dataview, record, item, index, e, eOpts) {
        if (record.data.viewed===0){
            var successCallback = function(resp, ops) {
                if (parseInt(resp.responseText)>0){
                    Ext.getCmp('notifbut_employee').setText(resp.responseText+" νέες");
                    Ext.getCmp('notifbut_employee').show();
                }
                else{
                    Ext.getCmp('notifbut_employee').setText();
                    Ext.getCmp('notifbut_employee').hide();
                }
            };
            datatopost=record;
            var mid=record.data.url.split("/");
            datatopost.data.viewed=1;
            delete datatopost.data._links;
            delete datatopost.data.id;
            delete datatopost.data.url;
            Ext.Ajax.request({
               url: '/tNotificationsAccountEntities/'+mid[mid.length-1],
                headers: { 'Content-Type': 'application/json' },
                jsonData: Ext.util.JSON.encode(datatopost.data),
                method: "PUT",
                success: function() {
                    //reload store to update grid
                    dataview.refresh();

                    //reload number of new
                    Ext.Ajax.request({
                         url: 'tNotificationsAccountEntities/search/countByViewed?viewed=0',
                         qualifier: 'Keep Alive',
                         success: successCallback
                    });
                }
            });
        }


        dataview.up().view_message_handler(record);


    },

    view_message_handler: function(record) {

                if (record) {
                    var detailPanel = this.ownerGrid.next();
                    this.ownerGrid.setHeight("50%");
                    detailPanel.show();
                    detailPanel.header.show();
                    detailPanel.update(record.data);
                }
    }

});