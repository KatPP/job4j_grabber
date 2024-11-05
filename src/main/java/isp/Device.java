package isp;

/**
 * Интерфейс Device включает методы, которые не применимы для всех устройств.
 * Принтер не может ни сканировать, ни отправлять факсы,
 * но он вынужден реализовывать эти методы, что нарушает принцип ISP.
 */

interface Device {
    void print();

    void scan();

    void fax();
}

class Printer implements Device {

    @Override
    public void print() {
        System.out.println("Печать документа");
    }

    @Override
    public void scan() {
        throw new UnsupportedOperationException("Принтер не может сканировать");
    }

    @Override
    public void fax() {
        throw new UnsupportedOperationException("Принтер не может отправлять факсы");
    }
}
