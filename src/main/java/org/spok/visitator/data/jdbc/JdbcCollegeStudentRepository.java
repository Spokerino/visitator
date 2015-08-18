package org.spok.visitator.data.jdbc;

import java.util.List;

import org.spok.visitator.data.StudentRepository;
import org.spok.visitator.data.enum_types.EducationInstitutionTypes;
import org.spok.visitator.data.enum_types.EducationSpecializationTypes;
import org.spok.visitator.data.enum_types.StudentTypes;
import org.spok.visitator.data.enum_types.TeacherTypes;
import org.spok.visitator.data.rowmappers.MarkRowMapper;
import org.spok.visitator.data.rowmappers.StudentRowMapper;
import org.spok.visitator.educ_person.Student;
import org.spok.visitator.lesson.Mark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcCollegeStudentRepository implements StudentRepository {

	private JdbcOperations jdbc;
	
	@Autowired	
	public JdbcCollegeStudentRepository(JdbcOperations jdbc) {
		this.jdbc = jdbc;
	}

	
	@Override
	public void addStudent(Student student) {
		String sql = "insert into student (firstName, lastName, birthday, gender,  group_id) "
				+ "values (?, ?, ?, ?, ?)";
		
		jdbc.update(sql,
					student.getFirstName(),
					student.getLastName(),
					student.getBirthday(),
					student.getGender(),
					student.getGroup().getId());
	}
	
	@Override
	public void updateStudent(Student student) {
		String sql = "update student "
				+ "set firstName = ?,"
				+ "lastName = ?,"
				+ "gender = ?,"
				+ "birthday = ?,"
				+ "group_id = ? "
				+ "where id = ?";
		
		jdbc.update(sql,
					student.getFirstName(),
					student.getLastName(),
					student.getGender(),
					student.getBirthday(),
					student.getGroup().getId(),
					student.getId());
	}
	
	@Override
	public void deleteStudent(Long studentId) {
		String sql = "delete from student "
				+ "where id = ?";
		
		jdbc.update(sql, studentId);
	}
	
	@Override
	public List<Student> findAllStudentsInCollege(Integer collegeId) {
		String sql = "select * from student st "
				+ " join grp g on st.group_id = g.groupId "
				+ " join faculty f on g.faculty_id = f.facultyId "
				+ " join college c on f.college_id = c.collegeId "
				+ "where c.collegeId = ? "
				+ "order by st.lastName";
		
		return jdbc.query(sql, new StudentRowMapper(StudentTypes.COLLEGE_STUDENT), collegeId);
	
	}
	
	@Override
	public List<Student> findAllStudentsInFaculty(Long facultyId) {
		String sql = "select * from student st "
				+ " join grp g on st.group_id = g.groupId "
				+ " join faculty f on g.faculty_id = f.facultyId "
				+ " join college c on f.college_id = c.collegeId "
				+ "where f.facultyId = ? "
				+ "order by st.lastName";
		
		return jdbc.query(sql, new StudentRowMapper(StudentTypes.COLLEGE_STUDENT), facultyId);
	
	}
	
	@Override
	public List<Student> findAllStudentsInGroup(Long groupId) {
		String sql = "select * from student st "
				+ "join grp g on st.group_id = g.groupId "
				+ "join faculty f on g.faculty_id = f.facultyId "
				+ "join college c on f.college_id = c.collegeId "
				+ "where g.groupId = ? "
				+ "order by st.lastName";
		
		return jdbc.query(sql, new StudentRowMapper(StudentTypes.COLLEGE_STUDENT), groupId);
	}

	@Override
	public List<Student> findAllStudentsInCollegeByName(Integer collegeId, String name) {
		String sql = "select * from student st "
				+ " join grp g on st.group_id = g.groupId "
				+ " join faculty f on g.faculty_id = f.facultyId "
				+ " join college c on f.college_id = c.collegeId "
				+ "where c.collegeId = ? "
				+ "and st.lastName like '" + name
				+ "%' order by st.lastName";
		
		return jdbc.query(sql, new StudentRowMapper(StudentTypes.COLLEGE_STUDENT), collegeId);
	
	}
	
	@Override
	public List<Student> findAllStudentsInFacultyByName(Long facultyId, String name) {
		String sql = "select * from student st "
				+ " join grp g on st.group_id = g.groupId "
				+ " join faculty f on g.faculty_id = f.facultyId "
				+ " join college c on f.college_id = c.collegeId "
				+ "where f.facultyId = ? "
				+ "and st.lastName like '" + name
				+ "%' order by st.lastName";
		
		return jdbc.query(sql, new StudentRowMapper(StudentTypes.COLLEGE_STUDENT), facultyId);
	
	}
	
	@Override
	public Student findStudentById(Long studentId) {
		String sql = "select * from student st "
				+ "join grp g on st.group_id = g.groupId "
				+ "join faculty f on g.faculty_id = f.facultyId "
				+ "join college c on f.college_id = c.collegeId "
				+ "where st.id = ?";
				
		
		return jdbc.queryForObject(sql, new StudentRowMapper(StudentTypes.COLLEGE_STUDENT), studentId);
	
	}
	
	@Override
	public List<Mark> findStudentMarks(Long studentId) {
		String sql = "select * from mark m "
				+ "where m.student_id = ?";
				
		
		return jdbc.query(sql, new MarkRowMapper(TeacherTypes.COLLEGE_TEACHER,
						 						 StudentTypes.COLLEGE_STUDENT,
						 						 EducationInstitutionTypes.COLLEGE,
						 						 EducationSpecializationTypes.COLLEGE_FACULTY),
						 						 studentId);
	
	}
	
	
	
}
