package org.spok.visitator.data.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.spok.visitator.data.enum_types.EducationInstitutionTypes;
import org.spok.visitator.factories.EducationInstitutionFactory;
import org.spok.visitator.institution.EducationInstitution;
import org.springframework.jdbc.core.RowMapper;

public class EducationInstitutionRowMapper implements RowMapper<EducationInstitution>{
	
	private EducationInstitutionTypes type;

	public EducationInstitutionRowMapper(EducationInstitutionTypes type) {
		this.type = type;
	}

	@Override
	public EducationInstitution mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		
		EducationInstitution institution = EducationInstitutionFactory.createInstitution(type);
		
		institution.setId(rs.getInt("collegeId"));
		institution.setName(rs.getString("collegeName"));
		institution.setAddress(rs.getString("address"));
		
		return institution;
	}

}