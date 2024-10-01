package fr.istic.vv;
import java.security.InvalidParameterException;
import java.util.Stack;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) {
        // pre conditions...
        if (str.length() % 2 == 1) { // for efficiency
            return false;
        }

        Stack<Character> stack = new Stack<>();
        int index = 0;
        char current;
        char closingBracketExpected;

        while (index < str.length()) {

            current = str.charAt(index);

            if (current == '}' || current == ')' || current == ']') {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    closingBracketExpected = getClosingBracketType(stack.pop());
                }

                if (current != closingBracketExpected) {
                    return false;
                }
            } else {
                stack.push(current);
            }

            index += 1;
        }

        return stack.isEmpty();
    }

    private static char getClosingBracketType(char openBracketType) {
        switch (openBracketType) {
            case '(':
                return ')';
            case '[':
                return ']';
            case '{':
                return '}';
            default:
                throw new InvalidParameterException();
        }
    }
}
