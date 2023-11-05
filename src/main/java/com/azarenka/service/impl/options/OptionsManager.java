package com.azarenka.service.impl.options;

import com.azarenka.domain.properties.CommonProperties;
import com.azarenka.service.api.IFileDataManager;
import com.azarenka.service.api.IOptionsManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

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
public class OptionsManager implements IOptionsManager {

    @Autowired
    @Qualifier("app.options.data.manager")
    private IFileDataManager<CommonProperties> fileDataManager;

    @Override
    public CommonProperties getProperties() {
        return fileDataManager.loadData();
    }

    @Override
    public void saveOptions(CommonProperties properties) {
        fileDataManager.saveData(properties);
    }
}
