interface TextProcessor {
    String process(String text);
}

class SimpleTextProcessor implements TextProcessor1 {
    @Override
    public String process(String text) {
        return text; // Возвращает текст без изменений
    }
}

// Декоратор, преобразующий текст в верхний регистр
class UpperCaseDecorator implements TextProcessor1 {
    private final TextProcessor1 processor;

    public UpperCaseDecorator(TextProcessor1 processor) {
        this.processor = processor;
    }

    @Override
    public String process(String text) {
        return processor.process(text).toUpperCase();
    }
}

// Декоратор, удаляющий пробелы в начале и конце строки
class TrimDecorator implements TextProcessor1 {
    private final TextProcessor1 processor;

    public TrimDecorator(TextProcessor1 processor) {
        this.processor = processor;
    }

    @Override
    public String process(String text) {
        return processor.process(text).trim();
    }
}

// Декоратор, заменяющий пробелы на подчеркивания
class ReplaceDecorator implements TextProcessor1 {
    private final TextProcessor1 processor;

    public ReplaceDecorator(TextProcessor1 processor) {
        this.processor = processor;
    }

    @Override
    public String process(String text) {
        return processor.process(text).replace(" ", "_");
    }
}

public class Task2 {
    public static void main(String[] args) {
        TextProcessor1 processor = new ReplaceDecorator1(
                new UpperCaseDecorator1(
                        new TrimDecorator1(new SimpleTextProcessor1())
                )
        );
        String result = processor.process(" Hello world ");
        System.out.println(result); // Вывод: HELLO_WORLD
    }
}