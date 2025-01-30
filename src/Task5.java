import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.*;
import java.util.concurrent.CompletableFuture;

public class Task5 {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Тимур\\Pictures\\Mob_dev\\async_file.txt";

        try (AsynchronousFileChannel asyncChannel =
                     AsynchronousFileChannel.open(Paths.get(filePath), StandardOpenOption.READ)) {

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            asyncChannel.read(buffer, 0, buffer, new java.nio.channels.CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    attachment.flip();
                    System.out.println("Прочитанные данные: ");
                    System.out.println(new String(attachment.array(), 0, result));
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    System.err.println("Ошибка чтения файла: " + exc.getMessage());
                }
            });

            // Ждем, чтобы асинхронное выполнение завершилось.
            Thread.sleep(2000);

        } catch (IOException | InterruptedException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}