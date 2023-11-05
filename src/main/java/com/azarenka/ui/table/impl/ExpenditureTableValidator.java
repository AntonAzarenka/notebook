package com.azarenka.ui.table.impl;

import com.azarenka.domain.finance.Expenditure;
import com.azarenka.service.validators.AmountValidator;
import com.azarenka.service.validators.DateValidator;
import com.azarenka.ui.util.Windows;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 06/25/2023
 *
 * @author Anton Azarenka
 */
public class ExpenditureTableValidator {

    public void validateDate(Expenditure item, String value) {
        if(DateValidator.validate(value)){
            item.setDate(LocalDate.parse(value));
        } else {
            Windows.showErrorMessage("Не правильный формат даты. Используйте YYYY-mm-dd");
        }
    }

    public void validateAmount(Expenditure item, String value) {
        if(AmountValidator.validate(value)){
            item.setAmount(new BigDecimal(value));
        } else {
            Windows.showErrorMessage("Не правильный формат. Используйте только цифры");
        }
    }
}
