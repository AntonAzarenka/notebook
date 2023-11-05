package com.azarenka.controller.ressourcepass;

import com.azarenka.controller.AbstractController;
import com.azarenka.domain.ResourcePassword;
import com.azarenka.ui.table.api.ITableManagerFactory;
import com.azarenka.ui.table.impl.AbstractTableManager;
import com.azarenka.ui.table.impl.ResourcePasswordTableManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

/**
 * Represents of controller for passwords window.
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 01/09/2023
 *
 * @author Anton Azarenka
 */
@Controller
public class PasswordsWindowController extends AbstractController {

    public TableView<ResourcePassword> table;
    public TableColumn<ResourcePassword, String> resourceColumn;
    public TableColumn<ResourcePassword, String> passwordColumn;
    public GridPane gridPane;
    public MenuItem setPassword;
    public MenuItem hideData;
    public MenuItem changePasswordField;

    @Autowired
    private ITableManagerFactory<ResourcePassword> tableManagerFactory;
    @Autowired
    private PasswordsWindowMediator mediator;
    private AbstractTableManager<ResourcePassword> manager;

    public void initialize() {
        manager = tableManagerFactory.createTableManager(ResourcePasswordTableManager.class);
        manager.createTable();
        initMediator();
        mediator.apply();
    }

    public void setUpPassword() {
        mediator.changePasswordStatus();
    }

    public void changePassword() {
        mediator.changePassword();
    }

    public void hideData() {
        if (hideData.getText().equals("Скрыть данные")) {
            manager.hideData();
        } else {
            manager.unHideData();
        }
        mediator.onChangeHideDataStatus();
    }

    public void closeWindow() {
        getEventProvider().getPasswordResourceEvent().setPasswordResourceWindowStatus(true);
        getSceneChanger().setNewScene(getWindowsProvider().getMainWindow());
    }

    private void initMediator() {
        mediator.setHideData(hideData);
        mediator.setPassword(setPassword);
        mediator.setChangePassword(changePasswordField);
    }
}
