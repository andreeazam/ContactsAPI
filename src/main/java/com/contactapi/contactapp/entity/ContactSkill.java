package com.contactapi.contactapp.entity;

public class ContactSkill {
	Skill skill;
	Level level;
	
	public ContactSkill(){}
	
	public ContactSkill(Skill skill, Level level){

		this.skill = skill;
		this.level = level;	
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
