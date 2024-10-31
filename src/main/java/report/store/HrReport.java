package report.store;

import report.Report;
import report.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class HrReport implements Report {

    private final Store store;

    public HrReport(MemoryStore store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        List<Employee> current = store.findBy(filter);
        current.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        for (Employee employee : current) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary()).append(" ")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
