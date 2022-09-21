package org.example.model.implementation;

import org.example.model.Passenger;

public class PassengerImpl implements Passenger {

    private int numberOfNextFloor;

    public PassengerImpl(int countOfFloor, int currentFloor) {
        setNumberOfNextFloor(countOfFloor, currentFloor);
    }

    @Override
    public int getNumberOfNextFloor() {
        return numberOfNextFloor;
    }

    @Override
    public void setNumberOfNextFloor(int countOfFloor, int currentFloor) {
        numberOfNextFloor = 1 + (int) (Math.random() * countOfFloor);
        if (numberOfNextFloor == currentFloor) setNumberOfNextFloor(countOfFloor, currentFloor);
    }
}
