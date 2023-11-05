package com.azarenka.service;

import com.azarenka.domain.properties.CommonProperties;
import com.azarenka.domain.properties.PasswordWindowProperties;
import com.azarenka.javafx.load.CommonWidget;
import com.azarenka.service.impl.options.OptionsManager;
import com.azarenka.ui.scene.PasswordsWindow;
import com.azarenka.util.ApplicationUtil;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * Represents of for checking passwords by defined window.
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 01/15/2023
 *
 * @author Anton Azarenka
 */
@Component
public class LockWindowManager {

    private static final Logger LOGGER = ApplicationUtil.getLogger();
    @Autowired
    private OptionsManager manager;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    /**
     * Maps contain registered windows for ability set, check and remove passwords.
     */
    private final Map<Class<? extends CommonWidget>, BiFunction<LockWindowManager, String, Boolean>>
        functionCheckPasswordsMap = Map.of(PasswordsWindow.class, LockWindowManager::checkResourcePasswordWindow);
    private final Map<Class<? extends CommonWidget>, BiFunction<LockWindowManager, String, Boolean>>
        functionSetPasswordsMap = Map.of(PasswordsWindow.class, LockWindowManager::setResourcePasswordWindow);
    private final Map<Class<? extends CommonWidget>, Consumer<LockWindowManager>>
        functionResetPasswordsMap = Map.of(PasswordsWindow.class, LockWindowManager::reset);

    /**
     * Functions for applying passwords.
     */
    private BiFunction<LockWindowManager, String, Boolean> checkFunction;
    private BiFunction<LockWindowManager, String, Boolean> setFunction;
    private Consumer<LockWindowManager> resetConsumer;

    public boolean setPassword(String password) {
        boolean result = false;
        if (Objects.nonNull(setFunction)) {
            result = setFunction.apply(this, password);
        } else {
            LOGGER.error("Sets Function should be applied");
        }
        return result;
    }

    public boolean checkPassword(String password) {
        boolean result = false;
        if (Objects.nonNull(checkFunction)) {
            result = checkFunction.apply(this, password);
        } else {
            LOGGER.error("Function should be applied");
        }
        return result;
    }

    public void resetPassword() {
        resetConsumer.accept(this);
    }

    private boolean checkResourcePasswordWindow(String password) {
        String establishedPassword = manager.getProperties().getPasswordWindowProperties().getPassword();
        return passwordEncoder.matches(password, establishedPassword);
    }

    private void reset() {
        CommonProperties properties = manager.getProperties();
        PasswordWindowProperties passwordWindowProperties = properties.getPasswordWindowProperties();
        passwordWindowProperties.setPassword(null);
        passwordWindowProperties.setLocked(false);
        manager.saveOptions(properties);
    }

    private Boolean setResourcePasswordWindow(String newPassword) {
        try {
            CommonProperties properties = manager.getProperties();
            PasswordWindowProperties passwordWindowProperties = properties.getPasswordWindowProperties();
            passwordWindowProperties.setPassword(passwordEncoder.encode(newPassword));
            passwordWindowProperties.setLocked(true);
            manager.saveOptions(properties);
        } catch (Exception e) {
            LOGGER.error("Couldn't write password. {}", e.getMessage());
            return false;
        }
        return true;
    }

    public void setExecutableType(Class<? extends CommonWidget> type) {
        BiFunction<LockWindowManager, String, Boolean> checkExecutableFunction = functionCheckPasswordsMap.get(type);
        BiFunction<LockWindowManager, String, Boolean> setExecutableFunction = functionSetPasswordsMap.get(type);
        Consumer<LockWindowManager> resetConsumer = functionResetPasswordsMap.get(type);
        if (Objects.nonNull(checkExecutableFunction)
            && Objects.nonNull(setExecutableFunction)
            && Objects.nonNull(resetConsumer)) {
            this.checkFunction = checkExecutableFunction;
            this.setFunction = setExecutableFunction;
            this.resetConsumer = resetConsumer;
        }
    }
}
