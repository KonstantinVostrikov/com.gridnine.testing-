package com.gridnine.testing;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class FlightFilterTest {
    private static List<Flight> flightList;


    @BeforeClass
    public static void createFlightList(){
        //creation of list, which contains 6 flights. One of them departs before now. One of them arrive before departure. Two of them have more than 2 hours on the ground.
        flightList = FlightBuilder.createFlights();
    }

    @Test
    public void flightsBeforeNowFilterShouldFilter(){

        //creation of list that is the result of the filter afterNowFlights
        List<Flight> flightsBeforeNow = FlightFilter.filter(flightList,  new FlightFilter.BeforeNow());

        //size of flightsBeforeNow should be 1
        Assert.assertEquals(1, flightsBeforeNow.size());
    }

    @Test
    public void flightsArrivalBeforeDepartureShouldFilter() {

        //creation of list that is the result of the filter arrivalBeforeDeparture
        List<Flight> flightsArrivalBeforeDeparture = FlightFilter.filter(flightList,  new FlightFilter.ArrivalBeforeDeparture());

        //size of flightsArrivalBeforeDeparture should be 1
        Assert.assertEquals(1, flightsArrivalBeforeDeparture.size());

    }

    @Test
    public void flightsMoreThanTwoHoursShouldFilter() {

        //creation of list that is the result of the filter moreThanTwoHours
        List<Flight> flightsMoreThanTwoHours = FlightFilter.filter(flightList,  new FlightFilter.MoreThanSomeHoursOnTheGround(2));

        //size of flightsMoreThanTwoHours should be 2
        Assert.assertEquals(2, flightsMoreThanTwoHours.size());

    }

    @Test
    public void flightsArrivalAfterDepartureShouldFilter() {

        //creation of list that is the result of the filter ArrivalAfterDeparture
        List<Flight> flightsArrivalAfterDeparture = FlightFilter.filter(flightList,  new FlightFilter.ArrivalAfterDeparture());

        //size of flightsArrivalAfterDeparture should be 5
        Assert.assertEquals(5, flightsArrivalAfterDeparture.size());

    }

    @Test
    public void flightsAfterNowShouldFilter() {

        //creation of list that is the result of the filter AfterNow
        List<Flight> flightsAfterNow = FlightFilter.filter(flightList,  new FlightFilter.AfterNow());

        //size of flightsAfterNow should be 5
        Assert.assertEquals(5, flightsAfterNow.size());

    }

    @Test
    public void flightsMoreThanSomeSegmentsShouldFilter() {

        //creation of list that is the result of the filter MoreThanSomeSegments
        List<Flight> flightsMoreThanSomeSegments = FlightFilter.filter(flightList,  new FlightFilter.MoreThanSomeSegments(1));

        //size of flightsMoreThanSomeSegments should be 3
        Assert.assertEquals(3, flightsMoreThanSomeSegments.size());

    }

    @Test
    public void flightsLessThanSomeHoursOnTheGroundShouldFilter() {

        //creation of list that is the result of the filter LessThanSomeHoursOnTheGround
        List<Flight> flightsLessThanSomeHoursOnTheGround = FlightFilter.filter(flightList,  new FlightFilter.LessThanSomeHoursOnTheGround(2));

        //size of flightsLessThanSomeHoursOnTheGround should be 3
        Assert.assertEquals(4, flightsLessThanSomeHoursOnTheGround.size());

    }

}
