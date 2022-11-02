/**
 * Created by Thien Tien
 * Date 11/2/2022 - 11:31 PM
 * Description: This class store credential of student
 */


public class Student {
    // Instance variables
    private String id;
    private String name;
    private float gpa;
    private String img;
    private String address;
    private String notes;


    /**
     * Parameterized Constructor
     * @param id
     * @param name
     * @param gpa
     * @param img
     * @param address
     * @param notes
     */
    Student(String id, String name, float gpa, String img, String address, String notes) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
        this.img = img;
        this.address = address;
        this.notes = notes;
    }

    /**
     * Getter of Student ID
     * @return Student ID
     */
    public String getId() {
        return this.id;
    }

    /**
     * Getter of Student name
     * @return name of student
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter of GPA
     * @return GPA of Student
     */
    public float getGpa() {
        return gpa;
    }

    /**
     * Getter of address
     * @return address of student
     */
    public String getAddress() {
        return address;
    }

    /**
     * Getter of img
     * @return image of student
     */
    public String getImg() {
        return img;
    }

    /**
     * Getter of notes
     * @return notes of student
     */
    public String getNotes() {
        return notes;
    }
}
