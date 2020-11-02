package datastructure;

import java.util.Arrays;

/**
 * Heap data structure is created based on the following sources
 * https://www.geeksforgeeks.org/min-heap-in-java/
 * https://algorithms.tutorialhorizon.com/binary-min-max-heap/
 */
public class DynDSHeap {
    private static final int HEAD = 1;
    private int[] heap;
    private int size;
    private int capacity;
    private int deadSpace;

    public DynDSHeap(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity + 1];
        this.heap[0] = Integer.MIN_VALUE;
        this.size = 0;
        this.deadSpace = 0;
    }

    public DynDSHeap(int[] heap) {
        this(heap.length);
        for (int value : heap) {
            push(value);
        }
    }

    /**
     * Removes smallest element from the heap
     *
     * @return the min element
     */
    public int pop() {
        if (isEmpty()) {
            return -1;
        }

        int head = heap[HEAD];
        System.out.println("\nRemoving smallest element: " + head + " from the Heap..");
        heap[HEAD] = heap[size];
        heap[size] = 0;
        percolateDown(HEAD);
        size--;
        System.out.println("Heap size after removing smallest element: " + size + "\n");
        return head;
    }

    /**
     * Pushes new elements into the heap
     *
     * @param element we want to push
     */
    public void push(int element) {
        if (isFull()) {
            System.out.println("Cant push element heap is full.");
            return;
        }
        System.out.println("Inserting element " + element + " into heap at index " + (getSize()));
        heap[++size] = element;
        int currentIndex = size;
        percolateUp(currentIndex);
    }

    /**
     * Helper method for swapping node indexes
     *
     * @param firstIndex  node
     * @param secondIndex node
     */
    private void swap(int firstIndex, int secondIndex) {
        int temp = heap[firstIndex];
        heap[firstIndex] = heap[secondIndex];
        heap[secondIndex] = temp;
    }

    /**
     * Moves element up to maintain heap order
     *
     * @param index of element we want to move up
     */
    private void percolateUp(int index) {

        int currentIndex = index;
        int parentIndex = getParentIndex(currentIndex);

        while (currentIndex > 0 && heap[currentIndex] < heap[parentIndex]) {
            swap(currentIndex, parentIndex);
            currentIndex = parentIndex;
            parentIndex = getParentIndex(parentIndex);
        }
    }

    /**
     * Moves element down to maintain heap order
     *
     * @param index of element we want to move down
     */
    private void percolateDown(int index) {
        int smallest = index;
        int leftChildIndex = getLeftChildIndex(index);
        int rightChildIndex = getRightChildIndex(index);
        if (leftChildIndex < getSize() && heap[smallest] > heap[leftChildIndex]) {
            smallest = leftChildIndex;
        }
        if (rightChildIndex < getSize() && heap[smallest] > heap[rightChildIndex]) {
            smallest = rightChildIndex;
        }
        if (smallest != index) {
            swap(index, smallest);
            percolateDown(smallest);
        }
    }

    /**
     * Helper method for inserting elements into deadSpace when they no longer fit into the heap
     *
     * @param element we want to push
     */
    public void insertIntoDeadSpace(int element) {
        System.out.println("Inserting " + element + " into deadSpace at index " + getDeadSpace());
        capacity--;
        deadSpace++;
        this.heap[capacity + 1] = element;
    }

    /**
     * Rebuilds heap from deadSpace
     *
     * @return heap made from deadSpace
     */
    public int[] buildHeapFromDeadSpace() {
        int[] deadSpaceHeap = new int[getDeadSpace()];
        System.arraycopy(heap, heap.length - getDeadSpace(), deadSpaceHeap, 0, deadSpaceHeap.length);
        System.out.println("new heap is: " + Arrays.toString(deadSpaceHeap));
        resetDeadSpace();
        return deadSpaceHeap;
    }

    /**
     * Helper methods for validation
     */

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.capacity;
    }

    /**
     * Getters and Setters for our Heap
     */

    private int getParentIndex(int index) {
        return index / 2;
    }

    private int getLeftChildIndex(int index) {
        return (2 * index);
    }

    private int getRightChildIndex(int index) {
        return (2 * index) + 1;
    }

    private int[] getHeap() {
        return this.heap;
    }

    private int getSize() {
        return size;
    }

    private void setHeapSize(int size) {
        this.size = size;
    }

    private void setMaxHeapSize(int capacity) {
        this.capacity = capacity;
    }

    private void resetDeadSpace() {
        this.deadSpace = 0;
    }

    private int getDeadSpace() {
        return deadSpace;
    }

    private int getCapacity() {
        return capacity;
    }
}
