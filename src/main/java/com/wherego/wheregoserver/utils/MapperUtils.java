package com.wherego.wheregoserver.utils;

import com.wherego.wheregoserver.dto.ReviewDetailDto;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class MapperUtils {
    public static <T> List<String> arrayToListString(Set<T> theObject, String methodChain) {
        List<String> listString = new ArrayList<>();
        for (T object : theObject) {
            String result = invokeMethod(object, methodChain);
            listString.add(result);
        }
        return listString;
    }

    public static <T> Float arrayToFloatAverage(Set<T> theObject, String methodChain) {
        List<Integer> ratings = new ArrayList<>();
        for (T object : theObject) {
            int result = invokeMethodInt(object, methodChain);
            ratings.add(result);
        }
        OptionalDouble average = ratings.stream().mapToDouble(rating -> rating).average();
        return (float) average.orElse(0);
    }

    public static <T> Set<ReviewDetailDto> objectReviewToReviewDetail(Set<T> reviews) {
        Set<ReviewDetailDto> result = new HashSet<>();
        for (T review : reviews) {
            ReviewDetailDto reviewDetail = new ReviewDetailDto();
            reviewDetail.setId(invokeMethodLong(review, "getId"));
            reviewDetail.setComment(invokeMethod(review, "getComment"));
            reviewDetail.setRating(invokeMethodInt(review, "getRating"));
            reviewDetail.setTravelerName(invokeMethod(review, "getTraveler.getName"));
            reviewDetail.setTravelerAvatar(invokeMethod(review, "getTraveler.getAvatar"));
            result.add(reviewDetail);
        }
        return result;
    }

    private static <T> String invokeMethod(T object, String methodChain) {
        try {
            String[] methods = methodChain.split("\\.");
            Object result = object;
            for (String methodName : methods) {
                result = result.getClass().getMethod(methodName).invoke(result);
            }
            return result.toString();
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> int invokeMethodInt(T object, String methodChain) {
        return Integer.parseInt(invokeMethod(object, methodChain));
    }

    private static <T> Long invokeMethodLong(T object, String methodChain) {
        return Long.parseLong(invokeMethod(object, methodChain));
    }


}
