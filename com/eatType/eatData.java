package com.eatType;

public class eatData {
	private String noPig;
	private String noMeat;
	private String noChic;
	private String halan;
	private String mangsa;
	private String veg;
	private String islam;
	private String noSeafood;
	private String noFish;
	private String noShrimp;
	
	
	public eatData(){
		noPig = "N";
		noMeat = "N";
		noChic = "N";
		halan = "N";
		mangsa = "N";
		veg = "N";
		islam ="N";
		noSeafood = "N";
		noFish = "N";
		noShrimp ="N";
	}
	
	public boolean isCheck(){
		return isNoPig()||
				isNoMeat()||
				isNoChic()||
				isHalan()||
				isMangsa()||
				isVeg()||
				isIslam()||
				isNoSeafood()||
				isNoFish()||
				isNoShrimp();
				
	}
	
	public boolean isNoShrimp() {
		boolean result = false;
		if(noShrimp.equals("Y")){
			result = true;
		}
		return result;
	}
	public boolean isNoFish() {
		boolean result = false;
		if(noFish.equals("Y")){
			result = true;
		}
		return result;
	}
	public boolean isNoSeafood() {
		boolean result = false;
		if(noSeafood.equals("Y")){
			result = true;
		}
		return result;
	}
	public boolean isIslam() {
		boolean result = false;
		if(islam.equals("Y")){
			result = true;
		}
		return result;
	}
	public boolean isVeg() {
		boolean result = false;
		if(veg.equals("Y")){
			result = true;
		}
		return result;
	}
	public boolean isMangsa() {
		boolean result = false;
		if(mangsa.equals("Y")){
			result = true;
		}
		return result;
	}
	public boolean isHalan() {
		boolean result = false;
		if(halan.equals("Y")){
			result = true;
		}
		return result;
	}
	public boolean isNoChic() {
		boolean result = false;
		if(noChic.equals("Y")){
			result = true;
		}
		return result;
	}
	
	public boolean isNoPig() {
		boolean result = false;
		if(noPig.equals("Y")){
			result = true;
		}
		return result;
	}
	
	public boolean isNoMeat() {
		boolean result = false;
		if(noMeat.equals("Y")){
			result = true;
		}
		return result;
	}
	
	public String getNoPig() {
		return noPig;
	}
	public void setNoPig(String noPig) {
		this.noPig = noPig;
	}
	public String getNoMeat() {
		return noMeat;
	}
	public void setNoMeat(String noMeat) {
		this.noMeat = noMeat;
	}
	public String getNoChic() {
		return noChic;
	}
	public void setNoChic(String noChic) {
		this.noChic = noChic;
	}
	public String getHalan() {
		return halan;
	}
	public void setHalan(String halan) {
		this.halan = halan;
	}
	public String getMangsa() {
		return mangsa;
	}
	public void setMangsa(String mangsa) {
		this.mangsa = mangsa;
	}
	public String getVeg() {
		return veg;
	}
	public void setVeg(String veg) {
		this.veg = veg;
	}
	public String getIslam() {
		return islam;
	}
	public void setIslam(String islam) {
		this.islam = islam;
	}
	public String getNoSeafood() {
		return noSeafood;
	}
	public void setNoSeafood(String noSeafood) {
		this.noSeafood = noSeafood;
	}
	public String getNoFish() {
		return noFish;
	}
	public void setNoFish(String noFish) {
		this.noFish = noFish;
	}
	public String getNoShrimp() {
		return noShrimp;
	}
	public void setNoShrimp(String noShrimp) {
		this.noShrimp = noShrimp;
	}
}
