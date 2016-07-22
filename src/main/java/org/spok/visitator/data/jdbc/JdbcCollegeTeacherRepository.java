package org.spok.visitator.data.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spok.visitator.data.TeacherRepository;
import org.spok.visitator.entities.enum_types.EducationInstitutionTypes;
import org.spok.visitator.entities.enum_types.EducationSpecializationTypes;
import org.spok.visitator.entities.enum_types.TeacherTypes;
import org.spok.visitator.data.rowmappers.TeacherRowMapper;
import org.spok.visitator.data.rsextractors.TeachersResultSetExtractor;
import org.spok.visitator.entities.person.CollegeTeacher;
import org.spok.visitator.entities.person.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcCollegeTeacherRepository implements TeacherRepository {

	private  JdbcOperations jdbc;
	
	@Autowired
	public JdbcCollegeTeacherRepository(JdbcOperations jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public List<Teacher> getCollegeTeachers(Integer collegeId) {
		String sql = "select * from teacher t "
				+ "left join teacher_subject t_s on t.teacherid = t_s.teacher_id "
				+ "left join subject s on t_s.subject_id = s.subjectId "
				+ "join faculty f on t.teacherFaculty_id = f.facultyId "
				+ "join college c on f.college_id = c.collegeId "
				+ "where c.collegeId = ? "
				+ "order by t.teacherFirstName, t.teacherLastName";
		
		return jdbc.query(sql, new TeachersResultSetExtractor(TeacherTypes.COLLEGE_TEACHER,
						  EducationInstitutionTypes.COLLEGE,
						  EducationSpecializationTypes.COLLEGE_FACULTY),
						  	collegeId);
	}
	
	@Override
	public List<Teacher> getFacultyTeachers(Long facultyId) {
		String sql = "select * from teacher t "
				+ "left join teacher_subject t_s on t.teacherid = t_s.teacher_id "
				+ "left join subject s on t_s.subject_id = s.subjectId "
				+ "join faculty f on t.teacherFaculty_id = f.facultyId "
				+ "join college c on f.college_id = c.collegeId "
				+ "where t.teacherFaculty_id = ? "
				+ " order by t.teacherFirstname, t.teacherLastName";
		
		return jdbc.query(sql, new TeachersResultSetExtractor(TeacherTypes.COLLEGE_TEACHER,
				  EducationInstitutionTypes.COLLEGE,
				  EducationSpecializationTypes.COLLEGE_FACULTY),
						  facultyId);
	}
	
	@Override
	public Teacher getTeacherForId(Long teacherId) {
		String sql = "select * from teacher t "
				+ "join faculty f on t.teacherFaculty_id = f.facultyId "
				+ "join college c on f.college_id = c.collegeId "
				+ "where t.teacherId = ? ";
		
		return jdbc.queryForObject(sql, new TeacherRowMapper(TeacherTypes.COLLEGE_TEACHER,
															 EducationInstitutionTypes.COLLEGE,
															 EducationSpecializationTypes.COLLEGE_FACULTY),
															 teacherId);

	}
	
	@Override
	public Teacher getLastTeacherId() {
		String sql = "select * from teacher t "
				+ "join faculty f on t.teacherFaculty_id = f.facultyId "
				+ "join college c on f.college_id = c.collegeId "
				+ "order by t.teacherId desc "
				+ "limit 1";
		
		return jdbc.queryForObject(sql, new TeacherRowMapper(TeacherTypes.COLLEGE_TEACHER,
															 EducationInstitutionTypes.COLLEGE,
															 EducationSpecializationTypes.COLLEGE_FACULTY));
	}
	
	@Override
	public void addTeacherFromFaculty(Teacher teacher, Long facultyId) {
		String sql = "insert into teacher (teacherFirstName, teacherlastName, teacherbirthday, teachergender, email, teacherfaculty_id) "
				+ "values (?, ?, ?, ?, ?, ?)";
		
		jdbc.update(sql,
					teacher.getFirstName(),
					teacher.getLastName(),
					teacher.getBirthday(),
					teacher.getGender(),
					teacher.getEmail(),
					facultyId);
	}
	
	@Override
	public void deleteTeacher(Long teacherId) {
		String sql = "delete from teacher "
				+ "where teacherId = ?";
		
		jdbc.update(sql, teacherId);
	}
	
	@Override
	public void updateTeacher(CollegeTeacher teacher, Long newFacultyId) {
		String sql = "update teacher "
				+ "set teacherFirstName = ?,"
				+ "teacherLastName = ?,"
				+ "teacherGender = ?,"
				+ "teacherBirthday = ?,"
				+ "email = ?,"
				+ "teacherFaculty_id = ? "
				+ "where teacherId = ?";
		
		jdbc.update(sql,
				teacher.getFirstName(),
				teacher.getLastName(),
				teacher.getGender(),
				teacher.getBirthday(),
				teacher.getEmail(),
				newFacultyId,
				teacher.getId());
	}

	@Override
	public Map getTeachersMap(Integer collegeId) {
		Map<Long, String> teachersMap = new HashMap<>();

		for(Teacher t : getCollegeTeachers(collegeId))
			teachersMap.put(t.getId(), t.getFullName());

		return teachersMap;
	}
	
}
