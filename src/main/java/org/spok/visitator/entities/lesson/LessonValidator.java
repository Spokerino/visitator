package org.spok.visitator.entities.lesson;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LessonValidator {

    @NotEmpty(message = "required")
    private String date;

    @NotEmpty(message = "required")
    private String start;

    @NotEmpty(message = "required")
    private String end;

    @Size(min = 1, message = "required")
    private String subject;

    @NotNull(message = "required")
    private Long teacher;

    @Min(value = 1, message = "required")
    private Long faculty;

    @Size(min = 1, message = "required")
    private String group;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start)  {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Long getTeacher() {
        return teacher;
    }

    public void setTeacher(Long teacher) {
        this.teacher = teacher;
    }

    public Long getFaculty() {
        return faculty;
    }

    public void setFaculty(Long faculty) {
        this.faculty = faculty;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
