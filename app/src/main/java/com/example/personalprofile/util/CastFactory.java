package com.example.personalprofile.util;

import java.util.HashSet;
import java.util.Set;


// copy pasted from previous code here:
// https://github.com/OllieJonas/BTEConversion/blob/master/src/main/java/uk/buildtheearth/conversionplugin/util/CastFactory.java
@SuppressWarnings("unchecked")
public class CastFactory {

    private static final Set<String> YES_WORDS;
    private static final Set<String> NO_WORDS;

    static {
        YES_WORDS = new HashSet<>();
        NO_WORDS = new HashSet<>();

        YES_WORDS.add("true");
        YES_WORDS.add("t");
        YES_WORDS.add("y");
        YES_WORDS.add("yes");
        YES_WORDS.add("yeah");
        YES_WORDS.add("hellyeahmotherf*****");

        NO_WORDS.add("false");
        NO_WORDS.add("f");
        NO_WORDS.add("n");
        NO_WORDS.add("no");
        NO_WORDS.add("nope");
    }

    public static <T> T cast(Object input, Class<T> toCast) {
        if (!input.getClass().isAssignableFrom(toCast))
            throw new InvalidTypeException(input, toCast);

        return (T) input;
    }

    public static <T> T stringToObject(String input, Class<T> toCast) {
        if (toCast == String.class) {
            return (T) input;
        } else if (toCast == Integer.class) {
            return (T) toInteger(input);
        } else if (toCast == Float.class) {
            return (T) toFloat(input);
        } else if (toCast == Double.class) {
            return (T) toDouble(input);
        } else if (toCast == Long.class) {
            return (T) toLong(input);
        } else if (toCast == Boolean.class) {
            return (T) toBoolean(input);
        } else if (toCast == Character.class) {
            return (T) toChar(input);
        } else {
            throw new IllegalArgumentException("Invalid class to cast to!");
        }
    }

    private static Object toInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidTypeException(e, input, Integer.class);
        }
    }

    private static Object toFloat(String input) {
        try {
            return Float.parseFloat(input);
        } catch (NumberFormatException e) {
            throw new InvalidTypeException(e, input, Float.class);
        }
    }

    private static Object toDouble(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new InvalidTypeException(e, input, Double.class);
        }
    }

    private static Object toLong(String input) {
        try {
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new InvalidTypeException(e, input, Long.class);
        }
    }

    private static Object toBoolean(String input) {
        if (YES_WORDS.contains(input.toLowerCase()))
            return true;
        else if (NO_WORDS.contains(input.toLowerCase()))
            return false;
        else
            throw new InvalidTypeException(input, Boolean.class);
    }

    private static Object toChar(String input) {
        if (input.length() > 1)
            throw new InvalidTypeException(input, Character.class);

        return input.charAt(0);
    }

    public static class InvalidTypeException extends RuntimeException {

        public InvalidTypeException(IllegalArgumentException e, String input, Class<?> expectedCast) {
            super("Unable to cast " + input + " to " + expectedCast.getSimpleName() + "!");
        }

        public InvalidTypeException(String input, Class<?> expectedCast) {
            super("Unable to cast " + input + " to " + expectedCast.getSimpleName() + "!");
        }

        public InvalidTypeException(Object input, Class<?> expectedCast) {
            super ("Unable to cast " + input + " to " + expectedCast.getSimpleName() + "!");
        }

    }
}
