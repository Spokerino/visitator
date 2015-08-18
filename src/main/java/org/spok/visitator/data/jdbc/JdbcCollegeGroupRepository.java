package org.spok.visitator.data.jdbc;

import java.util.List;

import org.spok.visitator.data.EducationGroupRepository;
import org.spok.visitator.data.enum_types.EducationGroupTypes;
import org.spok.visitator.data.enum_types.EducationInstitutionTypes;
import org.spok.visitator.data.enum_types.EducationSpecializationTypes;
import org.spok.visitator.data.rowmappers.EducationGroupRowMapper;
import org.spok.visitator.data.rsextractors.EducationGroupsResultSetExtractor;
import org.spok.visitator.institution.CollegeGroup;
import org.spok.visitator.institution.EducationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcCollegeGroupRepository implements EducationGroupRepository {

	private JdbcOperations jdbc;

	@Autowired
	public JdbcCollegeGroupRepository(JdbcOperations jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public void createGroup(CollegeGroup group, Long facultyId) {
		String sql = "insert into grp (groupName, course, faculty_id) "
				+ "values (?, ?, ?)";
		
		jdbc.update(sql, group.getName(), group.getCourse(), facultyId);
	}
	
	@Override
	public void updateGroup(CollegeGroup group) {
		String sql = "update grp "
				+ "set groupName = ?,"
				+ "course = ? "
				+ "where groupId = ?";
		
		jdbc.update(sql, group.getName(), group.getCourse(), group.getId());
	}
	
	@Override
	public void deleteGroup(Long groupId) {
		String sql = "delete from grp "
				+ "where groupId = ?";
		
		jdbc.update(sql, groupId);
	}
	
	@Override
	public List<EducationGroup> findAllFacultyGroups(Long facultyId) {
		String sql = "select * from grp g "
				+ "left join student st on g.groupId = st.group_id "
				+ "join faculty f on g.faculty_id = f.facultyId "
				+ "join college c on f.college_id = c.collegeId "
				+ "where g.faculty_id = ? "
				+ "order by g.groupName";
		
		return  jdbc.query(sql, new EducationGroupsResultSetExtractor(
										EducationInstitutionTypes.COLLEGE,
										EducationSpecializationTypes.COLLEGE_FACULTY,
										EducationGroupTypes.COLLEGE_GROUP),
										facultyId);
	}
	
	@Override
	public EducationGroup findGroupById(Long groupId) {
		String sql = "select * from grp g "
				+ "join faculty f on g.faculty_id = f.facultyId "
				+ "join college c on f.college_id = c.collegeId "
				+ "where g.groupId = ?";
		
		return jdbc.queryForObject(sql, new EducationGroupRowMapper(
				EducationInstitutionTypes.COLLEGE,
				EducationSpecializationTypes.COLLEGE_FACULTY,
				EducationGroupTypes.COLLEGE_GROUP),
				groupId);
	}
	
	@Override
	public EducationGroup findGroupByName(Long facultyId, String groupName) {
		String sql = "select * from grp g "
				+ "join faculty f on g.faculty_id = f.facultyId "
				+ "join college c on f.college_id = c.collegeId "
				+ "where g.faculty_id = ? "
				+ "and g.groupName = ?";
		
		return jdbc.queryForObject(sql, new EducationGroupRowMapper(
				EducationInstitutionTypes.COLLEGE,
				EducationSpecializationTypes.COLLEGE_FACULTY,
				EducationGroupTypes.COLLEGE_GROUP), 
				facultyId, groupName);
	}
}
