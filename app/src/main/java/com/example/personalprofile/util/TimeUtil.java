package com.example.personalprofile.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import lombok.experimental.UtilityClass;


@UtilityClass
public class TimeUtil {

    public String toTime(String time) {
        return time;
    }

    public List<String> getTimesInIntervals() {
        List<String> list = new ArrayList<>();
        for (int hour = 0; hour < 24; hour++) {

            for (int min = 0; min < 2; min++) {
                String time = String.format(Locale.ENGLISH, "%02d", hour) + ":" +
                        String.format(Locale.ENGLISH, "%02d", min * 30);
                list.add(time);
            }
        }
        return list;
    }
}
