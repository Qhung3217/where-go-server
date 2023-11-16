package com.wherego.wheregoserver.exception;

import org.springframework.http.HttpStatus;

public interface BaseException {
    HttpStatus getStatus();
}
