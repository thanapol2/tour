package tour.tourGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.base.icon;

import company.companyDetail.companyDetail;
import tour.tourListDetail.listDetailEdit;
import tour.tourListDetail.listDetailInsert;
import tour.tourListSearch.listSearch;
import tour.tourMain.subAccountMain;
import tour.tourMain.tourMain;

public class subAccountUserRun {

//	private listSearch frame;
//	private listDetailInsert frame;
	private subAccountMain frame;
	private final boolean IS_ACCOUNT = true;
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
					subAccountUserRun window = new subAccountUserRun();
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
	public subAccountUserRun() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		frame = new listSearch();
//		frame = new listDetailInsert();
		frame = new subAccountMain(IS_ACCOUNT);
//		frame = new companyDetail();
//		frame = new listDetailEdit();
		
	}

}
