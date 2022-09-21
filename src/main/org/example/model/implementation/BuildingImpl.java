package org.example.model.implementation;

import org.example.model.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BuildingImpl implements Building {

    Elevator elevator;
    List<Floor> floors;

    public BuildingImpl(Elevator elevator, List<Floor> floors) {
        this.floors = floors;
        this.elevator = elevator;

        setStartNumberOfFloorOfElevator();
        setStartDirectionOfElevator();
    }

    @Override
    public List<Floor> getFloors() {
        return floors;
    }

    @Override
    public Elevator getElevator() {
        return elevator;
    }

    @Override
    public void moveElevator() {
        int countOfPassengerInAllFloors = floors.stream()
                .map(Floor::getPassengers)
                .mapToInt(l -> l.size())
                .sum();
        int countOfPassengerInElevator = elevator.getPassengers().size();

        if (countOfPassengerInAllFloors + countOfPassengerInElevator == 0) {
            return;
        }

        Floor currentFloor = getFloorByNumber(elevator.getCurrentNumberOfFloor());
        List<Passenger> passengersToFloor = elevator.getAndDeletePassengersToMoveToFloor();

        if (isElevatorEmpty() && isFloorEmpty(currentFloor)) {
            setNewDirectionInEmptyCaseForElevator(floors.indexOf(currentFloor));
        } else if (isElevatorEmpty()) {
            elevator.setDirection(currentFloor.getMostCommonDirectionToMove());
        }

        List<Passenger> passengersToElevator =
                currentFloor.getAndDeletePassengersToMoveToElevator(
                        elevator.getNumberOfFreePlaceInElevator(),
                        elevator.getDirection()
                );

        int countOfFloors = floors.size();
        for (Passenger passenger : passengersToFloor) {
            passenger.setNumberOfNextFloor(countOfFloors, currentFloor.getNumberOfFloor());
        }

        currentFloor.addPassengers(passengersToFloor);
        elevator.addPassengers(passengersToElevator);
        elevator.move();
    }

    private void setStartNumberOfFloorOfElevator() {
        elevator.setCurrentNumberOfFloor(floors.get(0).getNumberOfFloor());
    }

    private void setStartDirectionOfElevator() {
        elevator.setDirection(Direction.UP);
    }

    private boolean isElevatorEmpty() {
        return  elevator.getSizeOfElevator() == elevator.getNumberOfFreePlaceInElevator();
    }

    private Floor getFloorByNumber(int numberOfFloor) {
        return floors.get(numberOfFloor - 1);
    }

    private void setNewDirectionInEmptyCaseForElevator(int indexOfCurrentFloor) {
        switch (elevator.getDirection()) {
            case UP -> setNewDirectionInEmptyCaseForElevatorWhenCommonDirectionIsUp(indexOfCurrentFloor);
            case DOWN -> setNewDirectionInEmptyCaseForElevatorWhenCommonDirectionIsDown(indexOfCurrentFloor);
        }
    }

    private void setNewDirectionInEmptyCaseForElevatorWhenCommonDirectionIsUp(int indexOfCurrentFloor) {

        for (int i = indexOfCurrentFloor; i < floors.size(); i++) {
            if (!isFloorEmpty(floors.get(i))) return;
        }
        elevator.setDirection(Direction.DOWN);
    }

    private void setNewDirectionInEmptyCaseForElevatorWhenCommonDirectionIsDown(int indexOfCurrentFloor) {

        for (int i = indexOfCurrentFloor; i >= 0; i--) {
            if (!isFloorEmpty(floors.get(i))) return;
        }
        elevator.setDirection(Direction.UP);
    }

    private boolean isFloorEmpty(Floor floor) {
        return floor.getNeededDirectionsOfElevator().size() == 0;
    }
}
