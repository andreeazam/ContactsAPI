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
	private static HashMap<Integer, HashMap<String, Level>> skills;
	private static List<Skill> entitySkills = new ArrayList<Skill>();
	private static HashMap<Integer, List<ContactSkill>> contactSkills ;
	private ContactSkill contactSkill;
	
	
	static{
		
		entitySkills = new ArrayList<Skill>(){
			{
				add(new Skill("Ruby"));
				add(new Skill("XML"));
				add(new Skill("HTTP"));
			}
		};
		
		skills = new HashMap<Integer, HashMap<String, Level>>(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{   
				{
					HashMap<String,Level> skillHash = new HashMap<String,Level>();
					skillHash.put(entitySkills.get(0).getName(), Level.ADVANCED);
					skillHash.put(entitySkills.get(1).getName(), Level.INTERMEDIATE);
					put(contacts.get(1).getContactId(), skillHash);
				}
				
				{
					HashMap<String,Level> skillHash = new HashMap<String,Level>();
					skillHash.put(entitySkills.get(2).getName(), Level.ADVANCED);
					skillHash.put(entitySkills.get(1).getName(), Level.BEGINNER);
					put(contacts.get(2).getContactId(), skillHash);
				}
			}
		};	
		
		contactSkills = new HashMap<Integer, List<ContactSkill>>(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{	List<ContactSkill> cskills = new ArrayList<ContactSkill>();
				cskills.add(new ContactSkill(entitySkills.get(0),Level.BEGINNER));
				cskills.add(new ContactSkill(entitySkills.get(1),Level.INTERMEDIATE));
				cskills.add(new ContactSkill(entitySkills.get(2),Level.BEGINNER));
				put(contacts.get(1).getContactId(), cskills);
			
				cskills = new ArrayList<ContactSkill>();
				cskills.add(new ContactSkill(entitySkills.get(0),Level.ADVANCED));
				cskills.add(new ContactSkill(entitySkills.get(1),Level.ADVANCED));
				cskills.add(new ContactSkill(entitySkills.get(2),Level.ADVANCED));
				put(contacts.get(2).getContactId(), cskills);
			
			}
		};
	}

	@Override
	public HashMap<String, Level> getSkillsByContactID(int contactID){
		return this.skills.get(contactID);
	}

	@Override
	public HashMap<Integer, HashMap<String, Level>> getSkills() {
		return this.skills;
	}
	
	@Override
	public List<Skill> getAllEntitySkills() {
		return this.entitySkills;
	}

	@Override
	public void updateSkillForContact(ContactSkill contactSkill,int contactId) {
		
		HashMap<String,Level> skillHash = skills.get(contactId);
		skillHash.put(contactSkill.getSkill().getName(), contactSkill.getLevel());
		 
		if(skills.containsKey(contactId)){
			this.skills.put(contactId, skillHash);
		}else{ 
			System.out.println("No such contact");
		}
	}

	@Override
	public void addSkillToContact(ContactSkill skill, Integer contactId) {
		

		HashMap<String,Level> contactSkills = getSkillsByContactID(contactId);
		Skill skillEntity = new Skill(skill.getSkill().getName());
		Level level = skill.getLevel();
		Contact c = new Contact(contactId);

		// Adds new skill to entity skill list if it does not exist:
		if(!this.entitySkills.contains(skillEntity)){
			this.entitySkills.add(skillEntity);
		}
		//Adds new skill to contacts skills
		if(!contactSkills.containsKey(skillEntity.getName())){
			contactSkills.put(skillEntity.getName(), level);
			this.skills.put(contactId, contactSkills);
		}else{
			System.out.println("Skill "+ skillEntity.getName()+" already exists for "+ contacts.get(contactId).getFullName());
		}
	}

	@Override
	public void deleteSkillForContact(Integer contactId, Skill skillName) {
		Skill skill = new Skill(skillName.getName());///skillName.substring(14, skillName.length()-2);
		try{
		this.skills.get(contactId).remove(skill.getName());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
