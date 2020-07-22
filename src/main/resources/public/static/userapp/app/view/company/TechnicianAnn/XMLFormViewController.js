/**
 * Created by dimitrisf on 15/4/2019.
 */

Ext.define('MyApp.view.company.TechnicianAnn.XMLFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companytechnicianannxmlform',

    onCheck_COMPANY_TECHNICIAN_ANN_XML: function (button, e, eOpts) {
        var fileurl = '/setDocument';
        if (button.up('form').getForm().findField('file') !== null && button.up('form').getForm().findField('file').getValue().endsWith('.xml'))
            fileurl = "/setDocument?docId=-1";
        else {
            Ext.Msg.alert('Αποτυχία Καταχώρησης', 'Το αρχείο που εισάγατε δεν είναι τύπου xml');
            return;
        }

        if (button.up('form').getForm().findField('file') !== "") {
            button.up('form').submit({
                url: fileurl,
                waitMsg: 'Καταχώρηση αρχείου...',
                success: function (form, action) {
                    var url = "/getDocument?docId=" + parseInt(action.result.fileId);
                    Ext.Ajax.request({
                        url: url,
                        success: function (response, options) {
                            var object = response.responseXML;

                            var dateStartRaw = Ext.DomQuery.selectValue('date_start', object);
                            var dateEndRaw = Ext.DomQuery.selectValue('date_end', object);

                            var dateStartArray = dateStartRaw.split('-');
                            var dateEndArray = dateEndRaw.split('-');

                            dateStartRaw = dateStartArray[1] + '-' + dateStartArray[0] + '-' + dateStartArray[2];
                            dateEndRaw = dateEndArray[1] + '-' + dateEndArray[0] + '-' + dateEndArray[2];

                            if (dateStartArray.length != 3 || dateEndArray.length != 3) {
                                Ext.Msg.alert('Μη έγκυρες ημερομηνίες', 'Οι ημερομηνίες που εισάγατε δεν είναι σωστές');
                                return;
                            }

                            var dateStart = new Date(dateStartRaw);
                            var dateEnd = new Date(dateEndRaw);

                            var dateStartMin = Ext.Date.add(new Date(
                                new Date().getFullYear(),
                                new Date().getMonth() - 1,
                                new Date().getDate()
                            ), Ext.Date.DAY, 1);
                            var dateStartMax = Ext.Date.add(new Date(
                                new Date().getFullYear(),
                                new Date().getMonth() + 1,
                                new Date().getDate()
                            ), Ext.Date.DAY, -1);
                            var dateEndMin = dateStart;
                            var dateEndMax = Ext.Date.add(Ext.Date.add(dateStart, Ext.Date.YEAR, 1), Ext.Date.DAY, -1);

                            if (dateStart < dateStartMin) {
                                Ext.Msg.alert('Λάθος Ημερομηνία', 'Η ημερομηνία έναρξης πρέπει να είναι το πολύ 1 μήνα πριν από την τρέχουσα ημερομηνία');
                                return;
                            }
                            else if (dateStart > dateStartMax) {
                                Ext.Msg.alert('Λάθος Ημερομηνία', 'Η ημερομηνία έναρξης πρέπει να είναι το πολύ 1 μήνα μετά από την τρέχουσα ημερομηνία');
                                return;
                            }

                            if (dateEnd <= dateEndMin) {
                                Ext.Msg.alert('Λάθος Ημερομηνία', 'Η ημερομηία λήξης πρέπει να είναι μεταγενέστερη της ημερομηνίας έναρξης');
                                return;
                            }
                            else if (dateEnd > dateEndMax) {
                                Ext.Msg.alert('Λάθος Ημερομηνία', 'Η ημερομηία λήξης πρέπει να είναι το πολύ 1 χρόνο μετά την ημερομηνία έναρξης');
                                return;
                            }

                            var formWindow = Ext.getCmp('companytechnicianannxml').up('window');
                            var successCall = function (options, success, response) {
                                formWindow.unmask();
                                if (response.responseText === "0") {

                                    var emp_comp2 = Ext.create('widget.companytechniciananntechnicianform', {});

                                    var entrie = Ext.create('widget.companytechniciananndiary', {});
                                    entrie.items.get(0).minValue = (dateStart > new Date()) ? dateStart : new Date();
                                    entrie.items.get(0).maxValue = dateEnd;
                                    entrie.items.get(6).setStyle({margin: '26px 0 0 2px'});
                                    Ext.getCmp('tadiaryEntries').add(entrie);

                                    var ptlBranch = Ext.getCmp('companyTechnicianAnn_Branches').getSelectionModel().getSelection()[0];
                                    emp_comp2.down('form').getForm().findField('compPtlBranchId').setValue(ptlBranch.get('ptlBranchId'));
                                    emp_comp2.down('form').getForm().findField('compEbrBranchId').setValue(ptlBranch.get('ebrBranchId'));

                                    emp_comp2.down('form').getForm().findField('brAddr').setValue(ptlBranch.get('brAddr'));
                                    emp_comp2.down('form').getForm().findField('brAddrTk').setValue(ptlBranch.get('brAddrTk'));
                                    emp_comp2.down('form').getForm().findField('brAddrP').setValue(ptlBranch.get('brAddrP'));
                                    emp_comp2.down('form').getForm().findField('brAddrPe').setValue(ptlBranch.get('brAddrPe'));
                                    emp_comp2.down('form').getForm().findField('brAddrD').setValue(ptlBranch.get('brAddrD'));
                                    emp_comp2.down('form').getForm().findField('brAddrK').setValue(ptlBranch.get('brAddrK'));

                                    var successAns2 = function (options, success, response) {
                                        if (Ext.JSON.decode(response.responseText).success) {

                                            var resp = Ext.JSON.decode(response.responseText);

                                            emp_comp2.down('form').getForm().findField('compCategANum').setValue(resp.categANum);
                                            emp_comp2.down('form').getForm().findField('compCategBNum').setValue(resp.categBNum);
                                            emp_comp2.down('form').getForm().findField('compCategCNum').setValue(resp.categCNum);
                                        }
                                        else {
                                            Ext.Msg.alert('Σφάλμα!', 'Υπάρχει πρόβλημα στην επισύναψη στοιχείων');
                                        }
                                    };

                                    Ext.Ajax.request({
                                        url: '/companyExtraInfo',
                                        method: "GET",
                                        callback: successAns2
                                    });

                                    var successAns3 = function (options, success, response) {
                                        if (Ext.JSON.decode(response.responseText).success) {

                                            var resp = Ext.JSON.decode(response.responseText);
                                            emp_comp2.down('form').getForm().findField('compFullName').setValue(resp.compFullName);
                                            emp_comp2.down('form').getForm().findField('compTaxNumber').setValue(resp.compTaxNumber);
                                            emp_comp2.down('form').getForm().findField('compAme').setValue(resp.compAme);
                                            emp_comp2.down('form').getForm().findField('compAddr').setValue(resp.compAddr);
                                            emp_comp2.down('form').getForm().findField('compAddrK').setValue(resp.compAddrK);
                                            emp_comp2.down('form').getForm().findField('compAddrTk').setValue(resp.compAddrTk);
                                            emp_comp2.down('form').getForm().findField('compPhone').setValue(resp.compPhone);
                                        }
                                        else {
                                            Ext.Msg.alert('Σφάλμα!', 'Υπάρχει πρόβλημα στην επισύναψη στοιχείων');
                                        }
                                    };

                                    Ext.Ajax.request({
                                        url: "/getCompanyInfo",
                                        method: 'GET',
                                        callback: successAns3
                                    });

                                    emp_comp2.show();
                                    emp_comp2.mask('Παρακαλώ Περιμένετε...');
                                    formWindow.destroy();

                                    emp_comp2.down('form').getForm().findField('dateStart').setValue(dateStart);
                                    emp_comp2.down('form').getForm().findField('dateEnd').setValue(dateEnd);

                                    var branchSector = Ext.DomQuery.selectValue('branch_sector', object);
                                    emp_comp2.down('form').getForm().findField('branchSector').setValue(branchSector);
                                    emp_comp2.down('form').getForm().findField('branchSectorId').setValue(parseInt(branchSector));
                                    emp_comp2.down('form').getForm().findField('cooperationTypeBasic').enable();

                                    var cooperationTypeBasic = Ext.DomQuery.selectValue('cooperation_type_basic', object);
                                    emp_comp2.down('form').getForm().findField('cooperationTypeBasic').fireEvent('change', emp_comp2.down('form').getForm().findField('cooperationTypeBasic'), parseInt(cooperationTypeBasic));
                                    emp_comp2.down('form').getForm().findField('cooperationTypeBasic').setValue(parseInt(cooperationTypeBasic));

                                    var techSize = Ext.DomQuery.select('Technician', object).length;
                                    var cooperationType = [techSize];
                                    var taAfm = [techSize];
                                    var afmCmp;

                                    var visitSize = Ext.DomQuery.select('Technician_Visit', object).length;
                                    var visitDate = [visitSize];
                                    var visitTime = [visitSize];
                                    var visitDurationMinutes = [visitSize];
                                    var visitTechAfm = [visitSize];

                                    var techAfms = [];
                                    var techUserIds = [];
                                    var counter = 0;
                                    for (var i = 0; i < techSize; i++)
                                    {
                                        var successCallTaAfm = function (options, success, response) {
                                            if (success) {
                                                var resp = Ext.JSON.decode(response.responseText);
                                                techUserIds[counter] = resp.userId.toString();
                                                techAfms[counter] = options.params.afm;
                                                counter ++;
                                            }
                                        };

                                        Ext.Ajax.request({
                                            url: "/findTaByAfm",
                                            params: {
                                                afm: Ext.DomQuery.selectValue('Technician_AFM:nth(' + (i + 1) + ')', object),
                                                ta: 'ta',
                                                branchSectorId: parseInt(branchSector),
                                                taSum: Ext.getCmp('tadiaryEntries').up('form').getForm().findField('taEntriesnum').getValue()
                                            },
                                            method: "GET",
                                            callback: successCallTaAfm
                                        });
                                    }

                                    var task = new Ext.util.DelayedTask(function () {

                                        Ext.suspendLayouts();

                                        for (var i = 0; i < visitSize; i++) {
                                            if (i > 0)
                                                Ext.getCmp('taentryadd').getEl().dom.click();
                                            visitDate[i] = Ext.DomQuery.selectValue('VisitDate:nth(' + (i + 1) + ')', object);
                                            visitTime[i] = Ext.DomQuery.selectValue('VisitTime:nth(' + (i + 1) + ')', object);
                                            visitDurationMinutes[i] = Ext.DomQuery.selectValue('VisitDurationMinutes:nth(' + (i + 1) + ')', object);
                                            visitTechAfm[i] = Ext.DomQuery.selectValue('VisitTechAFM:nth(' + (i + 1) + ')', object);

                                            Ext.getCmp('tadiaryEntries').items.get(5 + i).items.get(0).setValue(visitDate[i]);
                                            Ext.getCmp('tadiaryEntries').items.get(5 + i).items.get(1).setValue(visitTime[i]);
                                            Ext.getCmp('tadiaryEntries').items.get(5 + i).items.get(2).setValue(visitDurationMinutes[i]);
                                            Ext.getCmp('tadiaryEntries').items.get(5 + i).items.get(3).setValue(Math.floor(visitDurationMinutes[i] / 60));
                                            Ext.getCmp('tadiaryEntries').items.get(5 + i).items.get(4).setValue(visitDurationMinutes[i] % 60);
                                            Ext.getCmp('tadiaryEntries').items.get(5 + i).items.get(5).setValue(techUserIds[techAfms.indexOf(visitTechAfm[i])]);
                                        }
                                        Ext.getCmp('tadiaryEntries').up('form').getForm().findField('diaryEntriesnum').setValue(visitSize);

                                        Ext.resumeLayouts(true);
                                        emp_comp2.unmask();
                                    });

                                    if (cooperationTypeBasic == 3) //ΕΞΥΠΠ
                                    {
                                        var successCallAfm = function (options, success, response) {
                                            if (success) {
                                                var resp = Ext.JSON.decode(response.responseText);
                                                var exyppBasic = resp.userId;
                                                emp_comp2.down('form').getForm().findField('exyppBasic').setValue(exyppBasic.toString());
                                                emp_comp2.down('form').getForm().findField('exyppBasic').store.load({
                                                    callback: function (records, operation, success) {
                                                        for (var i = 0; i < techSize; i++) {
                                                            if (i > 0)
                                                                Ext.getCmp('newTaEntry').getEl().dom.click();
                                                            taAfm[i] = Ext.DomQuery.selectValue('Technician_AFM:nth(' + (i + 1) + ')', object);
                                                            afmCmp = Ext.getCmp('taAnnTaEntries').items.get(5 + i).items.get(0).items.get(2);
                                                            afmCmp.setValue(taAfm[i]);
                                                            afmCmp.fireEvent('blur', afmCmp);
                                                        }
                                                        task.delay(500);
                                                    }
                                                });
                                            }
                                        };
                                        Ext.Ajax.request({
                                            url: "/TCompany/search/findByAfm",
                                            params: {
                                                afm: Ext.DomQuery.selectValue('exypp_afm', object),
                                            },
                                            method: "GET",
                                            callback: successCallAfm
                                        });
                                    }
                                    else {
                                        for (var i = 0; i < techSize; i++) {
                                            if (i > 0)
                                                Ext.getCmp('newTaEntry').getEl().dom.click();
                                            cooperationType[i] = Ext.DomQuery.selectValue('cooperation_type:nth(' + (i + 1) + ')', object);
                                            taAfm[i] = Ext.DomQuery.selectValue('Technician_AFM:nth(' + (i + 1) + ')', object);
                                            afmCmp = Ext.getCmp('taAnnTaEntries').items.get(4 + i).items.get(0).items.get(2);
                                            Ext.getCmp('taAnnTaEntries').items.get(4 + i).items.get(0).items.get(0).setValue(cooperationType[i]);
                                            afmCmp.setValue(taAfm[i]);
                                            afmCmp.fireEvent('blur', afmCmp);
                                        }
                                        task.delay(500);
                                    }

                                    var categANum = Ext.DomQuery.selectValue('categ_a_num', object);
                                    var categBNum = Ext.DomQuery.selectValue('categ_b_num', object);
                                    var categCNum = Ext.DomQuery.selectValue('categ_c_num', object);

                                    if (parseInt(categCNum) != 0)
                                    {
                                        emp_comp2.down('form').getForm().findField('categANum').suspendEvents();
                                        emp_comp2.down('form').getForm().findField('categBNum').suspendEvents();
                                    }
                                    else if (parseInt(categBNum) != 0)
                                    {
                                        emp_comp2.down('form').getForm().findField('categANum').suspendEvents();
                                        emp_comp2.down('form').getForm().findField('categCNum').suspendEvents();
                                    }
                                    else if (parseInt(categANum) != 0)
                                    {
                                        emp_comp2.down('form').getForm().findField('categBNum').suspendEvents();
                                        emp_comp2.down('form').getForm().findField('categCNum').suspendEvents();
                                    }
                                    
                                    emp_comp2.down('form').getForm().findField('categANum').setValue(parseInt(categANum));
                                    emp_comp2.down('form').getForm().findField('categBNum').setValue(parseInt(categBNum));
                                    emp_comp2.down('form').getForm().findField('categCNum').setValue(parseInt(categCNum));

                                    emp_comp2.down('form').getForm().findField('categANum').resumeEvents();
                                    emp_comp2.down('form').getForm().findField('categBNum').resumeEvents();
                                    emp_comp2.down('form').getForm().findField('categCNum').resumeEvents();
                                }
                                else
                                {
                                    Ext.Msg.alert('Μη έγκυρες ημερομηνίες', 'Οι ημερομηνίες έχουν επικάλυψη με άλλη ενεργή αίτηση');
                                }

                            };
                            formWindow.mask("Παρακαλώ Περιμένετε...");
                            Ext.Ajax.request({
                                url: "/compTaAnn/search/countTaAnn",
                                params: {
                                    ptlBranchId: Ext.getCmp('companyTechnicianAnn_Branches').getSelectionModel().getSelection()[0].get('ptlBranchId'),
                                    startDate: dateStartRaw
                                },
                                method: "GET",
                                callback: successCall
                            });
                        }
                    });
                },
                failure: function (form, action) {
                    form.findField("file").focus();
                    Ext.Msg.alert('Αποτυχία Καταχώρησης Αρχείου', action.result.error);
                }
            });
        }
    }
});