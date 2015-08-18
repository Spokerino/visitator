package org.spok.visitator.data;

import java.util.List;

import org.spok.visitator.educ_person.CollegeTeacher;
import org.spok.visitator.educ_person.Teacher;

public interface TeacherRepository {
	
	List<Teacher> getCollegeTeachers(Integer collegeId);
	List<Teacher> getFacultyTeachers(Long facultyId);
	Teacher getTeacherForId(Long teacherId);
	void addTeacherFromFaculty(Teacher teacher, Long facultyId);
	Teacher getLastTeacherId();
	void deleteTeacher(Long teacherId);
	void updateTeacher(CollegeTeacher teacher, Long newFacultyId);
}
