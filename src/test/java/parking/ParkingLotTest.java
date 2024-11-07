package parking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {
    private ParkingLot parkingLot;

    @BeforeEach
    void setUp() {
        ParkingPlace[] parkingPlaces = new ParkingPlace[]{
                new SimpleParkingPlace(1),
                new SimpleParkingPlace(1),
                new SimpleParkingPlace(1),
                new SimpleParkingPlace(2),
                new SimpleParkingPlace(2)
        };
        parkingLot = new SimpleParkingLot(parkingPlaces);
    }

    @Test
    void testParkCar() {
        Car car1 = new SimpleCar("ABC123", 1);
        assertTrue(parkingLot.park(car1));
        assertEquals(4, parkingLot.getAvailableSpots());

        Car car2 = new SimpleCar("XYZ789", 2);
        assertTrue(parkingLot.park(car2));
        assertEquals(3, parkingLot.getAvailableSpots());
    }

    @Test
    void testParkTruckWhenAvailableSpotsExist() {
        parkingLot.park(new SimpleCar("CAR1", 1));
        parkingLot.park(new SimpleCar("CAR2", 1));
        parkingLot.park(new SimpleCar("CAR3", 1));
        Car truck = new SimpleCar("TRUCK1", 2);
        assertTrue(parkingLot.park(truck));
        assertEquals(1, parkingLot.getAvailableSpots());

    }

    @Test
    void testRemoveCar() {
        Car car1 = new SimpleCar("ABC123", 1);
        parkingLot.park(car1);
        assertEquals(4, parkingLot.getAvailableSpots());

        parkingLot.remove(car1);
        assertEquals(5, parkingLot.getAvailableSpots());
    }

    @Test
    void testRemoveNonExistentCar() {
        Car car1 = new SimpleCar("ABC123", 1);
        parkingLot.park(car1);
        assertEquals(4, parkingLot.getAvailableSpots());

        Car car2 = new SimpleCar("XYZ789", 1);
        parkingLot.remove(car2);
        assertEquals(4, parkingLot.getAvailableSpots());
    }

    @Test
    void testParkTruckOnTruckSpot() {
        Car truck = new SimpleCar("TRUCK1", 2);
        assertTrue(parkingLot.park(truck));
        assertEquals(4, parkingLot.getAvailableSpots());
    }
}


record SimpleCar(String licensePlate, int size) implements Car {
}

class SimpleParkingPlace implements ParkingPlace {
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


class SimpleParkingLot implements ParkingLot {
    private final ParkingPlace[] parkingPlaces;

    public SimpleParkingLot(ParkingPlace[] parkingPlaces) {
        this.parkingPlaces = parkingPlaces;
    }

    @Override
    public boolean park(Car car) {
        for (ParkingPlace place : parkingPlaces) {
            if (place.isAvailable() && place.getSize() >= car.size()) {
                place.parkCar(car);
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove(Car car) {
        for (ParkingPlace place : parkingPlaces) {
            if (place.getParkedCar() != null && place.getParkedCar().licensePlate().equals(car.licensePlate())) {
                place.removeCar();
                break;
            }
        }
    }

    @Override
    public int getAvailableSpots() {
        int count = 0;
        for (ParkingPlace place : parkingPlaces) {
            if (place.isAvailable()) {
                count++;
            }
        }
        return count;
    }
}