package com.contactsmanager.controller;

import java.util.List;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping(value = "/CMWebAPI")
public class ContactsManagerController {

	@Autowired
	private ContactsManagerServiceImpl contactsManagerService;

	public ContactsManagerServiceImpl getContactsManagerService() {
		return contactsManagerService;
	}

	public void ContactsManagerServiceImpl(ContactsManagerServiceImpl contactsManagerService) {
		this.contactsManagerService = contactsManagerService;
	}

	// @CrossOrigin(origins = "*", maxAge = 3600)
	// @CrossOrigin
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultRequest() {
		return "invalid request";
	}

	// GET all customers
	// API URL: /CMWebAPI/contacts
	// @CrossOrigin
	// @CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public ResponseEntity<List<Contact>> getAllContacts() {

		System.out.println("==== In Controller getAllContacts method ====");

		List<Contact> fetchedContactsList = null;

		HttpHeaders responseHeaders = new HttpHeaders();

		responseHeaders.add("Access-Control-Allow-Origin", "*");
		responseHeaders.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD");
		responseHeaders.add("Access-Control-Max-Age", "3600");
		responseHeaders.add("Access-Control-Allow-Headers", "*");
		responseHeaders.add("Access-Control-Expose-Headers",
				"content-type,transfer-encoding,date,connection,cache-control,x-final-url,access-control-allow-origin");

		fetchedContactsList = contactsManagerService.getAllContacts();

		return new ResponseEntity<List<Contact>>(fetchedContactsList, responseHeaders, HttpStatus.OK);

	}

	// GET a customer
	// API URL: /CMWebAPI/contacts/id
	// @CrossOrigin(origins = "*", maxAge = 3600)
	// @CrossOrigin
	// @RequestMapping(value = "/contacts/{id}", method = RequestMethod.GET)
	// public Contact getContactByID(@PathVariable("id") String contactID) {
	/*
	 * public Contact getContactByID(String contactID) { // edit this line Contact
	 * fetchedContact =
	 * contactsManagerService.getContactByID(Long.parseLong(contactID));
	 * 
	 * return fetchedContact; }
	 */

	@RequestMapping(value = "/contacts/{id}", method = RequestMethod.GET)
	public ResponseEntity<Contact> getContactResponseByID(@PathVariable("id") String contactID) {
		// edit this line
		Contact fetchedContact = contactsManagerService.getContactByID(Long.parseLong(contactID));

		HttpHeaders responseHeaders = new HttpHeaders();

		responseHeaders.add("Access-Control-Allow-Origin", "*");
		responseHeaders.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD");
		responseHeaders.add("Access-Control-Max-Age", "3600");
		responseHeaders.add("Access-Control-Allow-Headers", "*");
		responseHeaders.add("Access-Control-Expose-Headers",
				"content-type,transfer-encoding,date,connection,cache-control,x-final-url,access-control-allow-origin");

		return new ResponseEntity<Contact>(fetchedContact, responseHeaders, HttpStatus.OK);
	}

	// POST a customer
	// API URL: /CMWebAPI/contacts
	// @CrossOrigin(origins = "*", maxAge = 3600)
	// @CrossOrigin
	@RequestMapping(value = "/contacts", method = RequestMethod.POST)
	public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
		System.out.println("^^^^^ Request Body in createContact controller method ^^^^" + contact);
		
		HttpHeaders responseHeaders = new HttpHeaders();

		responseHeaders.add("Access-Control-Allow-Origin", "*");
		responseHeaders.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD");
		responseHeaders.add("Access-Control-Max-Age", "3600");
		responseHeaders.add("Access-Control-Allow-Headers", "*");
		responseHeaders.add("Access-Control-Expose-Headers",
				"content-type,transfer-encoding,date,connection,cache-control,x-final-url,access-control-allow-origin");

		if (contact == null) {
			return new ResponseEntity<Contact>(new Contact(new Long(0), "", "", new Long(0), "", ""), responseHeaders, HttpStatus.OK);
		}
		// edit this line
		Contact newContact = contactsManagerService.createContact(contact);

		if (newContact != null) {
			newContact = contactsManagerService.getContactByID(newContact.getContactID());
		}

		return new ResponseEntity<Contact>(newContact, responseHeaders, HttpStatus.OK);
	}

	// PUT a customer
	// API URL: /CMWebAPI/contacts
	// @CrossOrigin(origins = "*", maxAge = 3600)
	// @CrossOrigin
	@RequestMapping(value = "/contacts/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Contact> updateContact(@PathVariable("id") String contactID, @RequestBody Contact contact) {
		
		HttpHeaders responseHeaders = new HttpHeaders();

		responseHeaders.add("Access-Control-Allow-Origin", "*");
		responseHeaders.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD");
		responseHeaders.add("Access-Control-Max-Age", "3600");
		responseHeaders.add("Access-Control-Allow-Headers", "*");
		responseHeaders.add("Access-Control-Expose-Headers",
				"content-type,transfer-encoding,date,connection,cache-control,x-final-url,access-control-allow-origin");

		if (contact == null) {
			return new ResponseEntity<Contact>(new Contact(new Long(0), "", "", new Long(0), "", ""), responseHeaders, HttpStatus.OK);
		}

		// edit this line
		Contact updatedContact = contactsManagerService.updateContact(Long.parseLong(contactID), contact);

		return new ResponseEntity<Contact>(updatedContact, responseHeaders, HttpStatus.OK);
	}

	// DELETE a customer
	// API URL: /CMWebAPI/contacts/id
	// @CrossOrigin(origins = "*", maxAge = 3600)
	// @CrossOrigin
	@RequestMapping(value = "/contacts/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Contact> deleteContact(@PathVariable("id") String contactID) {
		
		HttpHeaders responseHeaders = new HttpHeaders();

		responseHeaders.add("Access-Control-Allow-Origin", "*");
		responseHeaders.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD");
		responseHeaders.add("Access-Control-Max-Age", "3600");
		responseHeaders.add("Access-Control-Allow-Headers", "*");
		responseHeaders.add("Access-Control-Expose-Headers",
				"content-type,transfer-encoding,date,connection,cache-control,x-final-url,access-control-allow-origin");
		
		// edit this line
		Contact deletedContact = contactsManagerService.deleteContact(Long.parseLong(contactID));
		return new ResponseEntity<Contact>(deletedContact, responseHeaders, HttpStatus.OK);
	}

}
