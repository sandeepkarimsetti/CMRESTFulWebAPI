package com.contactsmanager.repository;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.contactsmanager.domain.Contact;

@Repository
public class ContactsManagerDAOImpl implements ContactsManagerDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getAllContacts() {
		System.out.println("**** In DAO getAllContacts method ****");
		Session session = sessionFactory.openSession();
		//List<Contact> allContactsFromDB = session.createQuery("from Contact").list();
		Transaction tr = session.beginTransaction();
		//Criteria criteria = session.createCriteria(Contact.class);
		//List<Contact> allContactsFromDB = criteria.list();
		List<Contact> allContactsFromDB = session.createQuery("from Contact").list();
		//added for loop for testing purpose 
		for(Contact eachContact : allContactsFromDB){
			System.out.println(eachContact.toString());
		}
		tr.commit();
		session.close();
		return allContactsFromDB;
	}

	@Override
	public Contact getContactByID(long contactID) {
		System.out.println("**** In DAO getContactByID method ****");
		System.out.println("**** contactID as method arg is: ****"+contactID);
		
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		//Contact selectedContactByID = (Contact) session.load(Contact.class, contactID);
		Contact selectedContactByID = (Contact) session.get(Contact.class, contactID);
		System.out.println("=== retrieved object is === "+selectedContactByID);
		if(selectedContactByID == null) {
			selectedContactByID = new Contact(new Long(0), "", "", new Long(0), "", "");
			selectedContactByID.setCreatedDate(new Date(0));
			//""
		}
		tr.commit();
		session.close();
		
		return selectedContactByID;
	}

	@Override
	public Contact createContact(Contact contact) {
		System.out.println("**** In DAO createContact method ****");
		
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Contact createdContact = null;
		//session.persist(contact);
		contact.setCreatedDate(new Date());
		Long generatedContactId = (Long) session.save(contact);
		createdContact = session.get(Contact.class, generatedContactId);
		if(createdContact == null) {
			createdContact = new Contact(new Long(0), "", "", new Long(0), "", "");
			createdContact.setCreatedDate(new Date(0));
		}
		tr.commit();
		session.close();
		System.out.println("**** Created Object in DAO createContact method ****"+createdContact);
		return createdContact;
	}

	@Override
	public Contact updateContact(long contactID, Contact newContact) {
		System.out.println("**** In DAO updateContact method ****");
		
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Contact updatedContact = session.get(Contact.class, contactID);
		if(updatedContact != null){
			updatedContact.setContactFName(newContact.getContactFName());
			updatedContact.setContactLName(newContact.getContactLName());
			updatedContact.setContactMobile(newContact.getContactMobile());
			updatedContact.setContactEmail(newContact.getContactEmail());
			updatedContact.setContactCity(newContact.getContactCity());
			session.update(updatedContact);
			updatedContact = session.get(Contact.class, contactID);
		}else {
			updatedContact = new Contact(new Long(0), "", "", new Long(0), "", "");
			updatedContact.setCreatedDate(new Date(0));
		}
				
		tr.commit();
		session.close();
		return updatedContact;
	}

	@Override
	public Contact deleteContact(long contactID) {
		System.out.println("**** In DAO deleteContact method ****");
		
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Contact contactToBeDeleted = (Contact) session.get(Contact.class, contactID);
		//Contact selectedContactByID = (Contact) session.load(Contact.class, new Long(contactID));
		System.out.println("=== retrieved object is === "+contactToBeDeleted);
		if(null != contactToBeDeleted) {
			session.delete(contactToBeDeleted);
		}else {
			contactToBeDeleted = new Contact(new Long(0), "", "", new Long(0), "", "");
			contactToBeDeleted.setCreatedDate(new Date(0));
		}		
		tr.commit();
		session.close();
		return contactToBeDeleted;
	}

}
