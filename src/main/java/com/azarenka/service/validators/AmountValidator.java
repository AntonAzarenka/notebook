package com.azarenka.service.validators;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 06/26/2023
 *
 * @author Anton Azarenka
 */
public class AmountValidator {

    public static boolean validate(String value) {
        try{
            new BigDecimal(value);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }
}
