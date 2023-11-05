package com.azarenka.domain.finance;

import com.azarenka.domain.AbstractEntity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 03/19/2023
 *
 * @author Anton Azarenka
 */
public class Expenditure extends AbstractEntity {

    private String name;
    private BigDecimal amount;
    private LocalDate date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Expenditure that = (Expenditure) o;

        return new EqualsBuilder().append(name, that.name)
            .append(amount, that.amount)
            .append(date, that.date)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(name).append(amount).append(date).toHashCode();
    }

    @Override
    public String toString() {
        return "Expenditure{" +
            "name='" + name + '\'' +
            ", amount=" + amount +
            ", date=" + date +
            '}';
    }
}
