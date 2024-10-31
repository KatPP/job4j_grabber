package ocp;

/**
 * Здесь следующие нарушения: Чтобы добавить новый тип отчета, необходимо изменять существующий метод generateReport,
 * это противоречит принципу OCP, поэтому здесь нужно использовать полиморфизм и интерфейсы.
 */

class ReportGenerator {
    public String generateReport(String type) {

        if (type.equals("HR")) {
            return "HR Report";
        } else if (type.equals("Developer")) {
            return "Developer Report";
        } else {
            throw new IllegalArgumentException("Unknown report type");
        }
    }
}


