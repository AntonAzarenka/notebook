package com.azarenka.ui.table.impl;

import com.azarenka.domain.finance.Expenditure;
import com.azarenka.domain.properties.CommonProperties;
import com.azarenka.service.api.IFileDataManager;
import com.azarenka.ui.menu.ExpenditureTableContextMenu;
import com.azarenka.ui.menu.IContextMenu;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.Clipboard;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 03/19/2023
 *
 * @author Anton Azarenka
 */
public class ExpenditureTableManager extends AbstractTableManager<Expenditure> implements IContextMenu<Expenditure> {

    private final ObservableList<Expenditure> items = FXCollections.observableArrayList();

    private Map<TableColumn<Expenditure, String>, Function<Expenditure, String>> functionMap = new HashMap<>();
    private Map<TableColumn<Expenditure, String>, BiConsumer<Expenditure, String>> consumerMap = new HashMap<>();

    private TableView<Expenditure> table;
    private TableColumn<Expenditure, String> nameColumn;
    private TableColumn<Expenditure, String> amountColumn;
    private TableColumn<Expenditure, String> dateColumn;

    private final IFileDataManager<List<Expenditure>> dataFileLoader;
    private final IFileDataManager<CommonProperties> commonPropertiesDataManager;

    public ExpenditureTableManager(IFileDataManager<List<Expenditure>> fileDataManager,
                                   IFileDataManager<CommonProperties> commonPropertiesDataManager) {
        this.dataFileLoader = fileDataManager;
        this.commonPropertiesDataManager = commonPropertiesDataManager;
    }

    @Override
    public void setItems(Expenditure expenditure) {
        items.add(expenditure);
        table.setItems(items);
    }

    @Override
    public void resetTable() {
        this.items.clear();
        table.setItems(items);
    }

    @Override
    public void refresh(ObservableList<Expenditure> items) {
        resetTable();
        table.setItems(items);
        table.refresh();
    }

    @Override
    public void loadItems() {
        List<Expenditure> data = dataFileLoader.loadData();
        if (!data.isEmpty()) {
            items.addAll(FXCollections.observableArrayList(data));
            table.setItems(items);
        }
    }

    @Override
    public void saveItems() {
        dataFileLoader.saveData(new ArrayList<>(items));
    }

    @Override
    public void createTable() {
        populateTableColumnMaps();
        createDefaultValues();
        createFactoriesForColumns();
        createContextMenu();
        loadItems();
        table.refresh();
    }

    @Override
    public void hideData() {

    }

    @Override
    public void unHideData() {

    }

    @Override
    public void copy(String value) {
        copyValue(value);
    }

    public void addNewRow() {
        createDefaultValues();
    }

    @Override
    public void remove(Expenditure expenditure) {
        items.remove(expenditure);
        if (items.isEmpty()) {
            createDefaultValues();
        }
        table.refresh();
        refreshData();
    }

    @Override
    public Map<TableColumn<Expenditure, String>, BiConsumer<Expenditure, String>> getConsumerMap() {
        return consumerMap;
    }

    @Override
    public void createContextMenu() {
        ExpenditureTableContextMenu contextMenu = new ExpenditureTableContextMenu();
        contextMenu.setTableManager(this);
        table.setRowFactory(contextMenu);
    }

    @Override
    public void paste(BiConsumer<Expenditure, String> consumer, Expenditure type) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        consumer.accept(type, clipboard.getString());
        refreshData();
    }

    public void setTable(TableView<Expenditure> table) {
        this.table = table;
    }

    public void setNameColumn(TableColumn<Expenditure, String> nameColumn) {
        this.nameColumn = nameColumn;
    }

    public void setAmountColumn(TableColumn<Expenditure, String> amountColumn) {
        this.amountColumn = amountColumn;
    }

    public void setDateColumn(TableColumn<Expenditure, String> dateColumn) {
        this.dateColumn = dateColumn;
    }

    public void editRow() {
    }

    void refreshData() {
        saveItems();
        table.refresh();
    }

    private void populateTableColumnMaps() {
        functionMap.put(nameColumn, Expenditure::getName);
        functionMap.put(dateColumn, value -> value.getDate().toString());
        functionMap.put(amountColumn, value -> value.getAmount().toString());
        consumerMap.put(nameColumn, Expenditure::setName);
        consumerMap.put(dateColumn, (item, value) -> new ExpenditureTableValidator().validateDate(item, value));
        consumerMap.put(amountColumn, (item, value) -> new ExpenditureTableValidator().validateAmount(item, value));
        setTableColumnFunctionMap(functionMap);
        setTableColumnConsumerMap(consumerMap);
    }

    private void createDefaultValues() {
        Expenditure expenditure = new Expenditure();
        expenditure.setAmount(new BigDecimal("0.00"));
        expenditure.setDate(LocalDate.now());
        expenditure.setName("Enter name....");
        items.add(expenditure);
    }
}
