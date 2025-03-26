package Lesson_13;

import java.util.*;

public class Student {
    private final String name;
    private final int group;
    private int course;
    private final Map<String, Integer> grades;

    public Student(String name, int group, int course, Map<String, Integer> grades) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = new HashMap<>(grades);
    }

    public double getAverageGrade() {
        return grades.values().stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }

    public void promoteToNextCourse() {
        this.course++;
    }

    // Геттеры
    public String getName() { return name; }
    public int getGroup() { return group; }
    public int getCourse() { return course; }
    public Map<String, Integer> getGrades() { return new HashMap<>(grades); }


    @Override
    public String toString() {
        return name + " (Группа: " + group + ", Курс: " + course + ")";
    }
}