/*
 * File: app.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

// @require @packageOverrides
Ext.Loader.setConfig({
    disableCaching: false
});

Ext.application({
       appFolder: '/portal/static/userapp/app',
       stores: [
        'address.ADDR_P',
        'address.ADDR_Pe',
        'address.ADDR_D',
        'address.ADDR_K',
        'address1.ADDR_K1',
        'address1.ADDR_Pe1',
        'address1.ADDR_D1',
        'compScope.rtstakLevel1',
        'compScope.rtstakLevel2',
        'compScope.rtstakLevel3',
        'compScope.rtstakLevel4',
        'compScope.rtstakLevel5',
    ],
    views: [
        'anonymous.MainView',
        'anonymous.ComplaintForm',
        'shared.CloseFormTool',
        'shared.PrintFormTool',
    ],
    name: 'MyApp',

    launch: function() {

        var URLPrefix = '/portal';
         Ext.Ajax.on("beforerequest", function(conn, options, eOpts) {
         var urls = options.url.toString();
             if(urls.substring(0,4)!="http"){
         if(urls.substring(0,1)==="/")
         options.url = URLPrefix + options.url;
         else
         options.url = URLPrefix +"/"+ options.url;
         }
         });
		
		Ext.define('Ext.addon.MessagePop', {
			extend: "Ext.util.Observable",
			singleton: true,
		 
			constructor: function(config) {
			  Ext.apply(this,config);
			  this.superclass.constructor.call(this, config);
			  Ext.DomHelper.insertFirst(document.body, {id:'msg-div'}, true);
			},
		 
			msg : function(title, message, delay){
				if(!delay) {delay = 2000;}
				var msgCt = document.getElementById("msg-div")
				var s = Ext.String.format.apply(String, Array.prototype.slice.call(arguments, 1));
				var box = '<div class="msg"><h3>' + title+ '</h3><p>' + s + '</p></div>'
				var m = Ext.DomHelper.append(msgCt, box, true);
				m.hide();
				m.slideIn('t').ghost("t", { delay: delay, remove: true});
			},
		});
		
        //Ext.create('MyApp.view.anonymous.MainView');
        var emp_comp = Ext.create('widget.anonymouscomplaintform', {
        });
        emp_comp.down().getForm().findField('protNo').setValue("-");
        emp_comp.show();

        var telNumberVType = {
              telNumber: function(val, field) {
                var telNumberRegex = /^[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]$/;
                return telNumberRegex.test(val);
            },
            telNumberText: 'Το τηλεφώνο πρέπει να περιέχει μόνο αριθμούς (10 ψηφία).',
            telNumbermaxLength: 10
        };

        Ext.apply(Ext.form.field.VTypes, telNumberVType);


        var NumberVType = {
              Number: function(val, field) {
                var NumberRegex = /^[0-9]*$/;
                return NumberRegex.test(val);
            },
            NumberText: 'Το πεδίο πρέπει να περιέχει μόνο αριθμούς.',
            NumbermaxLength: 100
        };

        Ext.apply(Ext.form.field.VTypes, NumberVType);


        Ext.form.Field.prototype.invalidText = 'Το περιεχόμενο του πεδίου δεν είναι αποδεκτό';
        Ext.form.Field.prototype.msgTarget = 'under';

        Ext.form.TextField.prototype.blankText = 'Το πεδίο είναι υποχρεωτικό';
        Ext.form.TextField.prototype.minLengthText = 'Το μικρότερο αποδεκτό μήκος για το πεδίο είναι {0}';
        Ext.form.TextField.prototype.maxLengthText = 'Το μεγαλύτερο αποδεκτό μήκος για το πεδίο είναι {0}';
        Ext.form.TextField.prototype.regexText = "";
        Ext.form.TextField.prototype.emptyText = null;


        Ext.form.RadioGroup.prototype.blankText = 'Πρέπει να επιλέξετε κάποιο πεδιο';
        Ext.form.RadioGroup.prototype.msgTarget = 'under';

        Ext.form.ComboBox.prototype.blankText = 'Πρέπει να επιλέξετε κάποιο στοιχείο της λίστας';
        Ext.form.ComboBox.prototype.msgTarget = 'under';

        Ext.form.NumberField.prototype.minText = 'Η μικρότερη τιμή του πεδίου είναι {0}';
        Ext.form.NumberField.prototype.maxText = 'Η μεγαλύτερη τιμή του πεδίου είναι {0}';
        Ext.form.NumberField.prototype.nanText = '{0} δεν είναι αποδεκτός αριθμός';
        Ext.form.NumberField.prototype.blankText = 'Το πεδίο είναι υποχρεωτικό';
        Ext.form.NumberField.prototype.msgTarget = 'under';

        Ext.form.DateField.prototype.disabledDaysText = 'Ανενεργό';
        Ext.form.DateField.prototype.disabledDatesText = 'Ανενεργό';
        Ext.form.DateField.prototype.minText = 'Η ημερομηνία αυτού του πεδίου πρέπει να είναι μετά την {0}';
        Ext.form.DateField.prototype.maxText = 'Η ημερομηνία αυτού του πεδίου πρέπει να είναι πριν την {0}';
        Ext.form.DateField.prototype.invalidText = '{0} δεν είναι έγκυρη ημερομηνία - πρέπει να είναι στη μορφή {1}';
        Ext.form.DateField.prototype.format = 'd-m-Y';
        Ext.form.DateField.prototype.msgTarget = 'under';

        Ext.define('EXTJS-14607.picker.Date', {
            override: 'Ext.picker.Date',


            runAnimation: function(isHide) {
                var me = this,
                    picker = this.monthPicker,
                    options = {
                        duration: 200,
                        callback: function() {
                            picker.setVisible(!isHide);
                            // See showMonthPicker
                            picker.ownerCmp = isHide ? null : me;
                        }
                    };


                if (isHide) {
                    picker.el.slideOut('t', options);
                } else {
                    picker.el.slideIn('t', options);
                }
            },


            hideMonthPicker: function(animate) {
                var me = this,
                    picker = me.monthPicker;


                if (picker && picker.isVisible()) {
                    if (me.shouldAnimate(animate)) {
                        me.runAnimation(true);
                    } else {
                        picker.hide();
                        // See showMonthPicker
                        picker.ownerCmp = null;
                    }
                }
                return me;
            },


            showMonthPicker: function(animate) {
                var me = this,
                    el = me.el,
                    picker;


                if (me.rendered && !me.disabled) {
                    picker = me.createMonthPicker();
                    if (!picker.isVisible()) {
                        picker.setValue(me.getActive());
                        picker.setSize(el.getSize());
                        picker.setPosition(-el.getBorderWidth('l'), -el.getBorderWidth('t'));
                        if (me.shouldAnimate(animate)) {
                            me.runAnimation(false);
                        } else {
                            picker.show();
                            picker.ownerCmp = me;
                        }
                    }
                }
                return me;
            }
        });

        if (Ext.Date) {
            Ext.Date.monthNames = ["Ιανουάριος", "Φεβρουάριος", "Μάρτιος", "Απρίλιος", "Μάιος", "Ιούνιος", "Ιούλιος", "Αύγουστος", "Σεπτέμβριος", "Οκτώβριος", "Νοέμβριος", "Δεκέμβριος"];

            Ext.Date.shortMonthNames = ["Ιαν", "Φεβ", "Μάρ", "Απρ", "Μάι", "Ιού", "Ιού", "Αύγ", "Σεπ", "Οκτ", "Νοέ", "Δεκ"];

            Ext.Date.getShortMonthName = function(month) {
                return Ext.Date.monthNames[month].substring(0, 3);
            };

            Ext.Date.monthNumbers = {
                Jan: 0,
                Feb: 1,
                Mar: 2,
                Apr: 3,
                May: 4,
                Jun: 5,
                Jul: 6,
                Aug: 7,
                Sep: 8,
                Oct: 9,
                Nov: 10,
                Dec: 11
            };

            Ext.Date.getMonthNumber = function(name) {
                return Ext.Date.monthNumbers[name.substring(0, 1).toUpperCase() + name.substring(1, 3).toLowerCase()];
            };

            Ext.Date.dayNames = ["Κυριακή", "Δευτέρα", "Τρίτη", "Τετάρτη", "Πέμπτη", "Παρασκευή", "Σάββατο"];
        }

        Ext.DatePicker.prototype.todayText = 'Σήμερα';
        Ext.picker.Month.prototype.okText = '&#160;Επιλογή&#160;';
        Ext.picker.Month.prototype.cancelText = 'Ακύρωση';
        Ext.DatePicker.prototype.todayTip = '{0}';
        Ext.DatePicker.prototype.minText = 'Η Ημερομηνία είναι προγενέστερη από την παλαιότερη αποδεκτή';
        Ext.DatePicker.prototype.maxText = 'Η Ημερομηνία είναι μεταγενέστερη από την νεότερη αποδεκτή';
        Ext.DatePicker.prototype.format = 'd-m-Y';
        //Ext.DatePicker.prototype.disabledDaysText = '',
        //Ext.DatePicker.prototype.disabledDatesText = '',
        Ext.DatePicker.prototype.nextText = 'Επόμενος Μήνας (Control + Δεξί Βέλος)';
        Ext.DatePicker.prototype.prevText = 'Προηγούμενος Μήνας (Control + Αριστερό Βέλος)';
        Ext.DatePicker.prototype.monthYearText = 'Επιλογή Μήνα (Control + Επάνω/Κάτω Βέλος για μεταβολή ετών)';
        Ext.DatePicker.prototype.startDay = 0;


        Ext.PagingToolbar.prototype.displayMsg = 'Εμφάνιση {0} - {1} από {2}';
        Ext.PagingToolbar.prototype.emptyMsg = 'Δεν υπάρχουν δεδομένα';
        Ext.PagingToolbar.prototype.beforePageText = 'Σελίδα';
        Ext.PagingToolbar.prototype.afterPageText = 'από {0}';
		
        /*----BECAUSE OF EXTJS BUG COMBOBOX TOOLBAR CAN'T HAVE QUICKTIPS
		Ext.PagingToolbar.prototype.firstText = 'Πρώτη Σελίδα';
        Ext.PagingToolbar.prototype.prevText = 'Προηγούμενη Σελίδα';
        Ext.PagingToolbar.prototype.nextText = 'Επόμενη Σελίδα';
        Ext.PagingToolbar.prototype.lastText = 'Τελευταία Σελίδα';
        Ext.PagingToolbar.prototype.refreshText = 'Ανανέωση';
		*/

        Ext.PagingToolbar.prototype.firstText = '';
        Ext.PagingToolbar.prototype.prevText = '';
        Ext.PagingToolbar.prototype.nextText = '';
        Ext.PagingToolbar.prototype.lastText = '';
        Ext.PagingToolbar.prototype.refreshText = '';
		
        Ext.tab.Tab.prototype.closeText = "Κλείστε το Tab";

        Ext.view.AbstractView.prototype.loadingText = "Μεταφόρτωση δεδομένων...";
        Ext.view.AbstractView.prototype.msg = "Μεταφόρτωση δεδομένων...";

        Ext.form.field.ComboBox.prototype.loadingText = "Μεταφόρτωση δεδομένων...";


        Ext.window.MessageBox.prototype.buttonText = {
                ok: "Εντάξει",
                cancel: "Άκυρο",
                yes: "Ναι",
                no: "Όχι"
            };

        Ext.grid.feature.Grouping.prototype.emptyGroupText = '(Καμμία)';
        Ext.grid.feature.Grouping.prototype.groupByText = 'Ομαδοποίηση βάσει αυτού του πεδίου';
        Ext.grid.feature.Grouping.prototype.showGroupsText = 'Να εμφανίζεται στις ομάδες';

        Ext.grid.header.Container.prototype.sortAscText = "Αύξουσα ταξινόμηση";
        Ext.grid.header.Container.prototype.sortDescText = "Φθίνουσα ταξινόμηση";
        Ext.grid.header.Container.prototype.lockText = "Κλείδωμα στήλης";
        Ext.grid.header.Container.prototype.unlockText = "Ξεκλείδωμα στήλης";
        Ext.grid.header.Container.prototype.columnsText = "Στήλες";
    }

});
