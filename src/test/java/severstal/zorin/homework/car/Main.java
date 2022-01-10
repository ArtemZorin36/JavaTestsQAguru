package severstal.zorin.homework.car;

public class Main {

    public static void main(String[] args) {

        Car myCar = new Car("zaporozhets", 1.0);

        myCar.automaticTransmission = true;
        myCar.setColor("red");

        Wheels round = new Wheels("round");
        Wheels squer = new Wheels("Squer");

        myCar.wheels(round);
        myCar.showCar();



    }
}