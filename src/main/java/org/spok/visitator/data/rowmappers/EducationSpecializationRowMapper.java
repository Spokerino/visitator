package org.spok.visitator.data.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.spok.visitator.data.enum_types.EducationInstitutionTypes;
import org.spok.visitator.data.enum_types.EducationSpecializationTypes;
import org.spok.visitator.factories.EducationSpecializationFactory;
import org.spok.visitator.institution.CollegeFaculty;
import org.spok.visitator.institution.EducationSpecialization;
import org.springframework.jdbc.core.RowMapper;

public class EducationSpecializationRowMapper implements RowMapper<EducationSpecialization>{
	
	private EducationInstitutionTypes institutionType;
	private EducationSpecializationTypes specializationType;

	public EducationSpecializationRowMapper(EducationInstitutionTypes institutionType,
											EducationSpecializationTypes specializationType) {
		this.institutionType = institutionType;
		this.specializationType = specializationType;
	}

	@Override
	public EducationSpecialization mapRow(ResultSet rs, int rowNum)	throws SQLException {
		
		EducationSpecialization specialization = EducationSpecializationFactory.createSpecialization(specializationType);
		
		specialization.setId(rs.getLong("facultyId"));
		specialization.setName(rs.getString("facultyName"));
		specialization.setInstitution(new EducationInstitutionRowMapper(institutionType).mapRow(rs, rowNum));
		if(specializationType.equals(EducationSpecializationTypes.COLLEGE_FACULTY))
		{
			CollegeFaculty faculty = (CollegeFaculty) specialization;
			faculty.setYearsToBecomeBachelor(rs.getInt("bachelor"));
			faculty.setYearsToBecomeMaster(rs.getInt("master"));
			
			return faculty;
		}
		
		return specialization;
	}
	
	

}
