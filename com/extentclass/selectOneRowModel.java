package com.extentclass;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ListSelectionModel;

public class selectOneRowModel extends DefaultListSelectionModel {

    public selectOneRowModel () {
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    public void clearSelection() {
    }

    @Override
    public void removeSelectionInterval(int index0, int index1) {
    }


}
