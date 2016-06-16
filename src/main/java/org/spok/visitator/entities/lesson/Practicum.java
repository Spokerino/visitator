package org.spok.visitator.entities.lesson;

import java.util.List;

public class Practicum extends Lesson implements Markable{

	private List<Mark> marks;
	
	@Override
	public void setMarks(List<Mark> marks) {
		this.marks = marks;
		
	}

	@Override
	public List<Mark> getMarks() {
		return marks;
	}
	
	
}
