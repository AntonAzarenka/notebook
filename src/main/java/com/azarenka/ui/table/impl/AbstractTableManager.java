package com.azarenka.ui.table.impl;

import com.azarenka.domain.ResourcePassword;
import com.azarenka.ui.table.api.ITableManager;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 01/10/2023
 *
 * @author Anton Azarenka
 */
public abstract class AbstractTableManager<T> implements ITableManager<T> {

    private Map<TableColumn<T, String>, Function<T, String>> tableColumnFunctionMap = new HashMap<>();
    private Map<TableColumn<T, String>, BiConsumer<T, String>> tableColumnConsumerMap = new HashMap<>();

    public abstract void createTable();

    public abstract void hideData();

    public abstract void unHideData();

    public abstract void copy(String value);

    public abstract void paste(BiConsumer<T, String> consumer, T type);

    abstract void refreshData();

    public void setTableColumnFunctionMap(Map<TableColumn<T, String>, Function<T, String>> tableColumnFunctionMap) {
        this.tableColumnFunctionMap = tableColumnFunctionMap;
    }

    void setTableColumnConsumerMap(Map<TableColumn<T, String>, BiConsumer<T, String>> consumerMap) {
        this.tableColumnConsumerMap = consumerMap;
    }

    void createFactoriesForColumns() {
        tableColumnFunctionMap.forEach(this::applyColumnFactories);
        tableColumnConsumerMap.forEach(this::applyActionCommit);
    }

    private void applyColumnFactories(TableColumn<T, String> column, Function<T, String> function) {
        column.setCellValueFactory(param -> new SimpleStringProperty(function.apply(param.getValue())));
        column.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public void copyValue(String value) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(value);
        clipboard.setContent(content);
    }

    private void applyActionCommit(TableColumn<T, String> column, BiConsumer<T, String> consumer) {
        column.setOnEditCommit(event -> {
            consumer.accept(event.getTableView().getItems().get(event.getTablePosition().getRow()),
                event.getNewValue());
            saveItems();
        });
    }
}
