package org.spok.visitator.data;

import java.util.List;

import org.spok.visitator.data.enum_types.CollegeFacultyGroup;
import org.spok.visitator.educ_person.Student;
import org.spok.visitator.institution.EducationGroup;
import org.spok.visitator.institution.EducationSpecialization;
import org.spok.visitator.lesson.Lesson;
import org.spok.visitator.lesson.Mark;

public interface LessonRepository {

	List<EducationGroup> showLessonsGroups();

	Lesson findLessonById(Long lessonId);
	
	EducationGroup showLessonGroupById(Long lessonId);

	List<EducationGroup> showLessonGroupsById(Long lessonId);

	List<EducationSpecialization> showLessonsFaculties();

	List<Mark> showLessonMarks(Long lessonId);

	List<Student> findStudentsOnLesson(Long lessonId);

	List<Student> findStudentsOnLessons();

	List<Lesson> findLessonsForDate(CollegeFacultyGroup type, Number id,
			String date);

	List<Lesson> findLessons(CollegeFacultyGroup type, Number id);

	void updateMark(Long lessonId, Long studentId, int mark);

	void deleteMark(Long lessonId, Long studentId);

	void createMark(Long lessonId, Long studentId, int mark);

	Lesson getLastLesson();

	void saveToLesson(Lesson lesson, int type);

	void saveToLessonGroup(Long lessonId, Long groupId);

	void deleteLesson(Long lessonId);

	void addStudentToLesson(Long lessonId, Long studentId);

	void removeStudentFromLesson(Long lessonId, Long studentId);

	List<Lesson> findStudentLessons(Long studentId);

	List<Lesson> findMissedLessonsByStudent(Long studentId);

}
