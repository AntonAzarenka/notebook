package com.azarenka.service.events;


import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;

/**
 * Interface for .. .
 * <p>
 * Copyright (C) 2024 antazarenko@gmail.com
 * <p>
 * Date: 07/10/2024
 *
 * @author Anton Azarenka
 */
public interface IEventProvider {

    void registerEvent(EventTypeEnum typeEnum, ChangeListener<? super Boolean> listener);

    Property getEvent(EventTypeEnum eventTypeEnum);
}
