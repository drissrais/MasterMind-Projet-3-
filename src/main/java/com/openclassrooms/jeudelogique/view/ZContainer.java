package com.openclassrooms.jeudelogique.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

public abstract class ZContainer {
	protected JPanel panel;
	
	Font comics30 = new Font("Comics Sans MS", Font.BOLD, 30);
	Font arial = new Font("Arial", Font.BOLD, 15);
	
	public ZContainer(Dimension dim) {
		this.panel = new JPanel();
		this.panel.setBackground(Color.WHITE);
		this.panel.setPreferredSize(dim);
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	protected abstract void initPanel();

}
