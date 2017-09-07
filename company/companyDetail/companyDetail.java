package company.companyDetail;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public abstract class companyDetail {
	protected companyView 		view;
	protected ActionListener 	clearAction;
	protected ActionListener 	cancelAction;
	protected companyDAO 		dao;
	
	/*
	 *  pic name => include .fileType (No path)
	 */
	
	protected void addListenerBtn(){
		cancelAction = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				view.clear();
				view.setVisible(false);
			}
		};
		view.getCancleBtn().addActionListener(cancelAction);
	}
	protected companyData setCompanyListData(){
		companyData temp = new companyData();
		temp.setComID(view.getComID());
		temp.settName(view.getThaiName());
		temp.seteName(view.getEngName());
		temp.setAddress(view.getAddress());
		temp.setProvince(view.getProvince());
		temp.setPostNum(view.getPostNumber());
		temp.setTelNo(view.getTelNo());
		temp.setemail(view.getEmail());
		temp.setTaxID(view.getTaxID());
		temp.setRemark(view.getComment());
		temp.setComType(view.getComType());
		return temp;
	}
	

}
