package org.spok.visitator.data;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.spok.visitator.entities.lesson.Subject;

public interface SubjectRepository {

	Set<Subject> getCollegeSubjects(Integer collegeId);

	List<Subject> getFacultySubjects(Long facultyId);

	Subject getSubjectForId(Integer id);

	Subject getSubjectForName(String name);

	void createSubject(String name);

	Set<Subject> getAllSubjects();

	int getAutoIncrement();

	void updateSubject(Integer id, String name);

	void deleteSubject(Integer subjectId);

	void addSubjectToFaculty(Long facultyId, Integer subjectId);

	void removeSubjectFromFaculty(Long facultyId, Integer subjectId);

	List<Subject> getTeacherSubjects(Long teacherId);

	void addSubjectToTeacher(Long teacherId, Integer subjectId);

	void deleteSubjectFromTeacher(Long teacherId, Integer subjectId);

	Map<Integer, String> getsubjectsMap(Integer collegeId);
}
