package com.ui;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import java.awt.BorderLayout;
import java.awt.Font;

public class loadingPanel extends JDialog {

	/**
	 * Create the panel.
	 */
	public loadingPanel() {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(null);
	    setSize(360, 126);
	    setResizable(false);
//		pack();
		setVisible(true);
		JLabel loadingLabel = new JLabel("Please wait.......");
		loadingLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		loadingLabel.setBounds(102, 11, 166, 14);
		getContentPane().add(loadingLabel);
		JProgressBar progressBar = new JProgressBar();
	    progressBar.setIndeterminate(true);
	    progressBar.setBounds(10, 44, 319, 33);
	    getContentPane().add(progressBar);
		
	}
}
