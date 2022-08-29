package Task1_1_Max_Min_Avg;


/*
 Заполните двумерный массив случайным числами и выведите максимальное, минимальное и среднее значение.
 Array.sort() использовать нельзя, Random можно но не желательно
 */
public class Solution {

    public static int ARRAY_SIZE = 5;
    public static int SIZE_BOUND = 50;

    public static void main(String[] args) {
        System.out.println("Заполняем двумерный массив случайными числами, выводим max и min и average:");


        int[][] arr = new int[ARRAY_SIZE][ARRAY_SIZE];
        fill2DArrayWithRandomValues(arr);
        print2DArray(arr);
        getMaxValueWith2DArray(arr);
        getMinValueWith2DArray(arr);
        getAvgValueWith2DArray(arr);

    }

    public static void fill2DArrayWithRandomValues(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = CustomRandom.randomInt(SIZE_BOUND);
            }
        }
    }

    public static void print2DArray(int[][] arr) {
        if (arr == null) throw new IllegalArgumentException("Array is empty");
        for (int i = 0; i < arr.length; i++, System.out.println()) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
        }
    }

    public static void getMaxValueWith2DArray(int[][] arr) {
        if (arr == null) throw new IllegalArgumentException("Array is empty");
        int max = arr[0][0];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (max < arr[i][j])
                    max = arr[i][j];
            }
        }
        System.out.println("max = " + max);
    }

    public static void getMinValueWith2DArray(int[][] arr) {
        if (arr == null) throw new IllegalArgumentException("Array is empty");
        int min = arr[0][0];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (min > arr[i][j])
                    min = arr[i][j];
            }
        }
        System.out.println("min = " + min);
    }

    public static void getAvgValueWith2DArray(int[][] arr) {
        if (arr == null) throw new IllegalArgumentException("Array is empty");
        int sum = 0;
        int counter = 0;
        double avg;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                sum += arr[i][j];
                counter++;
            }
        }
        avg = (double) sum / counter;
        System.out.println("avg = " + avg);
    }


    static class CustomRandom {

        private final static int MULTIPLIER = 1103515245;
        private final static int INCREMENT = 12345;
        private final static int MODULUS = 65536;
        private static int randInt = (int) System.currentTimeMillis();


        public static int randomInt(int bound) {
            if (bound <= 0)
                throw new IllegalArgumentException("bound must be positive");

            randInt = (randInt * MULTIPLIER + INCREMENT) % MODULUS;
            ;
            if (randInt < 0) {
                randInt = randInt * -1;
            }
            return randInt % bound;
        }
    }
}