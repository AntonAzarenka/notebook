package com.azarenka.exeptions;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 01/12/2023
 *
 * @author Anton Azarenka
 */
public class FileAppDataNotFoundException extends RuntimeException{

    public FileAppDataNotFoundException(String message) {
        super(message);
    }

    public FileAppDataNotFoundException(Throwable cause) {
        super(cause);
    }
}
