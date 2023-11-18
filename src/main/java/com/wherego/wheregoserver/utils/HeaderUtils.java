package com.wherego.wheregoserver.utils;

public class HeaderUtils {
    public static String getToken(String authorizationHeader){
        return authorizationHeader.substring(7);
    }
}
