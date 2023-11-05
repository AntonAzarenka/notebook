package com.azarenka.ui.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 06/25/2023
 *
 * @author Anton Azarenka
 */
public class Windows {

    public static void showErrorMessage(String message) {
        prepareWindow(message, AlertType.ERROR).show();
    }

    private static Alert prepareWindow(String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Validation error");
        alert.setContentText(message);
        return alert;
    }
}
