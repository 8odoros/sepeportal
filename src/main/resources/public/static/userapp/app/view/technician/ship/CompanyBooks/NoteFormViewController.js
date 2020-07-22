/*
 * File: app/view/technician/ship/CompanyBooks/NoteFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.technician.ship.CompanyBooks.NoteFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.technicianshipcompanybooksnoteform',

    onSubmit_COMPANY_TECH_BOOK: function(button, e, eOpts) {
        var conffun = function(buttonText) {
            if (buttonText == "yes") {
                submitfun();
            }
            if (buttonText == "no") {
                return false;
            }
        };

        var msb = Ext.MessageBox.confirm('Καταχώρηση Υπόδειξης', 'Είστε σίγουροι ότι θελετε να γίνει η καταχώρηση στο βιβλίο; ', conffun);

        var submitfun = function(){
            var form = button.up('toolbar').up('window').down('form'),
                formWindow = button.up('toolbar').up('window'),
                values = form.getValues();


            if (form.isValid()) {
                var rest_method;

                rest_method = "POST";

                var successCall = function(options, success, response) {
                    formWindow.unmask();
                    if (Ext.JSON.decode(response.responseText).success) {

                        Ext.Msg.alert('Επιτυχία', 'Η υπόδειξη έχει καταχωρηθεί στο βιβλίο.');
                        var book = Ext.getCmp('technicianShipCompanyNotes_Book');
                        book.getView().store.proxy.setUrl('/compTaSannBookNote/search/findByCompTaSannId?compTaSannId='+values.compTaSannId);
                        book.store.load( { callback : function(records, operation, success) {
                            book.getView().refresh();
                            formWindow.destroy();
                        }
                    });
                }
                else{

                    Ext.Msg.alert('Αποτυχία', 'Ο οργανισμός δεν έχει ενεργό βιβλίο. Επικοινωνήστε με τον οργανισμό για ενεργοποίηση.');

                }

            };
            formWindow.mask("Παρακαλώ Περιμένετε...");
            Ext.Ajax.request({
                url: "/technicianSannBookNoteAdd",
                params: {
                    compTaSannId: values.compTaSannId,
                    shipId: values.shipId,
                    notes: values.notes
                },
                method: rest_method,
                callback: successCall
            });



        }
        else{
            Ext.Msg.alert('Αποτυχία Υποβολής', 'Παρακαλώ διορθώστε τα λάθος πεδία');

        }
    };
    }

});
