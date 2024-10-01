package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void testCase1() {
        assertTrue(
            isBalanced("()[]{}")
        );
    }

    @Test
    void testCase2() {
        assertTrue(isBalanced("([{}])"));
    }

    @Test
    void testCase3() {
        assertFalse(isBalanced("({))"));
    }

    @Test
    void testCase4() {
        assertFalse(isBalanced("("));
    }

    @Test
    void testCase5() {
        assertTrue(isBalanced(""));
    }

    @Test
    void testCase6() {
        assertFalse(isBalanced("()(())))"));
    }

    @Test
    void testCase7() {
        assertFalse(isBalanced("(((((())"));
    }

    @Test
    void testCase8() {
        assertThrows(InvalidParameterException.class, () -> isBalanced("(xx)"));
    }
}