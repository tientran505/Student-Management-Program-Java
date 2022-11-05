import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by Thien Tien
 * Date 11/3/2022 - 12:25 AM
 * Description: This class stores list of students by using Linkedlist
 */

public class StudentList {
    // Instance variables
    private LinkedList<Student> studentList;

    /**
     * Default Constructor
     */
    StudentList() {
        studentList = new LinkedList<Student>();
    }

    /**
     * Add student to list
     * @param s - student
     * @return true of false
     */
    public boolean addStudent(Student s) {
        String id = s.getId();
        Student checkStudent = getStudentById(id);
        if (checkStudent == null) {
            studentList.add(s);
            return true;
        }
        return false;
    }

    /**
     * Add student to list by credentials
     * @param id - Student ID
     * @param name - Student name
     * @param gpa - GPA of student
     * @param img - Image of student
     * @param address - Address of student
     * @param notes - Notes of student
     * @return true or false
     */
    public boolean addStudent(String id, String name, float gpa, String img, String address, String notes) {
        Student checkStudent = getStudentById(id);
        if (checkStudent == null) {
            studentList.add(new Student(id, name, gpa, img, address, notes));
            return true;
        }
        return false;
    }

    /**
     * Find if student is existed in list
     * @param id - Student ID
     * @return Student or null
     */
    public Student getStudentById(String id) {
        for (Student s : studentList) {
            if (id.equals(s.getId())) {
                return s;
            }
        }
        return null;
    }

    /**
     * Remove Student by ID
     * @param id - Student ID
     * @return true of false
     */
    public boolean removeStudentById(String id) {
        Student s = getStudentById(id);
        if (s == null) {
            return false;
        }
        return studentList.remove(getStudentById(id));
    }

    /**
     * Getter of student list
     * @return Student list
     */
    public LinkedList<Student> getStudentList() {
        return this.studentList;
    }

    public void doSth() {
        System.out.println(studentList.toString());
    }
    public void sortLinkedListById() {
        studentList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Integer.parseInt(o1.getId()) - Integer.parseInt(o2.getId());
            }
        });
    }

    public void sortLinkedListByGpa() {
        studentList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Float.compare(o1.getGpa(), o2.getGpa());
            }
        });
    }
}
