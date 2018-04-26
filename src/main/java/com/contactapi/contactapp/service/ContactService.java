package com.contactapi.contactapp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.contactapi.contactapp.dao.ContactDao;
import com.contactapi.contactapp.entity.Contact;

@Service
@Qualifier("ContactDaoImpl")
public class ContactService {
	
	@Autowired	
	private ContactDao contactDao;
	
	public Collection <Contact> getAllContacts(){
		return this.contactDao.getAllContacts();
	}
	
	public void updateContact(Contact contact, int currentUserId){
		this.contactDao.updateContact(contact, currentUserId);
	}
	
	public Contact getContactById(int id){
		return this.contactDao.getContactById(id);
	}
	
	public void deleteContactById(int contactid){
		this.contactDao.deleteContactById(contactid);
	}

	public void insertContactInDb(Contact contact) {
		this.contactDao.insertContactInDb(contact);
		
	}

}
