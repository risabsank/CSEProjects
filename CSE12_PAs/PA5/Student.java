/*
 * Name: Risab Sankar
 * Email: rsankar@ucsd.edu
 * PID: A17383972
 * Sources used: None
 * 
 * This file is used to implement the Student class. The file includes 
 * methods to get information about a student and to compare students.
 */

import java.util.Objects;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class stores information about a student and methods to
 * compare students based on the listed information.
 * 
 * Instance variables:
 * firstName - first name of the Student
 * lastName - last name of the Student
 * PID - the PID of the Student
 */
public class Student implements Comparable<Student> {
    private final String firstName;
    private final String lastName;
    private final String PID;

    /**
     * initializes a Student object
     * 
     * @param firstName first name of student
     * @param lastName  last name of student
     * @param PID       PID of student
     */
    public Student(String firstName, String lastName, String PID) {
        if (firstName == null || lastName == null || PID == null) {
            throw new IllegalArgumentException();
        } else {
            this.firstName = firstName;
            this.lastName = lastName;
            this.PID = PID;
        }
    }

    /**
     * gets first name of student
     * 
     * @return first name of student
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * gets last name of student
     * 
     * @return last name of student
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * gets PID of student
     * 
     * @return PID of student
     */
    public String getPID() {
        return this.PID;
    }

    /**
     * whether 2 students are equal or not
     * 
     * @param o object being compared to student
     * @return true if equal, false if not
     */
    public boolean equals(Object o) {
        // checks if object is null
        if (o == null) {
            return false;
        }
        // checks if object is a Student object
        if (!(o instanceof Student)) {
            return false;
        }
        // checks if each instance variable is equal
        if (!((((Student) o).getFirstName()).equals(this.firstName) &&
                (((Student) o).getLastName()).equals(this.lastName) &&
                (((Student) o).getPID()).equals(this.PID))) {
            return false;
        }
        return true;
    }

    /**
     * gets hashcode of the Student variables
     * 
     * @return the hashcode of the Student
     */
    public int hashCode() {
        return Objects.hash(this.firstName, this.lastName, this.PID);
    }

    /**
     * the difference between 2 students
     * 
     * @param o student being compared to this
     * @return difference between the 2 student objects
     */
    public int compareTo(Student o) {
        if (o == null) {
            throw new NullPointerException();
        }
        // checks difference between lastNames
        int diff = o.lastName.compareTo(this.lastName);
        if (diff == 0) {
            // checks difference between first name
            diff = o.firstName.compareTo(this.firstName);
            if (diff == 0) {
                // checks difference between last name
                diff = o.PID.compareTo(this.PID);
            }
        }
        // checks to see which is lexigraphically
        // behind the other student object
        if (diff > 0) {
            return -1;
        } else if (diff < 0) {
            return 1;
        } else {
            return 0;
        }
    }

}
