package com.azarenka.ui.table.api;

import com.azarenka.ui.table.impl.AbstractTableManager;

/**
 * Interface for .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 01/10/2023
 *
 * @author Anton Azarenka
 */
public interface ITableManagerFactory<T> {

    AbstractTableManager<T> createTableManager(Class<?> cl);
}
