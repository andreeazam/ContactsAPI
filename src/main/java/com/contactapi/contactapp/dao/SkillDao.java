package com.contactapi.contactapp.dao;

import java.util.HashMap;
import java.util.List;

import com.contactapi.contactapp.entity.ContactSkill;
import com.contactapi.contactapp.entity.Level;
import com.contactapi.contactapp.entity.Skill;


public interface SkillDao {
	
	HashMap<Integer, HashMap<String, Level>> getAllSkillsContactIDs();

	void updateSkillForContact(ContactSkill contactSkill, int contactId);

	HashMap<String, Level> getSkillsByContactID(int contactID);

	void addSkillToContact(ContactSkill contactSkill, Integer contactId);
	
	void deleteSkillForContact(Integer contactId, Skill skillName);

	List<Skill> getAllSkills();
}
