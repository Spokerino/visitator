package org.spok.visitator.factories;

import org.spok.visitator.data.enum_types.StudentTypes;
import org.spok.visitator.person.CollegeStudent;
import org.spok.visitator.person.Student;

public class StudentFactory {

	public static Student createStudent(StudentTypes type) {
		if (type.equals(StudentTypes.COLLEGE_STUDENT))
			return new CollegeStudent();
		else
			return null;
	}
}
