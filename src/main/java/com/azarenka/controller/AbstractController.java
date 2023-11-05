package com.azarenka.controller;

import com.azarenka.WindowsProvider;
import com.azarenka.controller.main.MainWindowMediator;
import com.azarenka.javafx.SceneChanger;
import com.azarenka.javafx.StageInitializer;
import com.azarenka.javafx.load.CommonWidget;
import com.azarenka.service.event.EventHandlerProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 01/09/2023
 *
 * @author Anton Azarenka
 */
@Component
public class AbstractController {

    @Autowired
    private SceneChanger sceneChanger;
    @Autowired
    private WindowsProvider windowsProvider;
    @Autowired
    private MainWindowMediator mediator;
    @Autowired
    private EventHandlerProvider eventProvider;

    public EventHandlerProvider getEventProvider() {
        return eventProvider;
    }

    private final DraggableSceneManager draggableSceneManager;

    public AbstractController() {
        this.draggableSceneManager = new DraggableSceneManager();
    }

    public void draggedMouse(MouseEvent mouseEvent) {
        draggableSceneManager.dragged(mouseEvent);
    }

    public void pressedMouse(MouseEvent mouseEvent) {
        draggableSceneManager.pressed(mouseEvent);
    }

    public void close() {
        System.exit(0);
    }

    public void goHome() {
        sceneChanger.setNewScene(windowsProvider.getMainWindow());
    }

    public void openFinanceWindow() {
        sceneChanger.setNewScene(windowsProvider.getFinanceWindow());
    }

    public void openPasswordWindow() {
       if(!eventProvider.getPasswordResourceEvent().getPasswordResourceWindowStatus() || mediator.isLockPasswordResourceWindow()){
           sceneChanger.setNewScene(windowsProvider.getPasswordsWindow());
       }else {
           eventProvider.getPasswordResourceEvent().passwordResourceWindowStatusProperty().addListener(
               (observableValue, aBoolean, newValue) -> {
                   if (!newValue) {
                       sceneChanger.setNewScene(windowsProvider.getPasswordsWindow());
                   }
               });
       }
    }

    public void closeWindow(CommonWidget widget) {
        sceneChanger.closeWindow(widget);
    }

    public SceneChanger getSceneChanger() {
        return sceneChanger;
    }

    public WindowsProvider getWindowsProvider() {
        return windowsProvider;
    }

    public static class DraggableSceneManager extends StageInitializer {

        private static double xOffset = 0;
        private static double yOffset = 0;

        public void pressed(MouseEvent event) {
            xOffset = getStage().getX() - event.getScreenX();
            yOffset = getStage().getY() - event.getScreenY();
        }

        public void dragged(MouseEvent event) {
            getStage().setX(event.getScreenX() + xOffset);
            getStage().setY(event.getScreenY() + yOffset);
        }
    }
}
