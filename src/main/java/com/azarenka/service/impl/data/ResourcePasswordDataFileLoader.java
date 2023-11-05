package com.azarenka.service.impl.data;

import com.azarenka.domain.ResourcePassword;
import com.azarenka.exeptions.FileAppDataNotFoundException;
import com.azarenka.service.api.IFileDataManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

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
@Service("app.resource.password.data.manager")
public class ResourcePasswordDataFileLoader extends AbstractFileDataLoaderManager implements
    IFileDataManager<List<ResourcePassword>> {

    @Value(value = "${app.common.resource_password.filename}")
    private Resource resource;

    @PostConstruct
    public void init() {
        setResource(resource);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ResourcePassword> loadData() {
        List<ResourcePassword> properties = null;
        try {
            properties = (List<ResourcePassword>) loadFile();
        } catch (FileAppDataNotFoundException e) {
            properties = saveData(List.of());
        }
        return properties;
    }

    @Override
    public List<ResourcePassword> saveData(List<ResourcePassword> data) {
        return saveFile(data) ? data : null;
    }
}
