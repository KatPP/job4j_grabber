package reporttests;

import org.junit.jupiter.api.Test;
import report.Report;
import report.formatter.DateTimeParser;
import report.formatter.ReportDateTimeParser;
import report.model.Employee;
import report.store.HrReport;
import report.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class HrReportTest {

    @Test
    public void whenCorrectHRReportGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        store.add(worker2);
        Report engine = new HrReport(store);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(" ")
                .append(worker2.getSalary()).append(" ")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(worker.getSalary()).append(" ")
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    public void whenMultipleEmployeesHRReportGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 300);
        Employee worker2 = new Employee("Petr", now, now, 200);
        Employee worker3 = new Employee("Anna", now, now, 400);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new HrReport(store);

        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker3.getName()).append(" ")
                .append(worker3.getSalary()).append(" ")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(" ")
                .append(worker1.getSalary()).append(" ")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(" ")
                .append(worker2.getSalary()).append(" ")
                .append(System.lineSeparator());

        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    public void whenNoEmployeesHRReportGenerated() {
        MemoryStore store = new MemoryStore();
        Report engine = new HrReport(store);

        String expected = "Name; Salary;" + System.lineSeparator();

        assertThat(engine.generate(employee -> true)).isEqualTo(expected);
    }
}
