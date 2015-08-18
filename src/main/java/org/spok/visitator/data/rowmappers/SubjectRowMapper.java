package org.spok.visitator.data.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;



import org.spok.visitator.education.Subject;
import org.springframework.jdbc.core.RowMapper;

public class SubjectRowMapper implements RowMapper<Subject>{

	@Override
	public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Subject subject = new Subject();
		subject.setId(rs.getInt("subjectId"));
		subject.setName(rs.getString("subjectName"));
		
		return subject;
	}

}
