package parking_lot.repositories;

import parking_lot.models.Vehicle;
import parking_lot.models.VehicleType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VehicleRepository {

    private Map<Integer, Vehicle> map;

    public VehicleRepository(Map<Integer, Vehicle> map) {
        this.map = map;
    }

    public VehicleRepository() {
        this.map = new HashMap<>();
    }

    private static int id = 1;

    public Vehicle createIfNotExists(String vehicleNumber, VehicleType vehicleType){
        for (Map.Entry<Integer, Vehicle> entry : map.entrySet()) {
            Vehicle vehicle = entry.getValue();
            if(vehicle.getVehicleNumber().equals(vehicleNumber)){
                return vehicle;
            }

        }
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber(vehicleNumber);
        vehicle.setVehicleType(vehicleType);
        vehicle.setId(id);
        map.put(id++, vehicle);
        return vehicle;
    }
}

