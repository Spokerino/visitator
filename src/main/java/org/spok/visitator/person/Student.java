package org.spok.visitator.person;

import org.spok.visitator.institution.EducationGroup;

public abstract class Student extends Person{
	
	private EducationGroup group;
	
	public Student() {}
	
	
	public EducationGroup getGroup() {
		return group;
	}

	public void setGroup(EducationGroup group) {
		this.group = group;
	}
}
