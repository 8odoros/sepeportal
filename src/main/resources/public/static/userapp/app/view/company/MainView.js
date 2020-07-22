/*
 * File: app/view/company/MainView.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.MainView', {
    extend: 'Ext.container.Viewport',
    alias: 'widget.companymainview',

    requires: [
        'MyApp.view.company.MainViewViewModel',
        'Ext.panel.Panel',
        'Ext.button.Button'
    ],

    viewModel: {
        type: 'companymainview'
    },
    id: 'companymainView',
    itemId: 'companymainView',
    layout: 'border',
    defaultListenerScope: true,

    items: [
        {
            xtype: 'panel',
            region: 'west',
            split: true,
            autoScroll: true,
            itemId: 'menuPanel',
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
                    height: 50,
                    itemId: 'adminMenu',
                    style: {
                        'background-color': '#ffffff'
                    },
                    layout: {
                        type: 'hbox',
                        align: 'middle',
                        pack: 'center'
                    },
                    items: [
                        {
                            xtype: 'button',
                            flex: 1,
                            cls: 'companyuser-button',
                            height: 30,
                            itemId: 'mybutton',
                            maxWidth: 150,
                            style: {
                                'background-color': '#cecece'
                            },
                            glyph: 'xf0c0@FontAwesome',
                            text: 'Διαχείριση Χρηστών',
                            listeners: {
                                click: 'onAdminPanelView'
                            }
                        }
                    ]
                },
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
                            id: 'notifbut_company',
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
                    itemId: 'Service1',
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
                            glyph: 'xf187@FontAwesome',
                            text: 'Προβολή Βιβλίου Εργατικών Ατυχημάτων',
                            listeners: {
                                click: 'onViewAccidentClick'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα αναγγελία εργατικού ατυχήματος',
                            listeners: {
                                click: 'onNewAccidentClick'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    itemId: 'Service2',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    //disabled: true,
                    title: 'Επαγγελματικές Ασθένειες',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf187@FontAwesome',
                            text: 'Προβολή Αρχείου Επαγγελματικών Ασθενειών',
                            listeners: {
                                click: 'onViewIllnessClick'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα αναγγελία επαγγελματικής ασθένειας',
                            listeners: {
                                click: 'onNewIllnessClick'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    itemId: 'Service3',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    title: 'Αιτήσεις χορήγησης άδειας εργασίας κατά την Κυριακή & ημέρα αργίας',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf187@FontAwesome',
                            text: 'Προβολή Αιτήσεων Άδειας Κυριακής και Ημέρας Αργίας',
                            listeners: {
                                click: 'onViewSundayPmtClick'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα Αίτηση Άδειας εργασίας κατά την Κυριακή και κατά Ημέρα Αργίας',
                            listeners: {
                                click: 'onNewSundayPmtClick'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    itemId: 'Service4',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    //disabled: true,
                    title: 'Καταστάσεις συμβάσεων εργασίας ΙΓΕΕ',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf187@FontAwesome',
                            text: 'Προβολή Καταστάσεων Συμβάσεων Εργασίας ΙΓΕΕ',
                            listeners: {
                                click: 'onViewJobRecrOfficeClick'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα Κατάσταση Συμβάσεων Εργασίας ΙΓΕΕ',
                            listeners: {
                                click: 'onNewJobRecrOfficeClick'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    itemId: 'Service20',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    //disabled: true,
                    title: 'Εργατικές Διαφορές',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf187@FontAwesome',
                            text: 'Προβολή Αιτήσεων',
                            listeners: {
                                click: 'onDisputeView'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα Αίτηση Συζήτησης Εργ. Διαφοράς',
                            listeners: {
                                click: 'onDisputeNew'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    itemId: 'Service5',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    //disabled: true,
                    title: 'Συμφιλιωτικές Διαδικασίες',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf187@FontAwesome',
                            text: 'Προβολή Αιτήσεων Συζήτησης Εργατικής Διαφοράς',
                            listeners: {
                                click: 'onViewDisputesClick'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα Αίτηση Συζήτησης Εργατικής Διαφοράς',
                            listeners: {
                                click: 'onNewDisputesClick'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    itemId: 'Service6',
                    hidden: true,
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
                            glyph: 'xf187@FontAwesome',
                            text: 'Προβολή Καταγγελιών',
                            listeners: {
                                click: 'onComplaintView'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα Καταγγελία',
                            listeners: {
                                click: 'onComplaintNew'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    itemId: 'Service7',
                    minHeight: 150,
                    animCollapse: true,
                    collapsed: true,
                    //disabled: true,
                    title: 'Λοιπές Αιτήσεις / Γνωστοποιήσεις',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf187@FontAwesome',
                            text: 'Προβολή Αιτήσεων / Γνωστοποιήσεων',
                            listeners: {
                                click: 'onGenRequestView'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα Αίτηση / Γνωστοποίηση',
                            listeners: {
                                click: 'onGenRequestNew'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    itemId: 'Service8',
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
                            glyph: 'xf187@FontAwesome',
                            text: 'Προβολή Αιτήσεων Πρόσθετων Στοιχείων',
                            listeners: {
                                click: 'onViewExtraReqsClick'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα Αίτηση Αποστολής Πρόσθετων Στοιχείων',
                            listeners: {
                                click: 'onNewExtraReqsClick'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    itemId: 'Service9',
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
                            glyph: 'xf187@FontAwesome',
                            text: 'Προβολή Εξηγήσεων Ελέγχων',
                            listeners: {
                                click: 'onViewExplanationsClick'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα Αίτηση Εξηγήσεων μετά από έλεγχο',
                            listeners: {
                                click: 'onNewExplanationsClick'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    itemId: 'Service10',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    title: 'Εκ των προτέρων γνωστοποίηση Οικοδομικών Εργασιών',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf187@FontAwesome',
                            text: 'Προβολή Γνωστοποιήσεων Εργασιών',
                            listeners: {
                                click: 'onViewProjectAnnClick'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα "Εκ των προτέρων γνωστοποίηση" εργασιών',
                            listeners: {
                                click: 'onNewProjectAnnClick'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    itemId: 'Service11',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    //disabled: true,
                    title: 'Ημερήσια Δελτία Προσωπικού',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf15b@FontAwesome',
                            text: 'Βιβλία Οικοδομικών και Τεχνικών Έργων',
                            listeners: {
                                click: 'onProjectsDailyBooks'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    itemId: 'Service12',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    title: 'Αναγγελίες Ι.Ε./Τ.Α.',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf061@FontAwesome',
                            text: 'Δήλωση Συνολικού Αριθμού Απασχολουμένων – Δήλωση Εργοδότη/Εργαζόμενου ΔΕ',
                            listeners: {
                                click: 'onTechnicianAnnSetEmplNums'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Αναγγελίες Τεχνικών Ασφαλείας',
                            listeners: {
                                click: 'onTechnicianAnnClick'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Αναγγελίες Ιατρών Εργασίας',
                            //disabled: true,
                            listeners: {
                                click: 'onDoctorAnnClick'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    itemId: 'Service22',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    //hidden: true,
                    //disabled: true,
                    title: 'Αναγγελίες Τεχνικών Ασφάλειας για ΈΝΟΠΛΕΣ ΔΥΝΑΜΕΙΣ',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf187@FontAwesome',
                            text: 'Προβολή Αιτήσεων',
                            listeners: {
                                click: 'onTechnicianMilitaryView'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα Αναγγελία Τεχνικών Ασφάλειας για ΈΝΟΠΛΕΣ ΔΥΝΑΜΕΙΣ',
                            listeners: {
                                click: 'onTechnicianMilitaryNew'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    itemId: 'Service13',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    //disabled: true,
                    title: 'Βιβλία Υποδείξεων',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf15b@FontAwesome',
                            text: 'Υποδείξεις Ιατρών Εργασίας',
                            listeners: {
                                click: 'onDoctorBooksClick'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf15b@FontAwesome',
                            text: 'Υποδείξεις Τεχνικών Ασφάλειας',
                            listeners: {
                                click: 'onTechnicianBooksClick'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    itemId: 'Service14',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    //disabled: true,
                    title: 'Ημερολόγιο Μέτρων Ασφάλειας',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Δημιουργία Ημερολογίου Μέτρων Ασφάλειας',
                            listeners: {
                                click: 'onNewSafetyBookClick'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Προβολή Βιβλίων & Προσθήκη Καταχωρήσεων',
                            listeners: {
                                click: 'onViewSafetyBooksClick'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    itemId: 'Service15',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    //disabled: true,
                    title: 'Εκτίμηση Επαγγελματικού Κινδύνου (ΓΕΕΚ)',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf187@FontAwesome',
                            text: 'Εκτιμήσεις Κινδύνου',
                            listeners: {
                                click: 'onViewDangersClick'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Δημιουργία Καρτέλας ΓΕΕΚ',
                            listeners: {
                                click: 'onNewDangerClick'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    itemId: 'Service16',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    //disabled: true,
                    title: 'Ημερολόγια Δρομολογίων Αυτοκινήτων',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf15b@FontAwesome',
                            text: 'Ημερολόγια Δρομολογίων',
                            listeners: {
                                click: 'onVehiclesBooks'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    itemId: 'Service17',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    hidden:true,
                    disabled: true,
                    title: 'Βιβλιάρια Οδηγών Τουριστικών Λεωφορείων',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf187@FontAwesome',
                            text: 'Προβολή Βιβλιαρίων Εργασίας (ρεπό)',
                            listeners: {
                                click: 'onViewDriversPmtClick'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα Αίτηση Θεώρησης Βιβλιαρίου (ρεπό)',
                            listeners: {
                                click: 'onNewDriversPmtClick'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    itemId: 'Service21',
                    minHeight: 150,
                    width: 150,
                    animCollapse: true,
                    collapsed: true,
                    title: 'Χορήγηση πιστοποιητικού του παρ.2γ, άρθρου 73 του Ν.4412/2016',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf187@FontAwesome',
                            text: 'Προβολή Αιτήσεων',
                            listeners: {
                                click: 'onCertificateView'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Νέα Αίτηση χορήγησης πιστοποιητικού',
                            listeners: {
                                click: 'onCertificateNew'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    height: 300,
                    itemId: 'Service18',
                    minHeight: 300,
                    animCollapse: true,
                    collapsed: true,
                    title: 'ΕΞΥΠΠ Διαχείριση Αναγγελιών Τεχνικών',
                    dockedItems: [
                        {
                            xtype: 'combobox',
                            dock: 'top',
                            margin: 10,
                            fieldLabel: 'Επιλέξτε τον τύπο αναγγελιών που θέλετε να διαχειριστείτε',
                            labelAlign: 'top',
                            labelWidth: 160,
                            name: 'taStatus',
                            validateOnChange: false,
                            value: 2,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            editable: false,
                            maxLength: 200,
                            autoLoadOnValue: true,
                            displayField: 'name',
                            valueField: 'abbr',
                            bind: {
                                store: '{TA_STATUS}'
                            },
                            listeners: {
                                change: 'onComboboxChange3'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf073@FontAwesome',
                            text: 'Προβολή Προγράμματος',
                            listeners: {
                                click: 'onDiaryViewClick1'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf145@FontAwesome',
                            text: 'Αναγγελίες σε Εκκρεμότητα',
                            listeners: {
                                click: 'onCompAnnPendViewClick1'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf145@FontAwesome',
                            text: 'Αντικατάσταση Προβολή - Απάντηση',
                            listeners: {
                                click: 'onCompAnnStopViewClick1'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Γνωστοποίηση Παύσης Καθηκόντων',
                            listeners: {
                                click: 'onTechCompAnnStopClick1'
                            }
                        },
                        {
                            xtype: 'button',
                            id: 'activeContractBtn',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf187@FontAwesome',
                            text: 'Επιχειρήσεις με Ενεργή Σύμβαση',
                            listeners: {
                                click: 'onTechCompAnnStopClick1'
                            }
                        },
                        {
                            xtype: 'button',
                            hidden: true,
                            dock: 'top',
                            height: 30,
                            glyph: 'xf073@FontAwesome',
                            text: 'Συνολικό Πρόγραμμα',
                            listeners: {
                                click: 'onMonthViewClick1'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Προσθήκη και Προβολή Υποδείξεων',
                            listeners: {
                                click: 'onCompanyBookClick1'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    itemId: 'Service19',
                    height: 150,
                    minHeight: 150,
                    animCollapse: true,
                    collapsed: true,
                    disabled: true,
                    title: 'ΕΞΥΠΠ Διαχείριση Αναγγελιών Ιατρών',
                    dockedItems: [
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf145@FontAwesome',
                            text: 'Αναγγελίες σε Εκκρεμότητα',
                            listeners: {
                                click: 'onCompAnnPendViewClick'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf145@FontAwesome',
                            text: 'Αντικατάσταση Προβολή - Απάντηση',
                            listeners: {
                                click: 'onCompAnnStopViewClick'
                            }
                        },
                        {
                            xtype: 'button',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf067@FontAwesome',
                            text: 'Γνωστοποίηση Παύσης Καθηκόντων',
                            listeners: {
                                click: 'onDoctorCompAnnStopClick'
                            }
                        },
                        {
                            xtype: 'button',
                            id: 'activeContractBtn',
                            dock: 'top',
                            height: 30,
                            glyph: 'xf187@FontAwesome',
                            text: 'Επιχειρήσεις με Ενεργή Σύμβαση',
                            listeners: {
                                click: 'onDoctorCompAnnStopClick'
                            }
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    border: false,
                    itemId: 'emptyPanel',
                    animCollapse: false,
                    collapsed: false,
                    hideCollapseTool: true,
                    overlapHeader: true,
                    listeners: {
                        afterrender: 'onEmptyPanelAfterRender'
                    }
                }
            ],
        },
        {
            xtype: 'panel',
            region: 'north',
            height: 62,
            itemId: 'headerPanel',
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

    onComboboxChange3: function(field, newValue, oldValue, eOpts) {
        if(newValue!==oldValue){
            field.up('panel').next().down('combobox').setValue(newValue);
            field.up('panel').next().next().down('combobox').setValue(newValue);
        }
    },

    onCompAnnPendViewClick1: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        if(button.up('panel').down('combobox').getValue()===2)
            var viewsub = Ext.create('MyApp.view.company.exypp.PendingCompanyAnnsPanel');
        //else if(button.up('panel').down('combobox').getValue()===1)
        // var viewsub = Ext.create('MyApp.view.technician.ship.PendingCompanyAnnsPanel');
        center.add(viewsub);
    },

    onCompAnnStopViewClick1: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        if(button.up('panel').down('combobox').getValue()===2)
            var viewsub = Ext.create('MyApp.view.company.exypp.ReplacedCompanyAnnsPanel');
        center.add(viewsub);
    },

    onTechCompAnnStopClick1: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        if(button.up('panel').down('combobox').getValue()===2)
            var viewsub = Ext.create('MyApp.view.company.exypp.ResignationsCompanyAnnsPanel');
        if (button.id == 'activeContractBtn')
            Ext.getCmp('companyexyppCompany_Resignations').hide();
        else
            Ext.getCmp('companyexyppActiveCompany_Anns').hide();
        center.add(viewsub);
    },

    onCompAnnPendViewClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();

        var viewsub = Ext.create('MyApp.view.company.exypp.PendingCompanyDoctorAnnsPanel');
        //else if(button.up('panel').down('combobox').getValue()===1)
        // var viewsub = Ext.create('MyApp.view.technician.ship.PendingCompanyAnnsPanel');
        center.add(viewsub);
    },

    onCompAnnStopViewClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();

        var viewsub = Ext.create('MyApp.view.company.exypp.ReplacedCompanyDoctorAnnsPanel');
        center.add(viewsub);
    },

    onDoctorCompAnnStopClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();

        var viewsub = Ext.create('MyApp.view.company.exypp.ResignationsCompanyDoctorAnnsPanel');
        if (button.id == 'activeContractBtn')
            Ext.getCmp('companyexyppCompanyDoctor_Resignations').hide();
        else
            Ext.getCmp('companyexyppActiveCompanyDoctor_Anns').hide();
        center.add(viewsub);
    },

    onAdminPanelView: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var store = Ext.StoreMgr.lookup('company.CompanyAdmin.USERPREV');
        store.load({
            callback: function(records, operation, success) {
                if (success) {
                    var viewsub = Ext.create('MyApp.view.company.CompanyAdmin.CompanyUsersPanel');
                    center.add(viewsub);
                }
            }
        });
    },

    onDiaryViewClick1: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        if(button.up('panel').down('combobox').getValue()===2)
            var viewsub = Ext.create('MyApp.view.company.exypp.CompanyDiariesPanel');
        center.add(viewsub);
    },

    onMonthViewClick1: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        if(button.up('panel').down('combobox').getValue()===2){
            var viewsub = Ext.create('MyApp.view.company.exypp.CompanyDiariesPanel');
            var emp_comp = Ext.create('widget.companyexypptechniciandiarymonthly', {});
        }
        center.add(viewsub);

        emp_comp.show();
    },

    onCompanyBookClick1: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        if(button.up('panel').down('combobox').getValue()===2)
            var viewsub = Ext.create('MyApp.view.company.exypp.CompanyBooksPanel');
        center.add(viewsub);
    },

    onNotificationView: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.company.NotificationsPanel');
        center.add(viewsub);
    },

    toStartPolling: function(component, eOpts) {
        var currentNewMessages=null;
        var oldNewMessages=0;
        var successCallback = function(resp, ops) {
            if (parseInt(resp.responseText)>0){
                currentNewMessages=parseInt(resp.responseText);
                Ext.getCmp('notifbut_company').setText(resp.responseText+" νέες");
                Ext.getCmp('notifbut_company').show();
                if (currentNewMessages>oldNewMessages){

                    //message to show
                    Ext.addon.MessagePop.msg("Προσοχή!", "Υπάρχουν μη αναγνωσμένες ενημερώσεις.", 5000);

                    //reloadgrid store if exist!
                    var view=Ext.getCmp('companymainView');
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
                Ext.getCmp('notifbut_company').setText();
                Ext.getCmp('notifbut_company').hide();
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
           interval: 60000
        });

        Ext.Direct.addProvider(poll);

        // poll.disconnect();
    },

    onViewAccidentClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.company.AccidentPanel');
        center.add(viewsub);
    },

    onNewAccidentClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var emp_disp = Ext.create('widget.companyaccidentformaccidentform', {
        });

        var successAns = function(options, success, response) {
            if (Ext.JSON.decode(response.responseText).success) {

                var resp =Ext.JSON.decode(response.responseText);
                emp_disp.down('form').getForm().findField('compFullName').setValue(resp.compFullName);
                emp_disp.down('form').getForm().findField('compTaxNumber').setValue(resp.compTaxNumber);
                emp_disp.down('form').getForm().findField('compAme').setValue(resp.compAme);
                emp_disp.down('form').getForm().findField('compAddr').setValue(resp.compAddr);
                emp_disp.down('form').getForm().findField('compAddrK').setValue(resp.compAddrK);
                emp_disp.down('form').getForm().findField('compAddrTk').setValue(resp.compAddrTk);
                emp_disp.down('form').getForm().findField('compPhone').setValue(resp.compPhone);
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

        emp_disp.down().getForm().findField('protNo').setValue("");
        
        emp_disp.show();
    },

    onViewIllnessClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.company.IllnessPanel');
        center.add(viewsub);
    },

    onNewIllnessClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var emp_disp = Ext.create('widget.companyillnessform', {});
        emp_disp.down().getForm().findField('protNo').setValue("");

        emp_disp.show();
    },

    onViewSundayPmtClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.company.SundayPmtPanel');
        center.add(viewsub);
    },

    onNewSundayPmtClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var emp_disp = Ext.create('widget.companysundaypmtformsundaypmtform', {});

        var successAns = function(options, success, response) {
            if (Ext.JSON.decode(response.responseText).success) {

                var resp =Ext.JSON.decode(response.responseText);
                emp_disp.down('form').getForm().findField('compName').setValue(resp.compFullName);
                emp_disp.down('form').getForm().findField('compAfm').setValue(resp.compTaxNumber);
                emp_disp.down('form').getForm().findField('compAme').setValue(resp.compAme);
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
        
        /*var pers = Ext.create('widget.companysundaypmtformpers', {});
        pers.items.getAt(0).items.getAt(0).setValue("1. ");
        pers.items.getAt(1).items.getAt(0).items.getAt(0).setValue("1");
        Ext.getCmp('pers').add(pers);*/
        emp_disp.down().getForm().findField('protNo').setValue("");

        emp_disp.show();
    },

    onViewJobRecrOfficeClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.company.JobRecrOfficePanel');
        center.add(viewsub);
    },

    onNewJobRecrOfficeClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var emp_disp = Ext.create('widget.companyjobrecrofficeformjobrecrofficeform', {});
        emp_disp.down().getForm().findField('protNo').setValue("");

        emp_disp.show();
    },

    onViewDisputesClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.company.DisputeNegPanel');
        center.add(viewsub);
    },

    onNewDisputesClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var emp_disp = Ext.create('widget.companydisputenegform', {});
        emp_disp.down().getForm().findField('protNo').setValue("");

        emp_disp.show();
    },

    onComplaintView: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.company.ComplaintsPanel');
        center.add(viewsub);
    },

    onComplaintNew: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var emp_comp = Ext.create('widget.companycomplaintform', {});
        emp_comp.down().getForm().findField('protNo').setValue("-");

        emp_comp.show();

    },

    onGenRequestView: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.company.GenRequestsPanel');
        center.add(viewsub);
    },

    onGenRequestNew: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var emp_comp = Ext.create('widget.companygenrequestform', {});
        emp_comp.down().getForm().findField('protNo').setValue("-");

        emp_comp.show();

    },

    onViewExtraReqsClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.company.ExtraRequestPanel');
        center.add(viewsub);
    },

    onNewExtraReqsClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var emp_disp = Ext.create('widget.companyextrarequestform', {});

        var successAns = function(options, success, response) {
            if (Ext.JSON.decode(response.responseText).success) {

                var resp =Ext.JSON.decode(response.responseText);
                emp_disp.down('form').getForm().findField('compName').setValue(resp.compFullName);
                emp_disp.down('form').getForm().findField('compAfm').setValue(resp.compTaxNumber);
                emp_disp.down('form').getForm().findField('compAme').setValue(resp.compAme);
                emp_disp.down('form').getForm().findField('compPhone').setValue(resp.compPhone);
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
        
        emp_disp.down().getForm().findField('protNo').setValue("");

        emp_disp.show();
    },

    onViewExplanationsClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.company.ExplanationsPanel');
        center.add(viewsub);
    },

    onNewExplanationsClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');

        var center = view.getComponent('contentPanel');
        center.removeAll();
        var emp_disp = Ext.create('widget.companyexplanationform', {});

        var successAns = function(options, success, response) {
            if (Ext.JSON.decode(response.responseText).success) {

                var resp =Ext.JSON.decode(response.responseText);
                emp_disp.down('form').getForm().findField('compName').setValue(resp.compFullName);
                emp_disp.down('form').getForm().findField('compAfm').setValue(resp.compTaxNumber);
                emp_disp.down('form').getForm().findField('compAme').setValue(resp.compAme);
                emp_disp.down('form').getForm().findField('compPhone').setValue(resp.compPhone);
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

        emp_disp.down().getForm().findField('protNo').setValue("");

        emp_disp.show();
    },

    onViewProjectAnnClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.company.ProjectAnnPanel');
        center.add(viewsub);
    },

    onNewProjectAnnClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');


        var center = view.getComponent('contentPanel');
        center.removeAll();
        var emp_disp = Ext.create('widget.companyprojectannformprojectannform', {});
        var projsmanagers = Ext.create('widget.companyprojectannformmng_studier_selfempl', {});
        projsmanagers.down().items.get(4).setValue("1");
        Ext.getCmp('projsmanagers').add(projsmanagers);
        var projsstudiers = Ext.create('widget.companyprojectannformmng_studier_selfempl', {});
        Ext.getCmp('projsstudiers').add(projsstudiers);
        projsstudiers.down().items.get(4).setValue("2");
        var projsselfempls = Ext.create('widget.companyprojectannformmng_studier_selfempl', {});
        Ext.getCmp('projsselfempls').add(projsselfempls);
        projsselfempls.down().items.get(4).setValue("3");
        projsselfempls.down().items.get(0).allowOnlyWhitespace=true;
        projsselfempls.down().items.get(1).allowOnlyWhitespace=true;
        projsselfempls.down().items.get(2).allowOnlyWhitespace=true;
        projsselfempls.down().items.get(3).allowOnlyWhitespace=true;
        projsselfempls.down().items.get(0).allowBlank=true;
        projsselfempls.down().items.get(1).allowBlank=true;
        projsselfempls.down().items.get(2).allowBlank=true;
        projsselfempls.down().items.get(3).allowBlank=true;
        var projscontr = Ext.create('widget.companyprojectannformcontr', {});
        projscontr.items.getAt(0).items.getAt(0).setValue("1. ");
        Ext.getCmp('projscontrs').add(projscontr);
        var projeng = Ext.create('widget.companyprojectannformeng', {});
        projeng.items.getAt(0).items.getAt(0).setValue("1. ");
        projeng.items.getAt(1).items.getAt(0).items.getAt(6).setValue("1");
        Ext.getCmp('projsengs1').add(projeng);
        projeng = Ext.create('widget.companyprojectannformeng', {});
        projeng.items.getAt(0).items.getAt(0).setValue("1. ");
        projeng.items.getAt(1).items.getAt(0).items.getAt(6).setValue("2");
        projeng.items.getAt(1).items.getAt(0).items.getAt(0).allowOnlyWhitespace=true;
        projeng.items.getAt(1).items.getAt(0).items.getAt(1).allowOnlyWhitespace=true;
        projeng.items.getAt(1).items.getAt(0).items.getAt(2).allowOnlyWhitespace=true;
        projeng.items.getAt(1).items.getAt(0).items.getAt(3).allowOnlyWhitespace=true;
        projeng.items.getAt(1).items.getAt(0).items.getAt(4).allowOnlyWhitespace=true;
        projeng.items.getAt(1).items.getAt(0).items.getAt(5).allowOnlyWhitespace=true;
        projeng.items.getAt(1).items.getAt(0).items.getAt(0).allowBlank=true;
        projeng.items.getAt(1).items.getAt(0).items.getAt(1).allowBlank=true;
        projeng.items.getAt(1).items.getAt(0).items.getAt(2).allowBlank=true;
        projeng.items.getAt(1).items.getAt(0).items.getAt(3).allowBlank=true;
        projeng.items.getAt(1).items.getAt(0).items.getAt(4).allowBlank=true;
        projeng.items.getAt(1).items.getAt(0).items.getAt(5).allowBlank=true;
        Ext.getCmp('projsengs2').add(projeng);
        projeng = Ext.create('widget.companyprojectannformeng', {});
        projeng.items.getAt(0).items.getAt(0).setValue("1. ");
        projeng.items.getAt(1).items.getAt(0).items.getAt(6).setValue("3");
        Ext.getCmp('projsengs3').add(projeng);

        emp_disp.down().getForm().findField('protNo').setValue("");

        emp_disp.show();
    },

    onProjectsDailyBooks: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.company.ProjectsBooksPanel');
        Ext.getCmp('companyProjectsBooks_Daily').store.clearData();
        Ext.getCmp('companyProjectsBooks_Personel').store.clearData();
        center.add(viewsub);
    },

    onDoctorAnnClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.company.DoctorAnnPanel');
        Ext.getCmp('companyDoctorAnn_Doctors').store.clearData();
        center.add(viewsub);
    },

    onTechnicianAnnSetEmplNums: function(button, e, eOpts) {

                        var view=Ext.getCmp('companymainView');
                        var center = view.getComponent('contentPanel');
                        center.removeAll();

                        var successAns = function(options, success, response) {
                            if (Ext.JSON.decode(response.responseText).success) {

                                var emp_disp_nums = Ext.create('widget.companytechnicianannemployeenumform', {});
                                var resp =Ext.JSON.decode(response.responseText);
                                if (resp.categCNum != 0)
                                {
                                    emp_disp_nums.down('form').getForm().findField('categANum').suspendEvents();
                                    emp_disp_nums.down('form').getForm().findField('categBNum').suspendEvents();
                                }
                                else if (resp.categBNum != 0)
                                {
                                    emp_disp_nums.down('form').getForm().findField('categANum').suspendEvents();
                                    emp_disp_nums.down('form').getForm().findField('categCNum').suspendEvents();
                                }
                                else if (resp.categANum != 0)
                                {
                                    emp_disp_nums.down('form').getForm().findField('categBNum').suspendEvents();
                                    emp_disp_nums.down('form').getForm().findField('categCNum').suspendEvents();
                                }
                                emp_disp_nums.down('form').getForm().findField('categANum').setValue(resp.categANum);
                                emp_disp_nums.down('form').getForm().findField('categBNum').setValue(resp.categBNum);
                                emp_disp_nums.down('form').getForm().findField('categCNum').setValue(resp.categCNum);
                                emp_disp_nums.down('form').getForm().findField('categANum').resumeEvents();
                                emp_disp_nums.down('form').getForm().findField('categBNum').resumeEvents();
                                emp_disp_nums.down('form').getForm().findField('categCNum').resumeEvents();
                                emp_disp_nums.down('form').getForm().findField('isTaNoneEmployerEmployee').setValue(resp.isTaNoneEmployerEmployee);
                                emp_disp_nums.down('form').getForm().findField('isValidAllData').setValue(resp.isValidAllData);
                                emp_disp_nums.down('form').getForm().findField('isValidData1').setValue(resp.isValidData1);
                                emp_disp_nums.down('form').getForm().findField('isValidData2').setValue(resp.isValidData2);
                                if(resp.taAfm!==null)
                                emp_disp_nums.down('form').getForm().findField('taAfm').setValue(resp.taAfm.toString());
                                if(resp.taFullname!==null)
                                emp_disp_nums.down('form').getForm().findField('taFullname').setValue(resp.taFullname.toString());
                                emp_disp_nums.down('form').getForm().findField('totalEmpls').setValue(resp.totalEmpls);
                                emp_disp_nums.down('form').getForm().findField('taDegree').setValue(resp.taDegree);
                                
                                if (resp.role == 7) {
                                    emp_disp_nums.down('form').getForm().findField('categANum').setReadOnly(true);
                                    emp_disp_nums.down('form').getForm().findField('categBNum').setReadOnly(true);
                                    emp_disp_nums.down('form').getForm().findField('categCNum').setReadOnly(true);
                                    emp_disp_nums.down('form').getForm().findField('isTaNoneEmployerEmployee').setReadOnly(true);
                                    emp_disp_nums.down('form').getForm().findField('isValidAllData').setReadOnly(true);
                                    emp_disp_nums.down('form').getForm().findField('isValidData1').setReadOnly(true);
                                    emp_disp_nums.down('form').getForm().findField('isValidData2').setReadOnly(true);
                                    emp_disp_nums.down('form').getForm().findField('taAfm').setReadOnly(true);
                                    emp_disp_nums.down('form').getForm().findField('taFullname').setReadOnly(true);
                                    emp_disp_nums.down('form').getForm().findField('totalEmpls').setReadOnly(true);
                                    emp_disp_nums.down('form').getForm().findField('taDegree').setReadOnly(true);
                                    emp_disp_nums.down('toolbar').getComponent('savebutton').hide();
                                }
                                emp_disp_nums.show();

                            }
                            else{
                                Ext.Msg.alert('Σφάλμα!', 'Υπάρχει πρόβλημα στην επισύναψη στοιχείων');

                            }
                        };

                        Ext.Ajax.request({
                            url: '/companyExtraInfo',
                            method: "GET",
                            callback: successAns
                        });
    },

    onTechnicianAnnClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var emp_disp = Ext.create('widget.companytechnicianannchooseform', {});
        emp_disp.show();
    },

    onDoctorBooksClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var store = Ext.StoreMgr.lookup('company.DoctorBooks.BOOKS');
        store.load({
            callback: function(records, operation, success) {
                if (success) {
                var viewsub = Ext.create('MyApp.view.company.DoctorBooksPanel');
                    Ext.getCmp('companyDoctorBooks_Book').store.clearData();
                    center.add(viewsub);
                }
            }
        });
    },

    onTechnicianBooksClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var emp_disp = Ext.create('widget.companytechnicianannchooseform', {});
        emp_disp.show();
        emp_disp.items.getAt(2).setValue("true");
    },

    onNewSafetyBookClick: function(button, e, eOpts) {

        var view=Ext.getCmp('companymainView');

        var center = view.getComponent('contentPanel');
        center.removeAll();
        var emp_disp = Ext.create('widget.companysafetybooksafetybookform', {});
        var projscontr = Ext.create('widget.companysafetybookcontr', {});
        projscontr.items.getAt(0).items.getAt(0).setValue("1. ");
        Ext.getCmp('bookcontrs').add(projscontr);
        var projeng = Ext.create('widget.companysafetybookeng', {});
        projeng.items.getAt(0).items.getAt(0).setValue("1. ");
        Ext.getCmp('bookengs').add(projeng);

        emp_disp.show();
    },

    onViewSafetyBooksClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
                var center = view.getComponent('contentPanel');
                center.removeAll();
                var viewsub = Ext.create('MyApp.view.company.SafetyBooksPanel');
                Ext.getCmp('companySafetyBooks_Notes').store.clearData();
                center.add(viewsub);
    },

    onViewDangersClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var store = Ext.StoreMgr.lookup('company.COMP_BRANCES');
                store.load({
                    callback: function(records, operation, success) {
                        if (success) {
                            var viewsub = Ext.create('MyApp.view.company.DangersPanel');
                            center.add(viewsub);
                        }
                    }
                });
    },

    onNewDangerClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var store = Ext.StoreMgr.lookup('company.COMP_BRANCES');
                store.load({
                    callback: function(records, operation, success) {
                        if (success) {
                            var viewsub = Ext.create('MyApp.view.company.DangersPanel');
                            center.add(viewsub);
                            var emp_disp = Ext.create('widget.companydangerformbranchcheck', {});
                            emp_disp.show();
                        }
                    }
                });



    },

    onVehiclesBooks: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.company.VehiclesBooksPanel');
        Ext.getCmp('companyVehiclesBooks_Routes').store.clearData();
        center.add(viewsub);

    },

    onViewDriversPmtClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.company.DriversPmtPanel');
        center.add(viewsub);
    },

    onNewDriversPmtClick: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');


        var center = view.getComponent('contentPanel');
        center.removeAll();
        var emp_disp = Ext.create('widget.companydriverspmtformdriverpmtform', {});
        /*var offday = Ext.create('widget.companydriverspmtformoffday', {});
        offday.items.getAt(0).items.getAt(0).setValue("1. ");
        Ext.getCmp('offdays').add(offday);*/

        emp_disp.down().getForm().findField('protNo').setValue("");

        emp_disp.show();
    },

    onEmptyPanelAfterRender: function(component, eOpts) {
        component.expand();
        component.header.hide();
    },

    onDisputeView: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.employee.DisputesPanel');
        center.add(viewsub);
    },

    onDisputeNew: function(button, e, eOpts) {
        Ext.getBody().mask("Παρακαλώ Περιμένετε...");

        var store = Ext.StoreMgr.lookup( 'employee.DISPUTE_REASONS' );
        store.load( { callback : function(records, operation, success) {
            var view=Ext.getCmp('companymainView');

            //console.log(view);
            var center = view.getComponent('contentPanel');
            center.removeAll();
            var emp_disp = Ext.create('widget.employeedisputeform', {
                // emp_comp.down()
            });
            //debugger;
            emp_disp.down().getForm().findField('protNo').setValue(0);
            // Show window
            emp_disp.show();
        }
        });

        Ext.getBody().unmask();

    },

    onCertificateView: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.company.CertificatesPanel');
        center.add(viewsub);
    },

    onCertificateNew: function(button, e, eOpts) {
        /*var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var emp_comp = Ext.create('widget.companycertificateform', {});
        emp_comp.down().getForm().findField('protNo').setValue("-");

        emp_comp.show();*/
        Ext.Msg.alert('Μήνυμα Συστήματος', 'Ενημερώνονται οι χρήστες ότι σύμφωνα με την παρ. 46.α. του άρθρου 43 του Ν. 4605/2019: «Μέχρι να καταστεί εφικτή η έκδοση του πιστοποιητικού που προβλέπεται στην περίπτωση γ΄ της παραγράφου 2 του άρθρου 80 (του ν. 4412/2016), αυτό αντικαθίσταται από υπεύθυνη δήλωση του οικονομικού φορέα, χωρίς να απαιτείται επίσημη δήλωση του Σ.ΕΠ.Ε. σχετικά με την έκδοση του πιστοποιητικού.»');

    },

    onTechnicianMilitaryView: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var viewsub = Ext.create('MyApp.view.company.TechnicianMilitaryPanel');
        center.add(viewsub);
    },

    onTechnicianMilitaryNew: function(button, e, eOpts) {
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        var emp_comp = Ext.create('widget.companytechnicianmilitaryform', {});
        emp_comp.down().getForm().findField('protNo').setValue("-");

        emp_comp.show();

    },

});