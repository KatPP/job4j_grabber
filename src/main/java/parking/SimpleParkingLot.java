package parking;

public class SimpleParkingLot implements ParkingLot {
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
