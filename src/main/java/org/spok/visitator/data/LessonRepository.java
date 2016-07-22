package org.spok.visitator.data;

import java.util.List;

import org.spok.visitator.entities.enum_types.CollegeFacultyGroup;
import org.spok.visitator.entities.institution.EducationGroup;
import org.spok.visitator.entities.institution.EducationSpecialization;
import org.spok.visitator.entities.lesson.Lesson;
import org.spok.visitator.entities.lesson.Mark;
import org.spok.visitator.entities.person.Student;

public interface LessonRepository {

	Lesson findLessonById(Long lessonId);
	
	List<EducationGroup> showLessonGroupsById(Long lessonId);

	List<Mark> showLessonMarks(Long lessonId);

	List<Student> findStudentsOnLesson(Long lessonId);

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

}
