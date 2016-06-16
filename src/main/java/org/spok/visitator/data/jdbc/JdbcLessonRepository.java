package org.spok.visitator.data.jdbc;

import java.util.List;

import org.spok.visitator.data.LessonRepository;
import org.spok.visitator.entities.enum_types.CollegeFacultyGroup;
import org.spok.visitator.entities.enum_types.EducationGroupTypes;
import org.spok.visitator.entities.enum_types.EducationInstitutionTypes;
import org.spok.visitator.entities.enum_types.EducationSpecializationTypes;
import org.spok.visitator.entities.enum_types.StudentTypes;
import org.spok.visitator.entities.enum_types.TeacherTypes;
import org.spok.visitator.data.rowmappers.EducationGroupRowMapper;
import org.spok.visitator.data.rowmappers.LessonRowMapper;
import org.spok.visitator.data.rowmappers.MarkRowMapper;
import org.spok.visitator.data.rowmappers.StudentRowMapper;
import org.spok.visitator.data.rsextractors.EducationGroupsResultSetExtractor;
import org.spok.visitator.data.rsextractors.EducationSpecializationsResultSetExtractor;
import org.spok.visitator.data.rsextractors.LessonsResultSetExtractor;
import org.spok.visitator.entities.institution.EducationGroup;
import org.spok.visitator.entities.institution.EducationSpecialization;
import org.spok.visitator.entities.lesson.Lesson;
import org.spok.visitator.entities.lesson.Mark;
import org.spok.visitator.entities.person.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcLessonRepository implements LessonRepository{

	private JdbcOperations jdbc;

	@Autowired
	public JdbcLessonRepository(JdbcOperations jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public List<Lesson> findLessons(CollegeFacultyGroup type, Number id) {
		
		String institution = null;
		
		switch(type) {
		
			case COLLEGE:
				institution = "g.college_id";
				break;
				
			case FACULTY:
				institution = "g.faculty_id";
				break;
				
			case GROUP:
				institution = "g.groupId";
				break;
		}
		String sql = "select * from lsn l "
				+ "join teacher t on l.teacher_id = t.teacherId "
				+ "join subject s on l.subject_id = s.subjectId "
				+ "join lsn_grp l_g on l.lsnId = l_g.lesson_id "
				+ "join grp g on g.groupId = l_g.group_id "
				+ "join faculty f on g.faculty_id = f.facultyId "
				+ "join college c on f.college_id = c.collegeId "
				+ "join student st on st.group_id = g.groupId "
				+ "where " + institution + " = ? "
				+ "order by l.date";
				
		return jdbc.query(sql,
				new LessonsResultSetExtractor(StudentTypes.COLLEGE_STUDENT,
											  TeacherTypes.COLLEGE_TEACHER,
											  EducationInstitutionTypes.COLLEGE,
											  EducationSpecializationTypes.COLLEGE_FACULTY,
											  EducationGroupTypes.COLLEGE_GROUP),
											  id);
	}

	@Override
	public List<Lesson> findLessonsForDate(CollegeFacultyGroup type, Number id, String date) {
		
		String institution = null;
		
		switch(type) {
		
			case COLLEGE:
				institution = "g.college_id";
				break;
				
			case FACULTY:
				institution = "g.faculty_id";
				break;
				
			case GROUP:
				institution = "g.groupId";
				break;
		}
		
		String sql = "select * from lsn l "
				+ "join teacher t on l.teacher_id = t.teacherId "
				+ "join subject s on l.subject_id = s.subjectId "
				+ "join lsn_grp l_g on l.lsnId = l_g.lesson_id "
				+ "join grp g on g.groupId = l_g.group_id "
				+ "join faculty f on g.faculty_id = f.facultyId "
				+ "join college c on f.college_id = c.collegeId "
				+ "join student st on st.group_id = g.groupId "
				+ "where " + institution + " = ? and l.date = ? ";
				
		return jdbc.query(sql,
				new LessonsResultSetExtractor(StudentTypes.COLLEGE_STUDENT,
											  TeacherTypes.COLLEGE_TEACHER,
											  EducationInstitutionTypes.COLLEGE,
											  EducationSpecializationTypes.COLLEGE_FACULTY,
											  EducationGroupTypes.COLLEGE_GROUP),
											  id, date);
	}
	
	@Override
	public List<EducationSpecialization> showLessonsFaculties() {
		String sql = "select * from faculty f "
				+ "join teacher t on f.facultyId = t.faculty_id "
				+ "join faculty_group f_g on f.facultyId = f_g.faculty_id "
				+ "join grp g on f_g.group_id = g.groupId "
				+ "join student st on g.groupId = st.group_id "
				+ "join faculty_subject f_s on f.facultyId = f_s.faculty_id "
				+ "join subject s on f_s.subject_id = s.subjectId "
				+ "join lsn_grp l_g on g.groupId = l_g.group_id "
				+ "join lsn l on l.lsnId = l_g.lesson_id ";
		
		return jdbc.query(sql, new EducationSpecializationsResultSetExtractor(
									EducationInstitutionTypes.COLLEGE,
									EducationSpecializationTypes.COLLEGE_FACULTY,
									EducationGroupTypes.COLLEGE_GROUP,
									TeacherTypes.COLLEGE_TEACHER));
	}
	
	@Override
	public List<EducationGroup> showLessonsGroups() {
		String sql = "select * from grp g "
				+ "join faculty f on g.faculty_id = f.facultyId "
				+ "join college c on f.college_id = c.collegeId "
				+ "join student st on g.groupId = st.group_id "
				+ "join lsn_grp l_g on g.groupId = l_g.group_id "
				+ "join lsn l on l.lsnId = l_g.lesson_id ";
		
		return jdbc.query(sql, new EducationGroupsResultSetExtractor(
				EducationInstitutionTypes.COLLEGE,
				EducationSpecializationTypes.COLLEGE_FACULTY,
				EducationGroupTypes.COLLEGE_GROUP));
	}
	
	@Override
	public EducationGroup showLessonGroupById(Long lessonId) {
		String sql = "select * from grp g "
				+ "join faculty f on g.faculty_id = f.facultyId "
				+ "join college c on f.college_id = c.collegeId "
				+ "join student st on g.groupId = st.group_id "
				+ "join lsn_grp l_g on g.groupId = l_g.group_id "
				+ "join lsn l on l.lsnId = l_g.lesson_id "
				+ "where l.lsnId = ?";
		
		return jdbc.queryForObject(sql, new EducationGroupRowMapper(
				EducationInstitutionTypes.COLLEGE,
				EducationSpecializationTypes.COLLEGE_FACULTY,
				EducationGroupTypes.COLLEGE_GROUP),
						  lessonId);
	}
	
	@Override
	public List<EducationGroup> showLessonGroupsById(Long lessonId) {
		String sql = "select * from grp g "
				+ "join faculty f on g.faculty_id = f.facultyId "
				+ "join college c on f.college_id = c.collegeId "
				+ "join student st on g.groupId = st.group_id "
				+ "join lsn_grp l_g on g.groupId = l_g.group_id "
				+ "join lsn l on l.lsnId = l_g.lesson_id "
				+ "where l.lsnId = ?";
		
		return jdbc.query(sql, new EducationGroupsResultSetExtractor(
				EducationInstitutionTypes.COLLEGE,
				EducationSpecializationTypes.COLLEGE_FACULTY,
				EducationGroupTypes.COLLEGE_GROUP),
				lessonId);
	}
	
	@Override
	public List<Mark> showLessonMarks(Long lessonId) {
		String sql = "select * from mark m "
				+ " join student st on m.student_id = st.id "
				+ "join lsn l on m.lsn_id = l.lsnId "
				+ "join subject s on l.subject_id = s.subjectId "
				+ "join teacher t on l.teacher_id = t.teacherId "
				+ "join lsn_grp l_g on l.lsnId = l_g.lesson_id "
				+ "join grp g on l_g.group_id = g.groupId "
				+ "join faculty f on g.faculty_id = f.facultyId "
				+ "join college c on f.college_id = c.collegeId "
				+ "where l.lsnId = ?";
		
		return jdbc.query(sql, new MarkRowMapper(TeacherTypes.COLLEGE_TEACHER,
												 StudentTypes.COLLEGE_STUDENT,
												 EducationInstitutionTypes.COLLEGE,
												 EducationSpecializationTypes.COLLEGE_FACULTY),
												 lessonId);
	}
	
	@Override
	public void updateMark(Long lessonId, Long studentId, int mark) {
		String sql = "update mark set mark = ? "
				+ "where lsn_id = ? "
				+ "and student_id = ? ";
		
		jdbc.update(sql, mark, lessonId, studentId);
	}
	
	@Override
	public void deleteMark(Long lessonId, Long studentId) {
		String sql = "delete from mark "
				+ "where lsn_id = ? "
				+ "and student_id = ? ";
		
		jdbc.update(sql, lessonId, studentId);
	}
	
	@Override
	public void createMark(Long lessonId, Long studentId, int mark) {
		String sql = "insert into mark (lsn_id, student_id, mark) "
				+ "values (?, ?, ?) ";
				
		jdbc.update(sql, lessonId, studentId, mark);
	}
		
	@Override
	public Lesson findLessonById(Long lessonId) {
		String sql = "select * from lsn l "
				+ "join teacher t on l.teacher_id = t.teacherId "
				+ "join subject s on l.subject_id = s.subjectId "
				+ "join faculty f on t.teacherFaculty_id = f.facultyId "
				+ "join college c on f.college_id = c.collegeId "
				+ "where l.lsnId = ?";
		
		return jdbc.queryForObject(sql, new LessonRowMapper(TeacherTypes.COLLEGE_TEACHER,
															 EducationInstitutionTypes.COLLEGE,
															 EducationSpecializationTypes.COLLEGE_FACULTY), lessonId);
	}
	
	@Override
	public List<Student> findStudentsOnLessons() {
		String sql = "select * from student st "
				+ "join lsn_student l_st on st.id = l_st.student_id "
				+ "join grp g on st.group_id = g.groupId "
				+ "join faculty f on g.faculty_id = f.facultyId "
				+ "join college c on f.college_id = c.collegeId";
				
		return jdbc.query(sql, new StudentRowMapper(StudentTypes.COLLEGE_STUDENT));
	}
	
	@Override
	public List<Student> findStudentsOnLesson(Long lessonId) {
		String sql = "select * from student st "
				+ "join lsn_student l_st on st.id = l_st.student_id "
				+ "join grp g on st.group_id = g.groupId "
				+ "join faculty f on g.faculty_id = f.facultyId "
				+ "join college c on f.college_id = c.collegeId "
				//+ "join lsn l on l_st.lesson_id = l.lsnId "
				+ "where l_st.lesson_id = ?";
		
		return jdbc.query(sql, new StudentRowMapper(StudentTypes.COLLEGE_STUDENT), lessonId);
	}
	
//	@Override
//	public void addStudentToAbsentList(Long lessonId, Long studentId) {
//		String sql = "insert into lsn_student (LESSON_ID, STUDENT_ID)"
//				+ "values (?, ?)";
//				
//		jdbc.update(sql, lessonId, studentId);
//		
//	}
//	
//	@Override
//	public void removeStudentFromAbsentList(Long lessonId, Long studentId) {
//		String sql = "delete from lsn_student "
//				+ " where lesson_id = ? "
//				+ "and student_id = ?";
//		
//		jdbc.update(sql, lessonId, studentId);
//		
//	}
	
	@Override
	public void addStudentToLesson(Long lessonId, Long studentId) {
		String sql = "insert into lsn_student (LESSON_ID, STUDENT_ID)"
				+ "values (?, ?)";
				
		jdbc.update(sql, lessonId, studentId);
		
	}
	
	@Override
	public void removeStudentFromLesson(Long lessonId, Long studentId) {
		String sql = "delete from lsn_student "
				+ " where lesson_id = ? "
				+ "and student_id = ?";
		
		jdbc.update(sql, lessonId, studentId);
		
	}
	
//	@Override
//	public long saveLesson(Lesson lesson, int type, long[] groupId) {
//		
//	    jdbc.update(
//	        "insert into lsn (date, start, end, teacher_id, subject_id, lsnType)" +
//	        " values (?, ?, ?, ?, ?, ?)",
//	        lesson.getDate(),
//	        lesson.getStart(),
//	        lesson.getEnd(),
//	        lesson.getTeacher().getId(),
//	        lesson.getSubject().getId(),
//	        type);
//	    
//	    Lesson lastLesson = getLastLesson();
//
//	    for(long g : groupId) {
//	        jdbc.update(
//	    		"insert into lsn_grp (lesson_id, group_id) values (?, ?)",
//	    		 lastLesson.getId(), g);
//	    }
//	    
//		return lastLesson.getId();
//	  }
	
	@Override
	public void saveToLesson(Lesson lesson, int type) {
		String sql = "insert into lsn (date, start, end, teacher_id, subject_id, lsnType)" +
		        " values (?, ?, ?, ?, ?, ?)";
		
		jdbc.update(sql,
				lesson.getDate(),
		        lesson.getStart(),
		        lesson.getEnd(),
		        lesson.getTeacher().getId(),
		        lesson.getSubject().getId(),
		        type);

	}
	
	@Override
	public void deleteLesson(Long lessonId) {
		String sql = "delete from lsn "
				+ "where lsnId = ?";
		
		jdbc.update(sql, lessonId);
	}
	
	
//	public void deleteFromLsnGroup(Long lessonId) {
//		String sql = "delete * from lsn_grp l_g "
//				+ "where l_g.lsnId = ?";
//		
//		jdbc.update(sql, lessonId);
//	}
	
	
	@Override
	public void saveToLessonGroup(Long lessonId, Long groupId) {
		String sql = "insert into lsn_grp (lesson_id, group_id) values (?, ?)";
		
		jdbc.update(sql, lessonId, groupId);
	}
	
	@Override
	public Lesson getLastLesson() {
		String sql = "select * from lsn l "
	    		+ "join teacher t on l.teacher_id = t.teacherId "
	    		+ "join faculty f on t.teacherFaculty_id = f.facultyId "
	    		+ "join college c on f.college_id = c.collegeId "
				+ "join subject s on l.subject_id = s.subjectId "
	    		+ "ORDER BY l.lsnId DESC "
	    		+ "LIMIT 1";
		
		return jdbc.queryForObject(sql, new LessonRowMapper(TeacherTypes.COLLEGE_TEACHER,
															 EducationInstitutionTypes.COLLEGE,
															 EducationSpecializationTypes.COLLEGE_FACULTY));
	}
	
	@Override
	public List<Lesson> findStudentLessons(Long studentId) {
		String sql = "select * from lsn l "
				+ "join subject s on l.subject_id = s.subjectId "
				+ "join teacher t on l.teacher_id = t.teacherId "
				+ "join lsn_grp l_g on l.lsnId = l_g.lesson_id "
				+ "join grp g on g.groupId = l_g.group_id "
				+ "join faculty f on t.teacherFaculty_id = f.facultyId "
	    		+ "join college c on f.college_id = c.collegeId "
				+ "join lsn_student l_st on l.lsnId = l_st.lesson_id "
				+ "join student st on st.group_id = g.groupId "
				+ "where l_st.student_id = ?";
		
		return jdbc.query(sql, new LessonsResultSetExtractor(StudentTypes.COLLEGE_STUDENT,
															 TeacherTypes.COLLEGE_TEACHER,
															 EducationInstitutionTypes.COLLEGE,
															 EducationSpecializationTypes.COLLEGE_FACULTY,
															 EducationGroupTypes.COLLEGE_GROUP), studentId);
	}

}
