package college.faculty;

public class Faculty {
    private int facultyId;
    private String name;
    private String department;
    private String designation;

    public Faculty(int facultyId, String name, String department, String designation) {
        this.facultyId = facultyId;
        this.name = name;
        this.department = department;
        this.designation = designation;
    }

    public void displayInfo() {
        System.out.println("=== FACULTY DETAILS ===");
        System.out.println("Faculty ID: " + facultyId);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Designation: " + designation);
        System.out.println("========================");
    }
}
