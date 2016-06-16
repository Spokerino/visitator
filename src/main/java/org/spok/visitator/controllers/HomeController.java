package org.spok.visitator.controllers;

import org.spok.visitator.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping({"/", "/homepage", "/home", "/main"})
public class HomeController {

	@Autowired
	private HomeService homeService;

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView home() {
			return homeService.home();
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error) {
			return homeService.login(error);
	}
	
	@RequestMapping(value="ajax/groups", method=RequestMethod.GET)
	public @ResponseBody  List<String> ajaxGroups(@RequestParam(value="facultyId", required=true)
													  Long facultyId) {
		return homeService.ajaxGroups(facultyId);
	}
	
	@RequestMapping(value="ajax/subjects", method=RequestMethod.GET)
	public @ResponseBody  List<String> ajaxSubjects(@RequestParam(value="facultyId", required=true)
														Long facultyId) {
		return homeService.ajaxSubjects(facultyId);
	}
}