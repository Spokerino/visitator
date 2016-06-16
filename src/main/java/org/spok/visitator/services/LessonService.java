package org.spok.visitator.services;

import org.spok.visitator.data.*;
import org.spok.visitator.entities.enum_types.CollegeFacultyGroup;
import org.spok.visitator.entities.institution.CollegeGroup;
import org.spok.visitator.entities.institution.EducationGroup;
import org.spok.visitator.entities.institution.EducationSpecialization;
import org.spok.visitator.entities.lesson.*;
import org.spok.visitator.entities.person.CollegeStudent;
import org.spok.visitator.entities.person.Student;
import org.spok.visitator.entities.person.Teacher;
import org.spok.visitator.factories.LessonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private EducationSpecializationRepository facultyRepository;
    @Autowired
    private EducationGroupRepository groupRepository;

    public ModelAndView showLessons(Number id, CollegeFacultyGroup type) {

        ModelAndView model = new ModelAndView();

        switch (type) {
            case COLLEGE:
                model.setViewName("college/collegeLessons");
                break;
            case FACULTY:
                model.setViewName("faculty/facultyLessons");
                break;
            case GROUP:
                model.setViewName("group/groupLessons");
                CollegeGroup group = (CollegeGroup) groupRepository.findGroupById((Long)id);
                model.addObject("group", group);
                break;
        }

        List<Lesson> lessons = lessonRepository.findLessons(type, id);
        for(Lesson l : lessons) {
            l.setStudents(lessonRepository.findStudentsOnLesson(l.getId()));
        }

        Collections.sort(lessons);
        model.addObject("lessonList", lessons);

        return model;
    }

    public ModelAndView showLesson(Long lessonId, CollegeFacultyGroup type) {

        ModelAndView model = new ModelAndView();

        switch (type) {
            case COLLEGE:
                model.setViewName("college/collegeLesson");
                break;
            case FACULTY:
                model.setViewName("faculty/facultyLesson");
                break;
            case GROUP:
                model.setViewName("group/groupLesson");
                break;
        }

        Lesson lesson = lessonRepository.findLessonById(lessonId);
        List<EducationGroup> groups = lessonRepository.showLessonGroupsById(lessonId);
        List<Mark> marks = lessonRepository.showLessonMarks(lessonId);
        List<Student> students = lessonRepository.findStudentsOnLesson(lessonId);
        lesson.setGroups(groups);
        lesson.setStudents(students);
        model.addObject("lesson", lesson);
        model.addObject("marks", marks);

        return model;
    }

    public ModelAndView showLessonGroup(Long groupId, Long lessonId, CollegeFacultyGroup type) {
        ModelAndView model = showLesson(lessonId, type);
        CollegeGroup group = (CollegeGroup) groupRepository.findGroupById(groupId);
        model.addObject("group", group);

        return model;
    }


    public ModelAndView showEditableLesson(Long lessonId, CollegeFacultyGroup type) {

        ModelAndView model = showLesson(lessonId, type);

        switch (type) {
            case COLLEGE:
                model.setViewName("college/editCollegeLesson");
                break;
            case FACULTY:
                model.setViewName("faculty/editFacultyLesson");
                break;
        }

        return model;
    }

    public ModelAndView showEditableLessonGroup(Long groupId, Long lessonId,
                                                CollegeFacultyGroup type) {

        ModelAndView model = showLessonGroup(groupId, lessonId, type);
        model.setViewName("group/editGroupLesson");

        return model;
    }


    public ModelAndView showEditedLesson(Number institutionId, Long lessonId, Long[] absenceChange,
                             String[] freshMarks, String isDeleted, CollegeFacultyGroup type) {

        if(isDeleted == null)
        {
            List<Student> absentStudents = lessonRepository.findStudentsOnLesson(lessonId);
            List<Long> modifiedStudentsId = new ArrayList<>();

            if(absenceChange != null) {
                modifiedStudentsId.addAll(Arrays.asList(absenceChange));
                for(Student s : absentStudents) {
                    for(Long l : absenceChange) {
                        if(s.getId().equals(l)) {
                            lessonRepository.removeStudentFromLesson(lessonId, l);
                            modifiedStudentsId.remove(l);
                        }
                    }
                }

                for(Long l : modifiedStudentsId) {
                    lessonRepository.addStudentToLesson(lessonId, l);
                }
            }

            if(freshMarks != null) {
                List<Mark> marksFromDb = lessonRepository.showLessonMarks(lessonId);

                for(String s : freshMarks) {
                    boolean isMatch = false;
                    Long id = Long.parseLong(s.split(" ")[0]);
                    for(Mark m : marksFromDb) {
                        if(m.getStudent().getId().equals(id)) {
                            isMatch = true;
                            if(s.split(" ").length > 1) {
                                int freshMark = Integer.parseInt(s.split(" ")[1]);
                                if(m.getMark() != freshMark)
                                    lessonRepository.updateMark(lessonId, id, freshMark);
                            }
                            else
                                lessonRepository.deleteMark(lessonId, id);
                        }
                    }
                    if(!isMatch && s.split(" ").length > 1 && !modifiedStudentsId.contains(id))
                        lessonRepository.createMark(lessonId, id, Integer.parseInt(s.split(" ")[1]));
                }
            }

            return showEditableLesson(lessonId, type);
        }

        else {
            lessonRepository.deleteLesson(lessonId);
            ModelAndView model = showLessons(institutionId, type);

            if(type.equals(CollegeFacultyGroup.FACULTY))
                model.setViewName("redirect:/colleges/{collegeId}/faculties/{facultyId}/lessons");
            else if(type.equals(CollegeFacultyGroup.GROUP))
                model.setViewName("redirect:/colleges/{collegeId}/faculties/{facultyId}/groups/{groupId}/lessons");

            return model;
        }
    }

    public ModelAndView lessonsForDate(String date, Number typeId, CollegeFacultyGroup type){

        ModelAndView model = new ModelAndView();

        switch(type) {
            case COLLEGE:
                model.setViewName("college/collegeLessons");
                break;
            case FACULTY:
                model.setViewName("faculty/facultyLessons");
                break;
            case GROUP:
                model.setViewName("group/groupLessons");
                break;
        }

        List<Lesson> lessons = lessonRepository.findLessonsForDate(type, typeId, date);
        if(lessons.size() == 0)
            lessons = lessonRepository.findLessons(type, typeId);

        for(Lesson l : lessons) {
            l.setStudents(lessonRepository.findStudentsOnLesson(l.getId()));
        }

        Collections.sort(lessons);
        model.addObject("lessonList", lessons);

        return model;
    }

    public ModelAndView groupStudentLessonsForDate(String date, Long groupId, Long studentId){

        ModelAndView model = new ModelAndView("group/groupStudent");
        List<Lesson> lessons = lessonRepository.findLessonsForDate(
                CollegeFacultyGroup.GROUP,
                groupId, date);

        if(lessons.size() == 0)
            lessons = lessonRepository.findLessons(CollegeFacultyGroup.GROUP,
                    groupId);

        for(Lesson l : lessons) {
            l.setStudents(lessonRepository.findStudentsOnLesson(l.getId()));
            if(l instanceof Markable) {
                ((Markable) l).setMarks(lessonRepository.showLessonMarks(l.getId()));
            }
        }
        Collections.sort(lessons);
        CollegeStudent student = (CollegeStudent) studentRepository.findStudentById(studentId);

        model.addObject("lessons", lessons);
        model.addObject("student", student);

        return model;
    }

    public ModelAndView newLessonFromCollege(Integer collegeId, boolean redirect){

        ModelAndView model = new ModelAndView();

        if(redirect) {
            model.setViewName("college/collegeLessonForm");
        }
        else {
            model = new ModelAndView("college/collegeLessonForm", "lesson", new LessonValidator());
        }

        Map<Long, String> facultiesMap = facultyRepository.getFacultiesMap(collegeId);
        Map<Long, String> teachersMap = teacherRepository.getTeachersMap(collegeId);
        Map<Long, String> groupsMap = groupRepository.getCollegeGroupsMap(collegeId);
        Map<Integer, String> subjectsMap = subjectRepository.getsubjectsMap(collegeId);

        model.addObject("faculties", facultiesMap);
        model.addObject("teachers", teachersMap);
        model.addObject("groups", groupsMap);
        model.addObject("subjects", subjectsMap);
        model.addObject("duration", Lesson.getLessonDuration());

        return model;
    }

    public ModelAndView newLessonFromFaculty(Integer collegeId,
                                             Long facultyId, boolean redirect){

        ModelAndView model = new ModelAndView();

        if(redirect) {
            model.setViewName("faculty/facultyLessonForm");
        }
        else {
            model = new ModelAndView("faculty/facultyLessonForm", "lesson", new LessonValidator());
        }

        EducationSpecialization faculty = facultyRepository.findFacultyById(facultyId);
        List<Subject> subjects = subjectRepository.getFacultySubjects(facultyId);
        List<Teacher> teachers = teacherRepository.getCollegeTeachers(collegeId);
        List<EducationGroup> groups = groupRepository.findAllFacultyGroups(facultyId);

        Map<Long, String> teachersMap = new HashMap<>();
        Map<String, String> groupsMap = new HashMap<>();
        Map<String, String> subjectsMap = new HashMap<>();

        for (Teacher t : teachers)
            teachersMap.put(t.getId(), t.getFullName());

        for (EducationGroup g : groups)
            groupsMap.put(g.getName(), g.getName());

        for (Subject s : subjects)
            subjectsMap.put(s.getName(), s.getName());

        faculty.setSubjects(subjects);
        faculty.setTeachers(teachers);
        faculty.setGroups(groups);

        model.addObject("faculty", faculty);
        model.addObject("subjects", subjectsMap);
        model.addObject("groups", groupsMap);
        model.addObject("teachers", teachersMap);
        model.addObject("duration", Lesson.getLessonDuration());

        return model;
    }

    public ModelAndView newLessonFromGroup(Integer collegeId, Long facultyId,
                                           boolean redirect){

        ModelAndView model;

        if(redirect) {
            model = newLessonFromFaculty(collegeId, facultyId, true);
        }
        else {
            model = newLessonFromFaculty(collegeId, facultyId, false);
        }

        model.setViewName("group/groupLessonForm");

        return model;
    }

    public ModelAndView saveLessonFromCollege(Integer collegeId,
                                              int type,
                                              String[] groupNames,
                                              LessonValidator lesson,
                                              CollegeFacultyGroup institutionType,
                                              BindingResult result){

        if(result.hasErrors()) {
             return newLessonFromCollege(collegeId, true);
        }

        Subject subject = subjectRepository.getSubjectForName(lesson.getSubject());

        Long lessonId = lessonRepository.getLastLesson().getId();
        for(String s : groupNames) {
            EducationGroup group = groupRepository.findGroupByName(lesson.getFaculty(), s);
            lessonRepository.saveToLessonGroup(lessonId, group.getId());
            List<Student> students = studentRepository.findAllStudentsInGroup(group.getId());
            for(Student st : students)
                lessonRepository.addStudentToLesson(lessonId, st.getId());
        }

        helper(subject, type, lesson.getTeacher(), lesson.getDate(), lesson.getStart(), lesson.getEnd());

        return showLesson(lessonId, institutionType);

    }

    public ModelAndView saveLessonFromFaculty(Integer collegeId,
                                              Long facultyId,
                                              int type,
                                              String[] groupNames,
                                              LessonValidator lesson,
                                              CollegeFacultyGroup institutionType,
                                              BindingResult result){

        if(result.hasErrors()) {
            return newLessonFromFaculty(collegeId, facultyId, true);
        }

        lesson.setFaculty(facultyId);
        Subject subject = subjectRepository.getSubjectForName(lesson.getSubject());

        helper(subject, type, lesson.getTeacher(), lesson.getDate(), lesson.getStart(), lesson.getEnd());

        Long lessonId = lessonRepository.getLastLesson().getId();

        for(String s : groupNames) {
            EducationGroup group = groupRepository.findGroupByName(facultyId, s);
            lessonRepository.saveToLessonGroup(lessonId, group.getId());
            List<Student> students = studentRepository.findAllStudentsInGroup(group.getId());
            if(students.size() > 0) {
                for (Student st : students)
                    lessonRepository.addStudentToLesson(lessonId, st.getId());
            }
        }

        return showLesson(lessonId, institutionType);
    }

    public ModelAndView saveLessonFromGroup(Integer collegeId,
                                            Long facultyId,
                                            Long groupId,
                                            int type,
                                            LessonValidator lesson,
                                            CollegeFacultyGroup institutionType,
                                            BindingResult result){

        if(result.hasErrors()) {
            return newLessonFromGroup(collegeId, facultyId, true);
        }

        lesson.setFaculty(facultyId);
        Subject subject = subjectRepository.getSubjectForName(lesson.getSubject());

        helper(subject, type, lesson.getTeacher(), lesson.getDate(),
                lesson.getStart(), lesson.getEnd());

        Long lessonId = lessonRepository.getLastLesson().getId();
        lessonRepository.saveToLessonGroup(lessonId, groupId);
        List<Student> students = studentRepository.findAllStudentsInGroup(groupId);
        for(Student st : students)
            lessonRepository.addStudentToLesson(lessonId, st.getId());

        return showLessonGroup(groupId, lessonId, institutionType);
    }

    private void helper(Subject subject, int type, Long teacherId,
                        String lessonDate, String lessonStart, String lessonEnd) {

        Lesson lesson = LessonFactory.createLesson(type);
        Teacher teacher = teacherRepository.getTeacherForId(teacherId);
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        Date date = new Date(), start = date, end = date;

        try {
            date = dateTimeFormat.parse(lessonDate);
            start = timeFormat.parse(lessonStart);
            end = timeFormat.parse(lessonEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        lesson.setDate(date);
        lesson.setStart(start);
        lesson.setEnd(end);
        lesson.setSubject(subject);
        lesson.setTeacher(teacher);


        lessonRepository.saveToLesson(lesson, type);

    }
}
