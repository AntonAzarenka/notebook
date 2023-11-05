package com.azarenka.ui.table.create;

import com.azarenka.ui.table.impl.AbstractTableManager;

import org.springframework.context.ApplicationContext;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 01/14/2023
 *
 * @author Anton Azarenka
 */
public abstract class AbstractTableManagerFactory<T> {

    abstract AbstractTableManager<T> getTable();

    abstract void setContext(ApplicationContext context);
}