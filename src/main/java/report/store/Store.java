package report.store;

import report.model.Employee;

import java.util.List;
import java.util.function.Predicate;

public interface Store {

    void add(Employee employee);

    List<Employee> findBy(Predicate<Employee> filter);
}