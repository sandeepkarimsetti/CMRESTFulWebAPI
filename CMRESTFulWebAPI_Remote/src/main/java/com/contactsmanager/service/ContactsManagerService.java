package com.contactsmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.contactsmanager.domain.Contact;

public interface ContactsManagerService {
	
	public List<Contact> getAllContacts();

	public Contact getContactByID(long contactID);

	public Contact createContact(Contact contact);

	public Contact updateContact(long contactID, Contact contact);

	public Contact deleteContact(long contactID);

}
