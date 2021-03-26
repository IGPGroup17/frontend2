package com.example.personalprofile.repositories.eventsearch;

import com.example.personalprofile.models.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

/**
 * wow 2 design patterns in one class name look at me go this isnt confusing at all
 *
 * it's a factory thing that returns a different strategy based on input
 */
public class EventSortingComparatorFactory {

    public static Strategy getStrategyFrom(String name) {
        switch (name) {
            case "Alphabetically (A - Z)":
                return new AlphabetStrategy();
            case "Alphabetically (Z - A)":
                return new AlphabetReverseStrategy();
            case "Likes (Most - Least)":
                return new PopularityStrategy();
            case "Likes (Least - Most)":
                return new PopularityReverseStrategy();
            case "Date":
                return new DateStrategy();

            default:
                return null;
        }
    }

    public interface Strategy {
        Comparator<Event> comparator();
    }

    public static class DateStrategy implements Strategy {

        private Date toDate(String date) throws ParseException {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.ENGLISH);
            return format.parse(date);
        }
        @Override
        public Comparator<Event> comparator() {
            return (event1, event2) -> {
                try {
                    Date date1 = toDate(event1.getScheduledTime());
                    Date date2 = toDate(event2.getScheduledTime());

                    return date1.compareTo(date2);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            };
        }
    }
    public static class AlphabetStrategy implements Strategy {

        @Override
        public Comparator<Event> comparator() {
            return (event1, event2) -> event1.getName().compareTo(event2.getName());
        }
    }

    public static class AlphabetReverseStrategy implements Strategy {

        @Override
        public Comparator<Event> comparator() {
            return (event1, event2) -> event2.getName().compareTo(event1.getName());
        }
    }

    public static class PopularityStrategy implements Strategy {

        @Override
        public Comparator<Event> comparator() {
            return (event1, event2) -> event2.getLikes() - event1.getLikes();
        }
    }

    public static class PopularityReverseStrategy implements Strategy {

        @Override
        public Comparator<Event> comparator() {
            return (event1, event2) -> event1.getLikes() - event2.getLikes();
        }
    }
}
