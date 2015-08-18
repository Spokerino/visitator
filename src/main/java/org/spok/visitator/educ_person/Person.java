package org.spok.visitator.educ_person;



import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.spok.visitator.institution.EducationInstitution;
import org.spok.visitator.institution.EducationSpecialization;
import org.spok.visitator.lesson.Lesson;
import org.springframework.format.annotation.DateTimeFormat;

public abstract class Person {

	  private Long id;
	  
//	  @Size(min=5, max=10)
//	  private String username;
//
//	  @Size(min=5, max=25)
//	  private String password;
	  
	  private EducationInstitution institution;
	  
	  private EducationSpecialization specialization;
	  
	  @NotNull
	  @Size (min=2, max=30, message="Length of First Name must be between {min} and {max} characters")
	  private String firstName;
	  
	  @NotNull
	  @Size (min=2, max=30, message="Length of Last Name must be between {min} and {max} characters")
	  private String lastName;
	
	  private int gender;
	  
	  @Past
	  @NotNull(message="Please, enter a date of birth")
	  @DateTimeFormat(pattern="yyyy-MM-dd")
	  private Date birthday;
	  
	  private List<Lesson> lessons;

	  public Person() {}
	  
	  public String getFullName() {
			return firstName + " " + lastName;
		}
	  
	  public int getGender() {
		   return gender;
	  }
	  
	  public void setGender(int gender) {
		  this.gender = gender;
	  }
	  
	  public String getGenderToString() {
		  if(this.gender == 0)
			  return "Female";
		  else 
			  return "Male";
		}
	  
//	  public int getAge(LocalDate bday) {
//		  return LocalDate.now().getYear() - bday.getYear();
//	  }

	  public Long getId() {
	    return id;
	  }

	  public void setId(Long id) {
	    this.id = id;
	  }

	  public String getFirstName() {
	    return firstName;
	  }

	  public void setFirstName(String firstName) {
	    this.firstName = firstName;
	  }

	  public String getLastName() {
	    return lastName;
	  }

	  public void setLastName(String lastName) {
	    this.lastName = lastName;
	  }
	  
	  public Date getBirthday() {
		  return birthday;
	  }

	  public void setBirthday(Date birthday) {
		  this.birthday = birthday;
	  }

	public EducationInstitution getInstitution() {
		return institution;
	}

	public void setInstitution(EducationInstitution institution) {
		this.institution = institution;
	}

	public EducationSpecialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(EducationSpecialization specialization) {
		this.specialization = specialization;
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}
	  
}
