package org.spok.visitator.institution;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.spok.visitator.educ_person.Student;
import org.spok.visitator.lesson.Lesson;

public abstract class EducationGroup {

	private Long id;
	@Size(min=2, max=15, message="Length of group name must be between {min} and {max} characters")
	private String name;
	@NotNull
	@Min(1)
	@Max(5)
	private int course;
	private List<Student> students;
	private EducationSpecialization specialization;
//	private int yearsToFinish;
//	private int[] courses;

	private List<Lesson> lessons;
	
	public EducationGroup() {}
	
	public EducationGroup(Long id, String name, int course, List<Student> students) {
		this.id = id;
		this.name = name;
		this.course = course;
		this.students = students;
	}
	
	public EducationGroup(String name, int course, List<Student> students) {
		this.id = null;
		this.name = name;
		this.course = course;
		this.students = students;
	}
	
	public String getNameAndCourse() {
		return this.name + this.course;
	}

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

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public EducationSpecialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(EducationSpecialization specialization) {
		this.specialization = specialization;
	}

//	public int getYearsToFinish() {
//		return yearsToFinish;
//	}
//
//	public void setYearsToFinish(int yearsToFinish) {
//		this.yearsToFinish = yearsToFinish;
//	}
//
//	public int[] getCourses() {
//		return courses;
//	}
//
//	public void setCourses(int yearsToFinish) {
//		this.courses = new int[yearsToFinish];
//		for(int i = 0; i < courses.length; i++) {
//			courses[i] = i + 1;
//		}
//	}
	
	
}
