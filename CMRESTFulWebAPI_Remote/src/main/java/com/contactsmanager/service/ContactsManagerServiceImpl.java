package com.contactsmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contactsmanager.domain.Contact;
import com.contactsmanager.repository.ContactsManagerDAO;
import com.contactsmanager.repository.ContactsManagerDAOImpl;

@Service
public class ContactsManagerServiceImpl implements ContactsManagerService{
	
	@Autowired
	private ContactsManagerDAOImpl contactsManagerDAO;

	public ContactsManagerDAOImpl getContactsManagerDAO() {
		return contactsManagerDAO;
	}

	public void ContactsManagerDAOImpl (ContactsManagerDAOImpl contactsManagerDAO) {
		this.contactsManagerDAO = contactsManagerDAO;
	}

	@Override
	public List<Contact> getAllContacts() {
		
		System.out.println("++++ In Service getAllContacts method +++++");
		
		List<Contact> allContactsFromDB = contactsManagerDAO.getAllContacts();
		
		return allContactsFromDB;
	}

	@Override
	public Contact getContactByID(long contactID) {

		Contact selectedContactByID = contactsManagerDAO.getContactByID(contactID);
		
		return selectedContactByID;
	}

	@Override
	
	public Contact createContact(Contact contact) {

		Contact createdContact = contactsManagerDAO.createContact(contact);
		return createdContact;
	}

	@Override
	public Contact updateContact(long contactID, Contact contact) {

		Contact updatedContact = contactsManagerDAO.updateContact(contactID, contact);
		return updatedContact;
	}

	@Override
	public Contact deleteContact(long contactID) {

		Contact deletedContact = contactsManagerDAO.deleteContact(contactID);
		return deletedContact;
	}

}
