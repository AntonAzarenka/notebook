package com.azarenka.ui.table.create;

import com.azarenka.controller.ressourcepass.PasswordsWindowController;
import com.azarenka.domain.ResourcePassword;
import com.azarenka.domain.properties.CommonProperties;
import com.azarenka.service.api.IFileDataManager;
import com.azarenka.ui.table.impl.AbstractTableManager;
import com.azarenka.ui.table.impl.ResourcePasswordTableManager;

import org.springframework.context.ApplicationContext;

import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 01/13/2023
 *
 * @author Anton Azarenka
 */
public class ResourcePasswordTableManagerFactory extends AbstractTableManagerFactory<ResourcePassword> {

    private ApplicationContext context;
    private TableView<ResourcePassword> tableView;
    private TableColumn<ResourcePassword, String> resourceColumn;
    private TableColumn<ResourcePassword, String> passwordColumn;
    private final IFileDataManager<List<ResourcePassword>> fileDataManager;
    private final IFileDataManager<CommonProperties> commonPropertiesDataManager;

    public ResourcePasswordTableManagerFactory(IFileDataManager<List<ResourcePassword>> fileDataManager,
                                               IFileDataManager<CommonProperties> commonPropertiesDataManager) {
        this.fileDataManager = fileDataManager;
        this.commonPropertiesDataManager = commonPropertiesDataManager;
    }

    @Override
    AbstractTableManager<ResourcePassword> getTable() {
        ResourcePasswordTableManager tableManager =
            new ResourcePasswordTableManager(fileDataManager, commonPropertiesDataManager);
        initTable();
        tableManager.setTable(tableView);
        tableManager.setResourceColumn(resourceColumn);
        tableManager.setPasswordColumn(passwordColumn);
        return tableManager;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    private void initTable() {
        tableView = context.getBean(PasswordsWindowController.class).table;
        resourceColumn = context.getBean(PasswordsWindowController.class).resourceColumn;
        passwordColumn = context.getBean(PasswordsWindowController.class).passwordColumn;
    }
}
