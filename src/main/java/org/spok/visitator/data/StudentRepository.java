package org.spok.visitator.data;

import java.util.List;

import org.spok.visitator.entities.lesson.Mark;
import org.spok.visitator.entities.person.Student;

public interface StudentRepository {

	List<Student> findAllStudentsInCollege(Integer collegeId);
	List<Student> findAllStudentsInGroup(Long groupId);
	List<Student> findAllStudentsInFaculty(Long facultyId);
	List<Student> findAllStudentsInCollegeByName(Integer collegeId, String name);
	List<Student> findAllStudentsInFacultyByName(Long facultyId, String name);
	void addStudent(Student student);
	void updateStudent(Student student);
	void deleteStudent(Long studentId);
	Student findStudentById(Long studentId);
	List<Mark> findStudentMarks(Long studentId);
}
