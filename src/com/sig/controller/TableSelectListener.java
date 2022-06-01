
package com.sig.controller;

import com.sig.model.InvoiceHeader;
import com.sig.model.InvoiceLine;
import com.sig.model.InvoiceLineTableModel;
import com.sig.view.InvoiceStructure;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class TableSelectListener implements ListSelectionListener {

    private InvoiceStructure structure;

    public TableSelectListener(InvoiceStructure structure) {
        this.structure = structure;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedInvIndex = structure.getHeaderTable().getSelectedRow();
        System.out.println("Invoice selected: " + selectedInvIndex);
        if (selectedInvIndex != -1) {
            InvoiceHeader selectedInv = structure.getInvoicesArray().get(selectedInvIndex);
            ArrayList<InvoiceLine> lines = selectedInv.getLines();
            InvoiceLineTableModel lineTableModel = new InvoiceLineTableModel(lines);
            structure.setLinesArray(lines);
            structure.getLineTable().setModel(lineTableModel);
            structure.getCustNameLbl().setText(selectedInv.getCustomer());
            structure.getInvNumLbl().setText("" + selectedInv.getNum());
            structure.getInvTotalLbl().setText("" + selectedInv.getInvoiceTotal());
            structure.getInvDateLbl().setText(InvoiceStructure.dateFormat.format(selectedInv.getInvDate()));
        }
    }

}
