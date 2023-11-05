package com.azarenka.controller.modal;

import com.azarenka.controller.AbstractController;
import com.azarenka.service.LockWindowManager;
import com.azarenka.service.event.EventHandlerProvider;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 01/15/2023
 *
 * @author Anton Azarenka
 */
@Controller
public class PasswordModalWindowController extends AbstractController {

    public Label errorLabel;
    public PasswordField passwordField;

    @Autowired
    private LockWindowManager lockWindowManager;
    @Autowired
    private EventHandlerProvider eventProvider;

    public void enterPassword() {
        if (lockWindowManager.checkPassword(passwordField.getText())) {
            eventProvider.getPasswordResourceEvent().setPasswordResourceWindowStatus(false);
            eventProvider.setChangePasswordEventResourceWindow(true);
            closeWindow();
        } else {
            errorLabel.setText("Неверный пароль. Повторите еще раз.");
        }
    }

    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            enterPassword();
        } else if (keyEvent.getCode().equals(KeyCode.ESCAPE)) {
            closeWindow();
        }
    }

    private void resetWindow() {
        passwordField.setText(StringUtils.EMPTY);
        errorLabel.setText(StringUtils.EMPTY);
    }

    public void closeWindow() {
        resetWindow();
        eventProvider.changePasswordEventResourceWindowProperty().set(false);
        closeWindow(getWindowsProvider().getPasswordModalWindow());
    }
}
