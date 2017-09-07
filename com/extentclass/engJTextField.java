package com.extentclass;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

/*
 * A JTextField that only accept the number of digits it should 
 * (declared in the constructor) and accept only digits in it 
 */
public class engJTextField extends JTextField implements DocumentListener {

	private static final long serialVersionUID = 201302141630L;
	private int nbColumns;
	private StringBuilder sb;

	public engJTextField(int nbColumns) {
		super(nbColumns);
		this.nbColumns = nbColumns;
		getDocument().addDocumentListener(this);
	}

	/*
	 * Make sure good length and numeric
	 */
	private void validateText() {

		String str = getText();
//		System.out.println("Test is >" + str + "<");
		int len = str.length();
		// if nothing to do
		if(len == 0)
			return;	
		// if we will need to update the text
		boolean updateRequired = false;
		sb = new StringBuilder(nbColumns);

		// truncate if too long
		if(len > nbColumns) {
			str = str.substring(0, nbColumns);
			updateRequired = true;
			len = nbColumns;
		}
		// cumulate the valid digts in the StringBuiilder
		for(int i = 0; i < len; ++i) {
			char digit = str.charAt(i);
			if(digit >= 'a' && digit <= 'z')
				sb.append(digit);
			else
				updateRequired = true;   // flag at least one invalid digit
		}
		// if truncated or invalid digit found
		if(updateRequired) {
			
		    SwingUtilities.invokeLater(new Runnable() {
		        @Override
		        public void run() {
		            setText(sb.toString());
		        }
		    });
			
		}
	}
		

	@Override
	public void changedUpdate(DocumentEvent de) {
		validateText();
	}

	@Override
	public void insertUpdate(DocumentEvent de) {
		validateText();
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
	}

}

