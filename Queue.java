/*
 * Sam Polyakov
 * Queue.java
 * 03/30/2023
 * Project 5
 * CS231 B
 */

public interface Queue<T>{
    // creates a queue

    /**
     * Adds the given {@code item} to the end of this queue.
     * 
     * @param item the item to add to the queue.
     */
    public void offer(T item);

    /**
     * Returns the number of items in the queue.
     * @return the number of items in the queue.
     */
    public int size();

    /**
     * Returns the item at the front of the queue.
     * @return the item at the front of the queue.
     */
    public T peek();

    /**
     * Returns and removes the item at the front of the queue.
     * @return the item at the front of the queue.
     */
    public T poll();
}