import java.util.LinkedList;
import java.util.Scanner;
import java.io.*;
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

    public void importList(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            reader.readLine();
            String line = "";

            while((line = reader.readLine()) != null) {
                String[] studentInfo = line.split(",");

                if (studentInfo.length > 5) {
                    for (int i = 0; i < studentInfo.length; i++) {
                        System.out.println(studentInfo[i]);
                        if (studentInfo[i].length() != 0 && studentInfo[i].charAt(0) == '"') {
                            System.out.println("I'm at " + i);
                            for (int j = i + 1; j < studentInfo.length; j++) {
                                System.out.println("Before: " + studentInfo[i]);
                                studentInfo[i] = studentInfo[i] + "," + studentInfo[j];
                                System.out.println("After: " + studentInfo[i]);
                                if (studentInfo[j].charAt(studentInfo[j].length() - 1) == '"') {
                                    for (int k = i + 1; k < studentInfo.length - 1; k++) {
                                        studentInfo[k] = studentInfo[k + 1];
                                    }
                                    i = j;
                                    for (int s = 0; s < studentInfo.length; s++) {
                                        System.out.println(s + " - " + studentInfo[s]);
                                    }
                                    System.out.println("I'm end " + j);
                                    break;
                                }
                            }
                        }
                    }
                }

                String id = studentInfo[0];
                String name = studentInfo[1];
                float gpa = Float.parseFloat(studentInfo[2]);
                String img = studentInfo[3];
                String address = studentInfo[4];
                String notes = studentInfo.length > 5 ? studentInfo[5] : "";

                Student s = new Student(id, name, gpa, img, address, notes);
                list.addStudent(s);
                System.out.println(id + " | " + name + " | " + address + " | " + notes);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFolder(String fileName) {
        try {
            PrintWriter writer = new PrintWriter(new File(fileName));
            writer.write("...123");

            writer.println("456789");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
