package com.azarenka.service.event.password;

import com.azarenka.service.event.EventHandlerProvider;

import org.springframework.stereotype.Component;

import javafx.beans.property.SimpleBooleanProperty;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 06/28/2023
 *
 * @author Anton Azarenka
 */
@Component
public class PasswordResourceEvent{

    /**
     * Contains status for Passwords window. Window locked if true, otherwise the window already unlocked.
     * it doesn't work if Passwords window doesn't contain passcode at all.
     */
    private final SimpleBooleanProperty passwordResourceWindowStatus = new SimpleBooleanProperty(true);

    public SimpleBooleanProperty passwordResourceWindowStatusProperty() {
        return passwordResourceWindowStatus;
    }

    public void setPasswordResourceWindowStatus(boolean passwordResourceWindowStatus) {
        this.passwordResourceWindowStatus.set(passwordResourceWindowStatus);
    }

    public boolean getPasswordResourceWindowStatus() {
        return passwordResourceWindowStatus.get();
    }
}
