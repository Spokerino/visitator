package org.spok.visitator.lesson;

import org.spok.visitator.person.Student;

public class Mark {

	private Markable lesson;
	private Student student;
	private int mark;
	
	
	public Markable getLesson() {
		return lesson;
	}
	
	public void setLesson(Markable lesson) {
		this.lesson = lesson;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}

	public int getMark() {
		return mark;
	}
	
	public void setMark(int mark) {
		this.mark = mark;
	}
	
	
	
	
}
