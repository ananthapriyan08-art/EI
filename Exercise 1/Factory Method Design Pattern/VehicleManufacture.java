// Product Interface
interface Vehicle {
    void assemble();
}

// Concrete Products
class Car implements Vehicle {
    public void assemble() {
        System.out.println("Assembling Car.");
    }
}

class Truck implements Vehicle {
    public void assemble() {
        System.out.println("Assembling Truck.");
    }
}

// Creator
abstract class VehicleFactory {
    public abstract Vehicle createVehicle();
}

// Concrete Factories
class CarFactory extends VehicleFactory {
    public Vehicle createVehicle() {
        return new Car();
    }
}

class TruckFactory extends VehicleFactory {
    public Vehicle createVehicle() {
        return new Truck();
    }
}

// Client Code
public class VehicleManufacture {
    public static void main(String[] args) {
        VehicleFactory carFactory = new CarFactory();
        Vehicle car = carFactory.createVehicle();
        car.assemble();

        VehicleFactory truckFactory = new TruckFactory();
        Vehicle truck = truckFactory.createVehicle();
        truck.assemble();
    }
}
