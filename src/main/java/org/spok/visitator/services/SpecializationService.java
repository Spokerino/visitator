package org.spok.visitator.services;

import org.spok.visitator.data.EducationGroupRepository;
import org.spok.visitator.data.EducationSpecializationRepository;
import org.spok.visitator.entities.institution.CollegeFaculty;
import org.spok.visitator.entities.institution.EducationGroup;
import org.spok.visitator.entities.institution.EducationSpecialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class SpecializationService {

    @Autowired
    private EducationSpecializationRepository specializationRepository;

    @Autowired
    private EducationGroupRepository groupRepository;

    public ModelAndView getAllFaculties(Integer collegeId) {

        ModelAndView model = new ModelAndView("faculty/faculties");
        List<EducationSpecialization> list = specializationRepository.findAllFaculties(collegeId);
        model.addObject("educationSpecializationList", list);
        model.addObject("collegeId", collegeId);

        return model;
    }

    public String addFacultyGet(Model model) {

        model.addAttribute(new CollegeFaculty());
        return "faculty/facultyForm";
    }


    public String addFacultyPost(Integer collegeId, CollegeFaculty faculty,
                                 BindingResult result) {

        if(result.hasErrors()){
            return "faculty/facultyForm";
        }

        specializationRepository.createFaculty(faculty, collegeId);
        return "redirect:/colleges/{collegeId}/faculties";
    }

    public ModelAndView showFacultyById(Integer collegeId, Long facultyId){

        ModelAndView model = new ModelAndView("faculty/faculty");
        EducationSpecialization faculty = specializationRepository.findFacultyById(facultyId);
        List<EducationGroup> list = groupRepository.findAllFacultyGroups(facultyId);

        model.addObject("educationGroupList", list);
        model.addObject("faculty", faculty);
        model.addObject(collegeId);

        return model;
    }

    public ModelAndView editFacultyGet(Long facultyId) {

        CollegeFaculty faculty = (CollegeFaculty) specializationRepository.findFacultyById(facultyId);
        ModelAndView model = new ModelAndView("faculty/editFaculty");
        model.addObject("faculty", faculty);

        return model;
    }

    public String editFacultyPost(Long facultyId, CollegeFaculty faculty, BindingResult result,
                                  String isDeleted) {

        if(isDeleted == null)
        {
            if(result.hasErrors())
                return "faculty/editFaculty";

            faculty.setId(facultyId);
            specializationRepository.updateFaculty(faculty);
        }
        else
        {
            specializationRepository.deleteFaculty(facultyId);
        }
        return "redirect:/colleges/{collegeId}/faculties";
    }

}
