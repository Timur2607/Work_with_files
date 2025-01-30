import java.io.*;

public class Task1 {
    public static void main(String[] args) {
        String inputFileName = "C:\\Users\\Тимур\\Pictures\\Mob_dev\\input.txt";   // Исходный файл
        String outputFileName = "C:\\Users\\Тимур\\Pictures\\Mob_dev\\output.txt"; // Выходной файл

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line.toUpperCase());
                writer.newLine(); // Запись новой строки
            }
            System.out.println("Файл успешно обработан.");
        } catch (IOException e) {
            System.err.println("Ошибка работы с файлами: " + e.getMessage());
        }
    }
}