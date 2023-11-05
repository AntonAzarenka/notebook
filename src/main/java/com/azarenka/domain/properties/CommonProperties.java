package com.azarenka.domain.properties;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 01/12/2023
 *
 * @author Anton Azarenka
 */
public class CommonProperties implements Serializable, IProperties {

    private PasswordWindowProperties passwordWindowProperties = new PasswordWindowProperties();

    public PasswordWindowProperties getPasswordWindowProperties() {
        return passwordWindowProperties;
    }

    public void setPasswordWindowProperties(PasswordWindowProperties passwordWindowProperties) {
        this.passwordWindowProperties = passwordWindowProperties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CommonProperties that = (CommonProperties) o;

        return new EqualsBuilder().append(passwordWindowProperties,
            that.passwordWindowProperties).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(passwordWindowProperties).toHashCode();
    }
}
