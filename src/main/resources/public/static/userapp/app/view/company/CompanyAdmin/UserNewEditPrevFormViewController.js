/*
 * File: app/view/company/CompanyAdmin/UserNewEditPrevFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.CompanyAdmin.UserNewEditPrevFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companycompanyadminuserneweditprevform',

    onSubmit_COMPANY_USER_CREATE: function(button, e, eOpts) {
        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formprevs = Ext.getCmp('companyuserprev'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues(),
            valuesprevs = formprevs.getValues();


        if (form.isValid() && formprevs.isValid()) {

            if (values.password===values.password2){
                var rest_method;
                rest_method = "POST";

                var successPrevs = function(options, success, response) {
                    formWindow.unmask();
                    if (Ext.JSON.decode(response.responseText).success) {

                        Ext.Msg.alert('Επιτυχία', 'Ο χρήστης δημιουργήθηκε με επιτυχία.');

                        formWindow.destroy();
                    }
                    else{

                        Ext.Msg.alert('Αποτυχία', 'Ο χρήστης δεν δημιουργήθηκε. '+
                        Ext.JSON.decode(response.responseText).error);

                    }


                };
                var successOrfailureCallback = function(options, success, response) {

                    if (Ext.JSON.decode(response.responseText).success) {
                        if (valuesprevs.userId==="")
                        valuesprevs.userId = Ext.JSON.decode(response.responseText).userId;
                        Ext.Ajax.request({
                            url: "/registerCompanyUserPrivilages/save",
                            params: {
                                userId: valuesprevs.userId,
                                serviceIds: valuesprevs.userprevs.toString(),
                                branchIds: ","+valuesprevs.branches.toString()+","
                            },
                            method: rest_method,
                            callback: successPrevs
                        });
                    }
                    else{
                        formWindow.unmask();
                        Ext.Msg.alert('Αποτυχία', 'Ο χρήστης δεν δημιουργήθηκε. '+
                        Ext.JSON.decode(response.responseText).error);

                    }


                };
                formWindow.mask("Παρακαλώ Περιμένετε...");

                Ext.Ajax.request({
                    url: "/registerCompanyUser/save",
                    headers: { 'Content-Type': 'application/json' },
                    jsonData: Ext.util.JSON.encode(values),
                    params: {
                        userId: valuesprevs.userId,
                        serviceIds: valuesprevs.userprevs.toString(),
                        branchIds: ","+valuesprevs.branches.toString()+","
                    },
                    method: rest_method,
                    callback: successOrfailureCallback
                });
            }
            else
            {
                form.getForm().findField('password2').markInvalid("Οι κωδικοί δεν είναι όμοιοι");
                Ext.Msg.alert('Αποτυχία', 'Ο χρήστης δεν δημιουργήθηκε. Διορθώστε τη φόρμα.');
            }
        }
        else{
            Ext.Msg.alert('Αποτυχία Υποβολής', 'Παρακαλώ διορθώστε τα λάθος πεδία');

        }

    },

    onDeactivate_COMPANY_USER: function(button, e, eOpts) {
        var view = this.getView(),
            formprevs = Ext.getCmp('companyuserprev'),
            formWindow = button.up('toolbar').up('window'),
            valuesprevs = formprevs.getValues();


        if (formprevs.isValid()) {

            var rest_method;
            rest_method = "POST";

            var successPrevs = function(options, success, response) {
                formWindow.unmask();
                if (Ext.JSON.decode(response.responseText).success) {

                    Ext.Msg.alert('Επιτυχία', 'Η πρόσβαση στις υπηρεσίες του χρήστη έχει ανασταλεί.');

                    formWindow.destroy();
                }
                else{

                    Ext.Msg.alert('Αποτυχία', 'Η πρόσβαση στις υπηρεσίες του χρήστη συνεχίζει να είναι ενεργή. '+
                    Ext.JSON.decode(response.responseText).error);

                }


            };
            formWindow.mask("Παρακαλώ Περιμένετε...");
            Ext.Ajax.request({
                url: "/companyUser/deactivate",
                params: {
                    userId: valuesprevs.userId
                },
                method: rest_method,
                callback: successPrevs
            });

        }
        else{
            Ext.Msg.alert('Αποτυχία Απενεργοποίησης', 'Κάτι δεν πήγε καλά. Επικοινωνήστε με τον διαχειριστή του συστήματος');

        }

    },

    onSave_COMPANY_USER_PREVS: function(button, e, eOpts) {
        var view = this.getView(),
            formprevs = Ext.getCmp('companyuserprev'),
            formWindow = button.up('toolbar').up('window'),
            valuesprevs = formprevs.getValues();


        if (formprevs.isValid()) {

            var rest_method;
            rest_method = "POST";

            var successPrevs = function(options, success, response) {
                formWindow.unmask();
                if (Ext.JSON.decode(response.responseText).success) {

                    Ext.Msg.alert('Επιτυχία', 'Τα δικαιώματα του χρήστη ενημερώθηκαν με επιτυχία.');

                    formWindow.destroy();
                }
                else{

                    Ext.Msg.alert('Αποτυχία', 'Τα δικαιώματα του χρήστη δεν ενημερώθηκαν'+
                    Ext.JSON.decode(response.responseText).error);

                }


            };
            formWindow.mask("Παρακαλώ Περιμένετε...");
            Ext.Ajax.request({
                url: "/registerCompanyUserPrivilages/save",
                params: {
                    userId: valuesprevs.userId,
                    serviceIds: valuesprevs.userprevs.toString(),
                    branchIds: ","+valuesprevs.branches.toString()+","
                },
                method: rest_method,
                callback: successPrevs
            });

        }
        else{
            Ext.Msg.alert('Αποτυχία Υποβολής', 'Παρακαλώ διορθώστε τα λάθος πεδία');

        }

    },

    onActivate_COMPANY_USER: function(button, e, eOpts) {
        var view = this.getView(),
            formprevs = Ext.getCmp('companyuserprev'),
            formWindow = button.up('toolbar').up('window'),
            valuesprevs = formprevs.getValues();


        if (formprevs.isValid()) {

            var rest_method;
            rest_method = "POST";

            var successPrevs = function(options, success, response) {
                formWindow.unmask();
                if (Ext.JSON.decode(response.responseText).success) {

                    Ext.Msg.alert('Επιτυχία', 'Η πρόσβαση στις υπηρεσίες του χρήστη έχει ενεργοποιηθεί. Οι υπηρεσίες έχουν ενημερωθεί.');

                    formWindow.destroy();
                }
                else{

                    Ext.Msg.alert('Αποτυχία', 'Η πρόσβαση στις υπηρεσίες του χρήστη συνεχίζει να είναι ανενεργή. '+
                    Ext.JSON.decode(response.responseText).error);

                }


            };
            formWindow.mask("Παρακαλώ Περιμένετε...");
            Ext.Ajax.request({
                url: "/registerCompanyUserPrivilages/save",
                params: {
                    userId: valuesprevs.userId,
                    serviceIds: valuesprevs.userprevs.toString(),
                    branchIds: ","+valuesprevs.branches.toString()+","
                },
                method: rest_method,
                callback: successPrevs
            });

        }
        else{
            Ext.Msg.alert('Αποτυχία Ενεργοποίησης', 'Κάτι δεν πήγε καλά. Επικοινωνήστε με τον διαχειριστή του συστήματος');

        }

    }

});
