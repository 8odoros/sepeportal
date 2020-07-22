/*
 * File: app/view/company/TechnicianAnn/ShipTechFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.technician.ship.ShipTechFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companytechnicianannshiptechform',

    onAccept_COMPANY_ANN: function(button, e, eOpts) {
        var conffun = function(buttonText) {
            if (buttonText == "yes") {
                submitfun();
            }
            if (buttonText == "no") {
                return false;
            }
        };

        var msb = Ext.MessageBox.confirm('Αποδοχή Αναγγελίας', 'Είστε σίγουροι ότι θέλετε να αποδεχτείτε την αναγγελία; ', conffun);

        var submitfun = function(){
            var form = button.up('toolbar').up('window').down('form'),
                formWindow = button.up('toolbar').up('window'),
                values = form.getValues();



            var successAns = function(options, success, response) {
                if (Ext.JSON.decode(response.responseText).success) {

                    Ext.Msg.alert('Επιτυχής Αποδοχή', 'Η αποδοχή της αναγγελίας έγινε απόδεκτή.');
                    formWindow.destroy();
                }
                else{
                    Ext.Msg.alert('Αποτυχία Αποδοχής', 'Η αποδοχή της αναγγελίας δεν έγινε απόδεκτή.');

                }


            };

            annId = values.id;
            Ext.Ajax.request({
                url: '/cCompTaSannRespond',
                params: {
                    'compTaSannId': annId,
                    'response': 1
                },
                method: "POST",
                callback: successAns
            });

        };

    },

    onReject_COMPANY_ANN: function(button, e, eOpts) {
        var conffun = function(buttonText) {
            if (buttonText == "yes") {
                submitfun();
            }
            if (buttonText == "no") {
                return false;
            }
        };

        var msb = Ext.MessageBox.confirm('Απόρριψη Αναγγελίας', 'Είστε σίγουροι ότι θέλετε να απορρίψετε την αναγγελία; ', conffun);

        var submitfun = function(){
            var form = button.up('toolbar').up('window').down('form'),
                formWindow = button.up('toolbar').up('window'),
                values = form.getValues();


            var successAns = function(options, success, response) {
                if (Ext.JSON.decode(response.responseText).success) {
                    Ext.Msg.alert('Επιτυχής Απόρριψη', 'Η απόρριψη της αναγγελίας έγινε απόδεκτή.');
                    formWindow.destroy();
                }
                else{
                    Ext.Msg.alert('Αποτυχία Απόρριψης', 'Η απόρριψη της αναγγελίας δεν έγινε απόδεκτή.');

                }


            };

            annId = values.compTaSannId;
            Ext.Ajax.request({
                url: '/cCompTaSannRespond',
                params: {
                    'compTaSannId': annId,
                    'response': -1
                },
                method: "POST",
                callback: successAns
            });
        };

    },

    onAcceptReplace_COMPANY_ANN: function(button, e, eOpts) {
        var conffun = function(buttonText) {
            if (buttonText == "yes") {
                submitfun();
            }
            if (buttonText == "no") {
                return false;
            }
        };

        var msb = Ext.MessageBox.confirm('Απάντηση Παύσης', 'Είστε σίγουροι ότι θέλετε να δηλώσετε παραίτηση; ', conffun);

        var submitfun = function(){
            var form = button.up('toolbar').up('window').down('form'),
                formWindow = button.up('toolbar').up('window'),
                values = form.getValues();



            var successAns = function(options, success, response) {
                if (Ext.JSON.decode(response.responseText).success) {

                    Ext.Msg.alert('Επιτυχής Καταχώρηση Απάντησης', 'Η απάντηση σας έγινε απόδεκτή.');
                    formWindow.destroy();
                }
                else{
                    Ext.Msg.alert('Αποτυχία Καταχώρησης Απάντησης', 'Η απάντηση σας δεν έγινε απόδεκτή.');

                }


            };

            annId = values.compTaSannId;
            Ext.Ajax.request({
                url: '/compTaSannCessationRespond',
                params: {
                    'compTaSannId': annId,
                    'response': 0
                },
                method: "POST",
                callback: successAns
            });
        };

    },

    onResignationReplace_COMPANY_ANN: function(button, e, eOpts) {
        var conffun = function(buttonText) {
            if (buttonText == "yes") {
                submitfun();
            }
            if (buttonText == "no") {
                return false;
            }
        };

        var msb = Ext.MessageBox.confirm('Απάντηση Παύσης', 'Είστε σίγουροι ότι θέλετε να δηλώσετε απόλυση; ', conffun);

        var submitfun = function(){
            var form = button.up('toolbar').up('window').down('form'),
                formWindow = button.up('toolbar').up('window'),
                values = form.getValues();



            var successAns = function(options, success, response) {
                if (Ext.JSON.decode(response.responseText).success) {

                    Ext.Msg.alert('Επιτυχής Καταχώρηση Απάντησης', 'Η απάντηση σας έγινε απόδεκτή.');
                    formWindow.destroy();
                }
                else{
                    Ext.Msg.alert('Αποτυχία Καταχώρησης Απάντησης', 'Η απάντηση σας δεν έγινε απόδεκτή.');

                }


            };

            annId = values.compTaSannId;
            Ext.Ajax.request({
                url: '/compTaSannCessationRespond',
                params: {
                    'compTaSannId': annId,
                    'response': 1
                },
                method: "POST",
                callback: successAns
            });
        };

    },

    onResignation_COMPANY_ANN: function(button, e, eOpts) {
        var conffun = function(buttonText) {
            if (buttonText == "yes") {
                submitfun();
            }
            if (buttonText == "no") {
                return false;
            }
        };

        var msb = Ext.MessageBox.confirm('Παύση Παροχής Υπηρεσιών', 'Είστε σίγουροι ότι θέλετε να παραιτηθείτε από τα καθήκοντα σας;', conffun);

        var submitfun = function(){
            var form = button.up('toolbar').up('window').down('form'),
                formWindow = button.up('toolbar').up('window'),
                values = form.getValues();



            var successAns = function(options, success, response) {
                if (Ext.JSON.decode(response.responseText).success) {

                    Ext.Msg.alert('Επιτυχής Παύση', 'Η παύση από τα καθήκοντα σας έγινε με επιτυχία.');
                    formWindow.destroy();
                }
                else{
                    Ext.Msg.alert('Αποτυχία Παύσης', 'Η παύση από τα καθήκοντα σας δεν έγινε απόδεκτή.');

                }


            };

            annId = values.compTaSannId;

            Ext.Ajax.request({
                url: '/taSannResign',
                params: {
                    'compTaSannId': annId
                },
                method: "POST",
                callback: successAns
            });

        };

    }

});
