package com.example.personalprofile.repositories.eventsearch;

import com.example.personalprofile.models.Event;

import java.util.ArrayList;
import java.util.List;

public class EventFilterFactory {

    public static Filter getFilterFrom(String name) {
        switch (name) {
            case "In-person":
                return new InPerson();
            case "Alcohol-free":
                return new IsAlcoholFree();
            case "Virtual":
                return new IsVirtual();
            default:
                return null;
        }
    }


    public static class InPerson implements Filter {

        @Override
        public boolean filter(Event event) {
            return !event.isInPerson();
        }
    }

    public static class IsVirtual implements Filter {

        @Override
        public boolean filter(Event event) {
            return !event.isVirtual();
        }
    }

    public static class IsAlcoholFree implements Filter {
        @Override
        public boolean filter(Event event) {
            return !event.isAlcoholFree();
        }
    }


    public interface Filter {
        boolean filter(Event event);
    }
}
