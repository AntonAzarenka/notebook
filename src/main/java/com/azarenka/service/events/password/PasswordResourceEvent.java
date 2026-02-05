package com.azarenka.service.events.password;

import com.azarenka.service.events.IEvent;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;

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
public class PasswordResourceEvent extends SimpleBooleanProperty implements IEvent {

    private final List<ChangeListener<? super Boolean>> listeners = new ArrayList<>();
    private boolean isBind = false;

    /**
     * Contains status for Passwords window. Window locked if true, otherwise the window already unlocked.
     * it doesn't work if Passwords window doesn't contain passcode at all.
     */
    public PasswordResourceEvent() {
        super(true);
    }

    public SimpleBooleanProperty passwordResourceWindowStatusProperty() {
        return this;
    }

    public void setPasswordResourceWindowStatus(boolean passwordResourceWindowStatus) {
        this.set(passwordResourceWindowStatus);
    }

    public boolean getPasswordResourceWindowStatus() {
        return this.get();
    }

    @Override
    public void changeStatusEvent() {
        setPasswordResourceWindowStatus(!getPasswordResourceWindowStatus());
    }

    @Override
    public void addListener(ChangeListener<? super Boolean> var1) {
        listeners.add(var1);
        super.addListener(var1);
    }

    @Override
    public void bind() {
        isBind = true;
    }

    @Override
    public boolean isBind() {
        return isBind;
    }

    @Override
    public void unbind() {
        isBind = false;
        listeners.forEach(this::removeListener);
    }
}
