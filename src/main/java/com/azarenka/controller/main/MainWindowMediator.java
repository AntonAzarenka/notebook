package com.azarenka.controller.main;

import com.azarenka.WindowsProvider;
import com.azarenka.controller.AbstractMediator;
import com.azarenka.domain.properties.CommonProperties;
import com.azarenka.domain.properties.PasswordWindowProperties;
import com.azarenka.javafx.SceneChanger;
import com.azarenka.service.LockWindowManager;
import com.azarenka.service.api.IOptionsManager;
import com.azarenka.ui.scene.PasswordsWindow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
public class MainWindowMediator extends AbstractMediator {

    public boolean isLockPasswordResourceWindow() {
        CommonProperties properties = getOptionsManager().getProperties();
        PasswordWindowProperties passwordWindowProperties = properties.getPasswordWindowProperties();
        if (passwordWindowProperties.isLocked()) {
            getLockWindowManager().setExecutableType(PasswordsWindow.class);
            getSceneChanger().showModalWindow(getWindowsProvider().getPasswordModalWindow());
        }
        return !passwordWindowProperties.isLocked();
    }
}
