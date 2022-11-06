import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
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

    public void initData() {
        File f = new File("data/default.csv");
        if (f.exists() && !f.isDirectory()) {
            System.out.println("Is valid");
            importList("default.csv");
        }
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
        System.out.println("Use mode of viewing list");
        System.out.println("1. With the student ID in ascending order");
        System.out.println("2. With the GPA in ascending order");

        Scanner scanner = new Scanner(System.in);
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) {
            list.sortLinkedListById();
        } else if (choice == 2) {
            list.sortLinkedListByGpa();
        } else {
            System.out.println("Invalid choice...");
            return;
        }

        LinkedList<Student> studentList = list.getStudentList();
        String leftAlignFormat = "|  %-9s | %-27s  | %-5.2f | %-5s | %-32s | %-32s |%n";
        String nextLineFormat = "|  %-9s | %-27s  | %-5s | %-5s | %-32s | %-32s |%n";

        System.out.format("+------------+------------------------------+-------+-------+----------------------------------+----------------------------------+%n");
        System.out.format("| Student ID |              Name            |  GPA  | Image |              Address             |               Notes              |%n");
        System.out.format("+------------+------------------------------+-------+-------+----------------------------------+----------------------------------+%n");

        for (Student s : studentList) {

            String addr = s.getAddress();
            if (addr.length() > 30) {
                int commaIndex = addr.indexOf(",");
                String substr1 =  addr.substring(0, commaIndex + 1);
                String substr2 = addr.substring(commaIndex + 1);
                System.out.format(leftAlignFormat, s.getId(), s.getName(), s.getGpa(), s.getImg(),
                        substr1, s.getNotes());
                System.out.format(nextLineFormat, "", "", "", "", substr2, "");
            }
            else {
                System.out.format(leftAlignFormat, s.getId(), s.getName(), s.getGpa(), s.getImg(),
                        s.getAddress(), s.getNotes());
            }
            System.out.format("+------------+------------------------------+-------+-------+----------------------------------+----------------------------------+%n");

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
            Scanner scanner = new Scanner(System.in);
            System.out.println("IMPORT THE STUDENT LIST");
            System.out.print("Enter filename: ");
            String dir = "data/";
            fileName = fileName.length() > 0 ? fileName : scanner.nextLine();
            BufferedReader reader = new BufferedReader(new FileReader(dir + fileName));
            reader.readLine();
            String line = "";

            while((line = reader.readLine()) != null) {
                String[] studentInfo = line.split(",");

                if (studentInfo.length > 5) {
                    for (int i = 0; i < studentInfo.length; i++) {
                         if (studentInfo[i].length() > 0 && studentInfo[i].charAt(0) == '"') {
                            for (int j = i + 1; j < studentInfo.length; j++) {
                                studentInfo[i] = studentInfo[i] + "," + studentInfo[j];
                                if (studentInfo[j].charAt(studentInfo[j].length() - 1) == '"') {
                                    int distance = j - i;
                                    if (j == studentInfo.length - 1) {
                                        for (int k = i + 1; k < studentInfo.length; k++) {
                                            studentInfo[k] = "";
                                        }
                                    }
                                    else {
                                        for (int k = j + 1; k < studentInfo.length; k++) {
                                            studentInfo[k - distance] = studentInfo[k];
                                        }
                                    }
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
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFolder(String fileName) { // 22212000
        try {
            PrintWriter writer = new PrintWriter(new File(fileName));
            writer.write("...123");

            writer.println("456789");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportStudentList() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String fileName = scanner.nextLine();
        try {
            PrintWriter writer = new PrintWriter(fileName);

            writer.write("student id,student name,gpa,image,address,notes\n");
            LinkedList<Student> stuList = list.getStudentList();
            for (Student s : stuList) {
                writer.write(s.getId() + ",");
                writer.write(s.getName() + ",");
                writer.write(s.getGpa() + ",");
                writer.write(s.getImg() + ",");
                String address = s.getAddress();
                if (address.indexOf(',') > 0 && address.indexOf('"') == -1) {
                    address = '"' + address + '"';
                }
                writer.write(address + ",");
                String notes = s.getNotes();
                if (notes.indexOf(',') > 0 && notes.indexOf('"') == -1) {
                    notes = '"' + notes + '"';
                }
                writer.write(notes + "\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateStudentInfor() {
        Scanner scanner = new Scanner(System.in);

        String id = scanner.nextLine();

        Student st = list.getStudentById(id);

        if (st == null) {
            System.out.println("Can't find entered student ID in the system");
        }
        else {
            System.out.println("Student found! Information: ");
            System.out.println("ID: " + st.getId());
            System.out.println("Name: " + st.getName());
            System.out.println("GPA: " + st.getGpa());
            System.out.println("Address: " + st.getAddress());
            System.out.println("Notes: " + st.getNotes());

            System.out.println("-------------------------------------------");
            System.out.println("Enter type of information need to change");
            System.out.println("1. Student ID");
            System.out.println("2. Name");
            System.out.println("3. GPA");
            System.out.println("4. Image");
            System.out.println("5. Address");
            System.out.println("6. Notes");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.print("New Student ID: ");
                    String newId = scanner.nextLine();
                    if (list.getStudentById(newId) == null) {
                        System.out.println("Student ID is successfully updated");
                        st.setId(newId);
                    } else {
                        System.out.println("Failed to update! Entered ID has been already in the system.");
                    }
                }
                case 2 -> {
                    System.out.print("New name: ");
                    String newName = scanner.nextLine();
                    st.setName(newName);
                    System.out.println("Student's name is successfully updated");
                }
                case 3 -> {
                    System.out.print("New GPA: ");
                    float newGpa = Float.parseFloat(scanner.nextLine());
                    st.setGpa(newGpa);
                    System.out.println("Student's GPA is successfully updated");
                }
                case 4 -> {
                    System.out.print("New image: ");
                    String newImg = scanner.nextLine();
                    st.setImg(newImg);
                    System.out.println("Student's image is successfully updated");
                }
                case 5 -> {
                    System.out.print("New Address: ");
                    String newAddr = scanner.nextLine();
                    st.setImg(newAddr);
                    System.out.println("Student's address is successfully updated");
                }
                case 6 -> {
                    System.out.print("New notes: ");
                    String newNotes = scanner.nextLine();
                    st.setNotes(newNotes);
                    System.out.println("Student's notes is successfully updated");
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    public void importExportStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------------------");
        System.out.println("1. Import student from csv file");
        System.out.println("2. Export student to csv file");

        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) {
            importList("");
        } else if (choice == 2) {
            exportStudentList();
        } else {
            System.out.println("Invalid choice...");
        }
    }
}
