package com.aui.entities;

public class Grade {

    public Student student;
    public Course course;
    public String grade;
    public int taken;

    public Grade(Student student, Course course, String grade, int taken) {
        this.student = student;
        this.course = course;
        this.grade = grade;
        this.taken = taken;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "student=" + student +
                ", course=" + course +
                ", grade='" + grade + '\'' +
                ", taken=" + taken +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grade grade1 = (Grade) o;

        if (taken != grade1.taken) return false;
        if (!student.equals(grade1.student)) return false;
        if (!course.equals(grade1.course)) return false;
        return grade.equals(grade1.grade);
    }

    @Override
    public int hashCode() {
        int result = student.hashCode();
        result = 31 * result + course.hashCode();
        result = 31 * result + grade.hashCode();
        result = 31 * result + taken;
        return result;
    }
}
