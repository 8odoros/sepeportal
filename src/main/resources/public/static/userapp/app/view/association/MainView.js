/*
 * File: app/view/association/MainView.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.association.MainView', {
    extend: 'Ext.container.Viewport',
    alias: 'widget.associationmainview',

    requires: [
        'MyApp.view.association.MainViewViewModel',
        'Ext.panel.Panel',
        'Ext.button.Button'
    ],

    viewModel: {
        type: 'associationmainview'
    },
    id: 'associationmainView',
    itemId: 'associationmainView',
    layout: 'border',
    defaultListenerScope: true,

    items: [
        {
            xtype: 'panel',
            region: 'west',
            split: true,
            autoScroll: true,
            itemId: 'headerPanel',
            resizable: true,
            width: '30%',
            animCollapse: true,
            collapsed: false,
            collapsible: true,
            header: false,
            title: 'Υπηρεσίες',
            titleCollapse: false,
            layout: {
                type: 'accordion',
                animate: true
            },
            dockedItems: [
                {
                    xtype: 'container',
                    dock: 'top',
                    layout: {
                        type: 'hbox',
                        align: 'stretch'
                    },
                    items: [
                        {
                            xtype: 'button',
                            flex: 1,
                            height: 30,
                            itemId: 'mybutton',
                            glyph: 'xf01c@FontAwesome',
                            text: 'Ενημερώσεις',
                            listeners: {
                                click: 'onNotificationView',
                                afterrender: 'toStartPolling'
                            }
                        },
                        {
                            xtype: 'button',
                            border: 0,
                            disabled: true,
                            hidden: true,
                            id: 'notifbut_associaton',
                            style: {
                                'background-color': 'red'
                            }
                        }
                    ]
                }
            ],
            items: [
                {
                    xtype: 'panel',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    title: 'Οικοδομικές Εργασίες',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα αίτηση "Εκ των προτέρων γνωστοποίησης"'
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    title: 'Γνωστοποιήσεις Ανάθεσης',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Ανάθεση καθηκόντων Τεχνικού Ασφαλείας'
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Ανάθεση καθηκόντων Ιατρού Εργασίας'
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    title: 'Εργατικά Ατυχήματα',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα αναγγελία εργατικού ατυχήματος'
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Προβολή Αρχείου Εργατικών Ατυχημάτων'
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    title: 'Επαγγελματική Ασθένεια',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα αναγγελία επαγγελματικής ασθένειας'
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    title: 'Ιδιωτικά Γραφεία Ευρέσεως Εργασίας',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Υποβολή Κατάστασης'
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    title: 'Έγγραφες εξηγήσεις μετά από έλεγχο',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Προβολή προβλημάτων και απάντηση'
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    title: 'Ημερήσια Δελτία Προσωπικού',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Οικοδομικά και Τεχνικά Έργα'
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    title: 'Ημερολόγιο Δρομολόγια Αυτοκινήτων',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Προβολή δρομολογίων'
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Προσθήκη δρομολογίου'
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    title: 'Ημερολόγιο Μέτρων Ασφάλειας',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Προβολή μέτρων ασφάλειας'
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Προσθήκη μέτρων ασφάλειας'
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    title: 'Βιβλίο Υποδείξεων',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Υποδείξεις Ιατρού Εργασίας'
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Υποδείξεις Τεχνικού Ασφάλειας'
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    title: 'Εκτίμηση Επαγγελματικού Κινδύνου (ΓΕΕΚ)',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Προβολή Αρχείου ΓΕΕΚ'
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα Εκτίμηση'
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: false,
                    title: 'Αιτήσεις χορήγησης άδειας εργασίας κατά την Κυριακή & ημέρα αργίας',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Προβολή Αρχείου Αδειών Κυριακής'
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα Άδεια Κυριακής'
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: false,
                    title: 'Εργατικές Διαφορές - Συμφιλιωτική Διαδικασία',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Προβολή εργατικών διαφορών και απάντηση'
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    minHeight: 150,
                    animCollapse: true,
                    collapsed: true,
                    title: 'Βεβαιώσεις Αντιγράφων ΣΕΠΕ',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf187@FontAwesome',
                            text: 'Προβολή Αιτήσεων'
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα Αίτηση Χορήγησης Αντιγράφων ΣΕΠΕ'
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα Αίτηση Βεβαίωσης Απασχολούμενου Προσωπικού'
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    title: 'Πρόσθετα στοιχεία μετά από έλεγχο',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Προβολή αιτημάτων και απάντηση'
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    title: 'Καταγγελίες',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Προβολή Καταγγελιών'
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα Καταγγελία'
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    animCollapse: false,
                    collapsed: false,
                    hideCollapseTool: true,
                    overlapHeader: true,
                    listeners: {
                        afterrender: 'onEmptyPanelAfterRender'
                    }
                }
            ]
        },
        {
            xtype: 'panel',
            region: 'north',
            height: 62,
            bodyStyle: {
                'background-color': '#cecece'
            }
        },
        {
            xtype: 'panel',
            region: 'south',
            height: 10,
            itemId: 'footerPanel',
            bodyStyle: {
                'background-color': '#cecece'
            }
        },
        {
            xtype: 'panel',
            region: 'center',
            split: true,
            itemId: 'contentPanel',
            layout: 'fit',
            collapsible: false
        }
    ],

    onNotificationView: function(button, e, eOpts) {

    },

    toStartPolling: function(component, eOpts) {
        /*
        var currentNewMessages=null;
        var oldNewMessages=0;
        var successCallback = function(resp, ops) {
            if (parseInt(resp.responseText)>0){
                currentNewMessages=parseInt(resp.responseText);
                Ext.getCmp('notifbut_associaton').setText(resp.responseText+" νέες");
                Ext.getCmp('notifbut_associaton').show();
                if (currentNewMessages>oldNewMessages){

                    //message to show
                    Ext.addon.MessagePop.msg("Προσοχή!", "Υπάρχουν μη αναγνωσμένες ενημερώσεις.", 5000);

                    //reloadgrid store if exist!
                    var view=Ext.getCmp('associationmainView');
                    var center = view.getComponent('contentPanel');
                    if (center.items.length==1 && center.items.get(0).items.length==2){ //will need fix at some point
                        grid=center.items.get(0).items.get(0);
                        if (grid.itemId==="employeenotificationgrid"){
                            grid.getView().store.reload();
                        }
                    }
                }
            }
            else{
                Ext.getCmp('notifbut_associaton').setText();
                Ext.getCmp('notifbut_associaton').hide();
            }
            oldNewMessages=currentNewMessages;
        };

        var poll = new Ext.direct.PollingProvider({
           type: 'polling',
           url: function () {
              Ext.Ajax.request({
                 url: 'tNotificationsAccountEntities/search/countByViewed?viewed=0',
                 qualifier: 'Keep Alive',
                 success: successCallback,
                 failure: function () {
                 }
              });
           },
           interval: 15000
        });


        Ext.Direct.addProvider(poll);
        */
        // poll.disconnect();

    },

    onEmptyPanelAfterRender: function(component, eOpts) {
        component.expand();
        component.header.hide();
    }

});