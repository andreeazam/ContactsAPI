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
	private static HashMap<Integer, HashMap<String, Level>> skills;
	private static int loginId;
	private static HashMap <Integer, Skill> entitySkills;
	private static Skill skill;

	static{	
			
		contacts = new HashMap<Integer, Contact>(){
			{
			put(1, new Contact(1,"James","Anne","JamesAnne","Lausanne","james@gmail.com", 4567543,"1234"));
			put(2, new Contact(2,"Peter","Clark","PeterClark","Zurich","clark@gmail.com", 646468,"1235"));
			put(3, new Contact(3,"Susan","Terrance","SusanTerrance","Zurich","susan@gmail.com", 646468,"1235"));
				}
			};
	}
	/* (non-Javadoc)
	 * @see com.contactapi.contactapp.dao.ContactDao#getAllContacts()
	 */
	@Override
	public Collection <Contact> getAllContacts(){
		return this.contacts.values();
	}
	
	/* (non-Javadoc)
	 * @see com.contactapi.contactapp.dao.ContactDao#updateContact(com.Id.contactapp.entity.Contact)
	 */
	@Override
	public void updateContact(Contact contact, int contactId) {

		Contact c = contacts.get(contact.getContactId());
		if (c.getContactId()==(contactId)){
			c.setFirstName(contact.getFirstName());
			c.setLastName(contact.getLastName());
			c.setFullName(contact.getFullName());
			c.setEmail(contact.getEmail());
			c.setAdress(contact.getAdress());
			c.setMobileNumber(contact.getMobileNumber());
			c.setPassword(contact.getPassword());
		contacts.put(contact.getContactId(), contact);
		}else{
			System.out.println("Can only update User contact!");
		}
	}
		
	/* (non-Javadoc)
	 * @see com.contactapi.contactapp.dao.ContactDao#getContactById(int)
	 */
	@Override
	public Contact getContactById(int contactid) {
		return this.contacts.get(contactid);
	}	
	
	/* (non-Javadoc)
	 * @see com.contactapi.contactapp.dao.ContactDao#deleteContactById(int)
	 */
	@Override
	public void deleteContactById(int contactid){
		this.contacts.remove(contactid);
	}

	/* (non-Javadoc)
	 * @see com.contactapi.contactapp.dao.ContactDao#insertContactInDb(com.contactapi.contactapp.entity.Contact)
	 */
	@Override
	public void insertContactInDb(Contact contact) {
		this.contacts.put(contact.getContactId(), contact);		
	}
}
