package com.openclassrooms.jeudelogique.view;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

public abstract class ZContainer {
	protected JPanel panel;
	
	protected Font comics30 = new Font("Comics Sans MS", Font.BOLD, 30);
	protected Font comics25 = new Font("Comics Sans MS", Font.BOLD, 25);
	protected Font comics20 = new Font("Comics Sans MS", Font.BOLD, 20);
	protected Font arial = new Font("Arial", Font.BOLD, 15);
	protected Font arial20 = new Font("Arial", Font.BOLD, 20);
	
	public ZContainer(Dimension dim) {
		this.panel = new JPanel();
//		this.panel.setBackground(Color.WHITE);
		this.panel.setPreferredSize(dim);
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	protected abstract void initPanel();

}
