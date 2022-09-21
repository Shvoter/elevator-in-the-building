package org.example.model.dto;

import org.example.model.Elevator;

import java.util.List;

public interface BuildingDTO {

    ElevatorDTO getElevatorDTO();

    List<FloorDTO> getFloorDTOS();


}
