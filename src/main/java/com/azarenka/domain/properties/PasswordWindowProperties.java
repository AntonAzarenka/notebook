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
public class PasswordWindowProperties implements Serializable, IProperties {

    private boolean isLocked;
    private boolean isHiddenData;
    private String password;

    public boolean isHiddenData() {
        return isHiddenData;
    }

    public void setHiddenData(boolean hiddenData) {
        isHiddenData = hiddenData;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PasswordWindowProperties that = (PasswordWindowProperties) o;

        return new EqualsBuilder().append(isLocked, that.isLocked)
            .append(password, that.password)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(isLocked).append(password).toHashCode();
    }
}
