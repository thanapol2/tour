package temp;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JDialog;
import javax.swing.table.AbstractTableModel;

import com.selectAddList.listAddSelect;
import com.table.rowMember;

import payment.search.rowPaymentHead;

public class test {
	public static void main(String[] args) {
		String b = "ลองูีd";
		int index = getSplitindex(b);
		System.out.println(b.length());
		System.out.println(index);
		System.out.println(b.substring(0,index));
	}
	private static int getSplitindex(String data){
		ArrayList<String> consonant = new ArrayList<>(Arrays.asList("่","้","๊","๋","็"
				,"ึ","ึ","ื","ี","ุ","ู"));
		int index = 0;
		if(data.length()<4){
			index = data.length();
		}else{
			if(consonant.contains(Character.toString(data.charAt(4)))){
				index = 4;
				while(index<data.length()){
					if(consonant.contains(Character.toString(data.charAt(index)))){
						index = index + 1;
					}else{
						return index;
					}
				}
				
			}else{
				index = 3 ;
			}
		}
		return index;
	}
}
