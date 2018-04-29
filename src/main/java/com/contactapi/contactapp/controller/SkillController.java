package com.contactapi.contactapp.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.contactapi.contactapp.entity.ContactSkill;
import com.contactapi.contactapp.entity.Level;
import com.contactapi.contactapp.entity.Skill;
import com.contactapi.contactapp.service.SkillService;

@RestController
@RequestMapping("/skills")
public class SkillController {
	
	@Autowired
	private SkillService skillService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Map<String, HashMap<String, Level>> getAllSkillsContacts(){	
		return skillService.getAllSkillsContacts();
	}  
	
	@RequestMapping(value = "/contactSkills/{contactId}",  method = RequestMethod.GET)
	public HashMap<String, Level> getSkillsByContactID(@PathVariable("contactId") Integer contactId){	
		return skillService.getSkillsByContactID(contactId);
	}
	
	@PutMapping(value = "/updateSkill/{contactId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateSkillForContact(@RequestBody ContactSkill skill, @PathVariable("contactId") Integer contactId){
		skillService.updateSkillForContact(skill, contactId);
	}
	
	@RequestMapping(value = "/addSkill/{contactId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addSkillToContact(@RequestBody ContactSkill contactSkill, @PathVariable("contactId") Integer contactId){
		skillService.addSkillToContact(contactSkill, contactId);
	}
	
	@RequestMapping(value = "/deleteskill/{contactid}", method = RequestMethod.DELETE)
	public void deleteSkillForContact(@PathVariable("contactid") Integer contactid, @RequestBody Skill skill){
		 this.skillService.deleteSkillForContact(contactid, skill);
	} 
	
	@RequestMapping(value = "/entities", method = RequestMethod.GET)
	public List<Skill> getAllSkills(){
		return this.skillService.getAllSkills();
	}
	
	@RequestMapping(value = "/{skillName}", method = RequestMethod.GET)
	public HashMap<String,Level> getContactsAndLevelsBySkill(@PathVariable("skillName") String skillName){
		return this.skillService.getContactsAndLevelsBySkill(skillName);
	}
}
