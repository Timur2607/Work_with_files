import java.io.*;

public class FileIO {
    public static void main(String[] args) {
        String inputFileName = "C:\\Users\\Тимур\\Desktop\\input.txt"; // Входной файл
        String outputFileName = "C:\\Users\\Тимур\\Desktop\\output.txt"; // Выходной файл

        // Блок try-with-resources для корректной обработки ресурсов
        try (
                FileReader fileReader = new FileReader(inputFileName);
                BufferedReader reader = new BufferedReader(fileReader);
                FileWriter fileWriter = new FileWriter(outputFileName);
                BufferedWriter writer = new BufferedWriter(fileWriter)
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line.toUpperCase()); // Преобразовать в верхний регистр
                writer.newLine(); // Писать каждая строка с новой строки
            }
            System.out.println("Файл успешно преобразован и записан.");
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка при работе с файлом: " + e.getMessage());
        }
    }
}