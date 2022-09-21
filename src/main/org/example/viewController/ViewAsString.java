package org.example.viewController;

import org.example.model.dto.BuildingDTO;
import org.example.model.dto.ElevatorDTO;
import org.example.model.dto.FloorDTO;
import org.example.model.dto.PassengerDTO;
import org.example.model.implementation.Direction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewAsString {

    BuildingDTO buildingDTO;
    int numberOfStep;

    public ViewAsString() {
        this.numberOfStep = 1;
    }

    private void setBuildingDTO(BuildingDTO buildingDTO) {
        this.buildingDTO = buildingDTO;
    }

    public String getView(BuildingDTO buildingDTO) {
        setBuildingDTO(buildingDTO);
        StringBuilder viewBuilder = new StringBuilder();
        viewBuilder
                .append("Step ")
                .append(numberOfStep)
                .append(" -----------------------------------------------------------\n\n");

        numberOfStep++;

        for (int i = buildingDTO.getFloorDTOS().size() - 1; i >= 0; i--) {
            viewBuilder
                    .append(getViewOfFloor(buildingDTO.getFloorDTOS().get(i)))
                    .append("\n");
        }

        return viewBuilder.toString();
    }

    private StringBuilder getViewOfFloor(FloorDTO floorDTO) {
        StringBuilder viewOfFloorBuilder = new StringBuilder();

        int maxSizeOfViewOfPassenger = 4;
        int maxSizeOfViewOfAllPassengers = getMaxCountOfPeopleOnFloor() * maxSizeOfViewOfPassenger;

        viewOfFloorBuilder
                .append(getViewOfDirection(floorDTO.getNeededDirectionsOfElevator()))
                .append("[")
                .append(getViewOfAllPassengers(floorDTO.getPassengerDTOS(), maxSizeOfViewOfAllPassengers))
                .append("]");


        if (floorDTO.getNumberOfFloor() == buildingDTO.getElevatorDTO().getCurrentNumberOfFloor()) {
            viewOfFloorBuilder.append(getViewOfElevator(buildingDTO.getElevatorDTO()));
        }

        return viewOfFloorBuilder;
    }

    private StringBuilder getViewOfElevator(ElevatorDTO elevatorDTO) {
        return new StringBuilder()
                .append("{")
                .append(getViewOfAllPassengers(elevatorDTO.getPassengerDTOS(), elevatorDTO.getSizeOfElevator()))
                .append("}")
                .append(getViewOfDirection(new ArrayList<>(Collections.singletonList(elevatorDTO.getDirection()))));
    }

    private StringBuilder getViewOfAllPassengers(List<PassengerDTO> passengers, int maxSizeOfViewOfAllPassengers) {
        StringBuilder viewOfAllPassengers = new StringBuilder();

        for (PassengerDTO passengerDTO : passengers) {
            viewOfAllPassengers.append(getViewOfPassenger(passengerDTO));
        }

        return getViewOfAllPassengersCastedToMaxSize(viewOfAllPassengers, maxSizeOfViewOfAllPassengers);
    }

    private String getViewOfPassenger(PassengerDTO passengerDTO) {
        return "(" + passengerDTO.getNumberOfNextFloor() + ")";
    }

    private StringBuilder getViewOfAllPassengersCastedToMaxSize(StringBuilder viewOfAllPassengers,
                                                         int maxSizeOfViewOfAllPassengers) {

        for (int i = viewOfAllPassengers.length(); i < maxSizeOfViewOfAllPassengers; i++) {
            viewOfAllPassengers.append(" ");
        }

        return viewOfAllPassengers;
    }

    private String getViewOfDirection(List<Direction> direction) {
        return switch (direction.size()) {
            case 0 -> "  ";
            case 1 -> direction.contains(Direction.UP) ? " ^" : "v ";
            default -> "v^";
        };
    }

    private int getMaxCountOfPeopleOnFloor() {
        int maxSizeOfFloor = 0;
        List<FloorDTO> floorDTOS = buildingDTO.getFloorDTOS();

        for (FloorDTO floorDTO : floorDTOS) {
            int sizeCurrentFloor = floorDTO.getPassengerDTOS().size();
            if (maxSizeOfFloor < sizeCurrentFloor) maxSizeOfFloor = sizeCurrentFloor;
        }


        return maxSizeOfFloor;
    }
}
