import java.util.Scanner;
import java.io.IOException;
/**
 * Created by ${USER}
 * Date ${DATE} - ${TIME}
 * Description: ...
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        StudentManagementSystem sys = new StudentManagementSystem(new StudentList());
        sys.initData();
        do {
            System.out.println("MENU");
            System.out.println("1. Add student");
            System.out.println("2. Update student information");
            System.out.println("3. Delete a student");
            System.out.println("4. View the student list");
            System.out.println("5. Save student list into file(s)");
            System.out.println("6. Import/Export student list from/to csv file");
            System.out.println("7. Exit the program");
            System.out.println("---------------------------------------------");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    sys.addStudent();
                    System.out.println("Press Enter to continue.....");
                    scanner.nextLine();
                    break;

                case 2:
                    sys.updateStudentInfor();
                    System.out.println("Press Enter to continue....");
                    scanner.nextLine();
                    break;

                case 3:
                    sys.deleteStudent();
                    System.out.println("Press Enter to continue.....");
                    scanner.nextLine();
                    break;

                case 4:
                    sys.printList();
                    System.out.println("Press Enter to continue.....");
                    scanner.nextLine();
                    break;

                case 5:
                    break;

                case 6:
                    sys.importExportStudent();
                    System.out.println("Press Enter to continue.....");
                    scanner.nextLine();
                    break;

                case 7:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Try again!");

            }
        } while(running);
    }


}