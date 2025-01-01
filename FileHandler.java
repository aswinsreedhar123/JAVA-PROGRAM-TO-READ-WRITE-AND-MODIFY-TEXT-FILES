import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // File path (you can change this to your desired file path)
        String filePath = "example.txt";

        try {
            while (true) {
                System.out.println("\n--- File Operations Menu ---");
                System.out.println("1. Read File");
                System.out.println("2. Write to File");
                System.out.println("3. Modify File");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        readFile(filePath);
                        break;
                    case 2:
                        System.out.print("Enter text to write to the file: ");
                        String textToWrite = scanner.nextLine();
                        writeFile(filePath, textToWrite);
                        break;
                    case 3:
                        System.out.print("Enter the text to replace: ");
                        String oldText = scanner.nextLine();
                        System.out.print("Enter the replacement text: ");
                        String newText = scanner.nextLine();
                        modifyFile(filePath, oldText, newText);
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    // Method to read a file
    public static void readFile(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        System.out.println("\n--- File Content ---");
        for (String line : lines) {
            System.out.println(line);
        }
    }

    // Method to write to a file
    public static void writeFile(String filePath, String content) throws IOException {
        Files.write(Paths.get(filePath), content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        System.out.println("Text written to file successfully.");
    }

    // Method to modify a file
    public static void modifyFile(String filePath, String oldText, String newText) throws IOException {
        Path path = Paths.get(filePath);
        List<String> lines = new ArrayList<>(Files.readAllLines(path));
        for (int i = 0; i < lines.size(); i++) {
            lines.set(i, lines.get(i).replace(oldText, newText));
        }
        Files.write(path, lines);
        System.out.println("File modified successfully.");
    }
}