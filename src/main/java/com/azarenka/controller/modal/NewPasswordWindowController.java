package com.azarenka.controller.modal;

import com.azarenka.controller.AbstractController;
import com.azarenka.service.LockWindowManager;
import com.azarenka.service.event.EventHandlerProvider;
import com.azarenka.ui.scene.PasswordsWindow;

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
 * Date: 02/09/2023
 *
 * @author Anton Azarenka
 */
@Controller
public class NewPasswordWindowController extends AbstractController {

    private static final String ERROR_LENGTH_PASSWORD_MESSAGE = "Пароль должен быть не менее 6 символов";
    private static final String ERROR_LENGTH_CONFIRMED_MESSAGE = "Пароли должны совпадать";

    @Autowired
    private LockWindowManager lockWindowManager;
    @Autowired
    private EventHandlerProvider eventProvider;

    public Label errorLabel;
    public PasswordField newPasswordField;
    public PasswordField confirmPasswordField;

    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            enterPassword();
        } else if (keyEvent.getCode().equals(KeyCode.ESCAPE)) {
            closeWindow();
        }
    }

    public void cancel() {
       closeWindow();
    }

    public void enterPassword() {
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        if(validateLengthPassword(newPassword) && validateConfirmField(newPassword, confirmPassword)) {
            lockWindowManager.setExecutableType(PasswordsWindow.class);
            if(lockWindowManager.setPassword(newPassword)) {
                eventProvider.setChangePasswordStatusOfResourceWindow(!eventProvider.isChangePasswordStatusOfResourceWindow());
                closeWindow();
            } else {
                errorLabel.setText("Что-то пошло не так. Обратитесь к разработчику.");
            }
        }
    }

    private boolean validateLengthPassword(String newPassword){
        if(newPassword.length() < 6) {
            errorLabel.setText(ERROR_LENGTH_PASSWORD_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean validateConfirmField(String newPassword, String confirmedPassword) {
        if(!newPassword.equals(confirmedPassword)) {
            errorLabel.setText(ERROR_LENGTH_CONFIRMED_MESSAGE);
            return false;
        }
        return true;
    }

    private void resetWindow() {
        newPasswordField.setText(StringUtils.EMPTY);
        confirmPasswordField.setText(StringUtils.EMPTY);
        errorLabel.setText(StringUtils.EMPTY);
    }

    public void closeWindow() {
        closeWindow(getWindowsProvider().getNewPasswordModalWindow());
        resetWindow();
    }
}
