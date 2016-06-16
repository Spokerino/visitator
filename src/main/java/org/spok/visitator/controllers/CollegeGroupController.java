package org.spok.visitator.controllers;

import org.spok.visitator.entities.institution.CollegeGroup;
import org.spok.visitator.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@RequestMapping("colleges/{collegeId}/faculties/{facultyId}/groups")
public class CollegeGroupController {

	@Autowired
	private GroupService groupService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView showCollegeGroups(@PathVariable Integer collegeId,
										  @PathVariable Long facultyId) {
		
		return groupService.showCollegeGroups(facultyId);
	}

	@RequestMapping(value="/{groupId}", method=RequestMethod.GET)
	public ModelAndView showCollegeGroupsById(@PathVariable Integer collegeId,
											  @PathVariable Long facultyId,
											  @PathVariable Long groupId) {
		return groupService.showCollegeGroupsById(groupId);
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public ModelAndView addGroupGet(@PathVariable Integer collegeId,
									@PathVariable Long facultyId) {
		return groupService.addGroupGet(facultyId);
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public ModelAndView addGroupPost(@PathVariable Integer collegeId,
									 @PathVariable Long facultyId,
							   		 @Valid @ModelAttribute("collegeGroup") CollegeGroup group,
							   		 BindingResult result) {
		
		return groupService.addGroupPost(facultyId, group, result);
	}

	@RequestMapping(value="/{groupId}/edit", method=RequestMethod.GET)
	public ModelAndView editGroupGet(@PathVariable Integer collegeId,
									 @PathVariable Long facultyId,
									 @PathVariable Long groupId) {
		
		return groupService.editGroupGet(facultyId, groupId);
	}
	
	@RequestMapping(value="/{groupId}/edit", method=RequestMethod.POST)
	public ModelAndView editGroupPost(@PathVariable Integer collegeId,
									  @PathVariable Long facultyId,
							    @PathVariable Long groupId,
							    @Valid @ModelAttribute("group") CollegeGroup group,
							    BindingResult result,
							    @RequestParam(required=false, value="check") String isDeleted) {
		
		return groupService.editGroupPost(facultyId, groupId, group, result, isDeleted);
	}
	
}
