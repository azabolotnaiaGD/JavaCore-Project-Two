import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {
    static final Scanner scanner = new Scanner(System.in);
    static List<String> listOfPeople = new ArrayList<>();
    static List<String> foundPeople = new ArrayList<>();

    public static void findAPerson() {
        System.out.println("\nSelect a matching strategy: ALL, ANY, NONE");
        switch (scanner.nextLine()) {
            case "ALL":
                findAll();
                break;
            case "ANY":
                findAny();
                break;
            case "NONE":
                findNone();
                break;
        }
    }

    public static void findAny() {
        System.out.println("\nEnter a name or email to search all suitable people.");
        String[] searchArray = scanner.nextLine().split(" ");
        int count = 0;

        System.out.println();

        for (String person : listOfPeople) {
            for (String dat : searchArray) {
                if (person.toLowerCase().contains(dat.toLowerCase())) {
                    count++;
                    foundPeople.add(person);
                    break;
                }
            }
        }
        foundPeople(count);
    }

    public static void findAll() {
        System.out.println("\nEnter a name or email to search all suitable people.");
        String[] searchArray = scanner.nextLine().split(" ");
        int count = 0;


        System.out.println();
        for (String person : listOfPeople) {
            for (String dat : searchArray) {
                if (Arrays.asList(person.toLowerCase().split(" ")).contains(dat.toLowerCase())) {
                    count++;
                    foundPeople.add(person);
                }
            }
        }
        foundPeople(count);
    }

    public static void findNone() {
        System.out.println("\nEnter a name or email to search all suitable people.");
        String[] searchArray = scanner.nextLine().split(" ");
        int count = 0;

        foundPeople = new ArrayList<>(listOfPeople);

        System.out.println();

        for (String person : listOfPeople) {
            for (String dat : searchArray) {
                if (Arrays.asList(person.toLowerCase().split(" ")).contains(dat.toLowerCase())) {
                    count++;
                    foundPeople.remove(person);
                }
            }
        }
        foundPeople(count);
    }

    public static void foundPeople(int count) {
        if (count > 0) {
            System.out.println(count + " persons found:");
            for (int i = 0; i < foundPeople.size(); i++) {
                System.out.println(foundPeople.get(i));
            }
            foundPeople.clear();
        } else {
            System.out.println("No matching people found.");
        }
    }

    public static void printAllPeople() {
        System.out.println("\n=== List of people ===");
        for (int i = 0; i < listOfPeople.size(); i++) {
            System.out.println(listOfPeople.get(i));
        }
    }

    public static void printMenu() {
        System.out.println("\n=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit");
    }

    public static void main(String[] args) {
        String fileSource = args[1];

        try (FileReader fileReader = new FileReader(fileSource)) {
            Scanner fileScanner = new Scanner(fileReader);
            while (fileScanner.hasNext()) {
                listOfPeople.add(fileScanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            printMenu();
            switch (scanner.nextLine()) {
                case "1":
                    findAPerson();
                    break;
                case "2":
                    printAllPeople();
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("Incorrect option! Try again.");
                    break;
            }
        }
    }
}