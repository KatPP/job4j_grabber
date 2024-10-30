package solid;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс FileDataHandler отвечает как за чтение файла, так и за обработку данных.
 * Это нарушает принцип SRP, так как обе задачи должны быть выделены в отдельные классы,
 * чтобы облегчить поддержку и тестирование.
 */

public class FileDataHandler {

    public List<String> readFile(String filePath) {
        List<String> data = new ArrayList<>();
        return data;
    }

    public void processFileData(String filePath) {
        List<String> data = readFile(filePath);
        for (String item : data) {
            System.out.println("Обрабатываем: " + item);
        }
    }
}
