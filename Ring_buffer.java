/* File: IntDeque.java
 * Date: 10/3/16
 * Author: Olivia Liberti (oliberti@bu.edu)
 * Purpose: Creates a Deque ADT using a circular-buffer technique.
 * Implements Dequeable
 */

public class IntDeque implements Dequeable {
    
    private int SIZE = 10;      //initial size of the array
    
    private int[] A = new int[SIZE];   //initializes the array
    
    private int next = 0;                //initializes next pointer
    
    private int front = 0;                //initializes front pointer
    
    private int size = 0;               //initializes size of queue
    
    //Resizes the array if there is overflow
    
    private void resize(){
       int[] T = new int[ A.length * 2 ];
       SIZE = SIZE * 2;
       for (int i = 0; i < A.length; ++i) {
            T[i] = A[front];
            front = nextSlot(front);
        }
       front = 0;
       next = 10;
       A = T;        
    }
    
    // Inserts a new element at the rear of the queue
    // If queue is full, resizes
    
    public void enqueueRear(int n){ 
       if((nextSlot(next) - 1) == 9 & A[next] == 0){
           next = 0;        
        }
        
        if(size == 10 & A[next] != 0) {
            resize();
        }
        
        A[next] = n;
        next = nextSlot(next);
        ++size;
    }
    
    // Insert a new element at the front of the queue
    // If queue is full, resizes
    
    public void enqueueFront(int n){                
        
        if(size == 10 & A[front] != 0){
            resize();      
        }
        
        front = previousSlot(front);
        A[front] = n;
        ++size;
    }
    
    //Provides value in the previous slot
    
    public int previousSlot(int k){
        if(k == 0){
           k = A.length - 1;
        }
        else{
           k = k - 1;
        }       
        return (k % A.length);
    }
    
    // Remove and return the front element of the queue

    public int dequeueFront(){
        int deq = A[front]; 
        front = nextSlot(front);
        if(size > 1){
           --size;
           return deq;
        }
        else{
            size = 0;
            return deq;
        }
    }
    
    // Remove and return the rear element of the queue
    
    public int dequeueRear(){
        int deq = A[previousSlot(next)];
        next = previousSlot(next);
        if(size > 1){
            --size;
           return deq;
        }
        else{
            size = 0;
            return deq;            
        }         
    }
    
    // Return front element of the queue without removing it
    
    public int peekFront(){
       return A[front];
    }
    
    //Moves next pointer
    //From lecture slides
    
    public int nextSlot(int k){
       return ((k +1) % A.length);
    }
    
    // Return rear element of the queue without removing it
    
    public int peekRear(){
       return A[next-1];
    }
    
    // Return the number of integers in the queue
    
    public int size(){
       return size;
    }
    
    // Return true iff there are no elements in the queue
    
    public boolean isEmpty(){
      return (size <= 0);
    }
    
  
   //Coverts the array to a string
    
    public String toString() {
        String s = "["; 
        for(int i = A.length-1; i > 0; --i) 
            s += (A[i] + ", ");
        s += A[0] + "] "; 
        s += (" length: " + A.length + "  size: " + size()); 
        s += ("  front: " + front + "  next: " + next); 
        return s; 
    } 
    
    // Unit Test Main
    
    public static void main(String[] args) {
        
        Dequeable D = new IntDeque();  
        System.out.println("\n[1] First test toString on empty deque... Should print out:"); 
        System.out.println("[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]  length: 10  size: 0  front: 0  next: 0"); 
        System.out.println(D); 
        
        System.out.println("\n[2] Test size and isEmpty... Should print out:\n0  true"); 
        System.out.println(D.size() + "  " + D.isEmpty()); 
        
        System.out.println("\n[3] Test enqueueRear.... Should print out:"
                               + "\n[0, 0, 0, 0, 0, 0, 4, 3, 2, 1]  length: 10  size: 4  front: 0  next: 4");
        D.enqueueRear(1); 
        D.enqueueRear(2);
        D.enqueueRear(3); 
        D.enqueueRear(4);
        
        System.out.println(D); 
        
        System.out.println("\n[4] Test size and isEmpty again... Should print out:\n4  false"); 
        System.out.println(D.size() + "  " + D.isEmpty()); 
        
        System.out.println("\n[5] Test dequeueFront.... Should print out:"
                               + "\nn = 1  m = 3");
        int n = D.dequeueFront(); 
        D.dequeueFront();  
        int m = D.dequeueFront(); 
        System.out.println("n = " + n + "  m = " + m); 
        
        System.out.println("\n[6] And should print out:"
                               + "\n[0, 0, 0, 0, 0, 0, 4, 3, 2, 1]  length: 10  size: 1  front: 3  next: 4");
        System.out.println(D); 
        
        
        
        System.out.println("\n[7] Test wrap-around of enqueueRear .... Should print out:"
                               + "\n[10, 9, 8, 7, 6, 5, 4, 13, 12, 11]  length: 10  size: 10  front: 3  next: 3");
        
        for(int i = 5; i < 14; ++i)
            D.enqueueRear(i);
        
        System.out.println(D); 
        
        System.out.println("\n[8] Test wrap-around of dequeueFront .... Should print out:"
                               + "\n[10, 9, 8, 7, 6, 5, 4, 13, 12, 11]  length: 10  size: 0  front: 3  next: 3  m = 13");
        
        for(int i = 0; i < 9; ++i)
            D.dequeueFront();
        m = D.dequeueFront(); 
        
        System.out.println(D + "  m = " + m); 
        
        System.out.println("\n[9] Test enqueueFront.... Should print out:"
                               + "\n[4, 9, 8, 7, 6, 5, 4, 1, 2, 3]  length: 10  size: 4  front: 9  next: 3");
        D.enqueueFront(1); 
        D.enqueueFront(2);
        D.enqueueFront(3); 
        D.enqueueFront(4);
        
        System.out.println(D); 
        
        System.out.println("\n[10] Test size and isEmpty again... Should print out:\n4  false"); 
        System.out.println(D.size() + "  " + D.isEmpty()); 
        
        System.out.println("\n[11] Test dequeueRear... Should print out:"
                               + "\nn = 1  m = 3");
        n = D.dequeueRear(); 
        D.dequeueRear();  
        m = D.dequeueRear(); 
        D.dequeueRear(); 
        System.out.println("n = " + n + "  m = " + m); 
        
        System.out.println("\n[12] And should print out:"
                               + "\n[4, 9, 8, 7, 6, 5, 4, 1, 2, 3]  length: 10  size: 0  front: 9  next: 9");
        System.out.println(D); 
        
        D.enqueueRear(0);        // front was accidentally at end of array, move it to test wrap-around
        D.dequeueFront(); 
        D.enqueueRear(0);        
        D.dequeueFront(); 
        D.enqueueRear(0);       
        D.dequeueFront(); 
        
        System.out.println("\n[13] Test wrap-around of enqueueFront .... Should print out:"
                               + "\n[102, 103, 104, 105, 6, 5, 4, 1, 100, 101]  length: 10  size: 6  front: 6  next: 2");
        
        for(int i = 100; i <= 105; ++i)
            D.enqueueFront(i);
        
        System.out.println(D); 
        
        System.out.println("\n[14] Test wrap-around of dequeueRear .... Should print out:"
                               + "\n[102, 103, 104, 105, 6, 5, 4, 1, 100, 101]  length: 10  size: 1  front: 6  next: 7  m = 104");
        
        for(int i = 0; i < 4; ++i)
            D.dequeueRear();
        m = D.dequeueRear(); 
        
        System.out.println(D + "  m = " + m);
        
        while(!D.isEmpty()) {
            D.dequeueRear(); 
        }
        
        System.out.println("\n[15] Now test all four together.... Should print out:\n1 2 3 4 5 6");  
        D.enqueueRear(6); 
        D.enqueueFront(4);
        D.enqueueRear(1);
        D.enqueueFront(2);
        System.out.print(D.dequeueRear() + " "); 
        System.out.print(D.dequeueFront() + " "); 
        D.enqueueRear(5);
        D.enqueueFront(3);
        System.out.print(D.dequeueFront() + " "); 
        System.out.print(D.dequeueFront() + " "); 
        System.out.print(D.dequeueRear() + " "); 
        System.out.println(D.dequeueFront() + " ");
        
        System.out.println("\n[16] All four.... Should print out:\n[102, 103, 5, 6, 4, 3, 4, 1, 100, 101]  length: 10  size: 0  front: 7  next: 7"); 
        System.out.println(D); 
        
        System.out.println("\n[17] Testing peekFront and peekRear .... Should print out:\n5\t6"); 
        System.out.println(D.peekFront() + "\t" + D.peekRear());  
        
        System.out.println("\n[18] Test resizing.... Should print out:"
                               + "\n[0, 0, 0, 0, 0, 0, 0, 14, 13, 12, 11, 10, 9, 8, 1, 2, 3, 4, 5, 6]  length: 20  size: 13  front: 0  next: 13"); 
        
        for(int i = 1; i < 7; ++i)
            D.enqueueFront(i);
        
        for(int i = 8; i < 15; ++i)
            D.enqueueRear(i);
        
        System.out.println(D); 
        
        System.out.println("\n[19] Make sure methods still work after resizing.... "
                               + "Should print out:\n[111, 222, 0, 0, 0, 0, 444, 333, 13, 12, 11, 10, 9, 8, 1, 2, 3, 4, 5, 6]  length: 20  size: 15  front: 19  next: 14"); 
        D.enqueueFront(111);
        D.enqueueFront(222);
        n = D.dequeueRear();
        D.enqueueRear(333);
        D.enqueueRear(444);
        m = D.dequeueFront(); 
        
        System.out.print(D);
        
        System.out.println("\n\n[20] And should print out:\nn = 14  m = 222");
        System.out.println("n = " + n + "  m = " + m); 
           
    }
}


