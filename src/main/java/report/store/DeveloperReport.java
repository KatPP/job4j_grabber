package report.store;

import report.Report;
import report.formatter.DateTimeParser;
import report.model.Employee;

import java.util.Calendar;
import java.util.function.Predicate;

public class DeveloperReport implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public DeveloperReport(MemoryStore store, DateTimeParser<Calendar> parser) {
        this.store = store;
        this.dateTimeParser = parser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name;Hired;Fired;Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(dateTimeParser.parse(employee.getHired())).append(";")
                    .append(dateTimeParser.parse(employee.getFired())).append(";")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
