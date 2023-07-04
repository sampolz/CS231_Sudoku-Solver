/*
 * Sam Polyakov
 * Stack.java
 * 03/30/2023
 * Project 5
 * CS231 B
 */

public interface Stack<T> {

    public int size();
    //returns item on top of stack
    public T peek();

    //return and remove the item on the top of the stack
    public T pop();

    // add this item onto the top of the stack
    public void push(T item);
    
}
    
    

