package com.azarenka.service.events;

import com.azarenka.domain.properties.CommonProperties;
import com.azarenka.domain.properties.PasswordWindowProperties;
import com.azarenka.service.api.IOptionsManager;
import com.azarenka.service.events.password.PasswordResourceEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

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
    private EventProvider eventProvider;

    @PostConstruct
    public void init() {
        CommonProperties properties = optionsManager.getProperties();
        PasswordWindowProperties passwordWindowProperties = properties.getPasswordWindowProperties();
        var passwordResourceEvent =
            (PasswordResourceEvent) eventProvider.getEvent(EventTypeEnum.PASSWORD_RESOURCE_EVENT);
        passwordResourceEvent.setPasswordResourceWindowStatus(passwordWindowProperties.isLocked());
    }
}
