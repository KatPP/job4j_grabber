package reporttests;

import org.junit.jupiter.api.Test;
import report.JSONReport;
import report.Report;
import report.model.Employee;
import report.store.MemoryStore;

import javax.xml.bind.JAXBException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

public class JSONReportTest {

    @Test
    void whenJSONGeneratedCorrectly() throws JAXBException {
        MemoryStore store = new MemoryStore();
        Employee employee = new Employee("John Doe",
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                5000.0);
        Employee employee1 = new Employee("Jane Smith",
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                6000.0);
        store.add(employee);
        store.add(employee1);
        Report engine = new JSONReport(store);
        String ex = """
                [
                  {
                    "name": "John Doe",
                    "hired": {
                      "year": 2023,
                      "month": 5,
                      "dayOfMonth": 8,
                      "hourOfDay": 17,
                      "minute": 41,
                      "second": 0
                    },
                    "fired": {
                      "year": 2023,
                      "month": 5,
                      "dayOfMonth": 8,
                      "hourOfDay": 17,
                      "minute": 41,
                      "second": 0
                    },
                    "salary": 5000.0
                  },
                  {
                    "name": "Jane Smith",
                    "hired": {
                      "year": 2023,
                      "month": 5,
                      "dayOfMonth": 8,
                      "hourOfDay": 17,
                      "minute": 41,
                      "second": 0
                    },
                    "fired": {
                      "year": 2023,
                      "month": 5,
                      "dayOfMonth": 8,
                      "hourOfDay": 17,
                      "minute": 41,
                      "second": 0
                    },
                    "salary": 6000.0
                  }
                ]""";
        assertThat(engine.generate(em -> true)).isEqualTo(ex);
    }

    @Test
    void whenNoEmployeesGenerated() throws JAXBException {
        MemoryStore store = new MemoryStore();
        Report report = new JSONReport(store);

        String expect = "[]";

        assertThat(report.generate(em -> true)).isEqualTo(expect);
    }
}
