package pxr180025;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;


public class BinaryHeap<T extends Comparable<? super T>> {
    @SuppressWarnings("rawtypes")
	Comparable[] pq;
    int size;

    /** Constructor for building an empty priority queue using natural ordering of T
     * 
     * @param maxCapacity	Capacity of the priority queue	
     */
    public BinaryHeap(int maxCapacity) {
	pq = new Comparable[maxCapacity];
	size = 0;
    }

    /** add method:		Inserts specified element into the priority queue and resizes if priority queue is full
     * 
     * @param x			Element to be added to the priority queue
     * @return			True after successfully inserting
     */
    public boolean add(T x) {
    	if (this.size == pq.length) {
            this.resize();	
        }
        pq[this.size] = x;
        percolateUp(this.size);
        this.size++;
	return true;
    }
    
    /** offer method:	Inserts specified element into the priority queue by calling the add method
     * 
     * @param x			Element to be added to the priority queue
     * @return			True after successfully inserting
     */
    public boolean offer(T x) {
	return add(x);
    }
    
    /** remove method:	Removes the head element of the priority queue (throws exception if priority queue is empty)
     * 
     * @return			Removed element from priority queue or Throws exception if priority queue is empty 
     * @throws 			NoSuchElementException If priority queue is empty
     */
    public T remove() throws NoSuchElementException {
	T result = poll();
	if(result == null) {
	    throw new NoSuchElementException("Priority queue is empty");
	} else {
	    return result;
	}
    }

    /** poll method:	Retrieves and removes the head of the priority queue or returns null if this queue is empty
     * 
     * @return			The head of the queue or null if this queue is empty
     */
    public T poll(){
    	if (isEmpty())
    		return null;
    	T val = (T)pq[0];
        pq[0] = pq[size-1];
        size--;
        percolateDown(0);
        return val;
    }
    /**	min method:		Retrieves, but does not remove, the head of the priority queue or returns null if this queue is empty
     * 
     * @return			The head of the queue or null if this queue is empty
     */
    public T min() { 
	return peek();
    }

    /** peek method:	Retrieves, but does not remove, the head of the priority queue or returns null if this queue is empty
     * 
     * @return			The head of the queue or null if this queue is empty
     */
    @SuppressWarnings("unchecked")
	public T peek() {
    	if (isEmpty())
    		return null;
    	return (T) pq[0];
    }
    
    /** parent method:	Returns the index of the parent of the specified index
     * 
     * @param i			Index of element that whose parent index is required 
     * @return			Index of parent for the specified index
     */
    int parent(int i) {
	return (i-1)/2;
    }
    
    /** leftChild method:	Returns the index of the left child of the specified index
     * 
     * @param i				Index of element that whose left child index is required
     * @return				Index of left child for the specified index
     */
    int leftChild(int i) {
	return 2*i + 1;
    }

    /** percolateUp method: 	Percolates up from a given node in the priority queue. (pq[index] may violate heap order with parent)
     * 
     * @param index				The index of the node at which the percolate begins
     */
    void percolateUp(int index) {
    	
     Comparable x = pq[index];
   	 while(index > 0 && (compare(pq[parent(index)], x) == 1))
   	 {
   		 pq[index] = pq[parent(index)];
   		 index = parent(index);
   	 }
   	 pq[index] = x;	
    	
    }

    /** percolateDown method:	Percolates down from a given node in the priority queue. (pq[index] may violate heap order with children) 
     * 
     * @param index				The index of the node at which the percolate begins
     */
    void percolateDown(int index) {
     int left = leftChild(index);
   	 int right = leftChild(index) + 1 ;
   	 int smallest = index;
   	 if(left <size && compare(pq[left], pq[index]) == -1)
   	 {
   		 smallest = left;
   	 }
   	 if(right <size && compare(pq[right], pq[smallest]) == -1)
   	 {
   		 smallest = right;
   	 }
   	 if(index != smallest)
   	 {
   		 Comparable temp = pq[smallest];
   		 pq[smallest] = pq[index];
   		 pq[index] = temp;
   		 percolateDown(smallest);
   	 }	
    }

	/** move method:	Assigns a specified value to the specified index of priority queue
	 * 
	 * @param dest		Index where the specified element is assigned 
	 * @param x			Element to be assigned
	 */
    void move(int dest, Comparable x) {
	pq[dest] = x;
    }
    
    /** compare method:		Compares the two arguments for order		
     * 
     * @param a				The first object to be compared
     * @param b				The second object to be compared
     * @return				A negative integer, zero, or a positive integer when the first argument is less than, 
     * 						equal to, or greater than the second respectively
     */
    int compare(Comparable a, Comparable b) {
	return ((T) a).compareTo((T) b);
    }
    
    /** buildHeap method:	Create a heap.  Precondition: none. 
     * 
     */
    void buildHeap() {
	for(int i=parent(size-1); i>=0; i--) {
	    percolateDown(i);
	}
    }
    /** isEmpty method:		Check if the priority queue is empty
     * 
     * @return				True if empty, False otherwise
     */
    public boolean isEmpty() {
	return size() == 0;
    }
    
    /** size method:	Returns the number of elements in the priority queue
     * 
     * @return			The number of elements in the priority queue
     */
    public int size() {
	return size;
    }

    /** resize method:	Resize array to double the current size
     * 
     */
    void resize() {
    	Comparable[] temp = new Comparable[size*2];
    	for (int i=0; i<pq.length; i++)
    		temp[i] = pq[i];
    	pq = temp;
    }
    
    public void print() {
		System.out.print((size) + ": ");
		for (int i = 0; i < size; i++) {
			System.out.print(pq[i] + " ");
		}
		System.out.println();
	}
    

    public static void main(String[] args) {
	Integer[] arr = new Integer[5];
	
	BinaryHeap<Integer> h = new BinaryHeap(arr.length);
	
	//System.out.print("Before:");
	//for(Integer x: arr) {
	  //  h.offer(x);
	   // System.out.print(" " + x);
	
    Scanner sc = new Scanner(System.in);
    System.out.println("1.Add  2.Offer	3.Remove  4.Poll  5.Peek  6.IsEmpty");
	whileloop: 
		while (sc.hasNext()) {
		int val;
		switch (sc.nextInt()) {
		case 1: // Add
			val = sc.nextInt();
			h.add(val);
			h.print();
			break;
		case 2: // Offer
			val = sc.nextInt();
			h.offer(val);
			h.print();
			break;
		case 3: // Remove
			System.out.println("Element removed: " + h.remove());
			h.print();
			break;
		case 4: // Poll
			Comparable element =h.poll();
			if (element != null){
				System.out.println("Element popped: " + element);}
			else {
				System.out.println("Priority queue is empty");
			}
			h.print();
			break;
		case 5: // Peek
			System.out.println("Peek: " +h.peek());
			break;
		case 6: // IsEmpty
			System.out.println("Is queue empty: " + h.isEmpty());
			break;
		default:
			break whileloop;
		}
	}}}
