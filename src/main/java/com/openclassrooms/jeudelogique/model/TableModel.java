package com.openclassrooms.jeudelogique.model;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

	private static final long serialVersionUID = -5037596278750368076L;

	// Donnees du tableau
	private Object[][] data;

	// Titres des colonnes du tableau.
	private String[] titles;

	// Constructeur de la classe ModelTableau.
	public TableModel(Object[][] data, String[] titles) {
		this.data = data;
		this.titles = titles;
	}

	@Override
	public int getRowCount() {
		return this.data.length;
	}

	@Override
	public int getColumnCount() {
		return this.titles.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.data[rowIndex][columnIndex];
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		this.data[rowIndex][columnIndex] = aValue;
	}
	
	@Override
	public String getColumnName(int column) {
		return this.titles[column];
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

}
