package task2_streams_sumOfTwo_fuzzySearch;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 1.Remove duplicates, sort by id, group by name
 * 2.Find a pair of integers in an array whose sum equals the given value. Return an array of those numbers
 * 3.Implement fuzzy search function
 */

public class ComplexExamples {

    static class Person {

        final int id;
        final String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person person)) return false;
            return getId() == person.getId() && getName().equals(person.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }
    }

    private static Person[] RAW_DATA = new Person[]{
            new Person(0, "Harry"),
            new Person(0, "Harry"),
            new Person(1, "Harry"),
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
    };

    public static void printMap(Map<String, Long> data) {
        if (data == null) {
            System.out.println("The entered data must not be empty");
            return;
        }
        for (Map.Entry entry : data.entrySet()) {
            System.out.println("Key: " + entry.getKey() + "\n"
                    + "Value: " + entry.getValue());
        }
    }

    public static TreeMap<String, Long> filterDuplicateGroupByNameSortById(Person[] array) {
        if (array == null) {
            System.out.println("Array must not be empty");
            return new TreeMap<>();
        }
        return Arrays.stream(array)
                .distinct()
                .collect(Collectors.groupingBy(Person::getName, TreeMap::new, Collectors.counting()));
    }


    public static void sumOfTwo(int[] array, int sum) {
        if (array == null) {
            System.out.println("Array must not be empty");
            return;
        }
        Arrays.sort(array);
        int first = 0;
        int last = array.length - 1;
        while (first < last) {
            int s = array[first] + array[last];
            if (s == sum) {
                int[] resultArr = {array[first], array[last]};
                System.out.println(Arrays.toString(resultArr));
                first++;
                last--;
            } else {
                if (s < sum) first++;
                else last--;
            }
        }
    }


    public static boolean fuzzySearch(String search, String storage) {
        if (search == null || storage == null) {
            System.out.println("The entered value must not be empty");
            return false;
        }
        if (search == storage) return true;
        if (storage.length() < search.length()) return false;
        if (storage.length() == search.length() && search.charAt(0) != storage.charAt(0)) return false;
        int searchIndex = 0;
        int storageIndex = 0;
        while (storageIndex < storage.length()) {
            if (search.charAt(searchIndex) == storage.charAt(storageIndex)) {
                searchIndex++;
            }
            storageIndex++;
            if (searchIndex == search.length()) return true;
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println("Raw data:");
        System.out.println();

        for (Person person : RAW_DATA) {
            System.out.println(person.id + " - " + person.name);
        }

        System.out.println("**************************************************");
        System.out.println("Duplicate filtered, grouped by name, sorted by name and id:");
        System.out.println();
        testFilterDuplicateGroupByNameSortById();
        printMap(filterDuplicateGroupByNameSortById(RAW_DATA));

        System.out.println();
        System.out.println("**************************************************");
        System.out.println("Find a pair of integers in an array whose sum equals the given value. Return an array of those numbers:");
        System.out.println();
        int[] arr = new int[]{3, 4, 2, 7};
        int sum = 10;
        System.out.println(Arrays.toString(arr));
        sumOfTwo(arr, sum);

        System.out.println();
        System.out.println("**************************************************");
        System.out.println("Implement fuzzy search function:");
        testFuzzySearch();
        System.out.println(fuzzySearch("car", "ca6$$#_rtwheel"));
        System.out.println(fuzzySearch("cwhl", "cartwheel"));
        System.out.println(fuzzySearch("cwhee", "cartwheel"));
        System.out.println(fuzzySearch("cartwheel", "cartwheel"));
        System.out.println(fuzzySearch("cwheeel", "cartwheel"));
        System.out.println(fuzzySearch("lw", "cartwheel"));
    }

    public static void testFilterDuplicateGroupByNameSortById() {
        Person[] testPeople = new Person[]{
                new Person(0, "Anna"),
                new Person(0, "Anna"),
                new Person(1, "Anna"),
                new Person(2, "Anna"),
                new Person(3, "Grisha"),
                new Person(4, "Grisha"),
                new Person(4, "Grisha"),
                new Person(5, "Grisha"),
                new Person(5, "Petr"),
                new Person(6, "Ilon"),
                new Person(7, "Bill"),
                new Person(8, "Joe"),
                new Person(9, "Petr"),
                new Person(10, "Joe"),
                new Person(10, "Joe"),
                new Person(11, "Yakov"),
                new Person(12, "Oksana"),
        };

        Map<String, Long> testData = new TreeMap<>();
        testData.put("Anna", 3L);
        testData.put("Grisha", 3L);
        testData.put("Petr", 2L);
        testData.put("Ilon", 1L);
        testData.put("Bill", 1L);
        testData.put("Joe", 2L);
        testData.put("Yakov", 1L);
        testData.put("Oksana", 1L);

        assert testData.equals(filterDuplicateGroupByNameSortById(testPeople)) : "test filterDuplicateGroupByNameSortById() провален";
        System.out.println("Метод filterDuplicateGroupByNameSortById() успешно прошел тестирование");

    }

    public static void testFuzzySearch() {
        assert !(fuzzySearch("rub", "russian")) : "test fuzzySearch() провален";
        assert (fuzzySearch("cod", "crocodile")) : "test fuzzySearch() провален";
        assert !(fuzzySearch("821", "3458298493")) : "test fuzzySearch() провален";
        assert !(fuzzySearch("belissimobravo", "belissimo")) : "test fuzzySearch() провален";
        assert (fuzzySearch("love", "love")) : "test fuzzySearch() провален";
        assert (fuzzySearch("uber", "youtuber")) : "test fuzzySearch() провален";

        System.out.println("Метод fuzzySearch() успешно прошел тестирование");
    }
}
