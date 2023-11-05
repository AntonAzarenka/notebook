package com.azarenka.ui.table.impl;

import com.azarenka.domain.ResourcePassword;
import com.azarenka.domain.properties.CommonProperties;
import com.azarenka.service.api.IFileDataManager;
import com.azarenka.ui.menu.IContextMenu;
import com.azarenka.ui.menu.ResourcePasswordTableContextMenu;
import com.azarenka.util.ApplicationUtil;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.Clipboard;

/**
 * Represents of table manager for Resource Passwords .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 01/10/2023
 *
 * @author Anton Azarenka
 */
public class ResourcePasswordTableManager extends AbstractTableManager<ResourcePassword>
    implements IContextMenu<ResourcePassword> {

    private static final Logger LOGGER = ApplicationUtil.getLogger();
    private final ObservableList<ResourcePassword> resourcePasswords = FXCollections.observableArrayList();
    private final ObservableList<ResourcePassword> hideResourcePasswords = FXCollections.observableArrayList();
    private Map<TableColumn<ResourcePassword, String>, Function<ResourcePassword, String>> functionMap =
        new HashMap<>();
    private Map<TableColumn<ResourcePassword, String>, BiConsumer<ResourcePassword, String>> consumerMap =
        new HashMap<>();

    private TableView<ResourcePassword> table;
    private TableColumn<ResourcePassword, String> resourceColumn;
    private TableColumn<ResourcePassword, String> passwordColumn;

    private IFileDataManager<List<ResourcePassword>> dataFileLoader;
    private IFileDataManager<CommonProperties> commonPropertiesDataManager;

    public ResourcePasswordTableManager(IFileDataManager<List<ResourcePassword>> dataFileLoader,
                                        IFileDataManager<CommonProperties> commonPropertiesDataManager) {
        this.dataFileLoader = dataFileLoader;
        this.commonPropertiesDataManager = commonPropertiesDataManager;
    }

    @Override
    public void createTable() {
        populateTableColumnMaps();
        createDefaultValues();
        createFactoriesForColumns();
        createContextMenu();
        table.setItems(resourcePasswords);
        loadItems();
    }

    @Override
    public void hideData() {
        CommonProperties properties = commonPropertiesDataManager.loadData();
        properties.getPasswordWindowProperties().setHiddenData(true);
        commonPropertiesDataManager.saveData(properties);
        convertToHide();
        table.refresh();
    }

    @Override
    public void unHideData() {
        CommonProperties properties = commonPropertiesDataManager.loadData();
        properties.getPasswordWindowProperties().setHiddenData(false);
        commonPropertiesDataManager.saveData(properties);
        loadItems();
    }


    void createDefaultValues() {
        ResourcePassword resourcePassword = new ResourcePassword();
        resourcePassword.setPassword("");
        resourcePassword.setResource("");
        resourcePasswords.add(resourcePassword);
    }

    public void addNewRow() {
        resourcePasswords.add(createResourcePassword());
        if (isHiddenData()) {
            convertToHide();
            //table.setItems(hideResourcePasswords);
        } else {
            table.setItems(resourcePasswords);
        }
        refreshData();
    }

    private boolean isHiddenData() {
        CommonProperties commonProperties = commonPropertiesDataManager.loadData();
        return commonProperties.getPasswordWindowProperties().isHiddenData();
    }

    private void convertToHide() {
        List<ResourcePassword> tempPasswords =
            resourcePasswords.stream().map(ResourcePassword::new).collect(Collectors.toList());
        tempPasswords.forEach(
            password -> password.setPassword(password.getPassword().replaceAll("^.*", "***********")));
        hideResourcePasswords.clear();
        hideResourcePasswords.addAll(tempPasswords);
        table.setItems(hideResourcePasswords);
    }

    public void copy(String value) {
        copyValue(value);
    }

    public void paste(BiConsumer<ResourcePassword, String> consumer, ResourcePassword resourcePassword) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        consumer.accept(resourcePassword, clipboard.getString());
        if (isHiddenData()) {
            convertToHide();
            table.setItems(hideResourcePasswords);
        }
        refreshData();
    }

    public void remove(ResourcePassword resourcePassword) {
        resourcePasswords.remove(resourcePassword);
        if (resourcePasswords.isEmpty()) {
            createDefaultValues();
        }
        table.refresh();
        refreshData();
    }

    public Map<TableColumn<ResourcePassword, String>, BiConsumer<ResourcePassword, String>> getConsumerMap() {
        return consumerMap;
    }

    @Override
    public void createContextMenu() {
        ResourcePasswordTableContextMenu resourcePasswordTableContextMenu = new ResourcePasswordTableContextMenu();
        resourcePasswordTableContextMenu.setTableManager(this);
        table.setRowFactory(resourcePasswordTableContextMenu);
    }

    private void populateTableColumnMaps() {
        functionMap.put(resourceColumn, ResourcePassword::getResource);
        functionMap.put(passwordColumn, ResourcePassword::getPassword);
        consumerMap.put(resourceColumn, ResourcePassword::setResource);
        consumerMap.put(passwordColumn, ResourcePassword::setPassword);
        setTableColumnFunctionMap(functionMap);
        setTableColumnConsumerMap(consumerMap);
    }

    private ResourcePassword createResourcePassword() {
        ResourcePassword resourcePassword = new ResourcePassword();
        resourcePassword.setResource("set new resource");
        resourcePassword.setPassword("set new password");
        return resourcePassword;
    }

    public void setTable(TableView<ResourcePassword> table) {
        this.table = table;
    }

    public void setResourceColumn(TableColumn<ResourcePassword, String> resourceColumn) {
        this.resourceColumn = resourceColumn;
    }

    public void setPasswordColumn(TableColumn<ResourcePassword, String> passwordColumn) {
        this.passwordColumn = passwordColumn;
    }

    @Override
    public void setItems(ResourcePassword word) {
        resourcePasswords.add(word);
        table.setItems(resourcePasswords);
    }

    @Override
    public void resetTable() {
        this.resourcePasswords.clear();
        table.setItems(resourcePasswords);
    }

    @Override
    public void refresh(ObservableList<ResourcePassword> items) {
        resetTable();
        table.setItems(items);
        table.refresh();
    }

    @Override
    public void loadItems() {
        List<ResourcePassword> data = dataFileLoader.loadData();
        if (!data.isEmpty()) {
            if (isHiddenData()) {
                resourcePasswords.clear();
                hideResourcePasswords.clear();
                resourcePasswords.addAll(FXCollections.observableArrayList(data));
                List<ResourcePassword> tempPasswords = new ArrayList<>(data);
                tempPasswords.forEach(
                    password -> password.setPassword(password.getPassword().replaceAll("^.*", "***********")));
                hideResourcePasswords.addAll(tempPasswords);
                refresh(hideResourcePasswords);
            } else {
                refresh(resourcePasswords);
            }
            resourcePasswords.addAll(FXCollections.observableArrayList(data));
        }
    }

    @Override
    public void saveItems() {
        dataFileLoader.saveData(new ArrayList<>(resourcePasswords));
    }

    void refreshData() {
        saveItems();
        table.refresh();
    }
}
