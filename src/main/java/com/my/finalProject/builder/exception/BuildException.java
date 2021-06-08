package com.my.finalProject.builder.exception;

public class BuildException extends RuntimeException {
    public BuildException(String message, Throwable cause){
        super(message, cause);
    }
}
