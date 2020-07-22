/*
 * File: app/view/company/DangerForm/BranchCheck.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.DangerForm.BranchCheck', {
    extend: 'Ext.window.Window',
    alias: 'widget.companydangerformbranchcheck',

    requires: [
        'MyApp.view.company.DangerForm.BranchCheckViewModel',
        'MyApp.view.company.DangerForm.BranchCheckViewController',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.field.ComboBox',
        'Ext.toolbar.Toolbar',
        'Ext.button.Button'
    ],

    controller: 'companydangerformbranchcheck',
    viewModel: {
        type: 'companydangerformbranchcheck'
    },
    constrain: true,
    draggable: false,
    frame: true,
    resizable: false,
    width: 800,
    title: 'Έλεγχος αν υπάρχει προηγούμενη Γραπτή Εκτίμηση Επαγγελματικού Κινδύνου',
    //modal: true,
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
    
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'form',
            id: 'companydangerbranchcheck',
            padding: 15,
            title: '',
            layout: {
                type: 'vbox',
                align: 'stretch'
            },
            items: [
                {
                    xtype: 'hiddenfield',
                    name: 'a_new_form',
                    submitValue: false,
                    validateOnChange: false,
                    value: true
                },
                {
                    xtype: 'container',
                    flex: 1,
                    focusOnToFront: false,
                    toFrontOnShow: false,
                    layout: 'anchor',
                    items: [
                        {
                            xtype: 'combobox',
                            anchor: '100%',
                            fieldLabel: 'Παράρτημα που αφορά',
                            labelWidth: 180,
                            name: 'branch1Id',
                            validateOnChange: false,
                            readOnly: false,
                            validateOnBlur: false,
                            allowBlank: false,
                            allowOnlyWhitespace: false,
                            //editable: false,
                            minChars: 0,
                            forceSelection: true,
                            queryParam: 'descr',
                            autoLoadOnValue: true,
                            displayField: 'rgEbrAddressStreetCombo',
                            store: 'company.COMP_BRANCES',
                            valueField: 'rgEbrBranchId',
                            listeners: {
                                beforequery: function(queryPlan, eOpts) {
                                    var field=queryPlan.combo;
                                    if (field.getValue()===null) {
                                        field.clearValue();
                                        field.getStore().getProxy().url = "/TEmployerBranchIKA/search/findBySession";
                                        field.getStore().getProxy().getReader().setRootProperty("_embedded.TEmployerBranchIKA");
                                    }
                                    else {
                                        field.getStore().getProxy().url = "/TEmployerBranchIKA/search/findByDescr";
                                        field.getStore().getProxy().getReader().setRootProperty("_embedded.TEmployerBranchIKA");
                                    }
                                }
                            }
                        }
                    ]
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
            dock: 'bottom',
            html: '<span style="color:#696969;font-size:11px;"><em>*Σε περίπτωση που υπάρχει ήδη ΓΕΕΚ<br>μπορείτε να την τροποποιήσετε</em></span>',
            id: 'compdangerbranch_check_toolbar',
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
                    itemId: 'savebutton',
                    maxWidth: 340,
                    padding: 10,
                    width: 150,
                    glyph: 'xf0c7@FontAwesome',
                    text: 'Έλεγχος',
                    listeners: {
                        click: 'onCheck_COMPANY_DANGER_BRANCH'
                    }
                }
            ]
        }
    ],

});