package com.azarenka.ui.menu;

import com.azarenka.domain.ResourcePassword;
import com.azarenka.ui.table.impl.ResourcePasswordTableManager;

import java.util.Objects;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 01/11/2023
 *
 * @author Anton Azarenka
 */
public class ResourcePasswordTableContextMenu
    implements Callback<TableView<ResourcePassword>, TableRow<ResourcePassword>> {

    private final SimpleObjectProperty<ContextMenuInformationHandler<ResourcePassword>> handlerProperty =
        new SimpleObjectProperty<>();
    private final ObservableList<ResourcePassword> resourcePasswords = FXCollections.observableArrayList();
    private ResourcePasswordTableManager tableManager;
    private TableView<ResourcePassword> table;
    private ContextMenu menu;

    @Override
    @SuppressWarnings("uncheked")
    public TableRow<ResourcePassword> call(TableView<ResourcePassword> resourcePasswordTableView) {
        this.table = resourcePasswordTableView;
        MenuItem add = new MenuItem("Добавить");
        MenuItem paste = new MenuItem("Вставить");
        MenuItem copy = new MenuItem("Копировать");
        MenuItem removeRow = new MenuItem("Удалить");
        add.setOnAction(event -> tableManager.addNewRow());
        paste.setOnAction(
            event -> tableManager.paste(handlerProperty.getValue().getConsumer(),
                handlerProperty.getValue().getItem()));
        copy.setOnAction(event -> tableManager.copy(handlerProperty.getValue().getValue()));
        removeRow.setOnAction(event -> tableManager.remove(handlerProperty.getValue().getItem()));
        menu = new ContextMenu(add, copy, paste, removeRow);
        TableRow<ResourcePassword> row = new TableRow<>();
        row.contextMenuProperty().setValue(menu);
        row.setOnContextMenuRequested(event -> {
            ResourcePassword selectedItem = row.getTableView().getSelectionModel().getSelectedItem();
            if (Objects.nonNull(selectedItem)) {
                Node intersectedNode = event.getPickResult().getIntersectedNode();
                TextFieldTableCell<ResourcePassword, String> table = intersectedNode instanceof TextFieldTableCell
                    ? (TextFieldTableCell<ResourcePassword, String>) event.getPickResult().getIntersectedNode()
                    : (TextFieldTableCell<ResourcePassword, String>) event.getPickResult()
                    .getIntersectedNode().getParent();
                TableColumn<ResourcePassword, String> tableColumn = table.getTableColumn();
                handlerProperty.setValue(
                    new ContextMenuInformationHandler(tableManager.getConsumerMap().get(tableColumn),
                        selectedItem, tableColumn.getCellData(selectedItem)));
            }
        });
        return row;
    }

    public void setTableManager(ResourcePasswordTableManager tableManager) {
        this.tableManager = tableManager;
    }
}
