package com.azarenka.ui.table.create;

import com.azarenka.controller.finance.FinanceWindowController;
import com.azarenka.domain.finance.Expenditure;
import com.azarenka.domain.properties.CommonProperties;
import com.azarenka.service.api.IFileDataManager;
import com.azarenka.ui.table.impl.AbstractTableManager;
import com.azarenka.ui.table.impl.ExpenditureTableManager;

import org.springframework.context.ApplicationContext;

import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 03/19/2023
 *
 * @author Anton Azarenka
 */
public class ExpenditureTableManagerFactory extends AbstractTableManagerFactory<Expenditure> {

    private ApplicationContext context;
    private final IFileDataManager<List<Expenditure>> fileDataManager;
    private final IFileDataManager<CommonProperties> commonPropertiesDataManager;

    private TableView<Expenditure> table;
    private TableColumn<Expenditure, String> nameColumn;
    private TableColumn<Expenditure, String> amountColumn;
    private TableColumn<Expenditure, String> dateColumn;

    public ExpenditureTableManagerFactory(IFileDataManager<List<Expenditure>> fileDataManager,
                                          IFileDataManager<CommonProperties> commonPropertiesDataManager) {
        this.fileDataManager = fileDataManager;
        this.commonPropertiesDataManager = commonPropertiesDataManager;
    }

    @Override
    AbstractTableManager<Expenditure> getTable() {
        ExpenditureTableManager manager = new ExpenditureTableManager(fileDataManager, commonPropertiesDataManager);
        initTable(manager);
        return manager;
    }

    @Override
    void setContext(ApplicationContext context) {
        this.context = context;
    }

    private void initTable(ExpenditureTableManager manager) {
        manager.setTable(context.getBean(FinanceWindowController.class).expenditureTableView);
        manager.setAmountColumn(context.getBean(FinanceWindowController.class).expenditureAmountTableColumn);
        manager.setDateColumn(context.getBean(FinanceWindowController.class).expenditureDateTableColumn);
        manager.setNameColumn(context.getBean(FinanceWindowController.class).expenditureNameTableColumn);
    }
}
