package org.spok.visitator.entities.person;

import org.spok.visitator.entities.institution.EducationGroup;

public abstract class Student extends Person{
	
	private EducationGroup group;

	public EducationGroup getGroup() {
		return group;
	}

	public void setGroup(EducationGroup group) {
		this.group = group;
	}
}
