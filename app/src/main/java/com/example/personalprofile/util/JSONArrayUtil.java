package com.example.personalprofile.util;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JSONArrayUtil {

    public <T> List<T> toList(JSONArray array, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                list.add(CastFactory.cast(array.get(i), clazz));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return list;
    }


    public List<Object> toList(JSONArray array) {
        List<Object> list = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            try {
                list.add(array.get(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
}
