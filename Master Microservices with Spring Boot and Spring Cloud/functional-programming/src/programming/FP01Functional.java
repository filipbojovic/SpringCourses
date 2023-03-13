package programming;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class FP01Functional {
    private static List<String> courses = List.of("Spring", "Spring Boot", "api", "Microservices", "AWS", "PCF", "Azure", "Docker", "K8S");
    private static List<String> fruits = List.of("apple", "banana", "mango");

    public static void main(String[] args) {
//        printAllNumbersInListFunctional(List.of(1, 2, 3, 4, 5));
//        printAllEvenNumbersInListFunctional(List.of(1, 2, 3, 4, 5));
//        System.out.println();
//        printSquaresOfEvenNumbersInListFunctional(List.of(1, 2, 3, 4, 5));
        filterFruits(fruits);
    }

    private static void printAllNumbersInListFunctional(List<Integer> numbers) {
        numbers
                .stream()
                .forEach(System.out::println);
    }

    private static boolean isEven(int num) {
        return num % 2 == 0;
    }

    private static void printAllEvenNumbersInListFunctional(List<Integer> numbers) {
        numbers
                .stream()
                .filter(FP01Functional::isEven)
                .forEach(System.out::println);
    }

    private static void printSquaresOfEvenNumbersInListFunctional(List<Integer> numbers) {
        numbers
                .stream()
                .filter(FP01Functional::isEven)
                .map(number -> Math.pow(number, 2))
                .forEach(System.out::println);
    }

    private static void filterFruits(List<String> fruits) {
        Predicate<? super String> predicate = fruit -> fruit.startsWith("c");
        Optional<String> first = fruits.stream().filter(predicate).findFirst();

        System.out.println(first);
        System.out.println(first.isEmpty());
        System.out.println(first.isPresent());
        System.out.println(first.get());

        
    }

}
