package severstal.zorin.homework.car;

public class Car {

    public String model;
    public double modelRevision;
    public boolean automaticTransmission;
    public String color;

    public Car(String model, double modelRevision) {
        this.model = model;
        this.modelRevision = modelRevision;
    }

    void wheels(Wheels wheels) {
        if (wheels.wheelsType.equals("round")) {
            System.out.println("Car can drive. Wheels -> " + wheels.wheelsType);
        }
        else
            System.out.println("Car can't drive. Wheels -> " + wheels.wheelsType);
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void showCar() {
        System.out.println("Yor car: "  + "\n Model - " + model + "\n Model revision - "
                + modelRevision + "\n Automatic transmission - " + automaticTransmission
                + "\n Color - " + color);
    }
}