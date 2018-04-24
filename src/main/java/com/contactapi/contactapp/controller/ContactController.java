package com.contactapi.contactapp.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.contactapi.contactapp.entity.Contact;
import com.contactapi.contactapp.service.ContactService;

@RestController
@RequestMapping("/contacts")
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Collection <Contact> getAllContacts(){
		return this.contactService.getAllContacts();
	}
	
	@PutMapping(value = "/{currentUserId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateContact(@RequestBody Contact contact, @PathVariable("contactId") int currentUserId){
		contactService.updateContact(contact, currentUserId);
	}
	
	@RequestMapping(value = "/{contactId}", method = RequestMethod.GET)
	public Contact getContactById(@PathVariable("contactId") int id){
		return contactService.getContactById(id);
	}    
	
	@RequestMapping(value = "/{contactid}", method = RequestMethod.DELETE)
	public void deleteContactById(@PathVariable("contactid") int contactid){
		 this.contactService.deleteContactById(contactid);
	}    
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertContactInDb(@RequestBody Contact contact){
		contactService.insertContactInDb(contact);
	}
}
