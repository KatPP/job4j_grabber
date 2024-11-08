package parking;

public class SimpleCar implements Car {
    private final String licensePlate;
    private final int size;

    public SimpleCar(String licensePlate, int size) {
        this.licensePlate = licensePlate;
        this.size = size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String licensePlate() {
        return licensePlate;
    }
}