package algo;

import java.util.Stack;

/**
 * Временная сложность: (O(n), где (n) — длина входной строки. Мы проходим по каждому символу строки один раз.
 * Пространственная сложность: O(n) в худшем случае, когда все символы являются открывающими скобками,
 * и стек будет содержать все открывающие скобки.
 */

class Brackets {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (!isMatchingPair(top, c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '{' && close == '}') ||
                (open == '[' && close == ']');
    }
}
