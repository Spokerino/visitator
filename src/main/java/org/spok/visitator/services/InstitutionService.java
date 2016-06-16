package org.spok.visitator.services;

import org.spok.visitator.DataTypeException;
import org.spok.visitator.data.EducationInstitutionRepository;
import org.spok.visitator.data.EducationSpecializationRepository;
import org.spok.visitator.entities.enum_types.EducationInstitutionTypes;
import org.spok.visitator.entities.institution.College;
import org.spok.visitator.entities.institution.EducationInstitution;
import org.spok.visitator.entities.institution.EducationSpecialization;
import org.spok.visitator.factories.EducationInstitutionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class InstitutionService {

    @Autowired
    private EducationInstitutionRepository institutionRepostory;

    @Autowired
    private EducationSpecializationRepository specializationRepository;

    public ModelAndView getAllColleges() {

		ModelAndView model = new ModelAndView("college/colleges");
		model.addObject("colleges", institutionRepostory.findAllInstitutions());

        return model;
    }

    public ModelAndView getCollegeById(int institutionId) {

        ModelAndView model = new ModelAndView("college/college");
        EducationInstitution institution =  institutionRepostory.findInstitutionById(institutionId);
        List<EducationSpecialization> list = specializationRepository.findAllFaculties(institutionId);
        institution.setSpecializations(list);
        model.addObject(institution);

        return model;
    }

    public String addInstitutionGet(Model model, EducationInstitutionTypes type) {


        try {
            model.addAttribute(EducationInstitutionFactory.createInstitution(type));
        } catch (DataTypeException e) {
            e.printStackTrace();
        }

        if (type.equals(EducationInstitutionTypes.COLLEGE))
            return "college/collegeForm";
        else
            return "school";

    }

    public String addCollegePost(EducationInstitution institution, BindingResult result){

        if(result.hasErrors()){
            return "college/collegeForm";
        }

        institutionRepostory.createInstitution(institution);
        return "redirect:/colleges";
    }

    public ModelAndView editCollegeGet(int institutionId){
        EducationInstitution institution = institutionRepostory.findInstitutionById(institutionId);

        ModelAndView model = new ModelAndView("college/editCollege");
        model.addObject("college", institution);

        return model;
    }

    public String editCollegePost(int collegeId, College college,
                                  BindingResult result, String isDeleted) {

        if(isDeleted == null)
        {
            if(result.hasErrors())
                return "college/editCollege";

            college.setId(collegeId);
            institutionRepostory.updateInstitution(college);
        }
        else
        {
            institutionRepostory.deleteInstitution(collegeId);
        }
        return "redirect:/colleges";
    }

}
