package parking;

public interface ParkingPlace {
    boolean isAvailable();

    void parkCar(Car car);

    void removeCar();

    int getSize();

    Car getParkedCar();
}
