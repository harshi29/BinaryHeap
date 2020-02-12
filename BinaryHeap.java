package hxr190001;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class BinaryHeap<T extends Comparable<? super T>> {
    @SuppressWarnings("rawtypes")
	Comparable[] pq;
    int size;

    // Constructor for building an empty priority queue using natural ordering of T
    public BinaryHeap(int maxCapacity) {
	pq = new Comparable[maxCapacity];
	size = 0;
    }

    // add method: resize pq if needed
    public boolean add(T x) {
    	if (this.size == pq.length) {
            this.resize();	
        }
        pq[this.size] = x;
        percolateUp(this.size);
        this.size++;
	return true;
    }

    public boolean offer(T x) {
	return add(x);
    }
    
    // throw exception if pq is empty
    public T remove() throws NoSuchElementException {
	T result = poll();
	if(result == null || isEmpty()) {
	    throw new NoSuchElementException("Priority queue is empty");
	} else {
	    return result;
	}
    }

    // return null if pq is empty
    public T poll() {
    	if (isEmpty())
    		return null;
    	T val = null ;
    	val = (T)pq[0];
        pq[0] = pq[size-1];
        size--;
        percolateDown(0);
        return val;
    }
    
    public T min() { 
	return peek();
    }

    // return null if pq is empty
    @SuppressWarnings("unchecked")
	public T peek() {
    	if (isEmpty())
    		return null;
    	return (T) pq[0];
    }

    int parent(int i) {
	return (i-1)/2;
    }

    int leftChild(int i) {
	return 2*i + 1;
    }

    /** pq[index] may violate heap order with parent */
    void percolateUp(int index) {
    	
     Comparable x = pq[index];
   	 while(index > 0 && (compare(pq[parent(index)], x) == 1))
   	 {
   		 pq[index] = pq[parent(index)];
   		 index = parent(index);
   	 }
   	 pq[index] = x;	
    	
    }

    /** pq[index] may violate heap order with children */
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

	/** use this whenever an element moved/stored in heap. Will be overridden by IndexedHeap */
    void move(int dest, Comparable x) {
	pq[dest] = x;
    }

    int compare(Comparable a, Comparable b) {
	return ((T) a).compareTo((T) b);
    }
    
    /** Create a heap.  Precondition: none. */
    void buildHeap() {
	for(int i=parent(size-1); i>=0; i--) {
	    percolateDown(i);
	}
    }

    public boolean isEmpty() {
	return size() == 0;
    }

    public int size() {
	return size;
    }

    // Resize array to double the current size
    void resize() {
    	Comparable[] temp = new Comparable[size*2];
    	for (int i=0; i<pq.length; i++)
    		temp[i] = pq[i];
    	pq = temp;
    }
    
    public interface Index {
        public void putIndex(int index);
        public int getIndex();
    }

    public static class IndexedHeap<T extends Index & Comparable<? super T>> extends BinaryHeap<T> {
        /** Build a priority queue with a given array */
        IndexedHeap(int capacity) {
            super(capacity);
	}

        /** restore heap order property after the priority of x has decreased */
        void decreaseKey(T x) {
        }

	@Override
        void move(int i, Comparable x) {
            super.move(i, x);
        }
    }

    public static void main(String[] args) {
	Integer[] arr = {0,9,7,5,3,1,8,6,4,2};
	BinaryHeap<Integer> h = new BinaryHeap(arr.length);

	System.out.print("Before:");
	for(Integer x: arr) {
	    h.offer(x);
	    System.out.print(" " + x);
	}
	System.out.println();
	
	for(int i=0; i<arr.length; i++) {
	    arr[i] = h.poll();
	}

	System.out.print("After :");
	for(Integer x: arr) {
	    System.out.print(" " + x);
	}
	System.out.println();
    }
}
