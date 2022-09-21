package org.example.model;

import java.util.List;

public interface Building {

    List<Floor> getFloors();

    Elevator getElevator();

    void moveElevator();
}
