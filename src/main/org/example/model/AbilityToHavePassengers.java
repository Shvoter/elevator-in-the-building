package org.example.model;

import java.util.List;


public interface AbilityToHavePassengers {

    List<Passenger> getPassengers();

    void addPassengers(List<Passenger> newPassengers);
}
