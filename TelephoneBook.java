import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class TelephoneBook {
       // Метод, который добавляет номера в книгу
    public static void addNumber(String name, int number, Map<String, List<Integer>> book) {
        List<Integer> numbers = book.getOrDefault(name, new ArrayList<>());
        numbers.add(number);
        book.put(name, numbers);
    }

    // Метод, который печатает список контактов
  public static void printBook(Map<String, List<Integer>> book) {
        Map<String, Integer> counts = new HashMap<>();
        for (String name : book.keySet()) {
            counts.put(name, book.get(name).size());
        }
        Map<String, Integer> sortedCounts = sortMapByValueDescending(counts);
        for (Map.Entry<String, Integer> count : sortedCounts.entrySet()) {
            System.out.println(count.getKey());
            for (Integer number : book.get(count.getKey())) {
                System.out.println(number);
            }
            System.out.println("*****");
        }
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortMapByValueDescending(Map<K, V> map) {
        return map.entrySet()
          .stream()
          .sorted(Map.Entry.<K, V>comparingByValue().reversed())
          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public static void main(String[] args) {
        Map<String, List<Integer>> bookPhone = new HashMap<>();
        addNumber("Ivanov", 123, bookPhone);
        addNumber("Ivanov", 1234, bookPhone);
        addNumber("Petrov", 546585, bookPhone);
        addNumber("Sidorov", 8956477, bookPhone);
        addNumber("Ivanov", 12356233, bookPhone);
        addNumber("Petrov", 787897, bookPhone);
        printBook(bookPhone);
       }
}