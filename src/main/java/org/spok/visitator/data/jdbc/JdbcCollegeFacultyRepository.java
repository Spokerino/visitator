package org.spok.visitator.data.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spok.visitator.data.EducationSpecializationRepository;
import org.spok.visitator.entities.enum_types.EducationGroupTypes;
import org.spok.visitator.entities.enum_types.EducationInstitutionTypes;
import org.spok.visitator.entities.enum_types.EducationSpecializationTypes;
import org.spok.visitator.entities.enum_types.TeacherTypes;
import org.spok.visitator.data.rowmappers.EducationSpecializationRowMapper;
import org.spok.visitator.data.rsextractors.EducationSpecializationsResultSetExtractor;
import org.spok.visitator.entities.institution.CollegeFaculty;
import org.spok.visitator.entities.institution.EducationSpecialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcCollegeFacultyRepository implements EducationSpecializationRepository {

	private JdbcOperations jdbc;

	@Autowired
	public JdbcCollegeFacultyRepository(JdbcOperations jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public void createFaculty(CollegeFaculty faculty, Integer collegeId) {
		String sql = "insert into faculty (facultyName, college_id, bachelor, master) "
				+ "values (?, ?, ?, ?)";
		
		jdbc.update(sql, faculty.getName(), collegeId, faculty.getYearsToBecomeBachelor(),
					faculty.getYearsToBecomeMaster());
	}
	
	@Override
	public void updateFaculty(CollegeFaculty faculty) {
		String sql = "update faculty "
				+ "set facultyName = ?,"
				+ "bachelor = ?,"
				+ "master = ? "
				+ "where facultyId = ?";
		
		jdbc.update(sql, faculty.getName(), faculty.getYearsToBecomeBachelor(),
					faculty.getYearsToBecomeMaster(), faculty.getId());
	}
	
	@Override
	public void deleteFaculty(Long facultyId) {
		String sql = "delete from faculty "
				+ "where facultyId = ?";
		
		jdbc.update(sql, facultyId);
	}
	
	@Override
	public List<EducationSpecialization> findAllFaculties(Integer collegeId) {
		String sql =  "select * from faculty f  "
				+ "left join grp g on f.facultyId = g.faculty_id "
				+ "left join teacher t on f.facultyId = t.teacherfaculty_id "
				+ "left join faculty_subject f_s on f.facultyId = f_s.faculty_id "
				+ "left join subject s on f_s.subject_id = s.subjectId "
				+ "join college c on f.college_id = c.collegeId "
				+ "where f.college_id = ? "
				+ "order by f.facultyName, teacherFirstName, g.groupName";
		
		return jdbc.query(sql, new EducationSpecializationsResultSetExtractor(
				EducationInstitutionTypes.COLLEGE,
				EducationSpecializationTypes.COLLEGE_FACULTY,
				EducationGroupTypes.COLLEGE_GROUP,
				TeacherTypes.COLLEGE_TEACHER), collegeId);
	}

	@Override
	public EducationSpecialization findFacultyById(Long facultyId) {
		String sql = "select * from faculty f "
				+ "join college c on f.college_id = c.collegeId "
				+ "where f.facultyId = ?";
		
		return jdbc.queryForObject(sql
				, new EducationSpecializationRowMapper(
						EducationInstitutionTypes.COLLEGE,
						EducationSpecializationTypes.COLLEGE_FACULTY),
						facultyId);
	}

	@Override
	public Map getFacultiesMap(Integer collegeId) {
		Map<Long, String> facultiesMap = new HashMap<>();

		for(EducationSpecialization faculty: findAllFaculties(collegeId))
			facultiesMap.put(faculty.getId(), faculty.getName());

		return facultiesMap;
	}
		
}