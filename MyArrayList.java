/*
 * Name: Risab Sankar
 * Email: rsankar@ucsd.edu
 * PID: A17383972
 * Sources used: None
 * 
 * This file is used to implement the MyArrayList class and its methods.
 * It does much of the work in developing the methods of the MyArrayList
 * class to be used in other classes.
 */

/**
 * This class stores the data of the array and the size of the array.
 * It also contains methods to manipulate these variables.
 * 
 * Instance variables:
 * data - an array to store the data of the list
 * size - the size of the array
 */
public class MyArrayList<E> implements MyList<E> {
    Object[] data;
    int size;

    /**
     * The constructor initializes the MyArrayList with no argument
     * with an initial capacity and size.
     */
    public MyArrayList() {
        this.data = new Object[5];
        this.size = 0;
    }

    /**
     * The constructor initializes the MyArrayList with an
     * initial capacity.
     * 
     * @param initialCapacity the capacity that the list is set to
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        } else {
            this.data = new Object[initialCapacity];
            this.size = 0;
        }
    }

    /**
     * Copies an array onto the Array List
     * 
     * @param arr the array being copied into the Array List
     */
    public MyArrayList(E[] arr) {
        if (arr == null) {
            this.data = new Object[5];
            this.size = 0;
        } else {
            // copies array onto the data array of the array list
            this.data = new Object[arr.length];
            for (int i = 0; i < arr.length; i++) {
                data[i] = arr[i];
            }
            this.size = arr.length;
        }
    }

    /**
     * expands the capacity of the array accordingly
     * 
     * @param requiredCapacity the necessary capacity the array needs
     */
    public void expandCapacity(int requiredCapacity) {
        if (requiredCapacity < this.size) {
            throw new IllegalArgumentException();
        }
        if (this.size == 0) {
            this.data = new Object[5];
        }
        if (this.data.length > requiredCapacity) {
            boolean done = true;
        } else if (this.data.length + 3 >= requiredCapacity) {
            // copies data onto extended array
            Object[] newArr = new Object[this.data.length + 3];
            for (int i = 0; i < data.length; i++) {
                newArr[i] = this.data[i];
            }
            this.data = newArr;
        } else {
            // copies data onto extended array
            Object[] newArr = new Object[requiredCapacity];
            for (int i = 0; i < data.length; i++) {
                newArr[i] = this.data[i];
            }
            this.data = newArr;
        }
    }

    /**
     * gets capacity of the array
     * 
     * @return returns the capacity of the array
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * inserts the specific element at the particular index in the array
     * 
     * @param index   the index at which element is being inserted
     * @param element element being inserted
     */
    public void insert(int index, E element) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (this.size == this.data.length) {
            expandCapacity((this.size));
        }
        this.size++;
        boolean placed = false;
        Object[] newArr = new Object[data.length];
        // goes through array, inserts new data
        // copies rest of data around it
        for (int i = 0; i < this.size; i++) {
            if (i == index) {
                newArr[i] = element;
                placed = true;
            } else if (placed) {
                newArr[i] = data[i - 1];
            } else {
                newArr[i] = data[i];
            }
        }
        this.data = newArr;
    }

    /**
     * appends the element at end of array
     * 
     * @param element being added at back
     */
    public void append(E element) {
        if (this.size == this.data.length) {
            expandCapacity((this.size));
        }
        Object[] newArr = new Object[data.length];
        for (int i = 0; i < this.size; i++) {
            newArr[i] = data[i];
        }
        // adds element at end
        newArr[size] = element;
        data = newArr;
        size++;
    }

    /**
     * prepends the element at start of array
     * 
     * @param element being added at front
     */
    public void prepend(E element) {
        if (this.size == this.data.length) {
            expandCapacity((this.size));
        }
        Object[] newArr = new Object[data.length];
        // adds element at start
        newArr[0] = element;
        for (int i = 0; i < this.size; i++) {
            newArr[i + 1] = data[i];
        }
        data = newArr;
        size++;
    }

    /**
     * gets element at index in array
     * 
     * @param index index of element wanted
     * @return the element at the index
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) data[index];
    }

    /**
     * sets element at index in the array
     * 
     * @param index   index at which element is being set
     * @param element element being set
     * @return the old element that was replaced
     */
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E old = (E) data[index];
        data[index] = element;
        return old;
    }

    /**
     * removes element at the index in the array
     * 
     * @param index the index at which element is being removed
     * @return element that was removed
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            E store = (E) data[index];
            Object[] newArr = new Object[data.length];
            int count = 0;
            for (int i = 0; i < this.size; i++) {
                // checks to see if particular index should be removed
                if (i == index) {
                    continue;
                } else {
                    newArr[count] = data[i];
                    count++;
                }
            }
            size--;
            this.data = newArr;
            return store;
        }
    }

    /**
     * returns size of array
     * 
     * @return size of array
     */
    public int size() {
        return this.size;
    }
}