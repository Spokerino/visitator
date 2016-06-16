package org.spok.visitator.entities.lesson;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Subject implements Comparable<Subject> {

	private Integer id;
	
	@NotEmpty
	@Size(min=3, max=15, message="Length of subject name must be between {min} and {max} characters")
	private String name;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		return (o instanceof Subject) && (((Subject) o).getName().equals(this.getName()));
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public int compareTo(Subject s) {
		return name.compareTo(s.getName());
	}
	
}
