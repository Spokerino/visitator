package org.spok.visitator.services;

import org.spok.visitator.data.EducationGroupRepository;
import org.spok.visitator.data.SubjectRepository;
import org.spok.visitator.entities.institution.EducationGroup;
import org.spok.visitator.entities.lesson.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {

    @Autowired
    private EducationGroupRepository repository;

    @Autowired
    private SubjectRepository subjectRepository;

    public ModelAndView home() {

        ModelAndView model = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        model.addObject("username", name);
        model.setViewName("mainPage");

        return model;

    }

    public ModelAndView login(String error) {

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

    public List<String> ajaxGroups(Long facultyId) {

        List<EducationGroup> groups = repository.findAllFacultyGroups(facultyId);
        List<String> names = new ArrayList<String>();
        for(EducationGroup g : groups)
            names.add(g.getName());

        return names;
    }

    public List<String> ajaxSubjects(Long facultyId) {

        List<Subject> subjects = subjectRepository.getFacultySubjects(facultyId);
        List<String> subjectNames = new ArrayList<String>();
        for(Subject s : subjects)
            subjectNames.add(s.getName());

        return subjectNames;
    }
}
