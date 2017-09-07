package com.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.base.ConnectionPool;
import com.base.Tools;

import payment.detail.payment;
import tour.tourMain.tourMain;

public class login {
	private loginView view;
	public login(){
		view = new loginView();
		setLoginAction();
	}
	public void setCancelAction(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
			}
		};
		view.setCancelAction(action);
	}
	
	public void setLoginAction(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String userName = view.getLoginField();
				String pass = view.getPassField();
				int result = ConnectionPool.login(userName,pass);
				if(result==0){
					view.setVisible(false);
					tourMain frame = new tourMain(ConnectionPool.isAccountUser());
					frame.control();
				}else if(result==1){
					JOptionPane.showMessageDialog(view, "Login/Password is vaild","Error", JOptionPane.ERROR_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(view, "Cannot connect to Database");		
				}
			}
		};
		view.setLoginAction(action);
	}
	
	public void control(){
		view.setVisible(true);
	}
}
