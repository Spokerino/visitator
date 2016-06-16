package org.spok.visitator.services;


import org.spok.visitator.data.SubjectRepository;
import org.spok.visitator.entities.lesson.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public Set<Subject> showAllSubjects() {
        Set<Subject> subjects = subjectRepository.getAllSubjects();
        return subjects;
    }

    public String addToAllSubjectsGet(Model model){

        model.addAttribute(new Subject());
        return "subjectForm";
    }

    public String addToAllSubjectsPost(Subject subject, Errors result){
        if(result.hasErrors())
            return "subjectForm";

        subjectRepository.createSubject(subject.getName());

        return "redirect:/subjects";
    }

    public ModelAndView editSubjectGet(Integer subjectId){

        Subject subject = subjectRepository.getSubjectForId(subjectId);
        ModelAndView model = new ModelAndView("subject");
        model.addObject("subject", subject);

        return model;
    }

    public ModelAndView editSubjectPost(Integer subjectId,
                                        Subject subject,
                                        Errors errors,
                                        String isDeleted){

//        subject.setId(subjectId);

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

    public ModelAndView showCollegeSubjects(Integer collegeId) {

        ModelAndView model = new ModelAndView("college/collegeSubjects");
        Set<Subject> subjects = subjectRepository.getCollegeSubjects(collegeId);

        model.addObject("subjectList", subjects);

        return model;
    }

    public ModelAndView showFacultySubjects(Integer collegeId, Long facultyId) {

        ModelAndView model = new ModelAndView("faculty/facultySubjects");
        List<Subject> subjects = subjectRepository.getFacultySubjects(facultyId);

        model.addObject("subjectList", subjects);

        return model;
    }

    public ModelAndView newSubjectFromFacultyGet(Integer collegeId, Long facultyId){

        Set<Subject> allSubjects = subjectRepository.getAllSubjects();
        List<Subject> facultySubjects = subjectRepository.getFacultySubjects(facultyId);

        allSubjects.removeAll(facultySubjects);

        ModelAndView model = new ModelAndView("faculty/facultySubjectForm");
        model.addObject("subjectsToAdd", allSubjects);
        model.addObject("subjectsToRemove", facultySubjects);

        return model;
    }

    public ModelAndView newSubjectFromFacultyPost(Integer collegeId,
                                                  Long facultyId,
                                                  Integer[] subjectAdd,
                                                  Integer[] subjectRemove){

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
