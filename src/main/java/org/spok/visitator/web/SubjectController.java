package org.spok.visitator.web;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.spok.visitator.data.SubjectRepository;
import org.spok.visitator.lesson.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class SubjectController {

	private SubjectRepository subjectRepository;
	
	@Autowired
	public SubjectController(SubjectRepository subjectRepository) {
		this.subjectRepository = subjectRepository;
	}
	
	@RequestMapping(value="/subjects", method=RequestMethod.GET)
	public Set<Subject> showAllSubjects() {
		
		Set<Subject> subjects = subjectRepository.getAllSubjects();
		return subjects;		
	}
	
	@RequestMapping(value="/subjects/new", method=RequestMethod.GET)
	public String addToAllSubjectsGet(Model model){

		model.addAttribute(new Subject());
		return "subjectForm";
	}
	
	@RequestMapping(value="/subjects/new", method=RequestMethod.POST)
	public String addToAllSubjectsPost(@Valid Subject subject,
									   Errors result){
		if(result.hasErrors())
			return "subjectForm";
		
		subjectRepository.createSubject(subject.getName());
				
		return "redirect:/subjects";
	}
	
	@RequestMapping(value="/subjects/{subjectId}/edit", method=RequestMethod.GET)
	public ModelAndView editSubjectGet(@PathVariable Integer subjectId){
		
		Subject subject = subjectRepository.getSubjectForId(subjectId);
		ModelAndView model = new ModelAndView("subject");
		model.addObject("subject", subject);
		
		return model;
	}
	
	@RequestMapping(value="/subjects/{subjectId}/edit", method=RequestMethod.POST)
	public ModelAndView editSubjectPost(@PathVariable Integer subjectId,
										@Valid Subject subject,
										Errors errors,
										@RequestParam(required=false, value="check") String isDeleted){
		
		if(isDeleted == null)
		{
			if(errors.hasErrors())
				return new ModelAndView("subject");
			
			subjectRepository.updateSubject(subjectId, subject.getName());
		}
		else
		{
			subjectRepository.deleteSubject(subjectId);
		}
		return new ModelAndView("redirect:/subjects");			
	}
	
	@RequestMapping(value="/colleges/{collegeId}/subjects", method=RequestMethod.GET)
	public ModelAndView showCollegeSubjects(
			@PathVariable Integer collegeId) {
		
		ModelAndView model = new ModelAndView("college/collegeSubjects");
		Set<Subject> subjects = subjectRepository.getCollegeSubjects(collegeId);
		
		model.addObject("subjectList", subjects);
				
		return model;		
	}
	
	@RequestMapping(value="/colleges/{collegeId}/faculties/{facultyId}/subjects", method=RequestMethod.GET)
	public ModelAndView showFacultySubjects(
			@PathVariable Integer collegeId,
			@PathVariable Long facultyId
			) {
		
		ModelAndView model = new ModelAndView("faculty/facultySubjects");
		List<Subject> subjects = subjectRepository.getFacultySubjects(facultyId);
		
		model.addObject("subjectList", subjects);
				
		return model; 
	}
	
	@RequestMapping(value="/colleges/{collegeId}/faculties/{facultyId}/subjects/new", method=RequestMethod.GET)
	public ModelAndView newSubjectFromFacultyGet(@PathVariable Integer collegeId,
											  	 @PathVariable Long facultyId){
		
		Set<Subject> allSubjects = subjectRepository.getAllSubjects();
		List<Subject> facultySubjects = subjectRepository.getFacultySubjects(facultyId);
		
		allSubjects.removeAll(facultySubjects);
		
		ModelAndView model = new ModelAndView("faculty/facultySubjectForm");
		model.addObject("subjectsToAdd", allSubjects);
		model.addObject("subjectsToRemove", facultySubjects);
		
		return model;
	}
	
	@RequestMapping(value="/colleges/{collegeId}/faculties/{facultyId}/subjects/new", method=RequestMethod.POST)
	public ModelAndView newSubjectFromFacultyPost(@PathVariable Integer collegeId,
											  	  @PathVariable Long facultyId,
											  	  @RequestParam(value="subjectToAdd", required=false) Integer[] subjectAdd,
											  	  @RequestParam(value="subjectToRemove", required=false) Integer[] subjectRemove){
		
		if(subjectAdd != null) {
			for(int subjectId : subjectAdd)
				subjectRepository.addSubjectToFaculty(facultyId, subjectId);
		}
		
		if(subjectRemove != null) {
			for(int subjectId : subjectRemove)
				subjectRepository.removeSubjectFromFaculty(facultyId, subjectId);
		}
		
		ModelAndView model = new ModelAndView("redirect:/colleges/{collegeId}/faculties/{facultyId}/subjects");
		
		return model;
	}

}
