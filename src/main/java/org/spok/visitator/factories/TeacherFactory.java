package org.spok.visitator.factories;

import org.spok.visitator.entities.enum_types.TeacherTypes;
import org.spok.visitator.entities.person.CollegeTeacher;
import org.spok.visitator.entities.person.Teacher;

public class TeacherFactory {
	
	public static Teacher createTeacher(TeacherTypes type) {
		if (type.equals(TeacherTypes.COLLEGE_TEACHER))
			return new CollegeTeacher();
		else
			return null;
	}
}
