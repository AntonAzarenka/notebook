package com.azarenka.service.events;

import com.azarenka.service.events.finance.ExpenditureEditEvent;
import com.azarenka.service.events.password.ChangePasswordResourceWindowEvent;
import com.azarenka.service.events.password.ChangePasswordStatusResourceWindowEvent;
import com.azarenka.service.events.password.PasswordResourceEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;

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
@SuppressWarnings("unchecked")
public class EventProvider implements IEventProvider {

    @Autowired
    private PasswordResourceEvent passwordResourceEvent;
    @Autowired
    private SaveDataEvent saveDataEvent;
    @Autowired
    private ChangePasswordStatusResourceWindowEvent changePasswordStatusOfResourceWindow;
    @Autowired
    private ChangePasswordResourceWindowEvent changePasswordResourceWindowEvent;
    @Autowired
    private ExpenditureEditEvent expenditureEditEvent;

    private Map<EventTypeEnum, Property> eventsMap = new HashMap<>();

    @PostConstruct
    public void init() {
        eventsMap.put(EventTypeEnum.SAVE_DATA_EVENT, saveDataEvent);
        eventsMap.put(EventTypeEnum.PASSWORD_RESOURCE_EVENT, passwordResourceEvent);
        eventsMap.put(EventTypeEnum.CHANGE_PASSW_STATUS_RESOURCE_EVENT, changePasswordStatusOfResourceWindow);
        eventsMap.put(EventTypeEnum.CHANGE_PASSWORD_RESOURCE_WINDOW_EVENT, changePasswordResourceWindowEvent);
        eventsMap.put(EventTypeEnum.EXPENDITURE_EDIT_EVENT, expenditureEditEvent);
    }

    public SaveDataEvent getSaveDataEvent() {
        return saveDataEvent;
    }

    @Override
    public void registerEvent(EventTypeEnum typeEnum, ChangeListener<? super Boolean> listener) {
        IEvent property = (IEvent) eventsMap.get(typeEnum);
        property.addListener(listener);
        property.bind();
    }

    @Override
    public Property getEvent(EventTypeEnum eventTypeEnum) {
        return eventsMap.get(eventTypeEnum);
    }
}
