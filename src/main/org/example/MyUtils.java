package org.example;

import org.example.model.Building;
import org.example.model.Elevator;
import org.example.model.Floor;
import org.example.model.Passenger;
import org.example.model.dto.implementation.BuildingDTOImpl;
import org.example.model.implementation.*;
import org.example.viewController.ViewAsString;

import java.util.ArrayList;
import java.util.List;

public class MyUtils {
    public static int getRandomNumber(int minInclusive, int maxInclusive) {
        return (int) (Math.random() * (maxInclusive - minInclusive + 1)) + minInclusive;
    }

    public static List<Passenger> getListOfPassengers(int countOfPassengers,int countOfFloors, int numberOfCurrentFloor) {
        List<Passenger> passengers = new ArrayList<>();

        for (int i = 0; i < countOfPassengers; i++) {
            passengers.add(new PassengerImpl(countOfFloors, numberOfCurrentFloor));
        }
        return passengers;
    }

    public  static List<Floor> demoGetFloors(int countOfFloors, int countOfPassengers) {
        List<Floor> floors = new ArrayList<>();

        int countOfUnassignedPassengers = countOfPassengers;
        for (int currentFloor = 1; currentFloor <= countOfFloors; currentFloor++) {

            int countOfPassengersOnCurrentFloor = MyUtils.getRandomNumber(0, countOfUnassignedPassengers);

            List<Passenger> passengersToMoveToCurrentFloor = MyUtils.getListOfPassengers(
                    countOfPassengersOnCurrentFloor,
                    countOfFloors,
                    currentFloor
            );

            floors.add( new FloorImpl(currentFloor, passengersToMoveToCurrentFloor));

            countOfUnassignedPassengers -= passengersToMoveToCurrentFloor.size();
        }

        Elevator elevator = new ElevatorImpl(5, Direction.UP);
        Building building = new BuildingImpl(elevator, floors);
        ViewAsString view = new ViewAsString();
        return floors;
    }

    public static void demoPrintOfStages(int countOfStages, Building building, ViewAsString view) {
        int countOfCurrentStage = 0;
        while (countOfCurrentStage < countOfStages) {
            printStage(building, view);
            countOfCurrentStage++;
        }
    }

    public static void demoInfinityPrint(Building building, ViewAsString view) {
        int countOfCurrentStage = 0;
        int countOfStages = Integer.MAX_VALUE;

        while(countOfCurrentStage < countOfStages) {
            printStage(building, view);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            countOfCurrentStage++;
        }
    }

    private static void printStage(Building building , ViewAsString view) {
        System.out.println(view.getView(new BuildingDTOImpl(building)));
        building.moveElevator();
    }
}
