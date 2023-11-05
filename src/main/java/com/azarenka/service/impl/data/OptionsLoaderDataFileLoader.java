package com.azarenka.service.impl.data;

import com.azarenka.domain.properties.CommonProperties;
import com.azarenka.exeptions.FileAppDataNotFoundException;
import com.azarenka.service.api.IFileDataManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 01/12/2023
 *
 * @author Anton Azarenka
 */
@Service("app.options.data.manager")
public class OptionsLoaderDataFileLoader extends AbstractFileDataLoaderManager implements IFileDataManager<CommonProperties> {

    @Value(value = "${app.common.properties.filename}")
    private Resource resource;

    @PostConstruct
    public void init() {
        setResource(resource);
    }

    @Override
    public CommonProperties loadData() {
        CommonProperties properties = null;
        try {
            properties = (CommonProperties) loadFile();
        } catch (FileAppDataNotFoundException e) {
            properties = saveData(new CommonProperties());
        }
        return properties;
    }

    @Override
    public CommonProperties saveData(CommonProperties properties) {
        return saveFile(properties) ? properties : null;
    }
}
