package parking;

public interface ParkingLot {
    boolean park(Car car);

    void remove(Car car);

    int getAvailableSpots();
}
