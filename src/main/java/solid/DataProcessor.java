package solid;

import java.util.List;

/**
 * Класс DataProcessor выполняет две задачи: обработку данных и вывод результатов.
 * Это нарушает принцип SRP, так как каждая ответственность должна быть выделена в отдельный класс.
 */

public class DataProcessor {

    public void processData(List<String> data) {
        for (String item : data) {
            System.out.println("Обрабатываем: " + item);
        }
        outputResults(data);
    }

    private void outputResults(List<String> data) {
        System.out.println("Результаты обработки: " + data);
    }
}
