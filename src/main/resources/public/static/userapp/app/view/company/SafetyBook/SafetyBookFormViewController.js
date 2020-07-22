/*
 * File: app/view/company/SafetyBook/SafetyBookFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.SafetyBook.SafetyBookFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companysafetybooksafetybookform',

    onDelete_COMPANY_SAFETYBOOK: function(button, e, eOpts) {

        var form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        var conffun = function(buttonText) {
            if (buttonText == "yes") {
                if(values.subStatus==="1" && form.getForm().findField("a_new_form").getValue()==="false"){
                    var res = values.url.split("/");
                    var fid = res[res.length-1];
                    var rest_method = "DELETE";
                    fid = fid + "/";
                    var successCallback = function(resp, ops) {

                        Ext.Msg.alert('Επιτυχής Διαγραφή', 'Η εγγραφή διαγράφηκε');

                        // Close window
                        formWindow.destroy();

                    };

                    // Failure
                    var failureCallback = function(resp, ops) {
                        Ext.Msg.alert('Αποτυχία Διαγραφής', 'Η εγγραφή δεν διαγράφηκε');

                    };

                    Ext.Ajax.request({
                        url: "/compSecDiary/" + fid,
                        headers: { 'Content-Type': 'application/json' },
                        method: rest_method,
                        success: successCallback,
                        failure: failureCallback
                    });
                }
                else{
                    Ext.Msg.alert('Αποτυχία', 'Η φόρμα δεν έπρεπε να φθάσει σε αυτή την κατάσταση');
                }
            }
            if (buttonText == "no") {
            }
        };

        var msb = Ext.MessageBox.confirm('Διαγραφή φόρμας', 'Είστε σίγουροι ότι θέλετε να διαγράψετε τη φόρμα;', conffun);
    },

    field_validation: function (form) {

        var form = form.getForm();
        var values = form.getValues();

        var invalidations = false;

        for (var i in values)
        {
            form.findField(i).clearInvalid();
            if (form.findField(i).getValue() != "" && form.findField(i).getValue() != null)
                if (!form.findField(i).isValid())
                    invalidations =  true;
        }
        return !invalidations;
    },

    onSave_COMPANY_SAFETYBOOK: function(button, e, eOpts) {
        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        if (form.isValid()) {
            var fid, rest_method;

            if (values.url===""){
                fid = "";
                rest_method = "POST";
            }
            else
            {
                var nurl = values.url;
                var res = nurl.split("/");
                var fid = res[res.length-1];
                fid = fid + "/";
                rest_method = "PUT";
            }

            // Success
            var successCallback = function(resp, ops) {

                Ext.Msg.alert('Επιτυχής Αποθήκευση', 'Το ημερολόγιο αποθηκεύθηκε με επιτυχία. Για να είν αι έτοιμο προς χρήση πρέπει να το υποβάλετε.');
                formWindow.destroy();

            };

            // Failure
            var failureCallback = function(resp, ops) {

                Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Η γνωστοποίηση δεν έγινε δεκτή από το σύστημα.');

            };

            values.protDate=button.up('toolbar').getCurrentTimestamp(1);
            values.submitTime=button.up('toolbar').getCurrentTimestamp(2);
            values.protYear=button.up('toolbar').getCurrentTimestamp(3);

            values.subStatus=1;

            var engsArr = [];
            if (parseInt(values.projscontrsnum)>1){
                for (var j = 0; j < (parseInt(values.projsengsnum)); j++) {
                    engsArr.push({
                        firstname: values.eng_firstname[j],
                        lastname: values.eng_lastname[j],
                        afm:  values.eng_afm[j],
                        addr: values.eng_addr[j],
                        engineerSpeciality: values.eng_engineerSpeciality[j],
                        birthplace: values.eng_birthplace[j],
                        birthdate: values.eng_birthdate[j],
                        cardNumber: values.eng_cardNumber[j],
                        cardIssuingAuth: values.eng_cardIssuingAuth[j],
                        teeNum: values.eng_teeNum[j],
                        cardType: values.eng_cardType[j]
                    });
                }
            }
            else{
                engsArr.push({
                    firstname: values.eng_firstname,
                    lastname: values.eng_lastname,
                    afm:  values.eng_afm,
                    addr: values.eng_addr,
                    engineerSpeciality: values.eng_engineerSpeciality,
                    birthplace: values.eng_birthplace,
                    birthdate: values.eng_birthdate,
                    cardNumber: values.eng_cardNumber,
                    cardIssuingAuth: values.eng_cardIssuingAuth,
                    teeNum: values.eng_teeNum,
                    cardType: values.eng_cardType
                });
            }

            delete values.eng_firstname;
            delete values.eng_lastname;
            delete values.eng_afm;
            delete values.eng_addr;
            delete values.eng_engineerSpeciality;
            delete values.eng_birthplace;
            delete values.eng_birthdate;
            delete values.eng_cardNumber;
            delete values.eng_cardIssuingAuth;
            delete values.eng_teeNum;
            delete values.eng_cardType;

            var contrsArr = [];
            if (parseInt(values.projscontrsnum)>1){
                for (var j = 0; j < (parseInt(values.projscontrsnum)); j++) {
                    contrsArr.push({
                        firstname: values.contr_firstname[j],
                        lastname: values.contr_lastname[j],
                        afm:  values.contr_afm[j],
                        addr: values.contr_addr[j],
                        contractorSpecialty: values.contr_specialty[j],
                        birthplace: values.contr_birthplace[j],
                        birthdate: values.contr_birthdate[j],
                        cardNumber: values.contr_cardNumber[j],
                        cardIssuingAuth: values.contr_cardIssuingAuth[j],
                        type: values.contr_type[j],
                        dateStart: values.contr_dateStart[j],
                        dateEnd: values.contr_dateEnd[j],
                        cardType: values.contr_cardType[j]
                    });
                }
            }
            else{
                contrsArr.push({
                    firstname: values.contr_firstname,
                    lastname: values.contr_lastname,
                    afm:  values.contr_afm,
                    addr: values.contr_addr,
                    contractorSpecialty: values.contr_specialty,
                    birthplace: values.contr_birthplace,
                    birthdate: values.contr_birthdate,
                    cardNumber: values.contr_cardNumber,
                    cardIssuingAuth: values.contr_cardIssuingAuth,
                    type: values.contr_type,
                    startDate: values.contr_dateStart,
                    endDate: values.contr_dateEnd,
                    cardType: values.contr_cardType
                });
            }

            delete values.contr_firstname;
            delete values.contr_lastname;
            delete values.contr_afm;
            delete values.contr_addr;
            delete values.contr_type;
            delete values.contr_specialty;
            delete values.contr_birthplace;
            delete values.contr_birthdate;
            delete values.contr_cardNumber;
            delete values.contr_cardIssuingAuth;
            delete values.contr_dateStart;
            delete values.contr_dateEnd;
            delete values.contr_cardType;

            values.projscontrs = contrsArr;
            values.projsengs = engsArr;


            Ext.Ajax.request({
                url: "/compSecDiary/" + fid,
                headers: { 'Content-Type': 'application/json' },
                jsonData: Ext.util.JSON.encode(values),
                method: rest_method,
                success: successCallback,
                failure: failureCallback
            });

        }
        else{
            Ext.Msg.alert('Αποτυχία Αποθήκευσης', 'Παρακαλώ διορθώστε τα λάθος πεδία');

        }
    },

    onSubmit_COMPANY_SAFETYBOOK: function(button, e, eOpts) {
        var view = this.getView(),
            form = button.up('toolbar').up('window').down('form'),
            formWindow = button.up('toolbar').up('window'),
            values = form.getValues();

        if (form.isValid()) {
            var fid, rest_method;

            if (values.url===""){
                fid = "";
                rest_method = "POST";
            }
            else
            {
                var nurl = values.url;
                var res = nurl.split("/");
                var fid = res[res.length-1];
                fid = fid + "/";
                rest_method = "PUT";
            }

            // Success
            var successCallback = function(resp, ops) {

                Ext.Msg.alert('Επιτυχής Υποβολή', 'Το ημερολόγιο δημιουργήθηκε με επιτυχία.'+
                'Μπορούν να γίνουν καταχωρίσεις.');
                formWindow.destroy();

            };

            // Failure
            var failureCallback = function(resp, ops) {

                Ext.Msg.alert('Αποτυχία Υποβολής', 'Το ημερολόγιο δεν έγινε δεκτή από το σύστημα.');

            };

            values.creationDateDate=button.up('toolbar').getCurrentTimestamp(1);
            values.creationYear=button.up('toolbar').getCurrentTimestamp(3);

            values.subStatus=2;

            var engsArr = [];
            if (parseInt(values.projscontrsnum)>1){
                for (var j = 0; j < (parseInt(values.projsengsnum)); j++) {
                    engsArr.push({
                        firstname: values.eng_firstname[j],
                        lastname: values.eng_lastname[j],
                        afm:  values.eng_afm[j],
                        addr: values.eng_addr[j],
                        engineerSpeciality: values.eng_engineerSpeciality[j],
                        birthplace: values.eng_birthplace[j],
                        birthdate: values.eng_birthdate[j],
                        cardNumber: values.eng_cardNumber[j],
                        cardIssuingAuth: values.eng_cardIssuingAuth[j],
                        teeNum: values.eng_teeNum[j],
                        cardType: values.eng_cardType[j]
                    });
                }
            }
            else{
                engsArr.push({
                    firstname: values.eng_firstname,
                    lastname: values.eng_lastname,
                    afm:  values.eng_afm,
                    addr: values.eng_addr,
                    engineerSpeciality: values.eng_engineerSpeciality,
                    birthplace: values.eng_birthplace,
                    birthdate: values.eng_birthdate,
                    cardNumber: values.eng_cardNumber,
                    cardIssuingAuth: values.eng_cardIssuingAuth,
                    teeNum: values.eng_teeNum,
                    cardType: values.eng_cardType
                });
            }

            delete values.eng_firstname;
            delete values.eng_lastname;
            delete values.eng_afm;
            delete values.eng_addr;
            delete values.eng_engineerSpeciality;
            delete values.eng_birthplace;
            delete values.eng_birthdate;
            delete values.eng_cardNumber;
            delete values.eng_cardIssuingAuth;
            delete values.eng_teeNum;
            delete values.eng_cardType;

            var contrsArr = [];
            if (parseInt(values.projscontrsnum)>1){
                for (var j = 0; j < (parseInt(values.projscontrsnum)); j++) {
                    contrsArr.push({
                        firstname: values.contr_firstname[j],
                        lastname: values.contr_lastname[j],
                        afm:  values.contr_afm[j],
                        addr: values.contr_addr[j],
                        contractorSpecialty: values.contr_specialty[j],
                        birthplace: values.contr_birthplace[j],
                        birthdate: values.contr_birthdate[j],
                        cardNumber: values.contr_cardNumber[j],
                        cardIssuingAuth: values.contr_cardIssuingAuth[j],
                        type: values.contr_type[j],
                        dateStart: values.contr_dateStart[j],
                        dateEnd: values.contr_dateEnd[j],
                        cardType: values.contr_cardType[j]
                    });
                }
            }
            else{
                contrsArr.push({
                    firstname: values.contr_firstname,
                    lastname: values.contr_lastname,
                    afm:  values.contr_afm,
                    addr: values.contr_addr,
                    contractorSpecialty: values.contr_specialty,
                    birthplace: values.contr_birthplace,
                    birthdate: values.contr_birthdate,
                    cardNumber: values.contr_cardNumber,
                    cardIssuingAuth: values.contr_cardIssuingAuth,
                    type: values.contr_type,
                    startDate: values.contr_dateStart,
                    endDate: values.contr_dateEnd,
                    cardType: values.contr_cardType
                });
            }

            delete values.contr_firstname;
            delete values.contr_lastname;
            delete values.contr_afm;
            delete values.contr_addr;
            delete values.contr_type;
            delete values.contr_specialty;
            delete values.contr_birthplace;
            delete values.contr_birthdate;
            delete values.contr_cardNumber;
            delete values.contr_cardIssuingAuth;
            delete values.contr_dateStart;
            delete values.contr_dateEnd;
            delete values.contr_cardType;

            values.projscontrs = contrsArr;
            values.projsengs = engsArr;


            Ext.Ajax.request({
                url: "/compSecDiary/" + fid,
                headers: { 'Content-Type': 'application/json' },
                jsonData: Ext.util.JSON.encode(values),
                method: rest_method,
                success: successCallback,
                failure: failureCallback
            });

        }
        else{
            Ext.Msg.alert('Αποτυχία Δημιουργίας', 'Παρακαλώ διορθώστε τα λάθος πεδία');

        }
    }

});
