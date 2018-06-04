package com.openclassrooms.jeudelogique.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class LabelRenderer extends JLabel implements TableCellRenderer {

	private static final long serialVersionUID = 2606993282889856508L;
	private Font arial = new Font("Arial", Font.BOLD, 14);

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		this.setText((value != null) ? value.toString() : "");
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setFont(arial);

		if ((row % 2 == 0 && column == 0) || (row % 2 == 1 && column == 1))
			this.setForeground(Color.decode("#51b46d"));
		else
			this.setForeground(Color.RED);
		return this;
	}

}
