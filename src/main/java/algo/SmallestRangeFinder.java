package algo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SmallestRangeFinder {

    public static int[] findSmallestRange(int[] nums, int k) {
        if (nums.length < k) {
            return null;
        }

        int left = 0, right = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        int distinctCount = 0;
        int minLength = Integer.MAX_VALUE;
        int[] result = null;

        while (right < nums.length) {
            if (countMap.getOrDefault(nums[right], 0) == 0) {
                distinctCount++;
            }
            countMap.put(nums[right], countMap.getOrDefault(nums[right], 0) + 1);
            right++;

            while (distinctCount >= k) {
                if (right - left < minLength) {
                    minLength = right - left;
                    result = new int[]{left, right - 1};
                }

                countMap.put(nums[left], countMap.get(nums[left]) - 1);
                if (countMap.get(nums[left]) == 0) {
                    distinctCount--;
                }
                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}
