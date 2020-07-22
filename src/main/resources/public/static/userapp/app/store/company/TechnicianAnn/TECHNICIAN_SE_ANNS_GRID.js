/**
 * Created by dimitrisf on 8/10/2018.
 */

Ext.define('MyApp.store.company.TechnicianAnn.TECHNICIAN_SE_ANNS_GRID', {
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
            pageSize: 10,
            remoteSort: true,
            storeId: 'company.TechnicianAnn.TECHNICIAN_SE_ANNS_GRID',
            autoLoad: false,
            fields: [
                {
                    name: 'protNo'
                },
                {
                    mapping: '_links.compTaAnnSeDiaryEntries.href',
                    name: 'technicianSeAnnDiaryEntries'
                },
                {
                    name: 'protDate'
                },
                {
                    name: 'subStatus'
                },
                {
                    name: 'reqStatus'
                },
                {
                    name: 'dateStart'
                },
                {
                    name: 'dateEnd'
                },
                {
                    name: 'taAfm'
                },
                {
                    mapping: '_links.self.href',
                    name: 'url'
                }
            ],
            proxy: {
                type: 'ajax',
                extraParams: {
                    size: 10,
                    sort: 'protDate,desc'
                },
                limitParam: '',
                startParam: '',
                url: '/compTaAnnSe/',
                reader: {
                    type: 'json',
                    rootProperty: '_embedded.compTaAnnSe',
                    totalProperty: 'page.totalElements'
                }
            }
        }, cfg)]);
    }
});