package com.azarenka.service.events;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;

/**
 * Represents of class which change status when any data was saved.
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 11/05/2023
 *
 * @author Anton Azarenka
 */
@Component
public class SaveDataEvent extends SimpleBooleanProperty implements IEvent{

    private final List<ChangeListener<? super Boolean>> listeners = new ArrayList<>();
    private boolean isBind = false;

    public SaveDataEvent() {
        super(false);
    }

    public boolean isSaveDataProperty() {
        return this.get();
    }

    public void setSaveDataProperty(boolean saveDataProperty) {
        this.set(saveDataProperty);
    }

    public void changeStatusEvent() {
        setSaveDataProperty(!isSaveDataProperty());
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
