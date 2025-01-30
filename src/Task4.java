import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.*;

public class Task4 {
    public static void copyFile(String source, String dest) {
        try (FileChannel srcChannel = FileChannel.open(Paths.get(source), StandardOpenOption.READ);
             FileChannel destChannel = FileChannel.open(Paths.get(dest),
                     StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            srcChannel.transferTo(0, srcChannel.size(), destChannel);
            System.out.println("Копирование завершено.");
        } catch (IOException e) {
            System.err.println("Ошибка при копировании: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String sourceFile = "C:\\Users\\Тимур\\Pictures\\Mob_dev\\large_file.txt";
        String destFile = "C:\\Users\\Тимур\\Pictures\\Mob_dev\\copied_file.txt";

        copyFile(sourceFile, destFile);
    }
}