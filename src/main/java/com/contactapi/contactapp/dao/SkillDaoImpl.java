package com.contactapi.contactapp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.contactapi.contactapp.entity.Contact;
import com.contactapi.contactapp.entity.ContactSkill;
import com.contactapi.contactapp.entity.Level;
import com.contactapi.contactapp.entity.Skill;

@Repository
@Qualifier("SkillDaoImpl")
public class SkillDaoImpl implements SkillDao{
	static HashMap<Integer, Contact> contacts = ContactDaoImpl.contacts;
	private static HashMap<Integer, HashMap<String, Level>> allSkillsContacts;
	private static List<Skill> skillsList = new ArrayList<Skill>();
	private ContactSkill contactSkill;
	
	static{
		
		skillsList = new ArrayList<Skill>(){
			{
				add(new Skill("Ruby"));
				add(new Skill("XML"));
				add(new Skill("HTTP"));
			}
		};
		
		allSkillsContacts = new HashMap<Integer, HashMap<String, Level>>(){

			{   
				{
					HashMap<String,Level> contactSkills = new HashMap<String,Level>();
					contactSkills.put(skillsList.get(0).getName(), Level.ADVANCED);
					contactSkills.put(skillsList.get(1).getName(), Level.INTERMEDIATE);
					put(contacts.get(1).getContactId(), contactSkills);
				}
				
				{
					HashMap<String,Level> contactSkills = new HashMap<String,Level>();
					contactSkills.put(skillsList.get(2).getName(), Level.ADVANCED);
					contactSkills.put(skillsList.get(1).getName(), Level.BEGINNER);
					put(contacts.get(2).getContactId(), contactSkills);
				}
			}
		};	
	}

	@Override
	public HashMap<String, Level> getSkillsByContactID(int contactID){
		return this.allSkillsContacts.get(contactID);
	}

	@Override
	public HashMap<Integer, HashMap<String, Level>> getAllSkillsContactIDs() {
		return this.allSkillsContacts;
	}
	
	@Override
	public List<Skill> getAllSkills() {
		return this.skillsList;
	}

	@Override
	public void updateSkillForContact(ContactSkill contactSkill,int contactId) {
		
		HashMap<String,Level> contactSkills = allSkillsContacts.get(contactId);
		contactSkills.put(contactSkill.getSkill().getName(), contactSkill.getLevel());
		 
		if(allSkillsContacts.containsKey(contactId)){
			this.allSkillsContacts.put(contactId, contactSkills);
		}else{ 
			System.out.println("No such contact");
		}
	}

	@Override
	public void addSkillToContact(ContactSkill newSkill, Integer contactId) {
		

		HashMap<String,Level> contactSkills = getSkillsByContactID(contactId);
		Skill skillEntity = new Skill(newSkill.getSkill().getName());
		Level level = newSkill.getLevel();

		// Adds new skill to entity skill list if it does not exist:
		if(!this.skillsList.contains(skillEntity)){
			this.skillsList.add(skillEntity);
		}
		//Adds new skill to contacts skills
		if(!contactSkills.containsKey(skillEntity.getName())){
			contactSkills.put(skillEntity.getName(), level);
			this.allSkillsContacts.put(contactId, contactSkills);
		}else{
			System.out.println("Skill "+ skillEntity.getName()+" already exists for "+ contacts.get(contactId).getFullName());
		}
	}

	@Override
	public void deleteSkillForContact(Integer contactId, Skill skillName) {
		Skill skill = new Skill(skillName.getName());
		HashMap<String, Level> contactSkill = new HashMap<String,Level>();
		contactSkill = this.allSkillsContacts.get(contactId);
		
		if(contactSkill.containsKey(skillName)){
		try{
			this.allSkillsContacts.get(contactId).remove(skill.getName());
			System.out.println("Deleted skill "+ skillName+ " for "+ contacts.get(contactId).getFullName());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		}else{
			System.out.println("Skill does not exist for contact " + contacts.get(contactId).getFullName());
		}
	}
}
