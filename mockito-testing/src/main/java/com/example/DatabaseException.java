package com.example;

public class DatabaseException extends Throwable {
    public DatabaseException(String saveFailed) {
        super(saveFailed);
    }
}