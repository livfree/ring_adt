/* File: Dequeable.java
 * Date: 10/3/16
 * Author: Olivia Liberti (oliberti@bu.edu)
 * Purpose: Interface, implemented by IntDeque
 */

public interface Dequeable {
    
    // Insert a new element at the rear of the queue
    // If queue is full, resizes
    
    public void enqueueRear(int n); 
    
    // Insert a new element at the front of the queue
    // If queue is full, resizes
    
    public void enqueueFront(int n); 
    
    // Remove and return the front element of the queue; move everything over to the left one slot
    // Note: Does not check for underflow
    
    public int dequeueFront(); 
    
        // Remove and return the front element of the queue; move everything over to the left one slot
    // Note: Does not check for underflow
    
    public int dequeueRear(); 
    
    // Return front element of the queue without removing it
    // Note: Does not check for underflow
    
    public int peekFront(); 
    
    // Return rear element of the queue without removing it
    // Note: Does not check for underflow
    
    public int peekRear(); 
    
    // Return the number of integers in the queue
    
    public int size(); 
    
    // Return true iff there are no elements in the queue
    
    public boolean isEmpty(); 
    
}