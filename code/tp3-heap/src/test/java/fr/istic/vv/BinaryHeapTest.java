package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.NoSuchElementException;

class BinaryHeapTest {
    @Test
    void testCountWhenEmpty() {
        BinaryHeap<Integer> tree = new BinaryHeap<Integer>(Comparator.naturalOrder());

        assertEquals(0, tree.count());
    }

    @Test
    void testCountWhenNonEmpty() {
        BinaryHeap<Integer> tree = new BinaryHeap<Integer>(Comparator.naturalOrder());
        tree.push(10);

        assertEquals(1, tree.count());
    }

    @Test
    void testPeekWhenEmpty() {
        BinaryHeap<Integer> tree = new BinaryHeap<Integer>(Comparator.naturalOrder());

        assertThrows(NoSuchElementException.class, () -> tree.peek());
    }

    // TODO do I test only peek() here ???
    @Test
    void testPeekWhenNonEmpty() {
        BinaryHeap<Integer> tree = new BinaryHeap<Integer>(Comparator.naturalOrder());
        tree.push(10);

        assertEquals(10, tree.peek());
    }

    // TODO do I test only push() here ??
    @Test
    void testPush() {
        BinaryHeap<Integer> tree = new BinaryHeap<Integer>(Comparator.naturalOrder());

        tree.push(10);
        tree.push(2);
        tree.push(12);
        tree.push(6);
        tree.push(4);
        tree.push(3);
        tree.push(1);
        // Assert
        assertEquals(1, tree.peek());
        assertEquals(7, tree.count());
    }

    // TODO do I test only push() here ??
    @Test
    void testPushReverse() {
        BinaryHeap<Integer> tree = new BinaryHeap<Integer>(Comparator.reverseOrder());

        tree.push(10);
        tree.push(2);
        tree.push(12);
        tree.push(6);
        tree.push(4);
        tree.push(3);
        tree.push(1);
        // Assert
        assertEquals(12, tree.peek());
        assertEquals(7, tree.count());
    }

    // TODO do I test only push() here ??
    @Test
    void testPopWhenEmpty() {
        BinaryHeap<Integer> tree = new BinaryHeap<Integer>(Comparator.naturalOrder());

        // Assert
        assertThrows(NoSuchElementException.class, () -> tree.pop());
    }

    @Test
    void testPopWhenNonEmpty() {
        BinaryHeap<Integer> tree = new BinaryHeap<Integer>(Comparator.naturalOrder());

        tree.push(10);
        tree.push(2);
        tree.push(12);
        tree.push(6);
        tree.push(4);
        tree.push(3);
        tree.push(1);

        // Assert
        assertEquals(1, tree.peek());
        assertEquals(7, tree.count());
        assertEquals(1, tree.pop());
        assertEquals(6, tree.count());
        assertEquals(2, tree.peek());
    }

    // @Test
    // void testPopAll() {
    //     BinaryHeap<Integer> tree = new BinaryHeap<Integer>(Comparator.naturalOrder());

    //     tree.push(10);
    //     tree.push(2);
    //     tree.push(12);
    //     tree.push(6);
    //     tree.push(4);
    //     tree.push(3);
    //     tree.push(1);

    //     // Assert
    //     assertEquals(1, tree.pop());
    //     assertEquals(2, tree.pop());
    //     assertEquals(3, tree.pop());
    //     assertEquals(4, tree.pop());
    //     assertEquals(6, tree.pop());
    //     assertEquals(10, tree.pop());
    //     assertEquals(12, tree.pop());
    //     assertEquals(0, tree.count());
    // }

    // @Test
    // void testPopAllReverse() {
    //     BinaryHeap<Integer> tree = new BinaryHeap<Integer>(Comparator.reverseOrder());

    //     tree.push(10);
    //     tree.push(2);
    //     tree.push(12);
    //     tree.push(6);
    //     tree.push(4);
    //     tree.push(3);
    //     tree.push(1);

    //     // Assert
    //     assertEquals(12, tree.pop());
    //     assertEquals(10, tree.pop());
    //     assertEquals(6, tree.pop());
    //     assertEquals(4, tree.pop());
    //     assertEquals(3, tree.pop());
    //     assertEquals(2, tree.pop());
    //     assertEquals(1, tree.pop());
    //     assertEquals(0, tree.count());
    // }
}