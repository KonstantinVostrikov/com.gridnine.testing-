package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FlightFilter {
    public static List<Flight> filter(List<Flight> flights, Predicate<Flight>... predicates) {
        if (predicates.length == 0) {
            return flights;
        }

        Predicate<Flight> compositePredicate = predicates[0];

        if (predicates.length > 1) {
            for (int i = 1; i < predicates.length; i++) {
                compositePredicate = compositePredicate.and(predicates[i]);
            }
        }

        List<Flight> result = flights.stream().filter(compositePredicate).collect(Collectors.toList());
        return result;
    }


    static class BeforeNow implements Predicate<Flight> {
        LocalDateTime now = LocalDateTime.now();

        @Override
        public boolean test(Flight v) {

            return v.getSegments().stream().anyMatch(segment -> segment.getDepartureDate().isBefore(now));
        }
    }

    static class AfterNow implements Predicate<Flight> {
        LocalDateTime now = LocalDateTime.now();

        @Override
        public boolean test(Flight v) {

            return v.getSegments().stream().allMatch(segment -> segment.getDepartureDate().isAfter(now));
        }
    }

    static class ArrivalBeforeDeparture implements Predicate<Flight> {

        @Override
        public boolean test(Flight v) {

            return v.getSegments().stream().anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate()));
        }
    }

    static class ArrivalAfterDeparture implements Predicate<Flight> {

        @Override
        public boolean test(Flight v) {

            return v.getSegments().stream().anyMatch(segment -> segment.getDepartureDate().isBefore(segment.getArrivalDate()));
        }
    }

    static class MoreThanSomeHoursOnTheGround implements Predicate<Flight> {
        private long hours;

        public MoreThanSomeHoursOnTheGround(long hours) {
            this.hours = hours;
        }

        @Override
        public boolean test(Flight v) {
            if (v.getSegments().size() <= 1) {
                return false;
            } else {
                return longerThanSomeHours(v.getSegments());
            }
        }

        private boolean longerThanSomeHours(List<Segment> segments) {
            long totalTime = 0;
            for (int j = 1; j < segments.size(); j++) {
                totalTime = totalTime + Duration.between(segments.get(j - 1).getArrivalDate(),
                        segments.get(j).getDepartureDate()).toHours();
            }
            return totalTime > hours;
        }
    }


    static class LessThanSomeHoursOnTheGround implements Predicate<Flight> {
        private long hours;

        public LessThanSomeHoursOnTheGround(long hours) {
            this.hours = hours;
        }

        @Override
        public boolean test(Flight v) {
            if (v.getSegments().size() <= 1) {
                return true;
            } else {
                return longerThanSomeHours(v.getSegments());
            }
        }

        private boolean longerThanSomeHours(List<Segment> segments) {
            long totalTime = 0;
            for (int j = 1; j < segments.size(); j++) {
                totalTime = totalTime + Duration.between(segments.get(j - 1).getArrivalDate(),
                        segments.get(j).getDepartureDate()).toHours();
            }
            return totalTime < hours;
        }
    }

    static class MoreThanSomeSegments implements Predicate<Flight> {
        private int segmentsNumber;

        public MoreThanSomeSegments(int segmentsNumber) {
            this.segmentsNumber = segmentsNumber;
        }

        @Override
        public boolean test(Flight v) {
            return moreThanSomeSegments(v.getSegments());
        }

        private boolean moreThanSomeSegments(List<Segment> segments) {
            return segments.size() > segmentsNumber;
        }
    }
}

