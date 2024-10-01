# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. This assertion fails because of the approximation of floating numbers. You may want to use the method `Assert.assertEquals(float expected, float actual, float delta)` with delta the maximum difference between the two floats.

2. `AssertSame` ensure that two *objects* are the *same* object, it compares the *identity* of the objects. `AssertEquals` asserts that *two values* are *equals*. 

TODO pas trop d'idées à revoir
3. `fail` also helps to handle unreachable code after conditionnal statements, like in the example below : 

```java
@Test
void testSwitchLogic() {
    int value = 3;
    switch (value) {
        case 1:
            // Handle case 1
            break;
        case 2:
            // Handle case 2
            break;
        default:
            fail("Unexpected value: " + value);  // Fail if value doesn't match any expected case
    }
}
```

It also helps to create custom failure points in loops. For example, if you have a List and you have to check that this list contains only valids elements, you can use a loop to test if a value of the list is invalid. For example : 

```java 
@Test
void testListProcessing() {
    List<String> data = List.of("item1", "item2", "invalid");

    for (String item : data) {
        if (item.equals("invalid")) {
            fail("Invalid item found in the list: " + item);  // Fail if an unexpected item is found
        }
    }
}
```


4. First, using the new assertions method `assertThrows` improves the code readability. Then, it allows us
to test the exact line where the exception is thrown, and test if the message of the exception is the one expected. These features where not possible with @Test annotation.

