package com.azarenka.service.api;

import com.azarenka.domain.properties.CommonProperties;

/**
 * Interface for .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 01/15/2023
 *
 * @author Anton Azarenka
 */
public interface IOptionsManager {

    CommonProperties getProperties();

    void saveOptions(CommonProperties properties);
}
