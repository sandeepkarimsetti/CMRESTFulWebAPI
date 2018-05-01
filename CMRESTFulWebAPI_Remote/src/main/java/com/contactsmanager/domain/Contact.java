package com.contactsmanager.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "contacts")
public class Contact implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8460142289258996292L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "contact_id")
	private long contactID;
	
	@Column(name = "contact_fname")
	private String contactFName;
	
	@Column(name = "contact_lname")
	private String contactLName;
	
	@Column(name = "contact_mobile")
	private long contactMobile;
	
	@Column(name = "contact_email")
	private String contactEmail;
	
	@Column(name = "contact_city")
	private String contactCity;
	
	@Column(name = "created_date")
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	public Contact() {
		super();
	}
	
	public Contact(long contactID, String contactFName, String contactLName, long contactMobile, String contactEmail,
			String contactCity) {
		
		this.contactID = contactID;
		this.contactFName = contactFName;
		this.contactLName = contactLName;
		this.contactMobile = contactMobile;
		this.contactEmail = contactEmail;
		this.contactCity = contactCity;
	}

	public String getContactFName() {
		return contactFName;
	}

	public void setContactFName(String contactFName) {
		this.contactFName = contactFName;
	}

	public String getContactLName() {
		return contactLName;
	}

	public void setContactLName(String contactLName) {
		this.contactLName = contactLName;
	}

	public long getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(long contactMobile) {
		this.contactMobile = contactMobile;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactCity() {
		return contactCity;
	}

	public void setContactCity(String contactCity) {
		this.contactCity = contactCity;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public long getContactID() {
		return contactID;
	}

	@Override
	public String toString() {
		return "Contact [contactID=" + contactID + ", contactFName=" + contactFName + ", contactLName=" + contactLName
				+ ", contactMobile=" + contactMobile + ", contactEmail=" + contactEmail + ", contactCity=" + contactCity
				+ ", createdDate=" + createdDate + "]";
	}
	
	

}
