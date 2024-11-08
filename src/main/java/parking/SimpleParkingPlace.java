package parking;

public class SimpleParkingPlace implements ParkingPlace {
    private final int size;
    private Car parkedCar;

    public SimpleParkingPlace(int size) {
        this.size = size;
    }

    @Override
    public boolean isAvailable() {
        return parkedCar == null;
    }

    @Override
    public void parkCar(Car car) {
        parkedCar = car;
    }

    @Override
    public void removeCar() {
        parkedCar = null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Car getParkedCar() {
        return parkedCar;
    }
}
