/*
 * Name: Risab Sankar
 * Email: rsankar@ucsd.edu
 * PID: A17383972
 * Sources used: None
 * 
 * This file is used to implement the Course class. It includes methods
 * to acquire information about a class, enroll and remove students 
 * from classes, and get the roster of a class.
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
 * This class stores information about courses as well as methods
 * to add and remove students from these courses.
 * 
 * Instance variables:
 * enrolled - list of students enrolled in the class
 * capacity - max students allowed in class
 * department - department of class
 * number - class number
 * description - subject matter of class
 */
public class Course {
    HashSet<Student> enrolled;
    private final int capacity;
    private final String department;
    private final String number;
    private final String description;

    /**
     * initializes a Course object
     * 
     * @param department  the department of the course
     * @param number      the number of the course
     * @param description the description of the course
     * @param capacity    the capacity of the course
     */
    public Course(String department, String number,
            String description, int capacity) {
        if (department == null || number == null || description == null ||
                capacity <= 0) {
            throw new IllegalArgumentException();
        } else {
            this.department = department;
            this.number = number;
            this.description = description;
            this.capacity = capacity;
            this.enrolled = new HashSet<>();
        }
    }

    /**
     * gets the department of the course
     * 
     * @return the department of the course
     */
    public String getDepartment() {
        return this.department;
    }

    /**
     * gets the course number
     * 
     * @return course number
     */
    public String getNumber() {
        return this.number;
    }

    /**
     * gets course description
     * 
     * @return course description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * gets capacity of the course
     * 
     * @return capacity
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * enrolls student in course
     * 
     * @param student student being enrolled
     * @return true if enrolled, false if not
     */
    public boolean enroll(Student student) {
        if (student == null) {
            throw new IllegalArgumentException();
        } else {
            boolean ret = true;
            // checks if student is already in the course
            boolean isIn = enrolled.contains(student);
            if (isIn) {
                ret = false;
            }
            // checks if course is at capacity
            if (enrolled.size() >= capacity) {
                ret = false;
            }
            // if both cases pass, student gets enrolled
            if (ret) {
                enrolled.add(student);
            }
            return ret;
        }
    }

    /**
     * drops student from a class
     * 
     * @param student the student being dropped
     * @return true if student is dropped, false if not
     */
    public boolean drop(Student student) {
        if (student == null) {
            throw new IllegalArgumentException();
        } else {
            // drops from course if student is in course
            boolean isIn = enrolled.contains(student);
            if (isIn) {
                enrolled.remove(student);
            }
            return isIn;
        }
    }

    /**
     * clears the hashset if the class is canceled
     */
    public void cancel() {
        enrolled.clear();
    }

    /**
     * checks if course is full
     * 
     * @return true if full, false if not
     */
    public boolean isFull() {
        if (enrolled.size() == capacity) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * checks how many students have enrolled
     * 
     * @return gets number of students in course
     */
    public int getEnrolledCount() {
        return enrolled.size();
    }

    /**
     * checks seat left in course
     * 
     * @return total seats remaining in course
     */
    public int getAvailableSeats() {
        return capacity - enrolled.size();
    }

    /**
     * returns shallow copy of enrolled list
     * 
     * @return shallow copy of enrolled list
     */
    public HashSet<Student> getStudents() {
        return ((HashSet<Student>) enrolled.clone());
    }

    /**
     * sorts enrolled list into a roster
     * 
     * @return a sorted arrayList
     */
    public ArrayList<Student> getRoster() {
        ArrayList<Student> arr = new ArrayList<>();
        // copies hashset elements into an arraylist to sort it
        for (Student student : enrolled) {
            arr.add(student);
        }
        Collections.sort(arr);
        return arr;
    }

    /**
     * prints our the course information as a String
     * 
     * @return String description of course
     */
    public String toString() {
        return this.department + " " + this.number + " " +
                "[" + this.capacity + "] " + this.description;
    }

}
