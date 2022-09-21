package org.example.model.implementation;

import org.example.model.Floor;
import org.example.model.Passenger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FloorImpl implements Floor {

    private final int numberOfFloor;

    private final List<Passenger> passengersMoveUp;
    private final List<Passenger> passengersMoveDown;
    private final List<Direction> directions;

    public FloorImpl(int numberOfFloor, List<Passenger> passengers) {
        this.numberOfFloor = numberOfFloor;
        this.passengersMoveDown = new ArrayList<>();
        this.passengersMoveUp = new ArrayList<>();
        this.directions = new ArrayList<>();

        addPassengers(passengers);
    }

    @Override
    public List<Passenger> getPassengers() {
        List<Passenger> passengers = new ArrayList<>();
        passengers.addAll(passengersMoveUp);
        passengers.addAll(passengersMoveDown);

        return passengers;
    }

    @Override
    public void addPassengers(List<Passenger> newPassengers) {
        for (Passenger newPassenger : newPassengers) {
            Direction directionOfPassenger;

            if (newPassenger.getNumberOfNextFloor() -  numberOfFloor > 0) {
                directionOfPassenger = Direction.UP;
                passengersMoveUp.add(newPassenger);
            } else {
                directionOfPassenger = Direction.DOWN;
                passengersMoveDown.add(newPassenger);
            }

            if (!directions.contains(directionOfPassenger)) directions.add(directionOfPassenger);
        }
    }

    @Override
    public List<Passenger> getAndDeletePassengersToMoveToElevator(int numberPlaceForPassengers, Direction directionOfElevator) {
        List<Passenger> passengers = directionOfElevator == Direction.UP ? passengersMoveUp : passengersMoveDown;
        List<Passenger> passengersToMove = getPassengersToMove(passengers, numberPlaceForPassengers);

        passengers.removeAll(passengersToMove);
        if (passengers.size() == 0) directions.remove(directionOfElevator);

        return passengersToMove;
    }

    @Override
    public List<Direction> getNeededDirectionsOfElevator() {
        return directions;
    }

    @Override
    public int getNumberOfFloor() {
        return numberOfFloor;
    }

    @Override
    public Direction getMostCommonDirectionToMove() {
        return passengersMoveUp.size() - passengersMoveDown.size() > 0? Direction.UP : Direction.DOWN;
    }

    private List<Passenger> getPassengersToMove(List<Passenger> passengers, int numberOfPassengers) {
        return passengers.stream()
                .limit(numberOfPassengers)
                .collect(Collectors.toList());
    }
}
