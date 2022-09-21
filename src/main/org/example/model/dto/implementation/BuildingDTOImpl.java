package org.example.model.dto.implementation;

import org.example.model.Building;
import org.example.model.Floor;
import org.example.model.Passenger;
import org.example.model.dto.BuildingDTO;
import org.example.model.dto.ElevatorDTO;
import org.example.model.dto.FloorDTO;

import java.util.ArrayList;
import java.util.List;

public class BuildingDTOImpl implements BuildingDTO {

    ElevatorDTO elevatorDTO;
    List<FloorDTO> floorDTOS;


    public BuildingDTOImpl(Building building) {
        this.floorDTOS = new ArrayList<>();

        createDTOOfFloors(building.getFloors());
        this.elevatorDTO = new ElevatorDTOImpl(building.getElevator());
    }

    @Override
    public ElevatorDTO getElevatorDTO() {
        return elevatorDTO;
    }

    @Override
    public List<FloorDTO> getFloorDTOS() {
        return floorDTOS;
    }

    private void createDTOOfFloors(List<Floor> floors) {
        for (Floor floor : floors) {
            floorDTOS.add(new FloorDTOImpl(floor));
        }
    }
}
