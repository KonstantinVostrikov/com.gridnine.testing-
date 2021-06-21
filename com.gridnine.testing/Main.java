package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flightList = FlightBuilder.createFlights();


        List<Flight> flightsBeforeNow = FlightFilter.filter(flightList,  new FlightFilter.BeforeNow());
        for (Flight flight : flightsBeforeNow){
            System.out.println(flight);
        }
        System.out.println();


        List<Flight> flightsArrivalBeforeDeparture = FlightFilter.filter(flightList,  new FlightFilter.ArrivalBeforeDeparture());
        for (Flight flight : flightsArrivalBeforeDeparture){
            System.out.println(flight);
        }
        System.out.println();

        List<Flight> flightsMoreThanTwoHours = FlightFilter.filter(flightList,  new FlightFilter.MoreThanSomeHoursOnTheGround(2));
        for (Flight flight : flightsMoreThanTwoHours){
            System.out.println(flight);
        }
        System.out.println();

    }


}
