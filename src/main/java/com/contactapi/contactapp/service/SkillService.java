package com.contactapi.contactapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.contactapi.contactapp.dao.SkillDao;
import com.contactapi.contactapp.entity.ContactSkill;
import com.contactapi.contactapp.entity.Level;
import com.contactapi.contactapp.entity.Skill;

@Service
@Qualifier("SkillDaoImpl")
public class SkillService {

	@Autowired	
	private SkillDao skillDao;
	
	public HashMap<String, Level> getSkillsByContactID(int contactID){
		return this.skillDao.getSkillsByContactID(contactID);
	}
	
	public HashMap<String, HashMap<String, Level>> getAllSkillsContacts(){
		return this.skillDao.getAllSkillsContacts();
	}
	
	public void updateSkillForContact(ContactSkill contactSkill, int contactId){
		this.skillDao.updateSkillForContact(contactSkill, contactId);
	}

	public void addSkillToContact(ContactSkill contactSkill, Integer contactId) {
		this.skillDao.addSkillToContact(contactSkill, contactId);
		
	}

	public void deleteSkillForContact(int contactId, Skill skillName) {
		this.skillDao.deleteSkillForContact(contactId, skillName);
		
	}
	
	public List<Skill> getAllSkills(){
		return this.skillDao.getAllSkills();
	}
	
	public HashMap<String,Level> getContactsAndLevelsBySkill(String skill){
		return this.skillDao.getContactsAndLevelsBySkill(skill);
	}
	
}
