import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Thien Tien
 * Date 11/5/2022 - 12:34 AM
 * Description: handle requirements of Student Management Program
 */

public class StudentManagementSystem {
    StudentList list;
    StudentManagementSystem(StudentList list) {
        this.list = list;
    }

    /**
     * add new Student
     */
    public void addStudent() {
        Scanner scanner = new Scanner(System.in);

        String id, name, img, address, notes;
        float gpa;

        System.out.println("Enter student's information!");
        System.out.print("Student ID: ");
        id = scanner.nextLine();

        System.out.print("Student Name: ");
        name = scanner.nextLine();

        System.out.print("Grade point average (GPA): ");
        gpa = Float.parseFloat(scanner.nextLine());

        System.out.print("Image: ");
        img = scanner.nextLine();

        System.out.print("Address: ");
        address = scanner.nextLine();

        System.out.print("Notes: ");
        notes = scanner.nextLine();

        if (list.addStudent(id, name, gpa, img, address, notes)) {
            System.out.println("Student added!");
        }
        else {
            System.out.println("Failed to adding new student.");
            System.out.println("Error: Entered student ID has already been in the list");
        }
    }

    public void printList() {
        LinkedList<Student> studentList = list.getStudentList();
        for (Student s : studentList) {
            System.out.println(s.getId());
        }
    }

    public void deleteStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID to delete: ");
        String id = scanner.nextLine();

        if (list.removeStudentById(id)) {
            System.out.println("Student was removed from the list");
        } else {
            System.out.println("Failed to remove student from the list");
            System.out.println("Error: Can't find Student ID in the list");
        }
    }
}
