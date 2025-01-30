import java.io.*;
import java.nio.file.*;
import java.nio.channels.*;
import java.nio.ByteBuffer;

public class IOvsNIOComparison {
    public static void main(String[] args) throws IOException {
        String inputFileName = "C:\\Users\\Тимур\\Documents\\largeInput.txt";
        String outputFileIO = "C:\\Users\\Тимур\\Documents\\outputIO.txt";
        String outputFileNIO = "C:\\Users\\Тимур\\Documents\\outputNIO.txt";

        // Сравнение I/O
        long startTime = System.currentTimeMillis();
        copyFileUsingIO(inputFileName, outputFileIO);
        long endTime = System.currentTimeMillis();
        System.out.println("Время выполнения через IO: " + (endTime - startTime) + " мс");

        // Сравнение NIO
        startTime = System.currentTimeMillis();
        copyFileUsingNIO(inputFileName, outputFileNIO);
        endTime = System.currentTimeMillis();
        System.out.println("Время выполнения через NIO: " + (endTime - startTime) + " мс");
    }

    private static void copyFileUsingIO(String inputFileName, String outputFileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    private static void copyFileUsingNIO(String inputFileName, String outputFileName) throws IOException {
        try (FileChannel inputChannel = FileChannel.open(Paths.get(inputFileName), StandardOpenOption.READ);
             FileChannel outputChannel = FileChannel.open(Paths.get(outputFileName), StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024); // Буфер на 1 КБ
            while (inputChannel.read(buffer) > 0) {
                buffer.flip();
                outputChannel.write(buffer);
                buffer.clear();
            }
        }
    }
}