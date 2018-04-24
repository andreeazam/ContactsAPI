package com.contactapi.contactapp.controller;


import java.util.HashMap;
import java.util.List;

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
	public HashMap<Integer, HashMap<String, Level>> getSkills(){	
		return skillService.getSkills();
	}  
	
	@RequestMapping(value = "/{contactId}",  method = RequestMethod.GET)
	public HashMap<String, Level> getSkillsByContactID(@PathVariable("contactId") int contactId){	
		return skillService.getSkillsByContactID(contactId);
	}
	
	@PutMapping(value = "/{contactId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateSkillForContact(@RequestBody ContactSkill skill, @PathVariable("contactId") Integer contactId){
		skillService.updateSkillForContact(skill, contactId);
	}
	
	@RequestMapping(value = "/{contactId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addSkillToContact(@RequestBody ContactSkill contactSkill, @PathVariable("contactId") Integer contactId){
		skillService.addSkillToContact(contactSkill, contactId);
	}
	
	@RequestMapping(value = "/{contactid}", method = RequestMethod.DELETE)
	public void deleteSkillForContact(@PathVariable("contactid") Integer contactid, @RequestBody Skill skillName){
		 this.skillService.deleteSkillForContact(contactid, skillName);
	} 
	
	@RequestMapping(value = "/entities", method = RequestMethod.GET)
	public List<Skill> getAllEntitySkills(){
		return this.skillService.getAllEntitySkills();
	}
}
