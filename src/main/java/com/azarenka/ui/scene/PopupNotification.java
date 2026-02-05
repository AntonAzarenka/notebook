package com.azarenka.ui.scene;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 11/05/2023
 *
 * @author Anton Azarenka
 */
public class PopupNotification {

    public static void showPopupMessage(final String message, Stage stage) {
        final Popup popup = createPopup(message);
        popup.setOnShown(e -> {
            popup.setX(stage.getWidth() - 520);
            popup.setY(stage.getHeight() + 80);
        });
        popup.show(stage);
    }

    private static Popup createPopup(final String message) {
        final Popup popup = new Popup();
        popup.setAutoFix(true);
        popup.setAutoHide(true);
        popup.setHideOnEscape(true);
        popup.setConsumeAutoHidingEvents(true);
        popup.setOpacity(1);
        Label label = new Label(message);
        label.setOnMouseReleased(e -> popup.hide());
        label.setFont(Font.font(12));
        label.getStyleClass().add("popup");
        popup.getContent().add(label);
        return popup;
    }
}
