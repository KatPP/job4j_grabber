package reporttests;

import org.junit.jupiter.api.Test;
import report.Report;
import report.formatter.DateTimeParser;
import report.formatter.ReportDateTimeParser;
import report.model.Employee;
import report.store.DeveloperReport;
import report.store.MemoryStore;

import javax.xml.bind.JAXBException;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class DeveloperReportTest {

    @Test
    public void whenCorrectCSVStyleReportGenerated() throws JAXBException {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Report engine = new DeveloperReport(store, parser);
        StringBuilder expected = new StringBuilder()
                .append("Name;Hired;Fired;Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(parser.parse(worker.getHired())).append(";")
                .append(parser.parse(worker.getFired())).append(";")
                .append(worker.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    public void whenMultipleEmployeesCSVStyleReportGenerated() throws JAXBException  {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Anna", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        Report engine = new DeveloperReport(store, parser);

        StringBuilder expected = new StringBuilder()
                .append("Name;Hired;Fired;Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(parser.parse(worker1.getHired())).append(";")
                .append(parser.parse(worker1.getFired())).append(";")
                .append(worker1.getSalary())
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(parser.parse(worker2.getHired())).append(";")
                .append(parser.parse(worker2.getFired())).append(";")
                .append(worker2.getSalary())
                .append(System.lineSeparator());

        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    public void whenNoEmployeesCSVStyleReportGenerated() throws JAXBException  {
        MemoryStore store = new MemoryStore();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Report engine = new DeveloperReport(store, parser);

        String expected = "Name;Hired;Fired;Salary;" + System.lineSeparator();

        assertThat(engine.generate(employee -> true)).isEqualTo(expected);
    }
}
