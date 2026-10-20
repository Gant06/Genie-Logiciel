public class main{
    public static void main(String[] args){
        VehicleFactory factory1 = new CarFactory();
        VehicleFactory factory2 = new TruckFactory();
        Vehicle car = factory1.createVehicle();
        Vehicle truck = factory2.createVehicle();
        car.drive();
        truck.drive();
    }
}