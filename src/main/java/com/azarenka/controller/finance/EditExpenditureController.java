package com.azarenka.controller.finance;

import com.azarenka.controller.AbstractController;

import org.springframework.stereotype.Component;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 06/28/2023
 *
 * @author Anton Azarenka
 */
@Component
public class EditExpenditureController extends AbstractController {

    public void cancel() {
    }

    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            //enterPassword();
        } else if (keyEvent.getCode().equals(KeyCode.ESCAPE)) {
            closeWindow();
        }
    }

    public void closeWindow() {
        resetWindow();
        //eventProvider.changePasswordEventResourceWindowProperty().set(false);
        super.closeWindow(getWindowsProvider().getPasswordModalWindow());
    }

    private void resetWindow() {
        //passwordField.setText(StringUtils.EMPTY);
        //errorLabel.setText(StringUtils.EMPTY);
    }

    public void applyChanges() {
    }
}
