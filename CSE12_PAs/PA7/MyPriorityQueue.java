/*
   Name: Risab Sankar
   Email: rsankar@ucsd.edu
   PID: A17383972
   Sources used: None
  
   This file is used to implement the MypPriorityQueue class by implementing
   the object from the MyMinHeap class. It additionally uses methods from 
   the MyMinHeap class to implement a majority of the methods in the
   class.
 */

import java.util.Collection;

/**
 * This class stores a MyMinHeap object and has its core methods that
 * are used to modify the heap to sort the priority queue.
 * It implements comparable, so that elements in the queue can be compared.
 * 
 * Instance variables:
 * heap - the heap storing the data of the queue
 */
public class MyPriorityQueue<E extends Comparable<E>> {
    protected MyMinHeap<E> heap;

    /**
     * initializes thte priority queue
     */
    public MyPriorityQueue() {
        heap = new MyMinHeap<>();
    };

    /**
     * initializes the heap object with a collection
     * 
     * @param collection collection being used to initialize the queue
     */
    public MyPriorityQueue(Collection<? extends E> collection) {
        if (collection == null) {
            throw new NullPointerException();
        } else {
            heap = new MyMinHeap<>(collection);
        }
    };

    /**
     * inserts element to the priority queue
     * 
     * @param element element being added to the priority queue
     */
    public void push(E element) {
        if (element == null) {
            throw new NullPointerException();
        } else {
            heap.insert(element);
        }
    };

    /**
     * gets the highest priority element in the queue
     * 
     * @return the highest priority ele\ment in the queue
     */
    public E peek() {
        return heap.getMin();
    };

    /**
     * removes the highest priority element in the queue
     * 
     * @return the previous highest priority element
     */
    public E pop() {
        return heap.remove();
    };

    /**
     * the size of the priority queue
     * 
     * @return the size of the heap
     */
    public int getLength() {
        return heap.size();
    };

    /**
     * clears the priority queue
     */
    public void clear() {
        heap.clear();
    };
}