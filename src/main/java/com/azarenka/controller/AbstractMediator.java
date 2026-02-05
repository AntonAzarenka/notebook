package com.azarenka.controller;

import com.azarenka.WindowsProvider;
import com.azarenka.javafx.SceneChanger;
import com.azarenka.service.LockWindowManager;
import com.azarenka.service.api.IOptionsManager;
import com.azarenka.service.events.EventProvider;
import com.azarenka.service.events.IEventProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 02/10/2023
 *
 * @author Anton Azarenka
 */
@Component
public class AbstractMediator {

    @Autowired
    private IOptionsManager optionsManager;
    @Autowired
    private SceneChanger sceneChanger;
    @Autowired
    private WindowsProvider windowsProvider;
    @Autowired
    private LockWindowManager lockWindowManager;
    @Autowired
    private IEventProvider eventProvider;

    public IEventProvider getEventHandlerProvider() {
        return eventProvider;
    }

    public IOptionsManager getOptionsManager() {
        return optionsManager;
    }

    public SceneChanger getSceneChanger() {
        return sceneChanger;
    }

    public WindowsProvider getWindowsProvider() {
        return windowsProvider;
    }

    public LockWindowManager getLockWindowManager() {
        return lockWindowManager;
    }
}
