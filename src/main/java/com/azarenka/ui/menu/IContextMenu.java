package com.azarenka.ui.menu;

import com.azarenka.domain.ResourcePassword;

import java.util.Map;
import java.util.function.BiConsumer;

import javafx.scene.control.TableColumn;

/**
 * Interface for .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 06/25/2023
 *
 * @author Anton Azarenka
 */
public interface IContextMenu<T> {

    void addNewRow();

    void remove(T type);

    Map<TableColumn<T, String>, BiConsumer<T, String>> getConsumerMap();

    void createContextMenu();
}
