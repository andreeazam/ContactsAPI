package com.contactapi.contactapp.dao;

import java.util.HashMap;
import java.util.List;

import com.contactapi.contactapp.entity.Level;
import com.contactapi.contactapp.entity.Skill;


public interface SkillDao {
	
	HashMap<Integer, HashMap<String, Level>> getSkills();

	void updateSkill(HashMap<Integer, HashMap<String, Level>> skills, int contactId);

	HashMap<String, Level> getSkillsByContactID(int contactID);

	void addSkillToContact(HashMap<String, Level> skillHash, Integer contactId);
	
	void deleteSkillForContact(Integer contactId, String skillName);

	List<Skill> getAllEntitySkills();
}
