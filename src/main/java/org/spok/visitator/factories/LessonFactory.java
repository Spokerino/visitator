package org.spok.visitator.factories;

import org.spok.visitator.entities.lesson.Consultation;
import org.spok.visitator.entities.lesson.LabPracticum;
import org.spok.visitator.entities.lesson.Lecture;
import org.spok.visitator.entities.lesson.Lesson;
import org.spok.visitator.entities.lesson.Practicum;

public class LessonFactory {
	
	public static Lesson createLesson(int type) {

		if(type == 1)
			return new Lecture();
		else if(type == 4)
			return new Consultation();
		else if(type == 2)
			return new Practicum();
		else if(type == 3)
			return new LabPracticum();
		else
			return null;
	}
}
