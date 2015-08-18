package org.spok.visitator.data.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.spok.visitator.data.enum_types.EducationGroupTypes;
import org.spok.visitator.data.enum_types.EducationInstitutionTypes;
import org.spok.visitator.data.enum_types.EducationSpecializationTypes;
import org.spok.visitator.factories.EducationGroupFactory;
import org.spok.visitator.institution.EducationGroup;
import org.springframework.jdbc.core.RowMapper;

public class EducationGroupRowMapper implements RowMapper<EducationGroup>{
	
	private EducationInstitutionTypes institutionType;
	private EducationSpecializationTypes specializationType;
	private EducationGroupTypes groupType;
	
	
	public EducationGroupRowMapper(EducationInstitutionTypes institutionType,
								   EducationSpecializationTypes specializationType,
								   EducationGroupTypes groupType) {
		this.institutionType = institutionType;
		this.specializationType = specializationType;
		this.groupType = groupType;
	}

	@Override
	public EducationGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		EducationGroup group = EducationGroupFactory.createGroup(groupType);
		
		group.setId(rs.getLong("groupId"));
		group.setName(rs.getString("groupName"));
		group.setCourse(rs.getInt("course"));
		group.setSpecialization(new EducationSpecializationRowMapper(institutionType, specializationType).mapRow(rs, rowNum));
		
			
		return group;
	}

}
