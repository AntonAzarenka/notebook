package com.azarenka.ui.table.api;

import com.azarenka.domain.ResourcePassword;

import java.util.HashSet;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 * Interface for managing of table view.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
public interface ITableManager<T> {

    /**
     * Sets items to table view.
     *
     * @param type  type of table
     */
    void setItems(T type);

    /**
     * Resets table view.
     */
    void resetTable();

    /**
     * Refreshes table.
     * @param items
     */
    void refresh(ObservableList<T> items);

    /**
     * Loads items.
     */
    void loadItems();

    /**
     * Saves items.
     */
    void saveItems();
}
