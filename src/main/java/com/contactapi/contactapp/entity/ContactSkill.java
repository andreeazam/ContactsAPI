package com.contactapi.contactapp.entity;

public class ContactSkill {
	int contactId;
	Skill skill;
	Level level;
	
	public ContactSkill(){}
	
	public ContactSkill(int contactId, Skill skill, Level level){
		this.contactId = contactId;
		this.skill = skill;
		this.level = level;
		
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

}
