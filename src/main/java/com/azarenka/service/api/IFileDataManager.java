package com.azarenka.service.api;

/**
 * Interface for .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 01/12/2023
 *
 * @author Anton Azarenka
 */
public interface IFileDataManager<T> {

    /**
     * Loads data from the file
     * @return type
     */
    T loadData();

    /**
     * Saves data to the file
     * @return type
     */
    T saveData(T file);
}
