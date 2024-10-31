package report;

import com.google.gson.GsonBuilder;
import report.model.Employee;
import report.store.MemoryStore;
import report.store.Store;

import java.util.function.Predicate;

public class JSONReport implements Report  {

    private final Store store;

    public JSONReport(MemoryStore store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        var library = new GsonBuilder().setPrettyPrinting().create();
        return library.toJson(store.findBy(filter));
    }
}
