package com.virendra.exceptionhandler;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NotFoundException.class)
    public Response handleNotFoundException(NotFoundException ex) {
        log.info("Not found exception "+ ex.getMessage());
        return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
    }
}
