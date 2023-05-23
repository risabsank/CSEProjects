/*
 * Name: Risab Sankar
 * Email: rsankar@ucsd.edu
 * PID: A17383972
 * Sources used: None
 * 
 * This file is used to implement the MyLinkedList class and its methods.
 * It also includes an implementation and methods for nodes which are
 * used to develop the LinkedLists.
 */

import java.util.AbstractList;

/**
 * This class stores the size of the linked list, the head node, and
 * the tail node. Additionally, it includes methods to access and
 * manipulate the data in the array.
 * 
 * Instance variables:
 * size - the size of the linked list
 * head - the head of the linked list
 * tail - the tail of the linked list
 */
public class MyLinkedList<E> extends AbstractList<E> {

    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes.
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         * 
         * @param element Element to add, can be null
         */
        public Node(E element) {
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /**
         * Set the parameter prev as the previous node
         * 
         * @param prev new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;
        }

        /**
         * Set the parameter next as the next node
         * 
         * @param next new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /**
         * Set the parameter element as the node's data
         * 
         * @param element new element
         */
        public void setElement(E element) {
            this.data = element;
        }

        /**
         * Accessor to get the next Node in the list
         * 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Accessor to get the prev Node in the list
         * 
         * @return the previous node
         */
        public Node getPrev() {
            return this.prev;
        }

        /**
         * Accessor to get the Nodes Element
         * 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        }
    }

    // Implementation of the MyLinkedList Class
    /** Only 0-argument constructor is defined */

    /**
     * initializes the instance variables of the LinkedList
     */
    public MyLinkedList() {
        this.head = new Node(null);
        this.tail = new Node(null);
        this.head.setNext(tail);
        this.tail.setPrev(head);
    }

    /**
     * returns size of the lists
     * 
     * @return size of the linked list
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * gets element at index in linked list
     * 
     * @param index the point in the array you want to access
     * @return the element at the index in the array
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            int counter = 0;
            Node curNode = this.head.next;
            E returnData = curNode.data;
            while (counter != index) {
                curNode = curNode.next;
                returnData = curNode.data;
                counter++;
            }
            return returnData;
        }
    }

    /**
     * adds element at index of linkedlist
     * shifts the rest of the elements in linkedlist
     * 
     * @param index index in which element is being inserted
     * @param data  data being added
     */
    @Override
    public void add(int index, E data) {
        if (data == null) {
            throw new NullPointerException();
        } else if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else if (size == index) {
            add(data);
        } else {
            if (index == 0) {
                Node prevHead = this.head.next;
                Node newData = new Node(data);
                this.head.setNext(newData);
                newData.setNext(prevHead);
                newData.setPrev(this.head);
                prevHead.setPrev(newData);
                size++;
            } else {
                Node previous = getNth(index);
                Node before = previous.prev;
                Node newNode = new Node(data);
                newNode.setNext(previous);
                newNode.setPrev(before);
                previous.setPrev(newNode);
                before.setNext(newNode);
                size++;
            }
        }
    }

    /**
     * adds element to end of the array
     * 
     * @param data data being added to array
     * @return true
     */
    public boolean add(E data) {
        if (data == null) {
            throw new NullPointerException();
        } else if (size == 0) {
            Node newData = new Node(data);
            this.head.setNext(newData);
            newData.setPrev(this.head);
            this.tail.setPrev(newData);
            newData.setNext(this.tail);
            size++;
            return true;
        } else {
            Node curNode = this.tail.prev;
            Node newTail = new Node(data);
            newTail.setPrev(curNode);
            curNode.setNext(newTail);
            newTail.setNext(this.tail);
            this.tail.setPrev(newTail);
            size++;
            return true;
        }
    }

    /**
     * sets element at index of the array
     * 
     * @param index index at which element is being set
     * @param data  data being added to the array
     * @return previous element at index
     */
    public E set(int index, E data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else if (data == null) {
            throw new NullPointerException();
        } else {
            if (index == 0) {
                Node previous = this.head.next;
                Node newHead = new Node(data);
                this.head.setNext(newHead);
                newHead.setNext(previous.next);
                newHead.setPrev(this.head);
                previous.next.setPrev(newHead);
                return previous.data;
            } else {
                Node previous = getNth(index);
                Node newNode = new Node(data);
                newNode.setPrev(previous.prev);
                newNode.setNext(previous.next);
                previous.prev.setNext(newNode);
                previous.next.setPrev(newNode);
                return previous.data;
            }
        }
    }

    /**
     * removes element at index
     * 
     * @param index index at which element is being removed
     * @return previous element that was there
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            if (index == size - 1) {
                E returnData = this.get(index);
                Node curNode = this.tail.prev;
                Node prevNode = curNode.prev;
                this.tail.setPrev(prevNode);
                this.tail.prev.setNext(this.tail);
                size--;
                return returnData;
            } else {
                E returnData = this.get(index);
                int counter = 0;
                Node curNode = this.head.next;
                while (counter != index) {
                    curNode = curNode.next;
                    counter++;
                }
                curNode.prev.setNext(curNode.next);
                curNode.next.setPrev(curNode.prev);
                size--;
                return returnData;
            }
        }
    }

    /**
     * clears all non-sentinel nodes in the array
     */
    public void clear() {
        this.size = 0;
        this.head = new Node(null);
        this.tail = new Node(null);
        this.head.setNext(tail);
        this.tail.setPrev(head);
    }

    /**
     * checks to see if array is empty
     * 
     * @return whether array is empty or not
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * returns the node at the index of the array
     * 
     * @param index the index at which the node is
     * @return the node at the index
     */
    protected Node getNth(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            int counter = 0;
            Node curNode = this.head.next;
            while (counter != index) {
                curNode = curNode.next;
                counter++;
            }
            return curNode;
        }
    }
}
