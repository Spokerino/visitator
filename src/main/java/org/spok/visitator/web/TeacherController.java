package org.spok.visitator.web;

import java.util.List;

import javax.validation.Valid;

import org.spok.visitator.data.EducationSpecializationRepository;
import org.spok.visitator.data.SubjectRepository;
import org.spok.visitator.data.TeacherRepository;
import org.spok.visitator.educ_person.CollegeTeacher;
import org.spok.visitator.educ_person.Teacher;
import org.spok.visitator.education.Subject;
import org.spok.visitator.institution.EducationSpecialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/colleges/{collegeId}")
public class TeacherController {

	private TeacherRepository teacherRepository;
	private SubjectRepository subjectRepository;
	private EducationSpecializationRepository facultyRepository;
	
	@Autowired
	public TeacherController(TeacherRepository repository,
							 SubjectRepository subjectRepository,
							 EducationSpecializationRepository facultyRepository) {
		this.teacherRepository = repository;
		this.subjectRepository = subjectRepository;
		this.facultyRepository = facultyRepository;
	}
	
	@RequestMapping(value="/teachers", method=RequestMethod.GET)
	public ModelAndView showCollegeTeachers(@PathVariable Integer collegeId) {
		
		ModelAndView model = new ModelAndView("college/collegeTeachers");
		List<Teacher> teachers = teacherRepository.getCollegeTeachers(collegeId);
		model.addObject("teacherList", teachers);
		
		return  model;
	}
	
	@RequestMapping(value="/faculties/{facultyId}/teachers", method=RequestMethod.GET)
	public ModelAndView showFacultyTeachers(@PathVariable Integer collegeId,
											@PathVariable Long facultyId) {
		
		ModelAndView model = new ModelAndView("faculty/facultyTeachers");
		List<Teacher> teachers = teacherRepository.getFacultyTeachers(facultyId);
		model.addObject("teacherList", teachers);
		
		return  model;
	}
	
	@RequestMapping(value="faculties/{facultyId}/teachers/new", method=RequestMethod.GET)
	public ModelAndView addTeacherFromFacultyGet(@PathVariable Integer collegeId,
												 @PathVariable Long facultyId) {
		
		List<Subject> subjects = subjectRepository.getFacultySubjects(facultyId);
		ModelAndView model = new ModelAndView("faculty/facultyTeacherForm");
		model.addObject("subjects", subjects);
		model.addObject("teacher", new CollegeTeacher());
		
		return model;
	}
	
	@RequestMapping(value="faculties/{facultyId}/teachers/new", method=RequestMethod.POST)
	public ModelAndView addTeacherFromFacultyPost(@PathVariable Integer collegeId,
			 									  @PathVariable Long facultyId,
										  		  @Valid @ModelAttribute("teacher") CollegeTeacher teacher,
										  		  BindingResult result,
										  		  @RequestParam(value="subject", required=false) int[] subjectsId) {
		
		if(result.hasErrors() || subjectsId == null) {
			ModelAndView model = new ModelAndView("faculty/facultyTeacherForm");
			List<Subject> subjects = subjectRepository.getFacultySubjects(facultyId);
			model.addObject("subjects", subjects);
			model.addObject("message", "Please, select at least one subject");
			return model; 
		}
		
		teacherRepository.addTeacherFromFaculty(teacher, facultyId);
		
		if(subjectsId != null) {
			long id = teacherRepository.getLastTeacherId().getId();
			for(int i : subjectsId)
				subjectRepository.addSubjectToTeacher(id, i);
		}
		
		ModelAndView model = showFacultyTeachers(collegeId, facultyId);
		model.setViewName("redirect:/colleges/{collegeId}/faculties/{facultyId}/teachers");
		
		return model;
	}
	
	@RequestMapping(value="/faculties/{facultyId}/teachers/{teacherId}/edit", 
			method=RequestMethod.GET)
	public ModelAndView editTeacherFromFacultyGet(@PathVariable Integer collegeId,
												  @PathVariable Long facultyId,
												  @PathVariable Long teacherId) {
		
		CollegeTeacher teacher = (CollegeTeacher) teacherRepository.getTeacherForId(teacherId);
		List<EducationSpecialization> faculties = facultyRepository.findAllFaculties(collegeId);
		List<Subject> subjectsToAdd = subjectRepository.getFacultySubjects(facultyId);
		List<Subject> teacherSubjects = subjectRepository.getTeacherSubjects(teacherId);
		subjectsToAdd.removeAll(teacherSubjects);
		teacher.setSubjects(teacherSubjects);
		ModelAndView model = addTeacherFromFacultyGet(collegeId, facultyId);
		model.addObject("faculties", faculties);
		model.addObject("teacher", teacher);
		model.setViewName("faculty/editFacultyTeacher");
		model.addObject("subjectsToAdd", subjectsToAdd);
		
		return model;
	}
	
	@RequestMapping(value="/faculties/{facultyId}/teachers/{teacherId}/edit",
			method=RequestMethod.POST)
	public ModelAndView editTeacherFromFacultyPost(@PathVariable Integer collegeId,
												   @PathVariable Long facultyId,
												   @PathVariable Long teacherId,
											 	   @Valid @ModelAttribute("teacher") CollegeTeacher teacher,
											 	   BindingResult result,
											 	   @RequestParam(required=false, value="check") String isDeleted,
											 	   @RequestParam(value="subjectToAdd", required=false) Integer[] subjectAdd,
											  	   @RequestParam(value="subjectToRemove", required=false) Integer[] subjectRemove,
											  	   @RequestParam("faculties") Long newFacultyId) {
	
		
		if(isDeleted == null)
		{
			teacher.setId(teacherId);
			
			if(result.hasErrors()) {
				
				ModelAndView model = editTeacherFromFacultyGet(collegeId, newFacultyId, teacherId); 
				model.addObject("teacher", teacher);
			}
			
			if(subjectAdd != null) {
				for(int i : subjectAdd)
					subjectRepository.addSubjectToTeacher(teacherId, i);
			}
			
			if(subjectRemove != null) {
				for(int i : subjectRemove)
					subjectRepository.deleteSubjectFromTeacher(teacherId, i);
			}
			
			
			
			if(newFacultyId != -1)
				teacherRepository.updateTeacher(teacher, newFacultyId);
			else
				teacherRepository.updateTeacher(teacher, facultyId);
			
			ModelAndView model = showFacultyTeachers(collegeId, facultyId);
			model.setViewName("redirect:/colleges/{collegeId}/faculties/{facultyId}/teachers");
			
			return model;
		}
		else
		{
			teacherRepository.deleteTeacher(teacherId);
			return new ModelAndView("redirect:/colleges/{collegeId}/faculties/{facultyId}/teachers");
	}
	
	}
	

	
	
	
}
