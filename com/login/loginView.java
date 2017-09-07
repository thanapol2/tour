package com.login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class loginView extends JFrame {

	private JPanel contentPane;
	private JTextField loginField;
	private JPasswordField passField;
	private JButton cancelBtn;
	private JButton loginBtn;

	/**
	 * Create the frame.
	 */
	public loginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 245);
//		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		loginField = new JTextField();
		loginField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loginField.setBounds(138, 47, 173, 34);
		contentPane.add(loginField);
		loginField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Login  :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(40, 45, 88, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password  :");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(40, 106, 88, 34);
		contentPane.add(lblPassword);
		
		passField = new JPasswordField();
		passField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passField.setBounds(138, 106, 173, 34);
		contentPane.add(passField);
		
		loginBtn = new JButton("Login");
		loginBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		loginBtn.setBounds(59, 161, 98, 34);
		contentPane.add(loginBtn);
		
		cancelBtn = new JButton("Cancel");
		cancelBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		cancelBtn.setBounds(213, 161, 98, 34);
		contentPane.add(cancelBtn);
	}

	public void setCancelAction(ActionListener action) {
		// TODO Auto-generated method stub
		
	}

	public void setLoginAction(ActionListener action) {
		loginBtn.addActionListener(action);
		
	}

	public String getPassField() {
		char[] pass = passField.getPassword();
		String passString = new String(pass);
		return passString;
	}

	public void setPassField(String passField) {
		this.passField.setText(passField);
	}

	public String getLoginField() {
		return loginField.getText();
	}

}
