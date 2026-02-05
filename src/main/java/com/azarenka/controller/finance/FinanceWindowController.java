package com.azarenka.controller.finance;

import com.azarenka.controller.AbstractController;
import com.azarenka.domain.finance.Expenditure;
import com.azarenka.service.events.EventTypeEnum;
import com.azarenka.service.events.IEvent;
import com.azarenka.service.events.SaveDataEvent;
import com.azarenka.ui.scene.PopupNotification;
import com.azarenka.ui.table.api.ITableManagerFactory;
import com.azarenka.ui.table.impl.AbstractTableManager;
import com.azarenka.ui.table.impl.ExpenditureTableManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 03/18/2023
 *
 * @author Anton Azarenka
 */
@Controller
public class FinanceWindowController extends AbstractController {

    public TableView<Expenditure> expenditureTableView;
    public TableColumn<Expenditure, String> expenditureNameTableColumn;
    public TableColumn<Expenditure, String> expenditureAmountTableColumn;
    public TableColumn<Expenditure, String> expenditureDateTableColumn;
    public MenuItem setPassword;
    @Autowired
    private FinanceMediator mediator;

    @Autowired
    private ITableManagerFactory<Expenditure> tableManagerFactory;

    private AbstractTableManager<Expenditure> manager;

    public void initialize() {
        manager = tableManagerFactory.createTableManager(ExpenditureTableManager.class);
        manager.createTable();
        manager.setSaveDataEvent(
            (IEvent) mediator.getEventHandlerProvider().getEvent(EventTypeEnum.EXPENDITURE_EDIT_EVENT));
        mediator.getEventHandlerProvider().registerEvent(EventTypeEnum.EXPENDITURE_EDIT_EVENT,
            (observableValue, aBoolean, newValue) ->
                PopupNotification.showPopupMessage("Data saved successfully",
                    (Stage) this.getWindowsProvider().getFinanceWindow().getScene().getWindow()));
    }

    public void setUpPassword() {
    }

    public void changePassword() {
    }

    public void hideData() {
    }

    public void addRow() {
        ((ExpenditureTableManager) manager).addNewRow();
    }
}
