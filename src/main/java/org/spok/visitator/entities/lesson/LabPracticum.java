package org.spok.visitator.entities.lesson;

import java.util.List;

public class LabPracticum extends Lesson implements Markable{

	private List<Mark> marks;
	
	public LabPracticum(){}

	@Override
	public void setMarks(List<Mark> marks) {
		this.marks = marks;
		
	}

	@Override
	public List<Mark> getMarks() {
		return marks;
	}
}
