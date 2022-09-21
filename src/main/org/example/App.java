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
        int countOfFloors = MyUtils.getRandomNumber(5, 20);
        int countOfPassengers = MyUtils.getRandomNumber(20, 20);


        List<Floor> floors = MyUtils.demoGetFloors(countOfFloors, countOfPassengers);
        Elevator elevator = new ElevatorImpl(5, Direction.UP);
        Building building = new BuildingImpl(elevator, floors);

        ViewAsString view = new ViewAsString();

        MyUtils.demoPrintOfStages(200, building, view);
        /*MyUtils.demoInfinityPrint(building, view);*/


    }
}
