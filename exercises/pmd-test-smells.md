# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer

### Rule `DetachedTestCase`

Using the rule `DetachedTestCase`, pmd found a test that was not annotated in the file 
commons-cli/src/test/java/org/apache/commons/cli/AbstractParserTestCase.java in line 68.

As we see, the test is not annotated : 

```java
public void testAmbiguousArgParsing() throws Exception {
    final String[] args = { "-=-" };
    final Options options = new Options();

    assertThrows(UnrecognizedOptionException.class, () -> parser.parse(options, args));
}
```

The fix is simple, we just have to annotate the test : 

```java
@Test
public void testAmbiguousArgParsing() throws Exception {
    final String[] args = { "-=-" };
    final Options options = new Options();

    assertThrows(UnrecognizedOptionException.class, () -> parser.parse(options, args));
}
```

As you can see in the file [commons-collections](../project_codebase/outputs/DetachedTestCase/commons-collections.txt), here PMD found a lot of similar issues. Some of the tests can be volontary not annotated, so there might be some false positive. 

### Rule `JUnitSpelling` 

No error was found. 

### Rule `JUnitContainsTooManyAssert`

from the root directery 'tp3' :

```sh 
pmd check -d ./project_codebase/commons-cli/src -R ./code/myRuleSet/TooManyAsserts.xml/JUnitTestContainsTooManyAsserts > ./code/outputs/JUnitTestContainsTooManyAsserts/commons-cli.txt
```

With this rule, we found a lot of tests that had more than 5 asserts, as we see in the [output](../code/outputs/JUnitTestContainsTooManyAsserts/commons-cli) file of the command. A improvement that we could do is to break the scenario of the test into multiple, to have a more precise feedback when testing. 


The number of asserts can be changed by editing [this file](../code/myRuleSet/TooManyAsserts.xml).  

Here is an example of a method with more than five asserts : 

output : `./project_codebase/commons-cli/src/test/java/org/apache/commons/cli/ValuesTest.java:76:	JUnitTestContainsTooManyAsserts:	Unit tests should not contain more than 5 assert(s).`

```java
@Test
public void testCharSeparator() {
    // tests the char methods of CommandLine that delegate to the String methods
    assertTrue(cmd.hasOption("j"), "Option j is not set");
    assertTrue(cmd.hasOption('j'), "Option j is not set");
    assertArrayEquals(new String[] {"key", "value", "key", "value"}, cmd.getOptionValues("j"));
    assertArrayEquals(new String[] {"key", "value", "key", "value"}, cmd.getOptionValues('j'));

    assertTrue(cmd.hasOption("k"), "Option k is not set");
    assertTrue(cmd.hasOption('k'), "Option k is not set");
    assertArrayEquals(new String[] {"key1", "value1", "key2", "value2"}, cmd.getOptionValues("k"));
    assertArrayEquals(new String[] {"key1", "value1", "key2", "value2"}, cmd.getOptionValues('k'));

    assertTrue(cmd.hasOption("m"), "Option m is not set");
    assertTrue(cmd.hasOption('m'), "Option m is not set");
    assertArrayEquals(new String[] {"key", "value"}, cmd.getOptionValues("m"));
    assertArrayEquals(new String[] {"key", "value"}, cmd.getOptionValues('m'));
}
```

We can see that we have 12 asserts in one test case. Moreover, the developper clearly separated the testcase into three parts. We can change that to have 3 tests : 

```java
@Test
public void testCharSeparatorOptionJ() {
    // tests the char methods of CommandLine that delegate to the String methods
    assertTrue(cmd.hasOption("j"), "Option j is not set");
    assertTrue(cmd.hasOption('j'), "Option j is not set");
    assertArrayEquals(new String[] {"key", "value", "key", "value"}, cmd.getOptionValues("j"));
    assertArrayEquals(new String[] {"key", "value", "key", "value"}, cmd.getOptionValues('j'));
}

@Test
public void testCharSeparatorOptionK() {
    assertTrue(cmd.hasOption("k"), "Option k is not set");
    assertTrue(cmd.hasOption('k'), "Option k is not set");
    assertArrayEquals(new String[] {"key1", "value1", "key2", "value2"}, cmd.getOptionValues("k"));
    assertArrayEquals(new String[] {"key1", "value1", "key2", "value2"}, cmd.getOptionValues('k'));
}

@Test
public void testCharSeparatorOptionM() {
    assertTrue(cmd.hasOption("m"), "Option m is not set");
    assertTrue(cmd.hasOption('m'), "Option m is not set");
    assertArrayEquals(new String[] {"key", "value"}, cmd.getOptionValues("m"));
    assertArrayEquals(new String[] {"key", "value"}, cmd.getOptionValues('m'));
}
```