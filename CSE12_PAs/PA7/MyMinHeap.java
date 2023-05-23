/*
   Name: Risab Sankar
   Email: rsankar@ucsd.edu
   PID: A17383972
   Sources used: None
  
   This file is used to implement the MyMinHeap class by implementing
   the MinHeapInterface. It imports the ArrayList library to implement
   the logic of the minHeap.
 */

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class stores an ArrayList and has helper methods to help
 * implements its core methods that are used to modify the heap
 * to meet the heap rules accordingly. It implements comparable, so that
 * elements in the heap can be compared.
 * 
 * Instance variables:
 * data - the data of the minHeap
 */
public class MyMinHeap<E extends Comparable<E>> implements MinHeapInterface<E> {
    protected ArrayList<E> data;

    /**
     * initilizes an MyMinHeap object
     */
    public MyMinHeap() {
        this.data = new ArrayList<>();
    }

    /**
     * initializes a MyMinHeap object using a collection
     * 
     * @param collection collection being used to initialize the heap
     */
    public MyMinHeap(Collection<? extends E> collection) {
        if (collection == null) {
            throw new NullPointerException();
        } else {
            this.data = new ArrayList<>(collection);
            for (int i = data.size() - 1; i >= 0; i--) {
                percolateDown(i);
            }
        }
    }

    /**
     * swaps 2 elements in the array
     * 
     * @param from one index that is being swapped
     * @param to   other index being swapped
     */
    protected void swap(int from, int to) {
        E old = data.get(from);
        data.set(from, data.get(to));
        data.set(to, old);
    }

    /**
     * gets the parent index of an index in the array
     * 
     * @param index child index
     * @return the parent index of the parameter index
     */
    protected static int getParentIdx(int index) {
        if (index % 2 == 0) {
            return (index - 1) / 2;
        } else {
            return index / 2;
        }
    }

    /**
     * returns left child of parameter index
     * 
     * @param index parent index
     * @return left child index
     */
    protected static int getLeftChildIdx(int index) {
        return index * 2 + 1;
    }

    /**
     * returns right child of parameter index
     * 
     * @param index parent index
     * @return right child index
     */
    protected static int getRightChildIdx(int index) {
        return index * 2 + 2;
    }

    /**
     * gets the minimum child index of the parent index
     * 
     * @param index parent index
     * @return minimum child index
     */
    protected int getMinChildIdx(int index) {
        int rightIndex = getRightChildIdx(index);
        int leftIndex = getLeftChildIdx(index);
        if (leftIndex >= this.data.size()) {
            return -1;
        } else if (leftIndex < this.data.size() &&
                rightIndex >= this.data.size()) {
            return leftIndex;
        } else {
            E leftElement = this.data.get(leftIndex);
            E rightElement = this.data.get(rightIndex);
            // checks if right element is less, else left is returned
            if (rightElement.compareTo(leftElement) < 0) {
                return rightIndex;
            } else {
                return leftIndex;
            }
        }
    }

    /**
     * percolates an element up to meet heap rules
     * 
     * @param index index of element being percolated up
     */
    protected void percolateUp(int index) {
        int parentIdx = getParentIdx(index);
        // checks if less than parent, then swaps if necessary
        // changes to then look at the next parent index
        while ((data.get(index)).compareTo(data.get(parentIdx)) < 0) {
            swap(index, parentIdx);
            index = parentIdx;
            parentIdx = getParentIdx(parentIdx);
        }
    }

    /**
     * percolates an element down to meet heap rules
     * 
     * @param index index of element being percolated down
     */
    protected void percolateDown(int index) {
        int childIdx = getMinChildIdx(index);
        // checks if index is a leaf
        if (childIdx != -1) {
            // checks if greater than parent, then swaps if necessary
            // changes to then look at the next minimum child index
            while ((data.get(index)).compareTo(data.get(childIdx)) >= 0) {
                swap(index, childIdx);
                index = childIdx;
                if (getMinChildIdx(childIdx) == -1) {
                    break;
                } else {
                    childIdx = getMinChildIdx(childIdx);
                }
            }
        }
    }

    /**
     * deletes index from the heap and adjusts heap
     * 
     * @param index index being deleted
     * @return the original element at the index
     */
    protected E deleteIndex(int index) {
        E returnValue = this.data.get(index);
        if (index == data.size() - 1) {
            this.data.remove(this.size() - 1);
            return returnValue;
        } else {
            this.data.set(index, data.get(data.size() - 1));
            this.data.remove(this.size() - 1);
            for (int i = data.size() - 1; i >= 0; i--) {
                percolateDown(i);
            }
            return returnValue;
        }
    }

    /**
     * inserts element at end of heap and percolates it up
     * 
     * @param element element being added to the heap
     */
    public void insert(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        this.data.add(element);
        percolateUp(data.size() - 1);
    }

    /**
     * gets the minimum element in the heap
     * 
     * @return the root element if there is a root
     */
    public E getMin() {
        if (this.data.size() == 0) {
            return null;
        } else {
            return data.get(0);
        }
    }

    /**
     * removes the root element of the heap
     * 
     * @return the previous root element
     */
    public E remove() {
        if (this.data.size() == 0) {
            return null;
        } else {
            return deleteIndex(0);
        }
    }

    /**
     * the size of the heap
     * 
     * @return the size of the data array
     */
    public int size() {
        return this.data.size();
    }

    /**
     * clears the heap
     */
    public void clear() {
        this.data.clear();
    }
}
