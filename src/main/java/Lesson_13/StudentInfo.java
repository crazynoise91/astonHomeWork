package Lesson_13;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StudentInfo {
    public static void removeUnderperformingStudents(Set<Student> students) {
        students.removeIf(student -> student.getAverageGrade() < 3.0);
    }

    public static void promoteEligibleStudents(Set<Student> students) {
        students.stream()
                .filter(student -> student.getAverageGrade() >= 3.0)
                .forEach(Student::promoteToNextCourse);
    }

    public static void printStudentsByCourse(Set<Student> students, int course) {
        System.out.println("\nСтуденты " + course + " курса:");
        students.stream()
                .filter(student -> student.getCourse() == course)
                .forEach(System.out::println);
    }

    public static void demo() {
        Set<Student> students = new HashSet<>();

        students.add(new Student("Михаил Ломоносов", 1711, 1,
                Map.of("Химия", 2, "Физика", 3, "Астрономия", 2)));

        students.add(new Student("Дмитрий Менделеев", 1834, 2,
                Map.of("Химия", 5, "Физика", 3, "Экономика", 5)));

        students.add(new Student("Константин Циолковский", 1857, 2,
                Map.of("Математика", 3, "Физика", 5, "История", 4)));

        students.add(new Student("Лев Ландау", 1908, 3,
                Map.of("Математика", 4, "Физика", 5, "Астрономия", 3)));


        System.out.println("\nИсходный список студентов:");
        students.forEach(System.out::println);

        removeUnderperformingStudents(students);
        System.out.println("\nКроме студентов с баллом < 3:");
        students.forEach(System.out::println);

        promoteEligibleStudents(students);
        System.out.println("\nПереведены на следующий курс:");
        students.forEach(System.out::println);

        printStudentsByCourse(students, 3);
    }
}
