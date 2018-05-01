package com.contactsmanager.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.contactsmanager.domain.Contact;

public interface ContactsManagerDAO {
	
	public List<Contact> getAllContacts();

	public Contact getContactByID(long contactID);

	public Contact createContact(Contact contact);

	public Contact updateContact(long contactID, Contact contact);

	public Contact deleteContact(long contactID);

}
