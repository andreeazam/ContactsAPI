package com.contactapi.contactapp.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.contactapi.contactapp.dao.SkillDao;
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
	
	public HashMap<Integer, HashMap<String, Level>> getSkills(){
		return this.skillDao.getSkills();
	}
	
	public void updateSkill(HashMap<Integer, HashMap<String, Level>> skills, int contactId){
		this.skillDao.updateSkill(skills, contactId);
	}

	public void addSkillToContact(HashMap<String, Level> skillHash, Integer contactId) {
		this.skillDao.addSkillToContact(skillHash, contactId);
		
	}

	public void deleteSkillForContact(int contactId, String skillName) {
		this.skillDao.deleteSkillForContact(contactId, skillName);
		
	}
	
	public List<Skill> getAllEntitySkills(){
		return this.skillDao.getAllEntitySkills();
	}
	
}
