package org.spok.visitator.factories;

import org.spok.visitator.data.enum_types.TeacherTypes;
import org.spok.visitator.person.CollegeTeacher;
import org.spok.visitator.person.Teacher;

public class TeacherFactory {
	
	public static Teacher createTeacher(TeacherTypes type) {
		if (type.equals(TeacherTypes.COLLEGE_TEACHER))
			return new CollegeTeacher();
		else
			return null;
	}
}
