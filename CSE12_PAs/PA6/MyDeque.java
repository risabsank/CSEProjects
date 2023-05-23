/*
   Name: Risab Sankar
   Email: rsankar@ucsd.edu
   PID: A17383972
   Sources used: None
  
   This file is used to implement the MyDeque class with methods that
   will be used in the MyStack and MyQueue classes.
 */

/**
 * This class implements the Deque ADT. It contains methods and helpers
 * that will enable it to be used in the Stack and Queue implementation
 * of the file.
 * 
 * Instance variables:
 * data - the array carrying the data
 * size - the size of the data in the deque
 * rear - the end of the deque
 * front - the start of the deque
 */
public class MyDeque<E> implements DequeInterface<E> {
    Object[] data;
    int size;
    int rear;
    int front;

    /**
     * initializes an instance of MyDeque
     * 
     * @param initialCapacity inital capacty of the data array
     */
    public MyDeque(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        } else {
            this.data = new Object[initialCapacity];
            this.size = 0;
            this.rear = 0;
            this.front = 0;
        }
    }

    /**
     * returns the size of the relevant data
     * 
     * @return the size of the relevant data
     */
    public int size() {
        return this.size;
    }

    /**
     * extends the capacity of the array if necessary
     */
    public void expandCapacity() {
        // if array has no length, instantly capacity is 10
        if (this.data.length == 0) {
            this.data = new Object[10];
        } else {
            // doubles length of array and makes sure data is still present
            Object[] originalData = this.data;
            int initial = this.data.length;
            this.data = new Object[initial * 2];
            if (this.size != 0) {
                int indexCounter = this.front - 1;
                for (int i = 0; i < this.size; i++) {
                    indexCounter++;
                    if (indexCounter == originalData.length) {
                        indexCounter -= originalData.length;
                    }
                    this.data[i] = originalData[indexCounter];
                    this.front = 0;
                    this.rear = this.size - 1;
                }
            }
        }
    }

    /**
     * adds element to the front of the array
     * 
     * @param element the element being added to the data
     */
    public void addFirst(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (this.size == this.data.length) {
            expandCapacity();
        }
        // places data at the first element if size is 0
        if (this.size == 0) {
            this.data[this.front] = element;
            this.size++;
        } else {
            int newPosition = this.front - 1;
            // wraps array around the front if necessary
            if (newPosition < 0) {
                newPosition += this.data.length;
            }
            this.data[newPosition] = element;
            this.front = newPosition;
            this.size++;
        }
    }

    /**
     * adds element to the front of the array
     * 
     * @param element the element being added to the data
     */
    public void addLast(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (this.size == this.data.length) {
            expandCapacity();
        }
        // places data at the first element if size is 0
        if (this.size == 0) {
            this.data[this.front] = element;
            this.size++;
        } else {
            int newPosition = this.rear + 1;
            // wraps array around the end if necessary
            if (newPosition == this.data.length) {
                newPosition -= this.data.length;
            }
            this.data[newPosition] = element;
            this.rear = newPosition;
            this.size++;
        }
    }

    /**
     * removes the first element in the data
     * 
     * @return the element that is removed
     */
    public E removeFirst() {
        if (this.size == 0) {
            return null;
        }
        E store = (E) data[front];
        this.data[front] = null;
        int newPosition = this.front + 1;
        // wraps around the front value if necessary
        if (newPosition == this.data.length) {
            newPosition -= this.data.length;
        }
        this.front = newPosition;
        this.size--;
        return store;
    }

    /**
     * removes the last element in the data
     * 
     * @return the element that is removed
     */
    public E removeLast() {
        if (this.size == 0) {
            return null;
        }
        E store = (E) data[rear];
        this.data[rear] = null;
        int newPosition = this.rear - 1;
        // wraps around the rear value if necessary
        if (newPosition < 0) {
            newPosition += this.data.length;
        }
        this.rear = newPosition;
        this.size--;
        return store;
    }

    /**
     * returns the front value
     * 
     * @return the front value
     */
    public E peekFirst() {
        if (this.size == 0) {
            return null;
        } else {
            return (E) this.data[front];
        }
    }

    /**
     * returns the rear value
     * 
     * @return the rear value
     */
    public E peekLast() {
        if (this.size == 0) {
            return null;
        } else {
            return (E) this.data[rear];
        }
    }

}