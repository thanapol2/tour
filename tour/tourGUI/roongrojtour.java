package tour.tourGUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.base.ConnectionPool;
import com.base.icon;
import com.login.login;

import company.companyDetail.companyDetail;
import tour.tourListDetail.listDetailEdit;
import tour.tourListDetail.listDetailInsert;
import tour.tourListSearch.listSearch;
import tour.tourMain.tourMain;

public class roongrojtour {

//	private listSearch frame;
//	private listDetailInsert frame;
	private login frame;
//	private final boolean IS_ACCOUNT = true;
//	private companyDetail frame;
//	private listDetailEdit frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					roongrojtour window = new roongrojtour();
					window.frame.control();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public roongrojtour() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		frame = new listSearch();
//		frame = new listDetailInsert();
//		frame = new tourMain(IS_ACCOUNT);
//		frame = new companyDetail();
//		frame = new listDetailEdit();
		frame = new login();
	}


}
