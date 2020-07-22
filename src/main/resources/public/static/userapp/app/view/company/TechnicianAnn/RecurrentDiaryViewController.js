Ext.define('MyApp.view.company.TechnicianAnn.RecurrentDiaryViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companytechnicianannrecurrentdiary',

    requires: [
        'MyApp.view.company.TechnicianAnn.TechnicianForm',
    ],

    onAdd_COMPANY_TECHNICIAN_ANN_RECURRENT_DATES: function(button, e, eOpts) {

        var form = button.up('toolbar').up('window').down('form');
        var formWindow = button.up('toolbar').up('window');

        function addRecurrentDiaries() {

            var viewInstance;
            if (Ext.isDefined(Ext.getCmp('techAnnWinId')))
            {
                viewInstance = Ext.getCmp('techAnnWinId');
            }
            else
            {
                viewInstance = Ext.getCmp('techAnnWinSEId');
            }

            var dateStart = form.getForm().findField("visitDateStart").getValue();
            var dateEnd = form.getForm().findField("visitDateEnd").getValue();
            var recurrency = form.getForm().findField("recurrency").getValue();
            var time = form.getForm().findField("visitTime").getValue();
            var durationH = form.getForm().findField("visitDurHs").getValue();
            var durationM = form.getForm().findField("visitDurMs").getValue();
            var technician;
            var technicianId;
            if (form.getForm().findField("taAnnTaIdLocal").isVisible())
            {
                technician = form.getForm().findField("taAnnTaIdLocal").rawValue;
                technicianId = form.getForm().findField("taAnnTaIdLocal").getValue();
            }
            else
            {
                technician = null;
                technicianId = null;
            }

            var totalDuration = durationH * 60 + durationM;


            var frequency;
            var weeksOrMonths;
            var specificRecurrency = false;
            var specificRecurrencyOrder;
            var specificRecurrencyDay;

            switch (recurrency)
            {
                case 0:
                    weeksOrMonths = Ext.Date.DAY;
                    frequency = 1;
                    break;
                case 1:
                    weeksOrMonths = Ext.Date.DAY;
                    frequency = 7;
                    break;
                case 2:
                    weeksOrMonths = Ext.Date.DAY;
                    frequency = 14;
                    break;
                case 3:
                    weeksOrMonths = Ext.Date.MONTH;
                    frequency = 1;
                    break;
                case 4:
                    specificRecurrency = true;
                    specificRecurrencyOrder = form.getForm().findField("specificRecurrencyOrder").getValue();
                    specificRecurrencyDay = form.getForm().findField("specificRecurrencyDay").getValue();
                    break;
                default:
                    weeksOrMonths = Ext.Date.DAY;
                    frequency = 1;
            }

            Ext.suspendLayouts();

            var holidayMsgEnabled = false;
            var holidayMsg = 'Οι επισκέψεις προστέθηκαν με επιτυχία εκτός από τις παρακάτω επειδή είναι αργίες. Μπορείτε να τις αντικαταστήσετε στο πρόγραμμα με αυτές που επιθυμείτε.</br>';
            var weekend = [0,6];
            var holidays = ["25/3", "15/8", "25/12", "26/12"];

            if (!specificRecurrency)
            {
                viewInstance.getRecurrentEntries(dateStart, time, totalDuration, durationH, durationM, technicianId);
                var t = Ext.Date.add (dateStart,weeksOrMonths,frequency);

                while (t <= dateEnd)
                {
                    if (holidays.includes(t.getDate() + "/" + (t.getMonth()+1)))
                    {
                        holidayMsgEnabled = true;
                        holidayMsg += '</br>' + t.getDate() + "/" + (t.getMonth()+1) + "/" + t.getFullYear();
                    }
                    if (!weekend.includes(t.getDay()) && !holidays.includes(t.getDate() + "/" + (t.getMonth()+1))) // It's a month behind so I add 1
                        viewInstance.getRecurrentEntries(t, time, totalDuration, durationH, durationM, technicianId);
                    t = Ext.Date.add (t,weeksOrMonths,frequency);
                }
            }
            else
            {
                var t = new Date(dateStart.getFullYear() + '-' + (dateStart.getMonth() + 1) + '-01');
                var daysCounter = 0;
                var mnth;
                var yr;
                while (t <= dateEnd)
                {
                    if (t.getDay() == specificRecurrencyDay)
                    {
                        daysCounter++;
                        if (daysCounter == specificRecurrencyOrder)
                        {
                            if (t >= dateStart && t <= dateEnd && holidays.includes(t.getDate() + "/" + (t.getMonth()+1)))
                            {
                                holidayMsgEnabled = true;
                                holidayMsg += '</br>' + t.getDate() + "/" + (t.getMonth()+1) + "/" + t.getFullYear();
                            }
                            if (t >= dateStart && t <= dateEnd && !weekend.includes(t.getDay()) && !holidays.includes(t.getDate() + "/" + (t.getMonth()+1)))
                            {
                                viewInstance.getRecurrentEntries(t, time, totalDuration, durationH, durationM, technicianId);
                            }
                            daysCounter = 0;
                            mnth = t.getMonth() + 1;
                            yr = t.getFullYear();
                            if (mnth == 12)
                            {
                                mnth = 0;
                                yr++;
                            }
                            t = new Date(yr + '-' + (mnth + 1) + '-01');
                            continue;
                        }
                    }
                    t = Ext.Date.add (t,Ext.Date.DAY,1);
                }
            }

            Ext.resumeLayouts(true);

            if (holidayMsgEnabled)
                Ext.Msg.alert('Επιτυχής Προσθήκη', holidayMsg);
            else
                Ext.Msg.alert('Επιτυχής Προσθήκη', 'Οι επισκέψεις προστέθηκαν με επιτυχία.');
            formWindow.unmask();

            // Close window
            formWindow.destroy();
        }

        if (!form.getForm().findField("taAnnTaIdLocal").isVisible())
        {
            form.getForm().findField("taAnnTaIdLocal").setDisabled(true);
        }

        if (form.isValid()){
            formWindow.mask("Παρακαλώ Περιμένετε...");
            setTimeout(function() {addRecurrentDiaries();}, 100);
            //this.addRecurrentDiaries(form, formWindow).defer(100);
        }
        else{
            Ext.Msg.alert('Αποτυχία Προσθήκης', 'Παρακαλώ διορθώστε τα λάθος πεδία');
        }
    }
});