package reporttests;

import org.junit.jupiter.api.Test;
import report.AccountantReport;
import report.Report;
import report.formatter.DateTimeParser;
import report.formatter.ReportDateTimeParser;
import report.model.Employee;
import report.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;
import static report.currency.Currency.EUR;
import static report.currency.Currency.USD;

public class AccountantReportTest {

    @Test
    public void whenCorrectUSDReportGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Report engine = new AccountantReport(store, parser, USD);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary; USD")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(worker.getSalary()).append(" ")
                .append(worker.getSalary() * 0.0162).append(" ")
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    public void whenNoEmployeesReportGenerated() {
        MemoryStore store = new MemoryStore();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Report engine = new AccountantReport(store, parser, EUR);

        String expected = "Name; Hired; Fired; Salary; EUR" + System.lineSeparator();

        assertThat(engine.generate(employee -> true)).isEqualTo(expected);
    }

    @Test
    public void whenCorrectEURReportGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Report engine = new AccountantReport(store, parser, EUR);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary; EUR")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(worker.getSalary()).append(" ")
                .append(worker.getSalary() * 0.0166).append(" ")
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }
}
