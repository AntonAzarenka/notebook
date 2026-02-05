package com.azarenka.service.events.password;

import com.azarenka.service.events.IEvent;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;

/**
 * Contains status for setting up new passcode.
 * <p>
 * Copyright (C) 2024 antazarenko@gmail.com
 * <p>
 * Date: 07/12/2024
 *
 * @author Anton Azarenka
 */
@Component
public class ChangePasswordResourceWindowEvent extends SimpleBooleanProperty implements IEvent {

    private final List<ChangeListener<? super Boolean>> listeners = new ArrayList<>();
    private boolean isBind = false;

    public ChangePasswordResourceWindowEvent() {
        super(false);
    }

    public boolean isChangePasswordEventResourceWindow() {
        return get();
    }

    public SimpleBooleanProperty changePasswordEventResourceWindowProperty() {
        return this;
    }

    public void setChangePasswordEventResourceWindow(boolean changePasswordEventResourceWindow) {
        this.set(changePasswordEventResourceWindow);
    }

    @Override
    public void changeStatusEvent() {
        setChangePasswordEventResourceWindow(!isChangePasswordEventResourceWindow());
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
