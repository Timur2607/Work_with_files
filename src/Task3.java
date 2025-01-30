import java.io.*;
import java.nio.file.*;
import java.nio.channels.FileChannel;

public class Task3 {
    public static void main(String[] args) throws IOException {
        String inputFile = "C:\\Users\\Тимур\\Pictures\\Mob_dev\\input_large.txt";
        String outputFileIO = "C:\\Users\\Тимур\\Pictures\\Mob_dev\\output_io.txt";
        String outputFileNIO = "C:\\Users\\Тимур\\Pictures\\Mob_dev\\output_nio.txt";

        // Метод IO: FileReader / BufferedReader
        long startIO = System.currentTimeMillis();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileIO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        }
        long endIO = System.currentTimeMillis();
        System.out.println("IO время: " + (endIO - startIO) + " ms");

        // Метод NIO: FileChannel
        long startNIO = System.currentTimeMillis();
        try (FileChannel sourceChannel = FileChannel.open(Paths.get(inputFile), StandardOpenOption.READ);
             FileChannel destChannel = FileChannel.open(Paths.get(outputFileNIO),
                     StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            sourceChannel.transferTo(0, sourceChannel.size(), destChannel);
        }
        long endNIO = System.currentTimeMillis();
        System.out.println("NIO время: " + (endNIO - startNIO) + " ms");
    }
}