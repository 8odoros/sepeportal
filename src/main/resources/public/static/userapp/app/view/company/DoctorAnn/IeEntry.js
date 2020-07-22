/*
 * File: app/view/company/DoctorAnn/IeEntry.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.DoctorAnn.IeEntry', {
    extend: 'Ext.form.FieldContainer',
    alias: 'widget.companydoctorannieentry',

    requires: [
        'MyApp.view.company.DoctorAnn.IeEntryViewModel',
        'Ext.form.FieldContainer',
        'Ext.form.field.ComboBox',
        'Ext.form.field.Hidden'
    ],

    viewModel: {
        type: 'companydoctorannieentry'
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
                        if (selectedrec.get('fullname')!==null){
                            formPart.getComponent(3).setValue(selectedrec.get('fullname'));
                        }

                        if (selectedrec.get('speciality')!==null){
                            formPart.getComponent(4).setValue(selectedrec.get('speciality').toString());
                        }

                        if (parseInt(selectedrec.get('speciality'))===99){
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

                        if (selectedrec.get('doctorReqId')!==null){
                            formPart.getComponent(7).setValue(selectedrec.get('doctorReqId'));
                        }
                        if (selectedrec.get('doctorReqUserId')!==null){
                            formPart.getComponent(6).setValue(selectedrec.get('doctorReqUserId'));
                        }
                        formPart.up('window').scrollTo(0, scrollpos);
                    },
                    fieldLabel: 'Α.Φ.Μ',
                    labelWidth: 180,
                    name: 'ieAfm',
                    validateOnChange: false,
                    validateOnBlur: false,
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    emptyText: 'Καταχωρήστε του ΑΦΜ του Τεχνικού εγγεγραμμένου στο μητρώο',
                    maxLength: 200,
                    listeners: {
                        blur: 'onAFMBlur1',
                        specialkey: 'onAFMEnterKey1'
                    }
                },
                {
                    xtype: 'textfield',
                    fieldLabel: 'Ονοματεπώνυμο',
                    labelWidth: 180,
                    name: 'ieFullname',
                    validateOnChange: false,
                    readOnly: true,
                    validateOnBlur: false,
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    maxLength: 200
                },
                {
                    xtype: 'combobox',
                    fieldLabel: 'Ειδικότητα',
                    labelWidth: 180,
                    name: 'ieSpeciality',
                    validateOnChange: false,
                    readOnly: true,
                    validateOnBlur: false,
                    //allowBlank: false,
                    //allowOnlyWhitespace: false,
                    editable: false,
                    autoLoadOnValue: true,
                    displayField: 'description',
                    valueField: 'abbr',
                    bind: {
                        store: '{DOCTOR_SPECIALITY}'
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
                    name: 'ieSpecialityOther',
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
                    name: 'doctorRegrequestUserId',
                    validateOnChange: false
                },
                {
                    xtype: 'hiddenfield',
                    width: 150,
                    labelWidth: 180,
                    name: 'doctorRegrequestId',
                    validateOnChange: false
                }
            ]
        }
    ],

    onCooperationChange: function(field, newValue, oldValue, eOpts) {
         var scrollpos=Ext.getCmp('doctorAnnWinId').getScrollY();

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

                    if(parseInt(Ext.getCmp('ieAnnIeEntries').up('form').getForm().findField('ieEntriesnum').getValue())>1){
                        field.setValue('1');
                        Ext.Msg.alert('Προσοχή!', 'Δεν μπορείτε να επιλέξετε ΕΞΥΠΠ σε αυτό το στάδιο.');
                    }
                    else{
                        if(parseInt(Ext.getCmp('ieAnnIeEntries').up('form').getForm().findField('cooperationTypeBasic').getValue())===1){
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

        Ext.getCmp('ieAnnIeEntries').up('form').getForm().findField('ieEntriesnum').setValue(parseInt(Ext.getCmp('ieAnnIeEntries').items.length - 4));


        Ext.getCmp('doctorAnnWinId').scrollTo(0, scrollpos);
    },

    onExyppChange: function(field, newValue, oldValue, eOpts) {
        if(parseInt(field.up().up().down().getComponent(0).getValue())===3){
                if(field.getSelectedRecord().getData().rgEmpEmployerStatus===1){
                    field.up().up().down().getComponent(3).setValue(field.getSelectedRecord().getData().rgEmpFullname);
                    field.up().up().down().getComponent(2).setValue(field.getSelectedRecord().getData().rgEmpTaxationNumber);
                    field.up().up().down().getComponent(4).setValue();
                    field.up().up().down().getComponent(5).setValue();
                    field.up().up().down().getComponent(6).setValue(field.getSelectedRecord().getData().abbr);
                field.up().up().down().getComponent(7).setValue(field.getSelectedRecord().getData().rgEmpEmployerId);

                }
        else{
            var scrollpos=field.up('window').getScrollY();
            newValue=oldValue;
            Ext.Msg.alert('Πρόβλημα ΕΞΥΠΠ', 'Η ΕΞΥΠΠ είναι ανενεργή. Επιλέξτε κάποια ενεργή');
            field.up('window').scrollTo(0, scrollpos);
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
                var store = Ext.StoreMgr.lookup( 'company.DOCTOR_INFO' );
                var formPart = component.up().up().down();
                store.getProxy().extraParams = {afm:component.getValue()};
                store.load({callback: function(records, operation, success) {
                    if (records[0].get('success')) {
                        component.autofillTechFields(formPart,records[0]);
                    } else {
                        Ext.Msg.alert('Πρόβλημα Α.Φ.Μ.', 'Το Α.Φ.Μ δεν αντιστοιχεί σε κάποιo Ιατρό Εργασίας');
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
    }

});