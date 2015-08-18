package org.spok.visitator.institution;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import org.spok.visitator.lesson.Subject;
import org.spok.visitator.person.Teacher;


public abstract class EducationSpecialization {

	private Long id;
	@Size(min=2, max=25, message="Length of faculty name must be between {min} and {max} characters")
	private String name;
	private List<Subject> subjects = new ArrayList<Subject>();
	private List<Teacher> teachers = new ArrayList<Teacher>();
	private EducationInstitution institution;
	private List<EducationGroup> groups = new ArrayList<EducationGroup>();
	
	public EducationSpecialization() {}
	
//	public EducationSpecialization(Long id, String name, List<Teacher> teachers
//			, List<EducationGroup> groups) {
//		this.id = id;
//		this.name = name;
//		this.subjects = subjects;
//		this.teachers = teachers;
//		this.groups = groups;
//	}
//	
//	public EducationSpecialization(String name, List<Teacher> teachers
//			, List<EducationGroup> groups) {
//		this.id = null;
//		this.name = name;
//		this.subjects = subjects;
//		this.teachers = teachers;
//		this.groups = groups;
//	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public List<EducationGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<EducationGroup> groups) {
		this.groups = groups;
	}

//	public void addSubject(Subject subject) {
//		subjects.add(subject);
//	}

	public void addGroup(EducationGroup group) {
		groups.add(group);
	}

	public void addTeacher(Teacher teacher) {
		teachers.add(teacher);
		
	}

	public EducationInstitution getInstitution() {
		return institution;
	}

	public void setInstitution(EducationInstitution institution) {
		this.institution = institution;
	}
	
}
