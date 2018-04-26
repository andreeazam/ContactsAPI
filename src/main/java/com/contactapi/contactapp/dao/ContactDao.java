package com.contactapi.contactapp.dao;

import java.util.Collection;

import com.contactapi.contactapp.entity.Contact;

public interface ContactDao {

	Collection<Contact> getAllContacts();

	void updateContact(Contact contact, int currentUserId);

	Contact getContactById(int contactid);

	void deleteContactById(int contactid);

	void insertContactInDb(Contact contact);

}