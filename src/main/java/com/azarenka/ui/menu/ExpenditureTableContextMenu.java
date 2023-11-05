package com.azarenka.ui.menu;

import com.azarenka.domain.ResourcePassword;
import com.azarenka.domain.finance.Expenditure;
import com.azarenka.ui.table.impl.ExpenditureTableManager;
import com.azarenka.ui.table.impl.ResourcePasswordTableManager;

import java.util.Objects;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
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
 * Date: 06/25/2023
 *
 * @author Anton Azarenka
 */
public class ExpenditureTableContextMenu implements Callback<TableView<Expenditure>, TableRow<Expenditure>> {

    private final SimpleObjectProperty<ContextMenuInformationHandler<Expenditure>> handlerProperty =
        new SimpleObjectProperty<>();
    private final ObservableList<Expenditure> resourcePasswords = FXCollections.observableArrayList();
    private ExpenditureTableManager tableManager;
    private TableView<Expenditure> table;
    private ContextMenu menu;

    @Override
    public TableRow<Expenditure> call(TableView<Expenditure> expenditureTableView) {
        this.table = expenditureTableView;
        MenuItem add = new MenuItem("Добавить");
        MenuItem paste = new MenuItem("Вставить");
        MenuItem copy = new MenuItem("Копировать");
        MenuItem removeRow = new MenuItem("Удалить");
        MenuItem editRow = new MenuItem("Редактировать");
        add.setOnAction(event -> tableManager.addNewRow());
        paste.setOnAction(
            event -> tableManager.paste(handlerProperty.getValue().getConsumer(), handlerProperty.getValue().getItem()));
        copy.setOnAction(event -> tableManager.copy(handlerProperty.getValue().getValue()));
        removeRow.setOnAction(event -> tableManager.remove(handlerProperty.getValue().getItem()));
        editRow.setOnAction(event -> {
           tableManager.editRow();
        });
        menu = new ContextMenu(add, copy, paste, removeRow, editRow);
        TableRow<Expenditure> row = new TableRow<>();
        row.contextMenuProperty().setValue(menu);
        row.setOnContextMenuRequested(event -> {
            Expenditure selectedItem = row.getTableView().getSelectionModel().getSelectedItem();
            if (Objects.nonNull(selectedItem)) {
                Node intersectedNode = event.getPickResult().getIntersectedNode();
                TextFieldTableCell<Expenditure, String> table = intersectedNode instanceof TextFieldTableCell
                    ? (TextFieldTableCell<Expenditure, String>) event.getPickResult().getIntersectedNode()
                    : (TextFieldTableCell<Expenditure, String>) event.getPickResult()
                    .getIntersectedNode().getParent();
                TableColumn<Expenditure, String> tableColumn = table.getTableColumn();
                handlerProperty.setValue(
                    new ContextMenuInformationHandler(tableManager.getConsumerMap().get(tableColumn),
                        selectedItem, tableColumn.getCellData(selectedItem)));
            }
        });
        return row;
    }

    public void setTableManager(ExpenditureTableManager tableManager) {
        this.tableManager = tableManager;
    }
}
