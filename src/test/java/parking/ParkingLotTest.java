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