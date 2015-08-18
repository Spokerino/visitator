package org.spok.visitator.data.jdbc;

import java.util.List;

import org.spok.visitator.data.EducationInstitutionRepository;
import org.spok.visitator.data.enum_types.EducationInstitutionTypes;
import org.spok.visitator.data.enum_types.EducationSpecializationTypes;
import org.spok.visitator.data.rowmappers.EducationInstitutionRowMapper;
import org.spok.visitator.data.rsextractors.EducationInstitutionsResultSetExtractor;
import org.spok.visitator.institution.College;
import org.spok.visitator.institution.EducationInstitution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcCollegeRepository implements EducationInstitutionRepository {

	private JdbcOperations jdbc;

	@Autowired
	public JdbcCollegeRepository(JdbcOperations jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public List<EducationInstitution> findAllInstitutions() {
		String sql = "select * from college c "
				+ "left join faculty f on c.collegeId = f.college_id "
				+ "left join grp g on c.collegeId = g.college_id "
				+ "left join teacher t on c.collegeId = t.teachercollege_id";
		
		return jdbc.query(sql, new EducationInstitutionsResultSetExtractor(
				EducationInstitutionTypes.COLLEGE, 
				EducationSpecializationTypes.COLLEGE_FACULTY));
	}
	
	@Override
	public College findCollegeById(Integer id) {
		String sql = "select * from college c "
				+ "where c.collegeId = ?";
		return (College) jdbc.queryForObject(sql, new EducationInstitutionRowMapper(EducationInstitutionTypes.COLLEGE), id);
	}
	
	@Override
	public College findCollegeByName(String name) {
		String sql = "select * from college c "
				+ "where c.collegeName = ?";
		return (College) jdbc.queryForObject(sql, new EducationInstitutionRowMapper(EducationInstitutionTypes.COLLEGE), name);
	}
	
	@Override
	public void createCollege(College college) {
		String sql = "insert into college (collegeName, address) "
				+ "values (?, ?)";
		
		jdbc.update(sql, college.getName(), college.getAddress());
	}
	
	@Override
	public void updateCollege(College college) {
		String sql = "update college "
				+ "set collegeName = ?, "
				+ "address = ? "
				+ "where collegeId = ?";
		
		jdbc.update(sql, college.getName(), college.getAddress(), college.getId());
	}
	
	@Override
	public void deleteCollege(Integer collegeId) {
		String sql = "delete from college "
				+ "where collegeId = ?";
		
		jdbc.update(sql, collegeId);
	}
	
//	@Override
//	public Set<Subject> getCollegeSubjects(Integer collegeId){
//		String sql = "select * from subject s "
//				+ "join faculty_subject f_s on s.subjectId = f_s.subject_id "
//				+ "join faculty f on f_s.faculty_id = f.facultyId "
//				+ "where f.college_id = ?";
//		
//		Set<Subject> subjects = new TreeSet<>(jdbc.query(sql, new SubjectRowMapper(), collegeId));
//		return subjects;
//	}
	
	
//	@Override
//	public List<Subject> getCollegeSubjects(Integer collegeId){
//		String sql = "select * from subject s "
//				+ "join faculty_subject f_s on s.subjectId = f_s.subject_id "
//				+ "join faculty f on f_s.faculty_id = f.facultyId "
//				+ "where f.college_id = ?";
//		
//		
//		return jdbc.query(sql, new SubjectRowMapper(), collegeId);
//	}
	
//	@Override
//	public List<Student> findAllStudentsInCollege(Integer collegeId) {
//		String sql = "select * from student st "
//				+ " join grp g on st.group_id = g.groupId "
//				+ " join faculty f on g.faculty_id = f.facultyId "
//				+ " join college c on f.college_id = c.collegeId "
//				+ "where c.collegeId = ? "
//				+ "order by st.lastName";
//		
//		return jdbc.query(sql, new StudentRowMapper(StudentTypes.COLLEGE_STUDENT), collegeId);
//	
//	}

}
