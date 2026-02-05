package com.azarenka.service.events.finance;

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
 * Date: 06/29/2023
 *
 * @author Anton Azarenka
 */
@Component
public class ExpenditureEditEvent extends SimpleBooleanProperty implements IEvent {

    private final List<ChangeListener<? super Boolean>> listeners = new ArrayList<>();
    private boolean isBind = false;

    public ExpenditureEditEvent() {
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
