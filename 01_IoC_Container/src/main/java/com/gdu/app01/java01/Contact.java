package com.gdu.app01.java01;

public class Contact {
	
	// field
	private String tel;
	private String Fax;
	
	// default constructor
	public Contact() {
		
		
	}

	// constructor
	public Contact(String tel, String Fax) {
		super();
		this.tel = tel;
		this.Fax = Fax;
	}

	// method(getter, setter)
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return Fax;
	}

	public void setFax(String Fax) {
		this.Fax = Fax;
	}
	
	
	
	

}
