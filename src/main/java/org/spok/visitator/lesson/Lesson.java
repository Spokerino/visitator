package org.spok.visitator.lesson;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.spok.visitator.institution.EducationGroup;
import org.spok.visitator.person.Student;
import org.spok.visitator.person.Teacher;

public abstract class Lesson implements Comparable<Lesson>{

	private static long lessonDuration = 100;
	private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	
	private Long id;
	
	private Date date;
	private Date start;
	private Date end;
	private Subject subject;
	private Teacher teacher;
	private List<EducationGroup> groups;
	private List<Student> students;
	
	public Lesson(){}
	
	public String getType(){
		return this.getClass().getSimpleName();
	}
	
	public Date getStart(){
		return this.start;
	}
		
	public void setStart(Date lessonStart) {
		this.start = lessonStart;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date lessonEnd) {
		this.end = lessonEnd;
	}

	
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static long getLessonDuration() {
		return lessonDuration;
	}

	public static void setLessonDuration(long lessonDuration) {
		Lesson.lessonDuration = lessonDuration;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public boolean equals(Object that) {
		return that instanceof Lesson && id.equals(((Lesson) that).getId());
	}
  
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	@Override
	public int compareTo(Lesson l) {
		return date.compareTo(l.getDate()) + start.compareTo(l.getStart());
	}

	public String getStartFormat() {
	return timeFormat.format(this.start);
}

	public String getEndFormat() {
	return timeFormat.format(this.end);
}

	public List<EducationGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<EducationGroup> groups) {
		this.groups = groups;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public void add(EducationGroup group) {
		groups.add(group);
	}
}
