package org.spok.visitator.web;

import java.util.ArrayList;
import java.util.List;

import org.spok.visitator.data.EducationGroupRepository;
import org.spok.visitator.data.SubjectRepository;
import org.spok.visitator.education.Subject;
import org.spok.visitator.institution.EducationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping({"/", "/homepage", "/home", "/main"})
public class HomeController {

	private EducationGroupRepository repository;
	private SubjectRepository subjectRepository;
	
	@Autowired
	public HomeController(EducationGroupRepository repository,
						  SubjectRepository subjectRepository) {
		this.repository = repository;
		this.subjectRepository = subjectRepository;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView home() {
	 
			ModelAndView model = new ModelAndView();
	 
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    String name = auth.getName();
		 
		    model.addObject("username", name);
			model.setViewName("mainPage");
	 
			return model;
	 
		}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error
			) {
	 
			ModelAndView model = new ModelAndView();
			if (error != null) {
				model.addObject("error", "Invalid username and password!");
			}
	 
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    String name = auth.getName();
		 
		    model.addObject("username", name);
		    model.setViewName("loginko");
	 
			return model;
	 
		}
	
	@RequestMapping(value="ajax/groups", method=RequestMethod.GET)
	
	public @ResponseBody  List<String> ajaxGroups(@RequestParam(value="facultyId", required=true) Long facultyId) {
		
		List<EducationGroup> groups = repository.findAllFacultyGroups(facultyId);
		List<String> names = new ArrayList<String>();
		for(EducationGroup g : groups)
			names.add(g.getName());
		
		return names;
	}
	
	@RequestMapping(value="ajax/subjects", method=RequestMethod.GET)
	
	public @ResponseBody  List<String> ajaxSubjects(@RequestParam(value="facultyId", required=true) Long facultyId) {
		
		List<Subject> subjects = subjectRepository.getFacultySubjects(facultyId);
		List<String> subjectNames = new ArrayList<String>();
		for(Subject s : subjects)
			subjectNames.add(s.getName());
		
		return subjectNames;
	}
	

}
