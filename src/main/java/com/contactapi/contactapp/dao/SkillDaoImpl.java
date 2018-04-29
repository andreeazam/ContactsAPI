package com.contactapi.contactapp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.contactapi.contactapp.entity.Contact;
import com.contactapi.contactapp.entity.ContactSkill;
import com.contactapi.contactapp.entity.Level;
import com.contactapi.contactapp.entity.Skill;
import com.google.common.base.Objects;

@Repository
@Qualifier("SkillDaoImpl")
public class SkillDaoImpl implements SkillDao{
	static HashMap<Integer, Contact> contacts = ContactDaoImpl.contacts;
	static HashMap<Integer, HashMap<String, Level>> allSkillsContacts;
	private static List<Skill> skillsList = new ArrayList<Skill>();
	private ContactSkill contactSkill;
	
	static{
		
		skillsList = new ArrayList<Skill>(){
			{
				add(new Skill("Ruby"));
				add(new Skill("XML"));
				add(new Skill("Java"));
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
				
				{
					HashMap<String,Level> contactSkills = new HashMap<String,Level>();
					contactSkills.put(skillsList.get(0).getName(), Level.BEGINNER);
					contactSkills.put(skillsList.get(2).getName(), Level.ADVANCED);
					put(contacts.get(3).getContactId(), contactSkills);
				}
			}
		};	
	}

	@Override
	public HashMap<String, Level> getSkillsByContactID(int contactId){
		if(contacts.containsKey(contactId)&&this.allSkillsContacts.containsKey(contactId)){
			return this.allSkillsContacts.get(contactId);
		}else{
			return null;
		}
	}

	@Override
	public HashMap<String, HashMap<String, Level>> getAllSkillsContacts() {
		Map<String, HashMap<String, Level>> result = this.allSkillsContacts							
				.entrySet()
				.stream()	
				//.filter(x->contacts.get(x)!=null)
				.collect(Collectors.toMap(x->contacts.get(x.getKey()).getFullName().toString(), x -> this.allSkillsContacts.get(x.getKey())));

		return (HashMap<String, HashMap<String,Level>>) result;
	}
	
	@Override
	public List<Skill> getAllSkills() {
		return this.skillsList;
	}

	@Override
	public void updateSkillForContact(ContactSkill contactSkill,int contactId) {
		
		HashMap<String,Level> contactSkills = allSkillsContacts.get(contactId);
		contactSkills.put(contactSkill.getSkill().getName(), contactSkill.getLevel());
		 
		if(this.allSkillsContacts.containsKey(contactId)){
			this.allSkillsContacts.put(contactId, contactSkills);
		}else{ 
			System.out.println("No such contact");
		}
	}

	@Override
	public void addSkillToContact(ContactSkill newSkill, Integer contactId) {
		HashMap<String,Level> contactSkills =  new HashMap<String,Level>();
		Skill skillEntity = new Skill(newSkill.getSkill().getName());
		Level level = newSkill.getLevel();
		
		if(ContactDaoImpl.contacts.containsKey(contactId)){
			if(getSkillsByContactID(contactId)!=null){
				 contactSkills = getSkillsByContactID(contactId);
			}else{
				 contactSkills = new HashMap<String,Level>();
			}
	
	
			// Adds new skill to skill list if it does not exist:
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
		}else{
			System.out.println("Contact does not exist");
		}
		
	}

	@Override
	public void deleteSkillForContact(Integer contactId, Skill skill) {
		Skill skillToDelete = new Skill(skill.getName());
		HashMap<String, Level> contactSkill = new HashMap<String,Level>();
		contactSkill = this.allSkillsContacts.get(contactId);
		
		if(contacts.containsKey(contactId)){
			if(contactSkill.get(skill.getName())!=null){
					this.allSkillsContacts.get(contactId).remove(skill.getName());
					System.out.println("Deleted skill "+ skill.getName()+ " for "+ contacts.get(contactId).getFullName());
					}else{
						System.out.println("Skill does not exist for contact " + contacts.get(contactId).getFullName());
					}
				}else{
				System.out.println("Contact does not exist!");
			 }
		}
	
	@Override
	public HashMap<String,Level> getContactsAndLevelsBySkill(String skillName){

		Map<String, Level> result = this.allSkillsContacts
										.entrySet()
										.stream()	
										.filter(x -> x.getValue().containsKey(skillName)&&(contacts.get(x.getKey())!=null))
										.collect(Collectors.toMap(x -> contacts.get(x.getKey()).getFullName().toString(), x -> x.getValue().get(skillName)));	
		System.out.println(result);
		HashMap<String,Level> result2 = (HashMap<String, Level>) result;
		return result2;
	}	          
}
