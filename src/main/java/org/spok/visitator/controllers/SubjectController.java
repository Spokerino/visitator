package org.spok.visitator.controllers;

import org.spok.visitator.entities.lesson.Subject;
import org.spok.visitator.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("/")
public class SubjectController {

	@Autowired
	private SubjectService subjectService;

	@RequestMapping(value="/subjects", method=RequestMethod.GET)
	public Set<Subject> showAllSubjects() {
		return subjectService.showAllSubjects();
	}
	
	@RequestMapping(value="/subjects/new", method=RequestMethod.GET)
	public String addToAllSubjectsGet(Model model){
		return subjectService.addToAllSubjectsGet(model);
	}
	
	@RequestMapping(value="/subjects/new", method=RequestMethod.POST)
	public String addToAllSubjectsPost(@Valid Subject subject,
									   Errors result){
		return subjectService.addToAllSubjectsPost(subject, result);
	}
	
	@RequestMapping(value="/subjects/{subjectId}/edit", method=RequestMethod.GET)
	public ModelAndView editSubjectGet(@PathVariable Integer subjectId){
		return subjectService.editSubjectGet(subjectId);
	}
	
	@RequestMapping(value="/subjects/{subjectId}/edit", method=RequestMethod.POST)
	public ModelAndView editSubjectPost(@PathVariable Integer subjectId,
										@Valid Subject subject,
										Errors errors,
										@RequestParam(required=false, value="check") String isDeleted){

		return subjectService.editSubjectPost(subjectId, subject, errors, isDeleted);
	}
	
	@RequestMapping(value="/colleges/{collegeId}/subjects", method=RequestMethod.GET)
	public ModelAndView showCollegeSubjects(
			@PathVariable Integer collegeId) {
		
		return subjectService.showCollegeSubjects(collegeId);
	}
	
	@RequestMapping(value="/colleges/{collegeId}/faculties/{facultyId}/subjects", method=RequestMethod.GET)
	public ModelAndView showFacultySubjects(
			@PathVariable Integer collegeId,
			@PathVariable Long facultyId) {
		
		return subjectService.showFacultySubjects(collegeId, facultyId);
	}
	
	@RequestMapping(value="/colleges/{collegeId}/faculties/{facultyId}/subjects/new", method=RequestMethod.GET)
	public ModelAndView newSubjectFromFacultyGet(@PathVariable Integer collegeId,
											  	 @PathVariable Long facultyId){
		
		return subjectService.newSubjectFromFacultyGet(collegeId, facultyId);
	}
	
	@RequestMapping(value="/colleges/{collegeId}/faculties/{facultyId}/subjects/new", method=RequestMethod.POST)
	public ModelAndView newSubjectFromFacultyPost(@PathVariable Integer collegeId,
											  	  @PathVariable Long facultyId,
											  	  @RequestParam(value="subjectToAdd", required=false) Integer[] subjectAdd,
											  	  @RequestParam(value="subjectToRemove", required=false) Integer[] subjectRemove){
		
		return subjectService.newSubjectFromFacultyPost(collegeId, facultyId, subjectAdd, subjectRemove);
	}

}
