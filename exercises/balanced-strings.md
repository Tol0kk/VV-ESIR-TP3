# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `)]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str) {
    ...
}
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators, check if the test cases written so far satisfy *Base Choice Coverage*. If needed, add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

## Answer

1. First question

The input space partitionning is the set of strings of length greater or equal to 0, made of the characters '(', ')', '{', '}', '[', ']' and this set is infinite. There is two types of inputs, balanced, and unbalanced.

We can create some tests with these informations, without looking at the structure of the programm. We will test :
- "([{}])" a valid balanced string ;
- "()[]{}" a valid balanced string ;
- "({))" and an unbalanced string.

With this tests, we had a branch coverage of 84%, and a code coverage of 86%. Let's add some tests to increase the coverage :

2. Second question

Here we used the fact that the algorithm use a stack to store the openning brackets. 

- "", an edge case ;
- "(", an other edge case ;
- "()(())))", an unbalanced string that will empty the stack ;
- "(((((())", and unbalanced string where the algorithm will end and the stack will not be empty.

With these new tests, we have a code coverage of 94% and branch coverage of 96%. 

3. Third question

All the tests here reach all the logic of the algorithm. No need to add new tests. 

4. Fourth question

Using PIT, we found two mutations that survived the tests. All the others mutations where killed.

In these lines of code, PIT replaced the modulo operation by a multiplication, causing the condition to be always false. 

```java
if (str.length() % 2 == 1) { // for efficiency
    return false;
}
```

This have no impact on the tests because this condition is only here for efficiency and does not impact the results of the method. 

Then, we saw that the line 53 of the code was not covered. Let's add a test to fix that and improve the mutation test and run again PIT. 

We added the following test for the cases where the paramater of the method is invalid.

```java 
@Test
void testCase8() {
    assertThrows(InvalidParameterException.class, () -> isBalanced("(xx)"));
}
```

Now, we have a great mutation coverage of 17/18, knowing that the last mutation does not have to be taken in account. 

