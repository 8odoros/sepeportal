/*
 * File: app/view/company/VehiclesBooks/RoutesForm.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.VehiclesBooks.RoutesForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.companyvehiclesbooksroutesform',

    requires: [
        'MyApp.view.company.VehiclesBooks.RoutesFormViewModel',
        'MyApp.view.company.VehiclesBooks.RoutesFormViewController',
        'MyApp.view.shared.PrintFormTool',
        'MyApp.view.shared.CloseFormTool',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.FieldSet',
        'Ext.form.field.Date',
        'Ext.form.field.TextArea',
        'Ext.form.field.Time',
        'Ext.toolbar.Toolbar',
        'Ext.button.Button',
        'Ext.panel.Tool'
    ],

    controller: 'companyvehiclesbooksroutesform',
    viewModel: {
        type: 'companyvehiclesbooksroutesform'
    },
    constrain: true,
    draggable: false,
    frame: true,
    height: '90%',
    overflowY: 'scroll',
    resizable: false,
    width: 800,
    closable: false,
    title: 'Νέο Δρομολόγιο Αυτοκινήτου',
    //modal: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'form',
            timestampToDate: function(timestamp) {

                var pD = timestamp.replace(/[^0-9]+/g,' ').split(" ");
                return (pD[2]+"-"+pD[1]+"-"+pD[0]);
            },
            id: 'vehiclesbooksroutes',
            padding: 15,
            title: '',
            layout: {
                type: 'vbox',
                align: 'stretch'
            },
            items: [
                {
                    xtype: 'hiddenfield',
                    fieldLabel: '',
                    name: 'a_new_form',
                    submitValue: false,
                    validateOnChange: false,
                    value: true
                },
                {
                    xtype: 'fieldset',
                    flex: 1,
                    focusOnToFront: false,
                    padding: 10,
                    toFrontOnShow: false,
                    title: 'ΣΤΟΙΧΕΙΑ ΔΕΛΤΙΟΥ',
                    items: [
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Α/Α Δελτίου',
                            labelWidth: 180,
                            name: 'aa',
                            validateOnChange: false,
                            readOnly: true,
                            validateOnBlur: false,
                            editable: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            disabled: true,
                            disabledCls: 'x-form-readonly',
                            fieldLabel: 'Ημερομηνία Δελτίου',
                            labelWidth: 180,
                            name: 'createdDate',
                            submitValue: false,
                            validateOnChange: false,
                            validateOnBlur: false,
                            editable: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Αρ. Άδειας Κυκλοφορίας',
                            labelWidth: 180,
                            name: 'licenceNumber',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50
                        }
                    ]
                },
                {
                    xtype: 'fieldset',
                    flex: 1,
                    focusOnToFront: false,
                    padding: 10,
                    toFrontOnShow: false,
                    title: 'ΣΤΟΙΧΕΙΑ ΟΔΗΓΟΥ',
                    items: [
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Όνομα',
                            labelWidth: 180,
                            name: 'driverFirstname',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Επώνυμο',
                            labelWidth: 180,
                            name: 'driverLastname',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Τόπος Γέννησης',
                            labelWidth: 180,
                            name: 'driverBirthplace',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            fieldLabel: 'Ημ. Γέννησης',
                            labelWidth: 180,
                            name: 'driverBirthdate',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50,
                            format: 'd-m-Y',
                            submitFormat: 'Y-m-d\\T00:00:00.000+0000'
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            fieldLabel: 'Διεύθυνση Κατοικίας',
                            labelWidth: 180,
                            name: 'driverAddr',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Αριθμός Δελτίου Ταυτότητας',
                            labelWidth: 180,
                            name: 'driverCardNumber',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Εκδίδουσα Άρχη',
                            labelWidth: 180,
                            name: 'driverCardIssuingAuth',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Α.Φ.Μ.',
                            labelWidth: 180,
                            name: 'driverAfm',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 9,
                            minLength: 9,
                            maskRe:/[0-9.]/
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Αρ. Διπλώματος Οδήγησης',
                            labelWidth: 180,
                            name: 'driverLicenceNum',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'hiddenfield',
                            height: 150,
                            labelWidth: 180,
                            name: 'driverCardType',
                            validateOnChange: false
                        }
                    ]
                },
                {
                    xtype: 'fieldset',
                    flex: 1,
                    focusOnToFront: false,
                    padding: 10,
                    toFrontOnShow: false,
                    title: 'ΣΤΟΙΧΕΙΑ ΒΟΗΘΟΥ',
                    items: [
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Όνομα',
                            labelWidth: 180,
                            name: 'assistFirstname',
                            validateOnChange: false,
                            validateOnBlur: false,
                            //allowBlank: false,
                            //allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Επώνυμο',
                            labelWidth: 180,
                            name: 'assistLastname',
                            validateOnChange: false,
                            validateOnBlur: false,
                            //allowBlank: false,
                            //allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Τόπος Γέννησης',
                            labelWidth: 180,
                            name: 'assistBirthplace',
                            validateOnChange: false,
                            validateOnBlur: false,
                            //allowBlank: false,
                            //allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            fieldLabel: 'Ημ. Γέννησης',
                            labelWidth: 180,
                            name: 'assistBirthdate',
                            validateOnChange: false,
                            validateOnBlur: false,
                            //allowBlank: false,
                            //allowOnlyWhitespace: false,
                            maxLength: 50,
                            format: 'd-m-Y',
                            submitFormat: 'Y-m-d\\T00:00:00.000+0000'
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            fieldLabel: 'Διεύθυνση Κατοικίας',
                            labelWidth: 180,
                            name: 'assistAddr',
                            validateOnChange: false,
                            validateOnBlur: false,
                            //allowBlank: false,
                            //allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Αριθμός Δελτίου Ταυτότητας',
                            labelWidth: 180,
                            name: 'assistCardNumber',
                            validateOnChange: false,
                            validateOnBlur: false,
                            //allowBlank: false,
                            //allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Εκδίδουσα Άρχη',
                            labelWidth: 180,
                            name: 'assistIssuingAuth',
                            validateOnChange: false,
                            validateOnBlur: false,
                            //allowBlank: false,
                            //allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Α.Φ.Μ.',
                            labelWidth: 180,
                            name: 'assistAfm',
                            validateOnChange: false,
                            validateOnBlur: false,
                            //allowBlank: false,
                            //allowOnlyWhitespace: false,
                            maxLength: 9,
                            minLength: 9,
                            maskRe:/[0-9.]/
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Αρ. Διπλώματος Οδήγησης',
                            labelWidth: 180,
                            name: 'assistLicenceNum',
                            validateOnChange: false,
                            validateOnBlur: false,
                            //allowBlank: false,
                            //allowOnlyWhitespace: false,
                            maxLength: 50
                        },
                        {
                            xtype: 'hiddenfield',
                            height: 150,
                            labelWidth: 180,
                            name: 'assistCardType',
                            validateOnChange: false
                        }
                    ]
                },
                {
                    xtype: 'fieldset',
                    flex: 1,
                    focusOnToFront: false,
                    padding: 10,
                    toFrontOnShow: false,
                    suspendLayout: true,
                    title: 'ΣΤΟΙΧΕΙΑ ΔΡΟΜΟΛΟΓΙΟΥ',
                    items: [
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Τόπος Εκκίνησης',
                            labelWidth: 180,
                            name: 'routeSource',
                            validateOnChange: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            maxLength: 128
                        },
                        {
                            xtype: 'timefield',
                            anchor: '100%',
                            fieldLabel: 'Έναρξη Απασχόλησης',
                            labelWidth: 180,
                            name: 'activityStartTime',
                            validateOnChange: false,
                            validateOnBlur: false,
                            //allowBlank: false,
                            //allowOnlyWhitespace: false,
                            maxLength: 180,
                            format: 'H:i',
                            increment: 30,
                            invalidText: 'Πληκρολογήστε μία έγκυρη ώρα',
                        },
                        {
                            xtype: 'timefield',
                            anchor: '100%',
                            fieldLabel: 'Έναρξη Οδήγησης',
                            labelWidth: 180,
                            name: 'driveStartTime',
                            validateOnChange: false,
                            validateOnBlur: false,
                            //allowBlank: false,
                            //allowOnlyWhitespace: false,
                            invalidText: 'Πληκρολογήστε μία έγκυρη ώρα',
                            maxLength: 180,
                            format: 'H:i',
                            increment: 30
                        },
                        {
                            xtype: 'timefield',
                            anchor: '100%',
                            fieldLabel: 'Άφιξη',
                            labelWidth: 180,
                            name: 'arivalTime',
                            validateOnChange: false,
                            validateOnBlur: false,
                            //allowBlank: false,
                            //allowOnlyWhitespace: false,
                            invalidText: 'Πληκρολογήστε μία έγκυρη ώρα',
                            maxLength: 180,
                            format: 'H:i',
                            increment: 30
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Τόπος Προορισμού',
                            labelWidth: 180,
                            name: 'routeDestination',
                            validateOnChange: false,
                            validateOnBlur: false,
                            //allowBlank: false,
                            //allowOnlyWhitespace: false,
                            maxLength: 20
                        },
                        {
                            xtype: 'timefield',
                            anchor: '100%',
                            fieldLabel: 'Χρονική Διάρκεια Επισκευής',
                            labelWidth: 180,
                            name: 'repairDuration',
                            validateOnChange: false,
                            validateOnBlur: false,
                            //allowBlank: false,
                            //allowOnlyWhitespace: false,
                            invalidText: 'Πληκρολογήστε μία έγκυρη ώρα',
                            maxLength: 180,
                            format: 'H:i',
                            increment: 30
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Είδος Φορτίου',
                            labelWidth: 180,
                            name: 'loadType',
                            validateOnChange: false,
                            validateOnBlur: false,
                            emptyText: 'Μόνο για Φορτηγά',
                            maxLength: 128
                        },
                        {
                            xtype: 'timefield',
                            anchor: '100%',
                            fieldLabel: 'Χρονική Διάρκεια Διαλειμμάτων',
                            labelWidth: 180,
                            name: 'breakDuration',
                            validateOnChange: false,
                            validateOnBlur: false,
                            //allowBlank: false,
                            //allowOnlyWhitespace: false,
                            invalidText: 'Πληκρολογήστε μία έγκυρη ώρα',
                            maxLength: 180,
                            format: 'H:i',
                            increment: 30
                        },
                        {
                            xtype: 'timefield',
                            anchor: '100%',
                            fieldLabel: 'Χρονική Διάρκεια Μεταξύ Δύο Διαδρομών',
                            labelWidth: 180,
                            name: 'betweenRouteDuration',
                            validateOnChange: false,
                            validateOnBlur: false,
                            //allowBlank: false,
                            //allowOnlyWhitespace: false,
                            invalidText: 'Πληκρολογήστε μία έγκυρη ώρα',
                            maxLength: 180,
                            format: 'H:i',
                            increment: 30
                        },
                        {
                            xtype: 'timefield',
                            anchor: '100%',
                            fieldLabel: 'Λήξη Απασχόλησης',
                            labelWidth: 180,
                            name: 'activityEndTime',
                            validateOnChange: false,
                            validateOnBlur: false,
                            //allowBlank: false,
                            //allowOnlyWhitespace: false,
                            invalidText: 'Πληκρολογήστε μία έγκυρη ώρα',
                            maxLength: 180,
                            format: 'H:i',
                            increment: 30
                        },
                        {
                            xtype: 'timefield',
                            anchor: '100%',
                            fieldLabel: 'Σύνολο Ωρών Εργασίας',
                            labelWidth: 180,
                            name: 'jobHours',
                            validateOnChange: false,
                            validateOnBlur: false,
                            //allowBlank: false,
                            //allowOnlyWhitespace: false,
                            invalidText: 'Πληκρολογήστε μία έγκυρη ώρα',
                            maxLength: 180,
                            format: 'H:i',
                            increment: 30
                        },
                        {
                            xtype: 'timefield',
                            anchor: '100%',
                            fieldLabel: 'Σύνολο Ωρών Απασχόλησης',
                            labelWidth: 180,
                            name: 'activityHours',
                            validateOnChange: false,
                            validateOnBlur: false,
                            //allowBlank: false,
                            //allowOnlyWhitespace: false,
                            invalidText: 'Πληκρολογήστε μία έγκυρη ώρα',
                            maxLength: 180,
                            format: 'H:i',
                            increment: 30
                        },
                        {
                            xtype: 'textareafield',
                            anchor: '100%',
                            fieldLabel: 'Παρατηρήσεις',
                            labelWidth: 180,
                            name: 'notes',
                            validateOnChange: false,
                            validateOnBlur: false,
                            maxLength: 3000,
                            validateBlank: true
                        }
                    ]
                },
                {
                    xtype: 'fieldset',
                    flex: 1,
                    title: 'Ιστορικό Δελτίου',
                    margin: '31 0 0 0',
                    items: [
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            disabledCls: 'x-form-item',
                            fieldLabel: 'Ημερομηνία Καταχώρησης',
                            labelWidth: 180,
                            name: 'dateCreated',
                            validateOnChange: false,
                            readOnly: true,
                            validateOnBlur: false,
                            editable: false,
                            hideTrigger: true,
                            maxLength: 50,
                            format: 'd-m-Y',
                            submitFormat: 'Y-m-d\\T00:00:00.000+0000'
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Ώρα Καταχώρησης',
                            labelWidth: 180,
                            name: 'timeCreated',
                            validateOnChange: false,
                            readOnly: true,
                            validateOnBlur: false,
                            editable: false,
                            hideTrigger: true
                        },
                        {
                            xtype: 'datefield',
                            anchor: '100%',
                            fieldLabel: 'Ημερομηνία Επεξεργασίας',
                            labelWidth: 180,
                            name: 'dateUpdated',
                            validateOnChange: false,
                            readOnly: true,
                            validateOnBlur: false,
                            editable: false,
                            maxLength: 50,
                            format: 'd-m-Y',
                            submitFormat: 'Y-m-d\\T00:00:00.000+0000'
                        },
                        {
                            xtype: 'textfield',
                            anchor: '100%',
                            fieldLabel: 'Ώρα Επεξεργασίας',
                            labelWidth: 180,
                            name: 'timeUpdated',
                            validateOnChange: false,
                            readOnly: true,
                            validateOnBlur: false,
                            editable: false,
                            hideTrigger: true
                        }
                    ]
                },
                {
                    xtype: 'hiddenfield',
                    height: 150,
                    labelWidth: 180,
                    name: 'companyId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    height: 150,
                    labelWidth: 180,
                    name: 'spPtlCompVehicleBookId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    height: 150,
                    labelWidth: 180,
                    name: 'url',
                    validateOnChange: false
                }
            ]
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
            dateToTimestamp: function(date) {
                var pD = date.split("-");
                return (pD[2]+"-"+pD[1]+"-"+pD[0]+"T00:00:00.000+0000");
            },
            dock: 'bottom',
            html: '<span style="color:#696969;font-size:11px;"><em>*Τα ανενεργά πεδία θα συμπληρωθούν αυτόματα<br>από το σύστημα κατά την υποβολή</em></span>',
            id: 'comproutes_save_submit_toolbar',
            style: {
                'background-color': '#f5f5f5'
            },
            layout: {
                type: 'hbox',
                pack: 'end'
            },
            items: [
                {
                    xtype: 'button',
                    itemId: 'submitbutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf1d8@FontAwesome',
                    text: 'Καταχώρηση',
                    listeners: {
                        click: 'onSubmit_COMPANY_ROUTES'
                    }
                }
            ]
        }
    ],
    listeners: {
        show: function(component, eOpts) {
            var view = Ext.getCmp('companymainView');
            var menu = view.getComponent('menuPanel');
            var contentPanel = view.getComponent('contentPanel');
            menu.setDisabled(true);
            contentPanel.setDisabled(true);
        },
        hide: function(button, e, eOpts) {
            var view = Ext.getCmp('companymainView');
            var menu = view.getComponent('menuPanel');
            var contentPanel = view.getComponent('contentPanel');
            menu.setDisabled(false);
            contentPanel.setDisabled(false);
        }
    },
    tools: [
        {
            xtype: 'sharedprintformtool'
        },
        {
            xtype: 'sharedcloseformtool'
        }
    ],

});