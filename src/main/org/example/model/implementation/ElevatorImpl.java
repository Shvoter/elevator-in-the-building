package org.example.model.implementation;

import org.example.model.Elevator;
import org.example.model.Passenger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ElevatorImpl implements Elevator {

    private final int sizeOfElevator;
    private int currentNumberOfFloor;

    private Direction direction;
    private final List<Passenger> passengers;

    public ElevatorImpl(int sizeOfElevator, Direction direction) {
        this.sizeOfElevator = sizeOfElevator;
        this.direction = direction;

        passengers = new ArrayList<>();
    }

    @Override
    public List<Passenger> getPassengers() {
        return passengers;
    }

    @Override
    public void addPassengers(List<Passenger> newPassengers) {
        passengers.addAll(newPassengers);
    }

    @Override
    public int getSizeOfElevator() {
        return sizeOfElevator;
    }

    @Override
    public int getCurrentNumberOfFloor() {
        return currentNumberOfFloor;
    }

    @Override
    public  void  setCurrentNumberOfFloor(int numberOfFloor) {
        this.currentNumberOfFloor = numberOfFloor;
    }

    @Override
    public List<Passenger> getAndDeletePassengersToMoveToFloor() {
        List<Passenger> passengersToMove = passengers.stream()
                .filter(p -> p.getNumberOfNextFloor() == currentNumberOfFloor)
                .collect(Collectors.toList());

        passengers.removeAll(passengersToMove);

        return passengersToMove;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void  setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void move() {
        switch (direction) {
            case UP -> currentNumberOfFloor++;
            case DOWN -> currentNumberOfFloor--;
        }
    }

    @Override
    public int getNumberOfFreePlaceInElevator() {
        return Math.max(sizeOfElevator - passengers.size(), 0);
    }
}
