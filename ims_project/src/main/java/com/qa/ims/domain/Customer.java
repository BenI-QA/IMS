package com.qa.ims.domain;


public class Customer {
	private Long ID;
	private String first_name;
	private String last_name;
	private String email;
	private Long phone;
	
	public Customer(Long iD, String f_name, String l_name, String email, Long phone) {
		this.ID = iD;
		this.first_name = f_name;
		this.last_name= l_name;
		this.email = email;
		this.phone = phone;
	}
	
	public Customer(String f_name, String l_name, String email, Long phone) {
		this.first_name = f_name;
		this.last_name= l_name;
		this.email = email;
		this.phone = phone;
	}
	
	
	public long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return "id:" + ID + " first name:" + first_name + " surname:" + last_name + " Email:" + email + " Phone No.:"+phone ;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (ID != other.ID)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		if (phone != other.phone)
			return false;
		return true;
	}

}

