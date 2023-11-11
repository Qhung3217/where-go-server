package com.wherego.wheregoserver.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;

public class MapperUtils {
    public static <T> List<String> arrayToListString(Set<T> theObject, String methodName){
        List<String> listString = new ArrayList<>();
        for (T object : theObject){
            try {
                String result = object.getClass()
                        .getMethod(methodName)
                        .invoke(object)
                        .toString();

                listString.add(result);
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        return listString;
    }

    public static <T> Float arrayToFloatAverage(Set<T> theObject, String methodName){
        List<Integer> ratings = new ArrayList<>();
        for(T object : theObject){
            try {
                int result = Integer.parseInt(object.getClass()
                        .getMethod(methodName)
                        .invoke(object)
                        .toString());

                ratings.add(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        OptionalDouble average = ratings.stream().mapToDouble(rating -> rating).average();
        return (float) average.orElse(0);
    }



}
