package task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.groupingBy;

public class ComplexExamples {

    static class Person {

        private final int id;
        private final String name;

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
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
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

    public static void main(String[] args) {
        System.out.println("Raw data:\n");

        for (Person person : RAW_DATA) {
            System.out.println(person.id + " - " + person.name);
        }

        /*
        Task1
            Убрать дубликаты, отсортировать по идентификатору, сгруппировать по имени

            Что должно получиться Key: Amelia
                Value:4
                Key: Emily
                Value:1
                Key: Harry
                Value:3
                Key: Jack
                Value:1
         */

        System.out.println("\n**************************************************\n");
        System.out.println("Duplicate filtered, grouped by name, sorted by name and id:");

        personArrayFilterDuplicateAndSortByIdAndGroupByName().forEach(
                (k, v) -> System.out.println("Key: " + k + "\nValue: " + v.size())
        );

        /*
        Task2

            [3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10
         */

        System.out.println("\n**************************************************\n");
        System.out.println("Two sum:");

        int[] nums = new int[]{3, 4, 2, 7};
        int target = 10;

        int[] twoSum = twoSum(nums, target);
        System.out.print(Arrays.toString(nums) + ", " + target + " -> " + Arrays.toString(twoSum));
        System.out.println();

        /*
        Task3
            Реализовать функцию нечеткого поиска

                    fuzzySearch("car", "ca6$$#_rtwheel"); // true
                    fuzzySearch("cwhl", "cartwheel"); // true
                    fuzzySearch("cwhee", "cartwheel"); // true
                    fuzzySearch("cartwheel", "cartwheel"); // true
                    fuzzySearch("cwheeel", "cartwheel"); // false
                    fuzzySearch("lw", "cartwheel"); // false
         */

        fuzzySearchTestCases();
    }

    private static void fuzzySearchTestCases() {
        assert fuzzySearch("car", "ca6$$#_rtwheel");
        assert fuzzySearch("cwhl", "cartwheel");
        assert fuzzySearch("cwhee", "cartwheel");
        assert fuzzySearch("cartwheel", "cartwheel");
        assert !fuzzySearch("cwheeel", "cartwheel");
        assert !fuzzySearch("lw", "cartwheel");
    }

    private static boolean fuzzySearch(String word, String target) {
        int stopIndex = word.length();
        int wordIndex = 0;
        for (char c : target.toCharArray()) {
            if (stopIndex == wordIndex) {
                return true;
            }
            if (c == word.charAt(wordIndex)) {
                wordIndex++;
            }
        }
        return stopIndex == wordIndex;
    }

    private static int[] twoSum(int[] nums, int target) {
        if (nums == null) {
            System.err.println("cannot be null");
            return new int[0];
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            int complement = target - num;
            if (list.contains(complement)) {
                return new int[]{complement, num};
            }
            list.add(num);
        }
        return new int[0];
    }

    private static Map<String, List<Person>> personArrayFilterDuplicateAndSortByIdAndGroupByName() {
        return Arrays.stream(RAW_DATA)
                .filter(Objects::nonNull)
                .distinct()
                .sorted(Comparator.comparingInt(Person::getId))
                .collect(groupingBy(Person::getName));
    }
}
