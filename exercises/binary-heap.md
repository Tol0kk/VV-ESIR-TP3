# Implementing and testing a binary heap

A [*binary heap*](https://en.wikipedia.org/wiki/Binary_heap) is a data structure that contains comparable objects and it is able to efficiently return the lowest element.
This data structure relies on a binary tree to keep the insertion and deletion operations efficient. It is the base of the [*Heapsort* algorithm](https://en.wikipedia.org/wiki/Heapsort).

Implement a `BinaryHeap` class with the following interface:

```java
class BinaryHeap<T> {

    public BinaryHeap(Comparator<T> comparator) { ... }

    public T pop() { ... }

    public T peek() { ... }

    public void push(T element) { ... }

    public int count() { ... }

}
```

A `BinaryHeap` instance is created using a `Comparator` object that represents the ordering criterion between the objects in the heap.
`pop` returns and removes the minimum object in the heap. If the heap is empty it throws a `NotSuchElementException`.
`peek` similar to `pop`, returns the minimum object but it does not remove it from the `BinaryHeap`.
`push` adds an element to the `BinaryHeap`.
`count` returns the number of elements in the `BinaryHeap`.

Design and implement a test suite for this `BinaryHeap` class.
Feel free to add any extra method you may need.

Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-heap](../code/tp3-heap) to complete this exercise.

## Answer

# Question 1 

Most method (pop,peek,count) does not take any argument for them the input will be the state of the Heap before each call to them. push() will take the state of the tree and it's input as inputs. 

The state of the tree depends also on the kind of tree (it's comparator). Each method could behave differently depending on the Comparator pass to the BinaryHeap.

pop() and peek() should throw when there is no elements in the Heap.

count() and peek() are not dependant of the Comparator pass to the Heap Since they don't use the compartor at all.

From **Input Space Partitioning** design, I would write the following test:
- testCountWhenEmpty()
- testCountWhenNonEmpty()
- testPeekWhenEmpty()
- testPeekWhenNonEmpty()
- testPush() (Comparator.naturalOrder())
- testPushReverse() (Comparator.reverseOrder())
- testPopWhenEmpty()
- testPopWhenNonEmpty()

# Question 2

Code Coverage is 100%

# Question 3

I have no predicate with more than one boolean check.

# Question 4

I got 3 mutant that survived, all of them of equivalent mutant. s

## Mutant 1

### Original Code

```java
if (right_child_idx < heap.size() &&
        comparator.compare(heap.get(left_child_idx), heap.get(right_child_idx)) > 0) {
    smaller_child_idx = right_child_idx;
}
```

### Mutant Code

```java
if (right_child_idx < heap.size() &&
        comparator.compare(heap.get(left_child_idx), heap.get(right_child_idx)) >=s 0) {
    smaller_child_idx = right_child_idx;
}
```

This mutant is equivalent to the Original Code since if they are equal there is no smaller child so any of them can be the smaller child.

## Mutant 2

### Original Code

```java
// Check if heap fixed (when last moved value is smaller than all is child)
if (comparator.compare(heap.get(idx), heap.get(smaller_child_idx)) <= 0) {
    break;
}
```

### Mutant Code

```java
// Check if heap fixed (when last moved value is smaller than all is child)
if (comparator.compare(heap.get(idx), heap.get(smaller_child_idx)) < 0) {
    break;
}
```

This Mutant is equivalent. It just swap them one more time. We are lousing some performance on the mutant case. 

## Mutant 3

### Original Code 

```java
while (i != 0 &&
        comparator.compare(heap.get(i), heap.get(parent(i))) < 0) {
    swap(i, parent(i));
    i = parent(i);
}
```

### Mutant Code

```java
while (comparator.compare(heap.get(i), heap.get(parent(i))) <= 0) {
    swap(i, parent(i));
    i = parent(i);
}
```

This mutant is also equivalent. The mutant is just less efficient since it is doing a useless swap.

