package com.azarenka.ui.menu;

import com.azarenka.domain.ResourcePassword;

import java.util.function.BiConsumer;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 06/25/2023
 *
 * @author Anton Azarenka
 */
public class ContextMenuInformationHandler<T> {

    private final BiConsumer<T, String> consumer;
    private final T item;
    private final String value;

    public ContextMenuInformationHandler(BiConsumer<T, String> consumer, T item,
                                         String value) {
        this.consumer = consumer;
        this.item = item;
        this.value = value;
    }

    public BiConsumer<T, String> getConsumer() {
        return consumer;
    }

    public T getItem() {
        return item;
    }

    public String getValue() {
        return value;
    }
}
