package com.contactapi.contactapp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.contactapi.contactapp.entity.Contact;
import com.contactapi.contactapp.entity.Level;
import com.contactapi.contactapp.entity.Skill;

@Repository
@Qualifier("SkillDaoImpl")
public class SkillDaoImpl implements SkillDao{
	static HashMap<Integer, Contact> contacts = ContactDaoImpl.contacts;
	private static HashMap<Integer, HashMap<String, Level>> skills;
	private static int loginId;
	//private static HashSet <Skill> entitySkills;
	private static List<Skill> entitySkills = new ArrayList<Skill>();
	private static Skill skill;
	
	static{
		entitySkills = new ArrayList<Skill>(){
			{
				add(new Skill("Ruby"));
				add(new Skill("XML"));
				add(new Skill("HTTP"));
			}
		};
		
		skills = new HashMap<Integer, HashMap<String, Level>> (){
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
	public void updateSkill(HashMap<Integer,HashMap<String, Level>> skill,int contactId) {
		
		HashMap<String,Level> skillHash = new HashMap<String,Level>();
		skillHash = skill.get(contactId);

		if(contacts.containsKey(contactId)){
			this.skills.put(contactId, skillHash);
		}else{
			System.out.println("No such contact");
		}
	}

	@Override
	public void addSkillToContact(HashMap<String, Level> skillHash, Integer contactId) {
		

		HashMap<String,Level> contactSkills = getSkillsByContactID(contactId);
		Skill skillEntity = new Skill(skillHash.keySet().toString().substring(1, skillHash.keySet().toString().length()-1));
		Level level = skillHash.get(skillEntity.getName());
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
	public void deleteSkillForContact(Integer contactId, String skillName) {
		String skill = skillName.substring(14, skillName.length()-2);
		try{
		this.skills.get(contactId).remove(skill);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
