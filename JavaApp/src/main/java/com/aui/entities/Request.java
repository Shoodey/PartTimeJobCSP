package com.aui.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Request {

    public static final int PURPOSE_GRADING = 1;
    public static final int PURPOSE_RESEARCH = 2;
    public static final int PURPOSE_BOTH = 3;

    public Faculty faculty;
    public int purpose;
    public Set<Student> students = new HashSet<Student>();
    public double quota;

    public Request(Faculty faculty, int purpose, Set<Student> students, double quota) {
        this.faculty = faculty;
        this.purpose = purpose;
        this.students = students;
        this.quota = quota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (purpose != request.purpose) return false;
        if (Double.compare(request.quota, quota) != 0) return false;
        if (!faculty.equals(request.faculty)) return false;
        return students.equals(request.students);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = faculty.hashCode();
        result = 31 * result + purpose;
        result = 31 * result + students.hashCode();
        temp = Double.doubleToLongBits(quota);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Request{" +
                "faculty=" + faculty +
                ", purpose=" + purpose +
                ", students=" + students +
                ", quota=" + quota +
                '}';
    }
}
