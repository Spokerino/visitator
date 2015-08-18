package org.spok.visitator.data.jdbc;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.spok.visitator.data.SubjectRepository;
import org.spok.visitator.data.rowmappers.SubjectRowMapper;
import org.spok.visitator.education.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcSubjectRepository implements SubjectRepository {

	private JdbcOperations jdbc;
	
	@Autowired
	public JdbcSubjectRepository(JdbcOperations jdbc){
		this.jdbc = jdbc;
	}
	
	@Override
	public int getAutoIncrement() {
		String sql = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES "
				+ "WHERE TABLE_SCHEMA = 'visitator' "
				+ "AND TABLE_NAME = 'subject'";
		
		return jdbc.queryForObject(sql, new Object[]{}, Integer.class);
	}
	
	@Override
	public void createSubject(String name){
		String sql = "insert into subject (subjectName) values (?)";
		jdbc.update(sql, name);
	}
	
	@Override
	public void updateSubject(Integer id, String name){
		String sql = "update subject "
				+ "set subjectName = ? "
				+ "where subjectId = ?";
		
		jdbc.update(sql, name, id);
	}
	
	@Override
	public void deleteSubject(Integer subjectId) {
		String sql = "delete from subject "
				+ "where subjectId = ?";
		
		jdbc.update(sql, subjectId);
	}
	
	@Override
	public void addSubjectToFaculty(Long facultyId, Integer subjectId){
		String sql = "insert into faculty_subject values (?, ?)";
		jdbc.update(sql, facultyId, subjectId);
	}
	
	@Override
	public void removeSubjectFromFaculty(Long facultyId, Integer subjectId){
		String sql = "delete from faculty_subject "
				+ "where faculty_id = ? "
				+ "and subject_id = ?";
		jdbc.update(sql, facultyId, subjectId);
	}
	
	@Override
	public Set<Subject> getAllSubjects(){
		String sql = "select * from subject";
		
		Set<Subject> subjects = new TreeSet<>(jdbc.query(sql, new SubjectRowMapper()));
		return subjects;
	}
	
//	@Override
//	public Set<Subject> getAllSubjectsNotInFacultyYet(){
//		String sql = "select s.subjectId from subject s "
//				+ "left join faculty_subject f_s "
//				+ "on s.subjectId = f_s.subject_id "
//				+ "where f_s.subject_id is null";
//		
//		Set<Subject> subjects = new TreeSet<>(jdbc.query(sql, new SubjectRowMapper()));
//		return subjects;
//	}
	
	@Override
	public Set<Subject> getCollegeSubjects(Integer collegeId){
		String sql = "select * from subject s "
				+ "join faculty_subject f_s on s.subjectId = f_s.subject_id "
				+ "join faculty f on f_s.faculty_id = f.facultyId "
				+ "where f.college_id = ?";
		
		Set<Subject> subjects = new TreeSet<>(jdbc.query(sql, new SubjectRowMapper(), collegeId));
		return subjects;
	}
	
	@Override
	public List<Subject> getFacultySubjects(Long facultyId){
		String sql = "select * from subject s "
				+ "join faculty_subject f_s on s.subjectId = f_s.subject_id "
				+ "where f_s.faculty_id = ?";
		
		return jdbc.query(sql, new SubjectRowMapper(), facultyId);
	}

	@Override
	public Subject getSubjectForId(Integer subjectId){
		String sql = "SELECT * FROM SUBJECT WHERE subjectID = ?";
		return jdbc.queryForObject(sql, new Object[]{subjectId}, new SubjectRowMapper());
	}
	
	@Override
	public Subject getSubjectForName(String subjectName){
		String sql = "SELECT * FROM SUBJECT WHERE subjectNAME = ?";
		return jdbc.queryForObject(sql, new Object[]{subjectName}, new SubjectRowMapper());
	}
	
	@Override
	public List<Subject> getTeacherSubjects(Long teacherId){
		String sql = "SELECT * FROM SUBJECT S "
				+ "join teacher_subject t_s on s.subjectId = t_s.subject_id "
				+ "WHERE t_s.teacher_id = ?";
		return jdbc.query(sql, new SubjectRowMapper(), teacherId);
	}
	
	@Override
	public void addSubjectToTeacher(Long teacherId, Integer subjectId) {
		String sql = "insert into teacher_subject "
				+ "values (?, ?)";
		
		jdbc.update(sql, teacherId,	subjectId);
	}
	
	@Override
	public void deleteSubjectFromTeacher(Long teacherId, Integer subjectId) {
		String sql = "delete from teacher_subject "
				+ "where teacher_id = ? "
				+ "and subject_id = ?";
		
		jdbc.update(sql, teacherId,	subjectId);
	}
	
}
