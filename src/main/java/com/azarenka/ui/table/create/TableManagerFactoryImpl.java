package com.azarenka.ui.table.create;

import com.azarenka.domain.ResourcePassword;
import com.azarenka.domain.finance.Expenditure;
import com.azarenka.domain.properties.CommonProperties;
import com.azarenka.service.api.IFileDataManager;
import com.azarenka.ui.table.api.ITableManagerFactory;
import com.azarenka.ui.table.impl.AbstractTableManager;
import com.azarenka.ui.table.impl.ExpenditureTableManager;
import com.azarenka.ui.table.impl.ResourcePasswordTableManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 01/15/2023
 *
 * @author Anton Azarenka
 */
@Component
public class TableManagerFactoryImpl<T> implements ITableManagerFactory<T> {

    private ApplicationContext context;
    @Autowired
    @Qualifier("app.resource.password.data.manager")
    private IFileDataManager<List<ResourcePassword>> resourcePasswordFileDataManager;
    @Autowired
    @Qualifier("app.resource.expenditure.data.manager")
    private IFileDataManager<List<Expenditure>> expenditureFileDataManager;
    @Autowired
    @Qualifier("app.options.data.manager")
    private IFileDataManager<CommonProperties> propertiesDataManager;

    public TableManagerFactoryImpl(ApplicationContext context) {
        this.context = context;
    }

    private Map<Class<?>, AbstractTableManagerFactory> map;

    @PostConstruct
    public void init() {
        map = initMap();
    }

    @Override
    public AbstractTableManager<T> createTableManager(Class<?> cl) {
        map.forEach((key, value) -> value.setContext(context));
        return map.get(cl).getTable();
    }

    private Map<Class<?>, AbstractTableManagerFactory> initMap() {
        Map<Class<?>, AbstractTableManagerFactory> factoryMap = new HashMap<>();
        factoryMap.put(ResourcePasswordTableManager.class,
            new ResourcePasswordTableManagerFactory(resourcePasswordFileDataManager, propertiesDataManager));
        factoryMap.put(ExpenditureTableManager.class,
            new ExpenditureTableManagerFactory(expenditureFileDataManager, propertiesDataManager));
        return factoryMap;
    }
}
