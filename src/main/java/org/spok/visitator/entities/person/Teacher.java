package org.spok.visitator.entities.person;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.spok.visitator.entities.lesson.Subject;

public abstract class Teacher extends Person {

	@NotNull
	private List<Subject> subjects = new ArrayList<Subject>();
	
	@Email
	private String email; 

	public void addSubject(Subject subject) {
		subjects.add(subject);
	}
	
	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
