/*
 * Name: Risab Sankar
 * Email: rsankar@ucsd.edu
 * PID: A17383972
 * Sources used: None
 * 
 * This file is used to test all 3 classes using custo testers
 * developed by myself.
 */

import java.util.Objects;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class contains a variety of testers to test the other classes.
 */
public class CustomTester {

    /**
     * tests method with a non Student class
     */
    @Test
    public void testStudentEquals() {
        String s = " ";
        Student stud = new Student("Student", "Student",
                "A12345678");
        assertEquals(false, stud.equals(s));

    }

    /**
     * tests method with this being lexigraphically lower in value
     * than the student object being compared to
     */
    @Test
    public void testStudentCompareTo() {
        Student s1 = new Student("Risab", "Sankar", "A11111111");
        Student s2 = new Student("Risab", "Sankar", "A22222222");
        assertEquals(-1, s1.compareTo(s2));
    }

    /**
     * tests method when student not in the course tries to drop course
     */
    @Test
    public void testCourseDrop() {
        Course course = new Course("CSE", "12", "Data Structure", 100);
        course.enrolled = new HashSet<>();
        Student stu = new Student("Whales", "Ocean", "A123");
        Student s = new Student("non", "existing", "none");
        course.enroll(stu);
        assertEquals(false, course.drop(s));
    }

    /**
     * tests enrollment when course is at capacity
     */
    @Test
    public void testCourseEnroll() {
        Course course = new Course("CSE", "12", "Data Structure", 1);
        course.enrolled = new HashSet<>();
        Student stu = new Student("Whales", "Ocean", "A123");
        course.enroll(stu);
        Student s = new Student("new", "student", "none");
        assertEquals(false, course.enroll(s));
        assertEquals(false, course.enrolled.contains(s));
        assertEquals(true, course.enrolled.contains(stu));
    }

    /**
     * tests the get roster method with a full roster
     */
    @Test
    public void testCourseGetRoster() {
        Course course = new Course("CSE", "12", "Data Structure", 100);
        course.enrolled = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            Student s = new Student("Student", "Name", "" + i);
            course.enroll(s);
        }
        ArrayList<Student> arr = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Student s = new Student("Student", "Name", "" + i);
            arr.add(s);
        }
        ArrayList<Student> newArr = course.getRoster();
        Collections.sort(arr);
        for (int i = 0; i < arr.size(); i++) {
            assertEquals(arr.get(i), newArr.get(i));
        }

    }

    /**
     * tests whether error is thrown when maxAnimals is negative
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSanctConstructor() {
        Sanctuary s = new Sanctuary(-2, 1);
    }

    /**
     * tests when more animals are rescued than maxAnimals
     */
    @Test
    public void testSanctRescuePartial() {
        Sanctuary s = new Sanctuary(100, 10);
        s.sanctuary.put("Koala", 75);
        assertEquals(25, s.rescue("Panda", 50));
        assertEquals(25, s.countForSpecies("Panda"));
        assertEquals(2, s.getTotalSpecies());
    }

    /**
     * tests rescue when maxSpecies is exceeded
     */
    @Test
    public void testSanctRescueMaxSpecies() {
        Sanctuary s = new Sanctuary(5, 1);
        s.sanctuary.put("Koala", 2);
        assertEquals(2, s.rescue("Panda", 2));
        assertEquals(false, s.sanctuary.containsKey("Panda"));
        assertEquals(true, s.sanctuary.containsKey("Koala"));
    }

    /**
     * tests when only some of the animals of a species are released
     */
    @Test
    public void testSanctReleasePartial() {
        Sanctuary s = new Sanctuary(100, 10);
        s.sanctuary.put("Koala", 50);
        s.release("Koala", 20);
        assertEquals(30, s.countForSpecies("Koala"));
        assertEquals(30, s.getTotalAnimals());
    }

    /**
     * tests when more animals are released than in the sanctuary
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSanctReleaseTooMany() {
        Sanctuary s = new Sanctuary(100, 10);
        s.sanctuary.put("Koala", 50);
        s.release("Koala", 80);
    }
}
