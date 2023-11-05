package com.azarenka.controller.ressourcepass;

import com.azarenka.controller.AbstractMediator;
import com.azarenka.domain.properties.PasswordWindowProperties;
import com.azarenka.service.event.password.PasswordResourceEvent;
import com.azarenka.ui.scene.PasswordsWindow;

import org.springframework.stereotype.Component;

import java.util.Objects;

import javafx.scene.control.MenuItem;

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
public class PasswordsWindowMediator extends AbstractMediator {

    //TODO add to properties file for supporting a few languages
    private static final String REMOVE_PASSWORD = "Убрать пароль";
    private static final String ADD_PASSWORD = "Установить пароль";
    private static final String HIDE_DATA = "Скрыть данные";
    private static final String UN_HIDE_DATA = "Открыть данные";

    public MenuItem password;
    public MenuItem hideData;
    public MenuItem changePassword;

    public void initMenuItem() {
        password.setText(getOptionsManager().getProperties().getPasswordWindowProperties().isLocked()
            ? REMOVE_PASSWORD
            : ADD_PASSWORD);
    }

    public void onChangeHideDataStatus() {
        hideData.setText(getOptionsManager().getProperties().getPasswordWindowProperties().isHiddenData()
            ? UN_HIDE_DATA
            : HIDE_DATA);
    }

    public void apply() {
        initMenuItem();
        onChangeHideDataStatus();
        onChangePasswordStatus();
    }

    private void onChangePasswordStatus() {
        changePassword.setDisable(!getOptionsManager().getProperties().getPasswordWindowProperties().isLocked());
    }

    public void changePasswordStatus() {
        if (isPasswordIsEmpty()) {
            getSceneChanger().showModalWindow(getWindowsProvider().getNewPasswordModalWindow());
            getEventHandlerProvider().changePasswordStatusOfResourceWindowProperty()
                .addListener((observableValue, aBoolean, t1) -> apply());
        } else {
            getLockWindowManager().setExecutableType(PasswordsWindow.class);
            getPasswordResourceEvent().setPasswordResourceWindowStatus(true);
            getSceneChanger().showModalWindow(getWindowsProvider().getPasswordModalWindow());
            getPasswordResourceEvent().passwordResourceWindowStatusProperty().addListener(
                (observableValue, aBoolean, newValue) -> {
                    getLockWindowManager().setExecutableType(PasswordsWindow.class);
                    getLockWindowManager().resetPassword();
                    apply();
                });
        }
    }

    public void changePassword() {
        getLockWindowManager().setExecutableType(PasswordsWindow.class);
        getPasswordResourceEvent().setPasswordResourceWindowStatus(true);
        getSceneChanger().showModalWindow(getWindowsProvider().getPasswordModalWindow());
        getEventHandlerProvider().changePasswordEventResourceWindowProperty().addListener(
            (observableValue, aBoolean, newValue) -> {
                if (!newValue && aBoolean) {
                    getSceneChanger().showModalWindow(getWindowsProvider().getNewPasswordModalWindow());
                }
            });
    }

    private boolean isPasswordIsEmpty() {
        PasswordWindowProperties passwordWindowProperties = getOptionsManager()
            .getProperties()
            .getPasswordWindowProperties();
        return Objects.isNull(passwordWindowProperties.getPassword())
            || passwordWindowProperties.getPassword().isEmpty();
    }

    public void setHideData(MenuItem hideData) {
        this.hideData = hideData;
    }

    public void setPassword(MenuItem password) {
        this.password = password;
    }

    public void setChangePassword(MenuItem changePassword) {
        this.changePassword = changePassword;
    }

    private PasswordResourceEvent getPasswordResourceEvent() {
        return getEventHandlerProvider().getPasswordResourceEvent();
    }
}
