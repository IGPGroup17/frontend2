package com.example.personalprofile.repositories.event;

import com.example.personalprofile.models.Event;

import java.util.Comparator;

/**
 * wow 2 design patterns in one class name look at me go this isnt confusing at all
 *
 * it's a factory thing that returns a different strategy based on input
 */
public class EventSortingComparatorFactory {

    public static Strategy getStrategyFrom(String name) {
        switch (name) {
            case "DATE":
                return new Date();
            case "LOCATION":
                return new Location();
            case "POPULARITY":
                return new Popularity();
            default:
                return null;
        }
    }

    public interface Strategy {
        Comparator<Event> comparator();
    }

    public static class Location implements Strategy {

        @Override
        public Comparator<Event> comparator() {
            return null;
        }
    }

    public static class Date implements Strategy {

        @Override
        public Comparator<Event> comparator() {
            return null;
        }
    }

    public static class Popularity implements Strategy {

        @Override
        public Comparator<Event> comparator() {
            return (event1, event2) -> event1.getLikes() - event2.getLikes();
        }
    }
}
