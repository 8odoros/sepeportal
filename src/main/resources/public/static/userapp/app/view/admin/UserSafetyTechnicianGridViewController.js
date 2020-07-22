/*
 * File: app/view/employee/ComplaintsGridViewController.js
 *
 * Created by Marios K.
 *
 * This file requires use of the Ext JS 5.0.x library.
 */

Ext.define('MyApp.view.admin.UserSafetyTechnicianGridViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.adminusersafetytechniciangrid',

    onTextfieldChange: function(field, newValue, oldValue, eOpts) {
        var me = field.up().up(), //field.up('#panelse'),
            count = 0;
        //debugger;
        me.view.refresh();
        // reset the statusbar  I will add the status bar in later
        /*me.statusBar.setStatus({
         text: me.defaultStatusText,
         iconCls: ''
         });*/

        me.searchValue = newValue;
        me.indexes = [];
        me.currentIndex = null;

        if (me.searchValue !== null) {
            me.searchRegExp = new RegExp(me.searchValue, 'g' + (me.caseSensitive ? '' : 'i'));


            me.store.each(function (record, idx) {
                var td = Ext.fly(me.view.getNode(idx)).down('td'),
                    cell, matches, cellHTML;
                while (td) {
                    cell = td.down('.x-grid-cell-inner');
                    matches = cell.dom.innerHTML.match(me.tagsRe);
                    cellHTML = cell.dom.innerHTML.replace(me.tagsRe, me.tagsProtect);

                    // populate indexes array, set currentIndex, and replace wrap matched string in a span
                    cellHTML = cellHTML.replace(me.searchRegExp, function (m) {
                        count += 1;
                        if (Ext.Array.indexOf(me.indexes, idx) === -1) {
                            me.indexes.push(idx);
                        }
                        if (me.currentIndex === null) {
                            me.currentIndex = idx;
                        }
                        return '<span class="x-livesearch-match">' + m + '</span>';
                    });
                    // restore protected tags
                    Ext.each(matches, function (match) {
                        cellHTML = cellHTML.replace(me.tagsProtect, match);
                    });
                    // update cell html
                    cell.dom.innerHTML = cellHTML;
                    td = td.next();
                }
            }, me);

            // results found
            if (me.currentIndex !== null) {
                me.getSelectionModel().select(me.currentIndex);
                /* me.statusBar.setStatus({  // status bar to come later
                 text: count + ' matche(s) found.',
                 iconCls: 'x-status-valid'
                 });*/
            }
        }
        field.focus();
    }

});
