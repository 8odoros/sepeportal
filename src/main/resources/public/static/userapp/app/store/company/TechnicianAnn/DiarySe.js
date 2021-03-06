/**
 * Created by dimitrisf on 9/10/2018.
 */

Ext.define('MyApp.store.company.TechnicianAnn.DiarySe', {
    extend: 'Ext.data.Store',

    requires: [
        'Ext.data.field.Field',
        'Ext.data.proxy.Ajax',
        'Ext.data.reader.Json'
    ],

    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            pageSize: 100,
            remoteSort: true,
            storeId: 'company.TechnicianAnn.DiarySe',
            autoLoad: false,
            fields: [
                {
                    name: 'incNum'
                },
                {
                    name: 'companyid'
                },
                {
                    mapping: '_links.self.href',
                    name: 'url'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    size: 200,
                    compIeAnnId: null,
                    sort: 'visitDate,asc'
                },
                limitParam: '',
                startParam: '',
                url: '/compTaAnnSeDiary/search/findByCompTaAnnId',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compTaAnnSeDiary',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});