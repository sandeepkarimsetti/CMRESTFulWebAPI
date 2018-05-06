package com.contactsmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.contactsmanager.domain.*;

import com.contactsmanager.service.ContactsManagerServiceImpl;

//@CrossOrigin(origins = "https://ang5-cm-rest-web-api-9912638661.stackblitz.io", maxAge = 3600)
//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin
@RestController
@RequestMapping(value = "/CMWebAPI")
public class ContactsManagerController {
	
	@Autowired
	private ContactsManagerServiceImpl contactsManagerService;

	public ContactsManagerServiceImpl getContactsManagerService() {
		return contactsManagerService;
	}

	public void ContactsManagerServiceImpl (ContactsManagerServiceImpl contactsManagerService) {
		this.contactsManagerService = contactsManagerService;
	}
//	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultRequest() {
		return "invalid request";
	}

	// GET all customers
	// API URL: /CMWebAPI/contacts
//	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public List<Contact> getAllContacts() {
		
		System.out.println("==== In Controller getAllContacts method ====");
		
		List<Contact> fetchedContactsList = null;
		
		/*allContactsList.add(new Contact("First Name", "Last Name", 9912638661L, "first.last@gmail.com", "city"));
		allContactsList
				.add(new Contact("First Name 1", "Last Name 1", 9912638661L, "first1.last1@gmail.com", "city 1"));
		allContactsList
				.add(new Contact("First Name 2", "Last Name 2", 9951178491L, "first2.last2@gmail.com", "city 2"));
		allContactsList
				.add(new Contact("First Name 3", "Last Name 3", 9494531402L, "first3.last3@gmail.com", "city 3"));
		allContactsList
				.add(new Contact("First Name 4", "Last Name 4", 9959911666L, "first4.last4@gmail.com", "city 4"));*/

		fetchedContactsList = contactsManagerService.getAllContacts();
		
		return fetchedContactsList;

	}

	// GET a customer
	// API URL: /CMWebAPI/contacts/id
	//@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/contacts/{id}", method = RequestMethod.GET)
	public Contact getContactByID(@PathVariable("id") String contactID) {
		// edit this line
		Contact fetchedContact = contactsManagerService.getContactByID(Long.parseLong(contactID));
		
		return fetchedContact;
	}

	// POST a customer
	// API URL: /CMWebAPI/contacts
	//@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/contacts", method = RequestMethod.POST)
	public Contact createContact(@RequestBody Contact contact) {
		System.out.println("^^^^^ Request Body in createContact controller method ^^^^" + contact);
		
		if(contact == null) {
			return  new Contact(new Long(0), "", "", new Long(0), "", "");
		}
		// edit this line
		Contact newContact = contactsManagerService.createContact(contact);
		
		if(newContact != null) {
			newContact = getContactByID(newContact.getContactID()+"");
		}
		
		return newContact;
	}

	// PUT a customer
	// API URL: /CMWebAPI/contacts
	//@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/contacts/{id}", method = RequestMethod.PUT)
	public Contact updateContact(@PathVariable("id") String contactID, @RequestBody Contact contact) {
		
		if(contact == null) {
			return  new Contact(new Long(0), "", "", new Long(0), "", "");
		}
		
		// edit this line
		Contact updatedContact = contactsManagerService.updateContact(Long.parseLong(contactID), contact);
		
		return updatedContact;
	}

	// DELETE a customer
	// API URL: /CMWebAPI/contacts/id
	//@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/contacts/{id}", method = RequestMethod.DELETE)
	public Contact deleteContact(@PathVariable("id") String contactID) {
		// edit this line
		Contact deletedContact = contactsManagerService.deleteContact(Long.parseLong(contactID));
		return deletedContact;
	}

}
