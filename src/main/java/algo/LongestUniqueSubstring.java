package algo;

import java.util.HashMap;

/**
 * Временная сложность O(n). Каждый символ обрабатывается не более двух раз (один раз при перемещении right и
 * один раз при перемещении left).
   Пространственная сложность O(m), где m это у нас количество уникальных символов в строке,
   так как мы используем хэш-мапу для хранения символов и их индексов.
 */

public class LongestUniqueSubstring {
    public static String longestUniqueSubstring(String str) {
        if (str.isEmpty()) {
            return "";
        }

        HashMap<Character, Integer> charIndexMap = new HashMap<>();
        int left = 0;
        int maxLength = 0;
        int startIndex = 0;

        for (int right = 0; right < str.length(); right++) {
            char currentChar = str.charAt(right);

            if (charIndexMap.containsKey(currentChar)) {
                left = Math.max(charIndexMap.get(currentChar) + 1, left);
            }

            charIndexMap.put(currentChar, right);

            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                startIndex = left;
            }
        }
        return str.substring(startIndex, startIndex + maxLength);
    }

    public static void main(String[] args) {
        String str = "abcbcde";
        String result = longestUniqueSubstring(str);
        System.out.println("Самая длинная подстрока с уникальными символами: " + result);
    }
}
