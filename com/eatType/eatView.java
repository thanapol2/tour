package com.eatType;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import java.awt.Font;

public class eatView extends JPanel {

	/**
	 * Create the panel.
	 */
	private JCheckBox noPig;
	private JCheckBox noMeat;
	private JCheckBox noChic;
	private JCheckBox halan;
	private JCheckBox mangsa;
	private JCheckBox veg;
	private JCheckBox islam;
	private JCheckBox noSeafood;
	private JCheckBox noFish;
	private JCheckBox noShrimp;
	public eatView() {
		setLayout(null);
//		setSize(219, 484);
		noPig = new JCheckBox("\u0E44\u0E21\u0E48\u0E17\u0E32\u0E19\u0E2B\u0E21\u0E39");
		noPig.setFont(new Font("Tahoma", Font.PLAIN, 13));
		noPig.setBounds(17, 27, 97, 23);
		add(noPig);
		
		noMeat = new JCheckBox("\u0E44\u0E21\u0E48\u0E17\u0E32\u0E19\u0E40\u0E19\u0E37\u0E49\u0E2D");
		noMeat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		noMeat.setBounds(17, 67, 97, 23);
		add(noMeat);
		
		noChic = new JCheckBox("\u0E44\u0E21\u0E48\u0E17\u0E32\u0E19\u0E44\u0E01\u0E48");
		noChic.setFont(new Font("Tahoma", Font.PLAIN, 13));
		noChic.setBounds(17, 107, 97, 23);
		add(noChic);
		
		halan = new JCheckBox("\u0E2E\u0E32\u0E25\u0E32\u0E19");
		halan.setFont(new Font("Tahoma", Font.PLAIN, 13));
		halan.setBounds(17, 147, 97, 23);
		add(halan);
		
		mangsa = new JCheckBox("\u0E21\u0E31\u0E07\u0E2A\u0E27\u0E34\u0E23\u0E31\u0E15");
		mangsa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		mangsa.setBounds(17, 187, 97, 23);
		add(mangsa);
		
		veg = new JCheckBox("\u0E40\u0E27\u0E40\u0E08\u0E17\u0E32\u0E40\u0E23\u0E35\u0E48\u0E22\u0E19");
		veg.setFont(new Font("Tahoma", Font.PLAIN, 13));
		veg.setBounds(17, 227, 97, 23);
		add(veg);
		
		islam = new JCheckBox("\u0E2D\u0E34\u0E2A\u0E25\u0E32\u0E21(\u0E44\u0E21\u0E48\u0E40\u0E04\u0E23\u0E48\u0E07\u0E21\u0E32\u0E01)");
		islam.setFont(new Font("Tahoma", Font.PLAIN, 13));
		islam.setBounds(17, 267, 135, 23);
		add(islam);
		
		noSeafood = new JCheckBox("\u0E44\u0E21\u0E48\u0E17\u0E32\u0E19Seafood");
		noSeafood.setFont(new Font("Tahoma", Font.PLAIN, 13));
		noSeafood.setBounds(17, 387, 118, 23);
		add(noSeafood);
		
		noFish = new JCheckBox("\u0E44\u0E21\u0E48\u0E17\u0E32\u0E19\u0E1B\u0E25\u0E32");
		noFish.setFont(new Font("Tahoma", Font.PLAIN, 13));
		noFish.setBounds(17, 347, 118, 23);
		add(noFish);
		
		noShrimp = new JCheckBox("\u0E44\u0E21\u0E48\u0E17\u0E32\u0E19\u0E01\u0E38\u0E49\u0E07");
		noShrimp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		noShrimp.setBounds(17, 307, 118, 23);
		add(noShrimp);
	}
	public void setData (eatData data){
		noPig.setSelected(data.isNoPig());
		noMeat.setSelected(data.isNoMeat());
		noChic.setSelected(data.isNoChic());
		halan.setSelected(data.isHalan());
		mangsa.setSelected(data.isMangsa());
		veg.setSelected(data.isVeg());
		islam.setSelected(data.isIslam());
		noSeafood.setSelected(data.isNoSeafood());
		noFish.setSelected(data.isNoFish());
		noShrimp.setSelected(data.isNoShrimp());
	}
	
	public void setSelectAll(boolean isSelected){
		noPig.setSelected(isSelected);
		noMeat.setSelected(isSelected);
		noChic.setSelected(isSelected);
		halan.setSelected(isSelected);
		mangsa.setSelected(isSelected);
		veg.setSelected(isSelected);
		islam.setSelected(isSelected);
		noSeafood.setSelected(isSelected);
		noFish.setSelected(isSelected);
		noShrimp.setSelected(isSelected);
	}
	
	public void setEnableAll(boolean isSelected){
		noPig.setEnabled(isSelected);
		noMeat.setEnabled(isSelected);
		noChic.setEnabled(isSelected);
		halan.setEnabled(isSelected);
		mangsa.setEnabled(isSelected);
		veg.setEnabled(isSelected);
		islam.setEnabled(isSelected);
		noSeafood.setEnabled(isSelected);
		noFish.setEnabled(isSelected);
		noShrimp.setEnabled(isSelected);
	}
	
	public eatData getData(){
		eatData data = new eatData();
		if(noPig.isSelected()){
			data.setNoPig("Y");
		}
		if(noMeat.isSelected()){
			data.setNoMeat("Y");
		}
		if(noChic.isSelected()){
			data.setNoChic("Y");
		}
		if(halan.isSelected()){
			data.setHalan("Y");
		}
		if(mangsa.isSelected()){
			data.setMangsa("Y");
		}
		if(veg.isSelected()){
			data.setVeg("Y");
		}
		if(islam.isSelected()){
			data.setIslam("Y");
		}
		if(noSeafood.isSelected()){
			data.setNoSeafood("Y");
		}
		if(noFish.isSelected()){
			data.setNoFish("Y");
		}
		if(noShrimp.isSelected()){
			data.setNoShrimp("Y");
		}
		return data;
	}
}
