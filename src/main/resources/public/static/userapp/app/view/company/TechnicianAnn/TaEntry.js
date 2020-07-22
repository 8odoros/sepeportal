/*
 * File: app/view/company/TechnicianAnn/TaEntry.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianAnn.TaEntry', {
    extend: 'Ext.form.FieldContainer',
    alias: 'widget.companytechniciananntaentry',

    requires: [
        'MyApp.view.company.TechnicianAnn.TaEntryViewModel',
        'Ext.form.FieldContainer',
        'Ext.form.field.ComboBox',
        'Ext.form.field.Hidden'
    ],

    viewModel: {
        type: 'companytechniciananntaentry'
    },
    padding: '0 5 5 5',
    layout: 'anchor',
    hideLabel: true,
    defaultListenerScope: true,

    items: [
        {
            xtype: 'fieldcontainer',
            focusOnToFront: false,
            padding: 10,
            toFrontOnShow: false,
            layout: {
                type: 'vbox',
                align: 'stretch'
            },
            items: [
                {
                    xtype: 'combobox',
                    fieldLabel: 'Ιδιότητα Συνεργασίας',
                    labelWidth: 180,
                    name: 'cooperationType',
                    validateOnChange: false,
                    validateOnBlur: false,
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    editable: false,
                    emptyText: 'Επιλέξτε για ενεργοποίηση του πεδίου Α.Φ.Μ.',
                    autoLoadOnValue: true,
                    displayField: 'description',
                    store: 'shared.COOPERATION_TYPE',
                    valueField: 'abbr',
                    listeners: {
                        change: 'onCooperationChange'
                    }
                },
                {
                    xtype: 'combobox',
                    hidden: true,
                    fieldLabel: 'Επιλογή ΕΞΥΠΠ',
                    labelWidth: 180,
                    name: 'exypp',
                    validateOnChange: false,
                    validateOnBlur: false,
                    allowOnlyWhitespace: false,
                    editable: false,
                    autoLoadOnValue: true,
                    displayField: 'description',
                    store: 'company.TechnicianAnn.EXYPP',
                    valueField: 'abbr',
                    listeners: {
                        change: 'onExyppChange'
                    }
                },
                {
                    xtype: 'textfield',
                    autofillTechFields: function(formPart, selectedrec) {
                        //Autofill for Doctor
                        var scrollpos = formPart.up('window').getScrollY();
                        if (selectedrec.get('technicianReqId')!==null){
                            formPart.getComponent(7).setValue(selectedrec.get('technicianReqId'));
                        }
                        if (selectedrec.get('technicianReqUserId')!==null){
                            formPart.getComponent(6).setValue(selectedrec.get('technicianReqUserId'));
                        }
                        
                        if (selectedrec.get('fullname')!==null){
                            formPart.getComponent(3).setValue(selectedrec.get('fullname'));
                        }

                        if (selectedrec.get('speciality')!==null){
                            try
                            {
                                formPart.getComponent(4).setValue(Ext.util.JSON.decode("["+selectedrec.get('speciality')+"]"));
                            }
                            catch (ex)
                            {
                                formPart.getComponent(4).store = Ext.getCmp('taAnnTaEntries').items.get(4).items.get(0).items.get(4).store; // Speciality Store
                                formPart.getComponent(4).setValue(Ext.util.JSON.decode("["+selectedrec.get('speciality')+"]"));
                            }
                        }

                        //if (parseInt(selectedrec.get('speciality'))===99){
                        if (selectedrec.get('speciality')!=null && selectedrec.get('speciality').indexOf('99') !== -1){
                            formPart.getComponent(5).setValue(selectedrec.get('specialityOther'));
                            formPart.getComponent(5).show();
                            formPart.getComponent(5).allowBlank=false;
                            formPart.getComponent(5).allowOnlyWhitespace=false;
                        }
                        else{
                            formPart.getComponent(5).setValue();
                            formPart.getComponent(5).hide();
                            formPart.getComponent(5).allowBlank=true;
                            formPart.getComponent(5).allowOnlyWhitespace=true;
                        }
                        
                        formPart.up('window').scrollTo(0, scrollpos);
                    },
                    fieldLabel: 'Α.Φ.Μ',
                    labelWidth: 180,
                    name: 'taAfm',
                    validateOnChange: false,
                    validateOnBlur: false,
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    emptyText: 'Καταχωρήστε το ΑΦΜ του Τεχνικού εγγεγραμμένου στο μητρώο',
                    //maxLength: 9,
                    //minLength: 9,
                    listeners: {
                        blur: 'onAFMBlur1',
                        specialkey: 'onAFMEnterKey1'
                    }
                },
                {
                    xtype: 'textfield',
                    fieldLabel: 'Ονοματεπώνυμο',
                    labelWidth: 180,
                    name: 'taFullname',
                    validateOnChange: false,
                    readOnly: true,
                    validateOnBlur: false,
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    maxLength: 200,
                    listeners: {
                        change: 'onFullnameChange'
                    }
                },
                {
                    xtype: 'combobox',
                    fieldLabel: 'Ειδικότητα',
                    labelWidth: 180,
                    name: 'taSpeciality',
                    validateOnChange: false,
                    readOnly: true,
                    validateOnBlur: false,
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    editable: false,
                    autoLoadOnValue: true,
                    multiSelect: true,
                    displayField: 'description',
                    valueField: 'abbr',
                    bind: {
                        store: '{TECHNICIAN_SPECIALITY}'
                    },
                    listeners: {
                        change: 'onSpecialitySelect1'
                    }
                },
                {
                    xtype: 'textfield',
                    hidden: true,
                    fieldLabel: '',
                    hideEmptyLabel: false,
                    labelWidth: 180,
                    name: 'taSpecialityOther',
                    validateOnChange: false,
                    value: '',
                    readOnly: true,
                    validateOnBlur: false,
                    emptyText: 'Λεκτικό Ειδικότητας',
                    maxLength: 50
                },
                {
                    xtype: 'hiddenfield',
                    width: 150,
                    labelWidth: 180,
                    name: 'technicianRegrequestUserId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    width: 150,
                    labelWidth: 180,
                    name: 'technicianRegrequestId',
                    validateOnChange: false
                }
            ]
        }
    ],

    onCooperationChange: function(field, newValue, oldValue, eOpts) {
         var scrollpos=Ext.getCmp('techAnnWinId').getScrollY();

                if (parseInt(newValue)===3 && parseInt(oldValue)!==3){
                    /*
                     field.up().up().down().getComponent(#)
                    * 0 cooperationType
                    * 1 exypp
                    * 2 taAfm
                    * 3 taFullname
                    * 4 taSpeciality
                    * 5 taSpecialityOther
                    * 6 technicianRegrequestUserId
                    * 7 technicianRegrequestId
                    * */

                    if(parseInt(Ext.getCmp('taAnnTaEntries').up('form').getForm().findField('taEntriesnum').getValue())>1 && parseInt(Ext.getCmp('taAnnTaEntries').up('form').getForm().findField('cooperationTypeBasic').getValue())!=3){
                        field.setValue('1');
                        Ext.Msg.alert('Προσοχή!', 'Δεν μπορείτε να επιλέξετε ΕΞΥΠΠ σε αυτό το στάδιο.');
                    }
                    else{
                        if(parseInt(Ext.getCmp('taAnnTaEntries').up('form').getForm().findField('cooperationTypeBasic').getValue())===1){
                            field.setValue('1');
                            Ext.Msg.alert('Προσοχή!', 'Δεν μπορείτε να επιλέξετε ΕΞΥΠΠ σε αυτό το στάδιο.');
                        }
                        else {
                            field.up().up().down().getComponent(1).show();
                            field.up().up().down().getComponent(1).allowBlank = false;
                            field.up().up().down().getComponent(2).setReadOnly(true);
                            field.up().up().down().getComponent(4).setValue();
                            field.up().up().down().getComponent(4).allowBlank = true;
                            field.up().up().down().getComponent(4).hide();
                        }
                    }
                }
                else{

                    field.up().up().down().getComponent(1).hide();
                    field.up().up().down().getComponent(1).allowBlank=true;
                    field.up().up().down().getComponent(1).setValue();
                    field.up().up().down().getComponent(2).setReadOnly(false);
                    field.up().up().down().getComponent(4).show();
                    field.up().up().down().getComponent(4).allowBlank=false;
                    if(parseInt(oldValue)===3 && parseInt(newValue)!==3){
                        field.up().up().down().getComponent(3).setValue();
                        field.up().up().down().getComponent(2).setValue();
                        field.up().up().down().getComponent(6).setValue();
                        field.up().up().down().getComponent(7).setValue();
                    }
                }

        Ext.getCmp('taAnnTaEntries').up('form').getForm().findField('taEntriesnum').setValue(parseInt(Ext.getCmp('taAnnTaEntries').items.length - 4));


        Ext.getCmp('techAnnWinId').scrollTo(0, scrollpos);
    },

    onExyppChange: function(field, newValue, oldValue, eOpts) {
        if(parseInt(field.up().up().down().getComponent(0).getValue())===3){
            if (field.getSelectedRecord() != null){
                if(field.getSelectedRecord().getData().rgEmpEmployerStatus===1){
                    field.up().up().down().getComponent(3).setValue(field.getSelectedRecord().getData().rgEmpFullname);
                    field.up().up().down().getComponent(2).setValue(field.getSelectedRecord().getData().rgEmpTaxationNumber);
                    field.up().up().down().getComponent(4).setValue();
                    field.up().up().down().getComponent(5).setValue();
                    field.up().up().down().getComponent(6).setValue(field.getSelectedRecord().getData().abbr);
                //field.up().up().down().getComponent(7).setValue(field.getSelectedRecord().getData().rgEmpEmployerId);

                }
        else{
            var scrollpos=field.up('window').getScrollY();
            newValue=oldValue;
            Ext.Msg.alert('Πρόβλημα ΕΞΥΠΠ', 'Η ΕΞΥΠΠ είναι ανενεργή. Επιλέξτε κάποια ενεργή');
            field.up('window').scrollTo(0, scrollpos);
        }
            }
        }
    },

    onAFMBlur1: function(component, event, eOpts) {
        var scrollpos=component.up('window').getScrollY();
        var cT = parseInt(component.up().up().down().getComponent(0).getValue());
        if( cT!==3 ){
            component.up().up().down().getComponent(4).allowBlank=false;
            component.up().up().down().getComponent(4).show();

            if (component.getValue().length>0){
                var store = Ext.StoreMgr.lookup( 'company.TECHNICIAN_INFO' );
                var formPart = component.up().up().down();
                var branchSectorId = component.up('form').getForm().findField('branchSectorId').getValue();
                var taSum = component.up('form').getForm().findField('taEntriesnum').getValue();
                store.getProxy().extraParams = {afm:component.getValue(), ta:'ta', branchSectorId: branchSectorId, taSum: taSum};
                store.load({callback: function(records, operation, success) {
                    if (records[0].get('success')) {
                        component.autofillTechFields(formPart,records[0]);
                    } else {
                        Ext.Msg.alert('Πρόβλημα', records[0].get('error'));
                        formPart.getComponent(3).setValue();
                        formPart.getComponent(4).setValue();
                        formPart.getComponent(5).hide();
                        formPart.getComponent(5).allowBlank=true;
                        formPart.getComponent(5).allowOnlyWhitespace=true;
                        formPart.getComponent(5).setValue();
                        formPart.getComponent(6).setValue();
                        formPart.getComponent(7).setValue();
                        component.setValue("");
                        component.up('window').scrollTo(0, scrollpos);
                    }
                }});
            }
            else
            {
                var formPart = component.up().up().down();
                component.setValue("");
                formPart.getComponent(3).setValue();
                formPart.getComponent(4).setValue();
                formPart.getComponent(5).hide();
                formPart.getComponent(5).allowBlank=true;
                formPart.getComponent(5).allowOnlyWhitespace=true;
                formPart.getComponent(5).setValue();
                formPart.getComponent(6).setValue();
                formPart.getComponent(7).setValue();
            }

        }
        else{
            component.up().up().down().getComponent(4).setValue();
            component.up().up().down().getComponent(4).allowBlank=true;
            component.up().up().down().getComponent(4).hide();
        }
        component.up('window').scrollTo(0, scrollpos);
    },

    onAFMEnterKey1: function(field, e, eOpts) {
        if (e.getKey() == e.ENTER) {

        field.blur();

        }
    },

    onSpecialitySelect1: function(field, newValue, oldValue, eOpts) {
        var combo = field;
        var scrollpos=combo.up('window').getScrollY();
        if (parseInt(newValue)===99){
                    combo.up().up().down().getComponent(5).show();
                    combo.up().up().down().getComponent(5).allowBlank=false;
                    combo.up().up().down().getComponent(5).allowOnlyWhitespace=false;
                }
                else{
                    combo.up().up().down().getComponent(5).setValue();
                    combo.up().up().down().getComponent(5).hide();
                    combo.up().up().down().getComponent(5).allowBlank=true;
                    combo.up().up().down().getComponent(5).allowOnlyWhitespace=true;
                }
        combo.up('window').scrollTo(0, scrollpos);
    },

    onFullnameChange: function(field, newValue, oldValue, eOpts) {
        var storeLocal = Ext.StoreMgr.lookup('company.TechnicianAnn.LocalTaEntries');
        var taS = Ext.getCmp('taAnnTaEntries');
        var tasEntriesArr = [];
        var tot = Ext.getCmp('taAnnTaEntries').items.length-4;
        if (parseInt(tot) > 0) {
            for (var j = 0; j < parseInt(tot); j++) {
                if(taS.items.getAt(j+4).items.getAt(0).items.getAt(6)!==null){
                    if(taS.items.getAt(j+4).items.getAt(0).items.getAt(0).getValue()!=='3') {
                        tasEntriesArr.push({
                            id: taS.items.getAt(j + 4).items.getAt(0).items.getAt(6).getValue(),
                            name: taS.items.getAt(j + 4).items.getAt(0).items.getAt(3).getValue()
                        });
                    }
                }
            }
            storeLocal.loadData(tasEntriesArr,false);
        }
    }

});