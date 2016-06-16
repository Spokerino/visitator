package org.spok.visitator.services;

import org.spok.visitator.data.EducationGroupRepository;
import org.spok.visitator.data.EducationSpecializationRepository;
import org.spok.visitator.data.StudentRepository;
import org.spok.visitator.entities.institution.CollegeFaculty;
import org.spok.visitator.entities.institution.CollegeGroup;
import org.spok.visitator.entities.institution.EducationGroup;
import org.spok.visitator.entities.person.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private EducationGroupRepository groupRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EducationSpecializationRepository specializationRepository;

    public ModelAndView showCollegeGroups(Long facultyId) {

        CollegeFaculty faculty = (CollegeFaculty) specializationRepository.findFacultyById(facultyId);
        ModelAndView model = new ModelAndView("group/groups");
        List<EducationGroup> list = groupRepository.findAllFacultyGroups(facultyId);
        model.addObject("educationGroupList", list);
        model.addObject("faculty", faculty);

        return model;
    }

    public ModelAndView showCollegeGroupsById(Long groupId) {

        ModelAndView model = new ModelAndView("group/group");
        EducationGroup group = groupRepository.findGroupById(groupId);
        List<Student> students = studentRepository.findAllStudentsInGroup(groupId);
        model.addObject("group", group);
        model.addObject("studentList", students);

        return model;
    }

    public ModelAndView addGroupGet(Long facultyId) {

        CollegeFaculty faculty = (CollegeFaculty) specializationRepository.findFacultyById(facultyId);
        int[] courses = new int[faculty.getYearsToBecomeMaster()];
        for(int i = 0; i < courses.length; i++)
            courses[i] = i + 1;

        ModelAndView model = new ModelAndView("group/groupForm");
        model.addObject(new CollegeGroup());
        model.addObject("courses", courses);
        model.addObject("faculty", faculty);

        return model;
    }

    public ModelAndView addGroupPost(Long facultyId, CollegeGroup group,
                                     BindingResult result) {

        if(result.hasErrors()){
            return addGroupGet(facultyId);
        }

        groupRepository.createGroup(group, facultyId);
        return new ModelAndView("redirect:/colleges/{collegeId}/faculties/{facultyId}");
    }


    public ModelAndView editGroupGet(Long facultyId, Long groupId) {

        CollegeGroup group = (CollegeGroup) groupRepository.findGroupById(groupId);
        ModelAndView model = addGroupGet(facultyId);
        model.setViewName("group/editGroup");
        model.addObject("group", group);

        return model;
    }

    public ModelAndView editGroupPost(Long facultyId, Long groupId,
                                      CollegeGroup group, BindingResult result,
                                      String isDeleted) {

        if(isDeleted == null)
        {
            if(result.hasErrors()) {
                ModelAndView model = addGroupGet(facultyId);
                model.setViewName("group/editGroup");
                return model;
            }

            group.setId(groupId);
            groupRepository.updateGroup(group);
        }
        else
        {
            groupRepository.deleteGroup(groupId);
        }
        return new ModelAndView("redirect:/colleges/{collegeId}/faculties/{facultyId}/groups");
    }


}
