package severstal.zorin.homework.car;

import java.security.SecureRandom;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Car myCar = new Car("zaporozhets", 1.0);

        myCar.automaticTransmission = true;
        myCar.setColor("red");

        Wheels round = new Wheels("round");
        Wheels square = new Wheels("square");
        Wheels rhombus = new Wheels("rhombus");
        Wheels rectangle = new Wheels("rectangle");

        var random = new SecureRandom();
        var list = Arrays.asList(round, square, rhombus, rectangle);
        var randomElement = list.get(random.nextInt(list.size()));

        myCar.wheels(randomElement);
        myCar.showCar();



    }
}