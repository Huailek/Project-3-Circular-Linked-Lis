import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class that create a circular linked list
 * @author      Niang Hual
 * @version     2023-05-17
 */
public class CircularLinkedList<E> implements CircularLinkedListInterface<E>{
    /** A node that will keep tract of the first node */
    Node<E> front;
    /** A Node that will keep track of the last node */
    Node<E> back;
    /** keeping track of the size of the node */
    int size;
    /**
     * CircularLinedList constructor
     */
    public CircularLinkedList(){
        front = null;
        back = null;
    }

    /**
     * Retrieves a count of elements being maintained by the list.
     *
     * @return the size of the list (count of elements)
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * Retrieves the data at the specified position in the list
     *
     * @param position 0-based index for the list; must be in the range 0 to size - 1
     * @return the data in the specified position in the list
     */
    @Override
    public E get(int position) {
        if (position < 0 || position >= size) {
            throw new IllegalArgumentException("Must be valid position");
        }
        Node<E> current = front;
        for (int index = 0; index < position; index++) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * Adds a new node to the end of the list; by nature, this element's next will point to the first element
     *
     * @param value the element to add to the list
     */
    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value);
        if (front == null) {
            front = newNode;
            front.next = front;
            back = newNode;
        }else {
            back.next = newNode;
            back = newNode;
        }
        back.next = front;
        size++;
    }

    /**
     * Removes the specified item from the list, if it exists there.
     *
     * @param value the element to remove from the list
     * @return true, if the element was found and removed; false, if not found or list is empty
     */
    @Override
    public boolean remove(E value) {
        // if there is nothing in the list
      if (size == 0) {
          return false;
      }
      boolean removed = false;
      Node<E> current = front;
        //if there is only one node in the list
        if (size == 1 && current.data.equals(value)) {
            front = null;
            back = null;
            removed = true;
            size --;
            // if the node to remove is the first node
        } else if (current.data.equals(value)) {
            front = front.next;
            back.next = front;
            removed = true;
            size --;
        }else {
            Node<E> prev = current;
            current = current.next;
            int count = 1;
            while (count < size) {
                if (current.data.equals(value)) {
                    if (current == back) {
                        back = prev;
                    }
                    prev.next = current.next;
                    back.next = front;
                    removed = true;
                    size--;
                }
                prev = current;
                current = current.next;
                count++;
            }
        }
       return removed;
    }

    /**
     * Removes the node at the specified position in the list
     *
     * @param position position in the list; must be in range 0 to size - 1
     */
    @Override
    public void remove(int position) {
        if (position < 0 || position >= size) {
            throw new IllegalArgumentException("must be valid position");
        }
        // if it's the first node
        if (position == 0) {
            front = front.next;
        } else {
            Node<E> current = front;
            int index = 1;
            while (index < position) {
                current = current.next;
                index++;
            }
            // if it's the last node
            if (position == size - 1) {
                back = current;
            }
            current.next = current.next.next;
        }
        back.next = front;
        size--;
    }

    /**
     * Retrieves an iterator over the list's elements.
     *
     * @return a strongly typed iterator over elements in the list
     */
    @Override
    public Iterator<E> iterator() {
        return new CircularListIterator();
    }

    /**
     * A private class of Node that keep the given data from client.
     */
    private static class Node<T> {
        /** T keep tracks of the data current data  */
        public T data;
        /** Keep track of the next data */
        public Node<T> next;
        /** Constructor for the node class */
        public Node(T data) {
            this.data = data;
        }
    }

    /**
     * A class that will make the data iterable
     */
    private class CircularListIterator implements Iterator<E>{
        Node<E> current = front;
        /** post: constructs an iterator for the given list */
        public CircularListIterator() {
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E result = current.data;
            current = current.next;
            return result;
        }
    }
}
