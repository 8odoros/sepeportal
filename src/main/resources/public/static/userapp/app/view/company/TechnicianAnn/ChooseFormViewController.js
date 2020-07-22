/*
 * File: app/view/company/TechnicianAnn/ChooseFormViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.company.TechnicianAnn.ChooseFormViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.companytechnicianannchooseform',

    onChoose_COMPANY_TECHNICIAN_ANN_NORMAL: function(button, e, eOpts) {
        var formWindow = button.up('window');
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        if (formWindow.items.getAt(2).getValue()==="true"){
            center.removeAll();
            var store = Ext.StoreMgr.lookup('company.TechnicianBooks.BOOKS');
            store.load({
                callback: function(records, operation, success) {
                    if (success) {
                        var viewsub = Ext.create('MyApp.view.company.TechnicianBooksPanel');
                        Ext.getCmp('companyTechnicianBooks_Branches').store.load({
                            callback: function(records, operation, success) {
                                if (success) {
                                    var records2=[];
                                    var branches= [];
                                    for(i=0; i<records.length; i++){
                                        if(branches.indexOf(records[i].get('ptlBranchId'))<0)
                                            branches.push(records[i].get('ptlBranchId'));
                                    }

                                    for(i=0; i<branches.length; i++){
                                        var flag=false;
                                        for(j=0; j<records.length;j++) {
                                            if(flag===false && branches[i]===records[j].get('ptlBranchId')){
                                                records2.push(records[j]);
                                                flag=true;
                                            }
                                        }

                                    }

                                    Ext.getCmp('companyTechnicianBooks_Branches').store.loadData(records2);
                                    Ext.getCmp('companyTechnicianBooks_Book').store.clearData();
                                    center.add(viewsub);
                                    formWindow.destroy();
                                }
                            }
                        });
                    }
                }
            });
        }
        else{
            var viewsub = Ext.create('MyApp.view.company.TechnicianAnnPanel');
            Ext.getCmp('companyTechnicianAnn_Technicians').store.clearData();
            center.add(viewsub);
            formWindow.destroy();
        }


    },

    onChoose_COMPANY_TECHNICIAN_ANN_SHIPS: function(button, e, eOpts) {
        var formWindow = button.up('window');
        var view=Ext.getCmp('companymainView');
        var center = view.getComponent('contentPanel');
        center.removeAll();
        if (formWindow.items.getAt(2).getValue()==="true"){
            center.removeAll();
            var store = Ext.StoreMgr.lookup('company.TechnicianBooks.SHIPS_BOOKS');
            store.load({
                callback: function(records, operation, success) {
                    if (success) {
                        var viewsub = Ext.create('MyApp.view.company.TechnicianBooksPanel');
                        Ext.getCmp('companyTechnicianBooks_Branches').hide();
                        Ext.getCmp('companyTechnicianBooks_Ships').show();
                        Ext.getCmp('companyTechnicianBooks_Ships').store.load();
                        Ext.getCmp('companyTechnicianBooks_Book').store.clearData();
                        center.add(viewsub);
                        formWindow.destroy();
                    }
                }
            });
        }
        else{
            var viewsub = Ext.create('MyApp.view.company.TechnicianShipAnnPanel');
            Ext.getCmp('companyTechnicianShipAnn_Technicians').store.clearData();
            center.add(viewsub);
            formWindow.destroy();
        }


    }

});
