/*
 * Name: Risab Sankar
 * Email: rsankar@ucsd.edu
 * PID: A17383972
 * Sources used: None
 * 
 * This file contains testers to test the MyListIterator class, 
 * particularly to see whether the methods throw exceptions accordingly
 * based on the inputs and the previous functions called. This file
 * also tests the edge cases at the ends of the lists.
 */

// DO NOT CHANGE THE METHOD NAMES

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.*;

public class MyListIteratorCustomTester {

    MyLinkedList list1;
    MyLinkedList.MyListIterator list1Iter;

    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    @Before
    public void setUp() throws Exception {
        list1 = new MyLinkedList();
        list1.add("Twelve");
        list1Iter = list1.new MyListIterator();
    }

    /**
     * Aims to test the next() method when iterator is at end of the list
     */
    @Test(expected = NoSuchElementException.class)
    public void testNextEnd() {
        list1Iter.next();
        list1Iter.next();
    }

    /**
     * Aims to test the previous() method when iterator is at the start of the
     * list
     */
    @Test(expected = NoSuchElementException.class)
    public void testPreviousStart() {
        list1Iter.previous();
    }

    /**
     * Aims to test the add(E e) method when an invalid element is added
     */
    @Test(expected = NullPointerException.class)
    public void testAddInvalid() {
        list1Iter.add(null);
    }

    /**
     * Aims to test the set(E e) method when canRemoveOrSet is false
     */
    @Test(expected = IllegalStateException.class)
    public void testCantSet() {
        list1Iter.add("Four");
        list1Iter.set("Fourteen");
    }

    /**
     * Aims to test the set(E e) method when an invalid element is set
     */
    @Test(expected = NullPointerException.class)
    public void testSetInvalid() {
        list1Iter.set(null);
    }

    /**
     * Aims to test the remove() method when canRemoveOrSet is false
     */
    @Test(expected = IllegalStateException.class)
    public void testCantRemove() {
        list1Iter.add("Four");
        list1Iter.remove();
    }

    /**
     * Aims to tests the hasNext() method at the end of a list
     */
    @Test
    public void testHasNextEnd() {
        list1Iter.next();
        assertEquals(false, list1Iter.hasNext());
    }

    /**
     * Aims to test the hasPrevious() method at the start of a list
     */
    @Test
    public void testHasPreviousStart() {
        assertEquals(false, list1Iter.hasPrevious());
    }

    /**
     * Aims to test the previousIndex() method at the start of a list
     */
    @Test
    public void testPreviousIndexStart() {
        assertEquals(-1, list1Iter.previousIndex());
    }

    /**
     * Aims to test the nextIndex() method at the end of a list
     */
    @Test
    public void testNextIndexEnd() {
        list1Iter.next();
        assertEquals(1, list1Iter.nextIndex());
    }
}
