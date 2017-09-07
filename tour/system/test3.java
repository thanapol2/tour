package tour.system;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class test3 {
	private static Object lock = new Object();
	private static JFrame frame = new JFrame();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] detail = "aaaa  vv a dd  ggg".split("  ");
		System.out.println(detail.length);
	}
}
