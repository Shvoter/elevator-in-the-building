package org.example;

import org.example.model.Building;
import org.example.model.Elevator;
import org.example.model.Floor;
import org.example.model.implementation.*;
import org.example.viewController.ViewAsString;


import java.util.List;

public class App
{
    public static void main( String[] args ) {
        int countOfFloors = MyUtils.getRandomNumber(5, 20); // you may change the range of floors that will be generated
        int countOfPassengers = MyUtils.getRandomNumber(0, 10); // you may change the range of passengers that will be generated
        int capacityOfElevator = 5; //you may change the capacity of the elevator

        String currentMode = "infinity mode"; //You may choose one of two mods: "infinity mode" or "iterations mode"
        int numberOfIterations = 100; //you may change count of iterations for "iterations mode"


        List<Floor> floors = MyUtils.demoGetFloors(countOfFloors, countOfPassengers);
        Elevator elevator = new ElevatorImpl(capacityOfElevator, Direction.UP);
        Building building = new BuildingImpl(elevator, floors);

        ViewAsString view = new ViewAsString();

        switch (currentMode) {
            case "infinity mode":
                MyUtils.demoInfinityPrint(building, view);
                break;
            case "iterations mode":
                MyUtils.demoPrintOfStages(numberOfIterations, building, view);
                break;
            default:
                System.out.println("Please ");
        }
    }
}
