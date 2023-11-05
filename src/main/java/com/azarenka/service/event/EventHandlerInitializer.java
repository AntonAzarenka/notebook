package com.azarenka.service.event;

import com.azarenka.domain.properties.CommonProperties;
import com.azarenka.domain.properties.PasswordWindowProperties;
import com.azarenka.service.api.IOptionsManager;
import com.azarenka.service.event.password.PasswordResourceEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 02/09/2023
 *
 * @author Anton Azarenka
 */
@Component
public class EventHandlerInitializer {

    @Autowired
    private IOptionsManager optionsManager;
    @Autowired
    private EventHandlerProvider eventHandlerProvider;

    @PostConstruct
    public void init() {
        CommonProperties properties = optionsManager.getProperties();
        PasswordWindowProperties passwordWindowProperties = properties.getPasswordWindowProperties();
        PasswordResourceEvent passwordResourceEvent = eventHandlerProvider.getPasswordResourceEvent();
        passwordResourceEvent.setPasswordResourceWindowStatus(passwordWindowProperties.isLocked());
    }
}
