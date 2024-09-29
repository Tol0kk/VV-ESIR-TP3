package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

// Implementation of a MinHeapTree
class BinaryHeap<T> {
    private ArrayList<T> heap;
    private Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        this.heap = new ArrayList<>();
    }

    public T pop() throws NoSuchElementException {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }

        // Swap last with first while keeping first to return
        int i = heap.size() - 1;
        swap(0, i);
        T result = heap.remove(i);

        // Idx of the value we moved
        int idx = 0;

        // Fix heap
        while (idx < heap.size() / 2) {
            // Get Idx of the smallest child
            int left_child_idx = left_child(idx);
            int right_child_idx = right_child(idx);
            int smaller_child_idx = left_child_idx;
            if (right_child_idx < heap.size() && comparator.compare(heap.get(left_child_idx), heap.get(right_child_idx)) > 0) {
                smaller_child_idx = right_child_idx;
            }

            // Check if heap fixed (when last moved value is smaller than all is child)
            if (comparator.compare(heap.get(idx), heap.get(smaller_child_idx)) < 0) {
                break;
            }

            // swap with smallest
            swap(idx, smaller_child_idx);
            idx = smaller_child_idx;
        }

        return result;
    }

    public T peek() throws NoSuchElementException {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }

        return heap.get(0);
    }

    // Pushes a new element onto the heap
    public void push(T element) {

        int i = heap.size();
        heap.add(element);

        while (i != 0 && comparator.compare(heap.get(i), heap.get(parent(i))) < 0) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    // Returns the number of elements in the heap
    public int count() {
        return heap.size();
    }

    private void swap(int a, int b) {
        T temp = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, temp);
    }

    // Get the Parent index for the given index
    private int parent(int key) {
        return (key - 1) / 2;
    }

    // Get the Left Child index for the given index
    private int left_child(int key) {
        return 2 * key + 1;
    }

    // Get the Right Child index for the given index
    private int right_child(int key) {
        return 2 * key + 2;
    }

}