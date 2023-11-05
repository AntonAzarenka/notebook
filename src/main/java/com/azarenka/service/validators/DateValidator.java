package com.azarenka.service.validators;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 06/25/2023
 *
 * @author Anton Azarenka
 */
public class DateValidator {

    public static boolean validate(String value) {
        try{
            LocalDate.parse(value);
        } catch (DateTimeParseException e){
            return false;
        }
        return true;
    }
}
