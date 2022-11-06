import java.util.Objects;
import java.util.Scanner;
/**
 * Created by Thien Tien
 * Date 11/5/2022 - 12:34 AM
 * Description: The program will run from here
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        StudentManagementSystem sys = new StudentManagementSystem(new StudentList());
        sys.initData();
        do {
            System.out.println("\nMENU");
            System.out.println("1. Add student");
            System.out.println("2. Update student information");
            System.out.println("3. Delete a student");
            System.out.println("4. View the student list");
            System.out.println("5. Save student list into file (default.csv)");
            System.out.println("6. Import/Export student list from/to csv file");
            System.out.println("7. Exit the program");
            System.out.println("---------------------------------------------");
            System.out.print("Enter your choice: ");

            String line = scanner.nextLine();
            if (Objects.equals(line, "")) {
                continue;
            }

            int choice = Integer.parseInt(line);
            switch (choice) {
                case 1 -> {
                    sys.addStudent();
                    System.out.println("Press Enter to continue.....");
                    scanner.nextLine();
                }
                case 2 -> {
                    sys.updateStudentInfor();
                    System.out.println("Press Enter to continue....");
                    scanner.nextLine();
                }
                case 3 -> {
                    sys.deleteStudent();
                    System.out.println("Press Enter to continue.....");
                    scanner.nextLine();
                }
                case 4 -> {
                    sys.printList();
                    System.out.println("Press Enter to continue.....");
                    scanner.nextLine();
                }
                case 5 -> sys.saveDefault();
                case 6 -> sys.importExportStudent();
                case 7 -> {
                    System.out.println("Thank you for using the program.");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Try again!");
            }
        } while(true);
    }


}