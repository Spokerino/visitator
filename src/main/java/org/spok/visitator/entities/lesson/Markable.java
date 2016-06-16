package org.spok.visitator.entities.lesson;

import java.util.List;

public interface Markable {
	
	void setMarks(List<Mark> marks);
	List<Mark> getMarks();
	
}
