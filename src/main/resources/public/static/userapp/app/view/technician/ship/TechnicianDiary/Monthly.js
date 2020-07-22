/*
 * File: app/view/technician/ship/TechnicianDiary/Monthly.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.technician.ship.TechnicianDiary.Monthly', {
    extend: 'Ext.window.Window',
    alias: 'widget.technicianshiptechniciandiarymonthly',

    requires: [
        'MyApp.view.technician.ship.TechnicianDiary.MontlyViewModel',
        'MyApp.view.technician.ship.TechnicianDiary.MonthlyViewController',
        'Ext.panel.Tool',
        'Ext.toolbar.Toolbar',
        'Ext.form.Panel',
        'Ext.form.field.ComboBox',
        'Ext.form.field.Number',
        'Ext.form.field.Hidden',
        'Ext.button.Button',
        'Ext.grid.Panel',
        'Ext.grid.column.Column',
        'Ext.grid.filters.filter.String',
        'Ext.grid.View',
        'Ext.grid.filters.Filters',
        'Ext.grid.feature.Grouping'
    ],

    controller: 'technicianshiptechniciandiarymonthly',
    viewModel: {
        type: 'technicianshiptechniciandiarymonthly'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    overflowY: 'scroll',
    resizable: false,
    width: '75%',
    title: 'Ημερολόγιο Επισκέψεων',
    modal: true,
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    tools: [
        {
            xtype: 'tool',
            type: 'print',
            listeners: {
                click: 'onPrintToolClick'
            }
        }
    ],
    dockedItems: [
        {
            xtype: 'toolbar',
            getCurrentTimestamp: function(part) {
                var currentDate = new Date();
                if (part === 1){
                    var month = currentDate.getMonth()+1;
                    if (month.toString().length < 2){
                        month = "0"+month;
                    }
                    var date = currentDate.getDate();
                    if (date.toString().length < 2){
                        date = "0"+date;
                    }
                    var hours = currentDate.getHours();
                    if (hours.toString().length < 2){
                        hours = "0"+hours;
                    }
                    var minutes = currentDate.getMinutes();
                    if (minutes.toString().length < 2){
                        minutes = "0"+minutes;
                    }
                    var seconds = currentDate.getSeconds();
                    if (seconds.toString().length < 2){
                        seconds = "0"+seconds;
                    }
                    return  currentDate.getFullYear()+"-"+month+"-"+date+
                    "T"+hours+":"+minutes+":"+seconds+"."+"000"+"+0000";

                }
                else if (part === 2){
                    var hours = currentDate.getHours();
                    if (hours.toString().length < 2){
                        hours = "0"+hours;
                    }
                    var minutes = currentDate.getMinutes();
                    if (minutes.toString().length < 2){
                        minutes = "0"+minutes;
                    }
                    return hours+":"+minutes;
                }
                else if (part === 3){
                    return currentDate.getFullYear();
                }


            },
            flex: 1,
            dock: 'top',
            id: 'tasmonthly_diary_toolbar',
            style: {
                'background-color': '#157fcc'
            },
            items: [
                {
                    xtype: 'form',
                    layout: 'hbox',
                    title: '',
                    items: [
                        {
                            xtype: 'combobox',
                            margin: 1,
                            msgTarget: 'title',
                            name: 'month',
                            validateOnChange: false,
                            readOnly: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            editable: false,
                            emptyText: 'Μήνας',
                            maxLength: 100,
                            autoLoadOnValue: true,
                            displayField: 'name',
                            valueField: 'value',
                            bind: {
                                store: '{Months}'
                            }
                        },
                        {
                            xtype: 'numberfield',
                            margin: 1,
                            width: 75,
                            msgTarget: 'title',
                            name: 'year',
                            validateOnChange: false,
                            readOnly: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            emptyText: 'Έτος',
                            hideTrigger: true,
                            maxLength: 4,
                            minLength: 4,
                            allowDecimals: false,
                            allowExponential: false,
                            minValue: 2015
                        },
                        {
                            xtype: 'hiddenfield',
                            width: 150,
                            fieldLabel: 'Όνομα',
                            labelWidth: 180,
                            name: 'companyId',
                            validateOnChange: false
                        }
                    ]
                },
                {
                    xtype: 'button',
                    itemId: 'submitbutton',
                    glyph: 'xf06e@FontAwesome',
                    text: 'Προβολή',
                    listeners: {
                        click: {
                            fn: 'onView_TECH_SCHEDULE',
                            scope: 'controller'
                        }
                    }
                }
            ]
        }
    ],
    items: [
        {
            xtype: 'gridpanel',
            flex: 1,
            id: 'technicianShipMonthly_Diary',
            itemId: 'technicianShipMonthly_Diary',
            manageHeight: false,
            title: '',
            columnLines: false,
            reserveScrollbar: true,
            scroll: 'none',
            bind: {
                store: '{MonthlyDiary}'
            },
            columns: [
                {
                    xtype: 'gridcolumn',
                    sortable: false,
                    dataIndex: 'visitDate',
                    text: 'Ημερομηνία',
                    flex: 10,
                    filter: {
                        type: 'string',
                        emptyText: 'Εισαγωγή κειμένου...'
                    }
                },
                {
                    xtype: 'gridcolumn',
                    sortable: false,
                    dataIndex: 'visitTime',
                    text: 'Ώρα',
                    flex: 10
                },
                {
                    xtype: 'gridcolumn',
                    sortable: false,
                    dataIndex: 'visitDurationMinutes',
                    text: 'Διάρκεια (σε λεπτά)',
                    flex: 10
                },
                {
                    xtype: 'gridcolumn',
                    sortable: false,
                    dataIndex: 'compGridName',
                    text: 'Οργανισμός - Πλοίο',
                    flex: 70,
                    filter: {
                        type: 'string',
                        emptyText: 'Εισαγωγή κειμένου...'
                    }
                }
            ],
            viewConfig: {
                frame: true,
                preserveScrollOnRefresh: true,
                listeners: {
                    refresh: 'onDiaryViewRefresh'
                }
            },
            plugins: [
                {
                    ptype: 'gridfilters',
                    menuFilterText: 'Αναζήτηση'
                }
            ],
            features: [
                {
                    ftype: 'grouping',
                    groupByText: 'Ομαδοποίηση',
                    showGroupsText: 'Προβολή ανά ημερομηνία'
                }
            ]
        }
    ],

    onPrintToolClick: function(tool, e, owner, eOpts) {
        var pnl=owner.down('form');
        if (!pnl) {
            pnl = this;
        }


                Ext.define('Ext.ux.converter.GridToHtmlTable', {

                    requires: [
                        'Ext.XTemplate'
                    ],

                    tableTemplate: [
                        '<table border="1", cellspacing="10px">',
                        //'   <thead align="center">',
                        //'      {headerRow}',
                        //'   </thead>',
                        '   <thead align="center" style="font-weight: bold">',
                        '      <td>&nbsp&nbspΗμερομηνία&nbsp&nbsp</td><td>&nbsp&nbspΏρα&nbsp&nbsp</td><td>&nbsp&nbspΔιάρκεια (σε λεπτά)&nbsp&nbsp</td><td>&nbsp&nbspΟργανισμός&nbsp&nbsp</td>',
                        '   </thead>',
                        '   <tbody>',
                        '       {bodyRows}',
                        '   </tbody>',
                        '</table>'
                    ],

                    headerRowTemplate: [
                        '<tr>',
                        '   <tpl for=".">',
                        '       <th colspan="{colspan}" rowspan="{rowspan}">{text}</th>',
                        '   </tpl>',
                        '</tr>'
                    ],

                    constructor: function () {
                        var me = this;
                        me.callParent(arguments);

                        me.tableTemplate = new Ext.XTemplate(me.tableTemplate);
                        me.headerRowTemplate = new Ext.XTemplate(me.headerRowTemplate);
                    },

                    convert: function (grid) {
                        var me = this,
                            headerColumns = me.getColumns(grid),
                            bodyColumns = me.getVisibleColumns(grid),
                            headerRow = me.renderHeaderRow(headerColumns),
                            records = grid.getStore().getRange(),
                            rowTemplate = me.getRowTemplateFromColumns(bodyColumns),
                            bodyRows = me.renderBodyRows(records, rowTemplate),
                            templateData = {
                                headerRow: headerRow,
                                bodyRows: bodyRows
                            },
                            htmlTable = me.tableTemplate.apply(templateData);
                        return htmlTable;
                    },

                    renderHeaderRow: function (columns) {
                        var me = this,
                            columnsOrderMap = {
                                0: columns
                            },
                            deepestLevel = me.getDeepestLevel(columns);
                        return me.renderComplexHeaderRow(columnsOrderMap, deepestLevel);
                    },

                    renderComplexHeaderRow: function (columnsOrderMap, deepestLevel) {
                        columnsOrderMap = columnsOrderMap || {};

                        var me = this,
                            templateData = [],
                            columns = columnsOrderMap[0] || [],
                            columnData,
                            levelDiff,
                            renderedRow;

                        columns.forEach(function (column) {
                            if (column.hidden === true) {
                                return;
                            }

                            columnData = me.getColumnAndRowSpan(column, deepestLevel);
                            columnData.text = column.text;
                            templateData.push(columnData);

                            childColumns = column.items.getRange();
                            if (childColumns.length > 0) {
                                levelDiff = deepestLevel - columnData.rowspan;
                                columnsOrderMap[levelDiff] = columnsOrderMap[levelDiff] || [];
                                columnsOrderMap[levelDiff] = columnsOrderMap[levelDiff].concat(childColumns);
                            }
                        });

                        renderedRow = me.headerRowTemplate.apply(templateData);

                        deepestLevel--;
                        if (deepestLevel < 1) {
                            return renderedRow;
                        }

                        if (columnsOrderMap.hasOwnProperty(deepestLevel) &&
                            !Ext.isEmpty(columnsOrderMap[deepestLevel])) {
                            columnsOrderMap[0] = columnsOrderMap[deepestLevel];
                            delete columnsOrderMap[deepestLevel];
                        }

                        renderedRow += me.renderComplexHeaderRow(columnsOrderMap, deepestLevel);

                        return renderedRow;
                    },

                    renderBodyRows: function (records, rowTemplate) {
                        var template = new Ext.XTemplate(rowTemplate);
                        return template.apply(records);
                    },

                    getRowTemplateFromColumns: function (columns) {
                        var me = this,
                            template = '<tpl for="."><tr>';

                        columns.forEach(function (column) {
                            if (!Ext.isEmpty(column.dataIndex)) {
                                    template += '<td style="padding: 5px;">{[values.get("' + column.dataIndex + '")]}</td>';
                            }
                        });

                        template += '</tr></tpl>';

                        return template;
                    },


                    getColumns: function (grid) {
                        if (!grid || Ext.isEmpty(grid)) {
                            return [];
                        }

                        var view = grid.getView(),
                            header = view.getHeaderCt(),
                            columns = header.items.getRange();
                        return columns;
                    },

                    getVisibleColumns: function (grid) {
                        if (!grid || Ext.isEmpty(grid)) {
                            return [];
                        }

                        var columnManager = grid.columnManager,
                            columns = columnManager.getColumns();
                        return columns;
                    },

                    getDeepestLevel: function (columns) {
                        var me = this,
                            deepestLevel = 1;
                        deepestLevel = columns.reduce(function (level, column) {
                            var depth = me.getColumnDepth(column);
                            return Math.max(level, depth);
                        }, 1);
                        return deepestLevel;
                    },

                    getColumnDepth: function (column) {
                        var me = this,
                            depth = 1,
                            children = column.items.getRange(),
                            numberOfChildren = children.length;

                        if (numberOfChildren > 0) {
                            depth += me.getDeepestLevel(children);
                        }

                        return depth;
                    },

                        getColumnAndRowSpan: function (column, deepestLevel) {
                        var me = this,
                            columnspan = 1,
                            rowspan = deepestLevel,
                            childColumns = column.items.getRange();
                        if (childColumns.length > 0) {
                            columnspan = childColumns.length;
                            rowspan -= me.getDeepestLevel(childColumns);
                        }

                        return {
                            colspan: columnspan,
                            rowspan: rowspan
                        };
                    }
                });

        var converter = Ext.create('Ext.ux.converter.GridToHtmlTable'),
            htmlTableString = converter.convert(owner.down('grid'));

        // instantiate hidden iframe

        var iFrameId = "printerFrame";
        var printFrame = Ext.get(iFrameId);

        if (printFrame === null) {
            printFrame = Ext.getBody().appendChild({
                id: iFrameId,
                tag: 'iframe',
                cls: 'x-hidden',
                style: {
                    display: "none"
                }
            });
        }



        var cw = printFrame.dom.contentWindow;
        var stylesheets = "";
        var markup;
        // instantiate application stylesheets in the hidden iframe



        var printTask = new Ext.util.DelayedTask(function(){
            // print the iframe
            cw.print();

            // destroy the iframe
            Ext.fly(iFrameId).destroy();

        });


        var strTask = new Ext.util.DelayedTask(function(){

            var titleprint=owner.title;
            var markup=pnl.getForm().findField('month').rawValue+" "+pnl.getForm().findField('year').getValue();
            var str = Ext.String.format('<!DOCTYPE html><html><meta charset="UTF-8"><head>{0}</head><body><h3>{3}</h3><h4>{1}</h4>{2}</body></html>',stylesheets,markup,htmlTableString,titleprint);


            // output to the iframe
            cw.document.open();
            cw.document.write(str);
            cw.document.close();

            // remove style attrib that has hardcoded height property
            //             cw.document.getElementsByTagName('DIV')[0].removeAttribute('style');
            printTask.delay(500);

        });

        var markUpTask = new Ext.util.DelayedTask(function(){
            // get the contents of the panel and remove hardcoded overflow properties
            pnl.getForm().getFields().each(function (field) {
                if (field.xtype==="combobox"){
                    field.getEl().dom.childNodes.item(1).childNodes.item(0).childNodes.item(0).childNodes.item(0).setAttribute('value',field.getRawValue());
                }
            });
            markup = pnl.getEl().dom.innerHTML;

            while (markup.indexOf('overflow: auto;') >= 0) {
                markup = markup.replace('overflow: auto;', '');
            }
            /*      while (markup.indexOf('background: rgb(255, 192, 203) !important;') >= 0) {
                    markup = markup.replace('background: rgb(255, 192, 203) !important;', 'background: pink !important;');
                }
        */
            strTask.delay(500);
        });


        var styleSheetConcatTask = new Ext.util.DelayedTask(function(){

            // various style overrides
            stylesheets += ''.concat(
                "<style>",
                ".x-panel-body {overflow: visible !important;}",
                // experimental - page break after embedded panels
                // .x-panel {page-break-after: always; margin-top: 10px}",
                "</style>"
            );

            markUpTask.delay(500);
        });


        var styleSheetCreateTask = new Ext.util.DelayedTask(function(){


            for (var i = 0; i < document.styleSheets.length; i++) {
                stylesheets += Ext.String.format('<link rel="stylesheet" href="{0}" />', document.styleSheets[i].href);
            }
            styleSheetConcatTask.delay(500);
        });

        styleSheetCreateTask.delay(500);

    },

    onDiaryViewRefresh: function(dataview, eOpts) {
        if (dataview.store.data.length<1 && dataview.store.filters.length>0){
            Ext.addon.MessagePop.msg("Προσοχή!", "Δεν υπάρχουν εγγραφές που να αντιστοιχούν στην αναζήτηση σας. Προβάλονται όλες οι εγγραφές της σελίδας.", 5000);
            dataview.up('grid').plugins[1].clearValues();
            dataview.store.reload();
        }
    }

});