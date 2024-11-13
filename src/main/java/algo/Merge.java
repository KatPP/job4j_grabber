package algo;

import java.util.Arrays;

public class Merge {
    public static int[] mergesort(int[] array) {
        int[] result = array;
        int n = array.length;
        if (n > 1) {
            int[] left = mergesort(Arrays.copyOfRange(array, 0, n / 2));
            int[] right = mergesort(Arrays.copyOfRange(array, n / 2, n));
            result = merge(left, right);
        }
        return result;
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int indexLeft = 0, indexRight = 0, indexResult = 0;

        while (indexLeft < left.length && indexRight < right.length) {
            if (left[indexLeft] <= right[indexRight]) {
                result[indexResult++] = left[indexLeft++];
            } else {
                result[indexResult++] = right[indexRight++];
            }
        }

        while (indexLeft < left.length) {
            result[indexResult++] = left[indexLeft++];
        }

        while (indexRight < right.length) {
            result[indexResult++] = right[indexRight++];
        }
        return result;
    }
}
