package com.aui.entities;

public class Student {

    public static final int LEVEL_GRAD = 1;
    public static final int LEVEL_UNDER = 2;
    public static final int LEVEL_BOTH = 3;

    public int id;
    public String name;
    public int level;
    public String major;
    public int quota;
    public String status;
    public int work = 0;
    public int const_quota;

    public Student(int id, String name, int level, String major, int quota, String status) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.major = major;
        this.quota = quota;
        this.status = status;
        this.const_quota = quota;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", major='" + major + '\'' +
                ", quota=" + quota +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (id != student.id) return false;
        if (level != student.level) return false;
        if (quota != student.quota) return false;
        if (!name.equals(student.name)) return false;
        if (!major.equals(student.major)) return false;
        return status.equals(student.status);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + level;
        result = 31 * result + major.hashCode();
        result = 31 * result + quota;
        result = 31 * result + status.hashCode();
        return result;
    }
}
