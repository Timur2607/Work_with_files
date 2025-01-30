// Общий интерфейс
interface TextProcessor {
    String process(String text);
}

// Простой процессор
class SimpleTextProcessor implements TextProcessor {
    @Override
    public String process(String text) {
        return text;
    }
}

// Декоратор: преобразование в верхний регистр
class UpperCaseDecorator implements TextProcessor {
    private TextProcessor processor;

    public UpperCaseDecorator(TextProcessor processor) {
        this.processor = processor;
    }

    @Override
    public String process(String text) {
        return processor.process(text).toUpperCase();
    }
}

// Декоратор: удаление пробелов в начале и конце строки
class TrimDecorator implements TextProcessor {
    private TextProcessor processor;

    public TrimDecorator(TextProcessor processor) {
        this.processor = processor;
    }

    @Override
    public String process(String text) {
        return processor.process(text).trim();
    }
}

// Декоратор: замена пробелов на подчеркивания
class ReplaceDecorator implements TextProcessor {
    private TextProcessor processor;

    public ReplaceDecorator(TextProcessor processor) {
        this.processor = processor;
    }

    @Override
    public String process(String text) {
        return processor.process(text).replace(" ", "_");
    }
}

// Демонстрация работы
public class Decorator {
    public static void main(String[] args) {
        TextProcessor processor = new SimpleTextProcessor();

        // Комбинирование декораторов
        TextProcessor decoratedProcessor = new ReplaceDecorator(
                new UpperCaseDecorator(
                        new TrimDecorator(processor)));

        String inputText = "   Hello World   ";
        String outputText = decoratedProcessor.process(inputText);

        System.out.println("Исходный текст: \"" + inputText + "\"");
        System.out.println("Обработанный текст: \"" + outputText + "\"");
    }
}
