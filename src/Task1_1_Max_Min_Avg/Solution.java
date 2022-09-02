package Task1_1_Max_Min_Avg;

/**
 * Fill a 2D array with random numbers
 * Print the maximum, minimum and average value.
 * Create a custom randomizer
 */

public class Solution {

    public static int ARRAY_SIZE = 5;
    public static int SIZE_BOUND = 50;

    public static void main(String[] args) {
        System.out.println("Fill a 2D array with random numbers, calculate max, min and average:");


        int[][] arr = new int[ARRAY_SIZE][ARRAY_SIZE];
        fill2DArrayWithRandomValues(arr);
        print2DArray(arr);
        getMaxValueWith2DArray(arr);
        getMinValueWith2DArray(arr);
        getAvgValueWith2DArray(arr);

    }

    public static void fill2DArrayWithRandomValues(int[][] arr) {
        int lengthArr = arr.length;
        for (int i = 0; i < lengthArr; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = CustomRandom.randomInt(SIZE_BOUND);
            }
        }
    }

    public static void print2DArray(int[][] arr) {
        if (arr == null) throw new IllegalArgumentException("Array is empty");
        int lengthArr = arr.length;
        for (int i = 0; i < lengthArr; i++, System.out.println()) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
        }
    }

    public static void getMaxValueWith2DArray(int[][] arr) {
        if (arr == null) throw new IllegalArgumentException("Array is empty");
        int max = Integer.MIN_VALUE;
        for (int[] ints : arr) {
            for (int anInt : ints) {
                if (max < anInt)
                    max = anInt;
            }
        }
        System.out.println("max = " + max);
    }

    public static void getMinValueWith2DArray(int[][] arr) {
        if (arr == null) throw new IllegalArgumentException("Array is empty");
        int min = Integer.MAX_VALUE;
        for (int[] ints : arr) {
            for (int anInt : ints) {
                if (min > anInt)
                    min = anInt;
            }
        }
        System.out.println("min = " + min);
    }

    public static void getAvgValueWith2DArray(int[][] arr) {
        if (arr == null) throw new IllegalArgumentException("Array is empty");
        int sum = 0;
        int counter = 0;
        double avg;
        for (int[] ints : arr) {
            for (int anInt : ints) {
                sum += anInt;
                counter++;
            }
        }
        if (counter == 0) {
            throw new ArithmeticException("division by zero");
        }
        avg = (double) sum / counter;
        System.out.println("avg = " + avg);
    }


    static class CustomRandom {

        private final static int MULTIPLIER = 1103515245;
        private final static int INCREMENT = 12345;
        private final static int MODULUS = 65536;
        private static int randInt = (int) System.currentTimeMillis() % 31;


        public static int randomInt(int bound) {
            if (bound <= 0)
                throw new IllegalArgumentException("bound must be positive");

            randInt = (randInt * MULTIPLIER + INCREMENT) % MODULUS;
            if (randInt < 0) {
                randInt = randInt * -1;
            }
            return randInt % bound;
        }
    }
}