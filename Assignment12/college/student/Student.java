package college.student;

public class Student {
    private int rollNumber;
    private String name;
    private String course;
    private int semester;

    public Student(int rollNumber, String name, String course, int semester) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.course = course;
        this.semester = semester;
    }

    public void displayInfo() {
        System.out.println("=== STUDENT DETAILS ===");
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Name: " + name);
        System.out.println("Course: " + course);
        System.out.println("Semester: " + semester);
        System.out.println("========================");
    }
}
