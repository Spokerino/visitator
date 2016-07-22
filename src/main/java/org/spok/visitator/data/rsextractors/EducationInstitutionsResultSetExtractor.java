package org.spok.visitator.data.rsextractors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.spok.visitator.entities.enum_types.EducationInstitutionTypes;
import org.spok.visitator.entities.enum_types.EducationSpecializationTypes;
import org.spok.visitator.data.rowmappers.EducationInstitutionRowMapper;
import org.spok.visitator.data.rowmappers.EducationSpecializationRowMapper;
import org.spok.visitator.entities.institution.EducationInstitution;
import org.spok.visitator.entities.institution.EducationSpecialization;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class EducationInstitutionsResultSetExtractor implements ResultSetExtractor<List<EducationInstitution>>{

	private EducationInstitutionTypes institutionType;
	private EducationSpecializationTypes specializationType;

	public EducationInstitutionsResultSetExtractor(EducationInstitutionTypes type,
												   EducationSpecializationTypes specializationType) {

		this.institutionType = type;
		this.specializationType = specializationType;
	}

	@Override
	public List<EducationInstitution> extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		Map<Integer, EducationInstitution> institutions = new LinkedHashMap<Integer, EducationInstitution>();
		
		int rowNum = 0;
		
		while(rs.next()) {
			
			Integer institutionId = rs.getInt("collegeId");
			EducationInstitution institution = institutions.get(institutionId);
			if(institution == null) {
				institution = new EducationInstitutionRowMapper(institutionType).mapRow(rs, rowNum);
				institutions.put(institutionId, institution);
			}
			
			List<EducationSpecialization> specializationsList = institution.getSpecializations();
			if(specializationsList == null) {
				specializationsList = new ArrayList<EducationSpecialization>();
				institution.setSpecializations(specializationsList);
			}
			
			EducationSpecialization specialization = new EducationSpecializationRowMapper(
					institutionType,
					specializationType).mapRow(rs, rowNum);
			
			boolean same = false;
			for(EducationSpecialization es : specializationsList) {
				if(specialization.getId().equals(es.getId()))
					same = true;
			}
			
			if (!same) specializationsList.add(specialization);
		}
		
		List<EducationInstitution> institutionsList = new ArrayList<EducationInstitution>(institutions.values());
		
		return institutionsList;
	}
	
	
}
