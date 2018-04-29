package com.contactapi.contactapp.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.contactapi.contactapp.entity.Contact;
import com.contactapi.contactapp.entity.ContactSkill;
import com.contactapi.contactapp.entity.Level;
import com.contactapi.contactapp.entity.Skill;

@Primary
@Repository
@Qualifier("ContactDaoImpl")
public class ContactDaoImpl implements ContactDao{
	
    static HashMap<Integer, Contact> contacts;
	static HashMap<Integer, HashMap<String,Level>> allSkillsContacts;

	static{	
			
		contacts = new HashMap<Integer, Contact>(){
			{
			put(1, new Contact(1,"James","Anne","JamesAnne","Lausanne","james@gmail.com", 4567543,"1234"));
			put(2, new Contact(2,"Peter","Clark","PeterClark","Zurich","peter@gmail.com", 646468,"1235"));
			put(3, new Contact(3,"Susan","Terrance","SusanTerrance","Bucharest","susan@gmail.com", 646468,"1235"));
				}
			};
	}

	@Override
	public Collection <Contact> getAllContacts(){
		return this.contacts.values();
	}

	@Override
	public void updateContact(Contact contact, int currentUserId) {

		Contact c = contacts.get(contact.getContactId());
		if (contacts.containsKey(currentUserId)&&c.getContactId()==(currentUserId)){
			c.setFirstName(contact.getFirstName());
			c.setLastName(contact.getLastName());
			c.setFullName(contact.getFullName());
			c.setEmail(contact.getEmail());
			c.setAdress(contact.getAdress());
			c.setMobileNumber(contact.getMobileNumber());
			c.setPassword(contact.getPassword());
		contacts.put(contact.getContactId(), contact);
		}else{
			System.out.println("Can only update valid User contact!");
		}
	}

	@Override
	public Contact getContactById(int contactId) {
		if (contacts.containsKey(contactId)){
			return this.contacts.get(contactId);
		}else{
			System.out.println("No such contact");
			return null;
		}
	}	

	@Override
	public void deleteContactById(int contactId){
		if(this.contacts.containsKey(contactId)){
			this.contacts.remove(contactId);
			
			if(SkillDaoImpl.allSkillsContacts.containsKey(contactId)){
			///	System.out.println("Deleted skills for contact "+ contacts.get(contactId).getFullName());

				SkillDaoImpl.allSkillsContacts.remove(contactId);
						}
		}else{
			System.out.println("No such contact");
		}
	}
		
	@Override
	public void insertContactInDb(Contact contact) {
		if (!this.contacts.containsKey(contact.getContactId())){
			this.contacts.put(contact.getContactId(), contact);		
		}else{
			System.out.print("Contact with id "+ contact.getContactId() + " already exists!");
		}
	}
}
