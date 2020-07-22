/*
 * File: app/view/company/exypp/CompanyInfoViewFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.exypp.CompanyInfoViewFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companyexyppcompanyinfoviewform',

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
            formWindow.mask('Παρακαλώ Περιμένετε...');



            var successAns = function(options, success, response) {
                formWindow.unmask();
                if (Ext.JSON.decode(response.responseText).success) {

                    Ext.Msg.alert('Επιτυχής Αποδοχή', 'Η αποδοχή της αναγγελίας έγινε απόδεκτή. \nΗ αναγγελία μένει στην λίστα μέχρι να αποδεχτούν όλες οι πλευρές (αν υπαρχουν).');
                    formWindow.destroy();
                }
                else{
                    if (Ext.JSON.decode(response.responseText).visitConflict)
                    {
                        var msgDates = Ext.JSON.decode(response.responseText).conflicts.split(":");
                        var errorDates = msgDates[msgDates.length - 1].trim().split(" ");
                        Ext.suspendLayouts();
                        for (var i = 0; i < errorDates.length; i++)
                            Ext.ComponentQuery.query('timefield')[parseInt(errorDates[i])].markInvalid("Ο TA έχει άλλη επίσκεψη.");
                        Ext.resumeLayouts(true);
                        Ext.Msg.alert('Αποτυχία Αποδοχής', 'Κάποιες από τις επισκέψεις των τεχνικών επικαλύπτονται από άλλες που έχουν γίνει ήδη αποδεκτές.');
                    }
                    else if (Ext.JSON.decode(response.responseText).previousDates)
                    {
                        Ext.Msg.alert('Αποτυχία Αποδοχής', 'Υπάρχουν επισκέψεις με ημερομηνία προγενέστερη της σημερινής.');
                    }
                    else
                        Ext.Msg.alert('Αποτυχία Αποδοχής', 'Η αποδοχή της αναγγελίας δεν έγινε απόδεκτή.');

                }


            };

            annId = values.compTaAnnId;
            annTaId = values.compTaAnnTaId;

            if (values.visitDate.constructor == Array){
                for (var i = 0; i < values.visitDate.length; i++)
                {
                    values.visitDate[i] = new Date(values.visitDate[i]).toISOString();
                }
            }
            else values.visitDate = new Date(values.visitDate).toISOString();

            Ext.Ajax.request({
                url: '/cCompTaAnnRespond',
                params: {
                    'compTaAnnId': annId,
                    'compTaAnnTaId': annTaId,
                    'response': 1,
                    'dateStart': new Date(values.dateStart).toISOString(),
                    'dateEnd': new Date(values.dateEnd).toISOString(),
                    'technicianRegrequestUserIds': values.taAnnTaIdLocal,
                    'visitDate': values.visitDate,
                    'visitTime': values.visitTime,
                    'visitDurationMinutes': values.visitDurationMinutes
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

            annId = values.compTaAnnId;
            annTaId = values.compTaAnnTaId;

            if (values.visitDate.constructor == Array){
                for (var i = 0; i < values.visitDate.length; i++)
                {
                    values.visitDate[i] = new Date(values.visitDate[i]).toISOString();
                }
            }
            else values.visitDate = new Date(values.visitDate).toISOString();

            Ext.Ajax.request({
                url: '/cCompTaAnnRespond',
                params: {
                    'compTaAnnId': annId,
                    'compTaAnnTaId': annTaId,
                    'response': -1,
                    'dateStart': new Date(values.dateStart).toISOString(),
                    'dateEnd': new Date(values.dateEnd).toISOString(),
                    'technicianRegrequestUserIds': values.taAnnTaIdLocal,
                    'visitDate': values.visitDate,
                    'visitTime': values.visitTime,
                    'visitDurationMinutes': values.visitDurationMinutes
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

            annId = values.compTaAnnId;
            annTaId = values.compTaAnnTaId;
            Ext.Ajax.request({
                url: '/compTaAnnCessationRespond',
                params: {
                    'compTaAnnId': annId,
                    'compTaAnnTaId': annTaId,
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

            annId = values.compTaAnnId;
            annTaId = values.compTaAnnTaId;
            Ext.Ajax.request({
                url: '/compTaAnnCessationRespond',
                params: {
                    'compTaAnnId': annId,
                    'compTaAnnTaId': annTaId,
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

            annId = values.compTaAnnId;
            annTaId = values.compTaAnnTaId;
            Ext.Ajax.request({
                url: '/taAnnResign',
                params: {
                    'compTaAnnId': annId,
                    'compTaAnnTaId': annTaId
                },
                method: "POST",
                callback: successAns
            });

        };

    }

});
