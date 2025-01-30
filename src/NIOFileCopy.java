import java.io.IOException;
import java.nio.file.*;
import java.nio.channels.FileChannel;

public class NIOFileCopy {
    public static void main(String[] args) {
        String sourceFile = "C:\\Users\\Тимур\\Pictures\\Mob_dev\\sourceLargeFile.txt";
        String destinationFile = "C:\\Users\\Тимур\\Pictures\\Mob_dev\\destinationLargeFile.txt";

        try {
            copyFileUsingNIO(sourceFile, destinationFile);
            System.out.println("Файл успешно скопирован.");
        } catch (IOException e) {
            System.err.println("Ошибка при копировании: " + e.getMessage());
        }
    }

    private static void copyFileUsingNIO(String sourceFile, String destinationFile) throws IOException {
        try (FileChannel sourceChannel = FileChannel.open(Paths.get(sourceFile), StandardOpenOption.READ);
             FileChannel destChannel = FileChannel.open(Paths.get(destinationFile), StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            sourceChannel.transferTo(0, sourceChannel.size(), destChannel);
        }
    }
}