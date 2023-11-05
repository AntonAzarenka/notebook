package com.azarenka.service.event;

import com.azarenka.service.event.password.PasswordResourceEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.beans.property.SimpleBooleanProperty;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 01/15/2023
 *
 * @author Anton Azarenka
 */
@Component
public class EventHandlerProvider {

    @Autowired
    private PasswordResourceEvent passwordResourceEvent;

    public PasswordResourceEvent getPasswordResourceEvent() {
        return passwordResourceEvent;
    }

    /**
     * Contains status for Password window. true if Window under passcode.
     */
    private final SimpleBooleanProperty changePasswordStatusOfResourceWindow = new SimpleBooleanProperty();
    /**
     * Contains status for set new passcode.
     */
    private final SimpleBooleanProperty changePasswordEventResourceWindow = new SimpleBooleanProperty(false);

    public SimpleBooleanProperty changePasswordStatusOfResourceWindowProperty() {
        return changePasswordStatusOfResourceWindow;
    }

    public void setChangePasswordStatusOfResourceWindow(boolean changePasswordStatusOfResourceWindow) {
        this.changePasswordStatusOfResourceWindow.set(changePasswordStatusOfResourceWindow);
    }



    public boolean isChangePasswordStatusOfResourceWindow() {
        return changePasswordStatusOfResourceWindow.get();
    }


    public boolean isChangePasswordEventResourceWindow() {
        return changePasswordEventResourceWindow.get();
    }

    public SimpleBooleanProperty changePasswordEventResourceWindowProperty() {
        return changePasswordEventResourceWindow;
    }

    public void setChangePasswordEventResourceWindow(boolean changePasswordEventResourceWindow) {
        this.changePasswordEventResourceWindow.set(changePasswordEventResourceWindow);
    }
}
