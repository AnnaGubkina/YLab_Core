package Task1_2_SortArray;

/*
Отсортируйте массив [5,6,3,2,5,1,4,9]
Учесть дубликаты в массиве. Сделать так, чтобы алгоритм работал правильно с дублями.
Написать простые тесты
 */

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {

        System.out.println("Сортируем массив методом sortArr():");
        testSortArr();
        int[] array = {5, 6, 3, 2, 5, 1, 4, 9};

        sortArr(array);
        printArr(array);


        System.out.println("Сортируем массив методом подсчета(countingSort()), при большом количестве одинаковых элементов:");
        testCountingSort();
        int[] arr = {5, 6, 3, 2, 5, 9, 1, 4, 9, 5, 6, 3, 2, 4, 5, 1, 4, 9, 4, 5, 6, 3, 2, 4, 5, 1, 4, 9, 5, 6, 3, 2, 5, 1, 4, 9};

        countingSort(arr);
        printArr(arr);
    }

    public static void sortArr(int[] array) {
        for (int j = array.length - 1; j > 0; j--) {
            for (int i = 0; i < j; i++) {
                if (array[i] > array[i + 1]) {
                    int tmp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = tmp;
                }
            }
        }
    }

    public static void printArr(int[] array) {
        for (int y = 0; y < array.length; y++) {
            System.out.print(array[y] + " ");
        }
        System.out.println();
    }

    public static void countingSort(int[] array) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int element : array) {
            if (element < min) {
                min = element;
            }
            if (element > max) {
                max = element;
            }
        }
        //we make an array of baskets, each of which contains the number of basic elements of the original array
        int[] buckets = new int[max - min + 1];
        for (int element : array) {
            buckets[element - min]++;
        }
        int arrayIndex = 0;
        for (int i = 0; i < buckets.length; i++) {
            for (int j = buckets[i]; j > 0; j--) {
                array[arrayIndex++] = i + min;
            }
        }
    }

    public static void testSortArr() {
        int[] arrTest = {8, 7, 0, 5, 6, 3, 2, 5, 1, 4, 9, 12, 13};
        sortArr(arrTest);
        assert Arrays.equals(arrTest, new int[]{0, 1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 12, 13}) : "test SortArr() провален";
        System.out.println("Метод sortArr() успешно прошел тестирование");

    }

    public static void testCountingSort() {
        int[] arrTest = {5, 5, 5, 5, 5, 1, 1, 1, 7, 8, 9, 0, 0, 9, 4, 4, 4, 4, 4, 7, 0, 9, 1, 2, 3, 4, 4, 4, 4, 1, 5, 4, 7, 9, 5, 6, 5, 6, 5, 6, 5, 6, 5, 6, 3, 2, 4, 5, 1, 1, 9, 0, 0, 8};
        sortArr(arrTest);
        assert Arrays.equals(arrTest, new int[]{0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 7, 7, 7, 8, 8, 9, 9, 9, 9, 9})
                : "test countingSort() провален";
        System.out.println("Метод countingSort() успешно прошел тестирование");

    }
}
