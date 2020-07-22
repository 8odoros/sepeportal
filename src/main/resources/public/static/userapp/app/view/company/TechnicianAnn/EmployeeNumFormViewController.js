/*
 * File: app/view/company/TechnicianAnn/EmployeeNumFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianAnn.EmployeeNumFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companytechnicianannemployeenumform',

    onSave_COMPANY_TECHNICIAN_ANN_EMPLS_NUM: function(button, e, eOpts) {

        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();



        values.sumEmplA=values.categANum;
        values.sumEmplB=values.categBNum;
        values.sumEmplC=values.categCNum;
        values.sumEmplTotal=Number(values.categANum)+Number(values.categBNum)+Number(values.categCNum);
        values.isTaNoneEmplrEmple=values.isTaNoneEmployerEmployee;

        if (form.isValid()) {
            var rest_method;

            rest_method = "POST";

            var successCall = function(options, success, response) {
                formWindow.unmask();
                if (Ext.JSON.decode(response.responseText).success) {

                    Ext.Msg.alert('Επιτυχία Ενημέρωσης Στοιχείων', 'Τα στοιχεία του Οργανισμού ενημερώθηκαν επιτυχώς');

                    formWindow.destroy();

                }
                else{

                    Ext.Msg.alert('Αποτυχία Ενημέρωσης',  Ext.JSON.decode(response.responseText).error);

                }

            };
            formWindow.mask("Παρακαλώ Περιμένετε...");
            Ext.Ajax.request({
                url: "/companyExtraInfo/save",
                headers: { 'Content-Type': 'application/json' },
                params: Ext.util.JSON.encode(values),
                method: rest_method,
                callback: successCall
            });



        }
        else{
            Ext.Msg.alert('Αποτυχία Υποβολής', 'Παρακαλώ διορθώστε τα λάθος πεδία');

        }
    }

});
